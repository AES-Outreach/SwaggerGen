package domain.output;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Server variables for the server URL
 * Currently used for specifying the scheme
 */
public class ServerVariable {
  private String[] enums;
  private String defaultEnum;
  private String description;

  public ServerVariable(String[] enums, String defaultEnum) {
    this.enums = enums;
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
}