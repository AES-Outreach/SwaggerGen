package utils.stringparsing;

import utils.stringparsing.exceptions.ParseException;

/**
 * Creates a POJO from a String.
 * 
 * @author William Gardiner (7267012)
 */
public interface StringParser<T> {

	/**
	 * Given a String, returns the object it describes.
	 * 
	 * @param s the string
	 * @return the object
	 * @throws ParseException 
	 */
	public T parse(String s) throws ParseException;

}
