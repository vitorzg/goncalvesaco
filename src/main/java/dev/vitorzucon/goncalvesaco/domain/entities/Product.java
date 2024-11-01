package dev.vitorzucon.goncalvesaco.domain.entities;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        String imagePath,
        BigDecimal price) {

}
