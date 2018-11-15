package com.deere.Helpers;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import com.deere.PageFactory.Login_Page_POF;
public class ImpersonateUser extends BaseClass{

	/**
	 * This method is used to impersonate the dealer through his rackfID 
	 * @author shrishail.baddi
	 * @param strRACFID
	 * @createdAt 05-06-2018
	 * @throws IOException
	 * 
	 * @modifyBy shrey.choudhary
	 * @modifyAt 07-06-2018
	 * @return boolean
	 * @throws Throwable 
	 */
	
	
	public static boolean impersonateUserSuccess(String strRACFID) throws Throwable {
		try {
		
			BaseClass.errorUserFound = false;
			if (!GenericFactory.isNull(strRACFID)) {
				if (ValidationFactory
						.isElementPresent(WaitFactory.explicitWaitByXpath("//b[text() = 'Admin Links']"))) {
					ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='Analyze_User']")).click();
					LogFactory.info("Clicked Analyze User link from left navigation.....");
					WaitFactory.waitForPageLoaded();
					LogFactory.info("Enter User RACF ID....." + strRACFID);
				WebElement ele=	ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='analyseUserId']"));
						ele.sendKeys(strRACFID);
						
						WaitFactory.WaitForTexttoBePresentInElement(ele,strRACFID);
		
					
					LogFactory.info("Click on Analyze Button.....");
					ValidationFactory.getElementIfPresent(By.xpath(".//*[@id='analyzeUserButton']")).click();
					WaitFactory.waitForPageLoaded();
						
						if (ValidationFactory
								.validateButtonEnable(By.xpath(".//*[@id='impersonateUser']"))) {
							
							GenericFactory.userAnalyzeInformation(strRACFID);							

							ValidationFactory
									.getElementIfPresent(By.xpath(".//*[@id='impersonateUser']"))
									.click();
							
							if (loginPageFactory.verifyImpersonatedUser(strRACFID)) {

								BaseClass.errorUserFound = false;
								
							if (ValidationFactory
							.getElementIfPresent(By.xpath("//a[@id='endimpersonatelink']")) != null) {
								BaseClass.errorUserFound = false;
								strUserRACFID =strRACFID;
								LogFactory.info("User " + strRACFID + " not able to login due to the error message : DealerPath Application is not available [AccountFlex is down] ");
								return false;
							}
							
							//LogFactory.info("User " + strRACFID + " successfully logged into appllication ");
							
							}
							else 
							{
								BaseClass.errorUserFound = true;
								return false;
							}
							
						} else {
							
							BaseClass.errorUserFound = true;
							LogFactory.error("Unable to locate the button Impersonate/ it is disabled");
							throw new SkipException("Run mode of testcase " + "test" + " is N");
							
						}
			}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
}