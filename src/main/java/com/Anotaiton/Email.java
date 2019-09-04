/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Anotaiton;

import com.Validator.EmailValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Retention(RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Email {
  String message() default "Sai định dạng số điện thoại. Phải đủ 10 số và chỉ được nhập 0-9";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}