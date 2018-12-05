package WCM_POF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;
import com.google.j2objc.annotations.ReflectionSupport.Level;

public class WCM_Conetnt_POF extends BaseClass {

	static WebDriver wcmalrtDriver;
	// private static String filename = "";
	static XSSFWorkbook workbook = null;
	static XSSFSheet spreadsheet = null;
	static String alertName = null;
	public static HashMap<String,String> linksAndSATHashMap=new HashMap<String,String>();
	private static XSSFWorkbook wcmbook;
	private static XSSFSheet wcmdataSheet;

	// static int testcaseNumber=1;

	static String testCaseID = "WCM_TC";

	public WCM_Conetnt_POF(WebDriver driver) {
		this.wcmalrtDriver = driver;

	}

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'pageSizeFiftywcmTable')]")
	public static WebElement bottomPagenumber;

	@FindBy(how = How.XPATH, using = "//table//tr[contains(@id,'wcmTable_')]")
	public static WebElement allAlerts;

	@FindBy(how = How.XPATH, using = "//td[contains(.,'Published') and @class='lotusMeta']")
	public static WebElement allPublishedAlerts;

	@FindBy(how = How.XPATH, using = "//h4[@role='presentation']//a[contains(.,'Content')]")
	public static WebElement contentSection;

	@FindBy(how = How.XPATH, using = "//li[@class='wcmBreadcrumbsElement']//a[contains(.,'Content')]")
	public static WebElement contentNavigtionSection;

	@FindBy(how = How.XPATH, using = "//h4[@role='presentation']//a[contains(.,'My DealerPath')]")
	public static WebElement myDealerPath;

	@FindBy(how = How.XPATH, using = "//h4[@role='presentation']//a[contains(.,'Announcements')]")
	public static WebElement announcementNavigation;

	@FindBy(how = How.XPATH, using = "//a[@id='close_controllable']")
	public static WebElement closeContent;

	@FindBy(how = How.XPATH, using = "//h4[@role='presentation']//a[contains(.,'Alerts')]")
	public static WebElement alertsSection;

	@FindBy(how = How.XPATH, using = "//table//tr[contains(@id,'wcmTable_')]")
	public static List<WebElement> totalAlerts;

	@FindBy(how = How.XPATH, using = "//td[contains(.,'Published') and @class='lotusMeta']")
	public static List<WebElement> totalPublishedAlerts;

	@FindBy(how = How.XPATH, using = "//*[@id='content_template']")
	public static WebElement contentTypeOnPage;

	@FindBy(how = How.XPATH, using = "//*[@id='id_ctrl_titlecom.aptrix.pluto.content.Content']")
	public static WebElement titleOnPage;

	@FindBy(how = How.XPATH, using = "//*[@id='locationcom.aptrix.pluto.content.Content']")
	public static WebElement locationOnPage;

	@FindBy(how = How.XPATH, using = "//*[@id='breadcrumb_library']")
	public static WebElement libraryOnPage;
	
	@FindBy(how = How.XPATH, using = "//label[.='Keywords:']")
	public static WebElement keywordsLabel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='keywords']")
	public static WebElement keywordsPage;
	
	@FindBy(how = How.XPATH, using = "//label[.='Users:']//following::div//ul[@class='lotusInlinelist']//li")
	public static List<WebElement> racfGroupUsersonPage;
	
	
	@FindBy(how = How.XPATH, using = "//div//span[.='Additional Groups']")
	public static WebElement racfGroupsonPage;
	

	@FindBy(how = How.XPATH, using = "//label[.='China MRU-Country']/following::span[1]")
	public static WebElement mruChinaCountry;

	@FindBy(how = How.XPATH, using = "//label[.='MRU-Country']//following::span[1]")
	public static WebElement mruCountry;

	@FindBy(how = How.XPATH, using = "//label[.='Product Type']/following::span[1]")
	public static WebElement productTypeOnPage;

	@FindBy(how = How.XPATH, using = "//label[.='China Product Type']/following::span[1]")
	public static WebElement chinaProductTypeOnPage;

	@FindBy(how = How.XPATH, using = "//label[.='Department']/following::span[1]")
	public static WebElement departmentOnPage;

	@FindBy(how = How.XPATH, using = "//label[.='Copy Department']/following::span[1]")
	public static WebElement copyDepartmentOnPage;

	@FindBy(how = How.XPATH, using = "//a[.='Edit']")
	public static WebElement editContent;

	@FindBy(how = How.XPATH, using = "//a[.='Read']")
	public static WebElement readContent;

	@FindBy(how = How.XPATH, using = "//label[.='Site Area Template:']/following::div[1]/a")
	public static WebElement siteArea;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Filter')]")
	public static WebElement filter;

	@FindBy(how = How.XPATH, using = "//a[contains(@id,'OkBtn')]")
	public static WebElement filterOk;

	@FindBy(how = How.XPATH, using = "//*[@id='breadcrumb_library']")
	public static WebElement checkForGlobalContent;

	/*
	 * @FindBy(how = How.XPATH, using =
	 * "//label[contains(.,'MRU-Country')]/following::div[1]") public static
	 * List<WebElement> globalCountries;
	 */

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Product Type')]/following::div[1]")
	public static List<WebElement> globalProductType;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'URL:')]/following::div[1]/span")
	public static WebElement linkText;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'File:')]/following::div[1]//span")
	public static WebElement documentText;

	@FindBy(how = How.XPATH, using = "//tr[contains(@id,'wcmTable_')]//td[2]//img[2]")
	public static List<WebElement> allChildren;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Rich Text')]")
	public static WebElement richTextLabel;

	@FindBy(how = How.XPATH, using = "//body[@spellcheck='true']//p[1]")
	public static WebElement richTextContent;

	@FindBy(how = How.XPATH, using = "//*[@id='Link_link']")
	public static WebElement webContentLink;

	@FindBy(how = How.XPATH, using = "//label[.='Web Content']/following::div[1]//a[1]//span")
	public static WebElement webContentLinkText;

	@FindBy(how = How.XPATH, using = "//label[.='Web Content']/following::div[1]//a[1]")
	public static WebElement webContentElement;

	@FindBy(how = How.XPATH, using = "//label[.='Dealer Type']//following::div[1]/span")
	public static WebElement dealerTypeOnPage;

	@FindBy(how = How.XPATH, using = "//img[@title='Remove Filter for Status']")
	public static WebElement removeFilterForStatus;

	@FindBy(how = How.XPATH, using = "//img[contains(@title,'Remove Filter for Last Saved')]")
	public static WebElement removeFilterForLastSavedDate;

	@FindBy(how = How.XPATH, using = "//a[@title='Click to sort ascending by Type']")
	public static WebElement webEleTypeasc;

	@FindBy(how = How.XPATH, using = "//a[@title='Click to sort descending by Type']")
	public static WebElement webEleTypedesc;

	@FindBy(how = How.XPATH, using = "//label[.='Description']//following::div[1]//textarea")
	public static WebElement description;

	@FindBy(how = How.XPATH, using = "//label[.='Description']//following::iframe[contains(@title,'Rich Text Editor')]")
	public static WebElement descriptionAlerts;

	@FindBy(how = How.XPATH, using = "//label[.='Rich Text']//following::iframe[contains(@title,'Rich Text Editor')]")
	public static WebElement richTextEditor;

	@FindBy(how = How.XPATH, using = "//label[.='Release Date']//following::tbody[1]//span")
	public static WebElement releaseDate;

	@FindBy(how = How.XPATH, using = "//label[.='Column 4']//following::div[1]/input")
	public static WebElement column4Table;

	@FindBy(how = How.XPATH, using = "//label[.='Column 5']//following::div[1]/input")
	public static WebElement column5Table;

	@FindBy(how = How.XPATH, using = "//*[@id='Link_link']")
	public static WebElement linkPresent;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'-MRU-Country')]//following::div[1]/span]")
	public static WebElement globalMappedCountires;

	/**
	 * @author Yogender singh This method fetch the Alerts and Announcements for the
	 *         Library mentioned in input sheet @ throws Throwable
	 */

	public static void readWCMAlertsAnnouncementsContent() throws Throwable {

		try {
			contentSection.click();

			//alertsSection.click();

			if (isElementNotPresent(removeFilterForStatus) && !(publishedDate.equals("NA"))) {
				applyFilterForStatusAndDate();
			}

			else {
				applyFilterForStatus();
			}

			moveInsideWCMContents("Alerts");

			System.out.println("***All General Alerts data fetched***");

			if (!publishedDate.equals("NA")) {
				removeFilterForLastSavedDate.click();
			}
			contentNavigtionSection.click();

			myDealerPath.click();

			announcementNavigation.click();

			if (!(publishedDate.equals("NA"))) {
				applyFilterForDate();
			}

			Thread.sleep(1000);

			moveInsideWCMContents("Announcement");

			System.out.println("***All General Announcements data fetched***");

		}

		catch (Exception e) {
			System.out.println("Link not clicked " + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method reads the Alerts and Announcements and
	 *         writes them into WCM Output excel sheet and writes them into WCM
	 *         output excel. @ throws Throwable
	 */

	public static void moveInsideWCMContents(String wcmsection) throws Throwable {
		HashMap<String, String> wcmKeyvalue = new HashMap<String, String>();
		List<String> alertsList;

		try {

			System.out.println("***fetching WCM contents for " + wcmsection + " ***");
			// List<WebElement>
			// publishedAlerts=ValidationFactory.getElementsIfPresent(By.xpath("//td[contains(.,'Published')
			// and @class='lotusMeta']/preceding-sibling::td[1]//a[not(contains(@title,'View
			// children'))]"));

			List<WebElement> publishedAlerts = allChildren;

			if (publishedAlerts.size() > 0) {
				alertsList = new ArrayList<String>();

				for (int sdc = 1; sdc <= publishedAlerts.size(); sdc++) {
					String subDeptImageTitle = wcmalrtDriver.findElement(By.xpath("//tr[" + sdc + "]//td[2]//img[2]"))
							.getAttribute("title");
					if (!(subDeptImageTitle.contains("View children"))) {
						String SubDeptChildName = wcmalrtDriver
								.findElement(By.xpath("//tr[" + sdc + "]//td[2]//img[2]/following::td[1]//a/span"))
								.getText();
						System.out.println(SubDeptChildName);
						alertsList.add(SubDeptChildName);
					}

					if (alertsList.size() == totalCount) {
						break;

					}

				}

				for (int i = 0; i < numberOfContentsToFetch(alertsList); i++) {
					System.out.println("Fetching content for " + wcmsection + " :" + alertsList.get(i));
					// WebElement
					// alert1=wcmalrtDriver.findElement(By.xpath("//a[contains(.,'"+alertsList.get(i)+"')]"));
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + alertsList.get(i) + "']"))) {

						WebElement alert1 = wcmalrtDriver.findElement(By.xpath("//a[.='" + alertsList.get(i) + "']"));
						alert1.click();

						String wcmTCID = testCaseID + testcaseNumber;
						wcmKeyvalue.put("WCMSection", wcmsection);
						wcmKeyvalue.put("Test Case ID", wcmTCID);

						writeWCMToExcel(wcmKeyvalue, "None");
						writeWCMHeaderContentFinalToExcel();
						testcaseNumber++;
						closeContent.click();
					} else {
						LogFactory.info("Unable to find the xpath for title::" + alertsList.get(i));
					}
				}
			}

			else {
				System.out.println("No contents to fetch ");
			}

		}

		catch (Exception e) {

			System.out.println("Error while writing contents for " + wcmsection + " " + e.getMessage().toString());
		}
	}

	/**
	 * @author Yogender singh This method is to check special characters in the
	 *         title of the content and writes them into WCM output excel.
	 * @return String(Title) @ throws Throwable
	 */

	private static String chceckForTitle(String text) throws Throwable {

		try {

			String arr[] = text.split("((?<=['({]))");
			if (arr.length > 1)
				return arr[0].substring(0, arr[0].length() - 1);
			else
				return text;

		} catch (Exception e) {
			System.out.println("Error while checking for title of:" + text + " " + e.getMessage().toString());
		}

		return null;
	}

	/**
	 * @author Yogender singh This method is to navigate to the library provided in
	 *         the input sheet and writes them into WCM output excel. @ throws
	 * Throwable
	 */

	public static void navigateToRegion(String library) throws Throwable {

		try {
			if (library != null) {
				if (ValidationFactory.isElementPresent(bottomPagenumber)) {
					bottomPagenumber.click();
				}
				wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + library + "')]")).click();

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't navigate to alert section " + e.getMessage().toString());
		}
	}

	/**
	 * @author Yogender singh This method is to apply filter for Status to the WCM
	 *         content and writes them into WCM output excel. @ throws Throwable
	 */

	public static void applyFilterForStatus() throws Throwable {
		try {

			wcmalrtDriver.navigate().refresh();
			Thread.sleep(2000);

			filter.click();
			Thread.sleep(1000);

			String clickFilter = "//*[@id='ibm_wcm_widget_filter_FilterField_0_menuLink']";
			WebElement filterclicked = WaitFactory.explicitWaitByXpath(clickFilter);

			filterclicked.click();
			Thread.sleep(1000);

			String selectingStatus = "//td[contains(@id,'_STATUS_text')]";

			WebElement statusSelect = WaitFactory.explicitWaitByXpath(selectingStatus);

			statusSelect.click();

			Thread.sleep(1000);
			filterOk.click();

			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Error while applying Status filter");
		}

	}

	/**
	 * @author Yogender singh This method is to apply filter for Status and Date
	 *         together to the WCM content and writes them into WCM output excel. @
	 * throws Throwable
	 */

	public static void applyFilterForStatusAndDate() throws Throwable {

		try {

			wcmalrtDriver.navigate().refresh();

			filter.click();

			Thread.sleep(1000);
			String clickFilter = "//*[@id='ibm_wcm_widget_filter_FilterField_0_menuLink']";

			WebElement filterclicked = WaitFactory.explicitWaitByXpath(clickFilter);

			filterclicked.click();
			Thread.sleep(2000);

			String selectingStatus = "//td[contains(@id,'_STATUS_text')]";

			WebElement statusSelect = WaitFactory.explicitWaitByXpath(selectingStatus);

			statusSelect.click();

			Thread.sleep(1000);

			/// applying filter for published date

			wcmalrtDriver.findElement(By.xpath("//a[.='Add a filter']")).click();

			Thread.sleep(1000);
			String clickFilter1 = "//*[@id='ibm_wcm_widget_filter_FilterField_1_menuLink']";
			WebElement filterclicked1 = WaitFactory.explicitWaitByXpath(clickFilter1);

			filterclicked1.click();
			Thread.sleep(2000);

			String selectingLastSaveddate = "//td[contains(@id,'1LAST_MODIFIED_DATE_text')]";

			WebElement lastSavedDateSelect = WaitFactory.explicitWaitByXpath(selectingLastSaveddate);

			lastSavedDateSelect.click();

			Thread.sleep(1000);
			String selectingAfterDropDown = "//a[@id='ibm_wcm_widget_filter_DateFilter_0_condition']";

			WebElement afterDropDownSelect = WaitFactory.explicitWaitByXpath(selectingAfterDropDown);

			afterDropDownSelect.click();
			Thread.sleep(1000);

			String selectingAfter = "//td[contains(@id,'_0_conditionAFTER_text')]";

			WebElement afterSelect = WaitFactory.explicitWaitByXpath(selectingAfter);

			afterSelect.click();
			Thread.sleep(1000);

			wcmalrtDriver.findElement(By.xpath("//input[contains(@id,'DateFilter_0_date1')]")).sendKeys(publishedDate);

			Thread.sleep(1000);

			filterOk.click();

			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Unable to apply filter: " + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method is to apply filter for Date together to
	 *         the WCM content and writes them into WCM output excel. @ throws
	 * Throwable
	 */
	public static void applyFilterForDate() throws Throwable {

		try {

			filter.click();

			Thread.sleep(1000);

			wcmalrtDriver.findElement(By.xpath("//a[.='Add a filter']")).click();

			Thread.sleep(1000);
			String clickFilter1 = "//*[@id='ibm_wcm_widget_filter_FilterField_1_menuLink']";
			WebElement filterclicked1 = WaitFactory.explicitWaitByXpath(clickFilter1);

			filterclicked1.click();
			Thread.sleep(2000);

			String selectingLastSaveddate = "//td[contains(@id,'1LAST_MODIFIED_DATE_text')]";

			WebElement lastSavedDateSelect = WaitFactory.explicitWaitByXpath(selectingLastSaveddate);

			lastSavedDateSelect.click();

			Thread.sleep(1000);
			String selectingAfterDropDown = "//a[@id='ibm_wcm_widget_filter_DateFilter_0_condition']";

			WebElement afterDropDownSelect = WaitFactory.explicitWaitByXpath(selectingAfterDropDown);

			afterDropDownSelect.click();
			Thread.sleep(1000);

			String selectingAfter = "//td[contains(@id,'_0_conditionAFTER_text')]";

			WebElement afterSelect = WaitFactory.explicitWaitByXpath(selectingAfter);

			afterSelect.click();
			Thread.sleep(1000);

			wcmalrtDriver.findElement(By.xpath("//input[contains(@id,'DateFilter_0_date1')]")).sendKeys(publishedDate);

			Thread.sleep(1000);

			filterOk.click();

		} catch (Exception e) {
			System.out.println("Unable to apply filter " + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method is to write the Headers and WCM content
	 *         row wise into WCM output excel sheet and writes them into WCM output
	 *         excel. @ throws Throwable
	 */
	public static void writeWCMHeaderContentFinalToExcel() throws Throwable {

		try {
			System.out.println("***Writing final content into WCM Excel***");
			writeWCMHeader(filename, BaseClass.headerListWCM);

			writeWCMRow(filename, BaseClass.finalResultforExcelWCM);

		} catch (Exception e) {

			System.out.println("error while writing WCM content excel" + e.getMessage().toString());
		}
	}

	/**
	 * @author Yogender singh This method is to create the WCM output excel sheet as
	 *         per the Name given in the WCM input sheet and writes them into WCM
	 *         output excel. @ throws Throwable
	 */
	public static void createWCMExcel() throws Throwable {

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
			Date date = new Date();
			filename = wcmDataOutputPath + dateFormat.format(date) + ".xlsx";

			BaseClass.headerListWCM = new ArrayList<String>();

			BaseClass.headerListWCM.add("Test Case ID");
			BaseClass.headerListWCM.add("EXECUTE");
			BaseClass.headerListWCM.add("URL");
			BaseClass.headerListWCM.add("Library");
			BaseClass.headerListWCM.add("Multilingual");
			BaseClass.headerListWCM.add("DepartmentName");
			BaseClass.headerListWCM.add("2ndLevel");
			BaseClass.headerListWCM.add("3rdLevelIndexPage");
			BaseClass.headerListWCM.add("3rdLevelIndexPageCategories");
			BaseClass.headerListWCM.add("3rdLevelIndexPageNestedCategories");
			BaseClass.headerListWCM.add("3rdLevelLandingPage");
			BaseClass.headerListWCM.add("3rdLevelChildIndexPage");
			BaseClass.headerListWCM.add("3rdLevelChildIndexPageCategories");
			BaseClass.headerListWCM.add("3rdLevelChildIndexPageNestedCategories");
			BaseClass.headerListWCM.add("3rdLevelGrandChildIndexPage");
			BaseClass.headerListWCM.add("3rdLevelGrandChildIndexPageCategories");
			BaseClass.headerListWCM.add("3rdLevelGrandChildIndexPageNestedCategories");
			BaseClass.headerListWCM.add("3rdLevelFolder");
			BaseClass.headerListWCM.add("4thLevelIndexPage");
			BaseClass.headerListWCM.add("4thLevelIndexPageCategories");
			BaseClass.headerListWCM.add("4thLevelIndexPageNestedCategories");
			BaseClass.headerListWCM.add("4thLevelLandingPage");
			BaseClass.headerListWCM.add("4thLevelChildIndexPage");
			BaseClass.headerListWCM.add("4thLevelChildIndexPageCategories");
			BaseClass.headerListWCM.add("4thLevelChildIndexPageNestedCategories");
			BaseClass.headerListWCM.add("4thLevelGrandChildIndexPage");
			BaseClass.headerListWCM.add("4thLevelGrandChildIndexPageCategories");
			BaseClass.headerListWCM.add("4thLevelGrandChildIndexPageNestedCategories");
			BaseClass.headerListWCM.add("ContentType");
			BaseClass.headerListWCM.add("IndexPageContentType");
			BaseClass.headerListWCM.add("Title");
			BaseClass.headerListWCM.add("Keywords");

			BaseClass.headerListWCM.add("DocPath");
			BaseClass.headerListWCM.add("Link");
			BaseClass.headerListWCM.add("Description");
			BaseClass.headerListWCM.add("Description_RichText");

			BaseClass.headerListWCM.add("ReleaseDate");
			BaseClass.headerListWCM.add("Column 4");
			BaseClass.headerListWCM.add("Column 5");
			BaseClass.headerListWCM.add("HeaderName");
			BaseClass.headerListWCM.add("FooterName");

			BaseClass.headerListWCM.add("Country");
			BaseClass.headerListWCM.add("ProductType");
			BaseClass.headerListWCM.add("DealerType (Main/Sub)");
			BaseClass.headerListWCM.add("Index_Page_Template");
			BaseClass.headerListWCM.add("Index_Page_Template_Label");
			BaseClass.headerListWCM.add("Sort By");
			BaseClass.headerListWCM.add("Sort Order");
			BaseClass.headerListWCM.add("Year Grouping Required");
			BaseClass.headerListWCM.add("RACFGroups");
			BaseClass.headerListWCM.add("CopyToDepartment");
			BaseClass.headerListWCM.add("Comments");

			System.out.println();

		} catch (Exception e) {

			System.out.println("error while creating WCM hedaer content list" + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method is to read all the wcm content fields and
	 *         store in Hashmap and writes them into WCM output excel. @ throws
	 * Throwable
	 */

	public static void writeWCMToExcel(HashMap<String, String> valuesToWrite, String contentToForward)
			throws Throwable {

		String contentType = null;
		String title = null;
		String countryTitle = null;
		String location = null;
		String library = null;
		String department = null;
		String copyToDepartment = null;
		String indexPageContentType = null;
		String link = null;
		String document = null;
		String prodTypeFinal = null;
		String dealerType = null;
		String descriptionText = null;
		String releaseDateOfContent = null;
		String column4Text = null;
		String column5Text = null;
		String richText = null;
		String keywords = null;
		String racfGroups = null;

		try {

			contentType = contentTypeOnPage.getText();
			String[] cType = contentType.split("/");
			String conType = cType[cType.length - 1].trim();

			title = titleOnPage.getText();
			location = locationOnPage.getText();
			library = libraryOnPage.getText();
			
			if (ValidationFactory.isElementPresent(keywordsLabel) && !(keywordsPage.getText().isEmpty())) 
			{
			
			keywords=keywordsPage.getText();
			
			}

			if (!conType.equals("AT-Default")) {
				if (checkForGlobalContent.getText().contains("GLOBAL_CONTENT")) {

					String countries = wcmalrtDriver
							.findElement(
									By.xpath("//label[.='" + globalCountries.get(alertRegion) + "']/following::span"))
							.getText();
					countryTitle = fetchCountriesList(countries);

					String products = wcmalrtDriver
							.findElement(By
									.xpath("//label[.='" + globalProductTypes.get(alertRegion) + "']/following::span"))
							.getText();
					;
					prodTypeFinal = fetchProductsList(products);
				}

				else {

					if (Region.equals("R1")) {
						System.out.println("Region is R1");
						String countryRegion = "";
						String productRegion = "";
						String[] regionArray = alertRegion.split("_");
						String reiognLast = regionArray[regionArray.length - 1].trim();

						switch (reiognLast) {
						case "TH":
							countryRegion = "Thailand MRU-Country";
							productRegion = "Thailand Product Type";
							break;
						case "CN":
							countryRegion = "China MRU-Country";
							productRegion = "China Product Type";
							break;
						case "SSA":
							countryRegion = "Sub Saharan Africa MRU-Country";
							productRegion = "Sub Saharan Africa Product Type";
							break;
						case "IN":
							countryRegion = "India MRU-Country";
							productRegion = "India Product Type";
							break;
						case "Asia":
							countryRegion = "JD Asia MRU-Country";
							productRegion = "JD Asia Product Type";
							break;

						}
						String countries1 = wcmalrtDriver
								.findElement(By.xpath("//label[.='" + countryRegion + "']/following::span[1]"))
								.getText();
						countryTitle = fetchCountriesList(countries1);

						String productType = wcmalrtDriver
								.findElement(By.xpath("//label[.='" + productRegion + "']/following::span[1]"))
								.getText();
						prodTypeFinal = fetchProductsList(productType);
						System.out.println(countryTitle + " " + prodTypeFinal);
					}

					else {
						countryTitle = fetchCountriesList(mruCountry.getText());
						prodTypeFinal = fetchProductsList(productTypeOnPage.getText());
					}

					if (ValidationFactory.isElementPresent(description) && !(description.getText().isEmpty())) {

						descriptionText = description.getText();
					}
					if(ValidationFactory.isElementPresent(racfGroupsonPage)) {
						
						if(ValidationFactory.isElementPresent(By.xpath("//label[.='Users:']//following::div//ul[@class='lotusInlinelist']"))) 
						{
							if(racfGroupUsersonPage.size()==1)
							{
								if(!(wcmalrtDriver.findElement(By.xpath("//label[.='Users:']//following::div//ul[@class='lotusInlinelist']//li")).getText().contains("None")))
										{
							
							racfGroups=wcmalrtDriver.findElement(By.xpath("//label[.='Users:']//following::div//ul[@class='lotusInlinelist']//li")).getText();
										}
								else
								{
									racfGroups=null;
									
								}
							}
							else
							{
								String rGroup="";
								for (int i = 1; i <= racfGroupUsersonPage.size(); i++) 
									{
									
									rGroup=rGroup+","+wcmalrtDriver.findElement(By.xpath("//label[.='Users:']//following::div//ul[@class='lotusInlinelist']//li["+i+"]")).getText();
									
									}
									racfGroups=rGroup.substring(1);
							}
						}
					}
					
					if (ValidationFactory.isElementPresent(descriptionAlerts)) {
						wcmalrtDriver.switchTo().frame(descriptionAlerts);

						String desc = wcmalrtDriver.findElement(By.xpath("//body")).getText();

						if (!desc.isEmpty())
							descriptionText = desc;

						wcmalrtDriver.switchTo().defaultContent();

					}

					if (ValidationFactory.isElementPresent(releaseDate) && !(releaseDate.getText().contains("None"))) {
						String releaseDateToSplit = releaseDate.getText();
						String newReleaseDate = releaseDateToSplit.substring(0, 12).trim();

						SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy");
						Date newDate = spf.parse(newReleaseDate);
						spf = new SimpleDateFormat("yyyy-MM-dd");
						String newDateString = spf.format(newDate);

						releaseDateOfContent = newDateString;
					}

					if (ValidationFactory.isElementPresent(column4Table) && !(column4Table.getText().isEmpty())) {
						column4Text = column4Table.getText();
					}

					if (ValidationFactory.isElementPresent(column5Table) && !(column5Table.getText().isEmpty())) {
						column5Text = column5Table.getText();
					}

					if (ValidationFactory.isElementPresent(dealerTypeOnPage)) {
						dealerType = fetchProductsList(dealerTypeOnPage.getText());
					}

				}
			}
			for (HashMap.Entry<String, String> entry : valuesToWrite.entrySet()) {

				if (entry.getKey().contains("WCMSection")) {
					System.out.println("Inside wcm");
					String wcmsection = valuesToWrite.get("WCMSection");

					if (wcmsection.equals("Alerts")) {
						System.out.println("Content type is alerts");
						department = BaseClass.department;
						break;
					} else if (wcmsection.equals("Announcement")) {
						if (conType.equals("AT-Announcement")) {
							String departmentType = departmentOnPage.getText();
							String[] deptType = departmentType.split("/");
							department = deptType[deptType.length - 1].trim();
							copyToDepartment = fetchCopyToDeptList(copyDepartmentOnPage.getText());
							break;
						}
						else {

							department =  BaseClass.department;
							break;
						}
					}

					else if (wcmsection.equals("Announcement for Departement")) {

						/*
						 * String checkForDuplicateAnnouncement=wcmalrtDriver.findElement(By.xpath(
						 * "//li/a[contains(.,'Announcements')]/preceding::li[2]//a")).getText();
						 * if(checkForDuplicateAnnouncement.equals("My DealerPath")) {
						 * System.out.println("Announcement: "+title+" already exists"); break; }
						 */

						if (conType.equals("AT-Announcement")) {

							String departmentType = departmentOnPage.getText();
							String[] deptType = departmentType.split("/");
							department = deptType[deptType.length - 1].trim();

							copyToDepartment = fetchCopyToDeptList(copyDepartmentOnPage.getText());

							break;
						} else {

							department =  BaseClass.department;
							break;
						}

					}

				} /// this is for alerts and announcements

				else {
					if (conType.contains("Default")) {
						department = wcmalrtDriver.findElement(By.id("breadcrumb_item_1")).getText();
						break;
					}

					else if (conType.equals("AT-Link") || conType.contains("AT-Link_Devl")) {
						if (ValidationFactory.isElementPresent(linkText)) {
							link = linkText.getText();
							indexPageContentType = "Link";
							break;
						}

						else if (ValidationFactory.isElementPresent(webContentElement)
								&& !((webContentLinkText).getText().contains("None"))) {
							link = webContentLinkText.getText();
							indexPageContentType = "Link";
							break;
						}

					}

					else if (conType.equals("AT-Document") || conType.contains("AT-Embedded Document")) {
						document = documentText.getText();
						indexPageContentType = "Document";
						break;
					}

					else if ((conType.equals("AT-Rich Text") || conType.equals("AT-Embedded RichText"))
							|| (ValidationFactory.isElementPresent(richTextLabel)
									&& ValidationFactory.isElementPresent(richTextContent))) {

						indexPageContentType = "Rich-Text";

						wcmalrtDriver.switchTo().frame(richTextEditor);
						String richTextContentIs = wcmalrtDriver.findElement(By.xpath("//body")).getText();
						wcmalrtDriver.switchTo().defaultContent();

						richText = richTextContentIs;
						break;
					}

					else {

						String contentIs = checkForLinkRichTextDocument();
						System.out.println("Content is" + contentIs);

						String fetchFirstLast[] = contentIs.split("/");

						String contentIsFinal = fetchFirstLast[fetchFirstLast.length - 1].trim();

						if (contentIs.contains("Link")) {
							link = contentIsFinal;
							indexPageContentType = "Link";
							break;
						} else if (contentIs.contains("Document")) {
							document = contentIsFinal;
							indexPageContentType = "Document";
							break;
						} else if (contentIs.contains("Rich-Text")) {
							indexPageContentType = "Rich-Text";

							wcmalrtDriver.switchTo().frame(richTextEditor);
							String richTextContentReturned = wcmalrtDriver.findElement(By.xpath("//body")).getText();
							wcmalrtDriver.switchTo().defaultContent();
							richText = richTextContentReturned;
							break;
						}

					}

				}

			}

			HashMap<String, String> finalkeyValueWCM;
			finalkeyValueWCM = new HashMap<String, String>();

			String deptName = valuesToWrite.get("DepartmentName");

			if (contentToForward.equals("None")) {

				// Adding elements to the recently created HashMap
				finalkeyValueWCM.put("ContentType", conType);
				finalkeyValueWCM.put("Title", title);
				finalkeyValueWCM.put("Location", location);
				finalkeyValueWCM.put("Library", library);
				if (!(deptName == null)) {
					finalkeyValueWCM.put("DepartmentName", deptName);
				} else {
					finalkeyValueWCM.put("DepartmentName", department);
				}
				finalkeyValueWCM.put("CopyToDepartment", copyToDepartment);
				finalkeyValueWCM.put("Country", countryTitle);
				finalkeyValueWCM.put("ProductType", prodTypeFinal);
				finalkeyValueWCM.put("IndexPageContentType", indexPageContentType);
				finalkeyValueWCM.put("Link", link);
				finalkeyValueWCM.put("DocPath", document);
				finalkeyValueWCM.put("EXECUTE", "Y");
				finalkeyValueWCM.put("Multilingual", multiLingual);
				finalkeyValueWCM.put("Description", descriptionText);
				finalkeyValueWCM.put("Description_RichText", richText);
				finalkeyValueWCM.put("ReleaseDate", releaseDateOfContent);
				finalkeyValueWCM.put("Column 4", column4Text);
				finalkeyValueWCM.put("Column 5", column5Text);
				finalkeyValueWCM.put("RACFGroups", racfGroups);
				finalkeyValueWCM.put("Keywords", keywords);

				finalkeyValueWCM.putAll(valuesToWrite);

				excelOutput(finalkeyValueWCM);
			}

			else {

				valuesToWrite.put("ContentType", conType);
				valuesToWrite.put("Title", title);
				valuesToWrite.put("Location", location);
				valuesToWrite.put("Library", library);
				if (!(deptName == null)) {
					valuesToWrite.put("DepartmentName", deptName);
				} else {
					valuesToWrite.put("DepartmentName", department);
				}
				valuesToWrite.put("CopyToDepartment", copyToDepartment);
				valuesToWrite.put("Country", countryTitle);
				valuesToWrite.put("ProductType", prodTypeFinal);
				valuesToWrite.put("IndexPageContentType", indexPageContentType);
				valuesToWrite.put("Link", link);
				valuesToWrite.put("DocPath", document);
				valuesToWrite.put("EXECUTE", "Y");
				valuesToWrite.put("Multilingual", multiLingual);
				valuesToWrite.put("Description", descriptionText);
				valuesToWrite.put("Description_RichText", richText);
				valuesToWrite.put("ReleaseDate", releaseDateOfContent);
				valuesToWrite.put("Column 4", column4Text);
				valuesToWrite.put("Column 5", column5Text);

				excelOutput(valuesToWrite);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error while fetching content from " + e.getMessage().toString());

		}
	}

	/**
	 * @author Yogender singh This method is to fetch all Product and writes them
	 *         into WCM output excel. @ throws Throwable
	 */

	private static String fetchProductsList(String products) throws Throwable {
		// TODO Auto-generated method stub
		String productTypeTitle = "";

		try {
			// =/DPATH_DESIGN/Product Type/Home/h1,/DPATH_DESIGN/Product Type/Home/h2
			// =/DPATH_DESIGN/MRU-Country Grouping/P1,/DPATH_DESIGN/MRU-Country Grouping/P2

			String[] prodTypeList = products.split(",");

			if (prodTypeList.length >= 2) {
				for (int n = 0; n <= prodTypeList.length - 1; n++) {
						
					String prodt=prodTypeList[n].trim();
					String temp = prodt.substring(32);
					productTypeTitle = productTypeTitle + "," + temp.trim();
				}
				return productTypeTitle.substring(1);

			} else {
				return products.substring(32);

			}

		} catch (Exception e) {
			System.out.println("Error while fetchin product types from ceontent" + e.getMessage().toString());

		}
		return null;
	}

	
	
	
	
	public static String fetchCopyToDeptList(String copyToDepts) throws Throwable{
		

		// TODO Auto-generated method stub
		String productTypeTitle = "";

		try {
			// =/DPATH_DESIGN/Product Type/Home/h1,/DPATH_DESIGN/Product Type/Home/h2
			// =/DPATH_DESIGN/MRU-Country Grouping/P1,/DPATH_DESIGN/MRU-Country Grouping/P2

			String[] prodTypeList = copyToDepts.split(",");

			if (prodTypeList.length >= 2) {
				for (int n = 0; n <= prodTypeList.length - 1; n++) {
						
					String prodt=prodTypeList[n].trim();
					String temp = prodt.substring(28);
					productTypeTitle = productTypeTitle + "," + temp.trim();
				}
				return productTypeTitle.substring(1);

			} else {
				return copyToDepts.substring(28);

			}

		} catch (Exception e) {
			System.out.println("Error while fetching product types from ceontent" + e.getMessage().toString());

		}
		return null;
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @author Yogender singh This method is to check the content type of content
	 *         and return it @ throws Throwable
	 * @return String(content Type, Link, Rich-Text, Document)
	 */
	private static String checkForLinkRichTextDocument() throws Throwable {

		try {

			System.out.println("verfying the content for Link, Rich-Text or Document");

			if (ValidationFactory.isElementPresent(linkText) && !(linkText.getText().contains("None"))) {
				// if((linkText.getText().contains("None")))
				return "Link/" + linkText.getText();

			}

			else if (ValidationFactory.isElementPresent(documentText) && !(documentText.getText().contains("None"))) {

				return "Document/" + documentText.getText();
			}

			else if (ValidationFactory.isElementPresent(webContentElement)
					&& !(webContentLinkText.getText().contains("None"))) {

				return "Link/" + webContentLinkText.getText();

			}

			// else if(ValidationFactory.isElementPresent(richTextLabel) &&
			// ValidationFactory.isElementPresent(richTextContent))
			else {
				System.out.println("It's Rich Text");
				return "Rich-Text/None";
			}
		}

		catch (Exception e) {

			System.out.println("Error while determining the type of content " + e.getMessage().toString());
		}

		return null;
	}

	/**
	 * @author Yogender singh This method is to add the content into Hasmap List and
	 *         return it @ throws Throwable
	 */
	public static void excelOutput(HashMap<String, String> wcmContentToWrite) throws Throwable {

		System.out.println("***mapping contents into List***");
		try {

			BaseClass.excelListWCM = new LinkedHashMap<String, String>();

			for (HashMap.Entry<String, String> entry : wcmContentToWrite.entrySet()) {
				BaseClass.excelListWCM.put(entry.getKey(), entry.getValue());
			}

			System.out.println("Key Value hashmap: " + BaseClass.excelListWCM);

			BaseClass.finalResultforExcelWCM.add(BaseClass.excelListWCM);
		}

		catch (Exception e) {
			System.out.println("Error while mapping content to list " + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method is to add the Headers into the WCM output
	 *         excel file @ throws Throwable
	 * @return String (Filename)
	 */

	public static String writeWCMHeader(String fileName, List<String> headerListWCM) throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			XSSFWorkbook book = new XSSFWorkbook();

			XSSFSheet sheet;

			sheet = book.createSheet("WCM Content");

			Row row = sheet.createRow(0);

			int cellNumber = 0;
			Font font = book.createFont();
			// font.setBold(true);
			font.setFontHeightInPoints((short) 9);
			font.setColor(IndexedColors.DARK_YELLOW.getIndex());
			font.setBold(true);

			CellStyle cellStyle1 = book.createCellStyle();

			for (String header : headerListWCM) {
				cellStyle1.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cellStyle1.setFont(font);

				Cell cell = row.createCell(cellNumber++);

				cell.setCellValue(header);
				cell.setCellStyle(cellStyle1);

				sheet.autoSizeColumn(cellNumber);

			}
			book.write(fos);
			book.close();
			fos.close();

			return fileName;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while creating excel with Hedaer data" + e.getMessage().toString());

		}
		return fileName;
	}

	/**
	 * @author Yogender singh This method is to add the WCM content row wise into
	 *         the WCm excel sheet @ throws Throwable
	 */
	public static void writeWCMRow(String fileName, List<HashMap<String, String>> rowList)
			throws IOException, InvalidFormatException, Throwable {

		try {
			int rowNum;

			File oFile = new File(fileName);
			FileInputStream input = new FileInputStream(oFile);
			wcmbook = new XSSFWorkbook(input);

			wcmdataSheet = wcmbook.getSheet("WCM Content");

			rowNum = wcmdataSheet.getLastRowNum();
			CellStyle style = wcmbook.createCellStyle();// *
			// Font font = wcmbook.createFont();// *

			for (int i = 0; i < rowList.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = rowList.get(i);

				XSSFRow row = wcmdataSheet.createRow(++rowNum);

				XSSFRow headerRow = wcmdataSheet.getRow(0);

				short minColIx = headerRow.getFirstCellNum(); // get the first column index for a row
				short maxColIx = headerRow.getLastCellNum(); // get the last column index for a row

				for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last column index

					XSSFCell cell = headerRow.getCell(colIx); // get the cell

					// add the cell contents (name of column) and cell index to the map
					String headerName = cell.getStringCellValue();

					int headerIndex = cell.getColumnIndex();

					XSSFCell cellToWrite = row.createCell(headerIndex);

					for (HashMap.Entry<String, String> entry : map.entrySet()) {

						// System.out.println("Writing Content:: Inner loop::"+map);
						if (map.containsKey(headerName)) {
							String cellVal1 = map.get(headerName);

							if (cellVal1 instanceof String) {
								cellToWrite.setCellValue((String) cellVal1);
							}

							else {
								cellToWrite.setCellValue("NA");
							}
						}

						else {

							cellToWrite.setCellValue("NA");
						}

						wcmdataSheet.autoSizeColumn(headerIndex);

						style.setWrapText(true);
						cell.setCellStyle(style);
					} // end of for loop for writing content into correct cell

				} // End of For loop for matching Excel header with map's key

			} // end of outer for loop writing all rows to excel

			input.close();
			FileOutputStream fos = new FileOutputStream(oFile);
			wcmbook.write(fos);
			wcmbook.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error While wrting row content" + e.getMessage().toString());

		}
	}

	/**
	 * @author Yogender singh This method is to add the Department wise content as
	 *         per given in the WCM input sheet @ throws Throwable
	 */

	public static void fetchDepartmentContents(String departmentName, int totalCount) throws Throwable {

		HashMap<String, String> wcmKeyValue1 = new HashMap<String, String>();

		System.out.println("**Now fetching Department wise data**");

		try {
			contentSection.click();

			myDealerPath.click();

			wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + departmentName + "')]")).click();

			System.out.println("fetching Announcements for department: " + departmentName);

			wcmalrtDriver.findElement(By.xpath("//a[contains(.,'Announcements')]")).click();

			if (isElementNotPresent(removeFilterForStatus) && !(publishedDate.equals("NA"))) {
				applyFilterForStatusAndDate();
			}

			else {
				applyFilterForStatus();
			}

			moveInsideWCMContents("Announcement for Departement");

			if (!(publishedDate.equals("NA"))) {
				removeFilterForLastSavedDate.click();
			}
			Thread.sleep(1000);
			wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + departmentName + "')]")).click();

			// fetching all subChildrens
			List<WebElement> allImagesUnderDepartment = allChildren;
			List<String> subDepartments = new ArrayList<String>();// contains all subDepartments under Business Admin &
																	// HR

			for (int i = 1; i <= allImagesUnderDepartment.size(); i++) {
				String FecthDeptsimageTitle = wcmalrtDriver.findElement(By.xpath("//tr[" + i + "]//td[2]//img[2]"))
						.getAttribute("title");
				if (FecthDeptsimageTitle.contains("View children")
						&& !(FecthDeptsimageTitle.contains("View children of Announcements"))) {
					String DeptChildrenName = wcmalrtDriver
							.findElement(By.xpath("//tr[" + i + "]//td[2]//img[2]/following::td[1]//a")).getAttribute("title").substring(17).trim();
					subDepartments.add(DeptChildrenName);
				}
			}

			// DepthasChildren contains all SAT-Index pages, Landing pages, SAT-Folders
			// folders etc
			System.out.println(
					"TOTAL SubDepartments under department: " + departmentName + " are::" + subDepartments.size());
			if (subDepartmentNumbers == 0) {
				subDepartmentNumbers = subDepartments.size();
			}
			System.out.println("Total Sub Departments to fetch::" + subDepartmentNumbers);

			// Fetching SubDepartment wise WCM content
			for (int sd = 0; sd < subDepartmentNumbers; sd++) {
				List<String> SAT_Index_pages = new ArrayList<String>();
				;
				List<String> SAT_Folders = new ArrayList<String>();
				;
				List<String> SAT_LandingPages = new ArrayList<String>();
				List<String> SAT_Table_Index_pages = new ArrayList<String>();

				String subDeptsUnderDeptName = subDepartments.get(sd); // Optimization (Sub Department)
				System.out.println("Fetching content for SubDepartment: " + subDeptsUnderDeptName);// Optimization

				if (ValidationFactory.isElementPresent(
						By.xpath("//a[.='" + subDeptsUnderDeptName + "' and starts-with(@title,'View children')]"))) {
					WebElement subDepartment = wcmalrtDriver.findElement(
							By.xpath("//a[.='" + subDeptsUnderDeptName + "' and starts-with(@title,'View children')]"));

					subDepartment.click(); // Optimization or Business Management Clicked

					if (!(publishedDate.equalsIgnoreCase("NA"))) {
						applyFilterForDate();
					}
					Thread.sleep(1000);

					////////// Inside Sub Department "Optimization"////////////////

					// now fetching all contents under sub department e.g: Optimization's
					// children(index pages and rest of the contents)

					List<WebElement> allSubDeptChildrenImages = allChildren;

					// adding contents under Sub Departments(Optimization) into different lists

					List<String> SubDeptHasChildren = new ArrayList<String>();
					List<String> SubDeptLinkPortlets = new ArrayList<String>();

					int totalCountAfterComparison = totalCount;
					if (allSubDeptChildrenImages.size() < totalCount) {
						totalCountAfterComparison = allSubDeptChildrenImages.size();
					}

					System.out.println("Total children for Subdepartment are:" + allSubDeptChildrenImages.size()
							+ " but content actually to check are:" + totalCount);
					for (int sdc = 1; sdc <= allSubDeptChildrenImages.size(); sdc++) {
						String subDeptImageTitle = wcmalrtDriver
								.findElement(By.xpath("//tr[" + sdc + "]//td[2]//img[2]")).getAttribute("title");

						if (!(subDeptImageTitle.contains("View children"))) {
							String SubDeptNoChildName = wcmalrtDriver
									.findElement(By.xpath("//tr[" + sdc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							SubDeptLinkPortlets.add(SubDeptNoChildName);
							// System.out.println("Added link:: "+SubDeptNoChildName+" for
							// SubDepartment::"+subDeptsUnderDeptName);
						}

						if (SubDeptLinkPortlets.size() == totalCount) {
							break;
						}

					}
					
					if (SubDeptLinkPortlets.size() > 0) {
						// Fetching Link Portlets for Sub Department Optimization in
						// excel(Link,documents,Rich text)
						System.out.println("Total Link portlets after comparison ::" + SubDeptLinkPortlets.size());
						for (int k = 0; k < SubDeptLinkPortlets.size(); k++) {

							String SubDeptLinkPortlet = SubDeptLinkPortlets.get(k);
							System.out.println("Fetching content for " + SubDeptLinkPortlet);

							if (ValidationFactory.isElementPresent(By.xpath("//a[contains(.,'" + SubDeptLinkPortlet
									+ "') and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
								WebElement subDeptlinkPortletElement = wcmalrtDriver
										.findElement(By.xpath("//a[contains(.,'" + SubDeptLinkPortlet
												+ "') and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"));

								subDeptlinkPortletElement.click();

								// checking for Index page inside the link
								String contentType = contentTypeOnPage.getText();
								String[] cType = contentType.split("/");
								String conType = cType[cType.length - 1].trim();
								
								if (conType.equals("AT-Link") && ValidationFactory.isElementPresent(webContentElement)
										&& !(webContentLinkText.getText().contains("None"))) {
									
									System.out.println("This is an index page link");
									String[] indexPageTitleArray = webContentLinkText.getText().split("/");
									String indexPageTitle = indexPageTitleArray[indexPageTitleArray.length - 1].trim();

									linksAndSATHashMap.put(indexPageTitle, SubDeptLinkPortlet);

									SubDeptHasChildren.add(indexPageTitle);
								}

								String wcmTCID = testCaseID + testcaseNumber;
								wcmKeyValue1.put("Test Case ID", wcmTCID);
								wcmKeyValue1.put("DepartmentName", departmentName);
								wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);

								writeWCMToExcel(wcmKeyValue1, "None");
								writeWCMHeaderContentFinalToExcel();
								testcaseNumber++;
								closeContent.click();
							}

							else {
								LogFactory.info("Unable to find the xpath for title::" + SubDeptLinkPortlet);
							}

						}

					}

					else {
						System.out.println("No published content to fetch");
					}

					// creating List of content type for Sub department e.g:Index pages,
					// Tables,Folders, Landing Pages
					if (SubDeptHasChildren.size() > 0) {

						for (int m = 0; m < SubDeptHasChildren.size(); m++) {
							String indexchildName = SubDeptHasChildren.get(m); // Business Continuation

							if (ValidationFactory.isElementPresent(By.xpath(
									"//a[.='" + indexchildName + "' and starts-with(@title,'View children')]"))) {
								String contentType = checkContentType(indexchildName);

								if (contentType.contains("SAT-LandingPage")) {
									SAT_LandingPages.add(indexchildName);
								} else if (contentType.contains("SAT-Folder site Area")) {
									SAT_Folders.add(indexchildName);
								} else if (contentType.contains("SAT-Index Page")) {
									SAT_Index_pages.add(indexchildName);
								} else if (contentType.contains("SAT-Table Index Page")) {
									SAT_Table_Index_pages.add(indexchildName);
								}
							} else {
								LogFactory.info("Unable to find the xpath for title::" + indexchildName);
							}
						} // end of loop for fetching total tree icon contents type for Sub Department
							// Optimization under Business Admoin & HR

						System.out.println("Total SAT_Index pages under:" + subDeptsUnderDeptName + " are "
								+ SAT_Index_pages.size());
						System.out.println("Total SAT_Landing pages under: " + subDeptsUnderDeptName + " are "
								+ SAT_LandingPages.size());
						System.out.println(
								"Total SAT_Folders under: " + subDeptsUnderDeptName + " are " + SAT_Folders.size());
						System.out.println("Total SAT_Tables under: " + subDeptsUnderDeptName + " are "
								+ SAT_Table_Index_pages.size());

						/// LOGIC FOR FETCHING CONTENT FOR ALL TABLES
						HashMap<String, String> subDepartmentTable = new HashMap<String, String>();
						HashMap<String, String> subDepartmentTableKeyValue = new HashMap<String, String>();

						for (int rt = 0; rt < SAT_Table_Index_pages.size(); rt++) {
							System.out.println("Fetching content for Table :" + SAT_Table_Index_pages.get(rt));
							subDepartmentTable = fetchTablesContent(SAT_Table_Index_pages.get(rt));// can contain Link
																									// Portlets and
																									// Child index pages

							String wcmTCID = testCaseID + testcaseNumber;

							subDepartmentTableKeyValue.put("Test Case ID", wcmTCID);
							subDepartmentTableKeyValue.put("DepartmentName", departmentName);
							subDepartmentTableKeyValue.put("2ndLevel", subDeptsUnderDeptName);
							//subDepartmentTableKeyValue.put("3rdLevelIndexPage", SAT_Table_Index_pages.get(rt));
							subDepartmentTableKeyValue.put("3rdLevelIndexPage", linksAndSATHashMap.get(SAT_Table_Index_pages.get(rt)));
							subDepartmentTableKeyValue.putAll(subDepartmentTable);

							excelOutput(subDepartmentTableKeyValue);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;

							fetchTableRowsContent(SAT_Table_Index_pages.get(rt), subDepartmentTableKeyValue,
									"3rdLevelIndexPage");

						}

						if (!publishedDate.equals("NA")) {
							removeFilterForLastSavedDate.click();
						}
						// CALLING FUNCTION FOR FETCHING CONTENT FOR ALL INDEX PAGES

						if (SAT_LandingPages.size() > 0) {
							fetchContentsForlandingPages(SAT_LandingPages, departmentName, subDeptsUnderDeptName);
						}

						if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+subDeptsUnderDeptName+"']")))
							wcmalrtDriver.findElement(By.xpath("//a[.='"+subDeptsUnderDeptName+"']")).click();
						
						if (SAT_Index_pages.size() > 0) {
							fetchContentTillGrandChild(SAT_Index_pages, departmentName, subDeptsUnderDeptName);

							if (ValidationFactory.isElementPresent(removeFilterForLastSavedDate)) {
								removeFilterForLastSavedDate.click();
							}

						}

						// wcmalrtDriver.findElement(By.xpath("//a[.='"+subDeptsUnderDeptName+"']")).click();

/*						if (SAT_Folders.size() > 0) {
							fetchContentsForFolders(SAT_Folders, departmentName, subDeptsUnderDeptName);// can contain
						}*/

					} else {
						System.out.println("No Index page content to fetch");

					}

					if (ValidationFactory.isElementPresent(removeFilterForLastSavedDate)) {
						removeFilterForLastSavedDate.click();
					}
					
					if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+departmentName+"']")))
					wcmalrtDriver.findElement(By.xpath("//a[.='"+departmentName+"']")).click();
				} else {
					LogFactory.info("Unable to find the xpath for SubDepartment::" + subDeptsUnderDeptName);
				}
			}
		} catch (Exception e) {
			LogFactory.info("Error while fetching Department wise wcm Content for department: " + departmentName + " "
					+ e.getMessage().toString());
			System.out.println("Error while fetching Department wise wcm Content for department: " + departmentName
					+ " " + e.getMessage().toString());
		}

	} /// END OF fetchDepartmentContents method

	/**
	 * @author Yogender singh This method is to fetch the content of a SAT-Table @
	 * throws Throwable
	 */
	private static void fetchTableRowsContent(String tableName, HashMap<String, String> tableStructure, String Level)
			throws Throwable {
		try {
			HashMap<String, String> tableRowDataFromWCM = new HashMap<String, String>();
			HashMap<String, String> tableChildIsCategory = new HashMap<String, String>();
			List<String> table_ChildCategoriesList = new ArrayList<String>();
			wcmalrtDriver.findElement(By.xpath("//a[.='" + tableName + "' and contains(@title,'View children')]"))
					.click();

			List<WebElement> allRowsimages = allChildren;
			List<String> tableRows = new ArrayList<String>();
			List<String> otherTableData = new ArrayList<String>();

			int totalCountAfterComparisonz = totalCount;
			if (allRowsimages.size() < totalCount) {
				totalCountAfterComparisonz = allRowsimages.size();
			}
			for (int trc = 1; trc <= totalCountAfterComparisonz; trc++) {
				String tableImageTitle = wcmalrtDriver.findElement(By.xpath("//tr[" + trc + "]//td[2]//img[2]"))
						.getAttribute("title");
				if (!(tableImageTitle.contains("View children"))) {
					String rowTitle = wcmalrtDriver
							.findElement(By.xpath("//tr[" + trc + "]//td[2]//img[2]/following::td[1]//a/span"))
							.getText();
					tableRows.add(rowTitle);
				}

				if (tableRows.size() == totalCount) {
					break;
				}

			}

			if(tableRows.size()==0)
			{
				System.out.println("Checking Categories for Table index page::" + tableName);

				for (int checkTableCategory = 1; checkTableCategory <= totalCountAfterComparisonz; checkTableCategory++) {
					WebElement tableCategories = wcmalrtDriver.findElement(
							By.xpath("//tr[" + checkTableCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
					String childCategoryTitleGCIP = tableCategories.getText();

					String childType = checkContentType(childCategoryTitleGCIP);
					if (childType.contains("SAT-Default Sub-Site Area")) {
						table_ChildCategoriesList.add(childCategoryTitleGCIP);
					}
				}

			}
			//fetchContentForGrandChildIndexPage(tableChildIsChildIndexPage, Level);
			
			for(int tcc=0;tcc<table_ChildCategoriesList.size();tcc++)
			{
				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + table_ChildCategoriesList.get(tcc)
				+ "' and starts-with(@title,'View children')]"))) {
			WebElement tableChildCategory = wcmalrtDriver
					.findElement(By.xpath("//a[.='" + table_ChildCategoriesList.get(tcc)
							+ "' and starts-with(@title,'View children')]"));

			String tableChildCategoryTitle = tableChildCategory.getText();
				
			tableChildCategory.click();
			List<WebElement> categoriesContentFinal = allChildren;
			int totalCountAfterComparisonz1 = totalCount;
			if (categoriesContentFinal.size() < totalCount) {
				totalCountAfterComparisonz1 = categoriesContentFinal.size();
			}
			for(int nccfc = 1; nccfc <= totalCountAfterComparisonz1; nccfc++)
			{
				if (ValidationFactory.isElementPresent(By.xpath("//tr[" + nccfc + "]//td[2]//img[2]/following::td[1]//a")))
				{
				wcmalrtDriver.findElement(By.xpath("//tr[" + nccfc + "]//td[2]//img[2]/following::td[1]//a"))
				.click();


			String wcmTCID = testCaseID + testcaseNumber;
			tableChildIsCategory.put("Test Case ID", wcmTCID);
			tableChildIsCategory.put("3rdLevelIndexPageCategories", tableChildCategoryTitle);
			tableChildIsCategory.putAll(tableStructure);
			writeWCMToExcel(tableChildIsCategory, "Table");
			writeWCMHeaderContentFinalToExcel();
			testcaseNumber++;
			closeContent.click();
				}
				else
					{LogFactory.info("Unable to find the xpath for title");}
				}
			
			wcmalrtDriver.findElement(By.xpath("//a[.='"+tableName+"']")).click();
			
			} 
				else {LogFactory.info("Unable to find the xpath for title");}
			}
			
			
			if (tableRows.size() > 0) {
				for (int rtr = 0; rtr < tableRows.size(); rtr++) {

					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + tableRows.get(rtr)
							+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
						wcmalrtDriver.findElement(By.xpath("//a[.='" + tableRows.get(rtr)
								+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
								.click();

						// checking for Index page inside the link
						String contentType = contentTypeOnPage.getText();
						String[] cType = contentType.split("/");
						String conType = cType[cType.length - 1].trim();

						if (conType.equals("AT-Index Page") && ValidationFactory.isElementPresent(webContentElement)
								&& !(webContentLinkText.getText().contains("None"))) {
							System.out.println("This is an index page link");
							String[] indexPageTitleArray = webContentLinkText.getText().split("/");
							String tableContentOtherThenRow = indexPageTitleArray[indexPageTitleArray.length - 1]
									.trim();

							linksAndSATHashMap.put(tableContentOtherThenRow, tableRows.get(rtr));
							
							otherTableData.add(tableContentOtherThenRow);
						}

						String wcmTCID = testCaseID + testcaseNumber;
						tableStructure.put("Test Case ID", wcmTCID);

						writeWCMToExcel(tableStructure, "Table");
						writeWCMHeaderContentFinalToExcel();
						testcaseNumber++;
						closeContent.click();
					} else {
						LogFactory.info("Unable to find the xpath for title");
					}
				}
				System.out.println("Content for Table::" + tableName
						+ " of AT-Index type(table rows content) is fetched sucessfully ");

				// now fetching table data apart from ROWS
				if (otherTableData.size() > 0) {
					System.out.println("Table::" + tableName + " has index page type content" + otherTableData.size());

					System.out.println("Now fetching Child Index page's content for Table index page::" + tableName);
					List<String> table_ChildIndexPagesList = new ArrayList<String>();
					
					List<String> table_ChildTablesList = new ArrayList<String>();

					HashMap<String, String> tableChildIsChildIndexPage = new HashMap<String, String>();
					HashMap<String, String> tableChildIsTables = new HashMap<String, String>();
					//HashMap<String, String> tableChildIsCategory = new HashMap<String, String>();

					for (int tip = 0; tip < otherTableData.size(); tip++) {
						String tableChildName = otherTableData.get(tip); // Business Continuation

						if (ValidationFactory.isElementPresent(
								By.xpath("//a[.='" + tableChildName + "' and starts-with(@title,'View children')]"))) {
							String tableChildContentType = checkContentType(tableChildName);

							if (tableChildContentType.contains("SAT-Child IndexPage")) {
								table_ChildIndexPagesList.add(tableChildName);
							} else if (tableChildContentType.contains("SAT-Table Index Page")) {
								table_ChildTablesList.add(tableChildName);
							} /*else if (tableChildContentType.contains("SAT-Default Sub-Site Area")) {
								table_ChildCategoriesList.add(tableChildName);
							}*/
						} else {
							LogFactory.info("Unable to find the xpath for title::" + tableChildName);
						}
					}

					System.out.println("Table Index page has ::" + table_ChildIndexPagesList.size()
							+ " Child Index pages," + table_ChildTablesList.size() + " Table childs and "
							+ table_ChildCategoriesList.size() + " categories");
					// fetching content for table index page apart from link portlets
					for (int tcipt = 0; tcipt < numberOfContentsToFetch(table_ChildTablesList); tcipt++) {

						System.out.println("Fetching Child Table:: " + table_ChildTablesList.get(tcipt)
								+ " Content for Table index page::" + tableName);// Child Table

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + table_ChildTablesList.get(tcipt)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement tableChildTable = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ table_ChildTablesList.get(tcipt) + "' and starts-with(@title,'View children')]"));

							String tableChildTabletitle = tableChildTable.getText();

							String wcmTCID = testCaseID + testcaseNumber;
							HashMap<String, String> tabledata = new HashMap<String, String>();

							tabledata = fetchTablesContent(tableChildTabletitle);

							tableChildIsTables.put("Test Case ID", wcmTCID);

							tableChildIsTables.put("DepartmentName", tableStructure.get("DepartmentName"));
							tableChildIsTables.put("2ndLevel", tableStructure.get("2ndLevel"));

							if (Level.contains("3rdLevelIndexPage")) {
								tableChildIsTables.put("3rdLevelIndexPage", linksAndSATHashMap.get(tableName));
								tableChildIsTables.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(tableChildTabletitle));
							} else {
								tableChildIsTables.put("4thLevelIndexPage", linksAndSATHashMap.get(tableName));
								tableChildIsTables.put("4thLevelChildIndexPage", linksAndSATHashMap.get(tableChildTabletitle));
							}
							tableChildIsTables.putAll(tabledata);

							excelOutput(tableChildIsTables);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;

							fetchTableRowsContentForChildTable(table_ChildTablesList.get(tcipt), tableChildIsTables,
									Level);
						} else {
							LogFactory.info("Unable to find the xpath for title::" + table_ChildTablesList.get(tcipt));
						}

					}

					/// Fetching content for Child index page's categories

					/*for (int tipc = 0; tipc < numberOfContentsToFetch(table_ChildCategoriesList); tipc++) {
						System.out.println("Reading content for Category " + table_ChildCategoriesList.get(tipc)
								+ " of Table Index Page::" + tableName);// SALES

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + table_ChildCategoriesList.get(tipc)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement tableChildCategory = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + table_ChildCategoriesList.get(tipc)
											+ "' and starts-with(@title,'View children')]"));

							String tableChildCategoryTitle = tableChildCategory.getText();

							tableChildCategory.click(); // tools and documents
							System.out.println("Category ::" + tableChildCategoryTitle + " for Table index page:"
									+ tableName + " is clicked");// table index page first category clicked

							// checking for nested category

							String wcmTCID = testCaseID + testcaseNumber;
							tableChildIsCategory.put("Test Case ID", wcmTCID);
							tableChildIsCategory.put("3rdLevelIndexPageCategories", tableChildCategoryTitle);
							tableChildIsCategory.putAll(tableStructure);

							checkForNestedcategories(tableChildIsCategory, "3rdLevelIndexPageCategories");

							wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + tableName + "')]")).click();
						} else {
							LogFactory
									.info("Unable to find the xpath for title::" + table_ChildCategoriesList.get(tipc));
						}
					}*/

					for (int tcip = 0; tcip < table_ChildIndexPagesList.size(); tcip++) {
						if (Level.contains("3rdLevelIndexPage")) {
							tableChildIsChildIndexPage.put("3rdLevelIndexPage", linksAndSATHashMap.get(tableName));
							tableChildIsChildIndexPage.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(table_ChildIndexPagesList.get(tcip)));
						} else {
							tableChildIsChildIndexPage.put("4thLevelIndexPage", linksAndSATHashMap.get(tableName));
							tableChildIsChildIndexPage.put("4thLevelChildIndexPage", linksAndSATHashMap.get(table_ChildIndexPagesList.get(tcip)));
						}
						
						//tableChildIsChildIndexPage.put("3rdLevelChildIndexPage", table_ChildIndexPagesList.get(tcip));

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + table_ChildIndexPagesList.get(tcip)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement categoryChildIndexPage = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + table_ChildIndexPagesList.get(tcip)
											+ "' and starts-with(@title,'View children')]"));
							categoryChildIndexPage.click();

							fetchContentForChildIndexPage(tableChildIsChildIndexPage, "3rdLevelChildIndexPage");
						} else {
							LogFactory
									.info("Unable to find the xpath for title::" + table_ChildCategoriesList.get(tcip));
						}
					} ////// Check again at this point

					// fetchContentTillGrandChild(otherTableData,
					// tableStructure.get("DepartmentName"), tableStructure.get("2ndLevel"));
				} else {
					System.out.println("No index page content to fetch");
				}
			} //// END of IF loop(>))
			else {
				System.out.println("Table::" + tableName + " has no content inside it");

			}

			if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+tableStructure.get("2ndLevel")+"']")))
				wcmalrtDriver.findElement(By.xpath("//a[.='"+tableStructure.get("2ndLevel")+"']")).click();

		} catch (Exception e) {
			System.out
					.println("Error While fetching content for table::" + tableName + " " + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method is to fetch the content of a SAT-Table at
	 *         Child Level @ throws Throwable
	 */

	private static void fetchTableRowsContentForChildTable(String childTablename,
			HashMap<String, String> childTableStructure, String nextlevel) throws Throwable {

		try {
			HashMap<String, String> childTableRowDataFromWCM = new HashMap<String, String>();
			wcmalrtDriver.findElement(By.xpath("//a[.='" + childTablename + "' and contains(@title,'View children')]"))
					.click();

			List<WebElement> allChildRowsimages = allChildren;
			List<String> allChildTableRows = new ArrayList<String>();
			List<String> otherChildTableData = new ArrayList<String>();
			List<String> childtable_CategoriesList = new ArrayList<String>();
			HashMap<String,String> childTableChildIsCategory= new HashMap<String,String>();

			int totalCountAfterComparisonf = totalCount;
			if (allChildRowsimages.size() < totalCount) {
				totalCountAfterComparisonf = allChildRowsimages.size();
			}
			for (int ctrt = 1; ctrt <= totalCountAfterComparisonf; ctrt++) {
				String childTableImageTitle = wcmalrtDriver.findElement(By.xpath("//tr[" + ctrt + "]//td[2]//img[2]"))
						.getAttribute("title");
				if (!(childTableImageTitle.contains("View children"))) {
					String childTablerowTitle = wcmalrtDriver
							.findElement(By.xpath("//tr[" + ctrt + "]//td[2]//img[2]/following::td[1]//a/span"))
							.getText();
					allChildTableRows.add(childTablerowTitle);
				}
				if (allChildTableRows.size() == totalCount) {
					break;
				}

			}
			
			
			if(allChildTableRows.size()==0)
			{
				System.out.println("Checking Categories for Child Table index page::" + childTablename);

				for (int checkTableCategory = 1; checkTableCategory <= totalCountAfterComparisonf; checkTableCategory++) {
					WebElement childTableCategories = wcmalrtDriver.findElement(
							By.xpath("//tr[" + checkTableCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
					String childCategoryTitleGCIP = childTableCategories.getText();

					String childType = checkContentType(childCategoryTitleGCIP);
					if (childType.contains("SAT-Default Sub-Site Area")) {
						childtable_CategoriesList.add(childCategoryTitleGCIP);
					}
				}

			}
			
			
			for(int tcc=0;tcc<childtable_CategoriesList.size();tcc++)
			{
				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + childtable_CategoriesList.get(tcc)
				+ "' and starts-with(@title,'View children')]"))) {
			WebElement tableChildCategory = wcmalrtDriver
					.findElement(By.xpath("//a[.='" + childtable_CategoriesList.get(tcc)
							+ "' and starts-with(@title,'View children')]"));

			String tableChildCategoryTitle = tableChildCategory.getText();
			tableChildCategory.click();
			List<WebElement> categoriesContentFinal = allChildren;
			int totalCountAfterComparisonyv1 = totalCount;
			if (categoriesContentFinal.size() < totalCount) {
				totalCountAfterComparisonyv1 = categoriesContentFinal.size();
			}
			for(int nccfc = 1; nccfc <= totalCountAfterComparisonyv1; nccfc++)
			{
				if (ValidationFactory.isElementPresent(By.xpath("//tr["+nccfc+"]//td[2]//img[2]/following::td[1]//")))
				{
				wcmalrtDriver.findElement(By.xpath("//tr[" + nccfc + "]//td[2]//img[2]/following::td[1]//a"))
				.click();


			String wcmTCID = testCaseID + testcaseNumber;
			childTableChildIsCategory.put("Test Case ID", wcmTCID);
			childTableChildIsCategory.put("3rdLevelChildIndexPageCategories", tableChildCategoryTitle);
			childTableChildIsCategory.putAll(childTableStructure);
			writeWCMToExcel(childTableChildIsCategory, "Table");
			writeWCMHeaderContentFinalToExcel();
			testcaseNumber++;
			closeContent.click();
				}
				else
					{LogFactory.info("Unable to find the xpath for title");}
				}
			
			wcmalrtDriver.findElement(By.xpath("//a[.='"+childTablename+"']")).click();
		} else {
			LogFactory.info("Unable to find the xpath for title");
		}
			}
			
	
			if (allChildTableRows.size() > 0) {
				for (int ctr = 0; ctr < allChildTableRows.size(); ctr++) {
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + allChildTableRows.get(ctr)
							+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
						wcmalrtDriver.findElement(By.xpath("//a[.='" + allChildTableRows.get(ctr)
								+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
								.click();
						// checking for Index page inside the link
						String contentType = contentTypeOnPage.getText();
						String[] cType = contentType.split("/");
						String conType = cType[cType.length - 1].trim();

						if ((conType.equals("AT-Index Page") || conType.equals("AT-Child Index Page"))
								&& ValidationFactory.isElementPresent(webContentElement)
								&& !(webContentLinkText.getText().contains("None"))) {
							System.out.println("This is an index page link");
							String[] indexPageTitleArray = webContentLinkText.getText().split("/");
							String childTableContentOtherThenRow = indexPageTitleArray[indexPageTitleArray.length - 1]
									.trim();
							linksAndSATHashMap.put(childTableContentOtherThenRow, allChildTableRows.get(ctr));
							otherChildTableData.add(childTableContentOtherThenRow);
						}

						String wcmTCID = testCaseID + testcaseNumber;
						// childTableRowDataFromWCM.put("Test Case ID", wcmTCID);
						childTableStructure.put("Test Case ID", wcmTCID);
						childTableRowDataFromWCM.putAll(childTableStructure);

						writeWCMToExcel(childTableRowDataFromWCM, "Table");
						writeWCMHeaderContentFinalToExcel();
						testcaseNumber++;
						closeContent.click();

					} else {
						LogFactory.info("Unable to find the xpath for title::" + allChildTableRows.get(ctr));
					}
					
					System.out
					.println("AT-Index type Content for Child Table::" + childTablename + " is read successfully ");

				}

				
				// now fetching table data apart from ROWS
				System.out.println("Child Table::" + childTablename + " has index page type content:"
						+ otherChildTableData.size());
				if(otherChildTableData.size()>0)
				{
				List<String> childTable_grandChildIndexPages = new ArrayList<String>();
				List<String> ChildTable_grandChildCategories = new ArrayList<String>();
				List<String> ChildTable_grandChildTables = new ArrayList<String>();

				HashMap<String, String> childtableGrandChildIndexPages = new HashMap<String, String>();
				HashMap<String, String> childtableGrandChildTables = new HashMap<String, String>();

				for (int tip = 0; tip < otherChildTableData.size(); tip++) {
					String childTableChildName = otherChildTableData.get(tip); // Business Continuation
					if (ValidationFactory.isElementPresent(
							By.xpath("//a[.='" + childTableChildName + "' and starts-with(@title,'View children')]"))) {
						String tableChildContentType = checkContentType(childTableChildName);

						if (tableChildContentType.contains("SAT-Grand Child IndexPage")) {
							childTable_grandChildIndexPages.add(childTableChildName);
						} else if (tableChildContentType.contains("SAT-Table Index Page")) {
							ChildTable_grandChildTables.add(childTableChildName);
						} /*else if (tableChildContentType.contains("SAT-Default Sub-Site Area")) {
							ChildTable_grandChildCategories.add(childTableChildName);
						}*/
					} else {
						LogFactory.info("Unable to find the xpath for title::" + childTableChildName);
					}
				}

				if (ChildTable_grandChildTables.size() > 0) {
					// fetching content for child table index page apart from link portlets
					for (int ctgct = 0; ctgct < numberOfContentsToFetch(ChildTable_grandChildTables); ctgct++) {

						System.out.println("Fetching Grand Child Table:: " + ChildTable_grandChildTables.get(ctgct)
								+ " Content for Child Table index page::" + childTablename);// Child Table

						if (ValidationFactory
								.isElementPresent(By.xpath("//a[.='" + ChildTable_grandChildTables.get(ctgct)
										+ "' and starts-with(@title,'View children')]"))) {
							WebElement childTableGrandChildTable = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + ChildTable_grandChildTables.get(ctgct)
											+ "' and starts-with(@title,'View children')]"));

							String childTableGrandChildTableTitle = childTableGrandChildTable.getText();

							String wcmTCID = testCaseID + testcaseNumber;
							HashMap<String, String> tabledata = new HashMap<String, String>();

							tabledata = fetchTablesContent(childTableGrandChildTableTitle);

							childtableGrandChildTables.put("Test Case ID", wcmTCID);
							if (nextlevel.contains("3rdLevelIndexPage") || nextlevel.contains("3rdLevelLandingPage")) {
								childtableGrandChildTables.put("3rdLevelGrandChildIndexPage",
										linksAndSATHashMap.get(childTableGrandChildTableTitle));
							} else {
								childtableGrandChildTables.put("4thLevelGrandChildIndexPage",
										linksAndSATHashMap.get(childTableGrandChildTableTitle));
							}
							childtableGrandChildTables.putAll(childTableStructure);
							childtableGrandChildTables.putAll(tabledata);

							excelOutput(childtableGrandChildTables);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;

							fetchTableRowsContentForGrandChildTable(ChildTable_grandChildTables.get(ctgct),
									childtableGrandChildTables, nextlevel);
						} else {
							LogFactory.info(
									"Unable to find the xpath for title::" + ChildTable_grandChildTables.get(ctgct));
						}

					}
				}

				if (childTable_grandChildIndexPages.size() > 0) {
					HashMap<String, String> childTableGrandChildIsIndexPage = new HashMap<String, String>();
					for (int ctgcip = 0; ctgcip < childTable_grandChildIndexPages.size(); ctgcip++) {

						if (nextlevel.contains("3rdLevelIndexPage") || nextlevel.contains("3rdLevelLandingPage")) {
							childTableGrandChildIsIndexPage.put("3rdLevelGrandChildIndexPage",
									linksAndSATHashMap.get(childTable_grandChildIndexPages.get(ctgcip)));
						} else {
							childTableGrandChildIsIndexPage.put("4thLevelGrandChildIndexPage",
									linksAndSATHashMap.get(childTable_grandChildIndexPages.get(ctgcip)));
						}
						if (ValidationFactory
								.isElementPresent(By.xpath("//a[.='" + childTable_grandChildIndexPages.get(ctgcip)
										+ "' and starts-with(@title,'View children')]"))) {
							WebElement childTableGrandChildIndexPage = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + childTable_grandChildIndexPages.get(ctgcip)
											+ "' and starts-with(@title,'View children')]"));
							childTableGrandChildIndexPage.click();

							String childTableGrandChildIndexPageTitle = childTableGrandChildIndexPage.getText();

							List<WebElement> grandChildIndexPageContent = new ArrayList<WebElement>();
							grandChildIndexPageContent = allChildren;

							int totalCountAfterComparisonnx = totalCount;
							if (grandChildIndexPageContent.size() < totalCount) {
								totalCountAfterComparisonnx = grandChildIndexPageContent.size();
							}
							for (int gcpc = 0; gcpc < totalCountAfterComparisonnx; gcpc++) {

								System.out.println(
										"Fetching GrandChild Index page content for Child Table::" + childTablename);

								String wcmTCID = testCaseID + testcaseNumber;
								childtableGrandChildIndexPages.put("Test Case ID", wcmTCID);
								if (nextlevel.contains("3rdLevelIndexPage")
										|| nextlevel.contains("3rdLevelLandingPage")) {
									childtableGrandChildIndexPages.put("3rdLevelGrandChildIndexPage",
											linksAndSATHashMap.get(childTableGrandChildIndexPageTitle));
								} else {
									childtableGrandChildIndexPages.put("4thLevelGrandChildIndexPage",
											linksAndSATHashMap.get(childTableGrandChildIndexPageTitle));
								}
								childtableGrandChildIndexPages.putAll(childTableStructure);

								writeWCMToExcel(childtableGrandChildIndexPages, "None");
								writeWCMHeaderContentFinalToExcel();
								testcaseNumber++;
								closeContent.click();

							}
						} else {
							LogFactory.info("Unable to find the xpath for title::"
									+ childTable_grandChildIndexPages.get(ctgcip));
						}
					}
				}

			}
			else
			{System.out.println("NO SAT type content to fetch");}

			} else {
				System.out.println("Child Table::" + childTablename + " has no content inside it");

			}
			
			System.out.println("final link to click to go back is ::"+childTableStructure.get(nextlevel));
			
			String key="";
			String keyToClick=childTableStructure.get(nextlevel);
			System.out.println("Key to click is:;"+keyToClick);
			for(HashMap.Entry<String, String> entry : linksAndSATHashMap.entrySet()){
	            if(entry.getValue().equals(keyToClick)){
	            	key=entry.getKey(); 
	            	System.out.println("Key found in hashmap is ::"+key);
	                break;//no break, looping entire hashtable
	            }
	           
	        }


	
			wcmalrtDriver.findElement(By.xpath("//a[.='"+key+"']")).click();

		} catch (Exception e) {
			System.out.println("Error While fetching content for Child table::" + childTablename + " "
					+ e.getMessage().toString());
		}
		
	}

	/**
	 * @author Yogender singh This method is to fetch the content of a SAT-Table at
	 *         Grand Child Level @ throws Throwable
	 */

	private static void fetchTableRowsContentForGrandChildTable(String grandChildTableName,
			HashMap<String, String> grandChildTableStructure, String nextestLevel) throws Throwable {
		HashMap<String, String> grandChildTableContent = new HashMap<String, String>();
		
		HashMap<String,String> grandChildTableChildIsCategory=new HashMap<String,String>();
		ArrayList<String> grandChildtable_CategoriesList=new ArrayList<String>();
		try {

			if (ValidationFactory.isElementPresent(
					By.xpath("//a[.='" + grandChildTableName + "' and contains(@title,'View children')]"))) {
				wcmalrtDriver
						.findElement(
								By.xpath("//a[.='" + grandChildTableName + "' and contains(@title,'View children')]"))
						.click();

				List<WebElement> allGrandChildTableChildContent = allChildren;
				List<String> grandChildTableRowContent = new ArrayList<String>();
				List<String> grandChildTableIndexPageContent = new ArrayList<String>();

				int totalCountAfterComparisony = totalCount;
				if (allGrandChildTableChildContent.size() < totalCount) {
					totalCountAfterComparisony = allGrandChildTableChildContent.size();
				}
				for (int gctc = 1; gctc <= allGrandChildTableChildContent.size(); gctc++) {
					String grandChildTableImageTitle = wcmalrtDriver
							.findElement(By.xpath("//tr[" + gctc + "]//td[2]//img[2]")).getAttribute("title");
					if (!(grandChildTableImageTitle.contains("View children"))) {

						String childTablerowTitle = wcmalrtDriver
								.findElement(By.xpath("//tr[" + gctc + "]//td[2]//img[2]/following::td[1]//a/span"))
								.getText();
						grandChildTableRowContent.add(childTablerowTitle);
					}

					if (grandChildTableRowContent.size() == totalCount) {
						break;
					}

				}
				
				
				if(grandChildTableRowContent.size()==0)
				{
					System.out.println("Checking Categories for grand Child Table index page::" + grandChildTableName);

					for (int checkTableCategory = 1; checkTableCategory <= totalCountAfterComparisony; checkTableCategory++) {
						WebElement tableCategories = wcmalrtDriver.findElement(
								By.xpath("//tr[" + checkTableCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
						String childCategoryTitleGCIP = tableCategories.getText();

						String childType = checkContentType(childCategoryTitleGCIP);
						if (childType.contains("SAT-Default Sub-Site Area")) {
							grandChildtable_CategoriesList.add(childCategoryTitleGCIP);
						}
					}

				}
				
			for(int tcc=0;tcc<grandChildtable_CategoriesList.size();tcc++)
				{
				System.out.println(
						"Reading content for Category " + grandChildtable_CategoriesList.get(tcc)
								+ " of Grand Child Index Page" + grandChildTableName);// SALES

				if (ValidationFactory.isElementPresent(
						By.xpath("//a[.='" + grandChildtable_CategoriesList.get(tcc)
								+ "' and starts-with(@title,'View children')]"))) {
					WebElement finalGrandchildCategory = wcmalrtDriver
							.findElement(By.xpath("//a[.='" + grandChildtable_CategoriesList.get(tcc)
									+ "' and starts-with(@title,'View children')]"));

					String grandChildCategoryTitle = finalGrandchildCategory.getText();
					finalGrandchildCategory.click(); // Child index page first category clicked

					grandChildTableChildIsCategory.put("3rdLevelGrandChildIndexPageCategories",
							grandChildCategoryTitle);

					checkForNestedcategories(grandChildTableChildIsCategory,
							"3rdLevelGrandChildIndexPageCategories");
					
					wcmalrtDriver
							.findElement(By.xpath(
									"//a[contains(.,'" + grandChildTableName + "')]"))
							.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::"
							+ grandChildtable_CategoriesList.get(tcc));
				}

			}
				
		if (grandChildTableRowContent.size() > 0) {
					for (int gctrc = 0; gctrc < grandChildTableRowContent.size(); gctrc++) {

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + grandChildTableRowContent.get(gctrc)
								+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
							wcmalrtDriver.findElement(By.xpath("//a[.='" + grandChildTableRowContent.get(gctrc)
									+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
									.click();

							// checking for Index page inside the link
							/*String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();

							if ((conType.equals("AT-Child Index Page") || conType.equals("AT-GrandChild Index Page"))
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {
								System.out.println("This is an index page link");
								String[] indexPageTitleArray = webContentLinkText.getText().split("/");
								String grandChildTableContentOtherThenRow = indexPageTitleArray[indexPageTitleArray.length
										- 1].trim();

								grandChildTableIndexPageContent.add(grandChildTableContentOtherThenRow);
							}*/

							String wcmTCID = testCaseID + testcaseNumber;
							grandChildTableContent.put("Test Case ID", wcmTCID);
							grandChildTableContent.putAll(grandChildTableStructure);

							writeWCMToExcel(grandChildTableContent, "Table");
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();

						} else {
							LogFactory.info(
									"Unable to find the xpath for title::" + grandChildTableRowContent.get(gctrc));
						}
					}
					System.out.println("AT-Index type Content for Grand Child Table::" + grandChildTableName
							+ " is fetched successfully ");

					// now fetching table data apart from ROWS
					/*System.out.println("Grand Child Table::" + grandChildTableName
							+ " has index page type content of size::" + grandChildTableIndexPageContent.size());
					System.out.println("Now reading Grand Child Table's index page content ::" + grandChildTableName);
*/
					//List<String> grandChildTableCategories = new ArrayList<String>();

					//HashMap<String, String> grandChildTableCategoryIndexPages = new HashMap<String, String>();
/*
					for (int tip = 0; tip < grandChildTableIndexPageContent.size(); tip++) {
						String grandChildTableCategory = grandChildTableIndexPageContent.get(tip);
						if (ValidationFactory.isElementPresent(By.xpath(
								"//a[.='" + grandChildTableCategory + "' and starts-with(@title,'View children')]"))) {// Business
																														// Continuation
							String grandChildTableContentType = checkContentType(grandChildTableCategory);

							if (grandChildTableContentType.contains("SAT-Default Sub-Site Area")) {
								grandChildTableCategories.add(grandChildTableCategory);
							}

						} else {
							LogFactory.info("Unable to find the xpath for title::" + grandChildTableCategory);
						}
					}*/
/*
					for (int gctcc = 0; gctcc < grandChildTableCategories.size(); gctcc++) {
						System.out.println("Reading content for Category " + grandChildTableCategories.get(gctcc)
								+ " of Grand Child Table Index Page:" + grandChildTableName);// SALES

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + grandChildTableCategories.get(gctcc)
								+ "' and starts-with(@title,'View children')]"))) {

							WebElement grandChildTableCategoryElement = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + grandChildTableCategories.get(gctcc)
											+ "' and starts-with(@title,'View children')]"));

							String grandChildTableCategoryTitle = grandChildTableCategoryElement.getText();

							grandChildTableCategoryElement.click(); // tools and documents
							System.out.println("Category ::" + grandChildTableCategoryTitle
									+ " for Grand Child Table index page:" + grandChildTableName + " is clicked");// table
																													// index
																													// page
																													// first
																													// category
																													// clicked

							// checking for nested category

							String wcmTCID = testCaseID + testcaseNumber;
							grandChildTableCategoryIndexPages.put("Test Case ID", wcmTCID);
							String levelIs;
							if (nextestLevel.contains("3rdLevelIndexPage")) {
								levelIs = "3rdLevelGrandChildIndexPageCategories";
								grandChildTableCategoryIndexPages.put("3rdLevelGrandChildIndexPageCategories",
										grandChildTableCategoryTitle);
							} else {
								levelIs = "4thLevelGrandChildIndexPageCategories";
								grandChildTableCategoryIndexPages.put("4thLevelGrandChildIndexPageCategories",
										grandChildTableCategoryTitle);

							}
							grandChildTableCategoryIndexPages.putAll(grandChildTableStructure);

							checkForNestedcategories(grandChildTableCategoryIndexPages, levelIs);

							wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + grandChildTableName + "')]"))
									.click();

						} else {
							LogFactory.info(
									"Unable to find the xpath for title::" + grandChildTableCategories.get(gctcc));
						}
					}*/

				}
			} else {
				LogFactory.info("Unable to find the xpath for title::" + grandChildTableName);
			}
		} catch (Exception e) {
			System.out.println("Error while fetching content for Grand Child Table::" + grandChildTableName + " "
					+ e.getMessage().toString());

		}

	}

	/**
	 * @author Yogender singh This method is to fetch the content of a SAT-Landing
	 *         Page @ throws Throwable
	 */

	private static void fetchContentsForlandingPages(List<String> sAT_LandingPages, String departmentName,
			String subDeptsUnderDeptName) throws Throwable {
		System.out.println("**Now Fetching WCM content for Landing pages**");

		HashMap<String, String> wcmKeyValueLP = new HashMap<String, String>();
		try {

			for (int lp = 0; lp < sAT_LandingPages.size(); lp++) {
				System.out.println("SAT Landing page ::" + sAT_LandingPages.get(lp));

				if (ValidationFactory.isElementPresent(By
						.xpath("//a[.='" + sAT_LandingPages.get(lp) + "' and starts-with(@title,'View children')]"))) {
					WebElement landingPage = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + sAT_LandingPages.get(lp) + "' and starts-with(@title,'View children')]"));

					String landingPageTitle = landingPage.getText();

					landingPage.click(); // FIRST Landing PAGE clicked
					// now checking for the child content for the landing page(TCFA index page
					// clicked)

					List<WebElement> allLandingPageChildImages = allChildren;

					// adding contents under Index pages under different lists with Child and no
					// Child
					// creating list for link portlets and grandchilds under Child
					List<String> landingPageChilds = new ArrayList<String>();
					List<String> landingPageLinkPortlets = new ArrayList<String>();

					int totalCountAfterComparison = totalCount;
					if (allLandingPageChildImages.size() < totalCount) {
						totalCountAfterComparison = allLandingPageChildImages.size();
					}

					List<String> IsLanding_Child_Tables = new ArrayList<String>();
					List<String> IsLanding_Child_Index_pages = new ArrayList<String>();
					

					for (int lpc = 1; lpc <= allLandingPageChildImages.size(); lpc++) {
						String landingChildImageTitle = wcmalrtDriver
								.findElement(By.xpath("//tr[" + lpc + "]//td[2]//img[2]")).getAttribute("title");

						if (!(landingChildImageTitle.contains("View children"))) {
							String linkPortlet = wcmalrtDriver
									.findElement(By.xpath("//tr[" + lpc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							landingPageLinkPortlets.add(linkPortlet);
						}

						if (landingPageLinkPortlets.size() == totalCount) {
							break;
						}
					}

					/// writing index page(TCFA_Index_Page) Link Portlets contents

					for (int lplp = 0; lplp < landingPageLinkPortlets.size(); lplp++) {
						System.out.println("fetching link portlet " + landingPageLinkPortlets.get(lplp)
								+ " for Landing page's index page::" + landingPageTitle);
						String indexPageLinks = landingPageLinkPortlets.get(lplp);

						if (ValidationFactory.isElementPresent(By
								.xpath("//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"))) {

							WebElement child12 = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"));
							child12.click();

							// checking for Index page inside the link
							String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();

							if ((conType.equals("AT-Link") || conType.equals("AT-Index Page"))
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {

								String[] indexPageTitleArray = webContentLinkText.getText().split("/");
								String landingPageIndexChild = indexPageTitleArray[indexPageTitleArray.length - 1]
										.trim();
								linksAndSATHashMap.put(landingPageIndexChild, indexPageLinks);
								landingPageChilds.add(landingPageIndexChild);
							}

							String wcmTCID = testCaseID + testcaseNumber;

							wcmKeyValueLP.put("Test Case ID", wcmTCID);
							wcmKeyValueLP.put("DepartmentName", departmentName);
							wcmKeyValueLP.put("2ndLevel", subDeptsUnderDeptName);
							wcmKeyValueLP.put("3rdLevelLandingPage", linksAndSATHashMap.get(landingPageTitle));

							writeWCMToExcel(wcmKeyValueLP, "None");
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();
						} else {
							LogFactory.info("Unable to find the xpath for title::" + indexPageLinks);

						}
					}

					System.out.println("Total child under landing page: " + landingPageTitle
							+ " apart from Link Portlets are::" + landingPageChilds.size());
					System.out.println("Now checking for landing page:" + landingPageTitle
							+ " content type apart from link portlets");
					for (int lpct = 0; lpct < landingPageChilds.size(); lpct++) {

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + landingPageChilds.get(lpct)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement landingChildPage = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ landingPageChilds.get(lpct) + "' and starts-with(@title,'View children')]"));
							String landingChildPageTitle = landingChildPage.getText();

							String landingPageChildType = checkContentType(landingChildPageTitle);
							if (landingPageChildType.contains("SAT-Table Index Page")) {
								IsLanding_Child_Tables.add(landingChildPageTitle);
							} else if (landingPageChildType.contains("SAT-Child IndexPage")) {
								IsLanding_Child_Index_pages.add(landingChildPageTitle);
							}

							System.out.println("This Child of Landing page " + landingChildPageTitle + " is a "
									+ landingPageChildType);
						} else {
							LogFactory.info("Unable to find the xpath for title::" + landingPageChilds.get(lpct));
						}
					}

					System.out.println("Landing page:: " + landingPageTitle + " has " + IsLanding_Child_Tables.size()
							+ " Tables and " + IsLanding_Child_Index_pages.size() + " Index Pages");
					// NOW READING INDEX PAGE TABLES
					HashMap<String, String> landingPageTables = new HashMap<String, String>();
					for (int lpctc = 0; lpctc < numberOfContentsToFetch(IsLanding_Child_Tables); lpctc++) {

						System.out.println("Reading content for Landing Page's:: " + landingPageTitle
								+ " Child table:: " + IsLanding_Child_Tables.get(lpctc));// Child Table

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsLanding_Child_Tables.get(lpctc)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement landingChildTable = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + IsLanding_Child_Tables.get(lpctc)
											+ "' and starts-with(@title,'View children')]"));

							String landingChildTableTitle = landingChildTable.getText();

							String wcmTCID = testCaseID + testcaseNumber;
							HashMap<String, String> tabledata = new HashMap<String, String>();

							tabledata = fetchTablesContent(landingChildTableTitle);

							landingPageTables.put("Test Case ID", wcmTCID);
							landingPageTables.put("DepartmentName", departmentName);
							landingPageTables.put("2ndLevel", subDeptsUnderDeptName);
							landingPageTables.put("3rdLevelLandingPage", linksAndSATHashMap.get(landingPageTitle));
							landingPageTables.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(landingChildTableTitle));

							landingPageTables.putAll(tabledata);
							excelOutput(landingPageTables);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;

							fetchTableRowsContentForChildTable(landingChildTableTitle, landingPageTables,
									"3rdLevelLandingPage");
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsLanding_Child_Tables.get(lpctc));
						}
					} /// all tables for Landing page read successfully

					HashMap<String, String> landingPageChildIndexPage = new HashMap<String, String>();
					// code for landing page child index pages///
					for (int lpcip = 0; lpcip < IsLanding_Child_Index_pages.size(); lpcip++) {

						System.out.println("Reading content for Landing Page's:: " + landingPageTitle + " Child Index Page:: "
								+ IsLanding_Child_Index_pages.get(lpcip));// Child Table

						if (ValidationFactory.isElementPresent(
								By.xpath("//a[.='" + IsLanding_Child_Index_pages.get(lpcip) + "']"))) {
							WebElement landingChildIndexPagee = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + IsLanding_Child_Index_pages.get(lpcip)
											+ "' and starts-with(@title,'View children')]"));

							String landingChildIndexpageTitle = landingChildIndexPagee.getText();

							landingChildIndexPagee.click();

							landingPageChildIndexPage.put("DepartmentName", departmentName);
							landingPageChildIndexPage.put("2ndLevel", subDeptsUnderDeptName);
							landingPageChildIndexPage.put("3rdLevelLandingPage", linksAndSATHashMap.get(landingPageTitle));
							landingPageChildIndexPage.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(landingChildIndexpageTitle));

							fetchContentForChildIndexPage(landingPageChildIndexPage, "3rdLevelChildIndexPage");
							// fetchContentTillGrandChildIndexPagesForLandingPage(IsLanding_Child_Index_pages,
							// landingPageTitle, departmentName, subDeptsUnderDeptName)
							// fetchContentTillGrandChild(SAT_Index_Pages, departmentName,
							// subDeptsUnderDeptName);
						} else {
							LogFactory.info(
									"Unable to find the xpath for title::" + IsLanding_Child_Index_pages.get(lpcip));
						}

					}
				} else {
					LogFactory.info("Unable to find the xpath for title::" + sAT_LandingPages.get(lp));
				}

			}

		} catch (Exception e) {
			System.out.println("Error while fetching landing pages content");
		}

	}

	/**
	 * @author Yogender singh This method is to fetch the Structure of a SAT-Table @
	 * throws Throwable
	 */

	public static HashMap<String, String> fetchTablesContent(String Tablename) throws Throwable {
		HashMap<String, String> dataToFetch = new HashMap<String, String>();
		try {
			String tableName = null;
			String totalColumns = null;
			String sortBy = null;
			String sortOrder = null;
			String yearGrouping = null;
			String Index_Page_Template_Label = null;
			String column1 = null;
			String column2 = null;
			String column3 = null;
			String column4 = null;
			String column5 = null;
			String SATContent = null;
			String libraries = null;
			String headerName = null;
			String footerName = null;

			// HashMap<String,String> dataToFetch=new HashMap<String,String>();
			String fetchAttribute = wcmalrtDriver
					.findElement(By.xpath("//a[.='" + Tablename + "' and contains(@title,'View children')]"))
					.getAttribute("id");

			String[] checkboxNumber = fetchAttribute.split("_");
			String checkboxToClick = checkboxNumber[checkboxNumber.length - 1].trim();

			ValidationFactory.getElementIfPresent(By.xpath("//input[@value='" + checkboxToClick + "']")).click();
			// wcmalrtDriver.findElement(By.xpath("//input[@value='"+checkboxToClick+"']")).click();

			editContent.click();
			// readContent.click();
			libraries = libraryOnPage.getText();
			tableName = wcmalrtDriver.findElement(By.xpath("//input[@id='id_ctrl_name']")).getAttribute("value");
			totalColumns = wcmalrtDriver.findElement(By.xpath(
					"//label[contains(.,'Number of Columns')]//following::select[1]//option[@selected='selected']"))
					.getText();

			String contentTypeIs = contentTypeOnPage.getText();
			String[] ctType = contentTypeIs.split("/");
			SATContent = ctType[ctType.length - 1].trim();

			wcmalrtDriver.switchTo().frame(0);
			String tableHeader = wcmalrtDriver.findElement(By.xpath("//body")).getText();
			System.out.println("**Header for table is::" + tableHeader);
			if (!tableHeader.isEmpty())
				headerName = tableHeader;
			wcmalrtDriver.switchTo().defaultContent();

			sortBy = wcmalrtDriver
					.findElement(By.xpath(
							"//label[contains(.,'Sort By')]//following::select[1]//option[@selected='selected']"))
					.getText();
			sortOrder = wcmalrtDriver
					.findElement(By.xpath(
							"//label[contains(.,'Sort Order')]//following::select[1]//option[@selected='selected']"))
					.getText();
			yearGrouping = wcmalrtDriver.findElement(By.xpath(
					"//label[contains(.,'Year Grouping Required')]//following::span//select//option[@selected='selected']"))
					.getText();

			wcmalrtDriver.switchTo().frame(1);
			String tableFooter = wcmalrtDriver.findElement(By.xpath("//body")).getText();
			System.out.println("**Footer for table is::" + tableFooter);
			if (!tableFooter.isEmpty())
				footerName = tableFooter;
			wcmalrtDriver.switchTo().defaultContent();

			for (int i = 1; i <= Integer.parseInt(totalColumns); i++) {
				String headerValue = wcmalrtDriver
						.findElement(By.xpath(
								"//td//label[contains(.,'Label for Column " + i + "')]//following::div[1]/input"))
						.getAttribute("value");
				if (i == 1)
					column1 = headerValue;
				else if (i == 2 && !(headerValue.isEmpty()))
					column2 = headerValue;
				else if (i == 3 && !(headerValue.isEmpty()))
					column3 = headerValue;
				else if (i == 4 && !(headerValue.isEmpty()))
					column4 = headerValue;
				else if (i == 5 && !(headerValue.isEmpty()))
					column5 = headerValue;

				Index_Page_Template_Label = Index_Page_Template_Label + "," + headerValue;

			}
			if (Index_Page_Template_Label.startsWith("null")) {
				Index_Page_Template_Label = Index_Page_Template_Label.substring(5);
			}

			String Index_Page_Template = "Table_" + Integer.parseInt(totalColumns) + "_columns";
			dataToFetch.put("TitleOfTable", tableName);
			dataToFetch.put("Index_Page_Template", Index_Page_Template);
			dataToFetch.put("Index_Page_Template_Label", Index_Page_Template_Label);
			dataToFetch.put("Sort By", sortBy);
			dataToFetch.put("Sort Order", sortOrder);
			dataToFetch.put("Year Grouping Required", yearGrouping);
			dataToFetch.put("Library", libraries);
			dataToFetch.put("Title", column1);
			dataToFetch.put("Description", column2);
			dataToFetch.put("ReleaseDate", column3);
			dataToFetch.put("Column 4", column4);
			dataToFetch.put("Column 5", column5);
			dataToFetch.put("ContentType", SATContent);
			dataToFetch.put("EXECUTE", "Y");
			dataToFetch.put("Multilingual", multiLingual);
			dataToFetch.put("HeaderName", headerName);
			dataToFetch.put("FooterName", footerName);

			wcmalrtDriver.findElement(By.id("save_and_close")).click();
			// closeContent.click();

			return dataToFetch;
		} catch (Exception e) {
			LogFactory.info("Error while fetching table structure content for:: "+Tablename);

		}
		return dataToFetch;

	}

	/**
	 * @author Yogender singh This method is to check the content type of an
	 *         SAT-Index Page @ throws Throwable
	 */

	private static String checkContentType(String indexchildName) {
		String fetchSiteArea;

		try {
			String fetchAttribute = wcmalrtDriver
					.findElement(
							By.xpath("//a[contains(.,'" + indexchildName + "') and contains(@title,'View children')]"))
					.getAttribute("id");

			String[] checkboxNumber = fetchAttribute.split("_");
			String checkboxToClick = checkboxNumber[checkboxNumber.length - 1].trim();

			wcmalrtDriver.findElement(By.xpath("//input[@value='" + checkboxToClick + "']")).click();

			readContent.click();
			fetchSiteArea = siteArea.getText();

			closeContent.click();

			return fetchSiteArea;
		} catch (Exception e) {

			System.out.println("Error while determining contenet type of:" + indexchildName);

		}
		return null;

	}

	/**
	 * @author Yogender singh This method is to fetch all the Child Level content of
	 *         a SAT-Index page @ throws Throwable
	 */
	public static void fetchContentTillGrandChild(List<String> SAT_Index_Pages, String departmentName,
			String subDeptsUnderDeptName) throws Throwable {

		try {
			System.out.println(
					"Total SAT_INDEX_PAGES for Sub Department::" + subDeptsUnderDeptName + " are:: " + SAT_Index_Pages);
			for (int ip = 0; ip < numberOfContentsToFetch(SAT_Index_Pages); ip++) {
				System.out.println("Fetching index page::" + SAT_Index_Pages.get(ip) + " content under Sub department::"
						+ subDeptsUnderDeptName);

				if (ValidationFactory.isElementPresent(
						By.xpath("//a[.='" + SAT_Index_Pages.get(ip) + "' and starts-with(@title,'View children')]"))) {
					WebElement indexPage = wcmalrtDriver.findElement(By
							.xpath("//a[.='" + SAT_Index_Pages.get(ip) + "' and starts-with(@title,'View children')]"));

					String indexPageTitle = indexPage.getText();

					indexPage.click(); // FIRST INDEX PAGE :---Business continuation /Optimization clicked
					// now checking for the child content for the Index page(TCFA index page
					// clicked)

					List<WebElement> allChildImages = allChildren;

					// adding contents under Index pages under different lists with Child and no
					// Child
					// creating list for link portlets and grandchilds under Child
					List<String> ChildHasGrandChild = new ArrayList<String>();
					List<String> IndexPageLinkPortlets = new ArrayList<String>();

					/*
					 * if(allChildImages.size()<totalCount) { totalCount=allChildImages.size(); }
					 */
					int totalCountAfterComparison = totalCount;
					if (allChildImages.size() < totalCount) {
						totalCountAfterComparison = allChildImages.size();
					}

					System.out.println("Total Count is::" + totalCount);
					List<String> IsChild_Tables = new ArrayList<String>();
					List<String> IsChild_Index_pages = new ArrayList<String>();
					
					List<String> IsChild_Categories = new ArrayList<String>();

					for (int cgc = 1; cgc <= totalCountAfterComparison; cgc++) {
						String childImageTitle = wcmalrtDriver.findElement(By.xpath("//tr[" + cgc + "]//td[2]//img[2]"))
								.getAttribute("title");

						if (!(childImageTitle.contains("View children"))) {
							String ChildWithNoGranChild = wcmalrtDriver
									.findElement(By.xpath("//tr[" + cgc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							IndexPageLinkPortlets.add(ChildWithNoGranChild);
						}

						if (IndexPageLinkPortlets.size() == totalCount) {
							break;
						}

					}

					if (IndexPageLinkPortlets.size() == 0) {
						System.out.println("Checking Categories for index page::" + indexPageTitle);

						for (int checkCategory = 1; checkCategory <= allChildImages.size(); checkCategory++) {
							WebElement childCategories = wcmalrtDriver.findElement(
									By.xpath("//tr[" + checkCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
							String childCategoryTitle = childCategories.getText();

							String childType = checkContentType(childCategoryTitle);
							if (childType.contains("SAT-Default Sub-Site Area")) {
								IsChild_Categories.add(childCategoryTitle);
							}

						}

					}

					/// writing index page(TCFA_Index_Page) Link Portlets contents
					HashMap<String, String> IndexPageLinkPortletsMap = new HashMap<String, String>();
					for (int cng = 0; cng < numberOfContentsToFetch(IndexPageLinkPortlets); cng++) {
						System.out.println("fetching link portlet " + IndexPageLinkPortlets.get(cng)
								+ " for index page::" + indexPageTitle);
						String indexPageLinks = IndexPageLinkPortlets.get(cng);

						if (ValidationFactory.isElementPresent(By
								.xpath("//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"))) {

							WebElement child12 = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"));
							child12.click();

							// checking for Index page inside the link
							String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();
							System.out.println("Content type for the link is::" + conType);

							if (conType.contains("AT-Index Page")
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {
								String[] indexPageTitleArray = (webContentLinkText).getText().split("/");
								String childwithGrandChild = indexPageTitleArray[indexPageTitleArray.length - 1].trim();

								
									linksAndSATHashMap.put(childwithGrandChild, indexPageLinks);

								
								ChildHasGrandChild.add(childwithGrandChild);
								System.out.println("Child Index page::" + childwithGrandChild + " added to list");
							}
							String wcmTCID = testCaseID + testcaseNumber;

							IndexPageLinkPortletsMap.put("Test Case ID", wcmTCID);
							IndexPageLinkPortletsMap.put("DepartmentName", departmentName);
							IndexPageLinkPortletsMap.put("2ndLevel", subDeptsUnderDeptName);
							//IndexPageLinkPortletsMap.put("3rdLevelIndexPage", indexPageTitle);
							IndexPageLinkPortletsMap.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));

							System.out.println("Link portlet Map::" + IndexPageLinkPortletsMap);
							writeWCMToExcel(IndexPageLinkPortletsMap, "None");
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IndexPageLinkPortlets.get(cng));
						}
					}

					//// identifying Index page's(TCFA_Index_Page) "indexPageTitle":- Child Content
					//// Type (TCFA_Child_Index_Pag), Tables or Categories
					System.out.println(
							"Total child under Index page: " + indexPageTitle + " apart from Link Portlets are::"
									+ ChildHasGrandChild.size() + "::" + ChildHasGrandChild);

					for (int z = 0; z < ChildHasGrandChild.size(); z++) {

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + ChildHasGrandChild.get(z)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement childIndexPage = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ ChildHasGrandChild.get(z) + "' and starts-with(@title,'View children')]"));
							String childIndexPageTitle = childIndexPage.getText();

							String childType = checkContentType(childIndexPageTitle);
							if (childType.contains("SAT-Table Index Page")) {
								IsChild_Tables.add(childIndexPageTitle);
							} else if (childType.contains("SAT-Child IndexPage")) {
								IsChild_Index_pages.add(childIndexPageTitle);
							}
							/*
							 * else if(childType.contains("SAT-Default Sub-Site Area")) {
							 * IsChild_Categories.add(childIndexPageTitle); }
							 */

							System.out
									.println("This Child of index page " + childIndexPageTitle + " is a " + childType);

						} else {
							LogFactory.info("Unable to find the xpath for title::" + ChildHasGrandChild.get(z));
						}
					}

					System.out.println("Index page: " + indexPageTitle + " has " + IsChild_Tables.size() + " Tables,"
							+ IsChild_Categories.size() + " Categories and " + IsChild_Index_pages.size()
							+ " Child Index Pages");
					// NOW READING INDEX PAGE TABLES

					HashMap<String, String> indexPageChildTables = new HashMap<String, String>();

					for (int ct = 0; ct < numberOfContentsToFetch(IsChild_Tables); ct++) {

						System.out.println("Reading content for Index Page " + indexPageTitle + " Child table "
								+ IsChild_Tables.get(ct));// Child Table

						if (ValidationFactory.isElementPresent(By.xpath(
								"//a[.='" + IsChild_Tables.get(ct) + "' and starts-with(@title,'View children')]"))) {
							WebElement childTable = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + IsChild_Tables.get(ct) + "' and starts-with(@title,'View children')]"));

							String childtableTitle = childTable.getText();

							String wcmTCID = testCaseID + testcaseNumber;
							HashMap<String, String> tabledata = new HashMap<String, String>();

							tabledata = fetchTablesContent(childtableTitle);

							indexPageChildTables.put("Test Case ID", wcmTCID);
							indexPageChildTables.put("DepartmentName", departmentName);
							indexPageChildTables.put("2ndLevel", subDeptsUnderDeptName);
							indexPageChildTables.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
							indexPageChildTables.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childtableTitle));
							indexPageChildTables.putAll(tabledata);

							excelOutput(indexPageChildTables);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;

							fetchTableRowsContentForChildTable(childtableTitle, indexPageChildTables,
									"3rdLevelIndexPage");
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_Tables.get(ct));
						}
					} /// all tables for index pages read successfully

					HashMap<String, String> indexPageChildCategories = new HashMap<String, String>();
					// now reading index page's child category (SALES AND OPTIMIZATION(Sub
					// Optimization))
					for (int cc = 0; cc < numberOfContentsToFetch(IsChild_Categories); cc++) {
						// Map<String,String> categoryContent=new HashMap<String,String>();
						System.out.println("Reading content for Category " + IsChild_Categories.get(cc)
								+ " of Index Page::" + indexPageTitle);// SALES
						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsChild_Categories.get(cc)
								+ "' and starts-with(@title,'View children')]"))) {

							WebElement childCategory = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ IsChild_Categories.get(cc) + "' and starts-with(@title,'View children')]"));

							String childCategoryTitle = childCategory.getText();

							childCategory.click(); // OPTIMIZATION TOOLS AND INFORMATION

							System.out.println("category " + childCategoryTitle + " for index page::" + indexPageTitle
									+ " is clicked ");// SALES clicked

							// checking for nested category
							indexPageChildCategories.put("DepartmentName", departmentName);
							indexPageChildCategories.put("2ndLevel", subDeptsUnderDeptName);
							indexPageChildCategories.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
							indexPageChildCategories.put("3rdLevelIndexPageCategories", childCategoryTitle);

							checkNestedCategoriesForIndexPage(indexPageChildCategories);

							// wcmalrtDriver.findElement(By.xpath("//a[contains(.,'"+childCategory+"')]")).click();
							wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + indexPageTitle + "')]")).click();
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_Categories.get(cc));
						}

					}

					////// now checking Child Index page contents
					for (int ici = 0; ici < numberOfContentsToFetch(IsChild_Index_pages); ici++) {
						System.out.println("Now fetching content for Child Index Page::" + IsChild_Index_pages.get(ici)
								+ " for index page::" + indexPageTitle);

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsChild_Index_pages.get(ici)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement childIndexPage = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ IsChild_Index_pages.get(ici) + "' and starts-with(@title,'View children')]"));

							String childIndexPageTitle = childIndexPage.getText();

							// now checking for the inside content for the CHILD INDEX PAGE
							// (TCFA_Child_Index_Page)

							childIndexPage.click(); // TCFA Child Index Page clicked
							System.out.println("Child Index page::" + childIndexPageTitle + " clicked successfully");
							List<String> IsGrandChild_Tables = new ArrayList<String>();
							List<String> IsGrandChildIndex_Index_pages = new ArrayList<String>();
							List<String> IsGrandChildIndex_Categories = new ArrayList<String>();

							List<WebElement> allChildForChildIndexPage = allChildren;

							List<String> childIndexPageLinkPortlet = new ArrayList<String>();
							List<String> grandChildContentForChildIndexPage = new ArrayList<String>();

							int totalCountAfterComparisonjh = totalCount;
							if (allChildForChildIndexPage.size() < totalCount) {
								totalCountAfterComparisonjh = allChildForChildIndexPage.size();
							}
							for (int cipc = 1; cipc <= totalCountAfterComparisonjh; cipc++) {
								String childIndexPageContentTitle = wcmalrtDriver
										.findElement(By.xpath("//tr[" + cipc + "]//td[2]//img[2]"))
										.getAttribute("title");

								if (!(childIndexPageContentTitle.contains("View children"))) {
									String ChildindexpageLinkPortletTitle = wcmalrtDriver
											.findElement(By.xpath(
													"//tr[" + cipc + "]//td[2]//img[2]/following::td[1]//a/span"))
											.getText();
									childIndexPageLinkPortlet.add(ChildindexpageLinkPortletTitle);
								}

								if (childIndexPageLinkPortlet.size() == totalCount) {
									break;
								}

							}

							if (childIndexPageLinkPortlet.size() == 0) {
								System.out.println("Checking Categories for Child index page::" + childIndexPageTitle);

								for (int checkChildIndexPageForCategory = 1; checkChildIndexPageForCategory <= allChildImages
										.size(); checkChildIndexPageForCategory++) {
									WebElement ChildIndexPageCategories = wcmalrtDriver
											.findElement(By.xpath("//tr[" + checkChildIndexPageForCategory
													+ "]//td[2]//img[2]/following::td[1]//a/span"));
									String childCategoryTitleCIP = ChildIndexPageCategories.getText();

									String childType = checkContentType(childCategoryTitleCIP);
									if (childType.contains("SAT-Default Sub-Site Area")) {
										IsGrandChildIndex_Categories.add(childCategoryTitleCIP);
									}
								}

							}

							HashMap<String, String> childIndexPageLinkPortletMap = new HashMap<String, String>();
							// Now reading link portlets for CHild index page
							for (int cilp = 0; cilp < numberOfContentsToFetch(childIndexPageLinkPortlet); cilp++) {
								System.out.println("fetching child index page link portlet::"
										+ childIndexPageLinkPortlet.get(cilp));
								String childIndexLinkPortlet = childIndexPageLinkPortlet.get(cilp);

								if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + childIndexLinkPortlet
										+ "' and not(contains(@title, 'View children'))]"))) {
									WebElement childIndexLink = wcmalrtDriver.findElement(By.xpath("//a[.='"
											+ childIndexLinkPortlet + "' and not(contains(@title, 'View children'))]"));
									childIndexLink.click();

									// checking for Index page inside the link
									String contentType = contentTypeOnPage.getText();
									String[] cType = contentType.split("/");
									String conType = cType[cType.length - 1].trim();

									if ((conType.contains("AT-Link") || conType.contains("AT-Index Page")
											|| conType.contains("AT-Child Index Page"))
											&& ValidationFactory.isElementPresent(webContentElement)
											&& !(webContentLinkText.getText().contains("None"))) {
										String[] indexPageTitleArray = webContentLinkText.getText().split("/");
										String childIndexPageWithGrandChild = indexPageTitleArray[indexPageTitleArray.length
												- 1].trim();
											
										linksAndSATHashMap.put(childIndexPageWithGrandChild,childIndexLinkPortlet);
										
										grandChildContentForChildIndexPage.add(childIndexPageWithGrandChild);
									}

									String wcmTCID = testCaseID + testcaseNumber;

									childIndexPageLinkPortletMap.put("Test Case ID", wcmTCID);
									childIndexPageLinkPortletMap.put("DepartmentName", departmentName);
									childIndexPageLinkPortletMap.put("2ndLevel", subDeptsUnderDeptName);
									childIndexPageLinkPortletMap.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
									childIndexPageLinkPortletMap.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childIndexPageTitle));
									writeWCMToExcel(childIndexPageLinkPortletMap, "None");
									writeWCMHeaderContentFinalToExcel();
									testcaseNumber++;
									closeContent.click();
								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ childIndexPageLinkPortlet.get(cilp));
								}
							}

							// creating list for Child index page content apart from link portlets
							for (int gcct = 0; gcct < grandChildContentForChildIndexPage.size(); gcct++) {

								String gccfci = grandChildContentForChildIndexPage.get(gcct);

								if (ValidationFactory.isElementPresent(
										By.xpath("//a[.='" + gccfci + "' and starts-with(@title,'View children')]"))) {
									String grandChildType = checkContentType(gccfci);
									if (grandChildType.contains("SAT-Table Index Page")) {
										IsGrandChild_Tables.add(gccfci);
									}

									else if (grandChildType.contains("SAT-GrandChild Index Page")) {
										IsGrandChildIndex_Index_pages.add(gccfci);
									}
									/*
									 * else if(grandChildType.contains("SAT-Default Sub-Site Area")) {
									 * IsGrandChildIndex_Categories.add(gccfci); }
									 */

									System.out.println("This child:" + gccfci + " is a " + grandChildType);
								} else {
									LogFactory.info("Unable to find the xpath for title::" + gccfci);
								}
							}

							System.out.println("Child Index page::" + childIndexPageTitle + " has total::"
									+ IsGrandChild_Tables.size() + " Grand Child tables, "
									+ IsGrandChildIndex_Index_pages.size() + " Grand Child Index pages and "
									+ IsGrandChildIndex_Categories.size() + " Grand Child categories");

							// fetching content for Child index page's tables
							HashMap<String, String> ChildIndexPageTables = new HashMap<String, String>();
							for (int gct = 0; gct < numberOfContentsToFetch(IsGrandChild_Tables); gct++) {

								System.out.println(
										"Fetching Grand Child Table:: " + IsGrandChild_Tables.get(gct) + " Content");// Child
																														// Table

								if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsGrandChild_Tables.get(gct)
										+ "' and starts-with(@title,'View children')]"))) {
									WebElement grandChildTable = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + IsGrandChild_Tables.get(gct)
													+ "' and starts-with(@title,'View children')]"));

									String grandChildtableTitle = grandChildTable.getText();

									String wcmTCID = testCaseID + testcaseNumber;
									HashMap<String, String> tabledata = new HashMap<String, String>();

									tabledata = fetchTablesContent(grandChildtableTitle);

									ChildIndexPageTables.put("Test Case ID", wcmTCID);
									ChildIndexPageTables.put("DepartmentName", departmentName);
									ChildIndexPageTables.put("2ndLevel", subDeptsUnderDeptName);
									ChildIndexPageTables.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
									ChildIndexPageTables.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childIndexPageTitle));
									ChildIndexPageTables.put("3rdLevelGrandChildIndexPage", linksAndSATHashMap.get(grandChildtableTitle));
									ChildIndexPageTables.putAll(tabledata);

									excelOutput(ChildIndexPageTables);
									writeWCMHeaderContentFinalToExcel();
									testcaseNumber++;

									fetchTableRowsContentForGrandChildTable(grandChildtableTitle, ChildIndexPageTables,
											"3rdLevelIndexPage");
								} else {
									LogFactory.info(
											"Unable to find the xpath for title::" + IsGrandChild_Tables.get(gct));
								}
							}

							/// Fetching content for Child index page's categories

							HashMap<String, String> GrandChildIndex_CategoriesMap = new HashMap<String, String>();
							for (int cc = 0; cc < numberOfContentsToFetch(IsGrandChildIndex_Categories); cc++) {

								// Map<String,String> childCategoryContent=new HashMap<String,String>();
								System.out
										.println("Reading content for Category " + IsGrandChildIndex_Categories.get(cc)
												+ " of Child Index Page:" + childIndexPageTitle);// SALES
								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Categories.get(cc)
												+ "' and starts-with(@title,'View children')]"))) {

									WebElement grandchildCategory = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + IsGrandChildIndex_Categories.get(cc)
													+ "' and starts-with(@title,'View children')]"));

									String childCategoryTitle = grandchildCategory.getText();

									grandchildCategory.click(); // tools and documents
									System.out.println("Category ::" + childCategoryTitle + " for Child index page:"
											+ childIndexPageTitle + " is clicked");// Child index page first category
																					// clicked

									// checking for nested category
									GrandChildIndex_CategoriesMap.put("DepartmentName", departmentName);
									GrandChildIndex_CategoriesMap.put("2ndLevel", subDeptsUnderDeptName);
									GrandChildIndex_CategoriesMap.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
									GrandChildIndex_CategoriesMap.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childIndexPageTitle));
									GrandChildIndex_CategoriesMap.put("3rdLevelChildIndexPageCategories",
											childCategoryTitle);
									// System.out.println(wcmKeyValue1);
									
									checkNestedcategorieForChildIndexPage(GrandChildIndex_CategoriesMap, "3rdLevelChildIndexPageCategories");

									wcmalrtDriver
											.findElement(By.xpath("//a[contains(.,'" + childIndexPageTitle + "')]"))
											.click();
								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ IsGrandChildIndex_Categories.get(cc));
								}

							}

							///// fetching content for GrandChild Index page content
							for (int gcip = 0; gcip < numberOfContentsToFetch(IsGrandChildIndex_Index_pages); gcip++) {
								System.out.println("Now fetching content for Grand child Index Page::"
										+ IsGrandChildIndex_Index_pages.get(gcip) + " under Child index page::"
										+ childIndexPageTitle);

								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Index_pages.get(ici)
												+ "' and starts-with(@title,'View children')]"))) {

									WebElement grandchildIndexPage = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + IsGrandChildIndex_Index_pages.get(ici)
													+ "' and starts-with(@title,'View children')]"));

									String grandChildIndexPageTitle = grandchildIndexPage.getText();

									// now checking for the inside content for the CHILD INDEX PAGE
									// (TCFA_Child_Index_Page)
									grandchildIndexPage.click(); // TCFA Grand Child Index Page clicked

									List<WebElement> allChildFoGrandChildIndexPage = allChildren;

									List<String> grandChildIndexPageLinkPortlet = new ArrayList<String>();
									List<String> grandChildindexPageContent = new ArrayList<String>();
									List<String> IsFinalChild_Tables = new ArrayList<String>();
									List<String> IsFinalChild_Categories = new ArrayList<String>();

									int totalCountAfterComparisonxx = totalCount;
									if (allChildFoGrandChildIndexPage.size() < totalCount) {
										totalCountAfterComparisonxx = allChildFoGrandChildIndexPage.size();
									}
									for (int gcipc = 1; gcipc <= totalCountAfterComparisonxx; gcipc++) {
										String grandChildIndexPageContentTitle = wcmalrtDriver
												.findElement(By.xpath("//tr[" + gcipc + "]//td[2]//img[2]"))
												.getAttribute("title");

										if (!(grandChildIndexPageContentTitle.contains("View children"))) {
											String grandChildindexpageLinkPortletTitle = wcmalrtDriver
													.findElement(By.xpath("//tr[" + gcipc
															+ "]//td[2]//img[2]/following::td[1]//a/span"))
													.getText();
											grandChildIndexPageLinkPortlet.add(grandChildindexpageLinkPortletTitle);
										}

										if (grandChildIndexPageLinkPortlet.size() == totalCount) {
											break;
										}

									}

									if (grandChildIndexPageLinkPortlet.size() == 0) {
										System.out.println("Checking Categories for Grand Child index page::"
												+ grandChildIndexPageTitle);

										for (int checkGCIPForCategory = 1; checkGCIPForCategory <= allChildImages
												.size(); checkGCIPForCategory++) {
											WebElement GCIPCategories = wcmalrtDriver
													.findElement(By.xpath("//tr[" + checkGCIPForCategory
															+ "]//td[2]//img[2]/following::td[1]//a/span"));
											String childCategoryTitleGCIP = GCIPCategories.getText();

											String childType = checkContentType(childCategoryTitleGCIP);
											if (childType.contains("SAT-Default Sub-Site Area")) {
												IsFinalChild_Categories.add(childCategoryTitleGCIP);
											}
										}

									}

									HashMap<String, String> grandChildIndexPageLinkPortletMap = new HashMap<String, String>();
									//// now fetching grand child index page link portlets
									for (int gclp = 0; gclp < numberOfContentsToFetch(
											grandChildIndexPageLinkPortlet); gclp++) {
										System.out.println("Fetching Grand child index page Link portlets::"
												+ grandChildIndexPageLinkPortlet.get(gclp));
										String grandchildContents = grandChildIndexPageLinkPortlet.get(gclp);
										if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + grandchildContents
												+ "' and not(contains(@title, 'View children'))]"))) {
											WebElement grandChildLinks = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + grandchildContents
															+ "' and not(contains(@title, 'View children'))]"));

											grandChildLinks.click();

											// checking for Index page inside the link
											String contentType = contentTypeOnPage.getText();
											String[] cType = contentType.split("/");
											String conType = cType[cType.length - 1].trim();

											if ((conType.contains("AT-Link") || conType.contains("AT-Index Page")
													|| conType.contains("AT-Child Index Page")
													|| conType.contains("AT-GrandChild Index Page"))
													&& ValidationFactory.isElementPresent(webContentElement)
													&& !(webContentLinkText.getText().contains("None"))) {

												String[] indexPageTitleArray = webContentLinkText.getText().split("/");
												String grandChildIndexPageWithGrandChild = indexPageTitleArray[indexPageTitleArray.length
														- 1].trim();
												
													linksAndSATHashMap.put(grandChildIndexPageWithGrandChild, grandchildContents);
												
												grandChildindexPageContent.add(grandChildIndexPageWithGrandChild);
											}

											String wcmTCID = testCaseID + testcaseNumber;

											grandChildIndexPageLinkPortletMap.put("Test Case ID", wcmTCID);
											grandChildIndexPageLinkPortletMap.put("DepartmentName", departmentName);
											grandChildIndexPageLinkPortletMap.put("2ndLevel", subDeptsUnderDeptName);
											grandChildIndexPageLinkPortletMap.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
											grandChildIndexPageLinkPortletMap.put("3rdLevelChildIndexPage",
													linksAndSATHashMap.get(childIndexPageTitle));
											grandChildIndexPageLinkPortletMap.put("3rdLevelGrandChildIndexPage",
													linksAndSATHashMap.get(grandChildIndexPageTitle));

											writeWCMToExcel(grandChildIndexPageLinkPortletMap, "None");
											writeWCMHeaderContentFinalToExcel();
											testcaseNumber++;
											closeContent.click();
										} else {
											LogFactory.info("Unable to find the xpath for title::"
													+ grandChildIndexPageLinkPortlet.get(gclp));
										}
									}

									for (int gchc = 0; gchc < grandChildindexPageContent.size(); gchc++) {
										String grandchildWithContents = grandChildindexPageContent.get(gchc);
										if (ValidationFactory
												.isElementPresent(By.xpath("//a[.='" + grandchildWithContents
														+ "' and (contains(@title, 'View children'))]"))) {
											WebElement finalChild = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + grandchildWithContents
															+ "' and (contains(@title, 'View children'))]"));

											String grandChild = finalChild.getText();
											String finalChildType = checkContentType(grandChild);
											if (finalChildType.contains("SAT-Table Index Page")) {
												IsFinalChild_Tables.add(finalChildType);
											}
											/*
											 * else if(finalChildType.contains("SAT-Default Sub-Site Area")) {
											 * IsFinalChild_Categories.add(finalChildType); }
											 */
										} else {
											LogFactory.info(
													"Unable to find the xpath for title::" + grandchildWithContents);
										}
									}

									// Now fetching table content for grand child index page
									HashMap<String, String> grandChildTableCOntent = new HashMap<String, String>();
									for (int gctc = 0; gctc < numberOfContentsToFetch(IsFinalChild_Tables); gctc++) {

										System.out.println("Fetching Grand Child Table:: "
												+ IsFinalChild_Tables.get(gctc) + " Content");// Child Table

										if (ValidationFactory
												.isElementPresent(By.xpath("//a[.='" + IsFinalChild_Tables.get(gctc)
														+ "' and starts-with(@title,'View children')]"))) {
											WebElement finalGrandChildTable = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + IsFinalChild_Tables.get(gctc)
															+ "' and starts-with(@title,'View children')]"));

											String finalGrandChildtableTitle = finalGrandChildTable.getText();

											String wcmTCID = testCaseID + testcaseNumber;
											HashMap<String, String> grandChildtabledata = new HashMap<String, String>();

											grandChildtabledata = fetchTablesContent(finalGrandChildtableTitle);

											grandChildTableCOntent.put("Test Case ID", wcmTCID);
											grandChildTableCOntent.put("DepartmentName", departmentName);
											grandChildTableCOntent.put("2ndLevel", subDeptsUnderDeptName);
											grandChildTableCOntent.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
											grandChildTableCOntent.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childIndexPageTitle));
											grandChildTableCOntent.put("3rdLevelGrandChildIndexPage",
													linksAndSATHashMap.get(grandChildIndexPageTitle));
											grandChildTableCOntent.putAll(grandChildtabledata);

											excelOutput(grandChildTableCOntent);
											writeWCMHeaderContentFinalToExcel();
											testcaseNumber++;
										} else {
											LogFactory.info("Unable to find the xpath for title::"
													+ IsFinalChild_Tables.get(gctc));
										}
									}

									HashMap<String, String> FinalChild_CategoriesMap = new HashMap<String, String>();
									// fetching grand child categoryies content
									for (int gcfcc = 0; gcfcc < numberOfContentsToFetch(
											IsFinalChild_Categories); gcfcc++) {
										System.out.println(
												"Reading content for Category " + IsFinalChild_Categories.get(gcfcc)
														+ " of Grand Child Index Page" + grandChildIndexPageTitle);// SALES

										if (ValidationFactory.isElementPresent(
												By.xpath("//a[.='" + IsFinalChild_Categories.get(gcfcc)
														+ "' and starts-with(@title,'View children')]"))) {
											WebElement finalGrandchildCategory = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + IsFinalChild_Categories.get(gcfcc)
															+ "' and starts-with(@title,'View children')]"));

											String grandChildCategoryTitle = finalGrandchildCategory.getText();
											finalGrandchildCategory.click(); // Child index page first category clicked

											FinalChild_CategoriesMap.put("DepartmentName", departmentName);
											FinalChild_CategoriesMap.put("2ndLevel", subDeptsUnderDeptName);
											FinalChild_CategoriesMap.put("3rdLevelIndexPage", linksAndSATHashMap.get(indexPageTitle));
											FinalChild_CategoriesMap.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childIndexPageTitle));
											FinalChild_CategoriesMap.put("3rdLevelGrandChildIndexPage",
													linksAndSATHashMap.get(grandChildIndexPageTitle));
											FinalChild_CategoriesMap.put("3rdLevelGrandChildIndexPageCategories",
													grandChildCategoryTitle);

											checkForNestedcategories(FinalChild_CategoriesMap,
													"3rdLevelGrandChildIndexPageCategories");
											wcmalrtDriver
													.findElement(By.xpath(
															"//a[contains(.,'" + grandChildIndexPageTitle + "')]"))
													.click();
										} else {
											LogFactory.info("Unable to find the xpath for title::"
													+ IsFinalChild_Categories.get(gcfcc));
										}

									} // end of checking for nested category or not

									wcmalrtDriver.findElement(By.xpath("//a[.='" + childIndexPageTitle + "']")).click();

								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ IsGrandChildIndex_Index_pages.get(ici));
								}
							} /// END OF GRANDCHILD INDEX PAGES

							wcmalrtDriver.findElement(By.xpath("//a[.='" + indexPageTitle + "']")).click();// navigating
																											// back to
																											// index
																											// page
																											// Business
																											// continuation
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_Index_pages.get(ici));
						}

					} // END OF FOR LOOP FOR ALL CHILD INDEX PAGES

					wcmalrtDriver.findElement(By.xpath("//a[.='" + subDeptsUnderDeptName + "']")).click();

				} else {
					LogFactory.info("Unable to find the xpath for title::" + SAT_Index_Pages.get(ip));
				}
			} // END OF FOR LOOP FOR ALL INDEX PAGES

		} // END OF TRY BLOCK
		catch (Exception e) {

			System.out.println(
					"Error while fetching content for " + SAT_Index_Pages + " :: " + e.getMessage().toString());
		}

	} ////// END of fetchCOntentTillGrandChild method

	/**
	 * @author Yogender singh This method is to check if the Category is a nested
	 *         Category or not for a SAT-Index page @ throws Throwable
	 */

	private static void checkNestedCategoriesForIndexPage(HashMap<String, String> wcmKeyValuePair) throws Throwable {

		HashMap<String, String> newWcmKeyValue = new HashMap<String, String>();
		HashMap<String, String> nestedCategoryHashMap = new HashMap<String, String>();
		try {
			System.out.println("**INSIDE METHOD CHECKNESTED CATEGORY FOR INDEX PAGE**");

			System.out.println(
					"now checking content for category::" + wcmKeyValuePair.get("3rdLevelIndexPageCategories"));

			System.out.println("INDEX PAGE Category data is:" + wcmKeyValuePair);
			List<WebElement> categoriesContent = allChildren;

			List<String> categoryContent = new ArrayList<String>();
			List<String> nestedcategoryContent = new ArrayList<String>();
			;
			List<String> indexPageCategorySATList=new ArrayList<String>();

			for (int nc = 1; nc <= categoriesContent.size(); nc++) {
				String isNestedCategoryPresent = wcmalrtDriver.findElement(By.xpath("//tr["+nc+"]//td[2]//img[2]"))
						.getAttribute("title");
				String checkNestedategory = wcmalrtDriver
						.findElement(By.xpath("//tr["+nc+"]//td[2]//img[2]/following::td[1]//a/span")).getText();
				if (isNestedCategoryPresent.contains("View children")) {
					nestedcategoryContent.add(checkNestedategory);
				} else {
					categoryContent.add(checkNestedategory);
				}
			}

			// System.out.println("For category
			// :"+wcmKeyValuePair.get("3rdLevelIndexPageCategories") +" Nested categories
			// are: "+nestedcategoryContent.size()+" And normal content's
			// are:"+categoryContent.size());

			for (int cc = 0; cc < numberOfContentsToFetch(categoryContent); cc++) {
				System.out.println("fetching content for normal category content:" + categoryContent.get(cc));

				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + categoryContent.get(cc)
						+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
					wcmalrtDriver.findElement(By.xpath("//a[.='" + categoryContent.get(cc)
							+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
							.click();
							
							System.out.println("checking and reading AT-Index pages for category::"+wcmKeyValuePair.get("3rdLevelIndexPageCategories"));
							
							String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();

							if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
									|| conType.equals("AT-Child Index Page"))
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {

								String[] indexPageTitleArray = webContentLinkText.getText().split("/");
								String grandChildIndexPageTitle = indexPageTitleArray[indexPageTitleArray.length - 1].trim();
								linksAndSATHashMap.put(grandChildIndexPageTitle, categoryContent.get(cc));
								indexPageCategorySATList.add(grandChildIndexPageTitle);
							}

					String wcmTCID = testCaseID + testcaseNumber;
					newWcmKeyValue.put("Test Case ID", wcmTCID);
					newWcmKeyValue.put("3rdLevelIndexPageCategories", categoryContent.get(cc));
					newWcmKeyValue.putAll(wcmKeyValuePair);
					writeWCMToExcel(newWcmKeyValue, "None");
					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					closeContent.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + categoryContent.get(cc));
				}
			}
			
			List<String> indexPageCategoryCIP= new ArrayList<String>();
			List<String> indexPageCategoryCTIP= new ArrayList<String>();
			for (int ipct = 0; ipct < indexPageCategorySATList.size(); ipct++) {
				String indexPageCategorySATChild = indexPageCategorySATList.get(ipct);
				if (ValidationFactory.isElementPresent(
						By.xpath("//a[.='" + indexPageCategorySATChild + "' and starts-with(@title,'View children')]"))) {

					String grandChildType = checkContentType(indexPageCategorySATChild);
					if (grandChildType.contains("SAT-Table Index Page")) {
						indexPageCategoryCTIP.add(indexPageCategorySATChild);
					} else if (grandChildType.contains("SAT-Child IndexPage")) {
						indexPageCategoryCIP.add(indexPageCategorySATChild);
					}
					/*
					 * else if(grandChildType.contains("SAT-Default Sub-Site Area")) {
					 * IsGrandChildIndex_Categories.add(gccfci); }
					 */
					System.out.println("This child:" + indexPageCategorySATChild + " is a " + grandChildType);
				} else {
					LogFactory.info("Unable to find the xpath for title::" + indexPageCategorySATChild);
				}
			}
			
			
			HashMap<String,String> indexPageCategoryChildtableMap=new HashMap<String,String>();
				for(int ipcTable=0;ipcTable<indexPageCategoryCTIP.size();ipcTable++)
				{
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + indexPageCategoryCTIP.get(ipcTable)
					+ "' and starts-with(@title,'View children')]"))) {
						System.out.println("fetching content for Table::"+indexPageCategoryCTIP.get(ipcTable));
				WebElement childTable = wcmalrtDriver.findElement(By.xpath("//a[.='"
						+ indexPageCategoryCTIP.get(ipcTable) + "' and starts-with(@title,'View children')]"));

				String childtableTitle = childTable.getText();

				String wcmTCID = testCaseID + testcaseNumber;
				HashMap<String, String> tabledata = new HashMap<String, String>();

				tabledata = fetchTablesContent(childtableTitle);

				indexPageCategoryChildtableMap.put("Test Case ID", wcmTCID);
				indexPageCategoryChildtableMap.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childtableTitle));
				indexPageCategoryChildtableMap.putAll(tabledata);

				excelOutput(indexPageCategoryChildtableMap);
				writeWCMHeaderContentFinalToExcel();
				testcaseNumber++;

				fetchTableRowsContentForChildTable(childtableTitle, indexPageCategoryChildtableMap, "3rdLevelIndexPage");
			} else {
				LogFactory.info("Unable to find the xpath for title::" + indexPageCategoryCTIP.get(ipcTable));
			}

		}
				if(indexPageCategoryCTIP.size()>0)
				wcmalrtDriver.findElement(By.xpath("//a[.='"+wcmKeyValuePair.get("3rdLevelIndexPageCategories")+"']")).click();
			
			
			//code for reading Index page categories' CIP contents
				
				
				HashMap<String,String> ipCategoryCIP=new HashMap<String,String>();
				for (int ipcCIP = 0; ipcCIP < indexPageCategoryCIP.size(); ipcCIP++) {
					ipCategoryCIP.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(indexPageCategoryCIP.get(ipcCIP)));
					ipCategoryCIP.putAll(wcmKeyValuePair);
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + indexPageCategoryCIP.get(ipcCIP)
							+ "' and starts-with(@title,'View children')]"))) {

						WebElement categoryChildIndexPage = wcmalrtDriver
								.findElement(By.xpath("//a[.='" + indexPageCategoryCIP.get(ipcCIP)
										+ "' and starts-with(@title,'View children')]"));
						categoryChildIndexPage.click();

						fetchContentForChildIndexPage(ipCategoryCIP, "3rdLevelChildIndexPage");
					} else {
						LogFactory
								.info("Unable to find the xpath for title::" + indexPageCategoryCIP.get(ipcCIP));
					}
				}
			
			if(indexPageCategoryCIP.size()>0)
				wcmalrtDriver.findElement(By.xpath("//a[.='"+wcmKeyValuePair.get("3rdLevelIndexPageCategories")+"']")).click();
			
			
			
			
			
			
			
			
			if (nestedcategoryContent.size() > 0) {
				/*
				 * for(int ncc=0;ncc<numberOfContentsToFetch(nestedcategoryContent);ncc++) {
				 */
				List<String> IsCategoryChild_Index_pages = new ArrayList<String>();
				List<String> IsCategoryChild_NestedCategories = new ArrayList<String>();
				List<String> IsCategoryChild_Table = new ArrayList<String>();

				System.out.println(
						"checking content type of Category::" + wcmKeyValuePair.get("3rdLevelIndexPageCategories"));
				for (int cct = 0; cct < nestedcategoryContent.size(); cct++) {
					WebElement categoryChild = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + nestedcategoryContent.get(cct) + "' and starts-with(@title,'View children')]"));
					String categoryChildTitle = categoryChild.getText();

					String categorysChildType = checkContentType(categoryChildTitle);

					if (categorysChildType.contains("SAT-Child IndexPage")) {
						IsCategoryChild_Index_pages.add(categoryChildTitle);
					} else if (categorysChildType.contains("SAT-Default Sub-Site Area")) {
						IsCategoryChild_NestedCategories.add(categoryChildTitle);
					} else if (categorysChildType.contains("SAT-Table Index Page")) {
						IsCategoryChild_Table.add(categoryChildTitle);
					}

					System.out.println("This Child :" + categoryChildTitle + " of category ::"
							+ wcmKeyValuePair.get("3rdLevelIndexPageCategories") + " is a " + categorysChildType);
				}

				for (int ncct = 0; ncct < IsCategoryChild_NestedCategories.size(); ncct++) {
					System.out.println(
							"fetching content for nested category:" + IsCategoryChild_NestedCategories.get(ncct));

					if (ValidationFactory
							.isElementPresent(By.xpath("//a[.='" + IsCategoryChild_NestedCategories.get(ncct)
									+ "' and (contains(@title, 'View children'))]"))) {
						String nestedcategoryTitle = wcmalrtDriver
								.findElement(By.xpath("//a[.='" + IsCategoryChild_NestedCategories.get(ncct)
										+ "' and (contains(@title, 'View children'))]"))
								.getText();
						// SUB SALES

						wcmalrtDriver.findElement(By.xpath("//a[.='" + IsCategoryChild_NestedCategories.get(ncct)
								+ "' and (contains(@title, 'View children'))]")).click();
						System.out.println("Nested category "+IsCategoryChild_NestedCategories.get(ncct) + " is clicked");
						List<WebElement> nestedcategoriesFinalContent = allChildren;
						List<String> categoryContentc = new ArrayList<String>();
						for (int nc = 1; nc <= nestedcategoriesFinalContent.size(); nc++) {

							String NestedategoryC = wcmalrtDriver
									.findElement(By.xpath("//tr[" + nc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();

							categoryContentc.add(NestedategoryC);

						}

						for (int fc = 0; fc < categoryContentc.size(); fc++) {
							if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + categoryContentc.get(fc)
									+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
								wcmalrtDriver.findElement(By.xpath("//a[.='" + categoryContentc.get(fc)
										+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
										.click();

								String wcmTCID = testCaseID + testcaseNumber;

								nestedCategoryHashMap.put("Test Case ID", wcmTCID);
								nestedCategoryHashMap.put("3rdLevelIndexPageNestedCategories", nestedcategoryTitle);
								nestedCategoryHashMap.putAll(wcmKeyValuePair);

								writeWCMToExcel(nestedCategoryHashMap, "None");

								writeWCMHeaderContentFinalToExcel();
								testcaseNumber++;
								closeContent.click();
							} else {
								System.out.println("content in list not found");
							}
						}
						wcmalrtDriver
								.findElement(
										By.xpath("//a[.='" + wcmKeyValuePair.get("3rdLevelIndexPageCategories") + "']"))
								.click();
					} else {
						LogFactory.info(
								"Unable to find the xpath for title::" + IsCategoryChild_NestedCategories.get(ncct));
					}

				}
				if (IsCategoryChild_NestedCategories.size() > 0) {
					wcmalrtDriver
							.findElement(
									By.xpath("//li[.='" + wcmKeyValuePair.get("3rdLevelIndexPageCategories") + "']"))
							.click();
				}

				// now reading child index page content for category
				HashMap<String,String> catChildIP=new HashMap<String,String>();
				for (int cciip = 0; cciip < IsCategoryChild_Index_pages.size(); cciip++) {
					catChildIP.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(IsCategoryChild_Index_pages.get(cciip)));
					catChildIP.putAll(wcmKeyValuePair);
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsCategoryChild_Index_pages.get(cciip)
							+ "' and starts-with(@title,'View children')]"))) {

						WebElement categoryChildIndexPage = wcmalrtDriver
								.findElement(By.xpath("//a[.='" + IsCategoryChild_Index_pages.get(cciip)
										+ "' and starts-with(@title,'View children')]"));
						categoryChildIndexPage.click();

						fetchContentForChildIndexPage(catChildIP, "3rdLevelChildIndexPage");
					} else {
						LogFactory
								.info("Unable to find the xpath for title::" + IsCategoryChild_Index_pages.get(cciip));
					}
				}

				HashMap<String, String> categoryChildtable = new HashMap<String, String>();
				for (int ccit = 0; ccit < IsCategoryChild_Table.size(); ccit++) {
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsCategoryChild_Table.get(ccit)
							+ "' and starts-with(@title,'View children')]"))) {
						WebElement childTable = wcmalrtDriver.findElement(By.xpath("//a[.='"
								+ IsCategoryChild_Table.get(ccit) + "' and starts-with(@title,'View children')]"));

						String childtableTitle = childTable.getText();

						String wcmTCID = testCaseID + testcaseNumber;
						HashMap<String, String> tabledata = new HashMap<String, String>();

						tabledata = fetchTablesContent(childtableTitle);

						categoryChildtable.put("Test Case ID", wcmTCID);
						
						categoryChildtable.put("3rdLevelChildIndexPage", linksAndSATHashMap.get(childtableTitle));
						categoryChildtable.putAll(wcmKeyValuePair);
						categoryChildtable.putAll(tabledata);

						excelOutput(categoryChildtable);
						writeWCMHeaderContentFinalToExcel();
						testcaseNumber++;

						fetchTableRowsContentForChildTable(childtableTitle, categoryChildtable, "3rdLevelIndexPage");
					} else {
						LogFactory.info("Unable to find the xpath for title::" + IsCategoryChild_Table.get(ccit));
					}

				}
			}
		}

		catch (Exception e) {

			System.out.println("Error while checking for Nested Categories for Index page" + e.getMessage().toString());
		}
	}

	/**
	 * @author Yogender singh This method is to fetch all the Child Level content of
	 *         a SAT- Child Index page @ throws Throwable
	 */

	private static void fetchContentForChildIndexPage(HashMap<String, String> tableChildIsChildIndexPage, String Level)
			throws Throwable {
		try {
			List<String> IsGrandChild_Tables = new ArrayList<String>();
			List<String> IsGrandChildIndex_Index_pages = new ArrayList<String>();
			List<String> IsGrandChildIndex_Categories = new ArrayList<String>();
			List<WebElement> allChildForChildIndexPage = allChildren;
			List<String> childIndexPageLinkPortlet = new ArrayList<String>();
			List<String> grandChildContentForChildIndexPage = new ArrayList<String>();

			int totalCountAfterComparisonsd = totalCount;
			if (allChildForChildIndexPage.size() < totalCount) {
				totalCountAfterComparisonsd = allChildForChildIndexPage.size();
			}
			for (int cipc = 1; cipc <= totalCountAfterComparisonsd; cipc++) {
				String childIndexPageContentTitle = wcmalrtDriver
						.findElement(By.xpath("//tr[" + cipc + "]//td[2]//img[2]")).getAttribute("title");

				if (!(childIndexPageContentTitle.contains("View children"))) {
					String ChildindexpageLinkPortletTitle = wcmalrtDriver
							.findElement(By.xpath("//tr[" + cipc + "]//td[2]//img[2]/following::td[1]//a/span"))
							.getText();
					childIndexPageLinkPortlet.add(ChildindexpageLinkPortletTitle);
				}

				if (childIndexPageLinkPortlet.size() == totalCount) {
					break;
				}

			}

			if (childIndexPageLinkPortlet.size() == 0) {
				System.out.println("Checking Categories for Child index page::" + tableChildIsChildIndexPage.get(Level));

				for (int checkCIPForCategory = 1; checkCIPForCategory <= allChildForChildIndexPage
						.size(); checkCIPForCategory++) {
					WebElement GCIPCategories = wcmalrtDriver.findElement(
							By.xpath("//tr[" + checkCIPForCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
					String childCategoryTitleGCIP = GCIPCategories.getText();

					String childType = checkContentType(childCategoryTitleGCIP);
					if (childType.contains("SAT-Default Sub-Site Area")) {
						IsGrandChildIndex_Categories.add(childCategoryTitleGCIP);
					}
				}

			}

			HashMap<String,String> cipChildLinksMap=new HashMap<String,String>();
			// Now reading link portlets for CHild index page
			for (int cilp = 0; cilp < numberOfContentsToFetch(childIndexPageLinkPortlet); cilp++) {

				System.out.println("fetching child index page link portlet:;" + childIndexPageLinkPortlet.get(cilp));
				String childIndexLinkPortlet = childIndexPageLinkPortlet.get(cilp);
				if (ValidationFactory.isElementPresent(By
						.xpath("//a[.='" + childIndexLinkPortlet + "' and not(contains(@title, 'View children'))]"))) {

					WebElement childIndexLink = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + childIndexLinkPortlet + "' and not(contains(@title, 'View children'))]"));
					childIndexLink.click();

					// checking for Index page inside the link
					String contentType = contentTypeOnPage.getText();
					String[] cType = contentType.split("/");
					String conType = cType[cType.length - 1].trim();

					if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
							|| conType.equals("AT-Child Index Page"))
							&& ValidationFactory.isElementPresent(webContentElement)
							&& !(webContentLinkText.getText().contains("None"))) {

						String[] indexPageTitleArray = webContentLinkText.getText().split("/");
						String grandChildIndexPageTitle = indexPageTitleArray[indexPageTitleArray.length - 1].trim();
						linksAndSATHashMap.put(grandChildIndexPageTitle, childIndexLinkPortlet);
						grandChildContentForChildIndexPage.add(grandChildIndexPageTitle);
					}

					String wcmTCID = testCaseID + testcaseNumber;

					cipChildLinksMap.put("Test Case ID", wcmTCID);
					cipChildLinksMap.putAll(tableChildIsChildIndexPage);
					// wcmKeyValuePair.put("3rdLevelChildIndexPage",childIndexPageTitle);
					writeWCMToExcel(cipChildLinksMap, "None");
					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					closeContent.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + childIndexPageLinkPortlet.get(cilp));
				}
			}
			// creating list for Child index page content apart from link portlets
			for (int gcct = 0; gcct < grandChildContentForChildIndexPage.size(); gcct++) {
				String gccfci = grandChildContentForChildIndexPage.get(gcct);
				if (ValidationFactory.isElementPresent(
						By.xpath("//a[.='" + gccfci + "' and starts-with(@title,'View children')]"))) {

					String grandChildType = checkContentType(gccfci);
					if (grandChildType.contains("SAT-Table Index Page")) {
						IsGrandChild_Tables.add(gccfci);
					} else if (grandChildType.contains("SAT-GrandChild Index Page")) {
						IsGrandChildIndex_Index_pages.add(gccfci);
					}
					/*
					 * else if(grandChildType.contains("SAT-Default Sub-Site Area")) {
					 * IsGrandChildIndex_Categories.add(gccfci); }
					 */
					System.out.println("This child:" + gccfci + " is a " + grandChildType);
				} else {
					LogFactory.info("Unable to find the xpath for title::" + gccfci);
				}
			}
			System.out.println("Child Index page::" + tableChildIsChildIndexPage.get(Level) + " has total::"
					+ IsGrandChild_Tables.size() + " tables, " + IsGrandChildIndex_Index_pages.size()
					+ " grand child Index pages and " + IsGrandChildIndex_Categories.size() + " categories");

			// fetching content for Child index page's tables
			HashMap<String,String> cipChildTableMap=new HashMap<String,String>();
			for (int gct = 0; gct < numberOfContentsToFetch(IsGrandChild_Tables); gct++) {
				System.out.println("Fetching Grand Child Table:: " + IsGrandChild_Tables.get(gct) + " Contents");// Child
																												// Table

				if (ValidationFactory.isElementPresent(By.xpath(
						"//a[.='" + IsGrandChild_Tables.get(gct) + "' and starts-with(@title,'View children')]"))) {
					WebElement grandChildTable = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + IsGrandChild_Tables.get(gct) + "' and starts-with(@title,'View children')]"));
					String grandChildtableTitle = grandChildTable.getText();
					String wcmTCID = testCaseID + testcaseNumber;
					Map<String, String> tabledata = new HashMap<String, String>();
					tabledata = fetchTablesContent(grandChildtableTitle);
					
					cipChildTableMap.put("Test Case ID", wcmTCID);
					cipChildTableMap.putAll(tableChildIsChildIndexPage);
					cipChildTableMap.putAll(tabledata);

					if (Level.contains("3rdLevelChildIndexPage") || Level.contains("3rdLevelLandingPage")) {

						cipChildTableMap.put("3rdLevelGrandChildIndexPage", linksAndSATHashMap.get(grandChildtableTitle));
					} else if (Level.contains("4thLevelChildIndexPage")) {
						cipChildTableMap.put("4thLevelGrandChildIndexPage", linksAndSATHashMap.get(grandChildtableTitle));

					}
					excelOutput(cipChildTableMap);

					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					
					fetchTableRowsContentForGrandChildTable(grandChildtableTitle, cipChildTableMap, Level);

				} else {
					LogFactory.info("Unable to find the xpath for title::" + IsGrandChild_Tables.get(gct));
				}
			}
			/// Fetching content for Child index page's categories
			
			HashMap<String,String> cipChildCategory=new HashMap<String,String>();
			for (int cc = 0; cc < numberOfContentsToFetch(IsGrandChildIndex_Categories); cc++) {
				System.out.println("Reading content for Category:: " + IsGrandChildIndex_Categories.get(cc)
						+ " of Child Index Page:" + tableChildIsChildIndexPage.get("3rdLevelChildIndexPage"));// SALES

				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Categories.get(cc)
						+ "' and starts-with(@title,'View children')]"))) {
					WebElement grandchildCategory = wcmalrtDriver.findElement(By.xpath("//a[.='"
							+ IsGrandChildIndex_Categories.get(cc) + "' and starts-with(@title,'View children')]"));
					String childCategoryTitle = grandchildCategory.getText();
					grandchildCategory.click(); // tools and documents
					System.out.println("Category ::" + childCategoryTitle + " for Child index page:"
							+ tableChildIsChildIndexPage.get(Level) + " is clicked");// Child index
																										// page first
																										// category
																										// clicked
					// checking for nested category
					cipChildCategory.putAll(tableChildIsChildIndexPage);
					String levelToFwd;
					if (Level.contains("3rdLevelChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
						levelToFwd = "3rdLevelChildIndexPageCategories";
						cipChildCategory.put("3rdLevelChildIndexPageCategories", childCategoryTitle);
					} else {
						levelToFwd = "4thLevelChildIndexPageCategories";
						cipChildCategory.put("4thLevelChildIndexPageCategories", childCategoryTitle);
					}
					// System.out.println(wcmKeyValue1);
					
					checkNestedcategorieForChildIndexPage(cipChildCategory, levelToFwd);

					wcmalrtDriver
							.findElement(By.xpath("//a[contains(.,'" + tableChildIsChildIndexPage.get(Level) + "')]"))
							.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + IsGrandChildIndex_Categories.get(cc));
				}
			}

			if (Level.contains("3rdLevelChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
				wcmalrtDriver.findElement(By.xpath(
						"//a[contains(.,'"+tableChildIsChildIndexPage.get("3rdLevelIndexPageCategories")+"')]"))
						.click();
			} else {
				wcmalrtDriver.findElement(By.xpath(
						"//a[contains(.,'"+tableChildIsChildIndexPage.get("4thLevelIndexPageCategories")+"')]"))
						.click();
			}
			///// fetching content for GrandChild Index page content
			for (int gcip = 0; gcip < numberOfContentsToFetch(IsGrandChildIndex_Index_pages); gcip++) {
				System.out.println("Now fetching content for Grand child Index Page::"
						+ IsGrandChildIndex_Index_pages.get(gcip) + " under Child index page::"
						+ tableChildIsChildIndexPage.get("3rdLevelChildIndexPage"));

				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Index_pages.get(gcip)
						+ "' and starts-with(@title,'View children')]"))) {
					WebElement grandchildIndexPage = wcmalrtDriver.findElement(By.xpath("//a[.='"
							+ IsGrandChildIndex_Index_pages.get(gcip) + "' and starts-with(@title,'View children')]"));
					String grandChildIndexPageTitle = grandchildIndexPage.getText();

					// now checking for the inside content for the CHILD INDEX PAGE
					// (TCFA_Child_Index_Page)
					grandchildIndexPage.click(); // TCFA Child Index Page clicked
					List<WebElement> allChildFoGrandChildIndexPage = allChildren;
					List<String> grandChildIndexPageLinkPortlet = new ArrayList<String>();
					List<String> grandChildindexPageContent = new ArrayList<String>();

					List<String> IsFinalChild_Tables = new ArrayList<String>();
					List<String> IsFinalChild_Categories = new ArrayList<String>();

					int totalCountAfterComparisonaz = totalCount;
					if (allChildFoGrandChildIndexPage.size() < totalCount) {
						totalCountAfterComparisonaz = allChildFoGrandChildIndexPage.size();
					}
					for (int gcipc = 1; gcipc <= totalCountAfterComparisonaz; gcipc++) {
						String grandChildIndexPageContentTitle = wcmalrtDriver
								.findElement(By.xpath("//tr[" + gcipc + "]//td[2]//img[2]")).getAttribute("title");

						if (!(grandChildIndexPageContentTitle.contains("View children"))) {
							String grandChildindexpageLinkPortletTitle = wcmalrtDriver
									.findElement(
											By.xpath("//tr[" + gcipc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							grandChildIndexPageLinkPortlet.add(grandChildindexpageLinkPortletTitle);
						}

						if (grandChildIndexPageLinkPortlet.size() == totalCount) {
							break;
						}

					}

					if (grandChildIndexPageLinkPortlet.size() == 0) {
						System.out
								.println("Checking Categories for Grand Child index page::" + grandChildIndexPageTitle);

						for (int checkGCIPForCategory = 1; checkGCIPForCategory <= allChildFoGrandChildIndexPage
								.size(); checkGCIPForCategory++) {
							WebElement GCIPCategories = wcmalrtDriver.findElement(By.xpath(
									"//tr[" + checkGCIPForCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
							String childCategoryTitleGCIP = GCIPCategories.getText();

							String childType = checkContentType(childCategoryTitleGCIP);
							if (childType.contains("SAT-Default Sub-Site Area")) {
								IsFinalChild_Categories.add(childCategoryTitleGCIP);
							}
						}

					}

					
					HashMap<String,String> gcipChildLinksMap=new HashMap<String,String>();
					//// now fetching grand child index page link portlets
					for (int gclp = 0; gclp < numberOfContentsToFetch(grandChildIndexPageLinkPortlet); gclp++) {
						System.out.println("Fetching Grand child index page Link portlets::"
								+ grandChildIndexPageLinkPortlet.get(gclp));
						String grandchildContents = grandChildIndexPageLinkPortlet.get(gclp);
						if (ValidationFactory.isElementPresent(By.xpath(
								"//a[.='" + grandchildContents + "' and not(contains(@title, 'View children'))]"))) {
							WebElement grandChildLinks = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + grandchildContents + "' and not(contains(@title, 'View children'))]"));

							grandChildLinks.click();

							// checking for Index page inside the link
							String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();

							if ((conType.equals("AT-Link") || conType.equals("AT-Child Index Page")
									|| conType.equals("AT-GrandChild Index Page"))
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {

								String[] indexPageTitleArray = webContentLinkText.getText().split("/");
								String grandChildIndexPageWithGrandChild = indexPageTitleArray[indexPageTitleArray.length
										- 1].trim();
								linksAndSATHashMap.put(grandChildIndexPageWithGrandChild,grandchildContents);
								grandChildindexPageContent.add(grandChildIndexPageWithGrandChild);
							}

							String wcmTCID = testCaseID + testcaseNumber;
							gcipChildLinksMap.put("Test Case ID", wcmTCID);
							gcipChildLinksMap.putAll(tableChildIsChildIndexPage);
							

							if (Level.contains("3rdLevelChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
								gcipChildLinksMap.put("3rdLevelGrandChildIndexPage", grandchildContents);
							} else {
								gcipChildLinksMap.put("4thLevelGrandChildIndexPage", grandchildContents);
							}

							writeWCMToExcel(gcipChildLinksMap, "None");
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();
						} else {
							LogFactory.info(
									"Unable to find the xpath for title::" + grandChildIndexPageLinkPortlet.get(gclp));
						}
					}

					for (int gchc = 0; gchc < grandChildindexPageContent.size(); gchc++) {
						String grandchildWithContents = grandChildindexPageContent.get(gchc);
						if (ValidationFactory.isElementPresent(By.xpath(
								"//a[.='" + grandchildWithContents + "' and (contains(@title, 'View children'))]"))) {
							WebElement finalChild = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + grandchildWithContents + "' and (contains(@title, 'View children'))]"));

							String grandChild = finalChild.getText();
							String finalChildType = checkContentType(grandChild);
							if (finalChildType.contains("SAT-Table Index Page")) {
								IsFinalChild_Tables.add(finalChildType);
							}
							/*
							 * else if(finalChildType.contains("SAT-Default Sub-Site Area")) {
							 * IsFinalChild_Categories.add(finalChildType); }
							 */
						} else {
							LogFactory.info("Unable to find the xpath for title::" + grandchildWithContents);
						}
					}
					// Now fetching table content for grand child index page
					HashMap<String,String> gcipChildTable=new HashMap<String,String>();
					for (int gctc = 0; gctc < numberOfContentsToFetch(IsFinalChild_Tables); gctc++) {
						System.out
								.println("Fetching Grand Child Table:: " + IsFinalChild_Tables.get(gctc) + " Content");// Child
																														// Table

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsFinalChild_Tables.get(gctc)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement finalGrandChildTable = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ IsFinalChild_Tables.get(gctc) + "' and starts-with(@title,'View children')]"));
							String finalGrandChildtableTitle = finalGrandChildTable.getText();
							String wcmTCID = testCaseID + testcaseNumber;
							Map<String, String> grandChildtabledata = new HashMap<String, String>();
							grandChildtabledata = fetchTablesContent(finalGrandChildtableTitle);
							gcipChildTable.put("Test Case ID", wcmTCID);
							gcipChildTable.putAll(tableChildIsChildIndexPage);
							

							if (Level.contains("3rdLevelChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
								gcipChildTable.put("3rdLevelGrandChildIndexPageCategories",
										linksAndSATHashMap.get(grandChildIndexPageTitle));
							} else {
								gcipChildTable.put("4thLevelGrandChildIndexPageCategories",
										linksAndSATHashMap.get(grandChildIndexPageTitle));
							}
							gcipChildTable.putAll(grandChildtabledata);
							excelOutput(gcipChildTable);

							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsFinalChild_Tables.get(gctc));
						}
					}
					// fetching grand child categoryies content
					HashMap<String,String> gcipChildCategory= new HashMap<String,String>();
					for (int gcfcc = 0; gcfcc < numberOfContentsToFetch(IsFinalChild_Categories); gcfcc++) {
						System.out.println("Reading content for Category " + IsFinalChild_Categories.get(gcfcc)
								+ " of Grand Child Index Page" + grandChildIndexPageTitle);// SALES

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsFinalChild_Categories.get(gcfcc)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement finalGrandchildCategory = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + IsFinalChild_Categories.get(gcfcc)
											+ "' and starts-with(@title,'View children')]"));
							String grandChildCategoryTitle = finalGrandchildCategory.getText();
							finalGrandchildCategory.click(); // Child index page first category clicked

							if (Level.contains("3rdLevelChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
								
								gcipChildCategory.put("3rdLevelGrandChildIndexPage", linksAndSATHashMap.get(grandChildIndexPageTitle));
								gcipChildCategory.put("3rdLevelGrandChildIndexPageCategories",
										grandChildCategoryTitle);
								gcipChildCategory.putAll(tableChildIsChildIndexPage);
								checkForNestedcategories(gcipChildCategory,
										"3rdLevelGrandChildIndexPageCategories");
							} else {
								
								gcipChildCategory.put("4thLevelGrandChildIndexPage", linksAndSATHashMap.get(grandChildIndexPageTitle));
								gcipChildCategory.put("4thLevelGrandChildIndexPageCategories",
										grandChildCategoryTitle);
								gcipChildCategory.putAll(tableChildIsChildIndexPage);
								checkForNestedcategories(gcipChildCategory,
										"4thLevelGrandChildIndexPageCategories");
							}

							wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + grandChildIndexPageTitle + "')]"))
									.click();
						} else {
							LogFactory
									.info("Unable to find the xpath for title::" + IsFinalChild_Categories.get(gcfcc));
						}
					} // end of checking for nested category or not
					wcmalrtDriver.findElement(By.xpath("//a[.='" + tableChildIsChildIndexPage.get(Level) + "']"))
							.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + IsGrandChildIndex_Index_pages.get(gcip));
				}

			} /// END OF GRANDCHILD INDEX PAGES
				// wcmalrtDriver.findElement(By.xpath("//a[.='"+indexPageTitle+"']")).click();//
				/// navigating back to index page Business continuation
		} // END OF FOR LOOP FOR ALL CHILD INDEX PAGES
		catch (Exception e) {
			System.out.println(e.getMessage().toString());

		}
	}

	/**
	 * @author Yogender singh This method is to check if a Category is Nested or
	 *         not @ throws Throwable
	 */

	private static void checkForNestedcategories(HashMap<String, String> wcmKeyValue, String categoryType)
			throws Throwable {

		HashMap<String, String> wcmKeyValue1 = new HashMap<String, String>();
		try {

			System.out.println("now checking content for category::" + wcmKeyValue.get(categoryType));

			System.out.println("category data is:" + wcmKeyValue);
			List<WebElement> categoriesContent = allChildren;

			List<String> categoryContent = new ArrayList<String>();
			List<String> nestedcategoryContent = new ArrayList<String>();
			List<String> notNestedcategoryContent = new ArrayList<String>();

			int totalCountAfterComparisonas = totalCount;
			if (categoriesContent.size() < totalCount) {
				totalCountAfterComparisonas = categoriesContent.size();
			}
			for (int nc = 1; nc <= totalCountAfterComparisonas; nc++) {
				String isNestedCategoryPresent = wcmalrtDriver.findElement(By.xpath("//tr[" + nc + "]//td[2]//img[2]"))
						.getAttribute("title");
				String checkNestedategory = wcmalrtDriver
						.findElement(By.xpath("//tr[" + nc + "]//td[2]//img[2]/following::td[1]//a/span")).getText();

				if (isNestedCategoryPresent.contains("View children")
						&& checkContentType(checkNestedategory).contains("SAT-Default Sub-Site Area")) {
					nestedcategoryContent.add(checkNestedategory);
				} else if (isNestedCategoryPresent.contains("View children")) {
					notNestedcategoryContent.add(checkNestedategory);
				}

				else {
					categoryContent.add(checkNestedategory);
				}
				
				
			}

			System.out.println("For category :" + wcmKeyValue.get(categoryType) + " Nested categories are: "
					+ nestedcategoryContent.size() + " And normal content's are:" + categoryContent.size()+" and SAT's are:"+notNestedcategoryContent.size());

			for (int cc = 0; cc < numberOfContentsToFetch(categoryContent); cc++) {
				System.out.println("fetching content for normal category content:" + categoryContent.get(cc));

				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + categoryContent.get(cc)
						+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
					wcmalrtDriver.findElement(By.xpath("//a[.='" + categoryContent.get(cc)
							+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
							.click();

					String wcmTCID = testCaseID + testcaseNumber;
					wcmKeyValue1.put("Test Case ID", wcmTCID);
					wcmKeyValue1.putAll(wcmKeyValue);
					writeWCMToExcel(wcmKeyValue1, "None");
					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					closeContent.click();

				} else {
					LogFactory.info("Unable to find the xpath for title::" + categoryContent.get(cc));
				}
			}
			
			
			//code for fetching GCIP's category table
			
			
			
			if (nestedcategoryContent.size() > 0) {
				HashMap<String,String> nccForGcip=new HashMap<String,String>();
				for (int ncc = 0; ncc <= numberOfContentsToFetch(nestedcategoryContent); ncc++) {
					System.out.println("fetching content for nested category:" + nestedcategoryContent.get(ncc));

					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + nestedcategoryContent.get(ncc)
							+ "' and (contains(@title, 'View children'))]"))) {
						String nestedcategoryTitle = wcmalrtDriver.findElement(By.xpath("//a[.='"
								+ nestedcategoryContent.get(ncc) + "' and (contains(@title, 'View children'))]"))
								.getText();
						// SUB SALES

						wcmalrtDriver.findElement(By.xpath("//a[.='" + nestedcategoryContent.get(ncc)
								+ "' and (contains(@title, 'View children'))]")).click();
						
						System.out.println("nested category is: "+nestedcategoryContent.get(ncc)+" clicked");
						List<WebElement> categoriesContentFinal = allChildren;

						for (int nccfc = 1; nccfc <= categoriesContentFinal.size(); nccfc++) {

							wcmalrtDriver
									.findElement(By.xpath("//tr[" + nccfc + "]//td[2]//img[2]/following::td[1]//a"))
									.click();

							String wcmTCID = testCaseID + testcaseNumber;

							String[] Key = categoryType.split("Categories");

							nccForGcip.put("Test Case ID", wcmTCID);
							nccForGcip.putAll(wcmKeyValue);
							nccForGcip.put(Key[0] + "NestedCategories", nestedcategoryTitle);

							writeWCMToExcel(nccForGcip, "None");

							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();
						}
						wcmalrtDriver.findElement(By.xpath("//li[.='" + nestedcategoryTitle + "']/preceding::a[.='"
								+ wcmKeyValue.get(categoryType) + "'][1]")).click();
					} else {
						LogFactory.info("Unable to find the xpath for title::" + nestedcategoryContent.get(ncc));
					}
				}
			}

			
		}

		catch (Exception e) {

			System.out.println("Error while checking for Nested Categories " + e.getMessage().toString());
		}

	}

	/**
	 * @author Yogender singh This method is to fetch all the Country Types for a
	 *         content in required format
	 * @return String(Country) @ throws Throwable
	 */
	public static String fetchCountriesList(String country) throws Throwable {

		String countryTitle = "";

		try {
			String[] prodTypeList = country.split(",");
			if (prodTypeList.length >= 2) {
				for (int n = 0; n <= prodTypeList.length - 1; n++) {
					
					String ctry=prodTypeList[n].trim();
					String temp = ctry.substring(35);
					countryTitle = countryTitle + "," + temp.trim();
				}
				return countryTitle.substring(1);
			} else {
				return country.substring(35);
			}
		} catch (Exception e) {
			System.out.println(
					"Error while fetching countries type from content::" + country + " " + e.getMessage().toString());

		}
		return countryTitle;

	}

	/**
	 * @author Yogender singh This method is to +compare the number of contents
	 *         available and to actually fetch @ throws Throwable
	 */

	public static int numberOfContentsToFetch(List<String> listOfContent) throws Throwable {

		int availablecount = 0;
		try {
			System.out.println("Total number of contents to be fetched is:: " + totalCount
					+ " ,And content actually present is::" + listOfContent.size());
			if (listOfContent.size() <= totalCount || totalCount == 0) {
				availablecount = listOfContent.size();
				return availablecount;
			} else {
				availablecount = totalCount;
				return availablecount;
			}

			// return availablecount;

		} catch (Exception e) {

			System.out.println("Error while comparing the number of contents to fetch " + e.getMessage().toString());
		}
		return availablecount;

	}

	/**
	 * @author Yogender singh This method is to fetch all the content for a
	 *         SAT-Folder @ throws Throwable
	 */

	private static void fetchContentsForFolders(List<String> sAT_Folders, String departmentName,
			String subDeptsUnderDeptName) throws Throwable {

		System.out.println("**Now Fetching WCM content for Folders**");

		HashMap<String, String> wcmKeyValueF = new HashMap<String, String>();
		try {
			for (int folder = 1; folder <= numberOfContentsToFetch(sAT_Folders); folder++) {
				System.out.println("SAT Folder page number : " + sAT_Folders.get(folder));//
				//

				if (ValidationFactory.isElementPresent(
						By.xpath("//a[.='" + sAT_Folders.get(folder) + "' and starts-with(@title,'View children')]"))) {
					WebElement folderPage = wcmalrtDriver.findElement(By
							.xpath("//a[.='" + sAT_Folders.get(folder) + "' and starts-with(@title,'View children')]"));

					String folderTitle = folderPage.getText();

					folderPage.click();

					List<WebElement> allfolderChildImages = allChildren;

					List<String> folderChilds = new ArrayList<String>();
					List<String> folderLinkPortlets = new ArrayList<String>();

					List<String> Isfolder_Child_Tables = new ArrayList<String>();
					List<String> Isfolder_Child_Index_pages = new ArrayList<String>();
					List<String> IsChild_LandingPage = new ArrayList<String>();

					int totalCountAfterComparison = totalCount;
					if (allfolderChildImages.size() < totalCount) {
						totalCountAfterComparison = allfolderChildImages.size();
					}
					for (int fc = 1; fc <= totalCountAfterComparison; fc++) {
						String folderChildImageTitle = wcmalrtDriver
								.findElement(By.xpath("//tr[" + fc + "]//td[2]//img[2]")).getAttribute("title");

						if (!(folderChildImageTitle.contains("View children"))) {
							String linkPortlet = wcmalrtDriver
									.findElement(By.xpath("//tr[" + fc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							folderLinkPortlets.add(linkPortlet);
						}
						if (folderLinkPortlets.size() == totalCount) {
							break;
						}

					}

					/// writing index page(TCFA_Index_Page) Link Portlets contents

					for (int flp = 0; flp < numberOfContentsToFetch(folderLinkPortlets); flp++) {
						System.out.println(
								"fetching link portlet " + folderLinkPortlets.get(flp) + " for Folder::" + folderTitle);
						String indexPageLinks = folderLinkPortlets.get(flp);
						if (ValidationFactory.isElementPresent(By
								.xpath("//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"))) {
							WebElement child12 = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"));
							child12.click();

							// checking for Index page inside the link
							String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();

							if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
									|| conType.equals("AT-Child Index Page"))
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {

								String[] indexPageTitleArray = webContentLinkText.getText().split("/");
								String childwithGrandChild = indexPageTitleArray[indexPageTitleArray.length - 1].trim();
								linksAndSATHashMap.put(childwithGrandChild, indexPageLinks);
								folderChilds.add(childwithGrandChild);
							}

							String wcmTCID = testCaseID + testcaseNumber;

							wcmKeyValueF.put("Test Case ID", wcmTCID);
							wcmKeyValueF.put("DepartmentName", departmentName);
							wcmKeyValueF.put("2ndLevel", subDeptsUnderDeptName);
							wcmKeyValueF.put("3rdLevelFolder", folderTitle);

							writeWCMToExcel(wcmKeyValueF, "None");
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();
						} else {
							LogFactory.info("Unable to find the xpath for title::" + folderLinkPortlets.get(flp));
						}
					}

					System.out.println("Total child under folder: " + folderTitle + " apart from Link Portlets are::"
							+ folderChilds.size());
					System.out.println("Now checking for folder's :" + folderTitle
							+ " child content type apart from link portlets");
					for (int Fct = 0; Fct < folderChilds.size(); Fct++) {
						if (ValidationFactory.isElementPresent(By.xpath(
								"//a[.='" + folderChilds.get(Fct) + "' and starts-with(@title,'View children')]"))) {
							WebElement folderChildPage = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + folderChilds.get(Fct) + "' and starts-with(@title,'View children')]"));
							String folderChildPageTitle = folderChildPage.getText();

							String folderPageChildType = checkContentType(folderChildPageTitle);
							if (folderPageChildType.contains("SAT-Table Index Page")) {
								Isfolder_Child_Tables.add(folderChildPageTitle);
							} else if (folderPageChildType.contains("SAT-Index Page")) {
								Isfolder_Child_Index_pages.add(folderChildPageTitle);
							} else if (folderPageChildType.contains("SAT-LandingPage")) {
								IsChild_LandingPage.add(folderChildPageTitle);

							}

							System.out.println(
									"This Child of Folder:: " + folderChildPageTitle + " is a " + folderPageChildType);
						} else {
							LogFactory.info("Unable to find the xpath for title::" + folderChilds.get(Fct));
						}
					}

					System.out.println("Folder: " + folderTitle + " has " + Isfolder_Child_Tables.size()
							+ " Tables and " + Isfolder_Child_Index_pages.size() + " Index Pages");
					// NOW READING INDEX PAGE TABLES
					HashMap<String, String> folderChildTableMap = new HashMap<String, String>();
					for (int fctc = 0; fctc < numberOfContentsToFetch(Isfolder_Child_Tables); fctc++) {
						System.out.println("Reading content for Index Page " + folderTitle + " Child table "
								+ Isfolder_Child_Tables.get(fctc));// Child Table

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + Isfolder_Child_Tables.get(fctc)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement folderChildTable = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ Isfolder_Child_Tables.get(fctc) + "' and starts-with(@title,'View children')]"));

							String folderChildTableTitle = folderChildTable.getText();

							String wcmTCID = testCaseID + testcaseNumber;
							Map<String, String> tabledata = new HashMap<String, String>();

							tabledata = fetchTablesContent(folderChildTableTitle);

							folderChildTableMap.put("Test Case ID", wcmTCID);
							folderChildTableMap.put("DepartmentName", departmentName);
							folderChildTableMap.put("2ndLevel", subDeptsUnderDeptName);
							folderChildTableMap.put("3rdLevelFolder", folderTitle);
							folderChildTableMap.put("4thLevelIndexPage", folderChildTableTitle);
							folderChildTableMap.putAll(tabledata);

							excelOutput(folderChildTableMap);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;

							fetchTableRowsContent(folderChildTableTitle, folderChildTableMap, "4thLevelIndexPage");

							// fetchTableRowsContentForChildTable(folderChildTableTitle,
							// folderChildTableMap)
						} else {
							LogFactory.info("Unable to find the xpath for title::" + Isfolder_Child_Tables.get(fctc));
						}
					}

					// fetching Folder's Index page content
					fetchContentTillGrandChildIndexPagesForFolder(Isfolder_Child_Index_pages, folderTitle,
							departmentName, subDeptsUnderDeptName);
					wcmalrtDriver.findElement(By.xpath("//a[.='" + folderTitle + "']")).click();

					// call landing page
					for (int fctc = 0; fctc < numberOfContentsToFetch(IsChild_LandingPage); fctc++) {
						System.out.println("fetching landing page " + IsChild_LandingPage.get(fctc) + " for Folder::"
								+ folderTitle);
						String fourthLevelLandingPagetitle = IsChild_LandingPage.get(fctc);

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + fourthLevelLandingPagetitle
								+ "' and contains(@title, 'View children')]"))) {
							WebElement childLandingPagefolder = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ fourthLevelLandingPagetitle + "' and contains(@title, 'View children')]"));
							childLandingPagefolder.click();

							System.out.println("Now checking for folder:" + folderTitle
									+ " content type apart from link portlets");

							List<WebElement> allfolderChildrenImages = allChildren;

							List<String> FolderLandingHasChildren = new ArrayList<String>();
							List<String> FolderlandingLinkPortlets = new ArrayList<String>();

							List<String> Islanding_Child_Index_pages = new ArrayList<String>();
							List<String> Islanding_Child_Tables = new ArrayList<String>();

							int totalCountAfterComparison1 = totalCount;
							if (allfolderChildrenImages.size() < totalCount) {
								totalCountAfterComparison1 = allfolderChildrenImages.size();
							}

							for (int sdc = 1; sdc <= totalCountAfterComparison1; sdc++) {
								String subDeptImageTitle = wcmalrtDriver
										.findElement(By.xpath("//tr[" + sdc + "]//td[2]//img[2]"))
										.getAttribute("title");

								if (!(subDeptImageTitle.contains("View children"))) {
									String SubDeptNoChildName = wcmalrtDriver
											.findElement(By
													.xpath("//tr[" + sdc + "]//td[2]//img[2]/following::td[1]//a/span"))
											.getText();
									FolderlandingLinkPortlets.add(SubDeptNoChildName);
								}

								if (FolderlandingLinkPortlets.size() == totalCount) {
									break;
								}

							}

							for (int fcIp = 0; fcIp < FolderlandingLinkPortlets.size(); fcIp++) {
								System.out.println("fetching link portlet " + FolderlandingLinkPortlets.get(fcIp)
										+ " for index page::" + childLandingPagefolder);
								String linkPortletForLandingPagetitle = FolderlandingLinkPortlets.get(fcIp);

								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + linkPortletForLandingPagetitle
												+ "' and not(contains(@title, 'View children'))]"))) {

									WebElement linkPortletForLandingPage = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + linkPortletForLandingPagetitle
													+ "' and not(contains(@title, 'View children'))]"));
									linkPortletForLandingPage.click();

									// checking for Index page inside the link
									String contentType = contentTypeOnPage.getText();
									String[] cType = contentType.split("/");
									String conType = cType[cType.length - 1].trim();

									if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
											|| conType.equals("AT-Child Index Page"))
											&& ValidationFactory.isElementPresent(webContentElement)
											&& !(webContentLinkText.getText().contains("None"))) {

										String[] indexPageTitleArray = webContentLinkText.getText().split("/");
										String indexPageTitle = indexPageTitleArray[indexPageTitleArray.length - 1]
												.trim();
										linksAndSATHashMap.put(indexPageTitle, linkPortletForLandingPagetitle);
										FolderLandingHasChildren.add(indexPageTitle);
									}

									String wcmTCID = testCaseID + testcaseNumber;

									wcmKeyValueF.put("Test Case ID", wcmTCID);
									wcmKeyValueF.put("DepartmentName", departmentName);
									wcmKeyValueF.put("2ndLevel", subDeptsUnderDeptName);
									wcmKeyValueF.put("3rdLevelFolder", folderTitle);
									wcmKeyValueF.put("4thLevelLandingPage", fourthLevelLandingPagetitle);

									writeWCMToExcel(wcmKeyValueF, "None");
									writeWCMHeaderContentFinalToExcel();
									testcaseNumber++;
									closeContent.click();
								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ FolderlandingLinkPortlets.get(fcIp));
								}
							}

							for (int Fct = 0; Fct < FolderLandingHasChildren.size(); Fct++) {

								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + FolderLandingHasChildren.get(Fct)
												+ "' and starts-with(@title,'View children')]"))) {
									WebElement folderChildPage = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + FolderLandingHasChildren.get(Fct)
													+ "' and starts-with(@title,'View children')]"));
									String folderChildPageTitle = folderChildPage.getText();

									String folderPageChildType = checkContentType(folderChildPageTitle);
									if (folderPageChildType.contains("SAT-Table Index Page")) {
										Islanding_Child_Tables.add(folderChildPageTitle);
									} else if (folderPageChildType.contains("SAT-Child Index Page")) {
										Islanding_Child_Index_pages.add(folderChildPageTitle);
									}

									System.out.println("This Child of Folder " + folderChildPageTitle + " is a "
											+ folderPageChildType);
								} else {
									LogFactory.info(
											"Unable to find the xpath for title::" + FolderLandingHasChildren.get(Fct));
								}
							}

							for (int flpcip = 0; flpcip < Islanding_Child_Index_pages.size(); flpcip++) {
								String folderLandingPageChildIndexPageTitle = Islanding_Child_Index_pages.get(flpcip);
								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + folderLandingPageChildIndexPageTitle
												+ "' and (contains(@title, 'View children'))]"))) {
									WebElement folderLandingPageChildIndexPage = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + folderLandingPageChildIndexPageTitle
													+ "' and (contains(@title, 'View children'))]"));
									folderLandingPageChildIndexPage.click();

									String wcmTCID = testCaseID + testcaseNumber;

									wcmKeyValueF.put("Test Case ID", wcmTCID);
									wcmKeyValueF.put("DepartmentName", departmentName);
									wcmKeyValueF.put("2ndLevel", subDeptsUnderDeptName);
									wcmKeyValueF.put("3rdLevelFolder", folderTitle);
									wcmKeyValueF.put("4thLevelLandingPage", fourthLevelLandingPagetitle);
									wcmKeyValueF.put("4thLevelChildIndexPage", folderLandingPageChildIndexPageTitle);

									fetchContentForChildIndexPage(wcmKeyValueF, "4thLevelChildIndexPage");

								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ Islanding_Child_Index_pages.get(flpcip));
								}
							}

							HashMap<String, String> folderLandingPageTableMap = new HashMap<String, String>();
							// add for loop for table and child index pages.
							for (int flpct = 0; flpct < numberOfContentsToFetch(Islanding_Child_Tables); flpct++) {
								System.out.println("Reading content for Index Page " + folderTitle + " Child table "
										+ Islanding_Child_Tables.get(flpct));// Child Table
								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + Islanding_Child_Tables.get(flpct)
												+ "' and starts-with(@title,'View children')]"))) {
									WebElement folderlandingChildTable = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + Islanding_Child_Tables.get(flpct)
													+ "' and starts-with(@title,'View children')]"));

									String folderChildlandingTableTitle = folderlandingChildTable.getText();

									String wcmTCID = testCaseID + testcaseNumber;
									Map<String, String> tabledata = new HashMap<String, String>();

									tabledata = fetchTablesContent(folderChildlandingTableTitle);

									folderLandingPageTableMap.put("Test Case ID", wcmTCID);
									folderLandingPageTableMap.put("DepartmentName", departmentName);
									folderLandingPageTableMap.put("2ndLevel", subDeptsUnderDeptName);
									folderLandingPageTableMap.put("3rdLevelFolder", folderTitle);
									folderLandingPageTableMap.put("4thLevelLandingPage", fourthLevelLandingPagetitle);
									folderLandingPageTableMap.put("4thLevelChildIndexPage",
											folderChildlandingTableTitle);
									folderLandingPageTableMap.putAll(tabledata);

									excelOutput(folderLandingPageTableMap);
									writeWCMHeaderContentFinalToExcel();
									testcaseNumber++;

									fetchTableRowsContentForGrandChildTable(folderChildlandingTableTitle,
											folderLandingPageTableMap, "4thLevelChildIndexPage");
								} else {
									LogFactory.info(
											"Unable to find the xpath for title::" + Islanding_Child_Tables.get(flpct));
								}
							}

						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_LandingPage.get(fctc));
						}

					}
					/// all tables for Landing page read successfully

					wcmalrtDriver.findElement(By.xpath("//a[.='" + subDeptsUnderDeptName + "']")).click();

				} else {
					LogFactory.info("Unable to find the xpath for title::" + sAT_Folders.get(folder));
				}
			}

		} catch (Exception e) {
			System.out.println("Error while fetching folder content");
		}

	}

	/**
	 * @author Yogender singh This method is to fetch all the content for a
	 *         SAT-Index page inside a SAAAT-Folder @ throws Throwable
	 */
	public static void fetchContentTillGrandChildIndexPagesForFolder(List<String> SAT_FolderIndex_Pages,
			String folderName, String departmentName, String subDeptsUnderDeptName) throws Throwable {
		// 3rd lvl folder
		HashMap<String, String> wcmKeyValue1 = new HashMap<String, String>();
		try {
			System.out.println(
					"Fetching Child index pages content for folder under Sub department " + subDeptsUnderDeptName);

			for (int ip = 0; ip < numberOfContentsToFetch(SAT_FolderIndex_Pages); ip++) {
				System.out.println("SAT_Index page number" + (++ip) + " : " + SAT_FolderIndex_Pages.get(ip));

				if (ValidationFactory.isElementPresent(By.xpath(
						"//a[.='" + SAT_FolderIndex_Pages.get(ip) + "' and starts-with(@title,'View children')]"))) {
					WebElement fourththLevelIndexPage = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + SAT_FolderIndex_Pages.get(ip) + "' and starts-with(@title,'View children')]"));

					String fourthLevelIndexPagetitle = fourththLevelIndexPage.getText();

					fourththLevelIndexPage.click(); // FIRST INDEX PAGE :---Business continuation /Optimization clicked
					// now checking for the child content for the Index page(TCFA index page
					// clicked)

					List<WebElement> allChildImages = allChildren;

					// adding contents under Index pages under different lists with Child and no
					// Child
					// creating list for link portlets and grandchilds under Child
					List<String> ChildHasGrandChild = new ArrayList<String>();
					List<String> IndexPageLinkPortlets = new ArrayList<String>();

					List<String> IsChild_Tables = new ArrayList<String>();
					List<String> IsChild_Index_pages = new ArrayList<String>();
					;
					List<String> IsChild_Categories = new ArrayList<String>();

					int totalCountAfterComparison123 = totalCount;
					if (allChildImages.size() < totalCount) {
						totalCountAfterComparison123 = allChildImages.size();
					}

					for (int cgc = 1; cgc <= totalCountAfterComparison123; cgc++) {
						String childImageTitle = wcmalrtDriver.findElement(By.xpath("//tr[" + cgc + "]//td[2]//img[2]"))
								.getAttribute("title");

						if (!(childImageTitle.contains("View children"))) {
							String ChildWithNoGranChild = wcmalrtDriver
									.findElement(By.xpath("//tr[" + cgc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							IndexPageLinkPortlets.add(ChildWithNoGranChild);
						}
						if (IndexPageLinkPortlets.size() == totalCount) {
							break;
						}

					}

					if (IndexPageLinkPortlets.size() == 0) {
						System.out.println("Checking Categories for Child index page::" + IndexPageLinkPortlets);

						for (int checkCIPForCategory = 1; checkCIPForCategory <= allChildImages
								.size(); checkCIPForCategory++) {
							WebElement GCIPCategories = wcmalrtDriver.findElement(By.xpath(
									"//tr[" + checkCIPForCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
							String childCategoryTitleGCIP = GCIPCategories.getText();

							String childType = checkContentType(childCategoryTitleGCIP);
							if (childType.contains("SAT-Default Sub-Site Area")) {
								IsChild_Categories.add(childCategoryTitleGCIP);
							}
						}

					}

					// System.out.println("Total child under Index page:
					// "+fourthLevelIndexPagetitle+" apart from Link Portlets
					// are::"+ChildHasGrandChild.size());

					/// writing index page(TCFA_Index_Page) Link Portlets contents

					for (int cng = 0; cng < numberOfContentsToFetch(IndexPageLinkPortlets); cng++) {
						System.out.println("fetching link portlet " + IndexPageLinkPortlets.get(cng)
								+ " for Folder's index page::" + fourthLevelIndexPagetitle);
						String indexPageLinks = IndexPageLinkPortlets.get(cng);
						if (ValidationFactory.isElementPresent(By
								.xpath("//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"))) {
							WebElement child12 = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + indexPageLinks + "' and not(contains(@title, 'View children'))]"));
							child12.click();

							// checking for Index page inside the link
							String contentType = contentTypeOnPage.getText();
							String[] cType = contentType.split("/");
							String conType = cType[cType.length - 1].trim();

							if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
									|| conType.equals("AT-Child Index Page"))
									&& ValidationFactory.isElementPresent(webContentElement)
									&& !(webContentLinkText.getText().contains("None"))) {

								String[] indexPageTitleArray = webContentLinkText.getText().split("/");
								String childwithGrandChild = indexPageTitleArray[indexPageTitleArray.length - 1].trim();
								linksAndSATHashMap.put(childwithGrandChild, indexPageLinks);
								ChildHasGrandChild.add(childwithGrandChild);
							}

							String wcmTCID = testCaseID + testcaseNumber;

							wcmKeyValue1.put("Test Case ID", wcmTCID);
							wcmKeyValue1.put("DepartmentName", departmentName);
							wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
							wcmKeyValue1.put("3rdLevelFolder", folderName);
							wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);

							writeWCMToExcel(wcmKeyValue1, "None");
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							closeContent.click();
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IndexPageLinkPortlets.get(cng));
						}

					}

					//// identifying Index page's(TCFA_Index_Page) "indexPageTitle":- Child Content
					//// Type (TCFA_Child_Index_Pag), Tables or Categories

					System.out.println("Now checking for Index page:" + fourthLevelIndexPagetitle
							+ " content type apart from link portlets");
					for (int z = 0; z < ChildHasGrandChild.size(); z++) {

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + ChildHasGrandChild.get(z)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement childIndexPage = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ ChildHasGrandChild.get(z) + "' and starts-with(@title,'View children')]"));
							String childIndexPageTitle = childIndexPage.getText();

							String childType = checkContentType(childIndexPageTitle);
							if (childType.contains("SAT-Table Index Page")) {
								IsChild_Tables.add(childIndexPageTitle);
							} else if (childType.contains("SAT-Child IndexPage")) {
								IsChild_Index_pages.add(childIndexPageTitle);
							}
							/*
							 * else if(childType.contains("SAT-Default Sub-Site Area")) {
							 * IsChild_Categories.add(childIndexPageTitle); }
							 */

							System.out
									.println("This Child of index page " + childIndexPageTitle + " is a " + childType);
						} else {
							LogFactory.info("Unable to find the xpath for title::" + ChildHasGrandChild.get(z));
						}
					}

					System.out.println("Index page: " + fourthLevelIndexPagetitle + " has " + IsChild_Tables.size()
							+ " Tables," + IsChild_Categories.size() + " Categories and " + IsChild_Index_pages.size()
							+ " Child Index Pages");
					// NOW READING INDEX PAGE TABLES

					for (int ct = 0; ct < numberOfContentsToFetch(IsChild_Tables); ct++) {
						System.out.println("Reading content for Index Page " + fourthLevelIndexPagetitle
								+ " Child table " + IsChild_Tables.get(ct));// Child Table
						if (ValidationFactory.isElementPresent(By.xpath(
								"//a[.='" + IsChild_Tables.get(ct) + "' and starts-with(@title,'View children')]"))) {
							WebElement childTable = wcmalrtDriver.findElement(By.xpath(
									"//a[.='" + IsChild_Tables.get(ct) + "' and starts-with(@title,'View children')]"));

							String childtableTitle = childTable.getText();

							String wcmTCID = testCaseID + testcaseNumber;
							Map<String, String> tabledata = new HashMap<String, String>();

							tabledata = fetchTablesContent(childtableTitle);

							wcmKeyValue1.put("Test Case ID", wcmTCID);
							wcmKeyValue1.put("DepartmentName", departmentName);
							wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
							wcmKeyValue1.put("3rdLevelFolder", folderName);
							wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
							wcmKeyValue1.put("4thLevelChildIndexPage", childtableTitle);
							wcmKeyValue1.putAll(tabledata);
							excelOutput(wcmKeyValue1);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_Tables.get(ct));
						}

					} /// all tables for index pages read successfully

					// now reading index page's child category (SALES AND OPTIMIZATION(Sub
					// Optimization))
					for (int cc = 0; cc < numberOfContentsToFetch(IsChild_Categories); cc++) {
						// Map<String,String> categoryContent=new HashMap<String,String>();
						System.out.println("Reading content for Category " + IsChild_Categories.get(cc)
								+ " of Index Page::" + fourthLevelIndexPagetitle);// SALES

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsChild_Categories.get(cc)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement childCategory = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ IsChild_Categories.get(cc) + "' and starts-with(@title,'View children')]"));

							String childCategoryTitle = childCategory.getText();

							childCategory.click();
							System.out.println("category " + childCategoryTitle + " for index page::"
									+ fourthLevelIndexPagetitle + " is clicked ");// SALES clicked

							// checking for nested category
							wcmKeyValue1.put("DepartmentName", departmentName);
							wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
							wcmKeyValue1.put("3rdLevelFolder", folderName);
							wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
							wcmKeyValue1.put("4thLevelIndexPageCategories", childCategoryTitle);

							
							checkNestedCategoriesForIndexPage(wcmKeyValue1);

							// wcmalrtDriver.findElement(By.xpath("//a[contains(.,'"+childCategory+"')]")).click();
							wcmalrtDriver.findElement(By.xpath("//a[contains(.,'" + fourthLevelIndexPagetitle + "')]"))
									.click();
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_Categories.get(cc));
						}
					}

					////// now checking Child Index page contents
					for (int ici = 0; ici < numberOfContentsToFetch(IsChild_Index_pages); ici++) {
						System.out.println("Now fetching content for Child Index Page::" + IsChild_Index_pages.get(ici)
								+ " for index page::" + fourthLevelIndexPagetitle);

						if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsChild_Index_pages.get(ici)
								+ "' and starts-with(@title,'View children')]"))) {
							WebElement fourthgrandchildIndexPage = wcmalrtDriver.findElement(By.xpath("//a[.='"
									+ IsChild_Index_pages.get(ici) + "' and starts-with(@title,'View children')]"));

							String fourthlevelchildIndexPageTitle = fourthgrandchildIndexPage.getText();

							// now checking for the inside content for the CHILD INDEX PAGE
							// (TCFA_Child_Index_Page)

							fourthgrandchildIndexPage.click(); // TCFA Child Index Page clicked

							System.out.println(
									"Child Index page::" + fourthlevelchildIndexPageTitle + " clicked successfully");
							List<String> IsGrandChild_Tables = new ArrayList<String>();
							List<String> IsGrandChildIndex_Index_pages = new ArrayList<String>();
							List<String> IsGrandChildIndex_Categories = new ArrayList<String>();

							List<WebElement> allChildForChildIndexPage = allChildren;

							List<String> childIndexPageLinkPortlet = new ArrayList<String>();
							List<String> grandChildContentForChildIndexPage = new ArrayList<String>();

							int totalCountAfterComparisone = totalCount;
							if (allChildForChildIndexPage.size() < totalCount) {
								totalCountAfterComparisone = allChildForChildIndexPage.size();
							}
							for (int cipc = 1; cipc <= totalCountAfterComparisone; cipc++) {
								String childIndexPageContentTitle = wcmalrtDriver
										.findElement(By.xpath("//tr[" + cipc + "]//td[2]//img[2]"))
										.getAttribute("title");

								if (!(childIndexPageContentTitle.contains("View children"))) {
									String ChildindexpageLinkPortletTitle = wcmalrtDriver
											.findElement(By.xpath(
													"//tr[" + cipc + "]//td[2]//img[2]/following::td[1]//a/span"))
											.getText();
									childIndexPageLinkPortlet.add(ChildindexpageLinkPortletTitle);
								}

								if (childIndexPageLinkPortlet.size() == totalCount) {
									break;
								}

							}

							if (childIndexPageLinkPortlet.size() == 0) {
								System.out.println(
										"Checking Categories for Grand Child index page::" + childIndexPageLinkPortlet);

								for (int checkGCIPForCategory = 1; checkGCIPForCategory <= allChildImages
										.size(); checkGCIPForCategory++) {
									WebElement GCIPCategories = wcmalrtDriver.findElement(By.xpath("//tr["
											+ checkGCIPForCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
									String childCategoryTitleGCIP = GCIPCategories.getText();

									String childType = checkContentType(childCategoryTitleGCIP);
									if (childType.contains("SAT-Default Sub-Site Area")) {
										IsGrandChildIndex_Categories.add(childCategoryTitleGCIP);
									}
								}

							}

							// Now reading link portlets for CHild index page
							for (int cilp = 0; cilp < numberOfContentsToFetch(childIndexPageLinkPortlet); cilp++) {
								System.out.println("fetching child index page link portlet:;"
										+ childIndexPageLinkPortlet.get(cilp));
								String childIndexLinkPortlet = childIndexPageLinkPortlet.get(cilp);

								if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + childIndexLinkPortlet
										+ "' and not(contains(@title, 'View children'))]"))) {
									WebElement childIndexLink = wcmalrtDriver.findElement(By.xpath("//a[.='"
											+ childIndexLinkPortlet + "' and not(contains(@title, 'View children'))]"));
									childIndexLink.click();

									// checking for Index page inside the link
									String contentType = contentTypeOnPage.getText();
									String[] cType = contentType.split("/");
									String conType = cType[cType.length - 1].trim();

									if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
											|| conType.equals("AT-Child Index Page"))
											&& ValidationFactory.isElementPresent(webContentElement)
											&& !(webContentLinkText.getText().contains("None"))) {
										String[] indexPageTitleArray = webContentLinkText.getText().split("/");
										String childIndexPageWithGrandChild = indexPageTitleArray[indexPageTitleArray.length
												- 1].trim();
										linksAndSATHashMap.put(childIndexPageWithGrandChild, childIndexLinkPortlet);
										grandChildContentForChildIndexPage.add(childIndexPageWithGrandChild);
									}

									String wcmTCID = testCaseID + testcaseNumber;

									wcmKeyValue1.put("Test Case ID", wcmTCID);
									wcmKeyValue1.put("DepartmentName", departmentName);
									wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
									wcmKeyValue1.put("3rdLevelFolder", folderName);
									wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
									wcmKeyValue1.put("4thLevelChildIndexPage", fourthlevelchildIndexPageTitle);
									writeWCMToExcel(wcmKeyValue1, "None");
									writeWCMHeaderContentFinalToExcel();
									testcaseNumber++;
									closeContent.click();

								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ childIndexPageLinkPortlet.get(cilp));
								}
							}

							// creating list for Child index page content apart from link portlets
							for (int gcct = 0; gcct < grandChildContentForChildIndexPage.size(); gcct++) {
								if (ValidationFactory.isElementPresent(
										By.xpath("//a[.='" + grandChildContentForChildIndexPage.get(gcct)
												+ "' and starts-with(@title,'View children')]"))) {
									String gccfci = grandChildContentForChildIndexPage.get(gcct);
									String grandChildType = checkContentType(gccfci);
									if (grandChildType.contains("SAT-Table Index Page")) {
										IsGrandChild_Tables.add(gccfci);
									}

									else if (grandChildType.contains("SAT-GrandChild Index Page")) {
										IsGrandChildIndex_Index_pages.add(gccfci);
									}
									/*
									 * else if(grandChildType.contains("SAT-Default Sub-Site Area")) {
									 * IsGrandChildIndex_Categories.add(gccfci); }
									 */

									System.out.println("This child:" + gccfci + " is a " + grandChildType);
								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ grandChildContentForChildIndexPage.get(gcct));
								}
							}

							System.out.println("Child Index page::" + fourthlevelchildIndexPageTitle + " has total::"
									+ IsGrandChild_Tables.size() + " tables, " + IsGrandChildIndex_Index_pages.size()
									+ " Index pages and " + IsGrandChildIndex_Categories.size() + " categories");

							// fetching content for Child index page's tables
							for (int gct = 0; gct < numberOfContentsToFetch(IsGrandChild_Tables); gct++) {
								System.out.println(
										"Fetching Grand Child Table:: " + IsGrandChild_Tables.get(gct) + " Content");// Child
																														// Table

								if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsGrandChild_Tables.get(gct)
										+ "' and starts-with(@title,'View children')]"))) {
									WebElement grandChildTable = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + IsGrandChild_Tables.get(gct)
													+ "' and starts-with(@title,'View children')]"));

									String grandChildtableTitle = grandChildTable.getText();

									String wcmTCID = testCaseID + testcaseNumber;
									Map<String, String> tabledata = new HashMap<String, String>();

									tabledata = fetchTablesContent(grandChildtableTitle);

									wcmKeyValue1.put("Test Case ID", wcmTCID);
									wcmKeyValue1.put("DepartmentName", departmentName);
									wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
									wcmKeyValue1.put("3rdLevelFolder", folderName);
									wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
									wcmKeyValue1.put("4thLevelChildIndexPage", fourthlevelchildIndexPageTitle);
									wcmKeyValue1.put("4thLevelGrandChildIndexPage", grandChildtableTitle);

									wcmKeyValue1.putAll(tabledata);

									excelOutput(wcmKeyValue1);
									writeWCMHeaderContentFinalToExcel();
									testcaseNumber++;
								} else {
									LogFactory.info(
											"Unable to find the xpath for title::" + IsGrandChild_Tables.get(gct));
								}

							}

							/// Fetching content for Child index page's categories

							for (int cc = 0; cc < numberOfContentsToFetch(IsGrandChildIndex_Categories); cc++) {
								System.out
										.println("Reading content for Category " + IsGrandChildIndex_Categories.get(cc)
												+ " of Child Index Page:" + fourthlevelchildIndexPageTitle);// SALES

								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Categories.get(cc)
												+ "' and starts-with(@title,'View children')]"))) {
									WebElement grandchildCategory = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + IsGrandChildIndex_Categories.get(cc)
													+ "' and starts-with(@title,'View children')]"));

									String childCategoryTitle = grandchildCategory.getText();

									grandchildCategory.click(); // tools and documents
									System.out.println("Category ::" + childCategoryTitle + " for Child index page:"
											+ fourthlevelchildIndexPageTitle + " is clicked");// Child index page first
																								// category clicked

									// checking for nested category
									wcmKeyValue1.put("DepartmentName", departmentName);
									wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
									wcmKeyValue1.put("3rdLevelFolder", folderName);
									wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
									wcmKeyValue1.put("4thLevelChildIndexPage", fourthlevelchildIndexPageTitle);
									wcmKeyValue1.put("4thLevelChildIndexPageCategories", childCategoryTitle);
									// System.out.println(wcmKeyValue1);
									checkNestedcategorieForChildIndexPage(wcmKeyValue1, "4thLevelChildIndexPageCategories");

									wcmalrtDriver
											.findElement(By
													.xpath("//a[contains(.,'" + fourthlevelchildIndexPageTitle + "')]"))
											.click();

								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ IsGrandChildIndex_Categories.get(cc));
								}
							}

							///// fetching content for GrandChild Index page content
							for (int gcip = 0; gcip < numberOfContentsToFetch(IsGrandChildIndex_Index_pages); gcip++) {
								System.out.println("Now fetching content for Grand child Index Page::"
										+ IsGrandChildIndex_Index_pages.get(gcip) + " under Child index page::"
										+ fourthlevelchildIndexPageTitle);
								if (ValidationFactory
										.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Index_pages.get(ici)
												+ "' and starts-with(@title,'View children')]"))) {
									WebElement grandchildIndexPage = wcmalrtDriver
											.findElement(By.xpath("//a[.='" + IsGrandChildIndex_Index_pages.get(ici)
													+ "' and starts-with(@title,'View children')]"));

									String fouthlevelgrandChildIndexPageTitle = grandchildIndexPage.getText();

									// now checking for the inside content for the CHILD INDEX PAGE
									// (TCFA_Child_Index_Page)
									grandchildIndexPage.click(); // TCFA Child Index Page clicked

									List<WebElement> allChildFoGrandChildIndexPage = allChildren;

									List<String> grandChildIndexPageLinkPortlet = new ArrayList<String>();
									List<String> grandChildindexPageContent = new ArrayList<String>();

									List<String> IsFinalChild_Tables = new ArrayList<String>();
									List<String> IsFinalChild_Categories = new ArrayList<String>();

									int totalCountAfterComparisonn = totalCount;
									if (allChildFoGrandChildIndexPage.size() < totalCount) {
										totalCountAfterComparisonn = allChildFoGrandChildIndexPage.size();
									}

									for (int gcipc = 1; gcipc <= totalCountAfterComparisonn; gcipc++) {
										String grandChildIndexPageContentTitle = wcmalrtDriver
												.findElement(By.xpath("//tr[" + gcipc + "]//td[2]//img[2]"))
												.getAttribute("title");

										if (!(grandChildIndexPageContentTitle.contains("View children"))) {
											String grandChildindexpageLinkPortletTitle = wcmalrtDriver
													.findElement(By.xpath("//tr[" + gcipc
															+ "]//td[2]//img[2]/following::td[1]//a/span"))
													.getText();
											grandChildIndexPageLinkPortlet.add(grandChildindexpageLinkPortletTitle);
										}

										if (grandChildIndexPageLinkPortlet.size() == totalCount) {
											break;
										}
									}

									if (grandChildIndexPageLinkPortlet.size() == 0) {
										System.out.println("Checking Categories for Grand Child index page::"
												+ fouthlevelgrandChildIndexPageTitle);

										for (int checkGCIPForCategory = 1; checkGCIPForCategory <= allChildImages
												.size(); checkGCIPForCategory++) {
											WebElement GCIPCategories = wcmalrtDriver
													.findElement(By.xpath("//tr[" + checkGCIPForCategory
															+ "]//td[2]//img[2]/following::td[1]//a/span"));
											String childCategoryTitleGCIP = GCIPCategories.getText();

											String childType = checkContentType(childCategoryTitleGCIP);
											if (childType.contains("SAT-Default Sub-Site Area")) {
												IsFinalChild_Categories.add(childCategoryTitleGCIP);
											}
										}

									}

									//// now fetching grand child index page link portlets
									for (int gclp = 0; gclp < numberOfContentsToFetch(
											grandChildIndexPageLinkPortlet); gclp++) {
										System.out.println("Fetching Grand child index page Link portlets::"
												+ grandChildIndexPageLinkPortlet.get(gclp));
										String grandchildContents = grandChildIndexPageLinkPortlet.get(gclp);
										if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + grandchildContents
												+ "' and not(contains(@title, 'View children'))]"))) {
											WebElement grandChildLinks = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + grandchildContents
															+ "' and not(contains(@title, 'View children'))]"));

											grandChildLinks.click();

											// checking for Index page inside the link
											String contentType = contentTypeOnPage.getText();
											String[] cType = contentType.split("/");
											String conType = cType[cType.length - 1].trim();

											if ((conType.equals("AT-Link") || conType.equals("AT-Index Page")
													|| conType.equals("AT-Child Index Page")
													|| conType.equals("AT-GrandChild Index Page"))
													&& ValidationFactory.isElementPresent(webContentElement)
													&& !(webContentLinkText.getText().contains("None"))) {

												String[] indexPageTitleArray = webContentLinkText.getText().split("/");
												String grandChildIndexPageWithGrandChild = indexPageTitleArray[indexPageTitleArray.length
														- 1].trim();
												linksAndSATHashMap.put(grandChildIndexPageWithGrandChild, grandchildContents);
												grandChildindexPageContent.add(grandChildIndexPageWithGrandChild);
											}

											String wcmTCID = testCaseID + testcaseNumber;

											wcmKeyValue1.put("Test Case ID", wcmTCID);
											wcmKeyValue1.put("DepartmentName", departmentName);
											wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
											wcmKeyValue1.put("3rdLevelFolder", folderName);
											wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
											wcmKeyValue1.put("4thLevelChildIndexPage", fourthlevelchildIndexPageTitle);
											wcmKeyValue1.put("4thLevelGrandChildIndexPage",
													fouthlevelgrandChildIndexPageTitle);

											writeWCMToExcel(wcmKeyValue1, "None");
											writeWCMHeaderContentFinalToExcel();
											testcaseNumber++;
											closeContent.click();
										} else {
											LogFactory.info("Unable to find the xpath for title::"
													+ grandChildIndexPageLinkPortlet.get(gclp));
										}
									}

									for (int gchc = 0; gchc < grandChildindexPageContent.size(); gchc++) {
										String grandchildWithContents = grandChildindexPageContent.get(gchc);
										if (ValidationFactory
												.isElementPresent(By.xpath("//a[.='" + grandchildWithContents
														+ "' and (contains(@title, 'View children'))]"))) {
											WebElement finalChild = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + grandchildWithContents
															+ "' and (contains(@title, 'View children'))]"));

											String grandChild = finalChild.getText();
											String finalChildType = checkContentType(grandChild);
											if (finalChildType.contains("SAT-Table Index Page")) {
												IsFinalChild_Tables.add(finalChildType);
											} else if (finalChildType.contains("SAT-Default Sub-Site Area")) {
												IsFinalChild_Categories.add(finalChildType);
											}
										} else {
											LogFactory.info(
													"Unable to find the xpath for title::" + grandchildWithContents);
										}
									}

									// Now fetching table content for grand child index page

									for (int gctc = 0; gctc < numberOfContentsToFetch(IsFinalChild_Tables); gctc++) {

										System.out.println("Fetching Grand Child Table:: "
												+ IsFinalChild_Tables.get(gctc) + " Content");// Child Table

										if (ValidationFactory
												.isElementPresent(By.xpath("//a[.='" + IsFinalChild_Tables.get(gctc)
														+ "' and starts-with(@title,'View children')]"))) {
											WebElement finalGrandChildTable = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + IsFinalChild_Tables.get(gctc)
															+ "' and starts-with(@title,'View children')]"));

											String finalGrandChildtableTitle = finalGrandChildTable.getText();

											HashMap<String, String> grandChildtabledata = new HashMap<String, String>();
											grandChildtabledata = fetchTablesContent(finalGrandChildtableTitle);

											String wcmTCID = testCaseID + testcaseNumber;
											wcmKeyValue1.put("Test Case ID", wcmTCID);
											wcmKeyValue1.put("DepartmentName", departmentName);
											wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
											wcmKeyValue1.put("3rdLevelFolder", folderName);
											wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
											wcmKeyValue1.put("4thLevelChildIndexPage", fourthlevelchildIndexPageTitle);
											wcmKeyValue1.put("4thLevelGrandChildIndexPage", finalGrandChildtableTitle);

											wcmKeyValue1.putAll(grandChildtabledata);

											excelOutput(wcmKeyValue1);
											writeWCMHeaderContentFinalToExcel();
											testcaseNumber++;

											fetchTableRowsContentForGrandChildTable(finalGrandChildtableTitle,
													grandChildtabledata, "4thLevelIndexPage");

										} else {
											LogFactory.info("Unable to find the xpath for title::"
													+ IsFinalChild_Tables.get(gctc));
										}
									}

									// fetching grand child categories content
									for (int gcfcc = 0; gcfcc < numberOfContentsToFetch(
											IsFinalChild_Categories); gcfcc++) {
										System.out.println("Reading content for Category "
												+ IsFinalChild_Categories.get(gcfcc) + " of Grand Child Index Page"
												+ fouthlevelgrandChildIndexPageTitle);// SALES
										if (ValidationFactory.isElementPresent(
												By.xpath("//a[.='" + IsFinalChild_Categories.get(gcfcc)
														+ "' and starts-with(@title,'View children')]"))) {
											WebElement finalGrandchildCategory = wcmalrtDriver
													.findElement(By.xpath("//a[.='" + IsFinalChild_Categories.get(gcfcc)
															+ "' and starts-with(@title,'View children')]"));

											String grandChildCategoryTitle = finalGrandchildCategory.getText();
											finalGrandchildCategory.click(); // Child index page first category clicked

											wcmKeyValue1.put("DepartmentName", departmentName);
											wcmKeyValue1.put("2ndLevel", subDeptsUnderDeptName);
											wcmKeyValue1.put("3rdLevelFolder", folderName);
											wcmKeyValue1.put("4thLevelIndexPage", fourthLevelIndexPagetitle);
											wcmKeyValue1.put("4thLevelChildIndexPage", fourthlevelchildIndexPageTitle);
											wcmKeyValue1.put("4thLevelGrandChildIndexPage",
													fouthlevelgrandChildIndexPageTitle);
											wcmKeyValue1.put("4thLevelGrandChildIndexPageCategories",
													grandChildCategoryTitle);

											checkForNestedcategories(wcmKeyValue1,
													"4thLevelGrandChildIndexPageCategories");
											wcmalrtDriver.findElement(By
													.xpath("//a[contains(.,'" + fourthlevelchildIndexPageTitle + "')]"))
													.click();
										} else {
											LogFactory.info("Unable to find the xpath for title::"
													+ IsFinalChild_Categories.get(gcfcc));
										}

									} // end of checking for nested category or not

									wcmalrtDriver
											.findElement(By.xpath("//a[.='" + fourthlevelchildIndexPageTitle + "']"))
											.click();
								} else {
									LogFactory.info("Unable to find the xpath for title::"
											+ IsGrandChildIndex_Index_pages.get(gcip));
								}
							} /// END OF GRANDCHILD INDEX PAGES

							wcmalrtDriver.findElement(By.xpath("//a[.='" + fourthLevelIndexPagetitle + "']")).click();// navigating
																														// back
																														// to
																														// index
																														// page
																														// Business
																														// continuation
						} else {
							LogFactory.info("Unable to find the xpath for title::" + IsChild_Index_pages.get(ici));
						}
					} // END OF FOR LOOP FOR ALL CHILD INDEX PAGES

					wcmalrtDriver.findElement(By.xpath("//a[.='" + subDeptsUnderDeptName + "']")).click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + SAT_FolderIndex_Pages.get(ip));
				}
			} // END OF FOR LOOP FOR ALL INDEX PAGES

		} // END OF TRY BLOCK
		catch (Exception e) {

			System.out.println(
					"Error while fetching content for " + SAT_FolderIndex_Pages + " :: " + e.getMessage().toString());
		}

	} ////// END of fetchCOntentTillGrandChild method

	/**
	 * @author Yogender singh This method is to check if an elements is not present
	 *         on the UI @ throws Throwable
	 */
	public static boolean isElementNotPresent(WebElement wbelObj) {
		try {

			if (wbelObj.isDisplayed())
				return false;
			else
				return true;
		} catch (NoSuchElementException e) {
			return true;
		}

		catch (Exception e) {
			return true;
		}

	}

	private static void checkNestedcategorieForChildIndexPage(HashMap<String, String> wcmKeyValuePair,
			String levelToFwd) throws Throwable {

		HashMap<String, String> newWcmKeyValue = new HashMap<String, String>();
		try {
			System.out.println("**INSIDE METHOD CHECKNESTED CATEGORY FOR CHILD INDEX PAGE**");

			System.out.println("now checking content for category::" + wcmKeyValuePair.get(levelToFwd));

			System.out.println("CHILD INDEX PAGE Category data is:" + wcmKeyValuePair);
			List<WebElement> categoriesContent = allChildren;

			List<String> categoryContent = new ArrayList<String>();
			List<String> nestedcategoryContent = new ArrayList<String>();
			

			for (int nc = 1; nc <= categoriesContent.size(); nc++) {
				String isNestedCategoryPresent = wcmalrtDriver.findElement(By.xpath("//tr[" + nc + "]//td[2]//img[2]"))
						.getAttribute("title");
				String checkNestedategory = wcmalrtDriver
						.findElement(By.xpath("//tr[" + nc + "]//td[2]//img[2]/following::td[1]//a/span")).getText();
				if (isNestedCategoryPresent.contains("View children")) {
					nestedcategoryContent.add(checkNestedategory);
				} else {
					categoryContent.add(checkNestedategory);
				}
			}

			// System.out.println("For category
			// :"+wcmKeyValuePair.get("3rdLevelIndexPageCategories") +" Nested categories
			// are: "+nestedcategoryContent.size()+" And normal content's
			// are:"+categoryContent.size());

			for (int cc = 0; cc < numberOfContentsToFetch(categoryContent); cc++) {
				System.out.println("fetching content for normal category content:" + categoryContent.get(cc));

				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + categoryContent.get(cc)
						+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
					wcmalrtDriver.findElement(By.xpath("//a[.='" + categoryContent.get(cc)
							+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
							.click();

					String wcmTCID = testCaseID + testcaseNumber;
					newWcmKeyValue.put("Test Case ID", wcmTCID);
					newWcmKeyValue.putAll(wcmKeyValuePair);
					writeWCMToExcel(newWcmKeyValue, "None");
					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					closeContent.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + categoryContent.get(cc));
				}
			}

			if (nestedcategoryContent.size() > 0) {

				List<String> IsCategoryChild_GCIP = new ArrayList<String>();
				List<String> IsCategoryChild_NestedCategories = new ArrayList<String>();
				List<String> IsCategoryChild_Table = new ArrayList<String>();

				System.out.println("checking content type of Category::" + wcmKeyValuePair.get(levelToFwd));
				for (int cct = 0; cct < nestedcategoryContent.size(); cct++) {
					WebElement categoryChild = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + nestedcategoryContent.get(cct) + "' and starts-with(@title,'View children')]"));
					String categoryChildTitle = categoryChild.getText();

					String categorysChildType = checkContentType(categoryChildTitle);

					if (categorysChildType.contains("SAT-GrandChild Index Page")) {
						IsCategoryChild_GCIP.add(categoryChildTitle);
					} else if (categorysChildType.contains("SAT-Default Sub-Site Area")) {
						IsCategoryChild_NestedCategories.add(categoryChildTitle);
					}
					else if(categorysChildType.contains("SAT-Table Index Page"))
					{IsCategoryChild_Table.add(categoryChildTitle);}

					System.out.println("This Child :" + categoryChildTitle + " of category ::"
							+ wcmKeyValuePair.get(levelToFwd) + " is a " + categorysChildType);
				}

				for (int ncct = 0; ncct < IsCategoryChild_NestedCategories.size(); ncct++) {
					System.out.println(
							"fetching content for nested category:" + IsCategoryChild_NestedCategories.get(ncct));

					if (ValidationFactory
							.isElementPresent(By.xpath("//a[.='" + IsCategoryChild_NestedCategories.get(ncct)
									+ "' and (contains(@title, 'View children'))]"))) {
						String nestedcategoryTitle = wcmalrtDriver
								.findElement(By.xpath("//a[.='" + IsCategoryChild_NestedCategories.get(ncct)
										+ "' and (contains(@title, 'View children'))]"))
								.getText();
						// SUB SALES

						wcmalrtDriver.findElement(By.xpath("//a[.='" + IsCategoryChild_NestedCategories.get(ncct)
								+ "' and (contains(@title, 'View children'))]")).click();

						// wcmalrtDriver.findElement(By.xpath("//tr[1]//td[2]//img[2]/following::td[1]//a")).click();

						List<WebElement> nestedCategoriesContent = allChildren;

						List<String> nestedcategoryNoChildContent1 = new ArrayList<String>();
						List<String> nestedcategoryChildContent = new ArrayList<String>();
						List<String> IsCategoryChildGCIP = new ArrayList<String>();
						List<String> IsCategoryChildTable= new ArrayList<String>();

						for (int ncc = 1; ncc <= nestedCategoriesContent.size(); ncc++) {
							String isNestedCategoryChildPresent = wcmalrtDriver
									.findElement(By.xpath("//tr[" + ncc + "]//td[2]//img[2]")).getAttribute("title");
							String checkNestedategory = wcmalrtDriver
									.findElement(By.xpath("//tr[" + ncc + "]//td[2]//img[2]/following::td[1]//a/span"))
									.getText();
							if (isNestedCategoryChildPresent.contains("View children")) {
								nestedcategoryChildContent.add(checkNestedategory);
								System.out.println("Child content " + checkNestedategory + " added as a SAT");
							} else {
								nestedcategoryNoChildContent1.add(checkNestedategory);
								System.out.println("Child content " + checkNestedategory + " added as a link");
							}

						}
						HashMap<String,String> ncNoChildContent=new HashMap<String,String>();
						for (int ncncc = 0; ncncc < numberOfContentsToFetch(nestedcategoryNoChildContent1); ncncc++) {
							System.out.println("fetching Final content for nested category:" + nestedcategoryTitle);

							if (ValidationFactory.isElementPresent(By.xpath("//a[.='"
									+ nestedcategoryNoChildContent1.get(ncncc)
									+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))) {
								wcmalrtDriver.findElement(By.xpath("//a[.='" + nestedcategoryNoChildContent1.get(ncncc)
										+ "' and not(contains(@title, 'View children')) and not(contains(@title, 'Navigate to'))]"))
										.click();

								String wcmTCID = testCaseID + testcaseNumber;
								String[] Key = levelToFwd.split("Categories");
								ncNoChildContent.put("Test Case ID", wcmTCID);
								ncNoChildContent.put(Key[0] + "NestedCategories", nestedcategoryTitle);
								ncNoChildContent.putAll(wcmKeyValuePair);
								writeWCMToExcel(ncNoChildContent, "None");
								writeWCMHeaderContentFinalToExcel();
								testcaseNumber++;
								closeContent.click();
							} else {
								LogFactory.info("Unable to find the xpath for title::"
										+ nestedcategoryNoChildContent1.get(ncncc));
							}
						}

						
						for (int nccip = 0; nccip < numberOfContentsToFetch(nestedcategoryChildContent); nccip++) {
							System.out.println("Checking for grandchild for nested category ");
							WebElement categoryChild = wcmalrtDriver
									.findElement(By.xpath("//a[.='" + nestedcategoryChildContent.get(nccip)
											+ "' and starts-with(@title,'View children')]"));
							String categoryChildTitle = categoryChild.getText();

							String categorysChildType = checkContentType(categoryChildTitle);

							if (categorysChildType.contains("SAT-GrandChild Index Page")) {
								IsCategoryChildGCIP.add(categoryChildTitle);
							}
							else if (categorysChildType.contains("SAT-Table Index Page")) {
								IsCategoryChildTable.add(categoryChildTitle);
							}
							System.out.println("This Child :" + categoryChildTitle + " of nested category ::"
									+ nestedcategoryTitle + " is a " + categorysChildType);
						}

						
						//Write code her for table
						
						HashMap<String,String> grandChildTableMap=new HashMap<String,String>();
						HashMap<String,String> ncChildTableContent=new HashMap<String,String>();
						for (int gct = 0; gct < IsCategoryChildTable.size(); gct++) {
							System.out.println("Fetching content for Grand Child Table of :" + IsCategoryChildTable.get(gct));
							
							if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsCategoryChildTable.get(gct)
											+ "' and starts-with(@title,'View children')]"))) {
								WebElement finalGrandChildTable = wcmalrtDriver
										.findElement(By.xpath("//a[.='" + IsCategoryChildTable.get(gct)
												+ "' and starts-with(@title,'View children')]"));

								String finalGrandChildtableTitle = finalGrandChildTable.getText();

								grandChildTableMap = fetchTablesContent(IsCategoryChildTable.get(gct));// can contain Link
																									// Portlets and
							String wcmTCID = testCaseID + testcaseNumber;

							ncChildTableContent.put("Test Case ID", wcmTCID);
							ncChildTableContent.putAll(wcmKeyValuePair);
							ncChildTableContent.putAll(grandChildTableMap);

							excelOutput(ncChildTableContent);
							writeWCMHeaderContentFinalToExcel();
							testcaseNumber++;
							fetchTableRowsContentForGrandChildTable(finalGrandChildtableTitle, ncChildTableContent, "3rdLevelChildIndexPage");
							}
							else
							{
								System.out.println("content not found in the list");
								
							}

						}

						if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+nestedcategoryTitle+"']")))
						{
						wcmalrtDriver.findElement(By.xpath("//a[.='"+nestedcategoryTitle+"']")).click();
						}
						
						HashMap<String,String> ncChildGCIP=new HashMap<String,String>();
						for (int ncgcip = 0; ncgcip < numberOfContentsToFetch(IsCategoryChildGCIP); ncgcip++) {
							System.out.println("fetchinf grand child index page content for nested category");
							ncChildGCIP.put("3rdLevelGrandChildIndexPage", linksAndSATHashMap.get(IsCategoryChildGCIP.get(ncgcip)));
							ncChildGCIP.putAll(wcmKeyValuePair);
							if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsCategoryChildGCIP.get(ncgcip)
									+ "' and starts-with(@title,'View children')]"))) {

								WebElement categoryChildIndexPage = wcmalrtDriver
										.findElement(By.xpath("//a[.='" + IsCategoryChildGCIP.get(ncgcip)
												+ "' and starts-with(@title,'View children')]"));
								categoryChildIndexPage.click();

								fetchContentForGrandChildIndexPage(ncChildGCIP, "3rdLevelGrandChildIndexPage");
							} else {
								LogFactory
										.info("Unable to find the xpath for title::" + IsCategoryChildGCIP.get(ncgcip));
							}
						}
						if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+wcmKeyValuePair.get(levelToFwd)+"']"))) {
						wcmalrtDriver.findElement(By.xpath("//a[.='" + wcmKeyValuePair.get(levelToFwd) + "']")).click();}

					} else {
						LogFactory.info(
								"Unable to find the xpath for title::" + IsCategoryChild_NestedCategories.get(ncct));
					}

				}
				if (IsCategoryChild_NestedCategories.size() > 0) {
					if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+wcmKeyValuePair.get("3rdLevelChildIndexPageCategories")+"']"))) {
					wcmalrtDriver
							.findElement(By
									.xpath("//li[.='"+wcmKeyValuePair.get("3rdLevelChildIndexPageCategories")+"']"))
							.click();}
				}

				
				
				
				//now reading CIP category for Table
				HashMap<String,String> cipTableStr=new HashMap<String,String>();
				HashMap<String,String> cipTable=new HashMap<String,String>();
				for (int cct = 0; cct < IsCategoryChild_Table.size(); cct++) {
					System.out.println("Fetching content for Grand Child Table :" + IsCategoryChild_Table.get(cct));
					
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsCategoryChild_Table.get(cct)
					+ "' and starts-with(@title,'View children')]"))) {
						WebElement finalGrandChildTable = wcmalrtDriver
				.findElement(By.xpath("//a[.='" + IsCategoryChild_Table.get(cct)
						+ "' and starts-with(@title,'View children')]"));

						String finalGrandChildtableTitle = finalGrandChildTable.getText();

						cipTableStr = fetchTablesContent(IsCategoryChild_Table.get(cct));// can contain Link
					String wcmTCID = testCaseID + testcaseNumber;

					cipTable.put("Test Case ID", wcmTCID);
					cipTable.putAll(wcmKeyValuePair);
					cipTable.putAll(cipTableStr);
					if(levelToFwd.equals("3rdLevelChildIndexPageCategories"))
						cipTable.put("3rdLevelGrandChildIndexPage", finalGrandChildtableTitle);
					else if(levelToFwd.equals("4thLevelChildIndexPageCategories"))
						cipTable.put("4thLevelGrandChildIndexPage", finalGrandChildtableTitle);
					

					excelOutput(cipTable);
					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					fetchTableRowsContentForGrandChildTable(finalGrandChildtableTitle, cipTable, "3rdLevelGrandChildIndexPage");
					
					}
					else
					{System.out.println("content not found in the list");}
				}
				
				if (IsCategoryChild_Table.size() > 0) {
					if(ValidationFactory.isElementPresent(By.xpath("//a[.='"+wcmKeyValuePair.get("3rdLevelChildIndexPageCategories")+"']"))) {
					
					wcmalrtDriver.findElement(By.xpath("//li[.='"+wcmKeyValuePair.get("3rdLevelChildIndexPageCategories")+"']")).click();}
				}			
				
				
				// now reading child index page content for category
				HashMap<String,String> catChildGCIP=new HashMap<String,String>();
				for (int cciip = 0; cciip < IsCategoryChild_GCIP.size(); cciip++) {
					catChildGCIP.put("3rdLevelGrandChildIndexPage", IsCategoryChild_GCIP.get(cciip));
					catChildGCIP.putAll(wcmKeyValuePair);
					if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsCategoryChild_GCIP.get(cciip)
							+ "' and starts-with(@title,'View children')]"))) {

						WebElement categoryChildIndexPage = wcmalrtDriver
								.findElement(By.xpath("//a[.='" + IsCategoryChild_GCIP.get(cciip)
										+ "' and starts-with(@title,'View children')]"));
						categoryChildIndexPage.click();
						System.out.println("GCIP:: "+IsCategoryChild_GCIP.get(cciip)+" is clicked");

						fetchContentForGrandChildIndexPage(catChildGCIP, "3rdLevelGrandChildIndexPage");
					} else {
						LogFactory
								.info("Unable to find the xpath for title::" + IsCategoryChild_GCIP.get(cciip));
					}
				}

			}

		}

		catch (Exception e) {

			System.out.println("Error while checking Nested Categories for child index page" + e.getMessage().toString());
		}

	}

	private static void fetchContentForGrandChildIndexPage(HashMap<String, String> tableChildIsChildIndexPage,
			String Level) throws Throwable {

		try {
			List<String> IsGrandChild_Tables = new ArrayList<String>();
			// List<String> IsGrandChildIndex_Index_pages=new ArrayList<String>();
			List<String> IsGrandChildIndex_Categories = new ArrayList<String>();
			List<WebElement> allChildForChildIndexPage = allChildren;
			List<String> childIndexPageLinkPortlet = new ArrayList<String>();
			List<String> grandChildContentForChildIndexPage = new ArrayList<String>();
			HashMap<String,String> gcipContentMap=new HashMap<String,String>();
			int totalCountAfterComparisonq = totalCount;
			if (allChildForChildIndexPage.size() < totalCount) {
				totalCountAfterComparisonq = allChildForChildIndexPage.size();
			}

			for (int cipc = 1; cipc <= totalCountAfterComparisonq; cipc++) {
				String childIndexPageContentTitle = wcmalrtDriver
						.findElement(By.xpath("//tr[" + cipc + "]//td[2]//img[2]")).getAttribute("title");

				if (!(childIndexPageContentTitle.contains("View children"))) {
					String ChildindexpageLinkPortletTitle = wcmalrtDriver
							.findElement(By.xpath("//tr[" + cipc + "]//td[2]//img[2]/following::td[1]//a/span"))
							.getText();
					childIndexPageLinkPortlet.add(ChildindexpageLinkPortletTitle);
				}

				if (childIndexPageLinkPortlet.size() == totalCount) {
					break;
				}

			}

			System.out.println("Link Portlets for Grand Child Index page are:" + childIndexPageLinkPortlet.size());

			if (childIndexPageLinkPortlet.size() == 0) {
				System.out.println("Checking Categories for Grand Child index page::" + tableChildIsChildIndexPage.get(Level));

				for (int checkCIPForCategory = 1; checkCIPForCategory <= allChildForChildIndexPage
						.size(); checkCIPForCategory++) {
					WebElement GCIPCategories = wcmalrtDriver.findElement(
							By.xpath("//tr[" + checkCIPForCategory + "]//td[2]//img[2]/following::td[1]//a/span"));
					String childCategoryTitleGCIP = GCIPCategories.getText();

					String childType = checkContentType(childCategoryTitleGCIP);
					if (childType.contains("SAT-Default Sub-Site Area")) {
						IsGrandChildIndex_Categories.add(childCategoryTitleGCIP);
					}
				}

			}

			// Now reading link portlets for CHild index page
			for (int cilp = 0; cilp < childIndexPageLinkPortlet.size(); cilp++) {

				System.out.println(
						"fetching Grand child index page link portlet:;" + childIndexPageLinkPortlet.get(cilp));
				String childIndexLinkPortlet = childIndexPageLinkPortlet.get(cilp);
				if (ValidationFactory.isElementPresent(By
						.xpath("//a[.='" + childIndexLinkPortlet + "' and not(contains(@title, 'View children'))]"))) {

					WebElement childIndexLink = wcmalrtDriver.findElement(By.xpath(
							"//a[.='" + childIndexLinkPortlet + "' and not(contains(@title, 'View children'))]"));
					childIndexLink.click();

					// checking for Index page inside the link
					String contentType = contentTypeOnPage.getText();
					String[] cType = contentType.split("/");
					String conType = cType[cType.length - 1].trim();

					if ((conType.equals("AT-Link") || conType.equals("AT-GrandChild Index Page")
							|| conType.equals("AT-Child Index Page"))
							&& ValidationFactory.isElementPresent(webContentElement)
							&& !(webContentLinkText.getText().contains("None"))) {

						String[] indexPageTitleArray = webContentLinkText.getText().split("/");
						String grandChildIndexPageTitle = indexPageTitleArray[indexPageTitleArray.length - 1].trim();
						linksAndSATHashMap.put(grandChildIndexPageTitle, childIndexLinkPortlet)	;
						grandChildContentForChildIndexPage.add(grandChildIndexPageTitle);
					}

					String wcmTCID = testCaseID + testcaseNumber;

					gcipContentMap.put("Test Case ID", wcmTCID);
					gcipContentMap.putAll(tableChildIsChildIndexPage);
					// wcmKeyValuePair.put("3rdLevelChildIndexPage",childIndexPageTitle);
					writeWCMToExcel(gcipContentMap, "None");
					writeWCMHeaderContentFinalToExcel();
					testcaseNumber++;
					closeContent.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + childIndexPageLinkPortlet.get(cilp));
				}
			}
			
			System.out.println("Grand Child Index page::" + tableChildIsChildIndexPage.get(Level) + " has total::"
					+ IsGrandChild_Tables.size() + " tables, and " + IsGrandChildIndex_Categories.size()
					+ " categories");

			/// Fetching content for Child index page's categories
			HashMap<String,String> gcipChildCategory=new HashMap<String,String>();
			for (int cc = 0; cc < numberOfContentsToFetch(IsGrandChildIndex_Categories); cc++) {
				System.out.println("Reading content for Category " + IsGrandChildIndex_Categories.get(cc)
						+ " of Grand Child Index Page:"
						+ tableChildIsChildIndexPage.get(Level));// SALES

				if (ValidationFactory.isElementPresent(By.xpath("//a[.='" + IsGrandChildIndex_Categories.get(cc)
						+ "' and starts-with(@title,'View children')]"))) {
					WebElement grandchildCategory = wcmalrtDriver.findElement(By.xpath("//a[.='"
							+ IsGrandChildIndex_Categories.get(cc) + "' and starts-with(@title,'View children')]"));
					String childCategoryTitle = grandchildCategory.getText();
					grandchildCategory.click(); // tools and documents
					System.out.println("Category ::" + childCategoryTitle + " for Grand Child index page:"
							+ tableChildIsChildIndexPage.get(Level) + " is clicked");// Child index
																										// page first
																										// category
																										// clicked
					// checking for nested category

					String levelToFwd;
					if (Level.contains("3rdLevelGrandChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
						levelToFwd = "3rdLevelGrandChildIndexPageCategories";
						gcipChildCategory.put("3rdLevelGrandChildIndexPageCategories", childCategoryTitle);
						gcipChildCategory.putAll(tableChildIsChildIndexPage);
						
					} else {
						levelToFwd = "4thLevelChildIndexPageCategories";
						gcipChildCategory.put("4thLevelChildIndexPageCategories", childCategoryTitle);
						gcipChildCategory.putAll(tableChildIsChildIndexPage);
					}
					// System.out.println(wcmKeyValue1);
					
					checkForNestedcategories(gcipChildCategory, levelToFwd);
					

					wcmalrtDriver
							.findElement(By.xpath("//a[contains(.,'" + tableChildIsChildIndexPage.get(Level) + "')]"))
							.click();
				} else {
					LogFactory.info("Unable to find the xpath for title::" + IsGrandChildIndex_Categories.get(cc));
				}
			}

			if (Level.contains("3rdLevelGrandChildIndexPage") || Level.contains("3rdLevelLandingPage")) {
				wcmalrtDriver.findElement(By.xpath("//a[contains(.,'"
						+ tableChildIsChildIndexPage.get("3rdLevelChildIndexPageCategories") + "')]")).click();
			} else {
				wcmalrtDriver.findElement(By.xpath("//a[contains(.,'"
						+ tableChildIsChildIndexPage.get("4thLevelChildIndexPageCategories") + "')]")).click();
			}
			///// fetching content for GrandChild Index page content

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());

		}

	}


}
