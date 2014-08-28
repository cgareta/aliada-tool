// ALIADA - Automatic publication under Linked Data paradigm
//          of library and museum data
//
// Component: aliada-user-interface
// Responsible: ALIADA Consortium

package eu.aliada.gui.action;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opensymphony.xwork2.ActionSupport;

import eu.aliada.gui.parser.XmlParser;
import eu.aliada.gui.rdbms.DBConnectionManager;
import eu.aliada.shared.log.Log;

/**
 * @author iosa
 * @since 1.0
 */
public class LinkingAction extends ActionSupport {

    private boolean showCheckButton;
    private boolean linkingStarted;
    private boolean notFiles;
    private HashMap<Integer, String> datasets;
    private HashMap<Integer, String> filesToLink;
    private String fileToLink;
    private List<Integer> rdfizerJobs;

    private final Log logger = new Log(LinkingAction.class);

    public String execute() {
        rdfizerJobs = (List<Integer>) ServletActionContext.getRequest()
                .getSession().getAttribute("filesToLink");
        if (rdfizerJobs == null) {
            setNotFiles(true);
        } else {
            setNotFiles(false);
            setFilesToLink(getFiles(rdfizerJobs));
        }
        setLinkingStarted(false);
        setShowCheckButton(false);
        return getDatasetsDb();
    }

    private HashMap<Integer, String> getFiles(List<Integer> rdfizerJobs) {
        Iterator<Integer> iterator = rdfizerJobs.iterator();
        filesToLink = new HashMap<Integer, String>();
        Connection con;
        try {
            con = new DBConnectionManager().getConnection();
            while (iterator.hasNext()) {
                Statement st;
                st = con.createStatement();
                int id = iterator.next();
                ResultSet rs = st
                        .executeQuery("select datafile from aliada.rdfizer_job_instances WHERE job_id="
                                + id);
                if (rs.next()) {
                    filesToLink.put(id, rs.getString(1));
                }
                rs.close();
                st.close();
            }
            con.close();
        } catch (SQLException e) {
            logger.debug("SQL error" + e);
        }
        return filesToLink;
    }

