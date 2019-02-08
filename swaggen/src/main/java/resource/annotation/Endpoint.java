package resource.annotation;

import java.util.List;

import resource.jsonschema.RequestBody;

/**
 * Represents a REST Endpoint. It is referenced from the Path by its method.
 * 
 * @author William Gardiner (7267012)
 */
public class Endpoint {
	
	/**
	 * The summary
	 */
	private String summary;
	
	/**
	 * The description 
	 */
	private String description;
	
	/**
	 * The parameters
	 */
	private List<Parameter> parameters;
	
	/**
	 * The request body
	 */
	private RequestBody requestBody;
	
	/**
	 * The possible responses
	 */
	private List<Response> responses;
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public RequestBody getRequestBody() {
		return requestBody;
	}
	
	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}
	
	public List<Response> getResponses() {
		return responses;
	}
	
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "Endpoint [summary=" + summary + ", description=" + description + ", parameters=" + parameters
				+ ", requestBody=" + requestBody + ", responses=" + responses + "]";
	}
}
