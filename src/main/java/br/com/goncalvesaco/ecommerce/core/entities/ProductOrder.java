package br.com.goncalvesaco.ecommerce.core.entities;

public record ProductOrder(
        String id,
        String name,
        float discount,
        float price
) {
}
