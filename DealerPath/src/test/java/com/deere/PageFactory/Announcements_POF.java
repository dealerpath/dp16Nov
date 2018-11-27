//Create separate class under page factory with name announcement_Factory and pase it u can run this method any where by classname.methodname

package com.deere.PageFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;

/**
 * @author shrey.choudhary This class having all test cases related to
 *         Announcement Portal.
 * @version 1.0
 */
public class Announcements_POF extends BaseClass {

	final WebDriver AnncmntDriver;

	public Announcements_POF(WebDriver driver) {
		this.AnncmntDriver = driver;
	}

	@FindBy(how = How.XPATH, using = ".//div[@class='section']//div[@class='list-item-body']/span")
	public static List<WebElement> AnnouncementTitleContents;

	@FindBy(how = How.XPATH, using = ".//*[@class='section-title announcement']")
	static WebElement wbelHeaderTitleAnnouncement;

	@FindBy(how = How.XPATH, using = ".//*[@class='wide-list hide-overflow is-expanded']")
	static WebElement wbelBodyAnnouncement;

	@FindBy(how = How.XPATH, using = ".//*[@class='wide-list hide-overflow is-expanded']/div")
	static List<WebElement> listAnnouncementBody;

	@FindBy(how = How.XPATH, using = ".//div/div/div/div/section/div/div/div/h3")
	static List<WebElement> listHeader;

	@FindBy(how = How.XPATH, using = ".//*[@class='wide-list hide-overflow is-expanded']/div/div[1]")
	static List<WebElement> listAnnouncementTitleBody;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[3]/section/div/div[2]/div")
	static List<WebElement> listAnnouncementTableElements;

	@FindBy(how = How.XPATH, using = ".//div[@class='section']//div[@class='list-item-body']/span")
	static List<WebElement> listAnnouncementTableTitle;

	@FindBy(how = How.XPATH, using = ".//*[@class='list-item-info parse-department']")
	static List<WebElement> listAnnouncementDeptName;

	@FindBy(how = How.XPATH, using = ".//*[@class='leftNav']/li/a")
	static List<WebElement> listAnnouncementLeftNavigationDeptName;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[3]/section/div/div[2]")
	public static WebElement wbelAnnouncementFramePath;

	@FindBy(how = How.XPATH, using = ".//div/div/div/div/section/div/div/div/h3")
	public static List<WebElement> HeaderList;

	@FindBy(how = How.XPATH, using = "//*[@class='group-value checkbox-value']//label[@class='click-target-only']//input[@type='checkbox']")
	public static List<WebElement> allCheck;

	@FindBy(how = How.XPATH, using = "//div[@class='group-value checkbox-value']/div[@class='value']")
	public static List<WebElement> allCheck1;

	@FindBy(how = How.XPATH, using = ".//div[@class='section']//div[@class='list-item-body']/span")
	public static List<WebElement> AnnouncementTableTitle;

	@FindBy(how = How.XPATH, using = ".//*[@id='js-segments-popover']/div[2]/div[3]/button")
	public static WebElement ApplyFilterButton;

	@FindBy(how = How.XPATH, using = "//div[@class='wide-list hide-overflow is-expanded']")
	public static WebElement Announcementportlet;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[3]/section/div/div[2]/div[2]/div[2]/div")
	public static WebElement linkAnnouncementOfExpand;

	@FindBy(how = How.XPATH, using = ".//*[@class='search-icons countryGrouping']")
	public static WebElement countryGroupingIcon;

	@FindBy(how = How.XPATH, using = ".//*[@id='country-grouping-form']/div/div/div")
	public static List<WebElement> countryGroupingList;

