package domain.output.content;

import java.util.Map;

import domain.input.jsonschema.JsonSchema;

public class Components {
	private Map<String, JsonSchema> schemas;

	public Map<String, JsonSchema> getSchemas() {
		return schemas;
	}

	public void setSchemas(Map<String, JsonSchema> schemas) {
		this.schemas = schemas;
	}
}
