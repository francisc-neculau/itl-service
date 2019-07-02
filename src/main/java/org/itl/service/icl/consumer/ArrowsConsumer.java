package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.model.CharImage;

public class ArrowsConsumer implements Consumer{

    @Override
    public String consume(CharImage arrow, CharImageSequence sequence) {
        return arrow.getCharType().getLatexName() + " ";
    }
}
