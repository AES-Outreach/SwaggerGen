package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * SwaggerGen annotation description.
 * 
 * @author William Gardiner (7267012)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerGenClass {
  /**
   * The base path
   * @return the base path
   */
  String basePath() default "";
}