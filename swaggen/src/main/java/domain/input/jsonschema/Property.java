package domain.input.jsonschema;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
     * The Property ID
     */
    @JsonProperty("$id")
    private String id;

    /**
     * The Property title
     */
    @JsonProperty("title")
    private String title;

    /**
     * A Regex Pattern
     */
    @JsonProperty("pattern")
    private String pattern;
    

    /**
     * Minimum number of items for an array property
     */
    @JsonProperty("maxItems")
    private Integer maxItems;
    
    /**
     * Maximum number of items for an array property
     */
    @JsonProperty("minItems")
    private Integer minItems;

    /**
     * The Property Type
     */
    @JsonProperty("type")
    private PropertyType type;

    /**
     * The properties if type is OBJECT
     */
    @JsonProperty("properties")
    private Map<String, Property> properties;

    /**
     * The items if type is ARRAY
     */
    @JsonProperty("items")
    private Property items;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
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
        return "Property [id=" + id + ", title=" + title + ", pattern=" + pattern + ", type=" + type + ", properties="
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
