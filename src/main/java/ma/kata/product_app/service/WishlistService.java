package ma.kata.product_app.service;

import ma.kata.product_app.dto.wishlist.WishlistItemRequestDto;
import ma.kata.product_app.dto.wishlist.WishlistResponseDto;

public interface WishlistService {
    void createWishlistForUser(Long userId);
    WishlistResponseDto getWishlist();
    WishlistResponseDto addProductToWishlist( WishlistItemRequestDto wishlistItemRequestDto);
    WishlistResponseDto removeProductFromWishlist(Long productId);
}
