package generator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.output.Swagger;
import domain.output.SwaggerEndpoint;
import domain.output.SwaggerInfo;
import domain.output.PathInfo;
import domain.output.ServerURL;
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
     * @param klasses an array of classes that contain methods annotated with
     *                SwaggerGen
     * @param config  The config properties taken from config.properties file
     * 
     * @throws JsonParseException   JsonParseException
     * @throws JsonMappingException JsonMappingException
     * @throws IOException          IOException
     **/
    public static void generateSwaggerFile(Class<?>[] klasses, Properties config)
            throws JsonParseException, JsonMappingException, IOException {

        // Create Paths
        Map<PathInfo, SwaggerEndpoint> paths = generator.PathGenerator.generatePathsFromClassList(klasses);

        Swagger swagger = new Swagger();
        swagger.setVersion(config.getProperty("version"));

        SwaggerInfo info = new SwaggerInfo();
        ServerURL serverURL = new ServerURL(config.getProperty("server"), config.getProperty("serverDescription"));
        info.setTitle(config.getProperty("title"));
        info.setDescription(config.getProperty("description"));
        info.setVersion("0.0.1");

        swagger.setInfo(info);
        swagger.setServers(new ArrayList<ServerURL>(Arrays.asList(serverURL)));
        swagger.setSwaggerPaths(SwaggerEndpoint.convertToValid(paths));
        // TODO : clean up components. 
        // only add to files with the matching components.
        // need to also look into 
        swagger.setComponents(SwaggerEndpoint.getDefinitions(paths));

        createYamlFiles(swagger);
    }

    /**
     * Sets up a swagger object to be written to a yaml file
     * 
     * @param swagger Swagger object with all the paths
     * @throws JsonParseException   JsonParseException
     * @throws JsonMappingException JsonMappingException
     * @throws IOException          IOException
     */
    private static void createYamlFiles(Swagger swagger) throws JsonParseException, JsonMappingException, IOException {
        for (PathInfo path : swagger.getSwaggerPaths().keySet()) {
            File file = new File("swagger/" + path.getFullPath());
            file.mkdirs();
            // yaml file's name will be the base path and the class name
            String name = "/" + path.getFullPath().replaceAll("/", "_") + "-test.yaml";
            path.setFilename(path.getFullPath() + name);

            Swagger toYaml = new Swagger(swagger);
            if (!path.getServer().isEmpty()) {
                toYaml.setServers(path.getServer());
            }
            Map<String, Map<RequestMethod, Endpoint>> exactEndpoint = new HashMap<>();
            exactEndpoint.put(path.getFullPath(), swagger.getSwaggerPaths().get(path));
            toYaml.setPaths(exactEndpoint);

            FileMapper.classToYaml(path.getFilename(), toYaml);
        }
    }
}
