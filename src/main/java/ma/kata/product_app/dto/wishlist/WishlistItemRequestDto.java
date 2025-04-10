package ma.kata.product_app.dto.wishlist;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WishlistItemRequestDto {
    @NotBlank(message = "Product id is required")
    private Long productId;
}
