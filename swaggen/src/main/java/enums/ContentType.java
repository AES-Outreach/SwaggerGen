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

    /**
     * JSON
     */
    JSON("application/json");

    /**
     * The String representation
     */
    private String value;

    /**
     * Constructor
     * 
     * @param value
     */
    private ContentType(String value) {
        this.value = value;
    }

    /**
     * Converts the ENUM to the String representation.
     * 
     * @return the string representation of the ENUM
     */
    @JsonValue
    public String value() {
        return value;
    }

    /**
     * Converts the String representation to the ENUM.
     * 
     * @param value the String representation
     * @return the ENUM value
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
