package syed.abdullah.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Wishlist.ENTITY_NAME)
@Table(name = Wishlist.TABLE_NAME, schema = "ecommerce", indexes = {
        @Index(name = "productCode", columnList = "productCode")
})
public class Wishlist implements Serializable {
    public static final String ENTITY_NAME = "Wishlist";
    public static final String TABLE_NAME = "wishlists";
    private static final long serialVersionUID = -1323208325796634960L;

    @EmbeddedId
    private WishlistId id;

    @MapsId("customerNumber")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customer;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "productCode", nullable = false)
    private Product productCode;

}