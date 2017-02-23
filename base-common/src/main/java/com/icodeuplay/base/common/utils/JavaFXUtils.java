package com.icodeuplay.base.common.utils;

public class JavaFXUtils {

	public static String getFormReference(Class<?> clazz) {
		StringBuilder result = new StringBuilder("/");
		result.append(clazz.getName().replaceAll("\\.", "/").replace("Controller", ".fxml"));
		return result.toString();
	}

}
