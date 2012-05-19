package com.clouway.gwtgenerator.shared;

import com.clouway.gwtgenerator.client.FormField;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.uibinder.rebind.IndentedWriter;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class TextFieldGenerator extends FieldGenerator {
  @Override
  public void write(JField field, FormField formField, IndentedWriter writer) {
    writer.write("component.%1$s.setEnabled(false);", field.getName());
    writer.write("component.%1$s.setText(\"%2$s\");", field.getName(), formField.defaultText());
    writer.write("component.%1$s.setName(\"%1$s\");", field.getName());
  }

  private boolean isInstanceOf(JField jField, final Class<?> aClass) {
    try {
      return aClass.isAssignableFrom(Class.forName(jField.getType().getQualifiedSourceName(), false, this.getClass().getClassLoader()));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


}
