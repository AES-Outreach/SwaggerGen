package domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import domain.path.Endpoint;
import enums.RequestMethod;

/**
 * Domain object representing an entire Swagger file.
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class Swagger {

	/**
	 * Top level map, mapping all endpoints by their request method and URL
	 */
	private Map<String, Map<RequestMethod, Endpoint>> paths;

	public Map<String, Map<RequestMethod, Endpoint>> getPaths() {
		return paths;
	}

	public void setPaths(Map<String, Map<RequestMethod, Endpoint>> paths) {
		this.paths = paths;
	}
	
}
