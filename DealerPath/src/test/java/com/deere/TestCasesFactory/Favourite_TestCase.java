
package com.deere.TestCasesFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.Helpers.ValidationFactory;


/**
* This class contains verification of all the favourites marked by the user on HomePage, Quick modal
* and on link portlet  
 *
* @author      Shubham Raj
* @version     v1.0
*/

public class Favourite_TestCase extends BaseClass {

                static ArrayList<String> CreatedMarkedFavlinkname = new ArrayList<String>();
                static HashMap<String, List<String>> DeptandMarkedlink = new HashMap<String, List<String>>();
                static ArrayList<String> Deptnamelist = new ArrayList<String>();
                static String CreatedLinkonDeptName = "";
                static String strExpectedValue;
                static String strTCID;

                @BeforeClass
                public void getReportHeader() throws InterruptedException {
                                ReportFactory.tableEnd();
                                GenericFactory.createHeaderSection("Portal Favourite");
                }

                // Create link as Favourite on a Department page
                // Favorite should display on Quick Favorite modal window on home, Department
                
                /**
     * @author      Shubham Raj
     * This method is used to mark the favourite links on Link portlet
     * @ exception       Description of exception you are handling
     *  
     */

                @Test(priority = 1)
                public static void verifyToCreateFavourite() throws Throwable {

                                String strTCID = "TC01_Favourite";
                                CreatedMarkedFavlinkname = new ArrayList<String>();
                                DeptandMarkedlink = new HashMap<String, List<String>>();
                                Deptnamelist = new ArrayList<String>();
                                CreatedLinkonDeptName = "";
                                LogFactory.beginTestCase(strTCID
                                                                + " : Verify creating a favorite in default favorite folder for dealer, employee/authorized contingent. ");
                                                                favouritesFactory.createFavourite();
                                
                }

                
    /**
     * @author      Shubham Raj
     * This method will verify that on changing product type, favourite should be filtered accordingly
     * @ exception       Description of exception you are handling
     * @ throws          Description of exception that the method throws 
     */
/*
                @Test(priority = 2)
                public static void verifyToFavouriteOnChanginProductType() throws Throwable {
                                strTCID = "TC02_Favourite";
                                strExpectedValue = BaseClass.getExcelDataByTestCaseID(strTCID);
                                System.out.println("test");
                                if (!strExpectedValue.equalsIgnoreCase("None")) {
                                                LogFactory.beginTestCase(strTCID + " : Verify favourite on changing the Product Type");
                                                try {
                                                                List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Document","AT-Rich Text","");
                                                                for (int i = 0; i < userWCMContent.size(); i++) {
                                                                                String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                                                                String Title = (String) userWCMContent.get(i).get("Title").toString().trim();
                                                                                String userProductType = (String) userWCMContent.get(i).get("Dealer_ProductType").toString().trim();
                                                                                String wcmProductType = (String) userWCMContent.get(i).get("ProductType").toString().trim();
                                                                                String wcmDealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString()
                                                                                                                .trim();
                                                                                String departmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                                                                favouritesFactory.VerifyFavouritesOnchangingProductType(wcmTestCaseID, departmentName, Title,
                                                                                                                wcmProductType, userProductType);
                                                                }
                                                } catch (Exception e) {
                                                                // TODO Auto-generated catch block
                                                                e.printStackTrace();
                                                }
                                                LogFactory.endTestCase(" ");
                                }
                }
                
                
                *//**
     * @author                     Shubham Raj
     * Verify Favourite for non-preferred deaprtment
     
     */

