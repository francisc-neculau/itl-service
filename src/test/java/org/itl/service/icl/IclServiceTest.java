package org.itl.service.icl;

import org.itl.service.ItlTestDataProviderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IclServiceTest {

    IclService unit = new IclServiceImpl();

    // Given[ExplainYourInput]When[WhatIsDone]Then[ExpectedResult]"
    //

    @Test
    public void simpleEquation() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleEquation());
        Assertions.assertEquals("X*A=20", latexEquation);
    }

    @Test
    public void simpleEquationWithGreekLetter() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleEquationWithGreekLetter());
        Assertions.assertEquals("y=\\omega X+B", latexEquation);
    }

    @Test
    public void simpleEquationWithSquareRoot() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleEquationWithRadical());
        Assertions.assertEquals("y=\\sqrt{x}", latexEquation);
    }

    @Test
    public void simpleEquationWithSquareRootAndExponents() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleEquationWithRadicalAndExponents());
        Assertions.assertEquals("y=\\sqrt{x^{2}+y^{n-1}}", latexEquation);
    }

    @Test
    public void simpleEquationWithSquareRootAndFractionAndExponents() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleEquationWithRadicalAndFractionAndExponents());
        Assertions.assertEquals("y=\\sqrt{\\frac{x^{2}+y^{n-1}}{2}}", latexEquation);
    }

    @Test
    public void simpleEquationWith2FractionsAnd2Exponents() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleEquationWith2FractionsAnd2Exponents());
        Assertions.assertEquals("6\\frac{dx}{dt}+z\\frac{d^{2}x}{dt^{2}}=3x", latexEquation);
    }

    @Test
    public void simpleLongEquation() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForSimpleLongEquation());
        Assertions.assertEquals("10xy^{2}+15x^{2}y-5xy=5\\left(2xy^{2}+3x^{2}y-xy\\right)", latexEquation);
    }

    @Test
    public void complexFirstEquation() {
        String latexEquation = unit.assemble(ItlTestDataProviderUtil.getCharImagesForComplexFirstEquation());
        Assertions.assertEquals("Q\\left(z\\right)=\\frac{1}{\\sqrt{2\\pi}}\\int_{z}^{\\infty}e^{\\frac{-x^{2}}{2}}dx", latexEquation);
    }
}
