package ma.kata.product_app.mapper;

import jakarta.validation.ConstraintViolation;
import ma.kata.product_app.dto.validator.ValidatorResponseDto;
import org.springframework.validation.FieldError;

import static ma.kata.product_app.util.Util.*;


public class ValidationMapper {
    public static ValidatorResponseDto toResponse(FieldError fieldError) {
        return ValidatorResponseDto.builder()
                .fieldName(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }

    public static ValidatorResponseDto toMultipeResponse(ConstraintViolation constraintViolation) {
        return ValidatorResponseDto.builder()
                .fieldName(extractFieldName(constraintViolation.getPropertyPath().toString()))
                .message(constraintViolation.getMessage())
                .build();
    }
}
