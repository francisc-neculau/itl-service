package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.model.CharImage;

public class DefaultConsumer implements Consumer {
    @Override
    public String consume(CharImage charImage, CharImageSequence sequence) {
        return "{undefined:" + charImage.getCharType().getLatexName() + "}";
    }
}
