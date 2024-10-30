package dev.vitorzucon.goncalvesaco.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.vitorzucon.goncalvesaco.application.gateways.IProductGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.Product;

class ProductInteractorTest {

    @Mock
    private IProductGateway productGateway;

    @InjectMocks
    private ProductInteractor productInteractor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product(1L, "product", "description", "imagem", new BigDecimal(10.0));
        when(productGateway.create(product)).thenReturn(product);

        // Act
        Product result = productInteractor.createProduct(product);

        // Assert
        assertNotNull(result);
        verify(productGateway, times(1)).create(product);
    }

    @Test
    void testFindProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product(1L, "product", "description", "imagem", new BigDecimal(10.0));
        when(productGateway.findProduct(productId)).thenReturn(product);

        // Act
        Product result = productInteractor.findProduct(productId);

        // Assert
        assertNotNull(result);
        verify(productGateway, times(1)).findProduct(productId);
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1L, "product", "description", "imagem", new BigDecimal(10.0)),
                new Product(2L, "product2", "description2", "imagem2", new BigDecimal(10.0)));
        when(productGateway.findAllProducts()).thenReturn(products);

        // Act
        List<Product> result = productInteractor.findAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productGateway, times(1)).findAllProducts();
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productInteractor.deleteProduct(productId);

        // Assert
        verify(productGateway, times(1)).deleteProduct(productId);
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product(1L, "product", "description", "imagem", new BigDecimal(10.0));

        // Act
        productInteractor.updateProduct(product);

        // Assert
        verify(productGateway, times(1)).updateProduct(product);
    }

}
