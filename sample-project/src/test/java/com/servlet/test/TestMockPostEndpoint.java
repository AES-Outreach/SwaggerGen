package com.servlet.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import annotation.SwaggerGen;

/**
 * Sanity unit tests for sample-project code.
 * @author ASEGU103
 */
public class TestMockPostEndpoint {

	/**
	 * Test to ensure MOJO reflection methods works within this project.
	 * @throws Exception
	 */
	@Test
	public void testGetmockPostEndpoint() throws Exception {
		File classesDirectory = new File(System.getProperty("user.dir"));
		URL classesUrl = classesDirectory.toURI().toURL();
		URL[] classesUrls = new URL[] { classesUrl };

		// Make sure to use the URLClassLoader, using the simple ClassLoader WILL NOT
		// WORK for reading the annotations
		URLClassLoader classLoader = URLClassLoader.newInstance(classesUrls, getClass().getClassLoader());

		Reflections ref = new Reflections(new ConfigurationBuilder().addUrls(classLoader.getURLs())
				.setScanners(new SubTypesScanner(false), new MethodAnnotationsScanner()));

		Set<Method> m = ref.getMethodsAnnotatedWith(SwaggerGen.class);
		assertNotNull(m);
		assertTrue(m.size() > 0);
	}
}
