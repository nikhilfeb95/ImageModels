package imagemodel.colordensity;

import imagemodel.image.Image;

/**
 * A interface for changing the color density of a imagemodel.image.
 */
public interface ColorDensity {
  /**
   * A method to convert imagemodel.image with dithering.
   *
   * @param image The imagemodel.image to be transformed.
   * @return The transformed imagemodel.image.
   */
  Image transformImageWithEssence(Image image);

  /**
   * A method to convert an imagemodel.image without dithering.
   *
   * @param image The imagemodel.image to be transformed
   * @return The transformed imagemodel.image.
   */
  Image transformImage(Image image);
}
