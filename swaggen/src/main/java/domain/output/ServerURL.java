package domain.output;

import domain.output.ServerVariable;
import java.util.HashMap;

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

    /**
     * variables of the server
     */
    private HashMap<String, ServerVariable> variables;

    public ServerURL() {

    }

    public ServerURL(String url, String description) {
        this(url, description, new HashMap<String, ServerVariable>());
    }

    public ServerURL(String url, String description, HashMap<String, ServerVariable> variables) {
        this.url = url;
        this.description = description;
        this.variables = variables;
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

    public HashMap<String,ServerVariable> getVariables() {
        return this.variables;
    }

    public void setVariables(HashMap<String, ServerVariable> variables) {
        this.variables = variables;
    }
}