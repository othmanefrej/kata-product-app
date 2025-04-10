package ma.kata.product_app.dto.product;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequestDto {
    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private String image;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or positive")
    private Double price;

    @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK",
            message = "Invalid format. Allowed values: INSTOCK, LOWSTOCK, OUTOFSTOCK")
    private String inventoryStatus;

    private Integer rating;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be zero or more")
    private Integer quantity;

    private String internalReference;

    private Long shellId;
}

