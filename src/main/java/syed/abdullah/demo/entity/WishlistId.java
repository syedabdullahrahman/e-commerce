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
public class WishlistId implements Serializable {
    public static final String COLUMN_CUSTOMERNUMBER_NAME = "customerNumber";
    public static final String COLUMN_PRODUCTCODE_NAME = "productCode";
    private static final long serialVersionUID = 7938847230528866053L;

    @NotNull
    @Column(name = COLUMN_CUSTOMERNUMBER_NAME, nullable = false)
    private Integer customerNumber;

    @Size(max = 15)
    @NotNull
    @Column(name = COLUMN_PRODUCTCODE_NAME, nullable = false, length = 15)
    private String productCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WishlistId entity = (WishlistId) o;
        return Objects.equals(this.productCode, entity.productCode) &&
                Objects.equals(this.customerNumber, entity.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, customerNumber);
    }

}