package br.com.goncalvesaco.ecommerce.core.entities;

public record Product(
        int id,
        String name,
        String description,
        float price,
        String image_url
) {
}