              /*  @Test(priority = 3)
                public static void VerifyToFavouriteOnNonPrefferedDepartment() throws Throwable {
                                strTCID = "TC03_Favourite";
                                List<String> listOfTitle = new ArrayList<String>();
                                LogFactory.beginTestCase(strTCID + " : Verify favourite on changing the prefferd department");

                                for (int i = 0; i < BaseClass.markedLinks.size(); i++) {
                                                LinkedHashMap userWCMContent = ExcelFactory.getWCMByTCID(BaseClass.markedLinks.get(i));
                                                String title = "";
                                                title = (String) userWCMContent.get("Title").toString().trim();
                                                listOfTitle.add(title);
                                }
                                List Depname = new ArrayList(departmentNames);
                                favouritesFactory.VerifyFavouriteForNonPreffredDept(strTCID, Depname, listOfTitle);
                                
                }
                */
                /**
     * @author      Shubham Raj
     * This case describe that favourites will be displayed irrespective of languages selected  
     * @ throws          Description of exception that the method throws 
     *//*

                @Test(priority = 4)
                public static void VerifyToFavouriteDifferentLanguagesOnHomepage() throws Throwable {
                                String strTCID = "TC04_Favourite";
                                LogFactory.beginTestCase(strTCID + " : Verify favorites for non-preferred Language on Home Page ");
                                favouritesFactory.verifyFavouritesDifferentLanguagesOnHomepage(strTCID);
                }
                
                
                *//**
     * @author      Shubham Raj
     * This case describe that folder should be created with the folder name given in Excel,
     * if folder name is 'NA' it will create a by default folder named 'Test Folder'  
      
     *//*
                @Test(priority = 5)
                public static void verifyToCreateFolder() throws Throwable {
                                String strTCID = "TC05_Favourite";
                                String folderName = getExcelDataByTestCaseID(strTCID);
                                folderName = (folderName.isEmpty() || folderName.equalsIgnoreCase("NA")) ? "Test Folder" : folderName;
                                LogFactory
                                                                .beginTestCase(strTCID + " : Verify creating folder for favorites from favorite portlet on homepage.");
                                favouritesFactory.verifyCreateFolder(strTCID, folderName);
                                LogFactory.endTestCase(" ");
                }


                *//**
     * @author      Shubham Raj
     * This case describe that created favourite folder should be deleted from the Homepage as well as
     * from the Quick modal window
      
     *//*
                @Test(priority = 6)
                public static void verifyToDeleteFavouriteFolder() throws Throwable {
                                String strTCID = "TC06_Favourite";
                                String folderName = getExcelDataByTestCaseID(strTCID);
                                folderName = (folderName.isEmpty() || folderName.equalsIgnoreCase("NA")) ? "Test Folder" : folderName;
                                LogFactory.beginTestCase(strTCID + " : Verify Deleting Folder in Favorite portlet on homepage.");
                                favouritesFactory.verifyDeleteFavouriteFolder(strTCID, folderName);
                }

                *//**
     * @author      Shubham Raj
     * This case describes moving/dragging the favourite folder from the top position to last 
      
     *//*
                
                @Test(priority = 7)
                public static void verifyToMovingFavouriteFolderandLink() throws Throwable {
                                String TCID = "TC07_Favourite";
                                LogFactory.beginTestCase("verify Moving Favourite Folder and Link ");
                                favouritesFactory.verifyMovingFavouriteFolderandLink(TCID);
                }

                *//**
     * @author      Shubham Raj
     * This case describes that upon clicking on the favourite folder it should expand and should show the
     * marked links
      
     *//*
                
                @Test(priority = 8)
                public static void verifyToExpandAndCollapseFavroiteFolder() throws Throwable {
                                String TCID = "TC08_Favourite";
                                LogFactory.beginTestCase("verify Expand And Collapse Favroite Folder ");
                                favouritesFactory.verifyExpandAndCollapseFavroiteFolder(TCID);
                }
                */
                /**
     * @author      Shubham Raj
     * This case describes that Quick Favorite modal window should be available on homepage, 
     * department and all levels of index pages as applicable
      
     */
           /*     
                @Test(priority = 9)
                public static void VerifyToQuickModalonHomeDepartmentandalllevelsofIndexpages() throws Throwable {
                                String TCID = "TC09_Favourite";
                                LogFactory.beginTestCase("Verify Quick Favorite modal window on Homepage, Department pages and Index page");
                                favouritesFactory.verifyQuickModalWindowOnHomepage(TCID);
                }
*/
                /**
     * @author      Shubham Raj
     * This case describes that user type some random text in the Filter 
     * text box that does not exist in any folder name nor any favorite link.
      
     */
/*                @Test(priority = 10)
                public static void verifySearchFilteringOnHomepage() throws Throwable {
                                
                                String TCID = "TC10_Favourite";
                                String linkName = "";
                                String ValidTestData = "";
                                String inValidTestData = "";
                                String s = "";
                                if (BaseClass.markedLinks.size() > 0) {
                                                
                                                linkName = BaseClass.markedlinkNames.get(0).toString().trim();
                                                LogFactory.beginTestCase("verify Filtering on Favourite portlet on Homepage");
                                                ValidTestData = linkName;
                                                favouritesFactory.VerifyFilterforInvalidAndValidData(TCID, ValidTestData);
                                                inValidTestData = ValidationFactory.reverseString(linkName);
                                                favouritesFactory.VerifyFilterforInvalidAndValidData(TCID, inValidTestData);
                                                
                                }
                                
                                else{
                                                ReportFactory.reporterOutput(TCID, "Verify filtering of the favorite portlet", "NA", "Favorite filter should work properly with no error message", "No Marked link is present for the particular dealer", "FAIL");
                                }
                }

                *//**
     * @author      Shubham Raj
     * This case describes that user should be able to deleted a favourite from link portlet
     * and it should not be further displayed on Homepage and Quick modal window 
           
     *//*
                @Test(priority = 11)
                public static void verifyToRemoveFavouriteLinkfromlinkportlet() throws Throwable {
                                String TCID1 = "TC11_Favourite";
                                String linkName = "";
                                
                                if (BaseClass.markedLinks.size() > 0) {
                                                
                                                LogFactory.beginTestCase("Verify removing a favorite from links portlet on Department Page ");
                                                
                                                favouritesFactory.verifyRemoveFavouriteLink(TCID1);
                                }
                                else{
                                                ReportFactory.reporterOutput(TCID1, "verify Remove Favourite Link from Link portlet Department", "NA", "Favourite should get removed and should no more display on 'Favourite' Portlet on homepage", "No Marked link is present for the particular dealer", "PASS");
                                }
                }

                *//**
     * @author      Shubham Raj
     * This case describes that user should be able to deleted a favourite from Quick modal window
     * and it should not be further displayed on Homepage 
           
     *//*
                
                @Test(priority = 12)
                public static void verifyToRemoveFavouriteLinkfromQuickmodalwindow() throws Throwable {
                                String TCID = "TC12_Favourite";
                                String linkName = "";
                                
                                if (BaseClass.markedLinks.size() > 0) {
                                                LogFactory
                                                .beginTestCase("Verify removing a favorite from Quick Modal window " + linkName);
                                                favouritesFactory.ToRemoveFavouriteLinkfromQuickmodalwindow(TCID);
                                }    
                                else{
                                    ReportFactory.reporterOutput(TCID, "verify Remove Favourite Link from Quick Modal window", "NA", "Favourite should be removed from Quick Modal and should no more display on Favourite portlet on homepage", "No Marked link is present for the particular dealer", "PASS");
                    }              
                }
                
                *//**
     * @author      Shubham Raj
     * This case describes that user should be able to deleted a favourite from homepage portlet
     * and it should not be further displayed on Quick modal window 
           
     *//*
                @Test(priority = 13)
                public static void verifyToRemoveFavLinkfromFavoritesPortlethomepage() throws Throwable {
                                String TCID = "TC13_Favourite";
                                String linkName = "";
                                String ThirdlevelFolder="";
                                LogFactory.beginTestCase("Verify removing a favorite from 'Favorites Portlet' on homepage,");
                                if (BaseClass.markedLinks.size() > 0) {
                                                favouritesFactory.ToRemoveFavLinkfromFavoritesPortlethomepage(TCID);
                                }
                                else {
                                	 ReportFactory.reporterOutput(TCID, "Verify remove favourite Link from 'Favorites Portlet' on homepage", "NA", "Favourite should get removed and should no more display on quick favourite modal window page and Favourite portlet on homepage", "No Marked link is present for the particular dealer", "PASS");
                                }
                }*/

