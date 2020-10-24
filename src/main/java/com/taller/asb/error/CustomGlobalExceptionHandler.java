package com.taller.asb.error;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.taller.asb.response.ResponseService;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	public static final String ACCESS_DENIED = "Lo sentimos, no está autorizado!";
	public static final String INVALID_REQUEST = "Algo salió mal, modifique su solicitud!";
	public static final String FIELD_ERROR_SEPARATOR = ": ";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException exception,
		HttpHeaders headers,
		HttpStatus status, 
		WebRequest request
	) {
		
		List<String> validationErrors = new LinkedList<String>();
		
		List<FieldError> filedErrors = exception.getBindingResult().getFieldErrors();
		List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();
		
		for (FieldError error : filedErrors) {
			validationErrors.add(/*error.getField() + FIELD_ERROR_SEPARATOR +*/ error.getDefaultMessage());
		}
		
		for (ObjectError error : objectErrors) {
			validationErrors.add(/*error.getCode() + FIELD_ERROR_SEPARATOR + */error.getDefaultMessage());
		}
		
		/*validationErrors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getField() + FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
				.collect(Collectors.toList());*/
		
		return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException exception,
		HttpHeaders headers, 
		HttpStatus status,
		WebRequest request
	) {
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(exception.getLocalizedMessage()));
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(
		ConstraintViolationException exception, 
		WebRequest request
	) {
		final List<String> validationErrors = exception.getConstraintViolations()
				.stream()
				.map(violation -> violation.getMessage())
				.collect(Collectors.toList());
		
		return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
	}


	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAllExceptions(
			Exception exception, 
			WebRequest request
	) {
		ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
		final HttpStatus status = responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
		final String localizedMessage = exception.getLocalizedMessage();
		String message = (!localizedMessage.isEmpty() ? localizedMessage:status.getReasonPhrase());
		
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}

	private ResponseEntity<Object> getExceptionResponseEntity(
		final Exception exception,
		final HttpStatus status,
		final WebRequest request,
		final List<String> errors
	) {
		ResponseService responseService = new ResponseService();
		
		final Map<String, Object> errorDetails = new LinkedHashMap<>();
		final String path = request.getDescription(false);
		errorDetails.put("timestamp", new Date());
		errorDetails.put("type", exception.getClass().getSimpleName());
		errorDetails.put("path", path);
		errorDetails.put("message", status);
		errorDetails.put("status", status.value());
		errorDetails.put("errors", errors);
		
		responseService.setResponseCode(status.value());
		responseService.setResponseMessage(getMessageForStatus(status));
		responseService.setResponseError(errorDetails);

		return new ResponseEntity<>(responseService, status);
	}

	private String getMessageForStatus(HttpStatus status) {
		switch (status) {
			case UNAUTHORIZED:
				return ACCESS_DENIED;
			case BAD_REQUEST:
				return INVALID_REQUEST;
			default:
				return status.getReasonPhrase();
		}
	}
}
