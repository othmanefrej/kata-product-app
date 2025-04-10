package ma.kata.product_app.service.Impl;

import ma.kata.product_app.dto.product.ProductRequestDto;
import ma.kata.product_app.dto.product.ProductResponseDto;
import ma.kata.product_app.mapper.ProductMapper;
import ma.kata.product_app.model.Product;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.repository.ProductRepository;
import ma.kata.product_app.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static ma.kata.product_app.util.Util.UtilCustomErrorException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toResponse);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND));
        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        if (productRepository.existsByCodeAndInternalReference(dto.getCode(), dto.getInternalReference())) {
            throw UtilCustomErrorException(MessageCode.PRODUCT_ALREADY_EXISTS);
        }
        Product savedProduct = productRepository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product prevProduct = productRepository.findById(id)
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND_WITH_ID));

        if (productRepository.existsByCodeAndIdNot(productRequestDto.getCode(), id)) {
            throw UtilCustomErrorException(MessageCode.PRODUCT_CODE_ALREADY_EXISTS);
        }
        if (productRepository.existsByInternalReferenceAndIdNot(productRequestDto.getInternalReference(), id)) {
            throw UtilCustomErrorException(MessageCode.PRODUCT_INTERNAL_REF_ALREADY_EXISTS);
        }
        Product updatedProduct = productRepository.save(ProductMapper.toUpdateEntity(productRequestDto, prevProduct));
        return ProductMapper.toResponse(updatedProduct);
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> UtilCustomErrorException(MessageCode.PRODUCT_NOT_FOUND_WITH_ID));
        productRepository.delete(product);
        return ProductMapper.toResponse(product);
    }
}
