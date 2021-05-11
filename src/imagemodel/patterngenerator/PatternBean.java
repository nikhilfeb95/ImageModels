package imagemodel.patterngenerator;

import imagemodel.image.Image;

import java.util.Set;

/**
 * An interface which represents the encapsulation of the three elements
 * generated by the image pattern generator, the new Image with the superpixels,
 * the text generation and the legend generated.
 */
public interface PatternBean {

  /**
   * A method to get the pattern from the bean.
   * 
   * @return The string pattern generated.
   */
  String getPattern();

  /**
   * A method to get the legend of the image.
   * 
   * @return A map containing the image
   */
  Set<String> getLegend();

  /**
   * A method to get an image from the bean.
   * 
   * @return The image object after pattern generation.
   */
  Image getImage();
}