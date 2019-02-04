package factory;

import annotation.SwaggerGen;
import resource.Endpoint;

/**
 * Creates a Swagger Endpoint from the annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class EndpointFactory {
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
