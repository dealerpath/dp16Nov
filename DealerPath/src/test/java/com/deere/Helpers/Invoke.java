package com.deere.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Invoke extends BaseClass {
	public FileInputStream ip;
//	RemoteWebDriver wbDriver=null;

	/**
	 * This method is the first step of DealerPath suite which sets user
	 * credentials, initiate drivers and page elements
	 * 
	 * @author shrishail.baddi
	 * @createdAt 07-06-2018
	 * @throws IOException
	 * @throws Exception
	 * @modifyBy shrey.choudhary
	 * @modifyAt
	 */
	@BeforeClass
	public void systemConfigSetup() throws IOException, Exception {
		try {
				System.err.close();
				ip = new FileInputStream(strWorkingDir + "\\src\\test\\java\\com\\deere\\ConfigFactory\\config.properties");
				configProperties.load(ip);
				
				GenericFactory.readPropertyFile();
				ExcelFactory.readWCMContentData();
				translationSheet=ExcelFactory.translationLookUp();
				classesExecution=ExcelFactory.getRunningStatus();
				ExcelFactory.setCredentials();
				GenericFactory.createXML();
				BrowserFactory.initiateDriver();
				initPageElements();
				ReportFactory.createHeader();
				GenericFactory.createHeaderSection("Login Page");
			} catch (Exception e) {
				LogFactory.info(e.getMessage());
			} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is use to invoke admin's login credentials then go to impersonate
	 * the dealer
	 * 
	 * @author shrey.choudhary
	 * @createdAt 07-06-2018
	 * @throws IOException
	 * @throws Exceptionss88593
	 * @modifyBy
	 * @modifyAt
	 * @throws Throwable
	 */
    @Test 
    public  void invokeUserCredentials() throws Throwable {
           LogFactory.beginTestCase("Verify Valid Login");
           if(appVersion.equalsIgnoreCase("8.5")) 
                  loginPageFactory.setCredentialsNewVersion(strUserName, strPassword);
           else
           loginPageFactory.setCredentials(strUserName, strPassword);
           if (loginPageFactory.verifyUserLogin()) {
                  
                  GenericFactory.utilityMenuAdminClick();
                  
                  IterationTest.testNgSuite();
           }
           else { 
                  ReportFactory.reporterOutput("Login & Homepage", "Verify user login",
                  BaseClass.strUserName, "User should redirect to homepage", "User unable to login due to unknown reason", "FAIL");
                  ReportFactory.tableEnd();
           }
    }

	
	
	@AfterClass
	
	public void closeDriver() throws IOException {
	//	wbDriver.quit();
		ExcelFactory.writeExcel();
		wbDriver.close();
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("taskkill /im chrome.exe /f /t");


	}
	
}