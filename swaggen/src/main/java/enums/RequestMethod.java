package enums;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Http request methods
 * 
 * @author William Gardiner (7267012)
 */
public enum RequestMethod {
	GET("get"), POST("post"), PUT("put"), DELETE("delete");
	
	private String value;

	private RequestMethod(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

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
