package com.clouway.gwtgenerator.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountInputForm extends InputForm {


  public HasClickHandlers getButton();

  public void bide(HasWidgets widgets);
}
