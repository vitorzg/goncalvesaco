package dev.vitorzucon.goncalvesaco.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.vitorzucon.goncalvesaco.application.gateways.IProductGateway;
import dev.vitorzucon.goncalvesaco.application.usecases.ProductInteractor;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.gateways.ProductEntityMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.gateways.ProductRepositoryGateway;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.ProductRepository;

@Configuration
public class ProductConfig {

    @Bean
    ProductInteractor productCase(IProductGateway productGateway) {
        return new ProductInteractor(productGateway);
    }

    @Bean
    IProductGateway productGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        return new ProductRepositoryGateway(productRepository, productEntityMapper);
    }

    @Bean
    ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }

    @Bean
    ProductDTOMapper productDTOMapper() {
        return new ProductDTOMapper();
    }
}
