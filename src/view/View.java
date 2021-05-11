package view;

import controller.Feature;

import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * The interface for the view class.
 */
public interface View {

  /**
   * Set the features in the view. This calls all the features provided.
   * 
   * @param features The list of features the controller provides.
   */
  void setFeatures(Feature features);

  /**
   * Get an image from the view which is loaded by the file chooser.
   * 
   * @return Return the buffered image to the controller, which inturn passes it
   *         to the model.
   */
  BufferedImage getImage();

  /**
   * Error messages passed by the controller to the view.
   * 
   * @param message The message being passed by the controller to the view.
   */
  void setMessage(String message);

  /**
   * Set the image to the panel after processing.
   * 
   * @param image The image to be painted to the GUI.
   */
  void setProcessedImage(BufferedImage image);

  /**
   * A method to get value from the user for values operations such as mosaic,
   * dither and pixellate.
   * 
   * @param message The message that is displayed to the user to get the value.
   * @return The value passed by the user.
   */
  int getValues(String message);

  /**
   * A method to set the legend after pattern generation.
   * 
   * @param legend The legend to be set in the GUI after pattern generation.
   */
  void setLegend(Set<String> legend);

  /**
   * A method to reset the UI components after some functions.
   */
  void reset();

  /**
   * Method to write the string text generated onto a text file.
   * 
   * @return The file path of the file to be saved. The file is saved by the
   *         controller.
   */
  String saveTextPattern();

  /**
   * Set the pattern to the new text area and pop the frame up.
   * 
   * @param text The text that has to be written to the text area passed by the
   *             controller.
   */
  void setPatternTextArea(String text);

  /**
   * Modify the legend being rendered in the view after pattern generation.
   * 
   * @param index The index of the view to be modified.
   */
  void modifyLegend(int index);

  /**
   * Get the file path of the file passed by the user.
   * 
   * @return The path of the file being uploaded by the user.
   */
  String getUploadFilePath();

  /**
   * Set the original image panel with the uploaded image.
   * 
   * @param bufferedImage The image to be set in the panel.
   */
  void setOriginalImagePanel(BufferedImage bufferedImage);
}
