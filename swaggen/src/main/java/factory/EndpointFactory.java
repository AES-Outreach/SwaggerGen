package factory;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import annotation.SwaggerGen;
import annotation.SwaggerGenClass;
import domain.output.path.Endpoint;

/**
 * Creates a Swagger Endpoint from the annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class EndpointFactory {
    /**
     * Creates an Endpoint using both method and class level annotation.
     * 
     * @param annotation the annotation
     * @param klassAnnotation the class level annotation information
     * @return the endpoint
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static Endpoint createEndpoint(SwaggerGen annotation, SwaggerGenClass klassAnnotation) throws JsonParseException, JsonMappingException, IOException {
        Endpoint endpoint = new Endpoint();
        
        if (klassAnnotation != null) {
            endpoint.setSummary(getAttribute(annotation.title(), klassAnnotation.title()));
            endpoint.setDescription(getAttribute(annotation.description(), klassAnnotation.description()));
        } else {
            endpoint.setSummary(annotation.title());
            endpoint.setDescription(annotation.description());
        }
        
        endpoint.setParameters(ParameterFactory.createParameters(annotation, klassAnnotation));
        endpoint.setRequestBody(RequestBodyFactory.createRequestBody(annotation));
        endpoint.setResponses(ResponseFactory.createResponses(annotation));
        endpoint.setDefinitions(DefinitionsFactory.createDefinitions(annotation));
        
        return endpoint;
    }

    private static String getAttribute(String methodAttribute, String classAttribute) {
        return (methodAttribute.isBlank()) ? classAttribute : methodAttribute;
    }
}
