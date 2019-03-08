package tests.jsonschema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import enums.PropertyType;
import resource.jsonschema.JsonSchema;
import resource.jsonschema.Property;
import utils.FileMapper;

public abstract class BaseSchemaDeserializerTest {

	protected static String SCHEMA = "http://json-schema.org/draft-07/schema#";

	protected static JsonSchema schema;

	protected void setFile(String filename) throws JsonParseException, JsonMappingException, IOException {
		schema = FileMapper.jsonToClass(filename, JsonSchema.class);
	}

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
