package generator;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.Swagger;
import domain.SwaggerInfo;
import domain.path.Endpoint;
import enums.RequestMethod;
import utils.FileMapper;

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
		Map<String, Map<RequestMethod, Endpoint>> paths = generator.PathGenerator.generatePathsFromClassList(klasses);
//		Set<String> schemaFilenames = new HashSet<>();

		// TODO: Use this code when we are ready to add schemas.
//		for(Path path : paths) {
//			for(RequestMethod endpointMethodType : path.getEndpoints().keySet()) {
//				Endpoint endpoint = path.getEndpoints().get(endpointMethodType);
//				if(endpoint.getRequestBody() != null) {
//					schemaFilenames.add(endpoint.getRequestBody().getFilename());
//				}
//				if(endpoint.getResponses() != null) {
//					for(Response response : endpoint.getResponses()) {
//						if(response.getBody() != null) {
//							schemaFilenames.add(response.getBody().getFilename());
//						}
//					}
//				}
//			}
//		}
		
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
