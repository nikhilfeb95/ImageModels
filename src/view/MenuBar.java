package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * A class that defines the menubar used by the program.
 */
class MenuBar extends JMenuBar {
  private static final long serialVersionUID = 5709711130455495342L;

  /**
   * The default constructor for the menu bar. All initializations for the class
   * are done here.
   */
  MenuBar() {
    // **Setting up the menu.
    JMenu mainMenu = new JMenu();
    mainMenu.setText("Processing Options");
    mainMenu.setContentAreaFilled(false);

    // **Setting up the menu Items**
    JMenuItem grayScaleMenuItem = new JMenuItem();
    grayScaleMenuItem.setText("Gray Scale");
    grayScaleMenuItem.setName("grayscale");
    grayScaleMenuItem.setActionCommand("grayscale");
    mainMenu.add(grayScaleMenuItem);

    JMenuItem sepiaMenuItem = new JMenuItem();
    sepiaMenuItem.setText("Sepia");
    sepiaMenuItem.setName("sepia");
    sepiaMenuItem.setActionCommand("sepia");
    mainMenu.add(sepiaMenuItem);

    JMenuItem blurMenuItem = new JMenuItem();
    blurMenuItem.setText("Blur");
    blurMenuItem.setName("blur");
    blurMenuItem.setActionCommand("blur");
    mainMenu.add(blurMenuItem);

    JMenuItem sharpenMenuItem = new JMenuItem();
    sharpenMenuItem.setText("Sharpen");
    sharpenMenuItem.setName("sharpen");
    sepiaMenuItem.setActionCommand("sharpen");
    mainMenu.add(sharpenMenuItem);

    JMenuItem mosaicMenuItem = new JMenuItem();
    mosaicMenuItem.setText("Mosaic");
    mosaicMenuItem.setName("mosaic");
    mosaicMenuItem.setActionCommand("mosaic");
    mainMenu.add(mosaicMenuItem);

    JMenuItem ditherMenuItem = new JMenuItem();
    ditherMenuItem.setText("Dither");
    ditherMenuItem.setName("dither");
    ditherMenuItem.setActionCommand("dither");
    mainMenu.add(ditherMenuItem);

    JMenuItem pixelateMenuItem = new JMenuItem();
    pixelateMenuItem.setText("Pixelate");
    pixelateMenuItem.setName("pixelate");
    pixelateMenuItem.setActionCommand("pixelate");
    mainMenu.add(pixelateMenuItem);

    JMenuItem patternGenerateMenuItem = new JMenuItem();
    patternGenerateMenuItem.setText("Generate Pattern");
    patternGenerateMenuItem.setName("pattern");
    patternGenerateMenuItem.setActionCommand("pattern");
    mainMenu.add(patternGenerateMenuItem);

    JMenuItem patternGenerateCustomMenuItem = new JMenuItem();
    patternGenerateCustomMenuItem.setText("Generate Pattern With Custom DMC Colors");
    patternGenerateCustomMenuItem.setName("custompattern");
    patternGenerateCustomMenuItem.setActionCommand("custompattern");
    mainMenu.add(patternGenerateCustomMenuItem);

    this.add(mainMenu);

  }
}
