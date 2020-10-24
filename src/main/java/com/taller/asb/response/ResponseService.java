package com.taller.asb.response;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor 
@ToString(callSuper=true, includeFieldNames=true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Component
public class ResponseService {

	private String type;
	private Integer responseCode;
    private String responseMessage;
    private String token;
    private Object data;
	private int pages;
	private Object responseError;
	
}
