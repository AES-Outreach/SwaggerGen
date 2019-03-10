package mock.yaml.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mock.yaml.MockYamlBase;
import mock.yaml.MockYamlListItem;
import mock.yaml.MockYamlMapItem;
import mock.yaml.MockYamlObject;

public class MockYamlFactory {

	private static final int NUMBER_OF_LIST_ITEMS = 3;
	private static final int NUMBER_OF_MAP_ITEMS = 3;
	private static final int NUMBER_OF_NESTED_MAPS = 2;
	private static final int NUMBER_OF_NESTED_MAP_ITEMS = 2;

	public static MockYamlBase getMockYaml() {
		MockYamlBase yaml = new MockYamlBase();
		yaml.setItems(getList());
		yaml.setMap(getMap());
		yaml.setNestedMap(getNestedMap());
		yaml.setObject(getObject());
		yaml.setStringItem("Some String");
		return yaml;
	}

	private static List<MockYamlListItem> getList() {
		List<MockYamlListItem> list = new ArrayList<>();
		for(int i = 0; i < NUMBER_OF_LIST_ITEMS; i++) {
			list.add(getListItem(1, "String " + i));
		}
		return list;
	}

	private static MockYamlListItem getListItem(int number, String string) {
		MockYamlListItem listItem = new MockYamlListItem();
		listItem.setItemProperty1(string);
		listItem.setItemProperty2(number);
		return listItem;
	}
	
	private static Map<String, MockYamlMapItem> getMap() {
		Map<String, MockYamlMapItem> map = new HashMap<>();
		for(int i = 0; i < NUMBER_OF_MAP_ITEMS; i++) {
			map.put("Key"+i, getMapItem(i));
		}
		return map;
	}

	private static MockYamlMapItem getMapItem(int number) {
		MockYamlMapItem mapItem = new MockYamlMapItem();
		mapItem.setMapItemProperty1("String " + number);
		mapItem.setMapItmeProperty2(number);
		return mapItem;
	}

	private static Map<String, Map<String, MockYamlMapItem>> getNestedMap() {
		Map<String, Map<String, MockYamlMapItem>> map = new HashMap<>();
		for(int i = 0; i < NUMBER_OF_NESTED_MAPS; i++) {
			map.put("Key"+i, getMapNestedMap(i));
		}
		return map;
	}

	private static Map<String, MockYamlMapItem> getMapNestedMap(int number) {
		Map<String, MockYamlMapItem> map = new HashMap<>();
		for(int i = 0; i < NUMBER_OF_NESTED_MAP_ITEMS; i++) {
			map.put("Key"+number+"-"+i, getMapItem(i));
		}
		return map;
	}

	private static MockYamlObject getObject() {
		MockYamlObject object = new MockYamlObject();
		object.setObjectProperty1("String Value");
		object.setObjectProperty2(0);
		return object;
	}
	
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
