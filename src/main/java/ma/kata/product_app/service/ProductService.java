package ma.kata.product_app.service;

import ma.kata.product_app.dto.product.ProductRequestDto;
import ma.kata.product_app.dto.product.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDto> getAllProducts(Pageable pageable);
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto deleteProduct(Long id);
    ProductResponseDto getProductById(Long id);
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
}
