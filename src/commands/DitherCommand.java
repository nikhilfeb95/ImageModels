package commands;

import imagemodel.ImageModel;

/**
 * A class which represents the command for dither.
 */
public class DitherCommand implements ImageCommand {
  private final int channels;

  /**
   * A constructor for the dither command.
   *
   * @param channels the number of channels for the command.
   */
  public DitherCommand(int channels) {
    if (channels <= 0) {
      throw new IllegalArgumentException();
    }
    this.channels = channels;
  }

  @Override
  public void execute(ImageModel imageModel) {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    imageModel.changeImageColorDensityWithEssence(channels);
  }
}
