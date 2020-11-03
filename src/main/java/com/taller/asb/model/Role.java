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
@Table(name="roles")
@Entity
public class Role {
	
	public static final String FIELD_ID = "Role_Id";
	public static final String FIELD_NAME = "Role_Name";
	public static final String FIELD_DISPLAY_NAME = "Role_DisplayName";
	
	@Id
	@Column(name = FIELD_ID)
	private Long idRole;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	@Column(name = FIELD_DISPLAY_NAME)
	private String displayName;
	
}
