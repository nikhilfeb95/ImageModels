package imagemodel.colortransform;


import imagemodel.clamp.Clamp;

/**
 * A class which represents the grayScale color transformation.
 */
public final class GrayScale extends AbstractColorTransform {
  private static final double[][] grayScale;

  static {
    grayScale = new double[][] { { 0.2126, 0.7152, 0.0722 }, { 0.2126, 0.7152, 0.0722 },
        { 0.2126, 0.7152, 0.0722 } };
  }

  /**
   * A constructor for the Grayscale color transformation.
   * 
   * @param clamp The imagemodel.clamp used by the transformation.
   */
  public GrayScale(Clamp clamp) {
    super(grayScale, clamp);
  }

  @Override
  public String toString() {
    return "GrayScale{}";
  }
}
