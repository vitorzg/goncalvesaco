package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product;

import java.math.BigDecimal;

public record ProductDTOResponse(
        Long id,
        String name,
        String description,
        String imagePath,
        BigDecimal price) {

}
