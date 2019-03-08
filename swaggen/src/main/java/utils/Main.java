package utils;

import java.util.List;

import domain.path.Path;

/**
 * Connects the plugin with the SwaggerGen logic
 * 
 * @author Danny Petitclerc (8074166)
 */

public class Main {

	public static void exec(Class<?>[] klasses) {
		
		/**
		 * Given an array of classes that contain methods annotated with SwaggerGen,
		 * generate a list of associated paths and POJO
		 * 
		 * @param klasses an array of classes that contain methods annotated with SwaggerGen
		 **/
		
		List<Path> paths = generator.PathGenerator.generatePathsFromClassList(klasses);
		
	}
}
