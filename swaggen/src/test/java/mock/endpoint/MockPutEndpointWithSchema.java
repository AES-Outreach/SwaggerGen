package mock.endpoint;
import annotation.SwaggerGen;
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
		basePath="base",
		method="PUT",
		description="Put Servlet Description",
		headers={"Content-Type=application/json", "langHeader=en"},
		body="schemas/fakePutSchema.json",
		responses={"200=OK", "400", "404"},
		scheme="HTTP"
	)
	public void doPut(Object request, Object resp) {
		// Implementation not important.
	}
}
