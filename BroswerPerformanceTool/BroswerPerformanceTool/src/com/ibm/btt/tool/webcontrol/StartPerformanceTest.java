package com.ibm.btt.tool.webcontrol;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
	  public void start() throws Exception {
		  try {
			  this.setUp();
			  this.test();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			this.tearDown();
			
		}
		  
	  }
	  @Before
	  public void setUp() throws Exception {
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
	  public void test() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Establish Session")).click();
		driver.findElement(By.id("index_link")).click();
		MemoryMap memoryMap=new MemoryMap();
		for (int currentCount = 1; currentCount <= ToolProperty.totalTimes; currentCount++) {
			Thread.sleep(ToolProperty.waitTime);
			driver.findElement(By.id("Anchor_B_1_btn_reload_label")).click();
			StringBuffer sb= new StringBuffer();
			if(currentCount%ToolProperty.recordInterval == 0){
				/*memoryMap.putMemoryInList(currentCount);
				sb.append("Count\t")
				for (Memory memory : memoryMap.getCurrentAllResults()) {
					sb.append(memory.getCurrentCount()+"\f"+memory.getWorkingset()+"\t");
				}
				MainFrame.textArea.setText(sb.toString());*/
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
		writeIntoExcel.createExcel(memoryMap.getCurrentAllResults());
	  }

	  @After
	  public void tearDown() throws Exception {
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
