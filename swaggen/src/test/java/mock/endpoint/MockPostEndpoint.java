package mock.endpoint;
import annotation.SwaggerGen;
import annotation.SwaggerResponse;
import annotation.body.*;
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
        responses={
        		@SwaggerResponse(body=@SwaggerBody("/test/schema.json")), 
        		@SwaggerResponse(code=400, body=@SwaggerBody("/test/schema.json")), 
        		@SwaggerResponse(code=404, body=@SwaggerBody("/test/schema.json"))
        }
    )
    protected static final void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
        // Implementation not important.
    }
}
