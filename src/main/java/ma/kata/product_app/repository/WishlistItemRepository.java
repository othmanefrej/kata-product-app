package ma.kata.product_app.repository;


import ma.kata.product_app.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    Optional<WishlistItem> findByProductIdAndWishlistId(Long productId, Long wishlistId);

}