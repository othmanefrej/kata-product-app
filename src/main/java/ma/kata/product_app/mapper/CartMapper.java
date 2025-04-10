package ma.kata.product_app.mapper;

import ma.kata.product_app.dto.cart.CartResponseDto;
import ma.kata.product_app.dto.cartItem.CartItemResponseDto;
import ma.kata.product_app.model.Cart;
import ma.kata.product_app.model.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartItemResponseDto toCartItemResponse(CartItem item) {
        return CartItemResponseDto.builder()
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .productName(item.getProduct() != null ? item.getProduct().getName() : null)
                .price(item.getProduct() != null ? item.getProduct().getPrice() : null)
                .quantity(item.getQuantity())
                .build();
    }

    public static CartResponseDto toCartResponse(Cart cart) {
        List<CartItemResponseDto> itemResponses = cart.getItems().stream()
                .map(CartMapper::toCartItemResponse)
                .collect(Collectors.toList());

        return CartResponseDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .totalPrice(cart.getTotalPrice())
                .items(itemResponses)
                .build();
    }
}
