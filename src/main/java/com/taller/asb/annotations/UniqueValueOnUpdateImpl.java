package com.taller.asb.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.taller.asb.interfaces.Uniqueable;
import com.taller.asb.model.EntityUtil;

public class UniqueValueOnUpdateImpl implements ConstraintValidator<UniqueValueOnUpdate, EntityUtil> {

	@Autowired
	private ApplicationContext applicationContext;
	private Uniqueable uniqueable;
	private String field;
	
	@Override
    public void initialize(UniqueValueOnUpdate uniqueValueOnUpdate) {

		Class<? extends Uniqueable> clazz = uniqueValueOnUpdate.manager();
		this.uniqueable = this.applicationContext.getBean(clazz);
		this.field = uniqueValueOnUpdate.field();
	}
	
    @Override
    public boolean isValid(EntityUtil entityUtil, ConstraintValidatorContext context) {
    	
    	return uniqueable.uniqueValueOnUpdate(field, entityUtil.getValue(), entityUtil.getId());
    }
}
