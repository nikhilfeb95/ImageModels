package imagemodel.filter.kernel;

/**
 * A kernel which represents sharpening.
 */
public final class SharpenKernel extends AbstractKernel {
  private static final float[][] sharpen;

  static {
    sharpen = new float[][] { { -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f },
        { -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f }, { -1 / 8f, 1 / 4f, 1, 1 / 4f, -1 / 8f },
        { -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f },
        { -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f } };
  }

  /**
   * A constructor for the kernel to sharpen images.
   */
  public SharpenKernel() {
    super(sharpen);
  }

  @Override
  public boolean checkType(Kernel other) {
    if (other == null) {
      return false;
    }
    if (other instanceof AbstractKernel) {
      AbstractKernel abstractKernel = (AbstractKernel) other;
      return abstractKernel.checkTypeSharpenKernel(this);
    }
    return false;
  }

  @Override
  protected boolean checkTypeSharpenKernel(Kernel sharpenKernel) {
    return true;
  }
}
