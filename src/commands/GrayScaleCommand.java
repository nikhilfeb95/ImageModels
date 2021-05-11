package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for grayscale.
 */
public class GrayScaleCommand implements ImageCommand {
  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.colorTransformImageToGrayScale();
  }
}
