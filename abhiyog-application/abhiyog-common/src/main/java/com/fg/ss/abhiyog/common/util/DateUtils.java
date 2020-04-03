package com.fg.ss.abhiyog.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtils {

	public static Date getDBFormatedDate(String date) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		Date covertDate = null;

		try {

			covertDate = formatter.parse(date);
			System.out.println(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		formatter = new SimpleDateFormat("yyyy-MM-dd");

		String strDate = formatter.format(covertDate);

		System.out.println("Converted Date=" + strDate);

		return formatter.parse(strDate);

	}
	
	public static LocalDate getDBFormatedDte(String date) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date covertDate = null;

		try {

			covertDate = formatter.parse(date);
			System.out.println(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		formatter = new SimpleDateFormat("yyyy-MM-dd");

		String strDate = formatter.format(covertDate);

		System.out.println("Converted Date=" + strDate);
		
		LocalDate localDate = LocalDate.parse(strDate);
		
		System.out.println("LocalDate** " +localDate);

		return localDate;

	}
	
	public static LocalDate getDBFormatDate(String date) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");

		Date covertDate = null;

		try {

			covertDate = formatter.parse(date);
			System.out.println(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		formatter = new SimpleDateFormat("yyyy-MM-dd");

		String strDate = formatter.format(covertDate);

		System.out.println("Converted Date=" + strDate);
		
		LocalDate localDate = LocalDate.parse(strDate);
		
		System.out.println("LocalDate** " +localDate);

		return localDate;

	}
}
