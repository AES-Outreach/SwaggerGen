package generator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	 * @param klasses s set of annotated classes
	 * @return the map
	 */
	public static Map<String, SwaggerEndpoint> generatePathsFromClassList(Class<?>[] klasses) {
		Map<String, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		for(Class<?> klass : klasses) {
			Map<String, SwaggerEndpoint> classPaths = generatePathsFromClass(klass);
			checkClassEndpoints(existingPaths, classPaths);
		}
		return existingPaths;
	}

	/**
	 * Generates a Map of URLs and RequestMethods to Endpoints.
	 * 
	 * @param klass the annotated class
	 * @return the map
	 */
	private static Map<String, SwaggerEndpoint> generatePathsFromClass(Class<?> klass) {
		Map<String, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		for(Method method : methods) {
			SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
			checkRequestMethods(annotation, existingPaths);
		}
		return existingPaths;
	}
	
	/**
	 * Generates a path from an annotation
	 * 
	 * @param annotation the annotation
	 * @return the path
	 */
	private static SwaggerEndpoint generatePathFromAnnotation(SwaggerGen annotation) {
		SwaggerEndpoint endpoint = new SwaggerEndpoint();
		endpoint.addEndpoint(RequestMethod.valueOf(annotation.method()), EndpointFactory.createEndpoint(annotation));
		return endpoint;
	}

	/**
	 * Checks if URL is already in the existing map, and if it is, appends the swagger
	 * endpoints to the URL
	 * Case: multiple classes with the same endpoint
	 * @param existingPaths Map of existing Swagger Endpoints
	 * @param classPaths Map of new Swagger Endpoints in the class
	 */
	private static void checkClassEndpoints(Map<String, SwaggerEndpoint> existingPaths, Map<String, SwaggerEndpoint> classPaths) {
		if (!existingPaths.isEmpty()) {
			for(Map.Entry<String, SwaggerEndpoint> pathEntry: existingPaths.entrySet()) {
				for(Map.Entry<String, SwaggerEndpoint> classEntry: classPaths.entrySet()) {
					if (pathEntry.getKey().equals(classEntry.getKey())) {
						pathEntry.getValue().addEndpoint(classEntry.getValue());
					} else {
						existingPaths.putAll(classPaths);
					}
				}
			}
		}	else {
		existingPaths.putAll(classPaths);
		}
	}

	/**
	 * Checks if there are any other request methods within a class
	 * and puts it in the swagger endpoints map
	 * Case: multiple request methods within the same class
	 * @param annotation SwaggerGen annotation
	 * @param existingPaths Map of existing Swagger Endpoints
	 */
	private static void checkRequestMethods(SwaggerGen annotation, Map<String, SwaggerEndpoint> existingPaths) {
		if(annotation != null) {
			if (!existingPaths.containsKey(annotation.basePath() + annotation.uri())){
				existingPaths.put(annotation.basePath() + annotation.uri(), generatePathFromAnnotation(annotation));
			} else {
				existingPaths.get(annotation.basePath() + annotation.uri()).addEndpoint(generatePathFromAnnotation(annotation));
			}
		}
	}
}
