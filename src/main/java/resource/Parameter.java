package resource;

import enums.ParamLocation;

/**
 * A Parameter for an Endpoint. Can be a Header, 
 * Query Parameter, or URL Variable.
 * 
 * @author William Gardiner (7267012)
 */
public class Parameter {
	
	/**
	 * The name/key
	 */
	private String name;
	
	/**
	 * The description
	 */
	private String description;
	
	/**
	 * The location (e.g. Header)
	 */
	private ParamLocation loc;
	
	/**
	 * The Schema (type and format)
	 */
	private ParamSchema schema;
	
	/**
	 * Is the variable required
	 */
	private boolean required;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ParamLocation getLoc() {
		return loc;
	}
	
	public void setLoc(ParamLocation loc) {
		this.loc = loc;
	}
	
	public ParamSchema getSchema() {
		return schema;
	}
	
	public void setSchema(ParamSchema schema) {
		this.schema = schema;
	}
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	@Override
	public String toString() {
		return "Parameter [name=" + name + ", description=" + description + ", loc=" + loc + ", schema=" + schema
				+ ", required=" + required + "]";
	}
}
