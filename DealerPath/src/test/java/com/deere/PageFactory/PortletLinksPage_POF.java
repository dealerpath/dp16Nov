/* 
 * Project    : DealerPath
* Script     : PortletLinksPage_POF
* Author     : Archana Gaikwad
* Date       : June.27.2018

*/

package com.deere.PageFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.asserts.SoftAssert;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.DateFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ReportOutput;
import com.deere.Helpers.ValidationFactory;

public class PortletLinksPage_POF {
	public static WebDriver PortletDriver;
	static SoftAssert softAssert = new SoftAssert();

	@SuppressWarnings("static-access")
	public PortletLinksPage_POF(WebDriver driver) {
		this.PortletDriver = driver;

	}

	@FindBy(how = How.XPATH, using = ".//*[@id='links-filter']")
	static WebElement filterbox;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'links-no-results')]")
	public static WebElement noResultfound;

	@FindBy(how = How.XPATH, using = ".//*[@id='js-segments-popover']/div[2]/div[3]/button")
	public static WebElement applyFilter;

	@FindBy(how = How.XPATH, using = ".//*[@id='country-grouping-save']")
	public static WebElement applyFilterCountry;

	@FindBy(how = How.XPATH, using = "//div[@class='user-info' ]")
	static WebElement wbelUserInfo;

	@FindBy(how = How.XPATH, using = "//h1[@class='app-title']")
	static WebElement wbelTitleOfHomePage;

	@FindBy(how = How.ID, using = "leftNav")
	static WebElement wbelLeftWindow;

	@FindBy(how = How.XPATH, using = ".//*[@class='wpthemeFooter']")
	public static WebElement wbelHomePageFooterFramePath;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[2]/section/div/div[2]/div")
	public static WebElement richTextUI;

	@FindBy(how = How.XPATH, using = ".//*[@id='productSegmentsError']")
	public static WebElement productError;

	@FindBy(how = How.XPATH, using = ".//*[@id='main-header']/div[1]/div/h1")
	public static WebElement homeLogo;

	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='active']")
	public static List<WebElement> ListAllActiveLinks;

	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='inactive']")
	public static List<WebElement> ListAllInActiveLinks;

	@FindBy(how = How.XPATH, using = ".//*[@id='flyOut']/li/a[@class='active']")
	public static List<WebElement> ListAllActiveFlyOutLinks;

	@FindBy(how = How.XPATH, using = ".//*[@id='flyOut']/li/a[@class='inactive']")
	public static List<WebElement> ListAllInActiveFlyOutLinks;

	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @checked='true']")
	static List<WebElement> checkedValues;

	@FindBy(how = How.XPATH, using = "//div[@class='group-value checkbox-value']/div[@class='value']")
	public static List<WebElement> allCheck;

	@FindBy(how = How.XPATH, using = "//div[@class='section-header']//h3")
	static List<WebElement> wbelSectionHeader;

	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[4]/section/div/div[4]/div[2]")
	public static WebElement departmentLinksfilterbox;

	@FindBy(how = How.XPATH, using = ".//*[@id='favorites-filter']")
	public static WebElement homePageFilter;

	static String strPInputValue = null;

	/**
	 * @author Archana Gaikwad Verify Fly-out navigation on Department page.
	 * @param param1
	 *            flyoutNavigationDept(strTCID,strDepartmentName);
	 * @return Flyout Navigation @ exception try and Catch @ throws Throwable
	 **/
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public static void flyoutNavigationDept(String strTCID, String strDepartmentValue) throws Throwable {

		List<String> strdepartmentActiveLeftNav = new ArrayList();
		List<String> strdepartmentActiveLeftNavflyOut = new ArrayList();
		String status = "Fail";
		String actresult = "Fly-out navigation on Department page is not working as expected.";
		List<WebElement> listOfActiveLinksLeftNav = new ArrayList<WebElement>();
		try {

			for (int n = 0; n < ListAllActiveLinks.size(); n++) {
				listOfActiveLinksLeftNav.add(ListAllActiveLinks.get(n));
				strdepartmentActiveLeftNav.add(ListAllActiveLinks.get(n).getText());
			}

			for (int i = 0; i < listOfActiveLinksLeftNav.size(); i++) {
				listOfActiveLinksLeftNav.get(i).click();
				break;
			}
			Thread.sleep(1000);
			WebElement element = PortletDriver.findElement(By.xpath(".//*[@id='left_nav_0']"));
			Actions action = new Actions(PortletDriver);
			action.moveToElement(element).build().perform();
			Thread.sleep(1000);
			List<WebElement> listOfActiveLinksFlyOut = new ArrayList<WebElement>();
			for (int n = 0; n < ListAllActiveFlyOutLinks.size(); n++) {

				listOfActiveLinksFlyOut.add(ListAllActiveFlyOutLinks.get(n));
				strdepartmentActiveLeftNavflyOut.add(ListAllActiveFlyOutLinks.get(n).getText());
			}

			strdepartmentActiveLeftNavflyOut.remove(element.getText());

			boolean flyoutFlag = strdepartmentActiveLeftNav.containsAll(strdepartmentActiveLeftNavflyOut);

			if (flyoutFlag == true) {
				status = "Pass";
				actresult = "Fly-out navigation on Department page is working as expected once we uncheck department.";
				ReportFactory.reporterOutput(strTCID, "Verify Fly-out navigation on Department page.",
						strDepartmentValue,
						"Fly-out navigation should be displayed just by hovering over the My DealerPath of left navigation listing all the accessible departments.(non-preferred departments are greyed out)",
						strdepartmentActiveLeftNav + "Matching with:" + strdepartmentActiveLeftNavflyOut, status);
			}

		} catch (Throwable e) {

			ReportFactory.reporterOutput(strTCID, "Verify Fly-out navigation on Department page.", strDepartmentValue,
					"Fly-out navigation should be displayed just by hovering over the My DealerPath of left navigation listing all the accessible departments.(non-preferred departments are greyed out)",
					e.getMessage().toString(), status);
		}

	}

	/**
	 * @param title
	 * @param TCID
	 * @throws Throwable
	 */

	/**
	 * @author shrey.choudhary
	 * @createdAt 22-05-2018
	 * @param testData
	 * @param TCID
	 * @throws Throwable
	 * @modifiedAt 22-05-2018
	 */
	public static void getDealerPrincipalRole(String strTestData, String strTCID) throws Throwable {

		String strFlag = "Fail";
		String strResult = "Dealer is not having dealer principal role";
		try {
			if (strTestData.equals("NA")) {
				ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", "NA",
						"Verify Dealer Principal role.", strResult, strFlag);
			} else {

				List<String> frameList = new ArrayList<String>();
				for (int i = 0; i < PortalLeftNavigation_POF.ListAllActiveLinks.size(); i++) {
					String temp = PortalLeftNavigation_POF.ListAllActiveLinks.get(i).getText();
					frameList.add(temp);
				}
				if (frameList.contains(strTestData)) {
					strFlag = "Pass";
					strResult = "Dealer is having dealer principal role";
				}
				ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", "NA",
						"Verify Dealer Principal role.", strResult, strFlag);
			}

		} catch (Throwable e) {

			ReportFactory.reporterOutput(strTCID, "Verify Dealer Principal role.", "NA",
					"Verify Dealer Principal role.", e.getMessage().toString(), strFlag);
		}
	}

	/**
	 * @author Archana Gaikwad
	 * @createdAt 27-06-2018
	 * @param testData
	 * @param TCID
	 * @throws Throwable
	 * @modifiedAt 27-05-2018
	 */
	// ********************Testcase-01*********************************

	/**
	 * @author Archana Gaikwad Verify filter functionality for links portlet with
	 *         valid data.--Enter Search criteria in search box and check correct
	 *         result is displayed.
	 * @param param1
	 *            verifyFiltervaliddata(strTCID,strDepartmentName,strSubDepartmentName,strTitle)
	 * @return Valid Search Criteria @ exception try and Catch @ throws Throwable
	 **/

	@SuppressWarnings({ "unused", "unchecked" })
	public static void verifyFiltervaliddata(String strTCID, String strDepartmentName, String strSubDepartmentName,
			String strSearchCriteria) throws Throwable {
		String status = "Fail";
		String result = null;
		try {

			LogFactory.info("Verify Department Name");
			List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
			strDepartmentName = translatedText.get(0);
			WebElement department = GenericFactory.getDeptname(strDepartmentName);

			if (department != null) {

				department.click();

				// ************************************************

				int count = 0;
				boolean folderFlag = false;
				boolean flagForLinksunderFolder = false;
				String temp = null;
				// ************************************

				// System.out.println(searchCriteriaFlag);
				boolean searchCriteriaFlag = GenericFactory
						.compareLinksandFolderName(strSearchCriteria.toUpperCase().trim());

				if (strSubDepartmentName.toUpperCase().toString()
						.equalsIgnoreCase(strSearchCriteria.trim().toString().toUpperCase())) {

					List<WebElement> listHeaderName = BaseClass.wbDriver
							.findElements(By.xpath(".//*[@class='link-group-header']"));
					for (int k = 0; k < listHeaderName.size(); k++) {

						String headerNameData = listHeaderName.get(k).getText().toString().trim();

						if (headerNameData.equalsIgnoreCase(strSearchCriteria)) {
							result = "Header Name/SubDepartment Name is matching with search Criteria."
									+ strSubDepartmentName;
							status = "Pass";
							break;
						}
					}
					ReportFactory.reporterOutput(strTCID, "Verify filter functionality for links portlet.",
							strSearchCriteria, "Search criteria should be available in the list.", result, status);

				}

				else if (searchCriteriaFlag == false) {
					filterbox.clear();
					filterbox.sendKeys(strSearchCriteria.trim().toString());
					filterbox.sendKeys(Keys.ENTER);

					boolean folderFlagInvi = false;
					List<WebElement> finallinkUnderFolder = PortletDriver
							.findElements(By.xpath(".//*[@id='links-target']/div/div"));
					String getFoldernameInvisi = null;
					for (int p = 0; p < finallinkUnderFolder.size(); p++) {

						String temp1 = finallinkUnderFolder.get(p).getText().trim();
						String[] lines = temp1.split("\n");

						for (int i = 0; i < lines.length; i++) {

							String links = lines[i];

							List<WebElement> listHeaderName = BaseClass.wbDriver
									.findElements(By.xpath(".//*[@class='link-group-header']"));
							for (int k = 0; k < listHeaderName.size(); k++) {
								try {
									temp = listHeaderName.get(i).getText().toString().trim();
								} catch (Exception e) {

								}
								if (temp.equalsIgnoreCase(strSubDepartmentName)) {
									WebElement framePath = BaseClass.wbDriver
											.findElement(By.xpath(".//*[@id='links-target']/div[" + (k + 1) + "]"));
									List<WebElement> listFolderName = framePath
											.findElements(By.xpath(".//*[@class='link-item']"));
									List<WebElement> folder = framePath
											.findElements(By.xpath(".//*[@class='link-item']/span"));
									for (int j = 0; j < listFolderName.size(); j++) {
										if (listFolderName.get(j).getText().equalsIgnoreCase(links)) {
											folder.get(j).click();
											folderFlagInvi = true;
											getFoldernameInvisi = links;
											break;

										}

									}
									if (folderFlagInvi) {
										break;
									}
								}

							}

						}
						if (folderFlagInvi) {
							break;
						}
					}

					ArrayList<String> LinksSearchedInvisibleUnderFolder = GenericFactory.listOfLinksGeneric();

					int afterSearchC = LinksSearchedInvisibleUnderFolder.size();

					filterbox.clear();
					filterbox.sendKeys(Keys.ENTER);

					ArrayList<String> LinksNSAfterInvisibleUnderFolder = GenericFactory.listOfLinksGeneric();

					LinksSearchedInvisibleUnderFolder.retainAll(LinksNSAfterInvisibleUnderFolder);

					int afterClearText = LinksSearchedInvisibleUnderFolder.size();

					List<String> translatedTextn = GenericFactory.getTranslation("No results found");

					if (getFoldernameInvisi != null
							&& LinksSearchedInvisibleUnderFolder.toString().trim().toUpperCase()
									.contains(strSearchCriteria.toUpperCase().trim())
							&& afterSearchC == afterClearText) {

						result = "Search criteria is available for the link/links in the list and Size of Links under Folder is :"
								+ afterClearText;
						status = "Pass";

					} else {

						result = translatedTextn.get(0);
						result = "No result Found";
						status = "Pass";

					}
					ReportFactory.reporterOutput(strTCID, "Verify filter functionality for links portlet.",
							strSearchCriteria,
							"Search criteria should be available in the list for searched Criteria and Folder with Links are :"
									+ afterSearchC,
							result, status);

					GenericFactory.toClickOnFolder(strSearchCriteria, strSubDepartmentName);
				} else {
					int countForAll = 0;
					int headerPresent = 0;
					ArrayList<String> linksAllPresent = new ArrayList<>();
					ArrayList<String> SAnchorTagsLinksBeforesearch = GenericFactory.listOfLinksGeneric();

					String getFoldername = GenericFactory.listLinksWithFolderName(strSearchCriteria,
							strSubDepartmentName);
					System.out.println(getFoldername);

					// Code change by Archana
					if (SAnchorTagsLinksBeforesearch.contains(getFoldername)) {

						getFoldername = null;
					} else {
						getFoldername = getFoldername;
					}

					if (getFoldername == null) {
						filterbox.clear();
						filterbox.sendKeys(strSearchCriteria.trim().toString());
						filterbox.sendKeys(Keys.ENTER);
						// GenericFactory.clickOnFolderFromAllSection();
						GenericFactory.toClickOnFolder(strSearchCriteria, strSubDepartmentName);
						ArrayList<String> headerNameSize = new ArrayList<>();
						List<WebElement> actualHeaderName = PortletDriver
								.findElements(By.xpath(".//*[@id='links-target']/div"));

						int countofLinksforHeader = GenericFactory.cntlistOfLinksheader(strSearchCriteria);

						boolean reporterOutputPass = false;
						for (int j = 0; j < actualHeaderName.size(); j++) {
							String tempValue = actualHeaderName.get(j).getText().trim();
							WebElement secondLevelLink = actualHeaderName.get(j);
							String[] lines = tempValue.split("\n");
							String SubDepartment = lines[0];
							if (SubDepartment != null) {
								headerPresent++;

							}

							headerNameSize.add(actualHeaderName.get(j).getText());
							List<WebElement> linksName = secondLevelLink.findElements(By.tagName("a"));
							int countn = 0;
							int countH = 0;

							for (WebElement linkValues : linksName) {

								String linkswithoutFolder = linkValues.getText();
								ArrayList<String> linksAllPresentData = new ArrayList<>();
								linksAllPresentData.add(linkswithoutFolder);

								if (!linkswithoutFolder.equalsIgnoreCase(null)) {
									count++;
									linksAllPresent.add(linkswithoutFolder);
									if (linkswithoutFolder.toUpperCase()
											.contains(strSearchCriteria.trim().toString().toUpperCase())) {
										System.out.println(countForAll);
										System.out.println(headerPresent);

										if ((countForAll + 1) == headerPresent) {

											result = "Search criteria is available in the list and Name of Link/links is/are: "
													+ linksAllPresentData;
											status = "Pass";
											reporterOutputPass = true;
										}
									} else {

										status = "Fail";
										result = "No Result Found.";

									}
								}
								countForAll++;
							}
							if (countn == 1 || countH == 1) {
								ReportFactory.reporterOutput(strTCID, "Verify filter functionality for links portlet.",
										strSearchCriteria, "Search criteria should be available in the list.", result,
										status);
								break;
							}

						}

						if (reporterOutputPass) {

							ReportFactory.reporterOutput(strTCID, "Verify filter functionality for links portlet.",
									strSearchCriteria,
									"Search criteria should be available in the list for all Links/Folder and Folder with Links are.",
									result, status);

						}

					}

					else {

						ArrayList<String> SAnchorTagsLinksOnclicksearch = GenericFactory.listOfLinksGeneric();

						SAnchorTagsLinksOnclicksearch.removeAll(SAnchorTagsLinksBeforesearch);

						// This is to collapse folder.

						// GenericFactory.listLinksWithFolderNameToCollapse(strSearchCriteria.toUpperCase().trim());
						GenericFactory.toClickOnFolder(strSearchCriteria, strSubDepartmentName);

						// ***********************************************

						filterbox.sendKeys(strSearchCriteria.trim().toString());
						filterbox.sendKeys(Keys.ENTER);

						ArrayList SAnchorTagsLinksBefore = GenericFactory.listOfLinksGeneric();

						List<WebElement> listHeaderName = BaseClass.wbDriver
								.findElements(By.xpath(".//*[@class='link-group-header']"));
						for (int h = 0; h < listHeaderName.size(); h++) {
							temp = listHeaderName.get(h).getText().toString().trim();
							if (temp.equalsIgnoreCase(strSubDepartmentName)) {
								WebElement framePath = BaseClass.wbDriver
										.findElement(By.xpath(".//*[@id='links-target']/div[" + (h + 1) + "]"));
								List<WebElement> listFolderName = framePath
										.findElements(By.xpath(".//*[@class='link-item']"));
								List<WebElement> folder = framePath
										.findElements(By.xpath(".//*[@class='link-item']/span"));
								for (int j = 0; j < listFolderName.size(); j++) {

									if (listFolderName.get(j).getText().toUpperCase()
											.contains(strSearchCriteria.toUpperCase())) {
										folder.get(j).click();
										folderFlag = true;

									}
									break;
								}
								if (folderFlag) {
									break;
								}

							}
						}

						@SuppressWarnings("rawtypes")
						ArrayList SAnchorTagsLinksAfter = GenericFactory.listOfLinksGeneric();
						ArrayList<String> additionalComingforFolder = new ArrayList<>();

						SAnchorTagsLinksAfter.removeAll(SAnchorTagsLinksBefore);
						additionalComingforFolder = SAnchorTagsLinksAfter;

						int multipleOnlyFolder = additionalComingforFolder.size();
						int cnt = 0;
						for (int i = 0; i < additionalComingforFolder.size(); i++) {
							if (additionalComingforFolder.get(i)
									.contains(strSearchCriteria.toUpperCase().trim().toString())) {
								cnt = i;
								if (cnt == additionalComingforFolder.size() - 1) {
									flagForLinksunderFolder = true;
								}
							}
						}

						int countofLinksforHeader = GenericFactory.cntlistOfLinksheader(strSearchCriteria);
						List<WebElement> actualHeaderName = PortletDriver
								.findElements(By.xpath(".//*[@id='links-target']/div"));
						ArrayList<String> SAnchorTagsLinks = new ArrayList<>();
						for (int j = 0; j < actualHeaderName.size(); j++) {
							String tempValue = actualHeaderName.get(j).getText().trim();
							WebElement secondLevelLink = actualHeaderName.get(j);
							String[] lines = tempValue.split("\n");
							String SubDepartment = lines[0];
							// System.out.println(SubDepartment);
							int numberLoop = 1;
							List<WebElement> linksName = secondLevelLink.findElements(By.tagName("a"));
							int countn = 0;
							for (WebElement linkValues : linksName) {

								String linksWhenFolder = linkValues.getText();
								SAnchorTagsLinks.add(linksWhenFolder);

								numberLoop++;

								// System.out.println(linksWhenFolder);
								int countH = 0;
								/*
								 * if (SubDepartment.toUpperCase().toString().contains(strSearchCriteria.trim().
								 * toString().toUpperCase())) {
								 * 
								 * countH++; result =
								 * "Header Name/SubDepartment Name is matching with search Criteria." +
								 * SubDepartment + "and Size is  :" + linksName.size(); status = "Pass"; if
								 * (countH == 1) { ReportFactory.reporterOutput("TC01_PortletLinks",
								 * "Verify filter functionality for links portlet.", strSearchCriteria,
								 * "Search criteria should be available in the list.", result, status); break; }
								 * 
								 * } else
								 */ if (!linksWhenFolder.equalsIgnoreCase(null)
										&& !(additionalComingforFolder.contains(linksWhenFolder))
										|| getFoldername.contains(strSearchCriteria.trim())) {

									if (linksWhenFolder.toUpperCase()
											.contains(strSearchCriteria.trim().toString().toUpperCase())
											&& (((folderFlag == true || flagForLinksunderFolder == true)
													&& (additionalComingforFolder
															.size() == SAnchorTagsLinksOnclicksearch.size()
															|| additionalComingforFolder
																	.size() == SAnchorTagsLinksOnclicksearch.size() + 1)
													|| ((folderFlag == false || flagForLinksunderFolder == true)
															&& (additionalComingforFolder
																	.size() == SAnchorTagsLinksOnclicksearch.size()
																	|| additionalComingforFolder
																			.size() == SAnchorTagsLinksOnclicksearch
																					.size() + 1))
													|| (folderFlag == true || flagForLinksunderFolder == false)
															&& (additionalComingforFolder
																	.size() == SAnchorTagsLinksOnclicksearch.size()
																	|| additionalComingforFolder
																			.size() == SAnchorTagsLinksOnclicksearch
																					.size() + 1)))) {

										result = "Search criteria is available in the list and Size of links under folder are "
												+ additionalComingforFolder.size();
										status = "Pass";
										if (count == (linksName.size() - additionalComingforFolder.size()) - 1) {
											ReportFactory.reporterOutput("TC01_PortletLinks",
													"Verify filter functionality for links portlet.", strSearchCriteria,
													"Search criteria should be available in the list for all Links/Folder and Folder with Links are :"
															+ additionalComingforFolder.size(),
													result, status);
											break;
										}
										count++;

									}

									else if (getFoldername.toUpperCase()
											.contains(strSearchCriteria.toUpperCase().trim())
											&& (((folderFlag == true || flagForLinksunderFolder == true)
													&& (additionalComingforFolder
															.size() == SAnchorTagsLinksOnclicksearch.size()
															|| additionalComingforFolder
																	.size() == SAnchorTagsLinksOnclicksearch.size() + 1)
													|| ((folderFlag == false || flagForLinksunderFolder == true)
															&& (additionalComingforFolder
																	.size() == SAnchorTagsLinksOnclicksearch.size()
																	|| additionalComingforFolder
																			.size() == SAnchorTagsLinksOnclicksearch
																					.size() + 1))
													|| (folderFlag == true || flagForLinksunderFolder == false)
															&& (additionalComingforFolder
																	.size() == SAnchorTagsLinksOnclicksearch.size())
													|| additionalComingforFolder
															.size() == SAnchorTagsLinksOnclicksearch.size() + 1))) {
										result = "Search criteria is available in the list and Size is :"
												+ SAnchorTagsLinksOnclicksearch.size();
										status = "Pass";
										ReportFactory.reporterOutput(strTCID,
												"Verify filter functionality for links portlet.", strSearchCriteria,
												"Search criteria should be available in the list for all Links/Folder and Folder with Links are  :"
														+ SAnchorTagsLinksOnclicksearch.size(),
												result, status);
										break;

									}

									else {

										countn++;
										status = "Fail";
										result = "Links are not matching or No Result Found.";
										if (countn == 1) {
											ReportFactory.reporterOutput(strTCID,
													"Verify filter functionality for links portlet.", strSearchCriteria,
													"Search criteria should be available in the list.", result, status);
											break;
										}

									}
								}
							}
							break;
						}

					}
				}

			} else {

				result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
				status = "Pass";
				ReportFactory.reporterOutput(strTCID, "Verify filter functionality for links portlet.",
						strSearchCriteria, "Search criteria should be available in the list.", result, status);
			}

		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Verify filter functionality for links portlet.", strSearchCriteria,
					"Search criteria should be available in the list.", e.getMessage().toString().substring(0, 250),
					"Fail");
		}

	}

	/**
	 * @author Archana Gaikwad Verify filter functionality for links portlet with
	 *         Invalid data.-->Enter invalid search criteria in search criteria and
	 *         verify corrrect result is displayed.
	 * @param param1
	 *            verifyFilterInvaliddata(strTCID,strDepartmentName,strSubDepartmentName);
	 * @return InValid Search Criteria @ exception try and Catch @ throws Throwable
	 **/
	public static void verifyFilterInvaliddata(String strTCID, String DepartmentName, String SearchCriteria)
			throws Throwable

	{

		String result = null;
		String status = "Fail";

		try {
			// *************************This is Country and
			// Product*****************************

			LogFactory.info("Verify Department Name");

			List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
			DepartmentName = translatedText.get(0);

			WebElement department = GenericFactory.getDeptname(DepartmentName);

			if (department != null) {
				department.click();

				SearchCriteria = SearchCriteria.concat("xyzpqrsds");
				filterbox.sendKeys(SearchCriteria.trim().toString());
				filterbox.sendKeys(Keys.ENTER);
				String resultFound = null;

				if (noResultfound.isDisplayed()) {
					resultFound = noResultfound.getText();

				}

				List<String> translatedTextnew = GenericFactory.getTranslation("No results found");
				String NoResultEng = translatedTextnew.get(0);

				System.out.println(resultFound);
				if (resultFound.equalsIgnoreCase(NoResultEng)) {
					status = "Pass";
					result = "No results found for search criteria.";
				} else {

					result = "Result displayed.";
					status = "Fail";

				}
			} else {

				result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
				status = "Pass";
			}
			ReportFactory.reporterOutput(strTCID, "Verify filter functionality of Links with Invalid filter.",
					SearchCriteria, "No results should be found for search criteria.", result, status);
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Verify filter functionality of Links with Invalid filter.",
					SearchCriteria, "No results should be found for search criteria.",
					e.getMessage().toString().substring(0, 250), "Fail");
		}

	}

	/**
	 * @author Archana Gaikwad Verify smooth scroll to the 2nd level category
	 *         (sub-department) on links.
	 * @param param1
	 *            verifySmoothScroll(strTCID,strDepartmentName,
	 *            strSubDepartmentName);
	 * @return Smooth Scroll @ exception try and Catch @ throws Throwable
	 **/

	@SuppressWarnings("unused")
	public static void verifySmoothScroll(String strTCID, String DepartmentName, String SubDepartmentName)
			throws Throwable {
		String headerName = null;
		String result = null;
		String status = "Fail";
		boolean flag = false;
		String expResult = null;

		try {

			// *************************This is Department
			// Check*****************************

			LogFactory.info("Verify Department Name");
			List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
			DepartmentName = translatedText.get(0);

			WebElement department = GenericFactory.getDeptname(DepartmentName);

			if (department != null) {
				department.click();

				List<WebElement> actualHeadername1 = PortletDriver
						.findElements(By.xpath("//a[@class='active leftNavIndexPadding ']"));

				for (int j = 0; j < actualHeadername1.size(); j++) {
					flag = true;
					headerName = actualHeadername1.get(j).getText().trim();
					// WebElement subCat = actualHeadername1.get(j);

					if (headerName.equalsIgnoreCase(SubDepartmentName)) {
						actualHeadername1.get(j).click();
						expResult = "System should smooth scroll to the particular 2nd level category (sub-department) on the page with fixed navigation.";
						status = "Pass";
						result = "Smooth scroll to the particular 2nd level category (sub-department) on the page with fixed navigation is working fine.";
						break;
					} else {
						status = "Pass";
						expResult = "System should not smooth scroll to the particular 2nd level category (sub-department) on the page with fixed navigation.";
						result = "Smooth scroll to the particular 2nd level category (sub-department) with fixed navigation failed as Subdepartment is not present.";
					}

				}
			} else {

				result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
				status = "Pass";
				expResult = "System should smooth scroll to the particular 2nd level category (sub-department) on the page with fixed navigation.";

			}
			ReportFactory.reporterOutput("TC03_PortletLinks",
					"Verify smooth scroll to the 2nd level category (sub-department) on links portlet.",
					SubDepartmentName, expResult, result, status);

		}

		catch (Exception e) {
			ReportFactory.reporterOutput("TC03_PortletLinks",
					"Verify smooth scroll to the 2nd level category (sub-department) on links portlet.",
					SubDepartmentName,
					"System should smooth scroll to the particular 2nd level category (sub-department) on the page with fixed navigation.",
					e.getMessage().toString().substring(0, 150), "Fail");
		}

	}

	/**
	 * @author Archana Gaikwad Verify Links portlet should display content filtered
	 *         with preferred product types of the user.
	 * @param param1
	 *            changingPrefProduct(strwcmTestCaseID,strUserDefinedCountry,
	 *            strWCMCountry, strUserDefinedProducts,
	 *            strWCMProducts,strContenttype, strDepartmentName,
	 *            strSubDepartmentName,strThirdLevelFolder, strTitle);
	 * @return Changing Produt Prefernce @ exception try and Catch @ throws
	 * Throwable
	 **/

	@SuppressWarnings("unused")
	public static void changingPrefProduct(String wcmTestCaseID, String UserDefinedCountry, String WCMCountry,
			String UserDefinedProducts, String WCMProducts, String Contenttype, String DepartmentName,
			String SubDepartmentName, String ThirdLevelFolder, String Title) throws Throwable {

		try {

			// ******************This is Department Check*****************************

			LogFactory.info("Verify Department Name.");

			List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
			DepartmentName = translatedText.get(0);

			WebElement department = GenericFactory.getDeptname(DepartmentName);

			if (department != null) {
				department.click();

				String result = null;
				String status = null;
				String Expectedresult = null;
				boolean statusResult = false;
				String parentItemUncheck = null;
				boolean flagItemnotfound = false;
				ArrayList<String> productWCMRemoved = new ArrayList<>();
				List<String> listOfElements = GenericFactory.getCheckBoxValuesAll();
				GenericFactory.checkAllProductsData(listOfElements);

				PortletLinksPage_POF.applyFilter.click();

				// System.out.println(listOfElements);
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
				hashsetWCMList.retainAll(listOfElements);
				System.out.println("UserPrefernceList=========" + listOfElements.size());

				if (!ThirdLevelFolder.equalsIgnoreCase("NA")) {
					filterbox.clear();
					filterbox.sendKeys(ThirdLevelFolder);
					filterbox.sendKeys(Keys.ENTER);

					GenericFactory.toClickOnFolder(ThirdLevelFolder, SubDepartmentName);

					filterbox.clear();
				}

				int k;
				for (k = 0; k < hashsetWCMList.size(); k++) {

					if (listOfElements.size() <= hashsetWCMList.size() && listOfElements.size() > 1) {

						hashsetWCMList.remove(listOfElements.get(0));
						System.out.println("Data removed------>" + hashsetWCMList);

					}

					Object[] myArr = hashsetWCMList.toArray();

					parentItemUncheck = myArr[k].toString();

					List<String> translatedText1 = GenericFactory.getTranslation(parentItemUncheck);
					parentItemUncheck = translatedText1.get(0);

					if ((listOfElements.size() > 1))

					{
						WebElement productSeg = PortletDriver
								.findElement(By.xpath(".//*[@id='productSegmentsForm']/div"));
						if (!productSeg.isDisplayed()) {

							ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
						}
						productWCMRemoved.add(parentItemUncheck);
						boolean checkboxstatus = GenericFactory.productcheckstatus(parentItemUncheck);

						if (k >= hashsetWCMList.size() - 1) {

							PortletLinksPage_POF.applyFilter.click();
							Thread.sleep(2000);
							filterbox.clear();
							filterbox.sendKeys(Title);
							filterbox.sendKeys(Keys.ENTER);

							List<WebElement> actualHeadernameP = PortletDriver
									.findElements(By.xpath(".//*[@id='links-target']/div"));
							for (int s = 0; s < actualHeadernameP.size(); s++) {
								String headerNamePortlet = actualHeadernameP.get(s).getText().trim();
								String[] linesdata = headerNamePortlet.split("\n");
								String headername = linesdata[0];
								WebElement secondLevel = actualHeadernameP.get(s);

								if (SubDepartmentName.equals(headername)) {

									break;
								}
							}

							List<WebElement> finallink = PortletDriver
									.findElements(By.xpath(".//*[@id='links-target']/div/div"));

							for (int j = 0; j < finallink.size(); j++) {

								String temp1 = finallink.get(j).getText().trim();
								String[] lines = temp1.split("\n");
								for (int i = 0; i < lines.length; i++) {

									String links = lines[i];
									String Resultfound = null;

									if (links.toUpperCase().contains(Title.toUpperCase())
											&& hashsetWCMList.size() == 1) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Fail";
										result = "Product Preference functionality is not working though we have unchecked product.";

									}

									else if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())
											&& hashsetWCMList.size() > 1 && k != (hashsetWCMList.size() - 1)) {

										Expectedresult = "Search criteria should be available in the list.";
										status = "Pass";
										result = "Title :" + Title
												+ " is available as it is mapped to multiple products.";

									}

									else if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())
											&& hashsetWCMList.size() > 1 && k == (hashsetWCMList.size() - 1)) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Fail";
										result = "Title :" + Title
												+ " is available though we have unchecked all related products.";

									}

									if (links.toUpperCase().contains(Title.toUpperCase())
											&& hashsetWCMList.size() == 1) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Fail";
										// result = "Product Preference functionality is not working though we have
										// unchecked product.";
										result = "Title :" + Title
												+ " is present after the corresponding product types are filtered. "
												+ WCMProducts;

									}

									else if (hashsetWCMList.size() == 1
											&& PortletLinksPage_POF.noResultfound.isDisplayed()) {

										Resultfound = PortletLinksPage_POF.noResultfound.getText();
										if (Resultfound.equalsIgnoreCase("No results found")) {

											Expectedresult = "Search criteria should not be available in the list.";
											status = "Pass";
											// result = "Search criteria is working fine when product is unchecked and
											// link not available.";
											result = "Title :" + Title
													+ " is not present after the corresponding product types are filtered. "
													+ WCMProducts;

										}

									}

									else if (hashsetWCMList.size() > 1 && k >= 1
											&& PortletLinksPage_POF.noResultfound.isDisplayed()) {

										if (PortletLinksPage_POF.noResultfound.isDisplayed()) {
											Resultfound = PortletLinksPage_POF.noResultfound.getText();

											List<String> translatedTextn = GenericFactory
													.getTranslation("No results found");
											result = translatedTextn.get(0);

											if (Resultfound.equalsIgnoreCase(result)) {

												Expectedresult = "Search criteria should not be available in the list after unchecking the items.";
												status = "Pass";
												result = "Title :" + Title
														+ " is not present after the corresponding product types are filtered. ";

											}
										}
									}

								}

							}

						}
					} /*
						 * else { flagItemnotfound = true; Expectedresult =
						 * "Search criteria should not be available in the list after unchecking the items."
						 * ; status = "Pass"; result =
						 * "User can not perform operation as userdefined Product Preference item/items does not match with WCM Product list."
						 * ; ReportFactory.reporterOutput(wcmTestCaseID,
						 * "Verify Links portlet on changing of preferred product types."
						 * ," <B>User Prefernce List :</B>"+listOfElements+ " UserDefined List :" +
						 * hashsetUserdefinedList + "WCMProduct :" + hashsetWCMList +
						 * " :and Link Name :" + Title, Expectedresult, result, status); break; }
						 */
				}

				if (m > 1 && flagItemnotfound == false) {
					ReportFactory.reporterOutput(wcmTestCaseID,
							"Verify Links portlet on changing of preferred product types.",
							" <B>User Prefernce List :</B>" + listOfElements + " UserDefined List :"
									+ hashsetUserdefinedList + "WCMProduct :" + WCMProducts
									+ " List of WCM Product items which are removed  " + productWCMRemoved
									+ " : and Link Name :" + Title,
							Expectedresult, result, status);
				} else if (hashsetUserdefinedList.size() == 1)

				{
					status = "Pass";
					ReportFactory.reporterOutput(wcmTestCaseID,
							"Verify Links portlet on changing of preferred product types.",
							" <B>User Prefernce List :</B>" + listOfElements + " UserDefined List :"
									+ hashsetUserdefinedList + "WCMProduct :" + WCMProducts + " :and Link Name :"
									+ Title,
							"Search criteria should not be available in the list after unchecking the items.",
							"User is not able to perform product preference as single product is available.", status);
				}

			}

		}

		catch (Exception e) {
			ReportFactory.reporterOutput(wcmTestCaseID, "Verify Links portlet on changing of preferred product types.",
					"NA", "Preferred Product preference should work.", e.getMessage().toString().substring(0, 250),
					"Fail");
		}

	}

	// 3rd level parent Index page name should be header.

	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput thirdLevelHeader(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String actualResult = null;
		String expectedResult = null;
		ReportOutput output = null;
		try {
			if (ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {

				String headerInner = PortletDriver.findElement(By.xpath(".//*[@id='content']/div[1]/h3")).getText();
				// System.out.println(headerInner);
				if (headerInner.equalsIgnoreCase(ThirdLevelIndexPage)) {

					flag = "Pass";
					actualResult = "3rd level parent Index page name is header";
					expectedResult = "3rd level parent Index page name should be header.";

				} else {
					flag = "Fail";
					actualResult = "3rd level parent Index page name is not header";
					expectedResult = "3rd level parent Index page name should be header.";
				}

			} else {
				flag = "Pass";
				expectedResult = "3rd level parent Index page name should be header.";
				actualResult = "As 3rdLevelIndexPageCategories is not NA so Not applicable.";
			}

		} catch (Exception e) {

			flag = "Fail";
			actualResult = e.getMessage().toString().substring(0, 250);
			expectedResult = "3rd level parent Index page name should be header.";

		}
		return output = new ReportOutput(flag, "Verify 3rd level parent Index page name should be header.",
				ThirdLevelIndexPage, wcmTestCaseID, actualResult, expectedResult);

	}

	// Content should be displayed with header and footer text if available.

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void contentHeaderFooterLogo(LinkedHashMap userWcmContent, String strExpectedValue) throws Throwable {
		String status = "Fail";
		String result = null;
		String DepartmentName = userWcmContent.get("DepartmentName").toString().trim();
		List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
		DepartmentName = translatedText.get(0);
		WebElement department = GenericFactory.getDeptname(DepartmentName);

		if (department != null) {

			GenericFactory.navigateToIndexPage(userWcmContent);

			String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
			String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
			LogFactory.info("Verify Department Name.");
			result = "Content with Header and Footer are not displayed on index page.";

			List<WebElement> footer = PortletDriver.findElements(By.xpath("//footer[@class='wpthemeFooter']//li"));
			List<String> valuesExcel = GenericFactory.splitString(strExpectedValue, ",");
			List<String> valuesFooterDept = new ArrayList<String>();

			for (int i = 0; i < footer.size(); i++) {

				String temp = footer.get(i).getText();

				valuesFooterDept.add(temp);

			}

			if (valuesExcel.equals(valuesFooterDept) && homeLogo.isDisplayed()) {
				result = "Content with Header and Footer are displayed on index page.";
				status = "Pass";
			}

		}

		else {

			result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
			status = "Pass";

			// testing.gssg
		}
		ReportFactory.reporterOutput("TC06_PortletLinks",
				"Verify Content should be displayed with header and footer text if available.", strExpectedValue,
				"Content should be displayed with header and footer.", result, status);

	}

	// This is to test Appropriate Content links should be displayed on the 3rd
	// level parent index page in two columns//

	@SuppressWarnings({ "rawtypes", "unused" })
	public static ReportOutput linksColumnDistri(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Appropriate Content links displayed on the 3rd level parent index page in two columns. ";
		String actualResult = "";
		String expectedResult = "";

		try

		{

			// ********************This is Department
			// Check*****************************
			if (ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {

				List<WebElement> columnCount = PortletDriver
						.findElements(By.xpath(".//*[@id='index-links-target']/div/div/div"));
				int Count = columnCount.size();

				if (Count == 2 || Count == 1) {

					flag = "Pass";
					actualResult = "Appropriate Content links is displayed on the 3rd level parent index page in one/two columns.";
					expectedResult = "Appropriate Content links should be displayed on the 3rd level parent index page in two columns. ";

				} else {
					flag = "Fail";
					actualResult = "Appropriate Content links are not displayed on the 3rd level parent index page in two columns.";
					expectedResult = "Appropriate Content links should be displayed on the 3rd level parent index page in two columns. ";

				}

			} else {
				flag = "Pass";
				expectedResult = "Appropriate Content links should be displayed on the 3rd level parent index page in two columns. ";
				actualResult = "As 3rdLevelIndexPageCategories is not NA so Not applicable.";
			}

		} catch (Exception e) {

			flag = "Fail";
			expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";
			actualResult = e.getMessage().toString().substring(0, 125);

		}

		return new ReportOutput(flag, scenarioName, ThirdLevelIndexPage, wcmTestCaseID, actualResult, expectedResult);

	}

	// Verify Fly-out navigation on Department page.

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void CheckFlyOutNavigation(LinkedHashMap userWcmContent, String strExpectedValue) throws Throwable {
		String status = "Fail";
		String result = null;
		String DepartmentName = userWcmContent.get("DepartmentName").toString().trim();

		WebElement department = GenericFactory.getDeptname(DepartmentName);

		if (department != null) {

			GenericFactory.navigateToIndexPage(userWcmContent);

			String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
			String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
			LogFactory.info("Verify Department Name.");
			result = "Content with Header and Footer are not displayed on index page.";

			List<WebElement> footer = PortletDriver.findElements(By.xpath("//footer[@class='wpthemeFooter']//li"));
			List<String> valuesExcel = GenericFactory.splitString(strExpectedValue, ",");
			List<String> valuesFooterDept = new ArrayList<String>();

			for (int i = 0; i < footer.size(); i++) {

				String temp = footer.get(i).getText();

				valuesFooterDept.add(temp);

			}

			if (valuesExcel.equals(valuesFooterDept) && homeLogo.isDisplayed()) {
				result = "Content with Header and Footer are displayed on index page.";
				status = "Pass";
			}

		}

		else {

			result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
			status = "Pass";

			// testing.gssg
		}
		ReportFactory.reporterOutput("TC06_PortletLinks",
				"Verify Content should be displayed with header and footer text if available.", strExpectedValue,
				"Content should be displayed with header and footer.", result, status);

	}

	// This is to test Links should be distributed evenly in two columns without
	// portlet scrolling.//
	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput linkswithoutScroll(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Appropriate Content links displayed on the 3rd level parent index page in two columns.";
		String actualResult = "";
		String expectedResult = "";
		try

		{
			if (ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {

				String JS_ELEMENT_IS_SCROLLABLE = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
				List<WebElement> columnScroll = PortletDriver
						.findElements(By.xpath(".//*[@id='index-links-target']/div"));
				JavascriptExecutor jse = (JavascriptExecutor) PortletDriver;
				Boolean isScrollable = (Boolean) jse.executeScript(JS_ELEMENT_IS_SCROLLABLE, columnScroll);
				// System.out.println(isScrollable);
				if (isScrollable == false) {
					flag = "Pass";
					actualResult = "Links are distributed without portlet scrolling.";
					expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";

				} else {
					flag = "Fail";
					actualResult = "Links are not distributed without portlet scrolling.";
					expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";

				}

			} else {
				flag = "Pass";
				expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";
				actualResult = "As 3rdLevelIndexPageCategories is not NA so Not applicable.";
			}
		}

		catch (Exception e) {

			actualResult = e.getMessage().toString().substring(0, 125);
			expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";
			flag = "Fail";

		}

		return new ReportOutput(flag, scenarioName, ThirdLevelIndexPage, wcmTestCaseID, actualResult, expectedResult);
	}

	// This is to test Links should be displayed in Alphabetical order.//

	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput linksAlphabeticOrder(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Links should be displayed in Alphabetical order.";
		String actualResult = "Links are not present to verify alphabetic Order.";
		String expectedResult = "Links should be displayed in Alphabetical order.";

		try {

			if (ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {

				List<WebElement> innerLinks = PortletDriver
						.findElements(By.xpath(".//*[@id='index-links-target']/div"));

				// cert-->.//*[@id='index-links-target']/div
				for (int k = 0; k < innerLinks.size(); k++) {
					String innerLinkList = innerLinks.get(k).getText().trim();
					WebElement innerData = innerLinks.get(k);

					ArrayList<String> obtainedList = new ArrayList<>();
					List<WebElement> ListofLinks = innerData.findElements(By.tagName("a"));
					for (WebElement we : ListofLinks) {

						obtainedList.add(we.getText());

					}

					ArrayList<String> sortedList = new ArrayList<>();
					for (String s : obtainedList) {
						sortedList.add(s);

					}

					Collections.sort(sortedList);

					boolean alphaOrderFlag = obtainedList.equals(sortedList);

					if (alphaOrderFlag == true) {

						expectedResult = "Links should be displayed in Alphabetical order.";
						actualResult = "Links are displayed in Alphabetical order.";
						flag = "Pass";
					} else {
						expectedResult = "Links should be displayed in Alphabetical order.";
						actualResult = "Links are not displayed in Alphabetical order.";
						flag = "Fail";

					}
					break;

				}
			}

			else {
				flag = "Pass";
				expectedResult = "Links should be displayed in Alphabetical order.";
				actualResult = "As 3rdLevelIndexPageCategories is not NA so Not applicable.";
			}

		}

		catch (Exception e) {

			expectedResult = "Categories are displayed in as per the Alphabetical order of the site area name.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}

		return new ReportOutput(flag, scenarioName, ThirdLevelIndexPage, wcmTestCaseID, actualResult, expectedResult);

	}

	/**
	 * @author Archana Gaikwad Category-->3rd level parent index page name should be
	 *         header.
	 * @return PZN Template @ exception try and Catch @ throws Throwable
	 **/

	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput thirdLevelHeaderCategory(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String ThirdLevelIndexPageNestedCategories = userWcmContent.get("3rdLevelIndexPageNestedCategories").toString()
				.trim();
		String ThirdLevel3rdLevelChildIndexPage = userWcmContent.get("3rdLevelChildIndexPage").toString().trim();
		String ThirdLevel3rdLevelGrandChildIndexPage = userWcmContent.get("3rdLevelGrandChildIndexPage").toString()
				.trim();
		String flag = "Fail";
		String scenarioName = "Verify 3rd level Index page name should be header.";
		String actualResult = "";
		String expectedResult = "";
		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		boolean filterbox = false;

		try {

			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}
			filterbox = false;
			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
				expectedResult = "Verify 3rd level Index page name should be header.";
				flag = "Pass";
				actualResult = "Verify 3rd level Index page name should be header and  this can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {
				if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")) {
					String headerInner = PortletDriver.findElement(By.xpath(".//*[@id='content']/div[1]/h3")).getText();

					if (headerInner.equalsIgnoreCase(ThirdLevelIndexPage)
							|| headerInner.equalsIgnoreCase(ThirdLevel3rdLevelChildIndexPage)
							|| headerInner.equalsIgnoreCase(ThirdLevel3rdLevelGrandChildIndexPage)) {

						expectedResult = "Index/child Index/Grand Child Index page name should be header.";
						actualResult = "Index/child Index/Grand Child Index page name is header";
						flag = "Pass";

					} else {

						expectedResult = "Index/child Index/Grand Child Index page name should be header.";
						actualResult = "Index/child Index/Grand Child Index page name is not header";
						flag = "Fail";
					}

				} else {
					expectedResult = "Index/child Index/Grand Child Index page name should be header.";
					actualResult = "As 3rdLevelIndexPageCategories is 'NA' so Not applicable.";
					flag = "Pass";

				}
			}
		}

		catch (Exception e) {

			expectedResult = "Index/child Index/Grand Child Index page name should be header.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}
		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);

	}

	/**
	 * @author Archana Gaikwad Categories are the header of the content groups
	 * @return PZN Template @ exception try and Catch @ throws Throwable
	 **/
	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput headerContent(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Categories are the header of the content groups.";
		String actualResult = "";
		String expectedResult = "";
		String catgoryName = null;

		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		boolean filterbox = false;

		try {

			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}

			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
				expectedResult = "Categories are the header of the content groups.";
				flag = "Pass";
				actualResult = "Categories should be the header of the content groups and this can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {

				if (!ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {
					try {
						WebElement category = PortletDriver
								.findElement(By.xpath("//h3[contains(.,'" + ThirdLevelIndexPageCategories + "')]"));
						catgoryName = category.getText().toString();
					} catch (Exception e) {
						expectedResult = "Categories are the header of the content groups.";
						actualResult = "Categories are not present.";
						flag = "Pass";

					}

					if (ThirdLevelIndexPageCategories.equalsIgnoreCase(catgoryName)) {

						expectedResult = "Categories should be the header of the content groups.";
						actualResult = "Categories are header of the content groups.";
						flag = "Pass";

					} else {

						expectedResult = "Categories should be the header of the content groups.";
						actualResult = "Categories are not the header of the content groups.";
						flag = "Fail";

					}

				} else {

					expectedResult = "Categories should be the header of the content groups.";
					actualResult = "As 3rdLevelIndexPageCategories is 'NA' so Not applicable.";
					flag = "Pass";
				}

			}
		} catch (Exception e) {
			expectedResult = "Categories are the header of the content groups.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}
		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);
	}

	// Links under Categories are sorted in alphabetical order.

	@SuppressWarnings("unused")
	public static ReportOutput alllinksundercatalph(@SuppressWarnings("rawtypes") LinkedHashMap userWcmContent)
			throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Links Under Categories are displayed in as per the Alphabetical order.";
		String actualResult = "";
		String expectedResult = "";
		try {
			if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")) {

				List<WebElement> CategoryAlpha = PortletDriver
						.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div"));
				ArrayList<String> loopListOriginal = new ArrayList<String>();

				int count = 0;
				for (int i = 0; i < CategoryAlpha.size(); i++)

				{
					WebElement Element = CategoryAlpha.get(i);
					Element.getText();
					List<WebElement> linkcount = PortletDriver
							.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div/div[2]"));
					for (int k = 0; k < linkcount.size(); k++) {

						String Catdata = linkcount.get(k).getText().toString();
						WebElement Linkcount = PortletDriver.findElement(By.linkText(Catdata));

						List<WebElement> links = Element.findElements(By.tagName("a"));
						for (WebElement we : links) {

							for (count = 0; count < links.size(); count++) {

								if (we.getText().equals(Linkcount.getText())) {

									// System.out.println(count);
								}
							}

						}

					}

				}

				// Need to add one checkpoint when only one link is present -->
				// CategoryAlpha.size()<2

				ArrayList<String> sorted = new ArrayList<String>(loopListOriginal);
				Collections.sort(sorted);
				boolean sortedflag = loopListOriginal.equals(sorted);
				if (sortedflag == true || count > 0) {

					expectedResult = "Links Under Categories should be displayed in as per the Alphabetical order of the site area name.";
					actualResult = "Links Under Categories are displayed in as per the Alphabetical order";
					flag = "Pass";

				}

				else {
					expectedResult = "Links Under Categories should be displayed in as per the Alphabetical order of the site area name.";
					actualResult = "Links Under Categories are not displayed in as per the Alphabetical order";
					flag = "Fail";

				}

			} else {

				expectedResult = "Links Under Categories should be displayed in as per the Alphabetical order of the site area name.";
				actualResult = "As 3rdLevelIndexPageCategories is 'NA' so Not applicable.";
				flag = "Pass";
			}

		} catch (Exception e) {

			expectedResult = "Links Under Categories should be displayed in as per the Alphabetical order of the site area name.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}
		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);
	}

	// *************************************************************************************************************
	/**
	 * @author Archana Gaikwad Verify Links should be displayed in Alphabetical
	 *         order.
	 * @return PZN Template @ exception try and Catch @ throws Throwable
	 **/
	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput linksAlphabeticOrderLinks(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Links should be displayed in Alphabetical order.";
		String actualResult = "Links are not present to verify alphabetic Order.";
		String expectedResult = "Links should be displayed in Alphabetical order.";
		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		boolean filterbox = false;

		try {
			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}

			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
				expectedResult = "Categories are the header of the content groups.";
				flag = "Pass";
				actualResult = "Categories should be the header of the content groups and this can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {
				if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")) {

					List<WebElement> innerLinks = PortletDriver
							.findElements(By.xpath("//div[contains(@class,'link-col col-xs-6')]"));

					for (int k = 0; k < innerLinks.size(); k++) {
						String innerLinkList = innerLinks.get(k).getText().trim();
						WebElement innerData = innerLinks.get(k);

						ArrayList<String> obtainedList = new ArrayList<>();
						List<WebElement> ListofLinks = innerData.findElements(By.tagName("a"));
						for (WebElement we : ListofLinks) {

							obtainedList.add(we.getText());

						}
						// newly added
						Collections.sort(obtainedList);

						ArrayList<String> sortedList = new ArrayList<>();
						for (String s : obtainedList) {
							sortedList.add(s);

						}
						System.out.println(">>>>>>>>>>>>>>>>." + obtainedList.equals(sortedList));

						Collections.sort(sortedList);
						boolean alphaOrderFlag = obtainedList.equals(sortedList);
						System.out.println(">>>>>>>>>>>>>>>>." + obtainedList.equals(sortedList));
						if (alphaOrderFlag == true) {

							expectedResult = "Links should be displayed in Alphabetical order.";
							actualResult = "Links are displayed in Alphabetical order.";
							flag = "Pass";
						} else {
							expectedResult = "Links should be displayed in Alphabetical order.";
							actualResult = "Links are not displayed in Alphabetical order.";
							flag = "Fail";

						}
						break;

					}

				} else {

					expectedResult = "Categories are displayed in as per the Alphabetical order of the site area name.";
					actualResult = "As 3rdLevelIndexPageCategories is 'NA' so Not applicable.";
					flag = "Pass";
				}
			}
		} catch (Exception e) {

			expectedResult = "Categories are displayed in as per the Alphabetical order of the site area name.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}

		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);

	}

	/**
	 * @author Archana Gaikwad Verify Categories should be displayed in Alphabetical
	 *         order.
	 * @return PZN Template @ exception try and Catch @ throws Throwable
	 **/
	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput alphabeticCategory(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Categories should be displayed in Alphabetical order.";
		String actualResult = "";
		String expectedResult = "Categories are displayed in as per the Alphabetical order.";
		try {

			String strContenttype = userWcmContent.get("ContentType").toString().trim();
			boolean filterbox = false;

			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}

			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
				expectedResult = "Categories should be displayed in as per the Alphabetical order of the site area name.";
				flag = "Pass";
				actualResult = "Categories are not displayed in as per the Alphabetical order as Index/Child/Grand-Child Link is not Present.";

			} else {
				if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")
						&& !ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {

					List<WebElement> categoryAlphabetic = PortletDriver
							.findElements(By.xpath("//h3[@class='section-category-title']"));
					ArrayList<String> loopListOriginal = new ArrayList<String>();

					for (int i = 0; i < categoryAlphabetic.size(); i++)

					{

						String Catdata = categoryAlphabetic.get(i).getText().toString();
						loopListOriginal.add(Catdata);

					}

					ArrayList<String> sorted = new ArrayList<String>(loopListOriginal);
					Collections.sort(sorted);

					boolean sortedflag = loopListOriginal.equals(sorted);
					if (sortedflag == true) {

						expectedResult = "Categories should be displayed in as per the Alphabetical order of the site area name.";
						actualResult = "Categories are displayed in as per the Alphabetical order.";
						flag = "Pass";

					} else {
						expectedResult = "Categories should be displayed in as per the Alphabetical order of the site area name.";
						actualResult = "Categories are not displayed in as per the Alphabetical order.";
						flag = "Fail";

					}

				} else {

					expectedResult = "Categories should be displayed in as per the Alphabetical order of the site area name.";
					actualResult = "As 3rdLevelIndexPageCategories is 'NA' so Not applicable.";
					flag = "Pass";
				}
			}
		} catch (Exception e) {

			expectedResult = "Categories should be displayed in as per the Alphabetical order of the site area name.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}
		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);
	}

	/**
	 * @author Archana Gaikwad This is to test Links should be distributed evenly in
	 *         two columns without portlet scrolling.
	 * @return PZN Template @ exception try and Catch @ throws Throwable
	 **/

	@SuppressWarnings({ "unused", "rawtypes" })
	public static ReportOutput linkswithoutScrollCategory(LinkedHashMap userWcmContent) throws Throwable {

		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String ContentType = userWcmContent.get("ContentType").toString().trim();
		String ThirdLevelIndexPageNestedCategories = userWcmContent.get("3rdLevelIndexPageNestedCategories").toString();
		String flag = "Fail";
		String scenarioName = "Verify Links under Category distributed evenly in two columns without portlet scrolling.";
		String actualResult = "";
		String expectedResult = "";
		List<WebElement> Column = null;
		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		boolean filterbox = false;

		if (!ThirdLevelIndexPage.equalsIgnoreCase("NA") && !ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {
			try {

				try {
					filterbox = departmentLinksfilterbox.isDisplayed();
				} catch (Exception e) {

				}

				if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
						|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
						|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
					expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
					flag = "Pass";
					actualResult = "Links under Category are not  distributed evenly in two columns without portlet scrolling as Index/Child/Grand-Child Link is not Present.";

				} else {
					boolean flagForIndex = false;

					if (!ThirdLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {
						flagForIndex = GenericFactory.clickOnIndexPageLinkOnChildLevelDepartmentcheck(Title,
								ThirdLevelIndexPageCategories, ThirdLevelIndexPageNestedCategories);
					}

					if (ContentType.equalsIgnoreCase("AT-Index Page")
							|| ContentType.equalsIgnoreCase("AT-ChildIndex Page")
							|| ContentType.equalsIgnoreCase("AT-GrandChild Index Page")) {
						List<WebElement> Table = PortletDriver
								.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div"));
						// List<WebElement> Table =
						// PortletDriver.findElements(By.xpath(".//*[@id='index-links-target']/div"));
						for (int i = 1; i < Table.size(); i++) {
							WebElement ThirdLevelIndexPageNestedCategoriesWeb = PortletDriver
									.findElement(By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]"));

							String allHeaderData = ThirdLevelIndexPageNestedCategoriesWeb.getText();

							String[] upperData = allHeaderData.split("\n");
							String headerFirst = upperData[0];

							if (headerFirst.equalsIgnoreCase(ThirdLevelIndexPageNestedCategories)) {

								Column = PortletDriver.findElements(
										By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]/div"));

							} else if (ThirdLevelIndexPageNestedCategories.equalsIgnoreCase("NA")
									|| ThirdLevelIndexPageNestedCategories == null) {
								Column = PortletDriver.findElements(
										By.xpath(".//*[@id='linkIndexPageContainer']/div[" + i + "]/div"));

							}
						}

						WebElement Category = PortletDriver
								.findElement(By.xpath("//h3[contains(.,'" + ThirdLevelIndexPageCategories + "')]"));
						List<WebElement> columnScroll = PortletDriver
								.findElements(By.xpath(".//*[@id='linkIndexPageContainer']/div/div[2]/div"));
						String CatName = Category.getText().toString();

						if (ThirdLevelIndexPageCategories.equalsIgnoreCase(CatName)) {

							String JS_ELEMENT_IS_SCROLLABLE = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
							JavascriptExecutor jse = (JavascriptExecutor) PortletDriver;
							Boolean isScrollable = (Boolean) jse.executeScript(JS_ELEMENT_IS_SCROLLABLE, columnScroll);

							if (Table.size() == 0) {
								flag = "Fail";
								actualResult = "Table with valid format/Links are not present to verify.";
								expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";
							}

							else if ((isScrollable == false && Column.size() == 1
									|| isScrollable == false && Column.size() == 2 && flagForIndex == true)
									|| isScrollable == false && Column.size() == 1
									|| isScrollable == false && Column.size() == 2
											&& ThirdLevelIndexPageNestedCategories.equalsIgnoreCase("NA")) {

								expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
								actualResult = "Links are distributed without portlet scrolling for Category :";
								flag = "Pass";
							}

							else {

								expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
								actualResult = "Links are not present to verify/distributed without portlet scrolling for Category :";
								flag = "Fail";
							}

						}

					}

					else {

						Column = PortletDriver
								.findElements(By.xpath(".//*[@id='index-links-target']/div/div/div/div[1]"));
						System.out.println(Column.size());
						WebElement columnScroll = PortletDriver
								.findElement(By.xpath(".//*[@id='index-links-target']/div/div"));

						String JS_ELEMENT_IS_SCROLLABLE = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
						JavascriptExecutor jse = (JavascriptExecutor) PortletDriver;
						Boolean isScrollable = (Boolean) jse.executeScript(JS_ELEMENT_IS_SCROLLABLE, columnScroll);
						if ((isScrollable == false && Column.size() == 1 || isScrollable == false && Column.size() == 2)
								|| isScrollable == false && Column.size() == 1) {

							expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
							actualResult = "Links are distributed without portlet scrolling for Category :";
							flag = "Pass";
						}

						else {

							expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
							actualResult = "Links are not present to verify/distributed without portlet scrolling for Category :";
							flag = "Fail";
						}

					}
				}

			} catch (Exception e) {

				expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
				actualResult = e.getMessage().toString().substring(0, 125);

			}

		} else if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")
				&& ThirdLevelIndexPageCategories.equalsIgnoreCase("NA")) {
			try

			{
				if (ContentType.equalsIgnoreCase("AT-Index Page") || ContentType.equalsIgnoreCase("AT-ChildIndex Page")
						|| ContentType.equalsIgnoreCase("AT-GrandChild Index Page"))

				{

					List<WebElement> Table = PortletDriver
							.findElements(By.xpath(".//*[@id='index-links-target']/div/div"));

					Column = PortletDriver.findElements(By.xpath(".//*[@id='index-links-target']/div/div/div/div"));
					System.out.println(Column.size());
					WebElement columnScroll = PortletDriver
							.findElement(By.xpath(".//*[@id='index-links-target']/div/div"));

					String JS_ELEMENT_IS_SCROLLABLE = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
					JavascriptExecutor jse = (JavascriptExecutor) PortletDriver;
					Boolean isScrollable = (Boolean) jse.executeScript(JS_ELEMENT_IS_SCROLLABLE, columnScroll);
					if ((isScrollable == false && Column.size() == 1 || isScrollable == false && Column.size() == 2)
							|| isScrollable == false && Column.size() == 1) {

						expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
						actualResult = "Links are distributed without portlet scrolling for Category :";
						flag = "Pass";
					}

					else {

						expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
						actualResult = "Links are not present to verify/distributed without portlet scrolling for Category :";
						flag = "Fail";
					}

				} else {
					Column = PortletDriver.findElements(By.xpath(".//*[@id='index-links-target']/div/div/div/div"));
					System.out.println(Column.size());
					WebElement columnScroll = PortletDriver
							.findElement(By.xpath(".//*[@id='index-links-target']/div/div"));

					String JS_ELEMENT_IS_SCROLLABLE = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
					JavascriptExecutor jse = (JavascriptExecutor) PortletDriver;
					Boolean isScrollable = (Boolean) jse.executeScript(JS_ELEMENT_IS_SCROLLABLE, columnScroll);
					if ((isScrollable == false && Column.size() == 1 || isScrollable == false && Column.size() == 2)
							|| isScrollable == false && Column.size() == 1) {

						expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
						actualResult = "Links are distributed without portlet scrolling for Category :";
						flag = "Pass";
					}

					else {

						expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
						actualResult = "Links are not present to verify/distributed without portlet scrolling for Category :";
						flag = "Fail";
					}

				}
			}

			catch (Exception e) {

				actualResult = e.getMessage().toString().substring(0, 125);
				expectedResult = "Links should be distributed evenly in two columns without portlet scrolling.";
				flag = "Fail";

			}

		}

		else {

			expectedResult = "Links under Category should be distributed evenly in two columns without portlet scrolling.";
			actualResult = "As Condition does not match so Not applicable.";
			flag = "Pass";
		}

		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);

	}

	/**
	 * @author Archana Gaikwad Content should be displayed with header and footer
	 *         text if available.
	 * @return PZN Template @ exception try and Catch @ throws Throwable
	 **/
	@SuppressWarnings("unused")
	public static ReportOutput linksWithHeadernFooter(@SuppressWarnings("rawtypes") LinkedHashMap userWcmContent)
			throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String ThirdLevelIndexPageNestedCategories = userWcmContent.get("3rdLevelIndexPageNestedCategories").toString()
				.trim();
		String headerlist = userWcmContent.get("HeaderName").toString().trim();
		String footerList = userWcmContent.get("FooterName").toString().trim();
		String flag = "Fail";
		String scenarioName = "Verify Content with header and footer text if available.";
		String actualResult;
		String expectedResult;
		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		boolean filterbox = false;

		try {

			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}

			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
				expectedResult = "Content should be displayed with header and footer text if available.";
				flag = "Pass";
				actualResult = "Header and footer text can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {
				if (!ThirdLevelIndexPage.equalsIgnoreCase("NA")) {
					String headerInner = null;
					String footerInner = null;

					try {
						headerInner = PortletDriver.findElement(By.xpath(".//*[@id='content']/div[2]")).getText();
						footerInner = PortletDriver.findElement(By.xpath(".//*[@id='content']/div[4]")).getText();
						headerInner = headerInner.replaceAll("\n", " ");
						footerInner = footerInner.replaceAll("\n", " ");

						if (headerInner.trim().equalsIgnoreCase(headerlist.trim())
								&& footerInner.trim().equalsIgnoreCase(footerList.trim())) {

							expectedResult = "Content should be displayed with header and footer text if available.";
							actualResult = "Content is displayed with header and footer text.";
							flag = "Pass";

						} else {

							expectedResult = "Content should be displayed with header and footer text if available.";
							actualResult = " Content is not displayed with header and footer text if available.";
							flag = "Fail";
						}

					} catch (Exception e) {

						expectedResult = "Content should be displayed with header and footer text if available.";
						actualResult = "Header and Footer are not Present to Verify.";
						flag = "Fail";

					}

				}

				else {
					expectedResult = "Content should be displayed with header and footer text if available.";
					actualResult = "As 3rdLevelIndexPage is 'NA' so Not applicable.";
					flag = "Pass";

				}

			}
		}

		catch (Exception e) {

			expectedResult = "Content should be displayed with header and footer text if available.";
			actualResult = e.getMessage().toString().substring(0, 125);
			flag = "Fail";

		}
		return new ReportOutput(flag, scenarioName, BaseClass.PORTLET_LINKFLAG, wcmTestCaseID, actualResult,
				expectedResult);

	}

	/**
	 * @author Archana Gaikwad Verify Navigate to an index page on DealerPath portal
	 *         created with 'Matrix - 2/3/4/5 columns' presentation template.
	 * @param param1
	 *            matrix2xolumns(userWCMContent.get(i));
	 * @return 2,3,4,5 Presentation Template @ exception try and Catch @ throws
	 * Throwable
	 **/

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void matrix2xolumns(LinkedHashMap userWcmContent) throws Throwable {
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String Index_Page_Template = userWcmContent.get("Index_Page_Template").toString().trim();
		String Index_Page_Labels = userWcmContent.get("Index_Page_Template_Label").toString().trim();
		String ReleaseDate = userWcmContent.get("ReleaseDate").toString().trim();
		String flag = "Fail";
		String result = "Matrix is not displayed.";
		List<WebElement> columnData = null;
		String actualResult;
		String expectedResult;
		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		boolean filterbox = false;
		Set<String> valueslinks = new HashSet<String>();
		String headerPresent = "NA";
		boolean flagHeader = false;
		boolean containsAll = false;
		int cnt = 0;
		try

		{

			int numberOnly = Integer.parseInt(Index_Page_Template.replaceAll("[^0-9]", ""));
			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}
			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {

				flag = "Pass";
				result = "Matrix columns presentation template can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {

				try {

					headerPresent = PortletDriver
							.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[1]/h3")).getText()
							.toString();
				} catch (Exception e) {
					headerPresent = "NA";
				}
				if (headerPresent.equalsIgnoreCase("NA") || headerPresent.equalsIgnoreCase("")) {

					List<WebElement> columnCount = PortletDriver
							.findElements(By.xpath("//div[contains(@class,'table-header ')]"));
					columnCount.size();
					columnData = PortletDriver.findElements(By.xpath("//div[contains(@class,'group-table-cell')]"));
					List<WebElement> columnCellTotal = PortletDriver
							.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[2]/div/div[1]"));
					columnCellTotal.size();
					if (columnCellTotal.size() == 0) {
						columnCellTotal = PortletDriver.findElements(
								By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div/div[2]/div/div[1]"));
						columnCellTotal.size();

					}
					int Count = 0;
					columnCount.size();
					columnData.size();

					if (!columnCount.isEmpty() && (!Index_Page_Template.equalsIgnoreCase("")
							|| (!Index_Page_Template.equalsIgnoreCase("NA")))) {

						for (int i = 0; i < columnCount.size(); i++) {

							// String columnName =
							// PortletDriver.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div/div[1]/div["
							// + i + "]")).getText();
							List<WebElement> columnName = PortletDriver
									.findElements(By.xpath("//div[contains(@class,'table-header ')]"));

							valueslinks.add(columnName.get(i).getText());

							String[] index_Page_Labels_list = Index_Page_Labels.split(",");

							containsAll = valueslinks.containsAll(Arrays.asList(index_Page_Labels_list));

						}

						if (columnCount.size() == columnData.size() / columnCellTotal.size()
								&& numberOnly == columnCount.size() && containsAll == true) {

							flag = "Pass";
							result = "Matrix is displayed with :" + columnCount.size() + " Column Table.";

						} else {
							flag = "Fail";
							result = "Matrix is displayed with Incorrect Column name and/or Data Count.";

						}
					} else {
						flag = "Fail";
						result = "Matrix Table/Index Page Template is not displayed.";
					}
				} else {
					List<WebElement> columnHeader = null;
					List<WebElement> headerMatch = PortletDriver
							.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div/h3"));
					for (int h = 1; h <= headerMatch.size(); h++) {
						String headerMatchString = PortletDriver
								.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h + "]/h3"))
								.getText();
						flagHeader = false;

						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date = dateFormat.parse(ReleaseDate);
						String[] fields = ReleaseDate.split("[ \\-]");
						String yearOnly = fields[0];

						if (yearOnly.equalsIgnoreCase(headerMatchString)) {

							columnHeader = PortletDriver.findElements(
									By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h + "]/div[1]/div"));
							for (int y = 1; y <= columnHeader.size(); y++) {
								List<String> items = Arrays.asList(Index_Page_Labels.split("\\s*,\\s*"));
								String columnName = PortletDriver
										.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h
												+ "]/div[1]/div[" + y + "]"))
										.getText();

								for (int z = 0; z < items.size(); z++) {

									if (columnName.equalsIgnoreCase(items.get(z).toString())
											|| columnName.contains(items.get(z).toString())) {
										cnt++;
										if (cnt == items.size()) {
											containsAll = true;
										}
									}
								}
							}

							if (numberOnly == columnHeader.size() && containsAll == true) {

								flag = "Pass";
								result = "Matrix is displayed with :" + columnHeader.size() + " Column Table.";

							} else {
								flag = "Fail";
								result = "Matrix is displayed with Incorrect Column name and/or Data Count.";

							}
						}

					}
				}

			}

			ReportFactory.reporterOutput(wcmTestCaseID,
					"Navigate to an index page on DealerPath portal created with Matrix columns presentation template from links portlet ",
					BaseClass.PORTLET_LINKFLAG,
					"Matrix should be displayed on the 3rd level parent index page in 2/3/4/5 columns.", result, flag);

		} catch (Exception e) {

			ReportFactory.reporterOutput(wcmTestCaseID,
					"Navigate to an index page on DealerPath portal created with Matrix columns presentation template from links portlet ",
					BaseClass.PORTLET_LINKFLAG,
					" Matrix should be displayed on the 3rd level parent index page in 2/3/4/5 columns.",
					e.getMessage().toString().substring(0, 125), "Fail");

		}
		GenericFactory.navigateToHomePage();
	}

	/**
	 * @author Archana Gaikwad Verify Navigate to an index page on DealerPath portal
	 *         created with 'Matrix - 2/3/4/5 columns' presentation template and
	 *         check sorting and grouping
	 * @param param1
	 *            matrixGrouping(userWCMContent.get(i),allDataComparison,allDates);
	 * @return Sorting and year Grouping @ exception try and Catch @ throws
	 * Throwable
	 **/

	public static void matrixGrouping(LinkedHashMap userWcmContent, List<String> allDataComparison,
			List<String> allDates) throws Throwable {

		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String ThirdLevelIndexPage = userWcmContent.get("3rdLevelIndexPage").toString().trim();
		String Title = userWcmContent.get("Title").toString().trim();
		String ThirdLevelIndexPageCategories = userWcmContent.get("3rdLevelIndexPageCategories").toString().trim();
		String Index_Page_Template = userWcmContent.get("Index_Page_Template").toString().trim();
		String Index_Page_Labels = userWcmContent.get("Index_Page_Template_Label").toString().trim();
		String strContenttype = userWcmContent.get("ContentType").toString().trim();
		String sorting = userWcmContent.get("Sort By").toString().trim();
		String ascendingDescending = userWcmContent.get("Sort Order").toString().trim();
		String YearGrouping = userWcmContent.get("Year Grouping Required").toString().trim();
		String headerYear = userWcmContent.get("ReleaseDate").toString().trim();
		boolean headerflag = false;
		String flag = "Fail";
		String result = "Matrix is not displayed.";
		List<WebElement> columnData = null;
		String actualResult;
		String expectedResult;
		boolean filterbox = false;
		ArrayList<String> valueslinks = new ArrayList<String>();
		ArrayList<String> allDatesString = new ArrayList<String>();
		ArrayList<Date> allDatesIndexPage = new ArrayList<>();
		boolean areDateSorted = true;
		Set<String> allYear = new HashSet<String>();
		ArrayList<String> allTitleIndexPage = new ArrayList<String>();
		String yearOnly = null;
		boolean titleContains = false;
		boolean flagHeader = false;
		int cntValue = 0;

		try

		{
			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}

			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {

				flag = "Pass";
				result = "Matrix columns presentation template can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {

				int count = 0;
				String headerPresent = "NA";
				if (sorting.equalsIgnoreCase("Column 5")) {
					sorting = "Column 5(Sorting)";
				}
				if (sorting.equalsIgnoreCase("Description")) {
					sorting = "Desc";
				}

				// This is to check header is present or not.
				try {

					headerPresent = PortletDriver
							.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[1]/h3")).getText()
							.toString();
				} catch (Exception e) {
					headerPresent = "NA";
				}
				// All conditions starts.
				// Condition1 -->when header is not present and sorting on
				// title,description,name
				if (headerPresent.equalsIgnoreCase("NA") && !sorting.contains("Date")
						|| headerPresent.equalsIgnoreCase("") && !sorting.contains("Date")) {
					List<WebElement> headerTable = PortletDriver
							.findElements(By.xpath("//div[@class='group-table-header']"));
					for (int l = 1; l <= headerTable.size(); l++) {
						List<WebElement> columnName = PortletDriver
								.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[1]/div"));

						for (int m = 1; m <= columnName.size(); m++) {
							String columnNameString = PortletDriver
									.findElement(
											By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[1]/div[" + m + "]"))
									.getText();

							if (sorting.contains(columnNameString) && columnNameString.contains("Date")) {
								sorting = "Date";
							}

							if (sorting.contains(columnNameString) || sorting.equalsIgnoreCase(columnNameString)) {

								int cnt = 0;

								List<WebElement> titleHorizontalEntry = PortletDriver.findElements(By
										.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[2]/div/div[" + m + "]"));
								for (int i = 1; i <= titleHorizontalEntry.size(); i++) {

									WebElement titleFromTable = PortletDriver.findElement(
											By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[2]/div[" + i
													+ "]/div[" + m + "]"));
									String titleFromTableString = titleFromTable.getText();
									allTitleIndexPage.add(titleFromTableString);

								}

								for (int i = 0; i < allDataComparison.size(); i++) {
									for (int j = 0; j < allTitleIndexPage.size(); j++) {

										String tableValue = allTitleIndexPage.get(j).toString();

										if (allDataComparison.get(i).toString().equalsIgnoreCase(tableValue)) {
											cnt++;
											if (cnt == allTitleIndexPage.size()) {
												titleContains = true;
												break;
											}
										}
									}
								}
								System.out.println(allTitleIndexPage);
								// *****************************************************************

								// **************************************************************
								ArrayList<String> sortedList = new ArrayList<>();
								for (String s : allTitleIndexPage) {
									sortedList.add(s);
								}
								System.out.println(">>>>>>>>>>>>>>>>." + allTitleIndexPage.equals(sortedList));
								Collections.sort(sortedList);

								// If order ascending then flag is true and for descending it is false.
								boolean alphaOrderFlag = allTitleIndexPage.equals(sortedList);

								flagHeader = true;
								if (ascendingDescending.equalsIgnoreCase("Ascending") && titleContains) {

									if ((YearGrouping.equalsIgnoreCase("No") && alphaOrderFlag == true)
											|| (YearGrouping.equalsIgnoreCase("No") && alphaOrderFlag == true)
											|| (YearGrouping.equalsIgnoreCase("No") && areDateSorted == true)) {
										count++;

										if (count == 1) {
											flag = "Pass";
											result = "Sorting done on Column namely " + sorting + " "
													+ ascendingDescending + " is working as expected.";
										}

									} else {
										if (count == 0 || count == 1) {
											flag = "Fail";
											result = "Sorting done on Column namely " + sorting + " "
													+ ascendingDescending + " is not working as expected.";
										}
									}

								} else if (ascendingDescending.equalsIgnoreCase("Descending") && titleContains)

								{
									Collections.sort(sortedList,
											Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
									alphaOrderFlag = allTitleIndexPage.equals(sortedList);
									if ((YearGrouping.equalsIgnoreCase("No") && alphaOrderFlag == true)
											|| (YearGrouping.equalsIgnoreCase("No") && alphaOrderFlag == true)
											|| (YearGrouping.equalsIgnoreCase("No") && (alphaOrderFlag == true))) {
										count++;
										// check
										if (count == 1) {
											flag = "Pass";
											result = "Sorting done on Column namely " + sorting + " "
													+ ascendingDescending + " is working as expected.";
										}
									} else {

										if (count == 0 || count == 1) {
											flag = "Fail";
											result = "Sorting done on Column namely " + sorting + " "
													+ ascendingDescending + " is not working as expected.";
										}
									}

								} else {
									if (count == 0 || count == 1) {
										flag = "Fail";
										result = "Sorting done on Column namely " + sorting + " " + ascendingDescending
												+ " is not working as expected.";
									}
								}
								allTitleIndexPage.removeAll(allTitleIndexPage);

								break;

							}

						}

					}
				}

				// Condition 2 *******When Header is not present and sorting done on
				// date.*****************
				else if ((headerPresent.equalsIgnoreCase("NA") && sorting.contains("Date"))
						|| (headerPresent.equalsIgnoreCase("") && sorting.contains("Date"))) {

					List<WebElement> columnName = PortletDriver
							.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[1]/div"));

					for (int m = 1; m <= columnName.size(); m++) {
						String columnNameString = PortletDriver
								.findElement(
										By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[1]/div[" + m + "]"))
								.getText();

						if (sorting.contains(columnNameString) || sorting.equalsIgnoreCase(columnNameString)) {
							// This is for Issue date.

							List<WebElement> issueDate = PortletDriver.findElements(
									By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[2]/div/div[" + m + "]"));
							for (int i = 1; i <= issueDate.size(); i++) {

								List<WebElement> issueDateFromTable = PortletDriver.findElements(By
										.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[2]/div/div[" + m + "]"));
								String sDate1 = PortletDriver
										.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[2]/div["
												+ i + "]/div[" + m + "]"))
										.getText();
								// String sDate1= issueDateFromTable.get(i).getText();

								SimpleDateFormat dateFormatDt = new SimpleDateFormat("yyyy-MM-dd");
								if (!sDate1.equalsIgnoreCase("")) {
									Date dateDt = dateFormatDt.parse(sDate1);

									allDatesIndexPage.add(dateDt);
									allDatesString.add(sDate1);
									String[] fieldsDt = sDate1.split("[ \\-]");
									yearOnly = fieldsDt[0];
									System.out.println(fieldsDt[0]);

								}
							}

							List<Date> allDatesSorted = new ArrayList<>(allDatesIndexPage);
							if (ascendingDescending.equalsIgnoreCase("Descending")
									&& allDatesString.containsAll(allDates)
									|| ascendingDescending.equalsIgnoreCase("Descending")
											&& allDates.containsAll(allDatesString)) {

								Collections.sort(allDatesSorted, new DateFactory.DescendingDateComparator());
								for (int index = 0; index < allDatesIndexPage.size(); index++) {
									Date date1 = allDatesIndexPage.get(index);
									Date date2 = allDatesSorted.get(index);
									if (!date1.equals(date2)) {
										areDateSorted = false;
										break;
									}
								}
								System.out.println(allYear);
							} else {

								Collections.sort(allDatesSorted, new DateFactory.AscendingDateComparator());

								for (int index = 0; index < allDatesIndexPage.size(); index++) {
									Date date1 = allDatesIndexPage.get(index);
									Date date2 = allDatesSorted.get(index);

									if (!date1.equals(date2)) {
										areDateSorted = false;
										break;
									}

								}
							}
							if (areDateSorted) {

								flag = "Pass";
								result = "Date Sorting and Year Grouping is working as expected and Sorting done on Column namely "
										+ sorting + " " + ascendingDescending + " .";

							} else {

								flag = "Fail";
								result = "Date Sorting and Year Grouping is not working as expected and Sorting done on Column namely "
										+ sorting + " " + ascendingDescending + ".";

							}

						}
					}
				}
				// Condition 3
				else {

					// This is to add only year

					Set<String> allYearExcelSet = new HashSet<String>();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					for (int b = 0; b < allDates.size(); b++) {
						Date date = dateFormat.parse(allDates.get(b));
						String[] fields = allDates.get(b).split("[ \\-]");
						yearOnly = fields[0];
						allYearExcelSet.add(yearOnly);
					}
					List<String> allYearExcel = new ArrayList<String>(allYearExcelSet);
					// This is Header Matching
					List<WebElement> headerMatch = PortletDriver
							.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div/h3"));
					for (int h = 1; h <= headerMatch.size(); h++) {
						String headerMatchString = PortletDriver
								.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h + "]/h3"))
								.getText();
						flagHeader = false;
						for (int b = 0; b < allYearExcel.size(); b++) {
							if (headerMatchString.equalsIgnoreCase(allYearExcel.get(b))) {
								// This is for ColumnName.
								List<WebElement> columnNameUpper = PortletDriver.findElements(By
										.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h + "]/div[1]/div"));

								for (int x = 1; x <= columnNameUpper.size(); x++) {
									String columnNameStringUpper = PortletDriver
											.findElement(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h
													+ "]/div[1]/div[" + x + "]"))
											.getText();

									headerflag = true;
									if (sorting.contains(columnNameStringUpper)
											&& columnNameStringUpper.contains("Date")) {
										sorting = "Date";
									}

									if (sorting.contains(columnNameStringUpper)
											&& !(columnNameStringUpper.contains("Date"))) {

										List<WebElement> titleFromTable = null;
										int cnt = 0;

										List<WebElement> titleBody = PortletDriver
												.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div["
														+ h + "]/div[2]/div/div[" + x + "]"));
										for (int j = 1; j <= titleBody.size(); j++) {
											WebElement titleFromWebTable = PortletDriver.findElement(
													By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h
															+ "]/div[2]/div[" + j + "]/div[" + x + "]"));

											allTitleIndexPage.add(titleFromWebTable.getText());
										}

										for (int u = 0; u < allDataComparison.size(); u++) {
											for (int j = 0; j < allTitleIndexPage.size(); j++) {
												String tableValue = allTitleIndexPage.get(j).toString();

												if (allDataComparison.get(u).toString().equalsIgnoreCase(tableValue)) {
													cnt++;
													if (cnt == allTitleIndexPage.size()) {
														titleContains = true;
													}
												}
											}
										}
										System.out.println(allTitleIndexPage);
										ArrayList<String> sortedList = new ArrayList<>();
										for (String s : allTitleIndexPage) {
											sortedList.add(s);
										}
										System.out.println(">>>>>>>>>>>>>>>>." + allTitleIndexPage.equals(sortedList));
										Collections.sort(sortedList);

										// If order ascending then flag is true and for descending it is false
										boolean alphaOrderFlag = allTitleIndexPage.equals(sortedList);
										flagHeader = true;
										if (ascendingDescending.equalsIgnoreCase("Ascending") && titleContains) {

											if ((YearGrouping.equalsIgnoreCase("Yes") && alphaOrderFlag == true
													&& allYear.size() == 1)
													|| (YearGrouping.equalsIgnoreCase("Yes") && alphaOrderFlag == true
															&& allYear.size() == 0)
													|| (YearGrouping.equalsIgnoreCase("No")
															&& alphaOrderFlag == true)) {

												if (h == headerMatch.size()) {
													flag = "Pass";
													result = "Sorting done on Column namely " + sorting + " "
															+ ascendingDescending + " is working as expected.";
												}

											} else {

												flag = "Fail";
												result = "Sorting done on Column namely " + sorting + " "
														+ ascendingDescending + " is not working as expected.";
												break;
											}

										} else if (ascendingDescending.equalsIgnoreCase("Descending") && titleContains)

										{
											Collections.sort(sortedList,
													Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
											alphaOrderFlag = allTitleIndexPage.equals(sortedList);
											if ((YearGrouping.equalsIgnoreCase("Yes") && alphaOrderFlag == false
													&& allYear.size() == 1)
													|| (YearGrouping.equalsIgnoreCase("Yes") && alphaOrderFlag == false
															&& allYear.size() == 0)
													|| (YearGrouping.equalsIgnoreCase("No")
															&& (alphaOrderFlag == false && allYear.size() == 1))
													|| (YearGrouping.equalsIgnoreCase("No") && alphaOrderFlag == true
															&& allYear.size() == 0)) {

												if (h == headerMatch.size()) {
													flag = "Pass";
													result = "Sorting done on Column namely " + sorting + " "
															+ ascendingDescending + " is working as expected..";
												}
											} else {
												flag = "Fail";
												result = "Sorting done on Column namely " + sorting + " "
														+ ascendingDescending + " is not working as expected..";

											}

										} else {

											flag = "Fail";
											result = "Sorting done on Column namely " + sorting + " "
													+ ascendingDescending + " is not working as expected..";

										}
										allTitleIndexPage.removeAll(allTitleIndexPage);

									} else if (sorting.contains(columnNameStringUpper)
											&& (columnNameStringUpper.contains("Date"))) {
										// This is for Issue date.

										List<WebElement> issueDate = PortletDriver
												.findElements(By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div["
														+ h + "]/div[2]/div/div[" + x + "]"));
										for (int i = 0; i < issueDate.size(); i++) {

											List<WebElement> issueDateFromTable = PortletDriver.findElements(
													By.xpath(".//*[@id='indexPageContainer']/div/div[2]/div[" + h
															+ "]/div[2]/div/div[" + x + "]"));
											String sDate1 = issueDateFromTable.get(i).getText();
											SimpleDateFormat dateFormatDt = new SimpleDateFormat("yyyy-MM-dd");
											Date dateDt = dateFormatDt.parse(sDate1);
											allDatesIndexPage.add(dateDt);
											allDatesString.add(sDate1);
											String[] fieldsDt = sDate1.split("[ \\-]");
											yearOnly = fieldsDt[0];
											System.out.println(fieldsDt[0]);
											allYear.add(yearOnly);
										}

										List<Date> allDatesSorted = new ArrayList<>(allDatesIndexPage);

										// Sorting
										if (ascendingDescending.equalsIgnoreCase("Descending")
												&& allDatesString.containsAll(allDates)
												|| ascendingDescending.equalsIgnoreCase("Descending")
														&& allDates.containsAll(allDatesString)) {
											Collections.sort(allDatesSorted,
													new DateFactory.DescendingDateComparator());

											System.out.println(allYear);
										} else {

											Collections.sort(allDatesSorted, new DateFactory.AscendingDateComparator());

										}
										for (int index = 0; index < allDatesIndexPage.size(); index++) {
											Date date1 = allDatesIndexPage.get(index);
											Date date2 = allDatesSorted.get(index);
											if (!date1.equals(date2)) {
												areDateSorted = false;
												break;
											}
										}
										if ((YearGrouping.equalsIgnoreCase("Yes") && areDateSorted
												&& allYear.size() == 1)
												|| (YearGrouping.equalsIgnoreCase("Yes") && areDateSorted
														&& allYear.size() == 0)
												|| (YearGrouping.equalsIgnoreCase("No") && areDateSorted)) {

											if (h == headerMatch.size()) {
												flag = "Pass";
												result = "Date Sorting and Year Grouping is working as expected and Sorting done on Column namely "
														+ sorting + " " + ascendingDescending + " .";
											}

										} else {

											flag = "Fail";
											result = "Date Sorting and Year Grouping is not working as expected and Sorting done on Column namely "
													+ sorting + " " + ascendingDescending + ".";

										}

										allDatesString.removeAll(allDatesString);
										allYear.removeAll(allYear);
									}
								}

							}
							if (flagHeader) {
								break;
							}
						}

					} // End of Header
				}

			}

			ReportFactory.reporterOutput(wcmTestCaseID,
					"Navigate to an index page on DealerPath portal created with Matrix columns presentation template from links portlet and verify sorting and Grouping",
					BaseClass.PORTLET_LINKFLAG,
					"Matrix should be displayed on the 3rd level parent index page in and sorting and grouping should work.",
					result, flag);

		} catch (Exception e) {

			ReportFactory.reporterOutput(wcmTestCaseID,
					"Navigate to an index page on DealerPath portal created with Matrix columns presentation template from links portlet ",
					BaseClass.PORTLET_LINKFLAG,
					"Matrix should be displayed on the 3rd level parent index page in and sorting and grouping should work.",
					e.getMessage().toString().substring(0, 125), "Fail");

		}

	}

	/**
	 * @author Archana Gaikwad From links portlet of any department if the same
	 *         department is unchecked & saved from 'My Preferences' for any user,
	 *         the system should redirect to the homepage.
	 * @param param1
	 *            ChangeOfPrefDept(strTCID,strDepartmentName);
	 * @return Department check and uncheck @ exception try and Catch @ throws
	 * Throwable
	 **/
	@SuppressWarnings("unused")
	public static void ChangeOfPrefDept(String strTCID, String departmentName) throws Throwable {
		String flag = "Fail";
		String result = "User is not on home Page once we uncheck department and click on Save Button.";
		WebElement department = GenericFactory.getDeptname(departmentName);
		boolean linkfolderFlag = false;
		if (department != null) {
			department.click();
		} else

		{
			GenericFactory.toClickonDept(departmentName);
		}

		GenericFactory.checkandUncheckFromPrefernce();

		if (BaseClass.wbDriver.findElement(By.xpath(".//*[@id='favorites-filter']")).isDisplayed()) {
			LogFactory.info("User is on homepage");
			result = "User is on home Page once we uncheck department and click on Save Button.";
			flag = "Pass";
		}

		ReportFactory.reporterOutput(strTCID, "Verify Links portlet on changing of preferred departments.",
				"Unchecked Department Name : " + departmentName,
				"From links portlet of any department if the same department is unchecked & saved from 'My Preferences' for any user, the system should redirect to the homepage.",
				result, flag);

		GenericFactory.checkDepartmentMyPreference();
		Thread.sleep(3000);
		BaseClass.wbDriver.findElement(By.xpath("//*[@id='preference-save']")).click();
		Thread.sleep(5000);

	}

	@SuppressWarnings("unused")
	public static void ChangeOfPrefDeptOnIndex(String wcmTestCaseID, String departmentName) throws Throwable {
		String flag = "Fail";
		String result = "Navigated to a index/Child/GrandChild Index Page and department uncheck functionality is not working as expected.";

		GenericFactory.checkUncheckDepartmentMyPreferenceIndex(departmentName);
		Thread.sleep(3000);

		Thread.sleep(5000);

		if (BaseClass.wbDriver.findElement(By.xpath(".//*[@id='favorites-filter']")).isDisplayed()) {
			LogFactory.info("User is on homepage");
			result = " Navigated to a index/Child/GrandChild Index Page and department uncheck functionality is working as expected.";
			flag = "Pass";

			ReportFactory.reporterOutput(wcmTestCaseID,
					" Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality.",
					BaseClass.PORTLET_LINKFLAG, "User Should be on Home Page", result, flag);

		} else {
			ReportFactory.reporterOutput(wcmTestCaseID,
					" Navigate to a index/Child/GrandChild Index Page and verify department uncheck functionality.",
					BaseClass.PORTLET_LINKFLAG, "User Should be on Home Page", result, flag);
		}
		GenericFactory.checkDepartmentMyPreference();

		Thread.sleep(3000);
		BaseClass.wbDriver.findElement(By.xpath("//*[@id='preference-save']")).click();
		Thread.sleep(5000);

	}

	/**
	 * @author Archana Gaikwad Verify navigating to different type of links/Document
	 *         Content/Rich Text content from links portlet.
	 * @param param1
	 *            portletLinks(wcmTestCaseID,strUserDefinedCountry, strWCMCountry,
	 *            strUserDefinedProducts, strWCMProducts,strContenttype,
	 *            strDepartmentName, strSubDepartmentName,
	 *            strTitle,strThirdLevelFolder,strDescription,strRACFGroups,strdealerType);
	 * @return Verify Content type whether it is
	 *         'AT-Link','AT-Document','AT-Rich-Text' @ exception try and Catch @
	 * throws Throwable
	 **/
	@SuppressWarnings("unused")
	public static void portletLinks(String strWCMTCID, String userDefinedCountry, String wcmCountry,
			String userDefinedProducts, String wcmProducts, String contentType, String departmentName,
			String secondLevel, String title, String ThirdLevelFolder, String strDescription, String strRACFGroups,
			String strdealerType) throws Throwable {

		String flagPT = "";

		boolean booCountry = GenericFactory.countryListComparison(userDefinedCountry, wcmCountry);
		boolean booProduct = GenericFactory.productlistcomparer(userDefinedProducts, wcmProducts);
		List<String> actualDataListOriginal = BaseClass.analyzerUserMap.get("Security Groups");
		boolean booTitleFlag = false;
		boolean sectionFlag = false;
		boolean correctLinkFolder = false;
		int invalidImageCount = 0;
		String linkExtentionType = "NA";

		String testCaseDescription = " Verify link portlet for content type : " + contentType;
		String inputValue = " <B >WCM Maping :</B> Country : " + wcmCountry + " ,Product Type : " + wcmProducts
				+ " ,Dealer Type : " + BaseClass.flagDealerType + " ,Racf Group : " + strRACFGroups + " ,Dept : "
				+ departmentName + " ,Sub Category :  " + secondLevel + "<P>" + " <B>Dealer Maping :</B> Country : "
				+ userDefinedCountry + " ,Product Type : " + userDefinedProducts + " ,Dealer Type : " + strdealerType
				+ " ,Racf Group : " + actualDataListOriginal + "<B> and Title is  </B>" + title;

		try {
			WebElement department = GenericFactory.getDeptname(departmentName);

			if (department != null) {
				department.click();
			} else {
				GenericFactory.toClickonDept(departmentName);
			}

			if (!ThirdLevelFolder.equalsIgnoreCase("NA")) {

				PortletLinksPage_POF.filterbox.clear();
				PortletLinksPage_POF.filterbox.sendKeys(ThirdLevelFolder.trim());
				PortletLinksPage_POF.filterbox.sendKeys(Keys.ENTER);

				GenericFactory.toClickOnFolder(ThirdLevelFolder, secondLevel);

				correctLinkFolder = GenericFactory.linkFolderCorrect(ThirdLevelFolder, title);

			} else {
				correctLinkFolder = true;
			}

			filterbox.clear();
			filterbox.sendKeys(title.trim());
			filterbox.sendKeys(Keys.ENTER);

			// Code Added for RacfGroup
			boolean racfGroup = GenericFactory.verifyRacfGroupMatched(strRACFGroups);
			boolean DealerType = GenericFactory.verifyDealerType(strdealerType);
			// Section Block for ****************** R4/NONJDIN.

			if (!noResultfound.isDisplayed() && correctLinkFolder == true) {

				List<WebElement> actualHeaderName = PortletDriver
						.findElements(By.xpath(".//*[@id='links-target']/div"));

				for (int j = 0; j < actualHeaderName.size(); j++) {
					String tempValue = actualHeaderName.get(j).getText().trim();
					String[] lines = tempValue.split("\n");
					String headerName = lines[0];

					WebElement secondLevelLink = actualHeaderName.get(j);

					if (secondLevel.equals(headerName)) {
						sectionFlag = true;
						List<WebElement> links = secondLevelLink.findElements(By.tagName("a"));

						for (WebElement linkValues : links) {

							if (linkValues.getText().equals(title)) {
								booTitleFlag = true;

								if (booCountry == true && booProduct == true && booTitleFlag == true) {

									if (racfGroup && DealerType) {

										String strLink = linkValues.getAttribute("href").toString().trim();

										switch (contentType) {

										case "AT-Link":
											if (strLink != null) {
												int respCode = 200;
												HttpURLConnection huc = (HttpURLConnection) (new URL(strLink)
														.openConnection());
												huc.setRequestMethod("HEAD");
												huc.connect();
												respCode = huc.getResponseCode();

												if (respCode <= 400) {

													LogFactory.info("This is AT-Link.");
													ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
															inputValue, "Link : " + title + " should be availabe",
															linkValues.getText() + ", HTTP Respose Code : " + respCode,
															"Pass");
												} else {
													LogFactory.info("This is AT-Link.");
													ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
															inputValue, title,
															"It's a Broken link and response code is : " + respCode,
															"Fail");
												}
												break;
											}

											else {

												LogFactory.info("This is not AT-Link.");
												ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
														inputValue, title, linkValues.getText(), "Fail");

											}

											break;

										case "AT-Document":
											String linkCharacters = strLink.substring(strLink.lastIndexOf("/") + 1);
											String linkExtention = strLink.substring(strLink.lastIndexOf(".") + 1);
											linkExtentionType = linkExtention.substring(0, 4);

											if (linkExtentionType.contains("exe") || linkExtentionType.contains("doc")
													|| linkExtentionType.contains("pdf")
													|| linkExtentionType.contains("bmp")
													|| linkExtentionType.contains("jpg")
													|| linkExtentionType.contains("jpeg")
													|| linkExtentionType.contains("html")
													|| linkExtentionType.contains("png")) {
												LogFactory.info("This is AT-Document.");

												ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
														inputValue,
														"Content Type should be AT-Document : " + linkExtentionType,
														title + "\n" + strLink, "Pass");

											}

											else {
												LogFactory.info(
														"This AT-Document but extension is not found in the extension list ");

												ReportFactory
														.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
																"Content Type is AT-Document but:  " + linkExtentionType
																		+ " not found in the extension list ",
																strLink, "Fail");

											}
											break;

										case "AT-RichText":

											try {
												WebElement richtextLink = PortletDriver
														.findElement(By.xpath("//a[contains(.,'" + title + "')]"));

												richtextLink.click();

												if (richTextUI.isDisplayed()) {

													String description = richTextUI.getText();
													description = description.replaceAll("\\\\n", "");
													System.out.println(description);
													strDescription = strDescription.replaceAll("\\\\n", "");
													System.out.println(strDescription);

													WebElement imgElement = PortletDriver.findElement(By.xpath(
															".//*[@id='layoutContainers']/div/div[2]/div[3]/div[2]/section/div/div[2]/div/p[2]/img"));

													if (imgElement != null) {
														GenericFactory.verifyimageActive(imgElement);
													}

													System.out.println(
															"Total no. of invalid images are " + invalidImageCount);

													if (description.equalsIgnoreCase(strDescription.trim())
															&& invalidImageCount == 0) {

														LogFactory.info("This is AT-RichText.");
														ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
																inputValue,
																"Content Type should be AT-RichText : " + title,
																"Content Type is Rich Text.", "Pass");
														PortletDriver.navigate().back();

														break;

													}
												}

												else {
													LogFactory.info("This  is not AT-RichText content");
													ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
															inputValue, "Content Type should be AT-RichText : " + title,
															"Content Type is not Rich Text", "Fail");
													PortletDriver.navigate().back();

												}

												break;
											} catch (Exception e) {

											}
										case "Index page":
											LogFactory.info("Index page");
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													title, title + strLink, "Pass");
											break;

										case "AT-GrandChild Index Page":
											LogFactory.info("Index page");
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													title, title + strLink, "Pass");
											break;

										default:
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													title, "Invalid Content Type " + contentType, "Pass");
										}
									} else {
										if (racfGroup == false) {
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													"Link should not be available in the department : " + departmentName
															+ " and section " + secondLevel,
													"User Racf Group  are not matching with WCM Racf Group and Link : "
															+ title + " is available in section : " + secondLevel,
													"Fail");
										} else {
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													"Link should not be available in the department : " + departmentName
															+ " and section " + secondLevel,
													"User Dealer Type are not matching with WCM Dealer Type and Link : "
															+ title + " is available in section : " + secondLevel,
													"Fail");

										}

									}
								}
							}

						}

						if (booCountry == true && booProduct == true && booTitleFlag == false) {

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" : Link should be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"User Country & Product Types are matching with WCM Country & Product Types but Link : "
											+ title + " is not available in section : " + secondLevel,
									"Fail");

						} else if (booCountry == true && booProduct == false && booTitleFlag == true) {

							// LogFactory.info(title + " : Link is available in the department : " +
							// departmentName + " and section : " + secondLevel);

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" Link should NOT be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"User Product types are not matching with WCM Product types but Link : " + title
											+ " is available in section : " + secondLevel,
									"Fail");

						} else if (booCountry == false && booProduct == true && booTitleFlag == true) {

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" Link should NOT be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"User Country is not matching with WCM country but Link : " + title
											+ " is available in section : " + secondLevel,
									"Fail");

						} else if (booCountry == false && booProduct == false && booTitleFlag == false) {

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" Link should NOT be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"User Country & Product types are not matching with WCM Country & Product types so Link : "
											+ title + " is NOT available in section : " + secondLevel,
									"Pass");

						} else if (booCountry == true && booProduct == false && booTitleFlag == false) {

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" Link should NOT be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"Product types are not matching with WCM Product types so Link : " + title
											+ " is not available in section : " + secondLevel,
									"Pass");

						} else if (booCountry == false && booProduct == false && booTitleFlag == true) {

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" Link should NOT be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"User Country & Product types are NOT matching with WCM Country & Product types but Link : "
											+ title + " is available in section : " + secondLevel,
									"Fail");

						} else if (booCountry == false && booProduct == true && booTitleFlag == false) {

							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
									" Link should NOT be available in the department : " + departmentName
											+ " and section " + secondLevel,
									"User Country is not matching with WCM country so Link : " + title
											+ " is NOT available in section : " + secondLevel,
									"Pass");

						}

					}
					// Section Block

				}
				if (sectionFlag != true) {
					LogFactory.info(secondLevel + " : section is not available in the department : " + departmentName);
					ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
							secondLevel + " : section should be available in the department : " + departmentName,
							"Section " + secondLevel + " is not available in " + departmentName, "Fail");
				}

			}

			else {

				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, "Title should be Present.",
						"Title is not Present.", "Fail");

			}

		} catch (Exception e) {

			ReportFactory.reporterOutput("Portlet Links", testCaseDescription, inputValue, "NA", e.getMessage(),
					"Fail");

		}

	}

	@SuppressWarnings("unused")
	public static void portletLinks_old(String strWCMTCID, String userDefinedCountry, String wcmCountry,
			String userDefinedProducts, String wcmProducts, String contentType, String departmentName,
			String secondLevel, String title, String ThirdLevelFolder, String strDescription) throws Throwable {
		int count = 0;
		String testCaseDescription = " Verify link portlet for content type : " + contentType;
		String inputValue = " <B>WCM Maping :</B> Country : " + wcmCountry + " ,Product Type : " + wcmProducts
				+ " ,Dept : " + departmentName + " ,Sub Category :  " + secondLevel + "<P>"
				+ " <B>Dealer Maping :</B> Country : " + userDefinedCountry + " ,Product Type : " + userDefinedProducts;
		boolean correctLinkFolder = true;
		Set<String> set = new HashSet<String>();
		boolean flag = false;
		WebElement department = GenericFactory.getDeptname(departmentName);
		boolean linkfolderFlag = false;
		if (department != null) {
			department.click();
		} else

		{
			GenericFactory.toClickonDept(departmentName);
		}
		try {

			if (!ThirdLevelFolder.equalsIgnoreCase("NA")) {

				PortletLinksPage_POF.filterbox.clear();
				PortletLinksPage_POF.filterbox.sendKeys(ThirdLevelFolder.trim());
				PortletLinksPage_POF.filterbox.sendKeys(Keys.ENTER);

				GenericFactory.toClickOnFolder(ThirdLevelFolder, secondLevel);

				correctLinkFolder = GenericFactory.linkFolderCorrect(ThirdLevelFolder, title);

			}

			filterbox.clear();
			filterbox.sendKeys(title.trim());
			filterbox.sendKeys(Keys.ENTER);

			if (!noResultfound.isDisplayed() && correctLinkFolder == true) {

				List<WebElement> actualHeaderName = PortletDriver
						.findElements(By.xpath(".//*[@id='links-target']/div"));

				for (int j = 0; j < actualHeaderName.size(); j++) {
					String tempValue = actualHeaderName.get(j).getText().trim();
					String[] lines = tempValue.split("\n");
					String headerName = lines[0];

					WebElement secondLevelLink = actualHeaderName.get(j);

					if (secondLevel.equals(headerName)) {

						List<WebElement> links = secondLevelLink.findElements(By.tagName("a"));

						for (WebElement linkValues : links) {
							set.add(linkValues.getText());
							if (linkValues.getText().equals(title)) {

								String strLink = linkValues.getAttribute("href").toString().trim();

								String linkCharacters = strLink.substring(strLink.lastIndexOf("/") + 1);

								String linkExtention = strLink.substring(strLink.lastIndexOf(".") + 1);

								String linkExtentionType = linkExtention.substring(0, 4);

								switch (contentType) {

								case "AT-Link":
									if (strLink != null) {
										int respCode = 200;
										HttpURLConnection huc = (HttpURLConnection) (new URL(strLink).openConnection());
										huc.setRequestMethod("HEAD");
										huc.connect();

										respCode = huc.getResponseCode();

										if (respCode <= 400) {

											LogFactory.info("This is AT-Link.");
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													"  Link : " + title + " should be available",
													linkValues.getText() + ", HTTP Respose Code : " + respCode, "Pass");
											flag = true;

										} else {
											LogFactory.info("This is AT-Link.");
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													title, "It's a Broken link and response code is : " + respCode,
													"Fail");
											flag = true;
										}
										break;
									}

									else {

										LogFactory.info("This is not AT-Link.");
										ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, title,
												linkValues.getText(), "Fail");
										flag = true;

									}

									break;
								case "AT-Document":
									if (linkExtentionType.contains("exe") || linkExtentionType.contains("doc")
											|| linkExtentionType.contains("pdf") || linkExtentionType.contains("bmp")
											|| linkExtentionType.contains("jpg") || linkExtentionType.contains("jpeg")
											|| linkExtentionType.contains("html")
											|| linkExtentionType.contains("png")) {
										LogFactory.info("This is AT-Document.");

										ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
												"Content Type should be AT-Document : " + linkExtentionType,
												title + "\n" + strLink, "Pass");
										flag = true;

									}

									else {
										LogFactory.info(
												"This AT-Document but extension is not found in the extension list ");

										ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
												"Content Type is AT-Document but:  " + linkExtentionType
														+ " not found in the extension list ",
												strLink, "Fail");
										flag = true;

									}
									break;

								case "AT-RichText":

									try {
										WebElement richtextLink = PortletDriver
												.findElement(By.xpath("//a[contains(.,'" + title + "')]"));

										richtextLink.click();

										if (richTextUI.isDisplayed()) {

											String description = richTextUI.getText();
											description = description.replaceAll("\\\\n", "");
											System.out.println(description);
											strDescription = strDescription.replaceAll("\\\\n", "");
											System.out.println(strDescription);

											if (description.equalsIgnoreCase(strDescription.trim())) {

												LogFactory.info("This is AT-RichText.");
												ReportFactory.reporterOutput(strWCMTCID, testCaseDescription,
														inputValue, "Content Type should be AT-RichText : " + title,
														"Content Type is Rich Text", "Pass");
												PortletDriver.navigate().back();
												flag = true;
												break;

											}
										}

										else {
											LogFactory.info("This  is not AT-RichText content");
											ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue,
													"Content Type should be AT-RichText : " + title,
													"Content Type is not Rich Text", "Fail");
											PortletDriver.navigate().back();
											flag = true;
										}

										break;
									} catch (Exception e) {

									}
								case "Index page":
									LogFactory.info("Index page");
									ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, title,
											title + strLink, "Pass");
									break;

								case "AT-GrandChild Index Page":
									LogFactory.info("Index page");
									ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, title,
											title + strLink, "Pass");
									break;

								default:
									ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue, title,
											"Invalid Content Type " + contentType, "Pass");
								}

							}

						}
						if (flag) {
							break;
						}
					} else if (!set.contains(title)) {
						count++;
						if (count == 1) {
							ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue + title,
									"Title should be Present.", "Title is not Present as criteria does match.", "Fail");
							break;
						}

					}

				}

			}

			else {

				ReportFactory.reporterOutput(strWCMTCID, testCaseDescription, inputValue + title,
						"Title should be Present.", "Title is not Present.", "Fail");

			}

			if (!ThirdLevelFolder.equalsIgnoreCase("NA")) {
				PortletLinksPage_POF.filterbox.clear();
				PortletLinksPage_POF.filterbox.sendKeys(ThirdLevelFolder.trim());
				PortletLinksPage_POF.filterbox.sendKeys(Keys.ENTER);
				GenericFactory.toClickOnFolder(ThirdLevelFolder, secondLevel);
			}
			filterbox.clear();
			filterbox.sendKeys(Keys.ENTER);
		} catch (Exception e) {

			ReportFactory.reporterOutput("Portlet Links", testCaseDescription, inputValue, "NA", e.getMessage(),
					"Fail");

		}
	}

	/**
	 * @author Archana Gaikwad Verify expanding and collapsing of folders on Link
	 *         portlet.
	 * @param param1
	 *            verifyExpandCollapseFolder(strTCID,strDepartmentName,SubDepartmentName,strFolderName);
	 * @return ExpandCollapseFolder @ exception try and Catch @ throws Throwable
	 **/

	@SuppressWarnings("unused")
	public static void verifyExpandCollapseFolder(String strTCID, String DepartmentName, String SubDepartmentName,
			String folderName) throws Throwable {
		String getFoldername = null;
		try {
			LogFactory.info("Verify Department Name");
			String result = null;
			String status = null;

			List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
			DepartmentName = translatedText.get(0);

			WebElement department = GenericFactory.getDeptname(DepartmentName);
			if (department != null) {
				department.click();
				PortletLinksPage_POF.filterbox.clear();
				PortletLinksPage_POF.filterbox.sendKeys(folderName);
				PortletLinksPage_POF.filterbox.sendKeys(Keys.ENTER);

				boolean folderStatus = GenericFactory.toClickOnFolder(folderName, SubDepartmentName);

				if (folderStatus == true && GenericFactory.linkspresent(folderName)) {
					result = "Bydefault Folder is in collapsed mode and onclick of it gets expanded and Links are present under folder.";
					status = "Pass";
				} else {
					result = "Folder/Links are not present and/or expand collpase is not working as expected.";
					status = "Fail";
				}

				GenericFactory.toClickOnFolder(folderName, SubDepartmentName);

			} else {

				result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
				status = "Pass";
			}
			ReportFactory.reporterOutput(strTCID, "Verify expanding and collapsing of folders on Link portlet.",
					folderName,
					"By default folders on the links portlet should be in collapsed mode and Clicking on collapsed folder expanding the folder showing links under it.",
					result, status);

		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Verify expanding and collapsing of folders on Link portlet.",
					folderName,
					"By default folders on the links portlet should be in collapsed mode and Clicking on collapsed folder expanding the folder showing links under it.",
					e.getMessage().toString().substring(0, 250), "Fail");
		}

	}

	@SuppressWarnings("unused")
	public static boolean linkonpresentFolder(String folderName, String Title) throws InterruptedException {
		String linksavailable = null;
		boolean status = false;
		List<WebElement> actualHeadernameP = PortletDriver.findElements(By.xpath(".//*[@id='links-target']/div"));
		// ArrayList<String> valueslinks = new ArrayList<String>();
		for (int s = 0; s < actualHeadernameP.size(); s++) {
			String headerNamePortlet = actualHeadernameP.get(s).getText().trim();
			String[] linesdata = headerNamePortlet.split("\n");
			WebElement secondLevel = actualHeadernameP.get(s);

			List<WebElement> links = secondLevel.findElements(By.tagName("a"));

			for (WebElement we : links) {

				we.getText();
				Thread.sleep(1000);
				if (!we.getText().toString().isEmpty()) {
					String result = "Links are present under folder";
					status = true;
					break;

				} else {
					String result = "Links are not present under folder";
					status = false;

				}
			}
		}
		return status;
	}

	/**
	 * @author Archana Gaikwad Verify The expanded state of the folder for the user
	 *         from Links portlet should persist for the session.
	 * @param param1
	 *            verifySessionforExpandFolder(strTCID,strDepartmentName,SubDepartmentName,strFolderName,strTitle);
	 * @return verifysessionExpandFolder @ exception try and Catch @ throws
	 * Throwable
	 **/

	@SuppressWarnings("unused")
	public static void verifySessionforExpandFolder(String strTCID, String DepartmentName, String SubDepartmentName,
			String folderName, String strTitle) throws Throwable {
		String getFoldername = null;
		String status = "Fail";
		String result = null;
		try {

			LogFactory.info("Verify Department Name");
			List<String> translatedText = GenericFactory.getTranslation(DepartmentName);
			DepartmentName = translatedText.get(0);
			WebElement department = GenericFactory.getDeptname(DepartmentName);

			if (department != null) {
				department.click();

				PortletLinksPage_POF.filterbox.clear();
				PortletLinksPage_POF.filterbox.sendKeys(folderName);
				PortletLinksPage_POF.filterbox.sendKeys(Keys.ENTER);

				boolean folderStatus = GenericFactory.toClickOnFolder(folderName, SubDepartmentName);
				boolean linksavailable = PortletLinksPage_POF.linkonpresentFolder(folderName, strTitle);

				GenericFactory.toClickonDept(DepartmentName);

				PortletLinksPage_POF.filterbox.clear();
				PortletLinksPage_POF.filterbox.sendKeys(folderName);
				PortletLinksPage_POF.filterbox.sendKeys(Keys.ENTER);
				boolean linkssession = PortletLinksPage_POF.linkonpresentFolder(folderName, strTitle);

				if (linkssession == linksavailable && linkssession != false) {
					status = "Pass";
					result = "The expanded state of the folder for the user from Links portlet persists for the session";
				} else {
					status = "Fail";
					result = "The expanded state of the folder for the user is not working  and/ or Folder/Links are not present.";
				}

			}

			else {

				result = "As Department is Inactive/NonClickable/NotVisible so We can not perform.";
				status = "Pass";
			}
			ReportFactory.reporterOutput(strTCID,
					"Verify The expanded state of the folder for the user from Links portlet should persist for the session.",
					folderName,
					"The expanded state of the folder for the user from Links portlet should persist for the session.",
					result, status);
		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID,
					"Verify The expanded state of the folder for the user from Links portlet should persist for the session.",
					folderName,
					"The expanded state of the folder for the user from Links portlet should persist for the session.",
					e.getMessage().toString().substring(0, 250), "Fail");
		}
		GenericFactory.toClickOnFolder(folderName, SubDepartmentName);
	}

	/**
	 * @author Archana Gaikwad Navigate to a 3rd parent level Landing Page from
	 *         links portlet and verify.
	 * @param param1
	 *            portletLandingPage(userWCMContent.get(i),strContenttype);
	 * @return Changing Produt Prefernce @ exception try and Catch @ throws
	 * Throwable
	 **/

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void portletLandingPage(LinkedHashMap userWcmContent, String strContenttype) throws Throwable

	{
		String flag = "Fail";
		String result = null;
		String wcmTestCaseID = userWcmContent.get("Test Case ID").toString().trim();
		String strSubDepartmentName = userWcmContent.get("2ndLevel").toString().trim();
		String strThirdLevelLandingPage = userWcmContent.get("3rdLevelLandingPage").toString().trim();
		String strThirdLevelFolder = userWcmContent.get("3rdLevelFolder").toString().trim();
		String strForthLevelLandingPage = userWcmContent.get("4thLevelLandingPage").toString().trim();
		String strTitle = userWcmContent.get("Title").toString().trim();
		String strDescription = userWcmContent.get("Description").toString().trim();
		String strIndexPageContentType = userWcmContent.get("IndexPageContentType").toString().trim();
		String strDescriptionRichText = userWcmContent.get("Description_RichText").toString().trim();
		String richTextContent = null;
		boolean filterbox = false;
		String descriptionData = null;
		WebElement descriptionPage = null;
		boolean compareDescription = false;
		boolean flagRichText = false;
		boolean checkFlag = false;
		int invalidImageCount = 0;

		try {

			try {
				filterbox = departmentLinksfilterbox.isDisplayed();
			} catch (Exception e) {

			}

			if (filterbox == true && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))) {
				String Expectedresult = "Verify Navigate to a 3rd parent level Landing Page from links portlet.";
				flag = "Pass";
				result = "Landing Page Testing can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {

				String result1;
				ArrayList<String> Linklist = Homepage_POF.getLeftWindowLinks();

				for (int s = 0; s < Linklist.size(); s++) {
					result1 = String.join(",", Linklist);
					checkFlag = true;
					if (!strDescription.equalsIgnoreCase("NA")) {
						try {
							descriptionPage = PortletDriver
									.findElement(By.xpath("//em[contains(.,'" + strDescription + "')]"));
						} catch (Exception e) {
							descriptionPage = null;
						}

						if (descriptionPage == null) {
							descriptionData = "NA";
							result = "Description is not Present on specified Landing Page.";
							flag = "Fail";
						} else {
							descriptionData = descriptionPage.getText();

						}
						compareDescription = strDescription.equalsIgnoreCase(descriptionData);

					}
					if (strIndexPageContentType.equalsIgnoreCase("AT-Rich Text")) {
						PortletDriver.findElement(By.linkText(strTitle)).click();
						try {

							richTextContent = PortletDriver
									.findElement(By.xpath("//p[contains(.,'" + strDescriptionRichText + "')]"))
									.getText();
						} catch (Exception e) {

						}
						if (richTextContent.equalsIgnoreCase(strDescriptionRichText)) {
							flagRichText = true;
						}

						WebElement imgElement = PortletDriver.findElement(By.xpath("//img[@border='0']"));

						if (imgElement != null) {
							GenericFactory.verifyimageActive(imgElement);
						}

						System.out.println("Total no. of invalid images are " + invalidImageCount);

					}

					if ((compareDescription == true && result1.contains(strTitle)
							&& strIndexPageContentType.equalsIgnoreCase("AT-Rich Text") && (flagRichText == true)
							&& invalidImageCount == 0)
							|| (result1.contains(strTitle) && strIndexPageContentType.equalsIgnoreCase("AT-Link"))) {

						result = "User is able to Navigate to a 3rd parent level Landing Page from links portlet and content and/Or Description are displayed.";
						flag = "Pass";
						break;
					}

					else {
						result = "User is not able to view displayed content from left navigation.";
						flag = "Fail";
					}
					if (checkFlag) {
						break;
					}
				}

			}
			ReportFactory.reporterOutput(wcmTestCaseID,
					"Verify Navigate to a 3rd parent level Landing Page from links portlet.", strTitle,
					"User should be able to Navigate to a 3rd parent level Landing Page from links portlet.", result,
					flag);
		}

		catch (Exception e) {

			ReportFactory.reporterOutput(wcmTestCaseID,
					"Verify Navigate to a 3rd parent level Landing Page from links portlet.", strTitle,
					"User should be able to Navigate to a 3rd parent level Landing Page from links portlet.",
					e.getMessage().toString().substring(0, 125), flag);
		}

	}

	/**
	 * @author Archana Gaikwad Verify Product Segment functionality.
	 * @param param1
	 *            matrix2xolumns(userWCMContent.get(i));
	 * @return productSegmentIndexPages(wcmTestCaseID,strTitle,strSubDepartmentName,strUserDefinedProducts,strWCMProducts,strThirdLevelFolder,strContenttype); @
	 * exception try and Catch @ throws Throwable
	 **/

	@SuppressWarnings("unused")
	public static void productSegmentIndexPages(String wcmTestCaseID, String Title, String strSubDepartmentName,
			String UserDefinedProducts, String WCMProducts, String strThirdLevelFolder, String strContenttype)
			throws Throwable {

		try {

			String result = null;
			String status = null;
			String Expectedresult = null;
			boolean statusResult = false;
			String parentItemUncheck = null;
			boolean flagItemnotfound = false;
			ArrayList<String> productWCMRemoved = new ArrayList<>();
			boolean filterbox = false;
			boolean filterboxHome = false;
			List<String> listOfElements = GenericFactory.getCheckBoxValuesAll();

			try {
				filterboxHome = homePageFilter.isDisplayed();

			}

			catch (Exception e) {
			}
			try {

				filterbox = departmentLinksfilterbox.isDisplayed();
			}

			catch (Exception e) {
			}

			if ((filterbox == true || filterboxHome == true) && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page")))

			{
				Expectedresult = "Search criteria should not be available in the list once we uncheck Product/Products from Product Segment";
				status = "Pass";
				result = "Product Segment Functionality can not be performed as Index/Child/Grand-Child Link is not Present.";
				ReportFactory.reporterOutput(wcmTestCaseID, "Verify Product Segment functionality on Index Pages.",
						"<B>ContentType :</B>" + strContenttype + "Link Name :" + Title, Expectedresult, result,
						status);

			} else {

				// List<String> listOfElements = GenericFactory.getCheckBoxValuesAll();

				GenericFactory.checkAllProductsData(listOfElements);
				Thread.sleep(500);
				PortletLinksPage_POF.applyFilter.click();

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
				hashsetWCMList.retainAll(listOfElements);

				System.out.println("hashsetWCMList=========" + hashsetWCMList);

				System.out.println("UserPrefernceList=========" + listOfElements);

				int k;
				for (k = 0; k < hashsetWCMList.size(); k++) {

					if (listOfElements.size() <= hashsetWCMList.size()) {

						if (listOfElements.size() <= hashsetWCMList.size() && listOfElements.size() > 1) {

							hashsetWCMList.remove(listOfElements.get(listOfElements.size() - 1));
							System.out.println("Data removed------>" + hashsetWCMList);

						}
					}

					Object[] myArr = hashsetWCMList.toArray();

					parentItemUncheck = myArr[k].toString();

					List<String> translatedText1 = GenericFactory.getTranslation(parentItemUncheck);
					parentItemUncheck = translatedText1.get(0);

					if ((listOfElements.size() > 1)) {
						WebElement productSeg = PortletDriver
								.findElement(By.xpath(".//*[@id='productSegmentsForm']/div"));
						if (!productSeg.isDisplayed()) {

							ValidationFactory.getElementIfPresent(By.xpath("//div[@id='js-segments']")).click();
						}
						productWCMRemoved.add(parentItemUncheck);
						boolean checkboxstatus = GenericFactory.productcheckstatus(parentItemUncheck);

						if (k >= hashsetWCMList.size() - 1) {

							PortletLinksPage_POF.applyFilter.click();
							Thread.sleep(1500);
							String Resultfound = null;
							List<WebElement> innerLinks = PortletDriver
									.findElements(By.xpath("//div[contains(@class,'link-col col-xs-6')]"));
							Thread.sleep(2000);
							if (innerLinks.size() == 0) {
								Expectedresult = "Search criteria should not be available in the list after unchecking the items.";
								status = "Pass";
								result = "Links are not available matching with Criteria.";
								break;

							}
							ArrayList<String> obtainedList = new ArrayList<>();
							for (int p = 0; p < innerLinks.size(); p++) {
								String innerLinkList = innerLinks.get(p).getText().trim();
								WebElement innerData = innerLinks.get(p);

								List<WebElement> ListofLinks = innerData.findElements(By.tagName("a"));
								for (WebElement we : ListofLinks) {

									String links = we.getText();
									System.out.println(links);
									obtainedList.add(links);
									if (links.toUpperCase().contains(Title.toUpperCase())
											&& hashsetWCMList.size() == 1) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Fail";
										result = "Product Preference functionality is not working though we have unchecked products.";

									}

									else if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())
											&& hashsetWCMList.size() > 1 && k != (hashsetWCMList.size() - 1)) {

										Expectedresult = "Search criteria should be available in the list.";
										status = "Pass";
										result = "Title :" + Title
												+ " is available as it is mapped to multiple products.";

									}

									else if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())
											&& hashsetWCMList.size() > 1 && k == (hashsetWCMList.size() - 1)) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Fail";
										result = "Title :" + Title
												+ " is available though we have unchecked all related products.";

									}

									if (links.toUpperCase().contains(Title.toUpperCase())
											&& hashsetWCMList.size() == 1) {

										Expectedresult = "Search criteria should not be available in the list.";
										status = "Fail";
										result = "Product Preference functionality is not working though we have unchecked product.";

									}

								}
							}
							System.out.println(obtainedList);
							for (int s = 0; s < obtainedList.size(); s++) {
								if (obtainedList.get(s).toString().equals(Title)) {

									break;

								} else {
									Expectedresult = "Search criteria should not be available in the list.";
									status = "Pass";
									result = "Title :" + Title
											+ " is not available when products are unchecked and link not available.";
									break;

								}
							}

						}

					}

				}

				if (m > 1 && flagItemnotfound == false) {
					ReportFactory.reporterOutput(wcmTestCaseID, "Verify Product Segment functionality on Index Pages.",
							"<B>ContentType :</B>" + strContenttype + " <B>User Prefernce List :</B>" + listOfElements
									+ " UserDefined List :" + hashsetUserdefinedList + "WCMProduct :" + WCMProducts
									+ " List of WCM Product items which are removed. " + productWCMRemoved
									+ " : and Link Name :" + Title,
							Expectedresult, result, status);
				} else if (hashsetUserdefinedList.size() == 1)

				{
					status = "Pass";
					ReportFactory.reporterOutput(wcmTestCaseID, "Verify Product Segment functionality on Index Pages.",
							"<B>ContentType :</B>" + strContenttype + " <B>User Prefernce List :</B>" + listOfElements
									+ " UserDefined List :" + hashsetUserdefinedList + "WCMProduct :" + WCMProducts
									+ " List of WCM Product items which are removed. " + productWCMRemoved
									+ " :and Link Name :" + Title,
							"Search criteria should not be available in the list after unchecking the items.",
							"User is not able to perform product preference as single product is available.", status);
				}

			}

			GenericFactory.checkAllProductsData(listOfElements);
			Thread.sleep(1000);
			PortletLinksPage_POF.applyFilter.click();
		}
		catch (Exception e) {
			ReportFactory.reporterOutput(wcmTestCaseID, "Verify Product Segment functionality on Index Pages.", "NA",
					"Preferred Product preference should work.", e.getMessage().toString().substring(0, 250), "Fail");
		}
	}

	// This is for country Grouping.

	@SuppressWarnings("unused")
	public static void countryFilterIndex(String wcmTestCaseID, String strDepartmentName, String Title,
			String strSubDepartmentName, String strUserDefinedCountry, String strWCMCountry, String strThirdLevelIndex,
			String strThirdLevelFolder, String strForthLevelIndexPage, String strContenttype) throws Throwable {

		try {

			String result = null;
			String status = null;
			String Expectedresult = null;
			boolean statusResult = false;
			String parentItemUncheck = null;
			boolean flagItemnotfound = false;
			String wcmCountrynameWcm = "";
			String regionData = null;
			boolean filterbox = false;

			try {

				filterbox = departmentLinksfilterbox.isDisplayed();

			} catch (Exception e) {

			}
			if ((filterbox == true) && (strContenttype.equalsIgnoreCase("AT-Index Page")
					|| strContenttype.equalsIgnoreCase("AT-ChildIndex Page")
					|| strContenttype.equalsIgnoreCase("AT-GrandChild Index Page")))

			{
				Expectedresult = "Search criteria should not be available in the list after Country Filtering..";
				status = "Pass";
				result = "Country filtering functionality can not be performed as Index/Child/Grand-Child Link is not Present.";

			} else {
				if (strThirdLevelIndex.equalsIgnoreCase("NA")) {
					WebElement department = GenericFactory.getDeptname(strDepartmentName);
					boolean linkfolderFlag = false;
					if (department != null) {
						department.click();
					} else

					{
						GenericFactory.toClickonDept(strDepartmentName);
					}

				}

				if (!strThirdLevelFolder.equalsIgnoreCase("NA")) {

					GenericFactory.toClickOnFolder(strThirdLevelFolder, strSubDepartmentName);
				}

				GenericFactory.checkAllCountryData();

				List<String> listOfElements = GenericFactory.getCheckBoxValuesCountry();

				PortletLinksPage_POF.applyFilterCountry.click();
				Thread.sleep(3000);

				int m = listOfElements.size();

				List<String> listWCM = new ArrayList<>();
				String[] strWCMCountryListMultiple = strWCMCountry.split(",");

				List<String> strWCMCountryData = Arrays.asList(strWCMCountryListMultiple);

				for (int j = 0; j < strWCMCountryData.size(); j++) {

					regionData = strWCMCountryData.get(j).toString();

				}

				if (!regionData.contains("/")) {

					Expectedresult = "Title/Link should not be available in the list after Country Filtering.";
					status = "Pass";
					result = "As WCM country list contains Main Region so can not perform.";

				}

				else if (listOfElements.size() == 1) {

					Expectedresult = "Title/Link should not be available in the list after Country Filtering.";
					status = "Pass";
					result = "As UserDefined Country List contains only one country so can not perform.";

				}

				else {
					ValidationFactory.getElementIfPresent(By.id("js-country-group")).click();
					int value = strWCMCountryData.size();
					if (listOfElements.size() == strWCMCountryData.size()) {

						value = value - 1;

					} else if (listOfElements.size() < strWCMCountryData.size()) {

						value = value - listOfElements.size();

					}

					for (int i = 0; i < value; i++) {

						String[] strWCMCountryList = strWCMCountryData.get(i).split("/");

						wcmCountrynameWcm = strWCMCountryList[1];

						if (strWCMCountryList.length > 2) {
							String wcmCountrynameWcmfinal = strWCMCountryList[2];
							wcmCountrynameWcm = wcmCountrynameWcmfinal;

						} else {
							wcmCountrynameWcm = wcmCountrynameWcm;
						}

						boolean checkboxstatus = GenericFactory.uncheckCountry(wcmCountrynameWcm);

					}

					PortletLinksPage_POF.applyFilterCountry.click();
					Thread.sleep(3000);

					List<WebElement> innerLinks = PortletDriver
							.findElements(By.xpath("//div[contains(@class,'link-col col-xs-6')]"));
					Thread.sleep(2000);
					if (innerLinks.size() == 0) {
						Expectedresult = "Search criteria should not be available in the list after Country Filtering.";
						status = "Pass";
						result = "Currently Link/Title is not available.";

					}
					ArrayList<String> obtainedList = new ArrayList<>();
					for (int p = 0; p < innerLinks.size(); p++) {
						String innerLinkList = innerLinks.get(p).getText().trim();
						WebElement innerData = innerLinks.get(p);

						List<WebElement> ListofLinks = innerData.findElements(By.tagName("a"));
						for (WebElement we : ListofLinks) {

							String links = we.getText();
							System.out.println(links);
							obtainedList.add(links);

							if (links.toUpperCase().equalsIgnoreCase(Title.toUpperCase())) {

								Expectedresult = "Search criteria should not be available in the list after Country Filtering..";
								status = "Fail";
								result = "As Title/Link is still Present after unchecking country/countries so Country filtering functionality is not working as expected.";

							}

						}
					}

					if (!obtainedList.contains(Title))

					{
						Expectedresult = "Title/Link should not be available in the list after Country Filtering..";
						status = "Pass";
						result = "Title/Link is not present once we uncheck Country/Countries.";
					}
				}
			}
			ReportFactory.reporterOutput(wcmTestCaseID,
					"Verify Country Filtering functionality for Link Portlet/Index/Child/GrandChild Pages.",
					"UserDefined Country :" + strUserDefinedCountry + "WCMCountry :" + strWCMCountry
							+ " : and Link Name :" + Title,
					Expectedresult, result, status);

			GenericFactory.checkAllCountryData();
		}

		catch (Exception e) {
			ReportFactory.reporterOutput(wcmTestCaseID, "Verify Country Filtering functionality.", "NA",
					"Country Filtering should work.", e.getMessage().toString().substring(0, 250), "Fail");
		}

	}

	// *************************************************getLeftWindowLinks********************************
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

	public static void departmentInactive(String wcmTestCaseID, String scenarioName, String input, String expResult,
			String result, String status) throws Throwable {

		ReportFactory.reporterOutput(wcmTestCaseID, scenarioName, input, expResult,
				"As Department is Inactive/NonClickable/NotVisible so We can not perform.", "Pass");
	}

	public static void recordNotFoundInactive(String wcmTestCaseID, String scenarioName, String input, String expResult,
			String result, String status) throws Throwable {

		ReportFactory.reporterOutput(wcmTestCaseID, scenarioName, input, expResult,
				"As Entry/Record not found/matching with criteria so We can not perform.", "Pass");
	}

	public static void matchNotFound(String wcmTestCaseID, String scenarioName, String input, String expResult,
			String result, String status) throws Throwable {

		ReportFactory.reporterOutput(wcmTestCaseID, scenarioName, input, expResult,
				"Criteria does not match so We can not perform./Data not found in WCM sheet.", "Fail");
	}

}
