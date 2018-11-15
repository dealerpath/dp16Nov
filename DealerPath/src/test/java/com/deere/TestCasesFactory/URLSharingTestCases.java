package com.deere.TestCasesFactory;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.ImpersonateUser;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.PageFactory.Homepage_POF;
import com.deere.PageFactory.URLSharing_POF;
import com.deere.Helpers.EndImpersonateUser;

@Test(groups = { "URLSharing" })
public class URLSharingTestCases extends BaseClass {

	private static final String String = null;
	WebDriver driver;
	static String strExpectedValue;
	static String strTCID;
	static String PageName;
	static String strdealer2,strWCMID,strDealer_Country,strDealer_ProductType,strDepartmentName,strContentType,strTitle,strCountry,strProductType,strDealerTypeMainSub,strRACFGroups;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("URL Sharing");
		GenericFactory.navigateToHomePage();
	}
	
	@Test(priority = 0)
	public void verifyURLSharing() throws Throwable {
		strTCID = "TC300_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		String [] strExpectedValueArray = strExpectedValue.split(",");
		strdealer2 = strExpectedValueArray[0];
		strWCMID = strExpectedValueArray[1];
	
		LinkedHashMap WCMDetails = ExcelFactory.getWCMByTCID(strWCMID);
		System.out.println(WCMDetails);
		
		strDealer_Country=(String) WCMDetails.get("Dealer_Country");
		strDealer_ProductType=(String) WCMDetails.get("Dealer_ProductType");
		strDepartmentName=(String) WCMDetails.get("DepartmentName");
		strContentType=(String) WCMDetails.get("ContentType");
		strTitle=(String) WCMDetails.get("Title");
		strCountry=(String) WCMDetails.get("Country");
		strProductType=(String) WCMDetails.get("ProductType");
		strDealerTypeMainSub=(String) WCMDetails.get("DealerType (Main/Sub)");
		strRACFGroups=(String) WCMDetails.get("RACFGroups");
				
		URLSharing_POF.URLValidation(strTCID,strdealer2,strWCMID,strDealer_Country,strDealer_ProductType,strDepartmentName,strContentType,strTitle,strCountry,strProductType,strDealerTypeMainSub,strRACFGroups);
		
		
	}
	
	
	
	
