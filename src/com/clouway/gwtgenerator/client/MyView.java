package com.clouway.gwtgenerator.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class MyView {


  interface InternalFormBinder extends FormBinder<MyView> {
  }

  //
  private final InternalFormBinder binder = GWT.create(InternalFormBinder.class);

  private HTMLPanel widgets = new HTMLPanel("main container !");

  Button button = new Button("Test Button !!");

  HTMLPanel htmlPanel = new HTMLPanel(new SafeHtml() {
    @Override
    public String asString() {
      return "ONE TWO THREE";
    }
  });


  InputForm form = GWT.create(InputForm.class);

  @FormField(styleName = "label", parentAccessor = "htmlPanel", defaultText = "XXXX !")
  Label label;

  @FormField(styleName = "texbox", parentAccessor = "htmlPanel", defaultText = "XXXX")
  TextBox textBox;


  public MyView() {
    binder.bind(this);
    htmlPanel.add(button);
    htmlPanel.add(form.getContainingPanel());
    widgets.add(htmlPanel);
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Ok nigga move on .... !");
      }
    });

    form.getSubmitButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Wiiiiiiiiihaaaaaaaaaaaaaaaaaaaaaa !");
      }
    });
    Button button = (Button) form.getSubmitButton();
    widgets.add(button);

    widgets.add(form.getContainingPanel());


  }

  public Widget asWidget() {
    return widgets;
  }
}
