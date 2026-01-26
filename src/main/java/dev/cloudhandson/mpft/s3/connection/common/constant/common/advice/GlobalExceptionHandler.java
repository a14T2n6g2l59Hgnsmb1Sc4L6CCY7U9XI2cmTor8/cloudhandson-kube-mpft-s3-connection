package dev.cloudhandson.mpft.s3.connection.common.constant.common.advice;

import dev.cloudhandson.mpft.s3.connection.common.constant.ErrorMessages;
import dev.cloudhandson.mpft.s3.connection.common.exception.ErrorResponse;
import dev.cloudhandson.mpft.s3.connection.common.exception.S3ConnectionAlreadyExistException;
import dev.cloudhandson.mpft.s3.model.S3ConnectionNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleRuntimeException(RuntimeException exception) {
        Throwable cause = exception.getCause();
        return processException(Objects.requireNonNullElse(cause, exception));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNoResourceFoundException(NoResourceFoundException exception) {
        Throwable cause = exception.getCause();
        return processException(Objects.requireNonNullElse(cause, exception));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleWebExchangeBindException(WebExchangeBindException exception) {
        return processException(exception);
    }

    @ExceptionHandler()
    private Mono<ResponseEntity<ErrorResponse>> processException(Throwable exception) {
        LOGGER.error(exception);
        ErrorResponse errorResponse = new ErrorResponse();
        String message = exception.getMessage();
        String type = exception
            .getClass()
            .getSimpleName();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

        if (exception instanceof S3ConnectionNotFoundException || exception instanceof NoResourceFoundException) {
            status = HttpStatus.NOT_FOUND.value();
        } else if (exception instanceof S3ConnectionAlreadyExistException) {
            status = HttpStatus.BAD_REQUEST.value();
        } else if (exception instanceof WebExchangeBindException ex) {
            List<Map<String, String>> errorList = new ArrayList<>();
            ex.getAllErrors()
              .forEach(error -> {
                  errorList.add(Map.of("field", ((FieldError) error).getField(), "message", error.getDefaultMessage()));
              });
            message = ErrorMessages.INVALID_PARAMETERS;
            status = HttpStatus.BAD_REQUEST.value();
            errorResponse.setErrors(errorList);
        }
        errorResponse.setMessage(message);
        errorResponse.setType(type);
        errorResponse.setCode(status);
        return Mono.just(ResponseEntity
            .status(status)
            .body(errorResponse));
    }
}

