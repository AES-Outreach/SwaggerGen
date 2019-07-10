package mock.endpoint;
import annotation.SwaggerGen;
import mock.service.MockSlingServerletRequest;
import mock.service.MockSlingServerletResponse;

/**
 * A Fake endpoint for testing.
 */
public class MockGetPostEndpoint {

	@SwaggerGen(
		uri="/combined",
		basePath="/base/all",
		method="POST",
		title="Post Serverlet Title",
		description="Post Serverlet Description",
		headers={"Content-Type=application/json"},
		queryParams={
				"i param = An Integer Param", 
				"b param2 = A Boolean Param", 
				"s64 param3 = A String Param",
				"param4 = Another String Param"
				},
		body="body.scheme",
		responses={"200=OK", "400=Bad Request", "404"},
		responseBody= "response.scheme",
		scheme="HTTP"
	)
	protected static final void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
  }
  
  @SwaggerGen(
		uri="/combined",
		basePath="/base/all",
		method="GET",
		title="Get Serverlet Title",
		description="Get Serverlet Description"
	)
	protected static final void doGet(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
  }
}
