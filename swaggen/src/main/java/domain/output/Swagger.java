package domain.output;

import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import domain.output.path.Endpoint;
import enums.RequestMethod;
import domain.output.SwaggerEndpoint;

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
	private String openapi;
	
	/**
	 * The Swagger Version
	 */
	private String swagger;
	
	/**
	 * The API Suite Info
	 */
	private SwaggerInfo info;

	/**
	 * Top level map, mapping all endpoints by their request method and URL
	 */
	private Map<String, List<SwaggerEndpoint>> paths;

	public Map<String, List<SwaggerEndpoint>> getPaths() {
		return paths;
	}

	public void setPaths(Map<String, List<SwaggerEndpoint>> paths) {
		this.paths = paths;
	}

	public String getOpenapi() {
		return openapi;
	}
	
	public String getSwagger() {
		return swagger;
	}

	/**
	 * Field name is openapi for version 3.x, swagger for 2.0
	 * 
	 * @param version the version number.
	 */
	public void setVersion(String version) {
		if("2.0".equals(version)) {
			openapi = null;
			swagger = version;
		} else {
			swagger = null;
			openapi = version;
		}
	}

	public SwaggerInfo getInfo() {
		return info;
	}

	public void setInfo(SwaggerInfo info) {
		this.info = info;
	}
}
