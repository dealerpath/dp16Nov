package com.deere.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.ImpersonateUser;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;
import com.gargoylesoftware.htmlunit.javascript.host.media.AnalyserNode;

import net.bytebuddy.description.type.TypeDescription.Generic;

public class MyPreferences_POF extends BaseClass {
	static WebDriver MyPreferencesDriver;

	public MyPreferences_POF(WebDriver driver) {
		this.MyPreferencesDriver = driver;
	}

	@FindBy(how = How.XPATH, using = ".//*[@id='switch-footer']")
	public static WebElement wbelSwitchFooterLink;
	@FindBy(how = How.XPATH, using = "//span[@class='primary-caret spr']")
	static WebElement wbelMyPreferenceUtilityMenu;

	@FindBy(how = How.XPATH, using = "//a[@id='preferences']")
	static WebElement wbelMyPreference;

	@FindBy(how = How.ID, using = "myModalLabel")
	static WebElement wbelMyPreferenceModalHeader;

	@FindBy(how = How.CSS, using = "#my-preferences > div.modal-dialog > div.modal-content > div.modal-header > button.close")
	static WebElement wbelMyPreferenceModalXButton;
	@FindBy(how = How.ID, using = "preference-save")
	public
	static WebElement wbelMyPreferenceModalSaveButton;

	@FindBy(how = How.ID, using = "preference-cancel")
	public static WebElement wbelMyPreferenceModalCancelButton;

	@FindBy(how = How.ID, using = "lang")
	public
	static WebElement wbelMyPreferenceModalLanguageDDL;

	@FindBy(how = How.XPATH, using = "//*[@type='radio']")
	static List<WebElement> wbelMyPreferenceModalTheme;

	@FindBy(how = How.XPATH, using = "//*[@name='theme-color']")
	public
	static List<WebElement> wbelMyPreferenceTheme;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Black & Yellow")
	static WebElement wbelMyPreferenceModalThemeBAndY;

	@FindBy(how = How.XPATH, using = "//*[@id='rbacRole']/../div")
	public
	static List<WebElement> wbelMyPreferenceModalDepartments;

	@FindBy(how = How.ID, using = "site")
	public
	static WebElement wbelMyPreferencePreferredSite;

	@FindBy(how = How.XPATH, using = "//*[@class='section-title']")
	static WebElement wbelHomeFav;

	@FindBy(how = How.XPATH, using = "//div[@class='user-info' ]")
	static WebElement wbelUserInfo;

	@FindBy(how = How.XPATH, using = "//h1[@class='app-title']")
	static WebElement wbelTitleOfHomePage;

	@FindBy(how = How.XPATH, using = ".//*[@class='wpthemeFooter']")
	public static WebElement wbelHomePageFooterFramePath;

	@FindBy(how = How.XPATH, using = "//div[@id='js-segments']")
	static WebElement wbelproductsegmenticon;

	@FindBy(how = How.ID, using = "left_nav_0")
	static WebElement wbelLeftHeader;

	@FindBy(how = How.XPATH, using = "//*[@class='section-title announcement']")
	static WebElement wbelAnnouncementHeader;

	@FindBy(how = How.XPATH, using = "//*[@id='js-user-info']/span[1]")
	static WebElement wbelHeaderWelcome;

	@FindBy(how = How.XPATH, using = "//div[@class='section']//h3[@class='section-title']")
	static WebElement favouriteHeader;

	@FindBy(how = How.XPATH, using = "//*[@id='js-segments-popover']/div[2]/div[1]")
	static WebElement wbelProductHeader;

	@FindBy(how = How.ID, using = "leftNav")
	static WebElement wbelLeftWindowFrame;

