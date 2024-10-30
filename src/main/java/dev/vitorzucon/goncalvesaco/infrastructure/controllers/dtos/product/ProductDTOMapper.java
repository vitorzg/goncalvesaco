package dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product;

import dev.vitorzucon.goncalvesaco.domain.entities.Product;

public class ProductDTOMapper {

    public ProductDTOResponse toResponse(Product product) {
        return new ProductDTOResponse(
                product.id(),
                product.name(),
                product.description(),
                product.imagePath(),
                product.price());
    }

    public Product toDomain(ProductDTORequest request) {
        return new Product(
                null,
                request.name(),
                request.description(),
                request.imagePath(),
                request.price());
    }
}
