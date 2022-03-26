package telran.annotation.validation.constraints;
//Ilyal-HW50
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Min {
String message() default "Min constraint violation";
double value();
}
