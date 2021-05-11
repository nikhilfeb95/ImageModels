package commands;


import imagemodel.ImageModel;

/**
 * A class which represents the command for color reduction.
 */
public class ReduceCommand implements ImageCommand {
  private final int channels;

  /**
   * A constructor for the reduce command.
   * 
   * @param channels the number of channels for the command.
   */
  public ReduceCommand(int channels) {
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
    imageModel.changeImageColorDensity(channels);
  }
}
