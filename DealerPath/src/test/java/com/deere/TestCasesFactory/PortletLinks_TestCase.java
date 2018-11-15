
/**
              
              * @author     Archana Gaikwad
              * @Script     PortletLinks_TestCase
              * @version
              * @Date       June.27.2018    
**/


package com.deere.TestCasesFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deere.Helpers.BaseClass;
import com.deere.Helpers.ExcelFactory;
import com.deere.Helpers.GenericFactory;
import com.deere.Helpers.LogFactory;
import com.deere.Helpers.ReportFactory;
import com.deere.PageFactory.PortletLinksPage_POF;

@Test(groups = { "PortletLinks" })
public class PortletLinks_TestCase extends BaseClass {

              WebDriver driver;
              static String strExpectedValue;
              static String strTCID;

              @BeforeClass
              public void getReportHeader() throws InterruptedException {
                             ReportFactory.tableEnd();
                             GenericFactory.createHeaderSection("Portlet Links");
              }

    /**
      * @author     Archana Gaikwad
      * Verify filter functionality for links portlet with valid data.--Enter Search criteria in search box and check correct result is displayed.
      * @param param1    verifyFiltervaliddata(strTCID,strDepartmentName,strSubDepartmentName,strTitle)
      * @return          Valid Search Criteria
      * @ exception      try and Catch
      * @ throws         Throwable
      **/

              
             @Test(priority = 1)
              public void verifyFilterValiddata() throws Throwable {


                             strTCID = "TC01_PortletLinks";
                             List<LinkedHashMap> userWCMContent =ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","","AT-Document","AT-Rich Text");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           int count =0;
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String    strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }
                                           LogFactory.info("Verify filter functionality of Links with valid filter Scenario Started.");
                                           PortletLinksPage_POF.verifyFiltervaliddata(strTCID,strDepartmentName,strSubDepartmentName,strTitle);
                                           count++;
                                           if(count==1)
                                           {
                                                          break;
                                           }

                             }
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_ValidSearch","Verify filter functionality of Links with Valid filter Criteria.","NA","Search criteria should be available in the list.", "actResult", "status");
                             }
                             GenericFactory.navigateToHomePage();              
                             LogFactory.info("Verify filter functionality of Links with valid filter Scenario Ended.");

              }

              /**
     * @author     Archana Gaikwad
     * Verify filter functionality for links portlet with Invalid data.-->Enter invalid search criteria in search criteria and verify corrrect result is displayed.
     * @param param1    verifyFilterInvaliddata(strTCID,strDepartmentName,strSubDepartmentName);
     * @return          InValid Search Criteria
     * @ exception      try and Catch
     * @ throws         Throwable
     **/

               @Test(priority = 2)
              public void verifyFilterInvaliddata() throws Throwable 
              {

                             strTCID = "TC02_PortletLinks";
                             List<LinkedHashMap> userWCMContent =ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Index Page","AT-Document","AT-Rich Text");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           int count =0;
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);

                                           }
                                           LogFactory.info("Verify filter functionality of Links with Invalid filter Scenario Started.");
                                           PortletLinksPage_POF.verifyFilterInvaliddata(strTCID,strDepartmentName,strSubDepartmentName);
                                           count++;
                                           if(count==1)
                                           {
                                                          break;
                                           }

                             }
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_InvalidSearch","Verify filter functionality of Links with Invalid filter Criteria.","NA","No results should be found for search criteria.", "actResult", "status");
                             }
                             GenericFactory.navigateToHomePage();              
                             LogFactory.info("Verify filter functionality of Links with Invalid filter Scenario Ended.");


              } 
              
               /**
                   * @author     Archana Gaikwad
                   * Verify smooth scroll to the 2nd level category (sub-department) on links.
                   * @param param1    verifySmoothScroll(strTCID,strDepartmentName, strSubDepartmentName);
                   * @return          Smooth Scroll
                   * @ exception      try and Catch
                   * @ throws         Throwable
                   **/
              
 
              @SuppressWarnings("rawtypes")
              @Test(priority = 3)
  public void verifySmoothScroll() throws Throwable 
              {

                             strTCID = "TC03_PortletLinks";
                             List<LinkedHashMap> userWCMContent =ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Index Page","AT-Document","AT-Rich Text");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           int count =0;
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);

                                           }
                                           LogFactory.info("Verify smooth scroll to the 2nd level category (sub-department) on links portlet Started.");
                                           PortletLinksPage_POF.verifySmoothScroll(strTCID,strDepartmentName, strSubDepartmentName);
                                           count++;
                                           if(count==1)
                                           {
                                                          break;
                                           }

                             }
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_Smooth Scroll","Verify smooth scroll to the 2nd level category (sub-department) on links portlet","NA","System should smooth scroll to the particular 2nd level category (sub-department) on the page with fixed navigation.","actResult", "status");
                             }
                             GenericFactory.navigateToHomePage();              
                             LogFactory.info("Verify smooth scroll to the 2nd level category (sub-department) on links portlet ended");
              }   

              /**
     * @author     Archana Gaikwad
     * Verify expanding and collapsing of folders on Link portlet.
     * @param param1    verifyExpandCollapseFolder(strTCID,strDepartmentName,SubDepartmentName,strFolderName);
     * @return          ExpandCollapseFolder
     * @ exception      try and Catch
     * @ throws         Throwable
     **/
              
              @SuppressWarnings("rawtypes")
              @Test(priority = 4)
              public void verifyExpandCollapseFolder() throws Throwable {

                             strTCID = "TC04_PortletLinks";
                             List<LinkedHashMap> userWCMContent =ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Index Page","AT-Rich Text","AT-GrandChild Index Page");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           int count =0;
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    SubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String    strFolderName = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }

                                           if(!strFolderName.equalsIgnoreCase("NA"))
                                           {

                                                          LogFactory.info("Verify Collapse/Expand of Folder Scenario Started.");
                                                         PortletLinksPage_POF.verifyExpandCollapseFolder(strTCID,strDepartmentName,SubDepartmentName,strFolderName);
                                                          count++;
                                                          if(count==1)
                                                          {
                                                                        break;
                                                          }

                                           }

                             }
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_ExpandCollapseFolder","Verify Collapse/Expand of Folder Scenario.","NA","By default folders on the links portlet should be in collapsed mode and Clicking on collapsed folder expanding the folder showing links under it.", "actResult", "status");
                             }
                             GenericFactory.navigateToHomePage();              
                             LogFactory.info("Verify Collapse/Expand of Folder Scenario Ended.");


              }  

              /**
     * @author     Archana Gaikwad
     * Verify The expanded state of the folder for the user from Links portlet should persist for the session.
     * @param param1    verifySessionforExpandFolder(strTCID,strDepartmentName,SubDepartmentName,strFolderName,strTitle);
     * @return          verifysessionExpandFolder
     * @ exception      try and Catch
     * @ throws         Throwable
     **/

              @SuppressWarnings("unused")
              @Test(priority = 5)
              public void verifysessionExpandFolder() throws Throwable {

                             strTCID = "TC05_PortletLinks";
                             @SuppressWarnings("rawtypes")
                             List<LinkedHashMap> userWCMContent =ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Index Page","AT-Rich Text","AT-GrandChild Index Page");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {
                                           int count =0;
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    SubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String    strFolderName = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim(); 
                                           String    strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }

                                           if(!strFolderName.equalsIgnoreCase("NA"))
                                           {

                                                          LogFactory.info("Verify The expanded state of the folder for the user from Links portlet should persist for the session Scenario Started.");
                                                         PortletLinksPage_POF.verifySessionforExpandFolder(strTCID,strDepartmentName,SubDepartmentName,strFolderName,strTitle);
                                                          count++;
                                                          if(count==1)
                                                          {
                                                                        break;
                                                          }
                                           } }                                                     


                             GenericFactory.navigateToHomePage();              
                             LogFactory.info("Verify The expanded state of the folder for the user from Links portlet should persist for the session Scenario Ended.");
              }
              
              /**
     * @author          Archana Gaikwad
     * Verify Fly-out navigation on Department page.
     * @param param1    flyoutNavigationDept(strTCID,strDepartmentName);
     * @return                   Flyout Navigation
     * @ exception      try and Catch
     * @ throws         Throwable
     **/
              

              @Test(priority = 6)
              public void verifyFlyOutNavigation() throws Throwable
              {
                             strTCID = "TC06_PortletLinks";
                             int count =0;
                             List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("","AT-Index Page", "","");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }


                                           LogFactory.info("Verify Fly-out navigation on Department Started.");
                                           PortletLinksPage_POF.flyoutNavigationDept(strTCID,strDepartmentName);

                                           count++;
                                           if(count==1)
                                           {
                                                          break;
                                           }
                             } 
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_Fly-out navigation","Verify Fly-out navigation on Department.","NA","Fly-out navigation should be displayed just by hovering over the My DealerPath of left navigation listing all the accessible departments.(non-preferred departments are greyed out)", "actResult", "status");
                             }
                             GenericFactory.navigateToHomePage();            
                             LogFactory.info("Verify Fly-out navigation on Department Ended.");
              } 






              /**
    * @author          Archana Gaikwad
    * From links portlet of any department if the same department is unchecked & saved from 'My Preferences' for any user, the system should redirect to the homepage.
    * @param param1    ChangeOfPrefDept(strTCID,strDepartmentName);
    * @return          Department check and uncheck
    * @ exception      try and Catch
    * @ throws         Throwable
    **/

              @Test(priority = 7)
              public void verifyOnChangePrefDept() throws Throwable 
              {

                             strTCID = "TC07_PortletLinks";
                             String    scenarioName ="Verify Links portlet on changing of preferred departments.";
                             LogFactory.info(scenarioName +"Scenario Started."); 
                             boolean departmentStatus=false;
                             int count =0;
                             List<LinkedHashMap> userWCMContent = ExcelFactory.getWCMSiteAreaDetails("NA");
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_ChangeOfPrefDept",scenarioName,"NA","Department is unchecked & saved from 'My Preferences' for any user, the system should redirect to the homepage.", "actResult", "status");
                             }
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           String    wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    strRACFGroups = (String) userWCMContent.get(i).get("RACFGroups").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }


                                           List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
                                           strDepartmentName=translatedText.get(0); 

                                           departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);

                                           if(departmentStatus==true)
                                           {
                                                         PortletLinksPage_POF.ChangeOfPrefDept(strTCID,strDepartmentName);


                                                          count++;
                                                          if(count==1)
                                                          {
                                                                        break;
                                                          }

                                                          GenericFactory.navigateToHomePage();            

                                           }

                                           else
                                           {

                                                         PortletLinksPage_POF.departmentInactive(strTCID,scenarioName,strDepartmentName,scenarioName+ "and Once We uncheck it should navigate to Home Page", "actResult", "status");
                                                          count++;
                                                          if(count==1)
                                                          {
                                                                        break;
                                                          }
                                           }


                             }  

                             LogFactory.info("Verify Links portlet on changing of preferred departments Scenario Ended."); 
                             GenericFactory.navigateToHomePage();
              }

     
               /**
                  * @author          Archana Gaikwad
                  * Verify Links portlet should display content filtered with preferred product types of the user.
                  * @param param1    changingPrefProduct(strwcmTestCaseID,strUserDefinedCountry, strWCMCountry, strUserDefinedProducts, strWCMProducts,strContenttype, strDepartmentName, strSubDepartmentName,strThirdLevelFolder, strTitle);
                  * @return          Changing Produt Prefernce
                  * @ exception      try and Catch
                  * @ throws         Throwable
                  **/


              

              @Test(priority = 8)
              public void verifyChangingPrefProd() throws Throwable {

                             String scenarioName ="Verify Links portlet on changing of preferred product types.";
                             LogFactory.info(scenarioName +" Scenario Started.");
                             int count=0;
                             @SuppressWarnings("rawtypes")

                             List<LinkedHashMap> userWCMContent =ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Document","AT-Rich Text","");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {
                                           System.out.println(userWCMContent.size());
                                           String    strwcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String    strUserDefinedCountry = (String) userWCMContent.get(i).get("Dealer_Country").toString().trim();
                                           String    strWCMCountry = (String) userWCMContent.get(i).get("Country").toString().trim();
                                           String    strUserDefinedProducts = (String) userWCMContent.get(i).get("Dealer_ProductType").toString().trim();
                                           String    strWCMProducts = (String) userWCMContent.get(i).get("ProductType").toString().trim();
                                           String    strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String    strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String    strThirdLevelIndexPage = (String) userWCMContent.get(i).get("3rdLevelIndexPage").toString().trim();
                                           String    strThirdLevelIndexPageCategories =(String) userWCMContent.get(i).get("3rdLevelIndexPageCategories").toString().trim();
                                           String    strThirdLevelIndexPageNestedCategories = (String) userWCMContent.get(i).get("3rdLevelIndexPageNestedCategories").toString().trim();
                                           String    strThirdLevelLandingPage = (String) userWCMContent.get(i).get("3rdLevelLandingPage").toString().trim();
                                           String    strThirdLevelChildIndexPage = (String) userWCMContent.get(i).get("3rdLevelChildIndexPage").toString().trim();
                                           String    strThirdLevelChildIndexPageCategories = (String) userWCMContent.get(i).get("3rdLevelChildIndexPageCategories").toString().trim();
                                           String    strThirdLevelChildIndexPageNestedCategories = (String) userWCMContent.get(i).get("3rdLevelChildIndexPageNestedCategories").toString().trim();
                                           String    strThirdLevelGrandChildIndexPage = (String) userWCMContent.get(i).get("3rdLevelGrandChildIndexPage").toString().trim();
                                           String    strThirdLevelGrandChildIndexPageCategories = (String) userWCMContent.get(i).get("3rdLevelGrandChildIndexPageCategories").toString().trim();
                                           String    strThirdLevelGrandChildIndexPageNestedCategories = (String) userWCMContent.get(i).get("3rdLevelGrandChildIndexPageNestedCategories").toString().trim();
                                           String    strThirdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
                                           String    strForthLevelIndexPage = (String) userWCMContent.get(i).get("4thLevelIndexPage").toString().trim();
                                           String    strForthLevelIndexPageCategories = (String) userWCMContent.get(i).get("4thLevelIndexPageCategories").toString().trim();
                                           String    strForthLevelIndexPageNestedCategories = (String) userWCMContent.get(i).get("4thLevelIndexPageNestedCategories").toString().trim();
                                           String    strForthLevelLandingPage = (String) userWCMContent.get(i).get("4thLevelLandingPage").toString().trim();
                                           String    strForthLevelChildIndexPage = (String) userWCMContent.get(i).get("4thLevelChildIndexPage").toString().trim();
                                           String    strForthLevelChildIndexPageCategories = (String) userWCMContent.get(i).get("4thLevelChildIndexPageCategories").toString().trim();
                                           String    strForthLevelChildIndexPageNestedCategories = (String) userWCMContent.get(i).get("4thLevelChildIndexPageNestedCategories").toString().trim();
                                           String    strForthLevelGrandChildIndexPage = (String) userWCMContent.get(i).get("4thLevelGrandChildIndexPage").toString().trim();
                                           String    strForthLevelGrandChildIndexPageCategories = (String) userWCMContent.get(i).get("4thLevelGrandChildIndexPageCategories").toString().trim();
                                           String    strForthLevelGrandChildIndexPageNestedCategories = (String) userWCMContent.get(i).get("4thLevelGrandChildIndexPageNestedCategories").toString().trim();
                                           String    strRACFGroups = (String) userWCMContent.get(i).get("RACFGroups").toString().trim();
                                           String    strdealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString().trim();
                                           String    wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String    library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }


                                           if(strThirdLevelIndexPage.equalsIgnoreCase("NA") && 
                                                                        strThirdLevelIndexPageCategories.equalsIgnoreCase("NA") && 
                                                                        strThirdLevelIndexPageNestedCategories.equalsIgnoreCase("NA") && 
                                                                        strThirdLevelLandingPage .equalsIgnoreCase("NA") && 
                                                                        strThirdLevelChildIndexPage .equalsIgnoreCase("NA") && 
                                                                        strThirdLevelChildIndexPageCategories .equalsIgnoreCase("NA") && 
                                                                        strThirdLevelChildIndexPageNestedCategories.equalsIgnoreCase("NA") && 
                                                                        strThirdLevelGrandChildIndexPage.equalsIgnoreCase("NA") && 
                                                                        strThirdLevelGrandChildIndexPageCategories.equalsIgnoreCase("NA") && 
                                                                        strThirdLevelGrandChildIndexPageNestedCategories.equalsIgnoreCase("NA") && 
                                                                       (strThirdLevelFolder.equalsIgnoreCase("NA") || !(strThirdLevelFolder.equalsIgnoreCase("NA"))) && strForthLevelIndexPage.equalsIgnoreCase("NA") && 
                                                                        strForthLevelIndexPageCategories.equalsIgnoreCase("NA") && 
                                                                        strForthLevelIndexPageNestedCategories.equalsIgnoreCase("NA") && 
                                                                        strForthLevelLandingPage.equalsIgnoreCase("NA") && 
                                                                        strForthLevelChildIndexPage.equalsIgnoreCase("NA") && 
                                                                        strForthLevelChildIndexPageCategories.equalsIgnoreCase("NA") && 
                                                                        strForthLevelChildIndexPageNestedCategories.equalsIgnoreCase("NA") && 
                                                                        strForthLevelGrandChildIndexPage.equalsIgnoreCase("NA") && 
                                                                        strForthLevelGrandChildIndexPageCategories.equalsIgnoreCase("NA") && 
                                                                        strForthLevelGrandChildIndexPageNestedCategories.equalsIgnoreCase("NA"))
                                           {

                                                          boolean departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);

                                                          if(departmentStatus == true)
                                                          {
                                                                        if(strContenttype.equalsIgnoreCase("AT-Link") || strContenttype.equalsIgnoreCase("AT-Document") || strContenttype.equalsIgnoreCase("AT-Rich Text"))
                                                                        {

                                                                                      PortletLinksPage_POF.changingPrefProduct(strwcmTestCaseID,strUserDefinedCountry, strWCMCountry, strUserDefinedProducts, strWCMProducts,strContenttype, strDepartmentName, strSubDepartmentName,strThirdLevelFolder, strTitle);
                                                                        }

                                                                        GenericFactory.navigateToHomePage();             
                                                          }

                                                          else
                                                          {
                                                                        PortletLinksPage_POF.departmentInactive(strwcmTestCaseID,scenarioName,strDepartmentName+ "  and  " +strTitle,"Preferred Product preference should work.", "actResult", "status");
                                                                        count++;
                                                                        if(count==1)
                                                                        {
                                                                                      break;
                                                                        }
                                                          }            
                                           }


                             }


                             LogFactory.info("Verify Links portlet should display content filtered with preferred product types of the user Scenario Ended.");

                             GenericFactory.tocheckAllProducts();
              }  
                             
              /**
              * @author          Archana Gaikwad
              * Navigate to a 3rd parent level Landing Page from links portlet and verify.
              * @param param1    portletLandingPage(userWCMContent.get(i),strContenttype);
              * @return          Changing Produt Prefernce
              * @ exception      try and Catch
              * @ throws         Throwable
              **/

 

              @Test(priority = 9)
              public void verifyLandingPage() throws Throwable
              {

                             String scenarioName = "Verify Navigate to a 3rd parent level Landing Page from links portlet";
                             LogFactory.info(scenarioName+ "Scenario Started.");
                             @SuppressWarnings("rawtypes")
                             int count=0;
                             GenericFactory.tocheckAllProducts();   
                             List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Link","AT-Index Page", "AT-Rich Text","");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String str3rdLevelLandingPage = (String) userWCMContent.get(i).get("3rdLevelLandingPage").toString().trim();
                                           String strThirdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
                                           String strForthLevelLandingPage = (String) userWCMContent.get(i).get("4thLevelLandingPage").toString().trim();
                                           String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }


                                           if(!str3rdLevelLandingPage.equalsIgnoreCase("NA") || !strThirdLevelFolder.equalsIgnoreCase("NA") && !strForthLevelLandingPage.equalsIgnoreCase("NA"))
                                           {
                                                          List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
                                                          strDepartmentName=translatedText.get(0); 

                                                          boolean departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);

                                                          if(departmentStatus == true)
                                                          {


                                                                        GenericFactory.navigateToIndexPage(userWCMContent.get(i));
                                                                        PortletLinksPage_POF.portletLandingPage(userWCMContent.get(i),strContenttype);
                                                          


                                                          }
                                                          else
                                                          {
                                                                        PortletLinksPage_POF.departmentInactive(wcmTestCaseID,scenarioName,str3rdLevelLandingPage,"User should be able to Navigate to a 3rd parent level Landing Page from links portlet.", "actResult", "status");
                                                                        count++;
                                                                        if(count==1)
                                                                        {
                                                                                      break;
                                                                        }

                                                          }
                                           }
                                           GenericFactory.navigateToHomePage();          
                             }

                
                             LogFactory.info("Verify Navigate to a 3rd parent level Landing Page from links portlet Scenario Ended.");
              }

 
              /**
              * @author          Archana Gaikwad
              * Verify navigating to different type of links/Document Content/Rich Text content from links portlet.
              * @param param1    portletLinks(wcmTestCaseID,strUserDefinedCountry, strWCMCountry, strUserDefinedProducts, strWCMProducts,strContenttype, strDepartmentName, strSubDepartmentName, strTitle,strThirdLevelFolder,strDescription,strRACFGroups,strdealerType);
              * @return          Verify Content type whether it is 'AT-Link','AT-Document','AT-Rich-Text'
              * @ exception      try and Catch
              * @ throws         Throwable
              **/
              


              @Test(priority = 10)
              public void verifyContentType() throws Throwable 
              {
                             String    scenarioName ="Verify navigating to different type of links/Document Content/Rich Text content from links portlet";
                             LogFactory.info(scenarioName +"Scenario Started."); 
                             boolean departmentStatus=false;
                             int count=0; 

              GenericFactory.tocheckAllProducts();

                             List<LinkedHashMap> userWCMContent = ExcelFactory.getWCMSiteAreaDetails("NA");

                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           String    wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String    strUserDefinedCountry = (String) userWCMContent.get(i).get("Dealer_Country").toString().trim();
                                           String    strWCMCountry = (String) userWCMContent.get(i).get("Country").toString().trim();
                                           String    strUserDefinedProducts = (String) userWCMContent.get(i).get("Dealer_ProductType").toString().trim();
                                           String    strWCMProducts = (String) userWCMContent.get(i).get("ProductType").toString().trim();
                                           String    strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
                                           String    strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String    strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String    strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String    strDescription = (String) userWCMContent.get(i).get("Description").toString().trim();
                                           String    strThirdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
                                           String    strRACFGroups = (String) userWCMContent.get(i).get("RACFGroups").toString().trim();
                                           String strdealerType = (String) userWCMContent.get(i).get("DealerType (Main/Sub)").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }


                                           List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
                                           strDepartmentName=translatedText.get(0); 

                                           departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);
                                           boolean racfFlag = GenericFactory.verifyRacfGroupMatched(strRACFGroups);
                                           boolean wcmDealerType= GenericFactory.verifyDealerType(strdealerType);

                                           if(departmentStatus==true)
                                           {


                                           PortletLinksPage_POF.portletLinks(wcmTestCaseID,strUserDefinedCountry, strWCMCountry, strUserDefinedProducts, strWCMProducts,strContenttype, strDepartmentName, strSubDepartmentName, strTitle,strThirdLevelFolder,strDescription,strRACFGroups,strdealerType);


                                           }

                                           else
                                           {
                                                         PortletLinksPage_POF.departmentInactive(wcmTestCaseID,scenarioName,strContenttype,"Title should be Present.", "actResult", "status");
                                                          count++;
                                                          if(count==1)
                                                          {
                                                                        break;
                                                          }
                                           }

                                           GenericFactory.navigateToHomePage();
                             }  
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_ContentType",scenarioName,"NA"," Link should NOT be available.", "actResult", "status");
                             }
                             LogFactory.info("Verify navigating to different type of links/Document Content/Rich Text content from links portlet Scenario Ended.");
                             GenericFactory.navigateToHomePage();
              } 


              /**
              * @author          Archana Gaikwad
              * Verify Navigate to an index page on DealerPath portal created with 'Matrix - 2/3/4/5 columns' presentation template.
              * @param param1    matrix2xolumns(userWCMContent.get(i));
              * @return          2,3,4,5 Presentation Template
              * @ exception      try and Catch
              * @ throws         Throwable
              **/

              @Test(priority = 11)
              public static void presentationTemplate() throws Throwable 
              {

                             int count=0;
                             String scenarioName = "Verify Navigate to an index page on DealerPath portal created with 'Matrix - 2/3/4/5 columns' presentation template";
                             LogFactory.info(scenarioName+ "Scenario Started.");
                             @SuppressWarnings("rawtypes")
                             List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("","AT-Index Page", "AT-GrandChild Index Page","");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {
                                           String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String str3rdLevelIndexPage = (String) userWCMContent.get(i).get("3rdLevelIndexPage").toString().trim();
                                           String strIndex_Page_Template = (String) userWCMContent.get(i).get("Index_Page_Template").toString().trim();
                                           String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }

                                           if(!str3rdLevelIndexPage.equalsIgnoreCase("NA") && !strIndex_Page_Template.equalsIgnoreCase(""))
                                           {

                                                          List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
                                                          strDepartmentName=translatedText.get(0); 
                                                          boolean departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);
                                                          if(departmentStatus == true)
                                                          {

                                                                        GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(i));    
                                                                        GenericFactory.navigateToIndexPage(userWCMContent.get(i));
                                                                        if(!strIndex_Page_Template.equalsIgnoreCase("NA"))
                                                                        {
                                                                        PortletLinksPage_POF.matrix2xolumns(userWCMContent.get(i));
                                                                        }
                                                                        
                                                          }
                                                          else
                                                          {
                                                                        PortletLinksPage_POF.departmentInactive(wcmTestCaseID,scenarioName,str3rdLevelIndexPage,"Matrix should be displayed on the 3rd level parent index page in 2/3/4/5 columns.", "actResult", "status");
                                                                        count++;
                                                                        if(count==1)
                                                                        {
                                                                                      break;
                                                                        }
                                                          }
                                           }
                                           GenericFactory.navigateToHomePage();
                             }
                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_Matrix",scenarioName,"NA","Matrix should be displayed on the 3rd level parent index page in 2/3/4/5 columns.", "actResult", "status");
                             }


                             LogFactory.info("Verify Navigate to an index page on DealerPath portal created with 'Matrix - 2/3/4/5 columns' presentation template Scenario Ended.");
                             
              }

              /**
              * @author          Archana Gaikwad
              * Verify Navigate to an index page on DealerPath portal created with 'Matrix - 2/3/4/5 columns' presentation template and check sorting and grouping
              * @param param1    matrixGrouping(userWCMContent.get(i),allDataComparison,allDates);
              * @return          Sorting and year Grouping
              * @ exception      try and Catch
              * @ throws         Throwable
              **/

 
              @Test(priority = 13)
              public static void sortingTemplate() throws Throwable 
              {
                             String scenarioName = "Verify Navigate to an index page on DealerPath portal created with 'Matrix - 2/3/4/5 columns' presentation template and check sorting and grouping";
                             LogFactory.info(scenarioName+ "Scenario Started.");
                             @SuppressWarnings("rawtypes")
                             int count=0;

                             Set<String> allDepartment = new HashSet<String>(); 
                             Set<String> allSubDepartmentName = new HashSet<String>(); 
                             Set<String> allIndex_Page_Template_Label = new HashSet<String>(); 
                             List<String> allDataComparison = new ArrayList<String>(); 
                             List<String> allDates = new ArrayList<String>(); 

                             List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProductAndTemplate("AT-Child Index Page","SAT-Table Index Page", "AT-Index Page","AT-GrandChild Index Page");
                             for (int i = 0; i < userWCMContent.size(); i++) 
                             {
                                           String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
                                           String str3rdLevelIndexPage = (String) userWCMContent.get(i).get("3rdLevelIndexPage").toString().trim();
                                           String strIndex_Page_Template = (String) userWCMContent.get(i).get("Index_Page_Template").toString().trim();
                                           String strIndex_Page_Template_Label = (String) userWCMContent.get(i).get("Index_Page_Template_Label").toString().trim();
                                           String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String sorting = (String) userWCMContent.get(i).get("Sort By").toString().trim();
                                           String strReleaseDate = (String) userWCMContent.get(i).get("ReleaseDate").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           
                              
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }

                                           if(strContenttype.equalsIgnoreCase("SAT-Table Index Page"))
                                           {
                                                          List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
                                                          strDepartmentName=translatedText.get(0); 
                                                          boolean departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);
                                                          if(departmentStatus == true)
                                                          {

                                                                        GenericFactory.navigateToIndexPage(userWCMContent.get(i));
                                                                        
                                                          }
                                                          else
                                                          {
                                                                        PortletLinksPage_POF.departmentInactive(wcmTestCaseID,scenarioName,str3rdLevelIndexPage,"Matrix should be displayed on the 3rd level parent index page in 2/3/4/5 columns and sorting and year grouping should work.", "actResult", "status");
                                                                        count++;
                                                                        if(count==1)
                                                                        {
                                                                                      break;
                                                                        }
                                                          }
                                           }
                                           if(strContenttype.equalsIgnoreCase("AT-Index Page") || strContenttype.equalsIgnoreCase("AT-Child Index Page") ||strContenttype.equalsIgnoreCase("AT-GrandChild Index Page"))
                                           {
                                                          int m=i+1;
                                           //           if(m==userWCMContent.size() ||  !(str3rdLevelIndexPage.equalsIgnoreCase((String) userWCMContent.get(m).get("3rdLevelIndexPage").toString().trim()) || !strContenttype.equalsIgnoreCase((String) userWCMContent.get(m).get("ContentType").toString().trim())))
                                                          if(m==userWCMContent.size() || (!strContenttype.equalsIgnoreCase((String) userWCMContent.get(m).get("ContentType").toString().trim())) )
                                                          {
                                                                        m=i;  
                                                          }
                                                          if(i<userWCMContent.size() && strIndex_Page_Template.equalsIgnoreCase((String) userWCMContent.get(m).get("Index_Page_Template").toString().trim()) && str3rdLevelIndexPage.equalsIgnoreCase((String) userWCMContent.get(m).get("3rdLevelIndexPage").toString().trim()))
                                                          {

                                                                        allDataComparison.add((String) userWCMContent.get(i).get(sorting).toString().trim());
                                                                        allDates.add((String) userWCMContent.get(i).get("ReleaseDate").toString().trim());
                                                                        allIndex_Page_Template_Label.add((String) userWCMContent.get(i).get("Index_Page_Template_Label").toString().trim());

                                                          }

                                                          if(i<userWCMContent.size()-1 && (!sorting.equalsIgnoreCase((String) userWCMContent.get(i+1).get("Sort By").toString().trim()) || !str3rdLevelIndexPage.equalsIgnoreCase((String) userWCMContent.get(i+1).get("3rdLevelIndexPage").toString().trim()) ||!strIndex_Page_Template.equalsIgnoreCase((String) userWCMContent.get(m).get("Index_Page_Template").toString().trim())  )|| i==userWCMContent.size()-1)
                                                          {

                                                                        GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(i));
                                                                        PortletLinksPage_POF.matrixGrouping(userWCMContent.get(i),allDataComparison,allDates);
                                                                        GenericFactory.navigateToHomePage();
                                                                        allDataComparison.removeAll(allDataComparison);
                                                                        allDates.removeAll(allDates);
                                                          }

                                           }

                             }

                             if(userWCMContent.size()==0)
                             {
                                           PortletLinksPage_POF.matchNotFound("TC_YearGrouping& Sorting",scenarioName,"NA","Matrix should be displayed on the 3rd level parent index page in 2/3/4/5 columns and sorting and year grouping should work.", "actResult", "status");
                             } 

                             GenericFactory.navigateToHomePage();

                             LogFactory.info("Verify Navigate to an index page on DealerPath portal created with 'Matrix - 2/3/4/5 columns' presentation template Scenario Ended.");
                             GenericFactory.navigateToHomePage();
              }

              /**
              * @author          Archana Gaikwad
              * Verify Product Segment functionality.
              * @param param1    matrix2xolumns(userWCMContent.get(i));
              * @return          productSegmentIndexPages(wcmTestCaseID,strTitle,strSubDepartmentName,strUserDefinedProducts,strWCMProducts,strThirdLevelFolder,strContenttype);
              * @ exception      try and Catch
              * @ throws         Throwable
              **/


              @Test(priority = 14)
              public void verifyProdSegmentIndexPages() throws Throwable {


                             String scenarioName = "Verify Product Segment functionality";
                             LogFactory.info(scenarioName+ "Scenario Started.");
                             int count=0;
                             @SuppressWarnings("rawtypes")
                             List<LinkedHashMap> userWCMContent = ExcelFactory.getUserWcmDetailsAfterFilteringCountryAndProduct("AT-Index Page", "AT-ChildIndex Page","AT-GrandChild Index Page","");
                             for (int i = 0; i <userWCMContent.size(); i++) 
                             {

                                           String wcmTestCaseID = (String) userWCMContent.get(i).get("Test Case ID").toString().trim();
                                           String strDepartmentName = (String) userWCMContent.get(i).get("DepartmentName").toString().trim();
                                           String strSubDepartmentName = (String) userWCMContent.get(i).get("2ndLevel").toString().trim();
                                           String strUserDefinedProducts = (String) userWCMContent.get(i).get("Dealer_ProductType").toString().trim();
                                           String strWCMProducts = (String) userWCMContent.get(i).get("ProductType").toString().trim();
                                           String strContenttype = (String) userWCMContent.get(i).get("ContentType").toString().trim();
                                           String strThirdLevelFolder = (String) userWCMContent.get(i).get("3rdLevelFolder").toString().trim();
                                           String strForthLevelIndexPage = (String) userWCMContent.get(i).get("4thLevelIndexPage").toString().trim();
                                           String strTitle = (String) userWCMContent.get(i).get("Title").toString().trim();
                                           String wcmMultilingual = (String) userWCMContent.get(i).get("Multilingual").toString().trim();
                                           String library = (String) userWCMContent.get(i).get("Library").toString().trim();
                                           if(wcmMultilingual.equalsIgnoreCase("NA") || !wcmMultilingual.isEmpty())
                                           {
                                                          GenericFactory.multilingualSwitch(library, wcmMultilingual);
                                           }


                                           List<String> translatedText = GenericFactory.getTranslation(strDepartmentName);
                                           strDepartmentName=translatedText.get(0); 

                                           boolean departmentStatus= GenericFactory.clickOnDepartmentByNameFlag(strDepartmentName);
                                           if(departmentStatus == true)
                                           {

                                                         GenericFactory.navigateToIndexPage(userWCMContent.get(i));
                                                         GenericFactory.SendPORTLET_LINKFLAG(userWCMContent.get(i));
                                                         PortletLinksPage_POF.productSegmentIndexPages(wcmTestCaseID,strTitle,strSubDepartmentName,strUserDefinedProducts,strWCMProducts,strThirdLevelFolder,strContenttype);


                                           }
                                           else
                                           {
                                                         PortletLinksPage_POF.departmentInactive(wcmTestCaseID,scenarioName,strTitle,"Search criteria should not be available in the list.", "actResult", "status");
                                                          count++;
                                                          if(count==1)
                                                          {
                                                                        break;
                                                          }

                                           }
                                           GenericFactory.navigateToHomePage();            

                             }
                             LogFactory.info("Verify Product Segment functionality Scenario Ended.");            
                             GenericFactory.tocheckAllProducts();                                             
              }             


}
