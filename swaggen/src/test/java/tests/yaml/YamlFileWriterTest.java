package tests.yaml;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import mock.yaml.MockYamlBase;
import mock.yaml.factory.MockYamlFactory;
import utils.io.FileMapper;

/**
 * Tests the YAML file writer
 * 
 * @author William Gardiner (7267012)
 */
public class YamlFileWriterTest {
	
	/**
	 * Test that the YAML Writer generates the expected YAML
	 * 
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	//@Test
	public void testYamlWriter() throws JsonGenerationException, JsonMappingException, IOException {
		String filename = "test/generated/testYamlWriter.yaml";
		MockYamlBase mockYamlBase = MockYamlFactory.getMockYaml();
		FileMapper.classToYaml(filename, mockYamlBase);
		File file = new File(filename);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		int index = 0;
		while(line != null) {
			assertEquals(line.trim(), MockYamlFactory.getYamlLines()[index++].trim());
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		file.delete();
	}
	
	/**
	 * Tests that null values are included in the YAML
	 */
	@Test
	public void testYamlWithNullValues() {
		// TODO: Implement
	}
	
	/**
	 * Tests that null values are not included in the YAML
	 */
	@Test
	public void testYamlWithoutNullValues() {
		// TODO: Implement
	}
	
}