	@FindBy(how = How.ID, using = "country-grouping-save")
	public static WebElement countryApplyFilterButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='js-country-group-popover']/div[2]/p")
	public static WebElement countryError;

	@FindBy(how = How.ID, using = "countryGroups")
	public static List<WebElement> webElementOfCountryCheckBox;

	@FindBy(how = How.XPATH, using = ".//*[@id='country-grouping-form']/div/div/div/label/div")
	public static List<WebElement> listCountryNameFromWebPage;

	@FindBy(how = How.CSS, using = ".//*[@class='modal-dialog']//..[@class='modal-content']//..[@class='modal-header']//../button[@class='close'][@type='button']")
	public static WebElement crossIconOnMyPreference;

	@FindBy(how = How.XPATH, using = ".//*[@id='preference-save']")
	public static WebElement MyPreferenceSavebtn;

	@FindBy(how = How.XPATH, using = "//*[@id='productSegmentsError']")
	public static WebElement wblProdSegmentError;

	/**
	 * @author shrey.choudhary This method is used to verify the announcement
	 *         table(frame) is present on Homepage.
	 * @param TCID
	 * @throws Throwable
	 */
	// To verify announcement Table is present or not
	public static void verifyAnnouncementTableOnHomePage(String strTCID) throws Throwable {
		String strFlag = "Pass";
		String strResult = "Announcement portlet is not displaying.";

		try {
			List<WebElement> listAnnTableObj = listAnnouncementTableElements;

			if (listAnnTableObj.size() > 0) {
				strResult = "Announcement portlet is displaying.";

				LogFactory.info("Announcement portlet is present");
			}
			ReportFactory.reporterOutput(strTCID, "Verify Announcement portlet is present on Home Page", "NA",
					"Announcement should displayed.", strResult, strFlag);
		} catch (Exception e) {
			String er = e.getMessage().toString();

			ReportFactory.reporterOutput(strTCID, "Verify Announcement portlet is present on Home Page", "NA",
					"Announcement portlet should be displayed.", strResult, er.substring(0, 25));

		}
	}

	/**
	 * @author shrey.choudhary This method is verifying the announcement content on
	 *         homepage
	 * @param strTCID
	 * @throws Throwable
	 */
	public static void verifyAnnouncementContentIsPrsent(String strTCID) throws Throwable {
		String strFlag = "Pass";
		String strResult = "Announcement portlet content/body is not present";

		try {
			WaitFactory.waitForPageLoaded();
			if (ValidationFactory.isElementPresent(listAnnouncementTableElements.get(0))) {
				List<WebElement> listAnnTableObj = listAnnouncementTableElements;

				if (listAnnTableObj.size() > 0) {

					strFlag = "Pass";
					strResult = "Announcement portlet content/body is present";

				}
			}
			ReportFactory.reporterOutput(strTCID, "Verify Announcement content/body is showing", "NA",
					"Announcement content/body should be display", strResult, strFlag);

		} catch (Exception e) {
			String er = e.getMessage().toString();

			ReportFactory.reporterOutput(strTCID, "Verify Announcement content/body is showing", "NA",
					"Announcement Content/body should be dispaly", strResult, er.substring(0, 25));
		}
	}

	/**
	 * @author shrey.choudhary
	 * @param strExpectedHeaderTxt
	 * @param strTC_ID
	 * @throws Throwable
	 */
	public static void verifyAnnouncementHeaderTextPrefferedLang(String strExpectedHeaderTxt, String strTC_ID)
			throws Throwable {

		String strTCID = "TC12_Homepage";
		String strActualHeaderText = null;
		String strFlag = "Fail";
		String strResult = "Announcement header is NOT showing as per the user preferred language.";

		try {
			if (listAnnouncementTableElements.size() > 0) {

				// LogFactory.info("Verify announcement header is showing as per the user
				// preferred language. ");

				if (ValidationFactory.isElementPresent(wbelHeaderTitleAnnouncement)) {

					String txtheadertxt = wbelHeaderTitleAnnouncement.getText().toString().trim();
					String[] headertxt = txtheadertxt.split(Pattern.quote("("));

					String actualHeaderTxt = headertxt[0].trim();
					if (actualHeaderTxt.length() > 0) {

						if (actualHeaderTxt.equals(strExpectedHeaderTxt)) {
							strFlag = "Pass";
							strResult = "Announcement header " + actualHeaderTxt
									+ " is showing as per the user preferred language.";
							LogFactory.info("Announcement header is showing as per the user preferred language."
									+ "Actual Value-" + strActualHeaderText + ";" + "Expected Value-"
									+ strExpectedHeaderTxt);
						}
					}
				}
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify Announcement header text is Present as per the user preferred language.",
					strExpectedHeaderTxt, "Announcement header should show as per the user preferred language.",
					strResult, strFlag);

		} catch (Exception e) {
			// LogFactory.error("e");
			String er = e.getMessage().toString();

			ReportFactory.reporterOutput(strTCID,
					"Verify Announcement header text is showing as per the user preferred language.", "NA",
					"Announcement Content should display", strResult, er.substring(0, 25));
		}
	}

	// verify Announcement in detail
	/**
	 * @author shrey.choudhary This method is verifying the announcement detail on
	 *         Homepage.
	 * @throws Throwable
	 */
	public static void verifyAnnouncementInDetail_Homepage() throws Throwable {

		String strTCID = "TC14_Announcement";
		String strFlag = "Fail";
		String strresult = "Announcement is displaying in Detail on Homepage";

		try {
			// To get all title of Announcement
			List<WebElement> listItemTitleDealerHomePage = listAnnouncementTableElements;
			List<WebElement> listItemInfoDepartment = listAnnouncementDeptName;

			for (int i = 0; i < listItemTitleDealerHomePage.size(); i++) {
				String titleValueDealerHomePage = listItemTitleDealerHomePage.get(i).getText().toString().trim();

				// To get Dept name
				String announcementDeptName = listItemInfoDepartment.get(i).getText().toString().trim();

				LogFactory.info("Navigating to department Page- " + announcementDeptName);

				// To get all dept list
				List<WebElement> deptListNames = listAnnouncementLeftNavigationDeptName;
				for (int k = 0; k < deptListNames.size(); k++) {
					String deptName = deptListNames.get(k).getText().toString().trim();
					if (deptName.equals(announcementDeptName)) {
						System.out.println(deptName + " = equals to = " + announcementDeptName);
						deptListNames.get(k).click();

						// Retrive and Compare
						List<WebElement> listItemTitleDeptPage = listAnnouncementTableTitle;

						for (int j = 0; j < listItemTitleDeptPage.size(); j++) {
							String titleValueDeptPage = listItemTitleDeptPage.get(j).getText().toString().trim();

							// to validate on desired dept
							if (titleValueDealerHomePage.equals(titleValueDeptPage)) {
								strFlag = "Pass";

								LogFactory.info(titleValueDealerHomePage
										+ " Homepage Announcement Deptartment Page Title is same as showing on DelerPath Homepage. "
										+ titleValueDeptPage);

								ReportFactory.reporterOutput(strTCID, "Verify Announcement In Detail Homepage",
										titleValueDealerHomePage, "Announcement is displaying in Detail on Homepage",
										strresult, strFlag);

							}

							else {

								continue;
							}
						}

						// to go back again to Announcement page
						// BaseClass.wbDv.navigate().back();

						BaseClass.wbDriver.findElement(By.xpath(".//*[@class='app-title']")).click();

						LogFactory.info("Navigating back to the DealerPath Homepage");

						ReportFactory.reporterOutput(strTCID, "Verify the Announcement In Detail on Homepage", "NA",
								"Announcement should show in Detail on Homepage", strresult, strFlag);

					}
				}

			}
		} catch (Exception e) {
			LogFactory.info(e.getMessage());
		}
	}

	// ****************************************************** E-O-M
	// ******************************************************************************

	public static void verifyDateFormat(String strTCID, String strHeader, String strDateFormat) throws Throwable {
		// String TCID = "TC14_Announcement";
		String strFlag = "Fail";
		String strResult = "Announcement date is not showing as per the UI Format.";

		try {
			List<Date> listDateList = GenericFactory.getListOfDatesByHeaderName(strHeader);
			LogFactory.info("dateList= " + listDateList);

			// validate format
			boolean dateFormatValue = GenericFactory.isValidDateFormat(strDateFormat, listDateList);
			if (dateFormatValue) {
				LogFactory.info("Date format is right.");
			} else {
				LogFactory.info("Date format is right.");
			}
		} catch (Exception e) {
			// LogFactory.error("e");
			// String er = e.getMessage().toString().trim();

			ReportFactory.reporterOutput(strTCID, "verify Date Format", "NA",
					"Announcement Date format should display in yyyy-mm-dd. ", strResult, strFlag);
		}

	}

	// ***************************************************** E-O-M
	// *****************************************************************************

	// To do the date sort
	public static void verifyDateOrder(String strHeader) throws Throwable {
		String strTCID = "TC14_Announcement";
		String strFlag = "Fail";
		String strResult = "Announcement date is not showing as per the UI Format.";

		try {
			List<Date> dateList = GenericFactory.getListOfDatesByHeaderName(strHeader);
			LogFactory.info("dateList= " + dateList);

			// sort
			boolean sortedDate = GenericFactory.verifyDateSortedOrder(dateList);
			if (sortedDate) {
				strFlag = "Pass";
				LogFactory.info("Date is in sorted order.");

				ReportFactory.reporterOutput(strTCID, "verify Date Order", "NA",
						"Announcement Date order should display in new to old. ", strResult, strFlag);
			} else {
				LogFactory.info("Date is not in sorted order.");

			}
		} catch (Exception e) {
			// LogFactory.error("e");
			// String er = e.getMessage().toString().trim();

			ReportFactory.reporterOutput(strTCID, "verify Date Order", "NA",
					"Announcement Date order should display in new to old. ", strResult, strFlag);
		}
	}

	/**
	 * @author shrey.choudhary This method is verifying the title & description of
	 *         Announcement portlet based on User's Country, Products, language etc.
	 * @return Test output that title & description is present or not.
	 * @throws Throwable
	 */
	public void verifyAnnouncementsOnTheAnnouncementPortlet(String strWCMTCID, String userDefinedCountry,
			String wcmCountry, String departmentName, String copyToDepartment, String wcmDealerType,
			String userDefinedProducts, String wcmProducts, String contentType, String announcementTitle,
			String description) throws Throwable {

		boolean country = GenericFactory.userAndWCMCountryComparison(userDefinedCountry, wcmCountry);
		boolean product = GenericFactory.userAndWCMProductTypeComparison(userDefinedProducts, wcmProducts);
		boolean flagTitle = true;
		boolean booleanDealerType = false;
		boolean flagActiveDepartment = true;
		List<String> departmentList = new ArrayList<String>();

		String inputValue = "<B>WCM Maping :</B> Country : " + wcmCountry + " ,</br><B> WCM Product Type : </B>  "
				+ wcmProducts + "</br><B>WCM Department Name :</B>  " + departmentName
				+ "</br><B> Copy to Department  :</B>  " + copyToDepartment + "</br> <B>Dealer Maping :</B> Country : "
				+ userDefinedCountry + " ,</br><B>User's Product Type : </B>" + userDefinedProducts;
		String testCaseDescription = " Verify the Announcement titles on the Announcement portlet for the Content Type :<B>"
				+ contentType + "</B>";
		String expectedValue = "Verify the announcement title :" + announcementTitle
				+ " published and displayed in Announcement Portlet ";
		String actualValue = "";
		List<String> inactiveDepartment = new ArrayList<String>();
		// Getting list of all active links from left navigation window
		if (departmentName.equalsIgnoreCase("General")) {
			GenericFactory.navigateToHomePage();
		} else {

			flagActiveDepartment = GenericFactory.clickOnDepartmentByName(departmentName);
		}

		if (flagActiveDepartment == true) {
			WaitFactory.waitForPageLoaded();
			flagTitle = verifyAnnouncementHeader(announcementTitle, description);
			if (country && product && flagTitle) {

				// compare DealerType with WCM DealerType content of AT-Announcement row.
				if (!flagDealerType.equalsIgnoreCase("NA")) {

					booleanDealerType = GenericFactory.verifyDealerType(wcmDealerType);

					if (booleanDealerType) {

						if (!copyToDepartment.equalsIgnoreCase("NA")) {

							departmentList.addAll(GenericFactory.splitString(copyToDepartment, ","));
							for (int j = 0; j < departmentList.size(); j++) {
								if (GenericFactory.clickOnDepartmentByName(departmentList.get(j))) {
									if (verifyAnnouncementHeader(announcementTitle, description)) {
										actualValue = actualValue.concat("<B>Title name :</B>  " + announcementTitle
												+ " is present in <B>" + departmentList.get(j) + " department.</B>");
									} else {
										actualValue = actualValue.concat(
												" <B>Title name :</B>  " + announcementTitle + " is present in <B>"
														+ departmentName + " department but NOT present in "
														+ departmentList.get(j) + " department</B>");
										flagTitle = false;
										break;
									}
								} else {
									inactiveDepartment.add(departmentList.get(j));
								}

							}
							if (inactiveDepartment.size() > 0) {
								actualValue = actualValue.concat("</br><B>List of inactive department is :</B>"
										+ String.join(", ", inactiveDepartment));
							}
						} else {
							inputValue = inputValue + "</br><B> User Dealer Type :</B> " + BaseClass.flagDealerType
									+ "<br/>" + "</br><B> WCM DealerType :</B> " + wcmDealerType;
							actualValue = actualValue.concat(" <B>Title name :</B>  " + announcementTitle
									+ " is present in <B>" + departmentName + " department</B>");
						}
					} else {
						flagTitle = false;
						inputValue = inputValue + "<B> User Dealer Type :</B> " + BaseClass.flagDealerType + "<br/>"
								+ "<B> WCM DealerType :</B> " + wcmDealerType;
						actualValue = actualValue.concat(" <B>Title name :</B>  " + announcementTitle
								+ " is present but Dealer Type is not matching with WCM Content ");

					}
				} else {
					if (!copyToDepartment.equalsIgnoreCase("NA")) {
						departmentList.addAll(GenericFactory.splitString(copyToDepartment, ","));
						for (int j = 0; j < departmentList.size(); j++) {

							if (GenericFactory.clickOnDepartmentByName(departmentList.get(j))) {

								if (verifyAnnouncementHeader(announcementTitle, description)) {
									actualValue = actualValue
											.concat("<ul style=\"list-style-type:circle\"> <li> <B>Title name :</B>  "
													+ announcementTitle + " is present in <B>" + departmentList.get(j)
													+ " departments</B></li></ul>");
								} else {
									actualValue = actualValue
											.concat("<ul style=\"list-style-type:circle\"> <li> <B>Title name :</B>  "
													+ announcementTitle + " is present in <B>" + departmentName
													+ " </B>department but NOT present in <B>" + departmentList.get(j)
													+ " department</B></li></ul>");
									flagTitle = false;
									break;
								}
							} else {
								System.out.println();
								inactiveDepartment.add(departmentList.get(j));
							}
						}
						if (inactiveDepartment.size() > 0) {
							actualValue = actualValue.concat("</br><B>List of inactive department is :</B>"
									+ String.join(", ", inactiveDepartment));
						}
					} else {
						actualValue = actualValue.concat(
								"User product and country match with the WCM content and title : " + announcementTitle
										+ " is also present in <B>" + departmentName + " department</B>");
					}
				}
				String flag = flagTitle ? "Pass" : "Fail";
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue, actualValue,
						flag);
			} else if (country && product && !flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User product and country match but title is not present in <B>" + departmentName
								+ " </B> department",
						"Fail");
			} else if (country && !product && !flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User product doesn't match hence title is not present", "Pass");
			} else if (!country && !product && !flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User product & country doesn't match hence title is not present", "Pass");
			} else if (!country && product && flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User country doesn't match but title is present", "Fail");
			} else if (!country && product && !flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User country doesn't match hence title is not present", "Pass");
			} else if (!country && !product && flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User country & product doesn't match but title is present", "Fail");
			} else if (country && !product && flagTitle) {
				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
						actualValue + "User product doesn't match but title is present", "Fail");
			}
		} else {
			ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, expectedValue,
					"User department is not active.", "Fail");

		}
	}

	public static boolean verifyAnnouncementHeader(String Title, String descriptionWCM) throws Throwable {
		List<WebElement> framePath = BaseClass.wbDriver
				.findElements(By.xpath(".//div[@class='section']//div[@class='list-item hide-overflow']"));
		try {
			for (int i = 0; i < AnnouncementTitleContents.size(); i++) {
				String temp = AnnouncementTitleContents.get(i).getText().toString().trim();
				int colonIndex = temp.indexOf(':');
				String titleOfAnnouncement = temp.substring(colonIndex + 1, temp.length()).toString().trim();
				String description = "";
				boolean readMoreLink = false;
				try {
					WebElement singleFrame = BaseClass.wbDriver.findElement(By
							.xpath(".//div[@class='section']//div[@class='list-item hide-overflow'][" + (i + 1) + "]"));
					readMoreLink = ValidationFactory.getElementIfPresent(
							singleFrame.findElement(By.xpath(".//div[@class='secondary-action-container']")));
				} catch (Exception e) {
					readMoreLink = false;
				}
				if (!readMoreLink) {
					temp = BaseClass.wbDriver.findElement(By.xpath(
							".//div[@class='section']//div[@class='list-item hide-overflow'][" + (i + 1) + "]/div[1]"))
							.getText().toString().trim();
					description = temp.substring(temp.indexOf("\n") + 1, temp.length());
				} else {
					WaitFactory.waitForElement(
							framePath.get(i).findElement(By.xpath(".//div[@class='secondary-action-container']")));
					WaitFactory.WaitForElementToVisible(
							framePath.get(i).findElement(By.xpath(".//div[@class='secondary-action-container']")));
					((JavascriptExecutor) wbDriver).executeScript("arguments[0].click();",
							framePath.get(i).findElement(By.xpath(".//div[@class='secondary-action-container']")));
					WebElement textAfterReadMoreLinkClick = WaitFactory.WaitForElementToVisible(
							framePath.get(i).findElement(By.xpath(".//div[@class='list-item-body is-expanded']")));
					String temp1 = textAfterReadMoreLinkClick.getText().toString().trim();
					description = temp1.substring(temp1.indexOf("\n") + 1, temp1.length());
					((JavascriptExecutor) wbDriver).executeScript("arguments[0].click();",
							framePath.get(i).findElement(By.xpath(".//div[@class='secondary-action-container']")));
					// framePath.get(i).findElement(By.xpath(".//div[@class='secondary-action-container']")).click();
				}
				description = StringUtils.normalizeSpace(description);
				descriptionWCM = StringUtils.normalizeSpace(descriptionWCM);

				if (titleOfAnnouncement.equals(Title) && description.equals(descriptionWCM)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	/**
	 * @author shrey.choudhary This method is verifying announcement header format
	 *         is correct or not.
	 * @return Test output that header format is correct or not.
	 * @throws Throwable
	 */
	public static void verifyAnnouncementHeaderContentWithFormat(String strTCID) throws Throwable {

		String strFlag = "Fail";
		String strResult = "Announcement Portlet is not available";

		/*****************
		 * check Announcement header title content format
		 ********************************************************/
		try {
			if (ValidationFactory.isElementPresent(wbelAnnouncementFramePath)) {
				LogFactory.info("Announcement portlet is present");

				if (GenericFactory.headerTitleFormat(AnnouncementTitleContents)) {
					strFlag = "Pass";
					strResult = "All Announcement header titles are displayed in the format --> Published Date ("
							+ BaseClass.dateformat + ") : Announcement Title ";
				} else {
					strFlag = "Fail";
					strResult = "Invalid Hearder or Date format found in the Announcement Portlet ";
				}

				ReportFactory.reporterOutput(strTCID, "Verify Announcement header titles on the Announcement Portlet ",
						"Data format is : " + BaseClass.dateformat,
						"Announcement header title should be in the format --> Published Date (" + BaseClass.dateformat
								+ ") : Announcement Title ",
						strResult, strFlag);

			} else {
				ReportFactory.reporterOutput(strTCID, "Verify Announcement header titles on the Announcement Portlet ",
						"Data format is : " + BaseClass.dateformat,
						"Announcement header title should be in the format --> Published Date (" + BaseClass.dateformat
								+ ") : Announcement Title",
						strResult, strFlag);
			}

		} catch (Exception e) {
			LogFactory.error("e");
			String er = e.getMessage().toString().trim();
			ReportFactory.reporterOutput(strTCID, "Verify Announcement header titles on the Announcement Portlet ",
					"NA", "NA", er, strFlag);
		}
	}

	/**
	 * @author shrey.choudhary This method is verifying that any announcement which
	 *         is having embedded links should not be broken.
	 * @return Test output that Embedded links are broken or not, if broken test case
	 *         would be failed.
	 * @throws Throwable
	 */
	public static void verifyEmbeddedlinks(String strTCID) throws Throwable {
		int respCode = 200;
		List<String> emptyLinks = new ArrayList<String>();
		Map<String, Integer> brokenLinks = new HashMap<String, Integer>();
		List<String> CorrectLinks = new ArrayList<String>();
		List<WebElement> readMoreLinks = BaseClass.wbDriver.findElements(By.xpath(
				".//div[@class='section']//div[@class='wide-list hide-overflow']//div[@class='secondary-action-container']"));
		for (int i = 0; i < readMoreLinks.size(); i++) {
			readMoreLinks.get(i).click();
			Thread.sleep(2000);
		}
		List<WebElement> lstWebElement = GenericFactory.getLinksFromFrame(wbelAnnouncementFramePath);
		try {

			if (lstWebElement.size() > 0) {
				for (int i = 1; i < lstWebElement.size(); i++) {
					String url = BaseClass.wbDriver.findElement(By.linkText(lstWebElement.get(i).getText()))
							.getAttribute("href");
					String urlName = BaseClass.wbDriver.findElement(By.linkText(lstWebElement.get(i).getText()))
							.getText();
					if (url == null || url.isEmpty()) {
						emptyLinks.add(urlName);
					} else {
						HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
						huc.setRequestMethod("HEAD");
						huc.connect();
						respCode = huc.getResponseCode();
						if (respCode >= 400) {
							brokenLinks.put(urlName + "Status response ", respCode);
						} else
							CorrectLinks.add(urlName);
					}
				}
				System.out.println("Broken Links are :" + brokenLinks + ", Empty/null links are :" + emptyLinks
						+ ", Correct links are :" + CorrectLinks);
				if (emptyLinks.isEmpty() && brokenLinks.isEmpty()) {

					ReportFactory.reporterOutput(strTCID, "Verify Embedded links on Announcement Portlet", "NA",
							"Embedded links should not be broken or empty",
							"Embedded links are not broken and working fine as expected :" + CorrectLinks, "Pass");
				} else {

					ReportFactory.reporterOutput(strTCID, "Verify Embedded Links on Announcement Portlet", "NA",
							"Embedded links should not be broken or empty",
							"<B>Working Embedded links are :</B>" + CorrectLinks + "</br><B>Broken Links are :</B>"
									+ brokenLinks + "</br><B>Empty links are :</B>" + emptyLinks,
							"Fail");
				}
			} else {
				System.out.println("No Embedded links are present");
				ReportFactory.reporterOutput(strTCID, "Verify Embedded Links on Announcement Portlets.", "NA",
						"Embedded links should not be broken or empty", "No links are present", "Pass");
			}
		} catch (Exception e) {
			LogFactory.error("e");
			String er = e.getMessage().toString().trim();
			ReportFactory.reporterOutput(strTCID, "Verify Embedded Links on Announcement Portlets.", "NA", "NA", er,
					"Fail");
		}
	}

	/**
	 * @author shrey.choudhary This method is verifying that all announcement should
	 *         be order as per their published date.
	 * @return Test output that all announcements are sorted as per their published
	 *         date, if not test case is getting failed.
	 * @throws Throwable
	 */
	public static void verifyAnnouncemntsShowingInDescendingOrderOnDateTime(String strTCID,
			String strDepartmentNameFromExcel) throws Throwable {

		String strFlag = "Fail";
		String strResult = "";
		String strResultPresentOnDepartment = "";
		String strResultNotPresentOnDepartment = "";

		List<String> liMatchingDepartments = new ArrayList<String>();
		List<String> liNonMatchingDepartments = new ArrayList<String>();
		List<String> liDepartmentNameFromExcel = GenericFactory.splitString(strDepartmentNameFromExcel, ",");

		try {

			if (!strDepartmentNameFromExcel.equalsIgnoreCase("NA")) {

				if (liDepartmentNameFromExcel.size() > 0) {

					for (int j = 0; j < liDepartmentNameFromExcel.size(); j++) {
						if (GenericFactory.clickOnDepartmentByName(liDepartmentNameFromExcel.get(j))) {
							/***********
							 * Get list of Announcement and verify if sorted according to date
							 ***************/
							if (ValidationFactory.isElementPresent(wbelAnnouncementFramePath)) {
								List<WebElement> numberOfAnnouncement = AnnouncementTitleContents;
								LogFactory.info("Number of Announcement are :" + numberOfAnnouncement.size());

								List<Date> AnnouncementHeaderDates = GenericFactory
										.getListOfDatesByFrame(wbelAnnouncementFramePath, "Announcement");

								if (GenericFactory.verifyDateSortedOrder(AnnouncementHeaderDates) == true) {
									liMatchingDepartments.add(liDepartmentNameFromExcel.get(j));
									strResultPresentOnDepartment = strResultPresentOnDepartment
											.concat(liDepartmentNameFromExcel.get(j));
								} else {
									liNonMatchingDepartments.add(liDepartmentNameFromExcel.get(j));
									strResultNotPresentOnDepartment = strResultNotPresentOnDepartment
											.concat(liDepartmentNameFromExcel.get(j));
								}
							} else {
								LogFactory.info("Announcement portlet is not present on page:");
								strResultNotPresentOnDepartment = strResultNotPresentOnDepartment
										.concat(liDepartmentNameFromExcel.get(j));
								liNonMatchingDepartments.add(liDepartmentNameFromExcel.get(j));
							}
						}
					}

					if (liMatchingDepartments.size() > 0) {
						strFlag = "Pass";
						strResult = "Announcement title dates are displayed in descending order on active departments :"
								+ liMatchingDepartments;
					} else if (liMatchingDepartments.size() == 0 || liNonMatchingDepartments.size() > 0) {
						strFlag = "Fail";
						strResult = "Announcement title dates are NOT displayed in descending order on departments  :"
								+ liNonMatchingDepartments;
					}
					ReportFactory.reporterOutput(strTCID,
							"Verify Announcement displayed in descending order with newest one's on top on department ",
							strDepartmentNameFromExcel,
							"Announcements should be displayed in descending order with newest one's on top on active department pages",
							strResult, strFlag);
				}
			} else {

				GenericFactory.navigateToHomePage();
				LogFactory.info(
						"Verifying Announcements are  displaying in descending order on date and time in homepage");
				/***********
				 * * Get list of Announcements and verify if sorted according to date
				 ***************/
				List<Date> AnnouncementHeaderDates = GenericFactory.getListOfDatesByFrame(wbelAnnouncementFramePath,
						"Announcement");

				if (GenericFactory.verifyDateSortedOrder(AnnouncementHeaderDates) == true) {

					strFlag = "Pass";
					strResult = "Announcement title dates are in descending order with newest one's on top by date on homepage";
				} else {
					LogFactory.info("Announcement title dates are not in sorted order on homepage");
					strResult = "Announcement title dates are not in sorted order on homepage";
				}
				ReportFactory.reporterOutput(strTCID,
						"Verify Announcements are displayed in descending order with newest one's on top of homepage",
						"NA",
						"Announcements should be displayed in descending order with newest one's on top of homepage",
						strResult, strFlag);
			}
		}

		catch (Throwable e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify Announcements are displayed in descending order with newest one's on top of homepage", "NA",
					"NA", e.getMessage().toString(), "Fail");
		}

	}

	/**
	 * @author shrey.choudhary This method is verifying the title & description of
	 *         Announcement portlet should not visible to user when a particular
	 *         department is unchecked given in test data sheet.
	 * @return Test output that title & description is present or not if related
	 *         department is unchecked.
	 * @throws Throwable
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static void verifyAnnouncementTitleOnDepartmentUncheck(String wcmDeptName, String titleName, String strTCID,
			String wcmCopyToDepartment, String description) throws Throwable {

		List<String> listOfDepartments = new ArrayList<>();
		if (!wcmDeptName.equalsIgnoreCase("General")) {

			listOfDepartments.add(wcmDeptName);

			if (GenericFactory.clickOnDepartmentByName(wcmDeptName)) {
				if (!wcmCopyToDepartment.equals("NA")) {
					listOfDepartments.addAll(GenericFactory.splitString(wcmCopyToDepartment, ","));
				}
				String strResult = "Fail";
				String commaSeprateListOfDepartments = String.join(", ", listOfDepartments);
				String actualResult = "Announcement title is still displayed " + titleName + " after unchecking the "
						+ commaSeprateListOfDepartments + " department";
				GenericFactory.checkDepartmentMyPreference();
				if (verifyAnnouncementHeader(titleName, description)) {

					GenericFactory.uncheckDepartmentMyPreference(listOfDepartments);

					List<String> listActualData = new ArrayList<String>();
					for (int i = 0; i < PortalLeftNavigation_POF.ListAllActiveLinks.size(); i++) {
						String temp = PortalLeftNavigation_POF.ListAllActiveLinks.get(i).getText();
						listActualData.add(temp);
					}
					if (!listOfDepartments.contains(listActualData)) {

						if (verifyAnnouncementHeader(titleName, description)) {
							strResult = "Fail";
							actualResult = "<B>Announcement title : </B>" + titleName
									+ " is present even after the : <B>" + commaSeprateListOfDepartments
									+ "</B>  departments are filtered ";
							System.out.println("Fail");
						} else {
							strResult = "Pass";
							actualResult = "<B>Announcement title : </B>" + titleName
									+ " is not present after the : <B>" + commaSeprateListOfDepartments
									+ "  </B>departments are filtered ";
							System.out.println("Pass");
						}

					} else {
						actualResult = "Department <B>" + commaSeprateListOfDepartments
								+ " </B>are not disabled after unchecking them due to some error.";
						strResult = "Fail";
					}
				} else {
					actualResult = "Announcement is supposed to be present on <B>" + wcmDeptName
							+ "</B> department but it's not present";
					strResult = "Fail";
				}
				ReportFactory.reporterOutput(strTCID,
						"To verify announcement on the announcement portlet when the respective department is filter out",
						"<B>Title name is :</B></br>" + titleName + "</br><B>WCM Department name is :</B></br>"
								+ wcmDeptName + "</br><B>WCM Copy to Department name is :</B></br>"
								+ wcmCopyToDepartment,
						"Announcements should not be displayed after filter out relevant department", actualResult,
						strResult);
			} else {
				ReportFactory.reporterOutput(strTCID,
						"To verify announcement on the announcement portlet when the respective department is filter out",
						"<B>Title name is :</B></br>" + titleName + "</br><B>WCM Department name is :</B></br>"
								+ wcmDeptName + "</br><B>WCM Copy to Department name is :</B></br>"
								+ wcmCopyToDepartment,
						"Announcements should not be displayed after filter out relevant department",
						"Department :<B>" + wcmDeptName + "</B> is inactive so unable to verify this scenario", "Pass");
			}

		} else {
			ReportFactory.reporterOutput(strTCID,
					"To verify announcement on the announcement portlet when the respective department is filter out",
					"<B>Title name is :</B></br>" + titleName + "</br><B>WCM Department name is :</B></br>"
							+ wcmDeptName + "</br><B>WCM Copy to Department name is :</B></br>" + wcmCopyToDepartment,
					"Announcements should not be displayed after filter out relevant department",
					"Unable to verify this scenario, because department name is 'General'.", "Pass");
		}

	}

	public static void verifyAnnouncementHeaderCount(String strTCID) throws Throwable {
		String flagResult = "Pass";
		String actualResult = "Announcement header is not present.";
		try {

			for (int i = 0; i < HeaderList.size(); i++) {
				if (HeaderList.get(i).getText().contains("(")) {
					String temp = GenericFactory.splitString(HeaderList.get(i).getText(), "(").get(1);
					String temp1 = GenericFactory.splitString(temp, ")").get(0);
					int headerCountFromHeader = Integer.parseInt(temp1);
					int actualHeaderCountFromTitleList = AnnouncementTableTitle.size();
					if (actualHeaderCountFromTitleList == headerCountFromHeader) {
						flagResult = "Pass";
						actualResult = "Announcement header count is :<B>" + headerCountFromHeader + "</B>"
								+ "</br>Actual count of Announcement title is :<B>" + actualHeaderCountFromTitleList
								+ "</B>";
					} else {
						flagResult = "Fail";
						actualResult = "Announcement header count is :<B>" + headerCountFromHeader + "</B></br>"
								+ "</br>Actual count of Announcement present is :<B>" + actualHeaderCountFromTitleList
								+ "</B>";

					}
				}
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify Announcement header count is same as announouncements available", "NA",
					"Announcement header count should be same as announcement present", actualResult, flagResult);

		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify Announcement header count is same as announouncements available", "NA", "NA",
					"Unable to verify this scenario due to below error</br>" + e.getMessage(), "Fail");

		}

	}

	/**
	 * @author shrey.choudhary This method is verifying the title & description of
	 *         Announcement portlet should not visible to user when a particular
	 *         product is unchecked given in test data sheet.
	 * @return Test output that title & description is present or not if related
	 *         product is unchecked.
	 * @throws Throwable
	 */
	public static void verifyAnnouncementTitleOnProductUncheck(String UserProductName, String WcmProducts,
			String titleName, String strTCID, String description) throws Throwable {
		String strAnnouncementFlag = "Fail";
		String strResult = "<B>Announcement title :</B>" + titleName + "is still present after the corresponding :<B>"
				+ WcmProducts + "</B> product types are filtered";
		List<String> listUserProducts = GenericFactory.splitString(UserProductName, ",");

		if (!GenericFactory.selectProductsFromProductSegmentList(WcmProducts)) {
			if (!verifyAnnouncementHeader(titleName, description)) {
				strAnnouncementFlag = "Pass";
				strResult = "<B>Announcement title :</B>" + titleName + " is not present after the corresponding :<B>"
						+ String.join(", ", WcmProducts) + "</B> product types are filtered out.";
				ReportFactory.reporterOutput(strTCID,
						"Verify Announcement title should not be visible to the user, if user filter out related products tagged with WCM content",
						"<B>User products : </B>" + String.join(", ", listUserProducts) + ""
								+ "</br><B>WCM products :</B>" + String.join(", ", WcmProducts)
								+ "</br><B>Announcement Title :</B>" + titleName,
						"Announcements should not be displayed after unchecking " + String.join(", ", WcmProducts)
								+ " product types.",
						strResult, strAnnouncementFlag);
			} else {
				LogFactory.info("Title still exists even after applying the Product type filter" + titleName);
				strAnnouncementFlag = "Fail";
				strResult = "<B>Announcement title :</B>" + titleName
						+ " is still visible to the user after product type filtered out.";
				ReportFactory.reporterOutput(strTCID,
						"Verify Announcement title should not be visible to the user, if user filter out related products tagged with WCM content",
						"<B>User products : </B>" + String.join(", ", listUserProducts) + "</br><B>WCM products : </B>"
								+ String.join(", ", WcmProducts) + "</br><B>Announcement Title :</B>" + titleName,
						"Announcements should not be displayed after unchecking :<B></br>"
								+ String.join(", ", WcmProducts) + "</B> product types.",
						strResult, strAnnouncementFlag);
			}

		} else {
			strResult = "Unable to apply Product Type filter reason could be invalid products or announcemnte is mapped with more products than the user products ";
			strAnnouncementFlag = "Pass";

			ReportFactory.reporterOutput(strTCID,
					"Verify Announcement title should not be visible to the user, if user filter out related products tagged with WCM content",
					"<B>User products : </B>" + String.join(", ", listUserProducts) + "</br><B>WCM products : </B>"
							+ String.join(", ", WcmProducts) + "</br><B>Announcement Title :</B>" + titleName,
					"Announcements should not be displayed after unchecking :<B></br>" + String.join(", ", WcmProducts)
							+ "</B> product types.",
					strResult, strAnnouncementFlag);
			ProductSegment_POF.wbelProductSegmentIcon.click();
		}

	}

	/**
	 * @author shrey.choudhary This method is verifying that announcement having
	 *         read more & collapse link is working properly.
	 * @return Test output that announcement having read more & collapse link is
	 *         working properly, if not test case is getting failed.
	 * @throws Throwable
	 */
	public static boolean checkReadMoreAndCollapseLinkForAnnouncement(String strTCID) throws Throwable {
		List<String> translatedCollapseName = GenericFactory.getTranslation("Collapse");
		List<String> translatedReadMoreName = GenericFactory.getTranslation("Read More");
		String strFlag = "Fail";
		String strResult = "<B>" + translatedReadMoreName + "</B> link is not present";
		boolean booFlagReadmore = true;
		boolean booFlagCollapse = true;
		try {
			WaitFactory.WaitForElementToVisible(wbelAnnouncementFramePath);
			WaitFactory.waitForPageLoaded();
			Thread.sleep(2000);
			List<WebElement> readMoreLink = wbelAnnouncementFramePath
					.findElements(By.xpath(".//div[@class='secondary-action-container']"));
			int readmorelinkcount = readMoreLink.size();

			if (readmorelinkcount > 0) {
				WaitFactory.WaitForElementToVisible(readMoreLink.get(0));
				Thread.sleep(2000);
				((JavascriptExecutor) wbDriver).executeScript("arguments[0].click();",readMoreLink.get(0));
				Thread.sleep(2000);
		//		readMoreLink.get(0).click();
				String strCollapseText = readMoreLink.get(0).getText().trim().toString();
				booFlagReadmore = true;
				List<String> CollapseTranslate = GenericFactory.getTranslation("Collapse");
				if (strCollapseText.equalsIgnoreCase(CollapseTranslate.get(0))) {
					booFlagCollapse = true;
					WaitFactory.WaitForElementToVisible(readMoreLink.get(0));
					Thread.sleep(2000);
					((JavascriptExecutor) wbDriver).executeScript("arguments[0].click();",readMoreLink.get(0));
					Thread.sleep(2000);
			//		readMoreLink.get(0).click();
					WaitFactory.WaitForElementToVisible(readMoreLink.get(0));
					String strReadmoreText = readMoreLink.get(0).getText().trim().toString();
					List<String> ReadMoreTranslate = GenericFactory.getTranslation("Read more");
					if (strReadmoreText.equalsIgnoreCase(ReadMoreTranslate.get(0))) {
						booFlagReadmore = true;
					} else {
						booFlagReadmore = false;
					}
				} else {
					booFlagReadmore = true;
					booFlagCollapse = false;
				}
			
		} else {
			booFlagReadmore = false;
		}

			if (booFlagReadmore && booFlagCollapse) {
				strFlag = "Pass";
				strResult = "<B>" + translatedReadMoreName
						+ "</B> link is displayed for Announcement portlet and working as expected";
			} else if (booFlagReadmore && !booFlagCollapse) {
				strFlag = "Fail";
				strResult = "<B>" + translatedReadMoreName
						+ "</B> link is present for Announcement portlet but not working as expected";
			} else {
				strFlag = "Pass";
				strResult = "<B>" + translatedReadMoreName + "</B> link is not displayed for Announcement portlet";
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify <B>" + translatedReadMoreName + " & " + translatedCollapseName
							+ "</B> links are displayed on Announcement portlet",
					"NA", "If 'ReadMore & Collapse' link is present then it should work as expected", strResult,
					strFlag);

		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Verify <B>" + translatedReadMoreName + " & " + translatedCollapseName
					+ "</B> links are displayed on Announcement portlet", "NA", "NA", e.getMessage(), "Fail");
		}
		return booFlagCollapse && booFlagReadmore;
	}

	/**
	 * @author shrey.choudhary This method is verifying that announcement having
	 *         Expand & collapse link is working properly.
	 * @return Test output that announcement having Expand & collapse link is
	 *         working properly, if not test case is getting failed.
	 * @throws Throwable
	 */
	public static void verifyExpandAndCollapseLinkOnAnnuoncementPortlet(String strTCID) throws Throwable {
		List<String> translatedCollapseName = GenericFactory.getTranslation("Collapse");
		List<String> translatedExpandName = GenericFactory.getTranslation("Expand");
		String strFlag = "Pass";
		String strResult = "Announcements count are less then 6, hence not verifying this scenario.";

		boolean booFlagExpand = false;
		boolean booFlagCollapse = true;

		try {
			int listOfWebElements = AnnouncementTableTitle.size();
			if (listOfWebElements > 6) {
				if (ValidationFactory.isElementPresent(linkAnnouncementOfExpand)) {
					linkAnnouncementOfExpand.click();

					if (linkAnnouncementOfExpand.getText().equalsIgnoreCase(translatedCollapseName.get(0))) {
						LogFactory.info("Collapse link is present");
						booFlagCollapse = true;
						Thread.sleep(2000);
						linkAnnouncementOfExpand.click();
						Thread.sleep(2000);

						if (linkAnnouncementOfExpand.getText().equalsIgnoreCase(translatedExpandName.get(0))) {
							booFlagExpand = true;
						}
					} else {
						LogFactory.info("Collapse link is not present");
						booFlagCollapse = false;
					}
				}
			} else {
				LogFactory.info("Expand link is not displayed");

			}
			if (booFlagExpand && booFlagCollapse) {
				LogFactory.info("Expand link  and collapse link are  present");
				strFlag = "Pass";
				strResult = "<B>" + translatedExpandName + " and " + translatedCollapseName
						+ "</B>link is displayed for announcement portlet and working as expected";
			} else if (booFlagExpand && !booFlagCollapse) {

				LogFactory.info("Expand link is displayed but collapse link is not present");
				strFlag = "Fail";
				strResult = "<B>" + translatedExpandName
						+ "</B> link is present for announcement portlet but not working as expected";
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify <B>" + translatedExpandName
							+ "</B> link displayed for announcement portlet on my dealerpath",
					"NA", "If <B>" + translatedExpandName + "</B> link is present it should work as expected",
					strResult, strFlag);
		}

		catch (Exception e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify <B>" + translatedExpandName
							+ "</B> link displayed for announcement portlet on my dealerpath",
					"NA", "If '<B>" + translatedExpandName + "</B>' link is present it should work as expected",
					"Unable to verify this scenario due to this error :" + e.getMessage(), "Fail");

		}
	}

	/**
	 * @author shrey.choudhary This method is verifying the title & description of
	 *         Announcement portlet should not visible to user when a particular
	 *         country is unchecked given in test data sheet.
	 * @return Test output that title & description is present or not if related
	 *         country is unchecked.
	 * @throws Throwable
	 */
	public static void verifyCountryUncheckOnHomepage(String wcmCountry, String announcementTitle, String strTCID,
			String description) throws Throwable {
		try {
			List<String> listOfWcmCountry = GenericFactory.splitString(wcmCountry, ",");
			String strFlag = "Pass";
			String strResult = "This scenario is not valid for this user because country icon is not visible or WCM content mapped with region not specific to any country.";

			GenericFactory.navigateToHomePage();
			if (ValidationFactory.isElementPresent(countryGroupingIcon)) {
				List<String> listOfParentCountry = GenericFactory.getParentCountryName(listOfWcmCountry);
				if (listOfParentCountry != null) {
					if (verifyAnnouncementHeader(announcementTitle, description)) {
						if (GenericFactory.uncheckCountryGrouping(listOfParentCountry)) {
							if (!verifyAnnouncementHeader(announcementTitle, description)) {
								strFlag = "Pass";
								strResult = "After unchecking the country " + listOfWcmCountry.toString()
										+ " <B>Announcement title :</B>" + announcementTitle + " is not visible";
							} else {
								strFlag = "Fail";
								strResult = "After unchecking the country " + listOfWcmCountry.toString()
										+ " <B>Announcement title :</B>" + announcementTitle + " is still visible";
							}
						} else {
							strFlag = "Fail";
							strResult = "Unable to click on country list given in test data sheet.";
						}
					} else {
						strFlag = "Fail";
						strResult = "Announcement is supposed to be present on homepage but it's not present";
					}
				}
			}
			ReportFactory.reporterOutput(strTCID, "Verify country uncheck on Announcement portlet", "NA",
					" If user uncheck relevent country for <B>Announcement Title :</B>" + announcementTitle, strResult,
					strFlag);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}