package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Maps JSON to POJOs
 * 
 * @author William Gardiner (7267012)
 */
public class JsonObjectMapper {

	/**
	 * Given a class of type T and a filename, maps the JSON file to the POJO
	 * described by the class.
	 * 
	 * @param fileName the filename
	 * @param klass the class
	 * @return the object of type T
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T fileToClass(String fileName, Class<T> klass)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(fileName);
		return objectMapper.readValue(file, klass);
	}

}
