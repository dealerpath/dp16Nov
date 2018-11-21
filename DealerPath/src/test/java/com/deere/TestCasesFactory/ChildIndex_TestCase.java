package com.deere.TestCasesFactory;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.PageFactory.Homepage_POF;
import com.deere.PageFactory.PortalLeftNavigation_POF;

@Test(groups = { "ChildIndexpage" })
public class ChildIndex_TestCase extends BaseClass {
	WebDriver driver;
	static String strExpectedValue;
	static String strTCID, WCMId;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Child Index page");
		GenericFactory.navigateToHomePage();
	}

	/**
	 * @author Bhavika Solanki
	 * 
	 *         Verify Theme color mentioned in Analyse User is selected on My
	 *         Preference modal window from home page and department page.
	 * 
	 * @return NA
	 * @throws Throwable
	 */
	@Test(priority = 1)
	static public void themePreferenceOnChildIndex() throws Throwable {
		strTCID = "TC01_childIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(strExpectedValue);
		GenericFactory.navigateToIndexPage(indexPage);
		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			if (!indexPage.isEmpty()) {
				LogFactory.beginTestCase(
						"Verify the theme preferences available in the 'Theme Color' field of My Preferences modal window for dealer on Child level index pages.");
				myPreferencesPageFactory.myPreferenceThemeVerification(strTCID, true, false, true);
			}
			GenericFactory.navigateToIndexPage(indexPage);
			if (!indexPage.isEmpty()) {
				LogFactory.beginTestCase(
						"Verify the theme preferences available in the 'Theme Color' field of My Preferences modal window for dealer on Child level index pages.");
				myPreferencesPageFactory.myPreferenceThemeVerification(strTCID, true, true, false);
			}
		}
	}

	/**
	 * @author Bhavika Solanki
	 * 
	 *         Verify that by changing the preferred site from the preferred site
	 *         drop down of My Preferences modal window, Url changes accordingly
	 *         from Child Index page.
	 * @throws Throwable
	 * @exception Exception
	 */
	@Test(priority = 2)
	public void myPreferenceUrlchangeOnSiteSelection() throws Throwable {

		strTCID = "TC02_childIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		String[] splittedTestdata = strExpectedValue.split("#");
		WCMId = splittedTestdata[1];

		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(WCMId);
		GenericFactory.navigateToIndexPage(indexPage);
		System.out.println(indexPage);

		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(
					"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window from child index page, Url changes accordingly");
			myPreferencesPageFactory.myPreferenUrlVerification(strTCID, splittedTestdata[0], false, true, true);
		}
	}

	/**
	 * @author Shrey Choudhary
	 * 
	 *         Verify notification link names on Parent index page.
	 * @throws Throwable
	 */
	@Test(priority = 4)
	public static void verifyNotificationInIndexPage() throws Throwable {
		String PageName = "Child Index Page";
		strTCID = "TC03_childIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			LinkedHashMap userWCMContent = ExcelFactory.getWCMByTCID(strExpectedValue);
			GenericFactory.navigateToIndexPage(userWCMContent);
			Homepage_POF.verifyNotificationsList(strTCID, PageName);
			GenericFactory.navigateToHomePage();
		}
	}

	/**
	 * @author Shrey Choudhary
	 * verify Left Navigation
	 * @throws Throwable
	 */
	@Test(priority = 5)
	public void verifyLeftNavigation() throws Throwable {
		strTCID = "TC04_childIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (strExpectedValue.isEmpty() || strExpectedValue.equalsIgnoreCase("NA")) {
			ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links name",
					"Verify Portal Left Navigation Links name on AT-Index Page",
					"Left navigation window should having same links as visible in all header/categories",
					"No test data provided", "Pass");

		} else {
			LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(strExpectedValue);
			GenericFactory.navigateToIndexPage(indexPage);
			String PageName = "AT-ChildIndex Page";
			PortalLeftNavigation_POF.verifyLeftNavigationWithCategories(PageName);
		}
	}
}