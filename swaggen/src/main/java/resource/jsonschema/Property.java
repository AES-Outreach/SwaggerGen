package resource.jsonschema;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import enums.PropertyType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Property {

	@JsonProperty("$id")
	private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("pattern")
	private String pattern;

	@JsonProperty("examples")
	private List<String> examples;

	@JsonProperty("default")
	private String def;

	@JsonProperty("required")
	private List<String> required;

	@JsonProperty("type")
	private PropertyType type;

	@JsonProperty("properties")
	private Map<String, Property> properties;

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
	
	public List<String> getExamples() {
		return examples;
	}
	
	public void setExamples(List<String> examples) {
		this.examples = examples;
	}
	
	public String getDef() {
		return def;
	}
	
	public void setDef(String def) {
		this.def = def;
	}
	
	public List<String> getRequired() {
		return required;
	}
	
	public void setRequired(List<String> required) {
		this.required = required;
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
		return "Property [id=" + id + ", title=" + title + ", pattern=" + pattern + ", examples=" + examples + ", def="
				+ def + ", required=" + required + ", type=" + type + ", properties=" + properties + ", items=" + items
				+ "]";
	}
}