//URL sharing for Index pages.Verify Url sharing for all index level pages and rich text 
	
	/*@Test(priority = 0)
	public void verifyURLSharing() throws Throwable {
		strTCID = "TC30_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		String[] strWCMInfo = strExpectedValue.split(",");
		String strDealer = strWCMInfo[0];
		LogFactory.beginTestCase(strTCID + "Verify URL sharing  test case begins");
		URLSharing_POF.ValidateURL(strTCID,strDealer);
	}*/
	//URL sharing for rich text. Author : Suchitra
	/*@Test(priority = 1)
	public void verifyURLSharingRichText() throws Throwable {

		strTCID = "TC31_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		LogFactory.beginTestCase(strTCID + "Verify URL sharing  test case begins");
		String PageName = "AT-Rich Text";//Changed the pagename value from AT-Index to AT-Rich Text
		URLSharing_POF.ValidateURL(strExpectedValue, PageName);
		URLSharing_POF.ValidateURL(strTCID,strDealer);
		
	}
	//URL sharing for Main and Sub. Author : Suchitra
		@Test(priority = 3)
		public void verifyURLSharingforMainandSub() throws Throwable {
			strTCID = "TC33_URLSharing";
			strExpectedValue = getExcelDataByTestCaseID(strTCID);
			String PageName = "AT-Index Page";
			BaseClass.flagDealerType = "Main";
			URLSharing_POF.ValidateURL(strExpectedValue, PageName);

		}*/
		
	//URL sharing for Department uncheck error message scenario for single dealer
	/*String DepartmentName;

	@Test(priority = 2)
	public void verifyURLSharingforError() throws Throwable {
		strTCID = "TC32_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		LogFactory.beginTestCase(
				strTCID + "Verify error message should be displayed on hitting the URL for deactivated department.");
		URLSharing_POF.ValidateURLforError(strTCID);
	}
	
	//URL Sharing for Product type
	String ProductType;
	WebElement userelement = ValidationFactory
			.getElementIfPresent(By.xpath("//div[contains (@class,'user-info') and @id ='js-user-info']"));
	@Test(priority = 4)
	public void verifyURLSharingforProductType() throws Throwable {
		strTCID = "TC33_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		String[] strWCMInfo = strExpectedValue.split(",");
		String strDealer = strWCMInfo[0];
		String strWCMId_IndexPg = strWCMInfo[1];
		LogFactory.beginTestCase(strTCID + "Verify URL sharing for Product type");
		// for AT-Index Page
		LogFactory.beginTestCase("Verify URL sharing for Product type >>>>>>>>>>>>>>>>>>>>>AT-Index Page");
		PageName = "AT-Index Page";
		URLSharing_POF.ValidateURLforProductType(strTCID, strWCMId_IndexPg, PageName);

		LogFactory.beginTestCase("Verify URL sharing for Product type >>>>>>>>>>>>>>>>>>>>>AT-ChildIndex Page");
		// for AT-ChildIndex Page
		String strWCMId_ChildIndexPg = strWCMInfo[2];
		PageName = "AT-ChildIndex Page";
		URLSharing_POF.ValidateURLforProductType(strTCID,  strWCMId_ChildIndexPg, PageName);

		LogFactory.beginTestCase("Verify URL sharing for Product type >>>>>>>>>>>>>>>>>>>>>AT-GrandChild Index Page");
		// for AT-GrandChild Index Page
		String strWCMId_GChildIndexPg = strWCMInfo[3];
		PageName = "AT-GrandChild Index Page";
		URLSharing_POF.ValidateURLforProductType(strTCID, strWCMId_GChildIndexPg, PageName);
	}
	
	//executed on cert.There is data dependency.
	//URL Sharing for Country
	@Test(priority = 5)
	public void verifyURLSharingforCountry() throws Throwable {
		strTCID = "TC34_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		String[] strWCMInfo = strExpectedValue.split(",");
		String strEmployee = strWCMInfo[0];
		String strWCMId = strWCMInfo[1];
		String strSelectSite = strWCMInfo[2];
		String strDealer1 = strWCMInfo[3];
		LogFactory.beginTestCase(strTCID + "Verify URL sharing for Country");
		
		// for AT-Index Page
		LogFactory.beginTestCase("Verify URL sharing for Product type >>>>>>>>>>>>>>>>>>>>>AT-Index Page");
		PageName = "AT-Index Page";
		URLSharing_POF.ValidateURLforCountryEmployeeToDealer(strTCID, strDealer1, strWCMId, PageName,
				strSelectSite);

	}
	//Executed on https://dealerpathqual.tal.deere.com/ . There is data dependency
	//URL sharing for Main and Sub dealer type
	
	@Test(priority = 6)
	public void verifyURLSharingforMainSubDealerType() throws Throwable {
		strTCID = "TC35_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		String[] strWCMInfo = strExpectedValue.split(",");
//		String strMainDealer = strWCMInfo[0];
		String strSubDealer = strWCMInfo[1];
		String strSelectSite = strWCMInfo[2];
		String strWCMId = strWCMInfo[3];
		LogFactory.beginTestCase(strTCID + "Verify URL sharing for Country");
		
		//for AT-Index Page
		LogFactory.beginTestCase("Verify URL sharing for Dealer Type >>>>>>>>>>>>>>>>>>>>>AT-Index Page");
		PageName = "AT-Link";
		URLSharing_POF.ValidateURLforDealerTypeMainSub(strTCID,strSubDealer, PageName,strSelectSite,strWCMId);
	}
	
	//Executed on https://dealerpathqual.tal.deere.com/ . There is data dependency
	//URL Sharing for Racf groups
	@Test(priority = 7)
	public void verifyURLSharingforRacfGroup() throws Throwable {
		strTCID = "TC36_URLSharing";
		strExpectedValue = getExcelDataByTestCaseID(strTCID);
		String[] strWCMInfo = strExpectedValue.split(",");
		String strWCMID = strWCMInfo[0];
//		String strDealerBelongingToRacfGrp = strWCMInfo[1];
		String strDealerBelongingToOtherGrp = strWCMInfo[2];
		LogFactory.beginTestCase(strTCID + "Verify URL sharing for Country");
		
		//for AT-Index Page
				LogFactory.beginTestCase("Verify URL sharing for Dealer Type >>>>>>>>>>>>>>>>>>>>>AT-Link");
				PageName = "AT-Link";
				URLSharing_POF.ValidateURLforRACFGroups(strTCID,strWCMID, strDealerBelongingToOtherGrp,PageName);
	}*/
	
/*	//URL Sharing : Unchecking all the products
	@Test(priority = 7)
	public void verifyURLSharingUncheckingAllProd() throws Throwable {
		System.out.println();
	}
	//URL Sharing : Unchecking all the Country
	@Test(priority = 8)
	public void verifyURLSharingUncheckingAllCountry() throws Throwable {
		
	}*/
	@AfterClass
	public void getReportFooter() throws InterruptedException {
		LogFactory.endTestCase("URLSharing Test cases");

	}

}