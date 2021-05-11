package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for sepia.
 */
public class SepiaCommand implements ImageCommand {
  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.colorTransformImageToSepia();
  }
}
