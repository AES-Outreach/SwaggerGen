package mock.yaml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MockYamlNullNotAllowed {
	
	private String notNull;
	private String nul;
	
	public MockYamlNullNotAllowed() {
		notNull = "Not Null";
		nul = null;
	}
	
}
