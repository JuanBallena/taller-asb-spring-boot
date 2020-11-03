package com.taller.asb.repository;

import java.util.List;

import com.taller.asb.model.Parameter;
import com.taller.asb.model.ParameterType;

public interface ParameterRepository extends CustomRepository<Parameter, Long> {

	public List<Parameter> findByParameterType(ParameterType parameterType);
}
