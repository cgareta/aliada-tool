<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="html"%>

<html:form id="managing">
	<h2 class="pageTitle"><html:text name="message.error"/></h2>
	<%@ include file="errorContent.jsp" %>
	<html:submit action="backToManage" cssClass="submitButton button" key="back" />
</html:form>

