package es.arr.flightsearch.common;

import java.lang.Math;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class DateUtils {
	
		// Initialize calendar with today's date
		private static Calendar cal = GregorianCalendar.getInstance();
		
		public static int dateSubtraction(Date d1, Date d2){
			
			double differenceInMs = d1.getTime() - d2.getTime();
			
			double differenceInDays = Math.floor(differenceInMs / (1000 * 60 * 60 * 24));

			return (int) differenceInDays;
		}
		
		
		public static int daysToDate(Date d1){
			Date today = new Date();
			
			return dateSubtraction(d1,new Date()); 
		}
		
		public static Date parseDate(String date, String format){
			SimpleDateFormat formateador = new SimpleDateFormat(format);
			Date parsedDate = null;
			
			try{
				parsedDate = formateador.parse(date);
			}catch (Exception e){
			}
			
			return parsedDate;
		}
		
		public static Date getTodaysDate(){
			
			cal = GregorianCalendar.getInstance();// set todays date in calendar and return date
			
			return cal.getTime();
			
		}
	
	
		public static  Date getFutureDate(int days){
			cal = GregorianCalendar.getInstance();// set todays date
			cal.add(Calendar.DAY_OF_YEAR, days);
			
			return cal.getTime();
			
		}
}
