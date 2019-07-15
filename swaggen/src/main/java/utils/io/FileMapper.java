package utils.io;

import java.io.File;
import java.io.IOException;

import domain.output.Swagger;

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
	 * @param path The file path of the swagger file
	 * @param newSwagger the Swagger
	 * 
	 * @throws JsonGenerationException JsonGenerationException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	public static void classToYaml(String path, Swagger newSwagger)
		throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		File file = new File(path);

		checkExistingEndpoints(newSwagger, file, objectMapper);
		objectMapper.writeValue(file, newSwagger);
	}

	/**
	 * Checks for existing endpoints in yaml files and if matched, add the existing
	 * request methods to the new Swagger file.
	 * @param swagger the swagger object containing all the endpoints
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
}
