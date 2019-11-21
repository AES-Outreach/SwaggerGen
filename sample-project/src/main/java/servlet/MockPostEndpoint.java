package servlet;
import annotation.SwaggerGen;
import annotation.SwaggerResponse;

/**
 * A Fake endpoint for testing.
 * 
 * @author William Gardiner (7267012)
 */
public class MockPostEndpoint {

    @SwaggerGen(
        uri="/endpoint",
        basePath="/base",
        method="POST",
        title="Post",
        description="Serverlet Description",
        headers={"Content-Type=application/json"},
        queryParams={
            "i param = An Integer Param", 
            "b param2 = A Boolean Param", 
            "s64 param3 = A String Param",
            "param4 = Another String Param"
            },
        requestBody="body.scheme",
        responses={
            	@SwaggerResponse(),
            	@SwaggerResponse(code=400),
            	@SwaggerResponse(code=401),
            	@SwaggerResponse(code=403),
            	@SwaggerResponse(code=500)        		
           },
        responseBody= "response.scheme"
    )
    protected static final void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
        // Implementation not important.
    }
}
