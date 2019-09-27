package pl.piotrschodzinski.homebudget.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.piotrschodzinski.homebudget.exception.customException.EntityNotFoundException;
import pl.piotrschodzinski.homebudget.exception.customException.IncorrectIdException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, IncorrectIdException.class})
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        HttpStatus httpStatus = getHttpStatus(ex);
        Map<String, String> errors = new HashMap<>();
        errors.put("custom exception", ex.getMessage());
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setHttpStatus(httpStatus.toString());
        customErrorResponse.setErrors(errors);
        return new ResponseEntity<>(customErrorResponse, httpStatus);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(v -> {
            errors.put(v.getPropertyPath().toString(), v.getMessage());
        });

        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setHttpStatus(HttpStatus.BAD_REQUEST.toString());
        customErrorResponse.setErrors(errors);

        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String errorMessage = e.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setHttpStatus(status.toString());
        customErrorResponse.setErrors(errors);

        return new ResponseEntity<>(customErrorResponse, status);
    }

    private HttpStatus getHttpStatus(Exception ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Class exClass = ex.getClass();
        if (exClass.equals(EntityNotFoundException.class)) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (exClass.equals(IncorrectIdException.class)) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return httpStatus;
    }
}
