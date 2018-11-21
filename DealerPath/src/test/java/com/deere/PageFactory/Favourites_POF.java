package com.deere.PageFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.BrowserFactory;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;

public class Favourites_POF extends BaseClass {
	final WebDriver FavouritesDriver;
	static SoftAssert softAssert = new SoftAssert();

	public Favourites_POF(WebDriver driver) {
		this.FavouritesDriver = driver;
	}
	
	static ArrayList<String> QuickLinks = new ArrayList<String>();


	@FindBy(how = How.XPATH, using = "//div[@class='value']//select[@id='lang']")
	public static WebElement language_label;
	@FindBy(how = How.ID, using = "preference-cancel")
	public static WebElement wblPreferenceCancel;
	@FindBy(how = How.XPATH, using = "//div[@class='popover-content']//div[@class='title' and contains(text(),'My DealerPath Favorites')]")
	public static WebElement PopoverHeaderTitile;
	@FindBy(how = How.XPATH, using = "//div[@class='popover-content-contianer']//ul[not(@class='custom-search-filter-list custom-search-department-list')]/li[2]")
	public static WebElement Click_CopyFromUser;
	@FindBy(how = How.XPATH, using = "//button[@id='copy-favorites-copy']/preceding-sibling::button")
	public static WebElement cancel_btn;
	@FindBy(how = How.XPATH, using = "//p[@class='error' and @id ='copy-favorites-error-invalid-non-dealer']")
	public static WebElement invalidEmployee;
	@FindBy(how = How.XPATH, using = ".//*[@id='copy-from-user']")
	public static WebElement EmployeeID_Textbox;
	@FindBy(how = How.XPATH, using = "//*[@class='group-value checkbox-value']//label[@class='click-target-only']//input[@type='checkbox']")
	public static List<WebElement> countryGroupingIconCheckboxList;
	@FindBy(how = How.XPATH, using = ".//*[@class='search-icons countryGrouping']")
	static List<WebElement> countryGroupingIcon;
	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[4]/section/div/div[2]/div[1]/h3")
	static WebElement header_title_Favourites;
	@FindBy(how = How.ID, using = "favorites-target")
	public static WebElement wbelFavouriteFrame;
	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[4]/section/div/div[2]/div[1]/h3")
	static WebElement wbelHeaderTitleFav;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='js-quick-favorites-popover']/div[2]/div[1]")
	static WebElement wbelQuickLinkHeader;
		

	
	@FindBy(how = How.XPATH, using = ".//*[@id='favorites-target']/div/p[2]")
	static WebElement wbelMessageFav;
	@FindBy(how = How.XPATH, using = ".//*[@id='favorites-target']/div/div/div/div/div/div[1]")
	static List<WebElement> lstActualFoldernames;
	@FindBy(how = How.XPATH, using = ".//*[@id='favorites-target']/div/div")
	static WebElement wbelNofavaddedFav;
	@FindBy(how = How.ID, using = "Favourites-filter")
	static WebElement searchbox_Favourites;
	@FindBy(how = How.XPATH, using = ".//*[@id='js-segments-popover']/div[2]/div[3]/button")
	public static WebElement ApplyFilterButton;
	@FindBy(how = How.XPATH, using = ".//*[@id='Favourites-target']/div/div")
	static WebElement nofavadded_Favourites;
	@FindBy(how = How.XPATH, using = ".//*[@id='Favourites-target']/div/p[2]/span")
	static WebElement icon_Favourites;
	@FindBy(how = How.XPATH, using = ".//*[@id='Favourites-target']/div/p[2]")
	static WebElement message_Favourites;
	@FindBy(how = How.LINK_TEXT, using = "Actions")
	static WebElement action_Favourites;
	@FindBy(how = How.XPATH, using = ".//*[@id='favorites-filter']")
	static WebElement FavFilter;
	@FindBy(how = How.XPATH, using = ".//*[@class='section-body' and @id='Favourites-target']")
	static WebElement sectionBody;
	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='active']")
	public static List<WebElement> ListAllActiveLinks;
	@FindBy(how = How.XPATH, using = "//div[@class='section-header']//h3")
	public static List<WebElement> header;
	@FindBy(how = How.XPATH, using = "//*[@class='app-title']")
	static WebElement home;

	/*
	 * @FindBy(how = How.LINK_TEXT, using = "Add Folder") static WebElement
	 * addfolder_Favourites;
	 */
	@FindBy(how = How.XPATH, using = ".//*[@id='js-favorites']")
	static WebElement wbelFavourSearch;
	@FindBy(how = How.XPATH, using = ".//*[@id='Favourites-target']/div/div/div/div[1]/div/div[1]")
	static List<WebElement> favouritesFoldername;
	@FindBy(how = How.XPATH, using = "//a[@id='js-fav-actions-trigger']")
	static WebElement addfolderFavourites;
	@FindBy(how = How.XPATH, using = ".//*[@id='links-target']")
	static WebElement framePath;
	@FindBy(how = How.XPATH, using = ".//*[@class='icon fav-star is-selected']")
	static String selectedFavouriteicon;
	@FindBy(how = How.XPATH, using = ".//*[@id='links-target']/div/div/div/div/span")
	static List<WebElement> Favouriteicon;
	@FindBy(how = How.XPATH, using = "//div[@id='js-Favourites']")
	static WebElement favouriteQuickIcon;
	@FindBy(how = How.XPATH, using = "//div[@id='quick-Favourites-target']//div[contains(@class,'link-item')]")
	static List<WebElement> favouriteQuickLinkFolder;
	@FindBy(how = How.XPATH, using = ".//*[@class='icon fav-star']")
	static WebElement favouriteUnmarkedIconStarOnDepartmentpage;
	@FindBy(how = How.XPATH, using = ".//*[@name='add-Favourite-folder']")
	static WebElement FavouriteAddFolderSelectboxpath;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-Favourite-new-folder']")
	static WebElement FavouriteAddNewFolderSelectboxpath;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-folder-modal']/div/div/div[3]/button[1]")
	static WebElement FavouriteAddFolderCancelbtn;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-favorite-save']")
	static WebElement favouriteSavebtn;
	@FindBy(how = How.XPATH, using = ".//*[@class='icon fav-star is-selected']")
	static WebElement favouriteMarkedIconStarOnDepartmentpage;
	@FindBy(how = How.XPATH, using = ".//*[@class='icon folder closed']")
	static List<WebElement> favouriteFolderClosedIcon;
	@FindBy(how = How.XPATH, using = "//div[@class='popover-content']//div[not(contains(@class,'link-item child  collapsed')) and contains(@class,'link-item')]")
	static List<WebElement> favouriteQuickIconFolderAndLinksList;
	private static Object createFavourite;
	@FindBy(how = How.XPATH, using = ".//*[@id='links-target']/div/div/div/div/span")
	static List<WebElement> favouiteIcon;
	@FindBy(how = How.XPATH, using = ".//*[@id='links-target']/div/div/div/div")
	static List<WebElement> favouiteLinkList;
	@FindBy(how = How.XPATH, using = ".//*[@id='js-fav-action-add-folder']")
	static WebElement addFolderbtn;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-folder-name']")
	static WebElement addFolderNametxtbox;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-folder-add']")
	static WebElement addbtn;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-folder-error-duplicate']")
	static WebElement addFolderDuplicate;
	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='active']")
	public static List<WebElement> ActivedepartmentList;
	@FindBy(how = How.XPATH, using = ".//*[@id='js-fav-actions-trigger']")
	public static List<WebElement> favouiteActions;
	@FindBy(how = How.XPATH, using = ".//*[@id='js-fav-action-add-folder']")
	public static List<WebElement> favouiteActions_JDIN;
	@FindBy(how = How.LINK_TEXT, using = ".//*[@class='custom-search-filter-list']/li[1]")
	static WebElement favActionsAddFolder;
	@FindBy(how = How.XPATH, using = "//span[@class='icon delete']")
	static List<WebElement> deleteIcon;
	@FindBy(how = How.XPATH, using = ".//*[@id='main-header']/div[1]/div/h1")
	static WebElement homepagepath;
	@FindBy(how = How.XPATH, using = ".//*[@id='layoutContainers']/div/div[2]/div[3]/div[4]/section/div/div[2]/div")
	static List<WebElement> favouriteTableElements;
	@FindBy(how = How.XPATH, using = "//div[@id='js-segments']")
	static WebElement wbelProductSegmentIcon;
	@FindBy(how = How.XPATH, using = ".//*[@name='add-favorite-folder']")
	static WebElement favoriteAddFolderSelectboxpath;
	@FindBy(how = How.XPATH, using = ".//*[@id='add-favorite-new-folder']")
	static WebElement favoriteAddNewFolderSelectboxpath;
	@FindBy(how = How.XPATH, using = "//input[@id='add-favorite-new-folder']")
	public static WebElement addFavoriteNewfolder;

	@FindBy(how = How.NAME, using = "add-favorite-folder")
	public static WebElement addFavFolder;
	public static List<String> options_Text = new ArrayList<>();
	static ArrayList<String> FavlinkHomepage = new ArrayList<>();
	static ArrayList<String> FavlinkQuickModal = new ArrayList<>();
	static List<WebElement> lang_obj_options = new ArrayList<>();
	static ArrayList<String> allLanguage = new ArrayList<String>();
	static String department1 = "";
	static List<String> iconFirst_List = null;
	static List<WebElement> iconLast_List = null;
	static WebElement outside_iconFirst = null;
	static WebElement Outside_iconLast = null;

	/**
	 * @author Shubham Raj This method is used to mark the favourite links on Link
	 *         portlet @ exception Description of exception you are handling
	 * 
	 */

	public static void createFavourite() throws Throwable {

		// String departmentName = "";
		String subDepartmentName = "";
		String title = "";
		String TCID = "";
		String subDepartmentLang = "";
		String linkname = "";
		int selectedindex = 0;
		String IconIsslected = "";
		String titleLang = "";
		String ThirdlevelFolder = "";
		Boolean clickCheck = false;

		try {

			List<LinkedHashMap> userWCMContent = ExcelFactory
					.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link", "AT-Document", "AT-Rich Text", "");
			System.out.println(userWCMContent);
			for (int i = 0; i < userWCMContent.size(); i++) {
				department1 = userWCMContent.get(i).get("DepartmentName").toString().trim();
				subDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
				subDepartmentLang = GenericFactory.getTranslation(subDepartmentName).toString().replaceAll("^\\[|\\]$",
						"");
				title = (String) userWCMContent.get(i).get("Title").toString().trim();
				TCID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
				ThirdlevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();

				if (GenericFactory.clickOnDepartmentByName(department1)) {
					{
						if (!ThirdlevelFolder.equals("NA")) {
							GenericFactory.toClickOnFolder(ThirdlevelFolder, subDepartmentName);

						}
						departmentNames.add(department1);
						List<WebElement> allHeaderNamesAndLinks = BaseClass.wbDriver.findElements(By.xpath(
								".//*[@id='links-target']/div[not(@class='links-no-results') and not(@class='empty-link-portlet-container')]"));
						secondloop: for (int j = 0; j < allHeaderNamesAndLinks.size(); j++) {
							WebElement thirdLevelLinks = allHeaderNamesAndLinks.get(j);
							String[] headerName = (thirdLevelLinks.getText()).split("\n");
							List<WebElement> links = null;

							if (subDepartmentLang.equals(headerName[0])) {
								// links =
								// thirdLevelLinks.findElements(By.tagName("a"));

								List<WebElement> icons = null;

								if (!ThirdlevelFolder.equals("NA")) {
									icons = thirdLevelLinks.findElements(By
											.xpath("//div[contains(@parent-folder,'" + ThirdlevelFolder + "')]/span"));
									links = thirdLevelLinks.findElements(By.xpath("//div[contains(@parent-folder,'"
											+ ThirdlevelFolder + "')]/span/following-sibling::a"));
								} else {
									icons = thirdLevelLinks.findElements(By.xpath("//h5[contains(text(),'"
											+ subDepartmentName
											+ "')]/..//div[@class='link-group']/div/div[@class='link-item wrap']/span"));
									links = thirdLevelLinks.findElements(By.xpath("//h5[contains(text(),'"
											+ subDepartmentName
											+ "')]/..//div[@class='link-group']/div/div[@class='link-item wrap']/a"));

								}
								for (int k = 0; k < icons.size(); k++) {
									IconIsslected = "";
									linkname = links.get(k).getAttribute("textContent").trim();
									IconIsslected = icons.get(k).getAttribute("class").toString().trim();

									if (IconIsslected.contains("fav-star is-selected") && linkname.equals(title)) {
										LogFactory.info(linkname + " is already marked so de-selecting it first");

										WaitFactory.waitForElements(icons, wbDriver);
										JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
										 js.executeScript("arguments[0].style.border='2px solid red'", links.get(k));
										 Thread.sleep(900);
										WaitFactory.waitForElementClickable(icons.get(k)).click();
										Thread.sleep(2000); //Time Difference between Unmarking and Marking a Favourite
										 
										if (i % 2 == 0) {

											WaitFactory.waitForElements(icons, wbDriver);
											
											WaitFactory.waitForElementClickable(icons.get(k)).click();
											WaitFactory.waitForElement(favouriteSavebtn);
											String dropdownVal = addFavFolder.getText();
											if (dropdownVal.contains("My Fav Folder")) {
												GenericFactory.selectByVisibleText(addFavFolder, "My Fav Folder");
												Thread.sleep(1000);
												if (ValidationFactory.isElementPresent(favouriteSavebtn))
													
													WaitFactory.WaitForElementToVisible(favouriteSavebtn);
														WaitFactory.waitForElementClickable(favouriteSavebtn).click();
														WaitFactory.waitforelementToinvisibile(favouriteSavebtn);
													
												WaitFactory.waitForPageLoaded();
												markedlinkNames.add(linkname);
												markedLinks.add(TCID);
												break secondloop;

											} else if (ValidationFactory.isElementPresent(addFavoriteNewfolder)) {
												addFavoriteNewfolder.sendKeys("My Fav Folder");
												WaitFactory.WaitForTexttoBePresentInElement(addFavoriteNewfolder,"My Fav Folder");
												if (ValidationFactory.isElementPresent(favouriteSavebtn))
													Thread.sleep(1000);
													WaitFactory.WaitForElementToVisible(favouriteSavebtn);
													WaitFactory.waitForElementClickable(favouriteSavebtn).click();
													WaitFactory.waitforelementToinvisibile(favouriteSavebtn);
												WaitFactory.waitForPageLoaded();
												markedlinkNames.add(linkname);
												markedLinks.add(TCID);
												break secondloop;
											}

										} else {
											icons.get(k).click();
											WaitFactory.waitForElements(icons, wbDriver);
											LogFactory.info("Clicked on the link to mark favourite = " + linkname);
											Thread.sleep(1000);
											WaitFactory.WaitForElementToVisible(favouriteSavebtn);
											if (ValidationFactory.isElementPresent(favouriteSavebtn)) {
												WaitFactory.waitForElementClickable(favouriteSavebtn).click();
												WaitFactory.waitforelementToinvisibile(favouriteSavebtn);
												LogFactory.info("Click On Save Button ");
												WaitFactory.WaitForElementToVisible(icons.get(k));
												markedlinkNames.add(linkname);
												markedLinks.add(TCID);
												break secondloop;

											}

										}

									} else if (IconIsslected.contains("icon fav-star") && linkname.equals(title)) {

										if (i % 2 == 0) {
											icons.get(k).click();
											Thread.sleep(1000);
											WaitFactory.WaitForElementToVisible(favouriteSavebtn);
											WaitFactory.waitForElement(favouriteSavebtn);
											String dropdownVal1 = addFavFolder.getText();
											if (dropdownVal1.contains("My Fav Folder")) {
												GenericFactory.selectByVisibleText(addFavFolder, "My Fav Folder");
												if (ValidationFactory.isElementPresent(favouriteSavebtn))
													WaitFactory.waitForElementClickable(favouriteSavebtn).click();
													WaitFactory.waitforelementToinvisibile(favouriteSavebtn);
												WaitFactory.waitForPageLoaded();
												markedlinkNames.add(linkname);
												markedLinks.add(TCID);
												break secondloop;

											} else if (ValidationFactory.isElementPresent(addFavoriteNewfolder)) {
												addFavoriteNewfolder.sendKeys("My Fav Folder");
												WaitFactory.WaitForTexttoBePresentInElement(addFavoriteNewfolder,"My Fav Folder");
												Thread.sleep(1000);
												if (ValidationFactory.isElementPresent(favouriteSavebtn))
													WaitFactory.WaitForElementToVisible(favouriteSavebtn);
													WaitFactory.waitForElementClickable(favouriteSavebtn).click();
													WaitFactory.waitforelementToinvisibile(favouriteSavebtn);
												WaitFactory.waitForPageLoaded();
												markedlinkNames.add(linkname);
												markedLinks.add(TCID);
												break secondloop;
											}

										} else {
											WaitFactory.waitForElements(icons, wbDriver);
											icons.get(k).click();

											WaitFactory.waitForElements(icons, wbDriver);
											LogFactory.info("Clicked on the link to mark favourite = " + linkname);
											Thread.sleep(1000);
											WaitFactory.WaitForElementToVisible(favouriteSavebtn);
											if (favouriteSavebtn.isDisplayed()) {
												WaitFactory.waitForElementClickable(favouriteSavebtn).click();
												WaitFactory.waitforelementToinvisibile(favouriteSavebtn);
												LogFactory.info("Click On Save Button ");
												WaitFactory.WaitForElementToVisible(icons.get(k));
												markedlinkNames.add(linkname);
												markedLinks.add(TCID);
												break secondloop;
											}

										}
									}

								}

							}
						}
					}
				}
			}

			LogFactory.info("Total marked links on link portlet are: " + markedlinkNames);
			if (ValidationFactory.isElementPresent(favouriteSavebtn)) {
				WaitFactory.waitForElementClickable(favouriteSavebtn).click();
			}
			GenericFactory.navigateToHomePage();
			BrowserFactory.RefreshBrowser();
			if (markedLinks.size() > 0) {

				ReportFactory.reporterOutput("TC01_Favourite", "To mark the titles as Favourites",
						"WCM ID's selected to mark as Favoutits" + markedLinks,
						"Links/Titles marked are from the department" + departmentNames,
						"Marked Favourites are : " + markedlinkNames, "Pass");
			} else {
				ReportFactory.reporterOutput("TC01_Favourite", "To mark the titles as Favourites", "NA", "NA",
						"No titles selected to mark as favourite", "Pass");
			}

			
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * @author Shubham Raj This method is used to fetch all the folders present on
	 *         the Dealer Path homepage @ exception Exception e
	 */

	public static ArrayList<String> ToGetFavouritesFolder_HomePage() {
		ArrayList<String> FavHomeFolderNameList = new ArrayList<String>();
		String FolderName = "";

		try {
			// homepagepath.click();
			List<WebElement> FolderListObj = BaseClass.wbDriver.findElements(By.xpath(".//*[@class='fav-link-body']"));

			for (int i = 0; i < FolderListObj.size(); i++) {

				WebElement folderobj = FolderListObj.get(i);
				List<WebElement> linkobj = folderobj.findElements(By.tagName("a"));
				if (!(linkobj.size() > 0)) {

					FolderName = folderobj.getText().toString().trim();
					FavHomeFolderNameList.add(FolderName);

				}

			}
			LogFactory.info("Folder List On Home Page Portlet " + FavHomeFolderNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FavHomeFolderNameList;
	}

	// ***********************************END ***************************

	/**
	 * @author Shubham Raj This method is used to fetch all the folders present on
	 *         the Quick Modal window @ exception Exception e
	 */
	public static ArrayList<String> TOGetFolderListQuickModal() {
		ArrayList<String> QuickFolderNameList = new ArrayList<String>();
		String FolderName = "";

		try {

			// BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-favorites']")).click();
			WaitFactory.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-favorites']")))
					.click();
			List<WebElement> Quicklemetlist = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='quick-favorites-target']/div/div/div/div"));

			for (int i = 0; i < Quicklemetlist.size(); i++) {

				WebElement folderobj = Quicklemetlist.get(i);
				List<WebElement> linkobj = folderobj.findElements(By.tagName("a"));
				if (!(linkobj.size() > 0)) {

					FolderName = folderobj.getText().toString().trim();
					QuickFolderNameList.add(FolderName);

				}

			}
			BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-favorites']")).click();
			LogFactory.info("Folder List under Quick Modal Window  " + QuickFolderNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return QuickFolderNameList;

	}

	// *********************************** END
	// ***************************************************************

	// Generic method to Get markedFav Link on Home Page My dealer Path
	/**
	 * @author Shubham Raj This method is used to fetch the marked link from the
	 *         dealer path homepage @ exception Exception e
	 */

	public static ArrayList<String> ToGetMarkedFavouriteslink_HomePage() {
		ArrayList<String> DealerPathFavouriteslinkList = new ArrayList<String>();
		try {
			String FavlinkText = "";
			List<WebElement> DealerPathFavouriteslink = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@class='fav-link-container']/div/a"));
			for (int i = 0; i < DealerPathFavouriteslink.size(); i++) {
				FavlinkText = DealerPathFavouriteslink.get(i).getAttribute("textContent").trim();
				DealerPathFavouriteslinkList.add(FavlinkText);
			}
			LogFactory.info("Marked Favourite Link on Homepage Portlet" + DealerPathFavouriteslinkList);
			return DealerPathFavouriteslinkList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DealerPathFavouriteslinkList;
	}

	// *********************************** END
	// ***************************************************************

	/**
	 * @author Shubham Raj This method is used to delete the favourite folder from
	 *         the home page and verifies that the deleted folder should not present
	 *         on Quick Modal window
	 * @param param1
	 *            Describe the name of the folder needs to be deleted @ exception
	 * Exception e
	 */

	public static void ToDeleteFavFolder(String folderName) throws Exception {

		ArrayList<String> actualfoldernameList = ToGetFavouritesFolder_HomePage();

		try {
			// to check folder is present
			if (actualfoldernameList.contains(folderName)) {
				// common Xpath to select Delete Button

				List<WebElement> deleteIconList = BaseClass.wbDriver
						.findElements(By.xpath("//span[@class='icon delete']"));

				for (int j = 0; j < actualfoldernameList.size(); j++) {
					String actualFolerName = actualfoldernameList.get(j).trim();

					if (actualFolerName.equals(folderName)) {

						System.out.println("test");
						@SuppressWarnings("unused")

						// to select the web element of delete btn
						WebElement WebDeleteButtonobj = deleteIconList.get(j);

						// move the cursor in corresponding foldername index
						Actions Action_obj = new Actions(BaseClass.wbDriver);
						Action_obj.moveToElement(WebDeleteButtonobj).build().perform();
						// Thread.sleep(3000);

						JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
						js.executeScript("arguments[0].click();", WebDeleteButtonobj);

						WaitFactory
								.WaitForElementToVisible(
										BaseClass.wbDriver.findElement(By.xpath(".//*[@id='delete-folder-ok']")))
								.click();

						break;
						// After delete Check Folder is Exist or not or home
						// page
					}

				}

				BrowserFactory.RefreshBrowser();
				ArrayList<String> FolderList = ToGetFavouritesFolder_HomePage();

				// After deletion Folder list should not contaion the deleted
				// Folder in List
				if (!FolderList.contains(folderName)) {
					LogFactory.info("Folder is deleted sucussfully " + folderName);

				} else {
					LogFactory.info("Folder is not deleted sucussfully " + folderName);
				}
			} else {
				// folder is not present

				LogFactory.info(" Given Folder is not not  present " + folderName);
			}
			// report

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// *************************** End Method
	// *******************************************

	// Generic method to Get markedFav Link on Dept Page

	public static ArrayList<String> ToGetMarkedFavouriteslink_Department() {

		ArrayList<String> selectedFavouriteIconLink = new ArrayList<String>();
		ArrayList<String> UnselectedFavouriteIconLink = new ArrayList<String>();
		String linktext = "";
		try {
			List<WebElement> Favicon = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='links-target']/div/div/div/div/span"));
			List<WebElement> Link = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='links-target']/div/div/div/div"));

			for (int i = 0; i < Favicon.size(); i++) {
				String Attributevalue = Favicon.get(i).getAttribute("class").toString().trim();
				linktext = Link.get(i).getText().toString().trim();
				if (Attributevalue.contains("is-selected")) {
					selectedFavouriteIconLink.add(linktext);
				} else {
					UnselectedFavouriteIconLink.add(linktext);
				}

			}
			LogFactory.info("Selected link with Favourite Star Icon on Department ---" + selectedFavouriteIconLink);
			// LogFactory.info("UnSelected link with Favourite Star Icon
			// ---"+UnselectedFavouriteIconLink);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectedFavouriteIconLink;
	}

	// ********* End Method *******************************************

	/**
	 * @author Shubham Raj This method is used to fetch the marked link from the
	 *         selected department and stores it into a List of String @ exception
	 * Exception e
	 */

	public static ArrayList<String> getDepartmentFavouriteslink() {

		ArrayList<String> FavouriteLinkNameList = new ArrayList<String>();
		String linktext = "";
		try {
			List<WebElement> LinkList = wbDriver.findElements(By.xpath(".//*[@id='links-target']/div/div/div/div/a"));
			for (int i = 0; i < LinkList.size(); i++) {
				linktext = LinkList.get(i).getAttribute("textContent").trim();
				FavouriteLinkNameList.add(linktext);
			}
			LogFactory.info("Fav Link on Departmnet is + ---" + FavouriteLinkNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FavouriteLinkNameList;
	}

	// *************************************************************
	// ********* End Method *******************************************

	/**
	 * @author Shubham Raj This method is used to fetch the marked link from the
	 *         Quick modal window and stores it into List @ exception Exception e
	 */

	public static ArrayList<String> ToGetMarkedFavouriteslinkQuickModal() throws Exception {
		ArrayList<String> FavlinkNameList = new ArrayList<String>();
		String Linknmae = "";
		try {

			WaitFactory.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-favorites']")))
					.click();
			// BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-favorites']")).click();
			List<WebElement> MarkedFAvlinkpath = BaseClass.wbDriver
					.findElements(By.xpath(".//*[@id='quick-favorites-target']/div/div/div/div/a"));
			for (int i = 0; i < MarkedFAvlinkpath.size(); i++) {
				Linknmae = MarkedFAvlinkpath.get(i).getAttribute("textContent").trim();
				FavlinkNameList.add(Linknmae);
			}
			WaitFactory.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath(".//*[@id='js-favorites']")))
					.click();
			LogFactory.info("Favourite Link on QuickModal Window " + FavlinkNameList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// WaitFactory.WaitForElementToVisible(homepagepath).click();

		return FavlinkNameList;
	}

	// ********************************** End Method
	// *******************************************

	/**
	 * @author Shubham Raj This method is used to mark the favourite links if
	 *         present on specific department
	 * @param param1
	 *            Describe the name of the department from which fav link needs to
	 *            be selected
	 * @param param2
	 *            Describe the name of the link to be selected @ exception Exception
	 * e
	 */

	public static String toMarkLinkFavourite(String departmentName, String titleLink) throws Exception {

		String linknmae = "";
		int selectedindex = 0;
		String IconIsslected = "";
		String markedlink = "";
		boolean linkFound = false;
		ArrayList<String> favlinkslist = new ArrayList<String>();

		try {

			if (GenericFactory.clickOnDepartmentByName(departmentName)) {
				favlinkslist = getDepartmentFavouriteslink();
				if (favlinkslist.size() > 0 && favlinkslist.contains(titleLink)) {
					List<WebElement> favouriteIconList = favouiteIcon;
					List<WebElement> favouriteLinkList = favouiteLinkList;

					// Logic to star mark to create favourite
					for (int i = 0; i < favouriteIconList.size(); i++) {
						IconIsslected = "";
						linknmae = favouriteLinkList.get(i).getAttribute("textContent").trim();
						IconIsslected = favouriteIconList.get(i).getAttribute("class").toString().trim();

						if (linknmae.equalsIgnoreCase(titleLink) && (!linknmae.contains("Global_content"))) {
							if (IconIsslected.contains("fav-star is-selected")
									&& favouriteIconList.get(i).isDisplayed()) {
								LogFactory.info(linknmae + " is already marked ");
								markedlink = titleLink;
								linkFound = true;
								break;
							} else {
								favouriteIconList.get(i).click();
								WaitFactory.WaitForElementToVisible(favouriteSavebtn);
								if (favouriteSavebtn.isDisplayed()) {
									favouriteSavebtn.click();
									LogFactory.info("Click On Save Button ");
									WaitFactory.WaitForinvisibilityOfElement(favouriteSavebtn);
									markedlink = titleLink;
									linkFound = true;
									break;
								}
							}

						}
					}
					// for loop end
					if (!linkFound) {
						markedlink = "link not found";
					}

				} else {
					markedlink = " WCM Test Link is Not present on given Departmnet ";
					LogFactory.info(markedlink);
				}
			} else {
				markedlink = "Not able to click on the given depertment";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return markedlink;
	}

	// *********************************************** GENERIC METHODS REQUIRED
	// FOR
	// FAVOURITE
	// ********************************************************************************

	// **************************************************** E - N - D
	// **********************************************************************************************************

	/**
	 * @author Shubham Raj This case describe that folder should be created with the
	 *         folder name given in Excel, if folder name is 'NA' it will create a
	 *         default folder named 'Test Folder'
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param1
	 *            Describe the name of the folder given by user in Test data sheet @
	 *            exception Exception e
	 */

	public static void verifyCreateFolder(String TCID, String folderName) throws Throwable {
		String flag = "Fail";
		String result = "Favourite Folder is not created ";

		try {

			WaitFactory.waitForPageLoaded();
			if (ToGetFavouritesFolder_HomePage().contains(folderName)) {
				LogFactory.info("Favourite folder is already exist so delete the existing folder first " + folderName);
				ToDeleteFavFolder(folderName);
			}

			if (favouiteActions_JDIN.size() > 0) {
				favouiteActions_JDIN.get(0).click();
			} else {
				favouiteActions.get(0).click();

			}

			Thread.sleep(3000);

			// [Naveen] // Execute the Java Script for the element which we find
			// out
			((JavascriptExecutor) BaseClass.wbDriver).executeScript("favActionsAddFolder();");
			LogFactory.info("Click On Add Folder Button");
			// Enter Folder Name
			addFolderNametxtbox.sendKeys(folderName);
			WaitFactory.WaitForTexttoBePresentInElement(addFolderNametxtbox,folderName);
			LogFactory.info("Entered Folder Name " + folderName);
			WaitFactory.WaitForElementToVisible(addbtn);
			WaitFactory.waitForElementClickable(addbtn).click();
			WaitFactory.WaitForinvisibilityOfElement(addbtn);
			// addbtn.click();
			LogFactory.info("Click On Add buuton");
			// WaitFactory.waitForPageLoaded();
			WebElement folder = BaseClass.wbDriver.findElement(By.xpath(".//*[@class='fav-link-body']"));
			WaitFactory.WaitForElementToVisible(folder);

			// To check Folder is already exist or not
			String errorobjtext = "";
			if (addFolderDuplicate.isDisplayed()) {
				errorobjtext = addFolderDuplicate.getText().toString().trim();
			}
			if (!(errorobjtext.length() > 0)) {
				// If does not Exist click on Save button

				// To Get Folder Name List after adding new folder
				// Thread.sleep(3000);
				// GenericFactory.navigateToHomePage();
				BrowserFactory.RefreshBrowser();
				ArrayList<String> FolderNameListHomepage = ToGetFavouritesFolder_HomePage();

				ArrayList<String> FolderNameListQuickModal = TOGetFolderListQuickModal();

				if (FolderNameListHomepage.contains(folderName) && FolderNameListQuickModal.contains(folderName)) { // Pass
					flag = "Pass";
					result = "Favourite folder is created and displaying on Homepage, Quickmodal Homepage, Department page ";
					LogFactory.info(result);
				} else { // fail
					flag = "Fail";
					result = "Favourite folder is not created ";
					LogFactory.info(result);
				}
			} else {
				// Folder already Exist
				flag = "Fail";
				result = "Favourite folder is already Exist ";
				LogFactory.info(errorobjtext);

				// Click on Cancel Button
				FavouriteAddFolderCancelbtn.click();
			}
			ReportFactory.reporterOutput(TCID,
					"Verify creating (Add) folder for Favourites from Favourite portlet on homepage.",
					"Create folder with name : " + folderName,
					"Favourite folder should get created and display on favourite portlet, Quickmodal on homepage, and on quick modal window on department page ",
					result, flag);

		} catch (Exception e) {
			String er = e.getMessage().substring(0, 180).toString().trim();

			ReportFactory.reporterOutput(TCID,
					"Verify creating (Add) folder for Favourites from Favourite portlet on homepage. ", folderName,
					"NA", er, flag);
			LogFactory.info(er);
		}

	}

	/**
	 * @author Shubham Raj This case describe that created favourite folder should
	 *         be deleted from the Homepage as well as from the Quick modal window
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param1
	 *            Describe the name of the folder to be deleted @ exception
	 *            Exception e
	 */

	public static void verifyDeleteFavouriteFolder(String TCID, String folderName) throws Throwable {
		String flag = "Fail";
		String result = "Favourite Folder is not deleted ";

		try {

			// To Get Folder Name List from Home Page

			ArrayList<String> actualfoldername1 = ToGetFavouritesFolder_HomePage();
			if (actualfoldername1.contains(folderName)) {

				// common Xpath to select Delete Button
				List<WebElement> deleteIconList = deleteIcon;

				for (int j = 0; j < actualfoldername1.size(); j++) {
					String actualFolerName = actualfoldername1.get(j).trim();

					if (actualFolerName.equals(folderName)) {
						@SuppressWarnings("unused")

						// to select the web element of delete btn
						WebElement WebDeleteButtonobj = deleteIconList.get(j);

						// move the cursor in corresponding foldername index
						Actions Action_obj = new Actions(BaseClass.wbDriver);
						Action_obj.moveToElement(WebDeleteButtonobj).build().perform();
						// Thread.sleep(3000);

						// use javaScriptExceuter to control delete functioality
						JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
						js.executeScript("arguments[0].click();", WebDeleteButtonobj);

						// Now click on Ok Button
						Thread.sleep(2000);
						BaseClass.wbDriver.findElement(By.xpath(".//*[@id='delete-folder-ok']")).click();
						LogFactory.info("Favourite folder is deleted. ");
						break;

					}
				}
				// After delete Check Folder is Exist or not or home page
				GenericFactory.navigateToHomePage();
				BrowserFactory.RefreshBrowser();
				ArrayList<String> FolderListHomePage = ToGetFavouritesFolder_HomePage();
				ArrayList<String> FolderListQuickModal = TOGetFolderListQuickModal();

				// After deletion Folder list should not contaion the deleted
				// Folder in List
				if ((!FolderListHomePage.contains(folderName)) && (!FolderListQuickModal.contains(folderName))) {
					flag = "Pass";
					result = "Favourite folder removed and is no more displayed on homepage and quick model window ";
					LogFactory.info("Folder is deleted sucussfully " + folderName);
					// Pass report

				} else {
					// Fail Report
					flag = "Fail";
					result = "Folder is not deleted ";
					LogFactory.info("Folder is not deleted sucussfully " + folderName);
				}
			} else {
				flag = "Pass";
				result = "Folder is not present so can not be deleteed- " + folderName;
				LogFactory.info("Folder is not present so can not be deleted- " + folderName);
			}

			ReportFactory.reporterOutput(TCID, "Verify deleting favourite folder ",
					"Delete the folder name : " + folderName,
					"The favorite deleted should not be available in Quick Favorite modal window, Favorite portal on homepage ",
					result, flag);
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
		} catch (Exception e) {
			String er = e.getMessage().substring(0, 160).toString().trim();

			ReportFactory.reporterOutput(TCID, "Verify deleting favourite folder  ", "NA", "NA", er, flag);
		}
	}

	/**
	 * @author Shubham Raj This case describes that user should be able to deleted a
	 *         favourite from link portlet and it should not be further displayed on
	 *         Homepage and Quick modal window
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 *            exception Exception e
	 */

	public static void verifyRemoveFavouriteLink(String TCID) throws Throwable {

		// String departmentName = "";
		String subDepartmentName = "";
		String title = "";
		String subDepartmentLang = "";
		String linkname = "";
		int selectedindex = 0;
		String IconIsslected = "";
		String titleLang = "";
		String ThirdlevelFolder = "";
		Boolean clickCheck = false;
		String DeletedLink_name = "";
		int exit_count = 2;
		List<String> deletedLink_list = new ArrayList<String>();

		try {
			ValidationFactory.isElementPresent(By.xpath("//input[@class='filter-box fav-filter']"));

			JavascriptExecutor js1 = (JavascriptExecutor) wbDriver;
			js1.executeScript("scroll(0, -250);");

			mainLoop: for (int i = 0; i < markedlinkNames.size(); i++) {
				if (exit_count == i) {
					break mainLoop;
				}
				department1 = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("DepartmentName").toString().trim();
				subDepartmentName = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("2ndLevel").toString().trim();
				subDepartmentLang = GenericFactory.getTranslation(subDepartmentName).toString().replaceAll("^\\[|\\]$",
						"");
				title = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("Title").toString().trim();
				String WCM_TCID = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("Test Case ID").toString().trim();
				ThirdlevelFolder = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("3rdLevelFolder").toString()
						.trim();

				if (GenericFactory.clickOnDepartmentByName(department1)) {
					{
						if (!ThirdlevelFolder.equals("NA")) {
							BrowserFactory.RefreshBrowser();
							System.out.println("test");
							GenericFactory.toClickOnFolder(ThirdlevelFolder, subDepartmentName);

						}
						departmentNames.add(department1);
						List<WebElement> allHeaderNamesAndLinks = BaseClass.wbDriver.findElements(By.xpath(
								".//*[@id='links-target']/div[not(@class='links-no-results') and not(@class='empty-link-portlet-container')]"));
						secondloop: for (int j = 0; j < allHeaderNamesAndLinks.size(); j++) {
							WebElement thirdLevelLinks = allHeaderNamesAndLinks.get(j);
							String[] headerName = (thirdLevelLinks.getText()).split("\n");
							List<WebElement> links = null;

							if (subDepartmentLang.equals(headerName[0])) {
								// links =
								// thirdLevelLinks.findElements(By.tagName("a"));

								List<WebElement> icons = null;

								if (!ThirdlevelFolder.equals("NA")) {
									icons = thirdLevelLinks.findElements(By
											.xpath("//div[contains(@parent-folder,'" + ThirdlevelFolder + "')]/span"));
									links = thirdLevelLinks.findElements(By.xpath("//div[contains(@parent-folder,'"
											+ ThirdlevelFolder + "')]/span/following-sibling::a"));
								} else {
									icons = thirdLevelLinks.findElements(By.xpath("//h5[contains(text(),'"
											+ subDepartmentName
											+ "')]/..//div[@class='link-group']/div/div[@class='link-item wrap']/span"));
									links = thirdLevelLinks.findElements(By.xpath("//h5[contains(text(),'"
											+ subDepartmentName
											+ "')]/..//div[@class='link-group']/div/div[@class='link-item wrap']/a"));

								}
								for (int k = 0; k < icons.size(); k++) {
									IconIsslected = "";
									linkname = links.get(k).getAttribute("textContent").trim();
									IconIsslected = icons.get(k).getAttribute("class").toString().trim();

									if (IconIsslected.contains("fav-star is-selected") && linkname.equals(title)) {
										LogFactory.info(
												linkname + " is already marked, Removing it from the link portlet");

										WaitFactory.waitForElements(icons, wbDriver);
										WaitFactory.waitForElementClickable(icons.get(k)).click();
										DeletedLink_name = links.get(k).getText().toString().trim();
										deletedLink_list.add(DeletedLink_name);
										break secondloop;

									}

								}

							}
						}
					}
				}
			}
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
			System.out.println("test");
			LogFactory.info("Deleted links from link portlet are: " + deletedLink_list);
			WebElement star = BaseClass.wbDriver.findElement(By.xpath("//div[@id='js-favorites']"));
			WaitFactory.waitForElementClickable(star).click();

			List<WebElement> Quick_icons_name = BaseClass.wbDriver.findElements(By.xpath(
					"//div[@class='link-group']//span[@class='icon fav-star is-selected']/following-sibling::a"));
			
			if (ValidationFactory.isElementPresent(By.xpath(
					"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]//span[@class='icon folder closed']"))) {

				WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
						"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]//span[@class='icon folder closed']")))
						.click();
			}
			WaitFactory.waitForElements(Quick_icons_name, wbDriver);
			if (Quick_icons_name.size() > 0) {
				for (int m = 0; m < Quick_icons_name.size(); m++) {
					String Quick_names = Quick_icons_name.get(m).getText().trim();
					QuickLinks.add(Quick_names);

				}
			}
			LogFactory.info("Links present after deleting on QuickModal window are: " + QuickLinks);
			if (!QuickLinks.contains(deletedLink_list) && !QuickLinks.isEmpty() && !deletedLink_list.isEmpty()) {
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Link portlet page ",
						"Selected links to be removed from link portlet page= " + deletedLink_list.toString(),
						"Favourite should get removed and should no more display on quick favourite modal window page",
						"Links successfully removed from <b>Quick favourite modal window page</b>", "PASS");

			} else if(QuickLinks.contains(deletedLink_list) && !QuickLinks.isEmpty() && !deletedLink_list.isEmpty()){
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Link portlet page ",
						"Selected links are not being removed: " + deletedLink_list.toString(),
						"Favourite should be removed and should no more display on quick favourite modal window page",
						"Links are not being removed from <b>quick favourite modal window page</b>", "FAIL");
			}
			else if(deletedLink_list.isEmpty()) {
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Link portlet page ",
						"Favourite Links are not present " + deletedLink_list.toString(),
						"Favourite should be removed and should no more display on quick favourite modal window page",
						"No sufficient favourites are present to execute  this test case</b>", "FAIL");
			}
			
			BrowserFactory.RefreshBrowser();
			LogFactory.info("The deleted links from Quick modal windows are: " + deletedLink_list);
			JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
			ArrayList<String> Homepage_link_names = new ArrayList<String>();
			js.executeScript("window.scrollBy(0,800)");
			if (ValidationFactory.isElementPresent(By.xpath(
					"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']"))) {

				WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
						"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']")))
						.click();
			}
			List<WebElement> homepageLinks_name = BaseClass.wbDriver.findElements(By.xpath(
					"//div[@class='fav-link-body']//span[@class='icon fav-star is-selected']//following-sibling::a"));
			for (int m = 0; m < homepageLinks_name.size(); m++) {
				String HomeIcon = homepageLinks_name.get(m).getText().toString().trim();
				Homepage_link_names.add(HomeIcon);

			}
			LogFactory.info("Links present after deleting on Homepage are: " + Homepage_link_names);
			if (!Homepage_link_names.contains(deletedLink_list) && !Homepage_link_names.isEmpty()
					&& !deletedLink_list.isEmpty()) {
				// String flag1="";
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Link portlet page ",
						"Selected links to be removed from Favourite Homepage portlet = " + deletedLink_list.toString(),
						"Favourite should get removed and should no more display on Favourite portlet on homepage",
						"Links successfully removed from <b>Favourite portlet on homepage</b>", "PASS");

			} else if(Homepage_link_names.contains(deletedLink_list) && !Homepage_link_names.isEmpty()
					&& !deletedLink_list.isEmpty()) {
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Link portlet page ",
						"Selected links are not being removed from Favourite portlet on homepage: "
								+ deletedLink_list.toString(),
						"Favourite should be removed and should no more display on Favourite portlet on homepage",
						"Links are not being removed from <b>Favourite portlet on homepage</b>", "FAIL");
			}
			else if(deletedLink_list.isEmpty()) {
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Link portlet page ",
						"Favourite Links are not present " + deletedLink_list.toString(),
						"Favourite should be removed and should no more display on quick Favourite portlet on homepage",
						"No sufficient favourites are present to execute  this test case</b>", "FAIL");
			}
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// **************************************************** E - N - D
	// **********************************************************************************************************

	/**
	 * @author Shubham Raj This case describes moving/dragging the favourite folder
	 *         from the top position to last
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 *            exception Exception e
	 */

	public static void verifyMovingFavouriteFolderandLink(String TCID) throws Throwable {
		String flag = "Fail";
		String result = "Favourite folder and link is not moving up and Down ";
		List<WebElement> elementlist;

		// using 'javascript' to scroll down

		try {

			JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
			js.executeScript("window.scrollBy(0,700)");
			/*
			 * if((ToGetFavouritesFolder_HomePage().size()>0 ) ||
			 * ToGetMarkedFavouriteslink_HomePage().size()>0)
			 * 
			 * {
			 */
			elementlist = BaseClass.wbDriver
					.findElements(By.xpath("//.//*[@id='favorites-target']/div/div/div/div/div/div[1]"));
			if (elementlist.size() > 1) {
				List<WebElement> moveFavouriteListIcon = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='icon drag ui-sortable-handle']"));
				int lastelemnetidex = moveFavouriteListIcon.size() - 1;

				Actions action = new Actions(BaseClass.wbDriver);

				WebElement from = moveFavouriteListIcon.get(0);
				String favouriteatFirstindexbeforeMove = elementlist.get(0).getText().toString().trim();
				WebElement to = moveFavouriteListIcon.get(lastelemnetidex);
				action.moveToElement(from).build().perform();
				Thread.sleep(1500);
				action.dragAndDrop(from, to).build().perform();
				Thread.sleep(2500);
				LogFactory.info(
						"Draging the webelement -" + favouriteatFirstindexbeforeMove + " from first index  to Last");
				GenericFactory.navigateToHomePage();
				BrowserFactory.RefreshBrowser();
				elementlist = BaseClass.wbDriver
						.findElements(By.xpath("//.//*[@id='favorites-target']/div/div/div/div/div/div[1]"));
				String favouriteatFirstindexAfterMove = elementlist.get(0).getText().toString().trim();
				String favouriteatLastindexAfterMove = elementlist.get(elementlist.size() - 1).getText().toString()
						.trim();
				if (!favouriteatFirstindexbeforeMove.equalsIgnoreCase(favouriteatFirstindexAfterMove)
						|| favouriteatFirstindexbeforeMove.equalsIgnoreCase(favouriteatLastindexAfterMove)) {

					flag = "Pass";
					result = "Favourite folder and link is moving up and down ";
					LogFactory.info("Draging the webelement from its destination to new destination is working ");
				} else {
					flag = "Fail";
					result = "Favourite folder and link is not  moving up and down ";
					LogFactory.info("Draging the webelement from its destination to new destination is not working ");
				}
			} else {
				flag = "Pass";
				result = "Favourite folder or link is not present or no sufficient favourits  can not validate ";
				LogFactory.info(result);
			}
			ReportFactory.reporterOutput(TCID,
					"Verify moving a favorite link and folder from favorite portlet on homepage ", "NA",
					"Favourite and Favourite folder should be moved accordingly ", result, flag);
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
		} catch (Exception e) {
			flag = "Fail";
			String er = e.getMessage().substring(0, 100).toString().trim();

			ReportFactory.reporterOutput(TCID,
					"Verify moving a favorite link and folder from favorite portlet on homepage ", "NA",
					"Favourite and Favourite folder should be moved accordingly ", er, flag);
			LogFactory.info(er);
		}

	}

	// **************************************************** E - N - D
	// **********************************************************************************************************

	// [ P29-- Verify Collapsing and Expanding a folder on Favourites portlet. ]
	/**
	 * @author Shubham Raj This case describes that upon clicking on the favourite
	 *         folder it should expand and should show the marked links
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 *            exception Exception e
	 */

	public static void verifyExpandAndCollapseFavroiteFolder(String TCID) throws Throwable {
		String flag = "Fail";
		String result = "Expand and Collapse Favourite Folder is not working ";
		String FolderName = "";

		try {

			// using 'javascript' to scroll down
			JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
			js.executeScript("window.scrollBy(0,700)");

			if (ToGetFavouritesFolder_HomePage().size() > 0) {

				int ClosedfoldercountBeforeExpand = favouriteFolderClosedIcon.size();

				List<WebElement> FolderListObj = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='fav-link-body']"));
				List<WebElement> Faviconlist = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='fav-link-body']/span"));
				for (int i = 0; i < FolderListObj.size(); i++) {

					WebElement folderobj = FolderListObj.get(i);
					List<WebElement> linkobj = folderobj.findElements(By.tagName("a"));
					if (!(linkobj.size() > 0)) {

						FolderName = folderobj.getText().toString().trim();
						Faviconlist.get(i).click();
						LogFactory.info("Clicked on folder to expand-  " + FolderName);

					}
				}

				int ClosedfoldercountAfterExpand = favouriteFolderClosedIcon.size();
				if (ClosedfoldercountAfterExpand == 0) {

					LogFactory.info("All Folder are Expanded ");

				}

				// No End Impersonate and

				GenericFactory.navigateToHomePage();

				GenericFactory.EndImpersonateUSER();

				GenericFactory.navigateToHomePage();
				GenericFactory.utilityMenuAdminClick();

				GenericFactory.impersonateUser(BaseClass.strUserRACFID);

				BrowserFactory.RefreshBrowser();
				int ClosedfoldercountafterRelogin = favouriteFolderClosedIcon.size();
				if (ClosedfoldercountafterRelogin == ClosedfoldercountBeforeExpand) {
					flag = "Pass";
					result = "Expand and Collapse Favourite and Folder is working ";
					LogFactory.info(result);
				} else {
					flag = "Fail";
					result = "Expand and Collapse Favourite and Folder is not working ";
					LogFactory.info(result);
				}
			} else {
				flag = "Pass";
				result = "Favourite and Folder both is not present so Expand and Collapse can not be verified ";
				LogFactory.info(result);
			}

			ReportFactory.reporterOutput(TCID, "Verify Collapsing and Expanding a folder on Favorites portlet ", "NA",
					"Favourite folder should be expanded or collapsed accordingly ", result, flag);
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
		} catch (Exception e) {
			String er = e.getMessage().substring(0, 180).toString().trim();

			ReportFactory.reporterOutput(TCID, "Verify Collapsing and Expanding a folder on favorites portlet ", "NA",
					"NA", er, flag);
			GenericFactory.navigateToHomePage();
			GenericFactory.endImpersonateOrLogoutUser();

			GenericFactory.utilityMenuAdminClick();

			GenericFactory.impersonateUser(BaseClass.strUserRACFID);
			GenericFactory.navigateToHomePage();

		}

		// To click on dealerpath homepge

	}

	// Verify Quick Favourite modal window on Home, Department and all levels of
	// Index pages.

	/**
	 * @author Shubham Raj This case describes that Quick Favorite modal window
	 *         should be available on homepage, department and all levels of index
	 *         pages as applicable
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 */
	public static void verifyQuickModalWindowOnHomepage(String TCID) throws Throwable {
		String Deptflag = "Fail";
		String Deptresult = "Favourite Links under Quick on Home page and on Departmnet page are not same   ";
		ArrayList<String> favouriteListHomePage = new ArrayList<String>();
		ArrayList<String> favouriteListHomePage_QuickModal = new ArrayList<String>();
		ArrayList<String> QuickMOdalfavouriteListOfDepartment = new ArrayList<String>();
		List<WebElement> departmentListCount;
		String departmentName = "";
		try {
			
			favouriteListHomePage_QuickModal = ToGetMarkedFavouriteslinkQuickModal();
			departmentListCount = ActivedepartmentList;
			for (int i = 1; i < departmentListCount.size(); i++) {
				departmentName = departmentListCount.get(i).getText().toString().trim();

				if (GenericFactory.toClickonDeptOnFavourite(departmentName)) {
					QuickMOdalfavouriteListOfDepartment = ToGetMarkedFavouriteslinkQuickModal();
					
					if ((favouriteListHomePage_QuickModal.equals(QuickMOdalfavouriteListOfDepartment))) {
						Deptflag = "Pass";
						Deptresult = "Links under Favourite Quick link Modal on Home Page and on Departmnet page are same  ";
						break;
					}

					else {

						Deptflag = "Fail";
						Deptresult = "Links under Favourite Quick link Modal on Home Page and on Departmnet page are not same  ";
						break;

					}

				}
			}
			//GenericFactory.navigateToIndexPage(userWCMContent);
			ReportFactory.reporterOutput(TCID, "Verify quick favourite link Modal and on Quick Link favourite Modal Departmnet Page are same or not",
					"NA", "Favourite link under Favourite Quick link Modal on Home Page and On Departmnet page must be same", Deptresult,
					Deptflag);
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
		} catch (Exception e) {
			String er = e.getMessage().substring(0, 160).toString().trim();

			ReportFactory.reporterOutput(TCID, "Verify quick Modal favouite Link On homepage and On Departmnet Page ",
					"NA", "NA", er, Deptflag);
		}

		// To click on dealerpath homepge

	}

	/**
	 * @author Shubham Raj This case describes that user type some random text in
	 *         the Filter text box that does not exist in any folder name nor any
	 *         favorite link.
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param2
	 *            Describe the valid/invalid input given by the user @ exception
	 *            Exception e
	 */

	public static void VerifyFilterforInvalidAndValidData(String TCID, String StrInputvalue) throws Throwable {
		String FavlinkListHomepage = "";
		String FavFolderListHomepage = "";
		String flag = "Fail";
		String result = "Favourite Filter is not working properly on homepage on entering data ";

		try {

			JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
			js.executeScript("window.scrollBy(0,700)");
			FavlinkListHomepage = ToGetMarkedFavouriteslink_HomePage().toString();
			FavFolderListHomepage = ToGetFavouritesFolder_HomePage().toString();
			if (FavlinkListHomepage.contains(StrInputvalue) || FavFolderListHomepage.contains(StrInputvalue)) {
				LogFactory.info("Verify Filter with valid input --" + StrInputvalue);
				WebElement Folder_present = BaseClass.wbDriver
						.findElement(By.xpath("//div[@class='fav-link-body' and contains(.,'My Fav Folder')]"));
				if (!Folder_present.isDisplayed()) {
					WaitFactory
							.waitForElementClickable(BaseClass.wbDriver.findElement(
									By.xpath("//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span")))
							.click();
				}
				FavFilter.click();
				FavFilter.sendKeys(StrInputvalue);
				
				WaitFactory.WaitForTexttoBePresentInElement(FavFilter,StrInputvalue);

				Thread.sleep(1000);
				FavlinkListHomepage = ToGetMarkedFavouriteslink_HomePage().toString();
				FavFolderListHomepage = ToGetFavouritesFolder_HomePage().toString();
				if (FavlinkListHomepage.contains(StrInputvalue) || FavFolderListHomepage.contains(StrInputvalue)) {
					// Pass with Valid Data
					flag = "Pass";
					result = "Favourite Filter is working properly on homepage on entering Valid data ";
					LogFactory.info(result);
				} else {
					// Fail with Valid Data
					flag = "Fail";
					result = "Favourite Filter is not working properly on home page on entering Valid data ";
					LogFactory.info(result);
				}

			} else {
				LogFactory.info("Verify Filter with invalid input --" + StrInputvalue);

				FavFilter.click();
				FavFilter.sendKeys(StrInputvalue);
				WaitFactory.WaitForTexttoBePresentInElement(FavFilter,StrInputvalue);

				Thread.sleep(1000);

				List<WebElement> WebFavlinkListHomepage = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='fav-link-item']"));
				List<WebElement> WebFavFolderListHomepage = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@class='fav-link-item folder']"));
				/*
				 * FavlinkListHomepage = ToGetMarkedFavouriteslink_HomePage().toString();
				 * FavFolderListHomepage = ToGetFavouritesFolder_HomePage().toString();
				 */
				if (WebFavlinkListHomepage.size() > 0 || WebFavFolderListHomepage.size() > 0) {
					flag = "Fail";
					result = "Favourite Filter is not working properly on home page on entering Invalid data ";
					LogFactory.info(result);
				} else {
					flag = "Pass";
					result = "Favourite Filter is working properly on home page on entering Invalid data ";
					LogFactory.info(result);
				}

			}

			ReportFactory.reporterOutput(TCID, "Verify filtering of the favorite portlet ", StrInputvalue,
					"No favorites should be displayed (filtered) with no error messages for invalid data and result should display for Valid Data ",
					result, flag);
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();
		} catch (Exception e) {
			String er = e.getMessage().substring(0, 125).toString().trim();
			flag = "Fail";
			ReportFactory.reporterOutput(TCID, "Verify filtering of the favorite portlet  ", "NA", "NA", er, flag);
		}
		/*
		 * GenericFactory.navigateToHomePage(); BrowserFactory.RefreshBrowser();
		 */
	}

	/**
	 * @author Shubham Raj This case describe that favourites will be displayed
	 *         irrespective of languages selected
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 *            exception Exception e
	 */

	public static void verifyFavouritesDifferentLanguagesOnHomepage(String TCID) throws Throwable {

		String flag = "Fail1";
		String result = "Favourites are not showing in user preferred languages on homepage ";
		String langValues = "";
		boolean isAllLanguageFavMatch = false;
		String preserveLangFirstLanguage = "";
		String nonMatchedLanguage = "";

		try {

			ArrayList<String> FavlinkHomepage_pref = ToGetMarkedFavouriteslink_HomePage();
			ArrayList<String> FavlinkQuickModal_pref = ToGetMarkedFavouriteslinkQuickModal();
			GenericFactory.utilityMenuMyPreferenceClickFavourites();
			LogFactory.info("Clicked on My Preference to change the Language");
			Thread.sleep(5000);
			if (ValidationFactory.getElementIfPresent(language_label)) {
				System.out.println("1");
				WaitFactory.WaitForElementToVisible(
						BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
				WebElement WE2 = BaseClass.wbDriver.findElement(By.xpath(".//*[@id='lang']"));
				WaitFactory.WaitForElementToVisible(WE2);
				Select lang_obj = new Select(WE2);
				List<WebElement> lang_obj_options = lang_obj.getOptions();
				preserveLangFirstLanguage = lang_obj.getFirstSelectedOption().getText().toString().trim();
				List<String> options_Text = new ArrayList<>();
				for (int i = 0; i < lang_obj_options.size(); i++) {
					options_Text.add(lang_obj_options.get(i).getText());
				}

				System.out.println("Check" + options_Text);
				if (lang_obj_options.size() >= 1) {

					LanguageBreak: for (int i = 0; i < lang_obj_options.size(); i++) {
						WE2 = wbDriver.findElement(By.xpath(".//*[@id='lang']"));
						WaitFactory.WaitForElementToVisible(WE2);
						lang_obj = new Select(WE2);
						lang_obj_options = lang_obj.getOptions();

						lang_obj.selectByVisibleText(options_Text.get(i).toString());
						langValues = lang_obj.getFirstSelectedOption().getText().toString().trim();
						allLanguage.add(langValues);
						LogFactory.info("Change into diffrerent Language");
						WaitFactory.WaitForElementToVisible(
								BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
						BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")).click();
						WaitFactory.waitforelementToinvisibile(
								BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
						FavlinkHomepage = ToGetMarkedFavouriteslink_HomePage();
						FavlinkQuickModal = ToGetMarkedFavouriteslinkQuickModal();
						if (FavlinkHomepage.containsAll(FavlinkHomepage_pref)

								&& FavlinkQuickModal.containsAll(FavlinkQuickModal_pref)) {
							isAllLanguageFavMatch = true;
						} else {
							nonMatchedLanguage = langValues;
							isAllLanguageFavMatch = false;
							break;
						}
						GenericFactory.utilityMenuMyPreferenceClickFavourites();
					}

					if (isAllLanguageFavMatch) {
						flag = "Pass";
						result = "All the favorites are displayed for different languages " + allLanguage
								+ " on favorites portlet on homepage and quick favorite modal window on home are matching ";
					} else {
						flag = "Fail";
						result = "All the favorites are not displayed/matched for the language" + nonMatchedLanguage
								+ " on favorites portlet on homepage and quick favorite modal window on home ";
						LogFactory.info("All the favorites are not displayed/Matched for " + nonMatchedLanguage
								+ " language on favorites portlet on homepage and quick favorite modal window");
					}

					WE2 = wbDriver.findElement(By.xpath(".//*[@id='lang']"));
					WaitFactory.WaitForElementToVisible(WE2);
					lang_obj = new Select(WE2);
					lang_obj_options = lang_obj.getOptions();
					lang_obj.selectByVisibleText(preserveLangFirstLanguage);
					WaitFactory.WaitForElementToVisible(
							BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));
					BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")).click();
					WaitFactory.waitforelementToinvisibile(
							BaseClass.wbDriver.findElement(By.xpath(".//*[@id='preference-save']")));

				} else {
					WaitFactory.WaitForElementToVisible(wblPreferenceCancel);
					wblPreferenceCancel.click();
					WaitFactory.waitforelementToinvisibile(wblPreferenceCancel);
					flag = "Pass";
					result = "Dealer/Employee is either having default language only or not having multiple languages access";
				}

			} else {
				WaitFactory.WaitForElementToVisible(wblPreferenceCancel);
				wblPreferenceCancel.click();
				WaitFactory.waitforelementToinvisibile(wblPreferenceCancel);
				flag = "Pass";
				result = "Dealer/Employee is either having default language only or not having multiple languages access";
			}
			ReportFactory.reporterOutput(TCID, "Verify favorites for different languages ", allLanguage.toString(),
					"Favorites should be displayed for the site irrespective of the languages selected on favorites portlet on homepage ",
					result, flag);

		} catch (Exception e) {
			flag = "Fail";
			String er = e.getMessage().substring(0, 160).toString().trim();
			ReportFactory.reporterOutput(TCID, "Verify favorites for different  languages ", "NA", "NA", er, flag);
			if (BaseClass.wbDriver.findElement(By.xpath(".//*[@id='my-preferences']/div/div/div[1]/button"))
					.isDisplayed()) {
				BaseClass.wbDriver.findElement(By.xpath(".//*[@id='my-preferences']/div/div/div[1]/button")).click();
			}
			LogFactory.info(er);
		}

		// To click on dealerpath homepge
	}

	/**
	 * @author Shubham Raj Verify Favourite for non-preferred deaprtment
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param2
	 *            Describe the Department name(s) from where favourite should be
	 *            selected
	 * @param param3
	 *            Describe all the marked links selected from different department @
	 *            exception Throwable
	 */

	public static void VerifyFavouriteForNonPreffredDept(String TCID, List<String> deptName, List<String> Favlink)
			throws Throwable {
		String flag = "Fail";
		String result = "Favourite is showing for non preffred departemnt ";

		try {

			// Click on Given Departmnet

			GenericFactory.checkDepartmentMyPreference();
			GenericFactory.uncheckDepartmentMyPreference(deptName);
			LogFactory.info("Uncheck and Disable the Departmnet " + deptName);

			ArrayList<String> FavlinknameOnhomepage = ToGetMarkedFavouriteslink_HomePage();
			ArrayList<String> FavlinknameQuickmodalWindow = ToGetMarkedFavouriteslinkQuickModal();

			GenericFactory.checkDepartmentMyPreference();

			/*
			 * WaitFactory .waitforelementToBeClickable(BaseClass.wbDriver.findElement(By.
			 * xpath("//*[@id='preference-save']"))) .click(); WaitFactory
			 * .waitforelementToinvisibile(BaseClass.wbDriver.findElement(By.
			 * xpath("//*[@id='preference-save']")));
			 */
			if (BaseClass.wbDriver.findElement(By.xpath("//*[@id='my-preferences']/div/div/div[1]/button/span"))
					.isDisplayed()) {
				BaseClass.wbDriver.findElement(By.xpath("//*[@id='my-preferences']/div/div/div[1]/button/span"))
						.click();
				LogFactory.info("Save Button is not working so closing the Department window page");

			}
			LogFactory.info("Click On save button");
			GenericFactory.navigateToHomePage();
			// BrowserFactory.RefreshBrowser();

			// if (Favlink.size() > 0) {
			if (FavlinknameOnhomepage.containsAll(Favlink) || FavlinknameQuickmodalWindow.containsAll(Favlink)) {
				flag = "Fail";
				result = "Favourite is showing for non preffred departemnt ";
				// Fail
				LogFactory.info("Favourite is present on Departmnet ");

			} else {
				flag = "Pass";
				result = "Favourite is not showing for non preffred departemnt ";
				LogFactory.info("Favourite is not showing for non preffred departemnt ");

			}
			/*
			 * } else { flag = "Pass"; result = "The given link test data is blank  " +
			 * Favlink; LogFactory.info(result); }
			 */

			ReportFactory.reporterOutput(TCID, "Verify favorites for non-preferred departments ",
					"Deparatment Name - " + GenericFactory.getTranslation(deptName.toString()).toString() + " "
							+ "Favorites Link - " + Favlink,
					"Favorites should not be displayed if the particular department is non-preferred department of any user ",
					result, flag);
			GenericFactory.navigateToHomePage();

		} catch (Exception e) {
			String er = e.getMessage().substring(0, 125).toString().trim();
			flag = "Fail";
			LogFactory.info(er);
			ReportFactory.reporterOutput(TCID, "Verify favorites for non-preferred departments ", "NA", "NA", er, flag);
			if (BaseClass.wbDriver.findElement(By.xpath(".//*[@id='my-preferences']/div/div/div[1]/button"))
					.isDisplayed()) {
				BaseClass.wbDriver.findElement(By.xpath(".//*[@id='my-preferences']/div/div/div[1]/button")).click();
			}
		}

	}

	/**
	 * @author Shubham Raj This method is to impersonate the Favourite user if
	 *         present in Dealer info sheet
	 * @param param1
	 *            Describe the RACFID of the user to be impersonated @ exception
	 * Exception e
	 */
	public static boolean impersonateFavUser(String RACFID) throws Throwable {
		try {

			BaseClass.errorUserFound = false;
			if (!GenericFactory.isNull(RACFID)) {System.out.println("test");
				if (ValidationFactory
						.isElementPresent(WaitFactory.explicitWaitByXpath("//b[text() = 'Admin Links']"))) {
					// BrowserFactory.RefreshBrowser();
					ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='Analyze_User']")).click();
					LogFactory.info("Clicked Analyze User link from left navigation.....");
					WaitFactory.waitForPageLoaded();
					LogFactory.info("Enter User RACF ID....." + RACFID);

					// WaitFactory.waitforelementToinvisibile(ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='analyseUserId']")));
					WebElement ele = ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='analyseUserId']"));
							ele.sendKeys(RACFID);
					
					WaitFactory.WaitForTexttoBePresentInElement(ele,RACFID);


					LogFactory.info("Click on Analyze Button.....");
					ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='analyzeUserButton']")).click();
					WaitFactory.waitForPageLoaded();

					if (ValidationFactory.validateButtonEnable(By.xpath(".//*[@id='impersonateUser']"))) {

						AnalyzeFavUserInformation(RACFID);

						ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='impersonateUser']")).click();

						if (ValidationFactory.getElementIfPresent(By.xpath("//a[@id='endimpersonatelink']")) != null) {
							BaseClass.errorUserFound = false;
							strUserRACFID = RACFID;
							LogFactory.info("User " + RACFID
									+ " not able to login due to the error message : DealerPath Application is not available [AccountFlex is down] ");
							return false;
						}

						LogFactory.info("User " + RACFID + " successfully logged into appllication ");

					} else {

						// BaseClass.errorUserFound = true;
						LogFactory.error("Unable to locate the button Impersonate/ it is disabled");
						// throw new SkipException("Run mode of testcase " +
						// "test" + " is N");

					}

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("test");
		}
		return true;
	}

	/**
	 * @author Shubham Raj This method is to analyze the impersonated user
	 * @param param1
	 *            Describe the RACFID of the user to be analyzed @ exception
	 * Exception e
	 */

	public static void AnalyzeFavUserInformation(String strRACFID) {

		List<String> userType = new ArrayList<String>();
		List<String> accessCode = new ArrayList<String>();
		String strUserType = "";
		analyzerUserMap.remove("User Type");

		List<WebElement> wbelAllrows = wbDriver.findElements(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr"));

		for (int i = 1; i <= wbelAllrows.size(); i++) {

			String test = wbDriver
					.findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[1]")).getText()
					.toString().trim();

			if (test.contains("Department")) {
				String strDeptList = BaseClass.wbDriver
						.findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
						.getText().toString().trim();
				String temp = strDeptList.replaceAll("[\\t\\n\\r]+", ", ");
				List<String> deptList = GenericFactory.splitString(temp, ",");
				analyzerFavUserMap.put("Department", deptList);
			}
			if (test.contains("Security Groups")) {
				String strSecurityGroup = BaseClass.wbDriver
						.findElement(By.xpath("//*[@id='analyseUserForm']/table[2]/tbody/tr[" + i + "]/td[2]"))
						.getText().toString().trim();
				String temp = strSecurityGroup.replaceAll("[\\t\\n\\r]+", ", ");
				List<String> SecurityGroups = GenericFactory.splitString(temp, ",");
				analyzerFavUserMap.put("Security Groups", SecurityGroups);

			}

		}

		LogFactory.info(strRACFID + " Analyze User Details" + analyzerUserMap);
	}
	// Verify copy Favourites for dealer user.

	/**
	 * @author Shubham Raj This case describes that Favorites from one dealer to
	 *         another dealer should be copied only if those links are accessible
	 *         (in terms of country tagging, product type tagging, additional RACF
	 *         group tagging, department access) to the dealer who is copying them.
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param2
	 *            Describe the RACFID of the dealer/employee whose favourites need
	 *            to be copied @ exception Exception e
	 */

	public static void verifyCopyFavouriteForDealer(String TCID, String RACFID) throws Throwable {
		try {
			String CountryCode = "";
			String department_WCM = "";
			String Product_type = "";
			String HomeIcon = "";
			String RACFGroup = "";
			String dealerType = "";
			Thread.sleep(5000);
			GenericFactory.EndImpersonateUSER();
			List<String> Department_2ndUser;
			String CountryCode_2ndUser = "";
			boolean department_new_flag = false;
			boolean countryFlag = false;
			boolean productFlag = false;
			boolean racfFlag = false;
			boolean wcmDealerType = false;
			boolean title_flag = false;
			String title = "";
			String copy = "";
			ArrayList<String> Homepage_link_names = new ArrayList<String>();
			ArrayList<String> CopiedTitle = new ArrayList<String>();
			ArrayList<String> NonCopiedTitle = new ArrayList<String>();
			List<String> Allflag_true_copied = new ArrayList<>();
			List<String> Allflag_true_nonCopied = new ArrayList<>();
			List<String> CountryFalse_copiedtrue = new ArrayList<>();
			List<String> productfalse_copiedTrue = new ArrayList<>();
			List<String> RacfGroup_copiedTrue = new ArrayList<>();
			List<String> RacfGroup_Noncopied = new ArrayList<>();
			List<String> DepFalse_copiedTrue = new ArrayList<>();
			String DealerProduct_type_2ndUser = "";
			String resultflag = "";
			String RACFGroup_2ndUser = "";
			String Noncopy = "";
			String dealerType_2nduser = "";
			String title_result = "";
			String Allflag_title_result = "";
			String country_result = "";
			String RACF_result = "";
			String product_result = "";
			String dep_result = "";
			LinkedHashMap rowdata = null;
			String FinalResult = "";
			
			ExcelFactory.getUserWCMContentnew(RACFID);
			GenericFactory.navigateToHomePage();
			if (flagDealerType != "NA") {

				if (impersonateFavUser(RACFID)) {
System.out.println("test");
					if (ValidationFactory.isElementPresent(By.xpath("//div[@class='section-header-actions']/a"))) {
						JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
						js.executeScript("window.scrollBy(0,500)");
						BaseClass.wbDriver.findElement(By.xpath("//div[@class='section-header-actions']/a")).click();
						// Thread.sleep(1500);
						// if(ValidationFactory.isElementPresent(Click_CopyFromUser)){

						JavascriptExecutor js1 = (JavascriptExecutor) BaseClass.wbDriver;
						js1.executeScript("favActionsCopyFavorites();");
						LogFactory.info("Clicked on Copy from user button");

						WaitFactory.WaitForElementToVisible(BaseClass.wbDriver
								.findElement(By.xpath("//h4[@class='modal-title' and @id='copy-favorites-header']")));
						WebElement header1 = ValidationFactory.getElementIfPresent(
								By.xpath("//h4[@class='modal-title' and @id='copy-favorites-header']"));
						if (ValidationFactory.isElementPresent(header1)) {
							WebElement test = ValidationFactory
									.getElementIfPresent(By.xpath(".//*[@class='form-group']/div/input"));
							test.sendKeys(strUserRACFID);
							WaitFactory.WaitForTexttoBePresentInElement(test,strUserRACFID);
							
							LogFactory.info("Entered " + BaseClass.strUserRACFID + "to copy favourites");
							WaitFactory.waitForElementClickable(
									BaseClass.wbDriver.findElement(By.xpath("//button[@id='copy-favorites-copy']")))
									.click();

							/*
							 * if(invalidEmployee.isDisplayed()){ String error =
							 * invalidEmployee.getText().toString().trim(); LogFactory.info(
							 * error+" is being displayed, Please check the user in Test data sheet" );
							 * WaitFactory.waitForElementClickable(cancel_btn). click(); }
							 */
						}
					}

					String str = analyzerFavUserMap.get("Department").toString().trim();
					String strnew = str.replaceAll("\\[", "").replaceAll("\\]", "");
					Department_2ndUser = GenericFactory.splitString(strnew, ",");

					RACFGroup_2ndUser = analyzerFavUserMap.get("Security Groups").toString().trim();
					CountryCode_2ndUser = userFavWCMData.get(0).get("Dealer_Country").toString().trim();
					DealerProduct_type_2ndUser = userFavWCMData.get(0).get("Dealer_ProductType").toString().trim();
					dealerType_2nduser = userFavWCMData.get(0).get("Dealer_Type").toString().trim();

					if (ValidationFactory.isElementPresent(By.xpath(
							"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']"))) {
						WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
								"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']")))
								.click();
					}
					List<WebElement> homepageLinks_name = BaseClass.wbDriver.findElements(By.xpath(
							"//div[@class='fav-link-body']//span[@class='icon fav-star is-selected']//following-sibling::a"));
					for (int m = 0; m < homepageLinks_name.size(); m++) {
						HomeIcon = homepageLinks_name.get(m).getText().toString().trim();
						Homepage_link_names.add(HomeIcon);
					}

					for (int i = 0; i < markedLinks.size(); i++) {

						department_WCM = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("DepartmentName")
								.toString().trim();
						CountryCode = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("Country").toString().trim();
						Product_type = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("ProductType").toString()
								.trim();
						RACFGroup = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("RACFGroups").toString().trim();
						dealerType = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("DealerType (Main/Sub)")
								.toString().trim();
						title = GenericFactory.getWCMByTCID(markedLinks.get(i)).get("Title").toString().trim();

						countryFlag = GenericFactory.userAndWCMCountryComparison(CountryCode_2ndUser, CountryCode);
						productFlag = GenericFactory.userAndWCMProductTypeComparison(DealerProduct_type_2ndUser,
								Product_type);
						racfFlag = GenericFactory.verifyRacfGroupMatched(RACFGroup_2ndUser);
						

						if (Department_2ndUser.contains("Finance & Incentives")) {
							department_new_flag = true;
						}
						List<WebElement> folder_icon = BaseClass.wbDriver.findElements(
								By.xpath("//div[@class='fav-link-body']//span[@class='icon folder closed']"));
						for (int j = 0; j < folder_icon.size(); j++) {

							if (ValidationFactory.isElementPresent(
									By.xpath("//div[@class='fav-link-body']//span[@class='icon folder closed']"))) {
								folder_icon.get(j).click();
							}
						}
						if (countryFlag && productFlag && racfFlag && department_new_flag) {

							if (Homepage_link_names.contains(title)) {
								Allflag_true_copied.add(title);
							} else {
								Allflag_true_nonCopied.add(title);
							}

						} else if (!countryFlag) {

							if (Homepage_link_names.contains(title)) {
								CountryFalse_copiedtrue.add(title);
							}
						} else if (!productFlag) {

							if (Homepage_link_names.contains(title)) {
								productfalse_copiedTrue.add(title);
							}
						} else if (!racfFlag) {

							if (!RACFGroup.equals("NA")) {

								if (Homepage_link_names.contains(title)) {
									RacfGroup_copiedTrue.add(title);
								}
							}
						} else if (!department_new_flag) {

							if (Homepage_link_names.contains(title)) {
								DepFalse_copiedTrue.add(title);
							}
						}

					}
					if (Allflag_true_copied.size() > 0) {
						resultflag = "PASS";
						Allflag_title_result = "All taggings are matching an the favourite links " + Allflag_true_copied
								+ " are copied successfully";
					}

					if (Allflag_true_nonCopied.size() > 0) {
						resultflag = "FAIl";
						title_result = "All taggings are matching but the favourites links " + Allflag_true_nonCopied
								+ " are not copied";
					}
					if (CountryFalse_copiedtrue.size() > 0) {
						resultflag = "FAIl";
						country_result = "User country are not matching for the links " + CountryFalse_copiedtrue
								+ " but the favourite titles are copied";
					}
					if (productfalse_copiedTrue.size() > 0) {
						resultflag = "FAIl";
						product_result = "User Product are not matching for the links " + productfalse_copiedTrue
								+ " but the favourite titles are copied";
					}
					if (RacfGroup_copiedTrue.size() > 0) {
						resultflag = "FAIl";
						RACF_result = "User RACF groups are not matching for the links " + RacfGroup_copiedTrue
								+ " but the favourite titles are copied";
					}
					if (DepFalse_copiedTrue.size() > 0) {
						resultflag = "FAIl";
						dep_result = "User departments are not matching for the links " + DepFalse_copiedTrue
								+ " but the favourite titles are copied";
					}
				}

				FinalResult = Allflag_title_result + "</br> " + title_result + "</br> " + country_result + "</br> "
						+ product_result + "";

				ReportFactory.reporterOutput(TCID, "Copy favourites from Employee/Dealer",
						"Favourites copied from the user <b>" + strUserRACFID + "</b> to <b>" + RACFID + "</b> user",
						"Favorites from one dealer/Employee to another dealer/Employee should be copied only if those links are accessible (in terms of additional RACF group tagging, Dealer Principal department access) to the employee/authorized contingent user who is copying them ",
						FinalResult, resultflag);
				Thread.sleep(5000);
				GenericFactory.EndImpersonateUSER();
				System.out.println("test");
				impersonateFavUser(BaseClass.strUserRACFID);
				GenericFactory.navigateToHomePage();
				WaitFactory.waitForPageLoaded();

			} else {

				ReportFactory.reporterOutput(TCID, "Copy favourites from Employee/Dealer", "NA",
						"Favorites from one dealer/Employee to another dealer/Employee should be copied only if those links are accessible (in terms of additional RACF group tagging, Dealer Principal department access) to the employee/authorized contingent user who is copying them ",
						"Copoy Favourite from another user is not applicable for JDIN users", "PASS");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @author Shubham Raj This method will verify that on changing product type,
	 *         favourite should be filtered accordingly
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param2
	 *            Describe the Department name from where favourite should be
	 *            selected
	 * @param param3
	 *            Describe the marked links selected from different department
	 * @param param4
	 *            Describe the list of WCM product fetched from Excel sheet
	 * @param param5
	 *            Describe the product list selected by the user @ exception
	 *            Description of exception you are handling
	 */

	public static void VerifyFavouritesOnchangingProductType(String TCID, String DepartmentName, String LinkName,
			String WCMproductlist, String UserProducts) throws Throwable {

		String flag = "Fail";
		String result = "Favourites On changing Product Type is not working for the given WCM Product Type";
		ArrayList<String> QuciMarkedFavlink_Homepage = new ArrayList<String>();
		ArrayList<String> MarkedFavlinkhomepage = new ArrayList<String>();
		ArrayList<String> MarkedFavlinkDept = new ArrayList<String>();

		String ProducSegmnet = "";

		try {
			String createdlink = toMarkLinkFavourite(DepartmentName, LinkName);
			if (createdlink.equalsIgnoreCase(LinkName)) {
				MarkedFavlinkDept = ToGetMarkedFavouriteslink_Department();
				GenericFactory.navigateToHomePage();

				if (GenericFactory.selectProductsFromProductSegmentList(
						(GenericFactory.getParentProductSegmnentList(WCMproductlist.toString())))) {

					QuciMarkedFavlink_Homepage = ToGetMarkedFavouriteslinkQuickModal();
					MarkedFavlinkhomepage = ToGetMarkedFavouriteslink_HomePage();

					// Check the link if Product is unchecked
					if (QuciMarkedFavlink_Homepage.contains(LinkName) && MarkedFavlinkhomepage.contains(LinkName)
							&& MarkedFavlinkDept.contains(LinkName)) {
						flag = "Fail";
						result = "Favourite Link : " + LinkName
								+ " is still visible on applying Product Type flitering";
					} else {
						// Pass
						flag = "Pass";
						result = "Favourite Link : " + LinkName
								+ " filtered out successfully on applying Product Type flitering";
					}
				} else {
					flag = "Pass";
					result = "Unable to perform product filtering since the content is tagged more products than users";
				}
			} else {
				flag = "Pass";
				result = "Favourite links : " + LinkName + " is not availabe or not marked as favourite link";
			}

			ReportFactory.reporterOutput(TCID, "Verify Favourite Portelt on applying Product Type filtering ",
					"<B>User products : </B>" + String.join(", ", UserProducts) + "" + "<B>WCM products :<B>"
							+ String.join(", ", WCMproductlist),
					"Favorites should be filtered out based on product types filters.", result, flag);
			GenericFactory.checkAllProducts();
			GenericFactory.navigateToHomePage();

		} catch (Exception e) {

			String er = e.getMessage().substring(0, 160).toString().trim();
			LogFactory.info(er);
			flag = "Fail";
			ReportFactory.reporterOutput(TCID, "Verify to favourite on changing Product Type ", "NA", "NA", er, flag);
		}
	}

	/**
	 * @author Shubham Raj This case describes that user should be able to deleted a
	 *         favourite from Quick modal window and it should not be further
	 *         displayed on Homepage
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 *            exception Exception e
	 */

	public static void ToRemoveFavouriteLinkfromQuickmodalwindow(String TCID) throws Throwable {

		String InnerLink = "";
		String OuterLink = "";
		boolean insidelink_flag = false;
		boolean OutsideLink__fag = false;
		String HomeIcon = "";
		GenericFactory.navigateToHomePage();
		BrowserFactory.RefreshBrowser();
		ArrayList<String> Deleted_Linkitems = new ArrayList<String>();
		ArrayList<String> Homepage_link_names = new ArrayList<String>();
		WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath("//div[@id='js-favorites']")))
				.click();
		if (ValidationFactory.isElementPresent(By.xpath(
				"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]//span[@class='icon folder closed']"))) {

			WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
					"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]//span[@class='icon folder closed']")))
					.click();
			WaitFactory.waitForElements(BaseClass.wbDriver.findElements(By.xpath(
					"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]/following-sibling::div[starts-with(@parent-folder,'2')]//span/following-sibling::a")),
					wbDriver);
		}

		for (int i = 0; i < markedlinkNames.size(); i++) {

			if (i % 2 == 0) {

				List<WebElement> Folder_icon = BaseClass.wbDriver.findElements(By.xpath(
						"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]/following-sibling::div[starts-with(@parent-folder,'2')]//span"));
				List<WebElement> Folder_icon_name = BaseClass.wbDriver.findElements(By.xpath(
						"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]/following-sibling::div[starts-with(@parent-folder,'2')]//span/following-sibling::a"));

				innerloop: for (int j = 0; j < Folder_icon_name.size(); j++) {

					InnerLink = Folder_icon_name.get(j).getText().toString().trim();
					if (markedlinkNames.get(i).equals(InnerLink)) {
						Deleted_Linkitems.add(InnerLink);
						LogFactory.info("Clicked on " + Folder_icon_name.get(j).getText() + " link");
						WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
						Thread.sleep(2000);
						Folder_icon.get(j).click();
						Thread.sleep(3000);
						WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
						insidelink_flag = true;
						break innerloop;
					}

				}
			} else {
				JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
				js.executeScript("window.scrollBy(0,200)");
				// BrowserFactory.RefreshBrowser();
				List<WebElement> outer_icon = BaseClass.wbDriver
						.findElements(By.xpath("//div[@class='link-group-container']//div[@class='link-item']/span"));
				List<WebElement> outer_icon_name = BaseClass.wbDriver.findElements(By.xpath(
						"//div[@class='link-group-container']//div[@class='link-item']/span/following-sibling::a"));
				outerlinkLoop: for (int k = 0; k < outer_icon_name.size(); k++) {
					{
						Thread.sleep(2000);
						OuterLink = outer_icon_name.get(k).getText().toString().trim();

						if (markedlinkNames.get(i).equals(OuterLink)) {
							Deleted_Linkitems.add(OuterLink);
							Thread.sleep(2000);
							WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
							LogFactory.info("Clicked on " + outer_icon_name.get(k).getText() + " link");
							Thread.sleep(3000);
							outer_icon.get(k).click();
							Thread.sleep(2000);
							WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
							// BrowserFactory.RefreshBrowser();
							OutsideLink__fag = true;
							break outerlinkLoop;
						} else
							continue;
					}
				}
			}
			if (insidelink_flag == true && OutsideLink__fag == true) {
				break;
			}
		}

		LogFactory.info("The deleted links from Quick modal windows are: " + Deleted_Linkitems);
		BrowserFactory.RefreshBrowser();
		JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
		js.executeScript("window.scrollBy(0,800)");
		if (ValidationFactory.isElementPresent(By.xpath(
				"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']"))) {

			WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
					"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']")))
					.click();
		}
		List<WebElement> homepageLinks_name = BaseClass.wbDriver.findElements(By.xpath(
				"//div[@class='fav-link-body']//span[@class='icon fav-star is-selected']//following-sibling::a"));
		for (int m = 0; m < homepageLinks_name.size(); m++) {
			HomeIcon = homepageLinks_name.get(m).getText().toString().trim();
			Homepage_link_names.add(HomeIcon);

		}
		LogFactory.info("Links present after deleting on Homepage are: " + Homepage_link_names);
		if (!Homepage_link_names.contains(Deleted_Linkitems) && !Deleted_Linkitems.isEmpty()) {
			// String flag1="";
			ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link On QuickModal window ",
					"Selected links to be removed from QuickModal window = " + Deleted_Linkitems.toString(),
					"Favourite should be removed from Quick Modal and should no more display on Favourite portlet on homepage",
					"Links successfully removed from Quick Link", "PASS");

		} else if(Homepage_link_names.contains(Deleted_Linkitems) && !Deleted_Linkitems.isEmpty()) {
			ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link On QuickModal window ",
					"Selected links are not being removed from the HomePage: " + Deleted_Linkitems.toString(),
					"Favourite should be removed from Quick Modal and should no more display on Favourite portlet on homepage",
					"Links are not being removed from HomePage", "FAIL");
		}
		else if(Deleted_Linkitems.isEmpty()) {
			ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link On QuickModal window ",
					"Favourite links are not present " + Deleted_Linkitems.toString(),
					"Favourite should be removed from Quick Modal and should no more display on Favourite portlet on homepage",
					"No sufficient favourite links found to execute this test case", "FAIL");
		}
		GenericFactory.navigateToHomePage();
		WaitFactory.waitForPageLoaded();
	}

	/**
	 * @author Shubham Raj This case describes that user should be able to deleted a
	 *         favourite from homepage portlet and it should not be further
	 *         displayed on Quick modal window
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 *            exception Exception e
	 */

	public static void ToRemoveFavLinkfromFavoritesPortlethomepage(String TCID) throws Throwable {
		String title = "";

		String Markerfavlink = "";
		String subDepartmentName = "";
		String flag = "Fail";
		boolean Outsidelink_flag = false;
		String result = "Favourite is not removed and displaying on 'Favourite' Portlet on homepage,quick favourite modal window Home page and Department";
		ArrayList<String> Favlinklistquick = new ArrayList<String>();
		ArrayList<String> Favlinklisthomepage = new ArrayList<String>();
		ArrayList<String> DeletedLinks = new ArrayList<String>();
		ArrayList<String> Outer_IconList = new ArrayList<String>();
		String InnerLinkName = "";
		String OuterLinkName1 = "";
		String OuterLinkName2 = "";
		boolean insidelink_flag = false;
		try {

			JavascriptExecutor js = (JavascriptExecutor) BaseClass.wbDriver;
			js.executeScript("window.scrollBy(0,800)");

			for (int i = 0; i < markedlinkNames.size(); i++) {

				// LinkedHashMap map =
				// ExcelFactory.getWCMByTCID(markedLinks.get(i));
				List<WebElement> icons = null;
				if (i % 2 == 0) {
					// WaitFactory.WaitForElementToVisible(BaseClass.wbDriver.findElement(By.xpath("//div[@class='fav-link-body'
					// and contains(.,'My Fav Folder')]//span[@class='icon
					// folder']")));

					if (ValidationFactory.isElementPresent(By.xpath(
							"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']"))) {
						LogFactory.info("Clicked on Folder to expand");
						WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
								"//div[@class='fav-link-body' and contains(.,'My Fav Folder')]//span[@class='icon folder  closed']")))
								.click();
					} else
						LogFactory.info("Folder already in expanded form");
					List<WebElement> folder_icon = BaseClass.wbDriver.findElements(By.xpath(
							"//div[@class='fav-link-item child']/div/div/span[not(@class='icon drag ui-sortable-handle')]"));
					List<WebElement> Folder_Link_name = BaseClass.wbDriver.findElements(
							By.xpath("//div[@class='fav-link-item child']/div/div/span/following-sibling::a"));

					innerloop: for (int k = 0; k < Folder_Link_name.size(); k++) {

						InnerLinkName = Folder_Link_name.get(k).getText().trim();

						if (markedlinkNames.get(i).equals(InnerLinkName)) {
							DeletedLinks.add(InnerLinkName);
							WaitFactory.waitForElements(folder_icon, wbDriver);
							LogFactory.info("Clicked on " + Folder_Link_name.get(k).getText() + " link");
							folder_icon.get(k).click();
							WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
							insidelink_flag = true;
							break innerloop;
						}
					}

				} else {
					WebElement outside_iconFirst = ValidationFactory.getElementIfPresent(By.xpath(
							"//div[@class='fav-link-item']/div/div/span[not(@class='icon drag ui-sortable-handle')]"));
					if (outside_iconFirst != null) {
						List<WebElement> outside_List = BaseClass.wbDriver.findElements(By.xpath(
								"//div[@class='fav-link-item']/div/div/span[not(@class='icon drag ui-sortable-handle')]"));

						for (int j = 0; j < outside_List.size(); j++) {

							List<WebElement> OutSide_Link_nameFIrst = BaseClass.wbDriver.findElements(
									By.xpath("//div[@class='fav-link-item']/div/div/span/following-sibling::a"));
							String Outer_LinkNameFirst = OutSide_Link_nameFIrst.get(j).getText().trim();

							if (markedlinkNames.get(i).equals(Outer_LinkNameFirst)) {
								WaitFactory.waitForElements(OutSide_Link_nameFIrst, wbDriver);
								OuterLinkName1 = OutSide_Link_nameFIrst.get(j).getText().trim();
								DeletedLinks.add(OuterLinkName1);
								WaitFactory.waitForElements(outside_List, wbDriver);
								LogFactory.info("Clicked on " + OutSide_Link_nameFIrst.get(j).getText() + " link");
								outside_List.get(j).click();
								WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
								Outsidelink_flag = true;
								break;

							}
						}

					}

					if (Outsidelink_flag == false) {
						WebElement outside_iconLast = ValidationFactory.getElementIfPresent(By.xpath(
								"//div[@class='fav-link-item last']/div/div/span[not(@class='icon drag ui-sortable-handle')]"));

						if (outside_iconLast != null) {
							List<WebElement> outside_List = BaseClass.wbDriver.findElements(By.xpath(
									"//div[@class='fav-link-item last']/div/div/span[not(@class='icon drag ui-sortable-handle')]"));

							for (int j = 0; j < outside_List.size(); j++) {

								List<WebElement> OutSide_Link_nameLast = BaseClass.wbDriver.findElements(By
										.xpath("//div[@class='fav-link-item last']/div/div/span/following-sibling::a"));
								String Outer_LinkNameLast = OutSide_Link_nameLast.get(j).getText().trim();
								if (markedlinkNames.get(i).equals(Outer_LinkNameLast)) {
									OuterLinkName2 = OutSide_Link_nameLast.get(j).getText().trim();
									DeletedLinks.add(OuterLinkName2);
									Outsidelink_flag = true;
									WaitFactory.waitForElements(outside_List, wbDriver);
									LogFactory.info("Clicked on " + OutSide_Link_nameLast.get(j).getText() + " link");
									outside_List.get(j).click();
									WaitFactory.WaitForElementToVisible(PopoverHeaderTitile);
									break;

								}

							}
						}
					}

				}
				if (insidelink_flag == true && Outsidelink_flag == true) {
					break;
				}
			}
			LogFactory.info("Deleted links are: " + DeletedLinks);
			WebElement star = BaseClass.wbDriver.findElement(By.xpath("//div[@id='js-favorites']"));
			WaitFactory.waitForElementClickable(star).click();

			List<WebElement> Quick_icons_name = BaseClass.wbDriver.findElements(By.xpath(
					"//div[@class='link-group']//span[@class='icon fav-star is-selected']/following-sibling::a"));

			if (ValidationFactory.isElementPresent(By.xpath(
					"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]//span[@class='icon folder closed']"))) {

				WaitFactory.waitForElementClickable(BaseClass.wbDriver.findElement(By.xpath(
						"//div[@class='link-group-container']//div[@class='link-item folder-space' and contains(.,'My Fav Folder')]//span[@class='icon folder closed']")))
						.click();
			}
			WaitFactory.waitForElements(Quick_icons_name, wbDriver);
			if (Quick_icons_name.size() > 0) {
				for (int m = 0; m < Quick_icons_name.size(); m++) {
					String Quick_names = Quick_icons_name.get(m).getText().trim();
					QuickLinks.add(Quick_names);

				}
			}
			LogFactory.info("Links present after deleting on QuickModal window are: " + QuickLinks);
			if (!QuickLinks.contains(DeletedLinks) && !DeletedLinks.isEmpty()) {
				// String flag1="";
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link On Favorites portlet on homepage ",
						"Selected links to be removed from Favourite Portlet = " + DeletedLinks.toString(),
						"Favourite should get removed and should no more display on 'Favourite' portlet on homepage, quick favourite modal window page",
						"Links successfully removed from Favorite portlet homepage", "PASS");

			} else if (QuickLinks.contains(DeletedLinks) && !DeletedLinks.isEmpty()){
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link On Favorites portlet on homepage ",
						"Selected links are not being removed: " + DeletedLinks.toString(),
						"Favourite should be removed and should no more display on 'Favourite' portlet on homepage, quick favourite modal window page",
						"Links successfully removed from Favorite portlet homepage", "FAIL");
			}
			else if(DeletedLinks.isEmpty()){
				ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link On Favorites portlet on homepage ",
						"Favourite links are not present " + DeletedLinks.toString(),
						"Favourite should be removed and should no more display on 'Favourite' portlet on homepage, quick favourite modal window page",
						"No sufficient favourite links found to execute this test case", "FAIL");
			}
			GenericFactory.navigateToHomePage();
			WaitFactory.waitForPageLoaded();

		} catch (Exception e) {
			String er = e.getMessage().substring(0, 125).toString().trim();
			System.out.println("test");
			LogFactory.info(er);
			flag = "Fail";

			ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Quick Window Modal ",
					DeletedLinks.toString(),
					"Favourite should get removed and should no more display on 'Favourite' portlet on homepage,quick favourite modal window page",
					er, flag);
		}

	}

	/**
	 * @author Shubham Raj This case describes Verify country filtering for employee
	 *         users on Links portlet and favorites portlet on Homepage & quick
	 *         favorites modal window and all types of index pages.
	 * 
	 * 
	 */
	public static void verifyFavouritesOnchangingCountryGroupingType(String TCID, String DepartmentName,
			String Titlelink, String wCMcountryGrouplist) throws Throwable {
		String flag = "Fail";
		String result = "Favourite on homepage is not working properly with Filtering  for diffrent Country Grouping ";

		ArrayList<String> FavouriteCreatedLinkHomepageBeforeFilter = new ArrayList<String>();
		ArrayList<String> FavouriteLinkHomepageAfterFilter = new ArrayList<String>();
		ArrayList<String> FavouriteLinkQuickmodalAfterFilter = new ArrayList<String>();

		List<String> listOfWcmCountry = GenericFactory.splitString(wCMcountryGrouplist, ",");

		List<WebElement> countryGroupingCheckboxList = null;
		int countryGroupingCount = 0;

		try {

			if (countryGroupingIcon.size() > 0) {
				WebElement weebobj = BaseClass.wbDriver
						.findElement(By.xpath(".//*[@class='search-icons countryGrouping']"));
				weebobj.click();
				countryGroupingCheckboxList = BaseClass.wbDriver
						.findElements(By.xpath(".//*[@id='country-grouping-form']/div/div/div/label/input"));
				countryGroupingCount = countryGroupingCheckboxList.size();
				weebobj.click();
				if (countryGroupingCount > 1) {

					GenericFactory.checkAllCountryGrouping();
					// BrowserFactory.RefreshBrowser();
					// creating Favourite
					String CreatefavonDepartmnet = toMarkLinkFavourite(DepartmentName, Titlelink);
					if (CreatefavonDepartmnet.equalsIgnoreCase(Titlelink)) {
						LogFactory.info("Created favourite link " + CreatefavonDepartmnet);
						GenericFactory.navigateToHomePage();
						// BrowserFactory.RefreshBrowser();
						/*
						 * WaitFactory.waitforelementToBeClickable(homepagepath) .click();
						 * WaitFactory.waitForPageLoaded();
						 */
						FavouriteCreatedLinkHomepageBeforeFilter = ToGetMarkedFavouriteslink_HomePage();

						GenericFactory.uncheckCountryGrouping(listOfWcmCountry);
						// GenericFactory.navigateToHomePage();
						BrowserFactory.RefreshBrowser();

						FavouriteLinkHomepageAfterFilter = ToGetMarkedFavouriteslink_HomePage();
						FavouriteLinkQuickmodalAfterFilter = ToGetMarkedFavouriteslinkQuickModal();

						if (!(FavouriteCreatedLinkHomepageBeforeFilter.equals(FavouriteLinkHomepageAfterFilter))
								&& (!FavouriteCreatedLinkHomepageBeforeFilter
										.equals(FavouriteLinkQuickmodalAfterFilter))) {
							flag = "Pass";
							result = "Favourite on homepage is working properly with filtering  for diffrent Country Grouping ";
							LogFactory.info(result);
						} else {
							flag = "Fail";
							result = "Favourite on homepage is not working properly with filtering  for diffrent Country Grouping ";
							LogFactory.info(result);
						}
					} else {
						flag = "Pass";
						result = "Given WCM Test Link is not present on UI So Can not do further validation ";
						LogFactory.info(result);
					}
				} else {
					flag = "Pass";
					result = "Showing ony one country grouping under CG option so can not abale to do further validation";
					LogFactory.info(result);
				}

			} else {
				flag = "Pass";
				result = "Country Grouping on favourite portlet for this user is not applicable ";
				LogFactory.info(result);
			}

			// Report
			ReportFactory.reporterOutput(TCID,
					"Verify country filtering for employee users on favorites portlet on Homepage and quick favorites modal window ",
					wCMcountryGrouplist.toString(),
					"After country filtering for employee users, the favourite link for uncheck country should not be display on favorites portlet on Homepage and quick favorites modal window The particular country should be checked or unchecked in Country Filtering modal window ",
					result, flag);

		} catch (Exception e) {
			flag = "Fail";

			String er = e.getMessage().substring(0, 160).toString().trim();
			LogFactory.info(er);

			ReportFactory.reporterOutput(TCID,
					"Verify country filtering for employee users on favorites portlet on Homepage and quick favorites modal window ",
					wCMcountryGrouplist.toString(),
					"After country filtering for employee users, the favourite link for uncheck country should not be display on favorites portlet on Homepage and quick favorites modal window The particular country should be checked or unchecked in Country Filtering modal window ",
					result, flag);

			GenericFactory.navigateToHomePage();
		}

	}

	// *************************************************** E - N - D
	// ************************************************************

	/**
	 * @author Shubham Raj
	 * @Functionality To verify the presence of Favorites header on homepage.
	 * @throws Throwable
	 */
	public static void verifyFavoritesHeaderPresent(String strTCID, String strExpectedHeaderMsg) throws Throwable {
		String strFlag = "Fail";
		try {
			WaitFactory.waitForPageLoaded();
			String strActualHeaderMsg = ""; // Aniket
			String strResult = "Favorites portlet with header text " + strExpectedHeaderMsg
					+ " is NOT displayed on the homepage in the preferred language of the dealer..";

			if (ValidationFactory.isElementPresent(wbelHeaderTitleFav)) {
				strActualHeaderMsg = wbelHeaderTitleFav.getText().toString();
				//System.out.println("Actual String ---> " +strActualHeaderMsg.trim() + "   " +strActualHeaderMsg.trim().length());
				//System.out.println("Expected String ---> " +strExpectedHeaderMsg.trim() + "   " +strExpectedHeaderMsg.trim().length());
				
				if (strActualHeaderMsg.equals(strExpectedHeaderMsg)) {
					strFlag = "Pass";
					strResult = "Favorites portlet with header text " + strActualHeaderMsg + " is displayed.";
					LogFactory.info("Favorites portlet with header is displayed.");
				} else if (!strActualHeaderMsg.equalsIgnoreCase(strExpectedHeaderMsg)) {
					strFlag = "Fail";
					strResult = "Favorites portlet with header text " + strActualHeaderMsg
							+ " is displayed but NOT matching with input header : " + strExpectedHeaderMsg;
				}
			}
			ReportFactory.reporterOutput(strTCID, "Verify Favorites portlet with header text is displayed on homepage.",
					strExpectedHeaderMsg, "Favorites portlet header should be " + strExpectedHeaderMsg, strResult,
					strFlag);
		} catch (Exception e) {
			ReportFactory.reporterOutput("Error_Favorites portlet", "Favorites portlet with header",
					"Favorites portlet with header", "NA", e.getMessage().toString(), strFlag);
		}
	}

	/**
	 * @author Shubham Raj
	 * @Functionality To verify No Favorites added yet message on homepage.
	 * @throws Throwable
	 *             exception.
	 */
	public static void verifyWhenNoFavouritePresent(String strTCID, String strExpectedNoFavMsg) throws Throwable {
		String strFlag = "Fail";
		try {
			WaitFactory.waitForPageLoaded();
			String strActualNofavmsg = ""; // Aniket
			String strResult = "Message 'No Favourites added yet' is not displayed.";
			if (ValidationFactory.isElementPresent(wbelNofavaddedFav)) {
				strActualNofavmsg = wbelNofavaddedFav.getText().toString();
				if (strActualNofavmsg.equalsIgnoreCase(strExpectedNoFavMsg)) {
					strFlag = "Pass";
					strResult = "Message 'No Favourites added yet' is displayed.";
					LogFactory.info("Verify 'No Favourites added yet.' message is displayed.");
				} else if (!strActualNofavmsg.equalsIgnoreCase(strExpectedNoFavMsg)) {
					strFlag = "PASS";
					strResult = "Currently Favourites/Folders are available so message 'No Favourites added yet' is not displayed";
					LogFactory.info("Verify Favourite Folders are created.");
				}
				// else {
				ReportFactory.reporterOutput(strTCID,
						"Verify message \"No Favourites added yet\" should be displayed when no favourites/folder are created ",
						strExpectedNoFavMsg,
						"Message " + strExpectedNoFavMsg + " should be displayed when no favourites/folder are created",
						strResult, strFlag);
				// }
			}

		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID,
					"Verify 'No Favourites added yet' message on home page when No Favourites added.", "NA", "NA",
					e.getMessage(), "Fail");
		}
	}

	/**
	 * @author Shubham Raj
	 * @Functionality : To verify favorite icon with message on homepage.
	 * @throws Throwable
	 */

	@SuppressWarnings("unused")

	public static void verifyFavouriteIconWhenNoFavouriteAdded(String TCID, String strExpectedIcon) throws Throwable {

		String flag = "Fail";
		try {
			String result = "Star icon with Message is not displayed.";
			if (lstActualFoldernames.size() > 0) {

				LogFactory.info("Verify Favourite Folders are exist in the favourites portlet.");
				for (int j = 0; j < lstActualFoldernames.size(); j++) {
					flag = "PASS";
					result = "Currently,Favourites are available so star icon with message is not showing.";
					break;
				}
			} else if (ValidationFactory.isElementPresent(wbelMessageFav)) {
				String strActualIcon = wbelMessageFav.getText().toString();

				if (strActualIcon.trim().equalsIgnoreCase(strExpectedIcon.trim())) {
					LogFactory.info("Verify Star icon with Message is displayed.");
					flag = "Pass";
					result = "Star icon with Message is displayed as expected..";
				}
			}
			ReportFactory.reporterOutput(TCID,
					"Verify when no favourites added, \"Click on the 'star' next to any DealerPath link to add to your favorites list.\" should be displayed in the preferred language",
					strExpectedIcon,
					"Star icon with Message " + strExpectedIcon + "should be displayed when no favourites are added",
					result, flag);
		} catch (Exception e) {

			ReportFactory.reporterOutput(TCID, "Favorites Icon with Message.", "NA", "NA",
					e.getMessage().toString().substring(0, 125), flag);
		}

	}

	/**
	 * @author Shubham Raj This case describe that the marked Favourite link from
	 *         the department should appear on Quick Modal window
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet @
	 * exception Exception e
	 */

	public static void favouriteQuickLinkOnHomePage(String strTCID) throws Throwable {
		String strFlag = "FAIL";
		String strResult = "Favourite Quick link Icon is not displayed.";
		try {
			WebElement ele = WaitFactory.waitforelementToBeClickable(wbelFavourSearch);
			if (ValidationFactory.isElementPresent(ele)) {
				LogFactory.info("Verify Favourite Quick link Icon is displayed.");
				strFlag = "PASS";
				strResult = "Favourite Quick link Icon is displayed.";
			}
			ReportFactory.reporterOutput(strTCID, "Verify Favourite Quick link Icon is displayed.", "Quick Link Icon",
					"Quick Link Icon should be displayed", strResult, strFlag);
		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID, "Verify Favourite Quick link Icon is displayed.",
					"Favorites quick link icon.", "NA", e.getMessage().toString(), strFlag);
		}
	}

	/**
	 * @author Shubham Raj This case describe that whether the header of the Quick
	 *         link window should be displayed according to the language selected by
	 *         the user
	 * @param param1
	 *            Describe the test case ID to be executed from the excel sheet
	 * @param param1
	 *            Describe the expected header name to be appear on the Quick modal
	 *            window @ exception Exception e
	 */

	public static void homePageQuickLinkContent(String strTCID, String strExpectedHeaderName) throws Throwable {
		try {
			String strFlag = "FAIL";
			String strResult = "Favorites Quick Link header is NOT showing as per dealer preferred language.";
			WaitFactory.waitForPageLoaded();
	
			WebElement ele = WaitFactory.waitForElementClickable(wbelFavourSearch);

			if (ValidationFactory.isElementPresent(ele)) {
				
//				WebElement ele = WaitFactory.waitForElementClickable(wbelFavourSearch);
				
			//	if (ValidationFactory.isElementPresent(ele)) {
					
					
					wbelFavourSearch.click();
					String strActualIconHeaderName = wbelQuickLinkHeader.getText().toString();

					if (strActualIconHeaderName.equals(strExpectedHeaderName)) {
						LogFactory.info("Verify Favourite Quick link Icon with content is displayed.");
						strFlag = "PASS";
						strResult = "Favourite Quick link Icon with contents are displayed.";
					} else {
						strFlag = "Fail";
						strResult = "Favorites Quick Link header :" + strActualIconHeaderName
								+ " is present but expected Quick Link header : " + strExpectedHeaderName
								+ " is not showing on the Favorites Quick Link";

					}

//				}
			}
			else
			{
				strFlag = "Fail";
				strResult = "Favorites Quick Link is either not clickable/disabled/not presnt while executing this  testcase";

			}
			ReportFactory.reporterOutput(strTCID,
					"Verify Favourites Quick Link header should be displayed in the preferred language of the dealer.",
					strExpectedHeaderName, "Quick Link Icon with content is displayed", strResult, strFlag);

		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID, "Favorites quick link icon.", "Favorites quick link icon.", "NA",
					e.getMessage().toString(), "Fail");
		}

	}

}
