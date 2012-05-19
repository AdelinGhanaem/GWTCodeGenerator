package com.clouway.gwtgenerator.shared;

import com.clouway.gwtgenerator.client.FormField;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.uibinder.rebind.IndentedWriter;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public abstract class FieldGenerator {

  public abstract void write(JField field, FormField formField, IndentedWriter writer);

}
