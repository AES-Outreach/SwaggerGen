package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Maps JSON to POJOs
 * 
 * @author William Gardiner (7267012)
 */
public class FileMapper {

	/**
	 * Maps a JSON file to a Java object representation of the json file.
	 * 
	 * @param filename the name of the JSON file
	 * @param klass    the class the represents the JSON structure
	 * @return the object that the JSON mapped to
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
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
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> void classToYaml(String filename, T object)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		objectMapper.writeValue(new File(filename), object);
	}

}
