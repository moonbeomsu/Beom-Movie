package com.example.beom_movie.validation;

import com.example.beom_movie.signup.SignupMemberForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BeanValidationTests {


    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        SignupMemberForm signupMemberForm = new SignupMemberForm();
        signupMemberForm.setEmail(null);
        signupMemberForm.setPassword(null);
        signupMemberForm.setNickname(null);

        Set<ConstraintViolation<SignupMemberForm>> violations = validator.validate(signupMemberForm);

        for (ConstraintViolation<SignupMemberForm> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation = " + violation.getMessage());
        }

    }





}
