package dev.vitorzucon.goncalvesaco.application.gateways;

import java.util.List;

import dev.vitorzucon.goncalvesaco.domain.entities.Product;

public interface IProductGateway {

    Product create(Product product);

    Product findProduct(Long id);

    List<Product> findAllProducts();

    void deleteProduct(Long id);

    void updateProduct(Product product);

}
