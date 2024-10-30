package dev.vitorzucon.goncalvesaco.infrastructure.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import dev.vitorzucon.goncalvesaco.application.usecases.ProductInteractor;
import dev.vitorzucon.goncalvesaco.domain.entities.Product;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTOMapper;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTORequest;
import dev.vitorzucon.goncalvesaco.infrastructure.controllers.dtos.product.ProductDTOResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductInteractor productInteractor;

    @Mock
    private ProductDTOMapper productDTOMapper;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProduct() throws Exception {
        // Arrange
        ProductDTORequest request = new ProductDTORequest("New Product", "Description", "imagePath.jpg",
                new BigDecimal("10.0"));
        Product product = new Product(null, "New Product", "Description", "imagePath.jpg", new BigDecimal("10.0"));
        Product savedProduct = new Product(1L, "New Product", "Description", "imagePath.jpg", new BigDecimal("10.0"));
        ProductDTOResponse response = new ProductDTOResponse(1L, "New Product", "Description", "imagePath.jpg",
                new BigDecimal("10.0"));

        when(productDTOMapper.toDomain(request)).thenReturn(product);
        when(productInteractor.createProduct(product)).thenReturn(savedProduct);
        when(productDTOMapper.toResponse(savedProduct)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/products")
                .contentType("application/json")
                .content(
                        "{\"name\":\"New Product\",\"description\":\"Description\",\"imagePath\":\"imagePath.jpg\",\"price\":10.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Product"));
    }

    @Test
    void testFindProduct() throws Exception {
        // Arrange
        Long id = 1L;
        Product product = new Product(1L, "Test Product", "Test Description", "imagePath.jpg", new BigDecimal("15.0"));
        ProductDTOResponse response = new ProductDTOResponse(1L, "Test Product", "Test Description", "imagePath.jpg",
                new BigDecimal("15.0"));

        when(productInteractor.findProduct(id)).thenReturn(product);
        when(productDTOMapper.toResponse(product)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testFindAllProducts() throws Exception {
        // Arrange
        Product product1 = new Product(1L, "Product One", "Description One", "imagePath1.jpg", new BigDecimal("20.0"));
        Product product2 = new Product(2L, "Product Two", "Description Two", "imagePath2.jpg", new BigDecimal("30.0"));
        ProductDTOResponse response1 = new ProductDTOResponse(1L, "Product One", "Description One", "imagePath1.jpg",
                new BigDecimal("20.0"));
        ProductDTOResponse response2 = new ProductDTOResponse(2L, "Product Two", "Description Two", "imagePath2.jpg",
                new BigDecimal("30.0"));
        List<Product> products = Arrays.asList(product1, product2);

        when(productInteractor.findAllProducts()).thenReturn(products);
        when(productDTOMapper.toResponse(product1)).thenReturn(response1);
        when(productDTOMapper.toResponse(product2)).thenReturn(response2);

        // Act & Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Product One"))
                .andExpect(jsonPath("$[1].name").value("Product Two"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        // Cenário 1: Produto encontrado e deletado com sucesso
        Long id = 1L;
        Product product = new Product(id, "Product", "Description", "imagePath.jpg", new BigDecimal("20.0"));

        // Simula a busca do produto e a deleção sem erros
        when(productInteractor.findProduct(id)).thenReturn(product);
        doNothing().when(productInteractor).deleteProduct(id);

        mockMvc.perform(delete("/products/{id}", id))
                .andExpect(status().isNoContent());

        // Cenário 2: Produto não encontrado
        Long nonExistentId = 99L;
        when(productInteractor.findProduct(nonExistentId)).thenReturn(null);

        mockMvc.perform(delete("/products/{id}", nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("product not exists"));

        // Cenário 3: Erro no servidor durante a deleção
        Long errorId = 2L;
        Product errorProduct = new Product(errorId, "Error Product", "Error Description", "errorPath.jpg",
                new BigDecimal("30.0"));

        when(productInteractor.findProduct(errorId)).thenReturn(errorProduct);
        doThrow(new RuntimeException("Delete error")).when(productInteractor).deleteProduct(errorId);

        mockMvc.perform(delete("/products/{id}", errorId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        // Cenário 1: Produto encontrado e atualizado com sucesso
        Long id = 1L;
        ProductDTORequest request = new ProductDTORequest("Updated Product", "Updated Description", "newImagePath.jpg",
                new BigDecimal("25.0"));
        Product existingProduct = new Product(id, "Old Product", "Old Description", "oldImagePath.jpg",
                new BigDecimal("20.0"));
        Product updatedProduct = new Product(id, "Updated Product", "Updated Description", "newImagePath.jpg",
                new BigDecimal("25.0"));
        ProductDTOResponse response = new ProductDTOResponse(id, "Updated Product", "Updated Description",
                "newImagePath.jpg", new BigDecimal("25.0"));

        when(productInteractor.findProduct(id)).thenReturn(existingProduct);
        when(productDTOMapper.toDomain(request)).thenReturn(updatedProduct);
        when(productDTOMapper.toResponse(updatedProduct)).thenReturn(response);

        mockMvc.perform(put("/products/update/{id}", id)
                .contentType("application/json")
                .content(
                        "{\"name\":\"Updated Product\",\"description\":\"Updated Description\",\"imagePath\":\"newImagePath.jpg\",\"price\":25.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"));

        // Cenário 2: Produto não encontrado
        Long nonExistentId = 99L;
        when(productInteractor.findProduct(nonExistentId)).thenReturn(null);

        mockMvc.perform(put("/products/update/{id}", nonExistentId)
                .contentType("application/json")
                .content(
                        "{\"name\":\"Updated Product\",\"description\":\"Updated Description\",\"imagePath\":\"newImagePath.jpg\",\"price\":25.0}"))
                .andExpect(status().isNotFound());

        // Cenário 3: Erro no servidor durante a atualização
        Long errorId = 2L;
        Product errorProduct = new Product(errorId, "Error Product", "Error Description", "errorImagePath.jpg",
                new BigDecimal("30.0"));
        ProductDTORequest errorRequest = new ProductDTORequest("Error Product", "Error Description",
                "errorImagePath.jpg", new BigDecimal("30.0"));

        when(productInteractor.findProduct(errorId)).thenReturn(errorProduct);
        when(productDTOMapper.toDomain(errorRequest)).thenReturn(errorProduct);
        doThrow(new RuntimeException("Update error")).when(productInteractor).updateProduct(errorProduct);

        mockMvc.perform(put("/products/update/{id}", errorId)
                .contentType("application/json")
                .content(
                        "{\"name\":\"Error Product\",\"description\":\"Error Description\",\"imagePath\":\"errorImagePath.jpg\",\"price\":30.0}"))
                .andExpect(status().isInternalServerError());
    }

}
