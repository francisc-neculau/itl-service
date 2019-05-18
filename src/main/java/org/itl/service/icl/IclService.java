package org.itl.service.icl;

import org.itl.service.model.CharImage;

import java.util.List;

/**
 *
 * The Image Charaters to LaTeX Service interface
 * enables consumers to assemble the LaTeX equation
 * out of the provided Image Charaters.
 *
 */
public interface IclService {

    String assemble(List<CharImage> charImages);

}
