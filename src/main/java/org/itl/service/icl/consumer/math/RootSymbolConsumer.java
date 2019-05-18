package org.itl.service.icl.consumer.math;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.icl.consumer.Consumer;
import org.itl.service.icl.consumer.ConsumerResolver;
import org.itl.service.icl.consumer.RootConsumer;
import org.itl.service.model.CharImage;

public class RootSymbolConsumer implements Consumer {
    @Override
    public String consume(CharImage rootSymbol, CharImagesStream charImagesStream) {
        String result = "\\sqrt{";
        result += RootConsumer.getInstance().consume(charImagesStream.crop(
                rootSymbol.getBoundingRectangle().getTopLeft(),
                rootSymbol.getBoundingRectangle().getBottomRight()
        ));
        result += "}";
        return result;
    }
}
