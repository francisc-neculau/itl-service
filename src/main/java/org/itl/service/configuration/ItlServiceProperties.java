package org.itl.service.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itl.service.external.icr.ScriptCallBasedIcrService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class ItlServiceProperties {

    private static Logger logger = LogManager.getLogger(ItlServiceProperties.class);
    private static ItlServiceProperties instance = new ItlServiceProperties();

    private Path protobufResultsPath;
    private Path icrPythonExe;
    private Path icrMainPyScript;

    private ItlServiceProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileURL = classLoader.getResource("app.properties");
        if(fileURL == null) {
            fileURL = classLoader.getResource("app-default.properties");
            if(fileURL == null) {
                // we cannot start the application.
                throw new IllegalStateException("No app or app-default properties file found in classpath");
            }
        }
        logger.info("Loading the properties file : " + fileURL.getFile());
        Properties appProperties = new Properties();
        try {
            appProperties.load(new FileInputStream(fileURL.getFile()));

            protobufResultsPath = Paths.get(appProperties.getProperty("icr-protobuf-results-path"));
            logger.info("icr-protobuf-results-path" + protobufResultsPath.toString());

            icrPythonExe = Paths.get(appProperties.getProperty("icr-python-exe"));
            logger.info("icr-python-exe=" + icrPythonExe.toString());

            icrMainPyScript = Paths.get(appProperties.getProperty("icr-main-py-script"));
            logger.info("icr-main-py-script=" + icrMainPyScript.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Loading completed!");
    }

    public static ItlServiceProperties getInstance() {
        return instance;
    }

    public Path getProtobufResultsPath() {
        return protobufResultsPath;
    }

    public Path getIcrPythonExe() {
        return icrPythonExe;
    }

    public Path getIcrMainPyScript() {
        return icrMainPyScript;
    }
}
