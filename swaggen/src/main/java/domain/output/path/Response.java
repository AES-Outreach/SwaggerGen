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
     * Contains either a schema or 
     */
	private Content content;    
	
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Response [description=" + description + ", content=" + content + "]";
	}

}