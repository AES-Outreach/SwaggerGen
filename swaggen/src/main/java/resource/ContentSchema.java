package resource;

import java.util.List;
import java.util.Map;

import enums.SchemaType;
import exceptions.WrongSchemaTypeException;

/**
 * Defines a JSON Structure
 * 
 * @author William Gardiner (7267012)
 */
public class ContentSchema {

	/**
	 * Defines the type of structure this element is
	 */
	private SchemaType type;
	
	/**
	 * If type is ARRAY: it will contain a list of ContentSchemas 
	 */
	private List<ContentSchema> items;
	
	/**
	 * If type is OBJECT: it will contain a map of key to ContentSchemas
	 */
	private Map<String, ContentSchema> properties;
	
	/**
	 * If type is SIMPLE: it will contatin a simple value
	 */
	private String value;
	
	public ContentSchema(SchemaType type) {
		this.type = type;
	}
	
	public SchemaType getType() {
		return type;
	}

	public List<ContentSchema> getItems() throws WrongSchemaTypeException {
		if(type == SchemaType.ARRAY)
			return items;
		else
			throw new WrongSchemaTypeException("Schema must be of type ARRAY!");
	}

	public void setItems(List<ContentSchema> items) throws WrongSchemaTypeException {
		if(type == SchemaType.ARRAY)
			this.items = items;
		else
			throw new WrongSchemaTypeException("Schema must be of type ARRAY!");
	}

	public Map<String, ContentSchema> getProperties() throws WrongSchemaTypeException {
		if(type == SchemaType.OBJECT)
			return properties;
		else
			throw new WrongSchemaTypeException("Schema must be of type OBJECT!");
	}

	public void setProperties(Map<String, ContentSchema> properties) throws WrongSchemaTypeException {
		if(type == SchemaType.OBJECT)
			this.properties = properties;
		else
			throw new WrongSchemaTypeException("Schema must be of type OBJECT!");
	}

	public String getValue() throws WrongSchemaTypeException {
		if(type == SchemaType.SIMPLE)
			return value;
		else
			throw new WrongSchemaTypeException("Schema must be of type SIMPLE!");
	}

	public void setValue(String value) throws WrongSchemaTypeException {
		if(type == SchemaType.SIMPLE)
			this.value = value;
		else
			throw new WrongSchemaTypeException("Schema must be of type SIMPLE!");
	}

	@Override
	public String toString() {
		switch(type) {
		case ARRAY:
			return items.toString();
		case OBJECT:
			return properties.toString();
		case SIMPLE:
			return value;
		default: 
			return "";
		}
		
	}
}
