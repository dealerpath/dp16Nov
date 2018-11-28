/**
 * This class is use to initiate the webElements of all mentioned pages
 * Project    : DealerPath
 * @author shrishail.baddi
 * @createdAt 15-05-2018
 * 
 * @ModifiedBy shrey.choudhary
 * @modifiedAt 07-06-2018
 */

package com.deere.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.deere.PageFactory.Alerts_POF;
import com.deere.PageFactory.Announcements_POF;
import com.deere.PageFactory.ChildIndex_POF;
import com.deere.PageFactory.Favourites_POF;
import com.deere.PageFactory.GrandChildIndex_POF;
import com.deere.PageFactory.Homepage_POF;
import com.deere.PageFactory.Login_Page_POF;
import com.deere.PageFactory.MyPreferences_POF;
import com.deere.PageFactory.ParentIndex_POF;
import com.deere.PageFactory.PortalLeftNavigation_POF;
import com.deere.PageFactory.PortletLinksPage_POF;
import com.deere.PageFactory.ProductSegment_POF;
import com.deere.PageFactory.Search_POF;
import com.deere.PageFactory.UtilityLinks_POF;

import WCM_POF.Alert_WCM_POF;
import WCM_POF.WCM_Conetnt_POF;

/*import com.deere.PageFactory.ProductSegment_POF;
import com.deere.PageFactory.Search_POF;
import com.deere.PageFactory.UtilityLinks_POF;
import com.deere.PageFactory.Alerts_POF;
import com.deere.PageFactory.Announcements_POF;
*/

public class BaseClass {
	
	//Global/Environment Variables & Constants
	public static int testcaseNumber=1;
	public static WebDriver wbDriver = null;
	public static List<String> markedLinks = new ArrayList<>();
	public static List<String> markedlinkNames = new ArrayList<>();
	public static List<LinkedHashMap> userFavWCMData;
	public static Properties configProperties = new Properties();

	public static String strWorkingDir = System.getProperty("user.dir").toString();
	public static String URL;
	public static String strBrowserType;
	public static String departmentName = "";
	public static String strUserName;
	public static String strPassword;
	// public static String strTestDataSheetName = "testdatasample";
	public static String ENABLE_LOGS = "ON"; // possible values "ON" or "OFF"
	public static String strUserPrefLang = "ENGLISH";
	public static String strUserRole = "Dealer";
	public static String strSiteRegion = "R4_AUNZ";
	public static String strDataPath;// = strWorkingDir + "\\TestData\\" + strTestDataSheetName.toString().trim() +
										// ".xlsx";
	public static String strDataOutputPath = strWorkingDir + "\\ExportToExcel\\ExportToExcel";
	public static String wcmDataOutputPath;
	public static String strWCMInput = strWorkingDir + "\\TestData\\WCMInput.xlsx";
	public static String dateformat = "";// = "yyyy-dd-MM";
	public static String strUserRACFID = "";
	public static Properties propConfig = null;
	public static String strTestCase = "Index TC";
	public static String strScreenshotDir;
	public static String ENABLE_SCREENSHOT;
	public static boolean errorUserFound = false;
	public static int totalCount=0;
	public static int subDepartmentNumbers=0;
	public static String includeAllContent;
	public static String Region;
	public static String alertRegion;
	public static String department;
	public static String publishedDate;
	public static String multiLingual;
	public static LinkedHashMap<String, HashMap<String, Object>> wcmInputdata;
	public static LinkedHashMap<String, String> commonInputValues;
	
	public static String filename="";
	
	@SuppressWarnings("rawtypes")
	public static List<LinkedHashMap> wcmExceldata;
	public static boolean flagBaseLanguage = true;
	public static HashMap<String, HashMap<String, String>> translationSheet = new HashMap<String, HashMap<String, String>>();
	public static Map<String, List<String>> analyzerUserMap = new HashMap<String, List<String>>();
	public static Map<String, List<String>> analyzerFavUserMap = new HashMap<String, List<String>>();
	public static Map<String, List<String>> racfGroupMapping;
	public static String endImpersonateURL; // = "https://dealerpathqual.tal.deere.com/wps/myportal/dpath/!ut/p/a1/04_SD9OPyvLKK_bOryzXNQ7Uj9SPMrQ0MLbQj8zJ1y_IcVQEAE4wkcg!/";
	public static String PORTLET_LINKFLAG = "NA";
	public static String appVersion;
	public static HashSet<String> departmentNames = new HashSet<String>();
	public static Map<String, List<String>> analyzerUserMapReset = new HashMap<String, List<String>>();
	public static List<String> classesExecution;
	public static HashMap<String, String> translationSheetEnglish = new HashMap<String, String>();

