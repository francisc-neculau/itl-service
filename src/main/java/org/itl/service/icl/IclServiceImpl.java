package org.itl.service.icl;

import org.itl.service.icl.consumer.RootConsumer;
import org.itl.service.model.CharImage;

import java.util.List;

public class IclServiceImpl implements IclService {

    @Override
    public String assemble(List<CharImage> charImages) {
        CharImageSequence charImagesStream = new CharImageSequence(charImages);
        return RootConsumer.getInstance().consume(charImagesStream).trim();
    }
}
