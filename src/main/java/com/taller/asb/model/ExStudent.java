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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ExStudent_Id")
	private Long idExStudent;
	
	@ManyToOne
	@JoinColumn(name="ExStudent_IdUser")
	private User user;
	
	@Column(name="ExStudent_Promotion")
	private String promotion;
	
	@Column(name="ExStudent_HasDocumentCopy")
	private Boolean hasDocumentCopy;
}
