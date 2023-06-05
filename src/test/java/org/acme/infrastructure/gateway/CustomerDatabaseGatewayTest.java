package org.acme.infrastructure.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.domain.exception.CustomerNotFoundException;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.domain.model.Customer;
import org.acme.infrastructure.config.db.repository.CustomerRepository;
import org.acme.infrastructure.config.db.schema.CustomerSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerDatabaseGatewayTest {


    @Mock
    private CustomerRepository customerRepository;

    private CustomerGateway customerGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerGateway = new CustomerDatabaseGateway(customerRepository);
    }

    @Test
    void create_ShouldPersistCustomerSchema() {
        // Arrange
        Customer customer = new Customer(null, "John Doe", "123456789", "1234567890", "john@example.com");

        // Act
        customerGateway.create(customer);

        // Assert
        verify(customerRepository, times(1)).persist(any(CustomerSchema.class));
    }

    @Test
    void findByCpf_WithExistingCpf_ShouldReturnOptionalCustomerSchema() {
        // Arrange
        String cpf = "123456789";
        CustomerSchema expectedCustomer = new CustomerSchema(1L, "John Doe", cpf, "1234567890", "john@example.com");

        when(customerRepository.findByCpf(cpf)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Optional<CustomerSchema> actualCustomer = customerGateway.findByCpf(cpf);

        // Assert
        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedCustomer, actualCustomer.get());
    }

    @Test
    void findByCpf_WithNonExistingCpf_ShouldReturnEmptyOptional() {
        // Arrange
        String cpf = "123456789";

        when(customerRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act
        Optional<CustomerSchema> actualCustomer = customerGateway.findByCpf(cpf);

        // Assert
        assertTrue(actualCustomer.isEmpty());
    }



    @Test
    void update_WithExistingCustomerId_ShouldPersistUpdatedCustomerSchema() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John Doe", "123456789", "1234567890", "john@example.com");
        CustomerSchema existingCustomer = new CustomerSchema(customerId, "John", "123456789", "1234567890", "john@example.com");

        when(customerRepository.findByIdOptional(customerId)).thenReturn(Optional.of(existingCustomer));

        // Act
        customerGateway.update(customerId, customer);

        // Assert
        verify(customerRepository, times(1)).persist(any(CustomerSchema.class));
    }

    @Test
    void update_WithNonExistingCustomerId_ShouldThrowCustomerNotFoundException() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John Doe", "123456789", "1234567890", "john@example.com");

        when(customerRepository.findByIdOptional(customerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomerNotFoundException.class, () -> customerGateway.update(customerId, customer));
    }

    @Test
    void delete_WithExistingCustomerId_ShouldDeleteCustomer() {
        // Arrange
        Long customerId = 1L;

        // Act
        customerGateway.delete(customerId);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void findById_WithExistingCustomerId_ShouldReturnCustomer() {
        // Arrange
        Long customerId = 1L;
        CustomerSchema customerSchema = new CustomerSchema(customerId, "John Doe", "123456789", "1234567890", "john@example.com");

        when(customerRepository.findByIdOptional(customerId)).thenReturn(Optional.of(customerSchema));

        // Act
        Customer actualCustomer = customerGateway.findById(customerId);

        // Assert
        assertNotNull(actualCustomer);
        assertEquals(customerId, actualCustomer.getId());
        assertEquals(customerSchema.getNome(), actualCustomer.getNome());
        assertEquals(customerSchema.getEmail(), actualCustomer.getEmail());
    }

    @Test
    void findById_WithNonExistingCustomerId_ShouldThrowCustomerNotFoundException() {
        // Arrange
        Long customerId = 1L;

        when(customerRepository.findByIdOptional(customerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomerNotFoundException.class, () -> customerGateway.findById(customerId));
    }

    // Helper method to mock the PanacheQuery
    private <T> org.mockito.stubbing.Answer<PanacheQuery<T>> mockPanacheQuery(List<T> list) {
        return invocation -> {
            @SuppressWarnings("unchecked")
            PanacheQuery<T> panacheQuery = mock(PanacheQuery.class);
            when(panacheQuery.list()).thenReturn(list);
            return panacheQuery;
        };
    }


}