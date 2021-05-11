package view;

import imagemodel.patterngenerator.DmcValues;

import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;


/**
 * A list renderer to color foreground of DMC to their correct color.
 */
class ListRenderer extends DefaultListCellRenderer {
  /**
   * Generated serial Id.
   */
  private static final long serialVersionUID = 3606776624033553936L;
  private Map<String, Integer[]> dmcValues;

  /**
   * A default constructor for the list renderer.
   */
  ListRenderer() {
    dmcValues = DmcValues.getdmc();
  }

  @Override
  public Component getListCellRendererComponent(JList<?> list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    if (dmcValues.containsKey(value)) {
      Integer[] background = dmcValues.get(value);
      setBackground(new Color(background[0], background[1], background[2]));
      return c;
    }
    return c;
  }
}
