package ma.kata.product_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@EqualsAndHashCode(callSuper = true)
@Table(name = "wish_list_item")
@SQLDelete(sql = "UPDATE wish_list_item SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "wishlist_id")
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Wishlist wishlist;
}
