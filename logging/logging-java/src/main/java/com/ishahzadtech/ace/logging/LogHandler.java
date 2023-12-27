package com.ishahzadtech.ace.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class acting as a bridge decoupling the applications from actual logging framework. <br />
 * SLF4J is not used to avoid further complexity and keeping the library lightweight. 
 * 
 * @author irfan shahzad
 * @since 1.0
 */
public class LogHandler {

	public static Boolean logEnabled(String loggerName, String logLevel) {
		Logger logger = LogManager.getLogger(loggerName);
		return logger.isEnabled(Level.toLevel(logLevel));
	}
	
	public static void log(String loggerName, String logLevel, String message) {
		logObject(loggerName, logLevel, message);
	}
	
	public static void logDebug(String loggerName, String message) {
		logObject(loggerName, "DEBUG", message);
	}
	
	public static void logDebug(String loggerName, String message, Object... args) {
		Logger logger = LogManager.getLogger(loggerName);
		logger.debug(message, args);
	}
	
	public static void logInfo(String loggerName, String message) {
		logObject(loggerName, "INFO", message);
	}
	
	public static void logInfo(String loggerName, String message, Object... args) {
		Logger logger = LogManager.getLogger(loggerName);
		logger.info(message, args);
	}
	
	public static void logWarning(String loggerName, String message) {
		logObject(loggerName, "WARN", message);
	}
	
	public static void logException(String loggerName, String message, Throwable throwable) {
		Logger logger = LogManager.getLogger(loggerName);
		logger.error(message, throwable);
	}
	
	public static void logException(String message, Throwable throwable) {
		Logger logger = LogManager.getLogger("default");
		logger.error(message, throwable);
	}
	
	public static void logObject(String loggerName, String logLevel, Object message) {
		//if (logEnabled(loggerName, logLevel)) {
		Logger logger = LogManager.getLogger(loggerName);
		
		if ("ERROR".equalsIgnoreCase(logLevel)) {
			logger.error(message);
			
		} else if ("WARN".equalsIgnoreCase(logLevel)) {
			logger.warn(message);
			
		} else if ("INFO".equalsIgnoreCase(logLevel)) {
			logger.info(message);
			
		} else if ("TRACE".equalsIgnoreCase(logLevel)) {
			logger.trace(message);
			
		} else {
			logger.debug(message);
		}
		//}
	}
}
