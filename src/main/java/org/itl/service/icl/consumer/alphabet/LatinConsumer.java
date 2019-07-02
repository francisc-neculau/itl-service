package org.itl.service.icl.consumer.alphabet;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.SuperscriptConsumer;
import org.itl.service.model.CharImage;

/**
 * This class is responsible for consuming CharImages in
 * the range : [a-zA-Z]
 */
public class LatinConsumer implements Consumer {

    private Consumer superscriptConsumer;


    public LatinConsumer() {
        this.superscriptConsumer = new SuperscriptConsumer();
    }

    @Override
    public String consume(CharImage charImage, CharImageSequence sequence) {
        if(charImage.getCharType().isLatinLoweCase()) {
            // check if it has superscript
            if(sequence.hasSuperscript(charImage)) {
                return superscriptConsumer.consume(charImage, sequence);
            }
        }
        return charImage.getCharType().getName();
    }

}
