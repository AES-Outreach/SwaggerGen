package factory;

import domain.output.path.ParamSchema;
import enums.ParamFormat;
import enums.ParamType;

/**
 * Creates a ParamSchema from the schema identifier.
 * 
 * @author William Gardiner (7267012)
 */
public class ParamSchemaFactory {

	public static ParamSchema createParamSchema() {
		return createParamSchema(null);
	}

	// TODO: Update the method below to use a static map instead of a switch statement.
	
	/**
	 * Creates a ParamSchema based on the schema identifier
	 * 
	 * @param schemaIdentifier the schema identifier
	 * @return the schema
	 */
	public static ParamSchema createParamSchema(String schemaIdentifier) {
		ParamSchema schema = new ParamSchema();
		if(schemaIdentifier == null || "".contentEquals(schemaIdentifier)) {
			schemaIdentifier = "s";
		}
		switch(schemaIdentifier) {
		case "s":
			schema.setType(ParamType.STRING);
			break;
		case "s32":
			schema.setType(ParamType.STRING);
			schema.setFormat(ParamFormat.STR_32);
			break;
		case "s64":
			schema.setType(ParamType.STRING);
			schema.setFormat(ParamFormat.STR_64);
			break;
		case "i":
			schema.setType(ParamType.INTEGER);
			break;
		case "i32":
			schema.setType(ParamType.INTEGER);
			schema.setFormat(ParamFormat.INT_32);
			break;
		case "i64":
			schema.setType(ParamType.INTEGER);
			schema.setFormat(ParamFormat.INT_64);
			break;
		case "n":
			schema.setType(ParamType.NUMBER);
			break;
		case "n32":
			schema.setType(ParamType.NUMBER);
			schema.setFormat(ParamFormat.FLOAT);
			break;
		case "n64":
			schema.setType(ParamType.NUMBER);
			schema.setFormat(ParamFormat.DOUBLE);
			break;
		case "b":
			schema.setType(ParamType.BOOLEAN);
			break;
		case "dat":
			schema.setType(ParamType.STRING);
			schema.setFormat(ParamFormat.DATE);
			break;
		case "dtm":
			schema.setType(ParamType.STRING);
			schema.setFormat(ParamFormat.DATE_TIME);
			break;
		case "byt":
			schema.setType(ParamType.STRING);
			schema.setFormat(ParamFormat.BYTE);
			break;
		case "bin":
			schema.setType(ParamType.STRING);
			schema.setFormat(ParamFormat.BINARY);
			break;
		default:
			schema.setType(ParamType.STRING);
		}
		return schema;
	}

}
