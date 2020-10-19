package com.taller.asb.dto.user;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taller.asb.annotations.UniqueDocument;
import com.taller.asb.annotations.UniqueUsername;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UniqueUsername
@UniqueDocument
public class UpdateUserFormDto {
	
	@NotNull(message = "Id user not null")
	private Integer idUser;
	
	@JsonProperty(required = true)
	@NotNull(message = "Username not null")
	@NotEmpty(message = "Username not empty")
	@NotBlank(message = "Username not blank")
	@Size(max = 20, message = "Username max 20 caracteres")
	private String username;
	
	@JsonProperty(required = true)
	@NotNull(message = "Passsword not null")
	@NotEmpty(message = "Passsword not empty")
	@NotBlank(message = "Passsword not blank")
	@Size(min = 6, message = "Passsword min 6 caracteres")
	private String password;
	
	@NotNull(message = "Id role type not null")
	private Integer idRole;
	
	@JsonProperty(required = true)
	@NotNull(message = "Name not null")
	@NotEmpty(message = "Name not empty")
	@NotBlank(message = "Name not blank")
	private String name;
	
	@JsonProperty(required = true)
	@NotNull(message = "Last name not null")
	@NotEmpty(message = "Last name not empty")
	@NotBlank(message = "Last name not blank")
	private String lastName;
	
	@JsonProperty(required = true)
	@NotNull(message = "Id document type not null")
	private Integer idDocumentType;
	
	@JsonProperty(required = true)
	@NotNull(message = "Document not null")
	@NotEmpty(message = "Document not empty")
	@NotBlank(message = "Document not blank")
	@Size(max = 20, message = "Document max 20 caracteres")
	
	private String document;
	
	@Size(max = 100, message = "Address max 100 caracteres")
	private String address;
	
	@Size(max = 30, message = "Phone max 30 caracteres")
	private String phone;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("user")
	private void getJsonProperties(Map<String, Object> user) {
		
		Map<String, Object> data = (Map<String, Object>) user.get("data");
		
		idUser           = (Integer) data.get("idUser");
		username         = (String)  data.get("username");
		password         = (String)  data.get("password");
		idRole         	 = (Integer) data.get("idRole");
		name             = (String)  data.get("name");
		lastName         = (String)  data.get("lastName");
		idDocumentType   = (Integer) data.get("idDocumentType");
		document         = (String)  data.get("document");
		address          = (String)  data.get("address");
		phone            = (String)  data.get("phone");
	}
}
