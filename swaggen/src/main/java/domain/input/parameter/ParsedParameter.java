package domain.input.parameter;

/**
 * A parameter as parsed from a String.
 * 
 * @author William Gardiner (7267012)
 *
 */
public class ParsedParameter {

    /**
     * The type identifier for the parameter
     */
    private String type;
    
    /**
     * The name of the parameter
     */
    private String name;
    
    /**
     * The description of the parameter
     */
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
