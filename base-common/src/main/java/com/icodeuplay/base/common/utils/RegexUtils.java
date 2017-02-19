package com.icodeuplay.base.common.utils;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils implements Serializable {

	private static final long serialVersionUID = -4442058730001162076L;

	/**
	 * Verify if the value is a valid content using the regular expression
	 * 
	 * @param regex
	 *            Regular expression
	 * @param value
	 *            Value to test
	 * @return true if is valid and false if is not
	 */
	public static Boolean isValid(String regex, String value) {
		return Pattern.matches(regex, value);
	}

	/**
	 * Verify if the url is a valid using the url pattern regular expression
	 * 
	 * @param urlPattern
	 *            url pattern from web.xml
	 * @param url
	 *            the url that will be checked
	 * @return true if is a valid url or false if is not
	 */
	public static Boolean isUrlPatternValid(String urlPattern, String url) {
		return Pattern.matches(RegexUtils.transformUrlPattern(urlPattern), url);
	}

	/**
	 * Transform the url pattern to a valid regular expression
	 * 
	 * @param urlPattern
	 * @return a valid regular expression
	 */
	private static String transformUrlPattern(String urlPattern) {
		Integer index = urlPattern.indexOf("*");
		Integer length = urlPattern.length();

		if (index == 0 && index < (length - 1)) { // no inicio da expressao
			urlPattern = urlPattern.replace("*", "(.*)\\");
		} else { // no final da expressao
			urlPattern = urlPattern.replace("*", "(.*)");
		}
		return urlPattern;
	}

	public static String getGroupt(String pattern, String value, Integer group) {
		Pattern pat = Pattern.compile(RegexUtils.transformUrlPattern(pattern));
		Matcher matcher = pat.matcher(value);

		if (matcher.find()) {
			return matcher.group(group.intValue());
		} else
			return null;
	}

}
