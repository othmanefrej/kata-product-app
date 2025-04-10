package ma.kata.product_app.service.Impl;

import jakarta.transaction.Transactional;
import ma.kata.product_app.dto.cart.CartResponseDto;
import ma.kata.product_app.dto.cartItem.CartItemRequestDto;
import ma.kata.product_app.mapper.CartMapper;
import ma.kata.product_app.model.Cart;
import ma.kata.product_app.model.CartItem;
import ma.kata.product_app.model.Product;
import ma.kata.product_app.model.User;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.repository.CartItemRepository;
import ma.kata.product_app.repository.CartRepository;
import ma.kata.product_app.repository.ProductRepository;
import ma.kata.product_app.repository.UserRepository;
import ma.kata.product_app.service.CartService;
import ma.kata.product_app.util.AuthUtils;
import org.springframework.stereotype.Service;

import static ma.kata.product_app.util.Util.UtilCustomErrorException;

@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthUtils authUtils;
    private final CartItemRepository cartItemRepository;


    public CartServiceImpl(UserRepository userRepository,
                           CartRepository cartRepository,
                           AuthUtils authUtils,
                           ProductRepository productRepository,
                           CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.authUtils = authUtils;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void createCartForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cartRepository.save(cart);
    }


    @Override
    @Transactional
    public CartResponseDto addProductToCart(CartItemRequestDto cartItemRequestDto) {
        Cart cart = cartRepository.findByUserId(authUtils.getAuthenticatedUserId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.CART_NOT_FOUND));


        Product product = productRepository.findById(cartItemRequestDto.getProductId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND));

        if (cartItemRequestDto.getQuantity() > product.getQuantity()) {
            throw  UtilCustomErrorException(MessageCode.CART_ITEM_QUANTITY_EXCEEDS);

        }
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cart.getId());
        cartItem.setProductId(product.getId());
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(cartItemRequestDto.getQuantity());
        cartItemRepository.save(cartItem);

        product.setQuantity(product.getQuantity() - cartItemRequestDto.getQuantity());
        productRepository.save(product);

        double currentTotal = cart.getTotalPrice() != null ? cart.getTotalPrice() : 0.0;
        cart.setTotalPrice(currentTotal + (product.getPrice() * cartItem.getQuantity()));
        Cart updatedCart = cartRepository.save(cart);

        return CartMapper.toCartResponse(updatedCart);
    }

    @Override
    @Transactional
    public CartResponseDto removeProductFromCart(Long productId) {
        Cart cart = cartRepository.findByUserId(authUtils.getAuthenticatedUserId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.CART_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND));
        CartItem cartItem = cartItemRepository.findByProductIdAndCartId(productId, cart.getId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.CART_ITEM_NOT_FOUND));

        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProduct().getPrice() * cartItem.getQuantity()));
        cartItemRepository.delete(cartItem);
        cartItemRepository.flush();

        product.setQuantity(product.getQuantity() + cartItem.getQuantity());
        productRepository.save(product);

        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toCartResponse(updatedCart);
    }

    @Override
    public CartResponseDto getCart() {
        Cart cart = cartRepository.findByUserId(authUtils.getAuthenticatedUserId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.CART_NOT_FOUND));
        return CartMapper.toCartResponse(cart);
    }
}
