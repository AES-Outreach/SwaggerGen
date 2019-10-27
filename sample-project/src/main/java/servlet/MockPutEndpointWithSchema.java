package servlet;
import annotation.SwaggerGen;
import annotation.SwaggerResponse;
/**
 * A Fake endpoint for testing.
 * 
 * @author Alexandre Seguin (7663995)
 */
public class MockPutEndpointWithSchema {
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/all-methods-endpoint",
        basePath="/base",
        method="PUT",
        title="Put",
        description="Put Servlet Description",
        headers={"Content-Type=application/json", "langHeader=en"},
        body="schemas/fakePutSchema.json",
        responses={
            	@SwaggerResponse(),
            	@SwaggerResponse(code=400),
            	@SwaggerResponse(code=401),
            	@SwaggerResponse(code=403),
            	@SwaggerResponse(code=500)        		
           }
    )
    public void doPut(Object request, Object resp) {
        // Implementation not important.
    }
}
