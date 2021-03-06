package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.model.CharImage;
import org.itl.service.model.Point;

import java.util.Optional;

public class SuperscriptConsumer implements Consumer {

    /**
     * This method handles the case of a superscript.
     *               D~~~~~~~~~~~~~~~~~~~~~~~+
     *               ~  +------------------+ ~
     *               ~  |                  | ~
     *               ~  | superscript of X | ~
     *         +----+~  |                  | ~         +----+
     *         |char|~  |                  | ~         |char|
     *  ====== | X  |~  +------------------+ ~         | Y  | ====== normal line of flow
     *         |    |A~~~~~~~~~~~~~~~~~~~~~~~C------->B|    |
     *         +----+                                  +----+
     * The superscript is determined by calculating the points
     * A, B, C and D.
     *
     * @param charImage current char image, X, that has a superscript.
     * @param sequence the stream of all other char images.
     * @return
     */
    @Override
    public String consume(CharImage charImage, CharImageSequence sequence) {
        int offset = (charImage.getBoundingRectangle().getBottomY() -
                charImage.getBoundingRectangle().getTopY()) / 4;
        int y = charImage.getBoundingRectangle().getBottomRight().getY() - offset;
        Optional<CharImage> optional = sequence.firstIntersectionWithY(y);
        if(optional.isPresent()) {
            CharImage nextCharImage = optional.get();
            int x = nextCharImage.getBoundingRectangle().getTopLeft().getX() - 1;
            Point B = new Point(x, y);
            Point A = new Point(charImage.getBoundingRectangle().getBottomRight().getX() + 1, y);
            int lowestIntersectingY = sequence.lowestIntersectingY(A, B) - 1;
            Point D = new Point(A.getX(), lowestIntersectingY);
            // Point C = calculate it !
            String result = charImage.getCharType().getName() + "^{";
            CharImageSequence superscriptCharImagesStream = sequence.crop(D, B);
            result += RootConsumer.getInstance().consume(superscriptCharImagesStream);
            result += "}";
            return result;
        } else {
            // this charImage is the last in the equation.
            String result = charImage.getCharType().getName() + "^{";
            result += RootConsumer.getInstance().consume(sequence);
            result += "}";
            return result;
        }
    }


}
