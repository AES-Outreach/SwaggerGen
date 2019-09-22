package factory;

import java.util.HashMap;
import java.util.Map;

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
     */
    public static Map<String, Response> createResponses(SwaggerGen annotation) {
        Map<String, Response> responses = new HashMap<>();
        String expectedResponseCode = "";
        for(SwaggerResponse responseAnnotation : annotation.responses()) {
        	
            Response response = new Response();
            if(responseAnnotation.description() != null) {
                response.setDescription(responseAnnotation.description());
            } else {
                response.setDescription(DEFAULT_DESCRIPTION);
            }
            
            String code = String.valueOf(responseAnnotation.code());
            responses.put(code, response);
            
            if("".contentEquals(expectedResponseCode)) {
                expectedResponseCode = code;
            }
        }
        if(annotation.responses().length > 0) {
            responses.get(expectedResponseCode).setSchema(ResponseBodyFactory.createRequestBody(annotation));
        }
        return responses;
    }

}
