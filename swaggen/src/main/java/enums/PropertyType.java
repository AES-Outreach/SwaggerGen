package enums;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Schema types used for describing JSON
 * 
 * @author William Gardiner (7267012)
 */
public enum PropertyType {
    
    /**
     * Object
     */
    OBJECT("object"), 
    
    /**
     * Array
     */
    ARRAY("array"), 
    
    /**
     * Boolean
     */
    BOOLEAN("boolean"), 
    
    /**
     * Integer
     */
    INTEGER("integer"), 
    
    /**
     * Floating point number
     */
    NUMBER("number"), 
    
    /**
     * String
     */
    STRING("string");
    
    /**
     * The String representation
     */
    private String value;

    /**
     * Constructor
     * 
     * @param value the String representation
     */
    private PropertyType(String value) {
        this.value = value;
    }

    /**
     * Converts the ENUM to the String representation
     * 
     * @return the String representation
     */
    @JsonValue
    public String value() {
        return value;
    }

    /**
     * Converts the String representation to the ENUM
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
