package factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * The delimiter between response codes and their description
     */
    private static final String DELIMITER = "=";
    
    /**
     * The index of the response code
     */
    private static final int CODE_INDEX = 0;
    
    /**
     * The index of the description
     */
    private static final int DESCRIPTION_INDEX = 1;
    
    /**
     * The description when no description id provided
     */
    private static final String DEFAULT_DESCRIPTION = "No description";
    
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
                response.setDescription(DEFAULT_DESCRIPTION);
            }
            
            String code = String.valueOf(responseAnnotation.code());
            
            if(!responseAnnotation.body().value().contentEquals("")) {
            	response.setContent(ResponseBodyFactory.createRequestBody(responseAnnotation.body().value(), annotation.title(), annotation.method()));
            }
            
            responses.put(code, response);

        }
       
        return responses;
    }

}
