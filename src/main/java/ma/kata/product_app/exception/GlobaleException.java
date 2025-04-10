package ma.kata.product_app.exception;

import jakarta.validation.ConstraintViolationException;
import ma.kata.product_app.dto.validator.ValidatorResponseDto;
import ma.kata.product_app.mapper.ValidationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobaleException {


    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomErrorException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(ex.getStatus().value()));
        response.put("responseCode", ex.getErrorCode());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 403 - Forbidden (Lack of permissions)
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ProblemDetail handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        return buildProblemDetail(HttpStatus.FORBIDDEN, "403", "Authorization denied");
    }
    // 403 - Forbidden (Lack of permissions)
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ProblemDetail handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        return buildProblemDetail(HttpStatus.UNAUTHORIZED, "401", "Insufficient authentication");
    }

    // Handle NoResourceFoundException (404)
    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handleNoResourceFoundException(NoResourceFoundException ex) {
        return buildProblemDetail(HttpStatus.NOT_FOUND, "000", "Resource not found");
    }

    // Handle NoHandlerFoundException (404)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return buildProblemDetail(HttpStatus.NOT_FOUND, "000", "Page not found");
    }

    // Handle MethodArgumentNotValidException for validation errors (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidatorResponseDto> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(ValidationMapper::toResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("responseCode", "002");
        response.put("message", "Validation errors");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    // Handle ConstraintViolationException for Liste validation errors (400)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidatorResponseDto> errors = ex.getConstraintViolations().stream()
                .map(ValidationMapper::toMultipeResponse)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("responseCode", "001");
        response.put("message", "Validation errors");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    // Handle generic Exception (500)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception exception) {
        return buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "000", exception.getClass().getSimpleName()
                + ": " + exception.getMessage());
    }


    private ProblemDetail buildProblemDetail(HttpStatus status, String errorCode, String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setProperty("status", status.value());
        problemDetail.setProperty("responseCode", errorCode);
        return problemDetail;
    }
}
