package org.itl.service.external.icr;

import org.itl.service.model.CharImage;

import java.nio.file.Path;
import java.util.List;

/**
 *
 * The Intelligent Character Recognition Service interface
 * enables consumers to extract Image Characters out of
 * an image.
 *
 */
public interface IcrService {

    List<CharImage> parse(Path inputPath);

}
