package com.clouway.gwtgenerator.shared;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class PrintWriterContainer {


  private final GeneratorContext generatorContext;

  private final String packageName;

  private final TreeLogger logger;

  private final Set<PrintWriter> writers = new HashSet<PrintWriter>();

  public PrintWriterContainer(TreeLogger logger, String packageName, GeneratorContext generatorContext) {
    this.logger = logger;
    this.packageName = packageName;
    this.generatorContext = generatorContext;
  }


  public PrintWriter getPrintWrite(String name) {
    PrintWriter writer = generatorContext.tryCreate(logger, packageName, name);
    if (writer != null) {
      writers.add(writer);
    }
    return writer;
  }

  public void commit() {
    for (PrintWriter writer : writers) {
      generatorContext.commit(logger, writer);
    }
  }
}
