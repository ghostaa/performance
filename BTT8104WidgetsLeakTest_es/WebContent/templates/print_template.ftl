<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ibm.btt.dojo.tag.ChannelParameterHelper" %>
<%@ page import="com.ibm.btt.base.IndexedCollection" %>
<%@ page import="com.ibm.btt.base.DataElement" %>
<jsp:useBean id="utb" scope="page" class="com.ibm.btt.cs.html.DSEJspContextServices">
	<%
		utb.initialize(request);			  
	%>
</jsp:useBean>	
${content}
