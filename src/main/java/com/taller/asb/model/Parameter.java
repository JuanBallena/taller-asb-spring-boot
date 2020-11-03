package com.taller.asb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="parameters")
@Entity
public class Parameter {
	
	public static final String FIELD_ID = "Parameter_Id";
	public static final String FIELD_ID_PARAMETER_TYPE = "Parameter_IdParameterType";
	public static final String FIELD_DESCRIPTION = "Parameter_Description";
	
	@Id
	@Column(name = FIELD_ID)
	private Long idParameter;
	
	@ManyToOne
	@JoinColumn(name = FIELD_ID_PARAMETER_TYPE)
	private ParameterType parameterType;
	
	@Column(name = FIELD_DESCRIPTION)
	private String description;

}
