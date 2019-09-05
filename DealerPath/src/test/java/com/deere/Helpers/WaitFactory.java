package com.deere.Helpers;

import java.time.Duration;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;


public class WaitFactory extends BaseClass {
	
	//Aniket
		public static  void WaitForElementNotPresent(WebElement element) throws Exception
		{	WebElement elem = null;
			try {
				@SuppressWarnings("deprecation")
				WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
				wait.withTimeout(Duration.ofSeconds(30));
				wait.pollingEvery(Duration.ofMillis(500));
				wait.ignoring(NoSuchElementException.class);
				
					elem = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
			   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					 
				}
		 }
	
		public static WebElement  waitforelementToBeClickable(WebElement element )
		{
			WebElement elem = null;
		    try {
				WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
				wait.withTimeout(Duration.ofSeconds(30));
				wait.pollingEvery(Duration.ofMillis(500));
				wait.ignoring(NoSuchElementException.class);
				elem =   wait.until(ExpectedConditions.elementToBeClickable(element));
			
			} catch (Exception e) {
				
			}
		
			return elem;	
			
		}
	
	//******************************************************************	
	
	public static  List<WebElement> WaitForListofElementsToVisible(List<WebElement> elements) throws Exception
	{	List<WebElement>  elem = null;
		    try {
			
				WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
				wait.withTimeout(Duration.ofSeconds(30));
				wait.pollingEvery(Duration.ofMillis(500));
				wait.ignoring(NoSuchElementException.class);
				
				elem =  (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
		//		Assert.fail("Timeout waiting for Element Load Request to complete."); 
			}
		
			return elem;	 
	 }
	

	public static Boolean waitforelementToinvisibile(WebElement element)

	{
		Boolean Flag = false;

		try {
			WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			Flag = wait.until(ExpectedConditions.invisibilityOf(element));

		} catch (Exception e) {

		}

		return Flag;
	}
	
	
public static  WebElement WaitForElementToVisible(WebElement element) throws Exception
{	WebElement elem = null;
	    try {
		
	    	WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			elem = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return elem;	 
 }


public static  boolean WaitForTexttoBePresentInElement(WebElement element, String text) 
{	WebElement elem = null;
	    try {
		
	    	WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
		  return (wait.until(ExpectedConditions.textToBePresentInElement(element, text)));

		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	Assert.fail("Timeout waiting for Element Load Request to complete."); 
			return false;	
		}
	
	 
 }

	
	public static void waitForPageLoaded() { 

		ExpectedCondition<Boolean> expectation = new 
				ExpectedCondition<Boolean>() { 
			public Boolean apply(WebDriver driver) { 
				return ((JavascriptExecutor)wbDriver).executeScript("return document.readyState").equals("complete"); 
			} 
		}; 

		WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
		try { 
			wait.until(expectation); 
		} catch(Throwable error) { 
	//	Assert.fail("Timeout waiting for Page Load Request to complete."); 
		} 
	} 
	
	
	public static WebElement explicitWait(final String strLocator)  
	{  
		Wait<WebDriver> wait = new FluentWait<WebDriver>(wbDriver)  
			//	.withTimeout(20, TimeUnit.SECONDS)  
			//	.pollingEvery(5, TimeUnit.SECONDS)  
				.ignoring(NoSuchElementException.class); 

		WebElement element= wait.until(new Function<WebDriver, WebElement>() {  
			public WebElement apply(WebDriver driver) {  
				return wbDriver.findElement(By.id(strLocator));  
			}  
		});  
		return element;  
	}  

	public static WebElement explicitWaitByXpath(final String strLocator)  
	{  
		Wait<WebDriver> wait = new FluentWait<WebDriver>(wbDriver)  
			//	.withTimeout(20, TimeUnit.SECONDS)  
			//	.pollingEvery(5, TimeUnit.SECONDS)  
				.ignoring(NoSuchElementException.class); 

		WebElement element= wait.until(new Function<WebDriver, WebElement>() {  
			public WebElement apply(WebDriver driver) {  
				return wbDriver.findElement(By.xpath(strLocator));  
			}  
		});  
		return element;  
	}  
	
	public static void implicitWaitInSeconds(int intWaitTime ){
		wbDriver.manage().timeouts().implicitlyWait(intWaitTime, TimeUnit.SECONDS);
	}
	

	public static WebElement waitForElement(WebElement Wbel) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			
			return wait.until(ExpectedConditions.visibilityOf(Wbel));
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}
	
	public static List<WebElement> waitForElements(List<WebElement> elements){
		try {
			//WebDriverWait wait = new WebDriverWait(wbDriver, 25);
			WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			
			return wait.until(ExpectedConditions
					.visibilityOfAllElements(elements));
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

	
	public static Alert waitForAlertPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);			
			return wait.until(ExpectedConditions.alertIsPresent())
					
					;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}
	
	public static int getRowsFromTable(WebElement Wbel){
		List<WebElement> Rows = Wbel.findElements(By.tagName("tr"));
		return Rows.size();
	}
	
	public static void acceptAlertMessage(WebDriver driver){
		WaitFactory.waitForAlertPresent(wbDriver);
		driver.switchTo().alert().accept();
	}
	
	public static WebElement waitForElementClickable(WebElement Wbel) {
		try {
			//WebDriverWait wait = new WebDriverWait(wbDriver, 50);
			WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.elementToBeClickable(Wbel));
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

	
	public static boolean waitForTextIsPresentInElement(WebElement Wbel,
			WebDriver driver, String Strtext) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
			wait.withTimeout(Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			
			return wait.until(ExpectedConditions.textToBePresentInElementValue(
					Wbel, Strtext));
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

	
	
	public static WebElement FluentWaitByWebElement(final WebElement strLocator)  
	{  
		Wait<WebDriver> wait = new FluentWait<WebDriver>(BaseClass.wbDriver)  
			//	.withTimeout(60, TimeUnit.SECONDS)  
			//	.pollingEvery(2, TimeUnit.SECONDS)  
				.ignoring(NoSuchElementException.class); 

		WebElement element= wait.until(new Function<WebDriver, WebElement>() {  
			public WebElement apply(WebDriver driver) { 
//				System.out.println("Checking for the Element!!");
				if (strLocator != null) {
					System.out.println("Element is found");
				}
				return strLocator;  
			}  
		});  
		return element;  
	}  
	
	public static  boolean WaitForinvisibilityOfElement(WebElement countryApplyFilterButton) throws Exception
	{	boolean elem=false;
		    try {
			
				WebDriverWait wait = new WebDriverWait(BaseClass.wbDriver, 25, 2500L);
				wait.withTimeout(Duration.ofSeconds(30));
				wait.pollingEvery(Duration.ofMillis(500));
				wait.ignoring(NoSuchElementException.class);
				elem = wait.until(ExpectedConditions.invisibilityOf((WebElement) countryApplyFilterButton));
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//	Assert.fail("Timeout waiting for Element Load Request to complete."); 
			}
		
			return elem;	 
	 }
	

	}
	
