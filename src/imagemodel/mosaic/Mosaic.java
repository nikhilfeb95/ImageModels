package imagemodel.mosaic;

import imagemodel.image.Image;

/**
 * An interface that represents the mosaic operation.
 */
public interface Mosaic {
  /**
   * A method to apply the imagemodel.mosaic operation to the image.
   * 
   * @param image The imagemodel.image where the operation is to be applied.
   * @return The transformed imagemodel.image.
   */
  Image transformImage(Image image);
}
