package resource.annotation;

import resource.jsonschema.RequestBody;

/**
 * A response with a code, description, and body
 * 
 * @author William Gardiner (7267012)
 */
public class Response {

	/**
	 * The response code
	 */
	private String code;
	
	/**
	 * The optional description
	 */
	private String description;
	
	/**
	 * The optional body
	 */
	private RequestBody body;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public RequestBody getBody() {
		return body;
	}
	
	public void setBody(RequestBody body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "Response [code=" + code + ", description=" + description + ", body=" + body + "]";
	}
}
