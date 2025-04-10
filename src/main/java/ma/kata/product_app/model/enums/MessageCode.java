package ma.kata.product_app.model.enums;

import org.springframework.http.HttpStatus;

public enum MessageCode {

    // User Messages
    USER_CREATED_SUCCESS(HttpStatus.CREATED, "User created successfully!", "100"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found!", "101"),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "User already exists!", "102"),


    // Product Messages
    PRODUCT_CODE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product code already exists!", "200"),
    PRODUCTS_GET_SUCCESS(HttpStatus.OK, "Products retrieved successfully!", "201"),
    PRODUCT_CREATED_SUCCESS(HttpStatus.CREATED, "Product created successfully!", "202"),
    PRODUCT_UPDATED_SUCCESS(HttpStatus.OK, "Product updated successfully!", "203"),
    PRODUCT_DELETED_SUCCESS(HttpStatus.OK, "Product deleted successfully!", "204"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found!", "205"),
    PRODUCT_NOT_FOUND_WITH_ID(HttpStatus.NOT_FOUND, "Product not found with id ", "206"),
    PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product already exists!", "207"),
    PRODUCT_INTERNAL_REF_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product internal ref already exists!", "208"),

    // Cart Messages
    CART_GET_SUCCESS(HttpStatus.OK, "Cart retrieved successfully!", "300"),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "Cart not found!", "301"),
    CART_ITEM_QUANTITY_EXCEEDS(HttpStatus.BAD_REQUEST, "Requested quantity exceeds available stock in cart", "302"),
    CART_ITEM_ADDED_SUCCESS(HttpStatus.CREATED, "Product successfully added to cart!", "303"),
    CART_ITEM_UPDATED_SUCCESS(HttpStatus.OK, "Product quantity updated in cart successfully!", "304"),
    CART_ITEM_DELETED_SUCCESS(HttpStatus.OK, "Product removed from cart successfully!", "305"),
    CART_ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST, "Cart item not found!", "306"),

    // Wishlist Messages
    WISHLIST_GET_SUCCESS(HttpStatus.OK, "Wishlist retrieved successfully!", "400"),
    WISHLIST_DELETED_SUCCESS(HttpStatus.OK, "Wishlist deleted successfully!", "401"),
    WISHLIST_ITEM_ALREADY_EXISTS(HttpStatus.CONFLICT, "Product already exists in wishlist!", "402"),
    WISHLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "Wishlist not found!", "403");


    private final HttpStatus status;
    private final String message;
    private final String codeResponse;

    MessageCode(HttpStatus status, String message, String codeResponse) {
        this.status = status;
        this.message = message;
        this.codeResponse = codeResponse;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCodeResponse() {
        return codeResponse;
    }
}
