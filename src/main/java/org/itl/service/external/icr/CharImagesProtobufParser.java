package org.itl.service.external.icr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itl.service.external.SegmentationResultOuterClass.SegmentationResult;
import org.itl.service.external.SegmentationResultOuterClass.SegmentationResult.CharImage;
import org.itl.service.model.BoundingRectangle;
import org.itl.service.model.CharType;
import org.itl.service.model.Point;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CharImagesProtobufParser {

    private static Logger logger = LogManager.getLogger(CharImagesProtobufParser.class);

    /**
     * This method parses a probuf file that contains CharImages.
     *
     * @param protobufEncodedCharImages Path to file with protobuf-encoded CharImages
     * @return list of the decoded CharImages.
     */
    public List<org.itl.service.model.CharImage> parse(Path protobufEncodedCharImages) throws IOException {
        FileInputStream file = new FileInputStream(protobufEncodedCharImages.toFile());
        SegmentationResult segmentationResult = SegmentationResult.parseFrom(file);

        logger.info(segmentationResult.getCharImagesList().size() + " charImages identified");
        List<org.itl.service.model.CharImage> result = new ArrayList<>();
        for(CharImage charImage : segmentationResult.getCharImagesList()) {
            result.add(convert(charImage));
        }
        result.stream().map(ci -> ci.getIdentifier() + " " + ci.getCharType()).forEach(s -> logger.info(s));
        return result;
    }

    private org.itl.service.model.CharImage convert(CharImage charImage) {
        Point topLeft = new Point(charImage.getBoundingRectangle().getTopLeft().getX(),
                charImage.getBoundingRectangle().getTopLeft().getY());
        Point bottomRight = new Point(charImage.getBoundingRectangle().getBottomRight().getX(),
                charImage.getBoundingRectangle().getBottomRight().getY());
        return new org.itl.service.model.CharImage(
                charImage.getIdentifier(),
                CharType.forName(charImage.getCharTypeName()),
                charImage.getHeight(),
                charImage.getWidth(),
                new Point(charImage.getCenterOfMass().getX(), charImage.getCenterOfMass().getY()),
                new BoundingRectangle(topLeft, bottomRight));
    }

}
