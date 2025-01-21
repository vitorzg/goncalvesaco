package br.com.goncalvesaco.ecommerce.infra.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.Product;
import br.com.goncalvesaco.ecommerce.infra.persistence.models.ProductEntity;

public class ProductEntityMapper {

    ProductEntity toEntity(Product productDomainObj){
        return new ProductEntity(productDomainObj.id(),productDomainObj.name(),productDomainObj.description(),productDomainObj.price(),productDomainObj.image_url());
    }

    Product toDomain(ProductEntity productEntityObj){
        return new Product(productEntityObj.getId(), productEntityObj.getName(), productEntityObj.getDescription(), productEntityObj.getPrice(), productEntityObj.getImage_url());
    }
}
