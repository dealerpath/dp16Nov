package com.deere.TestCasesFactory;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.PageFactory.Announcements_POF;

/**
 * @author shrey.choudhary This class having all test cases related to
 *         Announcement Portal.
 * @version 1.0
 *
 */
public class Announcement_TestCase extends BaseClass {

	WebDriver driver;
	static String strExpectedValue;
	static String strTCID;

	@BeforeClass
	public void getReportHeader() throws InterruptedException {
		ReportFactory.tableEnd();
		GenericFactory.createHeaderSection("Announcement");
		GenericFactory.navigateToHomePage();
	}

	/**
	 * @author shrey.choudhary 
	 * This method is verifying the title & description of
	 * Announcement portlet based on User's Country, Products, language etc.
	 * @return Test output that title & description is present or not.
	 * @throws Throwable
	 */
	@Test(priority = 1)
	public void verifyAnnouncementTitle() throws Throwable {

		@SuppressWarnings("rawtypes")
		List<LinkedHashMap> userWCMContent = ExcelFactory.getWCMContentDetails("AT-Announcement");
		for (int i = 0; i < userWCMContent.size(); i++) {

			String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			String userCountry = (String) userWCMContent.get(i).get("Dealer_Country").toString().trim();
			String MRUCountry = (String) userWCMContent.get(i).get("Country").toString().trim();
			String Title = (String) userWCMContent.get(i).get("Title").toString().trim();
			String userProductType = (String) userWCMContent.get(i).get("Dealer_ProductType").toString().trim();
			String wcmProductType = (String) userWCMContent.get(i).get("ProductType").toString().trim();
			String contenttype = (String) userWCMContent.get(i).get("ContentType");
			String copyToDepartment = (String) userWCMContent.get(i).get("CopyToDepartment");
			String wcmDealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString().trim();
			String departmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			String description = (String) userWCMContent.get(i).get("Description").toString().trim();
			LogFactory.beginTestCase("verify Announcement content on Announcement Portlet");

			GenericFactory.multilingualSwitch(library, wcmMultilingual);

			announcementFactory.verifyAnnouncementsOnTheAnnouncementPortlet(wcmTestCaseID, userCountry, MRUCountry,
					departmentName, copyToDepartment, wcmDealerType, userProductType, wcmProductType, contenttype,
					Title, description);

		}
	}

