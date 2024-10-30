package dev.vitorzucon.goncalvesaco.infrastructure.persistence;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTS")
@Data
@NoArgsConstructor
public class ProductEntity {

    public ProductEntity(String name, String description, String imagePath, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imagePath;
    private BigDecimal price;

}
