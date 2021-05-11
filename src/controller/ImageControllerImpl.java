package controller;

import commands.BlurCommand;
import commands.DitherCommand;
import commands.GrayScaleCommand;
import commands.ImageCommand;
import commands.MosaicCommand;
import commands.PixelateCommand;
import commands.ReduceCommand;
import commands.SepiaCommand;
import commands.SharpenCommand;
import imagemodel.ImageModel;
import imagemodel.ImageModelImpl;
import imagemodel.image.Image;

import java.io.IOException;
import java.util.Scanner;

/**
 * The implementation of a controller for the image.
 */
public class ImageControllerImpl implements ImageController {
  private final Appendable out;
  private final Readable in;
  private final Utilities utilities;

  /**
   * The constructor which takes in the appendable for output and readable for
   * input.
   *
   * @param out       Used for output.
   * @param in        Used for input.
   * @param utilities Utilities used for writing images and text documents.
   */
  public ImageControllerImpl(Appendable out, Readable in, Utilities utilities) {
    if (out == null || in == null || utilities == null) {
      throw new IllegalArgumentException();
    }
    this.out = out;
    this.in = in;
    this.utilities = utilities;
  }

  @Override
  public void start(ImageModel imageModel) throws IOException {
    if (imageModel == null) {
      throw new IllegalArgumentException();
    }
    // split the commands on new line.
    ImageCommand command = null;
    String pastCommands = "";
    Scanner scanner = new Scanner(in);
    String pattern = "";
    while (scanner.hasNext()) {
      String commands = scanner.nextLine().toLowerCase();
      if (commands.contains("load")) {
        String fileName = commands.split(" ")[1];
        imageModel = ImageModelImpl.ImageBuilder.getInstance().setImage(loadImage(fileName))
            .build();
      } else if (commands.contains("save")) {
        if (!pattern.equals("") && commands.contains("txt")) {
          writePatternToFile(pattern, commands);
          pattern = "";
        } else {
          saveImage(imageModel.getImage(), commands);
        }
      } else if (commands.contains("dither")) {
        command = new DitherCommand(extractValue(commands));
        out.append("Image dithered ");
      } else if (commands.contains("sepia")) {
        command = new SepiaCommand();
        out.append("Color Transformed to Sepia ");
      } else if (commands.contains("grayscale")) {
        command = new GrayScaleCommand();
        out.append("Color Transformed to GrayScale");
      } else if (commands.contains("sharpen")) {
        command = new SharpenCommand();
        out.append("Image Sharpened ");
      } else if (commands.contains("pixelate")) {
        pastCommands = commands;
        command = new PixelateCommand(extractValue(commands));
        out.append("Image pixelated ");
      } else if (commands.contains("mosaic")) {
        command = new MosaicCommand(extractValue(commands));
        out.append("Image Changed to Mosaic ");
      } else if (commands.contains("blur")) {
        command = new BlurCommand();
        out.append("Image blurred ");
      } else if (commands.contains("pattern")) {
        if (pastCommands.contains("pixelate")) {
          pattern = imageModel.generatePatternWithPixelate();
          out.append("Cross-stitch pattern generated ");
          pastCommands = "";
        } else {
          throw new IllegalStateException("Should've called pixelate before calling this.");
        }
      } else if (commands.contains("reduce")) {
        command = new ReduceCommand(extractValue(commands));
        out.append("Image reduced ");
      }
      if (command != null) {
        command.execute(imageModel);
        command = null;
      }
    }
  }

  /**
   * A helper method to load the image.
   *
   * @param fileName The image file to be loaded.
   * @return The image file loaded.
   */
  private Image loadImage(String fileName) throws IOException {
    out.append("Image loaded ");
    return utilities.readImage(fileName);
  }

  /**
   * A private helper method that is used to write the image to the destination
   * folder.
   *
   * @param image   The image to be written.
   * @param command The command passed from where the filename is to be extracted.
   * @throws IOException An input/output exception thrown by the write operation.
   */
  private void saveImage(Image image, String command) throws IOException {
    String fileName = command.split(" ")[1];
    out.append("Image saved. \n");
    utilities.writeImage(image, "./" + fileName);
  }

  /**
   * A helper method to write pattern to a text file.
   *
   * @param pattern The pattern to be written.
   * @param command The command to be passed.
   * @throws IOException Raised if issues in writing.
   */
  private void writePatternToFile(String pattern, String command) throws IOException {
    String fileName = command.split(" ")[1];
    out.append("Written to file.\n");
    utilities.writeToFile(pattern, "./" + fileName);
  }

  /**
   * A private helper to extract integer values.
   *
   * @param command The command passed.
   * @return The integer value passed along with the command.
   * @throws IOException If IO error occurs
   */
  private int extractValue(String command) throws IOException {
    int value = 0;
    try {
      value = Integer.parseInt(command.split(" ")[1]);
    } catch (NumberFormatException e) {
      out.append(e.getMessage());
    } catch (ArrayIndexOutOfBoundsException e) {
      out.append(String.format("You entered an invalid number : %s", e.getMessage()));
    }
    return value;
  }
}
