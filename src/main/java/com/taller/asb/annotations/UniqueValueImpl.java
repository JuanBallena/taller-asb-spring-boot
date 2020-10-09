package com.taller.asb.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.taller.asb.manager.UniqueValueManager;

public class UniqueValueImpl implements ConstraintValidator<UniqueValue, Object> {
	
	@Autowired
	private ApplicationContext applicationContext;
	private String column;
	private UniqueValueManager uniqueValueManager;
	
	@Override
    public void initialize(UniqueValue uniqueValue) {

		this.column = uniqueValue.column();
		Class<? extends UniqueValueManager> clazz = uniqueValue.manager();
		this.uniqueValueManager = this.applicationContext.getBean(clazz);
	}
	
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	
    	return uniqueValueManager.valueExistsInDatabase(column, value);
    }
}
