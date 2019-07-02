package org.itl.service.icl.consumer;

import org.itl.service.icl.CharImageSequence;
import org.itl.service.model.CharImage;
import org.itl.service.model.CharType;

public class ParenthesisConsumer implements Consumer {
    @Override
    public String consume(CharImage parenthesisCharImage, CharImageSequence sequence) {
        String result = "";
        if(parenthesisCharImage.getCharType() == CharType.LeftRoundBracket ||
            parenthesisCharImage.getCharType() == CharType.LeftSquareBracket||
            parenthesisCharImage.getCharType() == CharType.LeftCurlyBracket) {
            result = "\\left" + parenthesisCharImage.getCharType().getName();
        } else{
            result = "\\right" + parenthesisCharImage.getCharType().getName();
        }
        return result;
    }
}
