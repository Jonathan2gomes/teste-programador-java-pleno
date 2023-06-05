package org.acme.infrastructure.gateway;

import org.acme.domain.exception.OrderNotFoundException;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.model.Order;
import org.acme.domain.model.Product;
import org.acme.infrastructure.config.db.repository.OrderRepository;
import org.acme.infrastructure.config.db.schema.CustomerSchema;
import org.acme.infrastructure.config.db.schema.OrderSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderDatabaseGatewayTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductDatabaseGateway productGateway;
    @Mock
    private CustomerDatabaseGateway customerGateway;

    private OrderGateway orderDatabaseGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderDatabaseGateway = new OrderDatabaseGateway(orderRepository, productGateway, customerGateway);
    }

    @Test
    void findById_WhenOrderExists_ShouldReturnOrder() {
        // Arrange
        Long orderId = 1L;
        CustomerSchema customerSchema = new CustomerSchema(1L, "Test Customer", "989.309.220-58", "123456789", "email@email.com");
        OrderSchema orderSchema = new OrderSchema(orderId, LocalDate.now(), "Test Order", Collections.emptyList(), 100.0, customerSchema);

        when(orderRepository.findByIdOptional(orderId)).thenReturn(java.util.Optional.of(orderSchema));
        when(productGateway.findAllIds(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Act
        Order order = orderDatabaseGateway.findById(orderId);

        // Assert
        assertEquals(orderSchema.getId(), order.getId());
        assertEquals(orderSchema.getDataEmissao(), order.getDataEmissao());
        assertEquals(orderSchema.getDescricao(), order.getDescricao());
        assertEquals(orderSchema.getValorTotal(), order.getValorTotal());
    }

    @Test
    void findById_WhenOrderDoesNotExist_ShouldThrowOrderNotFoundException() {
        // Arrange
        Long orderId = 1L;

        when(orderRepository.findByIdOptional(orderId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(OrderNotFoundException.class, () -> orderDatabaseGateway.findById(orderId));
    }

    @Test
    void update_WhenOrderExists_ShouldUpdateOrderInRepository() {
        // Arrange
        Long orderId = 1L;
        CustomerSchema customerSchema = new CustomerSchema(1L, "Test Customer", "989.309.220-58", "123456789", "email@email.com");
        Order order = new Order(orderId, LocalDate.now(), "Test Order", Collections.singletonList(1L), 200.0, 1L);

        OrderSchema orderSchema = new OrderSchema(orderId, LocalDate.now(), "Test Order", Collections.emptyList(), 100.0, customerSchema);

        when(orderRepository.findByIdOptional(orderId)).thenReturn(java.util.Optional.of(orderSchema));
        when(productGateway.findById(1L)).thenReturn(new Product(1L, "Test Product", "Unit", 10.0));

        // Act
        orderDatabaseGateway.update(order, orderId);

        // Assert
        verify(orderRepository).persist(orderSchema);
    }

    @Test
    void update_WhenOrderDoesNotExist_ShouldThrowOrderNotFoundException() {
        // Arrange
        Long orderId = 1L;
        CustomerSchema customerSchema = new CustomerSchema(1L, "Test Customer", "989.309.220-58", "123456789", "email@email.com");
        Order order = new Order(orderId, LocalDate.now(), "Test Order", Collections.singletonList(1L), 200.0, 1L);

        when(orderRepository.findByIdOptional(orderId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(OrderNotFoundException.class, () -> orderDatabaseGateway.update(order, orderId));
    }

    @Test
    void delete_WhenOrderExists_ShouldDeleteOrderFromRepository() {
        // Arrange
        Long orderId = 1L;
        CustomerSchema customerSchema = new CustomerSchema(1L, "Test Customer", "989.309.220-58", "123456789", "email@email.com");
        OrderSchema orderSchema = new OrderSchema(orderId, LocalDate.now(), "Test Order", Collections.emptyList(), 100.0, customerSchema);

        when(orderRepository.findByIdOptional(orderId)).thenReturn(java.util.Optional.of(orderSchema));

        // Act
        orderDatabaseGateway.delete(orderId);

        // Assert
        verify(orderRepository).deleteById(orderId);
    }

    @Test
    void delete_WhenOrderDoesNotExist_ShouldThrowOrderNotFoundException() {
        // Arrange
        Long orderId = 1L;

        when(orderRepository.findByIdOptional(orderId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(OrderNotFoundException.class, () -> orderDatabaseGateway.delete(orderId));
    }
}
