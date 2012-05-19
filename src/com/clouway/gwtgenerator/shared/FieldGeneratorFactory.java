package com.clouway.gwtgenerator.shared;

import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FieldGeneratorFactory {
  private static final FieldGeneratorFactory INSTANCE = new FieldGeneratorFactory();
  private static final Map<String, FieldGenerator> generators = new HashMap<String, FieldGenerator>();

  static {
    generators.put(TextBox.class.getName(), new TextFieldGenerator());
    generators.put(Label.class.getName(), new LabelFieldGenerator());
  }

  private FieldGeneratorFactory() {
  }

  public static FieldGeneratorFactory getInstance() {
    return INSTANCE;
  }

  public FieldGenerator createFor(JField jField) {
    String className = jField.getType().getQualifiedSourceName();
    FieldGenerator fieldGenerator = generators.get(className);
    if (fieldGenerator == null) {
      throw new IllegalArgumentException("Did not find any generator for the [" + className + "].");
    }
    return fieldGenerator;
  }




}
