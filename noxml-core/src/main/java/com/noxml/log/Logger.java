package com.noxml.log;

import java.util.logging.Level;

/**
 * TODO - create custom our own logger
 *
 * @author Onur Karaduman
 * @since 05.11.17
 */
public class Logger {

    private java.util.logging.Logger loggerRoot;

    public static Logger getLogger(Class cl) {
        return getLogger(cl.getName());
    }

    public static Logger getLogger(String s) {
        Logger logger = new Logger();
        logger.loggerRoot = java.util.logging.Logger.getLogger(s);
        logger.addCustomHandler();
        return logger;
    }

    public void info(String message) {
        loggerRoot.info(message);
    }

    public void error(String message, Throwable throwable) {
        loggerRoot.log(Level.SEVERE, message, throwable);
    }

    public void debug(String message) {
        loggerRoot.log(Level.FINE, message);
    }

    private void addCustomHandler() {
        loggerRoot.addHandler(new LogHandler());
    }

    public void error(String message) {
        loggerRoot.log(Level.SEVERE, message);
    }
}
