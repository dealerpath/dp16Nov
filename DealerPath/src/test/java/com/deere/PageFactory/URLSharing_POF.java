package com.deere.PageFactory;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.EndImpersonateUser;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.ImpersonateUser;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;

import net.bytebuddy.description.type.TypeDescription.Generic;

public class URLSharing_POF extends BaseClass {

	static WebDriver locDriver;

	public URLSharing_POF(WebDriver driver) {
		this.locDriver = driver;

	}

	@FindBy(how = How.ID, using = "endimpersonatelink")
	static WebElement wbelEndImpersonatelink;
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"wpthemeControlBody wpthemeOverflowAuto wpthemeClear\"]/div[2]")
	static WebElement wbelErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//*[@id='links-target']/div[2]/div/div[1]/div[6]/a")
	static WebElement linkValues;
	
	@FindBy(how = How.XPATH, using = "//*[@id='main-header']/div[1]//h1")
	static WebElement strHomeDealer;
	
	@FindBy(how = How.XPATH, using = "//div[@id='js-segments']")
	static WebElement wbelproductsegmenticon;
	
	@FindBy(how = How.ID, using = "js-country-group")
	static WebElement wbelCountrySegmentIcon;
	
	@FindBy(how = How.XPATH, using = "//*[@id='countryGroups']/../div")
	static List<WebElement> wbelCountryGroups;
	
	@FindBy(how = How.ID, using = "countryGroups")
	static List<WebElement> wbelCountryCheckBox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='countryGroups'][@checked='checked']")
	static List<WebElement> wbelCountryCheckedCheckBox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='countryGroups'][@checked='checked']/../div")
	static List<WebElement> wbelCountryCheckedCountry;
	
	@FindBy(how = How.ID, using = "country-grouping-save")
	static WebElement wbelApplyFilter;
	
	@FindBy(how = How.ID, using = "site")
	static WebElement wbelMyPreferencePreferredSite;
	
	@FindBy(how = How.ID, using = "preference-save")
	static WebElement wbelMyPreferenceModalSaveButton;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='links-filter']")
	static WebElement filterbox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Analyze_User']")
	static WebElement AnalyzeUser;
	
	@FindBy(how = How.XPATH, using = "//*[@id='analyseUserId']")
	static WebElement AnalyzeUserTextBox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='analyzeUserButton']")
	static WebElement analyzeUserButton;
	
	@FindBy(how = How.XPATH, using = "//*[normalize-space(text()) = 'Security Groups']/../following-sibling::td")
	static WebElement securityGrps;
	
		
//	static Map<String, List<String>> userAnalyzeInformationMap = GenericFactory.userAnalyzeInformation();
	static boolean strNoWcm = false;
