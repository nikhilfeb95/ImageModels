package imagemodel.filter.kernel;

/**
 * An interface which represents a general kernel used by the sharpening and
 * blurring methods.
 */
public interface Kernel {
  /**
   * A method that retrieves the kernel.
   * 
   * @return A two dimensional matrix which represents the kernel.
   */
  float[][] getKernel();

  /**
   * Get the dimension of the kernel, as they can be 3X3, 5X5, etc.
   * 
   * @return The height of the kernel.
   */
  int getDimension();

  /**
   * Check the kernel type. This uses double dispatch to find types.
   * 
   * @param other The kernel to be compared with.
   * @return Whether the kernel belongs to the type or not.
   */
  boolean checkType(Kernel other);
}
