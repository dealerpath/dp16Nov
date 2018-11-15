package com.deere.TestCasesFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.PageFactory.Homepage_POF;

@Test(groups = { "Homepage" })
public class Homepage_TestCase extends BaseClass {

	WebDriver driver;
	static String strExpectedValue;
	static List<String> listExpectedValue;
	static String strTCID;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Home_Page");
		GenericFactory.navigateToHomePage();
	}

		
	@Test(priority = 1) // Aniket
	public void verifyHomepageTitleIsDisplayed() throws Throwable {
		strTCID = "TC02_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.isEmpty() && !listExpectedValue.get(0).toString().equals("None")) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify title on the Homepage is displayed");
			homePageFactory.checkUserLogIntoHomepage(strExpectedValue, strTCID);
		}
	}

	@Test(priority = 2) // Aniket
	public void verifyWelcomeMessageOnHomepage() throws Throwable {
		strTCID = "TC03_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify welcome message on homepage is showing");
			homePageFactory.getWelcomeMessageOnHomepage(strExpectedValue, strTCID);
		}

	}

	@Test(priority = 3) // Aniket
	public static void checkVerifyTheme() throws Throwable {
		strTCID = "TC04_Homepage";
		
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		
		if (!strExpectedValue.isEmpty() && strExpectedValue.equalsIgnoreCase("NA"))
		{
			List<String> expectedThemeColor = analyzerUserMap.get("Theme Colors");
			listExpectedValue = GenericFactory.getTranslation(expectedThemeColor.get(0).toString().trim());
		}else
		{

			listExpectedValue =GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		}
		
			
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify selected theme color label on My Preferences");
			homePageFactory.verifyUserSelectdTheme(strExpectedValue, strTCID);
			
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public static void verifyAlertsAreShowingOnHomepage() throws Throwable {
		strTCID = "TC05_Homepage";
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
				LogFactory.beginTestCase(strTCID + " : Verify presence of Alert portlet on Home Page");
				alertPageFactory.isAlertPortletPresent(strTCID);
		}
	}

	@Test(priority = 5) // Aniket
	public static void verifyALertHeaderWithTxtIsPresentOnHomepage() throws Throwable {
		strTCID = "TC06_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify alerts header text on homepage");
			alertPageFactory.getAlertPortletHeaderAndText(strExpectedValue, strTCID);
		}
	}

	@Test(priority = 6)
	public static void verifyWarningSignPresentWithAlertHeaderOnHomepage() throws Throwable {
			strTCID = "TC07_Homepage";
			strExpectedValue =getExcelDataByTestCaseID(strTCID);
			if (!strExpectedValue.equalsIgnoreCase("None")) {
				LogFactory.beginTestCase(strTCID + " : Verify alerts warningsign in alertheader on homepage");
				alertPageFactory.checkForWarningSignPresentInAlertHeader(strTCID);
			}
	}

	@Test(priority = 7) // Aniket
	public static void verifyHeaderTxtIsInTheUserPrefferedLanguage() throws Throwable {
		strTCID = "TC08_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify alerts headertext is in the user preferred language on Home Page");
			alertPageFactory.getAlertHeaderTxtInPreferredLanguage(strTCID,strExpectedValue);
		}
	}

	@Test(priority = 8) // swati
	public void verifyAnnouncementSequenceOnDealerpathHomepage() throws Throwable {
		strTCID = "TC09_Homepage";
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(strTCID + " : Verify Sequencing on Portlet Page");
			homePageFactory.verifyOrderofPortletsOnHomepage(strTCID);
		}
	}

	@Test(priority = 9)
	public static void verifyAnnouncementOnTheHomePage() throws Throwable {
		strTCID = "TC10_Homepage";
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(strTCID + " : Verify presence of Announcements portlet on Home Page");
			announcementFactory.verifyAnnouncementTableOnHomePage(strTCID);
		}
	}

	@Test(priority = 10)
	public static void verifyAnnouncementContentIsPresent() throws Throwable {
		strTCID = "TC11_Homepage";
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			LogFactory.beginTestCase(strTCID + " : Verify Announcement content/body is showing");
			announcementFactory.verifyAnnouncementContentIsPrsent(strTCID);
		}
	}

	@Test(priority = 11) // Aniket
	public static void verifyAnnouncementHeaderTextIsInUserPrefferedLang() throws Throwable {
		strTCID = "TC12_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify announcement header text in the user preferred language");
			announcementFactory.verifyAnnouncementHeaderTextPrefferedLang(strExpectedValue, strTCID);
		}
	}

	/**
	 * This method verify utility links name should shown in same order as given
	 * in test data sheet
	 * 
	 * @author shrey.choudhary
	 * @throws Throwable
	 */

	@Test(priority = 12) // Aniket
	public void verifyUtilityMenuLinksOnHomePage() throws Throwable {
		strTCID = "TC13_Homepage";
			listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			LogFactory.beginTestCase(strTCID + " : Verify utility links menu and their order on the home page");
			utilityLinksFactory.compareUtilityLinksWithTestData(listExpectedValue, strTCID);
		}
	}

	/**
	 * /**This method verify utility link button name
	 * 
	 * @author shrey.choudhary
	 * @throws Throwable
	 */

	@Test(priority = 13) // Aniket
	public void verifyUtilityButton() throws Throwable {
		strTCID = "TC14_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify Utility Links menu  button Sign-Out/EndImpersonate");
			utilityLinksFactory.compareUtilityButtonWithTestData(strExpectedValue, strTCID);
		}
	}

	/**
	 * Script : To Test Favourites on Home Page Author : Archana Gaikwad Date :
	 * April.15.2018
	 * 
	 **/
	@SuppressWarnings("static-access")
	@Test(priority = 14) // Aniket
	public void verifyFavouriteHeaderPresentOnHomePage() throws Throwable {
		strTCID = "TC15_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify favourites header is showing on the homepage in the preferred language of the dealer.");
			favouritesFactory.verifyFavoritesHeaderPresent(strTCID, strExpectedValue);
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 15) // Aniket
	public void verifyFavouriteMessageWhenNoFavouritePresent() throws Throwable {
		strTCID = "TC16_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify No Favourites message is displayed on Homepage.");
			favouritesFactory.verifyWhenNoFavouritePresent(strTCID, strExpectedValue);

		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 16) // Aniket
	public void verifyFavouriteIconWhenNoFavouritePresent() throws Throwable {
		strTCID = "TC17_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify Favourites Star Icon with message on Homepage");
			favouritesFactory.verifyFavouriteIconWhenNoFavouriteAdded(strTCID, strExpectedValue);

		}
	}
	  
	@SuppressWarnings("static-access")
	@Test(priority = 17)
	public void verify_homepagequicklink() throws Throwable {
		strTCID = "TC18_Homepage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None") || !strExpectedValue.isEmpty()) {
			LogFactory.beginTestCase(strTCID + " : Verify favourite Quick Links on HomePage");
			favouritesFactory.favouriteQuickLinkOnHomePage(strTCID);
		}

	}
	 
	@SuppressWarnings("static-access")
	@Test(priority = 18)//swati
	public void verifyQuickLinkFavouritesDropdown() throws Throwable {
		strTCID = "TC19_Homepage";
		listExpectedValue =GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify Favourites Quick Links content");
			favouritesFactory.homePageQuickLinkContent(strTCID, strExpectedValue);
		}
	}	
	
	
	/**
	 * @author shrey.choudhary
	 * @createdAt 22-05-2018
	 * @throws Throwable
	 *             modifiesAt 22-05-2018
	 */
	
	@Test(priority = 19)
	public void verifyDealerPrincipleRole() throws Throwable {
		String strTCID = "TC20_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			String strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify Dealer Principal role test case begins");
			homePageFactory.getDealerPrincipalRole(flagDealerPrinciple, strExpectedValue, strTCID);
			System.out.println("test");
		}
	}

	
	@Test(priority = 20) // Aniket
	public static void verifyProductSegmentsOrder() throws Throwable {
		strTCID = "TC21_Homepage";
		
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		
		if (!strExpectedValue.isEmpty() && strExpectedValue.equalsIgnoreCase("NA"))
		{	List<String> expectedDepartment = analyzerUserMap.get("User Products");
			listExpectedValue = GenericFactory.getTranslation(expectedDepartment.toString());
		}else
		{	listExpectedValue =GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		}
		
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
		//	strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + " : Verify the available product segments are displayed in order");
			productSegmentFactory.checkOrderOfProductSegment(strTCID,listExpectedValue);
			
		}
	}
	
	

	@Test(priority = 21)
	public void verifyNotificationIconHomepage() throws Throwable {
		strTCID = "TC22_Homepage";
		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {		
			LogFactory.beginTestCase(strTCID + "Verify Notification Icon test case begins");
			homePageFactory.verifyNotificationIconOnHomePage(strTCID);
		}
	}

	@Test(priority = 22)
	public void verifyNotificationList() throws Throwable {
		strTCID = "TC23_Homepage";		strExpectedValue =getExcelDataByTestCaseID(strTCID);
		//listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!strExpectedValue.equalsIgnoreCase("None") || !strExpectedValue.isEmpty()) {
		//	strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + "Verify Notifications list test case begins");
			homePageFactory.verifyNotificationsList(strTCID,"Homepage");
		}
	}

	
	@Test(priority = 23)
	public static void verifyLeftNavigationWindow() throws Throwable {
		strTCID = "TC24_Homepage";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.isEmpty() && strExpectedValue.equalsIgnoreCase("NA")) {
			ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links name",
					"Verify Portal Left Navigation Links name on AT-Index Page",
					"Left navigation window should having same links as visible in all header/categories",
					"No test data provided", "Pass");
		} else {
			LogFactory.beginTestCase("Verify Portal Left Navigation Links Order");
			portalLeftNavFactory.compareNavigationLinksOnDepartment(strTCID, strExpectedValue);
		}
	}
	
	
	@Test(priority = 24)
	public static void verifyAnnouncementFooterLinksOnHomepage() throws Throwable {
		strTCID = "TC25_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
		//	strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + "Verify Footer Links on Homepage.");
			homePageFactory.verifyFooterLinksonHomepage(listExpectedValue, strTCID);
		}
	}

	@Test(priority = 25) 
	public static void verifyCopyrightOfFooterLinksonHomepage() throws Throwable {
		strTCID = "TC26_Homepage";
		listExpectedValue = GenericFactory.getTranslation(getExcelDataByTestCaseID(strTCID));
		if (!listExpectedValue.get(0).equalsIgnoreCase("None") || !listExpectedValue.isEmpty()) {
			strExpectedValue = listExpectedValue.get(0).toString();
			LogFactory.beginTestCase(strTCID + "Verify Copy right year on Footer Links on Homepage.");
			homePageFactory.verifyCopyrightOfFooterLinksonHomepage(strTCID);
		}
	}


	@Test(priority = 26) 
	public void verifyPopUp() throws Throwable {
		strTCID = "TC_NewEmployePopUp";
		String listExpectedValue = getExcelDataByTestCaseID(strTCID);
		if(listExpectedValue.length()>0)
		{
		if (!listExpectedValue.isEmpty() && !listExpectedValue.equals("None")) {
			
			String[] values=listExpectedValue.split(",");
			LogFactory.beginTestCase(strTCID + " : Verify title on the Homepage is displayed");
			if(values.length==2)
				homePageFactory.verifyNewEmployee(values[0].trim(),values[1].trim(), strTCID);
			else
				ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User",
						values.toString(), "Default site for the User should be as per sheet", "Test data in sheet not provided in format for e.g. RACKF Id , Region to select...", "Fail");
			}else
			{
				ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User",
						"NA", "NA", "NA Test Case is found so not executed...", "Fail");	
			}
			
		}else
		{
			ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User",
					"NA", "NA", "Input Data for TC_ID:"+strTCID+" is not provided in Data Sheet...",
					"Fail");
			
		}
	}

	
	@SuppressWarnings("static-access")
	@Test(priority = 27)
	public void verifySearchOnHomePageText() throws Throwable {
		strTCID = "TC027_HomePage Search";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None") || !strExpectedValue.isEmpty()) {
			LogFactory.beginTestCase(strTCID + " : Verify favourite Quick Links on HomePage");
			searchFactory.enterSearchText(strExpectedValue, strTCID);;
		}

	}
	
	
	@AfterClass
	public void getReportFooter() throws InterruptedException {
		LogFactory.endTestCase("Home Page Testcases");
		ReportFactory.tableEnd();

	}

}