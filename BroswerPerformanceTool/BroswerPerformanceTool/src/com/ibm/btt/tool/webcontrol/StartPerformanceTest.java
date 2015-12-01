package com.ibm.btt.tool.webcontrol;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.ibm.btt.tool.common.JSONObjectManager;
import com.ibm.btt.tool.common.ToolProperty;
import com.ibm.btt.tool.memory.Memory;
import com.ibm.btt.tool.memory.MemoryMap;
import com.ibm.btt.tool.report.WriteIntoExcel;
import com.ibm.btt.tool.ui.MainFrame;

public class StartPerformanceTest implements Runnable{
	 private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private WriteIntoExcel writeIntoExcel = new WriteIntoExcel();
	  @Before
	  public void setUp()  {
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\ie\\IEDriverServer.exe"); 
		driver = new InternetExplorerDriver();
		/*DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();   
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);   
			WebDriver driver = new InternetExplorerDriver(ieCapabilities); 
		*/
	    baseUrl = ToolProperty.url;
//	    baseUrl = "http://9.112.234.65:8080";
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void start() throws Exception {
		JSONArray widgetsArray=JSONObjectManager.all_widgets_json_array;
		if (ToolProperty.recordType==ToolProperty.RECORD_BATCH) {
			runMoreWidgets(widgetsArray);
		}else if(ToolProperty.recordType==ToolProperty.RECORD_SINGLE){
			for (int i = 0; i < widgetsArray.length(); i++) {
				if (((JSONObject)widgetsArray.get(i)).getString("linktext").equals(ToolProperty.widgetIdOnTool)){
					runOneWidget(ToolProperty.widgetIdOnTool,((JSONObject)widgetsArray.get(i)).getString("widgetid"));
					break;
				}
			}
		}
	  }
	  private void runMoreWidgets(JSONArray widgetsArray) {
		  for (int index = 0; index < widgetsArray.length(); index++) {
				String linktext=((JSONObject)widgetsArray.get(index)).getString("linktext");
				String widgetid=((JSONObject)widgetsArray.get(index)).getString("widgetid");
				runOneWidget(linktext,widgetid);
				
			  }
	  }
	  private void runOneWidget(String linktext,String widgetid) {
		  	setUp();
			driver.get(baseUrl);
			driver.findElement(By.linkText("Establish Session")).click();
			driver.findElement(By.linkText(linktext)).click();
			MemoryMap memoryMap=new MemoryMap();
			for (int currentCount = 1; currentCount <= ToolProperty.totalTimes; currentCount++) {
				try {
					Thread.sleep(ToolProperty.waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.findElement(By.id(widgetid)).click();
				StringBuffer sb= new StringBuffer();
				if(currentCount%ToolProperty.recordInterval == 0){
					memoryMap.putMemoryInList(currentCount);
					List<Memory> memorys =memoryMap.getCurrentAllResults();
					sb.append("Count\t");
					for (int i=0;i<memorys.size();i++) {
						sb.append(memorys.get(i).getCurrentCount()+"\t");
					}
					sb.append("\nWorkSet\t");
					for (int i=0;i<memorys.size();i++) {
						sb.append(memorys.get(i).getWorkingset()+"\t");
					}
					MainFrame.textArea.setText(sb.toString());
					
				}
			}
			writeIntoExcel.intoExcel(memoryMap.getCurrentAllResults(),linktext);
			tearDown();
	  }
	  
	  
	  @After
	  public void tearDown(){
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
