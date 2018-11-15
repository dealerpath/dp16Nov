/* 
 * Project    : DealerPath
 * Script     : Homepage_POF
 * Author     : Shrishail
 * Date       : May.15.2018
 * Last Modified On:
 * Modified By :
 */

package com.deere.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.DateFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.ImpersonateUser;
import com.deere.Helpers.IterationTest;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;

public class Homepage_POF extends BaseClass {

	public static WebDriver HomDriver;
	// static SoftAssert softAssert = new SoftAssert();

	public Homepage_POF(WebDriver driver) {
		this.HomDriver = driver;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='user-info']")
	static WebElement wbelUserInfo;

	@FindBy(how = How.XPATH, using = "//h1[@class='app-title']")
	static WebElement wbelTitleOfHomePage;

	@FindBy(how = How.ID, using = "leftNav")
	static WebElement wbelLeftWindow;

	@FindBy(how = How.XPATH, using = ".//*[@class='wpthemeFooter']")
	public static WebElement wbelHomePageFooterFramePath;

	@FindBy(how = How.XPATH, using = "//div[@id='js-notifications']")
	static WebElement wbNotificationLink;

	@FindBy(how = How.ID, using = ".//*[@class='list-item']/div")
	static List<WebElement> wbNotificationList;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[2]/section/div/div[2]/div")
	static WebElement alertIndex;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[3]/section/div/div[2]/div")
	static WebElement announcementIndex;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[4]/section/div/div[2]/div")
	static WebElement favoritesIndex;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[1]")
	static WebElement searchIndex;

	@FindBy(how = How.XPATH, using = "//div[@class='section-header']/h3")
	static List<WebElement> sectionHeader;

	@FindBy(how = How.ID, using = "//div[@class='value']//input[@type='radio' and @checked='checked']")
	static WebElement wbelSelectedValue;

	/*
	 * @FindBy(how = How.XPATH, using = ".//*[@class='wpthemeFooter']") public
	 * static WebElement homepageFooterFramePath;
	 */
	@FindBy(how = How.CSS, using = "body.lotusui30dojo.tundra.locale_en.w1200:nth-child(2) div.wpthemeFrame:nth-child(20) > footer.wpthemeFooter:nth-child(3)")
	public static WebElement homepageFooterFramePath;

	@FindBy(how = How.XPATH, using = ".//*[@id='dealerPathFooterCopyright']")
	public static WebElement copyrightFooterPath;

	/*
	 * @FindBy(how = How.XPATH, using = ".//*[@id='main-header']/div[1]/div/h1")
	 * static WebElement homepagepath;
	 */

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[3]/section/div/div[2]")
	public static WebElement announcementFramePath;

	@FindBy(how = How.XPATH, using = ".//div[@class='section warning']")
	public static WebElement alertHeaderTitle;

	@FindBy(how = How.XPATH, using = ".//div[@class='section warning']//div[@class='section-header']")
	public static WebElement header_title_Favourites;

	@FindBy(how = How.XPATH, using = ".//div[@class='section-header']")
	public static WebElement HeaderList;

	@FindBy(how = How.XPATH, using = "//*[@id=\"layoutContainers\"]/div/div[2]/div[3]/div[2]/section/div/div[2]/div[1]")
	public static WebElement alertHeaderTitle1;

	@FindBy(how = How.XPATH, using = "//select[@id='default-site']")
	public static WebElement defaultSite;

	@FindBy(how = How.XPATH, using = "//button[@id='default-save']")
	public static WebElement saveButton;

	/*
	 * @FindBy(how = How.XPATH, using =
	 * "//*[@id=\"layoutContainers\"]/div/div[2]/div[3]/div[3]/section/div/div[2]/div[1]/h3")
	 * public static WebElement Announcements;
	 */

	@FindBy(how = How.XPATH, using = "//div[@id='layoutContainers']//div[@class='section-header']/h3[@class='section-title announcement']")
	public static WebElement Announcements;

	/*
	 * @FindBy(how = How.XPATH, using =
	 * "//*[@id=\"layoutContainers\"]/div/div[2]/div[3]/div[4]/section/div/div[2]/div[1]/h3")
	 * public static WebElement Favoritess;
	 */
	@FindBy(how = How.XPATH, using = "//div[@id='layoutContainers']//div[@class='section-header']/h3[@class='section-title']")
	public static WebElement Favoritess;

	@FindBy(how = How.XPATH, using = ".//div[@class='section-header']")
	public static List<WebElement> element;

	@FindBy(how = How.XPATH, using = "//*[@id=\"layoutContainers\"]//div[@name=\"right\"]//div[@class='section-header']")
	public static List<WebElement> elementHeader;

	/**
	 * @param title
	 * @param TCID
	 * @throws Throwable
	 */
	public static void checkUserLogIntoHomepage(String strExpectedTitle, String strTCID) throws Throwable {

		String strPageTitle = HomDriver.getTitle();
		String strPageName = "";
		// String strExpectedTitle = null;
		WaitFactory.waitForPageLoaded();
		LogFactory.info(" Page Title " + strPageTitle + " is displayed on the homepage");
		String strFlag = "FAIL";
		String strResult = "Page Title & Header are not showing as expected";

		try {
			if (ValidationFactory.isElementPresent(wbelTitleOfHomePage)) {
				strPageName = wbelTitleOfHomePage.getText();
				if (strPageTitle.equalsIgnoreCase(strExpectedTitle) && strPageName.equalsIgnoreCase(strExpectedTitle)) {
					strFlag = "PASS";
					strResult = "Page Title  and Page Header are showing as expected";
				}
			}
			ReportFactory.reporterOutput(strTCID, "Verify the Page Title & Header Name on the homepage",
					strExpectedTitle, "Page Title & Page Header on Homepage should be " + strPageTitle, strResult,
					strFlag);
		} catch (Throwable e) {

			ReportFactory.reporterOutput(strTCID, "Verify page title and page header on home page", "NA", "NA",
					e.getMessage().toString(), strFlag);
		}
	}

