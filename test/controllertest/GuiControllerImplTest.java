package controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import controller.GuiController;
import controller.GuiControllerImpl;
import controller.Utilities;
import imagemodel.ImageModel;
import imagemodel.MockImageModel;
import imagemodel.MockUtilities;
import imagemodel.MockView;
import imagemodel.MockViewReturnsFalse;
import org.junit.Before;
import org.junit.Test;
import view.View;

import java.util.HashSet;
import java.util.Set;

/**
 * A test for the Gui controller.
 */
public class GuiControllerImplTest {
  private StringBuilder modelLogger;
  private StringBuilder viewLogger;
  private StringBuilder utilitiesLogger;
  private Utilities utilities;
  private GuiController guiController;
  private ImageModel imageModel;

  /**
   * Set up the tests before execution.
   */
  @Before
  public void setUp() {
    utilitiesLogger = new StringBuilder();
    modelLogger = new StringBuilder();
    imageModel = new MockImageModel(modelLogger);
    viewLogger = new StringBuilder();
    View view = new MockView(viewLogger);
    utilities = new MockUtilities(utilitiesLogger);
    guiController = new GuiControllerImpl(imageModel, utilities);
    guiController.setView(view);
  }

  /**
   * Testing the constructor for the GUI controller.
   */
  @Test
  public void testGuiControllerImpl() {
    assertNotNull(guiController);
  }
  
