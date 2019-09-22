package mock.endpoint;
import annotation.SwaggerGen;
import annotation.SwaggerResponse;
import mock.service.MockSlingServerletRequest;
import mock.service.MockSlingServerletResponse;

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
        title="Serverlet Title",
        description="Serverlet Description",
        headers={"Content-Type=application/json"},
        queryParams={
                "i param = An Integer Param", 
                "b param2 = A Boolean Param", 
                "s64 param3 = A String Param",
                "param4 = Another String Param"
                },
        body="body.scheme",
        responses={
        		@SwaggerResponse(), 
        		@SwaggerResponse(code=400), 
        		@SwaggerResponse(code=404)
        },
        responseBody= "response.scheme"
    )
    protected static final void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
        // Implementation not important.
    }
}
