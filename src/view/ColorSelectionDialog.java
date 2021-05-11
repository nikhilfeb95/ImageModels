package view;

import imagemodel.patterngenerator.DmcValues;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



/**
 * A popup which gives the users option to select colors from a particular list
 * of colors.
 */
class ColorSelectionDialog extends JDialog {
  private static final long serialVersionUID = -6417169973362320625L;
  private JList<String> colorChosenList;
  private JButton processImageButton;
  private Set<String> colorSelectedList;
  private NewMainFrame mainFrame;

  /**
   * The default constructor for the Color Selection Dialog.
   */
  ColorSelectionDialog() {
    initComponents();
  }

  private void initComponents() {
    this.setPreferredSize(new Dimension(500, 500));
    setTitle("Choose your floss colors");
    colorChosenList = new javax.swing.JList<>();
    colorSelectedList = new HashSet<>();
    processImageButton = new javax.swing.JButton();
    processImageButton.setText("Process Image");
    processImageButton.setActionCommand("processimage");
    JScrollPane dmcColorPane = new javax.swing.JScrollPane();
    dmcColorPane.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("DMC Color Pane"));
    JList<String> dmcColorList = new javax.swing.JList<>();
    dmcColorList.setCellRenderer(new ListRenderer());
    dmcColorList.setModel(new javax.swing.AbstractListModel<String>() {
      String[] strings = DmcValues.getdmc().keySet().toArray(String[]::new);

      public int getSize() {
        return strings.length;
      }

      public String getElementAt(int i) {
        return strings[i];
      }
    });
    dmcColorList.addListSelectionListener(listSelectionEvent -> {
      JList source = (JList) listSelectionEvent.getSource();
      colorSelectedList.add((String) source.getSelectedValue());
      updateMainFrame();
      updateUserSelectionList();
    });

    dmcColorPane.setViewportView(dmcColorList);
    colorChosenList.setCellRenderer(new ListRenderer());
    JScrollPane colorChosenPane = new javax.swing.JScrollPane();
    colorChosenPane.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Color Chosen"));
    colorChosenPane.setViewportView(colorChosenList);
    colorChosenList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting()) {
          if (colorSelectedList.contains(colorChosenList.getSelectedValue())) {
            colorSelectedList.remove(colorChosenList.getSelectedValue());
            updateMainFrame();
            colorChosenList.setListData(colorSelectedList.toArray(String[]::new));
          }
        }
      }
    });
    javax.swing.GroupLayout selectionDialogLayout = new javax.swing.GroupLayout(
        this.getContentPane());
    this.getContentPane().setLayout(selectionDialogLayout);
    selectionDialogLayout.setHorizontalGroup(
        selectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dmcColorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(colorChosenPane, javax.swing.GroupLayout.PREFERRED_SIZE, 257,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(selectionDialogLayout.createSequentialGroup().addGap(193, 193, 193)
                .addComponent(processImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    selectionDialogLayout.setVerticalGroup(
        selectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                selectionDialogLayout.createSequentialGroup().addContainerGap(46, Short.MAX_VALUE)
                    .addGroup(selectionDialogLayout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dmcColorPane, javax.swing.GroupLayout.DEFAULT_SIZE, 329,
                            Short.MAX_VALUE)
                        .addComponent(colorChosenPane))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(processImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  }

  /**
   * Reset all the components in the JDialog.
   */
  void reset() {
    colorSelectedList = new HashSet<>();
  }

  /**
   * Update the users selection list with the list added by the user.
   */
  private void updateUserSelectionList() {
    colorChosenList.setModel(new AbstractListModel<String>() {
      private static final long serialVersionUID = 8471220817189405309L;
      String[] strings = colorSelectedList.stream().toArray(String[]::new);

      @Override
      public int getSize() {
        return strings.length;
      }

      @Override
      public String getElementAt(int i) {
        return strings[i];
      }
    });
  }

  /**
   * A getter for the process Image button which is used by the main frame.
   * 
   * @return The reference to the processImageButton.
   */
  JButton getProcessImageButton() {
    return processImageButton;
  }

  /**
   * Update the main frame with the list of all recently chosen colors by the
   * user.
   */
  private void updateMainFrame() {
    mainFrame.setCustomColorsChosen(this.colorSelectedList);
  }

  /**
   * Set the main frame reference for the class. This is used to notify the main
   * frame of changes.
   * 
   * @param mainFrame The reference to the main frame.
   */
  void setMainFrame(NewMainFrame mainFrame) {
    this.mainFrame = mainFrame;
  }
}
