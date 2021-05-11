package imagemodel.colordensity.dither;


import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

/**
 * Dither the imagemodel.image using the FLoyd Steinberg algorithm.
 */
public class Dither {

  /**
   * A static method to dither the imagemodel.image with the Floyd Steinberg algorithm.
   *
   * @param image The imagemodel.image to be dithered.
   * @param color The number of channels for dithering. 
   * @return The dithered imagemodel.image.
   */
  public static Image ditherImage( Image image, int color) {
    if (image == null || color <= 0) {
      throw new IllegalArgumentException("Invalid values passed");
    }
    int colorRange = (int) Math.floor(256 / (color - 1));
    int[][][] manipulatedImage = image.getImage();
    int height = image.getHeight();
    int width = image.getWidth();
    final float rightConstant = 7 / 16f;
    final float leftDiagonal = 3 / 16f;
    final float down = 5 / 16f;
    final float rightDiagonal = 1 / 16f;
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int oldColor = manipulatedImage[i][j][k];
          int newColor = findClosestPalleteColor(oldColor, colorRange);
          int error = oldColor - newColor;
          manipulatedImage[i][j][k] = newColor;

          // add (7/16 * error) to pixel on the right (r,c+1)
          if (j != width - 1) {
            manipulatedImage[i][j + 1][k] += rightConstant * error;
          }
          // add (3/16 * error) to pixel on the next-row-left (r+1,c-1)
          if (i != height - 1 && j != 0) {
            manipulatedImage[i + 1][j - 1][k] += leftDiagonal * error;
          }
          // add (5/16 * error) to pixel below in next row (r+1,c)
          if (i != height - 1) {
            manipulatedImage[i + 1][j][k] += down * error;
          }
          // add (1/16 * error) to pixel on the next-row-right (r+1,c+1)
          if (i != height - 1 && j != width - 1) {
            manipulatedImage[i + 1][j + 1][k] += rightDiagonal * error;
          }
        }
      }
    }
    return new ImageImpl(manipulatedImage);
  }

  /**
   * A helper method to find the closest color palette for dithering.
   *
   * @param oldColor   The old color of the current pixel.
   * @param colorRange The max number of colors allowed
   * @return The value with the closest pallete color.
   */
  private static int findClosestPalleteColor(int oldColor, int colorRange) {
    return (int) Math.floor((oldColor / colorRange)) * colorRange;
  }

  @Override
  public String toString() {
    return "Dither{}";
  }
}
