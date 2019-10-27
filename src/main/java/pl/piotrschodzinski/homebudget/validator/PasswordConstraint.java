package pl.piotrschodzinski.homebudget.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Password must be at least 8 characters, contain one upper case, " +
            "lower case, digit and special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
