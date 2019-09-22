package mock.endpoint;

import annotation.SwaggerGen;
import annotation.SwaggerResponse;

/**
 * Fake endpoints for testing
 */
public class MultiplePathsEndpoint {
    @SwaggerGen(
        uri = "/all-methods-endpoint", 
        basePath = "/base", 
        method = "GET", 
        description = "Get Servlet Description", 
        headers = { "Content-Type=application/json", "langHeader=en" }, 
        responses = {
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400),
        		@SwaggerResponse(code=404)
        }
    )
    public void doGet(Object request, Object resp) {

    }

    @SwaggerGen(
        uri = "/endpoint", 
        basePath = "/base", 
        method = "PUT", 
        description = "Put Servlet Description", 
        headers = { "Content-Type=application/json", "langHeader=en" }, 
        responses = {
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400),
        		@SwaggerResponse(code=404)
        }
    )
    public void doPut(Object request, Object resp) {

    }
}