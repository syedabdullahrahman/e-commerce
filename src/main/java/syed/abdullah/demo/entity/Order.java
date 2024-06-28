package syed.abdullah.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Order.ENTITY_NAME)
@Table(name = Order.TABLE_NAME, schema = "ecommerce", indexes = {
        @Index(name = "customerNumber", columnList = "customerNumber")
})
public class Order implements Serializable {
    public static final String ENTITY_NAME = "Order";
    public static final String TABLE_NAME = "orders";
    public static final String COLUMN_ID_NAME = "orderNumber";
    public static final String COLUMN_ORDERDATE_NAME = "orderDate";
    public static final String COLUMN_REQUIREDDATE_NAME = "requiredDate";
    public static final String COLUMN_SHIPPEDDATE_NAME = "shippedDate";
    public static final String COLUMN_STATUS_NAME = "status";
    public static final String COLUMN_COMMENTS_NAME = "comments";
    private static final long serialVersionUID = -8670088652920010770L;


    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Integer id;

    @NotNull
    @Column(name = COLUMN_ORDERDATE_NAME, nullable = false)
    private LocalDate orderDate;

    @NotNull
    @Column(name = COLUMN_REQUIREDDATE_NAME, nullable = false)
    private LocalDate requiredDate;

    @Column(name = COLUMN_SHIPPEDDATE_NAME)
    private LocalDate shippedDate;

    @NotNull
    @Column(name = COLUMN_STATUS_NAME, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Lob
    @Column(name = COLUMN_COMMENTS_NAME)
    private String comments;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderNumber")
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

}