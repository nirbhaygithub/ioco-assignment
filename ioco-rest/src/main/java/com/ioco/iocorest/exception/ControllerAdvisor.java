package com.ioco.iocorest.exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ioco.iocorest.response.IocoResponse;


/**
 * @author nirbhaysharma
 *
 */
@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> handleNodataFoundException(NoDataFoundException ex, WebRequest request) {

		IocoResponse response = new IocoResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value() + "", null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	
	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<Object> handleConstraintViolationException(TransactionSystemException ex,
			WebRequest request) {

		IocoResponse response = new IocoResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value() + "", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		IocoResponse response = new IocoResponse(errors, HttpStatus.BAD_REQUEST.toString(), null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
