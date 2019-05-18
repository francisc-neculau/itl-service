package org.itl.service;

import org.itl.service.external.icr.IcrService;
import org.itl.service.external.icr.ScriptCallBasedIcrService;
import org.itl.service.icl.IclService;
import org.itl.service.icl.IclServiceImpl;
import org.itl.service.model.CharImage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImageToLatexServiceImpl implements ImageToLatexService{
    /**
     * Image Characters to LaTeX service
     */
    private IclService iclService;
    /**
     * Intelligent Character Recognition service
     */
    private IcrService icrService;

    public ImageToLatexServiceImpl() {
        this.iclService = new IclServiceImpl();
        this.icrService = new ScriptCallBasedIcrService();
    }

    public static void main(String...args) {
        String sources = "C:\\ComputerScience\\source\\";
        System.setProperty("log4j.configurationFile", sources + "itl-service\\src\\main\\resources\\log4j2.json");
        Path imagePath = Paths.get(sources + "itl-icr\\org\\itl\\icr\\resources\\equation_images\\clean\\0.jpg");

        ImageToLatexService itlService = new ImageToLatexServiceImpl();
        String latexEquation = itlService.compute(imagePath);
        System.out.println(latexEquation);
    }

    @Override
    public String compute(Path imagePath) {
        String latexEquation = null;
        // Find individual characters.
        List<CharImage> charImages = icrService.parse(imagePath);
        // assemble into LaTeX equation.
        latexEquation = iclService.assemble(charImages);
        return latexEquation;
    }
}
