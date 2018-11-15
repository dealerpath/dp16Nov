package com.deere.TestCasesFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.PageFactory.Homepage_POF;
import com.deere.PageFactory.PortalLeftNavigation_POF;

@Test(groups = { "MyPreferences" })
public class MyPreferences_TestCase extends BaseClass {
	WebDriver driver;
	static String strExpectedValue;
	static String strTCID;
	static String strLinkClicked;
	static String linkClicked;
	static List<String> ExpectedListKey;
	static String strTCIDFooter;
	static String strExpectedFooter;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("My Preferences");
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that My Preferences is displayed in the utility links.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public static void utilityIsDisplayed() throws Throwable {
		try {
			strTCID = "TC01_PortalPreferences";
			strExpectedValue = getExcelDataByTestCaseID(strTCID);

			if (!strExpectedValue.equalsIgnoreCase("None")) {
				LogFactory.beginTestCase("Verify " + strExpectedValue + " is displayed in the utility links");
				myPreferencesPageFactory.compareUtilityOptionWithTestData(strTCID, strExpectedValue);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

	}
/**
 * @author      Bhavika Solanki
 * 
 * Verify that 'My Preferences' modal window is getting displayed on clicking on Utility Menu > My Preferences.
 * @throws               Throwable 
 */
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public static void myPreferenceWinIsPresent() throws Throwable {
		strTCID = "TC02_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase("Verify " + strExpectedValue
					+ " window is getting displayed on clicking on Utility Menu > My Preferences");
			myPreferencesPageFactory.myPreferenceWinVerification(strTCID, strExpectedValue);
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify Save, Cancel and X button are displayed along with depaertments and Theme on My Preference modal window.
     * 
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@Test(priority = 3)
	public static void myPreferenceVerification() throws Throwable {
	strTCID = "TC02_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(
					"Verify Save, Cancel and X button are displayed on My Preference modal window along with the language, Theme and Departments");
			myPreferencesPageFactory.myPreferenceVerification(strTCID);
		}

		strTCID = "TC03_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(
					"Verify Departments are displayed on My Preference modal window along with the language and Departments");
			myPreferencesPageFactory.myPreferenceDeptVerification(strTCID, strExpectedValue);
		}
		strTCID = "TC04_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase("Verify that Theme is displayed in the My Preference modal window on Home page.");
			myPreferencesPageFactory.myPreferenceThemeVerification(strTCID, false, false, true);
		}
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase("Verify that Theme is displayed in the My Preference modal window on Department.");
			myPreferencesPageFactory.myPreferenceThemeVerification(strTCID, false, true, false);
		}
	}
	
	/**
     * @author      Bhavika Solanki
     * 
     * Verify list of languages for selected site should be present.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	
	@Test(priority = 4)
	public static void myPreferenceLanguageVerification() throws Throwable {
		strTCID = "TC05_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		LogFactory.beginTestCase("Verify language preferences functionality : list of languages should be displayed as expected.");

		myPreferencesPageFactory.URLVerification(strTCID, strExpectedValue);
	}

	/**
     * @author      Bhavika Solanki
     * 
     * Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window, Url changes accordingly from home page and department page.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	
	@Test(priority = 5)
	public static void myPreferenceUrlchangeOnSiteSelection() throws Throwable {
		strTCID = "TC06_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(
					"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window, Url changes accordingly");
			myPreferencesPageFactory.myPreferenUrlVerification(strTCID, strExpectedValue, false, false, true);
		}
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(
					"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window from department page, Url changes accordingly");
			myPreferencesPageFactory.myPreferenUrlVerification(strTCID, strExpectedValue, true, false, false);
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that on changing the language from language preferences under my preferences & save and verify if the page is changing accordingly
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@SuppressWarnings("static-access")
	@Test(priority =9)
	public static void myPreferenceLangChange() throws Throwable {
		strTCID = "TC10_PortalPreferences";
		//Expected value is Not Applicable
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		
		strTCIDFooter = "TC13_PortalPreferences_FooterLinks";
		//footer links those are to be verified
		strExpectedFooter = getExcelDataByTestCaseID(strTCIDFooter);
		boolean onDept = false;
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(
					"Verify that on changing the language from language preferences under my preferences & save and verify if the page is changing accordingly");
			myPreferencesPageFactory.regoinWiseLangChangeVerification( strTCID, onDept,
					strExpectedFooter, true);
		}
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory
					.beginTestCase("Verify language preferences functionality for dealers and employee on department");
			onDept = true;
			myPreferencesPageFactory.regoinWiseLangChangeVerification( strTCID, onDept,
					strExpectedFooter, false);
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that departments checked on my preferences page are reflected in the Left pane for Departments on Home page.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@SuppressWarnings("static-access")
	@Test(priority =6)
	public static void VerifyPortletlinksformyprefernces() throws Throwable {
		// For all languages present
		strTCID = "TC07_PortalPreferences";
		if (!strTCID.equalsIgnoreCase("None")) {
		
		LogFactory.beginTestCase(
				"Verify that departments checked on my preferences page are reflected in the Left pane for Departments on Home page");
		myPreferencesPageFactory.ClickOnLanguagePrerefences(strTCID);
	}}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that if the department on which system is present is deactivated then the system redirects to Home page.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@Test(priority = 7)
	public static void verifyLeftNavigationDeptInactivation() throws Throwable {
		strTCID = "TC08_PortalPreferences";
		myPreferencesPageFactory.DeptUncheckTest(strTCID);
		LogFactory.beginTestCase(
				"Verify that if the department on which system is present is deactivated then the system redirects to Home page");
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify Pop up message when all the departments are  for My prefernces page.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@Test(priority = 8)
	public static void verifyAlertPopUpOnInactivatingAllDept() throws Throwable {
		strTCID = "TC09_PortalPreferences";
		LogFactory.beginTestCase("Verify Pop up message for My prefernces page");
		myPreferencesPageFactory.MyPreferencesCheckboxValidation(strTCID);
	}
	/**
     * @author      Archana Gaikwad
     * 
     * verify the functionality for switch site.
	 * @throws               Throwable 
     * @exception			 Exception
     */

	@Test(priority =10)
	public static void verifyToswitchSite() throws Throwable {
		LogFactory.beginTestCase("verify to switch site ");
		@SuppressWarnings("unused")
		Map<String, List<String>> employeeGroups = ExcelFactory.employeeGroups();
		System.out.println(employeeGroups);
		@SuppressWarnings("unused")
		LinkedHashMap<String, String> employeeGroup = BaseClass.employeeGroup();
		myPreferencesPageFactory.verifySwitchSite("TC11_PortalPreferences", BaseClass.strUserRACFID);
		LogFactory.info("Switch site executed.");
		LogFactory.endTestCase(" ");
	}

	/**
     * @author     Shrey Choudhary
     * 
     * verify Preffered Site Of User.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	
	@Test(priority = 11)
	public static void VerifyRACFpreferedSite() throws Throwable {
		strTCID = "TC12_PortalPreferences";
		LogFactory.info("verify Preffered Site Of User");
		myPreferencesPageFactory.verifyPrefferedSiteOfUser(strTCID);
	}

	@AfterClass
	public void getReportFooter() throws InterruptedException {
		LogFactory.endTestCase("My Preferences Test cases");

	}
}