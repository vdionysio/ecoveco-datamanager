package br.com.ecoveco.datamanager.controller.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LocalityExceptionHandler {

	private final String DUPLICATED_ENTRY = "DUPLICATED_ENTRY";
	private final String INVALID_ID = "INVALID_ID";
	private final String INVALID_ARGUMENTS = "INVALID_ARGUMENTS";

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public final ResponseEntity<?> SQLExceptionHandler(SQLIntegrityConstraintViolationException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(DUPLICATED_ENTRY, details);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<?> NotFoundExpection(ObjectNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(INVALID_ID, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> ArgumentNotValidExpecton(MethodArgumentNotValidException ex) {
		List<FieldError> details = new ArrayList<>();
		details.addAll(ex.getFieldErrors());
		ErrorResponse error = new ErrorResponse(INVALID_ARGUMENTS, details.stream()
				.map(e -> e.getDefaultMessage())
				.collect(Collectors.toList()));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
}
