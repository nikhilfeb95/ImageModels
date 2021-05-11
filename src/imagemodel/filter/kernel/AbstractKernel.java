package imagemodel.filter.kernel;

/**
 * A abstract kernel class that houses all the common methods for the kernels.
 */
public abstract class AbstractKernel implements Kernel {
  private float[][] kernel;

  /**
   * A constructor for the kernel which houses all the common methods.
   * 
   * @param kernel The kernel for the transformation.
   */
  AbstractKernel(float[][] kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException("Invalid kernel passed.");
    }
    this.kernel = kernel;
  }

  @Override
  public float[][] getKernel() {
    return this.kernel;
  }

  @Override
  public int getDimension() {
    return this.kernel.length;
  }

  /**
   * A method to check if the kernel is of the blur type. This is a double
   * dispatched method.
   *
   * @param blurKernel The kernel to be compared to.
   * @return Whether it belongs to a type or not.
   */
  boolean checkTypeBlurKernel(Kernel blurKernel) {
    return false;
  }

  /**
   * A method to check if the kernel is of the sharpen type. This is a double
   * dispatched method.
   *
   * @param sharpenKernel THe kernel to be compared to.
   * @return Whether they belong to the same type.
   */
  boolean checkTypeSharpenKernel(Kernel sharpenKernel) {
    return false;
  }
}
