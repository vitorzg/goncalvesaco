package dev.vitorzucon.goncalvesaco.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vitorzucon.goncalvesaco.application.usecases.ProductInteractor;
import dev.vitorzucon.goncalvesaco.domain.entities.Product;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTORequest;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTOResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductInteractor productInteractor;
    private final ProductDTOMapper productDTOMapper;

    public ProductController(ProductInteractor productInteractor, ProductDTOMapper productDTOMapper) {
        this.productInteractor = productInteractor;
        this.productDTOMapper = productDTOMapper;
    }

    @PostMapping("")
    public ResponseEntity<ProductDTOResponse> create(@RequestBody ProductDTORequest obj) {
        Product newProduct = productDTOMapper.toDomain(obj);
        Product savedProduct = productInteractor.createProduct(newProduct);
        ProductDTOResponse response = productDTOMapper.toResponse(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> findProduct(@PathVariable Long id) {
        Product product = productInteractor.findProduct(id);
        if (product != null) {
            ProductDTOResponse response = productDTOMapper.toResponse(product);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTOResponse>> findAllProduct() {
        List<Product> products = productInteractor.findAllProducts();
        List<ProductDTOResponse> response = products.stream()
                .map(productDTOMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        Product product = productInteractor.findProduct(id);
        if (product != null) {
            try {
                productInteractor.deleteProduct(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not exists");
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(@PathVariable Long id,
            @RequestBody ProductDTORequest requestObj) {
        Product product = productInteractor.findProduct(id);
        if (product != null) {
            try {
                Product updatedProduct = productDTOMapper.toDomain(requestObj);
                productInteractor.updateProduct(updatedProduct);
                ProductDTOResponse response = productDTOMapper.toResponse(updatedProduct);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productDTOMapper.toResponse(product));
        }
    }

}
