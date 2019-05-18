package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.RootConsumer;
import org.itl.service.model.CharImage;
import org.itl.service.model.Point;

public class FractionSymbolConsumer implements Consumer {
    @Override
    public String consume(CharImage fractionCharImage, CharImagesStream charImagesStream) {
        String result = "\\frac";
        // add numerator - top part of fraction
        result += "{";
        int lowestIntersectingY = charImagesStream.lowestIntersectingY(
                fractionCharImage.getBoundingRectangle().getBottomLeft(),
                fractionCharImage.getBoundingRectangle().getBottomRight());
        Point A = new Point(
                fractionCharImage.getBoundingRectangle().getTopLeft().getX(),
                lowestIntersectingY);
        result += RootConsumer.getInstance().consume(charImagesStream.crop(
                A,
                fractionCharImage.getBoundingRectangle().getBottomRight()
        ));
        result += "}";
        // add denominator - bottom part of fraction
        result += "{";
        int highestIntersectingY = charImagesStream.highestIntersectingY(
                fractionCharImage.getBoundingRectangle().getBottomLeft(),
                fractionCharImage.getBoundingRectangle().getBottomRight());
        Point B = new Point(
                fractionCharImage.getBoundingRectangle().getBottomRight().getX(),
                highestIntersectingY);
        result += RootConsumer.getInstance().consume(charImagesStream.crop(
                fractionCharImage.getBoundingRectangle().getBottomLeft(),
                B
        ));
        result += "}";

        return result;
    }
}
