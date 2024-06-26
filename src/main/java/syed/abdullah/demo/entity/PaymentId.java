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
public class PaymentId implements Serializable {
    public static final String COLUMN_CUSTOMERNUMBER_NAME = "customerNumber";
    public static final String COLUMN_CHECKNUMBER_NAME = "checkNumber";
    private static final long serialVersionUID = -7142318685662560191L;

    @NotNull
    @Column(name = COLUMN_CUSTOMERNUMBER_NAME, nullable = false)
    private Integer customerNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_CHECKNUMBER_NAME, nullable = false, length = 50)
    private String checkNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentId entity = (PaymentId) o;
        return Objects.equals(this.checkNumber, entity.checkNumber) &&
                Objects.equals(this.customerNumber, entity.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkNumber, customerNumber);
    }

}