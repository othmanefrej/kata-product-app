package ma.kata.product_app.service;

import ma.kata.product_app.dto.cart.CartResponseDto;
import ma.kata.product_app.dto.cartItem.CartItemRequestDto;

public interface CartService {
    void createCartForUser(Long userId);
    CartResponseDto addProductToCart(CartItemRequestDto cartItemRequestDto);
    CartResponseDto removeProductFromCart(Long productId);
    CartResponseDto getCart();
}