    private String getDatasetsDb() {
        datasets = new HashMap();
        Connection con;
        try {
            con = new DBConnectionManager().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st
                    .executeQuery("select * from ALIADA.t_external_dataset");
            while (rs.next()) {
                int code = rs.getInt("external_dataset_code");
                String name = rs.getString("external_dataset_name");
                this.datasets.put(code, name);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            logger.debug("SQL error" + e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String startLinking() {
        logger.debug("Started linking");
        if (fileToLink != null) {
            logger.debug("file selected to link: " + fileToLink);
            createJobLinking(fileToLink);
            setShowCheckButton(true);
            setLinkingStarted(true);
            return getDatasetsDb();
        } else {
            rdfizerJobs = (List<Integer>) ServletActionContext.getRequest()
                    .getSession().getAttribute("filesToLink");
            if (rdfizerJobs == null) {
                setNotFiles(true);
            } else {
                setNotFiles(false);
                setFilesToLink(getFiles(rdfizerJobs));
            }
            addActionError(getText("linking.file.not.selected"));
            return getDatasetsDb();
        }
    }

    private void createJobLinking(String fileToLink) {
        int addedId = 0;
        Connection connection = null;
        connection = new DBConnectionManager().getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery("select sparql_endpoint_uri, sparql_endpoint_login, sparql_endpoint_password, graph_uri, linking_config_file, tmp_dir, linking_client_app_bin_dir from organisation_data_server_configuration");
            if (rs.next()) {
                PreparedStatement preparedStatement;
                preparedStatement = connection
                        .prepareStatement(
                                "INSERT INTO linksdiscovery_job_instances (input_uri, input_login, input_password, input_graph, output_uri, output_login, output_password, output_graph, config_file, rdf_sink_folder, rdf_sink_login, rdf_sink_password, tmp_dir, client_app_bin_dir) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,
                        rs.getString("sparql_endpoint_uri"));
                preparedStatement.setString(2, null);
                preparedStatement.setString(3, null);
                preparedStatement.setString(4, rs.getString("graph_uri"));
                preparedStatement.setString(5,
                        rs.getString("sparql_endpoint_uri"));
                preparedStatement.setString(6,
                        rs.getString("sparql_endpoint_login"));
                preparedStatement.setString(7,
                        rs.getString("sparql_endpoint_password"));
                preparedStatement.setString(8, rs.getString("graph_uri"));
                preparedStatement.setString(9,
                        rs.getString("linking_config_file"));
                preparedStatement.setString(10, null);
                preparedStatement.setString(11, null);
                preparedStatement.setString(12, null);
                preparedStatement.setString(13, rs.getString("tmp_dir"));
                preparedStatement.setString(14,
                        rs.getString("linking_client_app_bin_dir"));
                preparedStatement.executeUpdate();
                ResultSet rs2 = preparedStatement.getGeneratedKeys();
                if (rs2.next()) {
                    addedId = (int) rs2.getInt(1);
                    logger.debug("Added job id: " + addedId);
                }
                rs2.close();
                preparedStatement.close();
                connection.close();
                URL url;
                HttpURLConnection conn = null;
                try {
                    url = new URL("http://localhost:8890/links-discovery/jobs/");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    String param = "jobid=" + addedId;
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(
                            conn.getOutputStream());
                    wr.writeBytes(param);
                    wr.flush();
                    wr.close();
                    if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                        throw new RuntimeException(
                                "Failed : HTTP error code : "
                                        + conn.getResponseCode());
                    } else {
                        ServletActionContext.getRequest().getSession()
                                .setAttribute("fileToLink", fileToLink);
                        ServletActionContext.getRequest().getSession()
                                .setAttribute("fileToLinkId", addedId);
                    }
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            logger.debug("SQL error" + e);
        }

    }

    public String startLDS() {
        fileToLink = (String) ServletActionContext.getRequest().getSession()
                .getAttribute("fileToLink");
        if (fileToLink != null) {
            createJobLDS(fileToLink);
            setShowCheckButton(true);
            setLinkingStarted(true);
            return getDatasetsDb();
        } else {
            return getDatasetsDb();
        }
    }

    private void createJobLDS(String fileToLink) {
        logger.debug("Creating link server job");
        int addedId = 0;
        Connection connection = null;
        connection = new DBConnectionManager().getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery("select store_ip,store_sql_port,sql_login, sql_password, graph_uri, dataset_base, isql_command_path, isql_commands_file, isql_commands_file_default from organisation_data_server_configuration");
            if (rs.next()) {
                PreparedStatement preparedStatement = connection
                        .prepareStatement(
                                "INSERT INTO linkeddataserver_job_instances (store_ip,store_sql_port,sql_login,sql_password,graph,dataset_base,isql_command_path,isql_commands_file,isql_commands_file_default) VALUES(?,?,?,?,?,?,?,?,?)",
                                PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, rs.getString("store_ip"));
                preparedStatement.setInt(2, rs.getInt("store_sql_port"));
                preparedStatement.setString(3, rs.getString("sql_login"));
                preparedStatement.setString(4, rs.getString("sql_password"));
                preparedStatement.setString(5, rs.getString("graph_uri"));
                preparedStatement.setString(6, rs.getString("dataset_base"));
                preparedStatement.setString(7, rs.getString("isql_command_path"));
                preparedStatement.setString(8, rs.getString("isql_commands_file"));
                preparedStatement.setString(9, rs.getString("isql_commands_file_default"));
                preparedStatement.executeUpdate();
                ResultSet rs2 = preparedStatement.getGeneratedKeys();
                if (rs2.next()) {
                    addedId = (int) rs2.getInt(1);
                    logger.debug("Added linked server job id: " + addedId);
                }
                rs2.close();
                preparedStatement.close();          
                URL url;
                HttpURLConnection conn = null;
                try {
                    url = new URL("http://localhost:8889/lds/jobs/");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    String param = "jobid=" + addedId;
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(param);
                    wr.flush();
                    wr.close();
                    if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    } else {
                        ServletActionContext.getRequest().getSession()
                                .setAttribute("fileToLink", fileToLink);
                        ServletActionContext.getRequest().getSession()
                                .setAttribute("fileToLinkId", addedId);
                    }
                    conn.disconnect();
                    getInfoLDS();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rs.close();
            statement.close();
            connection.close();  
            } catch (SQLException e) {
            logger.debug("SQL error" + e);
        }
    }

    private void getInfoLDS() throws IOException {
        int fileToLinkId = (int) ServletActionContext.getRequest().getSession()
                .getAttribute("fileToLinkId");
        URL url = new URL("http://localhost:8889/lds/jobs/" + fileToLinkId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/xml");
        if (conn.getResponseCode() != 202) {
            logger.debug("Failed : HTTP error code : " + conn.getResponseCode());
        }
        try {
            XmlParser parser = new XmlParser();
            Document doc = parser.parseXML(conn.getInputStream());
            NodeList readNode = doc.getElementsByTagName("startDate");
            String startDate = readNode.item(0).getTextContent();
            readNode = doc.getElementsByTagName("status");
            String status = readNode.item(0).getTextContent();
            logger.debug("Readed Start Date:" + startDate);
            logger.debug("Readed Status:" + status);
        } catch (Exception e) {
            logger.error("Failed reading xml" + e);
            conn.disconnect();
        }
        conn.disconnect();
    }

    /**
     * @return Returns the datasets.
     * @exception
     * @since 1.0
     */
    public HashMap<Integer, String> getDatasets() {
        return datasets;
    }

    /**
     * @param datasets
     *            The datasets to set.
     * @exception
     * @since 1.0
     */
    public void setDatasets(HashMap<Integer, String> datasets) {
        this.datasets = datasets;
    }

    /**
     * @return Returns the showCheckButton.
     * @exception
     * @since 1.0
     */
    public boolean isShowCheckButton() {
        return showCheckButton;
    }

    /**
     * @param showCheckButton
     *            The showCheckButton to set.
     * @exception
     * @since 1.0
     */
    public void setShowCheckButton(boolean showCheckButton) {
        this.showCheckButton = showCheckButton;
    }

    /**
     * @return Returns the filesToLink.
     * @exception
     * @since 1.0
     */
    public HashMap<Integer, String> getFilesToLink() {
        return filesToLink;
    }

    /**
     * @param filesToLink
     *            The filesToLink to set.
     * @exception
     * @since 1.0
     */
    public void setFilesToLink(HashMap<Integer, String> filesToLink) {
        this.filesToLink = filesToLink;
    }

    /**
     * @return Returns the notFiles.
     * @exception
     * @since 1.0
     */
    public boolean isNotFiles() {
        return notFiles;
    }

    /**
     * @param notFiles
     *            The notFiles to set.
     * @exception
     * @since 1.0
     */
    public void setNotFiles(boolean notFiles) {
        this.notFiles = notFiles;
    }

    /**
     * @return Returns the fileToLink.
     * @exception
     * @since 1.0
     */
    public String getFileToLink() {
        return fileToLink;
    }

    /**
     * @param fileToLink
     *            The fileToLink to set.
     * @exception
     * @since 1.0
     */
    public void setFileToLink(String fileToLink) {
        this.fileToLink = fileToLink;
    }

    /**
     * @return Returns the linkingStarted.
     * @exception
     * @since 1.0
     */
    public boolean isLinkingStarted() {
        return linkingStarted;
    }

    /**
     * @param linkingStarted
     *            The linkingStarted to set.
     * @exception
     * @since 1.0
     */
    public void setLinkingStarted(boolean linkingStarted) {
        this.linkingStarted = linkingStarted;
    }

}
