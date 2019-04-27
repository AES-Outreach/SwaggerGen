package tests.jsonschema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.input.jsonschema.JsonSchema;
import domain.input.jsonschema.Property;
import enums.PropertyType;
import utils.io.FileMapper;

/**
 * Tests the JsonSchema Deserializer
 * 
 * @author William Gardiner (7267012)
 */
public abstract class BaseSchemaDeserializerTest {

	/**
	 * The schema version
	 */
	protected static String SCHEMA = "http://json-schema.org/draft-07/schema#";

	/**
	 * The schema object
	 */
	protected static JsonSchema schema;

	protected void setFile(String filename) throws JsonParseException, JsonMappingException, IOException {
		schema = FileMapper.jsonToClass(filename, JsonSchema.class);
	}

	/**
	 * Asserts that a property matches the expected format
	 * 
	 * @param properties the map of properties
	 * @param key the property key
	 * @param id the property id
	 * @param type the property type
	 * @param title the property title
	 * @return the property
	 */
	protected Property assertProperty(Map<String, Property> properties, String key, String id, PropertyType type,
			String title) {
		if (properties == null || !properties.containsKey(key)) {
			assertFalse(true);
			return null;
		}
		Property property = properties.get(key);
		assertEquals(property.getId(), id);
		assertEquals(property.getTitle(), title);
		assertEquals(property.getType(), type);
		switch (type) {
		case OBJECT:
			assertNotNull(property.getProperties());
			assertNull(property.getItems());
			break;
		case ARRAY:
			assertNull(property.getProperties());
			assertNotNull(property.getItems());
			break;
		default:
			assertNull(property.getProperties());
			assertNull(property.getItems());
		}
		return property;
	}
	
	/**
	 * Asserts that the list item property matches the expected format
	 * 
	 * @param property the property
	 * @param id the property id
	 * @param type the property type
	 * @param title the property title
	 * @return the property
	 */
	protected Property assertItem(Property property, String id, PropertyType type, String title) {
		if (property == null || property.getItems() == null) {
			assertFalse(true);
			return null;
		}
		Property item = property.getItems();
		assertEquals(item.getId(), id);
		assertEquals(item.getTitle(), title);
		assertEquals(item.getType(), type);
		switch (type) {
		case OBJECT:
			assertNotNull(item.getProperties());
			assertNull(item.getItems());
			break;
		case ARRAY:
			assertNull(item.getProperties());
			assertNotNull(item.getItems());
			break;
		default:
			assertNull(item.getProperties());
			assertNull(item.getItems());
		}
		return item;
	}

}
