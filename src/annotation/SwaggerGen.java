package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * SwaggerGen annotation description.
 * 
 * @author William Gardiner (7267012)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerGen {

	String url();
	String method();
	String description() default "";
	String[] headers() default {};
	String[] queryParams() default {};
	String body() default "";
	int[] responses();
	String responseBody() default "";
	String scheme() default "HTTP";

}