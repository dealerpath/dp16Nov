
/* 
 * Project    : DealerPath
 * Script     : DateFactory
 * Author     : Shrishail Baddi
 * Date       : April.03.2018
 * Last Modified On:
 * Modified By :
 */

package com.deere.Helpers;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateFactory {

	public static String formate(Date date) {
		if (date == null)
			return "";
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", date);

	}

	/**
	 * 2010-09-10 21:08:17
	 */
	public static String formateYMDHMS(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", date);
	}

	/**
	 * 2010-09-10
	 */
	public static String formateYMD(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY-%1$tm-%1$td", date);
	}

	public static String captureYear(Date date) {
		if (date == null) {
			return "";
		}
		return DateFactory.formateYMD(date).split("-")[0];
	}

	/**
	 * :09-10
	 */
	public static String formateMD(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tMM-%1$td", date);
	}


	public static boolean isThisDateValid(String dateToValidate, String dateFromat) {

	try {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			 System.out.println(date);

		} catch (Exception e) {

			LogFactory.info("Verify the date" + dateFromat  + "Format is valid ot not " + e.getMessage());
			return false;
		}

		return true;
	}
	
	
	public static class DescendingDateComparator implements Comparator<Date> {
		  public DescendingDateComparator() {
		  }
		  @Override
		  public int compare(Date o1, Date o2) {
		   long time1 = o1.getTime();
		   long time2 = o2.getTime();
		   if(time1 < time2) {
		    return 1;
		   } else if(time1 > time2) {
		    return -1;
		   }
		   return 0;
		  }
		 }
		 
		 public static class AscendingDateComparator implements Comparator<Date> {
		  public AscendingDateComparator() {

		  }
		  @Override
		  public int compare(Date o1, Date o2) {
		   long time1 = o1.getTime();
		   long time2 = o2.getTime();
		   if(time1 < time2) {
		    return -1;
		   } else if(time1 > time2) {
		    return 1;
		   }
		   return 0;
		  }
		 }

}
