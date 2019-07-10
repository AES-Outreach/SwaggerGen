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
	 * @param newSwagger the Java object, typically swagger object 
	 * @param <T> The type
	 * 
	 * @throws JsonGenerationException JsonGenerationException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	public static <T> void classToYaml(String filename, T newSwagger)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		File file = new File(filename);

		checkExistingEndpoints(newSwagger, file, objectMapper);

		if (newSwagger instanceof Swagger) {
			Swagger toYaml = convertSwagger(newSwagger, filename, file, objectMapper);
			objectMapper.writeValue(file, toYaml);
		} else {
			objectMapper.writeValue(file, newSwagger);
		}
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
	private static <T> void checkExistingEndpoints(T swagger, File file, ObjectMapper objectMapper) 
		throws JsonGenerationException, JsonMappingException, IOException {
		if (file.exists() && swagger instanceof Swagger) {
			Swagger existingSwagger = objectMapper.readValue(file, Swagger.class);
			for (String newURL: ((Swagger)swagger).getPaths().keySet()) {
				for (String oldURL: existingSwagger.getPaths().keySet()) {
					if (newURL.equals(oldURL)) {
						((Swagger)swagger).getPaths().get(newURL).putAll(existingSwagger.getPaths().get(oldURL));
					}
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
	private static <T> Swagger convertSwagger(T swagger, String filename, File file, ObjectMapper objectMapper)
		throws JsonGenerationException, JsonMappingException, IOException {
		for (Map.Entry<String, Map<RequestMethod, Endpoint>> path: ((Swagger)swagger).getPaths().entrySet()) {
			String url = path.getKey();
			int basePathIndex = filename.lastIndexOf("/");
			String basePath = "/" + filename.substring(0, basePathIndex);

			if (url.equals(basePath)) {
				Swagger toYaml = new Swagger(((Swagger)swagger));
				Map<String, Map<RequestMethod, Endpoint>> exactEndpoint = new HashMap<>();
				exactEndpoint.put(path.getKey(), path.getValue());
				toYaml.setPaths(exactEndpoint);
				return toYaml;
			}
		}
		return new Swagger();
	}
}
