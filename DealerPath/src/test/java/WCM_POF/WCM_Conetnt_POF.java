package WCM_TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.BrowserFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.WCMInput;
import com.deere.PageFactory.Login_Page_POF;

import WCM_POF.Alert_WCM_POF;
import WCM_POF.WCM_Conetnt_POF;


public class WCM_TestCase extends BaseClass {
	
	static String allLibraries;
	static String allDepartments;
	static List<String> libraryDepartment=new ArrayList<String>();
	static String excelValues;
	static String count;
	static String date;
	static String lingual;
	static String subDepartmentsToRead;
	static String includeAll;
	static String regionIs;
	
	WebDriver driver;
	/**
	 * This method is the first step of WCM suite which reads input from excel sheet, sets user
	 * credentials, initiate drivers and page elements and creates WCM excel
	 * 
	 * @author Yogender.Singh
	 * @createdAt 07-06-2018
	 * @throws IOException
	 * @throws Exception
	 * @modifyBy yogender.singh 
	 */
	@BeforeClass
	public void systemConfigSetup() throws IOException, Exception {
		try {

			WCMInput.readWCMContentData();
			wcmInputdata=WCMInput.WCMInputValues;
			commonInputValues=WCMInput.InputValues;
			
			URL=commonInputValues.get("URL");
			strBrowserType=commonInputValues.get("Browser");
			strUserName=commonInputValues.get("Username");
			strPassword=commonInputValues.get("Password");
			wcmDataOutputPath=strWorkingDir + "\\WCMContent\\" + commonInputValues.get("FileName");
		
			
			WCM_Conetnt_POF.createWCMExcel();
			BrowserFactory.initiateDriver();
			initPageElements();
				
			} catch (Exception e) {
				LogFactory.info(e.getMessage());
			} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to invoke admin's login credentials then go to WCM
	 * and fetch library specific Alerts, Announcements and other WCM content
	 * 
	 * @author Yogender.Singh
	 * @createdAt 05-08-2018
	 * @throws Throwable
	 */
	@Test(priority=0)
	public static void invokeUserCredentials() throws Throwable {
		try
		{
		System.out.println("Verify Valid Login");
		if(!URL.equals("https://dealerpathqualauth.tal.deere.com"))
		{
			Login_Page_POF.setCredentialsNewVersion(strUserName, strPassword);
		}	
		else
		{
		loginPageFactory.setCredentials(strUserName, strPassword);
		}

		if (Login_Page_POF.verifyUserLogin()) 
		{	
			
			BaseClass.setGlobalCountries();
			BaseClass.setGlobalProductTypes();
		
			loginPageFactory.navigateToWCM();
			
			Set<String> keys = wcmInputdata.keySet();
			for(String key: keys)
		 	{
			
				regionIs=String.valueOf(wcmInputdata.get(key).get("Region"));
				
			allLibraries=String.valueOf(wcmInputdata.get(key).get("Library"));
			allDepartments=String.valueOf(wcmInputdata.get(key).get("Departments"));
			count=String.valueOf(wcmInputdata.get(key).get("Number Of Rows to fetch"));
			
			date=String.valueOf(wcmInputdata.get(key).get("Last Saved Date"));
			lingual=String.valueOf(wcmInputdata.get(key).get("Multilingual"));
			subDepartmentsToRead=String.valueOf(wcmInputdata.get(key).get("SubDepartments"));
			includeAll=String.valueOf(wcmInputdata.get(key).get("IncludeAll"));
			
			libraryDepartment.add(allLibraries+","+allDepartments+","+count+","+date+","+lingual+","+subDepartmentsToRead+","+includeAll+","+regionIs);
			
		System.out.println(libraryDepartment);
			}
			
			for(int i=0;i<libraryDepartment.size();i++)
			{
				excelValues=libraryDepartment.get(i);
					
			String arr[]=excelValues.split(",");
			alertRegion=arr[0]; 
			department=arr[1];
			
			if(!arr[2].equals("NA"))
			{
				totalCount=Integer.valueOf((String)arr[2]);
			}
			
			publishedDate=arr[3];
			multiLingual=arr[4];
			
			if(!arr[5].equals("NA"))
			{
				subDepartmentNumbers=Integer.valueOf((String)arr[5]);
			}
			includeAllContent=arr[6]; 
			
			Region=arr[7];
			
			System.out.println("Library is::"+alertRegion+" Department is::"+department+" Published Date is::"+publishedDate+" Multilingual is::"+multiLingual+" SubDepartements to fetch::"+subDepartmentNumbers+" Region is:"+Region);
			
			Alert_WCM_POF.navigateToRegion(alertRegion);
			
			//WCM_Conetnt_POF.readWCMAlertsAnnouncementsContent();
			
			//BaseClass.wbDriver.findElement(By.xpath("//a[.='"+alertRegion+"']")).click();
			
			if(includeAllContent.equals("Y"))
			{
				Alert_WCM_POF.fetchDepartmentContents(department,totalCount);
			}
			
			else
			{
				WCM_Conetnt_POF.fetchDepartmentContents(department, totalCount);
				
			}
	
			BaseClass.wbDriver.findElement(By.xpath("//a[.='Libraries']")).click();
	        }
			
		}
	}
		
		catch(Exception e)
		
		{
			LogFactory.info("Login for"+BaseClass.strUserName+"Failed");
			System.out.println("Login for"+BaseClass.strUserName+"Failed");
		}
	}
	
	/**
	 * This method is used to close the browser window
	 * 
	 * @author Yogender.Singh
	 * @createdAt 05-08-2018
	 * @throws Throwable
	 */
	
	@AfterClass
	public void closeDriver() throws Throwable{
		
		try
		{
		BaseClass.wbDriver.quit();
		System.out.println("all windows closed sucessfully");
		}
		catch(Exception e)
		{
		System.out.println("error while closing the browser window");
		}
	}
}
