package ma.kata.product_app.service.Impl;

import jakarta.transaction.Transactional;
import ma.kata.product_app.dto.wishlist.WishlistItemRequestDto;
import ma.kata.product_app.dto.wishlist.WishlistResponseDto;
import ma.kata.product_app.mapper.WishlistMapper;
import ma.kata.product_app.model.*;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.repository.ProductRepository;
import ma.kata.product_app.repository.UserRepository;
import ma.kata.product_app.repository.WishlistItemRepository;
import ma.kata.product_app.repository.WishlistRepository;
import ma.kata.product_app.service.WishlistService;
import ma.kata.product_app.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ma.kata.product_app.util.Util.UtilCustomErrorException;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final AuthUtils authUtils;


    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               ProductRepository productRepository,
                               UserRepository userRepository,
                               WishlistItemRepository wishlistItemRepository,
                               AuthUtils authUtils

    ) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.authUtils = authUtils;
    }

    @Override
    public void createWishlistForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.USER_NOT_FOUND));
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(user.getId());
        wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistResponseDto getWishlist() {
        Wishlist wishlist = wishlistRepository.findByUserId(authUtils.getAuthenticatedUserId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.WISHLIST_NOT_FOUND));
        return WishlistMapper.toWishlistResponse(wishlist);
    }

    @Override
    public WishlistResponseDto addProductToWishlist(WishlistItemRequestDto wishlistItemRequestDto) {
        Product product = productRepository.findById(wishlistItemRequestDto.getProductId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND));

        Wishlist wishlist = wishlistRepository.findByUserId(authUtils.getAuthenticatedUserId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.WISHLIST_NOT_FOUND));

        Optional<WishlistItem> existingItem = wishlistItemRepository.findByProductIdAndWishlistId(
                product.getId(), wishlist.getId());

        if (existingItem.isPresent()) {
            throw UtilCustomErrorException(MessageCode.WISHLIST_ITEM_ALREADY_EXISTS);
        }

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(product);
        wishlistItem.setProductId(wishlist.getId());
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setWishlistId(wishlist.getId());
        wishlistItemRepository.save(wishlistItem);

        return WishlistMapper.toWishlistResponse(wishlist);
    }

    @Override
    public WishlistResponseDto removeProductFromWishlist( Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(authUtils.getAuthenticatedUserId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.WISHLIST_NOT_FOUND));
        WishlistItem wishlistItem = wishlistItemRepository.findByProductIdAndWishlistId(productId, wishlist.getId())
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND));
        wishlistItemRepository.delete(wishlistItem);
        return WishlistMapper.toWishlistResponse(wishlist);
    }
}

