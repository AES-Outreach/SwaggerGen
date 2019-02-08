package resource.jsonschema;


public class BodySchema {

	private String filename;
	private JsonSchema schema;
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public JsonSchema getSchema() {
		return schema;
	}
	
	public void setSchema(JsonSchema schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return "BodySchema [filename=" + filename + ", schema=" + schema + "]";
	}
}
