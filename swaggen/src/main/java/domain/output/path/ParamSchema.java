package domain.output.path;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import enums.ParamFormat;
import enums.ParamType;

/**
 * The type and format of a Parameter
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class ParamSchema {
	
	/**
	 * Default constructor
	 */
	public ParamSchema() {
		
	}
	
	/**
	 * Constructs a ParamSchema with type and format.
	 * 
	 * @param type the param type
	 * @param format the param format
	 */
	public ParamSchema(ParamType type, ParamFormat format) {
		this.type = type;
		this.format = format;
	}
	
	/**
	 * The type
	 */
	private ParamType type;
	
	/**
	 * The format
	 */
	private ParamFormat format;
	
	public ParamType getType() {
		return type;
	}
	
	public void setType(ParamType type) {
		this.type = type;
	}
	
	public ParamFormat getFormat() {
		return format;
	}
	
	public void setFormat(ParamFormat format) {
		this.format = format;
	}
	
	@Override
	public String toString() {
		return "ParamSchema [type=" + type + ", format=" + format + "]";
	}
}
