package com.deere.TestCasesFactory;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ReportOutput;
import com.deere.PageFactory.Homepage_POF;
import com.deere.PageFactory.ParentIndex_POF;
import com.deere.PageFactory.PortalLeftNavigation_POF;
import com.deere.PageFactory.PortletLinksPage_POF;

@Test(groups = { "ParentIndexPage" })
public class ParentIndex_TestCase extends BaseClass {
	WebDriver driver;
	static String strExpectedValue, strExpectedValue1;
	static String strTCID, WCMId;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Parent Index Page");
		GenericFactory.navigateToHomePage();
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify Theme color mentioned in Analyse User is selected on My Preference modal window from home page and department page.
     * 
     * @return          	 NA
	 * @throws               Throwable 
     */
	@Test(priority = 1)
	static public void themePreferenceOnParentIndex() throws Throwable {
		strTCID = "TC01_ParentIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(strExpectedValue);
		GenericFactory.navigateToIndexPage(indexPage);
		System.out.println(indexPage);
		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			if (!indexPage.isEmpty()) {
				LogFactory.beginTestCase(
						"Verify the theme preferences available in the 'Theme Color' field of My Preferences modal window for dealer on Parent level index pages.");
				myPreferencesPageFactory.myPreferenceThemeVerification(strTCID, true, false, true);
			}
			GenericFactory.navigateToIndexPage(indexPage);
			if (!indexPage.isEmpty()) {
				LogFactory.beginTestCase(
						"Verify the theme preferences available in the 'Theme Color' field of My Preferences modal window for dealer on Parent level index pages.");
				myPreferencesPageFactory.myPreferenceThemeVerification(strTCID, true, true, false);
			}
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window, Url changes accordingly from Parent index page.
	 * @throws               Throwable 
     */
	@Test(priority = 2)
	public void myPreferenceLanguageVerification() throws Throwable {
		strTCID = "TC02_ParentIndexPage";
		String strTCID1 = "TC05_PortalPreferences";
		strExpectedValue = getExcelDataByTestCaseID(strTCID1);
		strExpectedValue1 = getExcelDataByTestCaseID(strTCID);

		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(strExpectedValue1);
		GenericFactory.navigateToIndexPage(indexPage);

		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			myPreferencesPageFactory.URLVerification(strTCID, strExpectedValue);
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window, Url changes accordingly from Index page.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@Test(priority = 3)
	public void myPreferenceUrlchangeOnSiteSelection() throws Throwable {
		strTCID = "TC03_ParentIndexPage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);

		String[] splittedTestdata = strExpectedValue.split("#");
		WCMId = splittedTestdata[1];
		LinkedHashMap indexPage = ExcelFactory.getWCMByTCID(WCMId);
		GenericFactory.navigateToIndexPage(indexPage);
		System.out.println(indexPage);

		if (!strExpectedValue.equalsIgnoreCase("None") && !strExpectedValue.isEmpty()
				&& !strExpectedValue.equals("NA")) {
			LogFactory.beginTestCase(
					"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window from parent index page, Url changes accordingly");
			myPreferencesPageFactory.myPreferenUrlVerification(strTCID, splittedTestdata[0], false, true, true);
		}

	}
	/**
     * @author    Shrey Choudhary
     * 
     * Verify notification link names on Parent index page.
	 * @throws               Throwable 
     */
	@Test(priority = 4)
	public static void verifyNotificationInIndexPage() throws Throwable {
		String PageName = "Parent Index Page";
		strTCID = "TC04_ParentIndexPage";
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
     * @author    Archana Gaikwad
     * 
     * Navigate to an 3rd Level index page with 'Links' PZN template from links portlet and verify.
	 * @throws               Throwable 
     */
	@Test(priority = 5)
	static public void verifythirdLevelIndexPage() throws Throwable {

		strTCID = "TC05_ParentIndexPage";

		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		int count = 0;
		String input = "NA";
		String scenarioName = "Navigate to an 3rd Level index page with 'Links' PZN template from links portlet and verify";
		LogFactory.info(scenarioName + "Scenario Started.");
		int rowCount = 12;
		@SuppressWarnings("rawtypes")
		Set<String> set = new HashSet<String>();
		List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct(
				"AT-GrandChild Index Page", "AT-Index Page", "AT-ChildIndex Page", "");
		for (int i = 0; i < userWCMContent.size(); i++) {

			String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			String strThirdLevelIndexPage = (String) userWCMContent.get(i).get("3rdLevelIndexPage").toString().trim();
			String strThirdLevelIndexPageCategories = (String) userWCMContent.get(i).get("3rdLevelIndexPageCategories")
					.toString().trim();
			String strThirdLevelIndexPageNestedCategories = (String) userWCMContent.get(i)
					.get("3rdLevelIndexPageNestedCategories").toString().trim();
			String strIndex_Page_Template = (String) userWCMContent.get(i).get("Index_Page_Template").toString().trim();
			String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
			String strRACFGroups = (String) userWCMContent.get(i).get("RACFGroups").toString().trim();
			String strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
			String strdealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString().trim();
			String str3rdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
			String str4thLevelLandingPage = (String) userWCMContent.get(i).get("4thLevelLandingPage").toString().trim();
			String str3rdLevelLandingPage = (String) userWCMContent.get(i).get("3rdLevelLandingPage").toString().trim();
			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			if (wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty()) {
				GenericFactory.multilingualSwitch(library, wcmMultilingual);
			}

			set.add(wcmTestCaseID);
			if ((!strThirdLevelIndexPage.equalsIgnoreCase("NA") && strIndex_Page_Template.equalsIgnoreCase(""))
					|| (!str3rdLevelFolder.equalsIgnoreCase("NA") && str4thLevelLandingPage.equalsIgnoreCase("NA"))
					|| (!str3rdLevelFolder.equalsIgnoreCase("NA") && str3rdLevelLandingPage.equalsIgnoreCase("NA"))) {
				if (wcmTestCaseID == strExpectedValue) {

					List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
					strDepartmentName = translatedText.get(0);

					boolean departmentStatus = GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);
					if (departmentStatus == true) {

						GenericFactory.navigateToIndexPage(userWCMContent.get(i));
						GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(i));
						ReportOutput thirdLevelHeadercat = PortletLinksPage_POF
								.thirdLevelHeaderCategory(userWCMContent.get(i));
						ReportFactory.reporterOutputMerge(thirdLevelHeadercat.getWcmTestCaseID(),
								thirdLevelHeadercat.getScenarioName(), thirdLevelHeadercat.getInputTestData(),
								thirdLevelHeadercat.getExpectedResult(), thirdLevelHeadercat.getActualResult(),
								thirdLevelHeadercat.getTestcaseStatus(), true, rowCount);

						ReportOutput thirdlevelHeadercontent = PortletLinksPage_POF
								.headerContent(userWCMContent.get(i));
						ReportFactory.reporterOutputMerge(thirdlevelHeadercontent.getWcmTestCaseID(),
								thirdlevelHeadercontent.getScenarioName(), thirdlevelHeadercontent.getInputTestData(),
								thirdlevelHeadercontent.getExpectedResult(), thirdlevelHeadercontent.getActualResult(),
								thirdlevelHeadercontent.getTestcaseStatus(), false, rowCount);

						ReportOutput alphaOrder = PortletLinksPage_POF.linksAlphabeticOrderLinks(userWCMContent.get(i));
						ReportFactory.reporterOutputMerge(alphaOrder.getWcmTestCaseID(), alphaOrder.getScenarioName(),
								alphaOrder.getInputTestData(), alphaOrder.getExpectedResult(),
								alphaOrder.getActualResult(), alphaOrder.getTestcaseStatus(), false, rowCount);

						ReportOutput alphaCat = PortletLinksPage_POF.alphabeticCategory(userWCMContent.get(i));
						ReportFactory.reporterOutputMerge(alphaCat.getWcmTestCaseID(), alphaCat.getScenarioName(),
								alphaCat.getInputTestData(), alphaCat.getExpectedResult(), alphaCat.getActualResult(),
								alphaCat.getTestcaseStatus(), false, rowCount);

						ReportOutput linksWithoutScroll = PortletLinksPage_POF
								.linkswithoutScrollCategory(userWCMContent.get(i));
						ReportFactory.reporterOutputMerge(linksWithoutScroll.getWcmTestCaseID(),
								linksWithoutScroll.getScenarioName(), linksWithoutScroll.getInputTestData(),
								linksWithoutScroll.getExpectedResult(), linksWithoutScroll.getActualResult(),
								linksWithoutScroll.getTestcaseStatus(), false, rowCount);

						ReportOutput linksWithHeadernFooter = PortletLinksPage_POF
								.linksWithHeadernFooter(userWCMContent.get(i));
						ReportFactory.reporterOutputMerge(linksWithHeadernFooter.getWcmTestCaseID(),
								linksWithHeadernFooter.getScenarioName(), linksWithHeadernFooter.getInputTestData(),
								linksWithHeadernFooter.getExpectedResult(), linksWithHeadernFooter.getActualResult(),
								linksWithHeadernFooter.getTestcaseStatus(), false, rowCount);
						break;

					}

					else {

						PortletLinksPage_POF.departmentInactive(wcmTestCaseID, scenarioName, BaseClass.PORTLET_LINKFLAG,
								"Index Page Scenarios should work as expected.", "actResult", "status");
						count++;
						if (count == 1) {
							break;
						}
					}
				}
			}

			GenericFactory.navigateToHomePage();
			input = strThirdLevelIndexPage + strThirdLevelIndexPageCategories;
		}

		if (!set.contains(strExpectedValue)) {
			PortletLinksPage_POF.recordNotFoundInactive(strExpectedValue, scenarioName, input,
					"Index Page Scenarios should work as expected.", "actResult", "status");
		}

		if (userWCMContent.size() == 0) {
			PortletLinksPage_POF.matchNotFound("TC_PZNTemplate", scenarioName, "NA",
					"Index Page Scenarios should work as expected.", "actResult", "status");
		}

		LogFactory.info(
				"Navigate to an 3rd Level index page with 'Links' PZN template from links portlet and verify Scenario Ended.");
		GenericFactory.navigateToHomePage();
	}

	/**
     * @author    Archana Gaikwad
     * 
     * Verify Country filtering functionality for Link Portlet and Index Pages.[Note:It will work Hispano,Au/Nz]
	 * @throws               Throwable 
     */
	@Test(priority = 6)
	public void verifycountryGroupIndexPages() throws Throwable {

		strTCID = "TC06_ParentIndexPage";

		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		int count = 0;

		String scenarioName = "Verify Country filtering functionality Scenario Started.";
		LogFactory.info(scenarioName + "Scenario Started.");
		String title = "NA";
		Set<String> set = new HashSet<String>();

		@SuppressWarnings("rawtypes")
		List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct(
				"AT-Index Page", "AT-ChildIndex Page", "AT-GrandChild Index Page", "AT-Link");
		for (int i = 0; i < userWCMContent.size(); i++) {

			String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
			String strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
			String strUserDefinedCountry = (String) userWCMContent.get(i).get("Dealer_Country").toString().trim();
			String strWCMCountry = (String) userWCMContent.get(i).get("Country").toString().trim();
			String strThirdLevelIndex = (String) userWCMContent.get(i).get("3rdLevelIndexPage").toString().trim();
			String strThirdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
			String strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
			String strThirdLevelLandingPage = (String) userWCMContent.get(i).get("3rdLevelLandingPage").toString()
					.trim();
			String strForthLevelIndexPage = (String) userWCMContent.get(i).get("4thLevelIndexPage").toString().trim();
			String strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
			String strRACFGroups = (String) userWCMContent.get(i).get("RACFGroups").toString().trim();
			String strdealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString().trim();
			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			if (wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty()) {
				GenericFactory.multilingualSwitch(library, wcmMultilingual);
			}

			set.add(wcmTestCaseID);
			if (wcmTestCaseID == strExpectedValue) {

				List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
				strDepartmentName = translatedText.get(0);

				boolean departmentStatus = GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);
				if (departmentStatus == true) {

					if (strThirdLevelIndex != "NA" || strThirdLevelLandingPage != "NA" || strThirdLevelFolder != "NA")

					{
						GenericFactory.navigateToIndexPage(userWCMContent.get(i));
					}
					PortletLinksPage_POF.countryFilterIndex(wcmTestCaseID, strDepartmentName, strTitle,
							strSubDepartmentName, strUserDefinedCountry, strWCMCountry, strThirdLevelIndex,
							strThirdLevelFolder, strForthLevelIndexPage, strContenttype);
					GenericFactory.navigateToHomePage();

				} else {
					PortletLinksPage_POF.departmentInactive(wcmTestCaseID, scenarioName, strTitle,
							"Country filtering should work as expected.", "actResult", "status");
					count++;
					title = strTitle;
					if (count == 1) {
						break;
					}

				}

			}

		}

		if (userWCMContent.size() == 0) {
			PortletLinksPage_POF.matchNotFound("TC_CountryFiltering", scenarioName, title,
					"Country filtering should work as expected.", "actResult", "status");
		}
		GenericFactory.navigateToHomePage();

		if (!set.contains(strExpectedValue)) {
			PortletLinksPage_POF.recordNotFoundInactive(strExpectedValue, scenarioName, title,
					"Country filtering should work as expected.", "actResult", "status");
		}
		LogFactory.info("Verify Country filtering functionality Scenario Ended.");
	}

