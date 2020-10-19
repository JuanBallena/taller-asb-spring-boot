package com.taller.asb.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.taller.asb.manager.Uniqueable;

public class UniqueValueImpl implements ConstraintValidator<UniqueValue, Object> {
	
	@Autowired
	private ApplicationContext applicationContext;
	private Uniqueable uniqueable;
	private String field;
	
	@Override
    public void initialize(UniqueValue uniqueValue) {

		Class<? extends Uniqueable> clazz = uniqueValue.manager();
		this.uniqueable = this.applicationContext.getBean(clazz);
		this.field = uniqueValue.field();
	}
	
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	
    	return uniqueable.valueExists(field, value);
    }
}
