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
@Table(name="themes")
@Entity
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Theme_Id")
	private Long idTheme;
	
	@Column(name="Theme_Title")
	private String title;
	
	@Column(name="Theme_Author")
	private String author;
	
	@ManyToOne
	@JoinColumn(name="Theme_Id007AllowedGroup")
	private Parameter allowedGroup;
	
	@Column(name="Theme_UrlLocationYoutube")
	private String urlLocationYoutube;
}
