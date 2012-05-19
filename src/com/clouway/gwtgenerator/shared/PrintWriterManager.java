package com.clouway.gwtgenerator.shared;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Write the generated Java files.
 *
 * @author Siegfried Bolz
 * @since 09.01.2010
 */
public class PrintWriterManager {

  private final GeneratorContext generatorContext;

  private final String packageName;

  private final TreeLogger logger;

  private final Set<PrintWriter> writers = new HashSet<PrintWriter>();

  public PrintWriterManager(GeneratorContext generatorContext, TreeLogger logger, String packageName) {
    this.generatorContext = generatorContext;
    this.packageName = packageName;
    this.logger = logger;
  }

  /**
   * Commit all writers we have vended.
   */
  public void commit() {
    for (PrintWriter writer : writers) {
      generatorContext.commit(logger, writer);
    }
  }

  /**
   * @param name classname
   * @return the printwriter
   * @throws RuntimeException if this class has already been written
   */
  public PrintWriter makePrintWriterFor(String name) {
    PrintWriter writer = tryToMakePrintWriterFor(name);
    if (writer == null) {
      throw new RuntimeException(String.format("Tried to write %s.%s twice.", packageName, name));
    }
    return writer;
  }

  /**
   * @param name classname
   * @return the printwriter, or null if this class has already been written
   */
  public PrintWriter tryToMakePrintWriterFor(String name) {
    PrintWriter writer = generatorContext.tryCreate(logger, packageName, name);
    if (writer != null) {
      writers.add(writer);
    }
    return writer;
  }
}
