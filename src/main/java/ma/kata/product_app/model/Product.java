package ma.kata.product_app.model;

import jakarta.persistence.*;
import lombok.*;
import ma.kata.product_app.converter.InventoryStatusConverter;
import ma.kata.product_app.model.enums.InventoryStatus;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    @Convert(converter = InventoryStatusConverter.class)
    private InventoryStatus inventoryStatus;
    private Integer rating;
    private String internalReference;
    private Long shellId;
}
