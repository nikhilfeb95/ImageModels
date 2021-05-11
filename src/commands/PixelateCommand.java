package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for the mosaic operation.
 */
public class PixelateCommand implements ImageCommand {
  private final int square;

  /**
   * A constructor for the mosaic command.
   *
   * @param square the number of seeds for the command.
   */
  public PixelateCommand(int square) {
    if (square <= 0) {
      throw new IllegalArgumentException();
    }
    this.square = square;
  }

  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.pixelateImage(square);
  }
}
