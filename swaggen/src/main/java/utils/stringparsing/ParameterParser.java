package utils.stringparsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.input.parameter.ParsedParameter;
import utils.stringparsing.exceptions.ParameterParseException;
import utils.stringparsing.exceptions.ParseException;

/**
 * Parses a parameter from a String.
 * 
 * @author William Gardiner (7267012)
 */
public class ParameterParser implements StringParser<ParsedParameter> {

	/**
	 * The default type delimiter
	 */
	public static final String DEFAULT_TYPE_DELIMITER = " ";
	
	/**
	 * The default description delimiter
	 */
	public static final String DEFAULT_DESCRIPTION_DELIMITER = "=";
	
	/**
	 * The allowed characters in a type identifier in a matching block
	 */
	private static final String TYPE_ALLOWED = "[a-zA-Z0-9]";
	
	/**
	 * The group name for the section matching type
	 */
	private static final String TYPE_GROUP = "type";
	
	/**
	 * The type group regex
	 */
	private static final String TYPE = "\\s*(?<"+TYPE_GROUP+">"+TYPE_ALLOWED+"+"+")\\s*";

	/**
	 * The allowed characters in a URL in a matching block
	 */
	private static final String NAME_ALLOWED = "[a-zA-Z0-9%\\-._~]";
	
	/**
	 * The group name for the section matching name
	 */
	private static final String NAME_GROUP = "name";
	
	/**
	 * The name group regex
	 */
	private static final String NAME = "\\s*(?<"+NAME_GROUP+">"+NAME_ALLOWED+"+"+")\\s*";

	/**
	 * The allowed characters in a description
	 */
	private static final String DESC_ALLOWED = ".";
	
	/**
	 * The group tag for the section matching description
	 */
	private static final String DESCRIPTION_GROUP = "decription";
	
	/**
	 * The description group regex
	 */
	private static final String DESCRIPTION = "\\s*(?<"+DESCRIPTION_GROUP+">"+DESC_ALLOWED+"+"+")\\s*";
	
	/**
	 * The pattern matching a parameter with type, name and description
	 */
	private Pattern fullParameter;
	
	/**
	 * The pattern matching a parameter with no type
	 */
	private Pattern noType;
	
	/**
	 * The pattern matching a parameter with no description
	 */
	private Pattern noDescription;
	
	/**
	 * The pattern matching a parameter with only a name
	 */
	private Pattern nameOnly;
	
	/**
	 * Constructs a parser with the given delimiters. Will parse
	 * a String with the format "type{typeDelimiter}name{descriptionDelimiter}description"
	 * 
	 * @param typeDelimiter The type delimiter
	 * @param descriptionDelimiter The description delimiter
	 */
	public ParameterParser(String typeDelimiter, String descriptionDelimiter) {
		fullParameter = Pattern.compile(TYPE+typeDelimiter+NAME+descriptionDelimiter+DESCRIPTION);
		noType = Pattern.compile(NAME+descriptionDelimiter+DESCRIPTION);
		noDescription = Pattern.compile(TYPE+typeDelimiter+NAME);
		nameOnly = Pattern.compile(NAME);
	}
	
	@Override
	public ParsedParameter parse(String parameterString) throws ParseException {
		Matcher fullParameterMatcher = fullParameter.matcher(parameterString);
		Matcher noTypeMatcher = noType.matcher(parameterString);
		Matcher noDescriptionMatcher = noDescription.matcher(parameterString);
		Matcher nameOnlyMatcher = nameOnly.matcher(parameterString);
		
		ParsedParameter parsedParameter = new ParsedParameter();
		
		// Find the first matching pattern then set the appropriate parsed parameter values.
		if(fullParameterMatcher.matches()) {
			parsedParameter.setType(fullParameterMatcher.group(TYPE_GROUP));
			parsedParameter.setName(fullParameterMatcher.group(NAME_GROUP));
			parsedParameter.setDescription(fullParameterMatcher.group(DESCRIPTION_GROUP));
		} else if(noTypeMatcher.matches()) {
			parsedParameter.setName(noTypeMatcher.group(NAME_GROUP));
			parsedParameter.setDescription(noTypeMatcher.group(DESCRIPTION_GROUP));
		} else if(noDescriptionMatcher.matches()) {
			parsedParameter.setType(noDescriptionMatcher.group(TYPE_GROUP));
			parsedParameter.setName(noDescriptionMatcher.group(NAME_GROUP));
		} else if(nameOnlyMatcher.matches()) {
			parsedParameter.setName(nameOnlyMatcher.group(NAME_GROUP));
		} else throw new ParameterParseException(parameterString);
		
		return parsedParameter;
	}

}
