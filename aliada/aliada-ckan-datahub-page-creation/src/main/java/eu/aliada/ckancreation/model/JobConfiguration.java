// ALIADA - Automatic publication under Linked Data paradigm
//          of library and museum data
//
// Component: aliada-ckan-datahub-page-creation
// Responsible: ALIADA Consortium
package eu.aliada.ckancreation.model;

/**
 * CKAN Page Creation job configuration.
 * 
 * @author Idoia Murua
 * @since 2.0
 */
public class JobConfiguration {
	/** Job identification number. */
	private Integer id;
	/** CKAN API URL. */
	private String ckanApiURL;
	/** CKAN API Key. */
	private String ckanApiKey;
	/** IP address of the machine where the RDF store resides. */
	private String storeIp;
	/** Port number of the RDF store for SQL access. */
	private int storeSqlPort;
	/** The login of the SQL access. */
	private String sqlLogin;
	/** The password of the SQL access. */
	private String sqlPassword;
	/** Full path to the ISQL command. */
	private String isqlCommandPath;
	/** Path for graphs dump. */
	private String dumpFolderPath;
	/** URL for graphs dump. */
	private String dumpFolderURL;
	/** Aliada ontology URL. */
	private String aliadaOntologyURL;

	/** ORGANIZATION **/
	/** The organization name. */
	private String orgName;
	/** The organization title. */
	private String orgTitle;
	/** The organization description. */
	private String orgDescription;
	/** The organization image URL. */
	private String orgImageURL;
	/** The organization home page. */
	private String orgHomePage;
	
	/** DATASET **/
	/** The dataset name. */
	private String datasetName;
	/** The dataset  author. */
	private String datasetAuthor;
	/** The dataset description. */
	private String datasetNotes;
	/** The dataset source URL. */
	private String datasetSourceURL;
	/** The SPARQL endpoint URL. */
	private String SPARQLendpoint;
	/** License id in CKAN. See opendefinition.org .*/
	private String licenseCKANId;
	/** License URL.*/
	private String licenseURL;
	/** Graph URI. */
	private String graphURI;
	/** Path of the folder where to store the dataset description file. */
	private String datasetDescFolderPath;
	/** URL of the folder where to store the dataset description file. */
	private String datasetDescFolderURL;

    
	/**
	 * Returns the identifier of this job configuration.
	 * 
	 * @return The identifier of this job configuration.
	 * @since 2.0
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Sets the identifier of this job configuration.
	 * 
	 * @param id The identifier of this job configuration.
	 * @since 2.0
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Returns the CKAN API URL.
	 * 
	 * @return The CKAN API URL.
	 * @since 2.0
	 */
	public String getCkanApiURL() {
		return ckanApiURL;
	}
	/**
	 * Sets the CKAN API URL.
	 * 
	 * @param ckanApiURL CKAN API URL.
	 * @since 2.0
	 */
	public void setCkanApiURL(final String ckanApiURL) {
		this.ckanApiURL = ckanApiURL;
	}

	/**
	 * Returns the CKAN API Key.
	 * 
	 * @return The CKAN API Key.
	 * @since 2.0
	 */
	public String getCkanApiKey() {
		return ckanApiKey;
	}
	/**
	 * Sets the CKAN API Key.
	 * 
	 * @param ckanApiKey CKAN API Key.
	 * @since 2.0
	 */
	public void setCkanApiKey(final String ckanApiKey) {
		this.ckanApiKey = ckanApiKey;
	}

	/**
	 * Returns the IP of the RDF store.
	 * 
	 * @return The IP of the RDF store
	 * @since 2.0
	 */
	public String getStoreIp() {
		return storeIp;
	}
	/**
	 * Sets the IP of the RDF store.
	 * 
	 * @param storeIp The IP of the RDF store.
	 * @since 2.0
	 */
	public void setStoreIp(final String storeIp) {
		this.storeIp = storeIp;
	}

	/**
	 * Returns the port for SQL data access.
	 * 
	 * @return The port for SQL data access.
	 * @since 2.0
	 */
	public int getStoreSqlPort() {
		return storeSqlPort;
	}
	/**
	 * Sets the port for SQL data access.
	 * 
	 * @param storeSqlPort The port for SQL data access.
	 * @since 2.0
	 */
	public void setStoreSqlPort(final int storeSqlPort) {
		this.storeSqlPort = storeSqlPort;
	}

	/**
	 * Returns the login required for authentication in the RDF store.
	 * 
	 * @return The login required for authentication in the RDF store.
	 * @since 2.0
	 */
	public String getSqlLogin() {
		return sqlLogin;
	}
	/**
	 * Sets the login required for authentication in the RDF store.
	 * 
	 * @param sqlLogin The login required for authentication in the RDF store.
	 * @since 2.0
	 */
	public void setSqlLogin(final String sqlLogin) {
		this.sqlLogin = sqlLogin;
	}
	
