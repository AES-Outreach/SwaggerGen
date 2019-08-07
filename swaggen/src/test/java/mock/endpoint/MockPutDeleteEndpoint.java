package mock.endpoint;
import annotation.SwaggerGen;
import mock.service.MockSlingServerletRequest;
import mock.service.MockSlingServerletResponse;

/**
 * A Fake endpoint for testing.
 */
public class MockPutDeleteEndpoint {

	@SwaggerGen(
		uri="/combined",
		basePath="/base/all",
		method="PUT",
		title="Put Serverlet Title",
		description="Put Serverlet Description"
	)
	protected static final void doPut(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
  }
  
  @SwaggerGen(
		uri="/combined",
		basePath="/base/all",
		method="DELETE",
		title="Delete Serverlet Title",
		description="Delete Serverlet Description"
	)
	protected static final void doDelete(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
  }
}
