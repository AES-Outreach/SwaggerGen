package tests.factory.response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import annotation.SwaggerGen;
import annotation.SwaggerResponse;
import annotation.body.SwaggerBody;
import domain.output.path.Response;
import factory.ResponseFactory;

public class ResponseFactoryTest {

	private static final String DESCRIPTION = "fooDescription";
	private static final int RESPONSE_CODE = 404;
	private static final int UNKNOWN_CODE = 1776;

	SwaggerGen swaggerGen;
	SwaggerResponse swaggerResponse;
	SwaggerBody swaggerBody;
	
	@Before
	public void beforeEach() {
		swaggerGen = mock(SwaggerGen.class);
		swaggerResponse = mock(SwaggerResponse.class);
		swaggerBody = mock(SwaggerBody.class);
		when(swaggerGen.responses()).thenReturn(new SwaggerResponse[] {swaggerResponse});
		when(swaggerResponse.code()).thenReturn(RESPONSE_CODE);
		when(swaggerResponse.body()).thenReturn(swaggerBody);
		when(swaggerBody.value()).thenReturn("");
	}
	
	@Test
	public void givenDescription_whenCreateResponse_thenResponseHasDescription() throws Exception {
		// Arrange
		when(swaggerResponse.description()).thenReturn(DESCRIPTION);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertEquals(DESCRIPTION, result.getDescription());
	}

	@Test
	public void givenNoDescription_whenCreateResponse_thenResponseHasCodeDefaultDescription() throws Exception {
		// Arrange
		when(swaggerResponse.description()).thenReturn(null);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertEquals(HttpStatus.getStatusText(RESPONSE_CODE), result.getDescription());
	}

	@Test
	public void givenUnkonwnCode_whenCreateResponse_thenResponseHasDefaultDescription() throws Exception {
		// Arrange
		when(swaggerResponse.code()).thenReturn(UNKNOWN_CODE);
		when(swaggerResponse.description()).thenReturn(null);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen).get(Integer.toString(UNKNOWN_CODE));
		
		// Assert
		assertEquals(ResponseFactory.DEFAULT_DESCRIPTION, result.getDescription());
	}
}
