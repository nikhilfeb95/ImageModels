package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for the mosaic operation.
 */
public class MosaicCommand implements ImageCommand {
  private final int seed;

  /**
   * A constructor for the mosaic command.
   *
   * @param seed the number of seeds for the command.
   */
  public MosaicCommand(int seed) {
    if (seed <= 0) {
      throw new IllegalArgumentException();
    }
    this.seed = seed;
  }

  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.mosaicImage(seed);
  }
}
