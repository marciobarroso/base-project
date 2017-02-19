package com.icodeuplay.base.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.icodeuplay.base.common.exceptions.BaseCommonException;

public class Serializator {

	public static final String SERIALIZATOR_KEY = ".ser";

	public static void serialize(Serializable object, String key) {
		try {
			String filename = key.concat(SERIALIZATOR_KEY);
			File file = FileUtils.createTempFile(filename);
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
			stream.writeObject(object);
			stream.close();
		} catch (Exception e) {
			throw new BaseCommonException(e);
		}
	}

	public static Serializable unserialize(String key) {
		try {
			String filename = key.concat(SERIALIZATOR_KEY);
			File file = FileUtils.createTempFile(filename);
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			Serializable result = (Serializable) stream.readObject();
			stream.close();
			return result;
		} catch (Exception e) {
			throw new BaseCommonException(e);
		}
	}
}
