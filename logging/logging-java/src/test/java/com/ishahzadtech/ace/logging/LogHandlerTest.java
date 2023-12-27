package com.ishahzadtech.ace.logging;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogHandlerTest {

	private static final String LOGGER_NAME = "default";
	private static final String LOG_FILE_NAME = "default.log";
	private static final Path LOG_FILE_PATH = Paths.get("target", LOG_FILE_NAME);

	@BeforeEach
	public void cleanup() {
		final File logFile = LOG_FILE_PATH.toFile();

		if (logFile.exists())
			logFile.delete();
	}
	
	@Test
	public void testLogDebug() {
		final long initialFileSize = LOG_FILE_PATH.toFile().length();
		
		LogHandler.logDebug(LOGGER_NAME, "Write DEBUG level message for verification");
		
		final File logFile = LOG_FILE_PATH.toFile();

		assertAll("Generated Log Debug Assertions",
				() -> assertNotNull(logFile, "file object is not null"),
				() -> assertTrue(logFile.exists(), "log file exists"),
				() -> assertTrue(logFile.isFile(), "log file is not a directory"),
				() -> assertEquals(initialFileSize, logFile.length(), "Debug message shouldn't be logged"));
	}
	
	
	@Test
	public void testLogInfo() {
		final long initialFileSize = LOG_FILE_PATH.toFile().length();
		
		LogHandler.logInfo(LOGGER_NAME, "Write INFO level message for verification");
		
		final File logFile = LOG_FILE_PATH.toFile();

		assertAll("Generated Log Info Assertions", 
				() -> assertNotNull(logFile, "file object is not null"),
				() -> assertTrue(logFile.exists(), "log file exists"),
				() -> assertTrue(logFile.isFile(), "log file is not a directory"),
				() -> assertTrue(logFile.length() > initialFileSize, "Info message should be logged"));
	}

	@Test
	public void testLogException() {
		final long initialFileSize = LOG_FILE_PATH.toFile().length();
		
		try {
			throw new IllegalArgumentException();
		} catch (Exception ex) {
			LogHandler.logException(LOGGER_NAME, "dummy exception for verification", ex);
		}
		
		final File logFile = LOG_FILE_PATH.toFile();
		
		assertAll("Generated Log Exception Assertions", 
				() -> assertNotNull(logFile, "file object is not null"),
				() -> assertTrue(logFile.exists(), "log file exists"),
				() -> assertTrue(logFile.isFile(), "log file is not a directory"),
				() -> assertTrue(logFile.length() > initialFileSize, "Exception message should be logged"));
	}
}
