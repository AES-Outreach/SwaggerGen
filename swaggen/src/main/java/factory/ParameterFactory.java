package factory;

import java.util.ArrayList;
import java.util.List;

import annotation.SwaggerGen;
import annotation.SwaggerGenClass;
import domain.input.parameter.ParsedParameter;
import domain.output.path.Parameter;
import enums.ParamLocation;
import utils.stringparsing.ParameterParser;
import utils.stringparsing.exceptions.ParseException;

/**
 * Creates a list of Parameters from an annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class ParameterFactory {
	
	private static final String NO_DESCRIPTION = "No Description";
	
	/**
	 * Starting symbol for path parameters
	 */
	private static final String OPEN = "{";
	
	/**
	 * Closing symbol for path parameters
	 */
	private static final String CLOSE = "}";
	
	/**
	 * Generates a list of parameters from an annotation.
	 * 
	 * @param annotation the annotation
	 * @return the list of parameters
	 */
	public static List<Parameter> createParameters(SwaggerGen annotation) {
		List<Parameter> parameters = new ArrayList<>();
		parameters.addAll(getParamList(ParamLocation.HEADER, annotation.headers()));
		parameters.addAll(getParamList(ParamLocation.QUERY, annotation.queryParams()));
		parameters.addAll(getParamList(ParamLocation.PATH, getPathVariables(annotation.uri())));
		return parameters;
	}

	/**
	 * Generates a list of parameters from method level and class level
	 * annotations, that is not null
	 * 
	 * @param annotation method level annotation
	 * @param klassAnnotation class level annotation
	 * @return the list of parameters
	 */
	public static List<Parameter> createParameters(SwaggerGen annotation, SwaggerGenClass klassAnnotation) {
		List<Parameter> parameters = new ArrayList<>();
		if (klassAnnotation.headers().length > 0) {
			parameters.addAll(getParamList(ParamLocation.HEADER, klassAnnotation.headers()));
		}
		parameters.addAll(getParamList(ParamLocation.HEADER, annotation.headers()));
		parameters.addAll(getParamList(ParamLocation.QUERY, annotation.queryParams()));
		parameters.addAll(getParamList(ParamLocation.PATH, getPathVariables(annotation.uri())));
		return parameters;
	}
	
	/**
	 * Generates a list of parameters from a ParamLocation and an array of param descriptions.
	 * 
	 * @param loc location
	 * @param params the list of parameters
	 * @return the list of parameters
	 */
	private static List<Parameter> getParamList(ParamLocation loc, String[] params) {
		List<Parameter> paramList = new ArrayList<>();
		ParameterParser parser = new ParameterParser(
				ParameterParser.DEFAULT_TYPE_DELIMITER, 
				ParameterParser.DEFAULT_DESCRIPTION_DELIMITER);
		for(String param : params) {
			try {
				ParsedParameter parsedParameter = parser.parse(param);
				Parameter parameter = new Parameter();
				parameter.setName(parsedParameter.getName());
				parameter.setDescription(
						parsedParameter.getDescription() != null ? 
						parsedParameter.getDescription() : NO_DESCRIPTION);
				parameter.setLoc(loc);
				parameter.setRequired(false);
				parameter.setSchema(
						ParamSchemaFactory.createParamSchema(parsedParameter.getType()));
				paramList.add(parameter);
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return paramList;
	}
	
	/**
	 * Gets an array of parameters from a uri
	 * 
	 * @param uri the uri
	 * @return the list of parameters
	 */
	private static String[] getPathVariables(String uri) {
		List<String> paramList = new ArrayList<>();
		int index = 0;
		while(index < uri.length() && uri.indexOf(OPEN, index) != -1) {
			int open = uri.indexOf(OPEN, index);
			int close = uri.indexOf(CLOSE, open);
			if(close != -1) {
				String parameter = uri.substring(open + 1, close);
				paramList.add(parameter);
			}
		}
		return paramList.toArray(new String[paramList.size()]);
	}

}
