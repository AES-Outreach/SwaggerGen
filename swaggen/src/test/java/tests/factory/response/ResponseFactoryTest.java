package tests.factory.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import annotation.SwaggerGen;
import annotation.SwaggerResponse;
import annotation.body.SwaggerBody;
import domain.output.path.Response;
import factory.ResponseFactory;

public class ResponseFactoryTest {

	private static final String DEFAULT_DESCRIPTION_PROPERTY = "default.response.description.";
	private static final String DEFAULT_BODY_PROPERTY = "default.response.body.";
	
	private static final String TITLE = "fooTitle";
	private static final String DESCRIPTION = "fooDescription";
	private static final String CONFIG_DESCRIPTION = "fooConfigDescription";
	private static final int RESPONSE_CODE = 404;
	private static final int UNKNOWN_CODE = 1776;
	private static final String CONFIG_BODY = "foo/config/body/location";

	private SwaggerGen swaggerGen;
	private SwaggerResponse swaggerResponse;
	private SwaggerBody swaggerBody;
	private Properties config;
	
	@Before
	public void beforeEach() {
		swaggerGen = mock(SwaggerGen.class);
		swaggerResponse = mock(SwaggerResponse.class);
		swaggerBody = mock(SwaggerBody.class);
		when(swaggerGen.title()).thenReturn(TITLE);
		when(swaggerGen.responses()).thenReturn(new SwaggerResponse[] {swaggerResponse});
		when(swaggerResponse.code()).thenReturn(RESPONSE_CODE);
		when(swaggerResponse.body()).thenReturn(swaggerBody);
		when(swaggerResponse.description()).thenReturn(DESCRIPTION);
		when(swaggerBody.value()).thenReturn("");
		
		config = new Properties();
		config.put(DEFAULT_DESCRIPTION_PROPERTY + RESPONSE_CODE, CONFIG_DESCRIPTION);
		config.put(DEFAULT_BODY_PROPERTY + RESPONSE_CODE, CONFIG_BODY);
	}
	
	@Test
	public void givenDescription_whenCreateResponse_thenResponseHasDescription() throws Exception {
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertEquals(DESCRIPTION, result.getDescription());
	}

	@Test
	public void givenNoDescription_whenCreateResponse_thenResponseHasCodeConfigDescription() throws Exception {
		// Arrange
		when(swaggerResponse.description()).thenReturn(null);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertEquals(CONFIG_DESCRIPTION, result.getDescription());
	}
	
	@Test
	public void givenNoDescriptionNorConfig_whenCreateResponse_thenResponseHasCodeDefaultDescription() throws Exception {
		// Arrange
		when(swaggerResponse.description()).thenReturn(null);
		config.remove(DEFAULT_DESCRIPTION_PROPERTY + RESPONSE_CODE);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertEquals(HttpStatus.getStatusText(RESPONSE_CODE), result.getDescription());
	}

	@Test
	public void givenUnkonwnCode_whenCreateResponse_thenResponseHasDefaultDescription() throws Exception {
		// Arrange
		when(swaggerResponse.code()).thenReturn(UNKNOWN_CODE);
		when(swaggerResponse.description()).thenReturn(null);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(UNKNOWN_CODE));
		
		// Assert
		assertEquals(ResponseFactory.DEFAULT_DESCRIPTION, result.getDescription());
	}
	
	@Test
	public void givenResponseBody_whenCreateResponse_thenResponseHasBody() throws Exception {
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertNotNull(result.getContent());
	}
	
	@Test
	public void givenDefaultBody_whenCreateResponse_thenResponseHasBody() throws Exception {
		// Arrange
		when(swaggerBody.value()).thenReturn("");
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertNotNull(result.getContent());
	}
	
	@Test
	public void givenNoBody_whenCreateResponse_thenResponseDoesNotHaveBody() throws Exception {
		// Arrange
		when(swaggerBody.value()).thenReturn("");
		config.remove(DEFAULT_BODY_PROPERTY + RESPONSE_CODE);
		
		// Act
		Response result = ResponseFactory.createResponses(swaggerGen, config).get(Integer.toString(RESPONSE_CODE));
		
		// Assert
		assertNull(result.getContent());
	}
	
	
	
	
}
