package org.itl.service;

import org.itl.service.external.icr.CharImagesProtobufParser;
import org.itl.service.model.CharImage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public final class ItlTestDataProviderUtil {

    private static final CharImagesProtobufParser parser = new CharImagesProtobufParser();

    private ItlTestDataProviderUtil() {}

    public static List<CharImage> getCharImagesForSimpleEquation() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_2"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquationWithGreekLetter() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquationWithRadical() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_with_radical"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquationWithRadicalAndExponents() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_with_radical_and_exponents"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquationWithRadicalAndFractionAndExponents() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_with_radical_and_fraction_and_exponents"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquationWith2FractionsAnd2Exponents() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_with_2fractions_and_2exponents"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleLongEquation() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_long_equation"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForComplexFirstEquation() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\complex_first_equation"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquation1WithGreekLetters() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_1_with_greek_letters"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<CharImage> getCharImagesForSimpleEquationWithArrow() {
        try {
            return parser.parse(Paths.get("src\\test\\resources\\simple_equation_with_arrow"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
