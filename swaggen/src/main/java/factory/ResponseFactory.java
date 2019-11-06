package factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.HttpStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import annotation.SwaggerGen;
import annotation.SwaggerResponse;
import domain.output.path.Response;


/**
 * Creates a response from the annotation
 * 
 * @author William Gardiner (7267012)
 */
public class ResponseFactory {
    
    /**
     * The description when no description id provided
     */
    public static final String DEFAULT_DESCRIPTION = "No description";
    
    /**
     * The properties path to a default response body
     */
    public static final String DEFAULT_RESPONE_BODY = "default.response.body.";
    
    /**
     * The properties path to a default response description
     */
    public static final String DEFAULT_RESPONE_DESCRIPTION = "default.response.description.";
    
    /**
     * Generates a Map of response codes to Responses from the annotation
     * 
     * @param annotation the annotation
     * @return the map
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static Map<String, Response> createResponses(SwaggerGen annotation, Properties config) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Response> responses = new HashMap<>();
        for(SwaggerResponse responseAnnotation : annotation.responses()) {
            Response response = new Response();
            response.setDescription(getDescription(responseAnnotation, config));
            response.setContent(ResponseBodyFactory.createRequestBody(getBody(responseAnnotation, config), annotation.title(), annotation.method()));
  
            responses.put(String.valueOf(responseAnnotation.code()), response);
        }
       
        return responses;
    }
    
    /**
     * Get the description form:
     * 1. the provided description
     * 2. the config description
     * 3. the default message based on code
     * 4. generic no description
     * 
     * @param annotation
     * @return
     */
    private static String getDescription(SwaggerResponse annotation, Properties config) {
    	String defaultStatusText = HttpStatus.getStatusText(annotation.code());
    	if(annotation.description() != null) {
            return annotation.description();
        } else if(config.getProperty(DEFAULT_RESPONE_DESCRIPTION + Integer.toString(annotation.code())) != null) {
        	return config.getProperty(DEFAULT_RESPONE_DESCRIPTION + Integer.toString(annotation.code()));
    	} else if(defaultStatusText != null) {
            return defaultStatusText;
        }
    	return DEFAULT_DESCRIPTION;
    }
    
    /**
     * Get the body from:
     * 1. the provided body
     * 2. the config body
     * 3. null
     * 
     * @param annotation
     * @param config
     * @return
     */
    private static String getBody(SwaggerResponse annotation, Properties config) {
    	if(!annotation.body().value().contentEquals("")) {
    		return annotation.body().value();
    	} else if(config.getProperty(DEFAULT_RESPONE_BODY + Integer.toString(annotation.code())) != null) {
    		return config.getProperty(DEFAULT_RESPONE_BODY + Integer.toString(annotation.code()));
    	}
    	return null;
    }

}