	/**
	 * @author shrey.choudhary 
	 * This method is verifying the title & description of
	 * Announcement portlet should not visible to user when a particular
	 * department is unchecked given in test data sheet.
	 * @return Test output that title & description is present or not if related department is unchecked.
	 * @throws Throwable
	 */
	@Test(priority = 2)
	public static void verifyAnnouncementOnDepartmentUncheck() throws Throwable {
		String wcmDeptName = "";
		String titleName = "";
		String strTCID = "";
		String wcmCopyToDepartment = "";
		List<LinkedHashMap> userWCMContent = ExcelFactory
				.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Announcement");
		GenericFactory.navigateToHomePage();
		for (int i = 0; i <= userWCMContent.size(); i++) {
			GenericFactory.checkDepartmentMyPreference();
			wcmDeptName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
			titleName = (String) userWCMContent.get(i).get("Title").toString().trim();
			strTCID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			wcmCopyToDepartment = (String) userWCMContent.get(i).get("CopyToDepartment").toString().trim();
			String description = (String) userWCMContent.get(i).get("Description").toString().trim();

			announcementFactory.verifyAnnouncementTitleOnDepartmentUncheck(wcmDeptName, titleName, strTCID,
					wcmCopyToDepartment, description);

		}

	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying the title & description of
	 * Announcement portlet should not visible to user when a particular
	 * product is unchecked given in test data sheet.
	 * @return Test output that title & description is present or not if related product is unchecked.
	 * @throws Throwable
	 */
	@Test(priority = 3)
	public static void verifyAnnouncementOnProductUncheck() throws Throwable {
		String UserDefinedProducts = "";
		String WCMProducts = "";
		String Title = "";
		String wcmTestCaseID = "";
		List<LinkedHashMap> userWCMContent = ExcelFactory
				.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Announcement");
		GenericFactory.navigateToHomePage();
		System.out.println("userWCMContent===========" + userWCMContent);

		for (int i = 0; i < userWCMContent.size(); i++) {

			wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
			UserDefinedProducts = (String) userWCMContent.get(i).get("Dealer_ProductType").toString().trim();
			WCMProducts = (String) userWCMContent.get(i).get("ProductType").toString().trim();
			Title = (String) userWCMContent.get(i).get("Title").toString().trim();

			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			String description = (String) userWCMContent.get(i).get("Description").toString().trim();
			GenericFactory.multilingualSwitch(library, wcmMultilingual);

			announcementFactory.verifyAnnouncementTitleOnProductUncheck(UserDefinedProducts, WCMProducts, Title,
					wcmTestCaseID, description);
		}
	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying announcement header format is correct or not.
	 * @return Test output that header format is correct or not.
	 * @throws Throwable
	 */
	@Test(priority = 4)
	public static void verifyAnnouncementHeaderFormat() throws Throwable {

		LogFactory.beginTestCase("Verifying the Announcement header format");

		strTCID = "TC09_Announcement_Portlet";
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			announcementFactory.verifyAnnouncementHeaderContentWithFormat(strTCID);
		}
	}

	/**
	 * @author shrey.choudhary 
	 * This method is verifying that any announcement which is having embedded links should not be broken.
	 * @return Test output that embeded links are broken or not, if broken test case would be failed.
	 * @throws Throwable
	 */
	@Test(priority = 5)
	public static void verifyAnnouncementPortletEmbededLinks() throws Throwable {
		LogFactory.beginTestCase("Verifying the embeded links showing on the Announcement portlet");
		strTCID = "TC10_Announcement_Portlet";
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			announcementFactory.verifyEmbededlinks(strTCID);
		}
	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying that all announcement should be order as per their published date.
	 * @return Test output that all announcements are sorted as per their published date, if not test case is getting failed.
	 * @throws Throwable
	 */
	@Test(priority = 6)
	public static void verifyOrderOfAnnouncemnts() throws Throwable {
		LogFactory.beginTestCase(
				"Verify whether the latest Announcements are showing at top on the Announcement portlet");
		strTCID = "TC11_Announcement_Portlet";
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			announcementFactory.verifyAnnouncemntsShowingInDescendingOrderOnDateTime(strTCID, strExpectedValue);
		}
	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying that announcement count is showing correct on header.
	 * @return Test output that all announcements count is showing correct on header or not, if not test case is getting failed.
	 * @throws Throwable
	 */
	@Test(priority = 7)
	public static void verifyAnnouncementCountOnHeader() throws Throwable {
		LogFactory.beginTestCase("Verifying the announcement count showing on Announcement portlet header");
		strTCID = "TC12_Announcement_Portlet";
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
		if (!strExpectedValue.equalsIgnoreCase("None")) {
			announcementFactory.verifyAnnouncementHeaderCount(strTCID);
		}
	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying that announcement having read more & collapse link is working properly.
	 * @return Test output that announcement having read more & collapse link is working properly, if not test case is getting failed.
	 * @throws Throwable
	 */
	@Test(priority = 8)
	public static void verifyReadMoreAndCollapseLinkOnAnnouncement() throws Throwable {
		LogFactory.beginTestCase("Verify the Readmore and Collapse links on the Announcement Portlet");
		strTCID = "TC13_Announcement_Portlet";
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);

		if (!strExpectedValue.equalsIgnoreCase("None")) {
			announcementFactory.checkReadMoreAndCollapseLinkForAnnouncement(strTCID);
		}
	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying that announcement having Expand & collapse link is working properly.
	 * @return Test output that announcement having Expand & collapse link is working properly, if not test case is getting failed.
	 * @throws Throwable
	 */
	@Test(priority = 9)
	public static void verifyExpandAndCollapseLinkOnAnnouncement() throws Throwable {
		LogFactory.beginTestCase("Verify the Expand and Collapse links on the Announcement Portlet");
		strTCID = "TC14_Announcement_Portlet";
		strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);

		if (!strExpectedValue.equalsIgnoreCase("None")) {
			announcementFactory.verifyExpandAndCollapseLinkOnAnnuoncementPortlet(strTCID);
		}
	}
	/**
	 * @author shrey.choudhary 
	 * This method is verifying the title & description of
	 * Announcement portlet should not visible to user when a particular
	 * country is unchecked given in test data sheet.
	 * @return Test output that title & description is present or not if related country is unchecked.
	 * @throws Throwable
	 */
	@Test(priority = 10)
	public static void verifyCountryUncheckOfAnnouncementPortletOnHomepage() throws Throwable {
		String wcmCountry = "";
		String titleName = "";
		String strTCID = "";
		@SuppressWarnings("rawtypes")
		List<LinkedHashMap> userWCMContent = ExcelFactory
				.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Announcement");
		GenericFactory.navigateToHomePage();
		for (int i = 0; i <= userWCMContent.size(); i++) {
			wcmCountry = (String) userWCMContent.get(i).get("Country").toString().trim();
			titleName = (String) userWCMContent.get(i).get("Title").toString().trim();
			strTCID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();

			String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
			String library = (String) userWCMContent.get(i).get("Library").toString().trim();
			GenericFactory.multilingualSwitch(library, wcmMultilingual);
			String description = (String) userWCMContent.get(i).get("Description").toString().trim();
			Announcements_POF.verifyCountryUncheckOnHomepage(wcmCountry, titleName, strTCID, description);
		}
	}

}
