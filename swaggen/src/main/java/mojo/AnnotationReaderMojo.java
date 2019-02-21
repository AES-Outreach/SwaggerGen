package mojo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * TODO - Add function call to annotation reader
 *
 */
@Mojo(name = "process-annotations", requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class AnnotationReaderMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject project;

	/**
	 * The greeting to display.
	 */
	@Parameter(property = "process-annotations.baseUrl", defaultValue = "localhost")
	private String baseUrl;

	@SuppressWarnings("unchecked")
	public void execute() throws MojoExecutionException {

		try {

			Set<URL> urls = new HashSet<>();
			List<String> elements = project.getRuntimeClasspathElements();
			for (String element : elements) {
				urls.add(new File(element).toURI().toURL());
			}

			ClassLoader contextClassLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]),
					Thread.currentThread().getContextClassLoader());

			Thread.currentThread().setContextClassLoader(contextClassLoader);

			Reflections ref = new Reflections(
					new ConfigurationBuilder().addUrls(ClasspathHelper.forClassLoader(contextClassLoader))
							.setScanners(new SubTypesScanner(false), new MethodAnnotationsScanner()));

			getLog().info("trying to get annotations");
			Set<Method> m = ref.getMethodsAnnotatedWith(SwaggerGen.class);
			for (Method mm : m) {
				getLog().info(mm.getAnnotation(SwaggerGen.class).toString());
			}
		} catch (Exception e) {
			getLog().error(e.toString());
			throw new MojoExecutionException("Dependency resolution failed", e);
		}

		getLog().info("-- DONE --");
	}
}