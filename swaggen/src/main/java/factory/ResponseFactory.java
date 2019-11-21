package factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * Generates a Map of response codes to Responses from the annotation
     * 
     * @param annotation the annotation
     * @return the map
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static Map<String, Response> createResponses(SwaggerGen annotation) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Response> responses = new HashMap<>();
        for(SwaggerResponse responseAnnotation : annotation.responses()) {
        	
            Response response = new Response();
            if(responseAnnotation.description() != null) {
                response.setDescription(responseAnnotation.description());
            } else {
            	String defaultStatusText = HttpStatus.getStatusText(responseAnnotation.code());
                response.setDescription(defaultStatusText != null ? defaultStatusText : DEFAULT_DESCRIPTION);
            }
            
            String code = String.valueOf(responseAnnotation.code());
            
            if(!responseAnnotation.body().value().contentEquals("")) {
            	response.setContent(BodyFactory.createContent(responseAnnotation.body().value(), annotation.title(), annotation.method()));
            }
            
            responses.put(code, response);

        }
       
        return responses;
    }

}
