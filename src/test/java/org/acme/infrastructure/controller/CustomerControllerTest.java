package org.acme.infrastructure.controller;

import jakarta.ws.rs.core.Response;
import org.acme.usecase.OutputPage;
import org.acme.usecase.customer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private GetCustomerUseCase getCustomerUseCase;
    @Mock
    private CreateCustomerUseCase createCustomerUseCase;
    @Mock
    private UpdateCustomerUseCase updateCustomerUseCase;
    @Mock
    private DeleteCustomerUseCase deleteCustomerUseCase;

    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(
                getCustomerUseCase,
                createCustomerUseCase,
                updateCustomerUseCase,
                deleteCustomerUseCase
        );
    }

    @Test
    void getCustomer_ShouldReturnOkResponse() {
        // Arrange
        Long customerId = 1L;

        CustomerOutput customerOutput = new CustomerOutput(1L, "Customer", "12345678900","259.361.110-32", "customer@email.com");

        when(getCustomerUseCase.execute(customerId)).thenReturn(customerOutput);

        // Act
        Response response = customerController.getCustomer(customerId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(customerOutput, response.getEntity());
    }

    @Test
    void getCustomers_ShouldReturnOkResponse() {
        // Arrange
        int page = 1;
        int size = 10;

        CustomerOutput customerOutput = new CustomerOutput(1L, "Customer", "12345678900","259.361.110-32", "customer@email.com");
        OutputPage outputPage = new OutputPage("0", "1", "1", List.of(customerOutput));

        when(getCustomerUseCase.execute(page, size)).thenReturn(outputPage);

        // Act
        Response response = customerController.getCustomers(page, size);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(outputPage, response.getEntity());
    }

    @Test
    void create_ShouldReturnCreatedResponse() {
        // Arrange
        CustomerInput input = new CustomerInput( "Customer", "12345678900","259.361.110-32", "customer@email.com");

        // Act
        Response response = customerController.create(input);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void update_ShouldReturnOkResponse() {
        // Arrange
        Long customerId = 1L;
        CustomerInput input = new CustomerInput( "Customer", "12345678900","259.361.110-32", "customer@email.com");

        // Act
        Response response = customerController.update(customerId, input);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void delete_ShouldReturnOkResponse() {
        // Arrange
        Long customerId = 1L;

        // Act
        Response response = customerController.delete(customerId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