	/**
	 * Returns the password required for authentication in the RDF store.
	 * 
	 * @return The password required for authentication in the RDF store.
	 * @since 2.0
	 */
	public String getSqlPassword() {
		return sqlPassword;
	}
	/**
	 * Sets the password required for authentication in the RDF store.
	 * 
	 * @param sqlPassword Password required for authentication in the RDF store.
	 * @since 2.0
	 */
	public void setSqlPassword(final String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}	
	
	/**
	 * Returns the path of the ISQL command of the RDF store.
	 * 
	 * @return The path of the ISQL command of the RDF store.
	 * @since 2.0
	 */
	public String getIsqlCommandPath() {
		return isqlCommandPath;
	}
	/**
	 * Sets the path of the ISQL command of the RDF store.
	 * 
	 * @param isqlCommandPath The path the ISQL command of the RDF store.
	 * @since 2.0
	 */
	public void setIsqlCommandPath(final String isqlCommandPath) {
		this.isqlCommandPath = isqlCommandPath;
	}		

	/**
	 * Returns the path of the folder for the graphs dump.
	 * 
	 * @return The path of the folder for the graphs dump.
	 * @since 2.0
	 */
	public String getDumpFolderPath() {
		return dumpFolderPath;
	}
	/**
	 * Sets the path of the folder for the graphs dump.
	 * 
	 * @param dumpFolderPath The path of the folder for the graphs dump.
	 * @since 2.0
	 */
	public void setDumpFolderPath(final String dumpFolderPath) {
		this.dumpFolderPath = dumpFolderPath;
	}

	/**
	 * Returns the URL for the folder of the graphs dump.
	 * 
	 * @return The URL for the folder of the graphs dump.
	 * @since 2.0
	 */
	public String getDumpFolderURL() {
		return dumpFolderURL;
	}
	/**
	 * Sets the URL for the folder of the graphs dump.
	 * 
	 * @param dumpURL The URL for the folder of the graphs dump.
	 * @since 2.0
	 */
	public void setDumpFolderURL(final String dumpFolderURL) {
		this.dumpFolderURL = dumpFolderURL;
	}

	/**
	 * Returns the ALIADA ontology URL.
	 * 
	 * @return The ALIADA ontology URL.
	 * @since 2.0
	 */
	public String getAliadaOntologyURL() {
		return aliadaOntologyURL;
	}
	/**
	 * Sets the ALIADA ontology URL.
	 * 
	 * @param aliadaOntologyURL The ALIADA ontology URL.
	 * @since 2.0
	 */
	public void setAliadaOntologyURL(final String aliadaOntologyURL) {
		this.aliadaOntologyURL = aliadaOntologyURL;
	}

