package br.com.goncalvesaco.ecommerce.core.usecases;

import br.com.goncalvesaco.ecommerce.core.entities.Product;
import br.com.goncalvesaco.ecommerce.core.gateways.ProductRepositoryGatewayCore;

import java.util.List;
import java.util.Map;

public class ProductUseCase {

    private final ProductRepositoryGatewayCore productRepositoryGatewayCore;

    public ProductUseCase(ProductRepositoryGatewayCore productRepositoryGatewayCore) {
        this.productRepositoryGatewayCore = productRepositoryGatewayCore;
    }

    public Product findProduct(String id){
        return productRepositoryGatewayCore.findProduct(id);
    }

    public List<Product> findAllProducts(){
        return productRepositoryGatewayCore.findAllProducts();
    }

    public Product addProduct(Product newProduct){
        return productRepositoryGatewayCore.addProduct(newProduct);
    }

    public void deleteProduct(String id){
        productRepositoryGatewayCore.deleteProduct(id);
    }

    public Product updateProduct(String id, Map<String, Object> updates){
        return productRepositoryGatewayCore.updateProduct(id, updates);
    }

}
