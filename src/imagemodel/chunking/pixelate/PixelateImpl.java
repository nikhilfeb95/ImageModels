package imagemodel.chunking.pixelate;


import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

/**
 * A class which represents the implementation of the Pixelate interface.
 */
public class PixelateImpl implements Pixelate {
  private final int squareSize;

  /**
   * A constructor for the PixelateImpl class which takes in the square size for pixelation.
   *
   * @param squareSize The size of the square used for pixelation .
   */
  public PixelateImpl(int squareSize) {
    if (squareSize <= 0) {
      throw new IllegalArgumentException();
    }
    this.squareSize = squareSize;
  }

  @Override
  public Image pixelateImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    int height = image.getHeight();
    int width = image.getWidth();
    // The square size is bigger than the image.
    if (squareSize > width) {
      return image;
    }
    int[][][] modifiedImage = image.getImage();
    int i = 0;
    int j = 0;
    // go through the rows of the image
    while (i < height) {
      int next = 0;
      j = 0;
      while (j < width) {
        int[] increment = calculatePixels(modifiedImage, i, j, height, width);
        // go through all the channels.
        for (int k = 0; k < 3; k++) {
          colorSquare(modifiedImage, i, j, k, height, width, increment[0], increment[1]);
        }
        j += increment[1];
        if (next == 0) {
          next = increment[0];
        }
      }
      i += next;
    }
    return new ImageImpl(modifiedImage);
  }

  @Override
  public int[] calculatePixels(int[][][] image, int row, int col, int height, int width) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    // Check the number of squares we can fit height and width wise.
    int widthIncrement = width / this.squareSize;
    int heightIncrement = height / this.squareSize;
    // ideally a square across the width so return it if not need to change.
    int[] result = {widthIncrement, widthIncrement};
    // The pixels remaining in the bottom part if all super pixels are perfect
    // squares.
    int remainingRowPixels = height - heightIncrement * squareSize;
    // find the index from where we need to accomodate extra pixels along the
    // rows. We pass one extra pixel to one super pixel.
    int rowAccommodatePoint = (squareSize - remainingRowPixels - 1) * squareSize;
    if (row > rowAccommodatePoint) {
      result[0] = heightIncrement + 1;
    }
    // The pixels remaining in the right part--> across the width if all superPixels
    // are perfect squares.
    int remainingColPixels = width - widthIncrement * squareSize;
    // find the index from where we need to accomodate extra pixels along the
    // column. We pass one extra pixel to one super pixel.
    int columnAccommodatePoint = (squareSize - remainingColPixels - 1) * squareSize;
    if (col > columnAccommodatePoint) {
      result[1] = widthIncrement + 1;
    }
    return result;
  }

  /**
   * A method to color the super pixel square.
   *
   * @param image           The image to be colored.
   * @param row             The row of the matrix where the super pixel starts.
   * @param col             The column of the matrix where the super pixel starts.
   * @param channel         The channel to be colored.
   * @param height          The height of the image.
   * @param width           The width of the image.
   * @param heightIncrement The max row to which we should pixelate.
   * @param widthIncrement  The max col to which we should pixelate.
   */
  private void colorSquare(int[][][] image, int row, int col, int channel, int height, int width,
                           int heightIncrement, int widthIncrement) {
    int color = -1;
    if (row + heightIncrement > height && col + widthIncrement > width) {
      color = image[row + ((height - row) / 2)][col + ((width - col) / 2)][channel];
    } else if (row + heightIncrement > height) {
      color = image[row + ((height - row) / 2)][col + (widthIncrement / 2)][channel];
    } else if (col + widthIncrement > width) {
      color = image[row + (heightIncrement / 2)][col + ((width - col) / 2)][channel];
    } else {
      color = image[row + (heightIncrement / 2)][col + (widthIncrement / 2)][channel];
    }
    // Color the squares with the central square.
    for (int i = row; i < row + heightIncrement && i < height; i++) {
      for (int j = col; j < col + widthIncrement && j < width; j++) {
        image[i][j][channel] = color;
      }
    }
  }
}
