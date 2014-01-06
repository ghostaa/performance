<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"   import="com.ibm.btt.base.*,java.util.*,com.ibm.btt.cs.ajax.AjaxUtils, com.ibm.btt.cs.html.remote.*"   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="utb" scope="page" class="com.ibm.btt.cs.html.DSEJspContextServices">
<%
	utb.initialize(request);			  		
%>
</jsp:useBean>	

<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<head></head>

<body class="tundra"   onLoad="document.getElementById('form').submit()" >
<h1>Please Wait .....</h1>

<% 
	String dse_remoteUrl=null;
	HashMap dataMap=null; //input data map to remote flow
	KeyedCollection parentEnvData= (KeyedCollection) utb.getContext().tryGetElementAt(RemoteConstant.REMOTE_PARENT_ENVDATA_KEY);
	
	if (parentEnvData == null){ // redirect to remote flow
		String dse_remoteFlowID=(String) utb.getContext().getValueAt(RemoteConstant.REMOTE_FLOW_ID);	  
	  	dse_remoteUrl=(String) utb.getContext().getValueAt(RemoteConstant.REMOTE_URL);
	  	boolean flag = false;
		if(utb.getContext().getParent() == null){
			utb.getContext().chainTo(utb.getSessionContext());
			flag= true;
		}
	  	Tag mapperTag=(Tag) utb.getContext().tryGetValueAt(RemoteConstant.REMOTE_INPUT_MAPPER);
	  	if (mapperTag != null) {
		  	RemoteDataMapper remoteMapper=new RemoteDataMapper(mapperTag, null);
		  	Context tempContext = ContextFactory.createContext();
		  	remoteMapper.mapContents(utb.getContext(), tempContext);
		  	dataMap=remoteMapper.getResultMap(); 
	  	}
	  	if(flag){
			utb.getContext().unchain();
		}
  	} else { // redirect back to parent flow
		dse_remoteUrl=(String) parentEnvData.getValueAt(RemoteConstant.REMOTE_PARENT_URL);
	  	String ctxjsonStr=AjaxUtils.convertToJson( utb.getContext(), Locale.getDefault(), "default").replace('"','\'');
  	}
%>	
<form  id="form"   action="<%=dse_remoteUrl%>" method="post" title="document.getElementById('form').submit()">
	<%if (parentEnvData ==null) { %> 
		<input type="hidden" name="dse_remoteFlowID"  value="<%=(String) utb.getContext().getValueAt(RemoteConstant.REMOTE_FLOW_ID)%>" >
		<input type="hidden" name="dse_remote_parent_sessionId"  value="<%=(String) utb.getContext().getValueAt("dse_sessionId")%>" >
		<input type="hidden" name="dse_remote_parent_processorId"  value="<%=(String) utb.getContext().getValueAt("dse_processorId")%>" >
		<input type="hidden" name="dse_remote_parent_processorState"  value="<%=(String) utb.getContext().getValueAt("dse_processorState")%>" >
		<input type="hidden" name="dse_remote_parent_operationName"  value="<%=(String) utb.getContext().getValueAt("dse_operationName")%>" >
		<input type="hidden" name="dse_remote_parent_applicationId"  value="<%=(String) utb.getContext().getValueAt("dse_applicationId")%>" >
		<input type="hidden" name="dse_remote_parent_URL"  value="<%=(String) utb.getContext().getValueAt(RemoteConstant.REMOTE_PARENT_URL).toString()%>" >
		<input type="hidden" name="dse_remote_parent_pageId"  value="<%=(String)utb.getSessionContext().getValueAt("dse_pageId")%>" >
		<input type="hidden" name="dse_operationName"  value="<%=(String) utb.getContext().getValueAt(RemoteConstant.REMOTE_FLOW_ID)%>" >
		<input type="hidden" name="dse_nextEventName"  value="start" >
		<%  
			if (dataMap!=null) {
				Iterator iter=dataMap.keySet().iterator();
				while (iter.hasNext()){
					Object key=iter.next();	
					Object value=dataMap.get(key);
					if (value!=null) {
		%> 
		<input type="hidden" name="<%=key %>"  value="<%=value%>"  >
	<%}}}}else { %>
		<input type="hidden" name="dse_sessionId"  value="<%=(String) parentEnvData.getValueAt("dse_remote_parent_sessionId")%>" >
		<input type="hidden" name="dse_processorId"  value="<%=(String) parentEnvData.getValueAt("dse_remote_parent_processorId")%>" >
		<input type="hidden" name="dse_processorState"  value="<%=(String) parentEnvData.getValueAt("dse_remote_parent_processorState")%>" >
		<input type="hidden" name="dse_operationName"  value="<%=(String) parentEnvData.getValueAt("dse_remote_parent_operationName")%>" >
		<input type="hidden" name="dse_applicationId"  value="<%=(String) parentEnvData.getValueAt("dse_remote_parent_applicationId")%>" >
		<input type="hidden" name="dse_pageId"  value="<%=(String) parentEnvData.getValueAt("dse_remote_parent_pageId")%>" >
		<input type="hidden" name="dse_nextEventName"  value="<%=(String) utb.getContext().getValueAt("dse_nextEventName")%>" >
		<input type="hidden" name="dse_remoteContextJson"  value="<%=AjaxUtils.convertToJson( utb.getContext(), Locale.getDefault(), "default").replace('"','\'') %>" >
	<%}%>
</form>
</body>
</html>