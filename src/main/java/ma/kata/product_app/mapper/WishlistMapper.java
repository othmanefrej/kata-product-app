package ma.kata.product_app.mapper;

import ma.kata.product_app.dto.wishListItem.WishlistItemResponseDto;
import ma.kata.product_app.dto.wishlist.WishlistResponseDto;
import ma.kata.product_app.model.Wishlist;
import ma.kata.product_app.model.WishlistItem;

import java.util.List;
import java.util.stream.Collectors;

public class WishlistMapper {
    public static WishlistItemResponseDto toWishlistItemResponse(WishlistItem item) {
        return WishlistItemResponseDto.builder()
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .productName(item.getProduct() != null ? item.getProduct().getName() : null)
                .build();
    }

    public static WishlistResponseDto toWishlistResponse(Wishlist wishlist) {
        List<WishlistItemResponseDto> itemResponses = wishlist.getWishlistItems().stream()
                .map(WishlistMapper::toWishlistItemResponse)
                .collect(Collectors.toList());

        return WishlistResponseDto.builder()
                .id(wishlist.getId())
                .wishlistItems(itemResponses)
                .build();
    }
}
