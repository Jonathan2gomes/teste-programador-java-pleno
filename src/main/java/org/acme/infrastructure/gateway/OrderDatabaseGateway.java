package org.acme.infrastructure.gateway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.exception.OrderNotFoundException;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.model.Order;
import org.acme.infrastructure.config.db.repository.OrderRepository;
import org.acme.infrastructure.config.db.schema.OrderSchema;
import org.acme.infrastructure.mapper.OrderMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class OrderDatabaseGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final ProductDatabaseGateway productGateway;

    public OrderDatabaseGateway(OrderRepository orderRepository, ProductDatabaseGateway productGateway){
        this.orderRepository = orderRepository;
        this.productGateway = productGateway;
    };
    @Override
    public void create(Order order) {
        orderRepository.persist(new OrderSchema(
                null,
                LocalDate.now(),
                order.getDescricao(),
                order.getListaProdutos(),
                calculateTotalValue(order)
        ));
    }

    @Override
    public List<Order> getAll() {
        return OrderMapper.toOrder(orderRepository.findAll().list());
    }

    @Override
    public Order findById(Long id) {
        OrderSchema orderSchema = orderRepository.findByIdOptional(id).orElseThrow(() -> new OrderNotFoundException("Pedido não foi encontrado"));
        return new Order(
                orderSchema.getId(),
                orderSchema.getDataEmissao(),
                orderSchema.getDescricao(),
                orderSchema.getListaProdutos(),
                orderSchema.getValorTotal()
        );
    }

    @Override
    public void update(Order order, Long id) {
        OrderSchema orderSchema = orderRepository.findByIdOptional(id).orElseThrow(() -> new OrderNotFoundException("Pedido não foi encontrado"));
        orderSchema.setDescricao(order.getDescricao());
        orderSchema.setListaProdutos(order.getListaProdutos());
        orderSchema.setValorTotal(calculateTotalValue(order));
        orderRepository.persist(orderSchema);
    }

    @Override
    public void delete(Long id) {
        orderRepository.findByIdOptional(id).orElseThrow(() -> new OrderNotFoundException("Pedido não foi encontrado"));
        orderRepository.deleteById(id);
    }

    private Double calculateTotalValue(Order order){

        List<Double> totalValues = new ArrayList<>();

        order.getListaProdutos().forEach(id ->
                totalValues.add(productGateway.findById(id).getValor()));

        return  totalValues.stream().mapToDouble(f -> f).sum();
    }
}
