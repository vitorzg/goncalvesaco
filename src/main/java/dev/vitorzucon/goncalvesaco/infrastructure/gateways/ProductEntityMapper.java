package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import dev.vitorzucon.goncalvesaco.domain.entities.Product;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.ProductEntity;

public class ProductEntityMapper {

    public ProductEntity toEntity(Product productDomainObj) {
        return new ProductEntity(
                productDomainObj.name(),
                productDomainObj.description(),
                productDomainObj.imagePath(),
                productDomainObj.price());
    }

    public Product toDomain(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImagePath(),
                productEntity.getPrice());
    }
}
