package utils;

import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.path.Endpoint;
import domain.path.Path;
import domain.path.RequestBody;
import enums.RequestMethod;

/**
 * Connects the plugin with the SwaggerGen logic
 * 
 * @author Danny Petitclerc (8074166)
 */

public class Main {
	
	public static Log log;

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

		log.info("- generating paths -");
		List<Path> paths = generator.PathGenerator.generatePathsFromClassList(klasses);

		for(Path path : paths) {
			for(RequestMethod endpointMethodType : path.getEndpoints().keySet()) {
				Endpoint endpoint = path.getEndpoints().get(endpointMethodType);
				log.info(endpoint.toString());
				if(endpointMethodType.equals(RequestMethod.POST)  || endpointMethodType.equals(RequestMethod.PUT)) {
					// fetch schema parameter
				}
			}
		}
	}
	
}
