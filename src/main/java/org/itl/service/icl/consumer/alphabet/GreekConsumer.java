package org.itl.service.icl.consumer.alphabet;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.model.CharImage;

public class GreekConsumer implements Consumer {

    @Override
    public String consume(CharImage charImage, CharImageSequence sequence) {
        return "\\" + charImage.getCharType().getName() + " ";
    }

}
