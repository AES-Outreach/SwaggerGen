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
	 * @throws JsonParseException JsonParseException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	public static <T> void classToYaml(String filename, T newSwagger)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		File file = new File(filename);
		// Check for existing endpoints in yaml file
		if (file.exists() && newSwagger instanceof Swagger) {
			Swagger existingSwagger = objectMapper.readValue(file, Swagger.class);
			for (String newURL: ((Swagger)newSwagger).getPaths().keySet()) {
				for (String oldURL: existingSwagger.getPaths().keySet()) {
					if (newURL.equals(oldURL)) {
						((Swagger)newSwagger).getPaths().get(newURL).putAll(existingSwagger.getPaths().get(oldURL));
					}
				}
			}
		}

		// Discard any endpoints from the given swagger that don't belong in the file
		if (newSwagger instanceof Swagger) {
			for (Map.Entry<String, Map<RequestMethod, Endpoint>> path: ((Swagger)newSwagger).getPaths().entrySet()) {
				String key = path.getKey();
				int basePathIndex = filename.lastIndexOf("/");
				String basePath = "/" + filename.substring(0, basePathIndex);
				if (key.equals(basePath)) {
					Swagger toYaml = new Swagger();
					toYaml.setInfo(((Swagger)newSwagger).getInfo());
					toYaml.setVersion(((Swagger)newSwagger).getOpenapi());

					Map<String, Map<RequestMethod, Endpoint>> exactEndpoint = new HashMap<>();
					exactEndpoint.put(path.getKey(), path.getValue());
					toYaml.setPaths(exactEndpoint);

					objectMapper.writeValue(file, toYaml);
					break;
				}
			}
		}else{
			objectMapper.writeValue(file, newSwagger);
		}
	}
}
