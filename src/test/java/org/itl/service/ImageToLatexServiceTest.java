package org.itl.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class ImageToLatexServiceTest {

    ImageToLatexService unit = new ImageToLatexServiceImpl();

    // Given[ExplainYourInput]When[WhatIsDone]Then[ExpectedResult]"
    //

    @Test
    public void simpleEquation() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_equation_2.jpg"));
        Assertions.assertEquals("X*A=20", latexEquation);
    }

    @Test
    public void simpleEquationWithGreekLetter() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_equation_1.jpg"));
        Assertions.assertEquals("y=\\omega X+B", latexEquation);
    }

    @Test
    public void simpleEquationWithSquareRoot() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_equation_with_radical.jpg"));
        Assertions.assertEquals("y=\\sqrt{x}", latexEquation);
    }

    @Test
    public void simpleEquationWithSquareRootAndExponents() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_equation_with_radical_and_exponents.jpg"));
        Assertions.assertEquals("y=\\sqrt{x^{2}+y^{n-1}}", latexEquation);
    }

    @Test
    public void simpleEquationWithSquareRootAndFractionAndExponents() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_equation_with_radical_and_fraction_and_exponents.jpg"));
        Assertions.assertEquals("y=\\sqrt{\\frac{x^{2}+y^{n-1}}{2}}", latexEquation);
    }

    @Test
    public void simpleEquationWith2FractionsAnd2Exponents() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_equation_with_2fractions_and_2exponents.jpg"));
        Assertions.assertEquals("6\\frac{dx}{dt}+z\\frac{d^{2}x}{dt^{2}}=3x", latexEquation);
    }

    @Test
    public void simpleLongEquation() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\simple_long_equation.jpg"));
        Assertions.assertEquals("10xy^{2}+15x^{2}y-5xy=5\\left(2xy^{2}+3x^{2}y-xy\\right)", latexEquation);
    }

    @Test
    public void complexFirstEquation() {
        String latexEquation = unit.compute(Paths.get("src\\test\\resources\\complex_first_equation.jpg"));
        Assertions.assertEquals("Q\\left(z\\right)=\\frac{1}{\\sqrt{2\\pi }}\\int_{z}^{\\infty}e^{\\frac{-x^{2}}{2}}dx", latexEquation);
    }
}
