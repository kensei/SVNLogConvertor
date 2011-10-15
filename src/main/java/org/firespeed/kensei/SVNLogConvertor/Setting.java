package org.firespeed.kensei.SVNLogConvertor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

/**
 * 
 * Setting
 * 
 * @author BCC
 *
 */
public final class Setting {
	
	private final static Logger logger_ = LogFactory.getLogger(Setting.class);
	private static Setting instance__ = new Setting();
	private Document document_;
	@SuppressWarnings("rawtypes")
	private Map cache_;

	/**
	 * 
	 * @return
	 */
	public static Setting getInstance() {
		return instance__;
	}

	/**
	 * 
	 * @param filePath
	 */
	public void init(String filePath) {
		URL url = getClass().getResource(filePath);
		init(url);
	}

	/**
	 * 
	 * @param settingFile
	 */
	@SuppressWarnings("deprecation")
	public void init(File settingFile) {
		try {
			init(settingFile.toURL());
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("url pars error" + settingFile.getAbsoluteFile());
		}
	}

	/**
	 * 
	 * @param url
	 */
	@SuppressWarnings("rawtypes")
	public void init(URL url) {
		try {
			SAXReader reader = new SAXReader();
			this.document_ = reader.read(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.cache_ = new HashMap();
	}

	/**
	 * 
	 * @param xpathValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getStringValue(String xpathValue) {
		String result = (String)this.cache_.get(xpathValue);
		if (null == result) {
			try {
				XPath xpath = DocumentHelper.createXPath(xpathValue);
				result = xpath.valueOf(this.document_);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			this.cache_.put(xpathValue, result);
		}
		logger_.info(xpathValue + " : " + result);
		return result;
	}

	/**
	 * 
	 * @param xpathValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getIntValue(String xpathValue) {
		Number result = (Number)this.cache_.get(xpathValue);
		if (null == result) {
			try {
				XPath xpath = DocumentHelper.createXPath(xpathValue);
				result = xpath.numberValueOf(this.document_);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			this.cache_.put(xpathValue, result);
		}
		logger_.info(xpathValue + " : " + result);
		return result.intValue();
	}
	
	/**
	 * 
	 * @param xpathValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean getBoolValue(String xpathValue) {
		String result = (String)this.cache_.get(xpathValue);
		if (null == result) {
			try {
				XPath xpath = DocumentHelper.createXPath(xpathValue);
				result = xpath.valueOf(this.document_);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			this.cache_.put(xpathValue, result);
		}
		logger_.info(xpathValue + " : " + result);
		return ("true".equals(result));
	}
}
