package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.RootConsumer;
import org.itl.service.model.CharImage;

public class RootSymbolConsumer implements Consumer {
    @Override
    public String consume(CharImage rootSymbol, CharImageSequence sequence) {
        String result = "\\sqrt{";
        result += RootConsumer.getInstance().consume(sequence.crop(
                rootSymbol.getBoundingRectangle().getTopLeft(),
                rootSymbol.getBoundingRectangle().getBottomRight()
        ));
        result += "}";
        return result;
    }
}
