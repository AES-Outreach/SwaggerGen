package generator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

import annotation.SwaggerGen;
import domain.output.SwaggerEndpoint;
import domain.output.PathURL;
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
	public static Map<PathURL, SwaggerEndpoint> generatePathsFromClassList(Class<?>[] klasses) {
		Map<PathURL, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		for(Class<?> klass : klasses) {
			Map<PathURL, SwaggerEndpoint> classPaths = generatePathsFromClass(klass);
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
	private static Map<PathURL, SwaggerEndpoint> generatePathsFromClass(Class<?> klass) {
		Map<PathURL, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		ArrayList<Method> swaggerMethods = new ArrayList<Method>();
		for(Method method : methods) {
			if (method.isAnnotationPresent(SwaggerGen.class)) {
				swaggerMethods.add(method);
			}
		}
		for(Method method : swaggerMethods){
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
	private static void checkClassEndpoints(Map<PathURL, SwaggerEndpoint> existingPaths, Map<PathURL, SwaggerEndpoint> classPaths) {
		if (existingPaths.isEmpty()) {
			existingPaths.putAll(classPaths);
			return;
		}

		for(PathURL pathKey: existingPaths.keySet()) {
			if (classPaths.containsKey(pathKey)) {
				existingPaths.get(pathKey).addEndpoint(classPaths.get(pathKey));
			} else {
				existingPaths.putAll(classPaths);
			}
		}
	}

	/**
	 * Checks if there are any other request methods within a class
	 * and puts it in the swagger endpoints map
	 * Case: multiple request methods within the same class
	 * @param annotation SwaggerGen annotation
	 * @param existingPaths Map of existing Swagger Endpoints
	 */
	private static void checkRequestMethods(SwaggerGen annotation, Map<PathURL, SwaggerEndpoint> existingPaths) {
		if (annotation == null) {
			throw new IllegalArgumentException("annotation cannot be null");
		}
		
		PathURL pathURL = new PathURL(annotation.basePath(), annotation.uri());
		if (!existingPaths.containsKey(pathURL)){
			existingPaths.put(pathURL, generatePathFromAnnotation(annotation));
		} else {
			existingPaths.get(pathURL).addEndpoint(generatePathFromAnnotation(annotation));
		}
		// for (PathURL paths : existingPaths.keySet()) {
		// 	PathURL pathURL = new PathURL(annotation.basePath(), annotation.uri());
		// 	if (!paths.getFullPath().equals(fullPath)){
		// 		existingPaths.put(pathURL, generatePathFromAnnotation(annotation));
		// 	} else {
		// 		existingPaths.get(pathURL).addEndpoint(generatePathFromAnnotation(annotation));
		// 	}
		// }
	}
}
