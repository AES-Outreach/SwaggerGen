package domain.input.jsonschema;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import enums.PropertyType;

/**
 * Jackson map for a JSON Schema.
 * 
 * @author William Gardiner (7267012)
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonSchema {

    /**
     * The base type
     */
    @JsonInclude(Include.NON_NULL)
    private PropertyType type;

    /**
     * The properties if type is OBJECT
     */
    @JsonInclude(Include.NON_NULL)
    private Map<String, Property> properties;

    /**
     * The items if type is ARRAY
     */
    @JsonInclude(Include.NON_NULL)
    private Property items;
    
    public PropertyType getType() {
        return type;
    }
    
    public void setType(PropertyType type) {
        this.type = type;
    }
    
    public Map<String, Property> getProperties() {
        return properties;
    }
    
    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }
    
    public Property getItems() {
        return items;
    }
    
    public void setItems(Property items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "JsonSchema [type=" + type + ", properties="
                + properties + ", items=" + items + "]";
    }
}
