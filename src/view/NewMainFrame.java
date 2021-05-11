package view;

import controller.Feature;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class which represents the central UI class.
 */
public class NewMainFrame extends javax.swing.JFrame implements ActionListener, View {
  private static final long serialVersionUID = -8136587554055757257L;

  /**
   * Default constructor for the main frame. We init all components of the main
   * frame here.
   */
  public NewMainFrame() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  private void initComponents() {
    patternFrame = new PatternFrame();
    originalImagePanel = new ImagePanel();
    processedImagePanel = new ProcessedImagePanel();

    uploadImageButton = new javax.swing.JButton();

    mainMenu = new javax.swing.JMenu();
    textPanel = new TextPanel();
    patternGenerationPanel = new PatternGenerationPanel();
    patternGenerationPanel.setVisible(false);
    colorChosenLabel = patternGenerationPanel.getColorChosenLabel();
    legendList = patternGenerationPanel.getLegendList();
    replaceButton = patternGenerationPanel.getReplaceButton();
    // **Setting up the values for the color selection dialog stuff here.
    colorSelectionDialog = new ColorSelectionDialog();
    colorSelectionDialog.setMainFrame(this);
    colorSelectionDialog.setBounds(100, 100, 550, 450);
    processImageButton = colorSelectionDialog.getProcessImageButton();
    processImageButton.addActionListener(this);

    savePatternTextButton = patternGenerationPanel.getSavePatternText();
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    patternTextArea = patternFrame.getPatternArea();
    JPanel saveImagePanel = new javax.swing.JPanel();
    saveImagePanel.setBorder(new javax.swing.border.MatteBorder(null));
    deletePixelButton = patternGenerationPanel.getDeleteButton();
    // Get the item which is selected from the list.
    JList<String> dmcColorList = patternGenerationPanel.getDmcColorsList();
    dmcColorList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting()) {
          JList source = (JList) listSelectionEvent.getSource();
          replacementColor = source.getSelectedValue().toString();
        }
      }
    });

    // ** Everything related to the save image process is here.
    JButton saveImageButton = new javax.swing.JButton();
    saveImageButton.setText("Save Image");
    saveImageButton.setActionCommand("save");
    saveImageButton.addActionListener(this);
    processedImagePanel.setMainFrame(this);
    javax.swing.GroupLayout saveImagePanelLayout = new javax.swing.GroupLayout(saveImagePanel);
    saveImagePanel.setLayout(saveImagePanelLayout);
    saveImagePanelLayout
        .setHorizontalGroup(
            saveImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(saveImagePanelLayout.createSequentialGroup().addGap(253, 253, 253)
                    .addComponent(saveImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(246, Short.MAX_VALUE)));
    saveImagePanelLayout
        .setVerticalGroup(
            saveImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(saveImagePanelLayout.createSequentialGroup().addContainerGap()
                    .addComponent(saveImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)));

    JPanel uploadImagePanel = new javax.swing.JPanel();
    uploadImagePanel
        .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    uploadImageButton.setText("Upload Image");

    javax.swing.GroupLayout uploadImagePanelLayout = new javax.swing.GroupLayout(uploadImagePanel);
    uploadImagePanel.setLayout(uploadImagePanelLayout);
    uploadImagePanelLayout.setHorizontalGroup(
        uploadImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uploadImagePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(uploadImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    uploadImagePanelLayout.setVerticalGroup(
        uploadImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uploadImagePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(uploadImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    setTextPanelComponents();
    JMenuBar mainMenuBar = new MenuBar();
    setJMenuBar(mainMenuBar);
    mainMenu = mainMenuBar.getMenu(0);
    // go through the components and set the values here.
    setMenuItems();
    customPatternGenerateMenuItem.addActionListener(this);

    JPanel batchPanel = new JPanel();
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(batchPanel);
    batchPanel.setLayout(layout);
    layout
        .setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(uploadImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE)
                .addComponent(originalImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 23, Short.MAX_VALUE)
                .addComponent(processedImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
            .addGroup(layout.createSequentialGroup().addContainerGap()
                .addComponent(patternGenerationPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addContainerGap(17, Short.MAX_VALUE)
            .addComponent(uploadImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16,
                Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(processedImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(originalImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(saveImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(patternGenerationPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap()));

    JTabbedPane jTabbedPane = new JTabbedPane();
    jTabbedPane.add("Main UI", batchPanel);
    jTabbedPane.add("Batch Processing", textPanel);
    this.add(jTabbedPane);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    pack();
    setVisible(true);
  }

  /**
   * A helper method to set all the menuItems.
   */
  private void setMenuItems() {
    int menuItemCount = mainMenu.getItemCount();
    for (int i = 0; i < menuItemCount; i++) {
      JMenuItem menuItem = (JMenuItem) mainMenu.getMenuComponent(i);
      menuItem.addActionListener(this);
      switch (menuItem.getName().toLowerCase()) {
        case "pixelate":
          pixelateMenuItem = menuItem;
          break;
        case "mosaic":
          mosaicMenuItem = menuItem;
          break;
        case "dither":
          ditherMenuItem = menuItem;
          break;
        case "pattern":
          patternGenerateMenuItem = menuItem;
          break;
        case "blur":
          blurMenuItem = menuItem;
          break;
        case "sepia":
          sepiaMenuItem = menuItem;
          break;
        case "sharpen":
          sharpenMenuItem = menuItem;
          break;
        case "grayscale":
          grayScaleMenuItem = menuItem;
          break;
        case "custompattern":
          customPatternGenerateMenuItem = menuItem;
          break;
        default:
          break;
      }
    }
  }

  /**
   * Set all the elements of the text Panel.
   */
  private void setTextPanelComponents() {
    for (Component component : textPanel.getComponents()) {
      String name = component.getName();
      if (name != null) {
        if (name.equals("batch_button")) {
          processBatchButton = (JButton) component;
        }
      }
    }
  }

  @Override
  public void setFeatures(Feature features) {
    if (features == null) {
      throw new IllegalArgumentException();
    }
    uploadImageButton.addActionListener(actionEvent -> features.setImageModel());
    blurMenuItem.addActionListener(evt -> features.processCommand(blurMenuItem.getActionCommand()));
    sharpenMenuItem.addActionListener(
        actionEvent -> features.processCommand(sharpenMenuItem.getActionCommand()));
    sepiaMenuItem
        .addActionListener(actionEvent -> features.processCommand(sepiaMenuItem.getName()));
    grayScaleMenuItem.addActionListener(
        actionEvent -> features.processCommand(grayScaleMenuItem.getActionCommand()));
    ditherMenuItem.addActionListener(
        actionEvent -> features.processCommand(ditherMenuItem.getActionCommand()));
    mosaicMenuItem.addActionListener(
        actionEvent -> features.processCommand(mosaicMenuItem.getActionCommand()));
    pixelateMenuItem.addActionListener(
        actionEvent -> features.processCommand(pixelateMenuItem.getActionCommand()));
    patternGenerateMenuItem.addActionListener(
        actionEvent -> features.processCommand(patternGenerateMenuItem.getActionCommand()));
    processBatchButton
        .addActionListener(actionEvent -> features.processBatch(textPanel.getTextArea()));
    savePatternTextButton.addActionListener(
        actionEvent -> features.processCommand(savePatternTextButton.getActionCommand()));
    replaceButton
        .addActionListener(actionEvent -> features.replaceColors(colorChosen, replacementColor));
    deletePixelButton.addActionListener(actionEvent -> features.deletePixel(colorChosen));
    processImageButton
        .addActionListener(actionEvent -> features.patternGenerateCustomColor(customColorsChosen));
  }

  @Override
  public BufferedImage getImage() {
    return originalImagePanel.getBufferedImage();
  }

  @Override
  public void setMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException();
    }
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void setProcessedImage(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    processedImagePanel.setBufferedImage(image);
    // reset values
    repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      throw new IllegalArgumentException();
    }
    String command = e.getActionCommand();
    switch (command.toLowerCase()) {
      case "save":
        if (processedImagePanel.getProcessedImage() == null) {
          setMessage("Please process the image before attempting to save the file.");
          return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save the file");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
          File fileToSave = fileChooser.getSelectedFile();
          String fileName = fileToSave.getAbsolutePath();
          String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
          try {
            FileOutputStream fo = new FileOutputStream(fileName);
            ImageIO.write(processedImagePanel.getProcessedImage(), extension, fo);
            fo.close();
          } catch (IOException ioException) {
            JOptionPane.showMessageDialog(this,
                "Oops we ran into a " + "problem while saving your file");
          }
        }
        break;
      case "pattern":
        if (originalImagePanel.getBufferedImage() != null) {
          patternGenerationPanel.setVisible(true);
        }
        break;
      case "custompattern":
        if (originalImagePanel.getBufferedImage() == null) {
          setMessage("Please upload an image before you start processing!");
          return;
        }
        colorSelectionDialog.setVisible(true);
        break;
      case "processimage":
        patternGenerationPanel.setVisible(true);
        colorSelectionDialog.setVisible(false);
        break;
      default:
        patternGenerationPanel.setVisible(false);
        break;
    }
  }

  @Override
  public String getUploadFilePath() {
    patternGenerationPanel.setVisible(false);
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, GIF & PNG Images",
        "jpg", "png", "jpeg", "gif");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      processedImagePanel.setBufferedImage(null);
      File file = chooser.getSelectedFile();
      return file.getAbsolutePath();
    }
    return "";
  }

  @Override
  public void setOriginalImagePanel(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    originalImagePanel.setBufferedImage(image);
    repaint();
  }

  @Override
  public int getValues(String message) {
    if (message == null) {
      throw new IllegalArgumentException();
    }
    int value;
    try {
      value = Integer.valueOf(JOptionPane.showInputDialog(message));
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Please enter a proper value");
      value = -1;
    }
    return value;
  }

  @Override
  public void setLegend(Set<String> legend) {
    if (legend == null) {
      throw new IllegalArgumentException();
    }
    legendList.setModel(new DefaultListModel<>() {
      String[] strings = legend.stream().toArray(String[]::new);

      @Override
      public int getSize() {
        return strings.length;
      }

      @Override
      public String getElementAt(int i) {
        return strings[i];
      }

      @Override
      public void setElementAt(String newValue, int i) {
        strings[i] = newValue;
      }
    });
  }

  @Override
  public void modifyLegend(int i) {
    if (i < 0)  {
      throw new IllegalArgumentException();
    }
    ((DefaultListModel) legendList.getModel()).setElementAt("BLANK", i);
    legendList.updateUI();
  }

  /**
   * Get updates from the processed Image panel about the changes on the pixel
   * selected.
   * 
   * @param colorChosen The color chosen by the user is passed here.
   */
  void update(int[] colorChosen) {
    Color color = new Color(colorChosen[0], colorChosen[1], colorChosen[2]);
    this.colorChosenLabel.setForeground(color);
    this.colorChosen = colorChosen;
  }

  /**
   * Set the list here in the main frame which is then passed for processing.
   * 
   * @param colorsChosen The colors chosen by the user.
   */
  void setCustomColorsChosen(Set<String> colorsChosen) {
    if (colorsChosen != null) {
      this.customColorsChosen = colorsChosen;
    }
  }

  @Override
  public void reset() {
    colorSelectionDialog.reset();
  }

  @Override
  public String saveTextPattern() {
    JFileChooser fileChooser = new JFileChooser();
    int userChoice = fileChooser.showSaveDialog(this);

    if (userChoice == JFileChooser.APPROVE_OPTION) {
      File toSave = fileChooser.getSelectedFile();
      return toSave.getAbsolutePath();
    }
    return "";
  }

  @Override
  public void setPatternTextArea(String text) {
    patternTextArea.setText(text);
    patternFrame.setVisible(true);
  }

  // private javax.swing.JMenuBar mainMenuBar;
  private JMenu mainMenu;
  private ImagePanel originalImagePanel;
  private ProcessedImagePanel processedImagePanel;
  // This variable holds the color selected value of the user.
  private JButton uploadImageButton;
  // Elements of the textPanel
  private TextPanel textPanel;
  private JButton processBatchButton;
  // All menu items.
  private JMenuItem sepiaMenuItem;
  private JMenuItem blurMenuItem;
  private JMenuItem ditherMenuItem;
  private JMenuItem grayScaleMenuItem;
  private JMenuItem pixelateMenuItem;
  private JMenuItem sharpenMenuItem;
  private JMenuItem mosaicMenuItem;
  private JMenuItem patternGenerateMenuItem;
  private JMenuItem customPatternGenerateMenuItem;
  // Stuff for the pattern generation panel
  private PatternGenerationPanel patternGenerationPanel;
  private JLabel colorChosenLabel;
  private JList<String> legendList;
  private JButton savePatternTextButton;
  private JButton replaceButton;
  private int[] colorChosen;
  private String replacementColor;
  private JButton deletePixelButton;
  // Stuff for the pattern frame.
  private JTextArea patternTextArea;
  private PatternFrame patternFrame;

  // Stuff for the JDialog for user chosen colors for pattern generation
  private ColorSelectionDialog colorSelectionDialog;
  private JButton processImageButton;
  // The custom colors selected by the user.
  private Set<String> customColorsChosen;
}
