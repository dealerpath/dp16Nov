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

@Test(groups = { "GrandChildIndexpage" })
public class GrandChildIndex_TestCase extends BaseClass {
	WebDriver driver;
	static String strExpectedValue;
	static String strTCID, WCMId;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Grand Child Index Page");
		GenericFactory.navigateToHomePage();
	}

	@Test(priority = 1)
	static public void themePreferenceOnChildIndex() throws Throwable {

		strTCID = "TC01_GrandchildIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(strExpectedValue);
		GenericFactory.navigateToIndexPage(indexPage);
		System.out.println(indexPage);
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

	@Test(priority = 2)
	public void myPreferenceUrlchangeOnSiteSelection() throws Throwable {

		strTCID = "TC02_GrandchildIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		String[] splittedTestdata = strExpectedValue.split("#");
		WCMId = splittedTestdata[1];

		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(WCMId);
		GenericFactory.navigateToIndexPage(indexPage);
		System.out.println(indexPage);

		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			LogFactory.beginTestCase(
					"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window from Grand child index page, Url changes accordingly");
			myPreferencesPageFactory.myPreferenUrlVerification(strTCID, splittedTestdata[0], false, true, true);
		}
	}

	/**
	 * @author Shrey Choudhary
	 * 
	 *         verify Left Navigation
	 * @throws Throwable
	 */
	@Test(priority = 3)
	public void verifyLeftNavigation() throws Throwable {
		strTCID = "TC03_GrandchildIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (strExpectedValue.isEmpty() || strExpectedValue.equalsIgnoreCase("NA")) {
			ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links name",
					"Verify Portal Left Navigation Links name on AT-Index Page",
					"Left navigation window should having same links as visible in all header/categories",
					"No test data provided", "Pass");

		} else {
			LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(strExpectedValue);
			GenericFactory.navigateToIndexPage(indexPage);
			System.out.println(indexPage);
			String PageName = "AT-GrandChildIndex Page";
			PortalLeftNavigation_POF.verifyLeftNavigationWithCategories(PageName);
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
		String PageName = "GrandChild Index Page";
		strTCID = "TC04_GrandchildIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			LinkedHashMap userWCMContent = ExcelFactory.getWCMByTCID(strExpectedValue);
			GenericFactory.navigateToIndexPage(userWCMContent);
			Homepage_POF.verifyNotificationsList(strTCID, PageName);
			GenericFactory.navigateToHomePage();
		}
	}

}
