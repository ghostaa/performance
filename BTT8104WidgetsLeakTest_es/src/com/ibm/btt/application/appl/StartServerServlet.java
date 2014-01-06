/*_
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2008 - All Rights Reserved
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 * 
 * DISCLAIMER:
 * The following [enclosed] code is sample code created by IBM
 * Corporation. This sample code is not part of any standard IBM product
 * and is provided to you solely for the purpose of assisting you in the
 * development of your applications. The code is provided 'AS IS',
 * without warranty of any kind. IBM shall not be liable for any damages
 * arising out of your use of the sample code, even if they have been
 * advised of the possibility of such damages.
 */
package com.ibm.btt.application.appl;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.clientserver.CSServerService;
import com.ibm.btt.config.InitManager;
import com.ibm.btt.config.dynamic.DynamicFlowHelper;
/**
 * This class is a sample servlet that starts the server in the Web server workstation. 
 * It creates an initial context in the server and starts the client server service.
 * In a real implementation, this class would have to perform any process required by the 
 * server such as the initial context creation and the 
 * initialization of the client server service.
 * @copyright(c) Copyright IBM Corporation 2011, 2012.
 */
public class StartServerServlet extends HttpServlet implements Servlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig arg0) throws ServletException {
		try {
			// Prevent the multi-initialization
			if (!InitManager.isInitialized()) {
				// Get the initialization parameter from servlet  
				String bttPath = (String)arg0.getInitParameter("bttConfigPath");
				// Initiate the BTT runtime environment 
				InitManager.reset(bttPath);
				
				// Initiate the rule definition for dynamic flow
				// Remove the comment on the following code to enable dynamic flow function
				// DynamicFlowHelper.getInstance().initDynamicFlow();
				
				// Creates the initial context in the server.
				Context context = ContextFactory.createContext("branchServer");
				((CSServerService)context.getService("realCSServer")).initiateServer();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
