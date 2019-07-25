package servlet;
import annotation.SwaggerGen;
import annotation.SwaggerGenClass;
/**
 * A Fake endpoint for testing.
 * 
 * @author Alexandre Seguin (7663995)
 */
@SwaggerGenClass(
    basePath="localhost/test",
    title="Meeting",
    servers={
        "{scheme}://{environment}.localhost:{port} = This uses scheme, environment and port variables"
    },
    scheme="https",
    environment="api",
    port="8080",
    headers={"Content-Type=application/json"}
)
public class DemoEndpoint {


    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/post",
        method="POST",
        description="Post Servlet Description",
        headers={"langHeader=en"},
        body="schemas/all/meetingPost.json",
        responses={"200=OK", "400", "404", "401", "403"}
    )
    public void doPost(Object request, Object resp) {
        RequestHandler.handle(request);
        ResponseHandler.handle(resp);
    }
    
    @SwaggerGen(
        uri= "/put",
        method="PUT",
        description="This is a sample PUT endpoint description to be printed in a Swagger format HTML documentation file.",
        headers={"authorization=token used for authorization", "langHeader=expected language of the request"},
        body="schemas/all/meetingPut.json",
        responses={"200=OK", "400", "404", "401", "403"}
    )
    public void doPut(Object request, Object resp) {
        RequestHandler.handle(request);
        ResponseHandler.handle(resp);
    }
    
    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/get",
        method="GET",
        description="This is a sample GET endpoint description to be printed in a Swagger format HTML documentation file.",
        headers={"authorization=authorization token", "langHeader=expected language of the request"},
        queryParams={
            "i meeting_id = id of the meeting being requested",
            "b detailed = boolean determining whether to get extra meeting information ",
            "meeting_minutes = notes taken during the meeting",
        },
        responses={"200=OK", "400", "404", "401", "403"}
    )
    public void doGet(Object request, Object resp) {
        RequestHandler.handle(request);
        ResponseHandler.handle(resp);
    }
}
