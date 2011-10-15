package org.firespeed.kensei.SVNLogConvertor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * LogFactory
 * 
 * @author BCC
 *
 */
public class LogFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final Map<Class, Logger> loggers__ = new HashMap();
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clazz) {
		Logger logger = (Logger)loggers__.get(clazz);
		if (logger == null) {
			logger = Logger.getLogger(clazz);
			logger.setLevel(Level.DEBUG);
			loggers__.put(clazz, logger);
		}
		return logger;
	}
}
