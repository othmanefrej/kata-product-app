package ma.kata.product_app.dto.wishListItem;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WishlistItemResponseDto {
    private Long productId;
    private String productName;
}
