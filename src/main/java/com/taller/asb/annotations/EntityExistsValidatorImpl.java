package com.taller.asb.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.taller.asb.interfaces.Existable;

public class EntityExistsValidatorImpl implements ConstraintValidator<EntityExistsValidator, Integer> {

	@Autowired
	private ApplicationContext applicationContext;
	private Existable existable;
	
	@Override
    public void initialize(EntityExistsValidator entityExists) {

		Class<? extends Existable> clazz = entityExists.manager();
		this.existable = this.applicationContext.getBean(clazz);
	}
	
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
    	
    	return existable.entityExistsInDatabase(Long.valueOf(id));
    }
}