	@FindBy(how = How.XPATH, using = "//*[@id='leftNav']/li/a[@class='active']")
	public static List<WebElement> ListAllActiveLinks;

	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='inactive']")
	public static List<WebElement> listAllInactiveLinks;

	@FindBy(how = How.XPATH, using = "(//div[@class=\"group-value checkbox-value\"])//div//div")

	public static List<WebElement> ListAllActiveLinksonmypreferences;

	@FindBy(how = How.XPATH, using = "//input[@id='rbacRole' and @checked='true']")
	public static List<WebElement> Mypreferencescheckbox;

	@FindBy(how = How.XPATH, using = "//div[@class='group-value checkbox-value']/div[@class='value']")
	public static List<WebElement> MypreferencescheckboxClass;

	@FindBy(how = How.XPATH, using = "//div[@class='group-value checkbox-value']/div[@class='value']//input")
	public static List<WebElement> MypreferencescheckboxType;

	@FindBy(how = How.XPATH, using = "//button[@id='preference-cancel']")
	public static WebElement MypreferencesCancel;

	@FindBy(how = How.XPATH, using = "//div[contains (@class,'user-info') and @id ='js-user-info']")
	public static WebElement utilityMenu;

	@FindBy(how = How.XPATH, using = "//*[@id='js-user-info-pop']/ul/li[4]/a")
	public static WebElement utilityAdmin;

	@FindBy(how = How.ID, using = "Analyze_User")
	static WebElement wbelAnalyzeUser;

	@FindBy(how = How.ID, using = "analyseUserId")
	static WebElement wbelAnalyzeUserID;

	@FindBy(how = How.ID, using = "analyzeUserButton")
	static WebElement wbelAnalyzeButton;

	@FindBy(how = How.ID, using = "analyseUserForm")
	static WebElement wbelAnalyzeForm;

	@FindBy(how = How.XPATH, using = "//*[@id='analyseUserForm']/table[2]/tbody/tr[7]/td[2]")
	public static WebElement analyzeDepts;

	@FindBy(how = How.XPATH, using = "//div[@class='value']//select[@id='lang']")
	public static WebElement language;

	@FindBy(how = How.XPATH, using = "//*[@id='leftNav']/li/a[@class='inactive']")
	public static List<WebElement> ListAllInActiveLinks;

	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @checked='true']")
	static List<WebElement> checkedValues;

	@FindBy(how = How.XPATH, using = "//div[@class='group-value checkbox-value']/div[@class='value']")
	static List<WebElement> allCheck;

	@FindBy(how = How.XPATH, using = "//*[@class='active  selected leftNavDeptPadding']")
	public static WebElement strLeftDept;
	@FindBy(how = How.XPATH, using = "//div[@class='section-header']//h3")
	static List<WebElement> wbelSectionHeader;
	static String strPInputValue = null;
	@FindBy(how = How.ID, using = "left_nav_0")
	static WebElement homepageDealerPath;

	@FindBy(how = How.XPATH, using = "//*[@id='site']/option[1]")
	static WebElement strOptionSelected;

	@FindBy(how = How.XPATH, using = "//div[@class='group-value checkbox-value']/div[@class='value']//input")
	static List<WebElement> Userelement;

	@FindBy(how = How.XPATH, using = "//*[@class='group-value checkbox-value']//label[@class='click-target-only']//input[@type='checkbox']")
	static List<WebElement> checkBoxList;

	@FindBy(how = How.XPATH, using = "//*[@id='site']/option[1]")
	static WebElement strSiteOption;

	@FindBy(how = How.XPATH, using = "//input[@class='filter-box fav-filter']")
	static WebElement strfilterboxfavfilter;

	@FindBy(how = How.XPATH, using = "//*[@id='main-header']/div[1]//h1")
	static WebElement strHomeDealer;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[4]/section/div/div[2]/div[1]/h3")
	static WebElement wbelHeaderTitleFav;

	@FindBy(how = How.XPATH, using = "//input[@id='rbacRole' and @checked='true']/../div")
	static List<WebElement> checkedDeptList;

	@FindBy(how = How.XPATH, using = "//*[@id='main-header']/div[1]/div/h1")
	static WebElement navigateToHomepage;

	/**
     * @author      Bhavika Solanki
     * 
     * Resetting the settings for the user from 'My Preferences' modal window.
     * 
	 * @throws               Throwable 
     * @exception			 Exception
     */
	
	public static void resetMyPreferenceSettings() throws Throwable {
		String LanguageReset = "";
		String ThemeReset = "";
		String DeptDifferent = "";
		String LanguageNotPresent = "", themeNotPresent = "", siteNotPresent = "";

		try {
			LogFactory.info(
					"Reset the settings for the user : " + strUserRACFID + " on My Preferences page is initiated.");
			// Thread.sleep(500);
			LogFactory.info("Navigated to 'My Preference' Modal window.");
			navigateToMyPreferences();
			Thread.sleep(500);

			String langList = "";
			// Language Reset
			LogFactory.info("Resetting the value for Language Drop down.");
			if (ValidationFactory.isElementPresent(wbelMyPreferenceModalLanguageDDL)) {
				String LanguagecodeReset = analyzerUserMapReset.get("Language").get(0);
				LanguageReset = selectLanguagebyCodeMap(LanguagecodeReset);
				Select select = new Select(wbelMyPreferenceModalLanguageDDL);
				List<WebElement> listOfSites = select.getOptions();
				for (int i = 0; i < listOfSites.size(); i++) {
					langList = langList + listOfSites.get(i).getText();
				}
				if (langList.contains(LanguageReset)) {
					select.selectByVisibleText(LanguageReset);
				} else {
					LanguageNotPresent = "<b>Language " + LanguageReset
							+ " is not present in the Language drop down</b>.";
				}
				LogFactory.info("Selected the Language : " + LanguageReset);
			} else {
				LogFactory.info("Language Drop down is not available.");
			}
			// Theme Reset
			if (ValidationFactory.isElementEnabled(wbelMyPreferenceTheme.get(0))) {
				LogFactory.info("Resetting the value for Theme.");
				ThemeReset = analyzerUserMapReset.get("Theme Colors").get(0);
				try {
					WebElement webelThemeReset = MyPreferencesDriver

							.findElement(By.xpath("//*[@type='radio'][@value='" + ThemeReset + "']"));
					webelThemeReset.click();
				} catch (Exception e) {
					themeNotPresent = "<b>Theme " + ThemeReset + " is not present</b>.";
				}
				// Thread.sleep(500);

				LogFactory.info("Selected the Theme.");
			} else {
				LogFactory.info("Theme is not available.");
			}

			// Department reset
			LogFactory.info("Resetting the values for Departments.");
			List<String> listApplication = new ArrayList<String>();

			int sizeOfDeptListFromApplication = wbelMyPreferenceModalDepartments.size();
			for (int i = 0; i < sizeOfDeptListFromApplication; i++) {
				String Dep = wbelMyPreferenceModalDepartments.get(i).getText();
				listApplication.add(Dep);
			}
			System.out.println(listApplication + " is the list of departments.");

			int sizeOfDeptListFromAnalyseUserMapReset = analyzerUserMapReset.get("Department").size();
			for (int i = 0; i < sizeOfDeptListFromAnalyseUserMapReset; i++) {
				String departmentFromAnalyseUserMapReset = GenericFactory
						.getTranslation(analyzerUserMapReset.get("Department").get(i)).toString();
				departmentFromAnalyseUserMapReset = departmentFromAnalyseUserMapReset.contains("[")
						? departmentFromAnalyseUserMapReset.replaceAll("[\\[\\]]", "")
						: departmentFromAnalyseUserMapReset;
				System.out.println(departmentFromAnalyseUserMapReset + " has to be checked.");
				if (listApplication.contains(departmentFromAnalyseUserMapReset)) {
					WebElement checkDepartment = MyPreferencesDriver
							.findElement(By.xpath("//*[@id='rbacRole']/../div[normalize-space() = '"
									+ departmentFromAnalyseUserMapReset + "']/../input"));
					if (!checkDepartment.getAttribute("checked").equalsIgnoreCase("true")) {
						checkDepartment.click();
					} else {
						LogFactory.info(departmentFromAnalyseUserMapReset + " is checked.");
					}
				} else {
					DeptDifferent = DeptDifferent + departmentFromAnalyseUserMapReset;
				}
			}

			String siteFromAnalyseUserMapReset = "";
			String siteList = "";
			// Site Reset
			LogFactory.info("Resetting the values for Site.");
			if (ValidationFactory.isElementPresent(wbelMyPreferencePreferredSite)) {
				siteFromAnalyseUserMapReset = analyzerUserMapReset.get("strSite").get(0);
				siteFromAnalyseUserMapReset = selectSitebyCodeMap(siteFromAnalyseUserMapReset);
				System.out.println("*************************" + siteFromAnalyseUserMapReset);
				Select select = new Select(wbelMyPreferencePreferredSite);
				List<WebElement> listOfSites = select.getOptions();
				for (int i = 0; i < listOfSites.size(); i++) {
					siteList = siteList + listOfSites.get(i).getText();
				}

				if (siteList.contains(siteFromAnalyseUserMapReset)) {
					select.selectByVisibleText(siteFromAnalyseUserMapReset);
					LogFactory.info("Selected the Site : " + siteFromAnalyseUserMapReset);
				} else {
					siteNotPresent = "<b>Site " + siteFromAnalyseUserMapReset
							+ " is not present in the Prefered site drop down</b>.";
				}

			} else {
				LogFactory.info("Site Dropdown is not available.");
			}

			// Product reset : Yet to be finalled.

			// Saving Changes
			wbelMyPreferenceModalSaveButton.click();
			WaitFactory.waitforelementToinvisibile(wbelMyPreferenceModalSaveButton);
			MyPreferencesDriver.switchTo().defaultContent();
			WaitFactory.waitForPageLoaded();
			String strFlag = "Fail";
			// Reporter Out put
			if (DeptDifferent.equalsIgnoreCase("") && LanguageNotPresent.equalsIgnoreCase("")
					&& themeNotPresent.equalsIgnoreCase("") && siteNotPresent.equalsIgnoreCase("")) {
				strFlag = "Pass";
				ReportFactory.reporterOutput("Reset My Preferences",
						"Verify that changes made to 'My Preference' are reverted back.",
						"Selections on 'My Preferences' before making any changes. </br><b> Language </b>: "
								+ LanguageReset + ".</br><b> Theme </b>: "
								+ analyzerUserMapReset.get("Theme Colors").get(0) + ".</br><b> Departments </b>: "
								+ analyzerUserMapReset.get("Department") + ".</br><b> Site </b>:"
								+ siteFromAnalyseUserMapReset,
						"Changes made to verify the functionalities should be reverted back.",
						"All the changes made to 'My Preferences' is reverted back.", strFlag);
			} else {
				String print = "";
				print = !DeptDifferent.equalsIgnoreCase("")
						? "</br> <b>Departments which are NOT present as per Analyze user</b> : " + DeptDifferent
								+ "</br>"
						: print;
				print = !LanguageNotPresent.equalsIgnoreCase("")
						? "</br> <b>Language which is NOT present as per Analyze user</b> : " + LanguageNotPresent
								+ "</br>"
						: print;
				print = !themeNotPresent.equalsIgnoreCase("")
						? "</br> <b>Theme which is NOT present as per Analyze user</b> : " + themeNotPresent + "</br>"
						: print;
				print = !siteNotPresent.equalsIgnoreCase("")
						? "</br> <b>Site which are NOT present as per Analyze user</b> : " + siteNotPresent + "</br>"
						: print;
				ReportFactory.reporterOutput("Reset My Preferences",
						"Verify that changes made to 'My Preference' are reverted back.",
						"Selections on 'My Preferences' before making any changes. </br><b> Language </b>: "
								+ LanguageReset + ".</br><b> Theme </b>: "
								+ analyzerUserMapReset.get("Theme Colors").get(0) + ".</br><b> Departments </b>: "
								+ analyzerUserMapReset.get("Department") + ".</br><b> Site </b>:"
								+ siteFromAnalyseUserMapReset + print,
						"Changes made to verify the functionalities should be reverted back.",
						"All the changes made to 'My Preferences' may not have reverted back.", strFlag);
			}

		} catch (Exception e) {
			LogFactory.info("catch block : resetMyPreferenceSettings");

		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that My Preferences is displayed in the utility links.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
     * @param strTestData    Data that is captured for the strTCID from test data.
     * @param utilityOption  Option that has to be clicked from the Utility Layout.
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	public static void compareUtilityOptionWithTestData(String strTCID, String strTestData)
			throws Throwable {
		String strFlag = "Fail";
		try {
			
			System.out.println("My Preferences starting>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("analyzer User Map Reset>>>>>>>>>>>>>>>"+analyzerUserMapReset);
			System.out.println("analyzer User Map>>>>>>>>>>>>>>>"+analyzerUserMap);

			System.out.println("Clicking on Utility menu.");
			WaitFactory.WaitForElementToVisible(wbelMyPreferenceUtilityMenu);
			wbelMyPreferenceUtilityMenu.click();
			System.out.println("Clicked Utility menu.");
			WaitFactory.FluentWaitByWebElement(wbelMyPreference);
			if (ValidationFactory.isElementPresent(wbelMyPreference)) {
				String ExpectedData = strTestData;
				List<String> header1 = GenericFactory.getTranslation(strTestData);

				ExpectedData = header1.get(0);
				String ActualData = wbelMyPreference.getText();
				if (ExpectedData.equals(ActualData)) {
					System.out.println("Actual and expected data is matching.");
					wbelMyPreferenceUtilityMenu.click();
					navigateToMyPreferences();
					strFlag = "Pass";
				}
				ReportFactory.reporterOutput(strTCID, "Verify " + strTestData + " link exists under Utility Links. ",
						strTestData, strTestData + " link should exists under Utility Links menu. ",
						strTestData + " link is present under Utility Links menu", strFlag);
			} else {
				ReportFactory.reporterOutput(strTCID, "Verify " + strTestData + " link exists under Utility Links.",
						strTestData, strTestData + " link should exists under Utility Links menu. ",
						strTestData + " link is Not present under Utility Links menu.", strFlag);
			}
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Verify " + strTestData + " link exists under Utility Links. ",
					strTestData, strTestData + " link should exists under Utility Links. ",
					strTestData + " link is present under Utility Links menu.Catch block", strFlag);
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that 'My Preferences' modal window is getting displayed on clicking on Utility Menu > My Preferences.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
     * @param strTestData    Data that is captured for the strTCID from test data.
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	public static void myPreferenceWinVerification(String strTCID, String strTestData) throws Throwable {
		String strFlag = "Fail";
		String header;
		String result = "My Preference Header is NOT getting displayed";
		String ExpectedData = "", ActualData = "";
		try {
			List<String> header1 = GenericFactory.getTranslation(strTestData);

			// My Preference will be already open with the control on My Preference
			WaitFactory.WaitForElementToVisible(wbelMyPreferenceModalHeader);
			System.out.println("Getting My Preference header text.");
			String headerText = wbelMyPreferenceModalHeader.getText();
			if (ValidationFactory.isElementPresent(wbelMyPreferenceModalHeader)) {
				System.out.println("Header is displayed.");
				ExpectedData = header1.get(0);
				ActualData = headerText;
				System.out.println("Expected data : " + ExpectedData + "Actual data : " + ActualData);
				if (ExpectedData.equals(ActualData)) {
					strFlag = "Pass";
					result = "Header " + ExpectedData + " text is displayed ";
					System.out.println("Expected data and Actual data is matching.");
				}
				ReportFactory.reporterOutputMerge(strTCID, "Verify '" + ExpectedData + "' header text is displayed ",
						strTestData, "'" + headerText + "' header text should be displayed. Expected Header : "
								+ ExpectedData + ".Actual Header : " + ActualData,
						result, strFlag, true, 8);
				navigateToHomepage();
				System.out.println("Navigated to Home page successfully.");
			} else {
				ReportFactory.reporterOutputMerge(strTCID, "Verify '" + ExpectedData + "' header text is displayed ",
						strTestData, headerText + " header text should be displayed ", result, strFlag, true, 8);
				navigateToHomepage();
				System.out.println("Navigated to Home page successfully.");
			}
		} catch (Exception e) {
			ReportFactory.reporterOutputMerge(strTCID, "Verify " + strTestData + " link exists in utility links ",
					"Verify " + strTestData + " link exists in utility links ", "NA", e.getMessage().toString(),
					strFlag, true, 8);
			navigateToHomepage();
			System.out.println("Navigated to Home page successfully from catch block.");
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify Save, Cancel and X button are displayed on My Preference modal window.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	public static void myPreferenceVerification(String strTCID) throws Throwable {
		String strFlag = "Fail";
		String result = "'X' icon is NOT available in 'My Preferences' modal window ";
		try {
			navigateToMyPreferences();
			System.out.println("Navigated to My Preferences.");
			if (wbelMyPreferenceModalXButton.isDisplayed()) {
				strFlag = "Pass";
				result = "'X' icon is available in 'My Preferences' modal window ";
				System.out.println("'X' button is available.");

				ReportFactory.reporterOutputMerge(strTCID,
						"Verify 'X' icon is available in 'My Preferences' modal window", "NA ",
						"'X' icon  should be available in 'My Preferences' modal window", result, strFlag, false, 8);
			} else {
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify 'X' icon is available in 'My Preferences' modal window", "NA ",
						"'X' icon  should be available in 'My Preferences' modal window", result, strFlag, false, 8);
			}
			navigateToHomepage();
			System.out.println("Navigated to Home page.");
		} catch (Exception e) {
			ReportFactory.reporterOutputMerge(strTCID + " : X button",
					"Verify 'X' icon is available in 'My Preferences' modal window", "NA ",
					"'X' icon  should be available in 'My Preferences' modal window", "X button is not displayed.",
					strFlag, false, 8);
			navigateToHomepage();
			System.out.println("Navigated to Home page from Catch block.");
		}
		try {
			navigateToMyPreferences();
			System.out.println("Navigated to My Preferences.");
			result = "'Save' button is available in 'My Preferences' modal window";
			if (wbelMyPreferenceModalSaveButton.isDisplayed()) {
				strFlag = "Pass";
				result = "'Save' button is available in 'My Preferences' modal window";
				System.out.println("'Save' button is available.");
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify 'Save' button is available in 'My Preferences' modal window", "NA ",
						"Verify 'Save' button should be available in 'My Preferences' modal window ", result, strFlag,
						false, 8);
			} else {
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify 'Save' button is available in 'My Preferences' modal window", "NA ",
						"Verify 'Save' button should be available in 'My Preferences' modal window ", result, strFlag,
						false, 8);
			}
			navigateToHomepage();
			System.out.println("Navigated to Home page.");
		} catch (Exception e) {
			ReportFactory.reporterOutputMerge(strTCID,
					"Verify 'Save' button is available in 'My Preferences' modal window", "NA ",
					"Verify 'Save' button should be available in 'My Preferences' modal window ", result, strFlag,
					false, 8);
			navigateToHomepage();
			System.out.println("Navigated to Home page from catch block.");
		}
		try {
			navigateToMyPreferences();
			System.out.println("Navigated to My Preferences.");
			result = "'Cancel' button is NOT available in 'My Preferences' modal window";
			if (wbelMyPreferenceModalCancelButton.isDisplayed()) {
				strFlag = "Pass";
				result = "'Cancel' button is available in 'My Preferences' modal window";
				System.out.println("'Cancel' button is available.");
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify 'Cancel' button is available in 'My Preferences' modal window", "NA ",
						"Verify 'Cancel' button should be available in 'My Preferences' modal window ", result, strFlag,
						false, 8);
			} else {
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify 'Cancel' button is available in 'My Preferences' modal window", "NA ",
						"Verify 'Cancel' button should be available in 'My Preferences' modal window ", result, strFlag,
						false, 8);
			}
			navigateToHomepage();
			System.out.println("Navigated to Home page.");
		} catch (Exception e) {
			ReportFactory.reporterOutputMerge(strTCID,
					"Verify 'Cancel' button is available in 'My Preferences' modal window", "NA ",
					"Verify 'Cancel' button should be available in 'My Preferences' modal window ", result, strFlag,
					false, 8);
			navigateToHomepage();
			System.out.println("Navigated to Home page from catch block.");
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify Departments are displayed on My Preference modal window.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
     * @param strTestData    List of departments mentioned in the test data
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	@SuppressWarnings("unlikely-arg-type")
	public static void myPreferenceDeptVerification(String strTCID, String strTestData) throws Throwable {
		String strFlag = "Fail";
		String result = "All the expected departments are NOT displayed in the department section of the My Preference modal window";
		String listFromMypreferences = "";
		String keythatDintMatch = "", strFlagFalse = "Pass";
		List<String> translationdept = new ArrayList<String>();
		List<String> listExpectedData = new ArrayList<String>();
		List<String> translationdept1 = new ArrayList<String>();
		List<String> listExpectedData1 = new ArrayList<String>();
		List<String> strDept = new ArrayList<String>();
		List<String> strLang = new ArrayList<String>();
		String dept = "", translationdeptStr = "", listExpectedDatastr = "";
		try {

			navigateToMyPreferences();
			System.out.println("Navigated to My Preferences.");
			strDept = analyzerUserMap.get("Department");
			strLang = analyzerUserMap.get("Language");
			String test = strDept.toString();
			test = test.contains("[") ? test.replaceAll("[\\[\\]]", "") : test;
			System.out.println(test);
			translationdept = GenericFactory.getTranslation(test);

			// List of checked dept on my preference
			for (int i = 0; i < checkedDeptList.size(); i++) {
				String Dept = checkedDeptList.get(i).getText();
				listExpectedData.add(Dept);
			}
			translationdeptStr = translationdept.toString();
			listExpectedDatastr = listExpectedData.toString();
			translationdeptStr = translationdeptStr.replaceAll(" ", "");
			listExpectedDatastr = listExpectedDatastr.replaceAll(" ", "");
			// donot remove this.It is specific for some languages.If removed will cause
			// failure.
			translationdeptStr = translationdeptStr.replaceAll("Â ", "");
			listExpectedDatastr = listExpectedDatastr.replaceAll("Â ", "");
			if (translationdeptStr.contains(listExpectedDatastr)) {
				strFlag = "Pass";
				result = "All the expected departments are displayed in the department section of the My Preference modal  window.Translated List : "
						+ translationdept;
			} else {
				strFlagFalse = "Fail";
				result = "All the expected departments are NOT displayed in the department section of the My Preference modal  window.Translated List : "
						+ translationdept;
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify the departments available in the department section of the modal window",
					"Departments displaying to user are based Prefered Depertments retrieved from 'Analyze User' : "
							+ strDept,
					"All accessible departments should be displayed in the department section of the My Preference modal  window.List of department on my preference modal window : </br>"
							+ listExpectedData,
					result, strFlag);
			navigateToHomepage();
			System.out.println("Navigated to My Home Page.");
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify the departments available in the department section of the modal window",
					"Departments displaying to user are based Prefered Depertments retrieved from 'Analyze User' : "
							+ strDept,
					"All accessible departments should be displayed in the department section of the My Preference modal  window after  analyzing the user"
							+ listExpectedData,
					e.getMessage().toString(), strFlag);
			navigateToHomepage();
			System.out.println("Navigated to My Home Page from catch block.");
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that Theme is displayed in the My Preference modal window from Home page and Department page.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
     * @param isIndex        isIndex flag signifies whether the test script is to be executed on Index page.
     * @param onDept         onDept flag signifies whether the test script is to be executed on Department. 
     * @param merger         merger flag signifies the parameter for reporting purpose. 
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	public static void myPreferenceThemeVerification(String strTCID, boolean isIndex, boolean onDept, boolean merger)
			throws Throwable {
		String strFlag = "Fail";
		String result = "Theme for exists in My Preference Modal Window should be present. ";
		String value = "";
		try {
			if (onDept && (isIndex == false)) {
				if (ListAllActiveLinks.get(0).isEnabled()) {
					ListAllActiveLinks.get(0).click();
					Thread.sleep(1000);
					WaitFactory.waitForPageLoaded();
					System.out.println("Navigated to first active department");
					strFlag = "Pass";
				} else {
					strFlag = "Fail";
					System.out.println("No department is active");
				}
			}
			navigateToMyPreferences();
			System.out.println("Navigated to My Preferences.");

			// get analyze user Theme value
			String themeFromAnalyse = analyzerUserMap.get("Theme Colors").get(0);
			System.out.println(themeFromAnalyse);
			// get the Themes on my preference modal window
			// My Preference will be open with control on it
			for (WebElement ele : wbelMyPreferenceModalTheme) {
				value = ele.getAttribute("Value");
				if (value.equalsIgnoreCase(themeFromAnalyse)) {
					strFlag = "Pass";
					result = "Theme color exists in My Preference Modal Window. ";
					break;
				} else {
					strFlag = "Fail";
					result = "Theme color does NOT exists in My Preference Modal Window";
				}
			}
			navigateToHomepage();
			System.out.println("Navigated to Home page.");
			if (!onDept) {
				ReportFactory
						.reporterOutputMerge(strTCID,
								"Verify that Theme is displayed in the My Preference modal window on Home page", "NA ",
								"Theme " + themeFromAnalyse + " should exists in My Preference Modal Window ",
								result + "</br><b>Theme in Analyse User : </b>" + themeFromAnalyse
										+ ".</br><b>Theme in My Preferences Modal window : </b>" + value,
								strFlag, merger, 4);
			} else {
				ReportFactory
						.reporterOutputMerge(strTCID,
								"Verify that Theme is displayed in the My Preference modal window on Department.",
								"NA ", "Theme " + themeFromAnalyse + " should exists in My Preference Modal Window ",
								result + "</br><b>Theme in Analyse User : </b>" + themeFromAnalyse
										+ ".</br><b>Theme in My Preferences Modal window : </b>" + value,
								strFlag, merger, 4);
			}
		} catch (Exception e) {
			if (!onDept) {
				ReportFactory.reporterOutputMerge(strTCID, "Verify Themes are displayed from department page.",
						"Verify Themes are NOT displayed ", "NA", e.getMessage().toString(), strFlag, merger, 4);
			} else {
				ReportFactory.reporterOutputMerge(strTCID, "Verify Themes are displayed ",
						"Verify Themes are NOT displayed ", "NA", e.getMessage().toString(), strFlag, merger, 4);
				homepageDealerPath.click();
				System.out.println("Navigated to Home page.");
			}
		}
	}

	/**
     * @author      Bhavika Solanki
     * 
     * Verify that on changing the language from language preferences under my preferences, home page should be changed in language that is saved.
     * 
     * @param strTCID        			strTCID is mentioned in the test data that will be reflected in the report.
     * @param onDept        			onDept flag signifies whether the test script is to be executed on Department. 
     * @param merger        			merger flag signifies the parameter for reporting purpose. 
     * @param strExpectedFooter         Expected footer is passed from test data. 
     * @return          				NA
	 * @throws              			Throwable 
     * @exception						Exception
     */
	
	public static void regoinWiseLangChangeVerification( String strTCID, boolean onDept,
			String strExpectedFooter, boolean merger) throws Throwable {
		try {
			//Using the URL, site is retrieved
			System.out.println("get the URL");
			String URL = MyPreferencesDriver.getCurrentUrl();
			String[] URLSite = URL.split("/");
			System.out.println("URL>>>>>>>>>>>>>>>>>"+URL);
			
			switch(URLSite[7]) {
			case "DealerPath":
				LogFactory.beginTestCase(
						"Verify that on changing the language from language preferences under my preferences, home page should be changed in language that is saved");
				myPreferenceLangChangeVerification( strTCID, onDept, strExpectedFooter, true,
						"R2 JDIN");
				System.out.println("DealerPath");
				break;
			case 	"R4Home" : 
				LogFactory.beginTestCase(
						"Verify that on changing the language from language preferences under my preferences & save and verify if the page is changing accordingly for R4 USA/Canada should be displayed.");
				myPreferenceLangChangeVerification( strTCID, onDept, strExpectedFooter, true,
						"R4 USA/Canada");
				System.out.println("R4Home");
				break;
			case 	"R3HispanoHome" :
				LogFactory.beginTestCase(
						"Verify that on changing the language from language preferences under my preferences & save and verify if the page is changing accordingly for R3 Hispano should be displayed.");
				myPreferenceLangChangeVerification( strTCID, onDept, strExpectedFooter, true,
						"R3 Hispano");
				System.out.println("R3HispanoHome");
				break;
			default: 
				LogFactory.beginTestCase(
						"Verify that on changing the language from language preferences under my preferences & save and verify if the page is changing accordingly.");
				myPreferenceLangChangeVerification( strTCID, onDept, strExpectedFooter, true, "other");
				System.out.println(URLSite[7]);
				break;
			}
		} catch (Exception e) {
			
			System.out.println("catch block : regoinWiseLangChangeVerification "+e.getMessage());
		}
	}

	/**
     * @author      Bhavika Solanki
     * 
     * Verify that on changing the language from language preferences under my preferences, home page should be changed in language that is saved.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
     * @param isIndex        isIndex flag signifies whether the test script is to be executed on Index page.
     * @param onDept         onDept flag signifies whether the test script is to be executed on Department. 
     * @param merger         merger flag signifies the parameter for reporting purpose. 
     * @return          	 NA
	 * @throws               Throwable 
     * @exception			 Exception
     */
	public static void myPreferenceLangChangeVerification( String strTCID, boolean onDept,
			String strExpectedFooter, boolean merger, String region) throws Throwable {
		String strFlag = "Pass";
		String result = "", langSelected = "", langSaved = "", expectedHeader = "", welcomeHeaderActual = "",
				productHeaderActual = "", str = "", ResetLanguage = null, langtobeSelected, translationfavouri = "",
				translationprod = "";
		List<String> langSa = new ArrayList<String>();
		List<String> translationProduct = new ArrayList<String>();
		List<String> translationFav = new ArrayList<String>();
		List<WebElement> listOfLanguages;
		List<String> value = new ArrayList<String>();
		int counter = 0, counter1 = 0;
		int colspan = 12;
		String tarnsCode = null, activeDepartment = "";
		try {
			System.out.println("Clicked on Home Page");
			navigateToHomepage.click();
			System.out.println("Clicked on Home Page");
			//If onDept is true then navigate to the first active department
			if (onDept) {
				if (ListAllActiveLinks.get(0).isEnabled()) {
					activeDepartment = ListAllActiveLinks.get(0).getText();
					Thread.sleep(500);
					System.out.println("Navigated to first active department" + activeDepartment);
				} else {
					strFlag = "Fail";
					System.out.println("No department is active");
				}
				Thread.sleep(500);
				GenericFactory.clickOnDepartmentByName(activeDepartment);
			}
			// logic for number of languages
			Thread.sleep(500);
			List<String> listOfLanguage = ListonMypreferences();
			Thread.sleep(1000);
			//if language ddl is not present 
			if (listOfLanguage.isEmpty()) {
				if (onDept) {
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify that the changes related to language are done on changing the language from My Preference Modal window from department page.",
							"NA ",
							"The changes related to language should be done on changing the language from My Preference Modal window",
							"Language is set by default", strFlag, merger, 2);
				} else {
					strFlag = "Pass";
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify that the changes related to language are done on changing the language from My Preference Modal window from Home page.",
							"NA ",
							"The changes related to language should be done on changing the language from My Preference Modal window",
							"Language is set by default", strFlag, merger, 2);
				}
			}
			//Normal flow
			else {
				//Reporting purpose
				colspan = colspan * listOfLanguage.size();
				System.out.println("colspan>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + colspan);
				Select languageSelector = new Select(wbelMyPreferenceModalLanguageDDL);
				for (int lang = 0; lang < listOfLanguage.size(); lang++) {
					Thread.sleep(500);
					//Navigate to my preferences
					navigateToMyPreferences();
					Thread.sleep(500);
					
					ResetLanguage = languageSelector.getFirstSelectedOption().getText();
					langtobeSelected = languageSelector.getOptions().get(lang).getText();
					//select the language and save
					languageSelector.selectByVisibleText(listOfLanguage.get(lang));
					value.clear();
					value.add(languageSelector.getOptions().get(lang).getAttribute("value"));
					analyzerUserMap.replace("Language", value);
					wbelMyPreferenceModalSaveButton.click();
					Thread.sleep(500);
					MyPreferencesDriver.switchTo().defaultContent();
					WaitFactory.waitForPageLoaded();
					/*
					 * Verify that the Language just changed should be displayed accordingly in the
					 * 'My Preferences' modal window.
					 */
					Thread.sleep(500);
					navigateToMyPreferences();
					Thread.sleep(500);
					langSelected = listOfLanguage.get(lang);
					listOfLanguages = language.findElements(By.xpath("//option"));
					langSaved = languageSelector.getFirstSelectedOption().getText();
					System.out.println("langSaved : " + langSaved);
					if (langSaved.equalsIgnoreCase(langSelected)) {
						System.out.println(langSelected + " is selected and saved successfully ");
					} else {
						System.out.println(langSelected + " is NOT selected and saved successfully ");
					}
					if (onDept) {
						counter = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify changing the language from language preferences under my preferences & save on department page",
								"Changed to : " + langSelected,
								"Newly changed language should be displayed in the 'My Preferences' modal window.",
								"Changed Language is displayed in the 'My Preference' modal window.", strFlag, merger,
								colspan);
					} else {
						counter = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify changing the language from language preferences under my preferences & save ",
								"Changed to : " + langSelected,
								"Newly changed language should be displayed in the 'My Preferences' modal window.",
								"Changed Language is displayed in the 'My Preference' modal window.", strFlag, merger,
								colspan);
					}
					strFlag = "Fail";
					if (counter == 1) {
						merger = false;
					}
					Thread.sleep(500);
					navigateToHomepage();
					Thread.sleep(500);
					WaitFactory.waitForPageLoaded();
					// Map for verification Points
					Map<String, String> homepageCheckPoints = new HashMap<String, String>();
					homepageCheckPoints.put("left Header", "My DealerPath");
					homepageCheckPoints.put("Favourites Header", "My DealerPath Favorites");
					homepageCheckPoints.put("header Welcome", "Welcome");
					homepageCheckPoints.put("Products", "Product Segments");
					if (onDept) {
						homepageDealerPath.click();
						WaitFactory.waitForPageLoaded();
					}
					// Verification point : Left Header
					String leftHeader = homepageCheckPoints.get("left Header");
					// Translation for left header
					List<String> translation = GenericFactory.getTranslation(leftHeader);
					String translatedleftHeader = translation.get(0);
					LogFactory.info("Verification points : Left Header");
					if (ValidationFactory.isElementPresent(wbelLeftHeader)) {
						leftHeader = wbelLeftHeader.getText();
						if (translatedleftHeader.equalsIgnoreCase(leftHeader)) {
							strFlag = "Pass";
							result = "Left header is changed." + "</br><b>Translated :</b>" + translatedleftHeader
									+ ".</br><b>Actual :</b>" + leftHeader;
						} else {
							strFlag = "Fail";
							result = "Left header is NOT changed." + "</br><b>Translated :</b>" + translatedleftHeader
									+ ".</br><b>Actual :</b>" + leftHeader;
						}
					} else {
						strFlag = "Fail";
						result = "Left header is NOT present.";
					}
					if (onDept) {
						counter1 = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"On language change from depaertment page, verify that Left header 'My DealerPath' is changed on the homepage",
								"Changed to : " + translatedleftHeader,
								"Verify that Left header should be changed 'My DealerPath' is changed on the homepage",
								result, strFlag, merger, colspan);
					} else {
						counter1 = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"On language change from home page, verify that Left header 'My DealerPath' is changed on the homepage",
								"Changed to : " + translatedleftHeader,
								"Verify that Left header should be changed 'My DealerPath' is changed on the homepage",
								result, strFlag, merger, colspan);
					}
					strFlag = "Fail";
					result = "";
					// Verification point : footer
					LogFactory.info("Verification points : Footer");
					List<String> translationFooter = GenericFactory.getTranslation(strExpectedFooter);

					if (counter1 == 1) {
						merger = false;
					}
					String listExpectedDataString = "";
					for (int i = 0; i < translationFooter.size(); i++) {
						listExpectedDataString = translationFooter.get(i) + ",";
					}
					verifyFooterLinks(listExpectedDataString, "TC13_PortalPreferences", merger, colspan, onDept);
					strFlag = "Fail";
					result = "";
					// Verification Point : Favourite
					LogFactory.info("Verification points : Favourite");
					String strActualFavHeader = "";
					if (ValidationFactory.isElementPresent(wbelHeaderTitleFav)) {
						strActualFavHeader = wbelHeaderTitleFav.getText();
						expectedHeader = homepageCheckPoints.get("Favourites Header");
						translationFav = GenericFactory.getTranslation(expectedHeader);
						translationfavouri = translationFav.get(0);

						strActualFavHeader = strActualFavHeader.replaceAll(" ", "");
						translationfavouri = translationfavouri.replaceAll(" ", "");

						strActualFavHeader = strActualFavHeader.replaceAll("Â ", "");
						translationfavouri = translationfavouri.replaceAll("Â ", "");

						System.out.println(translationfavouri.length() + "--" + strActualFavHeader.length());
						if (strActualFavHeader.equalsIgnoreCase(translationfavouri)) {
							strFlag = "Pass";
							result = "Favourite is present and displayed as expected.</br><b>Expected Favourite Header : <b>"
									+ translationfavouri + ".</br><b>Actual Favourite Header : <b>"
									+ strActualFavHeader;
						} else {
							strFlag = "Fail";
							result = "Favourite is NOT present and displayed as expected.</br><b>Expected Favourite Header : <b>"
									+ translationfavouri + ".</br><b>Actual Favourite Header : </b>"
									+ strActualFavHeader;
						}
					} else {
						strFlag = "Fail";
						result = "Favourite is NOT present and displayed as expected.";
					}
					if (onDept) {
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify that Favorites header is changed on the homepage from department page. ",
								"changed to :" + translationfavouri, "Favourites header  should be changed.", result,
								strFlag, merger, colspan);
					} else {
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify that Favorites header is changed  on the homepage are changed ",
								"changed to :" + translationfavouri, "Favourites header should be changed.", result,
								strFlag, merger, colspan);
					}
					strFlag = "Fail";
					result = "";
					// Verification Point : Welcome
					LogFactory.info("Verification points : Welcome");
					if (ValidationFactory.isElementPresent(wbelHeaderWelcome)) {
						welcomeHeaderActual = wbelHeaderWelcome.getText();
						String welcomeHeaderExpected = homepageCheckPoints.get("header Welcome");
						List<String> translationWelcome = GenericFactory.getTranslation(welcomeHeaderExpected);
						str = translationWelcome.toString();
						str = str.contains("[") ? str.replaceAll("[\\[\\]]", "") : str;
						if (welcomeHeaderActual.equalsIgnoreCase(str + ",")) {
							strFlag = "Pass";
							result = "Welcome is changed and displayed" + ".</br><b>Expected Welcome header : </b>"
									+ str + "," + "</br><b>Actual Welcome header : </b>" + welcomeHeaderActual;
						} else {
							strFlag = "Fail";
							result = "Welcome is NOT changed and displayed" + ".</br><b>Expected Welcome header : </b>"
									+ str + "," + "</br><b>Actual Welcome header : </b>" + welcomeHeaderActual;
						}
					} else {
						strFlag = "Fail";
						result = "Welcome is NOT present and displayed as expected.";
					}
					if (onDept) {
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify that the text Welcome is along with User Name is  changed on the homepage from department page.",
								"Changed to : " + str + ",",
								"Welcome should be changed and shown along with user name.", result, strFlag, merger,
								colspan);
					} else {
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify that the text Welcome is along with User Name is  changed on the homepage.",
								"Changed to : " + str + ",",
								"Welcome should be changed and shown along with user name.", result, strFlag, merger,
								colspan);
					}
					strFlag = "Fail";
					result = "";
					// Verification Point : Product title
					LogFactory.info("Verification points : Product title");
					wbelproductsegmenticon.click();
					if (ValidationFactory.isElementPresent(wbelproductsegmenticon)) {
						productHeaderActual = wbelProductHeader.getText();
						String favouriteExpectedHeader = homepageCheckPoints.get("Products");
						translationProduct = GenericFactory.getTranslation(favouriteExpectedHeader);
						translationprod = translationProduct.get(0);
						if (translationprod.contains(productHeaderActual)) {
							strFlag = "Pass";
							result = "Product Header is changed and displayed."
									+ "</br><b>Expected Product Header : <b>" + translationprod
									+ "</br><b>Actual Product Header : <b>" + productHeaderActual;
						} else {
							strFlag = "Fail";
							result = "Product Header is NOT changed and displayed.";
						}
					} else {
						strFlag = "Fail";
						result = "Product Header is NOT changed and displayed."
								+ "</br><b>Expected Product Header : <b>" + translationprod
								+ "</br><b>Actual Product Header : <b>" + productHeaderActual;
					}
					if (onDept) {
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify that Product Segments header is changed  on the homepage from department page",
								"Actual Changed to : " + translationprod,
								"Product header should be changed to Product Segments", result, strFlag, merger,
								colspan);
					} else {
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify that Product Header is " + homepageCheckPoints.get("Products"),
								"Actual Changed to : " + translationProduct,
								"Product header should be changed to Product Segments", result, strFlag, merger,
								colspan);
					}
					
				}
			}
		} catch (Exception e) {
			strFlag = "Fail";
			if (onDept) {
				System.out.println("Failed on department page");
				ReportFactory.reporterOutput(strTCID,
						"Verify that expected changes are reflected on changing the language from My Preferences modal window from department page.",
						"NA",
						"Verify that expected changes are reflected on changing the language from My Preferences modal window.",
						"Element Not Found", strFlag);
				navigateToHomepage.click();
				System.out.println("navigateToHomepage is clicked");
			} else {
				System.out.println("Failed on Home page");
				ReportFactory.reporterOutput(strTCID,
						"Verify that expected changes are reflected on changing the language from My Preferences modal window.",
						"NA",
						"Verify that expected changes are reflected on changing the language from My Preferences modal window.",
						"Element Not Found", strFlag);
				navigateToHomepage.click();
				System.out.println("navigateToHomepage is clicked");
			}
		}
	}

	
	/**
     * @author      Bhavika Solanki
     * 
     * Verify footer links are as per those mentioned in test data.
     * 
     * @param TCID        				strTCID is reflected in the report.
     * @param dept        			    dept flag signifies whether the test script is to be executed on Department. 
     * @param excelValue        		Expected footer links (Translated) mentioned in test data.
     * @param colspan         			Expected footer is passed from test data. 
     * @param merger        			merger flag signifies the parameter for reporting purpose. 
	 * @throws              			Throwable 
     * @exception						Exception
     */
	public static void verifyFooterLinks(String excelValue, String TCID, boolean merger, int colspan, boolean dept)
			throws Throwable {
		String flag = "Fail";
		String result = "Footer links are not showing as expected";
		String switchSiteName = "";
		List<String> StrhomepageFooterList = new ArrayList<String>();
		List<String> expectedFooterTestDataList = GenericFactory.splitString(excelValue, ",");
		try {
			if (ValidationFactory.isElementPresent(wbelSwitchFooterLink)) {
				switchSiteName = GenericFactory.splitString(wbelSwitchFooterLink.getText(), "[").get(0);
			}
			List<WebElement> WeHomepageFooterList = GenericFactory.getLinksFromFrame(wbelHomePageFooterFramePath);
			for (int i = 0; i < WeHomepageFooterList.size(); i++) {
				if (WeHomepageFooterList.get(i).getText().toString().trim().contains(switchSiteName)
						&& !switchSiteName.equals("")) {
					StrhomepageFooterList.add(i, switchSiteName);
				} else {
					String hearlinktxtvalue = WeHomepageFooterList.get(i).getText().toString().trim();
					StrhomepageFooterList.add(i, hearlinktxtvalue);
				}
			}
			System.out.println("Str homepage FooterList= " + StrhomepageFooterList);
			if (StrhomepageFooterList.equals(expectedFooterTestDataList)) {
				flag = "Pass";
				result = "Footer links are showing as expected";
				LogFactory.info(expectedFooterTestDataList + " is present on Footer link");
			}
			if (!dept) {
				ReportFactory.reporterOutputMerge(TCID, "Verify that the Footer Links on the homepage are changed ",
						"Changed to : " + excelValue, "Footer links should be present and show in sequence",
						result + " Actual Footer Links : " + StrhomepageFooterList, flag, merger, colspan);
			} else {
				ReportFactory.reporterOutputMerge(TCID, "Verify that the Footer Links on the homepage are changed ",
						"Changed to : " + excelValue, "Footer links should be present and show in sequence ",
						result + " Actual Footer Links : " + StrhomepageFooterList, flag, merger, colspan);
			}
		} catch (Exception e) {
			String er = e.getMessage().substring(0, 35).toString().trim();
			if (!dept) {
				ReportFactory.reporterOutputMerge(TCID, "Verify that the Footer Links on the homepage are changed ",
						"Changed to : " + excelValue, "FooterLinks should show as per on the UI.", er, flag, merger,
						colspan);
			} else {
				ReportFactory.reporterOutputMerge(TCID, "Verify that the Footer Links on the homepage are changed ",
						"Changed to : " + excelValue, "FooterLinks should show as per on the UI.", er, flag, merger,
						colspan);
			}
		}
	}

	/**
     * @author      Bhavika Solanki
     * 
     * Verify that departments checked on my preferences page are reflected in the Left pane for Departments on Home page.
     * 
     * @param strTCID        			strTCID is mentioned in the test data that will be reflected in the report.
	 * @throws              			Throwable 
     * @exception						Exception
     */
	
	static String resetLang = "";

	public static void ClickOnLanguagePrerefences(String strTCID) throws Throwable {
		int colspan = 2;
		boolean merger = true;
		String strFlag = "Fail";
		List<String> listhomepage;
		List<String> ListString;
		try {
			ListString = ListonMypreferences();
			if (ListString != null && !ListString.isEmpty()) {
				colspan = 2 * ListString.size();
				for (int i = 0; i < ListString.size(); i++) {
					listhomepage = MyPreferencesCheckboxValidation1(ListString.get(i));
					System.out.println("selected>>>>>" + ListString.get(i));
					WaitFactory.FluentWaitByWebElement(utilityMenu);
					navigateToMyPreferences();
					MyPreferencesDriver.switchTo().activeElement();
					WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalLanguageDDL);
					List<String> listMyprefernces = new ArrayList<String>();
					for (int j = 0; j < MypreferencescheckboxClass.size(); j++) {
						for (int k = 0; k < MypreferencescheckboxType.size(); k++) {
							if (MypreferencescheckboxType.get(j).getAttribute("checked") != null) {
								System.out.println(">>>>>>>>>>My preferences list>>>>>>>>  "
										+ MypreferencescheckboxClass.get(j).getText());
								listMyprefernces.add(MypreferencescheckboxClass.get(j).getText());
								break;
							}
						}
					}
					navigateToHomepage();
					navigateToMyPreferences();
					Select select = new Select(wbelMyPreferenceModalLanguageDDL);
					select.selectByVisibleText(resetLang);
					wbelMyPreferenceModalSaveButton.click();
//					Thread.sleep(1000);
					MyPreferencesDriver.switchTo().defaultContent();
//					Thread.sleep(1000);
					if (listhomepage.equals(listMyprefernces)) {
						strFlag = "Pass";
						System.out.println(strTCID);
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify by the changing the preferred language and save then all accessible departments should be displayed and checked in the department section of the modal window should also be reflected in the Left Navigation  on Home page.",
								"Changing the preferred language: " + ListString.get(i),
								"All accessible departments checked on My Preference modal window should be active and displayed on Left Naviagtion on Home page.List on Home page : "
										+ listhomepage,
								"The department those are checked on My Preference modal window are active and displayed  on Home page.List on My Preference : "
										+ listMyprefernces,
								strFlag, merger, colspan);
						merger = false;
					} else {
						strFlag = "Fail";
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify by the changing the preferred language and save then all accessible departments should be displayed and checked in the department section of the modal window should also be reflected in the Left Navigation  on Home page.",
								"Changing the preferred languagec: " + ListString.get(i),
								"All accessible departments checked on My Preference modal window should be active and displayed on Left Naviagtion on Home page.List on Home page : "
										+ listhomepage,
								"The department those are checked on My Preference modal window are NOT active and displayed  on Home page.List on My Preference : "
										+ listMyprefernces,
								strFlag, merger, colspan);
						merger = false;
					}
				}
			} else {
				strFlag = "Pass";
				WaitFactory.WaitForElementToVisible(navigateToHomepage);
				navigateToHomepage.click();
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify that departments checked on my preferences page are reflected in the Left pane for Departments on Home page in all the languages",
						"NA",
						"The department those are checked on My Preference modal window should be active on Home page for all the languages.",
						"The default language is selected.", strFlag, merger, 2);
			}
		} catch (Exception e) {
			strFlag = "Fail";
			ReportFactory.reporterOutputMerge(strTCID,
					"Verify by the changing the preferred language and save then all accessible departments should be displayed and checked in the department section of the modal window should also be reflected in the Left Navigation  on Home page.",
					"NA",
					"All accessible departments checked on My Preference modal window should be active and displayed on Left Naviagtion on Home page.",
					"The department those are checked on My Preference modal window are NOT active and displayed  on Home page.",
					strFlag, merger, 2);
			navigateToHomepage();
//			Thread.sleep(2000);
		}
	}

	/**
     * @author      Bhavika Solanki
     * 
     * logic for number of languages.
     * 
	 * @throws              			Throwable 
     * @exception						Exception
     * @return							number of languages present in the my preference modal window.
     */
	
	
	public static List<String> ListonMypreferences() throws Throwable {
		List<String> Languaguage = new ArrayList<String>();
		Thread.sleep(500);
		navigateToMyPreferences();
		Thread.sleep(500);
		try {
			Thread.sleep(1000);
			if (ValidationFactory.isElementPresent(wbelMyPreferenceModalLanguageDDL)) {
				wbelMyPreferenceModalLanguageDDL.isDisplayed();
				WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalLanguageDDL);
				Select select = new Select(wbelMyPreferenceModalLanguageDDL);
				List<WebElement> Listtemp = select.getOptions();
				System.out.println(">>>>>>>>>>Mypreferences Language size>>>>>>>>>>" + Listtemp.size());

				for (int i = 0; i < Listtemp.size(); i++) {
					String temp = Listtemp.get(i).getText();

					System.out.println(">>>>>>>>>>Mypreferences Language>>>>>>>>>> " + temp);
					Languaguage.add(temp);
				}
				navigateToHomepage();
			} else {
				WaitFactory.FluentWaitByWebElement(MypreferencesCancel);
				MypreferencesCancel.click();
				Thread.sleep(500);
				MyPreferencesDriver.switchTo().defaultContent();
				Thread.sleep(500);
			}
		} catch (Exception e) {
			return null;
		}
		return Languaguage;
	}

	/**
     * @author      Bhavika Solanki
     * 
     * select the language  from my preference modal window and save the same to get the list of active departments.
     * 
     * @param Id						Language that is to be selected from the language drop down on My Preferences modal window.
	 * @throws              			Throwable 
     * @exception						Exception
     * @return							list of active departments in the left pane.
     */
	public static List<String> MyPreferencesCheckboxValidation1(String Id) throws Throwable {
		try {
			System.out.println(MyPreferencesDriver.getTitle());
			Thread.sleep(500);
			navigateToMyPreferences();
			Thread.sleep(1000);
			MyPreferencesDriver.switchTo().activeElement();
			WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalLanguageDDL);
			Select select = new Select(wbelMyPreferenceModalLanguageDDL);
			resetLang = select.getFirstSelectedOption().getText();
			List<WebElement> dropdownList = select.getOptions();
			System.out.println(">>>>>>>>>>Home Page list>>>>>>>>>>  " + dropdownList.size());
			select.selectByVisibleText(Id);
			wbelMyPreferenceModalSaveButton.click();
			Thread.sleep(1000);
			MyPreferencesDriver.switchTo().defaultContent();
			Thread.sleep(1000);
			homepageDealerPath.click();
			Thread.sleep(1000);
			List<String> listhomepage = new ArrayList<String>();
			for (int i2 = 0; i2 < ListAllActiveLinks.size(); i2++) {
				String temp = ListAllActiveLinks.get(i2).getText();
				listhomepage.add(temp);
				System.out.println(">>>>>>>>>>" + temp);
			}
			return listhomepage;
		} catch (Exception e) {
			List<String> listhomepage = new ArrayList<String>();
			System.out.println("Exception block of MyPreferencesCheckboxValidation1");
			return listhomepage;
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify that department list from analyse users is same as on My Preference modal window.
     * 
     * @param ExpectedListKey			Expected department list
     * @param strTCID					TCID
	 * @throws              			Throwable 
     * @exception						Exception
     */
	public static void analyzeUserDeptListWithMyPreferecne(List<String> ExpectedListKey, String strTCID)
			throws Throwable {
		String strFlag = "Fail";
		List<String> deptListFromApplication;
		List<String> listFromMyPreference = new ArrayList<String>();
		try {
			deptListFromApplication = analyzerUserMap.get("Department");
			List<String> listfromAnalyseUser = new ArrayList<String>();
			Collections.sort(deptListFromApplication);
			// remove the blank entries
			for (int i = 0; i < deptListFromApplication.size(); i++) {
				if (!deptListFromApplication.get(i).equals("")) {
					listfromAnalyseUser.add(deptListFromApplication.get(i).toString().trim());
				}
			}
			System.out.println("*****Department list from analayze user*****" + listfromAnalyseUser);
			navigateToMyPreferences();
			for (int i = 0; i < wbelMyPreferenceModalDepartments.size(); i++) {
				String dept = wbelMyPreferenceModalDepartments.get(i).getText();
				System.out.println(dept);
				listFromMyPreference.add(dept);
			}
			System.out.println(listFromMyPreference);
			System.out.println("******Department list from My Preference*****" + listFromMyPreference);
			if (listfromAnalyseUser.equals(listFromMyPreference)) {
				strFlag = "Pass";
				ReportFactory.reporterOutput(strTCID,
						"Verify Departments present on Analyse user should be displayed in My Preference Modal Window ",
						"NA ",
						"The List of departments for My Preference modal window should be same as the list on Analyse user. List from Analyse user : "
								+ listfromAnalyseUser,
						"List of departments for My Preference modal window is same as the list on Analyse user is as expected.List from My Preference modal window : "
								+ listFromMyPreference,
						strFlag);
				navigateToHomepage();
				WaitFactory.FluentWaitByWebElement(strHomeDealer);
				strHomeDealer.click();
			} else {
				ReportFactory.reporterOutput(strTCID,
						"Verify that the List of departments for Analyze user is as expected ", "NA ",
						"The List of departments for My Preference modal window should be as expected ",
						"List of departments for Analyze user is NOT as expected", strFlag);
				navigateToHomepage();
				WaitFactory.FluentWaitByWebElement(strHomeDealer);
				strHomeDealer.click();
			}
		} catch (Exception e) {
			strFlag = "Fail";
			ReportFactory.reporterOutput(strTCID,
					"Verify Departments present on Analyse user should be displayed in My Preference Modal Window ",
					"NA", "The List of departments for My Preference modal window should be as expected ",
					"List of departments for Analyze user is NOT as expected", strFlag);
			WaitFactory.FluentWaitByWebElement(strHomeDealer);
			strHomeDealer.click();
		}
	}

	/**
     * @author      Bhavika Solanki
     * 
     * Saves language using the code for the language
     * 
     * @param code						Code of the language
	 * @throws              			Throwable 
     * @exception						Exception
     * @return							returnedLang
     */
	
	@SuppressWarnings("null")
	public static List<String> selectLanguageUsingCode(List<String> code) throws Exception {

		// map for language
		List<String> returnedLang = new ArrayList<String>();
		for (String lang : code) {
			Map<String, String> mp = new HashMap<String, String>();
			mp.put("fr_FR", "Franï¿½ais");
			mp.put("en_US", "English");
			mp.put("en_GB", "English");
			mp.put("es_MX", "Spanish");
			mp.put("pt_BR", "Portuguese");
			mp.put("ca_FR", "Canadian French");
			mp.put("ru_RU", "Russian");
			mp.put("de_DE", "German");
			mp.put("en_AU", "English");
			mp.put("zh_CN", "China");

			try {
				if (mp.containsKey(lang)) {
					String Value = mp.get(lang);
					returnedLang.add(Value);
					GenericFactory.utilityMenuMyPreferenceClick();
					if (language != null) {
						Select preferedLang = new Select(language);
						List<WebElement> dropdownValues = preferedLang.getOptions();
						List<String> listValues = new ArrayList<String>();
						for (int j = 0; j < dropdownValues.size(); j++) {
							String strValue = dropdownValues.get(j).getText();
							listValues.add(strValue);
						}
						if (listValues.contains(mp.get(lang))) {
							preferedLang.selectByVisibleText(mp.get(lang));
							wbelMyPreferenceModalSaveButton.click();
							LogFactory.info("Language " + mp.get(lang) + " is selected on "
									+ "My Preferences which is mapped to code " + lang);
						} else {
							LogFactory.info("Value from excel is not available in Language dropdown on My Preferences");
							wbelMyPreferenceModalCancelButton.click();

						}

					}
				}
			} catch (Exception e) {
				return null;
			}
		}
		return returnedLang;

	}

	/**
     * @author      Bhavika Solanki
     * 
     * returns language using the code for the language
     * 
     * @param code						Code of the language
	 * @throws              			Throwable 
     * @exception						Exception
     * @return							returnedLang
     */
	static String languagebyCode = "";

	public static String selectLanguagebyCodeMap(String code) throws Exception {
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("fr_FR", "Français");
		mp.put("en_US", "English");
		mp.put("en_GB", "English");
		mp.put("es_MX", "Spanish");
		mp.put("pt_BR", "Portuguese");
		mp.put("ca_FR", "Canadian French");
		mp.put("ru_RU", "Russian");
		mp.put("de_DE", "German");
		mp.put("en_AU", "English");
		mp.put("zh_CN", "China");
		mp.put("fr_CA","Français");

		languagebyCode = mp.get(code);
		return languagebyCode;

	}
	/**
     * @author      Bhavika Solanki
     * 
     * returns site using the code
     * 
     * @param code						Code of the site
	 * @throws              			Throwable 
     * @exception						Exception
     * @return							returnedLang
     */
	static String SitebyCode = "";

	public static String selectSitebyCodeMap(String code) throws Exception {
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("R3_H", "R3 Hispano");
		mp.put("R3_B", "R3 Brasil");
		mp.put("R1_SS", "R1 Sub Saharan Africa");
		mp.put("R4_AU", "R4 Australia/New Zealand");
		mp.put("R1_TH", "R1 Thailand");
		mp.put("R1_CH", "R1 China");
		mp.put("R1_AS", "R1 JD Asia");
		mp.put("JDIN", "R2 JDIN");
		mp.put("R4", "R4 USA/Canada");
		mp.put("R1_IN", "R1 India");
		mp.put("R1_CN", "R1 China");

		SitebyCode = mp.get(code);
		return SitebyCode;

	}

	/**
     * @author      Bhavika Solanki
     * 
     * Verify that if the department on which system is present is deactivated then the system redirects to Home page.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	
	public static void DeptUncheckTest(String strTCID) throws Throwable {
		String strFlag = "Fail";

		String result = "If department on which system is present is deactivated then system is NOT redirecting to homepage successfully";

		try {
			WaitFactory.WaitForElementToVisible(navigateToHomepage);
			navigateToHomepage.click();
			GenericFactory.checkDepartmentMyPreference();
			WaitFactory.waitforelementToinvisibile(wbelMyPreferenceModalSaveButton);
			// Navigate to the first active department
			for (int i = 0; i < ListAllActiveLinks.size(); i++) {
				strPInputValue = ListAllActiveLinks.get(i).getText();
				ListAllActiveLinks.get(i).click();
				Thread.sleep(500);

				for (int j = 0; j < wbelSectionHeader.size(); j++) {
					if (wbelSectionHeader.get(j).getText().contains(strPInputValue)) {
						LogFactory.info("User is on " + strPInputValue + " Department");
						break;
					}
				}
			}
			navigateToMyPreferences();
			List<WebElement> names = allCheck;
			for (int k = 0; k < names.size(); k++) {
				if (names.get(k).getText().equalsIgnoreCase(strPInputValue)) {
					if (!(checkBoxList.get(k).getAttribute("checked") == null)) {
						checkBoxList.get(k).click();
					}
					break;
				}
			}
			WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalSaveButton);
			JavascriptExecutor executor = (JavascriptExecutor) MyPreferencesDriver;
			executor.executeScript("arguments[0].click();", wbelMyPreferenceModalSaveButton);
			Thread.sleep(500);
			WaitFactory.waitforelementToinvisibile(wbelMyPreferenceModalSaveButton);
			if (homepageDealerPath.isDisplayed()) {
				LogFactory.info("User is on homepage");
			}
			strHomeDealer.click();
			WaitFactory.waitForPageLoaded();
//			Thread.sleep(2000);
			for (int n = 0; n < ListAllInActiveLinks.size(); n++) {
				if (ListAllInActiveLinks.get(n).getText().equalsIgnoreCase(strPInputValue)) {
					LogFactory.info(strPInputValue + " Department is inactive now");
					strFlag = "Pass";
					result = "System is redirecting to homepage successfully";
					break;
				}
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify that on any department if the same department is unchecked & saved from 'My Preferences' for any user, the system should redirect to the homepage.",
					"NA ",
					"From any department, if the same department is unchecked & saved from 'My Preferences' for any user then the system should redirect to the homepage.",
					result, strFlag);
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify that on any department if the same department is unchecked & saved from 'My Preferences' for any user, the system should redirect to the homepage.",
					"NA ",
					"From any department, if the same department is unchecked & saved from 'My Preferences' for any user then the system should redirect to the homepage.",
					result + "Catch block", strFlag);
			if(!ValidationFactory.isElementPresent(wbelHomeFav)) {
				navigateToHomepage();
				System.out.println("Navigated to Home page.");
				WaitFactory.FluentWaitByWebElement(wbelHomeFav);
			}
		}
	}

	static List<WebElement> listOfLangcode = new ArrayList<WebElement>();

	
	/**
     * @author      Bhavika Solanki
     * 
     * This method will pick the list of preferred site from the application.Select
	 * each site, save it and verify weather the respective languages for the
	 * preferred site are populated.
	 * 
     * @param strTCID       		  strTCID is mentioned in the test data that will be reflected in the report.
     * @param selectSite       		  site that is to be selected.
     * @param strExpectedValue        List of languages from test data.
	 * @throws              		  Throwable 
     * @exception					  Exception
     */
	@SuppressWarnings("unlikely-arg-type")
	public static void myPreferenceLangVerification(String strTCID, String strExpectedValue, String selectSite)
			throws Throwable {
		String strFlag = "Fail";
		String result;
		try {
			WaitFactory.WaitForElementToVisible(navigateToHomepage);
			navigateToHomepage.click();
//			Thread.sleep(1000);
			System.out.println("Click on Home Page.");
			navigateToMyPreferences();
			System.out.println("Navigated to My Preferences.");
			// Verify that Language ddl is present
			if (ValidationFactory.isElementPresent(wbelMyPreferenceModalLanguageDDL)) {
				WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalLanguageDDL);
				Select selectLang = new Select(wbelMyPreferenceModalLanguageDDL);
				listOfLangcode = selectLang.getOptions();
				List<String> listOfLang = new ArrayList<String>();
				for (int i = 0; i < listOfLangcode.size(); i++) {
					String code = listOfLangcode.get(i).getAttribute("value");
					System.out.println("code========" + code);
					String langReturned = returnLanguageUsingCode(code);
					System.out.println("langReturned========" + langReturned);
					listOfLang.add(langReturned);
				}
				String[] listOfExpectedLangFromTD = strExpectedValue.split(",");
				ArrayList<String> arrayListOfLanguageFromTD = new ArrayList<String>(
						Arrays.asList(listOfExpectedLangFromTD));
				if (listOfLang.containsAll(arrayListOfLanguageFromTD)
						&& listOfLang.size() == arrayListOfLanguageFromTD.size()) {
					strFlag = "Pass";
					result = "Language for " + selectSite + " is populated as expected";
				} else {
					strFlag = "Fail";
					result = "Languages for " + selectSite + " are NOT populated as expected";
				}
				ReportFactory.reporterOutput(strTCID,
						"Verify that Languages for " + selectSite + " are displayed as expected", strExpectedValue,
						"Languages for " + selectSite + " should be displayed as expected", result, strFlag);
				navigateToHomepage();
				System.out.println("Navigated to Home page.");
				WaitFactory.FluentWaitByWebElement(wbelHomeFav);
			} else {
				strFlag = "Pass";
				result = "Default language is selected.";
				ReportFactory.reporterOutput(strTCID,
						"Verify that Languages for " + selectSite + " are displayed as expected", strExpectedValue,
						"Languages for " + selectSite + " should be displayed as expected", result, strFlag);
				navigateToHomepage();
				System.out.println("Navigated to Home page.");
				WaitFactory.FluentWaitByWebElement(wbelHomeFav);
			}
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Verify that the List of language is displayed", "NA",
					"Verify that the List of language is displayed", e.getMessage().toString(), strFlag);
			if(!ValidationFactory.isElementPresent(wbelHomeFav)) {
				navigateToHomepage();
				System.out.println("Navigated to Home page.");
				WaitFactory.FluentWaitByWebElement(wbelHomeFav);
			}
		}
	}
	
	/**
     * @author      Bhavika Solanki
     * 
     * This method verifies that the language drop down is not getting displayed if
	 * the language is default for the site.
	 * 
     * @param strTCID       		  strTCID is mentioned in the test data that will be reflected in the report.
     * @param strExpectedValue        List of languages.
	 * @throws              		  Throwable 
     * @exception					  Exception
     */
	public static void myPreferenceLangVerification(String strTCID, String strExpectedValue) throws Throwable {
		String strFlag = "Fail";
		String result = "";
		int colspan = 1;
		List<String> sites = new ArrayList<String>();
		try {
			WaitFactory.FluentWaitByWebElement(strHomeDealer);
			navigateToMyPreferences();
//			Thread.sleep(1000);
			System.out.println("navigateToMyPreferences");
			WaitFactory.waitForPageLoaded();
			if (ValidationFactory.isElementPresent(wbelMyPreferencePreferredSite)) {
				Select listOfPreferredSite = new Select(wbelMyPreferencePreferredSite);
				List<WebElement> list = listOfPreferredSite.getOptions();
				System.out.println("list>>>>>>>>>>>>>." + list);
				List<String> listOfSites = new ArrayList<String>();
				for (int i = 0; i < list.size(); i++) {
					String site = list.get(i).getText();
					if (!site.equals("")) {
						listOfSites.add(site);
					}
				}
				System.out.println("listOfSites>>>>>>>>>>>>>." + listOfSites);
				for (int i = 0; i < list.size(); i++) {
					colspan = colspan * list.size();
					if (!(listOfSites.get(i).equals("R2 JDIN") || listOfSites.get(i).equals("R4 USA/Canada")
							|| listOfSites.get(i).equals("R3 Hispano"))) {
						listOfPreferredSite.selectByVisibleText(listOfSites.get(i));
						System.out.println("listOfSites.get(i)" + listOfSites.get(i));
						wbelMyPreferenceModalSaveButton.click();
//						Thread.sleep(1000);
						System.out.println("Clicked on Save button.");
						MyPreferencesDriver.switchTo().defaultContent();
						navigateToMyPreferences();
//						Thread.sleep(1000);
						System.out.println("Navigated to My Preferences.");
						try {
							wbelMyPreferenceModalLanguageDDL.isDisplayed();
							result = "Language drop down is NOT as expected";
						} catch (Exception e) {

							strFlag = "Pass";

							result = result + "</br>Preferred Language dropdown is not applicable for "
									+ listOfSites.get(i) + ".";
						} finally {
							sites = listOfSites;
						}
					}
				}
				colspan = 4;
				ReportFactory.reporterOutput(strTCID,
						"Verify that Languages for " + listOfSites
								+ " are not displayed as default language is applicable",
						"NA", "Verify that Preferred Language dropdown should not be applicable for " + listOfSites,
						result, strFlag);
				navigateToHomepage();
//				Thread.sleep(1000);
				System.out.println("Navigated to Home page.");
			} else {
				/*
				 * If the dealer is not able to view the preferred site drop down, then the user
				 * doesnot have the permissions to set the same.Hence in that case the below
				 * else part will be executed
				 */
				result = "Dealer do not have permissions to access preferred site";
				strFlag = "Pass";
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify that the default language is selected and the dropdown wont be displayed ", "NA ",
						"The respective default language is selected and the dropdown is not displayed ", result,
						strFlag, true, colspan);
				// navigate to home page
				wbelLeftHeader.click();
				System.out.println("Navigated to Home page.");
			}
		} catch (Exception e) {
			strFlag = "Pass";
			ReportFactory.reporterOutput(strTCID,
					"Verify that the default language is selected and the dropdown wont be displayed", "NA",
					"The respective default language is selected and the dropdown is not displayed ",
					"The default language is selected.", strFlag);
			if(!ValidationFactory.isElementPresent(wbelHomeFav)) {
				navigateToHomepage();
				System.out.println("Navigated to Home page.");
				WaitFactory.FluentWaitByWebElement(wbelHomeFav);
			}
		}
	}


	/**
     * @author      Bhavika Solanki
     * 
     * Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window, Url changes accordingly
     * 
     * @param strTCID       		  strTCID is mentioned in the test data that will be reflected in the report.
     * @param strExpectedValue        List of site from test data.
     * @param isIndex        isIndex flag signifies whether the test script is to be executed on Index page.
     * @param onDept         onDept flag signifies whether the test script is to be executed on Department. 
     * @param merger         merger flag signifies the parameter for reporting purpose. 
     * 
	 * @throws              		  Throwable 
     * @exception					  Exception
     */
	
	static String PreferedSite = "";

	@SuppressWarnings("null")
	public static void myPreferenUrlVerification(String strTCID, String strExpectedValue, boolean onDept,
			boolean isIndex, boolean merger) throws Throwable {
		String strFlag = "Fail";
		String result = "";
		String currentURL, SelectedSite, ableToAccess = "", notableToAccess = "";
		List<String> list = GenericFactory.splitString(strExpectedValue, ",");
		List<String> siteListFromApplication = new ArrayList<String>();
		int counter = 0;
		int counter1 = 0, colspan = 8;
		int counterfail = 1;
		String RunningURL = "";
		try {

//			Thread.sleep(1000);
			System.out.println("navigateToHomepage");
			homepageDealerPath.click();
			System.out.println("homepageDealerPath clicked.");
//			Thread.sleep(1000);
			/* Map for the preferred site as key and the change in the URL as value */
			Map<String, String> site = new HashMap<String, String>();
			site.put("R3 Brasil", "R3Brasil");
			site.put("R1 India", "R1India");
			site.put("R4 Australia/New Zealand", "R4Australia");
			site.put("R1 Thailand", "R1Thailand");
			site.put("R1 China", "R1China");
			site.put("R3 Hispano", "R3Hispano");
			site.put("R1 Sub Saharan Africa", "R1SSA");
			site.put("R2 JDIN", "Home/DealerPath");
			site.put("R4 USA/Canada", "R4");
			site.put("R1 JD Asia", "R1Asia");
			/*
			 * Verify all accessible sites should be displayed in the preferred site
			 * dropdown of "My Preferences" modal window for employees.(for all sites)
			 */
			WaitFactory.FluentWaitByWebElement(strHomeDealer);
			Thread.sleep(500);
			navigateToMyPreferences();
			Thread.sleep(500);
			System.out.println("Navigated to My Preferences.");
			WaitFactory.waitForPageLoaded();
			WaitFactory.FluentWaitByWebElement(wbelMyPreferencePreferredSite);
			System.out.println(
					"***********************************************************Navigated to My Preference page1");
			//Verify whether the prefered site ddl is present
			if (ValidationFactory.isElementPresent(wbelMyPreferencePreferredSite)) {
				Select preferredSite = new Select(wbelMyPreferencePreferredSite);
				PreferedSite = preferredSite.getFirstSelectedOption().getText();
				List<WebElement> listOfSitesFromApplication = preferredSite.getOptions();
				List<String> listOfSitesApp = new ArrayList<String>();
				//List from application is retrived
				for (WebElement eleSite : listOfSitesFromApplication) {
					siteListFromApplication.add(eleSite.getText());
				}
				
				//list from application is compared with the list from test data
				if (list.containsAll(siteListFromApplication) && list.size() == siteListFromApplication.size()) {
					strFlag = "Pass";
					result = "Expected list of Sites is populated under Preferred site drop down";
				} else {
					strFlag = "Fail";
					result = "Expected list of Sites is NOT populated under Preferred site drop down";
				}

				if (strFlag.equalsIgnoreCase("Pass")) {
					if (onDept) {
						counter = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) from department page.",
								strExpectedValue,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) ",
								result, strFlag, merger, colspan);
						counterfail++;
					} else {
						counter = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) ",
								strExpectedValue,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) ",
								result, strFlag, merger, colspan);
						counterfail++;
					}
					strFlag = "Fail";
					navigateToHomepage();
					System.out.println("Navigated to Homepage.");
					WaitFactory.waitForPageLoaded();
					WaitFactory.FluentWaitByWebElement(homepageDealerPath);
					homepageDealerPath.click();
					System.out.println("homepageDealerPath clicked.");
					System.out.println(
							"***********************************************************Navigated to home page2");
					/*
					 * Navigate to the department page and verify that Preferred site functionality
					 */
					for (String siteToBeSelected : list) {
						RunningURL = siteToBeSelected;
						if (onDept && (isIndex == false)) {
							if (ListAllActiveLinks.get(0).isEnabled()) {
								ListAllActiveLinks.get(0).click();
								Thread.sleep(2000);
								WaitFactory.waitForPageLoaded();
								System.out.println("Navigated to first active department");
								strFlag = "Pass";
							} else {
								strFlag = "Fail";
								System.out.println("No department is active");
							}
						}
						navigateToMyPreferences();
						System.out.println("Navigated to My Preferences.");
						WaitFactory.FluentWaitByWebElement(wbelMyPreferencePreferredSite);
						System.out.println(
								"***********************************************************Navigated to My Preference page3");
						WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalSaveButton);
						preferredSite.selectByVisibleText(siteToBeSelected);
						WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalSaveButton);
						wbelMyPreferenceModalSaveButton.click();
						Thread.sleep(500);
						WaitFactory.waitForPageLoaded();
						MyPreferencesDriver.switchTo().defaultContent();
						WaitFactory.waitForPageLoaded();
						WaitFactory.FluentWaitByWebElement(homepageDealerPath);
