package ma.kata.product_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import ma.kata.product_app.dto.api.ApiPageResponse;
import ma.kata.product_app.dto.api.ApiResponse;
import ma.kata.product_app.dto.product.ProductRequestDto;
import ma.kata.product_app.dto.product.ProductResponseDto;
import ma.kata.product_app.mapper.ApiResponseMapper;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
//    @PreAuthorize("@authService.isAdmin()")
    @Operation(summary = "Get all products", description = "Retrieve a paginated list of all products.")
    public ApiPageResponse<List<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction
    ) {
        Pageable pageable = (sortBy != null && direction != null)
                ? PageRequest.of(page, size, direction, sortBy)
                : PageRequest.of(page, size);

        return ApiResponseMapper.createPaginatedResponse(
                MessageCode.PRODUCTS_GET_SUCCESS,
                productService.getAllProducts(pageable)
        );
    }

    @PostMapping
    @PreAuthorize("@authService.isAdmin()")
    @Operation(summary = "Create a new product", description = "Create a new product with the provided details.")
    public ApiResponse<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.PRODUCT_CREATED_SUCCESS,
                productService.createProduct(productRequestDto)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authService.isAdmin()")
    @Operation(summary = "Update an existing product", description = "Update the details of a product by its ID.")
    public ApiResponse<ProductResponseDto> updateProduct(@PathVariable Long id,
                                                         @Valid @RequestBody ProductRequestDto productRequestDto) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.PRODUCT_UPDATED_SUCCESS,
                productService.updateProduct(id, productRequestDto)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authService.isAdmin()")
    @Operation(summary = "Delete a product", description = "Delete an existing product by its ID.")
    public ApiResponse<ProductResponseDto> deleteProduct(@PathVariable Long id) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.PRODUCT_DELETED_SUCCESS,
                productService.deleteProduct(id)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authService.isAdmin()")
    @Operation(summary = "Get a product by ID", description = "Retrieve the details of a specific product by its ID.")
    public ApiResponse<ProductResponseDto> getProduct(@PathVariable Long id) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.PRODUCTS_GET_SUCCESS,
                productService.getProductById(id)
        );
    }
}
