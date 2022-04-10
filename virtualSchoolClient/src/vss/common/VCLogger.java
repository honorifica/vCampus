package vss.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VCLogger {
	
	private static Logger logger = null;
	public static Logger clientLogger() {
		if (logger == null) {
			logger = Logger.getLogger("Client");
			logger.setLevel(Level.INFO);
		}
		return logger;		 
	}
	public static Logger serverLogger() {
		if (logger == null) {
			logger = Logger.getLogger("Server");
			logger.setLevel(Level.INFO);
		}
		return logger;		 
	}

}
