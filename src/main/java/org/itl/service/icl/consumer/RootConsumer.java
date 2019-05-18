package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImagesStream;
import org.itl.service.model.CharImage;

public class RootConsumer {

    private RootConsumer() {}

    /**
     * Initialization-on-demand holder design pattern.
     */
    private static class ConsumerResolverInstanceHolder {
        private static final RootConsumer instance = new RootConsumer();

    }

    public static RootConsumer getInstance() {
        return ConsumerResolverInstanceHolder.instance;
    }

    public String consume(CharImagesStream charImagesStream) {
        ConsumerResolver consumerResolver = ConsumerResolver.getInstance();
        StringBuilder sb = new StringBuilder();
        while(charImagesStream.hasNext()) {
            CharImage charImage = charImagesStream.next();
            Consumer consumer = consumerResolver.resolve(charImage.getCharType());
            String equationPart =  consumer.consume(charImage, charImagesStream);
            sb.append(equationPart);
        }
        return sb.toString();
    }
}
