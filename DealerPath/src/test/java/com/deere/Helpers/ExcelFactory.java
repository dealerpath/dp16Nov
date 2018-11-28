
/* 
 * Project    : DealerPath
 * Script     : Excel Factory
 * Author     : Shrishail Baddi
 * Date       : April.05.2018
 * Last Modified On:
 * Modified By :
 */

package com.deere.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFactory extends BaseClass {

	public static XSSFWorkbook book;
	private static XSSFSheet dataSheet;

	private static XSSFSheet dataDealerInfoSheet;
	private static XSSFSheet dataWCMContentSheet;
	private static XSSFSheet dataAdditionalTCSheet;
	private static XSSFSheet dataTranslationsSheet;
	private static XSSFSheet mappingOfRacfGroups;
	private static XSSFSheet runningStatus;

	private static XSSFRow row;
	private static XSSFCell cell;
	static List<LinkedHashMap> dealerinfo;
	static DataFormatter dataFormatter = new DataFormatter();

	/**
	 * @param file
	 *            - expects file name with absolute path
	 * @param sheetName
	 *            - expects name of the sheet to be read
	 * @return instance of XSSFSheet
	 */
	public static XSSFSheet readExcel(String strFile, String strSheetName) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(strFile));
			book = new XSSFWorkbook(excelFile);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			ioe.getMessage();
		}
		return dataSheet = book.getSheet(strSheetName);
	}

	/**
	 * 
	 * @param sheet
	 *            - expects instance of XSSFSheet
	 * @return number of rows in the sheet
	 */
	public static int getRowCount(XSSFSheet sheet) {
		return sheet.getLastRowNum();
	}

	/**
	 * 
	 * @param sheet
	 *            - expects instance of XSSFSheet
	 * @param rowNum
	 *            - expects the row number to be read
	 * @return data in the row as an instance of XSSFRow
	 */
	public static XSSFRow readRow(XSSFSheet sheet, int rowNum) {
		return sheet.getRow(rowNum);
	}

	/**
	 * 
	 * @param row
	 *            - expects row as instance of XSSFRow
	 * @return number of columns in the given row
	 */
	public static int getColumnCount(XSSFRow row) {
		return row.getPhysicalNumberOfCells();
	}

	/**
	 * 
	 * @param row
	 *            - expects row to be read as an instance of XSSFRow
	 * @return value of each column is read as an object in an ArrayList. Assumes
	 *         that excel contains either numeric or string values
	 */
	public static ArrayList<Object> readRowData(XSSFRow row) {
		int columnCount = 0;
		if (row != null) {
			columnCount = getColumnCount(row);
		}
		ArrayList<Object> rowData = new ArrayList();
		for (int i = 0; i < columnCount; i++) {
			XSSFCell cell = row.getCell(i);
			if (cell.getCellTypeEnum() == CellType.STRING) {
				// System.out.println(cell.getStringCellValue());
				rowData.add(cell.getStringCellValue());
			}
			if (cell.getCellTypeEnum() == CellType.NUMERIC) {

				if (DateUtil.isCellDateFormatted(cell)) {
					rowData.add(new SimpleDateFormat("MM_dd_yyyy").format(cell.getDateCellValue()));
				} else {
					rowData.add(cell.getNumericCellValue());
				}
			}
			if (cell.getCellTypeEnum() == CellType._NONE) {
				rowData.add(null);
			}
			if (cell.getCellTypeEnum() == CellType.BLANK) {
				rowData.add("");
			}
		}
		// System.out.println("Number of values in list: " + rowData.size());
		return rowData;

	}

	/**
	 * 
	 * @param sheet
	 *            - Expects the sheet to be read and an instance of XSSFSheet
	 * @return All the rows of sheet as an ArrayList of Maps. Each row of data is
	 *         converted into HashMap, with all the row headers as keys and cell
	 *         values in rows as values
	 */
	public static ArrayList<Map<String, Object>> readSheetAsList(XSSFSheet sheet) {
		ArrayList<Map<String, Object>> sheetData = new ArrayList();
		int rowCount = sheet.getLastRowNum();
		if (rowCount > 0) {
			ArrayList<String> header = new ArrayList();
			row = sheet.getRow(0);
			int colCount = row.getPhysicalNumberOfCells();
			for (int i = 0; i < colCount; i++) {
				cell = row.getCell(i);
				if (cell.getCellTypeEnum() == CellType.STRING) {
					header.add(cell.getStringCellValue());
				}
			}

			for (int i = 1; i <= rowCount; i++) {
				Map<String, Object> map = new HashMap<>();
				row = sheet.getRow(i);
				for (int j = 0; j < header.size(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						map.put(header.get(j), null);
					} else {
						if (cell.getCellTypeEnum() == CellType._NONE) {
							map.put(header.get(j), null);
						}
						if (cell.getCellTypeEnum() == CellType.STRING) {
							map.put(header.get(j), cell.getStringCellValue());

						}
						if (cell.getCellTypeEnum() == CellType.NUMERIC) {

							if (DateUtil.isCellDateFormatted(cell)) {
								map.put(header.get(j),
										new SimpleDateFormat("MM_dd_yyyy").format(cell.getDateCellValue()));
							} else {
								map.put(header.get(j), cell.getNumericCellValue());
							}
						}
						if (cell.getCellTypeEnum() == CellType.BLANK) {
							map.put(header.get(j), "");
						}
					}

				}
				sheetData.add(map);
			}
		}
		System.out.println(sheetData);
		return sheetData;
	}

	public static HashMap<String, String> getUserAddtionalTestcases() throws IOException, Exception {
		try {
			XSSFRow singleRow;
			XSSFCell cell;
			int TestRacfData_index = 0;
			int TestCaseID_Index = 0;
			int TestEnglishData_index = 0;

			LinkedHashMap<String, String> Mapobj = new LinkedHashMap<String, String>();
			// Excel Read
			int totalRows = dataAdditionalTCSheet.getLastRowNum();
			int totalCol = dataAdditionalTCSheet.getRow(4).getLastCellNum();

			for (int j = 0; j < totalCol; j++) {
				String Attribute_name = dataFormatter.formatCellValue(dataAdditionalTCSheet.getRow(4).getCell(j))
						.trim();
				// Passing Preferred Language Column name

				if (Attribute_name.equalsIgnoreCase(strUserRACFID)) {
					TestRacfData_index = j;
					break;
				} else if (Attribute_name.equalsIgnoreCase("English")) {
					TestEnglishData_index = j;
				}
				// Passing Test Case Column name
				else if (Attribute_name.equalsIgnoreCase(strTestCase)) {
					TestCaseID_Index = j;
				}
			}

			for (int i = 1; i <= totalRows; i++) {
				singleRow = dataAdditionalTCSheet.getRow(i);
				if ((singleRow.getCell(TestCaseID_Index).getCellType() == Cell.CELL_TYPE_STRING)) {
					if (dataFormatter.formatCellValue(singleRow.getCell(TestCaseID_Index)).trim().length() > 0) {
						String Testcase_id = dataFormatter.formatCellValue(singleRow.getCell(TestCaseID_Index)).trim();
						if (!Testcase_id.equalsIgnoreCase("NA")) {
							cell = singleRow.getCell(TestRacfData_index);
							if (dataFormatter.formatCellValue(cell).trim().isEmpty()
									|| dataFormatter.formatCellValue(cell).trim() == null
									|| cell.getCellType() == Cell.CELL_TYPE_BLANK) {
								cell = singleRow.getCell(TestEnglishData_index);
							}
							int cellvalue_data = 0;
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								Mapobj.put(Testcase_id, dataFormatter.formatCellValue(cell).trim());
							} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
								Mapobj.put(Testcase_id, "");
							} else {
								Mapobj.put(Testcase_id, "");
							}
						}
					}
				}
			}
			return Mapobj;
		} catch (Exception e) {
			LogFactory.info(e.getMessage());
			return null;
		}

	}

	public static void writeRow(String fileName, List<HashMap<String, String>> rowList)
			throws IOException, InvalidFormatException {
		File oFile = new File(fileName);
		FileInputStream input = new FileInputStream(oFile);
		book = new XSSFWorkbook(input);
		dataSheet = book.getSheet("TestResults");
		int rowNum = dataSheet.getLastRowNum();
		String cellVal;
		CellStyle style = book.createCellStyle();// *
		Font font = book.createFont();// *

		for (int i = 0; i < rowList.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map = rowList.get(i);
			int cellNumber = 0;
			XSSFRow row = dataSheet.createRow(++rowNum);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				cellVal = entry.getValue();
				// System.out.println("Current Cell: " + cellNumber);
				XSSFCell cell = row.createCell(cellNumber);
				// System.out.println(cell + "-" + cellNumber);

				if (cellVal instanceof String) {
					cell.setCellValue((String) cellVal);
				} else {
					cell.setCellValue("String");
				}

				// dataSheet.autoSizeColumn(cellNumber);
				dataSheet.setColumnWidth(0, 5000);
				dataSheet.setColumnWidth(1, 13000);
				dataSheet.setColumnWidth(2, 5000);
				dataSheet.setColumnWidth(3, 13000);
				dataSheet.setColumnWidth(4, 13000);
				dataSheet.setColumnWidth(5, 5000);
				style.setWrapText(true);
				cell.setCellStyle(style);

				cellNumber++;
			}
		}
		input.close();
		FileOutputStream fos = new FileOutputStream(oFile);
		book.write(fos);
		book.close();
		fos.close();
	}

	/**
	 * 
	 * @param fileName
	 *            - expects absolute path of the file, to which data is to be
	 *            written
	 * @param headerList
	 *            - expects all the column names in the file, as a List of String
	 * @return name of the file with absolute path, in which the header is written.
	 */

	public static String writeHeader(String fileName, List<String> headerList) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(fileName));
		XSSFWorkbook book = new XSSFWorkbook();

		XSSFSheet sheet = book.createSheet("TestResults");
		Row row = sheet.createRow(0);

		int cellNumber = 0;
		Font font = book.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
		CellStyle cellStyle1 = book.createCellStyle();

		for (String header : headerList) {
			Cell cell = row.createCell(cellNumber++);
			cellStyle1.setFont(font);
			cellStyle1.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			cell.setCellStyle(cellStyle1);
			cell.setCellValue(header);
			sheet.autoSizeColumn(cellNumber);

		}
		book.write(fos);
		book.close();
		fos.close();
		return fileName;
	}

	public static List<LinkedHashMap> getWCMContentDetails(String ContentType) {
		LinkedHashMap rowdata = null;
		List<LinkedHashMap> rowDataList = new ArrayList<LinkedHashMap>();
		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String value = (String) rowdata.get("ContentType");
				if (value.equalsIgnoreCase(ContentType)) {
					rowDataList.add(rowdata);

				}
			}

		}

		return rowDataList;
	}

	public static List<LinkedHashMap> getWCMSiteAreaDetails(String portletlinks) {
		LinkedHashMap rowdata = null;
		List<LinkedHashMap> rowDataList = new ArrayList<LinkedHashMap>();

		LogFactory.info("Reading WCM portlets links page content for " + strUserRACFID);

		for (int i = 0; i < userWCMData.size(); i++) {

			rowdata = userWCMData.get(i);
			// System.out.println("rowdata"+rowdata);
			String id_value = (String) rowdata.get("Dealer_User_id");
			// String contentType = (String) rowdata.get("ContentType");
			// System.out.println(id_value);

			if (id_value.equalsIgnoreCase(strUserRACFID)) {

				String portletLinkArea = (String) rowdata.get("3rdLevelIndexPage");
				String contenttype = (String) rowdata.get("ContentType");
				String depertment = (String) rowdata.get("DepartmentName");
				String secondndlevel = (String) rowdata.get("2ndLevel");

				if (portletLinkArea.equalsIgnoreCase(portletlinks) && !contenttype.equalsIgnoreCase("AT_Alerts")
						&& !contenttype.equalsIgnoreCase("AT_Announcement") && !depertment.equalsIgnoreCase("NA")
						&& !secondndlevel.equalsIgnoreCase("NA") && !contenttype.equalsIgnoreCase("AT-Index page")
						&& !contenttype.equalsIgnoreCase("AT-ChildIndex Page")
						&& !contenttype.equalsIgnoreCase("AT-GrandChild Index Page")) {
					// System.out.println(rowdata);
					rowDataList.add(rowdata);
					// break;
				}

			}

		}
		return rowDataList;
	}

	public static void setCredentials() throws Throwable {
		LinkedHashMap<String, String> Mapobj = new LinkedHashMap<String, String>();
		try {

			for (int j = 0; j <= dataAdditionalTCSheet.getRow(0).getLastCellNum(); j++) {
				for (int i = 0; i <= 3; i++) {

					String Attribute_name = (!(dataFormatter
							.formatCellValue(dataAdditionalTCSheet.getRow(i).getCell(j)) == null
							|| dataFormatter.formatCellValue(dataAdditionalTCSheet.getRow(i).getCell(j)).isEmpty()
							|| dataAdditionalTCSheet.getRow(i).getCell(j).getCellType() == cell.CELL_TYPE_BLANK))
									? dataFormatter.formatCellValue(dataAdditionalTCSheet.getRow(i).getCell(j))
									: "";
					if (!Attribute_name.toString().trim().isEmpty() && Attribute_name != null) {
						Mapobj.put(Attribute_name,
								dataFormatter.formatCellValue(dataAdditionalTCSheet.getRow(i).getCell(j + 1)).trim());
					}
				}
				j = j + 1;
			}
			strUserName = Mapobj.get("Username").toString().trim();
			strPassword = Mapobj.get("Password").toString().trim();
			URL = Mapobj.get("URL").toString().trim();
			strBrowserType = Mapobj.get("Browser").toString().trim();
			String screenshotPath = Mapobj.get("ScreenshotPath").toString().trim();
			strScreenshotDir = screenshotPath.isEmpty() ? strWorkingDir : screenshotPath;
			ENABLE_SCREENSHOT = Mapobj.get("EnableScreenshot").isEmpty() ? ENABLE_SCREENSHOT = "OFF"
					: Mapobj.get("EnableScreenshot");
			dateformat = Mapobj.get("Date Format").toString().trim();
			appVersion = Mapobj.get("Version").toString().trim();
			System.out.println(Mapobj);

		} catch (Exception e) {
			e.getMessage();

		}
		// TODO: handle exception
	}

	public static List<LinkedHashMap> readDealerInfoData() throws Exception {
		try {

			XSSFCell cell;
			int totalRows = dataDealerInfoSheet.getLastRowNum();
			int startRow = 0;
			int totalCol = 0;
			String HeadervalueList = "";
			String useridvalue = "";
			String Coutryvalue = "";
			String flagDealerPrinciple = "";
			String flagExecutionControl = "";
			String flagAddtionalTestcases = "";
			String flagDealerTypeContorl = "";
			String flagMultilingual = "";

			int flagExecutionControlIndex = 0;
			int flagAddtionalTestcasesIndex = 0;
			int flagDealerPrincipleIndex = 0;
			int flagDealerTypeIndex = 0;
			int flagMultilingualIndex = 0;

			List<LinkedHashMap> dealerRowDataList = null;
			// System.out.println(totalRows);

			ArrayList<String> ListAttributeName = new ArrayList<String>();
			ArrayList<String> dealerHeaderList = new ArrayList<String>();
			String headerList = "";

			for (int i = 1; i < totalRows; i++) {

				String cellvalue = dataFormatter.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(0)).trim();

				if (cellvalue.equalsIgnoreCase("Userid")) {
					startRow = i;
					// System.out.println(i);
					totalCol = dataDealerInfoSheet.getRow(startRow).getLastCellNum();

					for (int j = 0; j < totalCol; j++) {

						String dealerHeaderName = dataFormatter
								.formatCellValue(dataDealerInfoSheet.getRow(startRow).getCell(j)).trim();

						switch (dealerHeaderName) {

						case "Construction":
							dealerHeaderList.add("Construction" + "/" + dealerHeaderName);
							break;

						case "Utility":
							dealerHeaderList.add("Construction" + "/" + dealerHeaderName);
							break;

						case "Forestry":
							dealerHeaderList.add("Forestry" + "/" + dealerHeaderName);
							break;

						case "CWP":
							dealerHeaderList.add("CWP" + "/" + dealerHeaderName);
							break;

						case "Hitachi":
							dealerHeaderList.add("Hitachi" + "/" + dealerHeaderName);
							break;

						case "Mining":
							dealerHeaderList.add("Hitachi" + "/" + dealerHeaderName);
							break;

						case "Ag Equipment":
							dealerHeaderList.add("Agriculture" + "/" + dealerHeaderName);
							break;

						case "Sprayers & Nutrient Applicators":
							dealerHeaderList.add("Agriculture" + "/" + dealerHeaderName);
							break;

						case "Scraper and Scraper Tractor":
							dealerHeaderList.add("Agriculture" + "/" + dealerHeaderName);
							break;

						case "Forage Harvester":
							dealerHeaderList.add("Agriculture" + "/" + dealerHeaderName);
							break;

						case "Commercial":
							dealerHeaderList.add("Turf" + "/" + dealerHeaderName);
							break;

						case "Residential":
							dealerHeaderList.add("Turf" + "/" + dealerHeaderName);
							break;

						case "Homeowner":
							dealerHeaderList.add("Homeowner" + "/" + dealerHeaderName);
							break;

						case "JDPS Distributor":
							dealerHeaderList.add("JDPS" + "/" + dealerHeaderName);
							break;

						case "Direct OEM":
							dealerHeaderList.add("JDPS" + "/" + dealerHeaderName);
							break;

						case "Dealer Principle":
							flagDealerPrincipleIndex = j;
							break;
						case "Dealer Type (Main/Sub)":
							flagDealerTypeIndex = j;
							break;
						case "Multilingual":
							flagMultilingualIndex = j;
							break;
						case "Execution Control":
							flagExecutionControlIndex = j;
							break;
						case "Additional Testcases":
							flagAddtionalTestcasesIndex = j;
							break;
						default:
							dealerHeaderList.add(dealerHeaderName);
						}

					}
				} else {
					break;
				}
			}
			dealerRowDataList = new ArrayList<LinkedHashMap>();
			for (int i = 2; i <= totalRows; i++) {
				HeadervalueList = "";
				LinkedHashMap<String, String> RowValuedata = new LinkedHashMap<String, String>();

				useridvalue = dataFormatter.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(0)).trim();
				Coutryvalue = dataFormatter.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(2)).trim();
				flagDealerPrinciple = dataFormatter
						.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(flagDealerPrincipleIndex)).trim();
				flagDealerTypeContorl = dataFormatter
						.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(flagDealerTypeIndex)).trim();
				flagMultilingual = dataFormatter
						.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(flagMultilingualIndex)).trim();
				flagExecutionControl = dataFormatter
						.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(flagExecutionControlIndex)).trim();
				flagAddtionalTestcases = dataFormatter
						.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(flagAddtionalTestcasesIndex)).trim();
				for (int j = 2; j < totalCol; j++) {
					cell = dataDealerInfoSheet.getRow(i).getCell(j);
					if (cell.getCellTypeEnum() != CellType.BLANK && cell.getCellTypeEnum() != CellType._NONE) {
						String dealerRowValue = dataFormatter.formatCellValue(dataDealerInfoSheet.getRow(i).getCell(j))
								.trim();
						if (dealerRowValue.equalsIgnoreCase("x")) {
							if (HeadervalueList == "") {
								HeadervalueList = dealerHeaderList.get(j);
							} else {
								HeadervalueList = HeadervalueList + "," + dealerHeaderList.get(j);
							}
						}
					}
				}
				if (useridvalue.length() > 0) {
					RowValuedata.put("Dealer_User_id", useridvalue);
					RowValuedata.put("Dealer_Country", Coutryvalue);
					RowValuedata.put("Execution Control", flagExecutionControl);
					RowValuedata.put("Addtional Testcases", flagAddtionalTestcases);
					RowValuedata.put("Dealer Principle", flagDealerPrinciple);
					RowValuedata.put("Multilingual", flagMultilingual);
					RowValuedata.put("Dealer Type (Main/Sub)", flagDealerTypeContorl);
					RowValuedata.put("Dealer_ProductType", HeadervalueList);
					RowValuedata.put("dealerProducts", headerList);
					dealerRowDataList.add(RowValuedata);
				}
			}
			// System.out.println(dealerRowDataList);
			return dealerRowDataList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static HashSet<String> uniqueRACFId() throws Throwable {
		HashSet<String> userID = new LinkedHashSet();
		for (int i = 0; i < dealerinfo.size(); i++) {
			String id = (String) dealerinfo.get(i).get("Dealer_User_id");
			String flagExecutionControl = (String) dealerinfo.get(i).get("Execution Control");
			if (!flagExecutionControl.equalsIgnoreCase("N")) {
				userID.add(id);
			}
		}
		return userID;

	}

	public static void getDealersInfoFlag() throws Throwable {
		flagDealerPrinciple = "N";
		flagAddtionalTestcases = "N";
		flagDealerType = "NA";
		flagMultilingual = "NA";

		for (int i = 0; i < dealerinfo.size(); i++) {

			String racfID = (String) dealerinfo.get(i).get("Dealer_User_id").toString().trim();
			if (racfID.equalsIgnoreCase(strUserRACFID.toString().trim())) {
				flagDealerPrinciple = (String) dealerinfo.get(i).get("Dealer Principle").toString().trim();
				flagAddtionalTestcases = (String) dealerinfo.get(i).get("Addtional Testcases").toString().trim();
				flagDealerType = (String) dealerinfo.get(i).get("Dealer Type (Main/Sub)").toString().trim();
				flagMultilingual = (String) dealerinfo.get(i).get("Multilingual").toString().trim();
				LogFactory.info("DealerPrinciple : " + flagDealerPrinciple + "   Multilingual Flag :  "
						+ flagMultilingual + "   AddtionalTestcases : " + flagAddtionalTestcases + "    DealerType :   "
						+ flagDealerType);
				break;
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void readWCMContentData() throws Exception {
		try {
			ArrayList<String> ListAttributeName = new ArrayList<String>();
			FileInputStream inputStream = new FileInputStream(new File(strDataPath));
			book = new XSSFWorkbook(inputStream);

			dataDealerInfoSheet = book.getSheet("Dealer Info");
			dataWCMContentSheet = book.getSheet("WCM Content");
			dataAdditionalTCSheet = book.getSheet("Additional Testcases");
			dataTranslationsSheet = book.getSheet("Translations");
			mappingOfRacfGroups = book.getSheet("RacfGroup");

			int Row_count = dataWCMContentSheet.getLastRowNum();
			int colon_count = dataWCMContentSheet.getRow(0).getLastCellNum();
			String AttributeName = "";
			String AttributeValue = "";
			String Dealer_User_value = "";
			String Coutryvalue = "";
			String Dealer_ProductType = "";
			String Dealer_Type = "";

			for (int j = 0; j < colon_count; j++) {
				AttributeName = dataFormatter.formatCellValue(dataWCMContentSheet.getRow(0).getCell(j)).trim();
				// System.out.println(AttributeName);
				ListAttributeName.add(AttributeName);
			}
			dealerinfo = readDealerInfoData();
			// System.out.println(dealerinfo.size());
			wcmExceldata = new ArrayList<LinkedHashMap>();
			for (int k = 0; k < dealerinfo.size(); k++) {
				Dealer_User_value = (String) dealerinfo.get(k).get("Dealer_User_id").toString().trim();
				Coutryvalue = (String) dealerinfo.get(k).get("Dealer_Country").toString().trim();
				Dealer_ProductType = (String) dealerinfo.get(k).get("Dealer_ProductType").toString().trim();
				Dealer_Type = (String) dealerinfo.get(k).get("Dealer Type (Main/Sub)").toString().trim();
				for (int i = 1; i <= Row_count; i++) {
					LinkedHashMap<String, String> RowValuedata = new LinkedHashMap<String, String>();
					RowValuedata.put("Dealer_User_id", Dealer_User_value);
					RowValuedata.put("Dealer_Country", Coutryvalue);
					RowValuedata.put("Dealer_ProductType", Dealer_ProductType);
					RowValuedata.put("Dealer_Type", Dealer_Type);
					for (int j = 0; j < colon_count; j++) {
						AttributeName = ListAttributeName.get(j);
						AttributeValue = dataFormatter.formatCellValue(dataWCMContentSheet.getRow(i).getCell(j)).trim();
						RowValuedata.put(AttributeName, AttributeValue);
						// System.out.println(RowValuedata);
					}

					if (RowValuedata.get("EXECUTE").equalsIgnoreCase("Y") || RowValuedata.get("EXECUTE").equals("")
							|| RowValuedata.get("EXECUTE").isEmpty()) {
						wcmExceldata.add(RowValuedata);
						// System.out.println(RowValuedata);
					}

					// System.out.println(wcmExceldata);
				}
			}
			// System.out.println(wcmExceldata);
			BaseClass.racfGroupMapping = getRacfGroupMapping();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	public static void getUserWCMContent() throws Throwable {

		// String strUserRACFID = "XCI8711";
		userWCMData = new ArrayList<LinkedHashMap>();
		for (int i = 0; i < wcmExceldata.size(); i++) {
			String racfID = (String) wcmExceldata.get(i).get("Dealer_User_id");
			if (racfID.equalsIgnoreCase(strUserRACFID.toString().trim())) {
				userWCMData.add(wcmExceldata.get(i));
			}
		}
		// System.out.println(userWCMData);
	}

	public static void writeExcel() {

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
			Date date = new Date();
			String filename = strDataOutputPath + dateFormat.format(date) + ".xlsx";
			ExcelFactory.writeHeader(filename, BaseClass.headerList);
			ExcelFactory.writeRow(filename, BaseClass.finalResultforExcel);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @SuppressWarnings("rawtypes") public static List<LinkedHashMap>
	 * getUserWcmDetailsAfterFilteringCountryAndProduct(String ContentType, String
	 * IndexPage, String ChildIndexPage, String GrandChildIndexPage) { LinkedHashMap
	 * rowdata = null; List<LinkedHashMap> rowDataList = new
	 * ArrayList<LinkedHashMap>(); for (int i = 0; i < userWCMData.size(); i++) {
	 * rowdata = userWCMData.get(i); String id_value = (String)
	 * rowdata.get("Dealer_User_id"); if (id_value.equalsIgnoreCase(strUserRACFID))
	 * { String value = (String) rowdata.get("ContentType").toString().trim();
	 * String dealerCountry = (String) rowdata.get("Dealer_Country"); String
	 * delarProdType = (String) rowdata.get("Dealer_ProductType"); String wcmcountry
	 * = (String) rowdata.get("Country"); String wcmdProdType = (String)
	 * rowdata.get("ProductType");
	 * 
	 * boolean countryFlag=GenericFactory.userAndWCMCountryComparison(dealerCountry,
	 * wcmcountry); boolean
	 * productFlag=GenericFactory.userAndWCMProductTypeComparison(delarProdType,
	 * wcmdProdType);
	 * 
	 * 
	 * if ((countryFlag && productFlag) && ((value.equalsIgnoreCase(ContentType) ||
	 * value.equalsIgnoreCase(IndexPage) || value.equals(ChildIndexPage) ||
	 * value.equalsIgnoreCase(GrandChildIndexPage)))) { rowDataList.add(rowdata);
	 * 
	 * } } } return rowDataList; }
	 */

	@SuppressWarnings("rawtypes")
	public static List<LinkedHashMap> getUserWcmDetailsAfterFilteringCountryAndProduct(String ContentType,
			String IndexPage, String ChildIndexPage, String GrandChildIndexPage) throws Throwable {
		LinkedHashMap rowdata = null;
		List<LinkedHashMap> rowDataList = new ArrayList<LinkedHashMap>();
		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String value = (String) rowdata.get("ContentType").toString().trim();
				String dealerCountry = (String) rowdata.get("Dealer_Country");
				String delarProdType = (String) rowdata.get("Dealer_ProductType");
				String wcmcountry = (String) rowdata.get("Country");
				String wcmdProdType = (String) rowdata.get("ProductType");
				String strRACFGroups = (String) rowdata.get("RACFGroups");
				String strdealerType = (String) rowdata.get("DealerType (Main/Sub)");

				boolean racfFlag = GenericFactory.verifyRacfGroupMatched(strRACFGroups);
				boolean wcmDealerType = GenericFactory.verifyDealerType(strdealerType);

				boolean countryFlag = GenericFactory.userAndWCMCountryComparison(dealerCountry, wcmcountry);
				boolean productFlag = GenericFactory.userAndWCMProductTypeComparison(delarProdType, wcmdProdType);

				if ((countryFlag && productFlag) && (racfFlag == true) && (wcmDealerType == true)
						&& ((value.equalsIgnoreCase(ContentType) || value.equalsIgnoreCase(IndexPage)
								|| value.equals(ChildIndexPage) || value.equalsIgnoreCase(GrandChildIndexPage)))) {
					rowDataList.add(rowdata);

				}
			}
		}
		return rowDataList;
	}

	/*
	 * @SuppressWarnings("rawtypes") public static List<LinkedHashMap>
	 * getUserWcmDetailsAfterFilteringCountryAndProduct(String ContentType) {
	 * LinkedHashMap rowdata = null; List<LinkedHashMap> rowDataList = new
	 * ArrayList<LinkedHashMap>();
	 * 
	 * for (int i = 0; i < userWCMData.size(); i++) { rowdata = userWCMData.get(i);
	 * String id_value = (String) rowdata.get("Dealer_User_id"); if
	 * (id_value.equalsIgnoreCase(strUserRACFID)) { String value = (String)
	 * rowdata.get("ContentType"); String dealerCountry = (String)
	 * rowdata.get("Dealer_Country"); String delarProdType = (String)
	 * rowdata.get("Dealer_ProductType"); String wcmcountry = (String)
	 * rowdata.get("Country"); String wcmdProdType = (String)
	 * rowdata.get("ProductType"); if
	 * (GenericFactory.userAndWCMCountryComparison(dealerCountry, wcmcountry) &&
	 * GenericFactory.userAndWCMProductTypeComparison(delarProdType, wcmdProdType)
	 * && value.equalsIgnoreCase(ContentType)) { rowDataList.add(rowdata); } } }
	 * return rowDataList; }
	 */

	@SuppressWarnings("rawtypes")
	public static List<LinkedHashMap> getUserWcmDetailsAfterFilteringCountryAndProduct(String ContentType) {
		LinkedHashMap rowdata = null;
		List<LinkedHashMap> rowDataList = new ArrayList<LinkedHashMap>();
		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String value = (String) rowdata.get("ContentType");
				String dealerCountry = (String) rowdata.get("Dealer_Country");
				String delarProdType = (String) rowdata.get("Dealer_ProductType");
				String wcmcountry = (String) rowdata.get("Country");
				String wcmdProdType = (String) rowdata.get("ProductType");

				boolean countryFlag = GenericFactory.userAndWCMCountryComparison(dealerCountry, wcmcountry);
				boolean productFlag = GenericFactory.userAndWCMProductTypeComparison(delarProdType, wcmdProdType);

				if ((countryFlag && productFlag) && value.equalsIgnoreCase(ContentType)) {
					rowDataList.add(rowdata);
					// System.out.println(rowdata.get("Country"));
				}
			}
		}
		return rowDataList;
	}

	@SuppressWarnings("rawtypes")
	public static List<LinkedHashMap> getUserWCMTitlesToMarkFavourite() {
		LinkedHashMap rowdata = null;
		List<LinkedHashMap> rowDataList = new ArrayList<LinkedHashMap>();

		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String indexContentType = (String) rowdata.get("IndexPageContentType");
				String contentType = (String) rowdata.get("ContentType");
				String dealerCountry = (String) rowdata.get("Dealer_Country");
				String delarProdType = (String) rowdata.get("Dealer_ProductType");
				String wcmcountry = (String) rowdata.get("Country");
				String wcmdProdType = (String) rowdata.get("ProductType");
				String thirdLvlFolder = (String) rowdata.get("3rdLevelFolder");

				if (GenericFactory.userAndWCMCountryComparison(dealerCountry, wcmcountry)
						&& GenericFactory.userAndWCMProductTypeComparison(delarProdType, wcmdProdType)
						&& contentType.equalsIgnoreCase("AT-Link") && indexContentType.equalsIgnoreCase("NA")
						&& thirdLvlFolder.equalsIgnoreCase("NA")) {

					rowDataList.add(rowdata);
				}
			}
		}
		// System.out.println(rowDataList);
		return rowDataList;
	}

	public static LinkedHashMap getWCMByTCID(String TCID) {
		LinkedHashMap rowdata = null;
		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String value = (String) rowdata.get("Test Case ID");
				if (value.equalsIgnoreCase(TCID)) {
					System.out.println("wcm data by tcid" + rowdata);
					return rowdata;
				}
			}
		}
		return rowdata;
	}

	public static LinkedHashMap<String, HashMap<String, String>> translationLookUp() throws IOException, Exception {
		try {
			XSSFRow singleRow;
			XSSFRow singleHeaderRow;
			String strEnglishKey = "";
			String strTransKey = "";
			LinkedHashMap<String, HashMap<String, String>> mapTranslationSheet = new LinkedHashMap<String, HashMap<String, String>>();
			LinkedHashMap<String, String> mapTranslationLan = null;
			int totalRows = dataTranslationsSheet.getLastRowNum();
			int totalCol = dataTranslationsSheet.getRow(4).getLastCellNum();
			for (int j = 2; j < totalRows; j++) {
				singleRow = dataTranslationsSheet.getRow(j);
				singleHeaderRow = dataTranslationsSheet.getRow(1);
				if ((singleRow.getCell(0).getCellType() == Cell.CELL_TYPE_STRING)) {
					strEnglishKey = dataFormatter.formatCellValue(singleRow.getCell(0));
					mapTranslationLan = new LinkedHashMap<String, String>();
					for (int i = 1; i < totalCol; i++) {
						strTransKey = dataFormatter.formatCellValue(singleHeaderRow.getCell(i));
						String strTransValue = dataFormatter.formatCellValue(singleRow.getCell(i));
						if (!strTransValue.equalsIgnoreCase("X")) {
							mapTranslationLan.put(strTransKey.trim(), strTransValue.trim());
							mapTranslationSheet.put(strEnglishKey.trim(), mapTranslationLan);
						} else {
							mapTranslationLan.put(strTransKey.trim(), strEnglishKey.trim());
							mapTranslationSheet.put(strEnglishKey.trim(), mapTranslationLan);
						}
					}
				}
			}
			System.out.println(mapTranslationSheet);
			return mapTranslationSheet;
		} catch (Exception e) {
			LogFactory.info(e.getMessage());
			return null;
		}

	}

	public static Map<String, List<String>> employeeGroups() throws Throwable {
		Map<String, List<String>> employeeGroupMap = new LinkedHashMap<>();
		try {
			@SuppressWarnings("resource")
			FileInputStream inputStream = new FileInputStream(new File(strDataPath));

			book = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = book.getSheet("EmployeeGroup");
			Iterator<Row> sheetIterator = sheet.iterator();
			boolean isFirstRow = true;
			List<String> keyList = new ArrayList<>();
			while (sheetIterator.hasNext()) {
				Row row = sheetIterator.next();
				int totalCells = 0;
				if (isFirstRow) {
					totalCells = row.getPhysicalNumberOfCells();
					for (int cellIndex = 0; cellIndex < totalCells; cellIndex++) {
						Cell cell = row.getCell(cellIndex);
						String cellKey = cell.getStringCellValue();
						employeeGroupMap.put(cellKey, new ArrayList<String>());
						keyList.add(cellKey);
					}
					isFirstRow = false;
				} else {
					totalCells = row.getPhysicalNumberOfCells();
					for (int cellIndex = 0; cellIndex < totalCells; cellIndex++) {
						Cell cell = row.getCell(cellIndex);
						String cellValue = cell.getStringCellValue();
						List<String> securityGroups = employeeGroupMap.get(keyList.get(cellIndex));
						securityGroups.add(cellValue);
					}
				}

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return employeeGroupMap;
	}

	public static Map<String, List<String>> getRacfGroupMapping() throws IOException, Exception {
		Map<String, List<String>> mapRackfGroup = new HashMap<String, List<String>>();
		try {

			mappingOfRacfGroups = book.getSheet("RacfGroup");

			int colon_count = mappingOfRacfGroups.getRow(0).getLastCellNum();
			System.out.println(colon_count);
			int Row_count = 0;
			List<String> value = new ArrayList<String>();
			String key = "";
			for (int i = 0; i < colon_count; i++) {
				Row_count = mappingOfRacfGroups.getLastRowNum();
				for (int j = 1; j <= Row_count; j++) {
					String temp = dataFormatter.formatCellValue(mappingOfRacfGroups.getRow(j).getCell(i));
					key = dataFormatter.formatCellValue(mappingOfRacfGroups.getRow(0).getCell(i));
					if (!temp.isEmpty()) {
						value.add(dataFormatter.formatCellValue(mappingOfRacfGroups.getRow(j).getCell(i)));
					} else {
						break;
					}
				}
				mapRackfGroup.put(key, value);
			}
			System.out.println(mapRackfGroup);
		} catch (Exception e) {
			e.getMessage();
			// TODO: handle exception
		}
		return mapRackfGroup;
	}

	@SuppressWarnings("rawtypes")
	public static List<LinkedHashMap> getUserWcmDetailsAfterFilteringCountryAndProductAndTemplate(String ContentType,
			String IndexPage, String ChildIndexPage, String GrandChildIndexPage) throws Throwable {
		LinkedHashMap rowdata = null;
		String templateValue = "";
		List<LinkedHashMap> rowDataList = new ArrayList<LinkedHashMap>();
		for (int i = 0; i < userWCMData.size(); i++) {
			rowdata = userWCMData.get(i);
			String id_value = (String) rowdata.get("Dealer_User_id");
			if (id_value.equalsIgnoreCase(strUserRACFID)) {
				String testcaseID = (String) rowdata.get("Test Case ID");
				String value = (String) rowdata.get("ContentType");
				String dealerCountry = (String) rowdata.get("Dealer_Country");
				String delarProdType = (String) rowdata.get("Dealer_ProductType");
				String wcmcountry = (String) rowdata.get("Country");
				String wcmdProdType = (String) rowdata.get("ProductType");
				String index_Page_Template = (String) rowdata.get("Index_Page_Template");
				String index_Page_Template_Label = (String) rowdata.get("Index_Page_Template_Label");
				String strRACFGroups = (String) rowdata.get("RACFGroups");
				String strdealerType = (String) rowdata.get("DealerType (Main/Sub)");

				boolean racfFlag = GenericFactory.verifyRacfGroupMatched(strRACFGroups);
				boolean wcmDealerType = GenericFactory.verifyDealerType(strdealerType);

				boolean countryFlag = GenericFactory.userAndWCMCountryComparison(dealerCountry, wcmcountry);
				boolean productFlag = GenericFactory.userAndWCMProductTypeComparison(delarProdType, wcmdProdType);

				List<String> allIndex_Page_Template = new ArrayList<String>();
				allIndex_Page_Template.add(index_Page_Template);
				if (value.equalsIgnoreCase("SAT-Table Index Page")) {
					templateValue = userWCMData.get(i).get("Index_Page_Template").toString();
					countryFlag = true;
					productFlag = true;
				}
				if ((countryFlag && productFlag) && (racfFlag == true) && (wcmDealerType == true)
						&& ((value.equalsIgnoreCase(ContentType) || value.equalsIgnoreCase("AT-Child Index Page")
								|| value.equalsIgnoreCase("AT-GrandChild Index Page")
								|| value.equalsIgnoreCase(IndexPage) || value.equals(ChildIndexPage)
								|| value.equalsIgnoreCase(GrandChildIndexPage))
								&& (!index_Page_Template.equalsIgnoreCase("NA")
										|| !index_Page_Template.equalsIgnoreCase("")))) {
					if (templateValue.equalsIgnoreCase(index_Page_Template)) {

						rowDataList.add(rowdata);
						System.out.println(rowdata.get("Country"));
					}
				}
			}
		}
		return rowDataList;
	}

	public static void getUserWCMContentnew(String RACFID) throws Throwable {

		// String strUserRACFID = "XCI8711";
		userFavWCMData = new ArrayList<LinkedHashMap>();
		for (int i = 0; i < wcmExceldata.size(); i++) {
			String racfID = (String) wcmExceldata.get(i).get("Dealer_User_id");
			if (racfID.equalsIgnoreCase(RACFID.toString().trim())) {

				userFavWCMData.add(wcmExceldata.get(i));
				break;
			}
		}
	}

	public static List<String> getRunningStatus() {
		List<String> runningClasses = new ArrayList<String>();
		runningStatus = book.getSheet("TestCaseToExecute");
		String key = "";
		String value = "";
		int Row_count = 0;
		Row_count = runningStatus.getLastRowNum();
		for (int i = 1; i < Row_count + 1; i++) {
			key = dataFormatter.formatCellValue(runningStatus.getRow(i).getCell(22));
			value = dataFormatter.formatCellValue(runningStatus.getRow(i).getCell(23));
			if (value.equals("TRUE")) {
				runningClasses.add(key);
			}
		}
		return runningClasses;
	}
	
	
	public static LinkedHashMap<String, String> translationLookUpEnglish() throws IOException, Exception {
		try {
			XSSFRow singleRow;
			XSSFRow singleHeaderRow;
			String strEnglishKey = "";
			String strTransKey = "";
			LinkedHashMap<String, String> mapTranslationSheet = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> mapTranslationLan = null;
			int totalRows = dataTranslationsSheet.getLastRowNum();
			int rowNumberOfLanguageCode=0;
			 
			 
			String languageCode =analyzerUserMap.get("Language").get(0);
			
			int totalCol = dataTranslationsSheet.getRow(4).getLastCellNum();
			
			for (int j = 2; j < totalRows; j++) {
				singleRow = dataTranslationsSheet.getRow(j);
				singleHeaderRow = dataTranslationsSheet.getRow(1);
				
				for (int s = 0; s < totalCol; s++) 
				{
					strTransKey = dataFormatter.formatCellValue(singleHeaderRow.getCell(s));
					if(strTransKey.equalsIgnoreCase(languageCode))
					{
					 rowNumberOfLanguageCode = s;
					break;
					}
				}
				
				
				if ((singleRow.getCell(0).getCellType() == Cell.CELL_TYPE_STRING)) {
					
					strEnglishKey = dataFormatter.formatCellValue(singleRow.getCell(rowNumberOfLanguageCode));
					mapTranslationLan = new LinkedHashMap<String, String>();
					for (int i = 0; i < totalCol; i++) {
						strTransKey = dataFormatter.formatCellValue(singleHeaderRow.getCell(i));
						String strTransValue = dataFormatter.formatCellValue(singleRow.getCell(i));
						if(strTransKey.equalsIgnoreCase("en_US"))
						{
							
					
						if (!strTransValue.equalsIgnoreCase("X")) {
							 
							mapTranslationSheet.put(strEnglishKey.trim(), strTransValue.trim());
					 
						} else {
						 
							mapTranslationSheet.put(strEnglishKey.trim(), strTransValue.trim());
					 
						}
						}
					}
				}
			}
		 
			return mapTranslationSheet;
		} catch (Exception e) {
			LogFactory.info(e.getMessage());
			return null;
		}

	}
}
