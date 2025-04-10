package ma.kata.product_app.controller;


import io.swagger.v3.oas.annotations.Operation;
import ma.kata.product_app.dto.api.ApiResponse;
import ma.kata.product_app.dto.cart.CartResponseDto;
import ma.kata.product_app.dto.cartItem.CartItemRequestDto;
import ma.kata.product_app.mapper.ApiResponseMapper;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @Operation(
            summary = "Get cart of authenticated user",
            description = "Returns the cart details for the currently logged-in user."
    )
    @GetMapping
    public ApiResponse<CartResponseDto> getCart() {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.CART_GET_SUCCESS,
                cartService.getCart()
        );
    }

    @Operation(summary = "Add a product to the current user's cart")
    @PostMapping("/add")
    public ApiResponse<CartResponseDto> addProductToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.CART_ITEM_ADDED_SUCCESS,
                cartService.addProductToCart(cartItemRequestDto)
        );
    }

    @Operation(summary = "Remove a product from the current user's cart")
    @DeleteMapping("/remove/{productId}")
    public ApiResponse<CartResponseDto> removeProductFromCart(@PathVariable Long productId) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.CART_ITEM_DELETED_SUCCESS,
                cartService.removeProductFromCart(productId)
        );
    }
}
