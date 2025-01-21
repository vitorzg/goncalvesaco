package br.com.goncalvesaco.ecommerce.core.gateways;

import br.com.goncalvesaco.ecommerce.core.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepositoryGatewayCore {

    Product findProduct(String id);
    List<Product> findAllProducts();
    Product addProduct(Product newProduct);
    void deleteProduct(String id);
    Product updateProduct(String id, Map<String, Object> updates);
}