	/*
	 * // Constants
	 * 
	 * public final String R3_HIS = "R3 Hispano"; public final String JDIN=
	 * "R2 JDIN"; public final String R1_SS= "R1 Sub Saharan Africa"; public final
	 * String R1_AS="R1 JD Asia"; public final String R4_USA = "R4 USA/Canada";
	 * public final String R1_CH="R1 China"; public final String
	 * R1_TH="R1 Thailand"; public final String R1_IN="R1 India"; public final
	 * String R4_AUNZ= "R4 Australia/New Zealand"; public final String R3_Bra =
	 * "R3 Brasil";
	 */

	public static List<String> headerList;
	public static List<String> headerListWCM;
	public static HashMap<String, String> excelListWCM;
	public static HashMap<String, String> excelList;
	public static List<HashMap<String, String>> finalResultforExcel = new ArrayList<>();
	public static List<HashMap<String, String>> finalResultforExcelWCM = new ArrayList<>();
	// pages objects
	public static Login_Page_POF loginPageFactory;
	public static Homepage_POF homePageFactory;
	// public static Homepage_POF homePageFactory1;
	public static PortletLinksPage_POF portletLinksFactory;
	public static PortalLeftNavigation_POF portalLeftNavFactory;
	public static HashMap<String, String> mapAddtionalTestcase;
	public static String flagDealerType;
	public static String flagDealerPrinciple;
	public static String flagAddtionalTestcases;
	public static String flagMultilingual;

	public static List<LinkedHashMap> userWCMData;
	public static Alerts_POF alertPageFactory;
	public static Announcements_POF announcementFactory;
	public static UtilityLinks_POF utilityLinksFactory;
	public static Favourites_POF favouritesFactory;
	public static Search_POF searchFactory;
	public static ProductSegment_POF productSegmentFactory;
	public static MyPreferences_POF myPreferencesPageFactory;
	public static ParentIndex_POF myParentIndexPageFactory;
	public static ChildIndex_POF myChildIndexPageFactory;
	public static GrandChildIndex_POF myGrandChildIndexPageFactory;
public static WCMInput wcmFactory;
	public static Alert_WCM_POF alertContent;
	public static WCM_Conetnt_POF wcmContent;
	public static HashMap<String,String> globalCountries=new HashMap<String, String>();
	public static HashMap<String,String> globalProductTypes=new HashMap<String, String>();
	
	public static String MRUList;

