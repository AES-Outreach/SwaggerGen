package generator;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.output.Swagger;
import domain.output.SwaggerInfo;
import domain.output.path.Endpoint;
import enums.RequestMethod;
import utils.io.FileMapper;

/**
 * Connects the plugin with the SwaggerGen logic
 * 
 * @author Danny Petitclerc (8074166)
 */

public class SwaggerGenerator {
	
	/**
	 * Given an array of classes that contain methods annotated with SwaggerGen,
	 * generate a list of associated paths and POJO
	 * 
	 * @param klasses an array of classes that contain methods annotated with SwaggerGen
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 **/
	public static void generateSwaggerFile(Class<?>[] klasses, String filename) throws JsonParseException, JsonMappingException, IOException {
		
		// Create Paths
		Map<String, Map<RequestMethod, Endpoint>> paths = generator.PathGenerator.generatePathsFromClassList(klasses);

		Swagger swagger = new Swagger();
		swagger.setVersion("3.0.0");
		
		SwaggerInfo info = new SwaggerInfo();
		// These will be read from a config file.
		info.setTitle("Title");
		info.setDescription("Description");
		info.setVersion("0.0");
		
		swagger.setInfo(info);
		swagger.setPaths(paths);
		
		File file = new File(filename);
		file.getParentFile().mkdirs();
		
		FileMapper.classToYaml(filename, swagger);
		
	}
	
}
