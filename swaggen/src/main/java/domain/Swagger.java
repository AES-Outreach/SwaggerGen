package domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import domain.path.Endpoint;
import enums.RequestMethod;

@JsonInclude(Include.NON_NULL)
public class Swagger {

	private Map<String, Map<RequestMethod, Endpoint>> paths;

	public Map<String, Map<RequestMethod, Endpoint>> getPaths() {
		return paths;
	}

	public void setPaths(Map<String, Map<RequestMethod, Endpoint>> paths) {
		this.paths = paths;
	}
	
}
