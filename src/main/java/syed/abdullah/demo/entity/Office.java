package syed.abdullah.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Office.ENTITY_NAME)
@Table(name = Office.TABLE_NAME, schema = "ecommerce")
public class Office implements Serializable {
    public static final String ENTITY_NAME = "Office";
    public static final String TABLE_NAME = "offices";
    public static final String COLUMN_OFFICECODE_NAME = "officeCode";
    public static final String COLUMN_CITY_NAME = "city";
    public static final String COLUMN_PHONE_NAME = "phone";
    public static final String COLUMN_ADDRESSLINE1_NAME = "addressLine1";
    public static final String COLUMN_ADDRESSLINE2_NAME = "addressLine2";
    public static final String COLUMN_STATE_NAME = "state";
    public static final String COLUMN_COUNTRY_NAME = "country";
    public static final String COLUMN_POSTALCODE_NAME = "postalCode";
    public static final String COLUMN_TERRITORY_NAME = "territory";
    private static final long serialVersionUID = 7919960140448384635L;


    @Id
    @Size(max = 10)
    @Column(name = COLUMN_OFFICECODE_NAME, nullable = false, length = 10)
    private String officeCode;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_CITY_NAME, nullable = false, length = 50)
    private String city;

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
    @Column(name = COLUMN_STATE_NAME, length = 50)
    private String state;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_COUNTRY_NAME, nullable = false, length = 50)
    private String country;

    @Size(max = 15)
    @NotNull
    @Column(name = COLUMN_POSTALCODE_NAME, nullable = false, length = 15)
    private String postalCode;

    @Size(max = 10)
    @NotNull
    @Column(name = COLUMN_TERRITORY_NAME, nullable = false, length = 10)
    private String territory;

    @OneToMany(mappedBy = "officeCode")
    private Set<Employee> employees = new LinkedHashSet<>();

}