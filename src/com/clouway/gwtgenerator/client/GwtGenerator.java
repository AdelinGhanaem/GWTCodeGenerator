package com.clouway.gwtgenerator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class GwtGenerator implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    MyView myView = new MyView();
    RootLayoutPanel.get().add(myView.asWidget());
  }


}
