package mock.endpoint;
import annotation.SwaggerGen;
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
		uri="/endpoint",
		basePath="base/all",
		method="PUT",
		description="Put Servlet Description",
		headers={"Content-Type=application/json", "langHeader=en"},
		body="schemas/all/orderPut.json",
		responses={"200=OK", "400", "404", "401", "403"}
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
		uri="/endpoint",
		basePath="base/all",
		method="POST",
		description="Post Servlet Description",
		headers={"Content-Type=application/json", "langHeader=en"},
		body="schemas/all/orderPost.json",
		responses={"200=OK", "400", "404", "401", "403"}
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
		uri="/endpoint",
		basePath="base/all",
		method="GET",
		description="Get Servlet Description",
		headers={"Content-Type=application/json", "langHeader=en"},
		queryParams={
				"i order_id = id of the order being requested"
				},
		responses={"200=OK", "400", "404", "401", "403"}
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
		uri="/endpoint",
		basePath="base/all",
		method="DELETE",
		description="Delete Servlet Description",
		headers={"Content-Type=application/json", "langHeader=en"},
		queryParams={
				"i order_id = id of the order being requested"
				},
		responses={"200=OK", "400", "404", "401", "403"}
	)
	public void doDelete(Object request, Object resp) {
		// Implementation not important.
	}
}
