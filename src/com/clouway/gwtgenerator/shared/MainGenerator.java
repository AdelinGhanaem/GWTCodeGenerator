package com.clouway.gwtgenerator.shared;


import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.IndentedWriter;

import java.io.PrintWriter;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public abstract class MainGenerator extends Generator {

  protected static final String IMPORT = "import %1$s;";
  protected static final String PACKAGE = "package %s;";


  //This function returns a JClassType Object from TypeOracle and the given String
  protected JClassType interfaceType(TypeOracle typeOracle, String string, TreeLogger treeLogger) throws UnableToCompleteException {
    JClassType jClassType;
    try {
      jClassType = typeOracle.getType(string);
    } catch (NotFoundException e) {
      throw new UnableToCompleteException();
    }
    return jClassType;
  }


  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

    JClassType classType = interfaceType(context.getTypeOracle(), typeName, logger);
    //get the name of the package ...

    String packageName = classType.getPackage().getName();

    PrintWriterManager printWriterManager = new PrintWriterManager(context, logger, packageName);

    String implName = classType.getName().replace(".", "_");

    PrintWriter printWriter = printWriterManager.tryToMakePrintWriterFor(implName);

    if (printWriter != null) {
      IndentedWriter indentedWriter = new IndentedWriter(printWriter);
      indentedWriter.write(String.format(PACKAGE, packageName));
      indentedWriter.newline();
      doGenerate(classType, implName, indentedWriter);
      printWriterManager.commit();
    }
    String className = packageName + "." + implName;
    return className;
  }

  protected abstract void doGenerate(JClassType type, String implementationName, IndentedWriter writer);

  /**
   * write a class Intro e.g [ public class ClassImplementationName implements Interface ]
   */
  protected void writeClassIntro(JClassType interfaceType, String implName, IndentedWriter writer) {
    String classIntor = String.format("public class %1$s implements %2$s {\"", implName, interfaceType.getName());
    writer.write("public class %1$s implements %2$s {", implName, interfaceType.getName());

    writer.indent();

    writer.newline();

  }

  //This returns the parameter of the bind method !!!
  protected JParameter[] extractInterfaceMethodParams(JClassType interfaceType) {
    return interfaceType.getImplementedInterfaces()[0].getMethods()[0].getParameters();
  }

  protected void writeOutro(IndentedWriter writer) {
    writer.outdent();
    writer.write("}");
    writer.outdent();
    writer.write("}");
  }


}
