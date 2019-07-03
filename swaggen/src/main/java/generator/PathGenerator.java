package generator;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import annotation.SwaggerGen;
import domain.output.path.Endpoint;
import enums.RequestMethod;
import factory.EndpointFactory;

/**
 * Generates a list of paths from a list of classes. Main entry point for managing the input.
 * 
 * @author William Gardiner (7267012)
 */
public class PathGenerator {

	/**
	 * Generates a Map of URLs and RequestMethods to Endpoints.
	 * 
	 * @param klasses s list of annotated classes
	 * @return the map
	 */
	public static Map<String, Map<RequestMethod, Endpoint>> generatePathsFromClassList(Class<?>[] klasses) {
		Map<String, Map<RequestMethod, Endpoint>> pathMap = new HashMap<>();
		for(Class<?> klass : klasses) {
			pathMap.putAll(generatePathsFromClass(klass));
		}
		return pathMap;
	}

	/**
	 * Generates a Map of URLs and RequestMethods to Endpoints.
	 * 
	 * @param klass the annotated class
	 * @return the map
	 */
	private static Map<String, Map<RequestMethod, Endpoint>> generatePathsFromClass(Class<?> klass) {
		Map<String, Map<RequestMethod, Endpoint>> pathMap = new HashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		for(Method method : methods) {
			SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
			if(annotation != null) {
				pathMap.put(annotation.basePath() + annotation.url(), generatePathFromAnnotation(annotation));
			}
		}
		return pathMap;
	}
	
	/**
	 * Generates a path from an annotation
	 * 
	 * @param annotation the annotation
	 * @return the path
	 */
	private static Map<RequestMethod, Endpoint> generatePathFromAnnotation(SwaggerGen annotation) {
		Map<RequestMethod, Endpoint> endpointMap = new HashMap<>();
		endpointMap.put(RequestMethod.valueOf(annotation.method()), EndpointFactory.createEndpoint(annotation));
		return endpointMap;
	}
}
