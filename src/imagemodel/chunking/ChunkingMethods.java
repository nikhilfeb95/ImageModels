package imagemodel.chunking;

/**
 * An interface which represent various types of chunking methods such as
 * Pixelate.
 */
public interface ChunkingMethods {

  /**
   * Get the size of the next super pixel of the chunking method. For pixelate
   * these are usually squares, might be different for some other methods.
   *
   * @param image  The array representing the image.
   * @param row    The row we're operating at.
   * @param col    The col we're operating at.
   * @param height The height of the image.
   * @param width  The width of the image
   * @return An array containing the row and column limits for the new pixel.
   */
  int[] calculatePixels(int[][][] image, int row, int col, int height, int width);
}
