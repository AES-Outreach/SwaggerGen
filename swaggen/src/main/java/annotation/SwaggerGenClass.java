package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * SwaggerGen class level annotation description.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerGenClass {
  /**
   * The base path
   * 
   * @return the base path
   */
  String basePath() default "";
  	/**
	 * The title of the endpoint
	 * 
	 * @return the title of the endpoint
	 */
	String title() default "";
	
	/**
	 * The description of the endpoint
	 * 
	 * @return the description of the endpoint
	 */
  String description() default "";
  
  /**
   * The server url of the endpoints
   * of the form "{url} = {description}"
   * 
   * @return the server url
   */
  String[] servers() default "";

  /**
   * The scheme of the server url
   * Either http or https
   * 
   * @return the scheme
   */
  String scheme() default "http";

  /**
	 * List of headers of the form "{type} {name}={description}"
	 * 
	 * @return the list of headers
	 */
	String[] headers() default {};
}