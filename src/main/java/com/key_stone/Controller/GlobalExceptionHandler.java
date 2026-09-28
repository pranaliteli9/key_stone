package com.key_stone.Controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.key_stone.DTO.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Handle normal RuntimeException
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException exception) {

		ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
				LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Handle all other unexpected exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleException(Exception exception) {

		ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong",
				LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException exception) {

		String message = exception.getBindingResult().getFieldErrors().stream().findFirst()
				.map(error -> error.getDefaultMessage()).orElse("Validation failed");

		ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}