package servlet;
import annotation.SwaggerGen;
/**
 * A Fake endpoint for testing.
 * 
 * @author Alexandre Seguin (7663995)
 */
public class MinimalGetEndpointTest {
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/minified-endpoint",
        basePath="base",
        title="Minimum",
        method="GET"
    )
    public void doGet(Object request, Object resp) {
        // Implementation not important.
    }
}