  /**
   * Test the gui controller when the image model passed to it is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGuiControllerModelNull() {
    guiController = new GuiControllerImpl(null, utilities);
  }
  

  /**
   * Test if process command throws an IllegalArgumentException if the command
   * passed is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testProcessCommandNull() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand(null);
  }

  /**
   * Test if the blur command works well.
   */
  @Test
  public void testProcessCommandBlur() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("blur");
    assertEquals("blur getImage", modelLogger.toString().trim());
  }

  /**
   * Test if the sharpen command works.
   */
  @Test
  public void testProcessCommandSharpen() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("sharpen");
    assertEquals("sharpen getImage", modelLogger.toString().trim());
  }

  /**
   * Test if the sepia command works.
   */
  @Test
  public void testProcessCommandSepia() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("sepia");
    assertEquals("Sepia getImage", modelLogger.toString().trim());
  }

  /**
   * Test if the grayscale command works.
   */
  @Test
  public void testProcessCommandGrayScale() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("grayscale");
    assertEquals("GrayScale getImage", modelLogger.toString().trim());
  }

  /**
   * Test if the pixelate command works.
   */
  @Test
  public void testProcessCommandPixelate() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("pixelate");
    assertEquals("pixelated getImage", modelLogger.toString().trim());
  }
  
  /**
   * Test if the pixelate command works when the value passed is negative.
   */
  @Test
  public void testProcessCommandPixelateWhenValueNegative() {
    GuiControllerImpl actualController = new GuiControllerImpl(imageModel, utilities);
    View mockView = new MockViewReturnsFalse(viewLogger);
    actualController.setView(mockView);
    actualController.processCommand("pixelate");
    assertEquals("setting features setting features getting image getting values "
        + "Please enter valid seed size before pixelating the image.", 
        viewLogger.toString().trim());
  }

  /**
   * Test if the mosaic command works well with the gui controller.
   */
  @Test
  public void testProcessCommandMosaic() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("mosaic");
    assertEquals("mosaic getImage", modelLogger.toString().trim());
  }
  
  /**
   * Test if the mosaic command works when the value passed is negative.
   */
  @Test
  public void testProcessCommandMosaicWhenValueNegative() {
    GuiControllerImpl actualController = new GuiControllerImpl(imageModel, utilities);
    View mockView = new MockViewReturnsFalse(viewLogger);
    actualController.setView(mockView);
    actualController.processCommand("mosaic");
    assertEquals("setting features setting features getting image getting values "
        + "Please enter valid seed size before mosaic", 
        viewLogger.toString().trim());
  }

  /**
   * Test if the mosaic command works well with the gui controller.
   */
  @Test
  public void testProcessCommandDither() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("dither");
    assertEquals("ColorReductionEssence getImage", modelLogger.toString().trim());
  }
  
  /**
   * Test if the dither command works when the value passed is negative.
   */
  @Test
  public void testProcessCommandDitherWhenValueNegative() {
    GuiControllerImpl actualController = new GuiControllerImpl(imageModel, utilities);
    View mockView = new MockViewReturnsFalse(viewLogger);
    actualController.setView(mockView);
    actualController.processCommand("dither");
    assertEquals("setting features setting features getting image getting values "
        + "Please enter valid seed size before dithering.", 
        viewLogger.toString().trim());
  }

  /**
   * Test if the pattern command works well with the gui controller.
   */
  @Test
  public void testProcessCommandPattern() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("pattern");
    assertEquals("pixelated generating image pattern getImage", modelLogger.toString().trim());
  }
  
  /**
   * Test if the pattern command when the seed value passed is negative.
   */
  @Test
  public void testProcessCommandPatternSquareNegative() {
    GuiControllerImpl actualController = new GuiControllerImpl(imageModel, utilities);
    View mockView = new MockViewReturnsFalse(viewLogger);
    actualController.setView(mockView);
    actualController.processCommand("pattern");
    assertEquals("setting features setting features getting image getting values "
        + "Please enter valid seed size before pixelation", 
        viewLogger.toString().trim());
  }

  /**
   * Test if the save pattern displays the correct message when a .
   */
  @Test
  public void testProcessCommandSaveTextNoPatternGenerated() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("savepattern");
    assertEquals(
        "setting features getting image " + "Please generate the pattern before you proceed.",
        viewLogger.toString().trim());
  }

  /**
   * Test if the save pattern calls the correct utility on calling pattern.
   */
  @Test
  public void testProcessCommandSaveText() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("pattern");
    actualController.processCommand("savepattern");
    assertEquals("write to file", utilitiesLogger.toString().trim());
  }

  /**
   * Test the replace color method.
   */
  @Test
  public void testReplaceColors() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("pattern");
    actualController.replaceColors(new int[] { 0, 0, 0 }, "DMC-0");
    assertEquals("pixelated generating image pattern getImage" + " Replacing colors.getImage",
        modelLogger.toString().trim());
    assertEquals("setting features getting image getting values saving pattern "
        + "text area setting legend setting proccessed image "
        + "setting proccessed image setting legend", viewLogger.toString().trim());
  }

  /**
   * Test the test replace color method when the color to replace is null.
   */
  @Test
  public void testReplaceColorToReplaceNull() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.replaceColors(null, "DMC-0");
    assertEquals("setting features " + "Please select the color you want to replace!",
        viewLogger.toString().trim());
  }

  /**
   * Test the test replace color method when the color to replace is null.
   */
  @Test
  public void testReplaceColorReplacementNull() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.replaceColors(new int[] { 0, 0, 0 }, null);
    assertEquals("setting features " + "Please select the color you want to "
        + "replace the selected color with.", viewLogger.toString().trim());
  }

  /**
   * Test pattern generation when custom colors are passed by the user.
   */
  @Test
  public void testPatternGenerateCustomColor() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    Set<String> colors = new HashSet<String>();
    colors.add("DMC-0");
    actualController.patternGenerateCustomColor(colors);
    assertEquals("pixelated Generate pattern" + " with new colors.getImage",
        modelLogger.toString().trim());
  }
  
  /**
   * Test if the pattern generation with custom colors throws an error 
   * when wrong pixellation value is passed.
   */
  @Test
  public void testPatternGenerateCustomColorNegative() {
    GuiControllerImpl actualController = new GuiControllerImpl(imageModel, utilities);
    View mockView = new MockViewReturnsFalse(viewLogger);
    actualController.setView(mockView);
    Set<String> colorsList = new HashSet<>();
    colorsList.add("DMC-0");
    actualController.patternGenerateCustomColor(colorsList);
    assertEquals("setting features setting features getting values "
        + "Please enter valid seed size before pixelation", 
        viewLogger.toString().trim());
  }

  /**
   * Test pattern generation when custom colors are passed by the user but no
   * color is selected by the user.
   */
  @Test
  public void testPatternGenerateCustomColorNoDmc() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    Set<String> colors = new HashSet<String>();
    actualController.patternGenerateCustomColor(colors);
    assertEquals("setting features Please " + "select some colors before you proceed.",
        viewLogger.toString().trim());
  }
  

  /**
   * Test if the delete pixel method is being called properly by the controller.
   */
  @Test
  public void testDeletePixel() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("pattern");
    actualController.deletePixel(new int[] { 0, 0, 0 });
    assertEquals("setting features getting image getting values "
        + "saving pattern text area setting " + "legend setting proccessed image",
        viewLogger.toString().trim());
  }

  /**
   * Test if the delete pixel method is being called properly by the controller.
   */
  @Test
  public void testDeletePixelColorNull() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processCommand("pattern");
    actualController.deletePixel(null);
    assertEquals("setting features getting image getting values saving pattern text "
        + "area setting legend setting proccessed image "
        + "Please choose a color pixel to be deleted!", viewLogger.toString().trim());
  }

  /**
   * Test if the batch scripts are processed properly.
   */
  @Test
  public void testProcessBatch() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processBatch("load goat.png\n" + "blur");
    
    assertEquals("setting features SUCCESS\n"
        + "Image loaded Image blurred Look for files in the "
        + "directory of this jar.", viewLogger.toString().trim());
  }
  
  /**
   * Test when the user doesn't enter commands to the batch script.
   */
  @Test
  public void testProcessEmptyBatch() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.processBatch("");
    assertEquals("setting features Please enter a valid batch text.", viewLogger.toString().trim());
  }
  
  /**
   * Test if the set image model works or not.
   */
  @Test
  public void testSetImageModel() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.setImageModel();
    assertEquals("setting features getting upload "
        + "path setting original panel", viewLogger.toString().trim());
  }
  
  /**
   * Test if the set image model works or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetViewOnNull() {
    GuiControllerImpl actualController = (GuiControllerImpl) guiController;
    actualController.setView(null);
  }
  
}