	/**
     * @author    Archana Gaikwad
     * 
     * Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality from links portlet and verify.
	 * @throws               Throwable 
     */
	@Test(priority = 7)
	public void verifyDepartmentUncheckOnIndexPage() throws Throwable {

		strTCID = "TC07_ParentIndexPage";
		String scenarioName = "Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality";
		LogFactory.info(scenarioName + "Scenario Started.");
		int count = 0;
		GenericFactory.tocheckAllProducts();
		List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct(
				"AT-ChildIndex Page", "AT-Index Page", "AT-GrandChild Index Page", "");
		for (int i = 0; i < userWCMContent.size(); i++) {

			String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			String str3rdLevelLandingPage = (String) userWCMContent.get(i).get("3rdLevelLandingPage").toString().trim();
			String strThirdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
			String strForthLevelLandingPage = (String) userWCMContent.get(i).get("4thLevelLandingPage").toString()
					.trim();
			String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
			String strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
			String strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			if (wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty()) {
				GenericFactory.multilingualSwitch(library, wcmMultilingual);
			}

			List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
			strDepartmentName = translatedText.get(0);
			boolean departmentStatus = GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);

			if (departmentStatus == true) {

				GenericFactory.navigateToIndexPage(userWCMContent.get(i));
				GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(i));
				PortletLinksPage_POF.ChangeOfPrefDeptOnIndex(wcmTestCaseID, strDepartmentName);
				GenericFactory.navigateToHomePage();
				count++;
				if (count == 1) {
					break;
				}

			} else {
				PortletLinksPage_POF.departmentInactive(wcmTestCaseID, scenarioName, strDepartmentName,
						"Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality should work.",
						"actResult", "status");
				count++;
				if (count == 1) {
					break;
				}

			}

		}
		if (userWCMContent.size() == 0) {
			PortletLinksPage_POF.matchNotFound("TC_IndexCheckUncheck", scenarioName, "NA",
					"Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality should work.",
					"actResult", "status");
		}
		GenericFactory.navigateToHomePage();
		LogFactory.info(
				"Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality Scenario Ended.");
	}
	/**
     * @author    Shrey Choudhary
     * 
     * verify Left Navigation
	 * @throws               Throwable 
     */
	@Test(priority = 8)
	public void verifyLeftNavigation() throws Throwable {
		strTCID = "TC07_ParentIndexPage";
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
		String PageName = "AT-Index Page";
		PortalLeftNavigation_POF.verifyLeftNavigationWithCategories(PageName);
	}
	}
}
