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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerGen {

	/**
	 * The URL of the endpoint
	 * 
	 * @return
	 */
	String url();
	
	/**
	 * The HTTP Request Method
	 * 
	 * @return
	 */
	String method();
	
	/**
	 * The description of the endpoint
	 * 
	 * @return
	 */
	String description() default "";
	
	/**
	 * List of headers of the form "{type} {name}={description}"
	 * 
	 * @return
	 */
	String[] headers() default {};
	
	/**
	 * List of query parameters of the form "{type} {name}={description}"
	 * 
	 * @return
	 */
	String[] queryParams() default {};
	
	/**
	 * The name of the JSON schema file that describes the body of the request
	 * 
	 * @return
	 */
	String body() default "";
	
	/**
	 * A list of response codes of the form "{type} {name}={description}"
	 * 
	 * @return
	 */
	String[] responses() default {"200=OK"};
	
	/**
	 * The name of the JSON schema file that describes the body of the response
	 * corresponding to the first response code provided.
	 * 
	 * @return
	 */
	String responseBody() default "";
	
	/**
	 * The scheme: HTTP or HTTPS
	 * 
	 * @return
	 */
	String scheme() default "HTTP";

}