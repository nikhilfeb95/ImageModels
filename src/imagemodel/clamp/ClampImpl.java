package imagemodel.clamp;

/**
 * A helper class which is used to imagemodel.clamp images. Used by the color
 * transform and imagemodel.filter methods.
 */
public final class ClampImpl implements Clamp {
  private final int max;
  private final int min;

  /**
   * A constructor for the imagemodel.clamp class which takes in the max and min
   * values for the imagemodel.clamp.
   *
   * @param max The max value for the imagemodel.clamp.
   * @param min The min value for the imagemodel.clamp.
   */
  public ClampImpl(int max, int min) {
    if (max < min || max < 0 || min < 0) {
      throw new IllegalArgumentException();
    }
    this.max = max;
    this.min = min;
  }

  @Override
  public int clampImage(int value) {
    if (value > this.max) {
      return this.max;
    }
    return value < this.min ? this.min : value;
  }

  @Override
  public String toString() {
    return "ClampImpl{" + "MAX=" + max + ", MIN=" + min + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ClampImpl clamp = (ClampImpl) o;

    if (max != clamp.max) {
      return false;
    }
    return min == clamp.min;
  }

  @Override
  public int hashCode() {
    int result = max;
    result = 31 * result + min;
    return result;
  }
}
