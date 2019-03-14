package enums;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Format options in Swagger.
 * 
 * @author William Gardiner (7267012)
 */
public enum ParamFormat {
	
	/**
	 * 32 bit String
	 */
	STR_32("str32"), 
	
	/**
	 * 64 bit String
	 */
	STR_64("str64"), 
	
	/**
	 * 32 bit Integer
	 */
	INT_32("int32"), 
	
	/**
	 * 64 bit Integer
	 */
	INT_64("int64"), 
	
	/**
	 * Floating point number
	 */
	FLOAT("float"), 
	
	/**
	 * Double precision floating point number
	 */
	DOUBLE("double"), 
	
	/**
	 * Date [yyyy]-[mm]-[dd]
	 */
	DATE("date"), 
	
	/**
	 * Date [yyyy]-[mm]-[dd]T[hh]:[MM]:ss[+/-]hh:mm
	 */
	DATE_TIME("date-time"), 
	
	/**
	 * Byte
	 */
	BYTE("byte"), 
	
	/**
	 * Binary String
	 */
	BINARY("binary");
	
	/**
	 * The String representation
	 */
	private String value;

	private ParamFormat(String value) {
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
