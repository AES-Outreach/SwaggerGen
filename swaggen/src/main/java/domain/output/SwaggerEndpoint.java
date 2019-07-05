package domain.output;

import java.util.HashMap;
import java.util.Map;


import enums.RequestMethod;
import domain.output.path.Endpoint;

public class SwaggerEndpoint {
  private Map<RequestMethod, Endpoint> endpoint;

  public SwaggerEndpoint() {
    this.endpoint = new HashMap<>();
  };

  public Endpoint getEndpoint(RequestMethod request) {
    return this.endpoint.get(request);
  }

  public Map<RequestMethod, Endpoint> getEndpoint() {
    return this.endpoint;
  }

  public void setEndpoint(Map<RequestMethod, Endpoint> Endpoint) {
    this.endpoint = Endpoint;
  }

  public void addEndpoint(RequestMethod request, Endpoint endpoint) {
    this.endpoint.put(request, endpoint);
  }

  public void addEndpoint(SwaggerEndpoint endpoint) {
    this.endpoint.putAll(endpoint.getEndpoint());
  }

  public static Map<String, Map<RequestMethod, Endpoint>> convertToValid(Map<String, SwaggerEndpoint> paths) {
    Map <String, Map<RequestMethod, Endpoint>> newPaths = new HashMap<>();
    for (Map.Entry<String, SwaggerEndpoint> endpoints: paths.entrySet()) {
			newPaths.put(endpoints.getKey(), endpoints.getValue().getEndpoint());
    }	
    return newPaths;
  }
}