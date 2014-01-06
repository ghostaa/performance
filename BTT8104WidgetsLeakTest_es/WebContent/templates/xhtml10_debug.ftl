<#if page_comments??>
<%--${page_comments}--%>
</#if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="utb" scope="page" class="com.ibm.btt.cs.html.DSEJspContextServices">
	<%
		utb.initialize(request);			  
	%>
</jsp:useBean>	
<%
	java.util.Locale locale = (java.util.Locale)utb.getValue("dse_locale");
	if(locale == null) {
		locale = request.getLocale();
	}

	String language = locale.getLanguage();
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="<%=language %>" xml:lang="<%=language %>">
<!-- Generated from ${xui_file} by ${user}, on ${date} -->
<head>
<%@ taglib uri="/WEB-INF/xhtml.tld" prefix="bttdojo"%>
<%@ page import="com.ibm.btt.cs.html.JSPUtil" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title><#if page_title??>${page_title}</#if></title>
<style type="text/css">
@import "js/dojo/resources/dojo.css";
@import "js/dijit/themes/claro/claro.css";
@import "js/dojox/grid/enhanced/resources/claro/EnhancedGrid.css";
@import "css/dijit/message.css";
@import "js/com/ibm/btt/dijit/templates/FileUpload.css";
@import "js/dijit/themes/claro/document.css";
@import "js/com/ibm/btt/dijit/templates/Grid.css";
@import "js/com/ibm/btt/dijit/templates/ScreenCover.css";
@import "js/com/ibm/btt/dijit/templates/Group.css";
@import "js/com/ibm/btt/dijit/templates/Label.css";
@import "js/com/ibm/btt/dijit/templates/Ems.css";
@import "js/dojox/form/resources/CheckedMultiSelect.css";
@import "js/com/ibm/btt/dijit/templates/tableScrollbar.css";
@import "js/com/ibm/btt/dijit/templates/Keyboard.css";

<#list cssFiles as css>
@import "${css}";
</#list>

.dojoxGrid table { margin: 0; }
.dj_gecko .dijitTextBoxReadOnly INPUT.dijitInputInner {
    -moz-user-input: auto;
}
.dj_gecko .dijitTextBoxReadOnlyFocused INPUT.dijitInputInner {
	-moz-user-input: none;
}
/** override the default CSS(claro) of dojo, otherwise, 
	on Chrome, the value of disabled text box can't be told from its background
**/
.dj_webkit .dijitTextBoxDisabled INPUT {
	color: #777777;
}
html, body {
	height:100%;
}
.BTTTableStyle {
	display:table;
	border-collapse:collapse;
}

.BTTRowStyle {
	display:table-row;
}

.BTTCellStyle {
	display:table-cell;
	padding:1px;
}
.dojoxGridInvisible td{
	height:0px;
	padding-top:0px;
	padding-bottom:0px;
}
.dojoxGridInvisible th{
	height:0px;
	border:0px solid black;
	padding-top:0px;
	padding-bottom:0px;
}
.claro .dojoxGridInvisible .dojoxGridCell{
	padding-top:0px;
	padding-bottom:0px;
	border:0px;
}
</style>
<script type="text/javascript">
	var djConfig =  {
		baseUrl:"js/dojo/",
		<bttdojo:locale/>,
		isDebug: true, 
		parseOnLoad: true
	};
</script>

<script type="text/javascript" src="js/dojo/dojo_BTT.js"></script>
<script type="text/javascript" src="js/com/ibm/btt/btt.js"></script>
<script type="text/javascript" src="js/config/globalConfiguration.js"></script>
<script type="text/javascript">
// remove the role=presentation in data grid for KWCAG
com.ibm.btt.util.GlobalConfigurationUtil.setValue("kwcag.grid.role.presentation", false);
// add the scope=col in the <th> of data grid for KWCAG
com.ibm.btt.util.GlobalConfigurationUtil.setValue("kwcag.grid.th.scope", "col");
// use the maximumLength of context data as the maxLength automatically
// in this case, the effective maxLength is the smaller one of ctx.maxLength and xui.maxLength
com.ibm.btt.util.GlobalConfigurationUtil.setValue("com.ibm.btt.stringtextbox.maxlength", "auto");

// add the title attr to the background <iframe> of dojo for KWCAG
// com.ibm.btt.util.GlobalConfigurationUtil.setValue("kwcag.bgiframe.title", true);
if (!dijit._frames.rawPop){
	dijit._frames.bgititle = "background iframe"
//	dijit._frames.bgititle = I18nUtil.getI18nString("%nls.nlsfile/bgIframeTitle");
	dijit._frames.size = 0; 
	dijit._frames.rawPop = dijit._frames.pop;
	dijit._frames.pop = function(){
		var iframe = dijit._frames.rawPop();
		if (!iframe.title){
			iframe.title = dijit._frames.bgititle + " " + ++dijit._frames.size;
		}
		return iframe;
	};
}

dojo.addOnLoad(function(){
	if(window.dse_widgetsConfig){
		for(var idx = window.dse_widgetsConfig.length -1; idx>=0; idx --){
			var config = window.dse_widgetsConfig[idx];
			if(config.id){
				try{
					dojo.parser.instantiate([dojo.byId(config.id)], config);
				} catch(e) {
					console.error(e);
				}
			}
		}
	}
	var head = document.getElementsByTagName('head')[0].innerHTML;
	var titles = head.match(/<title>(.*)<\/title>/i);
	if(titles){
		var newtitle = StringUtil.escapeEnterWrap(I18nUtil.getI18nString(titles[1]));
		document.title = newtitle;
	}	
});

dojo.addOnLoad(function(){
	dojo.style(document.body, "visibility", "");
});
</script>

<script type="text/javascript">
	if(!window.engine){
		<%if(utb.ajaxNavigationEnabled()){%>
			window.engine = new com.ibm.btt.event.NavigationEngine();
		<%}else{%>
			window.engine = new com.ibm.btt.event.Engine();
		<%}%>
		engine.setMonitor(new com.ibm.btt.event.BaseMonitor());
		engine.registerCond("js/condition/condition.js");
		//<![CDATA[
		/** Uncomment this to use Global Function Key **/
		/** load definition in global project if necessary **/
		// engine.registerKeys("<url to global project>js/keymap/globalKeyMap.js");
		/** load definition in this(local) project **/
		// engine.registerKeys("js/keymap/globalKeyMap.js");
		/** please note that the local definition will override the global definition with the same name **/
		//  register back/forward/refresh/leave event handler
		//	engine.registerHandler("js/config/navigationHandler.js");		
		//]]>
		
	}
</script>
</head>
<body class="claro" style="visibility:hidden">
<#if widgets_config??>
${widgets_config}
</#if>
<#if js_file??>
<script type="text/javascript" src="<%=JSPUtil.getWebContextRootUrl()%>${js_file}"> </script>
</#if>
${content}
<!-- applet id="printApplet" name="printApplet" code="com.ibm.btt.print.applets.PrintrApplet" codebase="applets" archive="bttprint.jar,JSON4J.jar" style="width:0px; height:0px;"></applet-->
</body>
</html>
