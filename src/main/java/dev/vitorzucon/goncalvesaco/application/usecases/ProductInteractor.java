package dev.vitorzucon.goncalvesaco.application.usecases;

import java.util.List;

import dev.vitorzucon.goncalvesaco.application.gateways.IProductGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.Product;

public class ProductInteractor {

    private final IProductGateway productGateway;

    public ProductInteractor(IProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product createProduct(Product product){
        return productGateway.create(product);
    }

    public Product findProduct(Long id) {
        return productGateway.findProduct(id);
    }

    public List<Product> findAllProducts(){
        return productGateway.findAllProducts();
    }

    public void deleteProduct(Long id){
        productGateway.deleteProduct(id);
    }

    public void updateProduct(Product product){
        productGateway.updateProduct(product);
    }

}
