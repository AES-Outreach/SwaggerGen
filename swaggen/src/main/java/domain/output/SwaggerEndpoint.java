package domain.output;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import domain.input.jsonschema.JsonSchema;
import domain.output.content.Components;
import domain.output.path.Endpoint;
import enums.RequestMethod;

/**
 * Class containing a map of the request method and endpoint required to make a
 * swagger file.
 * 
 */
public class SwaggerEndpoint {
    private Map<RequestMethod, Endpoint> endpoint;

    public SwaggerEndpoint() {
        this.endpoint = new HashMap<>();
    };

    public Endpoint getEndpoint(RequestMethod request) {
        return this.endpoint.get(request);
    }

    public Map<RequestMethod, Endpoint> getEndpointMap() {
        return this.endpoint;
    }

    public void setEndpoint(Map<RequestMethod, Endpoint> Endpoint) {
        this.endpoint = Endpoint;
    }

    public void addEndpoint(RequestMethod request, Endpoint endpoint) {
        this.endpoint.put(request, endpoint);
    }

    public void addEndpoint(SwaggerEndpoint endpoint) {
        this.endpoint.putAll(endpoint.getEndpointMap());
    }

    /**
     * Extracts the map from the variable so that it will be compatible as a swagger
     * file.
     * 
     * @param paths The map with SwaggerEndpoints as values
     * @return Swagger compatible map
     */
    public static Map<PathInfo, Map<RequestMethod, Endpoint>> convertToValid(Map<PathInfo, SwaggerEndpoint> paths) {
        Map<PathInfo, Map<RequestMethod, Endpoint>> newPaths = new ConcurrentHashMap<>();
        for (PathInfo url : paths.keySet()) {
            newPaths.put(url, paths.get(url).getEndpointMap());
        }
        return newPaths;
    }

    /**
     * Extracts the map from the variable so that it will be compatible as a swagger
     * file.
     * 
     * @param paths The map with SwaggerEndpoints as values
     * @return Swagger compatible map
     */
    public static Components getDefinitions(Map<PathInfo, SwaggerEndpoint> paths) {
    	Components components = new Components(); 
    	Map<String, JsonSchema> allDefinitions = new ConcurrentHashMap<>();
        
    	 for (PathInfo url : paths.keySet()) {
    		 Map<RequestMethod, Endpoint> endpoint = paths.get(url).getEndpointMap();
    		 
    		 for(RequestMethod method : endpoint.keySet()) {
    			 allDefinitions.putAll(endpoint.get(method).getDefinitions());
    		 }
    		
        }
    	if(allDefinitions.size() > 0) {
    		components.setSchemas(allDefinitions);
            return components;
    	} else {
    		return null;
    	}
    		
    }
}