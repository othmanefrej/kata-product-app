package ma.kata.product_app.mapper;

import ma.kata.product_app.dto.product.ProductRequestDto;
import ma.kata.product_app.dto.product.ProductResponseDto;
import ma.kata.product_app.model.Product;
import ma.kata.product_app.model.enums.InventoryStatus;

public class ProductMapper {
    public static Product toEntity(ProductRequestDto productRequestDto) {
        return Product.builder()
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .image(productRequestDto.getImage())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .inventoryStatus(InventoryStatus.fromValue(productRequestDto.getInventoryStatus()))
                .internalReference(productRequestDto.getInternalReference())
                .rating(productRequestDto.getRating())
                .shellId(productRequestDto.getShellId())
                .build();
    }

    public static Product toUpdateEntity(ProductRequestDto productRequestDto, Product previous) {
        return Product.builder()
                .id(previous.getId())
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .image(productRequestDto.getImage())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .internalReference(productRequestDto.getInternalReference())
                .shellId(productRequestDto.getShellId())
                .inventoryStatus(InventoryStatus.fromValue(productRequestDto.getInventoryStatus()))
                .rating(productRequestDto.getRating())
                .build();
    }

    public static ProductResponseDto toResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .internalReference(product.getInternalReference())
                .shellId(product.getShellId())
                .inventoryStatus(product.getInventoryStatus() != null ? product.getInventoryStatus().getValue() : null)                .rating(product.getRating())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
