package mock.yaml.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mock.yaml.MockYamlBase;
import mock.yaml.MockYamlListItem;
import mock.yaml.MockYamlMapItem;
import mock.yaml.MockYamlObject;

/**
 * Poputlates the mock YAML objects
 * 
 * @author William Gardiner (7267012)
 */
public class MockYamlFactory {

	/**
	 * Number of list items.
	 */
	private static final int NUMBER_OF_LIST_ITEMS = 3;
	
	/**
	 * Number of map items.
	 */
	private static final int NUMBER_OF_MAP_ITEMS = 3;
	
	/**
	 * Number of nested maps.
	 */
	private static final int NUMBER_OF_NESTED_MAPS = 2;
	
	/**
	 * Number of nested map items.
	 */
	private static final int NUMBER_OF_NESTED_MAP_ITEMS = 2;

	/**
	 * Generates a MockYaml Object
	 * 
	 * @return
	 */
	public static MockYamlBase getMockYaml() {
		MockYamlBase yaml = new MockYamlBase();
		yaml.setItems(getList());
		yaml.setMap(getMap());
		yaml.setNestedMap(getNestedMap());
		yaml.setObject(getObject());
		yaml.setStringItem("Some String");
		return yaml;
	}

	/**
	 * Gets a list of MockYamlListItem
	 * 
	 * @return
	 */
	private static List<MockYamlListItem> getList() {
		List<MockYamlListItem> list = new ArrayList<>();
		for(int i = 0; i < NUMBER_OF_LIST_ITEMS; i++) {
			list.add(getListItem(1, "String " + i));
		}
		return list;
	}

	/**
	 * Generates a MockYamlListItem
	 * 
	 * @return
	 */
	private static MockYamlListItem getListItem(int number, String string) {
		MockYamlListItem listItem = new MockYamlListItem();
		listItem.setItemProperty1(string);
		listItem.setItemProperty2(number);
		return listItem;
	}
	
	/**
	 * Gets a map of MockYamlMapItem
	 * 
	 * @return
	 */
	private static Map<String, MockYamlMapItem> getMap() {
		Map<String, MockYamlMapItem> map = new HashMap<>();
		for(int i = 0; i < NUMBER_OF_MAP_ITEMS; i++) {
			map.put("Key"+i, getMapItem(i));
		}
		return map;
	}
	
	/**
	 * Generates a MockYamlMapItem
	 * 
	 * @return
	 */
	private static MockYamlMapItem getMapItem(int number) {
		MockYamlMapItem mapItem = new MockYamlMapItem();
		mapItem.setMapItemProperty1("String " + number);
		mapItem.setMapItmeProperty2(number);
		return mapItem;
	}

	/**
	 * Gets a map of of maps of MockYamlMapItem
	 * 
	 * @return
	 */
	private static Map<String, Map<String, MockYamlMapItem>> getNestedMap() {
		Map<String, Map<String, MockYamlMapItem>> map = new HashMap<>();
		for(int i = 0; i < NUMBER_OF_NESTED_MAPS; i++) {
			map.put("Key"+i, getMapNestedMap(i));
		}
		return map;
	}

	/**
	 * Gets a map of MockYamlMapItem
	 * 
	 * @return
	 */
	private static Map<String, MockYamlMapItem> getMapNestedMap(int number) {
		Map<String, MockYamlMapItem> map = new HashMap<>();
		for(int i = 0; i < NUMBER_OF_NESTED_MAP_ITEMS; i++) {
			map.put("Key"+number+"-"+i, getMapItem(i));
		}
		return map;
	}

	/**
	 * Generates a MockYamlObject
	 * 
	 * @return
	 */
	private static MockYamlObject getObject() {
		MockYamlObject object = new MockYamlObject();
		object.setObjectProperty1("String Value");
		object.setObjectProperty2(0);
		return object;
	}
	
	/**
	 * Returns the expected YAML as a list of lines.
	 * 
	 * @return
	 */
	public static String[] getYamlLines() {
		String[] yaml = 
		{
			"---",
			"items:",
			"- itemProperty1: \"String 0\"",
			  "itemProperty2: 1",
			"- itemProperty1: \"String 1\"",
			  "itemProperty2: 1",
			"- itemProperty1: \"String 2\"",
			  "itemProperty2: 1",
			"map:",
			  "Key2:",
			    "mapItemProperty1: \"String 2\"",
			    "mapItmeProperty2: 2",
			  "Key1:",
			    "mapItemProperty1: \"String 1\"",
			    "mapItmeProperty2: 1",
			  "Key0:",
			    "mapItemProperty1: \"String 0\"",
			    "mapItmeProperty2: 0",
			"nestedMap:",
			  "Key1:",
			    "Key1-1:",
			      "mapItemProperty1: \"String 1\"",
			      "mapItmeProperty2: 1",
			    "Key1-0:",
			      "mapItemProperty1: \"String 0\"",
			      "mapItmeProperty2: 0",
			  "Key0:",
			    "Key0-0:",
			      "mapItemProperty1: \"String 0\"",
			      "mapItmeProperty2: 0",
			    "Key0-1:",
			      "mapItemProperty1: \"String 1\"",
			      "mapItmeProperty2: 1",
			"stringItem: \"Some String\"",
			"object:",
			  "objectProperty1: \"String Value\"",
			  "objectProperty2: 0"
		};
		return yaml;
	}
	
	
}
