package com.taller.asb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="students")
@Entity
public class Student {
	
	public static final String FIELD_ID = "Student_Id";
	public static final String FIELD_ID_USER = "Student_IdUser";
	public static final String FIELD_NAME = "Student_Name";
	public static final String FIELD_LAST_NAME = "Student_LastName";
	public static final String FIELD_URL_LOCATION_PHOTO = "Student_UrlLocationPhoto";
	public static final String FIELD_ID_002_DOCUMENT_TYPE = "Student_Id002DocumentType";
	public static final String FIELD_DOCUMENT = "Student_Document";
	public static final String FIELD_ADDRESS = "Student_Address";
	public static final String FIELD_PHONE = "Student_Phone";
	public static final String FIELD_HAS_DOCUMENT_COPY = "Student_HasDocumentCopy";
	public static final String FIELD_SUSPENDED = "Student_Suspended";
	
	public static final int MAX_DOCUMENT = 20;
	public static final int MAX_ADDRESS = 100;
	public static final int MAX_PHONE = 30;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private Long idStudent;
	
	@OneToOne
	@JoinColumn(name = FIELD_ID_USER)
	private User user;
	
	@Column(name = FIELD_NAME)
	private String name;
	
	@Column(name = FIELD_LAST_NAME)
	private String lastName;
	
	@Column(name = FIELD_URL_LOCATION_PHOTO)
	private String urlLocationPhoto;
	
	@ManyToOne
	@JoinColumn(name = FIELD_ID_002_DOCUMENT_TYPE)
	private Parameter documentType;
	
	@Column(name = FIELD_DOCUMENT)
	private String document;
	
	@Column(name = FIELD_ADDRESS)
	private String address;
	
	@Column(name = FIELD_PHONE)
	private String phone;
	
	@Column(name = FIELD_HAS_DOCUMENT_COPY, insertable = false)
	private Boolean hasDocumentCopy;
	
	@Column(name = FIELD_SUSPENDED, insertable = false)
	private Boolean suspended;

}
