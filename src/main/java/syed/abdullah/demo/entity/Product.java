package syed.abdullah.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Product.ENTITY_NAME)
@Table(name = Product.TABLE_NAME, schema = "ecommerce", indexes = {
        @Index(name = "productLine", columnList = "productLine")
})
public class Product implements Serializable {
    public static final String ENTITY_NAME = "Product";
    public static final String TABLE_NAME = "products";
    public static final String COLUMN_PRODUCTCODE_NAME = "productCode";
    public static final String COLUMN_PRODUCTNAME_NAME = "productName";
    public static final String COLUMN_PRODUCTSCALE_NAME = "productScale";
    public static final String COLUMN_PRODUCTVENDOR_NAME = "productVendor";
    public static final String COLUMN_PRODUCTDESCRIPTION_NAME = "productDescription";
    public static final String COLUMN_QUANTITYINSTOCK_NAME = "quantityInStock";
    public static final String COLUMN_BUYPRICE_NAME = "buyPrice";
    public static final String COLUMN_MSRP_NAME = "MSRP";
    private static final long serialVersionUID = 1747846788563766319L;


    @Id
    @Size(max = 15)
    @Column(name = COLUMN_PRODUCTCODE_NAME, nullable = false, length = 15)
    private String productCode;

    @Size(max = 70)
    @NotNull
    @Column(name = COLUMN_PRODUCTNAME_NAME, nullable = false, length = 70)
    private String productName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productLine", nullable = false)
    private Productline productLine;

    @Size(max = 10)
    @NotNull
    @Column(name = COLUMN_PRODUCTSCALE_NAME, nullable = false, length = 10)
    private String productScale;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_PRODUCTVENDOR_NAME, nullable = false, length = 50)
    private String productVendor;

    @NotNull
    @Lob
    @Column(name = COLUMN_PRODUCTDESCRIPTION_NAME, nullable = false)
    private String productDescription;

    @NotNull
    @Column(name = COLUMN_QUANTITYINSTOCK_NAME, nullable = false)
    private Short quantityInStock;

    @NotNull
    @Column(name = COLUMN_BUYPRICE_NAME, nullable = false, precision = 10, scale = 2)
    private BigDecimal buyPrice;

    @NotNull
    @Column(name = COLUMN_MSRP_NAME, nullable = false, precision = 10, scale = 2)
    private BigDecimal msrp;

    @OneToMany(mappedBy = "productCode")
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

}