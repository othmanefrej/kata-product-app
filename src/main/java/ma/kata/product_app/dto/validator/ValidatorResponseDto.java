package ma.kata.product_app.dto.validator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidatorResponseDto {
    private String message;
    private String fieldName;
}
