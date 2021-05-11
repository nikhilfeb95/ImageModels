package imagemodel;

import imagemodel.image.Image;
import imagemodel.image.ImageImpl;
import imagemodel.patterngenerator.PatternBean;
import imagemodel.patterngenerator.PatternBeanImpl;

import java.util.HashSet;
import java.util.Set;



/**
 * A mock image model used by the test classes.
 */
public class MockImageModel implements ImageModel {
  private StringBuilder log;
  private PatternBean testBean;

  /**
   * The constructor for the mock image model that takes in the log for storing
   * info.
   * 
   * @param log The log to store method calls.
   */
  public MockImageModel(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException();
    }
    this.log = log;
    testBean = new PatternBeanImpl("test", new HashSet<>(), new ImageImpl(new int[3][3][3]));
  }

  @Override
  public void colorTransformImageToSepia() {
    log.append("Sepia ");
  }

  @Override
  public void colorTransformImageToGrayScale() {
    log.append("GrayScale ");
  }

  @Override
  public void changeImageColorDensity(int value) {
    log.append("ColorReduction ");
  }

  @Override
  public void changeImageColorDensityWithEssence(int value) {
    log.append("ColorReductionEssence ");

  }

  @Override
  public void blurImage() {
    log.append("blur ");

  }

  @Override
  public void sharpenImage() {
    log.append("sharpen ");

  }

  @Override
  public Image getImage() {
    log.append("getImage ");
    return new ImageImpl(new int[3][3][3]);
  }

  @Override
  public void pixelateImage(int value) {
    log.append("pixelated ");

  }

  @Override
  public void mosaicImage(int seedValue) {
    log.append("mosaic ");

  }

  @Override
  public String generatePatternWithPixelate() {
    log.append("pattern ");
    return "test";
  }

  @Override
  public PatternBean generatePatternImage() {
    log.append("generating image pattern ");
    return testBean;
  }

  @Override
  public PatternBean generatePatternCustomColors(Set<String> dmcColors) {
    log.append("Generate pattern with new colors.");
    return testBean;
  }

  @Override
  public void replaceImageWithNewColor(String replacementColor, int[] colorToReplace) {
    log.append("Replacing colors.");
  }

  @Override
  public void setImage(Image image) {
    return;
  }
  
  
}
