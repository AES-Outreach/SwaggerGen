package enums;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Param location options in Swagger
 * 
 * @author William Gardiner (7267012)
 */
public enum ParamLocation {
	
	/**
	 * Used if the parameter is in the path of the request.
	 * e.g. "/base/{path}/endpoint"
	 */
	PATH("path"), 
	
	/**
	 * Used if the parameter is a query parameter.
	 * e.g. "base/path?{query}={value}"
	 */
	QUERY("query"), 
	
	/**
	 * Used if the parameter is a header.
	 * e.g. {header}={value}
	 */
	HEADER("header");
	
	/**
	 * The String representation
	 */
	private String value;

	private ParamLocation(String value) {
		this.value = value;
	}

	/**
	 * Converts the ENUM to the String representation
	 * 
	 * @return
	 */
	@JsonValue
	public String value() {
		return value;
	}

	/**
	 * Converts the String representation to the ENUM
	 * 
	 * @param value
	 * @return
	 */
	@JsonCreator
	public PropertyType type(String value) {
		for (PropertyType type : new ArrayList<PropertyType>(Arrays.asList(PropertyType.values()))) {
			if (type.value().equals(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unable to deserialize JSON");
	}
}
