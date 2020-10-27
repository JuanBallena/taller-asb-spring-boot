package com.taller.asb.interfaces;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({FirstValidation.class, SecondValidation.class, Default.class})
public interface SequenceValidation {
	
}
