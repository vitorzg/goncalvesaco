package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import java.util.List;

import dev.vitorzucon.goncalvesaco.application.gateways.IProductGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.Product;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.ProductEntity;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.ProductRepository;
import jakarta.transaction.Transactional;

public class ProductRepositoryGateway implements IProductGateway {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        this.productRepository = productRepository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public Product create(Product product) {
        ProductEntity productEntity = productEntityMapper.toEntity(product);
        ProductEntity savedProduct = productRepository.save(productEntity);
        return productEntityMapper.toDomain(savedProduct);
    }

    @Override
    public Product findProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        return productEntityMapper.toDomain(productEntity);
    }

    @Override
    public List<Product> findAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        ProductEntity productEntity = productRepository.findById(product.id()).get();
        productEntity.setDescription(product.description());
        productEntity.setImagePath(product.imagePath());
        productEntity.setName(product.name());
        productEntity.setPrice(product.price());
        productRepository.save(productEntity);
    }

}
