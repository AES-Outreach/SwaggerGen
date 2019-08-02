package mojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Collection;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.scanners.ResourcesScanner;

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

    @Parameter(readonly = true)
    private String propertiesPath;

    /**
     * Function automatically called during the install phase of a project using
     * SwaggerGen as a dependency. Will search the Maven project's classpath for
     * classes annotated with the SwaggerGen and trigger the rest of the annotation
     * processing.
     * 
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute() throws MojoExecutionException {
        boolean showLogs = false;
        try {
            try (InputStream input = new FileInputStream(propertiesPath)) {
                Properties config = new Properties();
                config.load(input);
                showLogs = Boolean.parseBoolean(config.getProperty("showLogs"));

                if (showLogs) {
                    getLog().info("Loaded properties file.");
                    getLog().info("-- -- -- OBTAINING ANNOTATIONS -- -- --");
                }
                ClassLoader originalClassLoader = getClass().getClassLoader();
                Class<?>[] klasses = getClassArray(showLogs);
                if (showLogs) {
                    getLog().info("-- -- -- PROCESSING ANNOTATIONS -- -- --");
                    getLog().info("Swagger version number: " + config.getProperty("version"));
                }
                generator.SwaggerGenerator.generateSwaggerFile(klasses, config);
                Thread.currentThread().setContextClassLoader(originalClassLoader);
            } catch (IOException ex) {
                if (showLogs) {
                    getLog().warn(
                            "No valid properties file provided. Please add a <propertiesPath> tag to the plugin's build configuration tag.");
                }
                throw new MojoExecutionException("File not found.");
            }
        } catch (Exception e) {
            // Can add other catches here for more complete error handling
            if (showLogs) {
                getLog().error(e.toString());
            }
            throw new MojoExecutionException("Could not process annotations from project.", e);
        }
        if (showLogs) {
            getLog().info("-- DONE --");
        }
    }

    /**
     * Uses reflections to obtain the classes that have the SwaggerGen annotation in
     * the importing Maven project.
     * 
     * @return array of classes that contain methods annotated with SwaggerGen
     * @throws DependencyResolutionRequiredException if mojo is misconfigured
     * @throws MalformedURLException
     */
    private Class<?>[] getClassArray(boolean showLogs)
            throws MalformedURLException, DependencyResolutionRequiredException {

        Reflections loadedKlasses = new Reflections(
                new ConfigurationBuilder().addUrls(ClasspathHelper.forClassLoader(getClassLoader(showLogs)))
                        .setScanners(new SubTypesScanner(false), new MethodAnnotationsScanner()));
        if (showLogs) {
            getLog().info("Loaded Klasses instantiated");
            getLog().info("About to find methods annotated with SwaggerGen using reflections object");
        }
        Set<Method> swaggenMethods = loadedKlasses.getMethodsAnnotatedWith(SwaggerGen.class);
        if (showLogs) {
            getLog().info("Swaggen Methods instantiated");
        }
        Set<Class<?>> klasses = new HashSet<Class<?>>();
        for (Method method : swaggenMethods) {
            klasses.add(method.getDeclaringClass());
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
    private ClassLoader getClassLoader(boolean showLogs) throws DependencyResolutionRequiredException, MalformedURLException {
        Set<URL> urls = new HashSet<>();
        List<String> elements = project.getRuntimeClasspathElements();
        for (String element : elements) {
            urls.add(new File(element).toURI().toURL());
            if (showLogs) {
                getLog().info(element);
            }
        }
        if (showLogs) {
            getLog().info("About to instantiate URLClassLoader object using urls and context class loader");
        }
        ClassLoader contextClassLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]),
                this.getClass().getClassLoader());
        if (showLogs) {
            getLog().info("Context Class Loader instantiated with " + contextClassLoader.toString());
            getLog().info("About to set Thread context class loader using the URLClassLoader object");
        }
        Thread.currentThread().setContextClassLoader(contextClassLoader);
        if (showLogs) {
            getLog().info("Context Class Loader set with " + contextClassLoader.toString());
        }
        return contextClassLoader;
    }
}