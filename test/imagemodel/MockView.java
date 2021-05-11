package imagemodel;

import controller.Feature;
import view.View;

import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * A mock view for used by the GUI controller.
 */
public class MockView implements View {
  private StringBuilder logger;
  
  /**
   * A default constructor for the mock view class.
   */
  public MockView(StringBuilder logger) {
    this.logger = logger;
  }
  
  @Override
  public void setFeatures(Feature features) {
    logger.append("setting features ");
    return;
  }

  @Override
  public BufferedImage getImage() {
    logger.append("getting image ");
    return new BufferedImage(10, 10, 10);
  }

  @Override
  public void setMessage(String message) {
    logger.append(message);
  }

  @Override
  public void setProcessedImage(BufferedImage image) {
    logger.append("setting proccessed image ");
  }

  @Override
  public int getValues(String message) {
    logger.append("getting values ");
    return 0;
  }

  @Override
  public void setLegend(Set<String> legend) {
    logger.append("setting legend ");
  }

  @Override
  public void reset() {
    logger.append("reset ");
  }

  @Override
  public String saveTextPattern() {
    logger.append("save text pattern ");
    return "save text";
  }

  @Override
  public void setPatternTextArea(String text) {
    logger.append("saving pattern text area ");
  }

  @Override
  public void modifyLegend(int index) {
    logger.append("modify legend ");
  }

  @Override
  public String getUploadFilePath() {
    logger.append("getting upload path ");
    return "test";
  }

  @Override
  public void setOriginalImagePanel(BufferedImage bufferedImage) {
    logger.append("setting original panel ");
    return;
  }

}
