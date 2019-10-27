package pl.piotrschodzinski.homebudget.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx) {
        return password != null &&
                password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=,.!*()-}{:;\"'<>?\\[\\]\\/])(?=\\S+$).{8,}$");
    }
}
