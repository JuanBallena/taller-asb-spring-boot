package com.taller.asb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="users")
@Entity
public class User {
	
	public static final String FIELD_ID = "User_Id";
	public static final String FIELD_USERNAME = "User_Username";
	public static final String FIELD_PASSWORD = "User_Password";
	public static final String FIELD_ID_ROLE = "User_IdRole";
	public static final String FIELD_CHANGE_PASSWORD = "User_ChangePassword";
	public static final String FIELD_ID_001_STATUS = "User_Id001Status";
	
	public static final int MIN_USERNAME = 3;
	public static final int MAX_USERNAME = 20;
	public static final int MIN_PASSWORD = 6;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private Long idUser;
	
	@Column(name = FIELD_USERNAME)
	private String username;
	
	@Column(name = FIELD_PASSWORD )
	private String password;
	
	@ManyToOne
	@JoinColumn(name = FIELD_ID_ROLE)
	private Role role;
	
	@Column(name = FIELD_CHANGE_PASSWORD, insertable = false)
	private Boolean changePassword;
	
	@ManyToOne
	@JoinColumn(name = FIELD_ID_001_STATUS, insertable = false)
	private Parameter status;
}
