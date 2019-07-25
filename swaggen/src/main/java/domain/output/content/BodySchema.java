package domain.output.content;

import domain.input.jsonschema.JsonSchema;

/**
 * Object mapping a filename to a schema POJO.
 * 
 * @author William Gardiner (7267012)
 *
 */
public class BodySchema {

    /**
     * The file name
     */
    private String filename;
    
    /**
     * The schema.
     */
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