	/**
	 * Returns the organization name.
	 * 
	 * @return The organization name.
	 * @since 2.0
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * Sets the organization name.
	 * 
	 * @param orgName Organization name.
	 * @since 2.0
	 */
	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}

	/**
	 * Returns the organization title.
	 * 
	 * @return The organization title.
	 * @since 2.0
	 */
	public String getOrgTitle() {
		return orgTitle;
	}
	/**
	 * Sets the organization title.
	 * 
	 * @param orgTitle Organization title.
	 * @since 2.0
	 */
	public void setOrgTitle(final String orgTitle) {
		this.orgTitle = orgTitle;
	}

	/**
	 * Returns the organization description.
	 * 
	 * @return The organization description.
	 * @since 2.0
	 */
	public String getOrgDescription() {
		return orgDescription;
	}
	/**
	 * Sets the organization description.
	 * 
	 * @param orgDescription Organization description.
	 * @since 2.0
	 */
	public void setOrgDescription(final String orgDescription) {
		this.orgDescription = orgDescription;
	}
	
	/**
	 * Returns the organization image URL.
	 * 
	 * @return The organization image URL.
	 * @since 2.0
	 */
	public String getOrgImageURL() {
		return orgImageURL;
	}
	/**
	 * Sets the organization image URL.
	 * 
	 * @param orgImageURL Organization image URL.
	 * @since 2.0
	 */
	public void setOrgImageURL(final String orgImageURL) {
		this.orgImageURL = orgImageURL;
	}
	
	/**
	 * Returns the organization home page.
	 * 
	 * @return The organization home page.
	 * @since 2.0
	 */
	public String getOrgHomePage() {
		return orgHomePage;
	}
	/**
	 * Sets the organization home page.
	 * 
	 * @param orgHomePage Organization home page.
	 * @since 2.0
	 */
	public void setOrgHomePage(final String orgHomePage) {
		this.orgHomePage = orgHomePage;
	}

	/**
	 * Returns the dataset name.
	 * 
	 * @return The dataset name.
	 * @since 2.0
	 */
	public String getDatasetName() {
		return datasetName;
	}
	/**
	 * Sets the dataset name.
	 * 
	 * @param datasetName dataset name.
	 * @since 2.0
	 */
	public void setDatasetName(final String datasetName) {
		this.datasetName = datasetName;
	}

	/**
	 * Returns the dataset author.
	 * 
	 * @return The dataset author.
	 * @since 2.0
	 */
	public String getDatasetAuthor() {
		return datasetAuthor;
	}
	/**
	 * Sets the dataset author.
	 * 
	 * @param datasetAuthor dataset author.
	 * @since 2.0
	 */
	public void setDatasetAuthor(final String datasetAuthor) {
		this.datasetAuthor = datasetAuthor;
	}

	/**
	 * Returns the dataset description.
	 * 
	 * @return The dataset description.
	 * @since 2.0
	 */
	public String getDatasetNotes() {
		return datasetNotes;
	}
	/**
	 * Sets the dataset description.
	 * 
	 * @param datasetNotes dataset description.
	 * @since 2.0
	 */
	public void setDatasetNotes(final String datasetNotes) {
		this.datasetNotes = datasetNotes;
	}

	/**
	 * Returns the dataset source URL.
	 * 
	 * @return The dataset source URL.
	 * @since 2.0
	 */
	public String getDatasetSourceURL() {
		return datasetSourceURL;
	}
	/**
	 * Sets the dataset source URL.
	 * 
	 * @param datasetURL dataset source URL.
	 * @since 2.0
	 */
	public void setDatasetSourceURL(final String datasetSourceURL) {
		this.datasetSourceURL = datasetSourceURL;
	}

	/**
	 * Returns the URI of the SPARQL endpoint of the dataset.
	 * 
	 * @return The URI of the SPARQL endpoint of the dataset.
	 * @since 2.0
	 */
	public String getSPARQLEndpoint() {
		return SPARQLendpoint;
	}
	/**
	 * Sets the URI of the SPARQL endpoint of the dataset.
	 * 
	 * @param SPARQLendpoint The URI of the SPARQL endpoint of the dataset.
	 * @since 2.0
	 */
	public void setSPARQLEndpoint(final String SPARQLendpoint) {
		this.SPARQLendpoint = SPARQLendpoint;
	}


	/**
	 * Returns the CKAN id of the License of the dataset. See opendefinition.org .
	 * 
	 * @return The CKAN id of the License of the dataset.
	 * @since 2.0
	 */
	public String getLicenseCKANId() {
		return this.licenseCKANId;
	}
	/**
	 * Sets the CKAN id of the License of the dataset. See opendefinition.org .
	 * 
	 * @param licenseCKANId CKAN id of the License of the dataset.
	 * @since 2.0
	 */
	public void setLicenseCKANId(String licenseCKANId) {
		this.licenseCKANId = licenseCKANId;
	}

	/**
	 * Returns the license URL of the dataset. 
	 * 
	 * @return The license URL of the dataset.
	 * @since 2.0
	 */
	public String getLicenseURL() {
		return this.licenseURL;
	}
	/**
	 * Sets the license URL of the dataset.
	 * 
	 * @param licenseURL license URL of the dataset.
	 * @since 2.0
	 */
	public void setLicenseURL(String licenseURL) {
		this.licenseURL = licenseURL;
	}

	/**
	 * Returns the graph URI.
	 * 
	 * @return The graph URI.
	 * @since 2.0
	 */
	public String getGraphURI() {
		return graphURI;
	}
	/**
	 * Sets the graph URI.
	 * 
	 * @param uri The graph URI.
	 * @since 2.0
	 */
	public void setGraphURI(final String graphURI) {
		this.graphURI = graphURI;
	}

	/**
	 * Returns the path of the folder where to store the dataset description file.
	 * 
	 * @return The path of the folder where to store the dataset description file.
	 * @since 2.0
	 */
	public String getDatasetDescFolderPath() {
		return datasetDescFolderPath;
	}
	/**
	 * Sets the path of the folder where to store the dataset description file.
	 * 
	 * @param datasetDescFolderPath The path of the folder where to store the dataset description file.
	 * @since 2.0
	 */
	public void setDatasetDescFolderPath(final String datasetDescFolderPath) {
		this.datasetDescFolderPath = datasetDescFolderPath;
	}

	/**
	 * Returns the URL of the folder where to store the dataset description file.
	 * 
	 * @return The URL of the folder where to store the dataset description file.
	 * @since 2.0
	 */
	public String getDatasetDescFolderURL() {
		return datasetDescFolderURL;
	}
	/**
	 * Sets the URL of the folder where to store the dataset description file.
	 * 
	 * @param datasetDescURL The URL of the folder where to store the dataset description file.
	 * @since 2.0
	 */
	public void setDatasetDescFolderURL(final String datasetDescFolderURL) {
		this.datasetDescFolderURL = datasetDescFolderURL;
	}

}
