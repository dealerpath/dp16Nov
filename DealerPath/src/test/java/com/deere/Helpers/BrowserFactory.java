
/* 
 * Project    : DealerPath
 * Script     : BrowserFactory
 * Author     : Shrishail Baddi
 * Date       : April.02.2018
 * Last Modified On:
 * Modified By :
 */

package com.deere.Helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.deere.Helpers.BaseClass;

public class BrowserFactory {

	public static EventFiringWebDriver driver = null;
	private static String strChromeDriverPath = BaseClass.strWorkingDir
			+ "\\src\\test\\resources\\Drivers\\chromedriver.exe";
	private static String strIEDriverPath = BaseClass.strWorkingDir
			+ "\\src\\test\\resources\\Drivers\\IEDriverServer3.8.exe";
	private static String strGeckoDriverPath = BaseClass.strWorkingDir
			+ "\\src\\test\\resources\\Drivers\\geckodriver.exe";
	private static String strEdgeDriverPath = BaseClass.strWorkingDir
			+ "\\src\\test\\resources\\Drivers\\EDGEWebDriver16299.exe";
	private static String strBrowser = BaseClass.strBrowserType;

	/*
	 * This method is used to launch the browser - with supplied URL & Browser type
	 * 
	 * @exception Exception: If any exception is found
	 */

	@SuppressWarnings("deprecation")
	public static void initiateDriver() {

		try {

			if (!strBrowser.isEmpty() && !strBrowser.equals(null)) {

				// checking the type of browser
				if (strBrowser.equalsIgnoreCase("Chrome")) {

					System.setProperty("webdriver.chrome.driver", strChromeDriverPath);
					BaseClass.wbDriver = new ChromeDriver();

				} else if (strBrowser.equalsIgnoreCase("Firefox")) {

					System.setProperty("webdriver.gecko.driver", strGeckoDriverPath);
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					BaseClass.wbDriver = new FirefoxDriver(capabilities);

				} else if (strBrowser.equalsIgnoreCase("IE")) {

					System.setProperty("webdriver.ie.driver", strIEDriverPath);
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					capabilities.setCapability("requireWindowFocus", true);
					capabilities.setCapability("ignoreZoomSetting", true);
					BaseClass.wbDriver = new InternetExplorerDriver(capabilities);

				} else if (strBrowser.equalsIgnoreCase("EDGE")) {

					System.setProperty("webdriver.edge.driver", strEdgeDriverPath);
					BaseClass.wbDriver = new EdgeDriver();

				} else if (strBrowser.equalsIgnoreCase("HtmlUnit")) {

					/*
					 * DesiredCapabilities caps = new DesiredCapabilities();
					 * caps.setJavascriptEnabled(true); // not really needed: JS enabled by default
					 * caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					 * "C:/phantomjs.exe"); 
					 * WebDriver driver = new PhantomJSDriver(caps);
					 */
			}

				driver = new EventFiringWebDriver(BaseClass.wbDriver);
				// putting an implicit wait after every Action or Event
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				// opening the browser
				LogFactory.info("opening the browser....");
				driver.navigate().to(BaseClass.URL);
				BaseClass.endImpersonateURL = BaseClass.URL+"/wps/myportal/dpath/!ut/p/a1/04_SD9OPyvLKK_bOryzXNQ7Uj9SPMrQ0MLbQj8zJ1y_IcVQEAE4wkcg!/";
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();

			} else {

				LogFactory.info("Invalid Browser");
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void RefreshBrowser() {

		BaseClass.wbDriver.navigate().refresh();

	}

	// Method to close the driver

	public static void closeDriver() {
		if (BaseClass.wbDriver != null) {
			driver.close();
			BaseClass.wbDriver.close();

		}

	}

}