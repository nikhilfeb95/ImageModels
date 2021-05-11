package imagemodel.image;

import java.util.Arrays;

/**
 * Implementation of the imagemodel.image class.
 */
public class ImageImpl implements Image {
  private int[][][] image;

  /**
   * A constructor for the imagemodel.image.
   * 
   * @param image The imagemodel.image to be passed.
   */
  public ImageImpl(int[][][] image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;
  }

  /**
   * A copy constructor for the imagemodel.image class.
   * 
   * @param image The imagemodel.image to be copied.
   */
  public ImageImpl(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image.getImage();
  }

  @Override
  public int getHeight() {
    return image.length;
  }

  @Override
  public int getWidth() {
    return image[0].length;
  }

  @Override
  public int[][][] getImage() {
    return deepCopy();
  }

  /**
   * A private helper to deep copy the Image.
   * 
   * @return A copy of the imagemodel.image matrix.
   */
  private int[][][] deepCopy() {
    int[][][] copy = new int[this.getHeight()][this.getWidth()][3];
    for (int k = 0; k < 3; k++) {
      for (int i = 0; i < getHeight(); i++) {
        for (int j = 0; j < getWidth(); j++) {
          copy[i][j][k] = this.image[i][j][k];
        }
      }
    }
    return copy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageImpl image1 = (ImageImpl) o;

    return Arrays.deepEquals(image, image1.image);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(image);
  }

  @Override
  public String toString() {
    return "ImageImpl{" + "imagemodel.image=" + Arrays.toString(image) + '}';
  }
}
