package org.acme.infrastructure.gateway;

import jakarta.transaction.Transactional;
import org.acme.domain.exception.ProductNotFoundException;
import org.acme.domain.model.Product;
import org.acme.infrastructure.config.db.repository.ProductRepository;
import org.acme.infrastructure.config.db.schema.ProductSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
class ProductDatabaseGatewayTest {

    @Mock
    private ProductRepository productRepository;

    private ProductDatabaseGateway productDatabaseGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDatabaseGateway = new ProductDatabaseGateway(productRepository);
    }

    @Test
    void update_WhenProductDoesNotExist_ShouldThrowProductNotFoundException() {
        // Arrange
        Long id = 1L;
        Product product = new Product(1L, "Updated Product", "Unit", 20.0);

        when(productRepository.findByIdOptional(id)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productDatabaseGateway.update(id, product));
    }

    @Test
    void delete_WhenProductExists_ShouldDeleteProduct() {
        // Arrange
        Long id = 1L;
        ProductSchema existingProductSchema = new ProductSchema(id, "Existing Product", "Unit", 10.0);

        when(productRepository.findByIdOptional(id)).thenReturn(java.util.Optional.of(existingProductSchema));

        // Act
        productDatabaseGateway.delete(id);

        // Assert
        verify(productRepository).deleteById(id);
    }

    @Test
    void delete_WhenProductDoesNotExist_ShouldThrowProductNotFoundException() {
        // Arrange
        Long id = 1L;

        when(productRepository.findByIdOptional(id)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productDatabaseGateway.delete(id));
    }

    @Test
    void findById_WhenProductExists_ShouldReturnProduct() {
        // Arrange
        Long id = 1L;
        ProductSchema productSchema = new ProductSchema(id, "Test Product", "Unit", 10.0);

        when(productRepository.findByIdOptional(id)).thenReturn(java.util.Optional.of(productSchema));

        // Act
        Product product = productDatabaseGateway.findById(id);

        // Assert
        assertEquals(productSchema.getId(), product.getId());
        assertEquals(productSchema.getDescricao(), product.getDescricao());
        assertEquals(productSchema.getUnidade(), product.getUnidade());
        assertEquals(productSchema.getValor(), product.getValor());
    }

    @Test
    void findById_WhenProductDoesNotExist_ShouldThrowProductNotFoundException() {
        // Arrange
        Long id = 1L;

        when(productRepository.findByIdOptional(id)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productDatabaseGateway.findById(id));
    }

    @Test
    void findByIds_WhenProductIdsExist_ShouldReturnListOfProducts() {
        // Arrange
        List<Long> productIds = Arrays.asList(1L, 2L);
        List<ProductSchema> productSchemas = Arrays.asList(
                new ProductSchema(1L, "Product 1", "Unit", 10.0),
                new ProductSchema(2L, "Product 2", "Unit", 20.0)
        );

        when(productRepository.list("id in ?1", productIds)).thenReturn(productSchemas);

        // Act
        List<Product> products = productDatabaseGateway.findByIds(productIds);

        // Assert
        assertEquals(productIds.size(), products.size());
        for (int i = 0; i < productIds.size(); i++) {
            assertEquals(productIds.get(i), products.get(i).getId());
            assertEquals(productSchemas.get(i).getDescricao(), products.get(i).getDescricao());
            assertEquals(productSchemas.get(i).getUnidade(), products.get(i).getUnidade());
            assertEquals(productSchemas.get(i).getValor(), products.get(i).getValor());
        }
    }

    @Test
    void findByIds_WhenProductIdsDoNotExist_ShouldReturnEmptyListOfProducts() {
        // Arrange
        List<Long> productIds = Collections.singletonList(1L);

        when(productRepository.list("id in ?1", productIds)).thenReturn(Collections.emptyList());

        // Act
        List<Product> products = productDatabaseGateway.findByIds(productIds);

        // Assert
        assertTrue(products.isEmpty());
    }

    @Test
    void findAllIds_ShouldReturnListOfProductIds() {
        // Arrange
        List<ProductSchema> productSchemas = Arrays.asList(
                new ProductSchema(1L, "Product 1", "Unit", 10.0),
                new ProductSchema(2L, "Product 2", "Unit", 20.0)
        );

        // Act
        List<Long> productIds = productDatabaseGateway.findAllIds(productSchemas);

        // Assert
        assertEquals(productSchemas.size(), productIds.size());
        for (int i = 0; i < productSchemas.size(); i++) {
            assertEquals(productSchemas.get(i).getId(), productIds.get(i));
        }
    }
}
