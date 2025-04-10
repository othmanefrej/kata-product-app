package ma.kata.product_app.dto.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ma.kata.product_app.dto.cartItem.CartItemResponseDto;

import java.util.List;

@Getter
@Setter
@Builder
public class CartResponseDto {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private List<CartItemResponseDto> items;
}
