package com.taller.asb.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.taller.asb.interfaces.Uniqueable;


@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValueOnCreateImpl.class)
@Documented
public @interface UniqueValueOnCreate {
	
	String message() default "existing value";
	
	String field();
	
	Class<? extends Uniqueable> manager();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
