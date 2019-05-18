package org.itl.service.icl.consumer;

import org.itl.service.icl.consumer.alphabet.GreekConsumer;
import org.itl.service.icl.consumer.alphabet.LatinConsumer;
import org.itl.service.icl.consumer.math.DigitConsumer;
import org.itl.service.icl.consumer.math.MathConsumer;
import org.itl.service.model.CharType;
import org.itl.service.model.Group;

import java.util.EnumMap;

public class ConsumerResolver {

    private EnumMap<Group, Consumer> map;

    /**
     * Initialization-on-demand holder design pattern.
     */
    private static class ConsumerResolverInstanceHolder {
        public static final ConsumerResolver instance = new ConsumerResolver();
    }

    private ConsumerResolver() {
        map = new EnumMap<Group, Consumer>(Group.class);
        map.put(Group.Digit, new DigitConsumer());
        map.put(Group.Greek, new GreekConsumer());
        map.put(Group.Latin, new LatinConsumer());
        map.put(Group.MathematicalSymbol, new MathConsumer());
        map.put(Group.Parenthesis, new ParenthesisConsumer());
    }

    public static ConsumerResolver getInstance() {
        return ConsumerResolverInstanceHolder.instance;
    }

    public Consumer resolve(CharType charType) {
        return map.getOrDefault(charType.getGroup(), new DefaultConsumer());
    }

}
