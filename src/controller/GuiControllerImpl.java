package controller;

import imagemodel.ImageModel;
import imagemodel.image.Image;
import imagemodel.patterngenerator.DmcValues;
import imagemodel.patterngenerator.PatternBean;
import view.View;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

/**
 * A controller for the GUI. This class acts as the intermediary between the
 * model and the view and everything goes through it.
 */
public class GuiControllerImpl implements GuiController, Feature {
  private View view;
  private ImageModel imageModel;
  private Utilities utilities;

  // This holds the text generated by the pattern generate method. 
  //It's a cache to prevent recomputation.
  private String textGenerated;
  // This holds the dmc values generated by the image.
  private Set<String> dmcValues;

  /**
   * The constructor for the controller.
   * 
   * @param imageModel The imageModel being passed to the controller.
   * @param utilities The utility class used for writing/reading files images.
   */
  public GuiControllerImpl(ImageModel imageModel, Utilities utilities) {
    if (imageModel == null || utilities == null) {
      throw new IllegalArgumentException();
    }
    this.imageModel = imageModel;
    this.utilities = utilities;
  }

  @Override
  public void setImageModel() {
    String path = view.getUploadFilePath();
    try {
      Image image = utilities.readImage(path);
      imageModel.setImage(image);
      view.setOriginalImagePanel(utilities.imageToBuffer(image));
    } catch (IOException e) {
      view.setMessage("OOPS we had an issue reading your image!");
    }
  }

  @Override
  public void processCommand(String command) {
    if (command == null) {
      throw new IllegalArgumentException();
    }
    if (view.getImage() == null) {
      view.setMessage("Please upload an image before starting an operation.");
      return;
    }
    int value;
    switch (command.toLowerCase()) {
      case "blur":
        imageModel.blurImage();
        break;
      case "sharpen":
        imageModel.sharpenImage();
        break;
      case "pixelate":
        value = view.getValues("Please enter a positive integer for the pixel square size.");
        if (value <= -1) {
          view.setMessage("Please enter valid seed size before pixelating the image.");
          return;
        }
        imageModel.pixelateImage(value);
        break;
      case "grayscale":
        imageModel.colorTransformImageToGrayScale();
        break;
      case "sepia":
        imageModel.colorTransformImageToSepia();
        break;
      case "mosaic":
        value = view.getValues("Please enter a positive integer for the number of seeds.");
        if (value <= -1) {
          view.setMessage("Please enter valid seed size before mosaic");
          return;
        }
        view.setMessage("Please wait for processing as it may take some time. " 
            +
                "Press Ok to continue.");
        imageModel.mosaicImage(value);
        break;
      case "pattern":
        value = view.getValues("Please enter a positive integer for the pixel square size.");
        if (value <= -1) {
          view.setMessage("Please enter valid seed size before pixelation");
          return;
        }
        imageModel.pixelateImage(value);
        PatternBean bean = imageModel.generatePatternImage();
        this.textGenerated = bean.getPattern();
        view.setPatternTextArea(textGenerated);
        this.dmcValues = bean.getLegend();
        if (this.dmcValues != null) {
          view.setLegend(this.dmcValues);
        }
        break;
      case "dither":
        value = view.getValues("Please enter a positive integer for the number of channels.");
        if (value <= -1) {
          view.setMessage("Please enter valid seed size before dithering.");
          return;
        }
        imageModel.changeImageColorDensityWithEssence(value);
        break;
      case "savepattern":
        if (textGenerated == null || textGenerated.length() == 0) {
          view.setMessage("Please generate the pattern before you proceed.");
          return;
        }
        try {
          String path = view.saveTextPattern();
          utilities.writeToFile(this.textGenerated, path);
        } catch (IOException e) {
          view.setMessage(
              "Oops we had trouble saving the text file. Did you enter the correct file name?");
        }
        return;
      default:
        return;
    }
    Image image = imageModel.getImage();
    view.setProcessedImage(
        ImageUtilities.getBufferedImage(image.getImage(), image.getWidth(), image.getHeight()));
  }

  @Override
  public void replaceColors(int[] colorToReplace, String newColorToReplaceWith) {
    if (newColorToReplaceWith == null) {
      view.setMessage("Please select the color you want to replace the selected color with.");
      return;
    }
    if (colorToReplace == null) {
      view.setMessage("Please select the color you want to replace!");
      return;
    }
    imageModel.replaceImageWithNewColor(newColorToReplaceWith, colorToReplace);
    Image image = imageModel.getImage();
    view.setProcessedImage(
        ImageUtilities.getBufferedImage(image.getImage(), image.getWidth(), image.getHeight()));
    String toReplace = DmcValues.findDmcEquivalent(colorToReplace);
    // replace the legend in the image set.
    this.dmcValues.remove(toReplace);
    this.dmcValues.add(newColorToReplaceWith);
    view.setLegend(dmcValues);
    // replace the legend in the text file as well.
    this.textGenerated = this.textGenerated.replaceAll(toReplace, newColorToReplaceWith);
  }

  @Override
  public void patternGenerateCustomColor(Set<String> dmcColors) {
    if (dmcColors == null || dmcColors.size() == 0) {
      view.setMessage("Please select some colors before you proceed.");
      return;
    }
    int value = view.getValues("Please enter a positive integer for the pixel square size.");
    if (value <= -1) {
      view.setMessage("Please enter valid seed size before pixelation");
      return;
    }
    imageModel.pixelateImage(value);
    PatternBean bean = imageModel.generatePatternCustomColors(dmcColors);
    this.textGenerated = bean.getPattern();
    view.setPatternTextArea(textGenerated);
    this.dmcValues = bean.getLegend();
    if (this.dmcValues != null) {
      view.setLegend(this.dmcValues);
    }
    Image image = imageModel.getImage();
    view.setProcessedImage(
        ImageUtilities.getBufferedImage(image.getImage(), image.getWidth(), image.getHeight()));
  }

  @Override
  public void deletePixel(int[] colorSelected) {
    if (colorSelected == null) {
      view.setMessage("Please choose a color pixel to be deleted!");
      return;
    }
    String selectedDmc = DmcValues.findDmcEquivalent(colorSelected);
    this.textGenerated = this.textGenerated.replace("DMC-" + selectedDmc, "BLANK!");
    int indexCounter = 0;
    for (String item : dmcValues) {
      if (item.equals(selectedDmc)) {
        view.modifyLegend(indexCounter);
        return;
      }
      indexCounter++;
    }
  }

  @Override
  public void processBatch(String text) {
    if (text == null || text.length() == 0) {
      view.setMessage("Please enter a valid batch text.");
      return;
    }
    // Make the BatchController here and let that controller handle the inputs.
    Readable in = new StringReader(text);
    Appendable out = new StringBuilder();
    ImageController imageController = new ImageControllerImpl(out, in, utilities);
    try {
      // Pass the image model to the image controller.
      imageController.start(this.imageModel);
    } catch (IOException ioException) {
      view.setMessage("Had a problem reading your file. "
          + "Is it in the folder where the software is working?");
      return;
    } catch (IllegalStateException stateException) {
      view.setMessage("Please pixelate before you generate a pattern.");
      return;
    }
    view.setMessage("SUCCESS\n" + out.toString() + "Look for files in the directory of this jar.");
  }

  @Override
  public void setView(View view) {
    if (view == null)  {
      throw new IllegalArgumentException();
    }
    this.view = view;
    this.view.setFeatures(this);
  }
}
