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
	public static final String FIELD_URL_LOCATION_PHOTO = "User_UrlLocationPhoto";
	public static final String FIELD_NAME = "User_Name";
	public static final String FIELD_LAST_NAME = "User_LastName";
	public static final String FIELD_ID_002_DOCUMENT_TYPE = "User_Id002DocumentType";
	public static final String FIELD_DOCUMENT = "User_Document";
	public static final String FIELD_ADDRESS = "User_Address";
	public static final String FIELD_PHONE = "User_Phone";
	public static final String FIELD_CHANGE_PASSWORD = "User_ChangePassword";
	public static final String FIELD_ID_001_STATUS = "User_Id001Status";
	
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
	
	@Column(name = FIELD_URL_LOCATION_PHOTO)
	private String urlLocationPhoto;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	@Column(name = FIELD_LAST_NAME)
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name = FIELD_ID_002_DOCUMENT_TYPE)
	private Parameter documentType;
	
	@Column(name = FIELD_DOCUMENT)
	private String document;
	
	@Column(name = FIELD_ADDRESS)
	private String address;
	
	@Column(name = FIELD_PHONE)
	private String phone;
	
	@Column(name = FIELD_CHANGE_PASSWORD, insertable = false)
	private Boolean changePassword;
	
	@ManyToOne
	@JoinColumn(name = FIELD_ID_001_STATUS, insertable = false)
	private Parameter status;
}
