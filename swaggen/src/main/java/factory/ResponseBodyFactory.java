package factory;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.input.jsonschema.JsonSchema;
import domain.output.path.ApplicationJson;
import domain.output.path.Content;
import domain.output.path.Schema;

/**
 * Generates a RequestBody from the responseBody in the annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class ResponseBodyFactory {

	/**
	 * Generates a response body from the annotation.
	 * 
	 * @param annotation the annotation
	 * @return the response body.
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static Content createRequestBody(String schemaLocation, String servletName, String requestMethod) {
		Content responseSchema = new Content();

		String definitionName = DefinitionsFactory.nameGenerator(servletName, requestMethod);
		responseSchema.setAppjson(buildApplicationPOJO("#/components/schemas/" + definitionName));

		return responseSchema;
	}

	private static ApplicationJson buildApplicationPOJO(String definitionName) {
		Schema schema = new Schema();
		schema.set$ref(definitionName);

		ApplicationJson appjson = new ApplicationJson();
		appjson.setSchema(schema);
		return appjson;
	}

}
