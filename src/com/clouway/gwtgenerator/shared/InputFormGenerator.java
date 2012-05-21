package com.clouway.gwtgenerator.shared;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.IndentedWriter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class InputFormGenerator extends Generator {

  private static final String IMPORT = "import %1$s ;";

  private static final String PACKAGE = "package %s ;";

  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

    logger.log(TreeLogger.Type.ALL, "This is log message |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| !\n");

    JClassType classType = getClassType(context.getTypeOracle(), typeName);

    String packageName = classType.getPackage().getName();

    String implementationName = classType.getName().replace(".", "_") + "Impl";


    //get a print write from the context ... !
    PrintWriterContainer container = new PrintWriterContainer(logger, packageName, context);

    PrintWriter writer = container.getPrintWrite(implementationName);
    if (writer != null) {
      IndentedWriter indentedWriter = new IndentedWriter(writer);

      indentedWriter.write(String.format(PACKAGE, packageName));

      indentedWriter.newline();

      importPackages(indentedWriter);

      generate(classType, implementationName, indentedWriter);

      container.commit();


    }
    String returnedClassName = packageName + "." + implementationName;
    return returnedClassName;
  }


  private void importPackages(IndentedWriter writer) {
    writer.write(IMPORT, "com.google.gwt.user.client.ui.*");
    writer.write(IMPORT, "com.google.gwt.event.dom.client.HasClickHandlers");
    writer.write(IMPORT, "com.clouway.gwtgenerator.client.*");
//    writer.write(IMPORT, GWT.class.getName());
  }

  private void generate(JClassType classType, String implementationName, IndentedWriter indentedWriter) {

    importPackages(indentedWriter);

    generateClassIntro(classType, implementationName, indentedWriter);

    List<String> generatedFields = generateFieldsAndMethods(classType, indentedWriter);

    addFieldsToWidgetsContainer(generatedFields, indentedWriter);

    finishCodeGeneration(indentedWriter);

  }

  private void addFieldsToWidgetsContainer(List<String> generatedFields, IndentedWriter indentedWriter) {

    indentedWriter.write("public HTMLPanel getContainingPanel(){ \n");

    for (String field : generatedFields) {

      indentedWriter.write("html.add(" + field + ");\n");

    }

    indentedWriter.write("return html; }\n");
  }

  private List<String> generateFieldsAndMethods(JClassType classType, IndentedWriter indentedWriter) {
    JMethod[] jMethods = classType.getMethods();
    List<String> fields = new ArrayList<String>();
    for (JMethod jMethod : jMethods) {

      String methodName = jMethod.getName();

      checkMethodMethodPrefixIsCorrect(methodName);

      if (!"getContainingPanel".equals(methodName) && !"getSubmitButton".equals(methodName)) {
        String fieldName = methodName.substring(3, methodName.length());
        fields.add(fieldName);


        indentedWriter.write("TextBox " + fieldName + " = new TextBox();");
        indentedWriter.write("public void set" + fieldName + "(String " + fieldName + "Value){ \n " +
                fieldName + ".setText(" + fieldName + "Value); \n" +
                "}\n");

        indentedWriter.write("public String get" + fieldName + "(){ \n" +
                " return " + fieldName + ".getText();\n" +
                "}");
      }

    }
    return fields;
  }

  private void checkMethodMethodPrefixIsCorrect(String methodName) {
    String prefix = methodName.substring(0, 3);
    if (!"get".equals(prefix)) {
      throw new IllegalArgumentException("Your interface declaration must contain only getField() like methods !");
    }
  }


  private void finishCodeGeneration(IndentedWriter writer) {

    writer.write("public HasClickHandlers getSubmitButton(){\n" +
            " return submitButton;\n" +
            "}");
    writer.write("}");
    writer.newline();
//    writer.write("}");
  }

  private void generateClassIntro(JClassType classType, String implementationName, IndentedWriter indentedWriter) {
//    JClassType[] infoForm = classType.getImplementedInterfaces();
//    if (infoForm != null) {

//      indentedWriter.write("public class " + implementationName + " implements " + implementationName + "{ \n");
    indentedWriter.write("public class " + implementationName + " implements " + classType.getName() + "{\n");
    indentedWriter.write("private HTMLPanel html = new HTMLPanel(\"" + implementationName + "\"); ");
    indentedWriter.write("private Button submitButton = new Button(\" submit \"); ");
//    }

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
