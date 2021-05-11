package controller;

import imagemodel.image.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * General utilities required by the image model class.
 */
public interface Utilities {

  /**
   * Read an imagemodel.image from a file and return it as a 2D array of RGB
   * colors.
   *
   * @param filename The name of the file containing the imagemodel.image to read
   * @return a 2D array of RGB colors
   * @throws IOException if there is an error reading the file
   */
  public Image readImage(String filename) throws IOException;

  /**
   * Write the content of a 2D array of RGB colors to a file.
   *
   * @param image    An image which on which the operations are to be conducted.
   * @param filename The name of the file containing the imagemodel.image to read
   * @throws IOException if there is an error reading the file
   */
  public void writeImage(Image image, String filename) throws IOException;

  /**
   * This method is used to write the generated pattern into a text file. Files
   * written in UTF-16.
   *
   * @param data     The data to be written to a text file.
   * @param fileName The name of the file to be written.
   */
  public void writeToFile(String data, String fileName) throws IOException;

  /**
   * A method to convert a buffered image to a Image object which is used by the
   * model.
   * 
   * @param image The image to be converted.
   * @return The Image object after conversion.
   */
  public Image bufferedToImage(BufferedImage image);

  /**
   * Convert an image to a buffered image.
   * @param image The image to be converted.
   * @return The result of the image conversion.
   */
  public BufferedImage imageToBuffer(Image image);
}
