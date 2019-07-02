package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.icl.consumer.alphabet.GreekConsumer;
import org.itl.service.icl.consumer.alphabet.LatinConsumer;
import org.itl.service.icl.consumer.math.DigitConsumer;
import org.itl.service.icl.consumer.math.MathConsumer;
import org.itl.service.model.CharImage;
import org.itl.service.model.CharType;
import org.itl.service.model.Group;

import java.util.EnumMap;

public class RootConsumer {

    private EnumMap<Group, Consumer> map;

    private RootConsumer() {
        map = new EnumMap<Group, Consumer>(Group.class);
        map.put(Group.Digit, new DigitConsumer());
        map.put(Group.Greek, new GreekConsumer());
        map.put(Group.Latin, new LatinConsumer());
        map.put(Group.MathematicalSymbol, new MathConsumer());
        map.put(Group.Parenthesis, new ParenthesisConsumer());
        map.put(Group.Arrows, new ArrowsConsumer());
    }

    /**
     * Initialization-on-demand holder design pattern.
     */
    private static class ConsumerResolverInstanceHolder {
        private static final RootConsumer instance = new RootConsumer();

    }

    public static RootConsumer getInstance() {
        return ConsumerResolverInstanceHolder.instance;
    }

    public String consume(CharImageSequence sequence) {
        StringBuilder sb = new StringBuilder();
        while(sequence.hasNext()) {
            CharImage charImage = sequence.next();
            Consumer consumer = resolve(charImage.getCharType());
            String equationPart =  consumer.consume(charImage, sequence);
            sb.append(equationPart);
        }
        return sb.toString();
    }

    private Consumer resolve(CharType charType) {
        return map.getOrDefault(charType.getGroup(), new DefaultConsumer());
    }

}
