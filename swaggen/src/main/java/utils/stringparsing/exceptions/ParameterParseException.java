package utils.stringparsing.exceptions;

/**
 * Exception thrown when parameter cannot be parsed.
 * 
 * @author William Gardiner (7267012)
 */
public class ParameterParseException extends ParseException {
	
	/**
	 * Constructor
	 * 
	 * @param parsedString the failed parameter String
	 */
	public ParameterParseException(String parsedString) {
		super("Failed to parse parameter from String: " + parsedString);
	}
	
}
