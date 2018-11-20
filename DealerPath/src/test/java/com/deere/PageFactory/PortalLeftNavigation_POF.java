package com.deere.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;

/* 
 * Project    : DealerPath
 * Script     : PortalLeftNavigation_POF 
 * Author     : Shrey choudhary
 * Date       : May.15.2018
 * Last Modified On: May.15.2018
 * Modified By : Shrey choudhary
 */
public class PortalLeftNavigation_POF extends BaseClass {

	final WebDriver locDriver;

	public PortalLeftNavigation_POF(WebDriver driver) {
		this.locDriver = driver;
	}

	@FindBy(how = How.ID, using = "leftNav")
	static WebElement wbelLeftWindowFrame;

	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='active']")
	public static List<WebElement> ListAllActiveLinks;

	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li/a[@class='inactive']")
	public static List<WebElement> listAllInactiveLinks;

	@FindBy(how = How.XPATH, using = ".//*[@class='link-group-container']/h5")
	public static List<WebElement> listAllCategories;

	@FindBy(how = How.XPATH, using = ".//*[@id='leftNav']/li")
	public static List<WebElement> listAllSubCategories;

	@FindBy(how = How.XPATH, using = ".//*[@class='section-header']/h3")
	public static List<WebElement> listAllHeaders;

	@FindBy(how = How.XPATH, using = ".//*[@id='linkIndexPageContainer']/h3")
	public static List<WebElement> listAllSubHeaders;

	/**
	 * This method verifies Left navigation window link names are in correct order
	 * 
	 * @author shrey.choudhary
	 * @createdAt 15-05-2018
	 * @param testData
	 * @param TCID
	 * @return
	 * @throws Throwable
	 * @modifiedAt 21-05-2018
	 */

	public static void compareNavigationLinksOnDepartment(String strTCID, String DepartmentName) throws Throwable {
		try {
			boolean listAllCategoriesBoolean = ValidationFactory.getElementIfPresent(listAllCategories.get(0));
			if (listAllCategoriesBoolean) {
				List<String> listOfLeftNavigation = new ArrayList<String>();
				for (int i = 3; i <= listAllSubCategories.size(); i++) {
					String temp = wbDriver.findElement(By.xpath(".//*[@id='leftNav']/li[" + i + "]")).getText()
							.toString().trim();
					listOfLeftNavigation.add(temp);
				}
				List<String> listOfCategories = new ArrayList<String>();
				for (int i = 0; i < listAllCategories.size(); i++) {
					String temp = listAllCategories.get(i).getText();
					listOfCategories.add(temp);
				}
				System.out.println("list of left navigation is : " + listOfLeftNavigation);
				System.out.println("list of categories is : " + listOfCategories);
				if (listOfLeftNavigation.equals(listOfCategories)) {

					ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links names ", "NA",
							"Left Navigation links and their order should match with categories/header",
							"<B>List of left navigation is : </B>" + listOfLeftNavigation
									+ "</br><B>List of Categories/headers is :</B>" + listOfCategories,
							"Pass");
				} else {
					ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links names ", "NA",
							"Left Navigation links and their order should match with categories/header",
							"<B>List of left navigation is : </B>" + listOfLeftNavigation
									+ "</br><B>List of Categories/headers is :</B>" + listOfCategories,
							"Fail");
				}
			} else {
				ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links names ", "NA",
						"Left Navigation links and their order should match with categories/header",
						"No sub categories are present on homepage.", "Fail");

			}
		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID, "Verify Portal Left Navigation Links name",
					"Verify Portal Left Navigation Links name", "NA", e.getMessage().toString(), "Fail");
		}
	}

	public static void verifyLeftNavigationWithCategories(String pageName) throws Throwable {
		String strFlag = "Fail";
		int counter = (pageName == "AT-ChildIndex Page") ? 4 : (pageName == "AT-GrandChildIndex Page") ? 5 : 3;
		try {
			boolean listAllCategoriesBoolean = ValidationFactory.getListElementIfPresent(listAllCategories);
			if (listAllCategoriesBoolean) {
				List<String> listOfLeftNavigation = new ArrayList<String>();
				boolean listAllSubCategoriesBoolean = ValidationFactory
						.getElementIfPresent(listAllSubCategories.get(0));
				if (listAllSubCategoriesBoolean) {
					for (int i = counter; i <= listAllSubCategories.size(); i++) {
						String temp = wbDriver.findElement(By.xpath(".//*[@id='leftNav']/li[" + i + "]")).getText()
								.toString().trim();
						listOfLeftNavigation.add(temp);
					}
				}
				List<String> listOfCategories = new ArrayList<String>();
				for (int i = 0; i < listAllCategories.size(); i++) {
					String temp = listAllCategories.get(i).getText();
					listOfCategories.add(temp);
				}
				System.out.println("list of left navigation is : " + listOfLeftNavigation);
				System.out.println("list of categories is : " + listOfCategories);
				if (listOfLeftNavigation.equals(listOfCategories)) {
					strFlag = "Pass";
				}
				ReportFactory.reporterOutput("Verify Left Navigation", "Verify Portal Left Navigation Links name",
						"Verify Portal Left Navigation Links name on " + pageName, "List of left navigation should be same as categories/header",
						"<B>List of left navigation is :</B> " + listOfLeftNavigation + "<P>"
								+ " <B>List of Categories/headers is :</B> " + listOfCategories,
						strFlag);

			} else {
				boolean listAllHeadersBoolean = ValidationFactory.getListElementIfPresent(listAllHeaders);
				if (listAllHeadersBoolean) {
					List<String> listOfLeftNavigation = new ArrayList<String>();
					boolean listAllSubCategoriesBoolean = ValidationFactory
							.getListElementIfPresent(listAllSubCategories);
					if (listAllSubCategoriesBoolean) {
						for (int i = counter; i <= listAllSubCategories.size(); i++) {
							String temp = wbDriver.findElement(By.xpath(".//*[@id='leftNav']/li[" + i + "]")).getText()
									.toString().trim();
							listOfLeftNavigation.add(temp);
						}
					}
					List<String> listOfCategories = new ArrayList<String>();
					for (int i = 0; i < listAllHeaders.size(); i++) {
						String temp = listAllHeaders.get(i).getText();
						listOfCategories.add(temp);
					}
					if (ValidationFactory.getListElementIfPresent(listAllHeaders)) {
						if (listAllSubHeaders.size() > 0) {
							for (int i = 0; i < listAllSubHeaders.size(); i++) {
								String temp = listAllSubHeaders.get(i).getText();
								listOfCategories.add(temp);
							}
						}
					}
					System.out.println("list of left navigation is : " + listOfLeftNavigation);
					System.out.println("list of categories is : " + listOfCategories);
					if (listOfLeftNavigation.equals(listOfCategories)) {
						strFlag = "Pass";
					}
					ReportFactory.reporterOutput("Verify Left Navigation", "Verify Portal Left Navigation Links name",
							"Verify Portal Left Navigation Links name on " + pageName, "List of left navigation should be same as categories/header",
							"<B>List of left navigation is :</B> " + listOfLeftNavigation + "<P>"
									+ " <B>List of Categories/headers is :</B> " + listOfCategories,
							strFlag);

				}

			}
		} catch (Exception e) {
			ReportFactory.reporterOutput("Verify Left Navigation", "Verify Portal Left Navigation Links name",
					"Verify Portal Left Navigation Links name on " + pageName, "List of left navigation should be same as categories/header", e.getMessage(), strFlag);

		}
	}

}
