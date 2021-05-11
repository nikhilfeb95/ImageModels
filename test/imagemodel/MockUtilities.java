package imagemodel;

import controller.Utilities;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A mock class for utilities.
 *
 */
public class MockUtilities implements Utilities {
  private StringBuilder logger;

  /**
   * A default constructor for the mock utilities class which takes in the string
   * builder as the input.
   * 
   * @param logger The logger that will aggregate the output of the methods.
   */
  public MockUtilities(StringBuilder logger) {
    if (logger == null) {
      throw new IllegalArgumentException();
    }
    this.logger = logger;
  }

  @Override
  public Image readImage(String filename) throws IOException {
    logger.append("read Image ");
    return new ImageImpl(new int[3][3][3]);
  }

  @Override
  public void writeImage(Image image, String filename) throws IOException {
    logger.append("write Image ");
    return;
  }

  @Override
  public void writeToFile(String data, String fileName) throws IOException {
    logger.append("write to file ");
    return;
  }

  @Override
  public Image bufferedToImage(BufferedImage image) {
    logger.append("buffered to image ");
    return new ImageImpl(new int[3][3][3]);
  }

  @Override
  public BufferedImage imageToBuffer(Image image) {
    logger.append("image to bufer ");
    return new BufferedImage(10,12,BufferedImage.TYPE_3BYTE_BGR);
  }

}
