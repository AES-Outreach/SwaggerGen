package enums;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Base param types in Swagger
 * 
 * @author William Gardiner (7267012)
 */
public enum ParamType {
	
	/**
	 * String
	 */
	STRING("string"), 
	
	/**
	 * Integer
	 */
	INTEGER("integer"), 
	
	/**
	 * Floating point number
	 */
	NUMBER("number"), 
	
	/**
	 * Boolean
	 */
	BOOLEAN("boolean");
	
	/**
	 * The String representation
	 */
	private String value;

	private ParamType(String value) {
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
