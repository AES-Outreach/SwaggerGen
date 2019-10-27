package annotation;

import annotation.body.SwaggerBody;

/**
 * Annotation used to describe a response from an endpoint.
 * 
 * Example:
 * @ SwaggerGen {
 * 		...
 * 		responses = [
 * 			@ SwaggerResponse(body=@ JsonSchemaBody("path/to/file.json")),
 * 			@ SwaggerResponse(code=400, description="Bad Request", body=@ JsonSchemaBody("path/to/bad/file.json"))
 * 		]
 * }
 * 
 * @author William Gardiner (7267012)
 */
public @interface SwaggerResponse {

	public int code() default 200;
	public String description() default "";
	public SwaggerBody body() default @SwaggerBody(); 
	
}
