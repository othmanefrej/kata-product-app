package ma.kata.product_app.service.Impl.observer;

import ma.kata.product_app.service.CartService;
import ma.kata.product_app.service.WishlistService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedListener {

    private final CartService cartService;
    private final WishlistService wishlistService;

    public UserCreatedListener(CartService cartService,WishlistService wishlistService) {
        this.cartService = cartService;
        this.wishlistService = wishlistService;
    }

    @EventListener
    public void onUserCreated(UserCreatedEvent event) {
        cartService.createCartForUser(event.getUser().getId());
        wishlistService.createWishlistForUser(event.getUser().getId());

    }
}