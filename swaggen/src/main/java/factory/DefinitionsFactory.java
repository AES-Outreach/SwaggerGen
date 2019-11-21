package factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import annotation.SwaggerGen;
import annotation.SwaggerResponse;
import domain.input.jsonschema.JsonSchema;
import domain.output.path.ApplicationJson;
import domain.output.path.Content;
import domain.output.path.Schema;

/**
 * Generates a RequestBody from the responseBody in the annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class DefinitionsFactory {

	/**
	 * Generates a response body from the annotation.
	 * 
	 * @param annotation the annotation
	 * @return the response body.
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static Map<String, JsonSchema> createDefinitions(SwaggerGen annotation)
			throws JsonParseException, JsonMappingException, IOException {
		String title = annotation.title();
    	String method = annotation.method();
    	
    	Map<String, JsonSchema> definitions = new HashMap<String, JsonSchema>();

        for(SwaggerResponse responseAnnotation : annotation.responses()) {
        	String schemaLocation = responseAnnotation.body().value(); 
        	if(!schemaLocation.contentEquals("")) {

        		ObjectMapper mapper = new ObjectMapper();
        		try {
        			String schema = IOUtils.toString(
        					DefinitionsFactory.class.getResourceAsStream(schemaLocation), "UTF-8");
        			
        			JsonSchema schemaPOJO = mapper.readValue(schema, JsonSchema.class);
        			String definitionName = nameGenerator(title, method);
        			
        			definitions.put(definitionName, schemaPOJO);
        		} catch (IOException e) {
        			throw new IOException("Invalid schema resource path added to annotation for response body: "
        					+ schemaLocation, e);
        		}
        	}
        }
		return definitions;
	}
	
	public static String nameGenerator(String title, String method) {
		return ((title != null && title.length() > 0)
				? title.replaceAll("\\s","") 
				: "Untitled")
				+ "-" + method;
	}
}
