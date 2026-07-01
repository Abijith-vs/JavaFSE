package com.deepskilling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSystem {

    private static final Logger logger = LoggerFactory.getLogger(LoggingSystem.class);

    public void executeAppOperations(String operationName, boolean criticalStatus) {
        logger.info("Starting application system execution check.");

        if ("SecureConnection".equalsIgnoreCase(operationName)) {
            logger.warn("Warning: Attempting connection via insecure fallback protocols.");
        }

        if (criticalStatus) {
            logger.error("Critical Failure: System execution stopped due to internal exceptions.");
        }
    }

    public static void main(String[] args) {
        LoggingSystem system = new LoggingSystem();
        system.executeAppOperations("SecureConnection", true);
    }
}