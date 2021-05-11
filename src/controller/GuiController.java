package controller;

import view.View;

/**
 * An extension of the ImageController, made specifically for the GUI.
 */
public interface GuiController {

  /**
   * Set the view for the controller to interact with.
   * 
   * @param view The view being passed to the controller.
   */
  public void setView(View view);
}
