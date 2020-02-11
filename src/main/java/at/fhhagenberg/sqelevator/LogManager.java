package at.fhhagenberg.sqelevator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private LogManager() {
        logger.setLevel(Level.ALL);
    }

    public static Logger getLogger() {
        return logger;
    }
}
