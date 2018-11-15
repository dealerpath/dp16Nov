package com.deere.PageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;
import com.deere.Helpers.WaitFactory;

public class Search_POF {
	static WebDriver driver;

	public Search_POF(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(how = How.ID, using = "inputFormText")
	static WebElement wbelSearchBox;

	@FindBy(how = How.CLASS_NAME, using = "search-button")
	static WebElement wbelSearchBoxButton;

	@FindBy(how = How.XPATH, using = "//div[@class='column-facet-options']/div/div/label/div")
	static List<WebElement> listProductType;

	@FindBy(how = How.XPATH, using = "//div[@class='result-footer']/div/div[3]")
	static List<WebElement> listResultFooter;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'search-apply-filters')]/parent::div")
	 static WebElement wbelApplyFilters;

	 @FindBy(how = How.XPATH, using = "//ul[@class='pagination']")
	 static WebElement wbelPagination;
	 
	 @FindBy(how = How.CLASS_NAME, using = "next")
	 static WebElement wbelNextLink;
	
	 @FindBy(how = How.XPATH, using = "//div[@class='result-list']//div[1]//div[1]//a[1]")
	 static WebElement wbelFirstTitle;
	 
	 @FindBy(how = How.XPATH, using = "//h3[@class='section-title']")
	 static WebElement wbelResults;
		
	
	 
	/**
	 * @param strExpctSearchText
	 * @param TCID
	 * @throws Throwable
	 *             Verify text is entered in search box
	 */
	public void enterSearchText(String strExpctSearchText, String strTCID) throws Throwable {
		String strFlag = "Fail";
		String strResult = "text is not entered in search box";
		try {
			WaitFactory.WaitForElementToVisible(wbelSearchBox);
			if (wbelSearchBox.isEnabled()) {
				wbelSearchBox.sendKeys(strExpctSearchText);
				WaitFactory.WaitForTexttoBePresentInElement(wbelSearchBox,strExpctSearchText);
				strFlag="Pass";
				LogFactory.info("text is entered in search box");
				clickOnSearchButton(strTCID,strExpctSearchText);
			}
			else
			{	ReportFactory.reporterOutput(strTCID, "Enter the search text", strExpctSearchText,
					"Verify text is entered in search box", strResult, strFlag); }

		} catch (Exception e) {
			ReportFactory.reporterOutput(strTCID, "Enter the search text", strExpctSearchText,
					" Verify text is entered in search box", e.getMessage().toString(), strFlag);
		}
	}

	/**
	 * @param TCID
	 * @throws Throwable
	 *             Verify clicked on search button
	 */
	public void clickOnSearchButton(String strTCID, String strExpctSearchText) throws Throwable {
		String strFlag = "Fail";

		String strResult = "search box button is Noy clicked";
		try {
			WaitFactory.waitForElementClickable(wbelSearchBoxButton);
			if (wbelSearchBoxButton.isEnabled()) {
				wbelSearchBoxButton.click();
				strFlag = "Pass";
				LogFactory.info("search box is clicked");
				getResultTitleRowValues(strExpctSearchText, strTCID);
			}
			else {
					ReportFactory.reporterOutput(strTCID, "click on search button", "NA", "Verify clicked on search button",
					strResult, strFlag);
			}
		
		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID, "Click on search button", "NA", "Verify clicked on search button",
					e.getMessage().toString(), "Fail");
		}
	}



	public void getResultTitleRowValues(String strExpctSearchText, String strTCID) throws Throwable {

		String strFlag = "Fail";
		String strResult="";
		try {
			if (ValidationFactory.isElementPresent(wbelResults) ) {  
				
			
				if (ValidationFactory.isElementPresent(wbelFirstTitle) ) { 
					
				String strfirstTitleContent = WaitFactory.WaitForElementToVisible(wbelFirstTitle).getText();
				if (strfirstTitleContent.contains(strExpctSearchText)){
					LogFactory.info("Search Text is : " + strExpctSearchText + " : First title in the result is : " + strfirstTitleContent);
					strFlag = "Pass";
					strResult = "Seracrh text : "+ strExpctSearchText + " : found in the first title : "+ strfirstTitleContent + " : of the result";
				}
				else{
						strFlag = "Fail";
						strResult = "Seracrh text : "+ strExpctSearchText + " : NOT found in the first title of the result";
					}	
				}
				else
				{
					strFlag = "Fail";
					strResult = "Somthing went wrong or  results page not displayed for the seacrh text : "+ strExpctSearchText;	
				}
			}
			else {
					strFlag = "Pass";
					strResult = "Somthing went wrong or no results found for the seacrh text : "+ strExpctSearchText;
			}
		
			ReportFactory.reporterOutput(strTCID, "Verify the search text is present in the title of the first page", "Search text : "+ strExpctSearchText,
					"Search text should be prsent in the first title of the result", strResult, strFlag);

		} catch (Exception e) {

			ReportFactory.reporterOutput(strTCID, "Verify the search text is present in the title of the first page", "Search text : "+ strExpctSearchText,
					"Search text should be prsent in the first title of the result", "Somthing went wrong or no results found for the seacrh text : "+ e.getMessage().toString(), "Fail");
		}
	}
	
	
	
}
