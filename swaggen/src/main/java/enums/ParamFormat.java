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
	STR_32("str32"), STR_64("str64"), 
	INT_32("int32"), INT_64("int64"), 
	FLOAT("float"), DOUBLE("double"), 
	DATE("date"), DATE_TIME("date-time"), 
	BYTE("byte"), BINARY("binary");

	private String value;

	private ParamFormat(String value) {
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
