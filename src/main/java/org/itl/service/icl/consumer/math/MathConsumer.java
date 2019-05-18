package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.SuperscriptConsumer;
import org.itl.service.icl.consumer.SuperscriptConsumer;
import org.itl.service.model.CharImage;
import org.itl.service.model.CharType;

public class MathConsumer implements Consumer {

    private Consumer rootSymbolConsumer;

    private Consumer fractionSymbolConsumer;

    public MathConsumer() {
        this.rootSymbolConsumer = new RootSymbolConsumer();
        this.fractionSymbolConsumer = new FractionSymbolConsumer();
    }

    @Override
    public String consume(CharImage charImage, CharImagesStream charImagesStream) {
        if(charImage.getCharType() == CharType.RootSymbol) {
            return rootSymbolConsumer.consume(charImage, charImagesStream);
        }
        if(charImage.getCharType() == CharType.FractionSlash) {
            return fractionSymbolConsumer.consume(charImage, charImagesStream);
        }
        return charImage.getCharType().getName();
    }
}
