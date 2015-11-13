package com.ibm.btt.tool.webcontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.ibm.btt.tool.common.ToolProperty;

public class StartPerformanceTest {
	 private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();

	  public void start() throws Exception {
		  try {
			  this.setUp();
			  this.test();
		} catch (Exception e) {
			// TODO: handle exception
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
		for (int i = 0; i < ToolProperty.totalTimes; i++) {
			Thread.sleep(ToolProperty.waitTime);
			driver.findElement(By.id("Anchor_B_1_btn_reload_label")).click();
		}
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}
