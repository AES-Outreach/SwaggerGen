package factory;

import java.util.ArrayList;
import java.util.List;

import annotation.SwaggerGen;
import domain.path.Response;

/**
 * Creates a response from the annotation
 * 
 * @author William Gardiner (7267012)
 */
public class ResponseFactory {

	public static List<Response> Responses(SwaggerGen annotation) {
		List<Response> responses = new ArrayList<>();
		for(int code : annotation.responses()) {
			Response response = new Response();
			response.setCode(Integer.toString(code));
			responses.add(response);
		}
		if(responses.size() > 0) {
			Response response = responses.get(0);
			response.setBody(ResponseBodyFactory.RequestBody(annotation));
		}
		return responses;
	}

}
