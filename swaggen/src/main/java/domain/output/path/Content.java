package domain.output.path;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Content {
	

	@JsonProperty("application/json")
	private ApplicationJson appjson;
	
	public ApplicationJson getAppjson() {
		return appjson;
	}

	public void setAppjson(ApplicationJson appjson) {
		this.appjson = appjson;
	}

}
