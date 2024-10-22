package syed.abdullah.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Payment.ENTITY_NAME)
@Table(name = Payment.TABLE_NAME)
public class Payment implements Serializable {
    public static final String ENTITY_NAME = "Payment";
    public static final String TABLE_NAME = "payments";
    public static final String COLUMN_PAYMENTDATE_NAME = "paymentDate";
    public static final String COLUMN_AMOUNT_NAME = "amount";
    private static final long serialVersionUID = 7797160986810858836L;

    @EmbeddedId
    private PaymentId id;

    @MapsId("customerNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customerNumber;

    @NotNull
    @Column(name = COLUMN_PAYMENTDATE_NAME, nullable = false)
    private LocalDate paymentDate;

    @NotNull
    @Column(name = COLUMN_AMOUNT_NAME, nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}