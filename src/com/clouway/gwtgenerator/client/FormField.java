package com.clouway.gwtgenerator.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FormField {

  String styleName() default "";
  String parentAccessor();
  String defaultText() default "";


}
