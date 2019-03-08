package utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.path.Path;

/**
 * Connects the plugin with the SwaggerGen logic
 * 
 * @author Danny Petitclerc (8074166)
 */

public class Main {

	public static void exec(Class<?>[] klasses) throws JsonParseException, JsonMappingException, IOException {
		
		/**
		 * Given an array of classes that contain methods annotated with SwaggerGen,
		 * generate a list of associated paths and POJO
		 * 
		 * @param klasses an array of classes that contain methods annotated with SwaggerGen
		 * @throws JsonParseException
		 * @throws JsonMappingException
		 * @throws IOException
		 **/
		
		List<Path> paths = generator.PathGenerator.generatePathsFromClassList(klasses);
		for(Class<?> k: klasses) {
			utils.JsonObjectMapper.fileToClass(k.getName(), k);
		}
	}
}
