package ma.kata.product_app.dto.api;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private T data;
    private String codResponse;
    private String message;
    private HttpStatus statusCode;
}

