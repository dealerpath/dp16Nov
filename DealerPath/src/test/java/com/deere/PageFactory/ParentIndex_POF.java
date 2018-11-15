package com.deere.PageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;

public class ParentIndex_POF extends BaseClass {
	public static WebDriver IndexDriver;
	public ParentIndex_POF(WebDriver driver) {
		this.IndexDriver = driver;
	}

	@FindBy(how = How.ID, using = "preference-cancel")
	static WebElement wbelMyPreferenceModalCancelButton;

	
	@FindBy(how = How.XPATH, using = "//*[@type='radio']")
	static List<WebElement> wbelMyPreferenceModalTheme;
	
	@FindBy(how = How.XPATH, using = "//*[@class='section-title']")
	static WebElement wbelHomeFav;
	
	@FindBy(how = How.XPATH, using = "//span[@class='primary-caret spr']")
	static WebElement wbelMyPreferenceUtilityMenu;
	
	@FindBy(how = How.XPATH, using = "//a[@id='preferences']")
	static WebElement wbelMyPreference;
	
	@FindBy(how = How.ID, using = "left_nav_0")
	static WebElement wbelLeftHeader;
	
	@FindBy(how = How.XPATH, using = "//div[@id='js-notifications']")
	 static WebElement wbNotificationLink;

	 @FindBy(how = How.ID, using = ".//*[@class='list-item']/div")
	 static List<WebElement> wbNotificationList;
	
	public static void themePreferenceOnIndexLevel(String strTestData) throws Throwable {
		String strFlag = "Fail";
		String result = "Theme should exist in My Preference Modal Window from index page ";
		try {
			navigateToMyPreferences();
			// My Preference will be open with control on it
			for (WebElement ele : wbelMyPreferenceModalTheme) {
				String value = ele.getAttribute("Value");
				if ((value.equalsIgnoreCase("Green & Yellow") || value.equalsIgnoreCase("Black & Yellow"))
						&& wbelMyPreferenceModalTheme.size() == 2){
					strFlag = "Pass";
					result = "Theme exists in My Preference Modal Window ";
				} else {
					strFlag = "Fail";
					result = "Theme does NOT exists in My Preference Modal Window ";
					
				}

			}
			ReportFactory.reporterOutput("TC03_PortalPreferences : Theme",
					"Verify that theme exists in My Preference Modal Window ", "NA ",
					"Verify that theme exists in My Preference Modal Window ", result, strFlag);
			navigateToHomepage();
		} catch (Exception e) {

			ReportFactory.reporterOutput("TC03_PortalPreferences : Theme", "Verify Themes are getting displayed ",
					"Verify Themes are getting NOT displayed ", "NA", e.getMessage().toString(), strFlag);
		}
	}
	
	
	
	public static void navigateToHomepage() throws Exception {
		WaitFactory.implicitWaitInSeconds(5);
		Thread.sleep(500);
		wbelMyPreferenceModalCancelButton.click();

		IndexDriver.switchTo().defaultContent();
		WaitFactory.waitForElement(wbelHomeFav);
	}

	public static void navigateToMyPreferences() throws Exception {

		WaitFactory.waitForElement(wbelMyPreferenceUtilityMenu);
		wbelMyPreferenceUtilityMenu.click();
		Thread.sleep(500);
		WaitFactory.waitForElement(wbelMyPreference);
		wbelMyPreference.click();
		Thread.sleep(500);
		IndexDriver.switchTo().activeElement();
		WaitFactory.waitForPageLoaded();

	}
	
	public static void navigateToHOME() throws Exception {
		WaitFactory.waitForElement(wbelLeftHeader);
		wbelLeftHeader.sendKeys(Keys.ENTER);
		
	}
	
	public static void verifyNotificationIconOnIndexPage(String TCID) throws Throwable {
		  String flag = "Fail";
		  String result = "Notification link is not applicable/available";

		  try {
		   // if (ValidationFactory.isElementPresent(notificationLink))
		   
		   WebElement ele= WaitFactory.waitForElementClickable(wbNotificationLink);
		   Thread.sleep(5000);
		   if (ValidationFactory.isElementPresent(ele)) {
		    result = "Notification link is available";
		    flag = "Pass";
		   }

		   ReportFactory.reporterOutput(TCID, "Verify notification icon on homepage.", "NA",
		     "Verify notification link on homepage.", result, flag);

		  } catch (Exception e) {
		   ReportFactory.reporterOutput(TCID, "Verify notification icon on homepage.", "NA",
		     "Verify notification link on homepage.", e.getMessage().toString().substring(0, 125), flag);
		  }
		 }

		 /**
		  * This method compares Notification list with the given input through Excel
		  */
	
	
		 public static void verifyNotificationsList(String strNotification, String TCID) throws Throwable {

		  String flag = "Fail";
		  try {
		   // if (ValidationFactory.isElementPresent(notificationLink))
	 	   if (wbNotificationLink.isDisplayed()) {
		    wbNotificationLink.click();
		    Thread.sleep(3000);
		    List<String> ExpectedData = GenericFactory.splitString(strNotification, ",");
		    if (!strNotification.equalsIgnoreCase("NA")) {
		     List<WebElement> wbNotification = BaseClass.wbDriver
		       .findElements(By.xpath(".//*[@class='list-item']/div"));
		     List<String> notificationList = new ArrayList<String>();
		     for (int i = 0; i < wbNotification.size(); i++) {
		      String temp = wbNotification.get(i).getText();
		      notificationList.add(temp);
		     }
		     System.out.println(notificationList);
		     if (notificationList.equals(ExpectedData)) {
		      flag = "Pass";
		     }
		     ReportFactory.reporterOutput(TCID, "Verify notification link names on homepage.",
		       "Input data is : " + ExpectedData, "Notification list should match with Input data",
		       "Actual data : " + notificationList, flag);
		    }

		   } else {
		    ReportFactory.reporterOutput(TCID, "Verify notification link names on homepage.", "Notification List",
		      "Notification list should match with Input data", "Notification List is not present.", flag);
		   }
		  }

		  catch (Exception e) {
		   ReportFactory.reporterOutput(TCID, "Verify notification link names on homepage.", "NA",
		     "Verify notification link names on homepage.", e.getMessage().toString().substring(0, 125), flag);
		  }
		 }

}
