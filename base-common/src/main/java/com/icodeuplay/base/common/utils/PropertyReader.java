package com.icodeuplay.base.common.utils;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.icodeuplay.base.common.exceptions.BaseCommonException;

public class PropertyReader implements Serializable {

	private static final long serialVersionUID = 5148011073456876871L;

	private PropertiesConfiguration properties;

	public PropertyReader(String filename) {
		if (filename == null || "".equals(filename))
			throw new BaseCommonException("The filename must be a valid property file location");
		if (!filename.endsWith(".properties"))
			filename = filename.concat(".properties");

		try {
			this.properties = new PropertiesConfiguration(filename);
		} catch (ConfigurationException e) {
			throw new BaseCommonException(e);
		}
	}

	public PropertyReader(File file) {
		if (file == null)
			throw new BaseCommonException("The file cannot be null");

		try {
			this.properties = new PropertiesConfiguration(file);
		} catch (ConfigurationException e) {
			throw new BaseCommonException(e);
		}
	}

	public boolean containsKey(String key) {
		return this.properties.containsKey(key);
	}

	public Iterator<?> getKeys() {
		return this.properties.getKeys();
	}

	public BigDecimal getBigDecimal(String key) {
		return this.properties.getBigDecimal(key);
	}

	public BigInteger getBigInteger(String key) {
		return this.properties.getBigInteger(key);
	}

	public boolean getBoolean(String key) {
		return this.properties.getBoolean(key);
	}

	public byte getByte(String key) {
		return this.properties.getByte(key);
	}

	public double getDouble(String key) {
		return this.properties.getDouble(key);
	}

	public float getFloat(String key) {
		return this.properties.getFloat(key);
	}

	public int getInt(String key) {
		return this.properties.getInt(key);
	}

	public Iterator<?> getKeys(String prefix) {
		return this.properties.getKeys(prefix);
	}

	public List<?> getList(String key) {
		return this.properties.getList(key);
	}

	public char getListDelimiter() {
		return this.properties.getListDelimiter();
	}

	public long getLong(String key) {
		return this.properties.getLong(key);
	}

	public Properties getProperties(String key) {
		return this.properties.getProperties(key);
	}

	public short getShort(String key) {
		return this.properties.getShort(key);
	}

	public String getString(String key) {
		return this.properties.getString(key);
	}

	public String[] getStringArray(String arg0) {
		return this.properties.getStringArray(arg0);
	}
}
