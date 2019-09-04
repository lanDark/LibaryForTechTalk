/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Validator;

import com.Anotaiton.Email;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author vital
 */
public class EmailValidator implements ConstraintValidator<Email, String>{

    @Override
    public void initialize(Email constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)
            return false;
        else
        {
            if(!value.matches("[^!@#$%&*()_+]([A-Za-z0-9]+)([.][a-z]+)*(@[a-z]+)([.][a-z]+){1,2}"))
                return false;
        }
        return true;
    }
    
}
