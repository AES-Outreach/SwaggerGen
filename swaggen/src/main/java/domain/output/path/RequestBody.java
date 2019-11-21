package domain.output.path;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import enums.ContentType;

/**
 * Contains the Body of the request with its type
 * 
 * @author Alexandre Seguin (7663995)
 */
@JsonInclude(Include.NON_NULL)
public class RequestBody {

    /**
     * The content (schema)
     */
    private Content content;
    
    /**
     * The description for the request body
     */
    private String description;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RequestBody [content=" + content + ", description=" + description + "]";
	}

    
    
}
