package resource.jsonschema;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import enums.PropertyType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonSchema {

	@JsonProperty("$schema")
	private String schema;

	@JsonProperty("$id")
	private String id;
	
	@JsonProperty("type")
	private PropertyType type;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("required")
	private List<String> required;

	@JsonProperty("properties")
	private Map<String, Property> properties;

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
	
	public List<String> getRequired() {
		return required;
	}
	
	public void setRequired(List<String> required) {
		this.required = required;
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
		return "JsonSchema [schema=" + schema + ", id=" + id + ", type=" + type + ", title=" + title + ", required="
				+ required + ", properties=" + properties + ", items=" + items + "]";
	}
}
