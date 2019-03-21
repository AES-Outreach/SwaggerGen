package servlet;
import annotation.SwaggerGen;
/**
 * A Fake endpoint for testing.
 * 
 * @author William Gardiner (7267012)
 */
public class MockPostEndpoint {
	/**
	 * Fake endpoint for testing purposes
	 * @param request fake request object
	 * @param resp fake response object
	 */
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
	public void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
	}
}
