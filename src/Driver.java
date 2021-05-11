import controller.BatchUtilities;
import controller.GuiController;
import controller.GuiControllerImpl;
import controller.ImageController;
import controller.ImageControllerImpl;
import controller.Utilities;
import imagemodel.ImageModel;
import imagemodel.ImageModelImpl;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;
import view.NewMainFrame;
import view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The driver class for the project Image models.
 */
public class Driver {

  /**
   * The main function for the program.
   *
   * @param args The arguments being passed to the main function.
   */
  public static void main(String[] args) {
    try {
      // Construct the model.
      int[][][] imageArray = {};
      Image image = new ImageImpl(imageArray);
      ImageModel imageModel = ImageModelImpl.ImageBuilder.getInstance().setImage(image).build();
      String argument = args[0];
      switch (argument) {
        case "-script":
          String fileName = args[1];
          File file = new File(fileName);
          InputStream inputStream = new FileInputStream(file);
          InputStreamReader in = new InputStreamReader(inputStream);
          StringBuilder stringBuilder = new StringBuilder();
          Utilities utilities = new BatchUtilities();
          // Construct the controller.
          ImageController imageController = new ImageControllerImpl(stringBuilder, in, utilities);
          System.out.println("Processing your image..Please wait for a few moments.");
          // Relinquish control to the controller.
          imageController.start(imageModel);

          // Printing after the code ends.
          System.out.println(stringBuilder.toString().trim());
          System.out.println("The program has processed your images.");
          System.out.println("Press f to exit.");
          Scanner scanner = new Scanner(System.in);
          String exitString = "";
          while (!exitString.equals("f")) {
            exitString = scanner.next();
          }
          break;
        case "-interactive":
          //Construct Controller.
          GuiController controller = new GuiControllerImpl(imageModel, new BatchUtilities());
          //Construct the view.
          View view = new NewMainFrame();
          controller.setView(view);
          break;
        default:
          System.out.println("You passed the wrong option..Program terminating!");
          break;
      }
    } catch (IOException e) {
      System.out.println("Oops you ran into a problem!");
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println("Oops you ran into a problem!");
      System.out.println(e.getMessage());
    } catch (IllegalStateException e) {
      System.out.println("Please pixelate before pattern generation");
      System.out.println(e.getMessage());
    }
  }
}
