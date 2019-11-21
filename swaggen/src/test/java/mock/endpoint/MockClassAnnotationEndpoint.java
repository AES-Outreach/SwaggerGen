package mock.endpoint;
import annotation.SwaggerGenClass;
import annotation.SwaggerResponse;
import annotation.SwaggerGen;
/**
 * A Fake endpoint class for testing class level annotations.
 * 
 */
@SwaggerGenClass(
    basePath = "/base",
    title = "Class Test Title",
    description = "Class Test Description",
    servers = {
        "http://classapi.example.com = This is its description",
        "{scheme}://classapi2.example.com = This uses scheme",
        "http://localhost:{port} = This uses port",
        "{scheme}://{environment}.example.com = This uses environment"
    },
    scheme = "https",
    headers = {"Content-Type=application/json"},
    port = "8080",
    environment = "api"
)
public class MockClassAnnotationEndpoint {
  /**
     * Fake endpoint for testing purposes
     * empty headers
     * 
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/class",
        method="PUT",
        requestBody="schemas/all/orderPut.json",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400), 
        		@SwaggerResponse(code=401), 
        		@SwaggerResponse(code=403),
        		@SwaggerResponse(code=404)
        }
    )
    public void doPut(Object request, Object resp) {
        // Implementation not important.
    }
    
    /**
     * Fake endpoint for testing purposes
     * Some headers, overwriting title and description
     * 
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/class",
        method="GET",
        title="Overwriting class title",
        description="Overwriting class description",
        headers={"langHeader=en"},
        requestBody="schemas/all/orderPut.json",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400), 
        		@SwaggerResponse(code=401), 
        		@SwaggerResponse(code=403),
        		@SwaggerResponse(code=404)
        }
    )
    public void doGet(Object request, Object resp) {
        // Implementation not important.
  }
  
  /**
     * Fake endpoint for testing purposes
     * Overwriting basepath
     * 
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/class",
        basePath="/base/endpoint",
        method="POST",
        headers={"langHeader=en"},
        requestBody="schemas/all/orderPost.json",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400), 
        		@SwaggerResponse(code=401), 
        		@SwaggerResponse(code=404),
        		@SwaggerResponse(code=418)
        }
    )
    public void doPost(Object request, Object resp) {
        // Implementation not important.
    }
}