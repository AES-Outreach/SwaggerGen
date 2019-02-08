package mojo;

import java.awt.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import org.reflections.util.ClasspathHelper;
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

	public void execute() throws MojoExecutionException {

		ArrayList<String> classpathElements = null;

		try {

			classpathElements = this.project.getCompileClasspathElements();
			List<URL> projectClasspathList = new ArrayList<URL>();
			for (String element : classpathElements) {
				try {
					projectClasspathList.add(new File(element).toURI().toURL());
				} catch (MalformedURLException e) {
					throw new MojoExecutionException(element + " is an invalid classpath element", e);
				}
			}

			Reflections ref = new Reflections(new ConfigurationBuilder()
					.addUrls(ClasspathHelper.forClassLoader(ClassLoader.getPlatformClassLoader()))
					.setScanners(new SubTypesScanner(false), new MethodAnnotationsScanner()));

			Set<Method> annotated = ref.getMethodsAnnotatedWith(SwaggerGen.class);
			getLog().info(ref.getAllTypes().toString());

			getLog().info(annotated.toString());
			for (Method controller : annotated) {
				SwaggerGen request = controller.getAnnotation(SwaggerGen.class);
				String mapping = request.url();
				getLog().info("-- " + mapping + " --");
			}

		} catch (ClassNotFoundException e) {
			throw new MojoExecutionException(e.getMessage());
		} catch (Exception e) {
			new MojoExecutionException("Dependency resolution failed", e);
		}

		getLog().info("-- DONE --");
	}
}