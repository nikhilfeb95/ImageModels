package imagemodel.chunking.pixelate;

import imagemodel.chunking.ChunkingMethods;
import imagemodel.image.Image;

/**
 * An interface which represents the pixelation of images.
 */
public interface Pixelate extends ChunkingMethods {
  /**
   * A method used to pixelate images.
   * @param image The imagemodel.image to be pixelated.
   * @return The pixelated imagemodel.image.
   */
  Image pixelateImage(Image image);
}
