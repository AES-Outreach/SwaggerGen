package mock.endpoint;
import annotation.SwaggerGen;
import mock.service.MockSlingServerletRequest;
import mock.service.MockSlingServerletResponse;

/**
 * A Fake endpoint for testing.
 * 
 * @author Bill Zhang
 */
public class MockMinimalPutEndpoint {
  @SwaggerGen(
		uri="/minified-endpoint",
		basePath="/base",
		method="PUT"
	)
	protected static final void doPut(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
	}
}
