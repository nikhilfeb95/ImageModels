package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * An image panel that represents the processed image i.e. this panel holds the
 * processed image.
 */
class ProcessedImagePanel extends JPanel {

  private static final long serialVersionUID = 2604035170657451853L;

  private BufferedImage processedImage;

  private JLabel label;

  private JScrollPane imageScrollPane;
  private NewMainFrame newMainFrame;

  /**
   * The default constructor for the processed image panel. All inits are done
   * here.
   */
  ProcessedImagePanel() {
    processedImage = null;
    this.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    imageScrollPane = new JScrollPane();
    javax.swing.GroupLayout processedImagePanelLayout = new javax.swing.GroupLayout(this);
    this.setLayout(processedImagePanelLayout);
    processedImagePanelLayout.setHorizontalGroup(
        processedImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE));
    processedImagePanelLayout.setVerticalGroup(
        processedImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE));
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
      this.processedImage = image;
      imageScrollPane = new JScrollPane(label);
      label.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          getPixelColor(e);
        }
      });
      imageScrollPane.setBorder(BorderFactory.createTitledBorder("Processed Image"));
      Dimension dimension = new Dimension(400 < image.getWidth() ? 400 : image.getWidth(),
          400 < image.getHeight() ? 400 : image.getHeight());
      imageScrollPane.setSize(dimension);
      this.setPreferredSize(dimension);
      imageScrollPane.setOpaque(false);
      this.add(imageScrollPane);
      this.setOpaque(false);
      this.setVisible(true);
      this.updateUI();
    } else {
      this.remove(imageScrollPane);
    }
  }

  /**
   * Get the pixel color from the processed image.
   * 
   * @param event The event which triggers the get Pixel method, which is a mouse
   *              click in the label which contains the image.
   */
  private void getPixelColor(MouseEvent event) {
    if (event.getSource() == this.label) {
      if (processedImage.getWidth() > event.getX() && processedImage.getHeight() > event.getY()) {
        final int pixel = processedImage.getRGB(event.getX(), event.getY());
        Color color = new Color(pixel);
        int[] rgb = new int[] { color.getRed(), color.getGreen(), color.getBlue() };
        JLabel label = new JLabel(String.format("Color is R:%d,G:%d,B:%d", color.getRed(),
            color.getGreen(), color.getBlue()));
        label.setForeground(color);
        JOptionPane.showMessageDialog(this, label);
        newMainFrame.update(rgb);
      }
    }
  }

  /**
   * A method to get the processed Image.
   * 
   * @return The processed image.
   */
  BufferedImage getProcessedImage() {
    return this.processedImage;
  }

  /**
   * Get the label where the processed image is placed.
   * 
   * @return The label which contains the processed image.
   */
  JLabel getProcessedImageLabel() {
    return this.label;
  }

  /**
   * Set the main frame reference in this panel. This notifies the main frame if
   * there are changes to the color picked.
   */
  void setMainFrame(NewMainFrame mainFrame) {
    this.newMainFrame = mainFrame;
  }
}
