package bdb.Backend.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationUtil {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    /**
     * Validates an object and returns a list of validation errors
     * @param object Object to validate
     * @return List of validation errors with property path and message
     */
    public static List<ValidationError> validateObject(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        List<ValidationError> errors = new ArrayList<>();

        for (ConstraintViolation<Object> violation : violations) {
            errors.add(new ValidationError(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            ));
        }

        return errors;
    }

    /**
     * Prints validation errors to console
     * @param object Object to validate
     */
    public static void printValidationErrors(Object object) {
        List<ValidationError> errors = validateObject(object);

        for (ValidationError error : errors) {
            System.out.println("Propiedad: " + error.getProperty());
            System.out.println("Mensaje: " + error.getMessage());
            System.out.println("---");
        }
    }

    /**
     * Inner class to store validation error details
     */
    public static class ValidationError {
        private final String property;
        private final String message;

        public ValidationError(String property, String message) {
            this.property = property;
            this.message = message;
        }

        public String getProperty() {
            return property;
        }

        public String getMessage() {
            return message;
        }
    }
}
