package com.clouway.gwtgenerator.shared;

import com.clouway.gwtgenerator.client.FormField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.uibinder.rebind.IndentedWriter;

/**
 * Generate the Class-Framework for all Annotations
 * which are implementing the FormBinder-interface.
 *
 * @author Siegfried Bolz
 * @since 09.01.2010
 */
public class FormBinderGenerator extends BaseGenerator {

  @Override
  protected void doGenerate(JClassType interfaceType, String implName, IndentedWriter writer) {
    JParameter[] methodParams = extractInterfaceMethodParams(interfaceType);
    writeImports(writer, methodParams);
    writeClassIntro(interfaceType, implName, writer);
    writeFieldsIntro(writer);
    writeMethodIntro(writer, methodParams);
    writeFieldsBinding(interfaceType, writer);
    writeOutro(writer);
  }

  private void writeOutro(IndentedWriter writer) {

  }

  /**
   * Generate default code for all generated Components
   *
   * @param interfaceType
   * @param writer
   */
  private void writeFieldsBinding(JClassType interfaceType, IndentedWriter writer) {
    for (JField jField : interfaceType.getEnclosingType().getFields()) {
      FormField annotation = jField.getAnnotation(FormField.class);
      if (annotation != null) {
        writer.write("component.%1$s = new %2$s();", jField.getName(), jField.getType().getQualifiedSourceName());
        writer.write("component.%1$s.setStyleName(\"%2$s\");", jField.getName(), annotation.styleName());
        writer.write("component.%1$s.add(component.%2$s);", annotation.parentAccessor(), jField.getName());
        FieldGeneratorFactory.getInstance().createFor(jField).write(jField, annotation, writer);
      }
    }
  }


  private void writeMethodIntro(IndentedWriter writer, JParameter[] parameters) {
    writer.write("public void bind(%1$s component) {", parameters[0].getType().getQualifiedSourceName());
    writer.indent();
  }

  private void writeFieldsIntro(IndentedWriter writer) {
    // nothing to do now
    writer.newline();
  }

  private void writeImports(IndentedWriter writer, JParameter[] parameters) {
    writer.write(IMPORT, GWT.class.getName());
    //writer.write(IMPORT, parameters[1].getType().getQualifiedSourceName());
    writer.newline();
  }

  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }



}

