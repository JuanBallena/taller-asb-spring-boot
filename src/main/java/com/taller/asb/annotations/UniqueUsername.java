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
@Constraint(validatedBy =  { UniqueUsernameImpl.class })
@Documented
public @interface UniqueUsername {

	String message() default "Existing username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
