package imagemodel.filter;

import imagemodel.clamp.Clamp;
import imagemodel.filter.kernel.Kernel;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

/**
 * An abstract class for all the imagemodel.filter types. It houses all the common methods
 * for all imagemodel.filter types.
 */
public abstract class AbstractFilter implements Filter {
  private Kernel kernel;
  private Clamp clamp;

  /**
   * A constructor for the imagemodel.filter type.
   * 
   * @param kernel The kernel used for the filtering.
   * @param clamp  The imagemodel.clamp to be selected to imagemodel.clamp off imagemodel.image.
   */
  AbstractFilter(Kernel kernel, Clamp clamp) {
    if (kernel == null || clamp == null) {
      throw new IllegalArgumentException("Kernel cannot be null!");
    }
    this.clamp = clamp;
    this.kernel = kernel;
  }

  @Override
  public Image filterImage( Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    // Incase we have to do undo just get unmanipulated data
    int[][][] manipulate = image.getImage();
    // center represents the central portion of the kernel.
    int center = kernel.getDimension() / 2;
    // Go through all the channels of the imagemodel.image.
    for (int k = 0; k < 3; k++) {
      // pad the imagemodel.image according to the size of the imagemodel.filter passed.
      int[][] paddedImage = padArray(image, k);
      // go through the pixels of the imagemodel.image.
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          int newValue = applyKernel(paddedImage, i, j);
          // replace with new value both x and y.
          manipulate[i][j][k] = newValue;
          paddedImage[i + center][j + center] = newValue;
        }
      }
    }
    return new ImageImpl(manipulate);
  }

  /**
   * Add some padding to the array for some easy manipulation.
   *
   * @param image   The imagemodel.image to be manipulated.
   * @param channel The current channel we're operating on.
   * @return The array with padding.
   */
  private int[][] padArray(Image image, int channel) {
    int height = image.getHeight();
    int width = image.getWidth();
    // The center position decides the amount of padding we need to add.
    int padding = kernel.getDimension() / 2;
    // add this many rows and cols as a padding.
    int size = kernel.getDimension() - 1;
    int[][][] originalImage = image.getImage();
    int[][] paddedImage = new int[height + size][width + size];
    int paddedHeight = paddedImage.length;
    int paddedWidth = paddedImage[0].length;
    // add the pixel values to the new array

    for (int i = padding; i < paddedHeight - padding; i++) {
      for (int j = padding; j < paddedWidth - padding; j++) {
        paddedImage[i][j] = originalImage[i - padding][j - padding][channel];
      }
    }
    return paddedImage;
  }

  /**
   * A method to apply the kernel to the imagemodel.image.
   *
   * @param image The imagemodel.image passed.
   * @param row   The row where we start calculating.
   * @param col   The col where we start calculating.
   * @return The value after all the calculations.
   */
  private int applyKernel(int[][] image, int row, int col) {
    float[][] kernel = this.kernel.getKernel();
    int dimension = this.kernel.getDimension();

    int value = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        value += kernel[i][j] * image[row + i][col + j];
      }
    }
    value = this.clamp.clampImage(value);
    return value;
  }
}
