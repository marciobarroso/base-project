package com.icodeuplay.base.common.utils;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class MessageUtils {

	public static Integer WARNING_MESSAGE_TYPE = JOptionPane.WARNING_MESSAGE;
	public static Integer INFORMATION_MESSAGE_TYPE = JOptionPane.INFORMATION_MESSAGE;
	public static Integer ERROR_MESSAGE_TYPE = JOptionPane.ERROR_MESSAGE;
	public static Integer PLAIN_MESSAGE_TYPE = JOptionPane.PLAIN_MESSAGE;

	public static Integer YES_NO_OPTION = JOptionPane.YES_NO_OPTION;
	public static Integer YES_NO_CANCEL_OPTION = JOptionPane.YES_NO_CANCEL_OPTION;
	public static Integer CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
	public static Integer CLOSED_OPTION = JOptionPane.CLOSED_OPTION;
	public static Integer OK_CANCEL_OPTION = JOptionPane.OK_CANCEL_OPTION;

	private static PropertyReader MESSAGES;

	static {
		MESSAGES = new PropertyReader("messages");
	}

	public static String getString(String key) {
		if (MESSAGES.containsKey(key)) {
			return MESSAGES.getString(key);
		} else {
			return key;
		}
	}

	public static String getString(String key, Object... parameters) {
		String message = getString(key);

		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] != null && message.indexOf("{" + i + "}") > -1) {
				message = message.replace("{" + i + "}", parameters[i].toString());
			}
		}

		return message;
	}

	public static void showMessage(Component parente, String message, Integer messageType) {
		String title = MessageUtils.getString("app.title");
		String newMessage = getString(message);
		JOptionPane.showMessageDialog(parente, newMessage, title, messageType);
	}

	public static void showMessage(JComponent parente, String message, Integer messageType) {
		String title = MessageUtils.getString("app.title");
		String newMessage = getString(message);
		JOptionPane.showMessageDialog(parente, newMessage, title, messageType);
	}

	public static Boolean showConfirmDialog(JComponent parent, String message, String title, Integer messageTyInteger) {
		String newTitle = getString(title);
		String newMessage = getString(message);
		int i = JOptionPane.showConfirmDialog(parent, newMessage, newTitle, MessageUtils.OK_CANCEL_OPTION,
				messageTyInteger);
		return i == 0;
	}

	public static Integer showOptionDialog(JComponent parent, String question, String title, String[] options,
			Integer messageType) {
		question = getString(question);
		title = getString(title);

		for (int i = 0; i < options.length; i++) {
			options[i] = getString(options[i]);
		}

		return JOptionPane.showOptionDialog(parent, question, title, messageType, JOptionPane.QUESTION_MESSAGE, null,
				options, null);
	}
}