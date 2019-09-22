package domain.input.jsonschema;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
     * The Schema used
     */
    @JsonProperty("$schema")
    private String schema;

    /**
     * The Schema ID
     */
    @JsonProperty("$id")
    private String id;
    
    /**
     * The base type
     */
    @JsonProperty("type")
    private PropertyType type;
    
    /**
     * The Schema title
     */
    @JsonProperty("title")
    private String title;

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
    
    public String getSchema() {
        return schema;
    }
    
    public void setSchema(String schema) {
        this.schema = schema;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public PropertyType getType() {
        return type;
    }
    
    public void setType(PropertyType type) {
        this.type = type;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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
        return "JsonSchema [schema=" + schema + ", id=" + id + ", type=" + type + ", title=" + title + ", properties="
                + properties + ", items=" + items + "]";
    }
}
