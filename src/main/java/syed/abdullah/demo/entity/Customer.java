package syed.abdullah.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = Customer.ENTITY_NAME)
@Table(name = Customer.TABLE_NAME, schema = "ecommerce", indexes = {
        @Index(name = "salesRepEmployeeNumber", columnList = "salesRepEmployeeNumber")
})
public class Customer implements Serializable {
    public static final String ENTITY_NAME = "Customer";
    public static final String TABLE_NAME = "customers";
    public static final String COLUMN_ID_NAME = "customerNumber";
    public static final String COLUMN_CUSTOMERNAME_NAME = "customerName";
    public static final String COLUMN_CONTACTLASTNAME_NAME = "contactLastName";
    public static final String COLUMN_CONTACTFIRSTNAME_NAME = "contactFirstName";
    public static final String COLUMN_PHONE_NAME = "phone";
    public static final String COLUMN_ADDRESSLINE1_NAME = "addressLine1";
    public static final String COLUMN_ADDRESSLINE2_NAME = "addressLine2";
    public static final String COLUMN_CITY_NAME = "city";
    public static final String COLUMN_STATE_NAME = "state";
    public static final String COLUMN_POSTALCODE_NAME = "postalCode";
    public static final String COLUMN_COUNTRY_NAME = "country";
    public static final String COLUMN_CREDITLIMIT_NAME = "creditLimit";
    private static final long serialVersionUID = 8562787725744575855L;


    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_CUSTOMERNAME_NAME, nullable = false, length = 50)
    private String customerName;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_CONTACTLASTNAME_NAME, nullable = false, length = 50)
    private String contactLastName;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_CONTACTFIRSTNAME_NAME, nullable = false, length = 50)
    private String contactFirstName;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_PHONE_NAME, nullable = false, length = 50)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_ADDRESSLINE1_NAME, nullable = false, length = 50)
    private String addressLine1;

    @Size(max = 50)
    @Column(name = COLUMN_ADDRESSLINE2_NAME, length = 50)
    private String addressLine2;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_CITY_NAME, nullable = false, length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = COLUMN_STATE_NAME, length = 50)
    private String state;

    @Size(max = 15)
    @Column(name = COLUMN_POSTALCODE_NAME, length = 15)
    private String postalCode;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_COUNTRY_NAME, nullable = false, length = 50)
    private String country;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "salesRepEmployeeNumber")
//    private Employee salesRepEmployeeNumber;

    @Column(name = COLUMN_CREDITLIMIT_NAME, precision = 10, scale = 2)
    private BigDecimal creditLimit;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "customerNumber")
    @JsonIgnore
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Wishlist> wishlists = new LinkedHashSet<>();
}