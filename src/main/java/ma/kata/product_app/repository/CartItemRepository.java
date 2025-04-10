package ma.kata.product_app.repository;

import ma.kata.product_app.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductIdAndCartId(Long productId , Long cartId);
}
