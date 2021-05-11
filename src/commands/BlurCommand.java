package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for blur.
 */
public class BlurCommand implements ImageCommand {

  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.blurImage();
  }
}
