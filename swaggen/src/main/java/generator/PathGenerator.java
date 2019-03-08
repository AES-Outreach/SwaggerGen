package generator;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import annotation.SwaggerGen;
import domain.path.Endpoint;
import enums.RequestMethod;
import factory.EndpointFactory;

/**
 * Generates a list of paths from a list of classes. Main entry point for managing the input.
 * 
 * @author William Gardiner (7267012)
 */
public class PathGenerator {

	public static Map<String, Map<RequestMethod, Endpoint>> generatePathsFromClassList(Class<?>[] klasses) {
		Map<String, Map<RequestMethod, Endpoint>> pathMap = new HashMap<>();
		for(Class<?> klass : klasses) {
			pathMap.putAll(generatePathsFromClass(klass));
		}
		return pathMap;
	}

	private static Map<String, Map<RequestMethod, Endpoint>> generatePathsFromClass(Class<?> klass) {
		Map<String, Map<RequestMethod, Endpoint>> pathMap = new HashMap<>();
		Method[] methods = klass.getDeclaredMethods();
		for(Method method : methods) {
			SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
			if(annotation != null) {
				pathMap.put(annotation.url(), generatePathFromMethod(method, annotation));
			}
		}
		return pathMap;
	}
	
	private static Map<RequestMethod, Endpoint> generatePathFromMethod(Method method, SwaggerGen annotation) {
		Map<RequestMethod, Endpoint> endpointMap = new HashMap<>();
		endpointMap.put(RequestMethod.valueOf(annotation.method()), EndpointFactory.Endpoint(annotation));
		return endpointMap;
	}
}
