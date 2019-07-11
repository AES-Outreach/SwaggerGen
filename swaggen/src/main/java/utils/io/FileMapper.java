package utils.io;

import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;


import domain.output.Swagger;
import enums.RequestMethod;
import domain.output.path.Endpoint;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Converts between POJOs and files.
 * 
 * @author William Gardiner (7267012)
 */
public class FileMapper {

	/**
	 * Maps a JSON file to a Java object representation of the json file.
	 * 
	 * @param filename the name of the JSON file
	 * @param klass    the class the represents the JSON structure
	 * @param <T>      The type
	 * 
	 * @return the object that the JSON mapped to
	 * @throws JsonParseException JsonParseException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	public static <T> T jsonToClass(String filename, Class<T> klass)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(filename);
		return objectMapper.readValue(file, klass);
	}

	/**
	 * Maps a Java object to a YAML file.
	 * 
	 * @param filename the name of the YAML file
	 * @param object the Java object
	 * @param <T> The type
	 * 
	 * @throws JsonGenerationException JsonGenerationException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	public static <T> void classToYaml(String filename, T object)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		objectMapper.writeValue(new File(filename), object);
	}

	/**
	 * Maps a Swagger object to a YAML file.
	 * 
	 * @param filename the name of the YAML file
	 * @param newSwagger the Swagger
	 * 
	 * @throws JsonGenerationException JsonGenerationException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	public static void classToYaml(String filename, Swagger newSwagger)
		throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		File file = new File(filename);

		checkExistingEndpoints(newSwagger, file, objectMapper);

		Swagger toYaml = convertSwagger(newSwagger, filename, file, objectMapper);
		objectMapper.writeValue(file, toYaml);
	}

	/**
	 * Checks for existing endpoints in yaml files and if matched, add the existing
	 * request methods to the new Swagger file.
	 * @param swagger the swagger object containing all the endpoitns
	 * @param file file object, containing where it is supposed to be placed
	 * @param objectMapper the ObjectMapper
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private static void checkExistingEndpoints(Swagger swagger, File file, ObjectMapper objectMapper) 
		throws JsonGenerationException, JsonMappingException, IOException {
		if(!file.exists()) {
			return;
		}
		Swagger existingSwagger = objectMapper.readValue(file, Swagger.class);
		for (String newURL: swagger.getPaths().keySet()) {
			for (String oldURL: existingSwagger.getPaths().keySet()) {
				if (existingSwagger.getPaths().containsKey(newURL)) {
					swagger.getPaths().get(newURL).putAll(existingSwagger.getPaths().get(oldURL));
				}
			}
		}
	}


	/**
	 * Finds the correct request method and endpoint within the swagger map given the filename, and writes
	 * it to a yaml file
	 * @param swagger the swagger object containing all the endpoints
	 * @param filename name of the file
	 * @param file file object, containing where it is supposed to be placed
	 * @param objectMapper the ObjectMapper
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private static Swagger convertSwagger(Swagger swagger, String filename, File file, ObjectMapper objectMapper)
		throws JsonGenerationException, JsonMappingException, IOException {
		for (String url: swagger.getPaths().keySet()) {
			int basePathIndex = filename.lastIndexOf("/");
			String basePath = "/" + filename.substring(0, basePathIndex);

			if (url.equals(basePath)) {
				Swagger toYaml = new Swagger(swagger);
				Map<String, Map<RequestMethod, Endpoint>> exactEndpoint = new HashMap<>();
				exactEndpoint.put(url, swagger.getPaths().get(url));
				toYaml.setPaths(exactEndpoint);
				return toYaml;
			}
		}
		return new Swagger();
	}
}
