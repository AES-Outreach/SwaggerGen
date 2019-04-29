package factory;

import annotation.SwaggerGen;
import domain.output.path.Endpoint;

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
	public static Endpoint createEndpoint(SwaggerGen annotation) {
		Endpoint endpoint = new Endpoint();
		endpoint.setSummary(annotation.title());
		endpoint.setDescription(annotation.description());
		endpoint.setParameters(ParameterFactory.createParameters(annotation));
		endpoint.setRequestBody(RequestBodyFactory.createRequestBody(annotation));
		endpoint.setResponses(ResponseFactory.creaeteResponses(annotation));
		return endpoint;
	}
}
