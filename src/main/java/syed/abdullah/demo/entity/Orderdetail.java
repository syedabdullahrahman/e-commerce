package syed.abdullah.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Orderdetail.ENTITY_NAME)
@Table(name = Orderdetail.TABLE_NAME, indexes = {
        @Index(name = "productCode", columnList = "productCode")
})
public class Orderdetail implements Serializable {
    public static final String ENTITY_NAME = "Orderdetail";
    public static final String TABLE_NAME = "orderdetails";
    public static final String COLUMN_QUANTITYORDERED_NAME = "quantityOrdered";
    public static final String COLUMN_PRICEEACH_NAME = "priceEach";
    public static final String COLUMN_ORDERLINENUMBER_NAME = "orderLineNumber";
    private static final long serialVersionUID = -7416665297532758189L;


    @EmbeddedId
    private OrderdetailId id;

    @MapsId("orderNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderNumber", nullable = false)
    @JsonIgnore
    private Order orderNumber;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productCode", nullable = false)
    private Product productCode;

    @NotNull
    @Column(name = COLUMN_QUANTITYORDERED_NAME, nullable = false)
    private Integer quantityOrdered;

    @NotNull
    @Column(name = COLUMN_PRICEEACH_NAME, nullable = false, precision = 10, scale = 2)
    private BigDecimal priceEach;

    @NotNull
    @Column(name = COLUMN_ORDERLINENUMBER_NAME, nullable = false)
    private Short orderLineNumber;

}