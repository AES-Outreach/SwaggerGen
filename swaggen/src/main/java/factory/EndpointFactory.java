package factory;

import annotation.SwaggerGen;
import domain.path.Endpoint;

/**
 * Creates a Swagger Endpoint from the annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class EndpointFactory {
	
	/**
	 * Creates an Endpoint from an annotation.s
	 * 
	 * @param annotation the annotation
	 * @return the endpoint
	 */
	public static Endpoint Endpoint(SwaggerGen annotation) {
		Endpoint endpoint = new Endpoint();
		endpoint.setSummary(annotation.description());
		endpoint.setDescription(annotation.description());
		endpoint.setParameters(ParameterFactory.Parameters(annotation));
		endpoint.setRequestBody(RequestBodyFactory.RequestBody(annotation));
		endpoint.setResponses(ResponseFactory.Responses(annotation));
		return endpoint;
	}
}
