package controllertest;

import static org.junit.Assert.assertEquals;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.Utilities;
import imagemodel.ImageModel;
import imagemodel.MockImageModel;
import imagemodel.MockUtilities;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class to test the image controller. Isolated ImageModel using a mock
 * object.
 */
public class ImageControllerImplTest {
  private ImageModel imageModel;
  private StringBuilder stringBuilder;
  private ImageController controller;
  private Utilities utility;

  /**
   * Setup the required objects before testing.
   */
  @Before
  public void setUp() {
    stringBuilder = new StringBuilder();
    imageModel = new MockImageModel(stringBuilder);
    utility = new MockUtilities(new StringBuilder());
  }

  /**
   * Test the start method when the image model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImageControllerNull() throws IOException {
    controller = new ImageControllerImpl(null, null, null);
  }

  /**
   * Test the start method when the image model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartNullImageModel() throws IOException {
    String command = "blur";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    controller.start(null);
  }

  /**
   * Check if the controller calls the blur method properly.
   */
  @Test
  public void testBlur() {
    String command = "blur";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "blur";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the load method properly.
   */
  @Test
  public void testLoad() {
    String command = "load test.jpg";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    StringBuilder sb = new StringBuilder();
    controller = new ImageControllerImpl(sb, inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "Image loaded";
    assertEquals(expected, sb.toString().trim());
  }

  /**
   * Check if the controller calls the save method properly.
   */
  @Test
  public void testSave() {
    String command = "save test.jpg";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    StringBuilder sb = new StringBuilder();
    controller = new ImageControllerImpl(sb, inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "Image saved.";
    assertEquals(expected, sb.toString().trim());
  }

  /**
   * Check if the controller saves pattern properly.
   */
  @Test
  public void testSavePattern() {
    String command = "pixelate 50\n pattern \n save example.txt";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    StringBuilder sb = new StringBuilder();
    controller = new ImageControllerImpl(sb, inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "Image pixelated Cross-stitch pattern generated Written to file.";
    assertEquals(expected, sb.toString().trim());
  }

  /**
   * Check if the controller calls the colorTransformToGrayScale() method
   * properly.
   */
  @Test
  public void testSepia() {
    String command = "sepia";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "Sepia";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the colorTransformToGrayScale() method
   * properly.
   */
  @Test
  public void testGrayScale() {
    String command = "grayscale";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "GrayScale";
    assertEquals(expected, stringBuilder.toString().trim());
  }
  
  /**
   * Test if an exception is thrown when pattern is generated without pixelate.
   */
  @Test(expected = IllegalStateException.class)
  public void testPatternWithoutPixelate() {
    String command = "pattern";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "GrayScale";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the sharpen method properly.
   */
  @Test
  public void testSharpen() {
    String command = "sharpen";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "sharpen";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the color reduction method properly.
   */
  @Test
  public void testColorReduction() {
    String command = "reduce 8";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "ColorReduction";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the dither method properly.
   */
  @Test
  public void testDither() {
    String command = "dither 8";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "ColorReductionEssence";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the pixelate method properly.
   */
  @Test
  public void testPixelate() {
    String command = "pixelate 60";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "pixelated";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the mosaic method properly.
   */
  @Test
  public void testMosaic() {
    String command = "mosaic 1500";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "mosaic";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Check if the controller calls the pixelate method properly.
   */
  @Test
  public void testPatternGenerate() {
    String command = "pixelate 60\n pattern";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "pixelated pattern";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test calling blur multiple times.
   */
  @Test
  public void testMultipleBlur() {
    String command = "blur\n blur";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "blur blur";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test calling sepia multiple times.
   */
  @Test
  public void testMultipleSepia() {
    String command = "sepia\n sepia";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "Sepia Sepia";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test calling sharpen multiple times.
   */
  @Test
  public void testMultipleSharpen() {
    String command = "sharpen\n sharpen";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "sharpen sharpen";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test the grayscale multiple times.
   */
  @Test
  public void testMultipleGrayScale() {
    String command = "grayscale\n grayscale";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "GrayScale GrayScale";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test color reduction multiple times.
   */
  @Test
  public void testMultipleColorReduce() {
    String command = "reduce 8\nreduce 8";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "ColorReduction ColorReduction";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test dither multiple times.
   */
  @Test
  public void testMultipleDither() {
    String command = "dither 8\ndither 8";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "ColorReductionEssence ColorReductionEssence";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test pixelate multiple times.
   */
  @Test
  public void testMultiplePixelate() {
    String command = "pixelate 60\npixelate 60";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "pixelated pixelated";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test mosaic multiple times.
   */
  @Test
  public void testMultipleMosaic() {
    String command = "mosaic 1500\nmosaic 1500";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "mosaic mosaic";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test when calling blur, sepia and color reduction.
   */
  @Test
  public void testCombinationBlurSepiaColorReduction() {
    String command = "blur\n sepia\nreduce 8";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "blur Sepia ColorReduction";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test when calling grayScale, Sharpen and Dither.
   */
  @Test
  public void testCombinationGrayScaleSharpenDither() {
    String command = "grayscale\n sharpen\ndither 60";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "GrayScale sharpen ColorReductionEssence";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test when calling pixelate and patternGenerate.
   */
  @Test
  public void testCombinationPixelatePatternGenerate() {
    String command = "pixelate 60\n pattern";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "pixelated pattern";
    assertEquals(expected, stringBuilder.toString().trim());
  }

  /**
   * Test when calling pixelate and patternGenerate.
   */
  @Test
  public void testCombinationMosaicBlurDither() {
    String command = "mosaic 1500\n blur\ndither 16";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(in);
    controller = new ImageControllerImpl(new StringBuilder(), inputStreamReader, utility);
    try {
      controller.start(imageModel);
    } catch (IOException e) {
      stringBuilder.append(e.getMessage());
    }
    String expected = "mosaic blur ColorReductionEssence";
    assertEquals(expected, stringBuilder.toString().trim());
  }
}
