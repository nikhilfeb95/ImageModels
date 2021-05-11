package testimagemodels;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

/**
 * A class to test the Image class.
 */
public class ImageImplTest {

  Image image;

  /**
   * Setup the tests before running it.
   */
  @Before
  public void setUp() {
    int[][][] testImage = new int[4][4][3];
    image = new ImageImpl(testImage);
  }

  /**
   * Test if the constructor works properly for Image.
   */
  @Test
  public void testImage() {
    int expected = 4;
    assertEquals(expected, image.getHeight());
    assertEquals(expected, image.getWidth());
  }

  /**
   * Test if get Image works properly or not.
   */
  @Test
  public void testGetImage() {
    int[][][] copyImage = image.getImage();
    assertEquals(copyImage.length, image.getHeight());
  }

  /**
   * Test if an exception is thrown when imagemodel.image is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageWhenNull() {
    int[][][] test = null;
    Image img = new ImageImpl(test);
  }
}
