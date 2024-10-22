package syed.abdullah.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity(name = Productline.ENTITY_NAME)
@Table(name = Productline.TABLE_NAME)
public class Productline implements Serializable {
    public static final String ENTITY_NAME = "Productline";
    public static final String TABLE_NAME = "productlines";
    public static final String COLUMN_PRODUCTLINE_NAME = "productLine";
    public static final String COLUMN_TEXTDESCRIPTION_NAME = "textDescription";
    public static final String COLUMN_HTMLDESCRIPTION_NAME = "htmlDescription";
    public static final String COLUMN_IMAGE_NAME = "image";
    private static final long serialVersionUID = 8674736477393323691L;


    @Id
    @Size(max = 50)
    @Column(name = COLUMN_PRODUCTLINE_NAME, nullable = false, length = 50)
    private String productLine;

    @Size(max = 4000)
    @Column(name = COLUMN_TEXTDESCRIPTION_NAME, length = 4000)
    private String textDescription;

    @Column(name = COLUMN_HTMLDESCRIPTION_NAME, columnDefinition = "text")
    private String htmlDescription;

    @Column(name = COLUMN_IMAGE_NAME)
    private byte[] image;

    @OneToMany(mappedBy = "productLine")
    @JsonIgnore
    private Set<Product> products = new LinkedHashSet<>();

}