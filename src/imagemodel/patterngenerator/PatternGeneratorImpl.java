package imagemodel.patterngenerator;

import imagemodel.chunking.ChunkingMethods;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A class used to generate patterns from image chunking methods like
 * Pixellation.
 */
public class PatternGeneratorImpl implements PatternGenerator {


  /**
   * A private helper to calculate the closest color in the DMC range using the
   * redmean method.
   *
   * @param image     The imagemodel.image from where the pattern is to be
   *                  generated.
   * @param dmcValues The mapping of the dmc values to the RGB color.
   * @param row       The row of the pixel.
   * @param col       The col of the pixel.
   * @return The closest color to be written to the String.
   */
  private String closestColor(int[][][] image, Map<String, Integer[]> dmcValues, int row, int col) {
    String closestColor = "";
    double distance = Integer.MAX_VALUE;
    for (String code : dmcValues.keySet()) {
      double redmean = (image[row][col][0] + dmcValues.get(code)[0]) / 2;
      double deltaR = image[row][col][0] - dmcValues.get(code)[0];
      double deltaG = image[row][col][1] - dmcValues.get(code)[1];
      double deltaB = image[row][col][2] - dmcValues.get(code)[2];
      double currentDistance = Math.sqrt((2 + (redmean / 256)) * Math.pow(deltaR, 2)
          + 4 * Math.pow(deltaG, 2) + (2 + ((255 - redmean) / 256)) * Math.pow(deltaB, 2));
      if (currentDistance < distance) {
        distance = currentDistance;
        closestColor = code;
      }
    }
    return closestColor;
  }
  
  @Override
  public PatternBean generatePattern(Image image, ChunkingMethods chunkingMethods,
      Set<String> colors) {
    if (image == null || chunkingMethods == null) {
      throw new IllegalArgumentException("Image and chunking methods cannot be null.");
    }
    if (colors == null) {
      return generatePattern(image, chunkingMethods, DmcValues.getdmc());
    } else {
      return generatePattern(image, chunkingMethods, findColors(colors));
    }
  }

  private PatternBean generatePattern(Image image, ChunkingMethods chunkingMethods,
      Map<String, Integer[]> dmcValues) {
    if (image == null || chunkingMethods == null) {
      throw new IllegalArgumentException();
    }
    int characterCode = 33;
    Map<String, Character> legend = new TreeMap<>(new DmcValuesComparator());
    StringBuilder stringBuilder = new StringBuilder();
    int height = image.getHeight();
    int width = image.getWidth();
    int[][][] imageToPattern = image.getImage();
    stringBuilder.append(String.format("%d X %d \n", height, width));
    int row = 0;
    while (row < height) {
      int next = -1;
      int col = 0;
      while (col < width) {
        String closestColor = closestColor(imageToPattern, dmcValues, row, col);
        if (legend.containsKey(closestColor)) {
          stringBuilder.append(legend.get(closestColor));
        } else {
          legend.put(closestColor, (char) characterCode);
          stringBuilder.append((char) characterCode);
          if (characterCode == 127) {
            characterCode = 160;
          }
          characterCode++;
        }
        int[] superPixel = chunkingMethods.calculatePixels(imageToPattern, row, col,
            image.getHeight(), image.getWidth());
        paintPixelsWithClosestColors(imageToPattern, row, row + superPixel[0], col,
            col + superPixel[1], dmcValues, closestColor, height, width);
        col += superPixel[1];
        if (next == -1) {
          next = superPixel[0];
        }
      }
      row += next;
      stringBuilder.append("\n");
    }
    // append the legend to the string builder.
    stringBuilder.append("LEGEND\n");
    for (String codes : legend.keySet()) {
      stringBuilder.append(String.format("%c DMC-%s\n", legend.get(codes), codes));
    }
    // return the bean to the controller.
    return new PatternBeanImpl(stringBuilder.toString(), legend.keySet(),
        new ImageImpl(imageToPattern));
  }

  /**
   * Paint the pixels with the closest specified color.
   */
  private void paintPixelsWithClosestColors(int[][][] image, int startRow, int endRow, int startCol,
      int endCol, Map<String, Integer[]> dmcValues, String code, int height, int width) {
    // Paint the pixels of the original image here.
    for (int i = startRow; i < endRow && i < height; i++) {
      for (int j = startCol; j < endCol && j < width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = dmcValues.get(code)[k];
        }
      }
    }
  }

  @Override
  public Image replacePixelsFromImage(Image image, String replacementColor, int[] colorToReplace,
      ChunkingMethods chunkingMethods) {
    if (image == null || replacementColor == null || chunkingMethods == null) {
      throw new IllegalArgumentException();
    }
    int[][][] imageToReplace = image.getImage();
    int height = image.getHeight();
    int width = image.getWidth();
    // Only replace if we find the dmc equivalent.
    if (replacementColor.length() != 0) {
      int row = 0;
      while (row < height) {
        int next = -1;
        int col = 0;
        while (col < width) {
          int[] superPixel = chunkingMethods.calculatePixels(imageToReplace, row, col,
              image.getHeight(), image.getWidth());
          // Check if the color of the current super pixel is equal to the one being
          // passed.
          if (imageToReplace[row][col][0] == colorToReplace[0]
              && imageToReplace[row][col][1] == colorToReplace[1]
              && imageToReplace[row][col][0] == colorToReplace[0]) {
            paintPixelsWithClosestColors(imageToReplace, row, row + superPixel[0], col,
                col + superPixel[1], DmcValues.getdmc(), replacementColor, height, width);
          }
          col += superPixel[1];
          if (next == -1) {
            next = superPixel[0];
          }
        }
        row += next;
      }
    }
    return new ImageImpl(imageToReplace);
  }

  /**
   * Make an alternate list of dmc for the custom colors passed by the user.
   * 
   * @param colors The custom colors passed by the user.
   * @return The Map of colors and their corresponding values.
   */
  private Map<String, Integer[]> findColors(Set<String> colors) {
    Map<String, Integer[]> values = new HashMap<>();
    Map<String, Integer[]> dmcValues = DmcValues.getdmc();
    for (String color : colors) {
      Integer[] colorToAdd = dmcValues.get(color);
      values.putIfAbsent(color, colorToAdd);
    }
    return values;
  }
}
