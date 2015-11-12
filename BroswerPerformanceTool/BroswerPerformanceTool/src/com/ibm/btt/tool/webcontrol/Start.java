package com.ibm.btt.tool.webcontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Start {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

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
  public void testUntitled() throws Exception {
    driver.get(baseUrl + "/dra/");
    driver.findElement(By.linkText("��  Դ")).click();
    driver.findElement(By.cssSelector("b > a")).click();
    try {
      assertEquals("Ϊ�˱���������Դ��֪ʶ��Ȩ��ά���廪��ѧ��������ҲΪ�˱�֤���Ϸ��û�������Ȩ�棬ͼ���Ҫ���ʹ�õ�λ�͸������Ӳ����ص�����Դ֪ʶ��Ȩ���йع涨��", driver.findElement(By.cssSelector("b")).getText());
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
