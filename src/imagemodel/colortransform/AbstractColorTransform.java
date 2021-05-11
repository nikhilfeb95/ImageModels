package imagemodel.colortransform;

import imagemodel.clamp.Clamp;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

/**
 * An abstract class for Color transformations. This class houses all the common
 * methods.
 */
public class AbstractColorTransform implements ColorTransform {
  private double[][] transformationMatrix;
  private Clamp clamp;

  /**
   * A constructor for the color transformation.
   *
   * @param transformationMatrix The matrix used for transformation.
   * @param clamp                The imagemodel.clamp to be used by the
   *                             transformation.
   */
  AbstractColorTransform(double[][] transformationMatrix, Clamp clamp) {
    if (transformationMatrix == null || clamp == null) {
      throw new IllegalArgumentException();
    }
    if (transformationMatrix.length != 3 || transformationMatrix[0].length != 3) {
      throw new IllegalArgumentException("The matrix has to be a 3X3");
    }
    this.transformationMatrix = transformationMatrix;
    this.clamp = clamp;
  }

  @Override
  public Image colorTransform(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    int[][][] manipulatedImage = image.getImage();
    int height = image.getHeight();
    int width = image.getWidth();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        transformHelper(manipulatedImage, i, j);
      }
    }
    return new ImageImpl(manipulatedImage);
  }

  /**
   * A helper method to apply the transformation on every channel of a pixel.
   *
   * @param image The imagemodel.image to be passed.
   * @param row   The row of the imagemodel.image matrix to be manipulated.
   * @param col   The col of the imagemodel.image to be manipulated.
   */
  private void transformHelper(int[][][] image, int row, int col) {
    // Store new r,g and b values.
    // the transformation matrix can only be a 3X3 as there are 3 channels R,G and
    // B.
    int[] value = new int[3];
    for (int i = 0; i < transformationMatrix.length; i++) {
      value[i] = (int) ((image[row][col][0] * transformationMatrix[i][0])
          + (image[row][col][1] * transformationMatrix[i][1])
          + (image[row][col][2] * transformationMatrix[i][2]));
      // imagemodel.clamp the imagemodel.image for over/underflow.
      value[i] = clamp.clampImage(value[i]);
    }
    image[row][col][0] = value[0];
    image[row][col][1] = value[1];
    image[row][col][2] = value[2];
  }
}
