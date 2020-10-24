package com.taller.asb.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.taller.asb.interfaces.Existable;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EntityExistsValidatorImpl.class)
@Documented
public @interface EntityExistsValidator {

String message() default "Entity no exists";
	
	Class<? extends Existable> manager();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
