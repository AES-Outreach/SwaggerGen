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
     * The URI of the endpoint
     * 
     * @return the URI
     */
    String uri();
    
    /**
     * The HTTP Request Method
     * 
     * @return the HTTP Request Method
     */
    String method();
    
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
     * List of headers of the form "{type} {name}={description}"
     * 
     * @return the list of headers
     */
    String[] headers() default {};
    
    /**
     * List of query parameters of the form "{type} {name}={description}"
     * 
     * @return list of query params
     */
    String[] queryParams() default {};
    
    /**
     * The name of the JSON schema file that describes the body of the request
     * 
     * @return name of JSON schema file
     */
    String body() default "";
    
    /**
     * A list of response codes of the form "{type} {name}={description}"
     * 
     * @return list of response codes
     */
    String[] responses() default {"200=OK"};
    
    /**
     * The name of the JSON schema file that describes the body of the response
     * corresponding to the first response code provided.
     * 
     * @return name of JSON SCHEMA file describing the response body
     */
    String responseBody() default "";

    /**
     * The base path
     * @return the base path
     */
    String basePath() default "";
}