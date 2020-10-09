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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="User_Id")
	private Long idUser;
	
	@Column(name="User_Username")
	private String username;
	
	@Column(name="User_Password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="User_IdRole")
	private Role role;
	
	@Column(name="User_UrlLocationPhoto")
	private String urlLocationPhoto;
	
	@Column(name="User_Name")
	private String name;
	
	@Column(name="User_LastName")
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="User_Id002DocumentType")
	private Parameter documentType;
	
	@Column(name="User_Document")
	private String document;
	
	@Column(name="User_Address")
	private String address;
	
	@Column(name="User_Phone")
	private String phone;
	
	@Column(name="User_ChangePassword", insertable = false, updatable = false)
	private Boolean changePassword;
	
	@ManyToOne
	@JoinColumn(name="User_Id001Status", insertable = false, updatable = false)
	private Parameter status;
}
