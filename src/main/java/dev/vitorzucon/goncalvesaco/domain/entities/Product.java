package dev.vitorzucon.goncalvesaco.domain.entities;

import java.math.BigDecimal;

public record Product(
                String name,
                String description,
                String imagePath,
                BigDecimal price) {

}
