package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.model.CharImage;

public class DigitConsumer implements Consumer {

    @Override
    public String consume(CharImage charImage, CharImagesStream charImagesStream) {
        return charImage.getCharType().getName();
    }

}
