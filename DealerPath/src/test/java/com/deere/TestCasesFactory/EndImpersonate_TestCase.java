package com.deere.TestCasesFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.EndImpersonateUser;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;

public class EndImpersonate_TestCase extends BaseClass {
	
	WebDriver driver;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Reset user default settings");
		
	}
	
	@Test(priority = 1)
	public static void resetMyPreferenceSetting() throws Throwable {
		
		LogFactory.info("Reset the settings for the user : "+ strUserRACFID +" on My Preferences page.");
		GenericFactory.resetMyPreferenceSettings();
	
	}
	
	@Test (priority=2)
	public void endImpersonateUser() {
	//	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
	    new EndImpersonateUser();
	//	EndImpersonateUser.endImpersonateOrLogoutUser();
		GenericFactory.EndImpersonateUSER();
	}

}
