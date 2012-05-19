package com.clouway.gwtgenerator.mygenerator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.IndentedWriter;

import java.io.PrintWriter;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class InputFormGenerator extends Generator {

  private String packageName;
  private String className;
  private static final String CLASS = "public class %s";
  private static final String IMPORT = "import %1$s";
  private static final String PACKAGE = "package %s";

  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

    JClassType classType = getClassType(context.getTypeOracle(), typeName);

    String packageName = classType.getPackage().getName();

    String implementationName = classType.getName() + "Impl";

    //get a print write from the context ... !
    PrintWriterContainer container = new PrintWriterContainer(logger, packageName, context);

    PrintWriter writer = container.getPrintWrite(implementationName);

    IndentedWriter indentedWriter = new IndentedWriter(writer);

    indentedWriter.write(String.format(PACKAGE, packageName));

    indentedWriter.newline();

    doGenerate(classType, implementationName, indentedWriter);

    writeImports(indentedWriter);


    container.commit();

    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  private void writeImports(IndentedWriter writer) {
    writer.write(IMPORT, GWT.class.getName());
  }

  private void doGenerate(JClassType classType, String implementationName, IndentedWriter indentedWriter) {
    writeImports(indentedWriter);
    writeClassIntro(classType, implementationName, indentedWriter);
  }

  private void writeClassIntro(JClassType classType, String implementationName, IndentedWriter indentedWriter) {

  }


  private JClassType getClassType(TypeOracle oracle, String className) {
    JClassType classType = null;
    try {
      classType = oracle.getType(className);
    } catch (NotFoundException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    return classType;

  }

}