//						Thread.sleep(500);
						System.out.println(
								"***********************************************************Navigated to home page4");
						if (ValidationFactory.isElementPresent(homepageDealerPath)) {
							result = "System should be navigated to the HOME page";
						} else {
							result = "System is NOT navigating to the HOME page";
						}
						System.out.println(result);
						/* Select the site and save it on My Preference modal window */
						WaitFactory.FluentWaitByWebElement(wbelMyPreferenceUtilityMenu);
//						Thread.sleep(1000);
						navigateToMyPreferences();
						WaitFactory.waitforelementToinvisibile(wbelMyPreferenceModalSaveButton);
						WaitFactory.waitForPageLoaded();
//						Thread.sleep(2000);
						System.out.println(
								"***********************************************************Navigated to My Preference page5");
						SelectedSite = strOptionSelected.getText();
						System.out.println("***********************************************************************"
								+ SelectedSite);
						if (siteToBeSelected.equalsIgnoreCase(SelectedSite)) {
							strFlag = "Pass";
							result = "The site that is selected and saved is displayed as selected on My Preference modal window."
									+ siteToBeSelected + " is selected site";
						} else {
							strFlag = "Fail";
							result = "The site that is NOT selected and saved is displayed as selected on My Preference modal window.";
						}
						if (counter == 1) {
							merger = false;
						}
						if (onDept) {
							counter1 = 1;
							merger = false;
							counterfail++;
						} else {
							counter1 = 1;
							merger = false;
							counterfail++;
						}
						strFlag = "Fail";
						result = "";
						navigateToHomepage();
						WaitFactory.waitForPageLoaded();
						WaitFactory.FluentWaitByWebElement(strHomeDealer);
						homepageDealerPath.click();
						System.out.println(
								"***********************************************************Navigated to home page6");
						currentURL = MyPreferencesDriver.getCurrentUrl();
						String valueOfSelectedSite = site.get(siteToBeSelected);
						if (currentURL.contains(valueOfSelectedSite)) {
							if (valueOfSelectedSite.equalsIgnoreCase("Home/DealerPath")) {
								valueOfSelectedSite = "R2 JDIN";
							}
							strFlag = "Pass";
							ableToAccess = ableToAccess + "The URL for " + valueOfSelectedSite + " is displayed</br>";
						} else {
							if (valueOfSelectedSite.equalsIgnoreCase("Home/DealerPath")) {
								valueOfSelectedSite = "R2 JDIN";
							}
							strFlag = "Fail";
							notableToAccess = notableToAccess + "The URL for " + valueOfSelectedSite
									+ " is NOT displayed</br>";
						}
						if (counter == 1 || counter1 == 1) {
							merger = false;
						}

					}
					// Select the site that was selected before the execution of the method.
					navigateToMyPreferences();
					preferredSite.selectByVisibleText(PreferedSite);
					wbelMyPreferenceModalSaveButton.click();
					Thread.sleep(500);
					WaitFactory.waitForPageLoaded();
					MyPreferencesDriver.switchTo().defaultContent();
					WaitFactory.waitForPageLoaded();
					Thread.sleep(500);
					if (notableToAccess.equalsIgnoreCase("")) {
						if (onDept) {
							strFlag = "Pass";
							ReportFactory.reporterOutputMerge(strTCID,
									"Verify that the respective URLs for " + list.toString()
											+ " are displayed from department page.",
									list.toString(), "The respective URL for all Sites should be displayed ",
									ableToAccess + "</br>" + notableToAccess, strFlag, merger, colspan);
						} else {
							strFlag = "Pass";
							ReportFactory.reporterOutputMerge(strTCID,
									"Verify that the respective URLs for " + list.toString() + " are displayed ",
									list.toString(), "The respective URL for all Sites should be displayed ",
									ableToAccess + "</br>" + notableToAccess, strFlag, merger, colspan);
						}
					} else {
						if (onDept) {
							strFlag = "Fail";
							ReportFactory.reporterOutputMerge(strTCID,
									"Verify that the respective URLs for " + list.toString()
											+ " are displayed from department page.",
									list.toString(), "The respective URL for all Sites should be displayed ",
									ableToAccess + "</br>" + notableToAccess, strFlag, merger, colspan);
						} else {
							strFlag = "Fail";
							ReportFactory.reporterOutputMerge(strTCID,
									"Verify that the respective URLs for " + list.toString() + " are displayed ",
									list.toString(), "The respective URL for all Sites should be displayed ",
									ableToAccess + "</br>" + notableToAccess, strFlag, merger, colspan);
						}
					}
				} else {
					if (onDept) {
						counter = 1;

						ReportFactory.reporterOutputMerge(strTCID,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) from department page.",
								strExpectedValue,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) ",
								result + "List from Application : " + siteListFromApplication, strFlag, merger, 4);
						counterfail++;
						navigateToHomepage();
