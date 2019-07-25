package domain.output.path;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents a REST Endpoint. It is referenced from the Path by its method.
 * 
 * @author William Gardiner (7267012)
 */
@JsonInclude(Include.NON_NULL)
public class Endpoint {
    
    /**
     * The summary
     */
    private String summary;
    
    /**
     * The description 
     */
    private String description;
    
    /**
     * The parameters
     */
    private List<Parameter> parameters;
    
    /**
     * The request body
     */
    private RequestBody requestBody;
    
    /**
     * The possible responses
     */
    private Map<String, Response> responses;
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Parameter> getParameters() {
        return parameters;
    }
    
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
    public RequestBody getRequestBody() {
        return requestBody;
    }
    
    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }
    
    public Map<String, Response> getResponses() {
        return responses;
    }
    
    public void setResponses(Map<String, Response> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "Endpoint [summary=" + summary + ", description=" + description + ", parameters=" + parameters
                + ", requestBody=" + requestBody + ", responses=" + responses + "]";
    }
}
