package domain.output;

import java.util.Map;

import enums.RequestMethod;
import domain.output.path.Endpoint;

public class SwaggerEndpoint {
  private Map<RequestMethod, Endpoint> endpoint;

  public SwaggerEndpoint(Map<RequestMethod,Endpoint> endpoint){
    this.endpoint = endpoint;
  }

  public Endpoint getEndpoint(RequestMethod request) {
    return this.endpoint.get(request);
  }

  public Map<RequestMethod, Endpoint> getEndpointMap() {
    return this.endpoint;
  }

  public void setEndpointMap(Map<RequestMethod, Endpoint> EndpointMap) {
    this.endpoint = EndpointMap;
  }

  public void addEndpointMap(RequestMethod request, Endpoint endpoint) {
    this.endpoint.put(request, endpoint);
  }
}