package mojo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import annotation.SwaggerGen;

/**
 * Maven MOJO in charge of running during the install lifecycle phase of
 * projects importing the SwaggerGen annotation project.
 * 
 * @author Alexandre Seguin
 */
@Mojo(name = "process-annotations", requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class AnnotationReaderMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject project;

	/**
	 * Function automatically called during the install phase of a project using
	 * SwaggerGen as a dependency. Will search the Maven project's classpath for
	 * classes annotated with the SwaggerGen and trigger the rest of the annotation
	 * processing.
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	public void execute() throws MojoExecutionException {

		try {

			getLog().info("-- OBTAINING ANNOTATIONS --");
			Class<?>[] klasses = getClassArray();

			getLog().info("-- PROCESSING ANNOTATIONS --");
			
			generator.SwaggerGenerator.log = getLog();
			generator.SwaggerGenerator.generateSwaggerFile(klasses, "generated/swagger/TestYaml.yaml");

		} catch (Exception e) {
			// Can add other catches here for more complete error handling
			getLog().error(e.toString());
			throw new MojoExecutionException("Could not process annotations from project.", e);
		}

		getLog().info("-- DONE --");
	}

	/**
	 * Uses reflections to obtain the classes that have the SwaggerGen annotation in
	 * the importing Maven project.
	 * 
	 * @return array of classes that contain methods annotated with SwaggerGen
	 * @throws DependencyResolutionRequiredException if mojo is misconfigured
	 * @throws MalformedURLException
	 */
	private Class<?>[] getClassArray() throws MalformedURLException, DependencyResolutionRequiredException {

		Reflections loadedKlasses = new Reflections(
				new ConfigurationBuilder().addUrls(ClasspathHelper.forClassLoader(getClassLoader()))
						.setScanners(new SubTypesScanner(false), new MethodAnnotationsScanner()));

		Set<Method> swaggenMethods = loadedKlasses.getMethodsAnnotatedWith(SwaggerGen.class);
		Set<Class<?>> klasses = new HashSet<Class<?>>();
		for (Method methud : swaggenMethods) {
				klasses.add(methud.getDeclaringClass());
				getLog().info("Annotated class: " + methud.getDeclaringClass().toString());
		}

		return klasses.toArray(new Class<?>[klasses.size()]);
	}

	/**
	 * Gets the classloader for the maven project and loads it into the current
	 * thread.
	 * 
	 * @return the classloader
	 * @throws DependencyResolutionRequiredException if mojo is misconfigured
	 * @throws MalformedURLException                 if URLs in the classpath are
	 *                                               malformed
	 */
	private ClassLoader getClassLoader() throws DependencyResolutionRequiredException, MalformedURLException {
		Set<URL> urls = new HashSet<>();
		List<String> elements = project.getRuntimeClasspathElements();
		for (String element : elements) {
			urls.add(new File(element).toURI().toURL());
		}

		ClassLoader contextClassLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]),
				Thread.currentThread().getContextClassLoader());

		Thread.currentThread().setContextClassLoader(contextClassLoader);
		return contextClassLoader;
	}
}