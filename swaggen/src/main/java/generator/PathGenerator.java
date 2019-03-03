package generator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.SwaggerGen;
import domain.path.Endpoint;
import domain.path.Path;
import enums.RequestMethod;
import factory.EndpointFactory;

/**
 * Generates a list of paths from a list of classes. Main entry point for managing the input.
 * 
 * @author William Gardiner (7267012)
 */
public class PathGenerator {
	
	public static List<Path> generatePathsFromClassList(Class<?>[] klasses) {
		List<Path> pathList = new ArrayList<>();
		for(Class<?> klass : klasses) {
			List<Path> generatedPathes = generatePathsFromClass(klass);
			pathList = mergePathLists(pathList, generatedPathes);
		}
		return pathList;
	}

	private static List<Path> generatePathsFromClass(Class<?> klass) {
		List<Path> pathList = new ArrayList<>();
		Method[] methods = klass.getDeclaredMethods();
		for(Method method : methods) {
			SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
			if(annotation != null) {
				Path path = generatePathFromMethod(method, annotation);
				mergePathLists(pathList, path);
			}
		}
		return pathList;
	}
	
	private static Path generatePathFromMethod(Method method, SwaggerGen annotation) {
		Path path = new Path();
		path.setPath(annotation.url());
		
		Map<RequestMethod, Endpoint> endpointMap = new HashMap<>();
		endpointMap.put(RequestMethod.valueOf(annotation.method()), EndpointFactory.Endpoint(annotation));
		path.setEndpoints(endpointMap);
		return path;
	}
	
	private static List<Path> mergePathLists(List<Path> baseList, List<Path> additionalList) {
		for(Path path : additionalList) {
			baseList = mergePathLists(baseList, path);
		}
		return baseList;
	}
	
	private static List<Path> mergePathLists(List<Path> baseList, Path newPath) {
		for(Path basePath : baseList) {
			if(basePath.equals(newPath)) {
				Map<RequestMethod, Endpoint> baseEndpoints = basePath.getEndpoints();
				Map<RequestMethod, Endpoint> newEndpoints = newPath.getEndpoints();
				for(RequestMethod key : newEndpoints.keySet()) {
					baseEndpoints.put(key, newEndpoints.get(key));
				}
				return baseList;
			}
		}
		baseList.add(newPath);
		return baseList;
	}
}
