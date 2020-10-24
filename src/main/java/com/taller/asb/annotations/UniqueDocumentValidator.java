package com.taller.asb.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy =  { UniqueDocumentValidatorImpl.class })
@Documented
public @interface UniqueDocumentValidator {

	String message() default "Existing document";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
