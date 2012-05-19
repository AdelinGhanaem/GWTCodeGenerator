package com.clouway.gwtgenerator.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NewAccountInputForm implements AccountInputForm {

  private TextBox firstName = new TextBox();

  private PasswordTextBox password = new PasswordTextBox();

  private Label passwordLabel = new Label();

  private Label firstNameLabel = new Label();


  private Button button = new Button();

  @Override
  public String getPassword() {
    return password.getText();
  }

  @Override
  public String getFirstName() {
    return firstName.getText();
  }

  @Override
  public void setPasswordInfo(String message) {

    passwordLabel.setText(message);
  }


  @Override
  public void setFirstNameInfo(String message) {
    firstNameLabel.setText(message);
  }

  @Override
  public HasClickHandlers getButton() {
    return button;
  }

  @Override
  public void bide(HasWidgets widgets) {
    widgets.add(firstName);
    widgets.add(firstNameLabel);
    widgets.add(password);
    widgets.add(passwordLabel);
  }
}
