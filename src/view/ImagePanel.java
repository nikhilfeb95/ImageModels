package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * A class that defines the panel where the Image is written to.
 */
class ImagePanel extends JPanel {
  private static final long serialVersionUID = -4050512017750516382L;
  private BufferedImage bufferedImage;
  private JLabel label;
  private JScrollPane imageScrollPane;

  /**
   * The default constructor for the ImagePanel class. By default it has no
   * images.
   */
  ImagePanel() {
    bufferedImage = null;
    this.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    imageScrollPane = new JScrollPane();
    javax.swing.GroupLayout originalImagePanelLayout = new javax.swing.GroupLayout(this);
    this.setLayout(originalImagePanelLayout);
    originalImagePanelLayout.setHorizontalGroup(
        originalImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE));
    originalImagePanelLayout.setVerticalGroup(
        originalImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
  }

  /**
   * A getter for the buffered Image currently in the panel.
   * 
   * @return The buffered image stored in the image panel.
   */
  BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  /**
   * Setter for the Image panel.
   * 
   * @param image The image to be painted in the panel.
   */
  void setBufferedImage(BufferedImage image) {
    if (image != null) {
      this.remove(imageScrollPane);
      label = new JLabel(new ImageIcon(image));
      this.bufferedImage = image;
      imageScrollPane = new JScrollPane(label);
      imageScrollPane.setBorder(BorderFactory.createTitledBorder("Original Image"));

      Dimension dimension = new Dimension(400 < image.getWidth() ? 400 : image.getWidth(),
          400 < image.getHeight() ? 400 : image.getHeight());
      imageScrollPane.setSize(dimension);
      imageScrollPane.setAutoscrolls(true);
      this.setPreferredSize(dimension);
      imageScrollPane.setOpaque(false);
      this.add(imageScrollPane);
      this.setOpaque(false);
      this.setVisible(true);
      this.updateUI();
    } else {
      this.setVisible(false);
    }
  }
}
