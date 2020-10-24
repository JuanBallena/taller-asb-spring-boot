package com.taller.asb.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.taller.asb.dto.user.UpdateUserFormDto;
import com.taller.asb.manager.UserManager;

public class UniqueUsernameValidatorImpl implements ConstraintValidator<UniqueUsernameValidator, UpdateUserFormDto> {
		
	@Autowired
	private UserManager userManager;
	
	@Override
	public boolean isValid(UpdateUserFormDto updateUserDto, ConstraintValidatorContext context) {
		return userManager.uniqueUsernameInDatabase(updateUserDto);
	}

}