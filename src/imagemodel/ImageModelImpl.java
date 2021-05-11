package imagemodel;

import imagemodel.chunking.pixelate.Pixelate;
import imagemodel.chunking.pixelate.PixelateImpl;
import imagemodel.clamp.Clamp;
import imagemodel.clamp.ClampImpl;
import imagemodel.colordensity.ColorDensity;
import imagemodel.colordensity.ColorDensityImpl;
import imagemodel.colortransform.ColorTransform;
import imagemodel.colortransform.GrayScale;
import imagemodel.colortransform.Sepia;
import imagemodel.filter.BlurFilter;
import imagemodel.filter.Filter;
import imagemodel.filter.SharpenFilter;
import imagemodel.filter.kernel.BlurKernel;
import imagemodel.filter.kernel.Kernel;
import imagemodel.filter.kernel.SharpenKernel;
import imagemodel.image.Image;
import imagemodel.image.ImageImpl;
import imagemodel.mosaic.Mosaic;
import imagemodel.mosaic.MosaicImpl;
import imagemodel.patterngenerator.PatternBean;
import imagemodel.patterngenerator.PatternGenerator;
import imagemodel.patterngenerator.PatternGeneratorImpl;

import java.util.Set;

/**
 * A class which implements the ImageModel interface. This class serves as the
 * central class which controls all the other functionality.
 */
public class ImageModelImpl implements ImageModel {
  private Image image;
  private Filter blurFilter;
  private Filter sharpenFilter;
  private ColorDensity colorDensity;
  private ColorTransform sepia;
  private ColorTransform grayScale;
  private Pixelate pixelate;
  private Mosaic mosaic;
  private PatternGenerator patternGenerator;

  /**
   * A constructor for the Image model class. All **verifications** are done by
   * the builder before passing it to this constructor.
   *
   * @param builder A builder for the Image Model class.
   */
  private ImageModelImpl(ImageBuilder builder) {
    if (builder == null) {
      throw new IllegalArgumentException();
    }
    this.image = builder.image;
    this.sharpenFilter = builder.sharpenFilter;
    this.blurFilter = builder.blurFilter;
    this.colorDensity = builder.colorDensity;
    this.sepia = builder.sepia;
    this.grayScale = builder.grayScale;
    this.pixelate = builder.pixelate;
    this.mosaic = builder.mosaic;
    this.patternGenerator = builder.patternGenerator;
  }

  @Override
  public void colorTransformImageToSepia() {
    this.image = sepia.colorTransform(this.image);
  }

  @Override
  public void colorTransformImageToGrayScale() {
    this.image = grayScale.colorTransform(this.image);
  }

