package mock.endpoint;
import annotation.SwaggerGenClass;
import annotation.SwaggerGen;
/**
 * A Fake endpoint class for testing class level annotations.
 * 
 */
@SwaggerGenClass(
)
public class MockBlankClassAnnotationEndpoint {
    /**
     * Fake endpoint for testing purposes
     * empty headers
     * 
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/class/empty",
        method="PUT",
        body="schemas/all/orderPut.json",
        responses={"200=OK", "400", "404", "401", "403"}
    )
    public void doPut(Object request, Object resp) {
        // Implementation not important.
    }
    
    /**
     * Fake endpoint for testing purposes
     * Some headers, overwriting title and description
     * 
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/class/empty",
        method="GET",
        title="Overwriting class title",
        description="Overwriting class description",
        headers={"langHeader=en"},
        body="schemas/all/orderPut.json",
        responses={"200=OK", "400", "404", "401", "403"}
    )
    public void doGet(Object request, Object resp) {
        // Implementation not important.
    }
}