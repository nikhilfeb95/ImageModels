package imagemodel.filter;

import imagemodel.clamp.Clamp;
import imagemodel.filter.kernel.Kernel;
import imagemodel.filter.kernel.KernelHelper;

/**
 * A class which represents imagemodel.image sharpening.
 */
public final class SharpenFilter extends AbstractFilter {

  /**
   * A constructor for imagemodel.image sharpening imagemodel.filter.
   *
   * @param kernel The kernel for the imagemodel.filter.
   * @clamp The clamp which will cut-off pixels over the specified color range.
   */
  public SharpenFilter(Kernel kernel, Clamp clamp) {
    super(checkKernel(kernel), clamp);
  }

  /**
   * A static method to ensure the correct type of kernel is passed.
   * 
   * @param kernel The kernel to be compared with.
   * @return The kernel if its ok.
   */
  private static Kernel checkKernel(Kernel kernel) {
    if (!kernel.checkType(KernelHelper.getSharpenKernel())) {
      throw new IllegalArgumentException("Wrong kernel passed!");
    }
    return kernel;
  }

  @Override
  public String toString() {
    return "SharpenFilter{}";
  }
}
