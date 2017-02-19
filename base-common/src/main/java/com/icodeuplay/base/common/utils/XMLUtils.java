package com.icodeuplay.base.common.utils;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.icodeuplay.base.common.exceptions.BaseCommonException;

public class XMLUtils {

	private static XMLUtils instance;

	private File file;
	private Document document;

	private XMLUtils(File file) {
		try {
			this.file = file;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.parse(this.file);
		} catch (Exception e) {
			throw new BaseCommonException(e);
		}
	}

	public static XMLUtils getInstance(File file) {
		instance = new XMLUtils(file);
		return instance;
	}

	public Document getDocument() {
		return this.document;
	}

	public Element createElement(String name) {
		return this.getDocument().createElement(name);
	}

	public void write(Element node) {
		try {
			getDocument().getChildNodes().item(0).appendChild(node);

			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream(this.file)));
		} catch (Exception e) {
			throw new BaseCommonException(e);
		}
	}
}