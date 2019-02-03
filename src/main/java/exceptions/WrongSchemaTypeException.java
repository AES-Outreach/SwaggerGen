package exceptions;

/**
 * Exception to be thrown when trying to access data that is invalid for the schema type.
 * 
 * @author William Gardiner (7267012)
 */
public class WrongSchemaTypeException extends Exception {
	private static final long serialVersionUID = -8450915227293827728L;

	public WrongSchemaTypeException(String msg) {
		super(msg);
	}
	
}
