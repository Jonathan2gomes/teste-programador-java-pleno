package org.acme.usecase.product;

import org.acme.domain.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CreateProductUseCaseTest {


    @Mock
    private ProductGateway productGateway;

    private CreateProductUseCase createProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createProductUseCase = new CreateProductUseCase(productGateway);
    }

    @Test
    void execute_ShouldCreateProductWithCorrectDescription() {
        // Arrange
        ProductInput productInput = new ProductInput("Description", "Unit", 10.0);

        // Act
        createProductUseCase.execute(productInput);

        // Assert
        verify(productGateway, times(1)).create(
                argThat(product -> product.getDescricao().equals("Description"))
        );
    }

    @Test
    void execute_ShouldCreateProductWithCorrectUnitAndValue() {
        // Arrange
        ProductInput productInput = new ProductInput("Description", "Unit", 10.0);

        // Act
        createProductUseCase.execute(productInput);

        // Assert
        verify(productGateway, times(1)).create(
                argThat(product -> product.getUnidade().equals("Unit")
                        && product.getValor() == 10.0)
        );
    }
}