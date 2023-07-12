package br.com.superatecnologia.managementapi.exceptions.handlers;

import br.com.superatecnologia.managementapi.exceptions.*;
import br.com.superatecnologia.managementapi.exceptions.models.ErrorObject;
import br.com.superatecnologia.managementapi.exceptions.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionAdviceHandler extends ResponseEntityExceptionHandler implements ExceptionAdvice {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(HttpStatus status, List<ErrorObject> errors) {
        return new ErrorResponse(Instant.now().toEpochMilli(),
                status.value(),
                status.getReasonPhrase(),
                "Request has invalid fields",
                errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @ExceptionHandler(AccountException.class)
    ResponseEntity<ErrorResponse> handlerUsersException(AccountException ex) {
        return ResponseEntity.status(ex.getError().getStatusCode())
                .body((new ErrorResponse(Instant.now().toEpochMilli(),
                        ex.getError().getStatusCode(),
                        ex.getError().getCode(),
                        ex.getMessage(), new ArrayList<>())));
    }

    @ResponseBody
    @ExceptionHandler(OtpException.class)
    ResponseEntity<ErrorResponse> handlerOtpException(OtpException ex) {
        return ResponseEntity.status(ex.getError().getStatusCode())
                .body((new ErrorResponse(Instant.now().toEpochMilli(),
                        ex.getError().getStatusCode(),
                        ex.getError().getCode(),
                        ex.getMessage(), new ArrayList<>())));
    }

    @ResponseBody
    @ExceptionHandler(TokenException.class)
    ResponseEntity<ErrorResponse> handlerTokenException(TokenException ex) {
        return ResponseEntity.status(ex.getError().getStatusCode())
                .body((new ErrorResponse(Instant.now().toEpochMilli(),
                        ex.getError().getStatusCode(),
                        ex.getError().getCode(),
                        ex.getMessage(), new ArrayList<>())));
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ErrorResponse> handlerAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(ex.getError().getStatusCode())
                .body((new ErrorResponse(Instant.now().toEpochMilli(),
                        ex.getError().getStatusCode(),
                        ex.getError().getCode(),
                        ex.getMessage(), new ArrayList<>())));
    }

    @ResponseBody
    @ExceptionHandler(TransactionException.class)
    ResponseEntity<ErrorResponse> handlerTransactionException(TransactionException ex) {
        return ResponseEntity.status(ex.getError().getStatusCode())
                .body((new ErrorResponse(Instant.now().toEpochMilli(),
                        ex.getError().getStatusCode(),
                        ex.getError().getCode(),
                        ex.getMessage(), new ArrayList<>())));
    }
}