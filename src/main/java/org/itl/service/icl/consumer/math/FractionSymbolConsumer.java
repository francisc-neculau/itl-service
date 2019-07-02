package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.RootConsumer;
import org.itl.service.model.CharImage;
import org.itl.service.model.Point;

public class FractionSymbolConsumer implements Consumer {
    @Override
    public String consume(CharImage fractionCharImage, CharImageSequence sequence) {
        String result = "\\frac";
        // add numerator - top part of fraction
        result += "{";
        int lowestIntersectingY = sequence.lowestIntersectingY(
                fractionCharImage.getBoundingRectangle().getBottomLeft(),
                fractionCharImage.getBoundingRectangle().getBottomRight());
        if(lowestIntersectingY == -1) {
            return "/ ";
        }
        Point A = new Point(
                fractionCharImage.getBoundingRectangle().getTopLeft().getX(),
                lowestIntersectingY);
        result += RootConsumer.getInstance().consume(sequence.crop(
                A,
                fractionCharImage.getBoundingRectangle().getBottomRight()
        ));
        result += "}";
        // add denominator - bottom part of fraction
        result += "{";
        int highestIntersectingY = sequence.highestIntersectingY(
                fractionCharImage.getBoundingRectangle().getBottomLeft(),
                fractionCharImage.getBoundingRectangle().getBottomRight());
        Point B = new Point(
                fractionCharImage.getBoundingRectangle().getBottomRight().getX(),
                highestIntersectingY);
        result += RootConsumer.getInstance().consume(sequence.crop(
                fractionCharImage.getBoundingRectangle().getBottomLeft(),
                B
        ));
        result += "}";

        return result;
    }
}
