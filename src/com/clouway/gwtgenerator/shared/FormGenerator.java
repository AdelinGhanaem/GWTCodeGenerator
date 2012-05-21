package com.clouway.gwtgenerator.shared;

import com.clouway.gwtgenerator.client.FormField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.uibinder.rebind.IndentedWriter;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FormGenerator extends MainGenerator {

  @Override
  protected void doGenerate(JClassType interfaceType, String implName, IndentedWriter writer) {
    //This JParameter returns  the bind method(MyView view) parameter
    JParameter[] jParameter = extractInterfaceMethodParams(interfaceType);
    writeImports(writer);
    writeClassIntro(interfaceType, implName, writer);
    writeFieldsIntro(writer);
    writeMethodIntro(writer, jParameter);
    writeFieldsBinding(interfaceType, writer);
    writeOutro(writer);
  }


  private void writeFieldsBinding(JClassType interfaceType, IndentedWriter writer) {
    JClassType type = interfaceType.getEnclosingType();
    JField[] jFields = interfaceType.getEnclosingType().getFields();
    for (JField field : jFields) {
      FormField annotation = field.getAnnotation(FormField.class);
      if (annotation != null) {
        writer.write("component.%1$s = new %2$s();", field.getName(), field.getType().getQualifiedSourceName());
        writer.write("component.%1$s.setStyleName(\"%2$s\");", field.getName(), annotation.styleName());
        writer.write("component.%1$s.add(component.%2$s);", annotation.parentAccessor(), field.getName());
        FieldGeneratorFactory.getInstance().createFor(field).write(field, annotation, writer);
      }
    }
  }

  private void writeMethodIntro(IndentedWriter writer, JParameter[] jParameter) {
    String methodIntro = "public void bind(%1$s component) {" + jParameter[0].getType().getQualifiedSourceName();
    writer.write("public void bind(%1$s component) {", jParameter[0].getType().getQualifiedSourceName());
    writer.indent();
  }


  private void writeFieldsIntro(IndentedWriter writer) {
    writer.newline();
  }

  private void writeImports(IndentedWriter writer) {
    String gwtGetName = GWT.class.getName();
    writer.write(IMPORT, gwtGetName);
    writer.newline();
  }


}
