package imagemodel.clamp;

/**
 * A interface which represents imagemodel.image clamping.
 */
public interface Clamp {
  /**
   * A method to imagemodel.clamp the imagemodel.image if it exceeds either the max/min thresholds.
   * 
   * @param value The value to be clamped.
   * @return The clamped value if it requires one.
   */
  int clampImage(int value);
}
