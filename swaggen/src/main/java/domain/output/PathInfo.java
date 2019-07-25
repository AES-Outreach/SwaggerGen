package domain.output;

import java.util.ArrayList;

import domain.output.ServerURL;

/**
 * The path and base path of the server
 */
public class PathInfo {
    private String uri;
    private String basePath;
    private String filename;
    private ArrayList<ServerURL> server;

    public PathInfo() {

    }

    public PathInfo(String basePath, String uri, ArrayList<ServerURL> server) {
        this.uri = uri;
        this.basePath = basePath;
        this.server = server;
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

    public ArrayList<ServerURL> getServer() {
        return this.server;
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

    public void setServer(ArrayList<ServerURL> server) {
        this.server = server;
    }
}