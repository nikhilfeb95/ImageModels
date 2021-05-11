package imagemodel.colortransform;

import imagemodel.image.Image;

/**
 * An interface to transform the color of the imagemodel.image.
 */
public interface ColorTransform {

  /**
   * Transform the imagemodel.image according to the transform matrix.
   * 
   * @param image The imagemodel.image to be transformed.
   * @return The transformed imagemodel.image.
   */
  Image colorTransform(Image image);
}
