package imagemodel.colordensity;


import imagemodel.colordensity.dither.Dither;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

/**
 * A abstract class that implements the ColorDensity interface and houses all
 * the common methods.
 */
public class AbstractColorDensity implements ColorDensity {
  private int colorSize;

  /**
   * The constructor for the abstract color density class.
   * 
   * @param colorSize The color size for the transformation.
   */
  AbstractColorDensity(int colorSize) {
    if (colorSize < 0) {
      throw new IllegalArgumentException("Invalid size!");
    }
    this.colorSize = colorSize;
  }

  @Override
  public Image transformImageWithEssence(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    Image manipulatedImage = new ImageImpl(image.getImage());
    return Dither.ditherImage(manipulatedImage, colorSize);
  }

  @Override
  public Image transformImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    int colorRange = calculateColorRange();
    int[][][] manipulatedImage = image.getImage();
    int height = image.getHeight();
    int width = image.getWidth();
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          manipulatedImage[i][j][k] = reduceColorSize(manipulatedImage[i][j][k], colorRange);
        }
      }
    }
    return new ImageImpl(manipulatedImage);
  }

  /**
   * A helper method to find the closest color to reduce the imagemodel.image.
   *
   * @param oldColor   The old color of the current pixel.
   * @param colorRange The max number of colors allowed
   * @return The value with the closest pallete color.
   */
  private int reduceColorSize(int oldColor, int colorRange) {
    return (int) Math.floor((oldColor / colorRange)) * colorRange;
  }

  private int calculateColorRange() {
    return (int) Math.floor(256 / (colorSize - 1));
  }
}
