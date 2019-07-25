package utils.stringparsing.exceptions;

/**
 * Exception to be thrown when a String cannot be parsed by a StringParser.
 * 
 * @author William Gardiner (7267012)
 */
public class ParseException extends Exception {

    /**
     * Constructor
     * 
     * @param msg the message
     */
    public ParseException(String msg) {
        super(msg);
    }

}