  @Override
  public void changeImageColorDensity(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("value cannot be zero or negative");
    }
    this.colorDensity = new ColorDensityImpl(value);
    this.image = colorDensity.transformImage(this.image);
  }

  @Override
  public void changeImageColorDensityWithEssence(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("value cannot be zero or negative");
    }
    this.colorDensity = new ColorDensityImpl(value);
    this.image = colorDensity.transformImageWithEssence(this.image);
  }

  @Override
  public void blurImage() {
    this.image = blurFilter.filterImage(image);
  }

  @Override
  public void sharpenImage() {
    this.image = sharpenFilter.filterImage(image);
  }

  @Override
  public void pixelateImage(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("value cannot be zero or negative");
    }
    this.pixelate = new PixelateImpl(value);
    this.image = pixelate.pixelateImage(image);
  }

  @Override
  public void mosaicImage(int seedValue) {
    if (seedValue <= 0) {
      throw new IllegalArgumentException("seed value cannot be zero or negative");
    }
    this.mosaic = new MosaicImpl(seedValue);
    this.image = mosaic.transformImage(image);
  }

  @Override
  public String generatePatternWithPixelate() {
    return patternGenerator.generatePattern(image, pixelate, null).getPattern();
  }

  @Override
  public PatternBean generatePatternImage() {
    PatternBean patternBean = patternGenerator.generatePattern(image, pixelate, null);
    this.image = patternBean.getImage();
    return patternBean;
  }

  @Override
  public PatternBean generatePatternCustomColors(Set<String> dmcColors) {
    if (dmcColors == null) {
      throw new IllegalArgumentException();
    }
    PatternBean patternBean = patternGenerator.generatePattern(image, pixelate, dmcColors);
    this.image = patternBean.getImage();
    return patternBean;
  }

  @Override
  public void replaceImageWithNewColor(String replacementColor, int[] colorToReplace) {
    if (replacementColor == null || colorToReplace == null) {
      throw new IllegalArgumentException();
    }
    this.image = patternGenerator.replacePixelsFromImage(image, replacementColor, colorToReplace,
        pixelate);
  }

  @Override
  public Image getImage() {
    // This sends a deep copy.
    return new ImageImpl(this.image);
  }

  @Override
  public String toString() {
    return "ImageModelImpl{" + "imagemodel.image=" + image + ", blurFilter=" + blurFilter
        + ", sharpenFilter=" + sharpenFilter + ", eightColor=" + colorDensity + ", sepia=" + sepia
        + ", grayScale=" + grayScale + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ImageModelImpl that = (ImageModelImpl) o;

    if (!image.equals(that.image)) {
      return false;
    }
    if (!blurFilter.equals(that.blurFilter)) {
      return false;
    }
    if (!sharpenFilter.equals(that.sharpenFilter)) {
      return false;
    }
    if (!colorDensity.equals(that.colorDensity)) {
      return false;
    }
    if (!sepia.equals(that.sepia)) {
      return false;
    }
    return grayScale.equals(that.grayScale);
  }

  @Override
  public int hashCode() {
    int result = image.hashCode();
    result = 31 * result + blurFilter.hashCode();
    result = 31 * result + sharpenFilter.hashCode();
    result = 31 * result + colorDensity.hashCode();
    result = 31 * result + sepia.hashCode();
    result = 31 * result + grayScale.hashCode();
    return result;
  }

  /**
   * A static builder for the ImageModel class.
   */
  public static class ImageBuilder {
    private Image image;
    private Kernel blurKernel;
    private Kernel sharpenKernel;
    private Filter blurFilter;
    private Filter sharpenFilter;
    private ColorDensity colorDensity;
    private ColorTransform sepia;
    private ColorTransform grayScale;
    private Clamp clamp;
    private PatternGenerator patternGenerator;
    private Pixelate pixelate;
    private Mosaic mosaic;

    /**
     * A default constructor for the builder class.
     */
    private ImageBuilder() {
    }

    /**
     * A class which provides the builder to the end user.
     * 
     * @return The ImageBuilder instance.
     */
    public static ImageBuilder getInstance() {
      return new ImageBuilder();
    }

    /**
     * Set the imagemodel.image for the model.
     * 
     * @param image The imagemodel.image to be used.
     * @return The builder class reference.
     */
    public ImageBuilder setImage(Image image) {
      if (image == null) {
        throw new IllegalArgumentException();
      }
      this.image = image;
      return this;
    }

    /**
     * Set the blur kernel for filtering.
     * 
     * @param blurKernel The kernel for blurring.
     * @return The builder class reference.
     */
    public ImageBuilder setBlurKernel(Kernel blurKernel) {
      if (blurKernel == null) {
        throw new IllegalArgumentException();
      }
      this.blurKernel = blurKernel;
      return this;
    }

    /**
     * Set the kernel for sharpening.
     * 
     * @param sharpenKernel The kernel for sharpening.
     * @return The builder class reference.
     */
    public ImageBuilder setSharpenKernel(Kernel sharpenKernel) {
      if (sharpenKernel == null) {
        throw new IllegalArgumentException();
      }
      this.sharpenKernel = sharpenKernel;
      return this;
    }

    /**
     * Set the imagemodel.clamp values for the imagemodel.image.
     * 
     * @param clamp The imagemodel.clamp to be used.
     * @return The builder class reference.
     */
    public ImageBuilder setClamp(Clamp clamp) {
      if (clamp == null) {
        throw new IllegalArgumentException();
      }
      this.clamp = clamp;
      return this;
    }

    /**
     * A method to build the Imagemodel object. All verifications are done here.
     * 
     * @return The object of the ImageModelImpl class.
     */
    public ImageModelImpl build() {
      if (image == null) {
        throw new IllegalArgumentException("Image can't be null!");
      }
      if (clamp == null) {
        this.clamp = new ClampImpl(255, 0);
      }
      if (sharpenKernel == null) {
        this.sharpenKernel = new SharpenKernel();
      }
      if (blurKernel == null) {
        this.blurKernel = new BlurKernel();
      }
      this.sharpenFilter = new SharpenFilter(sharpenKernel, clamp);
      this.blurFilter = new BlurFilter(blurKernel, clamp);
      this.sepia = new Sepia(clamp);
      this.grayScale = new GrayScale(clamp);
      this.colorDensity = new ColorDensityImpl(8);
      this.pixelate = new PixelateImpl(50);
      this.mosaic = new MosaicImpl(500);
      this.patternGenerator = new PatternGeneratorImpl();
      return new ImageModelImpl(this);
    }
  }

  @Override
  public void setImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;
  }
}
