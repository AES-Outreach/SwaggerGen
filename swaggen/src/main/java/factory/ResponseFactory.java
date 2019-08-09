package factory;

import java.util.HashMap;
import java.util.Map;

import annotation.SwaggerGen;
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
        for(String responseString : annotation.responses()) {			
            String[] responseSplit = responseString.split(DELIMITER);
            Response response = new Response();
            if(responseSplit.length == 2) {
                response.setDescription(responseSplit[DESCRIPTION_INDEX]);
            } else {
                response.setDescription(DEFAULT_DESCRIPTION);
            }
            
            responses.put(responseSplit[CODE_INDEX], response);
            if("".contentEquals(expectedResponseCode)) {
                expectedResponseCode = responseSplit[CODE_INDEX];
            }
        }
        if(annotation.responses().length > 0) {
            responses.get(expectedResponseCode).setBody(ResponseBodyFactory.createRequestBody(annotation));
        }
        return responses;
    }

}
