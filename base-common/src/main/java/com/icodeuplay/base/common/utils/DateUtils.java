package com.icodeuplay.base.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {

	@SuppressWarnings("unused")
	private static final DateFormat DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_MILLISECOND;
	private static final DateFormat DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND;
	@SuppressWarnings("unused")
	private static final DateFormat HOUR_MINUTE_SECOND_MILLISECOND;
	@SuppressWarnings("unused")
	private static final DateFormat HOUR24_MINUTE_SECOND_MILLISECOND;
	private static final DateFormat HOUR24_MINUTE_SECOND_MILLISECOND_WITHOUT_SEPARATORS;

	static {
		DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("dd/MM/yyyy h:m:s.S");
		DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("dd/MM/yyyy H:m:s.S");
		HOUR_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("h:m:s.S");
		HOUR24_MINUTE_SECOND_MILLISECOND = new SimpleDateFormat("H:m:s.S");
		HOUR24_MINUTE_SECOND_MILLISECOND_WITHOUT_SEPARATORS = new SimpleDateFormat("ddMMyyyyHmsS");
	}

	public static double getTimeDifferenceInDays(Date initialDate, Date finalDate) {
		if (initialDate == null)
			throw new NullPointerException("The initialDate param cannot be null");
		if (finalDate == null)
			throw new NullPointerException("The finalDate param cannot be null");

		log("Getting the difference between two dates in days");
		log("Param # initialDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(initialDate));
		log("Param # finalDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(finalDate));

		double difference = finalDate.getTime() - initialDate.getTime();
		double days = (difference / 1000 / 60 / 60 / 24);

		return days;
	}

	public static double getTimeDifferenceInHours(Date initialDate, Date finalDate) {
		if (initialDate == null)
			throw new NullPointerException("The initialDate param cannot be null");
		if (finalDate == null)
			throw new NullPointerException("The finalDate param cannot be null");

		log("Getting the difference between two dates in hours");
		log("Param # initialDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(initialDate));
		log("Param # finalDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(finalDate));

		double difference = finalDate.getTime() - initialDate.getTime();
		double hours = (difference / 1000 / 60 / 60);

		return hours;
	}

	public static double getTimeDifferenceInMinutes(Date initialDate, Date finalDate) {
		if (initialDate == null)
			throw new NullPointerException("The initialDate param cannot be null");
		if (finalDate == null)
			throw new NullPointerException("The finalDate param cannot be null");

		log("Getting the difference between two dates in minutes");
		log("Param # initialDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(initialDate));
		log("Param # finalDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(finalDate));

		double difference = finalDate.getTime() - initialDate.getTime();
		double minutes = (difference / 1000 / 60);

		return minutes;
	}

	public static double getTimeDifferenceInSeconds(Date initialDate, Date finalDate) {
		if (initialDate == null)
			throw new NullPointerException("The initialDate param cannot be null");
		if (finalDate == null)
			throw new NullPointerException("The finalDate param cannot be null");

		log("Getting the difference between two dates in seconds");
		log("Param # initialDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(initialDate));
		log("Param # finalDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(finalDate));

		double difference = finalDate.getTime() - initialDate.getTime();
		double seconds = (difference / 1000);

		return seconds;
	}

	public static String getTimeDifferenceAsString(Date initialDate, Date finalDate) {
		if (initialDate == null)
			throw new NullPointerException("The initialDate param cannot be null");
		if (finalDate == null)
			throw new NullPointerException("The finalDate param cannot be null");

		log("Getting the difference between two dates as String");
		log("Param # initialDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(initialDate));
		log("Param # finalDate : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(finalDate));

		double difference = finalDate.getTime() - initialDate.getTime();
		double milliseconds = (difference % 1000);
		double seconds = (difference / 1000) % 60;
		double minutes = (difference / 1000 / 60) % 60;
		double hours = (difference / 1000 / 60 / 60);

		StringBuffer result = new StringBuffer();

		if (hours > 0)
			result.append(new Double(hours).intValue()).append(":");
		if (minutes > 0)
			result.append(new Double(minutes).intValue()).append(":");

		result.append(new Double(seconds).intValue());
		result.append(".");
		result.append(new Double(milliseconds).intValue());

		return result.toString();
	}

	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date toDate(XMLGregorianCalendar date) {
		try {
			return date.toGregorianCalendar().getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getDateAsString(Date date) {
		if (date == null)
			throw new NullPointerException("The initialDate param cannot be null");

		log("Getting the date as String");
		log("Param # date : " + DAY_MONTH_YEAR_HOUR24_MINUTE_SECOND_MILLISECOND.format(date));

		return HOUR24_MINUTE_SECOND_MILLISECOND_WITHOUT_SEPARATORS.format(date);
	}

	private static void log(String msg) {
		// Logger.getLogger(DateUtils.class).debug(msg);
	}
}