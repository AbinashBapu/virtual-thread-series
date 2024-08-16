package com.thinksync.virtualthreadseries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class VirtualThreadSeriesApplication {
	private static final Logger logger = LogManager.getLogger(VirtualThreadSeriesApplication.class);

	public static void main(String[] args) {
		logger.info("Info Logged");
		logger.debug("Debug Logged");
		logger.trace("Trace Logged");
		logger.warn("Warn Logged");
		logger.error("Error Logged");
	}

}
