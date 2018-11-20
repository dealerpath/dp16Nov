
/* 
 * Project    : DealerPath
 * Script     : GenericFactory
 * Author     : Shrishail Baddi
 * Date       : April.08.2018
 * Last Modified On:
 * Modified By :
 */

package com.deere.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
/*import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;*/
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.deere.PageFactory.Alerts_POF;
import com.deere.PageFactory.Announcements_POF;
import com.deere.PageFactory.Homepage_POF;
import com.deere.PageFactory.MyPreferences_POF;
/*import com.deere.PageFactory.Alerts_POF;
import com.deere.PageFactory.Announcements_POF;*/
import com.deere.PageFactory.PortalLeftNavigation_POF;
import com.deere.PageFactory.PortletLinksPage_POF;

public class GenericFactory extends BaseClass {

	public static List<String> listOfProductSegmentsGlobal = null;

	public static LinkedHashMap getWCMByTCID(String TCID) {
		LinkedHashMap rowdata = null;
		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String value = (String) rowdata.get("Test Case ID");
				if (value.equalsIgnoreCase(TCID)) {
					System.out.println("wcm data by tcid" + rowdata);
					return rowdata;
				}
			}
		}
		return rowdata;
	}



	public static void readPropertyFile() {
		FileInputStream ip;
		strDataPath = strWorkingDir + "\\TestData\\" + configProperties.getProperty("TestData_File_Name").toString().trim()
				+ ".xlsx";
		MRUList = configProperties.getProperty("MRUList");
		LogFactory.info("Test data file path is :" + strDataPath);
	}
	
	public static List<String> splitString(String str, String reg) {

		List<String> strList = new ArrayList<String>();

		if (isNull(str)) {
			return strList;
		}

		StringTokenizer st = new StringTokenizer(str, reg);

		while (st.hasMoreElements()) {

			strList.add(st.nextToken().trim());
		}

		return strList;

	}

	/*
	 * method to determine if a string is null or 0 Yes returns true otherwise false
	 * 
	 * @param str
	 * 
	 * @return boolean
	 */

	public static boolean isNull(String str) {
		if (str == null || str.length() < 1) {
			return true;
		}

		return false;
	}

	// public static WebElement element=null;

	@SuppressWarnings("null")
	public static boolean impersonateUser(String strRACFID) {

		WebDriver driver = BaseClass.wbDriver;

		try {

			if (!isNull(strRACFID)) {

				if (ValidationFactory.isElementPresent(By.xpath("//b[text() = 'Admin Links']"))) {

					ValidationFactory.getElementIfPresent(By.xpath("//a[text() = 'Impersonate User']")).click();
					LogFactory.info("Clicked Impersonate User link from left navigation.....");
					WaitFactory.waitForPageLoaded();
					Thread.sleep(7000);
					
					LogFactory.info("Enter User RACF ID.....");
					WebElement ele = ValidationFactory.getElementIfPresent(By.xpath("//input [@name = 'inputText_AU']"));
					WaitFactory.WaitForElementToVisible(ele);
	
					ele.sendKeys(strRACFID);
				
					WaitFactory.WaitForTexttoBePresentInElement(ele,strRACFID);
	
					LogFactory.info("Click on Search Button.....");
					ValidationFactory.getElementIfPresent(By.xpath("//input [ @name = 'Search']")).click();

					if (ValidationFactory.isElementPresent(By.xpath("//input[@type='radio' and @name='userDNS']"))) {

						LogFactory.info("Select the user.....");
						ValidationFactory.getElementIfPresent(By.xpath("//input[@type='radio' and @name='userDNS']"))
								.click();

						if (ValidationFactory
								.validateButtonEnable(By.xpath("//input[@type='button' and @name='Impersonate']"))) {

							ValidationFactory
									.getElementIfPresent(By.xpath("//input[@type='button' and @name='Impersonate']"))
									.click();
							LogFactory.info("User " + strRACFID + " successfully logged into appllication ");

							if (ValidationFactory.isElementPresent(homePageFactory.defaultSite))// Added by Aniket S
							{
								BaseClass.errorUserFound = true;
								strUserRACFID = strRACFID;
								LogFactory.info("User " + strRACFID
										+ " is imporsonated successfully but he/she is New Emplpoyee ");
								return true;
							}

							return true;
						} else {
							LogFactory.error("Unable to locate the button Impersonate/ it is disabled");
							return false;
						}
					} else {
						LogFactory.error(strRACFID + " does not exist/No user Found");
						return false;
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

	public static void endImpersonateOrLogoutUser() {

		WebElement userelement = null;

		try {

			userelement = ValidationFactory
					.getElementIfPresent(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']"));

			if (userelement != null) {

				userelement.click();

				WebElement btnelement = ValidationFactory
						.getElementIfPresent(By.xpath("//button[@class='btn' and @onclick='endImpersonate(this)']"));

				if (btnelement != null) {

					btnelement.click();
				} else {
					ValidationFactory.getElementIfPresent(
							By.xpath("//button[@class='btn' and @onclick='logoutDealerPath(this)']")).click();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			LogFactory.info("Unable to logout or impersonate the user");
			//Assert.fail("Unable to logout or impersonate the user");

		}

	}

	/*
	 * public static void utilityMenuAdminClick() {
	 * 
	 * try {
	 * 
	 * WebElement userelement = null;
	 * 
	 * userelement = ValidationFactory .getElementIfPresent(By.
	 * xpath("//div[contains (@class,'user-info') and @id ='js-user-info']"));
	 * 
	 * if (userelement != null) {
	 * 
	 * userelement.click(); LogFactory.info("Utility Menu Clicked.....");
	 * 
	 * WaitFactory.waitForElementClickable(ValidationFactory.getElementIfPresent(By.
	 * xpath("//a[contains (@href ,'dealerpath.admin')]")));
	 * 
	 * ValidationFactory.getElementIfPresent(By.
	 * xpath("//a[contains (@href ,'dealerpath.admin')]")).click();
	 * LogFactory.info("Successfully clicked on Admin Link from utility manu....");
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * LogFactory.info("Unable to click on Admin utility link or Element not found"
	 * ); Assert.fail("Unable to click on Admin utility link or Element not found");
	 * 
	 * } }
	 * 
	 * public static void utilityMenuMyPreferenceClick() {
	 * 
	 * try {
	 * 
	 * WebElement userelement = null;
	 * 
	 * userelement = ValidationFactory .getElementIfPresent(By.
	 * xpath("//div[contains (@class,'user-info') and @id ='js-user-info']"));
	 * 
	 * if (userelement != null) {
	 * 
	 * userelement.click(); LogFactory.info("Utility Menu Clicked.....");
	 * 
	 * ValidationFactory.getElementIfPresent(By.xpath("//a[@id='preferences']")).
	 * click(); LogFactory.
	 * info("Successfully clicked on My Preference Link from utility manu....");
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.getMessage(); LogFactory.
	 * info("Unable to click on My Preference utility link or Element not found");
	 * Assert.
	 * fail("Unable to click on My Preference utility link or Element not found");
	 * 
	 * }
	 * 
	 * }
	 */

	public static void utilityMenuAdminClick() {
		try {

			WebElement userelement = null;
			userelement = WaitFactory.WaitForElementToVisible(BaseClass.wbDriver
					.findElement(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']")));
			// userelement = ValidationFactory.getElementIfPresent(By.xpath("//div[contains
			// (@class,'user-info') and @id ='js-user-info']"));

			if (userelement != null) {

				userelement.click();
				LogFactory.info("Utility Menu Clicked.....");
				// Thread.sleep(2000);
				WaitFactory
						.WaitForElementToVisible(
								BaseClass.wbDriver.findElement(By.xpath("//a[contains (@href ,'dealerpath.admin')]")))
						.click();
				;
				// ValidationFactory.getElementIfPresent(By.xpath("//a[contains (@href
				// ,'dealerpath.admin')]")).click();
				LogFactory.info("Successfully clicked on Admin Link from utility manu....");

			}

		} catch (Exception e) {
			e.printStackTrace();
			LogFactory.info("Unable to click on Admin utility link or Element not found");
			// Assert.fail("Unable to click on Admin utility link or Element not found");

		}
	}

	public static void utilityMenuMyPreferenceClick() {

		try {

			WebElement userelement = null;

			// userelement = ValidationFactory.getElementIfPresent(By.xpath("//div[contains
			// (@class,'user-info') and @id ='js-user-info']"));
			userelement = WaitFactory.WaitForElementToVisible(BaseClass.wbDriver
					.findElement(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']")));
			if (userelement != null) {

				userelement.click();
				Thread.sleep(1000);
				LogFactory.info("Utility Menu Clicked.....");

				// ValidationFactory.getElementIfPresent(By.xpath("//a[@id='preferences']")).click();

				WaitFactory.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath("//a[@id='preferences']")))
						.click();
				LogFactory.info("Successfully clicked on My Preference Link from utility manu....");

			}

		} catch (Exception e) {
			e.getMessage();
			LogFactory.info("Unable to click on My Preference utility link or Element not found");
			// Assert.fail("Unable to click on My Preference utility link or Element not
			// found");

		}

	}

	public static void utilityMenuSwitchSiteClick() {

		try {

			WebElement userelement = null;

			userelement = ValidationFactory
					.getElementIfPresent(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']"));

			if (userelement != null) {

				userelement.click();
				LogFactory.info("Utility Menu Clicked.....");

				ValidationFactory.getElementIfPresent(By.xpath("//a[ @id='switch']")).click();
				LogFactory.info("Successfully clicked on switch site Link from utility manu....");

			}

		} catch (Exception e) {
			e.printStackTrace();
			LogFactory.info("Unable to click on switch site  utility link or Element not found");
			//Assert.fail("Unable to click on switch site utility link or Element not found");

		}

	}

	public static void getUserPrefredLanguage() {

		try {

			WebElement element = null;

			utilityMenuMyPreferenceClick();

			element = ValidationFactory.getElementIfPresent(By.xpath("//select[@id='lang']"));

			if (element != null) {

				Select preferedLang = new Select(element);

				BaseClass.strUserPrefLang = preferedLang.getFirstSelectedOption().getText();
				ValidationFactory.getElementIfPresent(By.xpath("//button[@id='preference-cancel']")).click();
				LogFactory.info("User current prefered  language is....." + BaseClass.strUserPrefLang);

			} else {

				BaseClass.strUserPrefLang = "default";
				LogFactory.info(
						"User do not have the option to change the  prefered language, so site is refereing to the default laguage as per the site Region.....");
				ValidationFactory.getElementIfPresent(By.xpath("//button[@id='preference-cancel']")).click();
			}

		} catch (Exception e) {
			e.getMessage();
			LogFactory.info("Unable to click on switch site  utility link or Element not found");
			// Assert.fail("Unable to click on switch site utility link or Element not
			// found");

		}

	}

	/**
	 * Method to get the list of Dates by header name for Alert and announcement
	 * 
	 * @author shrey.choudhary
	 * @param header
	 * @createdAt 26-05-2018
	 * @return
	 */
	/*
	 * public static List<String> getListOfDatesByHeaderName(String header) {
	 * 
	 * 
	 * 
	 * String headerAlert = Alerts_POF.wbelAlertFramePath.findElement(By.xpath(
	 * "//div[@class='section-header']/h3")).getText(); String headerAnnouncement =
	 * Announcements_POF.wbelAnnouncementFramePath.findElement(By.xpath(
	 * "//div[@class='section-header']/h3")) .getText(); List<String> dateList = new
	 * ArrayList<>(); if (headerAlert.equalsIgnoreCase(header)) { List<WebElement>
	 * elementsList = Alerts_POF.wbelAlertFramePath.findElements(By.xpath(
	 * ".//*[@class='list-item-title']")); for (int i = 0; i < elementsList.size();
	 * i++) { String temp = elementsList.get(i).getText();
	 * dateList.add(GenericFactory.splitString(temp, ":").get(0)); } return
	 * dateList; } else if (headerAnnouncement.equalsIgnoreCase(header)) {
	 * List<WebElement> elementsList = Announcements_POF.wbelAnnouncementFramePath
	 * .findElements(By.xpath(".//*[@class='list-item-title']")); for (int i = 0; i
	 * < elementsList.size(); i++) { String temp = elementsList.get(i).getText();
	 * dateList.add(GenericFactory.splitString(temp, ":").get(0)); } return
	 * dateList; } else {
	 * LogFactory.info("Unable to perform the sort order on dates."); } return null;
	 * }
	 * 
	 */
	/**
	 * Method to verify the format of dates on the Alert & Announcement portlet
	 * 
	 * @author shrey.choudhary
	 * @param header
	 * @createdAt 24-05-2018
	 * @return
	 */
	public static boolean isValidDateFormat(String dateformat, List<Date> listOfDates) {
		boolean isValidFormat = false;
		for (int i = 0; i < listOfDates.size(); i++) {
			Date date = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
				date = sdf.parse(listOfDates.get(i).toString());
				if (!listOfDates.get(i).equals(sdf.format(date))) {
					date = null;
				}
			} catch (ParseException ex) {
				ex.printStackTrace();

			}
			if (date == null) {
				isValidFormat = false;
				break;
			} else {
				isValidFormat = true;
			}
		}
		return isValidFormat;
	}

	/**
	 * This Method verifies the order of dates on the Alert & Announcement portlet
	 * 
	 * @author shrey.choudhary
	 * @param header
	 * @createdAt 27-05-201
	 * @return
	 */
	public static boolean verifyDateSortedOrder(List<Date> listOfDatesInFrame) {
		List<Date> ExpectedlistOfDates = new ArrayList<>();
		ExpectedlistOfDates.addAll(listOfDatesInFrame);
		Collections.sort(ExpectedlistOfDates, Collections.reverseOrder());
		System.out.println("Actual list is : ");
		listOfDatesInFrame.forEach(System.out::println);
		System.out.println("sorted list is : ");
		ExpectedlistOfDates.forEach(System.out::println);
		if (listOfDatesInFrame.equals(ExpectedlistOfDates)) {
			return true;
		}
		return false;
	}

	/**
	 * This method to find the list of webElements from a frame by xpath
	 * 
	 * @author shrey.choudhary
	 * @param WebElement
	 * @createdAt 27-05-2018
	 * @return List<WebElement>
	 */

	public static List<WebElement> getLinksFromFrame(WebElement framePath) {
		List<WebElement> getWebElementsLinks = framePath.findElements(By.tagName("a"));
		List<WebElement> listOfElements = new ArrayList<WebElement>();
		for (int i = 0; i < getWebElementsLinks.size(); i++) {
			listOfElements.add(getWebElementsLinks.get(i));
		}
		return listOfElements;
	}

	public static void createHeaderSection(String header) throws InterruptedException {

		ReportFactory.addRoottable();
		ReportFactory.reportSectionName(header);
		ReportFactory.reporterOutputHeader();

	}

	/*
	 * public static void navigateToHomePage() throws InterruptedException {
	 * 
	 * WebElement userelement = null; userelement =
	 * ValidationFactory.getElementIfPresent(By.xpath(".//*[@class='app-title']"));
	 * 
	 * if (userelement != null) {
	 * LogFactory.info("Navigating to the DealerPath Homepage");
	 * userelement.click(); }
	 * 
	 * }
	 * 
	 */

	public static void navigateToHomePage() throws InterruptedException {
		WebElement userelement = null;
		if (ValidationFactory.getElementIfPresent(By.xpath("//input[@class='filter-box fav-filter']")) != null) {
			LogFactory.info("User is on homepage");
		} else {
			userelement = ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='main-header']/div[1]/div/h1"));
			if (userelement != null) {
				LogFactory.info("Navigating to the DealerPath Homepage");
				WaitFactory.waitforelementToBeClickable(userelement).click();
				WaitFactory.waitForPageLoaded();
			}
		}
	}

	public static List<String> getCheckBoxValues() {
		// List<WebElement> getWebElementsLinks =
		// BaseClass.wbDv.findElements(By.xpath(".//*[@class='checkbox-value']/div/label/div"));

		List<WebElement> getWebElementsLinks = BaseClass.wbDriver
				.findElements(By.xpath("//form[@id='productSegmentsForm']/div/div[@class='value']//div"));
		// System.out.println(getWebElementsLinks.size());
		// System.out.println(getWebElementsLinks);
		List<String> listOfElements = new ArrayList<String>();
		for (int i = 0; i < getWebElementsLinks.size(); i++) {

			// System.out.println(">>>>>>>>>>>>>>"+getWebElementsLinks.get(i).getText());
			listOfElements.add(getWebElementsLinks.get(i).getText());

		}
		// System.out.println(listOfElements);

		return listOfElements;
	}

	/**
	 * This method is to capture the screenshot and save them in configured
	 * directory, i.e confi.property or user.dir
	 * 
	 * @author shrey.choudhary
	 * @createdAt 24-05-2018
	 * @param TCID
	 * @throws Exception
	 * s
	 * @ModifiedBy
	 * @ModifiedAt
	 */
	public static String getScreenshot(String TCID) throws Exception {
        String filePath = "";

        if (BaseClass.ENABLE_SCREENSHOT.equalsIgnoreCase("ON")) {
             BaseClass.strScreenshotDir = FileFolderFactory.createDirectory(BaseClass.strScreenshotDir,
                        "\\Screenshot\\");

             DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
             Date date = new Date();

             File scrFile = ((TakesScreenshot) BaseClass.wbDriver).getScreenshotAs(OutputType.FILE);
             try {
                  // now copy the screenshot to desired location using copyFile //method
                  filePath = BaseClass.strScreenshotDir +"\\Screenshot\\"+ TCID + "_" + dateFormat.format(date) + ".png";
                  FileHandler.copy(scrFile, new File(filePath));
             } catch (IOException e) {
             }
        }
        return filePath;
  }


	/*
	 * public static String productlistcomparer(String userDefinedProducts, String
	 * wcmProducts) {
	 * 
	 * String flag = "Fail"; try {
	 * 
	 * String str1 =
	 * "Agriculture/Ag Equipment,Agriculture/Sprayers & Nutrient Applicators,Construction/Construction,Forestry/Forestry,Hitachi/Mining,Hitachi/Hitachi"
	 * ;
	 * 
	 * String str2 = "Agriculture/Ag Equipment,Construction,Hitachi";
	 * 
	 * 
	 * List<String> userDefinedProductsList =
	 * Arrays.asList(userDefinedProducts.split("\\s*,\\s*")); List<String>
	 * wcmProductsList = Arrays.asList(wcmProducts.split("\\s*,\\s*"));
	 * 
	 * 
	 * for (int i = 0; i < wcmProductsList.size(); i++) {
	 * 
	 * String wcmProductsvalue = wcmProductsList.get(i);
	 * 
	 * for(int k=0;k<userDefinedProductsList.size();k++) {
	 * 
	 * String userDefinedProductsvalue = userDefinedProductsList.get(k);
	 * 
	 * if(userDefinedProductsvalue.contains(wcmProductsvalue)) { flag = "Pass";
	 * System.out.println(wcmProductsList.get(i) + " Is Same as " +
	 * userDefinedProductsList.get(k));
	 * LogFactory.info("Product details matched Successfully."); break;
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * for (int i = 0; i < userDefinedProductsList.size(); i++) {
	 * 
	 * String userDefinedProductsvalue = userDefinedProductsList.get(i);
	 * 
	 * for (int j = 0; j < wcmProductsList.size(); j++) {
	 * 
	 * String wcmProductsvalue = wcmProductsList.get(j);
	 * 
	 * if (wcmProductsvalue.contains(userDefinedProductsvalue.toString()) ||
	 * userDefinedProductsvalue.contains(wcmProductsvalue.toString())) { flag =
	 * "Pass"; System.out.println (wcmProductsvalue + " Is Same as " +
	 * userDefinedProductsvalue);
	 * LogFactory.info("Product details matched Successfully."); break;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } return flag;
	 * 
	 * }
	 * 
	 * catch (Exception e) { flag = "Fail"; LogFactory.info("Fail"); //
	 * Assert.fail("Fail");
	 * 
	 * } return flag;
	 * 
	 * }
	 */

	/*
	 * public static boolean productlistcomparer(String userDefinedProducts, String
	 * wcmProducts) {
	 * 
	 * boolean flag = false; try {
	 * 
	 * String str1 =
	 * "Agriculture/Ag Equipment,Agriculture/Sprayers & Nutrient Applicators,Construction/Construction,Forestry/Forestry,Hitachi/Mining,Hitachi/Hitachi"
	 * ;
	 * 
	 * String str2 = "Agriculture/Ag Equipment,Construction,Hitachi";
	 * 
	 * 
	 * LogFactory.
	 * info("Verifying whether user products are matching with WCM product types");
	 * 
	 * List<String> userDefinedProductsList =
	 * Arrays.asList(userDefinedProducts.split("\\s*,\\s*")); List<String>
	 * wcmProductsList = Arrays.asList(wcmProducts.split("\\s*,\\s*"));
	 * 
	 * 
	 * for (int i = 0; i < wcmProductsList.size(); i++) {
	 * 
	 * String wcmProductsvalue = wcmProductsList.get(i);
	 * 
	 * for(int k=0;k<userDefinedProductsList.size();k++) {
	 * 
	 * String userDefinedProductsvalue = userDefinedProductsList.get(k);
	 * 
	 * if(userDefinedProductsvalue.contains(wcmProductsvalue)) { flag = "Pass";
	 * System.out.println(wcmProductsList.get(i) + " Is Same as " +
	 * userDefinedProductsList.get(k));
	 * LogFactory.info("Product details matched Successfully."); break;
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * for (int i = 0; i < userDefinedProductsList.size(); i++) {
	 * 
	 * String userDefinedProductsvalue = userDefinedProductsList.get(i);
	 * 
	 * for (int j = 0; j < wcmProductsList.size(); j++) {
	 * 
	 * String wcmProductsvalue = wcmProductsList.get(j);
	 * 
	 * if (wcmProductsvalue.contains(userDefinedProductsvalue.toString()) ||
	 * userDefinedProductsvalue.contains(wcmProductsvalue.toString())) { flag =
	 * true; // System.out.println(wcmProductsvalue + " Is Same as " + //
	 * userDefinedProductsvalue);
	 * 
	 * break;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * String isCountryMatched = flag ? "Matched" : "Not Matched";
	 * LogFactory.info(" User products are " + isCountryMatched +
	 * " with WCM product type");
	 * 
	 * return flag;
	 * 
	 * }
	 * 
	 * catch (Exception e) { flag = false;
	 * 
	 * String isCountryMatched = flag ? "Matched" : "Not Matched";
	 * LogFactory.info(" User country is " + isCountryMatched +
	 * " with WCM country");
	 * 
	 * return flag;
	 * 
	 * }
	 * 
	 * }
	 */
	public static WebElement getDeptname(String deptname) {

		// ********* List for active links *****************************
		List<WebElement> deptListActiveLinks = new ArrayList<WebElement>();
		WebElement strdepnameinactive = null;
		WebElement strdepnameactive = null;

		for (int i = 0; i < PortalLeftNavigation_POF.ListAllActiveLinks.size(); i++) {
			strdepnameactive = PortalLeftNavigation_POF.ListAllActiveLinks.get(i);
			deptListActiveLinks.add(strdepnameactive);
			// System.out.println(deptListActiveLinks.get(i).getText());
		}

		// ********** List for inactive links ******************************
		List<WebElement> deptListInactiveLinks = new ArrayList<WebElement>();
		for (int i = 0; i < PortalLeftNavigation_POF.listAllInactiveLinks.size(); i++) {
			strdepnameinactive = PortalLeftNavigation_POF.listAllInactiveLinks.get(i);
			deptListInactiveLinks.add(strdepnameinactive);
			// System.out.println(strdepnameinactive.getText());
		}

		// *************** compare excel data with active and inactive
		// links*****************

		boolean flag1 = false;
		boolean flag2 = false;

		LogFactory.info(" Vearifying the department " + deptname + " is active or Not...");

		int j = 0;
		for (; j < deptListActiveLinks.size(); j++) {
			// System.out.println(deptList.get(j).getText() + "::" + testdata);

			if (deptListActiveLinks.get(j).getText().equals(deptname)) {
				flag1 = true;
				break;
			}

		}

		if (flag1 != true) {
			for (int k = 0; k < deptListInactiveLinks.size(); k++) {
				// System.out.println(deptListInactiveLinks.size());
				if (deptListInactiveLinks.get(k).getText().equals(deptname)) {
					flag2 = true;
					break;
				}

			}
		}

		if (flag1 == true && flag2 == false) {
			// LogFactory.info(" Department "+ deptname + " is active ...");
			return deptListActiveLinks.get(j);
		} else if (flag2 == true && flag1 == false) {
			// LogFactory.info(" Department "+ deptname + " is not active ...");
			return null;
		} else {
			// LogFactory.info(" Department "+ deptname + " is not active ...");
			return null;
		}
	}

	/*
	 * public static void toClickonDept(String deptName) { try{
	 * 
	 * // To clik on 'My DealerPath' arrow WebElement arrowClick =
	 * BaseClass.wbDriver.findElement(By.xpath(".//*[@id='left_nav_0']"));
	 * 
	 * 
	 * //Mouse hover Actions Actions obj = new Actions(BaseClass.wbDriver);
	 * obj.moveToElement(arrowClick).build().perform();
	 * 
	 * 
	 * 
	 * //To get list of all departments List<WebElement> deptNameList =
	 * BaseClass.wbDriver.findElements(By.xpath(".//*[@class='flyout']/li/a"));
	 * for(int i=1; i< deptNameList.size(); i++) { String deptNameListValue =
	 * deptNameList.get(i).getText().toString().trim(); //
	 * System.out.println(deptNameListValue);
	 * LogFactory.info("dept Name List Values= " +deptNameListValue);
	 * 
	 * //To click on desire department name given
	 * if(deptNameListValue.contains(deptName.trim())) {
	 * 
	 * deptNameList.get(i).click();
	 * LogFactory.info("Clicked on desired deparment name. " +deptName); break; } }
	 * } catch(Exception e) { LogFactory.info("Click on the department. "+e); } }
	 */

	public static boolean clickOnFlyOutDeptMenu(String UserDeptName) {
		try {

			String SelectedLeftTab = BaseClass.wbDriver
					.findElement(By.xpath("//*[@class='active  selected leftNavDeptPadding']")).getText();

			if (!UserDeptName.equals(SelectedLeftTab)) {
				Actions builder = new Actions(BaseClass.wbDriver);
				WebElement arrowClick = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='left_nav_0']"));
				builder.moveToElement(arrowClick).build().perform();

				List<WebElement> actualDeptNameList = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='flyout']/li/a[@class='active']"));
				for (int i = 1; i < actualDeptNameList.size(); i++) {
					String deptNameListValue = actualDeptNameList.get(i).getText().toString().trim();
					if (deptNameListValue.contains(UserDeptName.trim())) {

						actualDeptNameList.get(i).click();
						LogFactory.info(
								" Clicking on the active department " + UserDeptName + " from Portlet links page");
						return true;
					}
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			LogFactory.info("Unable to click on department" + e);
			return false;
		}
		return false;
	}

	/*
	 * public static boolean countryListComparison(String UserCountry, String
	 * wcmCountry) {
	 * 
	 * boolean flag = false;
	 * 
	 * List<String> wcmCountryList = Arrays.asList(wcmCountry.split("(,)")); //
	 * System.out.println(wcmCountryList);
	 * 
	 * List<String> userCountryList = Arrays.asList(UserCountry.split("(,)")); //
	 * System.out.println(userCountryList);
	 * 
	 * try {
	 * 
	 * LogFactory.info("Verifying user country is matching with WCM country");
	 * 
	 * for (int i = 0; i < wcmCountryList.size(); i++) { for (int j = 0; j <
	 * userCountryList.size(); j++) {
	 * 
	 * //
	 * System.out.println(wcmCountryList.get(j).concat("/")+"::"+userCountryList.get
	 * (i).split("/").trim()); if (!wcmCountryList.get(j).contains("/")) { String
	 * arr[] = userCountryList.get(j).split("/"); if
	 * (wcmCountryList.get(i).contains(arr[0])) { flag = true; //
	 * LogFactory.info("User country contains MRU country");
	 * 
	 * } else {
	 * 
	 * // LogFactory.info("User country doesnot contain WCM country");
	 * 
	 * } } else if (!userCountryList.get(j).contains("/")) { if
	 * (wcmCountryList.get(i).contains("/")) { flag = false; //
	 * LogFactory.info("User country doesnot contains MRU country"); } }
	 * 
	 * else { // System.out.println(wcmCountryList.get(i) + "::" +
	 * userCountryList.get(j)); if
	 * (wcmCountryList.get(i).contains(userCountryList.get(j).trim())) { flag =
	 * true; // LogFactory.info("User country contains WCM country");
	 * 
	 * } else {
	 * 
	 * // LogFactory.info("User country doesnot contain WCM country");
	 * 
	 * }
	 * 
	 * } } } } catch (Exception e) { LogFactory.info("Exception message :-->" +
	 * e.getMessage()); flag = false; }
	 * 
	 * String isCountryMatched = flag ? "Matched" : "Not Matched";
	 * LogFactory.info(" User country is " + isCountryMatched +
	 * " with WCM country");
	 * 
	 * return flag; }
	 */

	/**
	 * Method to get the list of Dates by header name for Alert and announcement
	 * Author: shrey.choudhary
	 * 
	 * @param header
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> getListOfDatesByHeaderName(String header) throws ParseException {

		Date date1;

		String headerAlert = Alerts_POF.wbelAlertFramePath.findElement(By.xpath("//div[@class='section-header']/h3"))
				.getText();

		String headerAnnouncement = Announcements_POF.wbelAnnouncementFramePath
				.findElement(By.xpath("//div[@class='section-header']/h3")).getText();
		List<Date> dateList = new ArrayList<>();
		if (headerAlert.equalsIgnoreCase(header)) {
			List<WebElement> elementsList = Alerts_POF.wbelAlertFramePath
					.findElements(By.xpath(".//*[@class='list-item-title']"));
			for (int i = 0; i < elementsList.size(); i++) {
				String temp = elementsList.get(i).getText();
				// dateList.add(GenericFactory.splitString(temp, ":").get(0));
				dateList.add(
						date1 = new SimpleDateFormat(dateformat).parse(GenericFactory.splitString(temp, ":").get(0)));

			}
			return dateList;
		} else if (headerAnnouncement.equalsIgnoreCase(header)) {
			List<WebElement> elementsList = Announcements_POF.wbelAnnouncementFramePath
					.findElements(By.xpath(".//*[@class='list-item-title']"));
			for (int i = 0; i < elementsList.size(); i++) {
				String temp = elementsList.get(i).getText();
				// dateList.add(GenericFactory.splitString(temp, ":").get(0));
				dateList.add(
						date1 = new SimpleDateFormat(dateformat).parse(GenericFactory.splitString(temp, ":").get(0)));
			}
			return dateList;
		} else {
			LogFactory.info("Unable to perform the sort order on dates.");
		}
		return null;
	}

	public static List<Date> getListOfDatesByFrame(WebElement alertannouncement, String portletName)
			throws ParseException {

		/*
		 * String headerAlert = Alerts_POF.wbelAlertFramePath.findElement(By.xpath(
		 * "//div[@class='section-header']/h3")).getText(); String headerAnnouncement =
		 * Announcements_POF.wbelAnnouncementFramePath.findElement(By.xpath(
		 * "//div[@class='section-header']/h3")).getText();
		 * 
		 */
		Date date1;

		List<Date> dateList = new ArrayList<>();

		if ((ValidationFactory.isElementPresent(alertannouncement)) && (portletName == "Alert")) {

			List<WebElement> elementsList = Alerts_POF.wbelAlertFramePath
					.findElements(By.xpath(".//*[@class='list-item-title']"));
			for (int i = 0; i < elementsList.size(); i++) {
				String temp = elementsList.get(i).getText();
				String dt = GenericFactory.splitString(temp, ":").get(0);
				date1 = new SimpleDateFormat(dateformat).parse(dt);
				dateList.add(date1);
			}
			return dateList;
		} else {
			List<WebElement> elementsList = Announcements_POF.wbelAnnouncementFramePath
					.findElements(By.xpath(".//*[@class='list-item-title']"));
			for (int i = 0; i < elementsList.size(); i++) {
				String temp = elementsList.get(i).getText();
				String dt = GenericFactory.splitString(temp, ":").get(0);
				date1 = new SimpleDateFormat(dateformat).parse(dt);
				dateList.add(date1);
			}
			return dateList;

		}

	}

	public static void setUserPreferredLang() throws InterruptedException {

		String strExcelValue = "English";

		if (!strExcelValue.isEmpty()) {

			utilityMenuMyPreferenceClick();

			Thread.sleep(1000);

			WebElement element = ValidationFactory.getElementIfPresent(By.xpath("//select[@id='lang']"));
			if (element != null) {
				Select preferedLang = new Select(element);
				BaseClass.strUserPrefLang = preferedLang.getFirstSelectedOption().getText();

				if (!BaseClass.strUserPrefLang.equals(strExcelValue)) {

					preferedLang.selectByVisibleText(strExcelValue);
					ValidationFactory.getElementIfPresent(By.xpath("//button[@id='preference-save']")).click();

					LogFactory.info("Language from excel sheet " + strExcelValue + " is selected");
				} else {

					LogFactory.info("Excel value and selected value on My Preferences is same");

					ValidationFactory.getElementIfPresent(By.xpath("//button[@id='preference-cancel']")).click();
				}
			} else {
				LogFactory.info(
						"User do not have the option to change the  prefered language, so site is refereing to the default laguage as per the site Region.....");
				ValidationFactory.getElementIfPresent(By.xpath("//button[@id='preference-cancel']")).click();
			}
		} else {
			LogFactory.info("Current language on My Preferences is English");

		}
	}

	public static boolean userAndWCMCountryComparison(String UserCountry, String wcmCountry) {
		boolean flag = false;
		List<String> wcmCountryList = Arrays.asList(wcmCountry.split("(,)"));
		
		try {
			for (int i = 0; i < wcmCountryList.size(); i++) {
				if (!wcmCountryList.get(i).contains("/")) {
					String arr[] = UserCountry.split("/");
					if (wcmCountryList.get(i).contains(arr[0].toString().trim())) {
						flag = true;
					}
				} else if (!UserCountry.contains("/")) {
					if (wcmCountryList.get(i).contains(UserCountry.toString().trim())) {
						flag = true;
					}
				} else if (UserCountry.contains("/")) {
					int Usercounter = 0;
					for (int j = 0; j < UserCountry.length(); j++) {
						if (UserCountry.charAt(j) == '/') {
							Usercounter++;
						}
					}
					int WCMcounter = 0;
					for (int j = 0; j < wcmCountryList.get(i).length(); j++) {
						if (wcmCountryList.get(i).charAt(j) == '/') {
							WCMcounter++;
						}
					}
					if (Usercounter == 1 && WCMcounter == 1) {
						String arr[] = UserCountry.split("/");
						if (wcmCountryList.get(i).contains(arr[1].toString().trim())) {
							flag = true;
						}
					} else if (Usercounter == 1 && WCMcounter == 2) {
						String arr[] = UserCountry.split("/");
						if (wcmCountryList.get(i).contains(arr[1].toString().trim())) {
							flag = true;
						}
					} else if (Usercounter == 2 && WCMcounter == 2) {
						String arr[] = UserCountry.split("/");
						if ((wcmCountryList.get(i).contains(arr[0].toString().trim()))
								&& (wcmCountryList.get(i).contains(arr[1].toString().trim()))
								&& (wcmCountryList.get(i).contains(arr[2].toString().trim()))) {
							flag = true;
						}
					} else if (Usercounter == 2 && WCMcounter == 1) {
						String arr[] = UserCountry.split("/");
						if (wcmCountryList.get(i).contains(arr[1].toString().trim())) {
							flag = true;
						}
					}
				} else {
					if (wcmCountryList.get(i).contains(UserCountry.trim().toString().trim())) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			LogFactory.info("Exception message :-->" + e.getMessage());
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings("null")
	public static List<String> strUserProductToMatchWithWCMList(String parentProductUser) {
		List<String> childProductAfterSplit = new ArrayList<>();
		// for (int i = 0; i < parentProductUser.size(); i++) {
		if (parentProductUser.contains("/")) {
			childProductAfterSplit.add(GenericFactory.splitString(parentProductUser, "/").get(0));
			childProductAfterSplit.add(GenericFactory.splitString(parentProductUser, "/").get(1));
		} else {
			childProductAfterSplit.add(parentProductUser);
			childProductAfterSplit.add("null");

		}
		return childProductAfterSplit;
	}

	public static boolean verifyIfProductMatch(List<String> parentProductWCM, List<String> childProductAfterSplit) {
		for (int i = 0; i < parentProductWCM.size(); i++) {
			if (parentProductWCM.get(i).contains("/")) {
				String parentProductAfterSplitWCM = GenericFactory.splitString(parentProductWCM.get(i), "/").get(0);
				String childProductAfterSplitWCM = GenericFactory.splitString(parentProductWCM.get(i), "/").get(1);
				if ((childProductAfterSplit.get(0).equalsIgnoreCase(parentProductAfterSplitWCM))
						&& ((childProductAfterSplitWCM.equalsIgnoreCase(childProductAfterSplit.get(1)))
								|| ((childProductAfterSplit.get(1).equals("null"))))) {

					return true;
				}

			} else if (parentProductWCM.get(i).equalsIgnoreCase(childProductAfterSplit.get(0))) {
				return true;
			}
		}
		return false;
	}

	public static boolean userAndWCMProductTypeComparison(String UserProduct, String wcmProduct) {
		List<String> listOfParentProductUser = GenericFactory.splitString(UserProduct, ",");
		List<String> listOfWcmProduct = GenericFactory.splitString(wcmProduct, ",");
		for (int i = 0; i < listOfParentProductUser.size(); i++) {
			List<String> ParentProductUser = strUserProductToMatchWithWCMList(listOfParentProductUser.get(i));
			if (verifyIfProductMatch(listOfWcmProduct, ParentProductUser)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is to parent product from the given product segments
	 * 
	 * @author Neeraja.mantri
	 * @createdAt 22-06-2018
	 * @param product
	 * @return
	 * @throws Throwable
	 * @ModifiedBy
	 * @ModifiedAt
	 */

	public static String getParentProductSegmnent(String product) {
		String productName = product;
		if (product.contains("/")) {
			List<String> temp = splitString(product, "/");
			productName = temp.get(1);
		}

		if (productName.matches("(?i)Construction|Utility")) {
			return "Construction";
		} else if (productName.matches("(?i)Forestry")) {
			return "Forestry";

		} else if (productName.matches("(?i)Homeowner")) {
			return "Homeowner";

		} else if (productName.matches("(?i)CWP")) {
			return "CWP";
		} else if (productName.matches("(?i)Hitachi|Mining")) {
			return "Hitachi";
		} else if (productName.matches(
				"(?i)Agriculture|Ag Equipment|Sprayers & Nutrient Applicators|Scraper and Scraper Tractor|Forage Harvester"))
			return "Agriculture";
		else if (productName.matches("(?i)Turf|Commercial|Residential")) {
			return "Turf";
		} else if (productName.matches("(?i)Golf")) {
			return "Golf";
		}
		return null;
	}

	/**
	 * This method is to parent product from the given product segments
	 * 
	 * @author Neeraja.mantri
	 * @createdAt 22-06-2018
	 * @param product
	 * @return
	 * @throws Throwable
	 * @ModifiedBy
	 * @ModifiedAt
	 */

	/**
	 * This method is to check the alerts/announcements title with format
	 * 
	 * @author Neeraja.mantri
	 * @createdAt 21-06-2018
	 * @param by
	 * @return
	 * @throws Throwable
	 * @ModifiedBy
	 * @ModifiedAt
	 */

	public static boolean headerTitleFormat(List<WebElement> title) throws Throwable {

		try {

			boolean dateflag = false;
			boolean titleflag = false;

			// List<WebElement> title = wbDriver.findElements(by);
			List<String> headertitles = new ArrayList<String>();

			for (int i = 0; i < title.size(); i++) {

				headertitles.add(title.get(i).getText());
				String strText = title.get(i).getText();

				if (title.get(i).getText().contains(":")) {

					String rowValues[] = title.get(i).getText().split(":");
					LogFactory.info("Date :" + rowValues[0].trim());
					String dateToValidate = rowValues[0].trim();

					if (dateToValidate != null) {

						if (DateFactory.isThisDateValid(dateToValidate, BaseClass.dateformat)) {
							dateflag = true;
							LogFactory.info("Date format is valid");

						} else {
							dateflag = false;
							LogFactory.info("Date format is not valid");
							break;
						}
					}

					if (rowValues[1].length() > 0) {
						LogFactory.info("-Title---------" + rowValues[1].trim());
						titleflag = true;
					} else {
						titleflag = false;
						break;
					}

					// LogFactory.info("Content is in required format");
				} else {

					dateflag = false;
					break;
				}

			}
			return dateflag && titleflag;
		} catch (Exception e) {
			LogFactory.info(e.getMessage());
			return false;
		}

	}

	/*
	 * public static boolean headerTitleFormat(By by) throws Throwable {
	 * 
	 * boolean flag = false;
	 * 
	 * List<WebElement> title = BaseClass.wbDriver.findElements(by);
	 * 
	 * List<String> headertitles = new ArrayList<String>();
	 * 
	 * for (int i = 0; i < title.size(); i++) {
	 * 
	 * // System.out.println("Alerts title list" + //
	 * alertsTitle.get(i).getText().toString());
	 * headertitles.add(title.get(i).getText());
	 * 
	 * String strText = title.get(i).getText();
	 * 
	 * if (title.get(i).getText().contains(":")) {
	 * 
	 * String rowValues[] = title.get(i).getText().split(":"); //
	 * System.out.println(alertsTitle.size()+">>>>>>>>>>>>>>"+rowValues[0].trim());
	 * LogFactory.info("Dates :" + rowValues[0].trim()); String dateToValidate =
	 * rowValues[0].trim(); flag = true; if (dateToValidate != null) { boolean
	 * validDate = DateFactory.isThisDateValid(dateToValidate,
	 * BaseClass.dateformat); flag = true; LogFactory.info("validDate=" +
	 * validDate); } else {
	 * 
	 * LogFactory.info("Date format is not valid"); }
	 * 
	 * if (rowValues[1].length() > 0) { LogFactory.info("-Titles---------" +
	 * rowValues[1].trim()); flag = true; }
	 * 
	 * // LogFactory.info("Content is in required format"); } else {
	 * 
	 * LogFactory.info("Content is not in the required format"); } }
	 * 
	 * return flag;
	 * 
	 * }
	 */

	/**
	 * This method is to get active second level department link
	 * 
	 * @author Neeraja.mantri
	 * @createdAt 28-06-2018
	 * @param strTestdata
	 * @return WebElement
	 * @ModifiedBy
	 * @ModifiedAt
	 */

	public static WebElement getActive2ndLevelDepartment(String strTestdata) {
		List<WebElement> secondLevelActiveDepartment = new ArrayList<WebElement>();
		int i = 0;
		for (; i < alertPageFactory.SecondLevelDepartment.size(); i++) {
			WebElement strdepartmentnameactive = alertPageFactory.SecondLevelDepartment.get(i);
			secondLevelActiveDepartment.add(strdepartmentnameactive);

			if (alertPageFactory.SecondLevelDepartment.get(i).getText().equals(strTestdata)) {
				LogFactory.info("Second level department matching is :"
						+ alertPageFactory.SecondLevelDepartment.get(i).getText());
				break;
			} else {

				LogFactory.info("Second level department is not matching :"
						+ alertPageFactory.SecondLevelDepartment.get(i).getText());

			}
		}

		return alertPageFactory.SecondLevelDepartment.get(i);

	}

	/**
	 * This method will click on a particular index page after verifying the
	 * department matched
	 * 
	 * @author Archana.gaikwad
	 * @createdAt 02-07-2018
	 * @param strSecondlevelDepartment
	 * @param strIndexPage
	 * @ModifiedBy Neeraja.mantri
	 */

	public static boolean clickOnIndexPageLinkOnSecondLevelDepartment(String strSecondlevelDepartment,
			String strIndexPage) {

		List<WebElement> liActualSubDepartmentsFrame = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='links-target']/div"));
		String strHeaderNameFlag = "Fail";
		String strResult = "Fail";
		boolean returnFlag = false;

		for (int j = 0; j < liActualSubDepartmentsFrame.size(); j++) {
			String strSubDepartment = liActualSubDepartmentsFrame.get(j).getText().trim();
			String[] lines = strSubDepartment.split("\n");
			String strheadername = lines[0];
			// System.out.println(strheadername);
			WebElement weSecondLevelLinks = liActualSubDepartmentsFrame.get(j);
			// System.out.println(liActualSubDepartmentsFrame.get(j).getText());

			if (strSecondlevelDepartment.equalsIgnoreCase(strheadername)) {
				strHeaderNameFlag = "Pass";

				List<WebElement> links = weSecondLevelLinks.findElements(By.tagName("a"));

				for (WebElement we : links) {
					if (we.getText().equalsIgnoreCase(strIndexPage)) {
						LogFactory.info("Index page link is present in the subdepartment :" + strSecondlevelDepartment);
						WebElement weThirdLevelIndexLink = BaseClass.wbDriver.findElement(By.linkText(strIndexPage));
						weThirdLevelIndexLink.click();
						returnFlag = true;
						break;
					} else {

						LogFactory.info(
								"Index Page link is not present in the subdepartment :" + strSecondlevelDepartment);

					}

				}
				break;
			} else {

				LogFactory.info("Secondlevel department name is not matching with expected");
			}

			break;
		}

		return returnFlag;

	}

	public boolean uncheckProductList(String wcmProductList) throws Throwable {

		boolean flag = true;
		BaseClass.wbDriver.findElement(By.xpath("//div[@id='js-segments']")).click();

		List<WebElement> productType = BaseClass.wbDriver
				.findElements(By.xpath("//div[@id='js-segments-popover']//div[@class='value']"));

		List<String> excel = new ArrayList<String>();
		excel.add("Agriculture");
		excel.add("CWP");
		excel.add("Construction");
		excel.add("Forestry");
		excel.add("Hitachi");
		excel.add("Turf");

		List<WebElement> checkBox = BaseClass.wbDriver.findElements(
				By.xpath("//div[@id='js-segments-popover']//div[@class='value']//input[@type='checkbox']"));

		for (int i = 0; i < checkBox.size(); i++) {
			if (!checkBox.get(i).isSelected()) {
				System.out.println("*********unchecked**********");
				checkBox.get(i).click();
			}
		}

		for (int j = 0; j < excel.size(); j++) {
			for (int i = 0; i < productType.size(); i++) {
				if (excel.get(j).equalsIgnoreCase(productType.get(i).getText())) {
					checkBox.get(i).click();
				}
			}
		}

		BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-segments-popover']/div[2]/div[3]/button")).click();

		String errorMessage = BaseClass.wbDriver
				.findElement(By.xpath("//div[@class='popover-content']//p[@id='productSegmentsError']")).getText();

		System.out.println("errorMessage===" + errorMessage);
		if (errorMessage != null) {
			flag = false;
			System.out.println("flag" + flag);

		}
		return flag;
	}

	public static boolean verifyDealerType(String wcmDealerType) {
		if (flagDealerType.equalsIgnoreCase(wcmDealerType)) {
			return true;
		} else if (flagDealerType.contains("/") || wcmDealerType.contains("/")
				|| wcmDealerType.equalsIgnoreCase("NA")) {
			return true;
		}
		return false;
	}

	/*
	 * public static boolean clickOnDepartmentByName(String DepartmentName) { try {
	 * 
	 * for (int i = 0; i < PortalLeftNavigation_POF.ListAllActiveLinks.size(); i++)
	 * { if (PortalLeftNavigation_POF.ListAllActiveLinks.get(i).getText().
	 * equalsIgnoreCase(DepartmentName)) {
	 * PortalLeftNavigation_POF.ListAllActiveLinks.get(i).click(); return true; } }
	 * System.out.println("Given department name : " + DepartmentName +
	 * " is not available/disabled in Left navigation window"); } catch (Exception
	 * e) { LogFactory .info("Given department name : " + DepartmentName +
	 * " is not available in Left navigation window"); } return false; }
	 * 
	 * public static boolean clickOnDepartment(String departmentName) {
	 * System.out.println("test"); if
	 * (ValidationFactory.isElementPresent(favouritesFactory.wbelFavouriteFrame)) {
	 * LogFactory.info("User is on homepage"); if
	 * (clickOnDepartmentByName(departmentName)) {
	 * LogFactory.info("Successfully clicked on Department :" + departmentName +
	 * " from homepage through left navigation window"); return true; } else {
	 * LogFactory .info("Department :" + departmentName +
	 * " is inactive or not found in left navigation window"); return false; } }
	 * else { if (GenericFactory.clickOnFlyOutDeptMenu(departmentName)) {
	 * LogFactory.info("Clicked on Department :" + departmentName +
	 * " from flyout menu"); return true; } } LogFactory.info("Department :" +
	 * departmentName + "is inactive/not present"); return false;
	 * 
	 * }
	 */

	public static void uncheckDepartmentMyPreference(List<String> departmentName) throws InterruptedException {
		String temp = String.join(",", departmentName);
		try {
			List<String> translatedDeptName = GenericFactory.getTranslation(temp);
			GenericFactory.utilityMenuMyPreferenceClick();
			Thread.sleep(5000);
			// WaitFactory.WaitForElementToVisible(element);
			for (int i = 0; i < announcementFactory.allCheck.size(); i++) {
				if (!(announcementFactory.allCheck.get(i).getAttribute("checked") == null)
						&& translatedDeptName.contains(announcementFactory.allCheck1.get(i).getText())) {
					announcementFactory.allCheck.get(i).click();
				}
			}
			WaitFactory
					.waitforelementToBeClickable(BaseClass.wbDriver.findElement(By.xpath("//*[@id='preference-save']")))
					.click();
			WaitFactory
					.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
			BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")).click();
			WaitFactory.waitforelementToinvisibile(
					BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkDepartmentMyPreference() throws InterruptedException {
		try {
			GenericFactory.utilityMenuMyPreferenceClick();
			Thread.sleep(5000);
			for (int i = 0; i < announcementFactory.allCheck.size(); i++) {
				if (announcementFactory.allCheck.get(i).getAttribute("checked") == null) {
					announcementFactory.allCheck.get(i).click();
				}
			}

			WaitFactory
					.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
			BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")).click();
			WaitFactory.waitforelementToinvisibile(
					BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean toClickOnFolder(String folderName, String secondLevelIndex) {
        try {
               List<WebElement> listHeaderName = BaseClass.wbDriver
                            .findElements(By.xpath(".//*[@class='link-group-header']"));
               for (int i = 0; i < listHeaderName.size(); i++) {
                     String temp = listHeaderName.get(i).getText().toString().trim();
                     if (temp.equalsIgnoreCase(secondLevelIndex)) {
                            WebElement framePath = BaseClass.wbDriver
                                          .findElement(By.xpath(".//*[@id='links-target']/div[" + (i + 1) + "]"));
                            List<WebElement> listFolderName = framePath.findElements(By.xpath(".//*[@class='link-item']"));
                            List<WebElement> folder = framePath.findElements(By.xpath(".//*[@class='link-item']/span"));
                            for (int j = 0; j < listFolderName.size(); j++) {
                                   if (listFolderName.get(j).getText().equalsIgnoreCase(folderName)) {
                                          String temp1 = folder.get(j).getAttribute("class").toString().trim();
                                          if (temp1.equalsIgnoreCase("icon folder")) {
                                                 LogFactory.info("Folder is already opened : " + folderName);
                                                 return true;
                                          } else {
                                                 folder.get(j).click();
                                                 LogFactory.info("Successfully clicked on folder : " + folderName);
                                                 return true;
                                          }
                                   }
                            }
                     }
               }
        } catch (Exception e) {
               LogFactory.info("Unable to click on folder : " + folderName);
               return false;
        }
        LogFactory.info("Unable to click on folder : " + folderName);
        return false;
 }


	public static boolean toClickonDeptOnFavourite(String deptName) throws InterruptedException {
		boolean Flag = false;
		// BaseClass.wbDriver.findElement(By.xpath(".//*[@id='main-header']/div[1]/div/h1")).click();
		GenericFactory.navigateToHomePage();
		BrowserFactory.RefreshBrowser();
		// To get list of all departments
		List<WebElement> deptNameList = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='leftNav']/li/a"));

		for (int i = 0; i < deptNameList.size(); i++) {
			String deptNameListValue = deptNameList.get(i).getText().toString().trim();

			if (deptNameListValue.equalsIgnoreCase(deptName)) {
				if (deptNameList.get(i).isEnabled()) {
					deptNameList.get(i).click();
					LogFactory.info("Clicked on desired deparment name. " + deptName);
					Flag = true;
				}
				break;
			}
		}
		return Flag;
	}

	/*
	 * public static boolean toClickonDeptOnFavourite(String deptName) { boolean
	 * flag = false; BaseClass.wbDriver.findElement(By.xpath(
	 * ".//*[@id='main-header']/div[1]/div/h1")).click();
	 * 
	 * //To get list of all departments List<WebElement> deptNameList =
	 * BaseClass.wbDriver.findElements(By.xpath(".//*[@id='leftNav']/li/a"));
	 * for(int i=0; i<=deptNameList.size()-1; i++) { String deptNameListValue =
	 * deptNameList.get(i).getText().toString().trim();
	 * 
	 * if(deptNameListValue.equalsIgnoreCase(deptName)) {
	 * if(deptNameList.get(i).isEnabled()) { deptNameList.get(i).click();
	 * LogFactory.info("Clicked on desired deparment name. " +deptName);
	 * 
	 * flag= true;
	 * 
	 * } break; } } return flag;
	 * 
	 * }
	 */

	public static void checkAllProducts() {

		try {
			ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
			List<String> listOfProductSegments = getCheckBoxValues();
			for (int i = 0; i < listOfProductSegments.size(); i++) {
				WebElement getCheckboxPath = BaseClass.wbDriver
						.findElement(By.xpath(".//*[@id='productSegmentsForm']/div/div[" + (i + 2) + "]/label/input"));
				if (!getCheckboxPath.isSelected()) {
					getCheckboxPath.click();
				}
			}
			alertPageFactory.ApplyFilterButton.click();
			WaitFactory.WaitForinvisibilityOfElement(alertPageFactory.ApplyFilterButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void productUncheck(String product) {
		// ProductSegment_POF.wbelProductSegmentIcon.click();
		List<String> listOfProductSegments = getCheckBoxValues();
		for (int i = 0; i < listOfProductSegments.size(); i++) {
			System.out.println();
			WebElement getCheckboxPath = BaseClass.wbDriver

					.findElement(By.xpath(".//*[@id='productSegmentsForm']/div/div[" + (i + 2) + "]/label/input"));
			if (product.equalsIgnoreCase(listOfProductSegments.get(i)) && getCheckboxPath.isSelected()) {
				getCheckboxPath.click();
				break;
			}
		}
	}

	public static void userAnalyzeInformation(String strRACFID) {

        List<String> userType = new ArrayList<String>();
        List<String> accessCode = new ArrayList<String>();
        String strUserType = "";
        analyzerUserMap.remove("User Type");

        List<WebElement> wbelAllrows = wbDriver.findElements(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr"));

        for (int i = 1; i <= wbelAllrows.size(); i++) {

               String test = wbDriver
                            .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[1]")).getText()
                            .toString().trim();

               if (test.contains("User Type")) {
                     strUserType = wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();
                     userType.add(strUserType);
                     analyzerUserMap.put("User Type", userType);

               }

               if (test.contains("Marketing Responsible Unit")) {
                     String strMRUCode = wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();

                     List<String> MRUCode = new ArrayList<String>();
                     MRUCode.add(strMRUCode);
                     analyzerUserMap.put("MRU", MRUCode);

               }

               if (test.contains("Language")) {
                     String strActualLanguageCode = wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();
                     List<String> languageCode = new ArrayList<String>();
                     languageCode.add(strActualLanguageCode);
                     analyzerUserMap.put("Language", languageCode);
                     analyzerUserMapReset.put("Language", languageCode);
                     flagBaseLanguage = true;
               }

               if (test.contains("Department")) {
                     String strDeptList = BaseClass.wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();
                     
                     String temp = strDeptList.replaceAll("[\\t\\n\\r]+", ", ");
                     if(temp.contains("Finance & Incentives")){
                    	temp= temp.replace("Finance & Incentives","Finance");
                     }
                     List<String> deptList = GenericFactory.splitString(temp, ",");
                     analyzerUserMap.put("Department", deptList);
                     analyzerUserMapReset.put("Department", deptList);
               }

               if (test.contains("Site")) {
                     String strSite = BaseClass.wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();

                     List<String> site = new ArrayList<String>();
                     site.add(strSite);
                     analyzerUserMap.put("strSite", site);
                     analyzerUserMapReset.put("strSite", site);
               }

               if (test.contains("Security Groups")) {
                     String strSecurityGroup = BaseClass.wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();
                     String temp = strSecurityGroup.replaceAll("[\\t\\n\\r]+", ", ");
                     List<String> SecurityGroups = GenericFactory.splitString(temp, ",");
                     analyzerUserMap.put("Security Groups", SecurityGroups);
                     analyzerUserMapReset.put("Security Groups", SecurityGroups);
               }
               if (test.contains("Market, Segment, Products")) {
                     List<String> tempSegments = new ArrayList<String>();

                     int sizeOfSegments = BaseClass.wbDriver
                                   .findElements(
                                                 By.xpath(".//*[@id='analyseUserForm']/table[2]/tbody/tr[6]/td[2]/table/tbody/tr/td[3]"))
                                   .size();
                     int n;
                     for (int k = 0; k < sizeOfSegments; k++) {
                            n = 2;
                            String temp = BaseClass.wbDriver.findElement(By.xpath(
                                          "//*[@id='analyseUserForm']/table[2]/tbody/tr[6]/td[2]/table/tbody/tr[" + n + "]/td[3]"))
                                          .getText().toString().trim();
                            if (!tempSegments.contains(temp)) {
                                   tempSegments.add(temp);
                            }
                            n = n + 1;
                     }
                     for (int j = 0; j < tempSegments.size(); j++) {
                            if (tempSegments.get(j).equals("Commercial Worksite Products")) {
                                   tempSegments.set(j, "CWP");
                            }
                     }
                     analyzerUserMap.put("User Products", tempSegments);
               }
               // To capture users 'Theme color' on Analyze user Info:
               if (test.contains("Theme Colors")) {
                     // To take map value xpath
                     String strActualthemeColorCode = wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();

                     // Array List to store all map value pair and add into an arrayList as an index
                     // form
                     List<String> themeColorCode = new ArrayList<String>();
                     themeColorCode.add(strActualthemeColorCode);
                     analyzerUserMap.put("Theme Colors", themeColorCode);
                     analyzerUserMapReset.put("Theme Colors", themeColorCode);
                     // System.out.println("themeColorCode= " +themeColorCode);
               }

               if (test.contains("Access")) {
                     // To take map value xpath
                     String strAccessCode = wbDriver
                                   .findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
                                   .getText().toString().trim();

                     // Array List to store all map value pair and add into an arrayList as an index
                     // form

                     accessCode.add(strAccessCode);
                     analyzerUserMap.put("Access", accessCode);
               }

        }

        LogFactory.info(strRACFID + " Analyze User Details" + analyzerUserMap);
 }



	/*
	 * public static List<String> getReverseTranslation(String strExpectedValue)
	 * throws IOException, Exception{ LinkedHashMap<String, String> hashMap =
	 * getTranslationMap(); List<String> listExpectedData = new ArrayList<String>();
	 * 
	 * listExpectedData = getKey(hashMap,strExpectedValue);
	 * 
	 * 
	 * return headerList;
	 * 
	 * 
	 * }
	 * 
	 * 
	 * public static <K, V> K getKey(Map<K, V> map, V value) { for (Map.Entry<K, V>
	 * entry : map.entrySet()) { if (value.equals(entry.getValue())) { return
	 * entry.getKey(); } } return null; }
	 */

	public static List<String> getTranslation(String strExpectedValue) throws IOException, Exception {
		boolean flagTranslationFound = false;
		String strExpectedTranslatedTitle = null;
		List<String> listExpectedData = new ArrayList<String>();
		List<String> ExpectedListKey = GenericFactory.splitString(strExpectedValue, ",");

		String languageCode = analyzerUserMap.get("Language").get(0);
		if (languageCode.equalsIgnoreCase("en_US")) {
			LogFactory.info(ExpectedListKey + " is translated to : " + ExpectedListKey);
			return ExpectedListKey;
		}

		if (ExpectedListKey.size() > 1) {

			for (String key : ExpectedListKey) {
				if (!strExpectedValue.equals("") && translationSheet.containsKey(key)) {

					if (!translationSheet.get(key).equals("")) {
						listExpectedData.add(translationSheet.get(key).get(languageCode).toString().trim());
						flagTranslationFound = true;
					}
				}

				else if (!strExpectedValue.equals("")) {
					listExpectedData.add(key.toString().trim());
				}
			}
			// Collections.sort(listExpectedData);
		} else if (!strExpectedValue.equals("") && translationSheet.containsKey(strExpectedValue)) {
			strExpectedTranslatedTitle = translationSheet.get(strExpectedValue).get(languageCode).toString().trim();
			if (!strExpectedTranslatedTitle.equals("")) {
				listExpectedData.add(strExpectedTranslatedTitle);
				flagTranslationFound = true;
			}
		}
		for (int i = 0; i < ExpectedListKey.size(); i++) {

			if (!translationSheet.containsKey(ExpectedListKey.get(i)) && listExpectedData.isEmpty()) {
				listExpectedData.add(strExpectedValue);
				flagTranslationFound = false;
			}
			break;
		}

		if (flagTranslationFound == true) {
			LogFactory
					.info(" Translation found for " + ExpectedListKey + " and is translated as : " + listExpectedData);
		} else {
			LogFactory.info("Translation is not found for : " + ExpectedListKey + " so it is translated to en_US as: "
					+ listExpectedData);
		}

		return listExpectedData;
	}

	public static void EndImpersonateUSER() {
		WebElement userelement = null;
		try {
			userelement = WaitFactory.WaitForElementToVisible(BaseClass.wbDriver
					.findElement(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']")));
			if (userelement != null) {
				userelement.click();
				WebElement btnelement = WaitFactory.WaitForElementToVisible(BaseClass.wbDriver
						.findElement(By.xpath("//button[@class='btn' and @onclick='endImpersonate(this)']")));
				if (btnelement != null) {
					btnelement.click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogFactory.info("Unable to logout or impersonate the user");
		}
	}

	/*
	 * public static void navigateToIndexPage(LinkedHashMap userWCMContent) { String
	 * ContentType = (String) userWCMContent.get("ContentType").toString().trim();
	 * String DepartmentName = (String)
	 * userWCMContent.get("DepartmentName").toString().trim(); String
	 * secondLevelIndex = (String) userWCMContent.get("2ndLevel").toString().trim();
	 * String thirdLevelIndexPage = (String)
	 * userWCMContent.get("3rdLevelIndexPage").toString().trim(); String
	 * thirdLevelFolder = (String)
	 * userWCMContent.get("3rdLevelFolder").toString().trim(); String
	 * forthLevelIndexPage = (String)
	 * userWCMContent.get("4thLevelIndexPage").toString().trim(); String
	 * thirdLevelChildIndexPage = (String)
	 * userWCMContent.get("3rdLevelChildIndexPage").toString().trim(); String
	 * thirdLevelIndexPageCategories = (String)
	 * userWCMContent.get("3rdLevelIndexPageCategories").toString() .trim(); String
	 * thirdLevelIndexPageNestedCategories = (String)
	 * userWCMContent.get("3rdLevelIndexPageNestedCategories") .toString().trim();
	 * String fourthLevelLandingPage = (String)
	 * userWCMContent.get("4thLevelLandingPage").toString().trim(); String
	 * forthLevelChildIndexPage = (String)
	 * userWCMContent.get("4thLevelChildIndexPage").toString().trim(); String
	 * forthLevelIndexPageCategories = (String)
	 * userWCMContent.get("4thLevelIndexPageCategories").toString() .trim(); String
	 * forthLevelIndexPageNestedCategories = (String)
	 * userWCMContent.get("4thLevelIndexPageNestedCategories") .toString().trim();
	 * String thirdLevelGrandChildIndexPage = (String)
	 * userWCMContent.get("3rdLevelGrandChildIndexPage").toString() .trim(); String
	 * thirdLevelChildIndexPageCategories = (String)
	 * userWCMContent.get("3rdLevelChildIndexPageCategories") .toString().trim();
	 * String thirdLevelChildIndexPageNestedCategories = (String) userWCMContent
	 * .get("3rdLevelChildIndexPageNestedCategories").toString().trim();
	 * 
	 * switch (ContentType) {
	 * 
	 * case "AT-Index Page": try { if (!DepartmentName.equalsIgnoreCase("NA") &&
	 * !secondLevelIndex.equalsIgnoreCase("NA") &&
	 * !thirdLevelIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex,
	 * thirdLevelIndexPage); break; } else if
	 * (thirdLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelFolder.equalsIgnoreCase("NA") &&
	 * !forthLevelIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * BaseClass.wbDriver.findElement(By.xpath("//span[contains(@folder,'" +
	 * thirdLevelFolder + "')]")) .click();
	 * BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage
	 * + "']")).click(); LogFactory.info("Navigated to index page successfully 7");
	 * break; } else if (thirdLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelFolder.equalsIgnoreCase("NA") &&
	 * forthLevelIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * BaseClass.wbDriver.findElement(By.xpath("//span[contains(@folder,'" +
	 * thirdLevelFolder + "')]")) .click();
	 * BaseClass.wbDriver.findElement(By.xpath("//a[text()='" +
	 * fourthLevelLandingPage + "']")).click();
	 * LogFactory.info("Navigated to index page successfully 8"); break; } else
	 * System.out.println("Invalid test data"); break; } catch (Exception e) {
	 * System.out.println("Link not found on UI"); } case "AT-ChildIndex Page": try
	 * { if (!DepartmentName.equalsIgnoreCase("NA") &&
	 * !secondLevelIndex.equalsIgnoreCase("NA") &&
	 * !thirdLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelChildIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex,
	 * thirdLevelIndexPage);
	 * clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelChildIndexPage,
	 * thirdLevelIndexPageCategories, thirdLevelIndexPageNestedCategories); break; }
	 * else System.out.println("Invalid test data"); break;
	 * 
	 * } catch (Exception e) {
	 * LogFactory.info("Link is not visible in AT-ChildIndex Page"); }
	 * 
	 * case "AT-GrandChild Index Page": try { if
	 * (!DepartmentName.equalsIgnoreCase("NA") &&
	 * !secondLevelIndex.equalsIgnoreCase("NA") &&
	 * !thirdLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelChildIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex,
	 * thirdLevelIndexPage);
	 * clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelChildIndexPage,
	 * thirdLevelIndexPageCategories, thirdLevelIndexPageNestedCategories);
	 * clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelGrandChildIndexPage,
	 * thirdLevelChildIndexPageCategories,
	 * thirdLevelChildIndexPageNestedCategories); break; } else if
	 * (thirdLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelFolder.equalsIgnoreCase("NA") &&
	 * forthLevelIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * BaseClass.wbDriver.findElement(By.xpath("//span[contains(@folder,'" +
	 * thirdLevelFolder + "')]")) .click();
	 * BaseClass.wbDriver.findElement(By.xpath("//a[text()='" +
	 * fourthLevelLandingPage + "']")).click();
	 * LogFactory.info("Navigated to index page successfully 8"); break; } else if
	 * (thirdLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !thirdLevelFolder.equalsIgnoreCase("NA") &&
	 * !forthLevelIndexPage.equalsIgnoreCase("NA") &&
	 * !forthLevelChildIndexPage.equalsIgnoreCase("NA")) {
	 * clickOnDepartmentByName(DepartmentName);
	 * BaseClass.wbDriver.findElement(By.xpath("//span[contains(@folder,'" +
	 * thirdLevelFolder + "')]")) .click();
	 * BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage
	 * + "']")).click();
	 * clickOnIndexPageLinkOnChildLevelDepartment(forthLevelChildIndexPage,
	 * forthLevelIndexPageCategories, forthLevelIndexPageNestedCategories); break; }
	 * else {
	 * LogFactory.info("Given data for AT-GrandChild Index Page is incorrect.");
	 * break; } } catch (Exception e) {
	 * LogFactory.info("Unable to find the given test data in UI."); } }
	 * 
	 * }
	 * 
	 */

	public static void navigateToIndexPage(LinkedHashMap userWCMContent) throws IOException, Exception {
		String ContentType = (String) userWCMContent.get("ContentType").toString().trim();
		String DepartmentName = (String) userWCMContent.get("DepartmentName").toString().trim();
		String secondLevelIndex = (String) userWCMContent.get("2ndLevel").toString().trim();
		String thirdLevelIndexPage = (String) userWCMContent.get("3rdLevelIndexPage").toString().trim();
		String thirdLevelFolder = (String) userWCMContent.get("3rdLevelFolder").toString().trim();
		String forthLevelIndexPage = (String) userWCMContent.get("4thLevelIndexPage").toString().trim();
		String thirdLevelChildIndexPage = (String) userWCMContent.get("3rdLevelChildIndexPage").toString().trim();
		String thirdLevelIndexPageCategories = (String) userWCMContent.get("3rdLevelIndexPageCategories").toString()
				.trim();
		String thirdLevelIndexPageNestedCategories = (String) userWCMContent.get("3rdLevelIndexPageNestedCategories")
				.toString().trim();
		String thirdLevelLandingPage = (String) userWCMContent.get("3rdLevelLandingPage");
		String fourthLevelLandingPage = (String) userWCMContent.get("4thLevelLandingPage").toString().trim();
		String forthLevelChildIndexPage = (String) userWCMContent.get("4thLevelChildIndexPage").toString().trim();
		String forthLevelIndexPageCategories = (String) userWCMContent.get("4thLevelIndexPageCategories").toString()
				.trim();
		String forthLevelIndexPageNestedCategories = (String) userWCMContent.get("4thLevelIndexPageNestedCategories")
				.toString().trim();
		String thirdLevelGrandChildIndexPage = (String) userWCMContent.get("3rdLevelGrandChildIndexPage").toString()
				.trim();
		String thirdLevelChildIndexPageCategories = (String) userWCMContent.get("3rdLevelChildIndexPageCategories")
				.toString().trim();
		String thirdLevelChildIndexPageNestedCategories = (String) userWCMContent
				.get("3rdLevelChildIndexPageNestedCategories").toString().trim();

		// Code added

		List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
		DepartmentName = translatedText.get(0);

		switch (ContentType) {

		case "AT-Index Page":
			try {
				if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelIndexPage);
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& !forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage + "']")).click();
					LogFactory.info("Navigated to index page successfully");
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + fourthLevelLandingPage + "']")).click();
					LogFactory.info("Navigated to index page successfully");
					break;
				} else if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelLandingPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelLandingPage);
					break;
				} else
					System.out.println("Invalid test data");
				break;
			} catch (Exception e) {
				System.out.println("Link not found on UI");
			}

		case "SAT-Table Index Page":
			try {
				if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelIndexPage.equalsIgnoreCase("NA")
						&& thirdLevelChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelIndexPage);

					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& !forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage + "']")).click();
					LogFactory.info("Navigated to index page successfully");
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + fourthLevelLandingPage + "']")).click();
					LogFactory.info("Navigated to index page successfully");
					break;
				}

				else if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelLandingPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelLandingPage);
					break;
				} else if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelIndexPage.equalsIgnoreCase("NA")
						&& !thirdLevelChildIndexPage.equalsIgnoreCase("NA")
						&& thirdLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelIndexPage);
					clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelChildIndexPage, thirdLevelIndexPageCategories,
							thirdLevelIndexPageNestedCategories);
					break;
				}
				// Code Added by Archana
				else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& !forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage + "']")).click();
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelChildIndexPage + "']")).click();
					LogFactory.info("Navigated to index page successfully");
					break;
				} else if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelIndexPage.equalsIgnoreCase("NA")
						&& !thirdLevelChildIndexPage.equalsIgnoreCase("NA")
						&& !thirdLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelIndexPage);
					clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelChildIndexPage, thirdLevelIndexPageCategories,
							thirdLevelIndexPageNestedCategories);
					clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelGrandChildIndexPage,
							thirdLevelChildIndexPageCategories, thirdLevelChildIndexPageNestedCategories);
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + fourthLevelLandingPage + "']")).click();
					LogFactory.info("Navigated to index page successfully ");
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& !forthLevelIndexPage.equalsIgnoreCase("NA")
						&& !forthLevelChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage + "']")).click();
					clickOnIndexPageLinkOnChildLevelDepartment(forthLevelChildIndexPage, forthLevelIndexPageCategories,
							forthLevelIndexPageNestedCategories);
					break;
				} else
					System.out.println("Invalid test data");
				break;
			} catch (Exception e) {
				System.out.println("Link not found on UI");
			}

		case "AT-ChildIndex Page":
			try {
				if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelIndexPage.equalsIgnoreCase("NA")
						&& !thirdLevelChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelIndexPage);
					clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelChildIndexPage, thirdLevelIndexPageCategories,
							thirdLevelIndexPageNestedCategories);
					break;
				}
				// Code Added by Archana
				else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& !forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage + "']")).click();
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelChildIndexPage + "']")).click();
					LogFactory.info("Navigated to index page successfully");
					break;
				} else
					System.out.println("Invalid test data");
				break;

			} catch (Exception e) {
				LogFactory.info("Link is not visible in AT-ChildIndex Page");
			}

		case "AT-GrandChild Index Page":
			try {
				if (!DepartmentName.equalsIgnoreCase("NA") && !secondLevelIndex.equalsIgnoreCase("NA")
						&& !thirdLevelIndexPage.equalsIgnoreCase("NA")
						&& !thirdLevelChildIndexPage.equalsIgnoreCase("NA")
						&& !thirdLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					clickOnIndexPageLinkOnSecondLevelDepartment(secondLevelIndex, thirdLevelIndexPage);
					clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelChildIndexPage, thirdLevelIndexPageCategories,
							thirdLevelIndexPageNestedCategories);
					clickOnIndexPageLinkOnChildLevelDepartment(thirdLevelGrandChildIndexPage,
							thirdLevelChildIndexPageCategories, thirdLevelChildIndexPageNestedCategories);
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& forthLevelIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + fourthLevelLandingPage + "']")).click();
					LogFactory.info("Navigated to index page successfully ");
					break;
				} else if (thirdLevelIndexPage.equalsIgnoreCase("NA") && !thirdLevelFolder.equalsIgnoreCase("NA")
						&& !forthLevelIndexPage.equalsIgnoreCase("NA")
						&& !forthLevelChildIndexPage.equalsIgnoreCase("NA")) {
					clickOnDepartmentByName(DepartmentName);
					toClickOnFolder(thirdLevelFolder, secondLevelIndex);
					BaseClass.wbDriver.findElement(By.xpath("//a[text()='" + forthLevelIndexPage + "']")).click();
					clickOnIndexPageLinkOnChildLevelDepartment(forthLevelChildIndexPage, forthLevelIndexPageCategories,
							forthLevelIndexPageNestedCategories);
					break;
				} else {
					LogFactory.info("Given data for AT-GrandChild Index Page is incorrect.");
					break;
				}
			} catch (Exception e) {
				
				LogFactory.info("Unable to find the given test data in UI." + e.getMessage().toString());
			}
		}

	}

	/*
	 * public static void clickOnIndexPageLinkOnChildLevelDepartment(String
	 * childIndexPageTitle, String childLevelIndexPageCategories, String
	 * childLevelIndexPageNestedCategories) { try { if
	 * (childLevelIndexPageCategories.equalsIgnoreCase("NA") &&
	 * childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) { WebElement
	 * linkFrame = BaseClass.wbDriver.findElement(By.id("content"));
	 * List<WebElement> links = linkFrame.findElements(By.tagName("a")); for (int i
	 * = 0; i < links.size(); i++) { if
	 * (links.get(i).getText().equals(childIndexPageTitle)) { links.get(i).click();
	 * LogFactory.info("Navigated to index page successfully 4"); return; } } } else
	 * if (!childLevelIndexPageCategories.equalsIgnoreCase("NA") &&
	 * !childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) { int
	 * sizeOfFrames = BaseClass.wbDriver.findElements(By.xpath(
	 * ".//*[@id='linkIndexPageContainer']/div")) .size(); for (int i = 1; i <=
	 * sizeOfFrames; i++) { String nameOfCategory = BaseClass.wbDriver
	 * .findElement(By.xpath(".//*[@id='linkIndexPageContainer']/h3[" + i +
	 * "]")).getText() .toString().trim(); String nameOfSubCategory =
	 * BaseClass.wbDriver
	 * .findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i +
	 * "]/div/h4")).getText() .toString().trim(); if
	 * (nameOfCategory.equalsIgnoreCase(childLevelIndexPageCategories) &&
	 * nameOfSubCategory.equalsIgnoreCase(childLevelIndexPageNestedCategories)) {
	 * WebElement givenFrame = BaseClass.wbDriver
	 * .findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));
	 * List<WebElement> links = givenFrame.findElements(By.tagName("a")); for (int j
	 * = 0; j < links.size(); j++) { if
	 * (links.get(j).getText().equals(childIndexPageTitle)) { links.get(j).click();
	 * LogFactory.info("Navigated to index page successfully 5"); return; } } } } }
	 * else if (!childLevelIndexPageCategories.equalsIgnoreCase("NA") &&
	 * childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) { int
	 * sizeOfFrames = BaseClass.wbDriver.findElements(By.xpath(
	 * ".//*[@id='linkIndexPageContainer']/div")) .size(); for (int i = 1; i <=
	 * sizeOfFrames; i++) { String nameOfCategory = BaseClass.wbDriver
	 * .findElement(By.xpath(".//*[@id='linkIndexPageContainer']/h3[" + i +
	 * "]")).getText() .toString().trim(); if
	 * (nameOfCategory.equalsIgnoreCase(childLevelIndexPageCategories)) { WebElement
	 * givenFrame = BaseClass.wbDriver
	 * .findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));
	 * List<WebElement> links = givenFrame.findElements(By.tagName("a")); for (int j
	 * = 0; j < links.size(); j++) { if
	 * (links.get(j).getText().equals(childIndexPageTitle)) { links.get(j).click();
	 * LogFactory.info("Navigated to index page successfully 6"); return; } } } } }
	 * else { LogFactory.info("Invalid test data provided."); } } catch (Exception
	 * e) { LogFactory.info("This is catch statement"); } }
	 */

	public static void clickOnIndexPageLinkOnChildLevelDepartment(String childIndexPageTitle,
			String childLevelIndexPageCategories, String childLevelIndexPageNestedCategories) {
		try {
			if (childLevelIndexPageCategories.equalsIgnoreCase("NA")
					&& childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
				WebElement linkFrame = BaseClass.wbDriver.findElement(By.id("content"));
				List<WebElement> links = linkFrame.findElements(By.tagName("a"));
				for (int i = 0; i < links.size(); i++) {

					// Code change by Archana
					if (links.get(i).getText().equalsIgnoreCase(childIndexPageTitle)) {
						links.get(i).click();
						LogFactory.info("Navigated to index page successfully 4");
						return;
					}
				}
			} else if (!childLevelIndexPageCategories.equalsIgnoreCase("NA")
					&& !childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
				int sizeOfFrames = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div"))
						.size();
				for (int i = 1; i <= sizeOfFrames; i++) {
					String nameOfCategory = BaseClass.wbDriver
							.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/h3[" + i + "]")).getText()
							.toString().trim();
					String nameOfSubCategory = BaseClass.wbDriver
							.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]/div/h4")).getText()
							.toString().trim();
					if (nameOfCategory.equalsIgnoreCase(childLevelIndexPageCategories)
							&& nameOfSubCategory.equalsIgnoreCase(childLevelIndexPageNestedCategories)) {
						WebElement givenFrame = BaseClass.wbDriver
								.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));
						List<WebElement> links = givenFrame.findElements(By.tagName("a"));
						for (int j = 0; j < links.size(); j++) {
							if (links.get(j).getText().equals(childIndexPageTitle)) {
								links.get(j).click();
								LogFactory.info("Navigated to index page successfully 5");
								return;
							}
						}
					}
				}
			} else if (childLevelIndexPageCategories.equalsIgnoreCase("NA")
					&& !childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
				int sizeOfFrames = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div"))
						.size();
				for (int i = 1; i <= sizeOfFrames; i++) {
					String nameOfCategory = BaseClass.wbDriver
							.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/h3[" + i + "]")).getText()
							.toString().trim();
					if (nameOfCategory.equalsIgnoreCase(childLevelIndexPageCategories)) {
						WebElement givenFrame = BaseClass.wbDriver
								.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));
						List<WebElement> links = givenFrame.findElements(By.tagName("a"));
						for (int j = 0; j < links.size(); j++) {
							if (links.get(j).getText().equals(childIndexPageTitle)) {
								links.get(j).click();
								LogFactory.info("Navigated to index page successfully 6");
								return;
							}
						}
					}
				}
			} else {
				LogFactory.info("Invalid test data provided.");
			}
		} catch (Exception e) {
			LogFactory.info("This is catch statement");
		}
	}

	/*
	 * public static List<String> getParentCountryName(List<String> countryName) {
	 * Set<String> hs = new HashSet<>(); for (int i = 0; i < countryName.size();
	 * i++) { if (countryName.get(i).contains("/")) { String temp =
	 * splitString(countryName.get(i), "/").get(1).toString().trim(); hs.add(temp);
	 * }
	 * 
	 * }
	 * 
	 * countryName.clear(); countryName.addAll(hs);
	 * 
	 * return countryName; }
	 */

	public static boolean uncheckCountryGrouping(List<String> listCountryNameToUncheck) throws InterruptedException {
		boolean flag = false;
		try {
			announcementFactory.countryGroupingIcon.click();
			WaitFactory.WaitForElementToVisible(Announcements_POF.countryApplyFilterButton);
			for (int i = 0; i < announcementFactory.webElementOfCountryCheckBox.size(); i++) {
				if (!announcementFactory.webElementOfCountryCheckBox.get(i).isSelected()) {
					announcementFactory.webElementOfCountryCheckBox.get(i).click();
				}
			}
			for (int j = 0; j < listCountryNameToUncheck.size(); j++) {
				System.out.println(listCountryNameToUncheck.get(j));
				for (int i = 1; i <= Announcements_POF.listCountryNameFromWebPage.size(); i++) {
					String temp = BaseClass.wbDriver
							.findElement(By.xpath(".//*[@id='country-grouping-form']/div/div/div[" + i + "]/label/div"))
							.getText().toString().trim();
					if (listCountryNameToUncheck.get(j).equalsIgnoreCase(temp)) {
						// Announcements_POF.webElementOfCountryCheckBox.get(i).click();
						BaseClass.wbDriver
								.findElement(By
										.xpath(".//*[@id='country-grouping-form']/div/div/div[" + i + "]/label/input"))
								.click();
						flag = true;
					}
				}
			}
			announcementFactory.countryApplyFilterButton.click();
			if (ValidationFactory.isElementPresent(Announcements_POF.countryError)) {
				Announcements_POF.countryGroupingIcon.click();
				flag = false;
				return flag;
			} else {
				WaitFactory.WaitForinvisibilityOfElement(Announcements_POF.countryApplyFilterButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	public static void checkAllCountryGrouping() {

		WebElement EachCheckboxCountryGrouping;

		try {

			WebElement weebobj = BaseClass.wbDriver
					.findElement(By.xpath(".//*[@class='search-icons countryGrouping']"));
			WaitFactory.WaitForElementToVisible(weebobj).click();

			LogFactory.info("Clicked on country Grouping Icon");

			List<WebElement> countryGroupingCheckboxList = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='country-grouping-form']/div/div/div/label/input"));
			for (int i = 0; i < countryGroupingCheckboxList.size(); i++) {
				// To take the list of country Grouping
				EachCheckboxCountryGrouping = countryGroupingCheckboxList.get(i);
				if (!EachCheckboxCountryGrouping.isSelected()) {
					EachCheckboxCountryGrouping.click();

				}
			}
			// countryApplyFilterbtnpath.click();
			BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-country-group-popover']/div/div[2]/button")).click();
			WaitFactory.waitForPageLoaded();

			LogFactory.info("Checked all Country Grouping checkbox ");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean selectProductsFromProductSegment(String strProductName) {
		int i;
		String eachProductName = "";
		WebElement eachProductCheckbox;
		checkAllProducts();

		ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();

		List<WebElement> productCheckboxList = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label/input"));
		List<WebElement> productNameList = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label/div"));
		for (i = 0; i < productCheckboxList.size(); i++) {
			eachProductCheckbox = productCheckboxList.get(i);
			if (!eachProductCheckbox.isSelected()) {
				eachProductCheckbox.click();
			}
			LogFactory.info("Selected All Product ");
			for (i = 0; i < productNameList.size(); i++) {
				// To take the list of product segment
				eachProductName = productNameList.get(i).getText().toString().trim();
				eachProductCheckbox = productCheckboxList.get(i);
				if (eachProductCheckbox.isSelected() && strProductName.contains(eachProductName)) {
					eachProductCheckbox.click();
				}
			}
		}

		ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='js-segments-popover']/div[2]/div[3]/button")).click();
		WaitFactory.waitForPageLoaded();
		LogFactory.info("Checked all given product segment name checkbox ");

		if (ValidationFactory.isElementPresent(By.xpath("//p[@id='productSegmentsError']"))) {
			ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
			return false;
		} else {
			return true;
		}

	}

	public static void utilityMenuMyPreferenceClickFavourites() {

		try {

			WebElement userelement = null;

			// userelement = ValidationFactory.getElementIfPresent(By.xpath("//div[contains
			// (@class,'user-info') and @id ='js-user-info']"));
			userelement = WaitFactory.waitforelementToBeClickable(BaseClass.wbDriver
					.findElement(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']")));
			if (userelement != null) {

				userelement.click();
				Thread.sleep(2000);
				LogFactory.info("Utility Menu Clicked.....");

				ValidationFactory.getElementIfPresent(By.xpath("//a[@id='preferences']")).click();

				// WaitFactory.waitforelementToBeClickable(BaseClass.wbDriver.findElement(By.xpath("//a[@id='preferences']"))).click();
				LogFactory.info("Successfully clicked on My Preference Link from utility manu....");

			}

		} catch (Exception e) {
			e.getMessage();
			LogFactory.info("Unable to click on My Preference utility link or Element not found "
					+ e.getMessage().substring(150));
			// Assert.fail("Unable to click on My Preference utility link or Element not
			// found");

		}

	}

	public static boolean clickOnDepartmentByName(String DepartmentNameInEnglish) throws IOException, Exception {
		try {
			String translatedDepartmentName = GenericFactory.getTranslation(DepartmentNameInEnglish).get(0);
			// LogFactory.info("Translated Department : "+translatedDepartmentName);
			boolean leftNavigationActiveDepartment = ValidationFactory.isElementPresent(By.xpath(
					".//*[@id='leftNav']/li/a[contains(text(),'" + translatedDepartmentName + "')][@class='active']"));
			boolean isUserOnHomePage = ValidationFactory
					.isElementPresent(By.xpath("//input[@class='filter-box fav-filter']"));
			if (isUserOnHomePage) {
				if (leftNavigationActiveDepartment) {
					WaitFactory.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='leftNav']/li/a[contains(text(),'"
							+ translatedDepartmentName + "')][@class='active']"))).click();
					return true;
				}
				return false;
			} else {
				String selectedLeftTab = BaseClass.wbDriver
						.findElement(By.xpath("//*[@class='active  selected leftNavDeptPadding']")).getText();
				if (!translatedDepartmentName.equals(selectedLeftTab)) {
					Actions builder = new Actions(BaseClass.wbDriver);
					WebElement arrowClick = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='left_nav_0']"));
					builder.moveToElement(arrowClick).build().perform();
					if (ValidationFactory.isElementPresent(By.xpath(".//*[@id='flyOut']/li/a[contains(text(),'"
							+ translatedDepartmentName + "')][@class='active']"))) {
						BaseClass.wbDriver.findElement(By.xpath(".//*[@id='flyOut']/li/a[contains(text(),'"
								+ translatedDepartmentName + "')][@class='active']")).click();
						return true;
					}
				} else
					return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static void multilingualSwitch(String libraryName, String wcmMultilungual) throws Throwable {
		try {

			if (BaseClass.flagMultilingual.equalsIgnoreCase("Y") && wcmMultilungual.equalsIgnoreCase("Y")) {
				switchToLibrary(libraryName);
				flagBaseLanguage = false;

			} else {
				if (flagBaseLanguage == false && !wcmMultilungual.equalsIgnoreCase("Y")) {
					switchToLibrary(libraryName);
					flagBaseLanguage = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static List<String> switchToLibrary(String libraryName) { List<String>
	 * switchedLanguage = new ArrayList<String>(); try { System.out.println();
	 * GenericFactory.utilityMenuMyPreferenceClick(); boolean language =
	 * ValidationFactory.isElementPresent(By.xpath(".//*[@id='lang']")); if
	 * (language) { Select libraryLanguage_obj = new
	 * Select(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='lang']")));
	 * List<WebElement> libraryLanguageOptions = libraryLanguage_obj.getOptions();
	 * for (int i = 0; i < libraryLanguageOptions.size(); i++) if
	 * (!libraryLanguageOptions.get(i).isSelected() &&
	 * LibraryLanguageListMap(libraryName)
	 * .equals(libraryLanguageOptions.get(i).getAttribute("value"))) {
	 * libraryLanguage_obj.selectByValue(libraryLanguageOptions.get(i).getAttribute(
	 * "value"));
	 * switchedLanguage.add(libraryLanguageOptions.get(i).getAttribute("value"));
	 * analyzerUserMap.replace("Language", switchedLanguage); } Thread.sleep(2000);
	 * Announcements_POF.MyPreferenceSavebtn.click();
	 * WaitFactory.waitForPageLoaded(); // analyzerUserMap.put("Language",
	 * libraryName);
	 * LogFactory.info("Language DDL selected the user preferred language "); } else
	 * { Announcements_POF.crossIconOnMyPreference.click(); Thread.sleep(5000);
	 * System.out.println(); }
	 * LogFactory.info("Language selection is not applicable for this user "); }
	 * catch (Exception e) {
	 * 
	 * // TODO: handle exception } return switchedLanguage; }
	 */

	public static List<String> switchToLibrary(String libraryName) {
		  List<String> switchedLanguage = new ArrayList<String>();
		  try {
		   GenericFactory.utilityMenuMyPreferenceClick();
		   WaitFactory.WaitForElementToVisible(Announcements_POF.MyPreferenceSavebtn);

		   boolean language = ValidationFactory.isElementPresent(By.xpath(".//*[@id='lang']"));
		   if (language) {
		    Select libraryLanguage_obj = new Select(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='lang']")));
		    List<WebElement> libraryLanguageOptions = libraryLanguage_obj.getOptions();
		    for (int i = 0; i < libraryLanguageOptions.size(); i++)
		     if (!libraryLanguageOptions.get(i).isSelected() && LibraryLanguageListMap(libraryName)
		       .equals(libraryLanguageOptions.get(i).getAttribute("value"))) {
		      libraryLanguage_obj.selectByValue(libraryLanguageOptions.get(i).getAttribute("value"));
		      switchedLanguage.add(libraryLanguageOptions.get(i).getAttribute("value"));
		      analyzerUserMap.replace("Language", switchedLanguage);
		     }
		    Announcements_POF.MyPreferenceSavebtn.click();
		    WaitFactory.waitforelementToinvisibile(Announcements_POF.MyPreferenceSavebtn);
		    WaitFactory.waitForPageLoaded();
		    LogFactory.info("Language DDL selected the user preferred language ");
		   } else {
		    /*WebElement crossButton = ValidationFactory.getElementIfPresent(By.cssSelector(
		      ".//*[@class='modal-dialog']//..[@class='modal-content']//..[@class='modal-header']//../button[@class='close'][@type='button']"));
		    crossButton.click();*/
		    
		    MyPreferences_POF.wbelMyPreferenceModalCancelButton.click();
		    WaitFactory.waitforelementToinvisibile(Announcements_POF.MyPreferenceSavebtn);
		    WaitFactory.waitForPageLoaded();
		   }
		   LogFactory.info("Language selection is not applicable for this user ");
		  } catch (Exception e) {

		   // TODO: handle exception
		  }
		  return switchedLanguage;
		 }
	
	public static String LibraryLanguageListMap(String libraryName) {
		LinkedHashMap<String, String> LibraryLanguageListMap = new LinkedHashMap<String, String>();

		LibraryLanguageListMap.put("R3_CONTENT_pt_BR", "Brazil Portuguese"); // Brasil Portuguese
		LibraryLanguageListMap.put("R3_CONTENT_es_MX", "Spanish"); // Spanish lang for R3 Hispano
		LibraryLanguageListMap.put("R3_CONTENT_en_US", "en_US"); // English lang for R3 Hispano
		LibraryLanguageListMap.put("JDIN_CONTENT_DE", "Deutsch"); // German lang for JDIN
		LibraryLanguageListMap.put("JDIN_CONTENT_EN", "English"); // English lang for JDIN
		LibraryLanguageListMap.put("JDIN_CONTENT_FR", "fr_CA"); // French lang for JDIN
		LibraryLanguageListMap.put("JDIN_CONTENT_RU", "Ñ€ÑƒÌÑÑÐºÐ¸Ð¹ ÑÐ·Ñ‹ÌÐº"); // Russian lang for JDIN
		LibraryLanguageListMap.put("R4_CONTENT_en_US", "en_US"); // US English lang for R4
		LibraryLanguageListMap.put("R4_CONTENT_es_MX", "es_MX"); // Mexican Spanish lang for R4
		LibraryLanguageListMap.put("R4_CONTENT_fr_CA", "fr_CA"); // Canadian French lang for R4
		LibraryLanguageListMap.put("R3_CONTENT_DE", "Deutsch"); // German lang for R3 Content
		LibraryLanguageListMap.put("R1_CONTENT_en_Asia", "en_US"); // English lang for JDAsia
		LibraryLanguageListMap.put("R1_CONTENT_en_IN", "en_US"); // English lang for Hispano
		LibraryLanguageListMap.put("R1_CONTENT_en_SSA", "en_US"); // English lang for Sub Saharan
		LibraryLanguageListMap.put("R1_CONTENT_th_TH", ""); // Thai lang for Thailand
		LibraryLanguageListMap.put("R1_CONTENT_zh_CN", ""); // Chinese lang for R1
		LibraryLanguageListMap.put("R4_CONTENT_en_AU", "English"); // English lang for Austria
		if (LibraryLanguageListMap.containsKey(libraryName)) {
			return LibraryLanguageListMap.get(libraryName);
		}
		return null;
	}

	// Aniket
	public static boolean selectProductsFromProductSegmentList(String strProductName) throws IOException, Exception {
		int i;
		String eachProductName = "";
		WebElement eachProductCheckbox;
		String[] productsUncheck = null;
		List<String> productsUncheckNew = new ArrayList<>();
		if (strProductName.contains("[") && strProductName.contains("]")) {
			strProductName = strProductName.replaceAll("[\\[\\]]", "");
			if (strProductName.contains(","))
				productsUncheck = strProductName.trim().split(",");

		}
		if (productsUncheck != null) {
			for (int k = 0; k < productsUncheck.length; k++) {
				productsUncheckNew.add(productsUncheck[k].trim());
			}
		} else {
			productsUncheckNew.add(strProductName);
		}
		checkAllProducts();
		ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();

		List<WebElement> productCheckboxList = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label/input"));
		List<WebElement> productNameList = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label/div"));
		for (i = 0; i < productCheckboxList.size(); i++) {
			eachProductCheckbox = productCheckboxList.get(i);
			if (!eachProductCheckbox.isSelected()) {
				eachProductCheckbox.click();
			}
			LogFactory.info("Selected All Product ");
			for (i = 0; i < productNameList.size(); i++) {
				// To take the list of product segment
				eachProductName = productNameList.get(i).getText().toString().trim();
				eachProductCheckbox = productCheckboxList.get(i);

				if (!(productsUncheckNew.isEmpty())) {
					for (int j = 0; j < productsUncheckNew.size(); j++) {
						if (eachProductCheckbox.isSelected() && productsUncheckNew.contains(eachProductName.trim())
								|| eachProductCheckbox.isSelected() && (GenericFactory
										.getTranslation(productsUncheckNew.get(j)).contains(eachProductName.trim()))) {
							eachProductCheckbox.click();
							break;
						}
					}
				}

			}

		}

		ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='js-segments-popover']/div[2]/div[3]/button")).click();
		WaitFactory.waitForPageLoaded();
		LogFactory.info("Checked all product " + productsUncheckNew + " segment name checkbox ");

		if (ValidationFactory.isElementPresent(By.xpath("//p[@id='productSegmentsError']"))) {
			alertPageFactory.segmentError = alertPageFactory.wblProdSegmentError.getText();
			if (ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")) != null)
				ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
			return false;
		} else {
			return true;
		}

	}

	/*
	 * public static String getParentProductSegmnentList(String product) { String
	 * productName = product; String[] productsUncheck = null; List<String>
	 * productsUncheckNew = new ArrayList<>(); if (product.contains("[") &&
	 * product.contains("]")) { product = product.replaceAll("[\\[\\]]", ""); if
	 * (product.contains(",")) productsUncheck = product.split(","); } if
	 * (productsUncheck != null) { for (int i = 0; i < productsUncheck.length; i++)
	 * { if (productsUncheck[i].contains("/")) { List<String> temp =
	 * splitString(productsUncheck[i], "/"); productName = temp.get(1); }
	 * 
	 * if (productName.matches("(?i)Construction|Utility")) { if
	 * (productsUncheckNew.contains("Construction")) continue; else
	 * productsUncheckNew.add("Construction"); } else if
	 * (productName.matches("(?i)Forestry")) {
	 * 
	 * if (productsUncheckNew.contains("Forestry")) continue; else
	 * productsUncheckNew.add("Forestry"); } else if
	 * (productName.matches("(?i)Homeowner")) { if
	 * (productsUncheckNew.contains("Homeowner")) continue; else
	 * productsUncheckNew.add("Homeowner"); } else if
	 * (productName.matches("(?i)CWP")) { if (productsUncheckNew.contains("CWP"))
	 * continue; else productsUncheckNew.add("CWP"); } else if
	 * (productName.matches("(?i)Hitachi|Mining")) { if
	 * (productsUncheckNew.contains("Hitachi")) continue; else
	 * productsUncheckNew.add("Hitachi"); } else if (productName.matches(
	 * "(?i)Agriculture|Ag Equipment|Sprayers & Nutrient Applicators|Scraper and Scraper Tractor|Forage Harvester"
	 * )) if (productsUncheckNew.contains("Agriculture")) continue; else
	 * productsUncheckNew.add("Agriculture"); else if
	 * (productName.matches("(?i)Turf|Commercial|Residential")) { if
	 * (productsUncheckNew.contains("Turf")) continue; else
	 * productsUncheckNew.add("Turf"); } else if (productName.matches("(?i)Golf")) {
	 * if (productsUncheckNew.contains("Golf")) continue; else
	 * productsUncheckNew.add("Golf"); } } } else { productsUncheckNew.add(product);
	 * }
	 * 
	 * if (productsUncheckNew.size() >0 ) { return productsUncheckNew.toString(); }
	 * else {productsUncheckNew.add(product); return productsUncheckNew.toString();}
	 * 
	 * 
	 * }
	 */

	public static String getParentProductSegmnentList(String product) {
		String productName = product;
		String[] productsUncheck = null;
		List<String> productsUncheckNew = new ArrayList<>();
		if (product.contains("[") && product.contains("]")) {
			product = product.replaceAll("[\\[\\]]", "");
			if (product.contains(","))
				productsUncheck = product.split(",");
		}
		if (productsUncheck != null) {
			for (int i = 0; i < productsUncheck.length; i++) {
				if (productsUncheck[i].contains("/")) {
					List<String> temp = splitString(productsUncheck[i], "/");
					productName = temp.get(1).trim();
				} else {
					productName = productsUncheck[i].trim();
				}

				if (productName.matches("(?i)Construction|Utility")) {
					if (productsUncheckNew.contains("Construction"))
						continue;
					else
						productsUncheckNew.add("Construction");
				} else if (productName.matches("(?i)Forestry")) {

					if (productsUncheckNew.contains("Forestry"))
						continue;
					else
						productsUncheckNew.add("Forestry");
				} else if (productName.matches("(?i)Homeowner")) {
					if (productsUncheckNew.contains("Homeowner"))
						continue;
					else
						productsUncheckNew.add("Homeowner");
				} else if (productName.matches("(?i)CWP")) {
					if (productsUncheckNew.contains("CWP"))
						continue;
					else
						productsUncheckNew.add("CWP");
				} else if (productName.matches("(?i)Hitachi|Mining")) {
					if (productsUncheckNew.contains("Hitachi"))
						continue;
					else
						productsUncheckNew.add("Hitachi");
				} else if (productName.matches(
						"(?i)Agriculture|Ag Equipment|Sprayers & Nutrient Applicators|Scraper and Scraper Tractor|Forage Harvester"))
					if (productsUncheckNew.contains("Agriculture"))
						continue;
					else
						productsUncheckNew.add("Agriculture");
				else if (productName.matches("(?i)Turf|Commercial|Residential")) {
					if (productsUncheckNew.contains("Turf"))
						continue;
					else
						productsUncheckNew.add("Turf");
				} else if (productName.matches("(?i)Golf")) {
					if (productsUncheckNew.contains("Golf"))
						continue;
					else
						productsUncheckNew.add("Golf");
				} else if (productName.matches("(?i)JDPS|JDPS Distributor|Direct OEM")) {
					if (productsUncheckNew.contains("JDPS"))
						continue;
					else
						productsUncheckNew.add("JDPS");
				}
			}
		} else {
			productsUncheckNew.add(product);
		}
		return productsUncheckNew.toString();

	}

	public static List<String> getParentCountryName(List<String> countryName) {
		Set<String> hs = new HashSet<>();
		for (int i = 0; i < countryName.size(); i++) {
			if (countryName.get(i).contains("/")) {
				String temp = splitString(countryName.get(i), "/").get(1).toString().trim();
				if (temp.contains("-")) {
					hs.add(splitString(temp, "-").get(0));
				} else {
					hs.add(temp);
				}
			}
		}
		countryName.clear();
		countryName.addAll(hs);
		return countryName;
	}

	public static Boolean compareInputWithActualTextFromDrpdwn(WebElement wb, String textToVerify) {
		Select sel = new Select(wb);
		String strCurrentValue = sel.getFirstSelectedOption().getText();
		System.out.println("Current text from dropdown: " + strCurrentValue);
		if (strCurrentValue.equalsIgnoreCase(textToVerify)) {
			return true;
		} else
			return false;
	}

	public static List<String> rackfGroupsMap() {
		Set<String> hs = new HashSet<>();
		List<String> listOfRackfGroupFromAnalyseUser = BaseClass.analyzerUserMap.get("Security Groups");
		for (Map.Entry<String, List<String>> entries : racfGroupMapping.entrySet()) {
			for (String str : listOfRackfGroupFromAnalyseUser) {
				if (entries.getValue().contains(str)) {
					hs.add(entries.getKey());
				}
			}
		}
		if (!(hs.size() == 0)) {
			return new ArrayList<String>(hs);
		}
		return null;
	}

	public static Boolean selectByVisibleText(WebElement wb, String textToSelect) {
		Select sel = new Select(wb);
		try {
			sel.selectByVisibleText(textToSelect);
			String strCurrentValue = sel.getFirstSelectedOption().getText();
			System.out.println("Current value of dropdown changed to : " + strCurrentValue);
			if (strCurrentValue.equalsIgnoreCase(textToSelect)) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verifyRacfGroupMatched(String strExcelData, String strTCID) throws Throwable {
		if (strExcelData.equalsIgnoreCase("NA")) {
			List<String> listOfRacfGroupFromWcmContent = GenericFactory.splitString(strExcelData, ",");
			List<String> actualDataListOriginal = BaseClass.analyzerUserMap.get("Security Groups");
			for (int i = 0; i < actualDataListOriginal.size(); i++) {
				if (listOfRacfGroupFromWcmContent.contains(actualDataListOriginal.get(i)))
					return true;
			}
			return false;
		}
		return true;
	}

	public static void SendPORTLET_LINKFLAG(LinkedHashMap userWCMContent) throws Throwable {

		String wcmTestCaseID = userWCMContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWCMContent.get("3rdLevelIndexPage").toString().trim();

		String Title = userWCMContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWCMContent.get("3rdLevelIndexPageCategories").toString().trim();
		String ThirdLevelChildIndexPageCategories = userWCMContent.get("3rdLevelIndexPageCategories").toString().trim();
		String ThirdLevelIndexPageNestedCategories = userWCMContent.get("3rdLevelChildIndexPageCategories").toString()
				.trim();
		String flag = "Fail";
		String scenarioName = "Verify 3rd level Index page name should be header.";
		String actualResult = "";
		String expectedResult = "";
		String LandingPage = userWCMContent.get("3rdLevelLandingPage").toString().trim();

		String DepartmentName = userWCMContent.get("DepartmentName").toString().trim();

		String SecondLevel = userWCMContent.get("2ndLevel").toString().trim();

		String ThirdLevelChildIndexPage = userWCMContent.get("3rdLevelChildIndexPage").toString().trim();
		String ThirdLevelFolder = userWCMContent.get("3rdLevelFolder").toString().trim();

		String strContentType = userWCMContent.get("ContentType").toString().trim();
		String strfourlevelIndexPage = userWCMContent.get("4thLevelIndexPage").toString().trim();
		String strfourlevelchildIndexPage = userWCMContent.get("4thLevelChildIndexPage").toString().trim();

		String strfourlevelLandingPage = userWCMContent.get("4thLevelLandingPage").toString().trim();

		String ThirdLevelGrandChildIndexPage = userWCMContent.get("3rdLevelGrandChildIndexPage").toString().trim();
		String FourLevelGrandChildIndexPage = userWCMContent.get("4thLevelGrandChildIndexPage").toString().trim();

		if (!ThirdLevelIndexPage.equalsIgnoreCase("NA") && !ThirdLevelChildIndexPage.equalsIgnoreCase("NA")) {

			if (!ThirdLevelIndexPage.equalsIgnoreCase("NA") && !ThirdLevelChildIndexPage.equalsIgnoreCase("NA")
					&& !ThirdLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
				BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelIndexPage + "->"
						+ ThirdLevelChildIndexPage + "->" + ThirdLevelGrandChildIndexPage;
			} else {
				BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelIndexPage + "->"
						+ ThirdLevelChildIndexPage;
			}
		} else if (!LandingPage.equalsIgnoreCase("NA")) {

			BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + LandingPage;

		} else if (!ThirdLevelFolder.equalsIgnoreCase("NA")) {
			if (!strfourlevelchildIndexPage.equalsIgnoreCase("NA")) {
				if (!strfourlevelchildIndexPage.equalsIgnoreCase("NA")
						&& !FourLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
					BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelFolder + "->"
							+ strfourlevelIndexPage + "->" + strfourlevelchildIndexPage + "->"
							+ FourLevelGrandChildIndexPage;

				} else {
					BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelFolder + "->"
							+ strfourlevelIndexPage + "->" + strfourlevelchildIndexPage;
				}

			} else {
				BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelFolder + "->"
						+ strfourlevelIndexPage;
			}
			if (!strfourlevelLandingPage.equalsIgnoreCase("NA")) {
				BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelFolder + "->"
						+ strfourlevelLandingPage;

			}
			if (!ThirdLevelChildIndexPage.equalsIgnoreCase("NA")
					&& !ThirdLevelGrandChildIndexPage.equalsIgnoreCase("NA")) {
				BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelFolder + "->"
						+ ThirdLevelChildIndexPage + "->" + ThirdLevelGrandChildIndexPage;
			}
		} else if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")) {
			BaseClass.PORTLET_LINKFLAG = DepartmentName + "->" + SecondLevel + "->" + ThirdLevelIndexPage;
		}
	}

	public static boolean findIndexPageLink(String linkNameTofind, String headerNameOfLink) {
		try {
			List<WebElement> listHeaderName = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@class='link-group-header']"));
			for (int i = 0; i < listHeaderName.size(); i++) {
				String temp = listHeaderName.get(i).getText().toString().trim();
				if (temp.equalsIgnoreCase(headerNameOfLink)) {
					WebElement framePath = ValidationFactory
							.getElementIfPresent(By.xpath(".//*[@id='links-target']/div[" + (i + 1) + "]"));
					boolean isElementFind = ValidationFactory
							.isElementPresent(framePath.findElement(By.xpath("//a[text()='" + linkNameTofind + "']")));
					if (isElementFind) {
						return true;
					}
				}
			}
			LogFactory.info("Given department name i.e. " + headerNameOfLink
					+ " is not present either in Left Navigation or in Category Header");
		} catch (Exception e) {
			LogFactory.info("Unable to search Index page Link " + linkNameTofind + " or Second Level category i.e. "
					+ headerNameOfLink);
			return false;
		}
		return false;
	}

	public static boolean clickOnDepartmentByNameFlag(String DepartmentName) {
		boolean flag = false;
		try {
			for (int i = 0; i < PortalLeftNavigation_POF.ListAllActiveLinks.size(); i++) {
				if (PortalLeftNavigation_POF.ListAllActiveLinks.get(i).getText().equalsIgnoreCase(DepartmentName)) {

					return true;
				}
			}
			System.out.println("Given department name : " + DepartmentName
					+ " is not available/disabled in Left navigation window");
		} catch (Exception e) {
			LogFactory.info(
					"Given department name : " + DepartmentName + " is not available/active in Left navigation window");
			return false;
		}
		return flag;
	}

	public static void tocheckAllProducts() throws Throwable {
		List<String> listOfElements = GenericFactory.getCheckBoxValuesAll();
		GenericFactory.checkAllProductsData(listOfElements);
		Thread.sleep(1000);
		PortletLinksPage_POF.applyFilter.click();
	}

	public static boolean checkAllProductsData(List<String> listOfElements) throws Throwable {

		boolean checkboxstatus = true;
		WebElement oCheckBox = null;
		WebElement productSeg = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='productSegmentsForm']/div"));
		if (!productSeg.isDisplayed()) {

			ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
		}

		for (int i = 0; i < listOfElements.size(); i++) {

			try {
				System.out.println(listOfElements.get(i).toString());
				if (!(listOfElements.get(i).toString().equalsIgnoreCase("JDPS"))) {
					oCheckBox = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='" + listOfElements.get(i) + "']"));

				} else {

					oCheckBox = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='JDPS Direct OEM']"));
				}

				checkboxstatus = oCheckBox.isSelected();

				if (checkboxstatus == false) {
					oCheckBox.click();
					checkboxstatus = true;

				}

			} catch (Exception e) {

			}

		}
		return checkboxstatus;

	}

	public static List<String> getCheckBoxValuesAll() throws Throwable {

		ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();

		List<WebElement> getWebElementsLinks = BaseClass.wbDriver
				.findElements(By.xpath("//form[@id='productSegmentsForm']/div/div[@class='value']//div"));
		List<String> listOfElements = new ArrayList<String>();
		for (int i = 0; i < getWebElementsLinks.size(); i++) {
			listOfElements.add(getWebElementsLinks.get(i).getText());
		}
		return listOfElements;

	}

	public static boolean compareLinksandFolderName(String strSearchCriteria) {
		boolean folderFlag = false;
		String getFoldername = null;
		List<WebElement> finallink = Homepage_POF.HomDriver
				.findElements(By.xpath(".//*[@id='links-target']//div[@class='link-group']"));
		for (int p = 0; p < finallink.size(); p++) {

			String temp1 = finallink.get(p).getText().trim();

			String[] lines = temp1.split("\n");

			for (int i = 0; i < lines.length; i++) {

				String links = lines[i];

				if (links.toUpperCase().contains(strSearchCriteria.toUpperCase())) {

					folderFlag = true;
					break;

				} else {
					folderFlag = false;
				}
			}

			if (folderFlag) {
				break;
			}

		}
		return folderFlag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<String> listOfLinksGeneric() {

		List<WebElement> linksNameB = Homepage_POF.HomDriver.findElements(By.xpath(
				"//*[@id='links-target']//div[@class='link-group']//div[@class='link-item wrap' or @class='link-item child wrap ']//a"));
		ArrayList<String> SAnchorTagsLinksBefore = new ArrayList<>();
		for (WebElement linkValuesB : linksNameB) {
			String linksFolderB = linkValuesB.getText();
			SAnchorTagsLinksBefore.add(linksFolderB.trim());
		}
		return SAnchorTagsLinksBefore;
	}

	public static String listLinksWithFolderName(String folderName, String secondLevelIndex) {

		boolean folderClickableFlag = false;
		String getFoldername = null;
		List<WebElement> listHeaderName = BaseClass.wbDriver.findElements(By.xpath(".//*[@class='link-group-header']"));
		for (int i = 0; i < listHeaderName.size(); i++) {
			String temp = listHeaderName.get(i).getText().toString().trim();
			if (temp.equalsIgnoreCase(secondLevelIndex)) {
				WebElement framePath = BaseClass.wbDriver
						.findElement(By.xpath(".//*[@id='links-target']/div[" + (i + 1) + "]"));
				List<WebElement> listFolderName = framePath.findElements(By.xpath(".//*[@class='link-item']"));
				List<WebElement> folder = framePath.findElements(By.xpath(".//*[@class='link-item']/span"));
				for (int j = 0; j < listFolderName.size(); j++) {
					if (listFolderName.get(j).getText().toUpperCase().contains(folderName.toUpperCase())) {
						getFoldername = listFolderName.get(j).getText();
						folder.get(j).click();
						folderClickableFlag = true;
						break;

					}
				}
				if (folderClickableFlag) {
					break;
				}
			}
		}
		return getFoldername;
	}

	public static String getParentProduct(String product) {
		String productName = product;
		if (product.contains("/")) {
			List<String> temp = splitString(product, "/");
			productName = temp.get(1);
		}
		if (productName.matches("(?i)Construction|Utility"))
			return "Construction";
		else if (productName.matches("(?i)Forestry"))
			return "Forestry";
		else if (productName.matches("(?i)CWP"))
			return "CWP";
		else if (productName.matches("(?i)Hitachi|Mining"))
			return "Hitachi";
		else if (productName.matches(
				"(?i)Agriculture|Ag Equipment|Sprayers & Nutrient Applicators|Scraper and Scraper Tractor|Forage Harvester"))
			return "Agriculture";
		else if (productName.matches("(?i)Commercial|Residential|Turf"))
			return "Turf";
		else if (productName.matches("(?i)Golf"))
			return "Golf";
		else if (productName.matches("(?i)JDPS|JDPS Distributor|Direct OEM"))
			return "JDPS";
		else
			LogFactory.info("Not returning any value");
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int cntlistOfLinksheader(String strSearchCriteria) {

		int countofLinksforHeader = 0;
		List<WebElement> actualHeaderName = Homepage_POF.HomDriver
				.findElements(By.xpath(".//*[@id='links-target']/div"));

		boolean reporterOutputPass = false;
		for (int j = 0; j < actualHeaderName.size(); j++) {
			String tempValue = actualHeaderName.get(j).getText().trim();
			WebElement secondLevelLink = actualHeaderName.get(j);
			String[] lines = tempValue.split("\n");
			String SubDepartment = lines[0];
			// System.out.println(SubDepartment);
			if (SubDepartment.equalsIgnoreCase(strSearchCriteria)) {
				List<WebElement> linksName = secondLevelLink.findElements(By.tagName("a"));
				int countn = 0;
				int countH = 0;

				for (WebElement linkValues : linksName) {

					String linkswithoutFolder = linkValues.getText();
					countofLinksforHeader = linksName.size();
				}
			}
		}
		return countofLinksforHeader;
	}

	public static boolean productcheckstatus(String product) {
		boolean checkboxstatus = false;
		WebElement oCheckBox = null;
		/*
		 * if(product.contains("JDPS Direct OEM")) { product= "JDPS"; }
		 */
		List<WebElement> productname = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label"));
		for (int i = 0; i < productname.size(); i++) {
			System.out.println(BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label")).get(i).getText());
			String productNameData = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label")).get(i).getText();
			oCheckBox = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='productSegmentsForm']/div/div/label"))
					.get(i);
			checkboxstatus = oCheckBox.isSelected();

			if (checkboxstatus == false && product.equalsIgnoreCase(productNameData)) {
				oCheckBox.click();
				checkboxstatus = true;
				break;
			}

			if (checkboxstatus) {
				break;
			}

		}
		return checkboxstatus;

	}

	public static boolean clickOnIndexPageLinkOnChildLevelDepartmentcheck(String childIndexPageTitle,
			String childLevelIndexPageCategories, String childLevelIndexPageNestedCategories) {
		boolean flagForIndex = false;
		try {
			if (childLevelIndexPageCategories.equalsIgnoreCase("NA")
					&& childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
				WebElement linkFrame = BaseClass.wbDriver.findElement(By.id("content"));
				List<WebElement> links = linkFrame.findElements(By.tagName("a"));
				for (int i = 0; i < links.size(); i++) {
					if (links.get(i).getText().equals(childIndexPageTitle)) {
						// links.get(i).click();
						LogFactory.info("Navigated to index page successfully 4");
						return true;
					}
				}
			} else if (!childLevelIndexPageCategories.equalsIgnoreCase("NA")
					&& !childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
				int sizeOfFrames = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div"))
						.size();
				for (int i = 1; i <= sizeOfFrames; i++) {

					String nameOfSubCategory = BaseClass.wbDriver
							.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]/div/h4")).getText()
							.toString().trim();
					if (nameOfSubCategory.equalsIgnoreCase(childLevelIndexPageNestedCategories)) {
						WebElement givenFrame = BaseClass.wbDriver
								.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));
						List<WebElement> links = givenFrame.findElements(By.tagName("a"));
						for (int j = 0; j < links.size(); j++) {
							if (links.get(j).getText().equals(childIndexPageTitle)) {
								// links.get(j).click();
								LogFactory.info("Navigated to index page successfully 5");
								LogFactory.info("Navigated to index page successfully 5");
								return true;

							}
						}
					}
				}
			} else if (childLevelIndexPageCategories.equalsIgnoreCase("NA")
					&& !childLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
				int sizeOfFrames = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div"))
						.size();
				for (int i = 1; i <= sizeOfFrames; i++) {
					String nameOfCategory = BaseClass.wbDriver
							.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/h3[" + i + "]")).getText()
							.toString().trim();
					if (nameOfCategory.equalsIgnoreCase(childLevelIndexPageCategories)) {
						WebElement givenFrame = BaseClass.wbDriver
								.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));
						List<WebElement> links = givenFrame.findElements(By.tagName("a"));
						for (int j = 0; j < links.size(); j++) {
							if (links.get(j).getText().equals(childIndexPageTitle)) {
								links.get(j).click();
								LogFactory.info("Navigated to index page successfully 6");
								return true;
							}
						}
					}
				}
			} else {
				LogFactory.info("Invalid test data provided.");
				return false;
			}
		} catch (Exception e) {
			LogFactory.info("This is catch statement");
		}
		return flagForIndex;
	}

	public static void toClickonDept(String deptName) {
		try {

			// To clik on 'My DealerPath' arrow
			WebElement arrowClick = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='left_nav_0']"));

			// Mouse hover Actions
			Actions obj = new Actions(BaseClass.wbDriver);
			obj.moveToElement(arrowClick).build().perform();

			// To get list of all departments
			List<WebElement> deptNameList = BaseClass.wbDriver.findElements(By.xpath(".//*[@class='flyout']/li/a"));
			for (int i = 1; i < deptNameList.size(); i++) {
				String deptNameListValue = deptNameList.get(i).getText().toString().trim();
				//// System.out.println(deptNameListValue);
				LogFactory.info("dept Name List Values= " + deptNameListValue);

				// To click on desire department name given
				if (deptNameListValue.contains(deptName.trim())) {
					deptNameList.get(i).click();
					LogFactory.info("Clicked on desired deparment name. " + deptName);
					break;
				}
			}
		} catch (Exception e) {
			LogFactory.info("Click on the department. " + e);
		}
	}

	public static void checkUncheckDepartmentMyPreferenceIndex(String departmentName) throws InterruptedException {
		try {
			GenericFactory.utilityMenuMyPreferenceClick();
			Thread.sleep(5000);
			for (int i = 0; i < announcementFactory.allCheck.size(); i++) {
				if (!(announcementFactory.allCheck.get(i).getAttribute("checked") == null)
						&& departmentName.contains(announcementFactory.allCheck1.get(i).getText())) {
					announcementFactory.allCheck.get(i).click();

				}
			}
			Thread.sleep(5000);
			BaseClass.wbDriver.findElement(By.xpath("//*[@id='preference-save']")).click();
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void checkandUncheckFromPrefernce() throws Throwable {
		GenericFactory.utilityMenuMyPreferenceClick();
		List<WebElement> names = BaseClass.wbDriver
				.findElements(By.xpath("//div[@class='group-value checkbox-value']/div[@class='value']"));
		Thread.sleep(5000);
		WebElement selectedElement = BaseClass.wbDriver
				.findElement(By.xpath("//*[@class='active  selected leftNavDeptPadding']"));
		String str = selectedElement.getText().toString();

		for (int k = 0; k < names.size(); k++) {
			if (names.get(k).getText().equalsIgnoreCase(str)) {
				List<WebElement> allCheckboxes = BaseClass.wbDriver.findElements(By.xpath(
						"//*[@class='group-value checkbox-value']//label[@class='click-target-only']//input[@type='checkbox']"));
				if (!(allCheckboxes.get(k).getAttribute("checked") == null)) {
					allCheckboxes.get(k).click();
				}
				break;
			}
		}
		Thread.sleep(3000);
		BaseClass.wbDriver.findElement(By.xpath("//*[@id='preference-save']")).click();
		Thread.sleep(5000);
	}

	public static void verifyimageActive(WebElement imgElement) {
		try {

			int invalidImageCount = 0;
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200) {
				invalidImageCount++;
			}
			System.out.println(invalidImageCount);
		} catch (Exception e) {

		}
	}

	public static boolean linkFolderCorrect(String folderName, String Title) throws InterruptedException {
		String linksavailable = null;
		boolean status = false;
		List<WebElement> actualHeadernameP = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='links-target']/div"));

		for (int s = 0; s < actualHeadernameP.size(); s++) {
			String headerNamePortlet = actualHeadernameP.get(s).getText().trim();
			@SuppressWarnings("unused")
			String[] linesdata = headerNamePortlet.split("\n");
			WebElement secondLevel = actualHeadernameP.get(s);

			List<WebElement> links = secondLevel.findElements(By.tagName("a"));

			for (WebElement we : links) {

				we.getText();
				Thread.sleep(1000);
				if (we.getText().toString().equalsIgnoreCase(Title)) {
					String result = "Correct Link under folder";
					status = true;
					break;

				} else {
					String result = "Links are not present under folder";
					status = false;

				}
			}
			if (status) {
				break;
			}
		}
		return status;
	}

	public static boolean countryListComparison(String UserCountry, String wcmCountry) {

		boolean flag = false;

		List<String> wcmCountryList = Arrays.asList(wcmCountry.split("(,)"));
		// System.out.println(wcmCountryList);

		List<String> userCountryList = Arrays.asList(UserCountry.split("(,)"));
		// System.out.println(userCountryList);

		try {

			for (int i = 0; i < wcmCountryList.size(); i++) {
				for (int j = 0; j < userCountryList.size(); j++) {

					// System.out.println(wcmCountryList.get(j).concat("/")+"::"+userCountryList.get(i).split("/").trim());
					if (!wcmCountryList.get(j).contains("/")) {
						String arr[] = userCountryList.get(j).split("/");
						if (wcmCountryList.get(i).contains(arr[0])) {
							flag = true;
							LogFactory.info("User country contains MRU country");

						} else {

							LogFactory.info("User country doesnot contain WCM country");

						}
					} else if (!userCountryList.get(j).contains("/")) {
						if (wcmCountryList.get(i).contains("/")) {
							flag = false;
							LogFactory.info("User country doesnot contains MRU country");
						}
					}

					else {
						System.out.println(wcmCountryList.get(i) + "::" + userCountryList.get(j));
						if (wcmCountryList.get(i).contains(userCountryList.get(j).trim())) {
							flag = true;
							LogFactory.info("User country contains WCM country");

						} else {

							LogFactory.info("User country doesnot contain WCM country");

						}

					}
				}
			}
		} catch (Exception e) {
			LogFactory.info("Exception message :-->" + e.getMessage());
			flag = false;
		}
		return flag;
	}

	public static boolean productlistcomparer(String userDefinedProducts, String wcmProducts) {

		boolean flag = false;
		try {

			List<String> userDefinedProductsList = Arrays.asList(userDefinedProducts.split("\\s*,\\s*"));
			List<String> wcmProductsList = Arrays.asList(wcmProducts.split("\\s*,\\s*"));

			for (int i = 0; i < userDefinedProductsList.size(); i++) {

				String userDefinedProductsvalue = userDefinedProductsList.get(i);

				for (int j = 0; j < wcmProductsList.size(); j++) {

					String wcmProductsvalue = wcmProductsList.get(j);

					if (wcmProductsvalue.contains(userDefinedProductsvalue.toString())
							|| userDefinedProductsvalue.contains(wcmProductsvalue.toString())) {
						flag = true;
						System.out.println(wcmProductsvalue + " Is Same as " + userDefinedProductsvalue);
						LogFactory.info("Product details matched Successfully.");
						break;

					}

				}

			}
			return flag;

		}

		catch (Exception e) {
			flag = false;
			LogFactory.info("Fail");

		}
		return flag;

	}

	public static boolean verifyRacfGroupMatched(String strExcelData) throws Throwable {
		if (!strExcelData.equalsIgnoreCase("NA") && !strExcelData.equalsIgnoreCase("")) {
			List<String> listOfRacfGroupFromWcmContent = GenericFactory.splitString(strExcelData, ",");
			List<String> actualDataListOriginal = BaseClass.analyzerUserMap.get("Security Groups");
			for (int i = 0; i < actualDataListOriginal.size(); i++) {
				if (listOfRacfGroupFromWcmContent.contains(actualDataListOriginal.get(i)))
					return true;
			}
			return false;
		}
		return true;
	}

	public static boolean linkspresent(String Folder) {
		List<WebElement> actualheadername1 = PortletLinksPage_POF.PortletDriver.findElements(
				By.xpath("//*[@id='links-target']//div[@class='link-group']//div[@class='link-item child wrap ']//a"));
		boolean linkspresent = false;

		for (int j = 0; j < actualheadername1.size(); j++) {

			if (actualheadername1.get(j).isDisplayed()) {
				linkspresent = true;

				break;
			}
		}
		return linkspresent;
	}

	public static List<String> getCheckBoxValuesCountry() throws Throwable {
		List<String> listOfElementscountry = new ArrayList<String>();
		List<WebElement> getWebElementsLinks = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='country-grouping-form']/div/div/div/label"));
		for (int i = 0; i < getWebElementsLinks.size(); i++) {
			listOfElementscountry.add(getWebElementsLinks.get(i).getText());
		}
		return listOfElementscountry;
	}

	public static boolean uncheckCountry(String wcmCountrynameWcm) throws IOException, Exception {
		boolean checkboxstatus = true;
		WebElement oCheckBox = null;
		List<WebElement> countyNameStatusofCheckBox = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='countryGroups']"));
		List<WebElement> countyName = BaseClass.wbDriver
				.findElements(By.xpath(".//*[@id='country-grouping-form']/div/div/div/label"));
		for (int i = 1; i <= countyName.size(); i++) {
			WebElement oCheckBoxstatusOfCheckBox = countyNameStatusofCheckBox.get(i - 1);
			checkboxstatus = oCheckBoxstatusOfCheckBox.isSelected();

			oCheckBox = BaseClass.wbDriver
					.findElement(By.xpath(".//*[@id='country-grouping-form']/div/div/div[" + i + "]/label"));
			String oCheckBox1 = BaseClass.wbDriver
					.findElement(By.xpath(".//*[@id='country-grouping-form']/div/div/div[" + i + "]/label")).getText();
			if (checkboxstatus == true && oCheckBox1.equalsIgnoreCase(wcmCountrynameWcm)) {
				oCheckBox.click();
				checkboxstatus = true;
				break;
			}
		}
		return checkboxstatus;

	}

	public static boolean checkAllCountryData() throws IOException, Exception {
		boolean checkboxstatus = true;
		WebElement oCheckBox = null;
		ValidationFactory.getElementIfPresent(By.id("js-country-group")).click();
		List<WebElement> countyName = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='countryGroups']"));
		for (int i = 0; i < countyName.size(); i++) {
			oCheckBox = countyName.get(i);
			checkboxstatus = oCheckBox.isSelected();
			if (checkboxstatus == false) {
				oCheckBox.click();
				checkboxstatus = true;
			}
		}
		return checkboxstatus;
	}
	
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
	
	
	public static void resetMyPreferenceSettings() throws Throwable {
        String LanguageReset = "";
        String ThemeReset = "";
        String DeptDifferent = "";
        String LanguageNotPresent = "",themeNotPresent = "",siteNotPresent = "";
        
        try {
               LogFactory.info(
                            "Reset the settings for the user : " + strUserRACFID + " on My Preferences page is initiated.");
               
               myPreferencesPageFactory.navigateToMyPreferences();
               LogFactory.info("Navigated to 'My Preference' Modal window.");
               String langList = "";
               // Language Reset
               LogFactory.info("Resetting the value for Language Drop down.");
               if (ValidationFactory.isElementPresent(myPreferencesPageFactory.wbelMyPreferenceModalLanguageDDL)) {
                     String LanguagecodeReset = analyzerUserMapReset.get("Language").get(0);
                     LanguageReset = myPreferencesPageFactory.selectLanguagebyCodeMap(LanguagecodeReset);
                     Select select = new Select(myPreferencesPageFactory.wbelMyPreferenceModalLanguageDDL);
                     List<WebElement> listOfSites=select.getOptions();
                     for(int i = 0;i<listOfSites.size();i++) {
                            langList = langList + listOfSites.get(i).getText();    
                     }
                     if(langList.contains(LanguageReset)) {
                     select.selectByVisibleText(LanguageReset);}
                     else {
                            LanguageNotPresent = "<b>Language "+LanguageReset+" is not present in the Language drop down</b>.";
                     }
                     LogFactory.info("Selected the Language : " + LanguageReset);
               } else {
                     LogFactory.info("Language Drop down is not available.");
               }
               // Theme Reset
               if (ValidationFactory.isElementEnabled(myPreferencesPageFactory.wbelMyPreferenceTheme.get(0))) {
                     LogFactory.info("Resetting the value for Theme.");
                     ThemeReset = analyzerUserMapReset.get("Theme Colors").get(0);
                     try{
                            WebElement webelThemeReset = wbDriver.findElement(By.xpath("//*[@type='radio'][@value='" + ThemeReset + "']"));
                            webelThemeReset.click();   
                     }
                     catch(Exception e) {
                            themeNotPresent = "<b>Theme "+ThemeReset+" is not present</b>.";
                     }
                     Thread.sleep(500);
               
                     LogFactory.info("Selected the Theme.");
               } else {
                     LogFactory.info("Theme is not available.");
               }

               // Department reset
               LogFactory.info("Resetting the values for Departments.");
               List<String> listApplication = new ArrayList<String>();

               int sizeOfDeptListFromApplication = myPreferencesPageFactory.wbelMyPreferenceModalDepartments.size();
               for (int i = 0; i < sizeOfDeptListFromApplication; i++) {
                     String Dep = myPreferencesPageFactory.wbelMyPreferenceModalDepartments.get(i).getText();
                     listApplication.add(Dep);
               }
               System.out.println(listApplication + " is the list of departments.");

               int sizeOfDeptListFromAnalyseUserMapReset = analyzerUserMapReset.get("Department").size();
               for (int i = 0; i < sizeOfDeptListFromAnalyseUserMapReset; i++) {
                     String departmentFromAnalyseUserMapReset = GenericFactory.getTranslation(analyzerUserMapReset.get("Department").get(i)).toString();
                      departmentFromAnalyseUserMapReset=departmentFromAnalyseUserMapReset.contains("[") ? departmentFromAnalyseUserMapReset.replaceAll("[\\[\\]]", "") : departmentFromAnalyseUserMapReset;
                     System.out.println(departmentFromAnalyseUserMapReset + " has to be checked.");
                     if (listApplication.contains(departmentFromAnalyseUserMapReset)) {
                            WebElement checkDepartment = wbDriver
                                          .findElement(By.xpath("//*[@id='rbacRole']/../div[normalize-space() = '"
                                                        + departmentFromAnalyseUserMapReset + "']/../input"));
                            if (!checkDepartment.getAttribute("checked").equalsIgnoreCase("true")) {
                                   checkDepartment.click();
                            } else {
                                   LogFactory.info(departmentFromAnalyseUserMapReset + " is checked present.");
                            }
                     } else {
                            DeptDifferent = DeptDifferent + departmentFromAnalyseUserMapReset;
                     }
               }

               String siteFromAnalyseUserMapReset = "";
               String siteList = "";
               // Site Reset
               LogFactory.info("Resetting the values for Site.");
               if (ValidationFactory.isElementPresent(myPreferencesPageFactory.wbelMyPreferencePreferredSite)) {
                     siteFromAnalyseUserMapReset = analyzerUserMapReset.get("strSite").get(0);
                     siteFromAnalyseUserMapReset = myPreferencesPageFactory.selectSitebyCodeMap(siteFromAnalyseUserMapReset);
                     System.out.println("*************************"+siteFromAnalyseUserMapReset);
                     Select select = new Select(myPreferencesPageFactory.wbelMyPreferencePreferredSite);
                     List<WebElement> listOfSites=select.getOptions();
                     for(int i = 0;i<listOfSites.size();i++) {
                            siteList = siteList + listOfSites.get(i).getText();    
                     }
                     
                     if(siteList.contains(siteFromAnalyseUserMapReset)) {
                            select.selectByVisibleText(siteFromAnalyseUserMapReset);
                            LogFactory.info("Selected the Site : " + siteFromAnalyseUserMapReset);
                     }else {
                            siteNotPresent = "<b>Site "+siteFromAnalyseUserMapReset+" is not present in the Prefered site drop down</b>.";
                     }
                     

               } else {
                     LogFactory.info("Site Dropdown is not available.");
               }
               // Saving Changes
               myPreferencesPageFactory.wbelMyPreferenceModalSaveButton.click();
        WaitFactory.waitforelementToinvisibile(myPreferencesPageFactory.wbelMyPreferenceModalSaveButton);
               wbDriver.switchTo().defaultContent();
               WaitFactory.waitForPageLoaded();
String strFlag = "Fail";
               // Reporter Out put
               if (DeptDifferent.equalsIgnoreCase("")&&LanguageNotPresent.equalsIgnoreCase("")&&themeNotPresent.equalsIgnoreCase("")&&siteNotPresent.equalsIgnoreCase("")) {
                     strFlag = "Pass";
                            ReportFactory.reporterOutput("Reset My Preferences",
                                          "Verify that changes made to 'My Preference' are reverted back.",
                                          "Selections on 'My Preferences' before making any changes. </br><b> Language </b>: "
                                                        + LanguageReset + ".</br><b> Theme </b>: "
                                                        + analyzerUserMapReset.get("Theme Colors").get(0) + ".</br><b> Departments </b>: "
                                                        + analyzerUserMapReset.get("Department")+".</br><b> Site </b>:"
                                                        + siteFromAnalyseUserMapReset,
                                          "Changes made to verify the functionalities should be reverted back.",
                                          "All the changes made to 'My Preferences' is reverted back.",strFlag);
                     } 
                else {
                     String print = "";
                     print = !DeptDifferent.equalsIgnoreCase("")?"</br> <b>Departments which are NOT present as per Analyze user</b> : "+DeptDifferent+"</br>":print;
                     print = !LanguageNotPresent.equalsIgnoreCase("")?"</br> <b>Language which is NOT present as per Analyze user</b> : "+LanguageNotPresent+"</br>":print;
                     print = !themeNotPresent.equalsIgnoreCase("")?"</br> <b>Theme which is NOT present as per Analyze user</b> : "+themeNotPresent+"</br>":print;
                     print = !siteNotPresent.equalsIgnoreCase("")?"</br> <b>Site which are NOT present as per Analyze user</b> : "+siteNotPresent+"</br>":print;
                            ReportFactory.reporterOutput("Reset My Preferences",
                                          "Verify that changes made to 'My Preference' are reverted back.",
                                          "Selections on 'My Preferences' before making any changes. </br><b> Language </b>: "
                                                        + LanguageReset + ".</br><b> Theme </b>: "
                                                        + analyzerUserMapReset.get("Theme Colors").get(0) + ".</br><b> Departments </b>: "
                                                        + analyzerUserMapReset.get("Department")
                                                        + ".</br><b> Site </b>:"
                                                                     + siteFromAnalyseUserMapReset+print,
                                          "Changes made to verify the functionalities should be reverted back.",
                                          "All the changes made to 'My Preferences' may not have reverted back.",strFlag);
                     } 
               

        } catch (Exception e) {
               LogFactory.info("I am in catch block");
               
        }
 }

    public static void createXML() throws Exception {
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("DealerPath Application Automation Test Suite");

        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("Dealer Path");
        xmlTest.setPreserveOrder("true");
        xmlTest.setThreadCount(1);

        List<XmlClass> list = new ArrayList<XmlClass>();
        for (int i = 0; i < classesExecution.size(); i++) {
               XmlClass publicTestClass = new XmlClass(Class.forName("com.deere.TestCasesFactory." + classesExecution.get(i)));
               list.add(publicTestClass);
        }
        XmlClass endEmpersonate = new XmlClass(com.deere.TestCasesFactory.EndImpersonate_TestCase.class);
        list.add(endEmpersonate);
        xmlTest.setXmlClasses(list);
        TestNG testng = new TestNG();
        List<XmlSuite> suite = new ArrayList<XmlSuite>();
        suite.add(xmlSuite);

        testng.setXmlSuites(suite);
        for (XmlSuite suitefor : suite) {
               createXmlFile(suitefor);
        }

 }

 public static void createXmlFile(XmlSuite mSuite) {
        FileWriter writer;
        try {
               writer = new FileWriter(new File(strWorkingDir + "\\TestNG_XML\\" + "WCMTestingWithAddtionalTestcases.xml"));
               writer.write(mSuite.toXml());
               writer.flush();
               writer.close();
               System.out.println(new File(strWorkingDir + "\\TestNG_XML\\" + "WCMTestingWithAddtionalTestcases.xml").getAbsolutePath());
        } catch (IOException e) {
               e.printStackTrace();
        }
 }

}
