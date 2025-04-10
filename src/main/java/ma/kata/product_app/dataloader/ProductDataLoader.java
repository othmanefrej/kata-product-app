package ma.kata.product_app.dataloader;

import jakarta.transaction.Transactional;
import ma.kata.product_app.model.Product;
import ma.kata.product_app.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class ProductDataLoader {

    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void loadData() {
        if (productRepository.count() == 0) {
            Product product1 = Product.builder()
                    .code("P1")
                    .name("Product 1")
                    .description("First product.")
                    .image("https://product-image-1.jpg")
                    .category("Electronics")
                    .price(99.99)
                    .quantity(25)
                    .internalReference("OF1")
                    .shellId(1L)
                    .build();

            Product product2 = Product.builder()
                    .code("P2")
                    .name("Product 2")
                    .description("Second product.")
                    .image("https://product-image-2.jpg")
                    .category("Home")
                    .price(99.99)
                    .quantity(25)
                    .internalReference("OF2")
                    .shellId(2L)
                    .build();

            productRepository.saveAll(Arrays.asList(product1, product2));
        }
    }
}