                /**
     * @author      Shubham Raj
     * This case describes that Favorites from one dealer to another dealer should be copied only if 
     * those links are accessible (in terms of country tagging, product type tagging, additional RACF
     *  group tagging, department access) to the dealer who is copying them.
     */
                
    @Test(priority = 14)
    public static void verifyCopyFavouriteDealerUser()  throws Throwable
    {
                   String TCID = "TC14_Favourite";
                   
                   LogFactory.beginTestCase("Verify copy Favourite for dealer user ");
                   String RACFID = getExcelDataByTestCaseID(TCID);
                   if (!RACFID.isEmpty() && !RACFID.equalsIgnoreCase("NA"))
                   {
                   favouritesFactory.verifyCopyFavouriteForDealer(TCID,RACFID);
                   }
                   else
                   {
       				ReportFactory.reporterOutput(strTCID, "Verify copy Favourite for dealer user ",
    						"NA", "NA","No Input value has been provided in the test data sheet", "Pass");

                   }
                   LogFactory.endTestCase("");
                   
    }

    /**
     * @author      Shubham Raj
     * This case describes Verify country filtering for employee users on
     * Links portlet and favorites portlet on Homepage & quick favorites 
     * modal window and all types of index pages.
     *  
     
     */
    @SuppressWarnings("static-access")
    @Test(priority= 15)
/*public static void verifyToFavouritesOnchangingCountryGroupingType() throws Throwable
  { 
       String TCID = "TC16_Favourite";
   //String WCMcountryGrouplist = "";
   String eachWcmCountryGroupName = "";
   String Title = "";
   String departmentName = "";
   String Titlelink = "";
   
   LogFactory.beginTestCase("Verify favourite on changing the Country Type ");

   //WCMcountryGrouplist= "Argentina, Hispano North,Hispano South";
   try {
          List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link");
          GenericFactory.navigateToHomePage();
          
          for (int i = 0; i < userWCMContent.size(); i++)
               {
               //WCMcountryGrouplist = (String) userWCMContent.get(i).get("Country").toString().trim();
               eachWcmCountryGroupName = userWCMContent.get(i).get("Country").toString().trim();
               departmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
               Title = (String) userWCMContent.get(i).get("Title").toString().trim();
               TCID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
          
            favouritesFactory.verifyFavouritesOnchangingCountryGroupingType(TCID, eachWcmCountryGroupName, Title, departmentName);
            LogFactory.info("Each Wcm Country Group Name is = "+eachWcmCountryGroupName);
               }
          
      } catch (Exception e) 
       {                             
             e.printStackTrace();
       }
   LogFactory.endTestCase(" ");
  }*/

                @AfterClass
                public void getReportFooter() throws InterruptedException {
                                LogFactory.endTestCase("Favourite Testcases");
                                ReportFactory.tableEnd();

                }

}
