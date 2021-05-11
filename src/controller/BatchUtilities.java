package controller;

import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;

/**
 * This class is used by the image model to write to a text file.
 */
public class BatchUtilities implements Utilities {

  /**
   * An enumeration of the different channels in our images.
   */
  public enum Channel {
    RED, GREEN, BLUE
  }

  /**
   * This method is used to write the generated pattern into a text file. Files
   * written in UTF-16.
   *
   * @param data     The data to be written to a text file.
   * @param fileName The name of the file to be written.
   */
  @Override
  public void writeToFile(String data, String fileName) throws IOException {
    if (data == null || fileName == null) {
      throw new IllegalArgumentException();
    }
    Writer fstream = new OutputStreamWriter(new FileOutputStream(fileName),
        StandardCharsets.UTF_16LE);
    fstream.write(data);
    fstream.close();
  }

  @Override
  public Image readImage(String filename) throws IOException {
    BufferedImage input;

    input = ImageIO.read(new FileInputStream(filename));

    int[][][] result = new int[input.getHeight()][input
        .getWidth()][BatchUtilities.Channel.values().length];

    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        result[i][j][BatchUtilities.Channel.RED.ordinal()] = c.getRed();
        result[i][j][BatchUtilities.Channel.GREEN.ordinal()] = c.getGreen();
        result[i][j][BatchUtilities.Channel.BLUE.ordinal()] = c.getBlue();
      }
    }
    return new ImageImpl(result);
  }

  @Override
  public void writeImage(Image image, String filename) throws IOException {
    int[][][] rgb = image.getImage();
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = rgb[i][j][BatchUtilities.Channel.RED.ordinal()];
        int g = rgb[i][j][BatchUtilities.Channel.GREEN.ordinal()];
        int b = rgb[i][j][BatchUtilities.Channel.BLUE.ordinal()];

        // color is stored in 1 integer, with the 4 bytes storing ARGB in that
        // order. Each of r,g,b are stored in 8 bits (hence between 0 and 255).
        // So we put them all in one integer by using bit-shifting << as below
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    String extension = filename.substring(filename.lastIndexOf(".") + 1);
    ImageIO.write(output, extension, new FileOutputStream(filename));
  }

  @Override
  public Image bufferedToImage(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    BufferedImage input;
    input = image;

    int[][][] result = new int[input.getHeight()][input
        .getWidth()][BatchUtilities.Channel.values().length];

    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        result[i][j][BatchUtilities.Channel.RED.ordinal()] = c.getRed();
        result[i][j][BatchUtilities.Channel.GREEN.ordinal()] = c.getGreen();
        result[i][j][BatchUtilities.Channel.BLUE.ordinal()] = c.getBlue();
      }
    }
    return new ImageImpl(result);
  }

  @Override
  public BufferedImage imageToBuffer(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    return ImageUtilities.getBufferedImage(image.getImage(), image.getWidth(), image.getHeight());
  }
}
