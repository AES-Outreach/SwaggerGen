package generator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

import annotation.SwaggerGen;
import annotation.SwaggerGenClass;
import domain.output.SwaggerEndpoint;
import domain.output.ServerURL;
import domain.output.PathInfo;
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
	public static Map<PathInfo, SwaggerEndpoint> generatePathsFromClassList(Class<?>[] klasses) {
		Map<PathInfo, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		for(Class<?> klass : klasses) {
			Map<PathInfo, SwaggerEndpoint> classPaths = generatePathsFromClass(klass);
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
	private static Map<PathInfo, SwaggerEndpoint> generatePathsFromClass(Class<?> klass) {
		Map<PathInfo, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		SwaggerGenClass klassAnnotation = klass.getAnnotation(SwaggerGenClass.class);
		ArrayList<Method> swaggerMethods = new ArrayList<Method>();
		for(Method method : methods) {
			if (method.isAnnotationPresent(SwaggerGen.class)) {
				swaggerMethods.add(method);
			}
		}
		for(Method method : swaggerMethods){
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
	private static Map<PathInfo, SwaggerEndpoint> checkClassEndpoints(Map<PathInfo, SwaggerEndpoint> existingPaths, Map<PathInfo, SwaggerEndpoint> classPaths) {
		for (PathInfo existingPath: existingPaths.keySet()) {
			for (PathInfo pathKey : classPaths.keySet()) {
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
	private static void checkRequestMethods(SwaggerGen annotation, Map<PathInfo, SwaggerEndpoint> existingPaths, SwaggerGenClass klassAnnotation) {
		if (annotation == null) {
			throw new IllegalArgumentException("annotation cannot be null");
		}
		PathInfo pathInfo = addPathInfo(annotation, klassAnnotation);

		if (existingPaths.isEmpty()) {
			existingPaths.put(pathInfo, generatePathFromAnnotation(annotation, klassAnnotation));
		}
		for (PathInfo existingPath: existingPaths.keySet()) {
			if ((existingPath.getBasePath().equals(pathInfo.getBasePath())) && 
			(existingPath.getURI().equals(pathInfo.getURI()))) {
				existingPaths.get(existingPath).addEndpoint(generatePathFromAnnotation(annotation, klassAnnotation));
			} else {
				existingPaths.put(pathInfo, generatePathFromAnnotation(annotation, klassAnnotation));
			}
		}
	}

	/**
	 * Creates a new PathInfo object that holds the server url, path base path and uri
	 * @param annotation method level annotation information
	 * @param klassAnnotation class level annotation information
	 * @return PathInfo object with necessary information
	 */
	private static PathInfo addPathInfo(SwaggerGen annotation, SwaggerGenClass klassAnnotation) {
		String basePath = (annotation.basePath().isBlank() && klassAnnotation != null) ? klassAnnotation.basePath() : annotation.basePath();
		String[] servers = (klassAnnotation != null) ? klassAnnotation.servers() : new String[0];
		ArrayList<ServerURL> serverURL = new ArrayList<ServerURL>(); 
		for (String server: servers) {
			String[] urlAndDesc = server.split("=");
			ServerURL newServerUrl = new ServerURL(urlAndDesc[0], urlAndDesc[1]);
			serverURL.add(newServerUrl);
		}
		return new PathInfo(basePath, annotation.uri(), serverURL);
	}
}
