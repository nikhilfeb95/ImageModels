package imagemodel.image;

/**
 * A general representation of an imagemodel.image.Image class.
 */
public interface Image {
  /**
   * Get the height of the imagemodel.image.
   * 
   * @return The height of the imagemodel.image.
   */
  int getHeight();

  /**
   * Get the width of the imagemodel.image.
   * 
   * @return The width of the imagemodel.image.
   */
  int getWidth();

  /**
   * Get the integer representation of the imagemodel.image.
   * 
   * @return The integer representation of the imagemodel.image.
   */
  int[][][] getImage();
}
