package testimagemodels;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import imagemodel.image.Image;
import imagemodel.image.ImageImpl;
import imagemodel.ImageModel;
import imagemodel.ImageModelImpl.ImageBuilder;

/**
 * A class to test the ImageModel class.
 */
public class ImageModelImplTest {

  ImageModel imageModel;

  /**
   * Setup the tests before executing them.
   */
  @Before
  public void setUp() {
    int[][][] testImage = new int[4][4][3];
    Image image = new ImageImpl(testImage);
    imageModel = ImageBuilder.getInstance().setImage(image).build();
  }

  /**
   * A test for the ImageModel class.
   */
  @Test
  public void testImageModel() {
    Image copyImage = imageModel.getImage();
    assertEquals(4, copyImage.getHeight());
    assertEquals(4, copyImage.getWidth());
  }

  /**
   * A test for the ImageModel class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageModelWhenImageNull() {
    ImageModel testModel = ImageBuilder.getInstance().setImage(null).build();
  }

}
