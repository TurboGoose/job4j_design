package io.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        // logMessages();
        logPrimitiveTypes();
    }

    public static void logMessages() {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }

    public static void logPrimitiveTypes() {
        char c = '0';
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4;
        float f = 5.6f;
        double d = 7.8;
        boolean bool = false;

        LOG.info("{} {} {} {} {} {} {} {}   ", c, b, s, i, l, f, d, bool);
    }
}
