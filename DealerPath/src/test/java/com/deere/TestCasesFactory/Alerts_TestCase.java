package com.deere.TestCasesFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.PageFactory.Alerts_POF;

public class Alerts_TestCase extends BaseClass {
	
	WebDriver driver;
	static String strExpectedValue;
	static String strTCID;
	static List<String> listExpectedValue;

	@BeforeClass
	public void getReportHeader() throws Throwable {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Alerts");
		GenericFactory.navigateToHomePage();
	}

	@Test(priority = 1) // Aniket
	public static void verifyAlertsPortletContent() throws Throwable {
		
		LogFactory.beginTestCase(" Verify alerts portlet content ");
		List<LinkedHashMap> userWCMContent = ExcelFactory.getWCMContentDetails("AT-Alert");

		for (int i = 0; i < userWCMContent.size(); i++) {
			String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			String userCountry = (String) userWCMContent.get(i).get("Dealer_Country");
			String MRUCountry = (String) userWCMContent.get(i).get("Country");
			String Title = (String) userWCMContent.get(i).get("Title");
			String userProductType = (String) userWCMContent.get(i).get("Dealer_ProductType");
			String wcmProductType = (String) userWCMContent.get(i).get("ProductType");
			String Contenttype = (String) userWCMContent.get(i).get("ContentType");
			String wcmDealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString().trim();
			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			String description = (String) userWCMContent.get(i).get("Description").toString().trim();
			GenericFactory.multilingualSwitch(library, wcmMultilingual);
			alertPageFactory.verifyAlertsOnTheAlertPortlet(wcmTestCaseID, userCountry, wcmDealerType, MRUCountry,
					userProductType, wcmProductType, Contenttype, Title,description);
		}
	}

	@Test(priority = 2) // Aniket
	public static void verifyAlertsContentFilteredOnProductType() throws Throwable {

		LogFactory
		.beginTestCase(" Verify alerts on the alert portlet on after applying the product type filtering");
		List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Alert");

		for (int i = 0; i < userWCMContent.size(); i++) {
			String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			String userCountry = (String) userWCMContent.get(i).get("Dealer_Country");
			String wcmCountry = (String) userWCMContent.get(i).get("Country");
			String Title = (String) userWCMContent.get(i).get("Title");
			String userProductType = (String) userWCMContent.get(i).get("Dealer_ProductType");
			String wcmProductType = (String) userWCMContent.get(i).get("ProductType");
			LogFactory.beginTestCase("verify alerts content filtered based on product types");
			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			String description = (String) userWCMContent.get(i).get("Description").toString().trim();

			GenericFactory.multilingualSwitch(library, wcmMultilingual);
		
			alertPageFactory.checkAlertContentFilterOnProductTypes(userCountry, wcmCountry, userProductType,
					wcmProductType, Title, wcmTestCaseID,description);
		}
	}

	@Test(priority = 3) // Aniket
	public static void checkAlertHeaderAndIconDisplayed() throws Throwable {
		strTCID = "TC01_ALERTS_DEPARTMENT";
		LogFactory
		.beginTestCase(strTCID + " Verify if  image of alert header & icon are displayed on all enabled department pages");
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.isEmpty() && !listExpectedValue.get(0).toString().equals("None")) {
			alertPageFactory.checkAlertHeaderAndIconDisplayedInPortlet(strTCID, listExpectedValue.toString());
		}
	}
	
	@Test(priority = 4) // Aniket

	public static void checkOrderofPortletsOntheDepartmentPage() throws Throwable {
		strTCID = "TC02_ALERTS_DEPARTMENT";
		LogFactory.beginTestCase(strTCID+" Verify the sequence of portlets displayed on the Department Page");
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			alertPageFactory.checkOrderOfPortletsOnDepartment(strTCID, strExpectedValue);
		}
	}

	@Test(priority = 5) // Aniket
	public static void verifyOrderOfAlerts() throws Throwable {
		strTCID = "TC03_ALERTS_DEPARTMENT";
		LogFactory.beginTestCase(strTCID + " Verify whether the latest Alerts are showing at top on the Alerts portlet");
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.isEmpty() && !listExpectedValue.get(0).toString().equals("None")) {
			alertPageFactory.verifyAlertsShowingInDescendingOrderOnDateTime(strTCID, listExpectedValue.toString());
		}
	}

	@Test(priority = 6) 
	public static void verifyAlertsHeaderFormat() throws Throwable {
		strTCID = "TC04_ALERTS_HOMEPAGE";
		LogFactory.beginTestCase(strTCID + " Alerts format content on My Dealerpath ");
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			alertPageFactory.checkAlertHeaderContentWithFormat(strTCID);
		}
	}

	@Test(priority = 7) // Aniket - not did any changes - need data which is having embedded links
	public static void verifyEmbededLinksOntheAlertPortlet() throws Throwable {

		strTCID = "TC05_ALERTS_HOMEPAGE";
		LogFactory.beginTestCase(strTCID + " Verisy Embeded Links on  Alert Portlet ");
		
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			alertPageFactory.verifyEmbeddedlinks(strTCID);
		}
	}

	@Test(priority = 8) // Aniket
	public static void verifyReadMoreAndCollapseLinkOnAlerts() throws Throwable {
		strTCID = "TC06_ALERTS_HOMEPAGE";
		LogFactory.beginTestCase(strTCID + " Verify the Readmore and Collapse links on the Alert Portlet");
		
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			alertPageFactory.checkReadMoreAndCollapseLinkForAlert(strTCID);
		}
	}

	@Test(priority = 9)
	public static void VerifyToAlertsOnCountryGroupingHomepage() throws Throwable {

		LogFactory
		.beginTestCase(" Verify alerts on the alert portlet after applying the country filtering");
	
		try {
			List<LinkedHashMap> userWCMContent = ExcelFactory
					.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Alert");

			for (int i = 0; i < userWCMContent.size(); i++) {
				String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
				String WCMcountryGrouplist = (String) userWCMContent.get(i).get("Country").toString().trim();
				String Title = (String) userWCMContent.get(i).get("Title").toString().trim();
				LogFactory.info("WCMcountryGrouplist is = " + WCMcountryGrouplist);
				String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
				String library = (String) userWCMContent.get(i).get("Library").toString().trim();
				String description = (String) userWCMContent.get(i).get("Description").toString().trim();
				GenericFactory.multilingualSwitch(library, wcmMultilingual);
				alertPageFactory.VerifyToAlertsOnCountryGroupingHomepage(wcmTestCaseID, Title, WCMcountryGrouplist,description);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void getReportFooter() throws InterruptedException {
		LogFactory.endTestCase("Alerts Testcases");
		ReportFactory.tableEnd();

	}

}
