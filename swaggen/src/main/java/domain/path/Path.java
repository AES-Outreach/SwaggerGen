package domain.path;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import enums.RequestMethod;

/**
 * A collection of all Endpoints belonging to a single URL
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class Path {
	
	/**
	 * The Endpoints
	 */
	private Map<RequestMethod, Endpoint> endpoints;

	public Map<RequestMethod, Endpoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Map<RequestMethod, Endpoint> endpoints) {
		this.endpoints = endpoints;
	}	
}