/*	public static void ValidateURLsuchitra(String strTCID,String strExpectedValue, String PageName) throws Throwable {

		String strFlag = "Fail";
	
		String strResult;
		String url = "Initial";

		try {
			String[] arrSplit = strExpectedValue.split(",");
			String[] arrURL = new String[2];
			for (int i = 0; i < arrSplit.length; i++) {
				String strExpectedUser = arrSplit[i];
				ImpersonateUser.impersonateUserSuccess(strExpectedUser);
//first dealer
				if (i == 0) {
					// Add the reporter for record not found
					List<LinkedHashMap> userWCMContent = ExcelFactory
							.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
					if (userWCMContent.isEmpty()) {
						strFlag = "Pass";
						ReportFactory.reporterOutput("URL Sharing " + PageName, "Verify URL sharing." + PageName,
								"" + strExpectedValue, "Verify that URL Sharing should be successful." + PageName,
								"No entries for respective country and product are available", strFlag);
						strNoWcm = true;
						EndImpersonateUser.endImpersonateOrLogoutUser();
						break;

					}
					GenericFactory.navigateToIndexPage(userWCMContent.get(0));
					url = locDriver.getCurrentUrl();

				} 
				// second dealer
				else {
					locDriver.get(url);
				}

				System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + url);
				
				
				String errorMsg = wbelErrorMsg.getText().toString();
				strFlag = "Pass";
				System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
				ReportFactory.reporterOutput(strTCID +" " + PageName, "Verify URL sharing.",
						strExpectedValue,
						"Verify that URL Sharing should be successful.",
						"URL sharing is successful "+ errorMsg, strFlag);
				wbelEndImpersonatelink.click();
				Thread.sleep(1000);
							
				try {
					WebElement element = locDriver.findElement(By.xpath("//a[@id='left_nav_2']"));
					arrURL[i] = new String(element.getText());
					System.out.println(" URL Title For >>>>>>>>>>>>>>>>>>>  " + arrURL[i]);
				} catch (NotFoundException e) {
					strFlag = "Fail";
					System.out.println("URL Title is missing");
					ReportFactory.reporterOutput("URL Sharing " + PageName, "Verify that expected Department " + PageName,
							"" + strExpectedValue, "URL Title is missing " + PageName, "Department is disabled.",
							strFlag);
				}
				EndImpersonateUser.endImpersonateOrLogoutUser();
			}
			if (!strNoWcm) {
				if (arrURL[0].equals(arrURL[1])) {
					System.out.println("URL Sharing is successful");
					strFlag = "Pass";
					strResult = "URL sharing successful";
					ReportFactory.reporterOutput("URL Sharing " + PageName, "Verify URL Sharing " + PageName,
							"" + strExpectedValue, "Verify URL Sharing " + PageName, strResult, strFlag);
				} else {
					strFlag = "Fail";
					strResult = "URL sharing unsuccessful";
					System.out.println("URL Sharing is unsuccessful and titles do not match");
					ReportFactory.reporterOutput("URL Sharing " + PageName, "Verify URL Sharing " + PageName,
							"" + strExpectedValue, "Verify URL Sharing " + PageName, strResult, strFlag);
				}
			}

		} catch (NotFoundException e) {
			strFlag = "Fail";
			strResult = "URL sharing unsuccessful";
			System.out.println("URL Sharing is unsuccessful");
			ReportFactory.reporterOutput("URL Sharing " + PageName, "Verify URL Sharing " + PageName,
					"" + strExpectedValue, "Verify URL Sharing " + PageName, e.getMessage().toString(), strFlag);
			EndImpersonateUser.endImpersonateOrLogoutUser();
		}

	}
	static int colspan = 1;static boolean merge = true; 
	static String WCMId,strTitle;static String PageName,putURL,putURLAndNavigationPath;
	public static void ValidateURL(String strTCID, String strDealer1)
			throws Throwable {

		Map<String, String> URLForAllIndexPages = new HashMap<String, String>();
		colspan = 8;
		boolean merger = true;
		try {
			strFlag = "Fail";
			PageName = "AT-Index Page";
			// ImpersonateUser.impersonateUserSuccess(strDealer1);
			putURLAndNavigationPath = getURLForPageName(strTCID, strDealer1,  PageName);
//			String [] urlAndNavigation = putURLAndNavigationPath.split("$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println(putURLAndNavigationPath);
			URLForAllIndexPages.put("AT-Index Page", putURLAndNavigationPath);

			// To navigate to home page
			GenericFactory.navigateToHomePage();
			PageName = "AT-ChildIndex Page";
			putURL = getURLForPageName(strTCID, strDealer1,  PageName);
			System.out.println(putURLAndNavigationPath);
			URLForAllIndexPages.put("AT-ChildIndex Page", putURLAndNavigationPath);

			// To navigate to home page
			GenericFactory.navigateToHomePage();
			PageName = "AT-GrandChild Index Page";
			putURL = getURLForPageName(strTCID, strDealer1,  PageName);
			System.out.println(putURLAndNavigationPath);
			URLForAllIndexPages.put("AT-GrandChild Index Page", putURLAndNavigationPath);

			// To navigate to home page
			GenericFactory.navigateToHomePage();
			PageName = "AT-Rich Text";
			putURL = getURLForPageName(strTCID, strDealer1,  PageName);
			System.out.println(putURLAndNavigationPath);
			URLForAllIndexPages.put("AT-Rich Text", putURLAndNavigationPath);

			// End impersonate the first user
			GenericFactory.EndImpersonateUSER();
			// Impersonate with second dealer
			ImpersonateUser.impersonateUserSuccess(strDealer1);

			// for AT-Index Page
			PageName = "AT-Index Page";
			if(!URLForAllIndexPages.get("AT-Index Page").isEmpty()) {
			VerifyURLForDealer2(strTCID, strDealer1, URLForAllIndexPages, PageName);}
			else {
				List<LinkedHashMap> userWCMContentIndex = ExcelFactory
						.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
				if(userWCMContentIndex.size()<=0) {
					strFlag = "Pass";
					
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify URL sharing for AT-Index Page",
							"URL Path : "+ BaseClass.PORTLET_LINKFLAG +" WCM ID : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
							"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
							"No entries for respective country and product are available", strFlag,merger,8);
					merger=false;
				}else {
				strFlag = "Fail";
				ReportFactory.reporterOutputMerge(strTCID, "Verify URL sharing for AT-Index Page",
						"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
						"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
						"Invalid URL : link is not present.", strFlag,merger,8);
				merger=false;
			}}
			
			// To navigate to home page
			GenericFactory.navigateToHomePage();

			// for AT-ChildIndex Page
			PageName = "AT-ChildIndex Page";
			if(!URLForAllIndexPages.get("AT-ChildIndex Page").isEmpty()) {
			VerifyURLForDealer2(strTCID, strDealer1, URLForAllIndexPages, PageName);}
			else {List<LinkedHashMap> userWCMContentIndex = ExcelFactory
					.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
			if(userWCMContentIndex.size()<=0) {
				strFlag = "Pass";
				
				ReportFactory.reporterOutputMerge(strTCID,
						"Verify URL sharing for AT-ChildIndex Page",
						"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
						"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
						"No entries for respective country and product are available", strFlag,merger,8);
			}else {
				strFlag = "Fail";
				ReportFactory.reporterOutputMerge(strTCID, "Verify URL sharing for AT-ChildIndex Page",
						"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
						"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
						"Invalid URL : link is not present.", strFlag,merger,8);
			}}
			// To navigate to home page
			GenericFactory.navigateToHomePage();

			// for AT-GrandChild Index Page
			PageName = "AT-GrandChild Index Page";
			if(!URLForAllIndexPages.get("AT-GrandChild Index Page").isEmpty()) {
			VerifyURLForDealer2(strTCID, strDealer1, URLForAllIndexPages, PageName);}
			else {
				List<LinkedHashMap> userWCMContentIndex = ExcelFactory
						.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
				if(userWCMContentIndex.size()<=0) {
					strFlag = "Pass";
					
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify URL sharing for AT-GrandChild Index Page",
							"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
							"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
							"No entries for respective country and product are available", strFlag,merger,8);
				}else {
				strFlag = "Fail";
				ReportFactory.reporterOutputMerge(strTCID , "Verify URL sharing for AT-GrandChild Index Page",
						"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
						"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
						"Invalid URL : link is not present.", strFlag,merger,8);
			}}
			// To navigate to home page
//			GenericFactory.navigateToHomePage();

			// for AT-GrandChild Index Page
			PageName = "AT-Rich Text";
			if(!URLForAllIndexPages.get("AT-Rich Text").isEmpty()) {
			VerifyURLForDealer2(strTCID, strDealer1, URLForAllIndexPages, PageName);}
			else {
				List<LinkedHashMap> userWCMContentIndex = ExcelFactory
						.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
				if(userWCMContentIndex.size()<=0) {
					strFlag = "Pass";
					
					ReportFactory.reporterOutputMerge(strTCID,
							"Verify URL sharing for AT-Rich Text",
							"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
							"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
							"No entries for respective country and product are available", strFlag,merger,8);
				}else {
				strFlag = "Fail";
				ReportFactory.reporterOutputMerge(strTCID,"Verify URL sharing for AT-Rich Text",
						"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
						"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
						"Invalid URL : link is not present.", strFlag,merger,8);
			}}
			// To navigate to home page
			GenericFactory.navigateToHomePage();
//			GenericFactory.EndImpersonateUSER();changed
		}

		catch (Exception e) {
			strFlag = "Pass";
			System.out.println("URL Sharing is unsuccessful");
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify URL Sharing.",
					"URL Path : "+" URL Sharing from USER-1 : " + strUserRACFID + " URL Sharing to USER-2 : " + strDealer1,
					"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
					"Both dealers are able to access the URL.", strFlag);
//			GenericFactory.EndImpersonateUSER();changed

		}
	}
public static String [] urlAndnavigation;
	private static void VerifyURLForDealer2(String strTCID, String strDealer1, Map<String, String> URLForAllIndexPages,String PageName)
			throws Throwable {
		colspan = 8;merge = false;
		try {
		putURL = URLForAllIndexPages.get(PageName);
		if(!putURL.equalsIgnoreCase("")) {
		if(putURL.contains("doc")||putURL.contains("pdf")) {
			strFlag = "Pass";
			ReportFactory.reporterOutput(strTCID, "Verify URL sharing for "+PageName,
					"URL Path : "+ BaseClass.PORTLET_LINKFLAG+

					"URL Sharing from USER-1 : "+strUserRACFID+
					"URL Sharing to USER-2 : "+strDealer1
 ,
					"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
					"The URL refers to a PDF/Document.",
					strFlag);
//				EndImpersonateUser.endImpersonateOrLogoutUser();
//			GenericFactory.EndImpersonateUSER();
			// Impersonate with second dealer
//			ImpersonateUser.impersonateUserSuccess(strDealer1);
//				break;
		}else {
		// add code for validating the URL sharing
			
			urlAndnavigation = putURLAndNavigationPath.split("$$$$$$$$$$$$$$$$$$$$$$");
		locDriver.get(urlAndnavigation[0]);	
		wbelEndImpersonatelink.isDisplayed();
			String errorMsg = wbelErrorMsg.getText().toString();
			strFlag = "Fail";
			System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
			ReportFactory.reporterOutputMerge(strTCID, "Verify URL sharing for "+PageName,
					"URL Path : "+urlAndnavigation[1] +

					"URL Sharing from USER-1 : "+strUserRACFID+
					"URL Sharing to USER-2 : "+strDealer1
 ,
					"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
					"The URL sharing is successful.",
					strFlag,merge,8);
			wbelEndImpersonatelink.click();
			//Impersonate with second dealer
			ImpersonateUser.impersonateUserSuccess(strDealer1);	
	}
	}else{
	}
		
			}
	catch (Exception e) {
		strFlag = "Fail";
		System.out.println("URL Sharing is unsuccessful");
		ReportFactory.reporterOutputMerge(strTCID, "Verify URL sharing for "+PageName,
				"URL Path : "+ urlAndnavigation[1]+

				"URL Sharing from USER-1 : "+strUserRACFID+
				"URL Sharing to USER-2 : "+strDealer1
,
				"If USER-2 has access to the content in terms of country tagged, product types tagged, preferred department then USER-2 should be able to navigate  to the Index Page of shared URL by USER-1",
				"The URL sharing is NOT successful.",
				strFlag,merge,8);

	}}
	static String departmentClass,DepartmentName;
	static String currentUrl;static String strFlag;
	static List<String> Dept = new ArrayList<String>();
	
	public static void ValidateURLforError(String strTCID) throws Throwable {
		Map<String, String> URLForError = new HashMap<String, String>();
		colspan = 6;merge = true;
		try {
			// ImpersonateUser.impersonateUserSuccess(strExpectedValue);
			PageName = "AT-Index Page";
			putURL=getURLForUncheckDeptError(strTCID,colspan,merge);
			URLForError.put("AT-Index Page", putURL);
			
			PageName = "AT-ChildIndex Page";
			putURL=getURLForUncheckDeptError(strTCID,colspan,merge);
			URLForError.put("AT-ChildIndex Page", putURL);
			
			PageName = "AT-GrandChild Index Page";
			putURL=getURLForUncheckDeptError(strTCID,colspan,merge);
			URLForError.put("AT-GrandChild Index Page", putURL);
			
			
			
			if(putURL!=null) {
			currentUrl = locDriver.getCurrentUrl();
			GenericFactory.uncheckDepartmentMyPreference(Dept);
			locDriver.get(putURL);
			System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + currentUrl);
			try {

				String errorMsg = wbelErrorMsg.getText().toString();
				strFlag = "Pass";
				System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						"Verify error message is displayed on hitting the URL for deactivated department.",
						strUserRACFID,
						"Verify error message should be displayed on hitting the URL for deactivated department.",
						"Error message is displayed on hitting the URL for deactivated department. Error Message : "
								+ errorMsg,
						strFlag);
				wbelEndImpersonatelink.click();
				Thread.sleep(1000);
				// ImpersonateUser.impersonateUserSuccess(strUserRACFID);
				GenericFactory.impersonateUser(strUserRACFID);
				GenericFactory.checkDepartmentMyPreference();
				Thread.sleep(1000);
				// GenericFactory.EndImpersonateUSER();
			} catch (NotFoundException e) {
				strFlag = "Fail";
				System.out.println("URL Sharing is unsuccessful");
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						"Verify error message is displayed on hitting the URL for deactivated department.",
						strUserRACFID,
						"Verify error message should be displayed on hitting the URL for deactivated department.",
						"Error message is NOT displayed on hitting the URL for deactivated department.", strFlag);
				GenericFactory.checkDepartmentMyPreference();
			}
		}}
		catch (NotFoundException e) {
			strFlag = "Fail";
			System.out.println("URL Sharing is unsuccessful");
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL for deactivated department.",
					strUserRACFID,
					"Verify error message should be displayed on hitting the URL for deactivated department.",
					"Error message is NOT displayed on hitting the URL for deactivated department.", strFlag);

//			GenericFactory.EndImpersonateUSER();
			// EndImpersonateUser.endImpersonateOrLogoutUser();
		}
	}

	public static void ValidateURLForMainandSub(String strExpectedValue, String pageName) throws Throwable {
		String strFlag;
		String strResult;
		String url = null;

		try {
			String[] arrSplit = strExpectedValue.split(",");
			String[] arrURL = new String[2];
			for (int i = 0; i < arrSplit.length; i++) {
				String strExpectedUser = arrSplit[i];
				ImpersonateUser.impersonateUserSuccess(strExpectedValue);

				if (i == 0) {
					List<LinkedHashMap> userWCMContent = ExcelFactory
							.getUserWcmDetailsAfterFilteringCountryAndProduct(pageName);
					if (userWCMContent.get(0).get("DelaerType") == BaseClass.flagDealerType) {
						GenericFactory.navigateToIndexPage(userWCMContent.get(0));
						url = locDriver.getCurrentUrl();

					}
				} else {
					locDriver.get(url);
				}

				System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + url);
				try {
					WebElement element = locDriver.findElement(By.xpath("//a[@id=\"left_nav_2\"]"));
					arrURL[i] = new String(element.getText());
					System.out.println(" URL Title For >>>>>>>>>>>>>>>>>>>  " + arrURL[i]);
				} catch (NotFoundException e) {
					strFlag = "Fail";
					System.out.println("URL Title is missing");
					ReportFactory.reporterOutput("URL Sharing " + pageName, "URL Title is missing" + pageName,
							"" + strExpectedValue, "URL Title is missing " + pageName, e.getMessage().toString(),
							strFlag);
				}
				EndImpersonateUser.endImpersonateOrLogoutUser();
			}

			if (arrURL[0].equals(arrURL[1])) {
				System.out.println("URL Sharing is successful");
				strFlag = "Pass";
				strResult = "URL sharing successful";
				ReportFactory.reporterOutput("URL Sharing " + pageName, "Verify URL Sharing " + pageName,
						"" + strExpectedValue, "Verify URL Sharing " + pageName, strResult, strFlag);
			} else {
				strFlag = "Fail";
				strResult = "URL sharing unsuccessful";
				System.out.println("URL Sharing is unsuccessful and titles do not match");
				ReportFactory.reporterOutput("URL Sharing " + pageName, "Verify URL Sharing " + pageName,
						"" + strExpectedValue, "Verify URL Sharing " + pageName, strResult, strFlag);
			}

		} catch (NotFoundException e) {
			strFlag = "Fail";
			strResult = "URL sharing unsuccessful";
			System.out.println("URL Sharing is unsuccessful");
			ReportFactory.reporterOutput("URL Sharing " + pageName, "Verify URL Sharing " + pageName,
					"" + strExpectedValue, "Verify URL Sharing " + pageName, e.getMessage().toString(), strFlag);
			EndImpersonateUser.endImpersonateOrLogoutUser();
		}

	}

	public static void ValidateURLforProductType(String strTCID, String strWCMId, String PageName)
			throws Throwable {
		String WCMId, strFlag = "Fail";
		String verificationStatement = "Verify when Index Page URL is shared for second user's preferred Product Types ";
		boolean dealerFoundInWCM = false;
		try {
			dealerFoundInWCM = false;
//			ImpersonateUser.impersonateUserSuccess(strDealer);
			List<LinkedHashMap> userWCMContentIndex = ExcelFactory
					.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
			for (int i = 0; i < userWCMContentIndex.size(); i++) {
				WCMId = userWCMContentIndex.get(i).get("Test Case ID").toString();
				if (WCMId.equalsIgnoreCase(strWCMId)) {
					dealerFoundInWCM = true;
					LinkedHashMap navigateTo = userWCMContentIndex.get(i);
					strHomeDealer.click();
					GenericFactory.navigateToIndexPage(navigateTo);
					String strWCMProducts = (String) userWCMContentIndex.get(i).get("ProductType").toString().trim();
					// URL
					String strTitle = (String) userWCMContentIndex.get(i).get("Title").toString().trim();
					try {
						WebElement link = locDriver.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
					} catch (Exception e) {
						strFlag = "Fail";
						System.out.println("URL Sharing is unsuccessful");
						
						if(PageName.equalsIgnoreCase("AT-Index Page")) {
							verificationStatement = "Verify when Index Page URL is shared for second user's non-preferred Product Type ";
						}if(PageName.equalsIgnoreCase("AT-ChildIndex Page")) {
							verificationStatement = "Verify when Child Index Page URL is shared for second user's non-preferred Product Types ";
						}if(PageName.equalsIgnoreCase("AT-GrandChild Index Page")) {
							verificationStatement = "Verify when GrandChild Index Page URL is shared for second user's preferred Product Types ";
						}
						ReportFactory.reporterOutput(strTCID,
								verificationStatement,
								"URL Path :  " + "Preferred Products Types : " + strWCMProducts,
								"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
								"In valid URL : link " + strTitle + " is not present.", strFlag);
						// EndImpersonateUser.endImpersonateOrLogoutUser();
//						GenericFactory.EndImpersonateUSER();
						break;
					}

					String strLink = locDriver.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"))
							.getAttribute("href").toString().trim();
					HttpURLConnection huc = (HttpURLConnection) (new URL(strLink).openConnection());
					java.net.URL URLTo = huc.getURL();
					String putURL = URLTo.toString();

					// To navigate to home page
					strHomeDealer.click();

					// Click on Product segment icon
					WaitFactory.FluentWaitByWebElement(wbelproductsegmenticon);

					String strwcmTestCaseID = (String) userWCMContentIndex.get(i).get("Test Case ID").toString().trim();
					String strUserDefinedCountry = (String) userWCMContentIndex.get(i).get("Dealer_Country").toString().trim();
					String strWCMCountry = (String) userWCMContentIndex.get(i).get("Country").toString().trim();
					String strUserDefinedProducts = (String) userWCMContentIndex.get(i).get("Dealer_ProductType").toString().trim();
					
					String strContenttype = (String) userWCMContentIndex.get(i).get("ContentType").toString().trim();
					String strDepartmentName = (String) userWCMContentIndex.get(i).get("DepartmentName").toString().trim();
					String strSubDepartmentName = (String) userWCMContentIndex.get(i).get("2ndLevel").toString().trim();
					strTitle = (String) userWCMContentIndex.get(i).get("Title").toString().trim();
					String strThirdLevelFolder = (String) userWCMContentIndex.get(i).get("3rdLevelFolder").toString()
							.trim();

					System.out.println(strwcmTestCaseID);
					System.out.println(strThirdLevelFolder);
					changingPrefProduct(strwcmTestCaseID, strUserDefinedCountry, strWCMCountry,
							strUserDefinedProducts, strWCMProducts, strContenttype, strDepartmentName,
							strSubDepartmentName, strThirdLevelFolder, strTitle);
					GenericFactory.navigateToHomePage();
					// add code for validating the URL sharing
					locDriver.get(putURL);

					// try {

					String errorMsg = wbelErrorMsg.getText().toString();
					strFlag = "Pass";
					System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
					ReportFactory.reporterOutput(strTCID + " " + PageName,
							verificationStatement,
							"Dealer : " + strUserRACFID + " WCM ID : " + strWCMId,
							"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
							"Error message is displayed on hitting the URL for deactivated Product Type. Error Message : "
									+ errorMsg,
							strFlag);
					wbelEndImpersonatelink.click();
					Thread.sleep(1000);
					// Impersonate to check all the products
					ImpersonateUser.impersonateUserSuccess(strUserRACFID);
					GenericFactory.checkAllProductsData();
					Thread.sleep(1000);
					break;
				}
			}
			if (dealerFoundInWCM == false) {
				strFlag = "Pass";
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						verificationStatement,
						"Dealer : " + strUserRACFID + " WCM ID : " + strWCMId,
						"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
						"WCM TCID doesnot match after filtering with Product and Country.", strFlag);
				
			}

		} catch (Exception e) {
			strFlag = "Fail";
			System.out.println("URL Sharing is unsuccessful");
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL for deactivated Product Type.",
					"Dealer : " + strUserRACFID + " WCM ID : " + strWCMId,
					"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
					"Error message is NOT displayed on hitting the URL for deactivated Product Type.", strFlag);
//			EndImpersonateUser.endImpersonateOrLogoutUser();
//			GenericFactory.EndImpersonateUSER();

		}

	}
	// Verify Links portlet on changing of preferred language.

		@SuppressWarnings("unused")
		public static void changingPrefProduct(String wcmTestCaseID, String UserDefinedCountry, String WCMCountry,
				String UserDefinedProducts, String WCMProducts, String Contenttype, String DepartmentName,
				String SubDepartmentName, String ThirdLevelFolder, String Title) throws Throwable {

			try {

				// ******************This is Department
			// Check*****************************

			LogFactory.info("Verify Department Name.");

			List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
			DepartmentName=	translatedText.get(0); 
			
			WebElement department = GenericFactory.getDeptname(DepartmentName);

			if (department != null) {
				department.click();
			

			String result = null;
			String status = null;
			String Expectedresult = null;
			boolean statusResult = false;
			String parentItemUncheck = null;
			boolean flagItemnotfound = false;

		 	List<String> listOfElements = GenericFactory.getCheckBoxValuesAll();

		 	GenericFactory.checkAllProductsData();
			
			Thread.sleep(3000);
			PortletLinksPage_POF.applyFilter.click();

			//System.out.println(listOfElements);
			int m = listOfElements.size();

			List<String> list = new ArrayList<>();
			String[] UserDefinedProductslist = UserDefinedProducts.split(",");

			for (int k = 0; k < UserDefinedProductslist.length; k++) {
				String UserDefinedProd = UserDefinedProductslist[k];
				String ParentProd = GenericFactory.getParentProduct(UserDefinedProd);
				list.add(ParentProd);

			}

			Set<String> hashsetUserdefinedList = new HashSet<String>(list);

			hashsetUserdefinedList.remove(null);
			 

			List<String> WCMProdlist = new ArrayList<>();
			String[] WCMlist = WCMProducts.split(",");

			for (int l = 0; l < WCMlist.length; l++) {
				String WCMProd = WCMlist[l];
				String ParentProd1 = GenericFactory.getParentProduct(WCMProd.trim().toString());
				WCMProdlist.add(ParentProd1);

			}
			Set<String> hashsetWCMList = new HashSet<String>(WCMProdlist);

			hashsetWCMList.remove(null);
			System.out.println("hashsetWCMList=========" + hashsetWCMList.size());
		 
			System.out.println("UserPrefernceList=========" + listOfElements.size());
			Thread.sleep(3000);
			if (!ThirdLevelFolder.equalsIgnoreCase("NA")) 
			{
				filterbox.clear();
				filterbox.sendKeys(ThirdLevelFolder);
				filterbox.sendKeys(Keys.ENTER);
		 
				
				GenericFactory.toClickOnFolder(ThirdLevelFolder,SubDepartmentName);
			 
				filterbox.clear();
			}

			int k;
			for (k = 0; k < hashsetWCMList.size(); k++) {

				if (listOfElements.size() <= hashsetWCMList.size()) {
					result = "User Defined Product preference List Product preference is less/equal than WCM Defined List so can not perform.";
					status = "Pass";
					Expectedresult = "UserDefined List :" + hashsetUserdefinedList + "WCMProduct :" + hashsetWCMList
							+ "and Link Name :" + Title;
					break;
				}

			

				Object[] myArr = hashsetWCMList.toArray();

				parentItemUncheck = myArr[k].toString();
				List<String> translatedText1 = GenericFactory.getTranslation(parentItemUncheck);
				parentItemUncheck=	translatedText1.get(0); 

				if ((listOfElements.contains(parentItemUncheck.trim())) && (listOfElements.size() > 1))

				{
					WebElement productSeg = locDriver.findElement(By.xpath(".//*[@id='productSegmentsForm']/div"));
					if (!productSeg.isDisplayed()) {

						ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
					}
					Thread.sleep(1500);
					boolean checkboxstatus = GenericFactory.productcheckstatus(parentItemUncheck);

					if (k >= hashsetWCMList.size() - 1) {
						Thread.sleep(3000);
						PortletLinksPage_POF.applyFilter.click();
						Thread.sleep(1500);
						filterbox.clear();
						filterbox.sendKeys(Title);
						filterbox.sendKeys(Keys.ENTER);
						Thread.sleep(1500);

						List<WebElement> actualHeadernameP = locDriver
								.findElements(By.xpath(".//*[@id='links-target']/div"));
						for (int s = 0; s < actualHeadernameP.size(); s++) {
							String headerNamePortlet = actualHeadernameP.get(s).getText().trim();
							String[] linesdata = headerNamePortlet.split("\n");
							String headername = linesdata[0];
							WebElement secondLevel = actualHeadernameP.get(s);

							if (SubDepartmentName.equals(headername))
							{

								break;
							}
						}
						Thread.sleep(2000);

						List<WebElement> finallink = locDriver
								.findElements(By.xpath(".//*[@id='links-target']/div/div"));

						for (int j = 0; j < finallink.size(); j++) {

							String temp1 = finallink.get(j).getText().trim();
							String[] lines = temp1.split("\n");
							for (int i = 0; i < lines.length; i++) {

								String links = lines[i];
								String Resultfound = null;

								if (links.toUpperCase().contains(Title.toUpperCase()) && hashsetWCMList.size() == 1) {

									Expectedresult = "Search criteria should not be available in the list.";
									status = "Fail";
									result = "Product Preference functionality is not working though we have unchecked product.";

								}

								else if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())
										&& hashsetWCMList.size() > 1 && k != (hashsetWCMList.size() - 1)) {

									Expectedresult = "Search criteria should be available in the list.";
									status = "Pass";
									result = "Search Criteria is available as it is mapped to multiple products.";

								}

								else if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())
										&& hashsetWCMList.size() > 1 && k == (hashsetWCMList.size() - 1)) {

									Expectedresult = "Search criteria should not be available in the list.";
									status = "Fail";
									result = "Search Criteria is available though we have unchecked all related products.";

								}

								if (links.toUpperCase().contains(Title.toUpperCase()) && hashsetWCMList.size() == 1) {

									Expectedresult = "Search criteria should not be available in the list.";
									status = "Fail";
									result = "Product Preference functionality is not working though we have unchecked product.";

								}

								else if (hashsetWCMList.size() == 1
										&& PortletLinksPage_POF.noResultfound.isDisplayed()) {

									Resultfound = PortletLinksPage_POF.noResultfound.getText();
									if (Resultfound.equalsIgnoreCase("No results found")) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Pass";
										result = "Search criteria is working fine when product is unchecked and link not available.";

									}

								}

								else if (hashsetWCMList.size() > 1 && k >= 1
										&& PortletLinksPage_POF.noResultfound.isDisplayed()) {

									if (PortletLinksPage_POF.noResultfound.isDisplayed()) {
										Resultfound = PortletLinksPage_POF.noResultfound.getText();
										
										List<String> translatedTextn = GenericFactory.getTranslation("No results found");
										 result = translatedTextn.get(0);

										if (Resultfound.equalsIgnoreCase(result)) {

											Expectedresult = "Search criteria should not be available in the list after unchecking the items.";
											status = "Pass";
											result = "Search criteria is working fine after unchecking all mapped products.";

										}
									}
								}

							}

						}

					}
				} else {
					flagItemnotfound = true;
					Expectedresult = "Search criteria should not be available in the list after unchecking the items.";
					status = "Pass";
					result = "User can not perform operation as userdefined Product Preference item/items does not match with WCM Product list.";
					LogFactory.info(result);
//					ReportFactory.reporterOutput(wcmTestCaseID,
//							"Verify Links portlet on changing of preferred product types.",
//							"UserDefined List :" + hashsetUserdefinedList + "WCMProduct :" + hashsetWCMList
//									+ " :and Link Name :" + Title,
//							Expectedresult, result, status);
					break;
				}
			}

			if (m > 1 && flagItemnotfound == false) {
//				ReportFactory.reporterOutput(wcmTestCaseID,
//						"Verify Links portlet on changing of preferred product types.", "UserDefined List :"
//								+ hashsetUserdefinedList + "WCMProduct :" + hashsetWCMList + " : and Link Name :" + Title,
//						Expectedresult, result, status);
				LogFactory.info(result);
			} else if (hashsetUserdefinedList.size() == 1)

			{
				status = "Pass";
//				ReportFactory.reporterOutput(wcmTestCaseID,
//						"Verify Links portlet on changing of preferred product types.",
//						"UserDefined List :" + hashsetUserdefinedList + "WCMProduct :" + hashsetWCMList
//						+ " :and Link Name :" + Title,"Search criteria should not be available in the list after unchecking the items.",
//						"User is not able to perform product preference as single product is available.", status);
				LogFactory.info(result);
				}

			}
		 

				else {
					 
					String  result = "As Department is Inactive/NonClickable/Visible so We can not perform.";
				String  status = "Pass";
				LogFactory.info(result);
//				ReportFactory.reporterOutput(wcmTestCaseID,
//						"Verify Links portlet on changing of preferred product types.", "NA",
//						"Search criteria should not be available in the list after unchecking the items.",
//							result, status);
						}
				
				 
		}

			catch (Exception e) {
//				ReportFactory.reporterOutput(wcmTestCaseID, "Verify Links portlet on changing of preferred product types.",
//					"NA", "Preferred Product preference should work.", e.getMessage().toString().substring(0, 250),
//					"Fail");
				LogFactory.info(e.getMessage().toString().substring(0, 250));
			}

		}
	*//**
	 * @param strTCID
	 * @param strDealer
	 * @param strWCMId
	 * @param PageName
	 * @param strSelectSite
	 * @throws Throwable
	 *//*
	public static void ValidateURLforCountry(String strTCID, String strDealer, String strWCMId, String PageName, String strSelectSite)
			throws Throwable {
		String WCMId, Country, strFlag = "Fail";boolean CountrySeg = true;
		boolean dealerFoundInWCM = false;
		
		try {
			dealerFoundInWCM = false;
			ImpersonateUser.impersonateUserSuccess(strDealer);
			if (!ValidationFactory.isElementPresent(wbelCountrySegmentIcon)) {
				myPreferencesPageFactory.navigateToMyPreferences();
				if (ValidationFactory.isElementPresent(wbelMyPreferencePreferredSite)) {
					Select site = new Select(wbelMyPreferencePreferredSite);
					site.selectByVisibleText(strSelectSite);
					wbelMyPreferenceModalSaveButton.click();
					Thread.sleep(1000);
					if (ValidationFactory.isElementPresent(wbelCountrySegmentIcon)) {
						CountrySeg = true;
					} else {
						CountrySeg = false;
					}
				}
			}

			if (CountrySeg) {
				List<LinkedHashMap> userWCMContentIndex = ExcelFactory
						.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
				if(userWCMContentIndex.size()>0) {
				for (int i = 0; i < userWCMContentIndex.size(); i++) {
					WCMId = userWCMContentIndex.get(i).get("Test Case ID").toString();
					Country = userWCMContentIndex.get(i).get("Country").toString();
					List<String> country = new ArrayList<String>(Arrays.asList(Country.split(",")));
					if (WCMId.equalsIgnoreCase(strWCMId)) {
						dealerFoundInWCM = true;
						LinkedHashMap navigateTo = userWCMContentIndex.get(i);
						strHomeDealer.click();
						GenericFactory.navigateToIndexPage(navigateTo);

						// URL
						String strTitle = (String) userWCMContentIndex.get(i).get("Title").toString().trim();
						try {
							WebElement link = locDriver
									.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
						} catch (Exception e) {
							strFlag = "Fail";
							System.out.println("URL Sharing is unsuccessful");
							ReportFactory.reporterOutput(strTCID + " " + PageName,
									"Verify error message is displayed on hitting the URL for deactivated Product Type.",
									"Dealer : " + strDealer + " WCM ID : " + strWCMId,
									"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
									"Invalid URL : link " + strTitle + " is not present.", strFlag);
							break;
						}
						String putURL = getURL(strTitle);
						
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>URL : "+putURL);

						// To navigate to home page
						strHomeDealer.click();
						Thread.sleep(1000);
						List<String> parentCountry = GenericFactory.getParentCountryName(country);
						wbelCountrySegmentIcon.click();

						countryUncheck(parentCountry);
						wbelApplyFilter.click();
						Thread.sleep(1000);

						locDriver.get(putURL);
						String errorMsg = wbelErrorMsg.getText().toString();
						wbelEndImpersonatelink.click();
						Thread.sleep(1000);
						strFlag = "Pass";
						System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
						ReportFactory.reporterOutput(strTCID + " " + PageName,
								"Verify error message is displayed on hitting the URL for unchecked Country.",
								"Dealer : " + strDealer + " WCM ID : " + strWCMId,
								"Verify error message should be displayed on hitting the URL for unchecked Country.",
								"Error message is displayed on hitting the URL for unchecked Country. Error Message : "
										+ errorMsg,
								strFlag);
						
						// Impersonate to check all the products
						ImpersonateUser.impersonateUserSuccess(strDealer);
						GenericFactory.checkAllProductsData();
						Thread.sleep(1000);
						break;
					}
				}}
				else {
					strFlag = "Pass";
					ReportFactory.reporterOutput(strTCID + " " + PageName,
							"Verify error message is displayed on hitting the URL for unchecked Country.",
							"Dealer : " + strDealer + " WCM ID : " + strWCMId,
							"Verify error message should be displayed on hitting the URL for unchecked Country.",
							"WCM TCID doesnot match after filtering with Product and Country.", strFlag);
				}
			}

			else {
				strFlag = "Pass";
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						"Verify error message is displayed on hitting the URL for unchecked Country.",
						"Dealer : " + strDealer + " WCM ID : " + strWCMId,
						"Verify error message should be displayed on hitting the URL for unchecked Country.",
						"Country segment is not available for the dealer.", strFlag);
			}

			} catch (Exception e) {
			strFlag = "Fail";
			System.out.println("URL Sharing is unsuccessful");
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL for unchecked Country.",
					"Dealer : " + strDealer + " WCM ID : " + strWCMId,
					"Verify error message should be displayed on hitting the URL for unchecked Country.",
					"Error message is NOT displayed on hitting the URL for unchecked Country.", strFlag);
			EndImpersonateUser.endImpersonateOrLogoutUser();

		}
	}
	
	public static void ValidateURLforCountryEmployeeToDealer(String strTCID,String strDealer, String strWCMId, String PageName, String strSelectSite) throws Throwable {
		String WCMId, Country, strFlag = "Fail";boolean CountrySeg = true;
		boolean dealerFoundInWCM = false;
		
		try {
			dealerFoundInWCM = false;
//			ImpersonateUser.impersonateUserSuccess(strUserRACFID);
			// Select the site that will give multiple markets from My Preferences and save
			// the same
			if (!ValidationFactory.isElementPresent(wbelCountrySegmentIcon)) {
				CountrySeg = false;
				myPreferencesPageFactory.navigateToMyPreferences();
				if (ValidationFactory.isElementPresent(wbelMyPreferencePreferredSite)) {
					Select site = new Select(wbelMyPreferencePreferredSite);
					site.selectByVisibleText(strSelectSite);
					wbelMyPreferenceModalSaveButton.click();
					Thread.sleep(1000);
					if (ValidationFactory.isElementPresent(wbelCountrySegmentIcon)) {
						CountrySeg = true;
					} else {
						CountrySeg = false;
					}
				} else {
					strFlag = "Pass";
					wbelMyPreferenceModalSaveButton.click();
					Thread.sleep(1000);
					ReportFactory.reporterOutput(strTCID + " " + PageName,
							"Verify error message is displayed on hitting the URL for unchecked Country.",
							"Dealer : " + strDealer + " WCM ID : " + strWCMId,
							"Verify error message should be displayed on hitting the URL for unchecked Country.",
							"Country segment is not available for the dealer.", strFlag);

				}

			}else {
			// If country selection segment is present then proceed with further steps
			if (CountrySeg) {
				List<LinkedHashMap> userWCMContentIndex = ExcelFactory
						.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
				if (userWCMContentIndex.size() > 0) {
					for (int i = 0; i < userWCMContentIndex.size(); i++) {
						WCMId = userWCMContentIndex.get(i).get("Test Case ID").toString();
						Country = userWCMContentIndex.get(i).get("Country").toString();
						List<String> country = new ArrayList<String>(Arrays.asList(Country.split(",")));
						if (WCMId.equalsIgnoreCase(strWCMId)) {
							dealerFoundInWCM = true;
							LinkedHashMap navigateTo = userWCMContentIndex.get(i);
							strHomeDealer.click();
							GenericFactory.navigateToIndexPage(navigateTo);

							// URL
							String strTitle = (String) userWCMContentIndex.get(i).get("Title").toString().trim();
							try {
								WebElement link = locDriver
										.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
							} catch (Exception e) {
								strFlag = "Fail";
								System.out.println("URL Sharing is unsuccessful");
								if (PageName.equalsIgnoreCase("AT-Link")) {
									PageName = "AT-Index Page";
								}
								ReportFactory.reporterOutput(strTCID + " " + PageName,
										"Verify error message is displayed on hitting the URL for deactivated Product Type.",
										"Dealer : " + strDealer + " WCM ID : " + strWCMId,
										"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
										"Invalid URL : link " + strTitle + " is not present.", strFlag);
								break;
							}
							String putURL = getURL(strTitle);

							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>URL : " + putURL);

							// To navigate to home page
							strHomeDealer.click();
							Thread.sleep(1000);

							// end Impersonate employee and impersonate the Dealer
//							EndImpersonateUser.endImpersonateOrLogoutUser();
//							Thread.sleep(1000);
//							ImpersonateUser.impersonateUserSuccess(strDealer);
							GenericFactory.EndImpersonateUSER();
							Thread.sleep(1000);
							GenericFactory.impersonateUser(strDealer);

							// Hit the URL copied from the Employee using the dealer id
							locDriver.get(putURL);
							String errorMsg = wbelErrorMsg.getText().toString();
							wbelEndImpersonatelink.click();
							Thread.sleep(1000);
							strFlag = "Pass";
							System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
							ReportFactory.reporterOutput(strTCID + " " + PageName,
									"Verify error message is displayed on hitting the URL for unchecked Country.",
									"Dealer : " + strDealer + " WCM ID : " + strWCMId,
									"Verify error message should be displayed on hitting the URL for unchecked Country.",
									"Error message is displayed on hitting the URL for unchecked Country. Error Message : "
											+ errorMsg,
									strFlag);

						}else {
							strFlag = "Pass";
							if (PageName.equalsIgnoreCase("AT-Link")) {
								PageName = "AT-Index Page";
							}
							ReportFactory.reporterOutput(strTCID + " " + PageName,
								"Verify error message is displayed on hitting the URL for unchecked Country.",
								"Dealer : " + strDealer + " WCM ID : " + strWCMId,
								"Verify error message should be displayed on hitting the URL for unchecked Country.",
								"WCM TCID doesnot match after filtering with Product and Country.", strFlag);
							
						}
					}
				} else {
					strFlag = "Pass";
					if (PageName.equalsIgnoreCase("AT-Link")) {
						PageName = "AT-Index Page";
					}
					ReportFactory.reporterOutput(strTCID + " " + PageName,
							"Verify error message is displayed on hitting the URL for unchecked Country.",
							"Dealer : " + strDealer + " WCM ID : " + strWCMId,
							"Verify error message should be displayed on hitting the URL for unchecked Country.",
							"WCM TCID doesnot match after filtering with Product and Country.", strFlag);
				}}
			

			else {
				strFlag = "Pass";
				if (PageName.equalsIgnoreCase("AT-Link")) {
					PageName = "AT-Index Page";
				}
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						"Verify error message is displayed on hitting the URL for unchecked Country.",
						"Dealer : " + strDealer + " WCM ID : " + strWCMId,
						"Verify error message should be displayed on hitting the URL for unchecked Country.",
						"Country segment is not available for the dealer.", strFlag);
			}

		}} catch (Exception e) {
			strFlag = "Fail";
			System.out.println("URL Sharing is unsuccessful");
			if(PageName.equalsIgnoreCase("AT-Link")) {
				PageName = "AT-Index Page";
			}
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL for unchecked Country.",
					"Dealer : " + strDealer + " WCM ID : " + strWCMId,
					"Verify error message should be displayed on hitting the URL for unchecked Country.",
					"Error message is NOT displayed on hitting the URL for unchecked Country.", strFlag);
//			EndImpersonateUser.endImpersonateOrLogoutUser();
//			Thread.sleep(1000);
			GenericFactory.EndImpersonateUSER();
			Thread.sleep(1000);
			GenericFactory.impersonateUser(strUserRACFID);

		}
	}
	
	
	static List<String> countryListApp = new ArrayList<String>();

	public static void countryUncheck(List<String> parentCountry) throws Exception {
		// To get the list of countries present in the country list of application
		for (int i = 0; i < wbelCountryGroups.size(); i++) {
			String temp = wbelCountryGroups.get(i).getText();
			countryListApp.add(temp);
			if (temp.equalsIgnoreCase(parentCountry.get(0))) {
				wbelCountryCheckBox.get(i).click();
			}

		}

	}
	
	public static void ValidateURLforDealerTypeMainSub(String strTCID,String strSubDealer,String PageName,String strSelectSite,String strWCMId) throws Throwable {
		String WCMId, Country, strFlag = "Fail";boolean dealerFoundInWCM ;
		try {
//			ImpersonateUser.impersonateUserSuccess(strUserRACFID);
			myPreferencesPageFactory.navigateToMyPreferences();
			
			if (ValidationFactory.isElementPresent(wbelMyPreferencePreferredSite)) {
				Select site = new Select(wbelMyPreferencePreferredSite);
				site.selectByVisibleText(strSelectSite);
				wbelMyPreferenceModalSaveButton.click();
				Thread.sleep(2000);
			}else {
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						"Verify error message is displayed on hitting the URL for deactivated Product Type.",
						"Dealer : " + strUserRACFID + " WCM ID : " + strWCMId,
						"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
						"", strFlag);
				
			}
			
			List<LinkedHashMap> userWCMContentIndex = ExcelFactory
					.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
			System.out.println("********************************************check" + userWCMContentIndex.size());
			
			if(userWCMContentIndex.size()>0) {
			for (int i = 0; i < userWCMContentIndex.size(); i++) {
				WCMId = userWCMContentIndex.get(i).get("Test Case ID").toString();
				Country = userWCMContentIndex.get(i).get("Country").toString();
				List<String> country = new ArrayList<String>(Arrays.asList(Country.split(",")));
				if (WCMId.equalsIgnoreCase(strWCMId)) {
					dealerFoundInWCM = true;
					LinkedHashMap navigateTo = userWCMContentIndex.get(i);
					strHomeDealer.click();
					GenericFactory.navigateToIndexPage(navigateTo);

					// URL
					String strTitle = (String) userWCMContentIndex.get(i).get("Title").toString().trim();
					try {
						WebElement link = locDriver.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
					} catch (Exception e) {
						strFlag = "Fail";
						System.out.println("URL Sharing is unsuccessful");
						if(PageName.equalsIgnoreCase("AT-Link")) {
							PageName = "AT-Index Page";
						}
						ReportFactory.reporterOutput(strTCID + " " + PageName,
								"Verify error message is displayed on hitting the URL for deactivated Product Type.",
								"Dealer : " + strUserRACFID + " WCM ID : " + strWCMId,
								"Verify error message should be displayed on hitting the URL for deactivated Product Type.",
								"Invalid URL : link " + strTitle + " is not present.", strFlag);
						break;
					}
					String putURL = getURL(strTitle);

					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>URL : " + putURL);
					// To navigate to home page
					strHomeDealer.click();
					Thread.sleep(1000);

					// End Impersonate the main dealer and Impersonate the sub dealer
//					EndImpersonateUser.endImpersonateOrLogoutUser();
//					Thread.sleep(1000);
//					ImpersonateUser.impersonateUserSuccess(strSubDealer);
					GenericFactory.EndImpersonateUSER();
					Thread.sleep(1000);
					GenericFactory.impersonateUser(strSubDealer);
					// Hit the URL that we noted using main dealer
					locDriver.get(putURL);
					String errorMsg = wbelErrorMsg.getText().toString();
					wbelEndImpersonatelink.click();
					Thread.sleep(1000);
					strFlag = "Pass";
					System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
					if(PageName.equalsIgnoreCase("AT-Link")) {
						PageName = "AT-Index Page";
					}
					ReportFactory.reporterOutput(strTCID + " " + PageName,
							"Verify error message is displayed on hitting the URL for Main dealer using the Sub dealer.",
							"Main Dealer : " + strUserRACFID + " Sub Dealer : " + strSubDealer + " WCM ID : "
									+ strWCMId,
							"Verify error message should be displayed on hitting the URL for Main dealer using the Sub dealer.",
							"Error message is displayed on hitting the URL for Main dealer using the Sub dealer. Error Message : "
									+ errorMsg,
							strFlag);
					GenericFactory.EndImpersonateUSER();
					Thread.sleep(1000);
					GenericFactory.impersonateUser(strUserRACFID);
				} // If no matching rows are found in the WCM content
				
			}}else {
				strFlag = "Pass";
				if(PageName.equalsIgnoreCase("AT-Link")) {
					PageName = "AT-Index Page";
				}
				ReportFactory.reporterOutput(strTCID + " " + PageName,
						"Verify error message is displayed on hitting the URL for Main dealer using the Sub dealer.",
						"Main Dealer : " + strUserRACFID + " Sub Dealer : " + strSubDealer + " WCM ID : "
								+ strWCMId,
						"Verify error message should be displayed on hitting the URL for Main dealer using the Sub dealer.",
						"WCM TCID doesnot match after filtering with Product and Country.", strFlag);
			}
				
			}catch(Exception e) { 
			strFlag = "Fail";
			System.out.println("URL Sharing is unsuccessful");
			if(PageName.equalsIgnoreCase("AT-Link")) {
				PageName = "AT-Index Page";
			}
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL for Main dealer using the Sub dealer.",
					"Main Dealer : " + strUserRACFID + " Sub Dealer : "+strSubDealer +" WCM ID : " + strWCMId,
					"Verify error message should be displayed on hitting the URL for Main dealer using the Sub dealer.",
					"Error message is NOT displayed on hitting the URL for Main dealer using the Sub dealer.", strFlag);
//			EndImpersonateUser.endImpersonateOrLogoutUser();
			GenericFactory.EndImpersonateUSER();
			Thread.sleep(1000);
			GenericFactory.impersonateUser(strUserRACFID);
			
		}
	}

	public static void ValidateURLforRACFGroups(String strTCID,String strWCMId,String strDealerBelongingToOtherGrp,String PageName)  throws Throwable {
		String WCMId,strFlag;
		try {
//			ImpersonateUser.impersonateUserSuccess(strDealerBelongingToRacfGrp);
			List<LinkedHashMap> userWCMContentIndex = ExcelFactory
					.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
			System.out.println(
					"Number of rows fetched from WCM that matches the respective data : " + userWCMContentIndex.size());

			if (userWCMContentIndex.size() > 0) {
				for (int i = 0; i < userWCMContentIndex.size(); i++) {
					WCMId = userWCMContentIndex.get(i).get("Test Case ID").toString();
					if (WCMId.equalsIgnoreCase(strWCMId)) {
						LinkedHashMap navigateTo = userWCMContentIndex.get(i);
						strHomeDealer.click();
						GenericFactory.navigateToIndexPage(navigateTo);

						// URL
						String strTitle = (String) userWCMContentIndex.get(i).get("Title").toString().trim();
						try {
							WebElement link = locDriver
									.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
						} catch (Exception e) {
							strFlag = "Fail";
							System.out.println("URL Sharing is unsuccessful");
							ReportFactory.reporterOutput(strTCID + " " + PageName,
									"Verify error message is displayed on hitting the URL through the dealer which doesnot belong to the RACF group.",
									"Dealer belonging to RACF group : " + strUserRACFID + " ,WCM ID : "
											+ strWCMId + " ,Dealer belonging to Other RACF group : "
											+ strDealerBelongingToOtherGrp,
									"Verify error message should be displayed on hitting the URL through the dealer which doesnot belong to the RACF group.",
									"Invalid URL : link " + strTitle + " is not present.", strFlag);
							break;
						}

						String putURL = getURL(strTitle);

						System.out.println(
								">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>URL for the dealer that belongs to the RACF group : "
										+ putURL);
						// To navigate to home page
						strHomeDealer.click();
						Thread.sleep(1000);

						// End Impersonate the main dealer and Impersonate the sub dealer
//						EndImpersonateUser.endImpersonateOrLogoutUser();
//						Thread.sleep(1000);
//						ImpersonateUser.impersonateUserSuccess(strDealerBelongingToOtherGrp);
						
						GenericFactory.EndImpersonateUSER();
						Thread.sleep(1000);
						GenericFactory.impersonateUser(strDealerBelongingToOtherGrp);

						// Hit the URL that we noted using main dealer
						locDriver.get(putURL);
						String errorMsg = wbelErrorMsg.getText().toString();
						wbelEndImpersonatelink.click();
						Thread.sleep(1000);
						strFlag = "Pass";
						System.out.println("URL available for Sharing  >>>>>>>>>>>>>>>>>>>  " + errorMsg);
						if (PageName.equalsIgnoreCase("AT_Link")) {
							PageName = "AT-Index Page";
						}
						ReportFactory.reporterOutput(strTCID + " " + PageName,
								"Verify error message is displayed on hitting the URL through the dealer which doesnot belong to the RACF group.",
								"Dealer belonging to RACF group : " + strUserRACFID + " ,WCM ID : "
										+ strWCMId + " ,Dealer belonging to Other RACF group : "
										+ strDealerBelongingToOtherGrp,
								"Verify error message should be displayed on hitting the URL through the dealer which doesnot belong to the RACF group.",
								"Error message is displayed on hitting the URL through the dealer which doesnot belong to the RACF group. Error Message : "
										+ errorMsg,
								strFlag);
					} // If no matching rows are found in the WCM content

				}
			}
		}catch(Exception e) { 
			strFlag = "Fail";
			System.out.println("URL Sharing is unsuccessful");
			if(PageName.equalsIgnoreCase("AT_Link")) {
				PageName = "AT-Index Page";
			}
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL through the dealer which doesnot belong to the RACF group.",
					"Dealer belonging to RACF group : " + strUserRACFID + " ,WCM ID : "
							+ strWCMId + " ,Dealer belonging to Other RACF group : "
							+ strDealerBelongingToOtherGrp,
					"Verify error message should be displayed on hitting the URL through the dealer which doesnot belong to the RACF group.",
					"Error message is NOT displayed on hitting the URL through the dealer which doesnot belong to the RACF group.", strFlag);
			GenericFactory.EndImpersonateUSER();
		}
	}
*/
public static String getURL(String strTitle) throws Exception {
	
	String strLink = locDriver.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"))
			.getAttribute("href").toString().trim();
	HttpURLConnection huc = (HttpURLConnection) (new URL(strLink).openConnection());
	java.net.URL URLTo = huc.getURL();
	String putURL = URLTo.toString();
	return putURL;
	
}/*
public static String getURLForPageName(String strTCID, String strDealer1,String PageName)
		throws Throwable, Exception {
	String navigationparent="";
	List<LinkedHashMap> userWCMContentIndex = ExcelFactory
			.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
	if(userWCMContentIndex.size()>0) {
//	for (int i = 0; i < userWCMContentIndex.size(); i++) {
		LinkedHashMap navigateTo = userWCMContentIndex.get(0);
//			strHomeDealer.click();
		GenericFactory.navigateToIndexPage(navigateTo);
//		GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(i));
		navigationparent = BaseClass.PORTLET_LINKFLAG;
//		GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(0));
		// URL
		// Get the title from the WCM and check whether it is present in the application
		strTitle = (String) userWCMContentIndex.get(0).get("Title").toString().trim();
		try {
			locDriver.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
			putURL = getURL(strTitle);
		} catch (Exception e) {
			strFlag = "Fail";
			
			System.out.println("URL Sharing is unsuccessful");
//			ReportFactory.reporterOutputMerge(strTCID + " " + PageName, "Verify URL sharing.",
//					"Dealer : " + strDealer1 ,
//					"Verify URL sharing should be successful",
//					"Invalid URL : link " + strTitle + " is not present.", strFlag,merge,colspan);
//				EndImpersonateUser.endImpersonateOrLogoutUser();
//			GenericFactory.EndImpersonateUSER();
//			break;
		}//}
	}else
	{
		putURL = "";
//		strFlag = "Pass";
//		
//		ReportFactory.reporterOutput(strTCID + " " + PageName,
//				"Verify error message is displayed on hitting the URL for deactivated department.",
//				"Dealer : " + strDealer1,
//				"Verify error message should be displayed on hitting the URL for deactivated department."
//						+ PageName,
//				"No entries for respective country and product are available", strFlag);
	}
		
	return putURL+"$$$$$$$$$$$$$$$$$$$$$$"+navigationparent;
}

private static String getURLForUncheckDeptError(String strTCID,int colspan, boolean merger) throws Throwable, Exception {
	@SuppressWarnings("rawtypes")
	List<LinkedHashMap> userWCMContentIndex = ExcelFactory
			.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
	DepartmentName = userWCMContentIndex.get(0).get("DepartmentName").toString();
	strFlag = "Fail";
	strNoWcm = false;
	Dept.add(DepartmentName);

	// check if the department is enabled so that it can be disabled
	departmentClass = locDriver.findElement(By.xpath("//a[contains(text(),'" + DepartmentName + "')]"))
			.getAttribute("class");
	if (departmentClass.equalsIgnoreCase("inactive")) {
		strFlag = "Pass";
		ReportFactory.reporterOutput(strTCID + " " + PageName,
				"Verify error message is displayed on hitting the URL for deactivated department.", "NA",
				"Verify error message is displayed on hitting the URL for deactivated department.",
				"The department for which the scenario is to be verified is disabled for the dealer", strFlag);
		return null;
		} else {
		
		 * Url that is displayed on navigating to particular department> Index page
		 * mentioned in the navigation flow from WCM
		 
		@SuppressWarnings("rawtypes")
		List<LinkedHashMap> userWCMContent = ExcelFactory
				.getUserWcmDetailsAfterFilteringCountryAndProduct(PageName);
		if (userWCMContent.isEmpty()) {
			strFlag = "Pass";
			ReportFactory.reporterOutput(strTCID + " " + PageName,
					"Verify error message is displayed on hitting the URL for deactivated department.",
					strUserRACFID,
					"Verify error message should be displayed on hitting the URL for deactivated department."
							+ PageName,
					"No entries for respective country and product are available", strFlag);
			strNoWcm = true;
		}

		GenericFactory.navigateToIndexPage(userWCMContent.get(0));
		String strTitle = (String) userWCMContent.get(0).get("Title").toString().trim();
		String putURL = getURL(strTitle);
		System.out.println("****************************************************************" + putURL);
		return putURL;
	}
	
	
}*/
static String RACFGrp,strFlag,strRACFGrpdealer2FromAnalzeUser,strRACFGrpdealer2,strCountryDealer2,strDealer_ProductTypeDealer2,strDepartmentNameDealer2,strContentTypeDealer2,strTitleDealer2,strProductTypeDealer2,strDealerTypeMainSubDealer2,strRACFGroupsDealer2;
static boolean countryPresent1 = false ,countrySharingsuccess = false,  countryPresent2 = false ,countrySharing = false,localflagCountry,localflagProductType,localflagdealerType,localflagRACFGrps,localcountryComparisonflag,localProductTypeComparisonflag;
static String result, errorMsg,finalResult,resultCountry="";
static List<String> countryDealer1 = new ArrayList<String>();
static List<String> countryDealer2 = new ArrayList<String>();
public static void URLValidation(String strTCID,String strdealer2,String strWCMID,String strDealer_Country,String strDealer_ProductType,String strDepartmentName,String strContentType,String strTitle,String strCountry,String strProductType,String strDealerTypeMainSub,String strRACFGroups) throws Throwable, Exception {
	boolean flag = true;
//System.out.println(strDealer_Country+strDealer_ProductType+strDepartmentName+strContentType+strTitle+strCountry+strProductType+strDealerTypeMainSub+strRACFGroups);
		try {
			int size = ExcelFactory.wcmExceldata.size();
			strFlag="Fail";
			for (int i = 0; i < size; i++) {
						if (ExcelFactory.wcmExceldata.get(i).get("Dealer_User_id").equals(strdealer2)
						&& ExcelFactory.wcmExceldata.get(i).get("Test Case ID").toString().trim().equals(strWCMID)) {
					strFlag = "Pass";
					strCountryDealer2 = (String) ExcelFactory.wcmExceldata.get(i).get("Dealer_Country");
					strDealer_ProductTypeDealer2 = (String) ExcelFactory.wcmExceldata.get(i).get("Dealer_ProductType");
					strDealerTypeMainSubDealer2 = (String) ExcelFactory.wcmExceldata.get(i)
							.get("DealerType (Main/Sub)");
					strRACFGroupsDealer2 = (String) ExcelFactory.wcmExceldata.get(i).get("RACFGroups");
					break;
				} else {
					// 2nd delr info sheet/wcm refrence is wrng
					strFlag = "Fail";
				}
			}
			if(strFlag.equals("Pass")) {	
				localflagCountry = GenericFactory.userAndWCMCountryComparison(strCountryDealer2, strCountry);
				localflagProductType = GenericFactory.userAndWCMProductTypeComparison(strDealer_ProductTypeDealer2,
						strProductType);
				System.out.println("***************************************"+strDealer_ProductTypeDealer2);
				System.out.println("***************************************"+strDealer_ProductType);
				localflagdealerType=GenericFactory.verifyDealerType(strdealer2);
			
				//country
				if (localflagCountry) {
					result = "Country Matched.</br>";
					localcountryComparisonflag = true;
				} else {
					result = "<b>Country Doesnot Matched.</b></br>";
					localcountryComparisonflag = false;
				}
				//Product Type
				if (localflagProductType) {
					result = result + "Product Type Matched.</br>";
					localProductTypeComparisonflag = true;
				} else {
					result = result + "<b>Product Type doesnot Matched.</b></br>";
					localProductTypeComparisonflag = false;
				}
				//Dealer Type
				if (localflagdealerType) {
					result = result + "Dealer Type Matched.</br>";
					localcountryComparisonflag = true;
				} else {
					result = result	+ "<b>Dealer Type doesnot Matched.</b></br>";
					localcountryComparisonflag = false;
				}
				String RACF2=getRACFGrpOfDealer2(strdealer2);
				System.out.println("***************************************"+RACF2);
				//change the RACF grp
				strRACFGrpdealer2FromAnalzeUser=(!strRACFGroups.equals("NA"))?RACF2:"NA";
				localflagRACFGrps= GenericFactory.verifyRacfGroupMatched(strRACFGroups,strRACFGrpdealer2FromAnalzeUser);
				System.out.println("***************************************"+strRACFGroups);
				System.out.println("***************************************"+strRACFGrpdealer2FromAnalzeUser);
				if (!strRACFGrpdealer2FromAnalzeUser.equals("NA")) {
					//RACF groups
					if (localflagRACFGrps) {
						result = result + "RACF groups Matched.</br>";
					} else {
						result = result	+ "<b>RACF groups doesnot Matched.</b></br>";
					}	
				}
				
				
				
				GenericFactory.impersonateUser(strUserRACFID);
				//country list of dealer from dealer info sheet
				if(ValidationFactory.isElementPresent(wbelCountrySegmentIcon)) {
					countryPresent1 = true;
					countrySharing = true;
//					resultCountry ="Country segment is available for dealer 1";
					wbelCountrySegmentIcon.click();
					for(int i = 0 ;i<wbelCountryCheckedCountry.size();i++) {
						String checkedCountry=wbelCountryCheckedCountry.get(i).getText();
						countryDealer1.add(checkedCountry);
							}
					System.out.println("*************************************************"+countryDealer1);
				}else {
					
					System.out.println("*************************************************Country segment not available for dealer 1");
//					resultCountry ="Country segment not available for dealer 1.</n>"; 
				}
				
					// Navigate using the WCM data in the WCM row provided from the test data
				@SuppressWarnings("rawtypes")
				LinkedHashMap Map = ExcelFactory.getWCMByTCID(strWCMID);
					GenericFactory.navigateToIndexPage(Map);
					GenericFactory.SendPORTLET_LINKFLAG(ExcelFactory.getWCMByTCID(strWCMID));
					boolean eleFlag = true;
					try {
						locDriver.findElement(By.xpath("//a[contains(text(),'" + strTitle + "')]"));
					}catch (Exception e) {
						eleFlag = false;
					}
					
					if(eleFlag) {
					URL = getURL(strTitle);
					GenericFactory.navigateToHomePage();
					// End impersonate the first user
					GenericFactory.EndImpersonateUSER();
					// Impersonate with second dealer
					ImpersonateUser.impersonateUserSuccess(strdealer2);
					
					//get the country of dealer from additional test case sheet if the country of dealer info's dealer was available
					if(ValidationFactory.isElementPresent(wbelCountrySegmentIcon)) {
						resultCountry = resultCountry + "Country Segment for dealer 2 is present";
						if(countryPresent1) {
						countryPresent2 = true;
						wbelCountrySegmentIcon.click();
						for(int i = 0 ;i<wbelCountryCheckedCountry.size();i++) {
							String checkedCountry=wbelCountryCheckedCountry.get(i).getText();
							countryDealer2.add(checkedCountry);
								}	}					
					}else {
						System.out.println("*****************************Country segment for dealer 2 is not present");
						resultCountry = resultCountry + "Country Segment for dealer 2 is not present";
					}
					
					boolean execute = false;
					if(CollectionUtils.containsAny(countryDealer1,countryDealer2)) {  
						execute = true;
					} else { 
						execute = false;
					} 
					if(!countryDealer1.isEmpty()&&!countryDealer2.isEmpty()&&execute) {
						//URL will be shared
						countrySharingsuccess = true;
					}else
					{
						countrySharingsuccess = false;
						//URL should not be shared
					}
					
					if (!URL.equalsIgnoreCase("")) {//no else
					       String DepartmentName = (String) Map.get("DepartmentName").toString().trim();
					    
					       WebElement dept = locDriver.findElement(By.xpath("//a[contains(text(),'"+strDepartmentName+"')]"));
						if(!dept.isEnabled()) {
							System.out.println("Department is inactive");
						}				
					
						try { // Added by Aniket
							if (BaseClass.strBrowserType.equalsIgnoreCase("Chrome") && URL.contains("doc")) {
								String mainWind = BaseClass.wbDriver.getWindowHandle();
								// BaseClass.wbDriver.get("https://dealerpathqual.tal.deere.com/wps/wcm/myconnect/dpath/4378b33d-add7-48bc-8e92-e2de03f4371d/R4DealerPathFAQ.pdf?MOD=AJPERES");
								BaseClass.wbDriver.get(URL);
								WaitFactory.waitForPageLoaded();

								if (!ValidationFactory.isElementPresent(wbelErrorMsg)) {
									finalResult = strdealer2
											+ " is able to navigate to the Index Page of shared URL by " + strUserRACFID
											+ ".";
									flag = true;
								} else {
									flag = false;
									if (ValidationFactory.isElementPresent(wbelErrorMsg)) {
										errorMsg = wbelErrorMsg.getText().toString();
										finalResult = "Error Message displayed";
									} else
										errorMsg = strdealer2 + " is not having the access URL shared by "
												+ strUserRACFID + ".";
									;
									if (ValidationFactory.isElementPresent(wbelEndImpersonatelink))
										wbelEndImpersonatelink.click();

								}
							} else if (BaseClass.strBrowserType.equalsIgnoreCase("Chrome") && URL.contains("pdf")) {
								try {
									String mainWind = BaseClass.wbDriver.getWindowHandle();
									// BaseClass.wbDriver.get("https://dealerpathqual.tal.deere.com/wps/wcm/myconnect/dpath/4378b33d-add7-48bc-8e92-e2de03f4371d/R4DealerPathFAQ.pdf?MOD=AJPERES");
									BaseClass.wbDriver.get(URL);
									WaitFactory.waitForPageLoaded();
									if (ValidationFactory.isElementPresent(wbelErrorMsg)) {
										flag = false;
										errorMsg = wbelErrorMsg.getText().toString();
										finalResult = "Error Message displayed";
										if (ValidationFactory.isElementPresent(wbelEndImpersonatelink))
											wbelEndImpersonatelink.click();
									} else {
										Thread.sleep(4000);
										if (BaseClass.wbDriver.getWindowHandles().size() > 1) {
											Set<String> handles = BaseClass.wbDriver.getWindowHandles();
											for (String handle : handles) {

												if (!handle.equals(mainWind)) {
													BaseClass.wbDriver.switchTo().window(handle);
												}
											}
										}
										Robot rb = new Robot();
										rb.keyPress(KeyEvent.VK_TAB);
										rb.keyRelease(KeyEvent.VK_TAB);
										rb.keyPress(KeyEvent.VK_TAB);
										rb.keyRelease(KeyEvent.VK_TAB);
										rb.keyPress(KeyEvent.VK_TAB);
										rb.keyRelease(KeyEvent.VK_TAB);
										rb.keyPress(KeyEvent.VK_ENTER);
										rb.keyRelease(KeyEvent.VK_ENTER);
										Thread.sleep(6000);
										rb.keyPress(KeyEvent.VK_ENTER);
										rb.keyRelease(KeyEvent.VK_ENTER);

										BaseClass.wbDriver.switchTo().window(mainWind);
										BaseClass.wbDriver.navigate().back();
										WaitFactory.waitForPageLoaded();
										finalResult = strdealer2
												+ " is able to navigate to the Index Page of shared URL by "
												+ strUserRACFID + ".";
										flag = true;
									}
								} catch (Exception e) {
									System.out.println("Downloading Failed..." + e.getMessage());
								}
							} else if(strContentType.equalsIgnoreCase("AT-Rich Text")||strContentType.equalsIgnoreCase("AT-Index Page")) {
								try {
									locDriver.get(URL);
									wbelEndImpersonatelink.isDisplayed();
									flag = false;
									errorMsg = wbelErrorMsg.getText().toString();
									wbelEndImpersonatelink.click();
									finalResult = "Error Messege displayed";
								} catch (Exception e) {
									finalResult = strdealer2+" is able to navigate to the Index Page of shared URL by "+strUserRACFID+".";
									flag = true;
								}
							}else {
							}
								Reporter.log(
										"This test case will not work properly on IE, so please execute the same on Chrome...");
							
						} catch (Exception e) {
							flag=false;
							if(ValidationFactory.isElementPresent(wbelErrorMsg))
							{
							errorMsg = wbelErrorMsg.getText().toString();
							finalResult = "Error Message displayed";
							}else
								errorMsg = strdealer2+" is not having the access URL shared by "+strUserRACFID+".";;
							if(ValidationFactory.isElementPresent(wbelEndImpersonatelink))
								wbelEndImpersonatelink.click();
						}
						if (localflagCountry && localflagProductType && localflagdealerType&&localflagRACFGrps) {
							if (flag&&finalResult.equalsIgnoreCase(
									strdealer2+" is able to navigate to the Index Page of shared URL by "+strUserRACFID+".")) {
								result = result+strdealer2+" is able to navigate to the Index Page of shared URL by "+strUserRACFID+".</br>";
								strFlag = "Pass";
							} else {
								result = result+strdealer2+" is NOT able to navigate to the Index Page of shared URL by "+strUserRACFID+".</br>";
								strFlag = "Fail";
							}
							ReportFactory.reporterOutput(strTCID, "Verify URL sharing.",
									"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG + "<br><b>WCM Id : </b>"
											+ strWCMID + "<br><b>URL Sharing to USER-2 : </b>" + strdealer2 +"<br><b>WCM Country : </b>"+strCountry +"<br><b>Dealer 2 Country : </b>"+strCountryDealer2 +"<br><b>WCM product Type</b>"+strProductType +"<br><b>dealer 2 Product Type : </b>"+strDealer_ProductTypeDealer2  +"<br><b>WCM Dealer Type : </b>"+strDealerTypeMainSub +"<br><b>Dealer 2 Dealer Type : </b>"+strDealerTypeMainSubDealer2+"<br><b>WCM RACF Group : </b>"+strRACFGroups  +"<br><b>Dealer 2 RACF Group : </b>"+strRACFGroupsDealer2,
											strdealer2+" has access to the content in terms of country tagged, product types tagged, preferred department,"+strdealer2+" should be able to navigate to the Index Page of shared URL by "+strUserRACFID+".",
									result, strFlag);
						} else {
							if (!flag&&finalResult.equalsIgnoreCase("Error Messege displayed")) {
								result = result+"Error Messege displayed.</br>";
								strFlag = "Pass";
							} else {
								result = result+"Error Messege NOT displayed.</br>";
								strFlag = "Fail";
							}
							ReportFactory.reporterOutput(strTCID, "Verify URL sharing.",
									"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG + "<br><b>WCM Id : </b>"
											+ strWCMID + "<br><b>URL Sharing to USER-2 : </b>" + strdealer2 +"<br><b>WCM Country : </b>"+strCountry +"<br><b>Dealer 2 Country : </b>"+strCountryDealer2 +"<br><b>WCM product Type</b>"+strProductType +"<br><b>dealer 2 Product Type : </b>"+strDealer_ProductTypeDealer2  +"<br><b>WCM Dealer Type : </b>"+strDealerTypeMainSub +"<br><b>Dealer 2 Dealer Type : </b>"+strDealerTypeMainSubDealer2+"<br><b>WCM RACF Group : </b>"+strRACFGroups  +"<br><b>Dealer 2 RACF Group : </b>"+strRACFGroupsDealer2,
											strdealer2+" doesnot has access to the content in terms of country tagged, product types tagged, preferred department,"+strdealer2+" should get Error messege.",
									result, strFlag);
						}
						if (countrySharingsuccess) {
							if (!flag) {
								strFlag = "Pass";
								ReportFactory.reporterOutput(strTCID, "Verify URL sharing for country.",
										"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG
												+ "<br><b>WCM Id : </b>" + strWCMID
												+ "<br><b>Country from WCM : </b>" + countryDealer1
												+ "<br><b>Country for Dealer from additional Testcases : </b>"
												+ countryDealer2,
										"Atleast one Country of both dealers matches.URL should be shared.",
										resultCountry+"</n>URL is Shared.", strFlag);
							} else {
								strFlag = "Fail";
								ReportFactory.reporterOutput(strTCID, "Verify URL sharing for country.",
										"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG
										+ "<br><b>WCM Id : </b>" + strWCMID
												+ "<br><b>Country from WCM : </b>" + countryDealer1
												+ "<br><b>Country for Dealer from additional Testcases : </b>"
												+ countryDealer2,
										"Atleast one Country of both dealers matches.URL should be shared.",
										resultCountry+"</n>URL is NOT Shared.", strFlag);
							}
						}else {
							if(!flag) {
								strFlag = "Pass";
								ReportFactory.reporterOutput(strTCID, "Verify URL sharing for country.",
										"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG
										+ "<br><b>WCM Id : </b>" + strWCMID
												+ "<br><b>Country from WCM : </b>" + countryDealer1
												+ "<br><b>Country for Dealer from additional Testcases : </b>"
												+ countryDealer2,
										"Atleast one Country of both dealers matches.URL should <b>NOT</b> be shared.",
										resultCountry+"</n>Error Message is displayed.", strFlag);
							}else {
							strFlag = "Fail";
							ReportFactory.reporterOutput(strTCID, "Verify URL sharing for country.",
									"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG
									+ "<br><b>WCM Id : </b>" + strWCMID
											+ "<br><b>Country from WCM : </b>" + countryDealer1
											+ "<br><b>Country for Dealer from additional Testcases : </b>"
											+ countryDealer2,
									"Atleast one Country of both dealers matches.URL should <b>NOT</b> be shared.",
									resultCountry+"</n>URL is Shared.", strFlag);
						}}
					
					}
				} else{
					//else  link is not available
					System.out.println("URL Sharing is unsuccessful");
					strFlag = "Fail";
					ReportFactory.reporterOutput(strTCID, "Verify URL sharing.",
							"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG + "<br><b>WCM Id : </b>"
									+ strWCMID + "<br><b>URL Sharing to USER-2 : </b>" + strdealer2 +"<br><b>WCM Country : </b>"+strCountry +"<br><b>Dealer 2 Country : </b>"+strCountryDealer2 +"<br><b>WCM product Type</b>"+strProductType +"<br><b>dealer 2 Product Type : </b>"+strDealer_ProductTypeDealer2  +"<br><b>WCM Dealer Type : </b>"+strDealerTypeMainSub +"<br><b>Dealer 2 Dealer Type : </b>"+strDealerTypeMainSubDealer2+"<br><b>WCM RACF Group : </b>"+strRACFGroups  +"<br><b>Dealer 2 RACF Group : </b>"+strRACFGroupsDealer2,
							"If "+strdealer2+" has access to the content in terms of country tagged, product types tagged, preferred department then "+strdealer2+" should be able to navigate  to the Index Page of shared URL by "+strUserRACFID+".",
							"Invalid Navigation path is not available.",
							strFlag);
				}
//			}else {
//				System.out.println("URL Sharing is unsuccessful");
//				strFlag = "Fail";
//				ReportFactory.reporterOutput(strTCID, "Verify URL sharing.",
//						"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG + "<br><b>URL Sharing from User-1 : </b>"
//								+ strUserRACFID + "<br><b>URL Sharing to USER-2 : </b>" + strdealer2 +"<br><b>Dealer 1 Country : </b>"+strDealer_Country +"<br><b>Dealer 2 Country : </b>"+strCountryDealer2 +"<br><b>Dealer 1 product Type</b>"+strDealer_ProductType +"<br><b>dealer 2 Product Type : </b>"+strDealer_ProductTypeDealer2  +"<br><b>Dealer 1 Dealer Type : </b>"+strDealerTypeMainSub +"<br><b>Dealer 2 Dealer Type : </b>"+strDealerTypeMainSubDealer2+"<br><b>Dealer 1 RACF Group : </b>"+strRACFGroups  +"<br><b>Dealer 2 RACF Group : </b>"+strRACFGroupsDealer2,
//						"If "+strdealer2+" has access to the content in terms of country tagged, product types tagged, preferred department then "+strdealer2+" should be able to navigate  to the Index Page of shared URL by "+strUserRACFID+".",
//						"RACF Group is not mentioned in the Testdata.",
//						strFlag);
//			}
					}else {
				strFlag = "Fail";
				ReportFactory.reporterOutput(strTCID,							
						"Verify the URL Sharing : "+strdealer2+" is present in the dealer info sheet and WCM reference is correct.",
						"NA",
						"Verify that "+strdealer2+" should be present in the dealer info sheet and WCM reference should be correct.",
						"Either Second dealer is not present in the dealer info sheet OR WCM reference is invalid",
						strFlag);
			}}catch (Exception e) {
		strFlag = "Fail";
		ReportFactory.reporterOutput(strTCID, "Verify URL sharing.",
				"<b>URL Path : </b>" + BaseClass.PORTLET_LINKFLAG + "<br><b>WCM Id : </b>"
						+ strWCMID + "<br><b>URL Sharing to USER-2 : </b>" + strdealer2 +"<br><b>WCM Country : </b>"+strCountry +"<br><b>Dealer 2 Country : </b>"+strCountryDealer2 +"<br><b>WCM product Type</b>"+strProductType +"<br><b>dealer 2 Product Type : </b>"+strDealer_ProductTypeDealer2  +"<br><b>WCM Dealer Type : </b>"+strDealerTypeMainSub +"<br><b>Dealer 2 Dealer Type : </b>"+strDealerTypeMainSubDealer2+"<br><b>WCM RACF Group : </b>"+strRACFGroups  +"<br><b>Dealer 2 RACF Group : </b>"+strRACFGroupsDealer2,
				"URL sharing should be successful",
				result + "URL sharing is failed.",
				strFlag);
	}
	
	
		
	
}

public static String getRACFGrpOfDealer2(String strdealer2) throws Exception {
	GenericFactory.EndImpersonateUSER();
//	GenericFactory.utilityMenuAdminClick();
//	AnalyzeUser.click();
	Thread.sleep(1000);
	AnalyzeUserTextBox.sendKeys(strdealer2);
	analyzeUserButton.click();
	Thread.sleep(1000);
	RACFGrp = securityGrps.getText();
	
	String SecurityGroups =RACFGrp.replaceAll("\n", ",");
	
	return SecurityGroups;
}
}
