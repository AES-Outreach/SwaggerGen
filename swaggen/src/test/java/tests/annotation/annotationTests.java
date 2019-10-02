package tests.annotation;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.output.path.Endpoint;
import domain.output.PathInfo;
import domain.output.SwaggerEndpoint;
import enums.ParamType;
import enums.RequestMethod;
import mock.endpoint.MockPostEndpoint;


/**
 * Tests the annotation
 * 
 * @author William Gardiner (7267012)
 */
public class annotationTests {

    /**
     * Test the annotation and factories by generating a path.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @Test
    public void testAnnotation() throws JsonParseException, JsonMappingException, IOException {
        
        Class<?>[] klasses = {MockPostEndpoint.class};
        Map<PathInfo, SwaggerEndpoint> path = generator.PathGenerator.generatePathsFromClassList(klasses);
        Endpoint endpoint = path.get(path.keySet().toArray()[0]).getEndpoint(RequestMethod.POST);
        
        assertEquals(endpoint.getDescription(), "Serverlet Description");
        assertEquals(endpoint.getSummary(), "Serverlet Title");
        assertEquals(endpoint.getResponses().size(), 3);
        assertEquals(endpoint.getParameters().size(), 5);
        assertEquals(endpoint.getParameters().get(0).getSchema().getType(), ParamType.STRING);

    }
    
}
