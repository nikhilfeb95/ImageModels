package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for sharpen.
 */
public class SharpenCommand implements ImageCommand {

  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.sharpenImage();
  }
}
