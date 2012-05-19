package com.clouway.gwtgenerator.shared;

import com.clouway.gwtgenerator.client.FormField;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.uibinder.rebind.IndentedWriter;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class LabelFieldGenerator extends FieldGenerator {


  @Override
  public void write(JField field, FormField formField, IndentedWriter writer) {
    writer.write("component.%1$s.setText(\"%2$s\");", field.getName(), formField.defaultText());
  }

}
