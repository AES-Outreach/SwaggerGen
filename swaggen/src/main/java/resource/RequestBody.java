package resource;

import enums.ContentType;

/**
 * Contains the Body of the request with its type
 * 
 * @author William Gardiner (7267012)
 */
public class RequestBody {

	/**
	 * The type
	 */
	private ContentType type;
	
	/**
	 * The body
	 */
	private ContentSchema schema;
	
	public ContentType getType() {
		return type;
	}
	
	public void setType(ContentType type) {
		this.type = type;
	}
	
	public ContentSchema getSchema() {
		return schema;
	}
	
	public void setSchema(ContentSchema schema) {
		this.schema = schema;
	}
	
	@Override
	public String toString() {
		return "RequestBody [type=" + type + ", schema=" + schema + "]";
	}
}
