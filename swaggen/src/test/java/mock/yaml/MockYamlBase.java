package mock.yaml;

import java.util.List;
import java.util.Map;

/**
 * A class for testing the YAML writer.
 * 
 * @author William Gardiner (7267012)
 */
public class MockYamlBase {

	private List<MockYamlListItem> items;
	private Map<String, MockYamlMapItem> map;
	private Map<String, Map<String, MockYamlMapItem>> nestedMap;
	private String stringItem;
	private MockYamlObject object;
	
	public List<MockYamlListItem> getItems() {
		return items;
	}
	public void setItems(List<MockYamlListItem> items) {
		this.items = items;
	}
	public Map<String, MockYamlMapItem> getMap() {
		return map;
	}
	public void setMap(Map<String, MockYamlMapItem> map) {
		this.map = map;
	}
	public Map<String, Map<String, MockYamlMapItem>> getNestedMap() {
		return nestedMap;
	}
	public void setNestedMap(Map<String, Map<String, MockYamlMapItem>> nestedMap) {
		this.nestedMap = nestedMap;
	}
	public String getStringItem() {
		return stringItem;
	}
	public void setStringItem(String stringItem) {
		this.stringItem = stringItem;
	}
	public MockYamlObject getObject() {
		return object;
	}
	public void setObject(MockYamlObject object) {
		this.object = object;
	}
	
}
