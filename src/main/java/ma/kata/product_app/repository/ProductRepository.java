package ma.kata.product_app.repository;

import ma.kata.product_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodeAndInternalReference(String code, String internalReference);
    boolean existsByCodeAndIdNot(String code, Long id);
    boolean existsByInternalReferenceAndIdNot(String internalReference, Long id);
}
