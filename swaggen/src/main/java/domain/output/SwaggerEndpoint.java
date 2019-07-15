package domain.output;

import java.util.HashMap;
import java.util.Map;


import enums.RequestMethod;
import domain.output.path.Endpoint;
import domain.output.PathURL;

/**
 * Class containing a map of the request method and endpoint required
 * to make a swagger file.
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
   * Extracts the map from the variable so that it will be compatible
   * as a swagger file.
   * 
   * @param paths The map with SwaggerEndpoints as values
   * @return Swagger compatible map 
   */
  public static Map<PathURL, Map<RequestMethod, Endpoint>> convertToValid(Map<PathURL, SwaggerEndpoint> paths) {
    Map <PathURL, Map<RequestMethod, Endpoint>> newPaths = new HashMap<>();
    for (PathURL url: paths.keySet()) {
      newPaths.put(url, paths.get(url).getEndpointMap());
    }
    return newPaths;
  }
}