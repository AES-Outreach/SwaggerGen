package factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.SwaggerGen;
import domain.path.Parameter;
import enums.ParamLocation;

/**
 * Creates a list of Parameters from an annotation.
 * 
 * @author William Gardiner (7267012)
 */
public class ParameterFactory {

	private static final String DELIMITER = "=";
	private static final String TYPE_DELIMITER = " ";
	private static final String OPEN = "{";
	private static final String CLOSE = "}";
	
	public static List<Parameter> Parameters(SwaggerGen annotation) {
		List<Parameter> parameters = new ArrayList<>();
		parameters.addAll(getParamList(ParamLocation.HEADER, annotation.headers()));
		parameters.addAll(getParamList(ParamLocation.QUERY, annotation.queryParams()));
		parameters.addAll(getPathVariableList(annotation.url()));
		return parameters;
	}
	
	private static List<Parameter> getParamList(ParamLocation loc, String[] params) {
		List<Parameter> paramList = new ArrayList<>();
		Map<String, String> paramMap = splitParams(params);
		for(String key : paramMap.keySet()) {
			String name = key;
			String type = null;
			String[] keySplit = key.split(TYPE_DELIMITER);
			if(keySplit.length == 2) {
				type = keySplit[0];
				name = keySplit[1];
			}
			Parameter parameter = new Parameter();
			parameter.setName(name);
			parameter.setDescription(paramMap.get(key));
			parameter.setLoc(loc);
			parameter.setRequired(false);
			parameter.setSchema(ParamSchemaFactory.ParamSchema(type));
			paramList.add(parameter);
		}
		return paramList;
	}
	
	private static Map<String, String> splitParams(String[] params) {
		Map<String, String> paramMap = new HashMap<>();
		for(String param : params) {
			String[] split = param.split(DELIMITER);
			if(split.length != 2) {
				throw new IllegalArgumentException();
			}
			paramMap.put(split[0].trim(), split[1].trim());
		}
		return paramMap;
	}
	
	private static List<Parameter> getPathVariableList(String url) {
		List<Parameter> paramList = new ArrayList<>();
		int index = 0;
		while(index < url.length() && url.indexOf(OPEN, index) != -1) {
			int open = url.indexOf(OPEN, index);
			int close = url.indexOf(CLOSE, open);
			if(close != -1) {
				String key = url.substring(open + 1, close);
				Parameter parameter = new Parameter();
				parameter.setName(key);
				parameter.setDescription(key);
				parameter.setLoc(ParamLocation.PATH);
				parameter.setRequired(true);
				parameter.setSchema(ParamSchemaFactory.ParamSchema());
				paramList.add(parameter);
			}
		}
		return paramList;
	}

}
