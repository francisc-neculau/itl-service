package org.itl.service;

import org.itl.service.model.CharImage;

import java.nio.file.Path;
import java.util.List;

/**
 * This interface provides consumers with the ability to
 * get the LaTeX representation of an image of an equation.
 */
public interface ImageToLatexService {

    String compute(Path imagePath);

}
