package com.clouway.gwtgenerator.shared;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.IndentedWriter;

/**
 * Create the Implementation-Class and add a basic header data for ALL
 * generated Annotations.
 *
 * @author Siegfried Bolz
 * @since 09.01.2010
 */
public abstract class BaseGenerator extends Generator {

  protected static final String IMPORT = "import %1$s;";
  protected static final String PACKAGE = "package %s;";

  //this method provide JClassType from String .... ! The JClassType is a type used to represent any non-primitive type !
  protected JClassType interfaceType(TypeOracle oracle, String s, TreeLogger treeLogger) throws UnableToCompleteException {
    JClassType interfaceType;
    try {
      interfaceType = oracle.getType(s);
    } catch (NotFoundException e) {
//      treeLogger.log(TreeLogger.ERROR, String.format("%s: Could not find the interface [%s]. %s", e.getClass().getName(), s, e.getMessage()));
      throw new UnableToCompleteException();
    }
    return interfaceType;
  }
//
//  @Override
//  public String generate(TreeLogger treeLogger, Generato rContext generatorContext, String s) throws UnableToCompleteException {
//    JClassType interfaceType = interfaceType(generatorContext.getTypeOracle(), s, treeLogger);
//

//    String packageName = interfaceType.getPackage().getName();

//    PrintWriterManager writers = new PrintWriterManager(generatorContext, treeLogger, packageName);

//    String implName = interfaceType.getName().replace(".", "_") + "Impl";

//    PrintWriter printWriter = writers.tryToMakePrintWriterFor(implName);

//    if (printWriter != null) {
//      IndentedWriter writer = new IndentedWriter(printWriter);
//      writer.write(String.format(PACKAGE, packageName));
//
//       writer.newline();
//      doGenerate(interfaceType, implName, writer);
//
//      writers.commit();

//    }
//    return packageName + "." + implName;
//  }


//
  protected abstract void doGenerate(JClassType interfaceType, String implName, IndentedWriter writer);

  protected void writeClassIntro(JClassType interfaceType, String implName, IndentedWriter writer) {
//
    writer.write("public class %1$s implements %2$s {", implName, interfaceType.getName());
//
    writer.indent();
//
    writer.newline();
//
  }
//
                                                                                    //thi
  protected JParameter[] extractInterfaceMethodParams(JClassType interfaceType) {
    return interfaceType.getImplementedInterfaces()[0].getMethods()[0].getParameters();
  }
//
//  protected void writeOutro(IndentedWriter writer) {
//    writer.outdent();
//    writer.write("}");
//    writer.outdent();
//    writer.write("}");
//  }
}
