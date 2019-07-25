package tests.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import domain.input.parameter.ParsedParameter;
import utils.stringparsing.ParameterParser;
import utils.stringparsing.StringParser;
import utils.stringparsing.exceptions.ParseException;

/**
 * Tests the Parameter parser.
 * 
 * @author William Gardiner (7267012)
 */
public class ParameterParserTests {

    /**
     * The default type delimiter
     */
    public static final String TYPE_DELIMITER = " ";
    
    /**
     * The default description delimiter
     */
    public static final String DESCRIPTION_DELIMITER = "=";
    
    /**
     * The default type
     */
    public static final String TYPE = "str";
    
    /**
     * The default parameter name
     */
    public static final String NAME = "n";
    
    /**
     * The default description
     */
    public static final String DESCRIPTION = "desc";
    
    /**
     * An invalid parameter String
     */
    public static final String INVALID = "ABC$CD";
    
    /**
     * Tests a full parameter String.
     * 
     * @throws ParseException
     */
    @Test
    public void testFullParameterParser() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, DESCRIPTION_DELIMITER);
        ParsedParameter param = parser.parse(TYPE + TYPE_DELIMITER + NAME + DESCRIPTION_DELIMITER + DESCRIPTION);
        assertEquals(param.getType(), TYPE);
        assertEquals(param.getName(), NAME);
        assertEquals(param.getDescription(), DESCRIPTION);
    }
    
    /**
     * Tests a full parameter String with extra whitespace.
     * 
     * @throws ParseException
     */
    @Test
    public void testFullWithExtraWhitespace() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, DESCRIPTION_DELIMITER);
        ParsedParameter param = parser.parse(" " + TYPE + " " + TYPE_DELIMITER + NAME + " " + DESCRIPTION_DELIMITER + " " + DESCRIPTION);
        assertEquals(param.getType(), TYPE);
        assertEquals(param.getName(), NAME);
        assertEquals(param.getDescription(), DESCRIPTION);
    }
    
    /**
     * Tests a parameter missing a type.
     * 
     * @throws ParseException
     */
    @Test
    public void testNoTypeParameterParser() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, DESCRIPTION_DELIMITER);
        ParsedParameter param = parser.parse(" " + NAME + DESCRIPTION_DELIMITER + DESCRIPTION);
        assertNull(param.getType());
        assertEquals(param.getName(), NAME);
        assertEquals(param.getDescription(), DESCRIPTION);
    }
    
    /**
     * Tests a parameter missing a description.
     * 
     * @throws ParseException
     */
    @Test
    public void testNoDescriptionParameterParser() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, DESCRIPTION_DELIMITER);
        ParsedParameter param = parser.parse(TYPE + TYPE_DELIMITER + NAME);
        assertEquals(param.getType(), TYPE);
        assertEquals(param.getName(), NAME);
        assertNull(param.getDescription());
    }
    
    /**
     * Tests a parameter with a name only.
     * 
     * @throws ParseException
     */
    @Test
    public void testNameOnlyParameterParser() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, DESCRIPTION_DELIMITER);
        ParsedParameter param = parser.parse(NAME + " ");
        assertNull(param.getType());
        assertEquals(param.getName(), NAME);
        assertNull(param.getDescription());
    }
    
    /**
     * Tests a parameter with invalid characters.
     * 
     * @throws ParseException
     */
    @Test(expected=ParseException.class)
    public void testInvalidCharacter() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, DESCRIPTION_DELIMITER);
        ParsedParameter param = parser.parse(INVALID);
    }
    
    /**
     * Tests a parameter using the same delimiter for type and description.
     * 
     * @throws ParseException
     */
    @Test
    public void testSameDelimiter() throws ParseException {
        StringParser<ParsedParameter> parser = new ParameterParser(TYPE_DELIMITER, TYPE_DELIMITER);
        ParsedParameter param = parser.parse(TYPE + TYPE_DELIMITER + NAME + TYPE_DELIMITER + DESCRIPTION);
        assertEquals(param.getType(), TYPE);
        assertEquals(param.getName(), NAME);
        assertEquals(param.getDescription(), DESCRIPTION);
    }
}
