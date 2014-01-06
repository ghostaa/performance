<%@page import="com.ibm.btt.base.FunctionalErrorTraceHelper"%>
<%@page import="com.ibm.btt.cs.html.HtmlConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<tt style="color: red">
An internal error has occurred. Please review the traces.
 <%
	if (null != request.getAttribute(HtmlConstants.EXCEPTION)) {
		Exception e = (Exception) request
				.getAttribute(HtmlConstants.EXCEPTION);
		StringBuffer sb = new StringBuffer();
		sb.append("<hr/>");
		sb.append("Detailed exception message is:<br/>");
		if (null != e.getMessage()) {
			sb.append(e.getMessage().replaceAll("<","< ").replaceAll(">"," >"));
		}
		out.println(sb.toString());
		// Show additional info if possible
		String[] msgs = FunctionalErrorTraceHelper.getMessagesForException(e);
		if (msgs.length > 0) {
			out.println("<br/><br/>Additional error information:<br/>");
			for (String msg : msgs) {
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + msg + "<br/>");
			}
		}
	}
%>
</tt>
</body>
</html>