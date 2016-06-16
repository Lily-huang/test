package mengli.test.externaltools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagnoseLogger {

	private static final Logger logger = LoggerFactory.getLogger(DiagnoseLogger.class);
	
	private static DiagnoseLogger instance = new DiagnoseLogger();
	
	public static DiagnoseLogger getInstance() {
		return instance;
	}

	public void debug(String message, Throwable t) {
		logger.debug(message, t);
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void error(String message, Throwable t) {
		logger.error(message, t);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void info(String message, Throwable t) {
		logger.info(message, t);
	}

	public void info(String message) {
		logger.info(message);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public void warn(String message, Throwable t) {
		logger.warn(message, t);
	}

	public void warn(String message) {
		logger.warn(message);
	}
}
