package generator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.SwaggerGen;
import domain.output.SwaggerEndpoint;
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
	public static Map<String, List<SwaggerEndpoint>> generatePathsFromClassList(Class<?>[] klasses) {
		Map<String, List<SwaggerEndpoint>> pathMap = new HashMap<>();
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
	private static Map<String, List<SwaggerEndpoint>> generatePathsFromClass(Class<?> klass) {
		Map<String, List<SwaggerEndpoint>> pathMap = new HashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		for(Method method : methods) {
			SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
			if(annotation != null) {
				if (!pathMap.containsKey(annotation.basePath() + annotation.uri())){
					List<SwaggerEndpoint> empty = new ArrayList<>();
					pathMap.put(annotation.basePath() + annotation.uri(), empty);
				}
				pathMap.get(annotation.basePath() + annotation.uri()).add(generatePathFromAnnotation(annotation));
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
	private static SwaggerEndpoint generatePathFromAnnotation(SwaggerGen annotation) {
		SwaggerEndpoint endpoint = new SwaggerEndpoint(new HashMap<>());
		endpoint.addEndpointMap(RequestMethod.valueOf(annotation.method()), EndpointFactory.createEndpoint(annotation));
		return endpoint;
	}
}
