package ma.kata.product_app.mapper;

import ma.kata.product_app.dto.api.ApiPageResponse;
import ma.kata.product_app.dto.api.ApiResponse;
import ma.kata.product_app.model.enums.MessageCode;
import org.springframework.data.domain.Page;

import java.util.List;

public class ApiResponseMapper {
    public static <T> ApiResponse<T> FromMessageCodetoResponse(MessageCode messageCode, T data) {
        return new ApiResponse<>(
                data,
                messageCode.getCodeResponse(),
                messageCode.getMessage(),
                messageCode.getStatus()
        );
    }

    public static <T> ApiPageResponse<List<T>> createPaginatedResponse(
            MessageCode messageCode,
            Page<T> page) {

        ApiPageResponse<List<T>> response = new ApiPageResponse<>(
                page.getContent(),
                messageCode.getCodeResponse(),
                messageCode.getMessage(),
                messageCode.getStatus()
        );

        response.addPaginationMetadata(page);
        return response;
    }
}
