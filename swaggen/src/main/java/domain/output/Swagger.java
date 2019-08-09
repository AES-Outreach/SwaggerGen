package domain.output;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import domain.output.path.Endpoint;
import domain.output.PathInfo;
import enums.RequestMethod;

/**
 * Domain object representing an entire Swagger file.
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class Swagger {

    public Swagger() {
        this.paths = new HashMap<>();
        this.servers = new ArrayList<>();
    }

    /**
     * copy constructor
     * 
     * @param swagger Swagger object
     */
    public Swagger(Swagger swagger) {
        this.info = swagger.getInfo();
        this.setVersion(swagger.getOpenapi());
        this.servers = swagger.getServers();
    }

    /**
     * The OpenAPI Version
     */
    private String openapi;

    /**
     * The Swagger Version
     */
    private String swagger;

    /**
     * The API Suite Info
     */
    private SwaggerInfo info;

    /**
     * Server info
     */
    private ArrayList<ServerURL> servers;

    /**
     * Top level map, mapping all endpoints by their request method and URL
     */
    private Map<String, Map<RequestMethod, Endpoint>> paths;

    @JsonIgnore
    private Map<PathInfo, Map<RequestMethod, Endpoint>> swaggerPaths;

    public Map<String, Map<RequestMethod, Endpoint>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Map<RequestMethod, Endpoint>> paths) {
        this.paths = paths;
    }

    public String getOpenapi() {
        return openapi;
    }

    public String getSwagger() {
        return swagger;
    }

    @JsonProperty
    public Map<PathInfo, Map<RequestMethod, Endpoint>> getSwaggerPaths() {
        return swaggerPaths;
    }

    @JsonIgnore
    public void setSwaggerPaths(Map<PathInfo, Map<RequestMethod, Endpoint>> swaggerPaths) {
        this.swaggerPaths = swaggerPaths;
    }

    /**
     * Field name is openapi for version 3.x, swagger for 2.0
     * 
     * @param version the version number.
     */
    public void setVersion(String version) {
        if ("2.0".equals(version)) {
            openapi = null;
            swagger = version;
        } else {
            swagger = null;
            openapi = version;
        }
    }

    public SwaggerInfo getInfo() {
        return info;
    }

    public void setInfo(SwaggerInfo info) {
        this.info = info;
    }

    public ArrayList<ServerURL> getServers() {
        return this.servers;
    }

    public void setServers(ArrayList<ServerURL> server) {
        this.servers = server;
    }
}
