package generator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import annotation.SwaggerGen;
import annotation.SwaggerGenClass;
import domain.output.PathInfo;
import domain.output.ServerURL;
import domain.output.ServerVariable;
import domain.output.SwaggerEndpoint;
import enums.RequestMethod;
import factory.EndpointFactory;
import utils.stringparsing.ServerParsing;


/**
 * Generates a list of paths from a list of classes. Main entry point for
 * managing the input.
 * 
 * @author William Gardiner (7267012)
 */
public class PathGenerator {

    /**
     * Generates a Map of URLs and RequestMethods to Endpoints.
     * 
     * @param klasses list of annotated classes
     * @return the map
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static Map<PathInfo, SwaggerEndpoint> generatePathsFromClassList(Class<?>[] klasses, Properties config) throws JsonParseException, JsonMappingException, IOException {
        Map<PathInfo, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
        for (Class<?> klass : klasses) {
            Map<PathInfo, SwaggerEndpoint> classPaths = generatePathsFromClass(klass, config);
            existingPaths = checkClassEndpoints(existingPaths, classPaths);
        }
        return existingPaths;
    }

    /**
     * Generates a Map of URLs and RequestMethods to Endpoints.
     * 
     * @param klass the annotated class
     * @return the map
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    private static Map<PathInfo, SwaggerEndpoint> generatePathsFromClass(Class<?> klass, Properties config) throws JsonParseException, JsonMappingException, IOException {
        Map<PathInfo, SwaggerEndpoint> existingPaths = new ConcurrentHashMap<>();
        Method[] methods = klass.getDeclaredMethods();
        SwaggerGenClass klassAnnotation = klass.getAnnotation(SwaggerGenClass.class);
        ArrayList<Method> swaggerMethods = new ArrayList<Method>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(SwaggerGen.class)) {
                swaggerMethods.add(method);
            }
        }
        for (Method method : swaggerMethods) {
            SwaggerGen annotation = method.getAnnotation(SwaggerGen.class);
            checkRequestMethods(annotation, existingPaths, klassAnnotation, config);
        }
        return existingPaths;
    }

    /**
     * Generates a path from an annotation
     * 
     * @param annotation the annotation
     * @return the path
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    private static SwaggerEndpoint generatePathFromAnnotation(SwaggerGen annotation, SwaggerGenClass klassAnnotation, Properties config) throws JsonParseException, JsonMappingException, IOException {
        SwaggerEndpoint endpoint = new SwaggerEndpoint();
        endpoint.addEndpoint(RequestMethod.valueOf(annotation.method()),
                EndpointFactory.createEndpoint(annotation, klassAnnotation, config));

        return endpoint;
    }

    /**
     * Checks if URL is already in the existing map, and if it is, appends the
     * swagger endpoints to the URL Case: multiple classes with the same endpoint
     * 
     * @param existingPaths Map of existing Swagger Endpoints
     * @param classPaths    Map of new Swagger Endpoints in the class
     */
    private static Map<PathInfo, SwaggerEndpoint> checkClassEndpoints(Map<PathInfo, SwaggerEndpoint> existingPaths,
            Map<PathInfo, SwaggerEndpoint> classPaths) {
        for (PathInfo existingPath : existingPaths.keySet()) {
            for (PathInfo pathKey : classPaths.keySet()) {
                if ((existingPath.getBasePath().equals(pathKey.getBasePath()))
                        && (existingPath.getURI().equals(pathKey.getURI()))) {
                    existingPaths.get(existingPath).addEndpoint(classPaths.get(pathKey));
                    classPaths.remove(pathKey);
                }
            }
        }
        existingPaths.putAll(classPaths);
        return existingPaths;
    }

    /**
     * Checks if there are any other request methods within a class and puts it in
     * the swagger endpoints map Case: multiple request methods within the same
     * class
     * 
     * @param annotation    SwaggerGen annotation
     * @param existingPaths Map of existing Swagger Endpoints
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    private static void checkRequestMethods(SwaggerGen annotation, Map<PathInfo, SwaggerEndpoint> existingPaths,
            SwaggerGenClass klassAnnotation, Properties config) throws JsonParseException, JsonMappingException, IOException {
        if (annotation == null) {
            throw new IllegalArgumentException("annotation cannot be null");
        }
        PathInfo pathInfo = addPathInfo(annotation, klassAnnotation);

        if (existingPaths.isEmpty()) {
            existingPaths.put(pathInfo, generatePathFromAnnotation(annotation, klassAnnotation, config));
        }
        for (PathInfo existingPath : existingPaths.keySet()) {
            if ((existingPath.getBasePath().equals(pathInfo.getBasePath()))
                    && (existingPath.getURI().equals(pathInfo.getURI()))) {
                existingPaths.get(existingPath).addEndpoint(generatePathFromAnnotation(annotation, klassAnnotation, config));
            } else {
                existingPaths.put(pathInfo, generatePathFromAnnotation(annotation, klassAnnotation, config));
            }
        }
    }

    /**
     * Creates a new PathInfo object that holds the server url, path base path and
     * uri
     * 
     * @param annotation      method level annotation information
     * @param klassAnnotation class level annotation information
     * @return PathInfo object with necessary information
     */
    private static PathInfo addPathInfo(SwaggerGen annotation, SwaggerGenClass klassAnnotation) {
    	// OpenAPIv3 spec requires paths to start with a slash
        String basePath = (annotation.basePath().isBlank() && klassAnnotation != null) 
        		? "/"+klassAnnotation.basePath()
                : "/"+annotation.basePath();
        String[] servers = (klassAnnotation != null) ? klassAnnotation.servers() : new String[0];
        ArrayList<ServerURL> serverURL = new ArrayList<ServerURL>();

        for (String server : servers) {
            Pair<String, String> serverInfo = ServerParsing.parsePropertyWithDescription(server, "=");

            HashMap<String, ServerVariable> serverVariables = ServerVariable.addServerVariables(serverInfo.getKey(),
                    klassAnnotation);

            ServerURL newServerUrl = new ServerURL(serverInfo.getKey(), serverInfo.getValue(), serverVariables);
            serverURL.add(newServerUrl);
        }
        return new PathInfo(basePath, annotation.uri(), serverURL);
    }
}
