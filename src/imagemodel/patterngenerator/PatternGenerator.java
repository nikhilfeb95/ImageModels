package imagemodel.patterngenerator;

import imagemodel.chunking.ChunkingMethods;
import imagemodel.image.Image;

import java.util.Set;



/**
 * An interface that represents generation of cross-stitch patterns by mapping .
 */
public interface PatternGenerator {
  /**
   * A method to generate the pattern.
   * 
   * @param image           The image passed for pattern generation.
   * @param chunkingMethods A type of chunking method passed for pattern
   *                        generation.
   * @param colors          The colors passed by the user. If null choose default
   *                        DMC list.
   * @return The pattern generated as a string.
   */
  PatternBean generatePattern(Image image, ChunkingMethods chunkingMethods, Set<String> colors);

  /**
   * Replace colors in the image with the one passed.
   * 
   * @param image            The image from which the colors are to be replaced.
   * @param replacementColor The new color that replaces the old one.
   * @param colorToReplace   The original color of the pixel which is to be
   *                         replaced.
   * @param chunkingMethods  The chunking method that'll be used to calculate
   *                         super pixels.
   * @return The image with the equivalent pixels replaced.
   */
  Image replacePixelsFromImage(Image image, String replacementColor, int[] colorToReplace,
      ChunkingMethods chunkingMethods);
}
