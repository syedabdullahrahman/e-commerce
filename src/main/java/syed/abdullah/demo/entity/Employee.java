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
@Entity(name = Employee.ENTITY_NAME)
@Table(name = Employee.TABLE_NAME, schema = "ecommerce", indexes = {
        @Index(name = "officeCode", columnList = "officeCode"),
        @Index(name = "reportsTo", columnList = "reportsTo")
})
public class Employee implements Serializable {
    public static final String ENTITY_NAME = "Employee";
    public static final String TABLE_NAME = "employees";
    public static final String COLUMN_ID_NAME = "employeeNumber";
    public static final String COLUMN_LASTNAME_NAME = "lastName";
    public static final String COLUMN_FIRSTNAME_NAME = "firstName";
    public static final String COLUMN_EXTENSION_NAME = "extension";
    public static final String COLUMN_EMAIL_NAME = "email";
    public static final String COLUMN_JOBTITLE_NAME = "jobTitle";
    private static final long serialVersionUID = 3826079814935019264L;


    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_LASTNAME_NAME, nullable = false, length = 50)
    private String lastName;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_FIRSTNAME_NAME, nullable = false, length = 50)
    private String firstName;

    @Size(max = 10)
    @NotNull
    @Column(name = COLUMN_EXTENSION_NAME, nullable = false, length = 10)
    private String extension;

    @Size(max = 100)
    @NotNull
    @Column(name = COLUMN_EMAIL_NAME, nullable = false, length = 100)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "officeCode", nullable = false)
    private Office officeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportsTo")
    private Employee reportsTo;

    @Size(max = 50)
    @NotNull
    @Column(name = COLUMN_JOBTITLE_NAME, nullable = false, length = 50)
    private String jobTitle;

//    @OneToMany(mappedBy = "salesRepEmployeeNumber")
//    private Set<Customer> customers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "reportsTo")
    private Set<Employee> employees = new LinkedHashSet<>();

}