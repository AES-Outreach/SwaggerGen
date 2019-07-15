package mock.endpoint;
import annotation.SwaggerGen;
/**
 * Fake endpoints for testing
 */
public class MultiplePathsEndpoint {
  @SwaggerGen(
    uri="/all-methods-endpoint",
    basePath="base",
    method="GET",
    description="Get Servlet Description",
		headers={"Content-Type=application/json", "langHeader=en"},
		responses={"200=OK", "400", "404"},
		scheme="HTTP"
  )
  public void doGet(Object request, Object resp) {

  }

  @SwaggerGen(
    uri="/endpoint",
    basePath="base",
    method="PUT",
    description="Put Servlet Description",
    headers={"Content-Type=application/json", "langHeader=en"},
		responses={"200=OK", "400", "404"},
		scheme="HTTP"
  )
  public void doPut(Object request, Object resp) {

  }
}