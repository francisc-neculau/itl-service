package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.model.CharImage;
import org.itl.service.model.CharType;

public class MathConsumer implements Consumer {

    private Consumer rootSymbolConsumer;

    private Consumer fractionSymbolConsumer;

    private Consumer integralConsumer;

    public MathConsumer() {
        this.rootSymbolConsumer = new RootSymbolConsumer();
        this.fractionSymbolConsumer = new FractionSymbolConsumer();
        this.integralConsumer = new IntegralConsumer();
    }

    @Override
    public String consume(CharImage charImage, CharImageSequence sequence) {
        if(charImage.getCharType() == CharType.RootSymbol) {
            return rootSymbolConsumer.consume(charImage, sequence);
        } else if(charImage.getCharType() == CharType.FractionSlash) {
            return fractionSymbolConsumer.consume(charImage, sequence);
        } else if(charImage.getCharType() == CharType.IntegralSymbol) {
            return integralConsumer.consume(charImage, sequence);
        }
        return charImage.getCharType().getLatexName();
    }
}
