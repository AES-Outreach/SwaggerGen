package factory;

import annotation.SwaggerGen;
import domain.output.path.RequestBody;

/**
 * Generates a RequestBody from the requestBody in the annotation.
 * 
 * @author Alexandre Seguin (7663995)
 *
 */
public class RequestBodyFactory {

    /**
     * Generates a Request body from the annotation
     * 
     * @param annotation the annotation
     * @return the request body
     */
    public static RequestBody createRequestBody(SwaggerGen annotation) {
        if (annotation.requestBody() != null) {
        	RequestBody requestBody = new RequestBody();
        	requestBody.setContent(BodyFactory.createContent(annotation.requestBody(), 
        			annotation.title(), annotation.method()+"-RequestBody"));
        	return requestBody;
        }
        return null;
    }

}
