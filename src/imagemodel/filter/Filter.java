package imagemodel.filter;

import imagemodel.image.Image;

/**
 * A interface which represents a general imagemodel.filter.
 */
public interface Filter {
  /**
   * A method which filters the imagemodel.image according to the kernel passed to it.
   *
   * @param image The imagemodel.image to be passed.
   * @return The manipulated imagemodel.image.
   */
  Image filterImage(Image image);
}
