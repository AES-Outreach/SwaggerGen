package domain.output.path;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A response with a code, description, and body
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class Response {
    
    /**
     * The description
     */
    private String description;
    
    /**
     * The optional body
     */
    private RequestBody body;
    
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
    
}
