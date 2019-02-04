package endpoint;
import annotation.SwaggerGen;
import service.MockSlingServerletRequest;
import service.MockSlingServerletResponse;

/**
 * A Fake endpoint for testing.
 * 
 * @author William Gardiner (7267012)
 */
public class MockPostEndpoint {

	@SwaggerGen(
		url="/base/endpoint",
		method="POST",
		description="Serverlet Description",
		headers={"Content-Type=application/json"},
		queryParams={
				"i param = An Integer Param", 
				"b param2 = A Boolean Param", 
				"s64 param3 = A String Param",
				"param4 = Another String Param"
				},
		body="body.scheme",
		responses={200, 400, 404},
		responseBody= "response.scheme",
		scheme="HTTP"
	)
	protected static final void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
	}
}
