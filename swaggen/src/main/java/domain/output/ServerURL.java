package domain.output;

/**
 * The server part of Swagger
 */
public class ServerURL {
  /**
   * The server url
   */
  private String url;

  /**
   * Description of the url
   */
  private String description;

  public ServerURL() {

  }

  public ServerURL(String url, String description) {
    this.url = url;
    this.description = description;
  }

  public String getURL() {
    return this.url;
  }

  public void setURL(String url) {
    this.url = url;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description; 
  }
}