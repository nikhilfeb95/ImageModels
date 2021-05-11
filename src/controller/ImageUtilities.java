package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image utility class that has methods to read an image from file and write to
 * a file.
 */
public final class ImageUtilities {

  /** An enumeration of the different channels in our images. */
  public enum Channel {
    RED, GREEN, BLUE;
  }

  /**
   * Returns the width of the image file.
   * 
   * @param filename the image file to read.
   * @return the width of the image file.
   * @throws IOException if the file cannot be found.
   */
  public static int getWidth(String filename) throws IOException {
    checkFilename(filename);

    FileInputStream fi = new FileInputStream(filename);
    BufferedImage input = ImageIO.read(fi);
    fi.close();

    return input.getWidth();
  }

  /**
   * Returns the height of the image file.
   * 
   * @param filename the image file to read.
   * @return the height of the image file.
   * @throws IOException if the file cannot be found.
   */
  public static int getHeight(String filename) throws IOException {
    checkFilename(filename);

    FileInputStream fi = new FileInputStream(filename);
    BufferedImage input = ImageIO.read(fi);
    fi.close();

    return input.getHeight();
  }

  /**
   * Reads an image file into an RGB color array.
   * 
   * @param filename the image file to read.
   * @return an array of RGB color values.
   * @throws IOException if the file cannot be found.
   */
  public static int[][][] readImage(String filename) throws IOException {
    checkFilename(filename);

    FileInputStream fi = new FileInputStream(filename);
    BufferedImage input = ImageIO.read(fi);
    fi.close();

    int[][][] result = new int[input.getHeight()][input.getWidth()][3];

    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        result[i][j][Channel.RED.ordinal()] = c.getRed();
        result[i][j][Channel.GREEN.ordinal()] = c.getGreen();
        result[i][j][Channel.BLUE.ordinal()] = c.getBlue();
      }
    }
    return result;
  }

  /**
   * Writes an RGB color array to a file as an image.
   * 
   * @param rgb      the color array to write to a file.
   * @param width    the width of the image file.
   * @param height   the height of the image file.
   * @param filename the filename of the written image file.
   * @throws IOException if the file cannot be written to.
   */
  public static void writeImage(int[][][] rgb, int width, int height, String filename)
      throws IOException {
    checkFilename(filename);
    BufferedImage output = getBufferedImage(rgb, width, height);
    String extension = filename.substring(filename.indexOf(".") + 1);

    FileOutputStream fo = new FileOutputStream(filename);
    ImageIO.write(output, extension, fo);
    fo.close();
  }

  /**
   * Writes an RGB color array to a file as an image.
   * 
   * @param rgb    the color array to write to a file.
   * @param width  the width of the image file.
   * @param height the height of the image file.
   * @throws IOException if the file cannot be written to.
   */
  public static BufferedImage getBufferedImage(int[][][] rgb, int width, int height) {
    if (rgb == null) {
      throw new IllegalArgumentException("rgb array cannot be null");
    }
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = rgb[i][j][Channel.RED.ordinal()];
        int g = rgb[i][j][Channel.GREEN.ordinal()];
        int b = rgb[i][j][Channel.BLUE.ordinal()];

        // color is stored in 1 integer, with the 4 bytes storing ARGB in that
        // order. Each of r,g,b are stored in 8 bits (hence between 0 and 255).
        // So we put them all in one integer by using bit-shifting << as below
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    return output;
  }

  /**
   * Helper function that validates the filename.
   * 
   * @param filename the filename that needs to be validated
   * @throws IllegalArgumentException if the filename is null
   */
  private static void checkFilename(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("filename is cannot be null");
    }
  }
}
