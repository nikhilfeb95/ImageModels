package imagemodel.patterngenerator;

import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A utility class which contains the image, legend and the text generated.
 */
public class PatternBeanImpl implements PatternBean {
  private String patternGenerated;
  private Set<String> legend;
  private Image image;

  /**
   * The constructor for the bean.
   * 
   * @param patternGenerated The pattern generated after the operation.
   * @param legend           The legend of the image generated.
   * @param image            The image generated after the operation.
   */
  public PatternBeanImpl(String patternGenerated, Set<String> legend, Image image) {
    if (patternGenerated == null || legend == null || image == null) {
      throw new IllegalArgumentException();
    }
    this.patternGenerated = patternGenerated;
    this.legend = legend;
    this.image = image;
  }

  @Override
  public String getPattern() {
    return patternGenerated;
  }

  // Sending a deep copy of the object.
  @Override
  public Set<String> getLegend() {
    return new LinkedHashSet<>(legend);
  }

  @Override
  public Image getImage() {
    // return a deep copy of the image.
    return new ImageImpl(image);
  }
}
