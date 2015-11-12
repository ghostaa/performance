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
	    baseUrl = "http://lib.tsinghua.edu.cn/";
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void test() throws Exception {
	    driver.get(baseUrl + "/dra/");
	    driver.findElement(By.linkText("资  源")).click();
	    driver.findElement(By.cssSelector("b > a")).click();
	    try {
	      assertEquals("为了保护电子资源的知识产权，维护清华大学的声誉，也为了保证广大合法用户的正当权益，图书馆要求各使用单位和个人重视并遵守电子资源知识产权的有关规定。", driver.findElement(By.cssSelector("b")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
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
