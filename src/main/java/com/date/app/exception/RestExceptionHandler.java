package com.date.app.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.date.app.constants.ApiConstants;

/**
 * 
 * The type Rest exception handler.
 */
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * 
	 * Bad request exception handler api error response.
	 * 
	 * @param ex the ex
	 * @return the api error response
	 */
	@ExceptionHandler(value = { ValidationException.class })
	public ResponseEntity<String> badRequestExceptionHandler(final ValidationException ex) {
		return ResponseEntity.badRequest()
				             .body((ex.getLocalizedMessage()));
	}

	/**
	 * 
	 * Invalid arguments exception handler api error response.
	 * 
	 * @param ex the ex
	 * @return the api error response
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String errorMsg = ex.getName() + ApiConstants.REQ_TYPE_MSG + ex.getRequiredType();
		return ResponseEntity.badRequest()
				             .body((errorMsg));
	}
	
	/**
	 * 
	 * Invalid arguments exception handler api error response.
	 * 
	 * @param ex the ex
	 * @return the api error response
	 */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ResponseEntity<String> handleMethodArgumentTypeMismatch(MissingServletRequestParameterException ex,
			WebRequest request) {
		String errorMsg = ex.getParameterName() + ApiConstants.MISSING_PARAMETER_MSG;
		return ResponseEntity.badRequest()
				             .body((errorMsg));
	}

}
