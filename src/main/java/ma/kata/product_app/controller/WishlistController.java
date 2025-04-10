package ma.kata.product_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import ma.kata.product_app.dto.api.ApiResponse;
import ma.kata.product_app.dto.wishlist.WishlistItemRequestDto;
import ma.kata.product_app.dto.wishlist.WishlistResponseDto;
import ma.kata.product_app.mapper.ApiResponseMapper;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.service.WishlistService;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/wishLists")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    @Operation(
            summary = "Get wish list of authenticated user",
            description = "Returns the wish list details for the currently logged-in user."
    )
    public ApiResponse<WishlistResponseDto> getWishlist() {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.WISHLIST_GET_SUCCESS,
                wishlistService.getWishlist()
        );
    }

    @PostMapping("/add")
    @Operation(summary = "Add a product to the current user's wish list")
    public ApiResponse<WishlistResponseDto> addProductToWishlist(@RequestBody WishlistItemRequestDto wishlistItemRequestDto) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.PRODUCT_CREATED_SUCCESS,
                wishlistService.addProductToWishlist(wishlistItemRequestDto)
        );
    }

    @DeleteMapping("/remove/{productId}")
    @Operation(summary = "Remove a product from the current user's wish list")
    public ApiResponse<WishlistResponseDto> removeProductFromWishlist(@PathVariable Long productId) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.WISHLIST_DELETED_SUCCESS,
                wishlistService.removeProductFromWishlist(productId)
        );
    }
}