//						Thread.sleep(1000);
						System.out.println("navigateToHomepage");
					} else {
						counter = 1;
						ReportFactory.reporterOutputMerge(strTCID,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) ",
								strExpectedValue,
								"Verify all accessible sites should be displayed in the preferred site dropdown of 'My Preferences' modal window for employees.(for all sites) ",
								result + "List from Application : " + siteListFromApplication, strFlag, merger, 4);
						counterfail++;
						navigateToHomepage();
//						Thread.sleep(1000);
						System.out.println("navigateToHomepage");
					}
				}
			} else {
				result = "Dealer do not have permissions to access preferred site";
				strFlag = "Pass";
				if (onDept) {
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window from department page, Url changes accordingly",
							"NA ", "The respective URL for should be displayed ", result, strFlag, merger, 4);
					navigateToHomepage();
					System.out.println("navigateToHomepage");
					counterfail++;
				} else {
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify that by changing the preferred site from the preferred site drop down of My Preferences modal window, Url changes accordingly",
							"NA ", "The respective URL for should be displayed ", result, strFlag, merger, 4);
					navigateToHomepage();
					System.out.println("navigateToHomepage");
					counterfail++;
				}
			}
		} catch (Exception e) {
			strFlag = "Fail";
			System.out.println(e.getMessage().toString());
			// GenericFactory.navigateToHomePage();
			WaitFactory.WaitForElementToVisible(navigateToHomepage);
			navigateToHomepage.click();
			System.out.println("navigateToHomepage is clicked");
			if (onDept) {
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify that the URL is displayed with the expected contents from department page.",
						list.toString(), "Verify that the URL is displayed with the expected contents",
						"Element is not visible.URL Verification failed for : " + RunningURL, strFlag, merger, colspan);
			} else {
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify that the URL is displayed with the expected contents", list.toString(),
						"Verify that the URL is displayed with the expected contents",
						"Element is not visible.URL Verification failed for : " + RunningURL, strFlag, merger, colspan);
			}
		}
	}

	/**
     * @author      Bhavika Solanki
     * 
     * This method returns language for specific language code
	 * 
     * @param code       	    	  Language code
	 * @throws              		  Throwable 
     * @exception					  Exception
     * @returns 					  Language
     */
	
	static String returnLang;

	public static String returnLanguageUsingCode(String code) throws Exception {
		// Map for language
		HashMap<String, String> language = new HashMap<String, String>();
		language.put("fr_CA", "French");
		language.put("es_MX", "Spanish");
		language.put("ru_RU", "Russian");
		language.put("fr_FR", "French");
		language.put("de_DE", "German");
		language.put("en_GB", "English");
		language.put("en_US", "English");
		returnLang = language.get(code);
		return returnLang;

	}


	/**
     * @author      Bhavika Solanki
     * 
     * This method Navigates to Home page by clicking on cancel button on my preference modal window.
	 * 
	 * @throws              		  Exception 
     */
	public static void navigateToHomepage() throws Exception {
		WaitFactory.waitForElement(wbelMyPreferenceModalCancelButton);
		wbelMyPreferenceModalCancelButton.click();
		Thread.sleep(500);
		System.out.println("navigateToHomepage");
		MyPreferencesDriver.switchTo().defaultContent();
		WaitFactory.waitForElement(wbelHomeFav);
		Thread.sleep(500);
	}
	/**
     * @author      Bhavika Solanki
     * 
     * This method Navigates to My Preference modal window.
	 * 
	 * @throws              		  Exception 
     */

	public static void navigateToMyPreferences() throws Exception {
		WaitFactory.waitForElement(wbelMyPreferenceUtilityMenu);
		Thread.sleep(3000);
		wbelMyPreferenceUtilityMenu.click();
		WaitFactory.waitForElement(wbelMyPreference);
		wbelMyPreference.click();
		Thread.sleep(3000);
		System.out.println("navigateToMyPreferences");
		Thread.sleep(500);
	}

	/**
     * @author      Bhavika Solanki
     * 
     * Verify Pop up message when all the departments are  for My prefernces page.
     * 
     * @param strTCID        strTCID is mentioned in the test data that will be reflected in the report.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	
	public static void MyPreferencesCheckboxValidation(String strTCID) throws Throwable {
		String strFlag = "Fail";
		try {
			navigateToMyPreferences();
//			Thread.sleep(3000);
			System.out.println("MyPreferencesCheckboxValidation");
			WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalSaveButton);
			System.out.println("Print temList Size: " + MypreferencescheckboxType.size());
			for (int k = 0; k < MypreferencescheckboxType.size(); k++) {
				if (MypreferencescheckboxType.get(k).getAttribute("checked") != null) {
					MypreferencescheckboxType.get(k).click();
					WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalSaveButton);
				}
			}
			WaitFactory.FluentWaitByWebElement(wbelMyPreferenceModalSaveButton);
			wbelMyPreferenceModalSaveButton.click();
			try {
				Alert pop = BaseClass.wbDriver.switchTo().alert();
				pop.accept();
				navigateToHomepage();
				strFlag = "Pass";
				LogFactory.info("Validating Error message for my preferences page");
				ReportFactory.reporterOutput(strTCID,
						"Verify that at least one department needs to be selected in My Preferences window  and pop up should be displayed on unchecking all the department.",
						"NA",
						"Pop up with the Error message: 'At least one department should be selected' should be displayed on unchecking all the department on My Preference modal window.",
						"Alert Pop up with the Error message: 'At least one department should be selected' is displayed on uncheck of all the departments on My Preference modal window.",
						strFlag);
			} catch (Exception e) {
				ReportFactory.reporterOutput(strTCID,
						"Verify that at least one department needs to be selected in My Preferences window  and pop up should be displayed on unchecking all the department.",
						"NA",
						"Pop up with the Error message: 'At least one department should be selected' should be displayed on unchecking all the department on My Preference modal window.",
						"Alert Pop up with the Error message: 'At least one department should be selected' is NOT displayed on uncheck of all the departments on My Preference modal window.",
						strFlag);
				if(!ValidationFactory.isElementPresent(wbelHomeFav)) {
					navigateToHomepage();
					System.out.println("Navigated to Home page.");
					WaitFactory.FluentWaitByWebElement(wbelHomeFav);
				}
			}
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify that at least one department needs to be selected in My Preferences window  and pop up should be displayed on unchecking all the department.",
					"NA",
					"Pop up with the Error message: 'At least one department should be selected' should be displayed on unchecking all the department on My Preference modal window.",
					"Alert Pop up with the Error message: 'At least one department should be selected' is NOT displayed on uncheck of all the departments on My Preference modal window.",
					strFlag);
			if(!ValidationFactory.isElementPresent(wbelHomeFav)) {
				navigateToHomepage();
				System.out.println("Navigated to Home page.");
				WaitFactory.FluentWaitByWebElement(wbelHomeFav);
			}
		}
	}
	/**
     * @author      Bhavika Solanki
     * 
     * Verify language preferences functionality : list of languages should be displayed as expected.
     * 
     * @param strTCID       		  strTCID is mentioned in the test data that will be reflected in the report.
     * @param strExpectedValue        List of languages from test data.
	 * @throws              		  Throwable 
     * @exception					  Exception
     */
	public static void URLVerification(String strTCID, String strExpectedValue) throws Throwable {
		try {
			String URL = MyPreferencesDriver.getCurrentUrl();
			String[] URLSite = URL.split("/");
			String URLSiteReturned = getSiteName(URLSite[7]);
			if (URLSite[7].equalsIgnoreCase("DealerPath")) {
				if (!strExpectedValue.equalsIgnoreCase("None")) {
					LogFactory.beginTestCase(
							"Verify language preferences functionality : list of languages for R2 JDIN should be displayed.");
					myPreferenceLangVerification(strTCID, URLSiteReturned, "R2 JDIN");
				}
			}
			if (URLSite[7].equalsIgnoreCase("R4Home")) {
				if (!strExpectedValue.equalsIgnoreCase("None")) {
					LogFactory.beginTestCase(
							"Verify language preferences functionality : list of languages for R4 USA/Canada should be displayed.");
					myPreferenceLangVerification(strTCID, URLSiteReturned, "R4 USA/Canada");
				}
			}
			if (URLSite[7].equalsIgnoreCase("R3HispanoHome")) {
				if (!strExpectedValue.equalsIgnoreCase("None")) {
					LogFactory.beginTestCase(
							"Verify language preferences functionality : list of languages for R3 Hispano should be displayed.");
					myPreferenceLangVerification(strTCID, URLSiteReturned, "R3 Hispano");
				}
			}
			if (URLSite[7].equalsIgnoreCase("R3BrasilHome") | URLSite[7].equalsIgnoreCase("R1IndiaHome")
					| URLSite[7].equalsIgnoreCase("R4AustraliaHome") | URLSite[7].equalsIgnoreCase("R1ThailandHome")
					| URLSite[7].equalsIgnoreCase("R1ChinaHome") | URLSite[7].equalsIgnoreCase("R1SSAHome")
					| URLSite[7].equalsIgnoreCase("R1AsiaHome"))
				if (!strExpectedValue.equalsIgnoreCase("None")) {
					LogFactory.beginTestCase(
							"Verify language preferences functionality : list of languages for other sites should be displayed.");
					myPreferenceLangVerification(strTCID, "English,FranÃ§ais,EspaÃ±ol");
				}
		} catch (Exception e) {
			System.out.println("I am in catch block");
		}
	}

	public static String getSiteName(String URLSite) {
		Map<String, String> RegionToLang = new HashMap<String, String>();
		RegionToLang.put("R3BrasilHome", "NA");
		RegionToLang.put("R1IndiaHome", "NA");
		RegionToLang.put("R4AustraliaHome", "NA");
		RegionToLang.put("R1ThailandHome", "NA");
		RegionToLang.put("R1ChinaHome", "NA");
		RegionToLang.put("R3HispanoHome", "English,Spanish");
		RegionToLang.put("R1SSAHome", "NA");
		RegionToLang.put("DealerPath", "English,Russian,French,German");
		RegionToLang.put("R4Home", "English,French,Spanish");
		RegionToLang.put("R1AsiaHome", "NA");
		URLSite = RegionToLang.get(URLSite);

		System.out.println(RegionToLang.entrySet());
		return URLSite;
	}

	/**
     * @author      Shrey Choudhary
     *  
     * Verify RACKF groups available on 'Preferred Site' dropdown from 'My Preferrences' modal window.
     * 
     * @param strTCID        strTCID is reflected in the report.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	public static void verifyPrefferedSiteOfUser(String strTCID) throws Throwable {
		try {
			String strFlag = "Pass";
			String strResult = "This scenario is not valid for this user because 'Preferred Site' dropdown is not available.";
			String strActualResult = "Preferred site dropdown should have values related to RACKF groups available in 'Analyse User' section.";
			List<String> listOfSitesFromMyPreferences = new ArrayList<String>();
			
			//racf grps from analyse user
			List<String> listOfRackfGroupFromAnalyseUser = GenericFactory.rackfGroupsMap();
			
			//navigate to my preferences
			GenericFactory.utilityMenuMyPreferenceClick();
			Thread.sleep(3000);
			List<WebElement> test = BaseClass.wbDriver.findElements(By.xpath(".//*[@id='site']/option"));
			if (!(test.size() == 0)) {
				for (int i = 0; i < test.size(); i++) {
					listOfSitesFromMyPreferences.add(test.get(i).getAttribute("value"));
				}
				if (listOfSitesFromMyPreferences.size() > 1) {
					if (!(listOfRackfGroupFromAnalyseUser == null)) {
						if (listOfSitesFromMyPreferences.containsAll(listOfRackfGroupFromAnalyseUser)
								&& listOfSitesFromMyPreferences.size() == listOfRackfGroupFromAnalyseUser.size()) {
							strFlag = "Pass";
							strResult = "List from analyse user and preferred site dropdown are matched."
									+ "</br><B>Available preferred site in dropdown :</B>"
									+ String.join(", ", listOfSitesFromMyPreferences);
							strActualResult = strActualResult
									.concat("</br><B>Available preferred site from Analyse User are :</B>"
											+ String.join(", ", listOfRackfGroupFromAnalyseUser));
						} else {
							ArrayList<String> al3 = new ArrayList<String>();
							for (String temp : listOfSitesFromMyPreferences)
								if (!listOfRackfGroupFromAnalyseUser.contains(temp)) {
									al3.add(temp);
								}
							for (String temp : listOfRackfGroupFromAnalyseUser)
								if (!listOfSitesFromMyPreferences.contains(temp)) {
									al3.add(temp);
								}
							strFlag = "Fail";
							strActualResult = "List from analyse user and preferred site dropdown are not matched."
									+ "</br><B>Available preferred site in dropdown :</B>"
									+ String.join(", ", listOfSitesFromMyPreferences)
									+ "</br><B>Available preferred site from Analyse User are :</B>"
									+ String.join(", ", listOfRackfGroupFromAnalyseUser);
							strResult = "</br><B>Non matched sites are : </B>" + String.join(", ", al3);
						}
					} else {
						strFlag = "Fail";//
						strResult = "No RACKF groups are matched of user with Analyse user screen."
								+ "</br><B>Available preferred site in dropdown :</B>"
								+ String.join(", ", listOfSitesFromMyPreferences);
						strActualResult = strActualResult
								.concat("</br><B>Available preferred site from Analyse User are :</B>"
										+ String.join(", ", listOfRackfGroupFromAnalyseUser));
					}
				} else {
					strResult = "In the prefered site drop down on My Preferences modal window, there is only one value.";

				}
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify RACKF groups available on 'Preferred Site' dropdown from 'My Preferrences' modal window.",
					"<B>Rackf Group from 'Analyse User' are :</br></B>"
							+ String.join(", ", BaseClass.analyzerUserMap.get("Security Groups")),
					strActualResult, strResult, strFlag);
			navigateToHomepage();
			// WebElement crossButton =
			// WaitFactory.waitForElement(BaseClass.wbDriver.findElement(By.cssSelector(
			// "#my-preferences > div.modal-dialog > div.modal-content > div.modal-header >
			// button.close")));
			// if (crossButton != null) {
			// crossButton.click();
			// Thread.sleep(5000);
			// }
		} catch (Exception e) {
			System.out.println("Exception Occurred>>>>>>>>" + e);
		}
	}
	/**
     * @author      Archana Gaikwad
     * 
     * Verify switch sites functionality.
     * 
     * @param strTCID        strTCID is reflected in the report.
     * @param strRACFID      User for which the scenario is executed.
	 * @throws               Throwable 
     * @exception			 Exception
     */
	// Verify Switch Site functionality.
	public static void verifySwitchSite(String TCID, String strRACFID) throws Throwable {
		try {
			String userSiteCodeInfo = null;
			String result = "Site Shown in analyse User info screen is not matching with Site Value from Preference.";
			String status = "Fail";
			String flag = "Fail";

			LinkedHashMap<String, String> userSiteCode = BaseClass.siteRegionCode();
			List<String> actualuserSiteCode = analyzerUserMap.get("strSite");
			System.out.println("actualuserSiteCode>>>>>>>>>>" + actualuserSiteCode);
			if (actualuserSiteCode != null) {
				userSiteCodeInfo = actualuserSiteCode.get(0).toString();
				System.out.println(userSiteCodeInfo);
				String actualuserSiteCodeupdated = userSiteCode.get(userSiteCodeInfo);
				// GenericFactory.utilityMenuMyPreferenceClick();
				//navigate to my preferences
				navigateToMyPreferences();
				System.out.println("utilityMenuMyPreferenceClick");
				Thread.sleep(1000);
				Select sitePreferredDefault = new Select(BaseClass.wbDriver.findElement(By.xpath("//*[@id='site']")));
				Thread.sleep(1000);
				System.out.println("sitePreferredDefault");
				//get the selected site
				WebElement SitePreferred = sitePreferredDefault.getFirstSelectedOption();
				String sitePrefer = SitePreferred.getText();
				System.out.println("sitePrefer>>>>>>>>>>>" + sitePrefer);
				Thread.sleep(1000);
				BaseClass.wbDriver.findElement(By.xpath("//*[@id='preference-save']")).click();
				System.out.println("save");
				Thread.sleep(1000);
				//click on Switch site menu from the utility links
				GenericFactory.utilityMenuSwitchSiteClick();
				Select switchSite = new Select(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='switchSite']")));
				System.out.println("switchSite");
				WebElement switchSiteOption = switchSite.getFirstSelectedOption();
				@SuppressWarnings("unused")
				//get the site which is selected in the switch site
				String switchSiteValue = switchSiteOption.getText();
				Thread.sleep(1000);
				System.out.println("switchSiteValue");
				if (switchSite.getOptions().size() <= 1) {
					result = "There is only one value in the Switch Site Drop Down";
					ReportFactory.reporterOutput(TCID, "Verify switch sites functionality.", "NA",
							"The default setting of the current site (preferred site) should be employee default site region.",
							result, "Pass");
				} else {
//select other site and save
					switchSite.selectByIndex(2);
					System.out.println("selected...");
					Thread.sleep(500);
					BaseClass.wbDriver.findElement(By.xpath(".//*[@id='switch-save']")).click();
					Thread.sleep(1000);
					//end impersonate the racf id
					GenericFactory.endImpersonateOrLogoutUser();
					Thread.sleep(1000);
					//impersonate the racf id and navigate to the my preference modal window
					ImpersonateUser.impersonateUserSuccess(strRACFID);
					Thread.sleep(1000);
					GenericFactory.utilityMenuMyPreferenceClick();
					Thread.sleep(1000);
					
					//get the first selected site 
					Select sitePreferredDefaultAfterLogin = new Select(
							BaseClass.wbDriver.findElement(By.xpath(".//*[@id='site']")));
					WebElement SitePreferredAfterLogin = sitePreferredDefaultAfterLogin.getFirstSelectedOption();
					String sitePreferAfterLogin = SitePreferredAfterLogin.getText();
					//If site after login is same as the site which was previously saved then pass
					if (sitePreferAfterLogin.equalsIgnoreCase(sitePrefer)
							&& sitePrefer.equalsIgnoreCase(actualuserSiteCodeupdated)) {
						System.out.println("Pass");
						result = "Site Shown in analyse User info screen is matching with Site Value from Preference screen and Switch site Screen.";
						flag = "Pass";

					}
					ReportFactory.reporterOutput(TCID, "Verify switch sites functionality.", userSiteCodeInfo,
							"The default setting of the current site (preferred site) should be employee default site region.",
							result, flag);
				}
			} else if (!(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='switchSite']")).isDisplayed())
					&& actualuserSiteCode == null) {
				ReportFactory.reporterOutput(TCID, "Verify switch sites functionality.", "NA",
						"The default setting of the current site (preferred site) should be employee default site region.",
						"Switch Site Option is not available for this User.", "Pass");
			} else {
				ReportFactory.reporterOutput(TCID, "Verify switch sites functionality.", userSiteCodeInfo,
						"The default setting of the current site (preferred site) should be employee default site region.",
						result, flag);
			}
		} catch (Exception e) {
			String er = e.getMessage().substring(0, 60).toString().trim();

			ReportFactory.reporterOutput(TCID, "Verify switch sites functionality.", "NA", "NA", er, "Fail");
		}
	}

}