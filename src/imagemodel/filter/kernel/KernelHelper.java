package imagemodel.filter.kernel;

/**
 * A helper class which provides static objects for comparing with concrete
 * types. Used by the double dispatched method checkType.
 */
public class KernelHelper {

  /**
   * Get a blur kernel type for checking types.Used by the double dispatched
   * method checkType.
   * 
   * @return The kernel of type BlurKernel.
   */
  public static Kernel getBlurKernel() {
    return new BlurKernel();
  }

  /**
   * Get a sharpen kernel for checking types.Used by the double dispatched method
   * checkType.
   * 
   * @return The kernel of type SharpenKernel.
   */
  public static Kernel getSharpenKernel() {
    return new SharpenKernel();
  }
}
