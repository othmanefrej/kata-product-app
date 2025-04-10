package ma.kata.product_app.dto.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ApiPageResponse<T> extends ApiResponse<T> {
    private Map<String, Object> metadata = new HashMap<>();

    public ApiPageResponse(T data, String codResponse, String message, HttpStatus statusCode) {
        super(data, codResponse, message, statusCode);
    }

    public void addPaginationMetadata(Page<?> page) {
        this.metadata.put("currentPage", page.getNumber());
        this.metadata.put("totalItems", page.getTotalElements());
        this.metadata.put("totalPages", page.getTotalPages());
        this.metadata.put("pageSize", page.getSize());
        this.metadata.put("hasNext", page.hasNext());
        this.metadata.put("hasPrevious", page.hasPrevious());
    }
}
