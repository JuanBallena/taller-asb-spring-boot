package com.taller.asb.interfaces;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({PriorityValidation.class, Default.class})
public interface SequenceValidation {
	
}
