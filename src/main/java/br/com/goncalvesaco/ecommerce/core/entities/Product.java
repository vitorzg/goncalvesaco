package br.com.goncalvesaco.ecommerce.core.entities;

public record Product(
        String name,
        String description,
        float price,
        String image_url
) {
}
