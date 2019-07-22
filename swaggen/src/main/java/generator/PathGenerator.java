package generator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import annotation.SwaggerGen;
import annotation.SwaggerGenClass;
import domain.output.SwaggerEndpoint;
import domain.output.PathUri;
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
	 * @param klasses list of annotated classes
	 * @return the map
	 */
	public static Map<PathUri, SwaggerEndpoint> generatePathsFromClassList(Class<?>[] klasses) {
		Map<PathUri, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		for(Class<?> klass : klasses) {
			Map<PathUri, SwaggerEndpoint> classPaths = generatePathsFromClass(klass);
			existingPaths = checkClassEndpoints(existingPaths, classPaths);
		}
		return existingPaths;
	}

	/**
	 * Generates a Map of URLs and RequestMethods to Endpoints.
	 * 
	 * @param klass the annotated class
	 * @return the map
	 */
	private static Map<PathUri, SwaggerEndpoint> generatePathsFromClass(Class<?> klass) {
		Map<PathUri, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		SwaggerGenClass klassAnnotation = klass.getAnnotation(SwaggerGenClass.class);
		for(Method method : methods) {
			SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
			checkRequestMethods(annotation, existingPaths, klassAnnotation);
		}
		return existingPaths;
	}
	
	/**
	 * Generates a path from an annotation
	 * 
	 * @param annotation the annotation
	 * @return the path
	 */
	private static SwaggerEndpoint generatePathFromAnnotation(SwaggerGen annotation, SwaggerGenClass klassAnnotation) {
		SwaggerEndpoint endpoint = new SwaggerEndpoint();
		endpoint.addEndpoint(RequestMethod.valueOf(annotation.method()), EndpointFactory.createEndpoint(annotation, klassAnnotation));
		
		return endpoint;
	}

	/**
	 * Checks if URL is already in the existing map, and if it is, appends the swagger
	 * endpoints to the URL
	 * Case: multiple classes with the same endpoint
	 * @param existingPaths Map of existing Swagger Endpoints
	 * @param classPaths Map of new Swagger Endpoints in the class
	 */
	private static Map<PathUri, SwaggerEndpoint> checkClassEndpoints(Map<PathUri, SwaggerEndpoint> existingPaths, Map<PathUri, SwaggerEndpoint> classPaths) {
		for (PathUri existingPath: existingPaths.keySet()) {
			for (PathUri pathKey : classPaths.keySet()) {
				if ((existingPath.getBasePath().equals(pathKey.getBasePath())) && 
					(existingPath.getURI().equals(pathKey.getURI()))) {
					existingPaths.get(existingPath).addEndpoint(classPaths.get(pathKey));
					classPaths.remove(pathKey);
				}
			}
		}
		existingPaths.putAll(classPaths);
		return existingPaths;
	}

	/**
	 * Checks if there are any other request methods within a class
	 * and puts it in the swagger endpoints map
	 * Case: multiple request methods within the same class
	 * @param annotation SwaggerGen annotation
	 * @param existingPaths Map of existing Swagger Endpoints
	 */
	private static void checkRequestMethods(SwaggerGen annotation, Map<PathUri, SwaggerEndpoint> existingPaths, SwaggerGenClass klassAnnotation) {
		if (annotation == null) {
			throw new IllegalArgumentException("annotation cannot be null");
		}

		String basePath = (annotation.basePath().isBlank() && klassAnnotation != null) ? klassAnnotation.basePath() : annotation.basePath();
		PathUri pathUri = new PathUri(basePath, annotation.uri());

		if (existingPaths.isEmpty()) {
			existingPaths.put(pathUri, generatePathFromAnnotation(annotation, klassAnnotation));
		}
		for (PathUri existingPath: existingPaths.keySet()) {
			if ((existingPath.getBasePath().equals(pathUri.getBasePath())) && 
			(existingPath.getURI().equals(pathUri.getURI()))) {
				existingPaths.get(existingPath).addEndpoint(generatePathFromAnnotation(annotation, klassAnnotation));
			} else {
				existingPaths.put(pathUri, generatePathFromAnnotation(annotation, klassAnnotation));
			}
		}
	}
}
