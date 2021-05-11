package controller;

import imagemodel.ImageModel;

import java.io.IOException;

/**
 * The interface for the controller of the imageModel class.
 */
public interface ImageController {
  /**
   * Start the controller and pass the model to the controller.
   * 
   * @param imageModel The model on which the commands will be applied.
   * @throws IOException Exception thrown if there is a problem reading or writing
   *                     the file.
   */
  void start(ImageModel imageModel) throws IOException;
}
