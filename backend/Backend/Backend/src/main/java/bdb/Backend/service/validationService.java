package bdb.Backend.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class validationService {

    private final SpringValidatorAdapter validator;

    public validationService(SpringValidatorAdapter validator) {
        this.validator = validator;

    }

    public List<String> validate(Object object) {
        DataBinder binder = new DataBinder(object);
        binder.setValidator(validator);
        binder.validate();

        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(this::formatErrorMessage)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private String formatErrorMessage(FieldError error) {
        return String.format("%s: %s", error.getField(), error.getDefaultMessage());
    }


}
