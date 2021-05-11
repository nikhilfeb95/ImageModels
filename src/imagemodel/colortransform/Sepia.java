package imagemodel.colortransform;

import imagemodel.clamp.Clamp;

/**
 * A class which represents the sepia transformation.
 */
public final class Sepia extends AbstractColorTransform {
  private static final double[][] sepiaMatrix;

  static {
    sepiaMatrix = new double[][] { { 0.393, 0.769, 0.189 }, { 0.349, 0.686, 0.168 },
        { 0.272, 0.534, 0.131 } };
  }

  /**
   * A constructor for the Sepia color transformation.
   * 
   * @param clamp The imagemodel.clamp used by the transformation.
   */
  public Sepia(Clamp clamp) {
    super(sepiaMatrix, clamp);
  }

  @Override
  public String toString() {
    return "Sepia{}";
  }
}
