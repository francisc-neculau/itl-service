package org.itl.service.external.icr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itl.service.configuration.ItlServiceProperties;
import org.itl.service.external.SegmentationResultOuterClass.SegmentationResult;
import org.itl.service.model.BoundingRectangle;
import org.itl.service.model.CharImage;
import org.itl.service.model.CharType;
import org.itl.service.model.Point;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScriptCallBasedIcrService implements IcrService {

    private static Logger logger = LogManager.getLogger(ScriptCallBasedIcrService.class);
    private String icrPythonExe;
    private String icrMainPyScript;
    private Path protobufResultsPath;
    private CharImagesProtobufParser parser;

    public ScriptCallBasedIcrService() {
        this.icrPythonExe = ItlServiceProperties.getInstance().getIcrPythonExe().toString();
        this.icrMainPyScript = ItlServiceProperties.getInstance().getIcrMainPyScript().toString();
        this.protobufResultsPath = ItlServiceProperties.getInstance().getProtobufResultsPath();
        this.parser = new CharImagesProtobufParser();
    }

    public List<CharImage> parse(Path inputImagePath) {
        logger.info("Start parsing " + inputImagePath.toString());
        List<CharImage> result = null;
        executeIcrScript(inputImagePath);
        try {
            result = parser.parse(protobufResultsPath);
        } catch (FileNotFoundException e) {
            throw new IcrServiceException("protobuf results are missing", e);
        } catch (IOException e) {
            throw new IcrServiceException("protobuf results cannot be parsed", e);
        }
        logger.info("Finished parsing");
        return result;
    }

    private void executeIcrScript(Path inputImagePath) {
        StringBuilder command = new StringBuilder();
        command.append(icrPythonExe).append(" ");
        command.append(icrMainPyScript).append(" ");
        command.append(inputImagePath.toString()).append(" ");
        command.append(protobufResultsPath.toString());
        try {
            Process process = Runtime.getRuntime().exec(command.toString());
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                throw new IcrServiceException("ICR main.py execution failed with exit value " + exitValue);
            }
        } catch (IOException | InterruptedException e) {
            throw new IcrServiceException("ICR main.py execution error", e);
        }
    }

}
