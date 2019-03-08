package factory;

import java.util.HashMap;
import java.util.Map;

import annotation.SwaggerGen;
import domain.path.Response;

/**
 * Creates a response from the annotation
 * 
 * @author William Gardiner (7267012)
 */
public class ResponseFactory {

	public static Map<String, Response> Responses(SwaggerGen annotation) {
		Map<String, Response> responses = new HashMap<>();
		for(int code : annotation.responses()) {
			responses.put(Integer.toString(code), new Response());
		}
		if(annotation.responses().length > 0) {
			String expectedResponseCode = Integer.toString(annotation.responses()[0]);
			responses.get(expectedResponseCode).setBody(ResponseBodyFactory.RequestBody(annotation));
		}
		return responses;
	}

}
