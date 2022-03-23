package telran.annotation.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * constraint for validation against a regular expression given in a value
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Patern {
String message() default "Patern constraint violation";
String value();
}
