package mock.endpoint;
import annotation.SwaggerGen;
import annotation.SwaggerResponse;
/**
 * Fake endpoints for testing
 */
public class MockShareBasePath {
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/in-depth",
        basePath="/base/endpoint",
        method="PUT",
        description="Put Servlet Description",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400), 
        		@SwaggerResponse(code=404)
        }
    )
    public void doPut(Object request, Object resp) {
        // Implementation not important.
    }
  
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/in-depth",
        basePath="/base/endpoint",
        method="GET",
        description="Put Servlet Description",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400),
        		@SwaggerResponse(code=404)
        }
    )
    public void doGet(Object request, Object resp) {
        // Implementation not important.
    }
  
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/in-depth",
        basePath="/base/endpoint",
        method="POST",
        description="Post Servlet Description",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400),
        		@SwaggerResponse(code=404)
        }
    )
    public void doPost(Object request, Object resp) {
        // Implementation not important.
    }
  
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/in-depth",
        basePath="/base/endpoint",
        method="DELETE",
        description="Delete Servlet Description",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400),
        		@SwaggerResponse(code=404)
        }
    )
    public void doDelete(Object request, Object resp) {
        // Implementation not important.
    }
}