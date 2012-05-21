package com.clouway.gwtgenerator.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */

public class NewAccountInputForm implements InputForm {

  private HTMLPanel htmlPanel = new HTMLPanel("NewAccountInputForm");

  private TextBox firstName = new TextBox();

  private PasswordTextBox password = new PasswordTextBox();

  private Label passwordLabel = new Label();

  private Label firstNameLabel = new Label();

  private Button button = new Button();

  public String getPassword() {

    return password.getText();
  }

  public String getFirstName() {
    return firstName.getText();
  }

  @Override
  public String getLastName() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getAddress() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getAge() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getHoppies() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public void setPasswordInfo(String message) {

    passwordLabel.setText(message);
  }


  public void setFirstNameInfo(String message) {
    firstNameLabel.setText(message);
  }

  @Override
  public HasClickHandlers getSubmitButton() {
    return button;
  }

  @Override
  public HTMLPanel getContainingPanel() {
    htmlPanel.add(firstName);
    htmlPanel.add(password);
    htmlPanel.add(firstName);
    htmlPanel.add(firstName);
    htmlPanel.add(button);
    return htmlPanel;
  }


}
