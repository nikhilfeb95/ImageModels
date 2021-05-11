package view;

import imagemodel.patterngenerator.DmcValues;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * The panel which is generated after the pattern generation of an image. Shows
 * the legend to the user and also the option to replace colors for all of the
 * specific pixels.
 */
class PatternGenerationPanel extends JPanel {
  private static final long serialVersionUID = 5032631428075727415L;
  private JLabel colorChosenLabel;
  private JList<String> dmcColorsList;
  private JList<String> legendList;
  private JButton replaceButton;
  private JButton savePatternText;
  private JButton deletePixel;

  /**
   * A constructor for the pattern generation panel. This constructor is used to
   * init all components and values.
   */
  PatternGenerationPanel() {
    legendList = new JList<>();
    dmcColorsList = new JList<>();
    colorChosenLabel = new JLabel();
    replaceButton = new JButton();
    savePatternText = new JButton();
    deletePixel = new JButton();

    legendList.setModel(new javax.swing.AbstractListModel<String>() {
      private static final long serialVersionUID = 1L;
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

      public int getSize() {
        return strings.length;
      }

      public String getElementAt(int i) {
        return strings[i];
      }
    });
    legendList.setCellRenderer(new ListRenderer());
    JScrollPane legendPane = new JScrollPane();
    legendPane.setViewportView(legendList);
    legendPane.setBorder(BorderFactory.createTitledBorder("Legend List"));

    colorChosenLabel.setText("Color Chosen");

    dmcColorsList.setModel(new javax.swing.AbstractListModel<String>() {
      private static final long serialVersionUID = 1L;
      String[] strings = DmcValues.getdmc().keySet().toArray(String[]::new);

      public int getSize() {
        return strings.length;
      }

      public String getElementAt(int i) {
        return strings[i];
      }
    });
    dmcColorsList.setCellRenderer(new ListRenderer());
    JScrollPane dmcColorPane = new JScrollPane();
    dmcColorPane.setViewportView(dmcColorsList);
    dmcColorPane.setBorder(BorderFactory.createTitledBorder("Replacement DMC Values"));
    replaceButton.setText("Replace Pixel Color");
    replaceButton.setActionCommand("replacepixel");

    savePatternText.setText("Save Pattern text");
    savePatternText.setActionCommand("savepattern");

    deletePixel.setText("Delete Pixel");

    javax.swing.GroupLayout patternGenerationPanelLayout = new javax.swing.GroupLayout(this);
    this.setLayout(patternGenerationPanelLayout);
    patternGenerationPanelLayout.setHorizontalGroup(
        patternGenerationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                patternGenerationPanelLayout.createSequentialGroup().addContainerGap()
                    .addGroup(patternGenerationPanelLayout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(colorChosenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deletePixel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dmcColorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 198,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(replaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(savePatternText, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(legendPane, javax.swing.GroupLayout.PREFERRED_SIZE, 188,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    patternGenerationPanelLayout.setVerticalGroup(patternGenerationPanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(patternGenerationPanelLayout.createSequentialGroup()
            .addGroup(patternGenerationPanelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(patternGenerationPanelLayout.createSequentialGroup().addGap(22, 22, 22)
                    .addComponent(colorChosenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(deletePixel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(10, 10, 10))
                .addGroup(patternGenerationPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(patternGenerationPanelLayout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(patternGenerationPanelLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(savePatternText, javax.swing.GroupLayout.DEFAULT_SIZE, 91,
                                Short.MAX_VALUE)
                            .addComponent(replaceButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(patternGenerationPanelLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dmcColorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 124,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(legendPane, javax.swing.GroupLayout.PREFERRED_SIZE, 124,
                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap()));
  }

  /**
   * Package private getter to fetch the label --> used by the main frame.
   * 
   * @return The JLabel in this panel.
   */
  JLabel getColorChosenLabel() {
    return colorChosenLabel;
  }

  /**
   * A getter for the JList which will contain the legend.
   * 
   * @return The list where the legend will be populated.
   */
  JList<String> getLegendList() {
    return legendList;
  }

  /**
   * A getter for the JList which will contain the legend.
   * 
   * @return The list where the legend will be populated.
   */
  JList<String> getDmcColorsList() {
    return dmcColorsList;
  }

  /**
   * A getter for the button that is used to replace pixels .
   * 
   * @return The JButton reference.
   */
  JButton getReplaceButton() {
    return replaceButton;
  }

  /**
   * A getter for the button to save the pattern text.
   * 
   * @return The reference for the button.
   */
  JButton getSavePatternText() {
    return savePatternText;
  }

  /**
   * A getter for the button to delete the pixel.
   * 
   * @return The reference for the delete pixel button.
   */
  JButton getDeleteButton() {
    return deletePixel;
  }
}