	/**
	 * @param welcomemsg
	 * @param TCID
	 * @throws Throwable
	 */
	public static void getWelcomeMessageOnHomepage(String strWelcomemsg, String strTCID) throws Throwable {

		String strWelcomeMsg = wbelUserInfo.getText();
		String strWelcome = "";

		LogFactory.info(" Welcome username :" + strWelcomeMsg);
		String strFlag = "FAIL";
		String strResult = "Welcome message is not displayed";

		try {
			if (ValidationFactory.isElementPresent(wbelUserInfo)) {

				strWelcome = (GenericFactory.splitString(strWelcomeMsg, ",").get(0).toString().trim());

				if (strWelcomeMsg.contains(strWelcome)) {
					strFlag = "PASS";
					strResult = "Welcome message is displayed :" + strWelcome;
				}
			}

			ReportFactory.reporterOutput(strTCID, "Verify welcome message on homepage", strWelcomemsg,
					"Welcome message on homepage should be displayed ", strResult, strFlag);

		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID, "Verify welcome message on homepage", strWelcomemsg,
					"Welcome message on homepage should be displayed ", e.getMessage().toString(), strFlag);
		}

	}

	/**
	 * @author shrey.choudhary
	 * @createdAt 22-05-2018
	 * @param testData
	 * @param TCID
	 * @throws Throwable
	 * @modifiedAt 22-05-2018
	 */

	public static void getDealerPrincipalRole(String strDealerPrincipleRole, String strTestData, String strTCID)
			throws Throwable {

		String strFlag = "FAIL";
		String strResult = "Dealer is not having dealer principal role";

		try {

			if (strTestData.equals("NA") && strDealerPrincipleRole.equalsIgnoreCase("Y")) {
				strFlag = "FAIL";
				strResult= "Invalid TestData ";
				ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", strTestData,
						"Verify Dealer Principal role.", strResult, strFlag);
	
			}
			
			else if (strTestData.equals("NA") && strDealerPrincipleRole.equalsIgnoreCase("N")) {
				strFlag = "Pass";
				strResult= "Dealer Principal Role is not applicable for the user ";
				ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", strTestData,
						"Verify Dealer Principal role.", strResult, strFlag);
	
			}
			else {

				List<String> frameList = new ArrayList<String>();
				for (int i = 0; i < PortalLeftNavigation_POF.ListAllActiveLinks.size(); i++) {
					String temp = PortalLeftNavigation_POF.ListAllActiveLinks.get(i).getText();
					frameList.add(temp);
				}

				if (frameList.contains(strTestData) && strDealerPrincipleRole.equalsIgnoreCase("Y")) {
					strFlag = "Pass";
					strResult = "Dealer has the Dealer Priciple role and also link Dealer Priciple found in the Left Naviagation";
				}

				if (!frameList.contains(strTestData) && strDealerPrincipleRole.equalsIgnoreCase("Y")) {
					strFlag = "Fail";
					strResult = "Dealer has the Dealer Priciple role but link Dealer Priciple not found in the Left Naviagation";
				}
				
				if (frameList.contains(strTestData) && strDealerPrincipleRole.equalsIgnoreCase("N")) {
					strFlag = "Fail";
					strResult = "Dealer does not have Dealer Priciple role but link Dealer Priciple found in the Left Naviagation";
				}
				
				if (!frameList.contains(strTestData) && strDealerPrincipleRole.equalsIgnoreCase("N")) {
					strFlag = "Pass";
					strResult = "Dealer does not have Dealer Priciple role and the link Dealer Priciple not found in the Left Naviagation";
				}


				ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", strTestData,
						"Verify Dealer Principal role.", strResult, strFlag);
			}

		} catch (Throwable e) {

			ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", strTestData,
					"Verify Dealer Principal role.", e.getMessage().toString(), strFlag);
		}
	}

	/*public static void verifyNotificationsList(String TCID) throws Throwable {

		String flag = "Fail";
		try {
			// if (ValidationFactory.isElementPresent(notificationLink))
			if (ValidationFactory.isElementPresent(wbNotificationLink)) { // Aniket - changed isDisplayed to
																			// isElementPresent method
				wbNotificationLink.click();
				// List<String> ExpectedData = GenericFactory.splitString(strNotification, ",");
				// if (!strNotification.equalsIgnoreCase("NA")) {
				List<WebElement> wbNotification = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='list-item']/div"));
				List<String> notificationList = new ArrayList<String>();
				for (int i = 0; i < wbNotification.size(); i++) {
					String temp = wbNotification.get(i).getText();
					notificationList.add(temp);
				}

				ReportFactory.reporterOutput(TCID, "Verify notifications on homepage.", "NA",
						"Notifications list should be displayed,if available.", "Notifications  : " + notificationList,
						"Pass");
				wbNotificationLink.click();
				// }

			} else {
				ReportFactory.reporterOutput(TCID, "Verify notifications on homepage.", "NA",
						"Notifications list should be displayed,if available.", "Notification iocn is not available",
						"Pass");
			}
		}

		catch (Exception e) {
			ReportFactory.reporterOutput(TCID, "Verify notification link names on homepage.", "NA",
					"Verify notification link names on homepage.", e.getMessage().toString().substring(0, 125), flag);
		}
	}*/

	public static void verifyNotificationIconOnHomePage(String TCID) throws Throwable {
		String flag = "Fail";
		String result = "Notification link is not applicable/available";

		try {
			WaitFactory.WaitForElementToVisible(MyPreferences_POF.wbelproductsegmenticon); // added by Aniket
			if (ValidationFactory.isElementPresent(wbNotificationLink)) {
				result = "Notification link is available";
				flag = "Pass";
			}
			ReportFactory.reporterOutput(TCID, "Verify notification icon on homepage.", "NA",
					"Verify notification link on homepage.", result, flag);

		} catch (NoSuchElementException e) {

			ReportFactory.reporterOutput(TCID, "Verify notification icon on homepage.", "NA",
					"Verify notification link on homepage.", result, flag);
		} catch (Exception e) {
			ReportFactory.reporterOutput(TCID, "Verify notification icon on homepage.", "NA",
					"Verify notification link on homepage.", e.getMessage().toString().substring(0, 125), flag);
		}
	}

	// To verify homepage footer Links
	public static void verifyFooterLinksonHomepage(List<String> header, String TCID) throws Throwable {
		// String TCID = "Footer Links";
		String flag = "Fail";
		String result = "Footer links are not showing as expected";
		ArrayList<String> StrhomepageFooterList = new ArrayList<String>();
		try {
			List<WebElement> WeHomepageFooterList = GenericFactory.getLinksFromFrame(homepageFooterFramePath);
			for (int i = 0; i < WeHomepageFooterList.size(); i++) {
				String hearlinktxtvalue = WeHomepageFooterList.get(i).getText().toString().trim();
				StrhomepageFooterList.add(hearlinktxtvalue);
			}
			/*
			 * Collections.sort(StrhomepageFooterList); Collections.sort(header);
			 */
			System.out.println("Test");
			if (StrhomepageFooterList.equals((header))) {
				flag = "Pass";
				result = "Footer links are showing as expected";
				LogFactory.info(header + " is present on Footer link");
			} else {
				flag = "Fail";
				result = "FooterLinks is not showing as expected on the UI ";
				LogFactory.info(header + " is not present on Footer link ");
			}
			ReportFactory.reporterOutput(TCID, "Verify the sequence & presence of Footer links on the homepage ",
					header.toString(), "Footer links should be present and show in sequence ",
					result + " Actual Footer Links : " + StrhomepageFooterList.toString(), flag);

		} catch (Exception e) {
			// LogFactory.error("e");
			String er = e.getMessage().substring(0, 35).toString().trim();

			ReportFactory.reporterOutput(TCID, "Verify the sequence & presence of Footer links on the homepage", "NA",
					"FooterLinks should show as per on the UI.", er, flag);

		}
	}

	/*
	 * @author sweta.ranjan
	 * 
	 * @createdAt 04-05-2018
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 * 
	 * @modifyBy sweta.ranjan
	 * 
	 * @modifyAt 12-07-2018
	 */

	// To verify homepage footer Links
	public static void verifyCopyrightOfFooterLinksonHomepage(String TCID) throws Throwable {
		// String TCID = "Copyright Footer Links";
		String flag = "Fail";
		String result = "Copyright is displayed with incorrect year";

		// Store all footer links in an arraylist
		ArrayList<String> StrhomepageFooterList = new ArrayList<String>();

		// getting system DateTime
		String actualCopyText = "";
		// String expectedCopyText= "Copyright";
		String copyrirightText = copyrightFooterPath.getText().toString().trim();
		try {
			List<String> copyrightYr = GenericFactory.splitString(copyrirightText, "Â©");
			List<String> copyrightYrNew = GenericFactory.splitString(copyrightYr.get(1), " ");

			String copyrightYrValue = copyrightYrNew.get(0);
			Date date = new Date();
			String systemYr = DateFactory.captureYear(date);

			// getting UI 'copyright' link date
			if (copyrightYrValue.equals(systemYr)) {
				flag = "Pass";
				result = "Copyright is displayed with current year ";
				LogFactory.info("Copyright Date is correct as FY yr.");
			} else {
				flag = "Fail";
				result = "Copyright is displayed with incorrect year";
				LogFactory.info("Copyright Date is not correct as FY yr.");
			}

			ReportFactory.reporterOutput(TCID, "Verify copyright link is displayed as per current year", "NA",
					"Copyright link should be displayed with current year", result, flag);

		} catch (Exception e) {
			// LogFactory.error("e");
			String er = e.getMessage().substring(0, 35).toString().trim();

			ReportFactory.reporterOutput(TCID, "verify copyright footerLinks on homepage", "NA",
					"Copyright footerLinks should show as per on the UI.", er, flag);

		}
	}

	// ***************************************************** E-O-M
	// *****************************************************************************

	public static void checkorderofportletsdisplayed(String TCID) throws Throwable {
		String flag = "Fail";
		String resultPortletOrder = "";
		try {
			// List adding portlet frames
			ArrayList<WebElement> listPortletframes = new ArrayList<WebElement>();
			listPortletframes.add(searchIndex);
			listPortletframes.add(alertIndex);
			listPortletframes.add(announcementIndex);
			listPortletframes.add(favoritesIndex);

			int intListPortletFrameSize = listPortletframes.size();

			HashMap<Integer, String> headerTxtHomePage = new HashMap<Integer, String>();
			int intListPageSize = sectionHeader.size();

			for (int i = 0; i < sectionHeader.size(); i++) {
				headerTxtHomePage.put(i, sectionHeader.get(i).getText());

				if (sectionHeader.get(i).getText().contains("My DealerPath Announcements")) {

					String strAnnouncementHeaderTxt = sectionHeader.get(i).getText();
					String strAnnouncementTxt = strAnnouncementHeaderTxt.substring(0,
							strAnnouncementHeaderTxt.lastIndexOf('(') - 1);

					headerTxtHomePage.put(i, strAnnouncementTxt);
				}

				// System.out.println("List of elements :" + sectionHeader.get(i).getText());
			}
			HashMap<Integer, String> headerTxtExcelValues = new HashMap<Integer, String>();
			headerTxtExcelValues.put(0, "DealerPath Alerts");
			headerTxtExcelValues.put(1, "My DealerPath Announcements");
			headerTxtExcelValues.put(2, "My DealerPath Favorites");

			int headerValue = headerTxtHomePage.size();

			switch (headerValue) {
			case 3:
				resultPortletOrder = "Portlets are not displayed as expected";
				for (int i = 0; i < headerTxtExcelValues.size(); i++) {
					if (headerTxtExcelValues.get(i).equals(headerTxtHomePage.get(i))) {
						flag = "Pass";
						resultPortletOrder = "Alerts,Announcements & Favourites portlet order are displayed as expected";
						LogFactory.info(
								headerTxtExcelValues.get(i) + " " + "are equal" + " " + headerTxtExcelValues.get(i));
					}

					else {
						LogFactory.info(headerTxtExcelValues.get(i) + " " + "are not equal" + " "
								+ headerTxtExcelValues.get(i));
						flag = "Fail";
						break;
					}

				}
				LogFactory.info("All the portlets are present in order");
				ReportFactory.reporterOutput(TCID,
						"Verify Portlets displayed in order :Alert portlet first,announcement portlet second and favourites portlet last.",
						"NA",
						"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
						resultPortletOrder, flag);
				break;
			case 2:

				resultPortletOrder = "Portlets are not displayed as expected";

				for (int i = 0; i < headerTxtHomePage.size(); i++) {

					String strHeaderTxt = headerTxtHomePage.get(i);
					if (strHeaderTxt.equals("DealerPath Alerts")) {
						flag = "Pass";
						resultPortletOrder = "Alert portlet is displayed first and above announcements & favourites portlet";
						LogFactory.info("ALerts portlet is displayed first ");
						ReportFactory.reporterOutput(TCID,
								"Verify Portlets displayed in order :Alert portlet first,announcement portlet second and favourites portlet last.",
								"NA",
								"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
								resultPortletOrder, flag);
						// break;
					} else if (strHeaderTxt.equals("My DealerPath Announcements")) {
						flag = "Pass";
						resultPortletOrder = "Announcement portlet is displayed as expected and above favourites portlet or below alerts portlet";
						LogFactory.info("Announcements portlet is displayed");
						ReportFactory.reporterOutput(TCID,
								"Verify Portlets displayed in order :Alert portlet first,announcement portlet second and favourites portlet last.",
								"NA",
								"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
								resultPortletOrder, flag);
						break;
					} else if (strHeaderTxt.equals("My DealerPath Favourites")) {
						flag = "Pass";
						LogFactory.info("Favourites portlet is displayed");
						resultPortletOrder = "Favorites portlet is displayed at last";
						ReportFactory.reporterOutput(TCID,
								"Verify Portlets displayed in order :Alert portlet first,announcement portlet second and favourites portlet last.",
								"NA",
								"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
								resultPortletOrder, flag);
						break;
					} else {
						flag = "Fail";
						ReportFactory.reporterOutput(TCID,
								"Verify Portlets displayed in order :Alert portlet first,announcement portlet second and favourites portlet last.",
								"NA",
								"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
								resultPortletOrder, flag);
						break;
					}

				}

				break;
			case 1:

				for (int i = 0; i < headerTxtHomePage.size(); i++) {
					String header = headerTxtHomePage.get(i);
					LogFactory.info("Only" + header + "portlet is present");
					resultPortletOrder = "Only" + header + "portlet is displayed as expected.";
					flag = "Pass";
				}
				ReportFactory.reporterOutput(TCID, "Verify order of portlets displayed", "NA",
						"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
						resultPortletOrder, flag);

				break;
			default:

				String resultOrder = "No portlets are displayed";
				LogFactory.info("No portlets are displayed");
				flag = "Fail";
				ReportFactory.reporterOutput(TCID, "Verify order of portlets displayed", "NA",
						"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
						resultOrder, flag);
			}

		} catch (Exception e) {
			ReportFactory.reporterOutput(TCID, "Verify order of portlets displayed", "NA",
					"Portlets should be displayed in order :Alert portlet in first,announcement portlet in second and favourites portlet in last",
					e.getMessage().toString().substring(0, 125), flag);
		}
	}

	// Verify selected theme color on My preference is as per expected value
	// ********************************************************************************************************************************************

	public static void verifyUserSelectdTheme(String strExpectedValue, String strTCID) throws Throwable {

		String strResult = "Selected theme color on My Preferences is not as Expected";
		String strFlag = "Fail";

		GenericFactory.utilityMenuMyPreferenceClick();

		WebElement wbelSelectedValue = WaitFactory.WaitForElementToVisible(
				HomDriver.findElement(By.xpath("//div[@class='group-value radio-value']//div[1]//label[1]//div")));

		String strSelectedValue = wbelSelectedValue.getText().toString().trim();
		if (strSelectedValue.equalsIgnoreCase(strExpectedValue.trim())) {
			strFlag = "Pass";
			strResult = "Selected theme color on My Preferences is as Expected";
		}

		ReportFactory.reporterOutput(strTCID, "Verify text of selected theme color", strExpectedValue,
				"Selected theme color text should be as per Input value", strResult, strFlag);

		WaitFactory.WaitForElementToVisible(
				HomDriver.findElement(By.xpath("//div[@id='my-preferences']//button[@class='close']//span")));
		((JavascriptExecutor) HomDriver).executeScript("arguments[0].scrollIntoView(true);",
				HomDriver.findElement(By.xpath("//div[@id='my-preferences']//button[@class='close']//span")));
		WaitFactory
				.waitForElementClickable(
						HomDriver.findElement(By.xpath("//div[@id='my-preferences']//button[@class='close']//span")))
				.click();

	}

	@SuppressWarnings("null")
	public static void verifyOrderofPortletsOnHomepage(String strTCID) throws Throwable {
		boolean dAlertsFlag = false;
		boolean dAnnouncementsFlag = false;
		boolean dFavoritesFlag = false;
		int dAlertsIndex = 0;
		int dAnnouncementsIndex = 0;
		int dFavoritesIndex = 0;
		String strFlag = "Fail";
		String arrPortletHeader = "";

		String announcementHeaderTxt = "";
		WebElement alertHeaderTitle = null;

		for (int i = 0; i < element.size(); i++) {
			LogFactory.info("Total Portlet present on page " + element.get(i).getText().toString());
		}

		String AlertHeaderTxtt = "";
		if (ValidationFactory.isElementPresent(
				By.xpath("//*[@id=\"layoutContainers\"]/div/div[2]/div[3]/div[2]/section/div/div[2]/div[1]"))) {
			AlertHeaderTxtt = alertHeaderTitle1.getText().toString().trim();
			LogFactory.info("Alert Portlet exists on page as: " + AlertHeaderTxtt);

		} else {
			LogFactory.info("Alert Portlet is not present on page ");
		}

		String AnnouncementsHeaderTxt = "";
		if (ValidationFactory.isElementPresent(Announcements)) {
			AnnouncementsHeaderTxt = Announcements.getText().toString().trim();
			LogFactory.info("Announcements Portlet exists on page as: " + AnnouncementsHeaderTxt);
		} else {
			LogFactory.info("Announcements Portlet is not present on page ");
		}

		String FavoritesHeaderTxt = "";
		if (ValidationFactory.isElementPresent(Favoritess)) {
			FavoritesHeaderTxt = Favoritess.getText().toString().trim();
			LogFactory.info("Favorites Portlet exists on page as: " + FavoritesHeaderTxt);
		} else {
			LogFactory.info("Favorites Portlet is not present on page ");
		}

		for (int i = 0; i < elementHeader.size(); i++) {
			String headertext = elementHeader.get(i).getText().toString().trim();
			if (headertext.contains(AlertHeaderTxtt) && !(AlertHeaderTxtt.isEmpty())) { // isEmpty check added because
																						// contains was returning true
																						// even alert portlet not found
																						// - Aniket
				LogFactory.info("DealerPath Alerts is present as : " + headertext);
				dAlertsFlag = true;
				dAlertsIndex = i;
				strFlag = "Pass";
				arrPortletHeader = arrPortletHeader + " " + AlertHeaderTxtt + ",";
			} else if (headertext.contains(AnnouncementsHeaderTxt) && !(AnnouncementsHeaderTxt.isEmpty())) { // isEmpty
																												// check
																												// added
																												// because
																												// contains
																												// was
																												// returning
																												// true
																												// even
																												// announcements
																												// portlet
																												// not
																												// found
																												// -
																												// Aniket
				LogFactory.info("Announcements is present as:" + headertext);
				dAnnouncementsFlag = true;
				dAnnouncementsIndex = i;
				strFlag = "Pass";
				arrPortletHeader = arrPortletHeader + " " + AnnouncementsHeaderTxt + ",";
			} else if (headertext.contains(FavoritesHeaderTxt) && !(FavoritesHeaderTxt.isEmpty())) { // isEmpty check
																										// added because
																										// contains was
																										// returning
																										// true even
																										// favourites
																										// portlet not
																										// found -
																										// Aniket
				LogFactory.info("Favorites is present as: " + headertext);
				dFavoritesFlag = true;
				dFavoritesIndex = i;
				strFlag = "Pass";
				arrPortletHeader = arrPortletHeader + ' ' + FavoritesHeaderTxt;
			}
		}
		if ((dAlertsFlag == true) && (dAnnouncementsFlag = true) && (dFavoritesFlag == true)) {
			if ((dFavoritesIndex > dAnnouncementsIndex) && (dAnnouncementsIndex > dAlertsIndex)) {
				strFlag = "Pass";
				LogFactory.info(
						"Portlet is displayed in correct sequence Alert, Announcements and Favorites below each other ");
				ReportFactory.reporterOutput(strTCID, "Verify the sequence of portlets displayed on the Home Page",
						"Available Portlets on Home page :" + arrPortletHeader,
						"Portlet order should be Alert First, Announcement Next and Favourits Last",
						"Portlets are displaying in correct sequence Alert, Announcements and Favorites below each other on home page",
						strFlag);
			}
		} else if ((dAlertsFlag == true) && (dAnnouncementsFlag = true) && (dFavoritesFlag == false)) {
			if ((dAnnouncementsIndex > dAlertsIndex)) {
				strFlag = "Pass";
				LogFactory.info("Announcements is displayed below the Alert ");

				ReportFactory.reporterOutput(strTCID, "Verify the sequencing of portlets displayed on the Home Page",
						"Available Portlets on Home page :" + arrPortletHeader,
						"Portlet order should be Alert First & Announcement Next",
						"Announcements is displayed below the Alert", strFlag);
			}
		} else if ((dAlertsFlag == false) && (dAnnouncementsFlag = true) && (dFavoritesFlag == true)) {
			if ((dFavoritesIndex > dAnnouncementsIndex)) {
				strFlag = "Pass";
				LogFactory.info("Favorites is displayed below the Announcements ");

				ReportFactory.reporterOutput(strTCID, "Verify the sequence of portlets displayed on the Home Page",
						"Available Portlets on Home page :" + arrPortletHeader,
						"Portlet order should be Alert First & Announcement Next",
						"Favorites is displayed below the Announcements", strFlag);
			}
		} else if ((dAlertsFlag == true) && (dAnnouncementsFlag = false) && (dFavoritesFlag == true)) {

			if ((dFavoritesIndex > dAlertsIndex)) {
				strFlag = "Pass";
				LogFactory.info("Favorites is displayed below the alert ");

				ReportFactory.reporterOutput(strTCID, "Verify the sequence of portlets displayed on the Home Page",
						"Available Portlets on Home page :" + arrPortletHeader,
						"Portlet order should be Alert First & Announcement Next",
						"Favorites is displayed below the Alerts", strFlag);
			}
		}

		else if ((dAlertsFlag == false) && (dAnnouncementsFlag = false) && (dFavoritesFlag == true)) {

			strFlag = "Pass";
			LogFactory.info("Only Favorites Portlet is displayed ");

			ReportFactory.reporterOutput(strTCID, "Verify the sequence of portlets displayed on the Home Page",
					"Available Portlets on Home page :" + arrPortletHeader,
					"Portlet order should be Alert First, Announcement Next and Favorites Last",
					"Only Favorites portlet is available ", strFlag);
		} else if ((dAlertsFlag == false) && (dAnnouncementsFlag = false) && (dFavoritesFlag == false)) {

			strFlag = "Fail";
			LogFactory.info("No Portlets are dispalyed on the Home page ");

			ReportFactory.reporterOutput(strTCID, "Verify the sequence of portlets displayed on the Home Page", "NA",
					"Portlet order should be Alert First, Announcement Next and Favorites Last",
					"No Portlets Dispalyed", strFlag);
		}
	}

	public static void verifyNewEmployee(String region, String strTCID) throws Throwable {
		WaitFactory.waitForPageLoaded();
		String strFlag = "FAIL";
		String strFlagMyPre = "FAIL";
		String strResult = "Default site for the User is not showing as expected";
		try {
			WaitFactory.WaitForElementToVisible(defaultSite);
			if (ValidationFactory.isElementPresent(defaultSite)
					&& GenericFactory.compareInputWithActualTextFromDrpdwn(defaultSite, region)) {
				strFlag = "PASS";
				strResult = "Default site for the User is showing as expected";
				// write the code of save
				if (ValidationFactory.isElementPresent(saveButton))
					saveButton.click();
				// String strFlag = "Fail";
				String result = "";
				String currentURL, SelectedSite;
				List<String> siteListFromApplication = new ArrayList<String>();
				try {
					WaitFactory.FluentWaitByWebElement(MyPreferences_POF.strHomeDealer);
					MyPreferences_POF.navigateToMyPreferences();
					Thread.sleep(1000);
					WaitFactory.waitForPageLoaded();
					WaitFactory.FluentWaitByWebElement(MyPreferences_POF.wbelMyPreferencePreferredSite);
					System.out.println(
							"***********************************************************Navigated to My Preference page1");
					if (ValidationFactory.isElementPresent(MyPreferences_POF.wbelMyPreferencePreferredSite)
							&& GenericFactory.compareInputWithActualTextFromDrpdwn(
									MyPreferences_POF.wbelMyPreferencePreferredSite, region)) {
						strFlagMyPre = "Pass";
						strResult = "Expected list of Sites is populated under Preferred site drop down";
						MyPreferences_POF.wbelMyPreferenceModalSaveButton.click();
					} else {
						strFlagMyPre = "Fail";
						strResult = "Expected list of Sites is NOT populated under Preferred site drop down";
						if (ValidationFactory.isElementPresent(MyPreferences_POF.wbelMyPreferenceModalSaveButton)) {
							MyPreferences_POF.wbelMyPreferenceModalSaveButton.click();
							WaitFactory.waitForPageLoaded();
						}
					}

					ReportFactory.reporterOutput(strTCID + "Verify the Default site for the User is Displayed",
							"Verify default site: " + region
									+ " for the user should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.",
							region,
							"Verify default site: " + region
									+ " for the user should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees. ",
							strResult, strFlagMyPre);
				} catch (Exception e) {
					strFlag = "Fail";
					System.out.println(e.getMessage().toString());
					ReportFactory.reporterOutput(strTCID, "Verify that the URL is displayed with the expected contents",
							region, "Verify that the URL is displayed with the expected contents",
							"Element is not visible", strFlag);

				}
			} else {
				ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User", region,
						"Default site for the User should be " + region, "User is not new Employee...", strFlag);
			}
		} catch (Exception e) {
			if (e.getClass().getName().contains("java.lang.AssertionError"))
				Assert.assertFalse(true, strResult);
			else
				ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User", region,
						"Default site for the User should be " + region, strResult, strFlag);
		}
	}

	public static void verifyNewEmployee(String rackfId, String region, String strTCID) throws Throwable {
		
		String userRacfIDOld=strUserRACFID;
		
		Thread.sleep(5000);
		
		GenericFactory.EndImpersonateUSER();
		WaitFactory.waitForPageLoaded();
		if (GenericFactory.impersonateUser(rackfId)) {
			String strFlag = "FAIL";
			String strFlagMyPre = "FAIL";
			String strResult = "Default site for the User is not showing as expected";
			try {
				WaitFactory.waitForPageLoaded();
				if (ValidationFactory.isElementPresent(defaultSite)) {
					if (GenericFactory.compareInputWithActualTextFromDrpdwn(defaultSite, region)) {
						strFlag = "PASS";
						strResult = "Default site for the User is already selected same as mentioned in data sheet i.e."
								+ region;
					} else if (ValidationFactory.isElementPresent(defaultSite)
							&& !GenericFactory.compareInputWithActualTextFromDrpdwn(defaultSite, region)
							&& GenericFactory.selectByVisibleText(defaultSite, region)) {
						// GenericFactory.selectByVisibleText(defaultSite,region);
						strFlag = "PASS";
						strResult = "Default site " + region + " for the User is selected ";
					} else {
						strResult = "Default site " + region
								+ " given in sheet for the User is not listed into the dropdown values... ";
						BaseClass.wbDriver.get(BaseClass.endImpersonateURL);
						WaitFactory.waitForPageLoaded();
					}
					if (ValidationFactory.isElementPresent(saveButton)
							&& GenericFactory.compareInputWithActualTextFromDrpdwn(defaultSite, region)) {
						saveButton.click();
						WaitFactory.waitForPageLoaded();
						// String strFlag = "Fail";
						String result = "";
						String currentURL, SelectedSite;
						List<String> siteListFromApplication = new ArrayList<String>();
						try {
							WaitFactory.FluentWaitByWebElement(MyPreferences_POF.strHomeDealer);
							MyPreferences_POF.navigateToMyPreferences();
							Thread.sleep(1000);
							WaitFactory.waitForPageLoaded();
							WaitFactory.FluentWaitByWebElement(MyPreferences_POF.wbelMyPreferencePreferredSite);
							System.out.println(
									"***********************************************************Navigated to My Preference page1");
							if (ValidationFactory.isElementPresent(MyPreferences_POF.wbelMyPreferencePreferredSite)
									&& GenericFactory.compareInputWithActualTextFromDrpdwn(
											MyPreferences_POF.wbelMyPreferencePreferredSite, region)) {
								strFlagMyPre = "Pass";
								strResult = "New Employee is successfully switched to " + region;
								MyPreferences_POF.wbelMyPreferenceModalSaveButton.click();
							} else {
								strFlagMyPre = "Fail";
								strResult = "New Employee is not successfully switched to " + region;
								if (ValidationFactory
										.isElementPresent(MyPreferences_POF.wbelMyPreferenceModalSaveButton)) {
									MyPreferences_POF.wbelMyPreferenceModalSaveButton.click();
									WaitFactory.waitForPageLoaded();
								}
							}

							ReportFactory.reporterOutput(strTCID, "Verify default site: " + region + " for the user '"
									+ rackfId
									+ "' should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.",
									"Site: " + region + ", User: " + rackfId,
									"Verify default site: " + region + " for the user '" + rackfId
											+ "' should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees. ",
									strResult, strFlagMyPre);
							// End current user as it's just for checking User is new or not
							Thread.sleep(5000);
							GenericFactory.EndImpersonateUSER();
							WaitFactory.waitForPageLoaded();
							strUserRACFID=userRacfIDOld;
							System.out.println("Re-impoersonating Dealer:" + IterationTest.strUserRACFID);
							GenericFactory.impersonateUser(strUserRACFID);
						} catch (Exception e) {
							strFlag = "Fail";
							System.out.println(e.getMessage().toString());
							ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User " + rackfId,
									"Site: " + region + ", User: " + rackfId,
									"Default site for the User '" + rackfId + "' should be " + region, strResult,
									strFlag);

						}

					} else { // End current user as it's just for checking User is new or not
						Thread.sleep(5000);
						GenericFactory.EndImpersonateUSER();
						WaitFactory.waitForPageLoaded();
						GenericFactory.impersonateUser(BaseClass.strUserRACFID);
						ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User " + rackfId,
								"Site: " + region + ", User: " + rackfId,
								"Default site for the User '" + rackfId + "' should be " + region,
								region + " is not found in dropdown value, please give valid region...", strFlag);
					}

				} else {
					// End current user as it's just for checking User is new or not
					Thread.sleep(5000);
					GenericFactory.EndImpersonateUSER();
					WaitFactory.waitForPageLoaded();
					GenericFactory.impersonateUser(BaseClass.strUserRACFID);
					ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User",
							"Site: " + region + ", User: " + rackfId,
							"Default site for the User '" + rackfId + "' should be " + region,
							"User is not new Employee...", strFlag);
				}

			} catch (Exception e) {
				if (e.getClass().getName().contains("java.lang.AssertionError"))
					Assert.assertFalse(true, strResult);
				else
					ReportFactory.reporterOutput(strTCID, "Verify the Default site for the User",
							"Site: " + region + ", User: " + rackfId,
							"Default site for the User '" + rackfId + "' should be " + region, strResult, strFlag);
			}
		} else {
			LogFactory.info("Entered Racf id is incorrect");
			ReportFactory.tableEnd();
			GenericFactory.createHeaderSection("Error Impersoanate User " + BaseClass.strUserRACFID);

			ReportFactory.reporterOutput("Error Impersonate", "User unable to login", rackfId,
					"User should redirect to Impersonate New User",
					"Reason could be : DealerPath Application is not available [AccountFlex is down] ", "Fail");
			if (ValidationFactory.isElementPresent(By.xpath("//a[@id='endimpersonatelink']")))
				BaseClass.wbDriver.findElement(By.xpath("//a[@id='endimpersonatelink']")).click();
			ReportFactory.tableEnd();
		}
	}

	/*public static void verifyNotificationsList(String strNotification, String TCID, String PageName) throws Throwable {

		String flag = "Fail";
		try {
			if (ValidationFactory.isElementPresent(wbNotificationLink)) {
				// if (wbNotificationLink.isDisplayed()) {
				wbNotificationLink.click();
				Thread.sleep(3000);
				List<String> ExpectedData = GenericFactory.splitString(strNotification, ",");
				if (!strNotification.equalsIgnoreCase("NA")) {
					List<WebElement> wbNotification = BaseClass.wbDriver
							.findElements(By.xpath(".//*[@class='list-item']/div"));
					List<String> notificationList = new ArrayList<String>();
					for (int i = 0; i < wbNotification.size(); i++) {
						String temp = wbNotification.get(i).getText();
						notificationList.add(temp);
					}
					System.out.println(notificationList);
					if (notificationList.equals(ExpectedData)) {
						flag = "Pass";
					}
					ReportFactory.reporterOutput(TCID, "Verify notification link names on " + PageName,
							"Input data is : " + ExpectedData, "Notification list should match with Input data",
							"Actual data : " + notificationList, flag);
				} else {
					ReportFactory.reporterOutput(TCID, "Verify notification link names on " + PageName,
							"Input data is : " + ExpectedData, "Notification list should match with Input data",
							"Notification is not mentioned in the Testdata", flag);
				}

			} else {
				ReportFactory.reporterOutput(TCID, "Verify notification link names on " + PageName, "Notification List",
						"Notification list should match with Input data", "Notification List is not present.", flag);
			}
		} catch (Exception e) {
			ReportFactory.reporterOutput(TCID, "Verify notification link names on " + PageName, "NA",
					"Verify notification link names on homepage.", e.getMessage().toString().substring(0, 125), flag);
		}
	}
*/

	
	public static void verifyNotificationsList(String TCID, String PageName) throws Throwable {

        String flag = "Fail";
        try {
               if (ValidationFactory.isElementPresent(wbNotificationLink)) {
                     wbNotificationLink.click();
                     List<WebElement> wbNotification = BaseClass.wbDriver
                                   .findElements(By.xpath(".//*[@class='list-item']/div"));
                     List<String> notificationList = new ArrayList<String>();
                     for (int i = 0; i < wbNotification.size(); i++) {
                            String temp = wbNotification.get(i).getText();
                            notificationList.add(temp);
                     }

                     ReportFactory.reporterOutput(TCID, "Verify notification link names on " +PageName, "NA",
                                   "Notifications list should be displayed,if available.", "Notifications  : " + notificationList,
                                   "Pass");
                     wbNotificationLink.click();
                     // }

               } else {
                     ReportFactory.reporterOutput(TCID, "Verify notification link names on "+PageName, "NA",
                                   "Notifications list should be displayed,if available.", "Notification icon is not available",
                                   "Pass");
               }
        }

        catch (Exception e) {
               ReportFactory.reporterOutput(TCID, "Verify notification link names on "+PageName, "NA",
                            "Verify notification link names on homepage.", e.getMessage().toString().substring(0, 125), flag);
        }
 }

	
	
	public static ArrayList<String> getLeftWindowLinks() {
		List<WebElement> webElements = wbelLeftWindow.findElements(By.tagName("a"));
		int webElementsSize = webElements.size();
		HashMap<Integer, WebElement> hm = new HashMap<>();
		for (int i = 1; i < webElementsSize; i++) {
			hm.put(i + 1, webElements.get(i));
		}
		List<String> strList = new ArrayList<>();
		for (Entry<Integer, WebElement> entry : hm.entrySet()) {
			strList.add(entry.getValue().getText());
		}
		return (ArrayList<String>) strList;

	}

}

// *********************************************************************************************************************************************
