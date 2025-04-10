package ma.kata.product_app.dto.cartItem;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItemResponseDto {
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
}
