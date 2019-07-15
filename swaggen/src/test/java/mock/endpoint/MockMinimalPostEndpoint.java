package mock.endpoint;
import annotation.SwaggerGen;
import mock.service.MockSlingServerletRequest;
import mock.service.MockSlingServerletResponse;

/**
 * A Fake endpoint for testing.
 * 
 * @author Bill Zhang
 */
public class MockMinimalPostEndpoint {
  @SwaggerGen(
		uri="/minified-endpoint",
		basePath="base",
		method="POST"
	)
	protected static final void doPost(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
		// Implementation not important.
	}
}
