package domain.path;

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
