package tests.jsonschema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import enums.PropertyType;
import resource.jsonschema.Property;

/**
 * Tests the JSON Schema Parsing
 * 
 * @author William Gardiner (7267012)
 */
public class DeserializeSchemaTests extends BaseSchemaDeserializerTest {
	
	/**
	 * Tests every nested property in a fairly simple, yet exhaustive schema.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void testSimpleJsonSchema() throws JsonParseException, JsonMappingException, IOException {
		setFile("swaggen/test/schema/simple_object_schema.json");
		
		// Top Level Schema
		assertEquals(schema.getSchema(), SCHEMA);
		assertEquals(schema.getId(), "http://example.com/root.json");
		assertEquals(schema.getType(), PropertyType.OBJECT);
		assertEquals(schema.getTitle(), "The Root Schema");
		assertNotNull(schema.getProperties());
		
		//Properties
		Property glossary = assertProperty(
				schema.getProperties(), "glossary", "#/properties/glossary", 
				PropertyType.OBJECT, "The Glossary Schema");
		if(glossary == null) { return; }
		
		Property title = assertProperty(
				glossary.getProperties(), "title", "#/properties/glossary/properties/title", 
				PropertyType.STRING, "The Title Schema");
		if(title == null) { return; }
		
		Property glossDiv = assertProperty(
				glossary.getProperties(), "GlossDiv", "#/properties/glossary/properties/GlossDiv", 
				PropertyType.OBJECT, "The Glossdiv Schema");
		if(glossDiv == null) { return; }
		
		Property glossDivTitle = assertProperty(
				glossDiv.getProperties(), "title", "#/properties/glossary/properties/GlossDiv/properties/title", 
				PropertyType.STRING, "The Title Schema");
		if(glossDivTitle == null) { return; }
		
		Property glossList = assertProperty(
				glossDiv.getProperties(), "GlossList", "#/properties/glossary/properties/GlossDiv/properties/GlossList", 
				PropertyType.OBJECT, "The Glosslist Schema");
		if(glossList == null) { return; }
		
		Property glossEntry = assertProperty(
				glossList.getProperties(), "GlossEntry", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry", 
				PropertyType.OBJECT, "The Glossentry Schema");
		if(glossEntry == null) { return; }
		
		Property glossEntryId = assertProperty(
				glossEntry.getProperties(), "ID", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/ID", 
				PropertyType.STRING, "The Id Schema");
		if(glossEntryId == null) { return; }
		
		Property sortAs = assertProperty(
				glossEntry.getProperties(), "SortAs", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/SortAs", 
				PropertyType.STRING, "The Sortas Schema");
		if(sortAs == null) { return; }
		
		Property glossTerm = assertProperty(
				glossEntry.getProperties(), "GlossTerm", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/GlossTerm", 
				PropertyType.STRING, "The Glossterm Schema");
		if(glossTerm == null) { return; }
		
		Property acronym = assertProperty(
				glossEntry.getProperties(), "Acronym", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/Acronym", 
				PropertyType.STRING, "The Acronym Schema");
		if(acronym == null) { return; }
		
		Property abbrev = assertProperty(
				glossEntry.getProperties(), "Abbrev", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/Abbrev", 
				PropertyType.STRING, "The Abbrev Schema");
		if(abbrev == null) { return; }
		
		Property glossDef = assertProperty(
				glossEntry.getProperties(), "GlossDef", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/GlossDef", 
				PropertyType.OBJECT, "The Glossdef Schema");
		if(glossDef == null) { return; }
		
		Property para = assertProperty(
				glossDef.getProperties(), "para", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/GlossDef/properties/para", 
				PropertyType.STRING, "The Para Schema");
		if(para == null) { return; }
		
		Property glossSeeAlso = assertProperty(
				glossDef.getProperties(), "GlossSeeAlso", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/GlossDef/properties/GlossSeeAlso", 
				PropertyType.ARRAY, "The Glossseealso Schema");
		if(glossSeeAlso == null) { return; }
		
		Property glossSeeAlsoItem = assertItem(
				glossSeeAlso, "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/GlossDef/properties/GlossSeeAlso/items", 
				PropertyType.STRING, "The Items Schema");
		if(glossSeeAlsoItem == null) { return; }
		
		Property glossSee = assertProperty(
				glossEntry.getProperties(), "GlossSee", "#/properties/glossary/properties/GlossDiv/properties/GlossList/properties/GlossEntry/properties/GlossSee", 
				PropertyType.STRING, "The Glosssee Schema");
		if(glossSee == null) { return; }
		
	}
	
	/**
	 * Tests the ability to deserialize many schemas in succession. 
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test 
	public void testManySchemas() throws JsonParseException, JsonMappingException, IOException {
		for(int i = 0; i < 100; i++) {
			setFile("swaggen/test/schema/simple_object_schema.json");
			assertNotNull(schema.getTitle());
			schema = null;
		}
	}
	
	/**
	 * Tests the ability to deserialize a larger schema.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test 
	public void testLargeSchema() throws JsonParseException, JsonMappingException, IOException {
		setFile("swaggen/test/schema/large_schema.json");
		
		assertEquals(schema.getSchema(), SCHEMA);
		assertEquals(schema.getId(), "http://example.com/root.json");
		assertEquals(schema.getType(), PropertyType.ARRAY);
		assertEquals(schema.getTitle(), "The Root Schema");
		assertNotNull(schema.getItems());
		
	}
	
	/**
	 * Tests the appropriate response from an improperly formatted JSON file.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test(expected = JsonMappingException.class)
	public void TestBadJson() throws JsonParseException, JsonMappingException, IOException {
		setFile("swaggen/test/schema/bad_json.json");
	}
	
	/**
	 * Tests a schema with no body.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test 
	public void testEmptyBody() throws JsonParseException, JsonMappingException, IOException {
		setFile("swaggen/test/schema/empty_body.json");
		
		assertEquals(schema.getSchema(), SCHEMA);
		assertEquals(schema.getId(), "http://example.com/root.json");
		assertEquals(schema.getType(), PropertyType.OBJECT);
		assertEquals(schema.getTitle(), "The Root Schema");
		assertNull(schema.getProperties());
		
		
	}
	
	/**
	 * Tests an empty file.
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test(expected = IOException.class) 
	public void testEmptyFile() throws JsonParseException, JsonMappingException, IOException {
		setFile("swaggen/test/schema/empty_file.json");
	}
	
	/**
	 * Cleans up the schema.
	 */
	@After
	public void after() {
		schema = null;
	}
}
