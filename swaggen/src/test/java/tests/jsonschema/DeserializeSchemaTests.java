package tests.jsonschema;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import resource.jsonschema.JsonSchema;
import utils.JsonObjectMapper;

public class DeserializeSchemaTests {
	
	@Test
	public void TestJosnSchemaComplex() throws JsonParseException, JsonMappingException, IOException {
		String filename = "testschema.json";
		JsonSchema schema = JsonObjectMapper.fileToClass(filename, JsonSchema.class);
	}

}
