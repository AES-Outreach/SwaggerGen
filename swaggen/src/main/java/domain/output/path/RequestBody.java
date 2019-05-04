package domain.output.path;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import enums.ContentType;

/**
 * Contains the Body of the request with its type
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class RequestBody {

	/**
	 * The type
	 */
	private ContentType type;
	
	/**
	 * The body
	 */
	private String filename;

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "RequestBody [type=" + type + ", filename=" + filename + "]";
	}
	
}
