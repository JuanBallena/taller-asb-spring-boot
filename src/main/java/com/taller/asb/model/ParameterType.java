package com.taller.asb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@ToString(callSuper=true, includeFieldNames=true)
@Table(name="parameter_types")
@Entity
public class ParameterType {

	public static final String FIELD_ID = "ParameterType_Id";
	public static final String FIELD_DESCRIPTION = "ParameterType_Description";
	
	@Id
	@Column(name = FIELD_ID)
	private Long idParameterType;
	
	@Column(name = FIELD_DESCRIPTION)
	private String description;
}
