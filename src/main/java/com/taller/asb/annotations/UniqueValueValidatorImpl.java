package com.taller.asb.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.taller.asb.interfaces.Uniqueable;

public class UniqueValueValidatorImpl implements ConstraintValidator<UniqueValueValidator, Object> {
	
	@Autowired
	private ApplicationContext applicationContext;
	private Uniqueable uniqueable;
	private String field;
	
	@Override
    public void initialize(UniqueValueValidator uniqueValueValidator) {

		Class<? extends Uniqueable> clazz = uniqueValueValidator.manager();
		this.uniqueable = this.applicationContext.getBean(clazz);
		this.field = uniqueValueValidator.field();
	}
	
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	
    	return uniqueable.uniqueInDatabase(field, value);
    }
}
