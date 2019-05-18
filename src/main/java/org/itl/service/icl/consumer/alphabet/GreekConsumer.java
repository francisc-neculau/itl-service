package org.itl.service.icl.consumer.alphabet;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.model.CharImage;

public class GreekConsumer implements Consumer {

    @Override
    public String consume(CharImage charImage, CharImagesStream charImagesStream) {
        return "\\" + charImage.getCharType().getName() + " ";
    }

}
