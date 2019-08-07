package generator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.output.Swagger;
import domain.output.SwaggerEndpoint;
import domain.output.SwaggerInfo;
import domain.output.PathUri;
import enums.RequestMethod;
import domain.output.path.Endpoint;
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
	 * 
	 * @throws JsonParseException JsonParseException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 **/
	public static void generateSwaggerFile(Class<?>[] klasses) throws JsonParseException, JsonMappingException, IOException {
		
		// Create Paths
		Map<PathUri, SwaggerEndpoint> paths = generator.PathGenerator.generatePathsFromClassList(klasses);

		Swagger swagger = new Swagger();
		swagger.setVersion("3.0.0");
		
		SwaggerInfo info = new SwaggerInfo();
		// These will be read from a config file.
		info.setTitle("Title");
		info.setDescription("Description");
		info.setVersion("0.0");
		
		swagger.setInfo(info);
		swagger.setSwaggerPaths(SwaggerEndpoint.convertToValid(paths));

		createYamlFiles(swagger);
	}

	/**
	 * Sets up a swagger object to be written to a yaml file
	 * @param swagger Swagger object with all the paths
	 * @throws JsonParseException JsonParseException
	 * @throws JsonMappingException JsonMappingException
	 * @throws IOException IOException
	 */
	private static void createYamlFiles(Swagger swagger) throws JsonParseException, JsonMappingException, IOException {
		for(PathUri path: swagger.getSwaggerPaths().keySet()) {
			File file = new File("swagger/" + path.getFullPath().substring(1));
			file.mkdirs();
			// yaml file's name will be the base path and the class name
			String name = "/" + path.getFullPath().substring(1).replaceAll("/", "_") + "-test.yaml";
			path.setFilename(path.getFullPath() + name);

			Swagger toYaml = new Swagger(swagger);
			Map<String, Map<RequestMethod, Endpoint>> exactEndpoint = new HashMap<>();
			exactEndpoint.put(path.getFullPath(), swagger.getSwaggerPaths().get(path));
			toYaml.setPaths(exactEndpoint);
			FileMapper.classToYaml(path.getFilename(), toYaml);
		}
	}
}
