package dev.vitorzucon.goncalvesaco.infrastructure.gateways;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dev.vitorzucon.goncalvesaco.domain.entities.Product;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.ProductEntity;
import dev.vitorzucon.goncalvesaco.infrastructure.persistence.ProductRepository;

class ProductRepositoryGatewayTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private ProductRepositoryGateway productRepositoryGateway;

    private Product product;
    private ProductEntity productEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Product1", "Description1", "image/path1", BigDecimal.valueOf(10.0));
        productEntity = new ProductEntity("Product1", "Description1", "image/path1", BigDecimal.valueOf(10.0));
    }

    @Test
    void testCreateProduct() {
        // Arrange
        when(productEntityMapper.toEntity(product)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productEntityMapper.toDomain(productEntity)).thenReturn(product);

        // Act
        Product result = productRepositoryGateway.create(product);

        // Assert
        assertNotNull(result);
        assertEquals(product, result);
        verify(productEntityMapper, times(1)).toEntity(product);
        verify(productRepository, times(1)).save(productEntity);
        verify(productEntityMapper, times(1)).toDomain(productEntity);
    }

    @Test
    void testFindProduct() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(productEntityMapper.toDomain(productEntity)).thenReturn(product);

        // Act
        Product result = productRepositoryGateway.findProduct(productId);

        // Assert
        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(productId);
        verify(productEntityMapper, times(1)).toDomain(productEntity);
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        List<ProductEntity> productEntities = Arrays.asList(productEntity, productEntity);
        when(productRepository.findAll()).thenReturn(productEntities);
        when(productEntityMapper.toDomain(any(ProductEntity.class))).thenReturn(product);

        // Act
        List<Product> result = productRepositoryGateway.findAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
        verify(productEntityMapper, times(2)).toDomain(any(ProductEntity.class));
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productRepositoryGateway.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        when(productRepository.findById(product.id())).thenReturn(Optional.of(productEntity));

        // Act
        productRepositoryGateway.updateProduct(product);

        // Assert
        verify(productRepository, times(1)).findById(product.id());
        verify(productRepository, times(1)).save(productEntity);
        assertEquals(product.name(), productEntity.getName());
        assertEquals(product.description(), productEntity.getDescription());
        assertEquals(product.imagePath(), productEntity.getImagePath());
        assertEquals(product.price(), productEntity.getPrice());
    }
}
