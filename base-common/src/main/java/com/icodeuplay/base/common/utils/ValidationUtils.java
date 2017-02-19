package com.icodeuplay.base.common.utils;

import java.io.Serializable;

public class ValidationUtils implements Serializable {

	private static final long serialVersionUID = -4197598502398795843L;

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isEmpty(String obj) {
		return isNotNull(obj) && "".equals(obj);
	}

	public static boolean isNotEmpty(String obj) {
		return isNotNull(obj) && !"".equals(obj);
	}

	public static boolean isValidString(String obj) {
		return isNotNull(obj) && isNotEmpty(obj);
	}

}
