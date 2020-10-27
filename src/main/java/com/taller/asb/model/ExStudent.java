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
@Table(name="ex_students")
@Entity
public class ExStudent {
	
	public static final String FIELD_ID = "ExStudent_Id";
	public static final String FIELD_ID_USER = "ExStudent_IdUser";
	public static final String FIELD_NAME = "ExStudent_Name";
	public static final String FIELD_LAST_NAME = "ExStudent_LastName";
	public static final String FIELD_URL_LOCATION_PHOTO = "ExStudent_UrlLocationPhoto";
	public static final String FIELD_ID_002_DOCUMENT_TYPE = "ExStudent_Id002DocumentType";
	public static final String FIELD_DOCUMENT = "ExStudent_Document";
	public static final String FIELD_ADDRESS = "ExStudent_Address";
	public static final String FIELD_PHONE = "ExStudent_Phone";
	public static final String FIELD_HAS_DOCUMENT_COPY = "ExStudent_HasDocumentCopy";
	public static final String FIELD_PROMOTION = "ExStudent_Promotion";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = FIELD_ID)
	private Long idExStudent;
	
	@ManyToOne
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
	
	@Column(name = FIELD_HAS_DOCUMENT_COPY)
	private Boolean hasDocumentCopy;
	
	@Column(name = FIELD_PROMOTION)
	private String promotion;
}
