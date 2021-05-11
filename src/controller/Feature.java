package controller;

import java.util.Set;

/**
 * This interface represents all the set of features that the controller
 * provides.
 */
public interface Feature {

  /**
   * Process the batch text that is set by the user.
   *
   * @param text The text input passed by the user for the process.
   */
  void processBatch(String text);

  /**
   * A method to process the commands being passed to the controller.
   *
   * @param command The command for processing being passed to the controller.
   */
  void processCommand(String command);

  /**
   * Method to replace the pixels selected with the one selected by the user.
   * 
   * @param colorSelected  The color selected by the user.
   * @param colorToReplace The color to be replaced with.
   */
  void replaceColors(int[] colorSelected, String colorToReplace);

  /**
   * Delete the pixel from the image.
   * 
   * @param colorSelected The color which is to be deleted from the image.
   */
  void deletePixel(int[] colorSelected);

  /**
   * Generate pattern with the custom colors selected by the user.
   * 
   * @param dmcColors The custom colors passed by the user.
   */
  void patternGenerateCustomColor(Set<String> dmcColors);

  /**
   * Set the Image model with the updated image.
   */
  void setImageModel();
}
