package edu.devernul.project.validation;

import edu.devernul.project.model.Task;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class TaskValidator implements Validator {


    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Task task = (Task) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Task name is required");
        if (task.getStatus() == null)
            errors.rejectValue("project", "", "Task status is required, choose s status");
    }


}
