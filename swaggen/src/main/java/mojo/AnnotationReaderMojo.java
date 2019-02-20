package mojo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import annotation.SwaggerGen;

/**
 * TODO - Add function call to annotation reader
 *
 */
@Mojo(name = "process-annotations")
public class AnnotationReaderMojo extends AbstractMojo {

	@Component
	private MavenProject project;
	
	/**
	 * The greeting to display.
	 */
	@Parameter(property = "process-annotations.baseUrl", defaultValue = "localhost")
	private String baseUrl;

	@SuppressWarnings("unchecked")
	public void execute() throws MojoExecutionException {

		try {
			
			
			// The outputDirectory is the ${project.build.directory} the Maven plugin is executing in
			File classesDirectory = new File(project.getBuild().getDirectory() + "\\classes\\servlet");  

			getLog().info(project.getBuild().getDirectory()+ "\\classes\\servlet");
			URL classesUrl = classesDirectory.toURI().toURL();
			URL[] classesUrls = new URL[]{classesUrl}; 

			// Make sure to use the URLClassLoader, using the simple ClassLoader WILL NOT WORK for reading the annotations       
			URLClassLoader classLoader = URLClassLoader.newInstance(classesUrls, getClass().getClassLoader());

			
			
			Reflections ref = new Reflections(new ConfigurationBuilder()
					.addUrls(classLoader.getURLs())
					.setScanners(new SubTypesScanner(false), new MethodAnnotationsScanner()));

			getLog().info(ref.getAllTypes().toString());
			getLog().info("trying to get annotations");
			Set<Method> m = ref.getMethodsAnnotatedWith(SwaggerGen.class);
		
		} catch (Exception e) {
			getLog().error(e.toString());
			throw new MojoExecutionException("Dependency resolution failed", e);
		}

		getLog().info("-- DONE --");
	}
}