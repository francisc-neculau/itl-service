package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.model.CharImage;

public interface Consumer {

    /**
     * This will compute a part of the LaTeX equation.
     * @param charImage current char image.
     * @param sequence the stream of all other char images.
     * @return String representing part of the LaTeX equation.
     */
    String consume(CharImage charImage, CharImageSequence sequence);

}