	/**
	 * This method is use to initiate the webElements of all mentioned pages
	 * 
	 * @author shrishail.baddi
	 * @createdAt 15-05-2018
	 * 
	 * @ModifiedBy shrey.choudhary
	 * @modifiedAt 07-06-2018
	 */
	public static void initPageElements() {
		loginPageFactory = PageFactory.initElements(BaseClass.wbDriver, Login_Page_POF.class);
		homePageFactory = PageFactory.initElements(BaseClass.wbDriver, Homepage_POF.class);
		portalLeftNavFactory = PageFactory.initElements(BaseClass.wbDriver, PortalLeftNavigation_POF.class);
		portletLinksFactory = PageFactory.initElements(BaseClass.wbDriver, PortletLinksPage_POF.class);
		alertPageFactory = PageFactory.initElements(BaseClass.wbDriver, Alerts_POF.class);
		announcementFactory = PageFactory.initElements(BaseClass.wbDriver, Announcements_POF.class);
		utilityLinksFactory = PageFactory.initElements(BaseClass.wbDriver, UtilityLinks_POF.class);
		favouritesFactory = PageFactory.initElements(BaseClass.wbDriver, Favourites_POF.class);
		searchFactory = PageFactory.initElements(BaseClass.wbDriver, Search_POF.class);
		productSegmentFactory = PageFactory.initElements(BaseClass.wbDriver, ProductSegment_POF.class);
		myPreferencesPageFactory = PageFactory.initElements(BaseClass.wbDriver, MyPreferences_POF.class);
		myParentIndexPageFactory = PageFactory.initElements(BaseClass.wbDriver, ParentIndex_POF.class);
		myChildIndexPageFactory = PageFactory.initElements(BaseClass.wbDriver, ChildIndex_POF.class);
		myGrandChildIndexPageFactory = PageFactory.initElements(BaseClass.wbDriver, GrandChildIndex_POF.class);
		alertContent=PageFactory.initElements(BaseClass.wbDriver, Alert_WCM_POF.class);
		wcmFactory=PageFactory.initElements(BaseClass.wbDriver, WCMInput.class);
		wcmContent=PageFactory.initElements(BaseClass.wbDriver, WCM_Conetnt_POF.class);

		
		
	}
	public static void setGlobalCountries() {
		BaseClass.globalCountries.put("JDIN_CONTENT_DE", "JDIN-MRU-Country");
		BaseClass.globalCountries.put("JDIN_CONTENT_EN", "JDIN-MRU-Country");
		BaseClass.globalCountries.put("JDIN_CONTENT_FR", "JDIN-MRU-Country");
		BaseClass.globalCountries.put("JDIN_CONTENT_RU", "JDIN-MRU-Country");
		BaseClass.globalCountries.put("R4_CONTENT_en_US", "R4-MRU-Country");
		BaseClass.globalCountries.put("R4_CONTENT_es_MX", "R4-MRU-Country");
		BaseClass.globalCountries.put("R4_CONTENT_fr_CA", "R4-MRU-Country");
		BaseClass.globalCountries.put("R3_CONTENT_en_US", "R3-Hispano-MRU-Country");
		BaseClass.globalCountries.put("R3_CONTENT_es_MX", "R3-Hispano-MRU-Country");
		BaseClass.globalCountries.put("R3_CONTENT_pt_BR", "R3-Brasil-MRU-Country");
		BaseClass.globalCountries.put("R1_CONTENT_en_Asia", "R1-JD Asia-MRU-Country");
		BaseClass.globalCountries.put("R1_CONTENT_en_SSA", "R1-Sub Saharan Africa-MRU-Country");
		BaseClass.globalCountries.put("R4_CONTENT_en_AU", "AU_NZ-MRU-Country");
	}
	
	
	public static void setGlobalProductTypes() {
		BaseClass.globalProductTypes.put("JDIN_CONTENT_DE", "JDIN-Product Type");
		BaseClass.globalProductTypes.put("JDIN_CONTENT_EN", "JDIN-Product Type");
		BaseClass.globalProductTypes.put("JDIN_CONTENT_FR", "JDIN-Product Type");
		BaseClass.globalProductTypes.put("JDIN_CONTENT_RU", "JDIN-Product Type");
		BaseClass.globalProductTypes.put("R4_CONTENT_en_US", "R4-Product Type");
		BaseClass.globalProductTypes.put("R4_CONTENT_es_MX", "R4-Product Type");
		BaseClass.globalProductTypes.put("R4_CONTENT_fr_CA", "R4-Product Type");
		BaseClass.globalProductTypes.put("R3_CONTENT_en_US", "R3-Hispano-Product Type");
		BaseClass.globalProductTypes.put("R3_CONTENT_es_MX", "R3-Hispano-Product Type");
		BaseClass.globalProductTypes.put("R3_CONTENT_pt_BR", "R3-Brasil-Product Type");
		BaseClass.globalProductTypes.put("R1_CONTENT_en_Asia", "R1-JD Asia-Product Type");
		BaseClass.globalProductTypes.put("R1_CONTENT_en_SSA", "R1-Sub Saharan Africa-Product Type");
		BaseClass.globalProductTypes.put("R4_CONTENT_en_AU", "AU_NZ-Product Type");
	}
	public static String getExcelDataByTestCaseID(String strTestId) throws Exception {
		HashMap<String, String> Data = mapAddtionalTestcase;
		
		if (Data.containsKey(strTestId)) {
			String value = Data.get(strTestId);
			// prefferedLanguage = Data.get
			return value;
		} else {
			LogFactory.error("------ TestCase_ID :  " + strTestId + " is not Avaliable ");
			return "None";
		}
	}

	public static LinkedHashMap<String, String> siteRegionCode() {
		LinkedHashMap<String, String> siteRegionListMap = new LinkedHashMap<String, String>();

		siteRegionListMap.put("R4_AU", "R4 Australia/New Zealand");
		siteRegionListMap.put("R3_B", "R3 Brasil");
		siteRegionListMap.put("R3_H", "R3 Hispano");
		siteRegionListMap.put("R1_IN", "R1 India");
		siteRegionListMap.put("R1_CN", "R1 China");
		siteRegionListMap.put("R1_AS", "R1 JD Asia");
		siteRegionListMap.put("R1_SSA", "R1 Sub Saharan Africa");
		siteRegionListMap.put("JDIN", "R2 JDIN");
		siteRegionListMap.put("R4", "R4 USA/Canada");
		siteRegionListMap.put("R1_TH", "R1 Thailand");
		siteRegionListMap.put("R1_CH", "R1 China");

		return siteRegionListMap;

	}

	public static LinkedHashMap<String, String> employeeGroup() {
		LinkedHashMap<String, String> employeeGroup = new LinkedHashMap<String, String>();

		employeeGroup.put("Region4", "CCSAIS");
		employeeGroup.put("Region4", "O05");
		employeeGroup.put("Region4", "O10");
		employeeGroup.put("Region4", "O17");
		employeeGroup.put("Region4", "O18");
		employeeGroup.put("Region4", "O5M");
		employeeGroup.put("Region4", "O76");

		return employeeGroup;

	}

}
