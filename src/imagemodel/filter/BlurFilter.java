package imagemodel.filter;

import imagemodel.clamp.Clamp;
import imagemodel.filter.kernel.Kernel;
import imagemodel.filter.kernel.KernelHelper;

/**
 * A class which represents the blur filters.
 */
public final class BlurFilter extends AbstractFilter {

  /**
   * A constructor for the blur filters.
   *
   * @param kernel The kernel to be used for the transformation.
   * @param clamp  The imagemodel.clamp to be used for the images.
   */
  public BlurFilter(Kernel kernel, Clamp clamp) {
    super(checkKernel(kernel), clamp);
  }

  /**
   * A static method to ensure the correct type of kernel is passed.
   *
   * @param kernel The kernel to be compared with.
   * @return The kernel if its ok.
   */
  private static Kernel checkKernel(Kernel kernel) {
    if (!kernel.checkType(KernelHelper.getBlurKernel())) {
      throw new IllegalArgumentException("Wrong kernel passed!");
    }
    return kernel;
  }

  @Override
  public String toString() {
    return "BlurFilter{}";
  }
}
