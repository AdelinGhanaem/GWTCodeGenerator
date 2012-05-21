package com.clouway.gwtgenerator.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface InputForm {

  public String getFirstName();

  public String getLastName();

  public String getAddress();

  public String getAge();

  public String getHoppies();

  public String getSomething();

  public String getSomething2();

  public HasClickHandlers getSubmitButton();

  public HTMLPanel getContainingPanel();

}
