package syed.abdullah.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderdetailId implements Serializable {
    public static final String COLUMN_ORDERNUMBER_NAME = "orderNumber";
    public static final String COLUMN_PRODUCTCODE_NAME = "productCode";
    private static final long serialVersionUID = -5519569166820104327L;

    @NotNull
    @Column(name = COLUMN_ORDERNUMBER_NAME, nullable = false)
    private Integer orderNumber;

    @Size(max = 15)
    @NotNull
    @Column(name = COLUMN_PRODUCTCODE_NAME, nullable = false, length = 15)
    private String productCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderdetailId entity = (OrderdetailId) o;
        return Objects.equals(this.orderNumber, entity.orderNumber) &&
                Objects.equals(this.productCode, entity.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }

}