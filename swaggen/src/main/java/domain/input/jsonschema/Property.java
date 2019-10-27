package domain.input.jsonschema;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import enums.PropertyType;

/**
 * The Jackson map for a JSON Schema Property
 * 
 * @author William Gardiner (7267012)
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Property {

    /**
     * A Regex Pattern
     */
    @JsonInclude(Include.NON_NULL)
    private String pattern;
    

    /**
     * Minimum number of items for an array property
     */
    @JsonInclude(Include.NON_NULL)
    private Integer maxItems;
    
    /**
     * Maximum number of items for an array property
     */
    @JsonInclude(Include.NON_NULL)
    private Integer minItems;

    /**
     * The Property Type
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
   
    public String getPattern() {
        return pattern;
    }
    
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
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
        return "Property [pattern=" + pattern + ", type=" + type + ", properties="
                + properties + ", items=" + items + "]";
    }

	public Integer getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(Integer maxItems) {
		this.maxItems = maxItems;
	}

	public Integer getMinItems() {
		return minItems;
	}

	public void setMinItems(Integer minItems) {
		this.minItems = minItems;
	}
}
