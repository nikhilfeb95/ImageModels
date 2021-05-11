package imagemodel.mosaic;

import imagemodel.image.Image;
import imagemodel.image.ImageImpl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * An implementation of the imagemodel.mosaic interface.
 */
public class MosaicImpl implements Mosaic {
  private final int seed;

  /**
   * A constructor for the imagemodel.mosaic operation that takes in the seed
   * value.
   *
   * @param seed The seed for selecting points in the imagemodel.image.
   */
  public MosaicImpl(int seed) {
    if (seed <= 0) {
      throw new IllegalArgumentException("wrong seed passed.");
    }
    this.seed = seed;
  }

  @Override
  public Image transformImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("null images not allowed");
    }
    int[][][] modifiedImage = image.getImage();
    int height = image.getHeight();
    int width = image.getWidth();
    Set<int[]> coordinates = generateSeeds(height - 1, width - 1);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // this holds the data to the coordinate of the smallest value and its
        // coordinates
        int[] minCoordinate = { Integer.MAX_VALUE, -1, -1 };
        // find distance to the closest coordinate
        for (int[] seedCoordinates : coordinates) {
          int distance = Math.abs((int) Math
              .sqrt(Math.pow((seedCoordinates[0] - i), 2) + Math.pow((seedCoordinates[1] - j), 2)));
          if (distance < minCoordinate[0]) {
            minCoordinate[0] = distance;
            minCoordinate[1] = seedCoordinates[0];
            minCoordinate[2] = seedCoordinates[1];
          }
        }
        // change color of this pixel for all channels with the coordinate of the min
        // seed.
        modifiedImage[i][j][0] = modifiedImage[minCoordinate[1]][minCoordinate[2]][0];
        modifiedImage[i][j][1] = modifiedImage[minCoordinate[1]][minCoordinate[2]][1];
        modifiedImage[i][j][2] = modifiedImage[minCoordinate[1]][minCoordinate[2]][2];
      }
    }
    return new ImageImpl(modifiedImage);
  }

  /**
   * Helper method to generate all the possible seeds.
   * 
   * @param height The height of the imagemodel.image.
   * @param width  The width of the imagemodel.image.
   * @return The set with all the coordinates for the seeds
   */
  private Set<int[]> generateSeeds(int height, int width) {
    Random random = new Random();
    Set<int[]> coordinates = new HashSet<int[]>();
    int i = 0;
    while (i < seed) {
      int randomRow = random.nextInt((height - 0) + 1);
      int randomCol = random.nextInt((width - 0) + 1);
      coordinates.add(new int[] { randomRow, randomCol });
      i++;
    }
    return coordinates;
  }
}
