package servlet;
import annotation.SwaggerGen;
import annotation.SwaggerResponse;
/**
 * A Fake endpoint for testing.
 * 
 * @author Alexandre Seguin (7663995)
 */
public class MockAllMethodsEndpoint {

    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/put-endpoint",
        basePath="/base/all",
        method="PUT",
        title="All",
        description="Put Servlet Description",
        headers={"Content-Type=application/json", "langHeader=en"},
        requestBody="schemas/all/orderPut.json",
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

    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/put-endpoint",
        basePath="/base/all",
        method="POST",
        title="All",
        description="Post Servlet Description",
        headers={"Content-Type=application/json", "langHeader=en"},
        requestBody="schemas/all/orderPost.json",
        responses={
            	@SwaggerResponse(),
            	@SwaggerResponse(code=400),
            	@SwaggerResponse(code=401),
            	@SwaggerResponse(code=403),
            	@SwaggerResponse(code=500)        		
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
        uri="/get-endpoint",
        basePath="/base/all",
        method="GET",
        title="All",
        description="Get Servlet Description",
        headers={"Content-Type=application/json", "langHeader=en"},
        queryParams={
            "i order_id = id of the order being requested"
            },
        responses={
            	@SwaggerResponse(),
            	@SwaggerResponse(code=400),
            	@SwaggerResponse(code=401),
            	@SwaggerResponse(code=403),
            	@SwaggerResponse(code=500)        		
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
        uri="/delete-endpoint",
        basePath="/base/all",
        method="DELETE",
        title="All",
        description="Delete Servlet Description",
        headers={"Content-Type=application/json", "langHeader=en"},
        queryParams={
            "i order_id = id of the order being requested"
            },
        responses={
            	@SwaggerResponse(),
            	@SwaggerResponse(code=400),
            	@SwaggerResponse(code=401),
            	@SwaggerResponse(code=403),
            	@SwaggerResponse(code=500)        		
           }
    )
    public void doDelete(Object request, Object resp) {
        // Implementation not important.
    }
}
