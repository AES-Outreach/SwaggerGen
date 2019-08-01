package mock.endpoint;
import annotation.SwaggerGen;
import mock.service.MockSlingServerletRequest;
import mock.service.MockSlingServerletResponse;

/**
 * A Fake endpoint for testing.
 */
public class MockNonSwaggerMethods {
    /**
     * non annotated method
     */
    public void init() {

    }

    @SwaggerGen(
        uri="/minified-endpoint",
        basePath="/base",
        method="DELETE"
    )
    protected static final void doDelete(MockSlingServerletRequest request, MockSlingServerletResponse resp) {
        // Implementation not important.
    }
}