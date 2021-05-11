package view;

import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 * A panel to add a batch text and process the text accordingly.
 */
class TextPanel extends javax.swing.JPanel {
  /**
   * The serial id generated.
   */
  private static final long serialVersionUID = 451652684592693728L;

  private javax.swing.JTextArea batchTextArea;
  
  /**
   * A default constructor for the text panel. All setup is done here.
   */
  TextPanel() {
    initComponents();
  }

  /**
   * Init all the components for the text panel.
   */
  private void initComponents() { 
    batchTextArea = new javax.swing.JTextArea();
    
    JScrollPane batchScrollPane = new javax.swing.JScrollPane();
    batchScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Batch Text"));

    batchTextArea.setColumns(20);
    batchTextArea.setRows(5);
    batchTextArea.setName("batchTextArea");
    batchScrollPane.setViewportView(batchTextArea);
    
    JButton processBatchButton = new javax.swing.JButton();
    processBatchButton.setText("Process Batch");
    processBatchButton.setToolTipText("Press to process the batch text");
    processBatchButton.setActionCommand("batch");
    processBatchButton.setName("batch_button");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(batchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(processBatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 492,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup().addGap(214, 214, 214).addComponent(
                    processBatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
                    javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  }

  /**
   * A package private method to get the input from the text area. The text area
   * is in the view port of the scroll pane.
   *
   * @return The string in the text area.
   */
  String getTextArea() {
    return batchTextArea.getText();
  }
}
