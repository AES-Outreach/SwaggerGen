package mojo;
 
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
 
/**
 * TODO - Add function call to annotation reader
 *
 */
@Mojo( name = "process-annotations")
public class AnnotationReaderMojo extends AbstractMojo
{
	 /**
     * The greeting to display.
     */
    @Parameter( property = "process-annotations.baseUrl", defaultValue = "localhost" )
    private String baseUrl;
    
    public void execute() throws MojoExecutionException
    {
        getLog().info( "-- FUNCTION CALL --" );
    }
} 