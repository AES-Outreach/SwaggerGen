package domain.output;

/**
 * The path and base path of the server
 */
public class PathURL {
  private String uri;
  private String basePath;
  private String filename;
  public PathURL() {

  }
  public PathURL(String basePath, String uri) {
    this.uri = uri;
    this.basePath = basePath;
  }

  public String getURI() {
    return this.uri;
  }

  public String getBasePath() {
    return this.basePath;
  }

  public String getFilename() {
    return this.filename;
  }

  public String getFullPath() {
    return this.basePath + this.uri;
  }

  public void setURI(String uri) {
    this.uri = uri;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}