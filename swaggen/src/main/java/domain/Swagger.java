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
	 * The OpenAPI Version
	 */
	private String version;
	
	/**
	 * The API Suite Info
	 */
	private SwaggerInfo info;

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public SwaggerInfo getInfo() {
		return info;
	}

	public void setInfo(SwaggerInfo info) {
		this.info = info;
	}
}
