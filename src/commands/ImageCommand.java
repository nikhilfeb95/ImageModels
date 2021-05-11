package commands;

import imagemodel.ImageModel;

/**
 * A generic command for the command pattern for the image model class.
 */
public interface ImageCommand {

  /**
   * Execute the command defined in it.
   * @param imageModel The model from where to run the functions.
   */
  public void execute(ImageModel imageModel);
}
