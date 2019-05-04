package domain.output;

/**
 * The info part of the Swagger Header
 * 
 * @author William Gardiner (7267012)
 */
public class SwaggerInfo {

	/**
	 * The API Suite title
	 */
	private String title;
	
	/**
	 * The API Suite description
	 */
	private String description;
	
	/**
	 * The API Suite Version
	 */
	private String version;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}
