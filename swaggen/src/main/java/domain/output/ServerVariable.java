package domain.output;
import com.fasterxml.jackson.annotation.JsonProperty;
import annotation.SwaggerGenClass;
import java.util.HashMap;

/**
 * Server variables for the server URL
 * Currently used for specifying the scheme
 */
public class ServerVariable {
  /**
   * The default variable that swagger uses 
   * Required
   */
  private String defaultEnum;
  /**
   * List of enums that the variable can be
   * Not required or shown on the generated html
   */
  private String[] enums;
  /**
   * Description of the variable
   * Not required or shown on the generated html
   */
  private String description;

  public ServerVariable(String defaultEnum) {
    this.defaultEnum = defaultEnum;
  }

  @JsonProperty("enum")
  public String[] getEnums() {
    return this.enums;
  }

  @JsonProperty("default")
  public String getDefaultEnum() {
    return this.defaultEnum;
  }

  public String getDescription() {
    return this.description;
  }

  public void setEnums(String[] enums) {
    this.enums = enums;
  }

  public void setDefaultEnum(String defaultEnum) {
    this.defaultEnum = defaultEnum;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
	 * Creates a map for server variables and checks if the current url uses any
	 * of the supported variables
	 * 
	 * @param serverUrl The server URL
	 * @param klassAnnotation class level annotaitons
	 * @return the appropriate class variables in a map
	 */
	public static HashMap<String, ServerVariable> addServerVariables(String serverUrl, SwaggerGenClass klassAnnotation) {
		HashMap<String, ServerVariable> variables = new HashMap<>();
		if (serverUrl.contains("{scheme}")) {
			ServerVariable scheme = new ServerVariable(klassAnnotation.scheme());
			variables.put("scheme", scheme);
		}
		if (serverUrl.contains("{port}")) {
			ServerVariable port = new ServerVariable(klassAnnotation.port());
			variables.put("port", port);
		}
		if (serverUrl.contains("{environment}")) {
			ServerVariable environment = new ServerVariable(klassAnnotation.environment());
			variables.put("environment", environment);
		}
		return variables;
	}
}