package imagemodel;

import imagemodel.image.Image;
import imagemodel.patterngenerator.PatternBean;

import java.util.Set;

/**
 * The central portion of the imagemodel.image model project. Handles all the
 * major operations.
 */
public interface ImageModel {
  /**
   * Apply color Transformation to the imagemodel.image to convert it into a Sepia
   * imagemodel.image.
   */
  void colorTransformImageToSepia();

  /**
   * Apply color Transformation to the imagemodel.image to turn it into a
   * grayscale.
   */
  void colorTransformImageToGrayScale();

  /**
   * Apply color density reduction to the number of channels passed.
   * 
   * @param value The number of channels for the color reduction.
   */
  void changeImageColorDensity(int value);

  /**
   * Apply color density reduction to 8 colors with dithering.
   * 
   * @param value The number of channels for the color reduction.
   */
  void changeImageColorDensityWithEssence(int value);

  /**
   * blur the imagemodel.image according to the kernel passed to the imagemodel.
   */
  void blurImage();

  /**
   * sharpen the imagemodel.image according to the kernel passed to the
   * imagemodel.
   */
  void sharpenImage();

  /**
   * Get the deep copy of an imagemodel.image.
   *
   * @return A deep copy of the imagemodel.image.
   */
  Image getImage();

  /**
   * Method to pixelate the image to the number of squares required by the client.
   * 
   * @param value The number of squares the image is to be pixelated with.
   */
  void pixelateImage(int value);

  /**
   * Method to turn the image into mosaic.
   * 
   * @param seedValue The seed value for the number of random mosaic points.
   */
  void mosaicImage(int seedValue);

  /**
   * Method to generate a cross-stitch pattern from the image if it is pixelated
   * before hand.
   * 
   * @return The cross-stitch pattern generated.
   */
  String generatePatternWithPixelate();

  /**
   * Method to transform the pattern generated to the ones in the DMC floss.
   */
  PatternBean generatePatternImage();

  /**
   * Method to generate a pattern by using the custom color being passed by the
   * user.
   * 
   * @param dmcColors The custom colors selected by the users.
   * @return The bean with the text generated and the image.
   */
  PatternBean generatePatternCustomColors(Set<String> dmcColors);

  /**
   * Replace the pixels in the image with the one selected by the user.
   * 
   * @param replacementColor The color which will replace the old one.
   * @param colorToReplace   The color which will replace the new one.
   */
  void replaceImageWithNewColor(String replacementColor, int[] colorToReplace);
  
  /**
   * Set the image of the model.
   * @param image The image to be set in the model.
   */
  void setImage(Image image);
}
