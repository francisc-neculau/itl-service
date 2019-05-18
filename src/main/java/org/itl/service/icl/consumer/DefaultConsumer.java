package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.model.CharImage;

public class DefaultConsumer implements Consumer {
    @Override
    public String consume(CharImage charImage, CharImagesStream charImagesStream) {
        return "{undefined:" + charImage + "}";
    }
}
