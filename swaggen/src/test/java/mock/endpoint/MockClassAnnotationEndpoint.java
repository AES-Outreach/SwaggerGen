package mock.endpoint;
import annotation.SwaggerGenClass;
import annotation.SwaggerGen;
/**
 * A Fake endpoint for testing.
 * 
 */
@SwaggerGenClass(
  basePath = "base",
  title = "Class Test Title",
	description = "Class Test Description",
	servers = {
		"http://classapi.example.com=This is its description"
	}
)
public class MockClassAnnotationEndpoint {
  /**
	 * Fake endpoint for testing purposes
	 * @param request fake request object
	 * @param resp fake response object
	 */
	@SwaggerGen(
		uri="/class",
		method="PUT",
		headers={"Content-Type=application/json", "langHeader=en"},
		body="schemas/all/orderPut.json",
		responses={"200=OK", "400", "404", "401", "403"},
		scheme="HTTP"
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
		uri="/class",
    basePath="/base/endpoint",
		method="POST",
		headers={"Content-Type=application/json", "langHeader=en"},
		body="schemas/all/orderPost.json",
		responses={"200=OK", "400", "404", "401", "418"},
		scheme="HTTP"
	)
	public void doPost(Object request, Object resp) {
		// Implementation not important.
	}
}