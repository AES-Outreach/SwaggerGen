package enums;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Possible content types
 * 
 * @author William Gardiner (7267012)
 */
public enum ContentType {
	JSON("application/json");
	
	private String value;

	private ContentType(String value) {
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
