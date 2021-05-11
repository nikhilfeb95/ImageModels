package imagemodel.filter.kernel;

/**
 * A representation of a blur Kernel.
 */
public final class BlurKernel extends AbstractKernel {
  private static final float[][] blur;

  static {
    blur = new float[][] { { 1 / 16f, 1 / 8f, 1 / 16f }, { 1 / 8f, 1 / 4f, 1 / 8f },
        { 1 / 16f, 1 / 8f, 1 / 16f } };
  }

  /**
   * A constructor for the blur kernel.
   */
  public BlurKernel() {
    super(blur);
  }

  @Override
  protected boolean checkTypeBlurKernel(Kernel blurKernel) {
    return true;
  }

  @Override
  public boolean checkType(Kernel other) {
    if (other == null) {
      return false;
    }
    if (other instanceof AbstractKernel) {
      AbstractKernel abstractKernel = (AbstractKernel) other;
      return abstractKernel.checkTypeBlurKernel(this);
    }
    return false;
  }
}
