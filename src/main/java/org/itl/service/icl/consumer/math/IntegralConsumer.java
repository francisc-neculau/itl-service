package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.RootConsumer;
import org.itl.service.model.CharImage;
import org.itl.service.model.Point;

import java.util.Optional;

public class IntegralConsumer implements Consumer {
    /**
     * This will consume integrals of the form :
     *
     *  *expresion*
     *        /\              /\ *expresion*
     *       /        OR     /
     *     \/              \/ *expresion*
     *  *expresion*
     *      (A)                  (B)
     *
     * FIXME: Currently (B) is not available.
     *
     * @param integral
     * @param sequence the stream of all other char images.
     * @return
     */
    @Override
    public String consume(CharImage integral, CharImageSequence sequence) {

        Optional<CharImage> striking = sequence.firstIntersectionWithY(integral.getBoundingRectangle().getMiddleLeft().getY());
        int leftX = integral.getBoundingRectangle().getLeftX();
        int rightX = integral.getBoundingRectangle().getRightX();
        if(striking.isPresent()) {
            rightX = striking.get().getBoundingRectangle().getLeftX();
        }

        int lowY = sequence.lowestIntersectingY(
                integral.getBoundingRectangle().getTopLeft(),
                new Point(rightX, integral.getBoundingRectangle().getTopY())
        );
        CharImageSequence upperSequence = sequence.crop(
                new Point(leftX, lowY),
                new Point(rightX, integral.getBoundingRectangle().getTopY())
        );

        int highY = sequence.highestIntersectingY(
                integral.getBoundingRectangle().getBottomLeft(),
                integral.getBoundingRectangle().getBottomRight());
        CharImageSequence lowerSequence = sequence.crop(
                integral.getBoundingRectangle().getBottomLeft(),
                new Point(rightX, highY)
        );

        String result = "\\int_{";
        // upper part of integral
        result += RootConsumer.getInstance().consume(lowerSequence);
        // -------------
        result += "}^{";
        // -------------
        // lower part of integral
        result += RootConsumer.getInstance().consume(upperSequence);
        result += "}";
        return result;
    }
}
