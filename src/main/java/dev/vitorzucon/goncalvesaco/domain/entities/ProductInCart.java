package dev.vitorzucon.goncalvesaco.domain.entities;

import java.math.BigDecimal;

public record ProductInCart(
        Long id,
        String name,
        BigDecimal price,
        Double rebate,
        int quantity) {

}
