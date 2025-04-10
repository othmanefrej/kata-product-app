package ma.kata.product_app.dto.wishlist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ma.kata.product_app.dto.wishListItem.WishlistItemResponseDto;

import java.util.List;

@Getter
@Setter
@Builder
public class WishlistResponseDto {
    private Long id;
    private List<WishlistItemResponseDto> wishlistItems;
}
