package org.acme.infrastructure.gateway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.model.Order;
import org.acme.infrastructure.config.db.repository.OrderRepository;
import org.acme.infrastructure.config.db.schema.OrderSchema;
import org.acme.infrastructure.mapper.OrderMapper;

import java.util.List;

@ApplicationScoped
@Transactional
public class OrderDatabaseGateway implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderDatabaseGateway(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    };
    @Override
    public void create(Order order) {
        orderRepository.persist(new OrderSchema(
                null,
                order.getDataEmissao(),
                order.getDescricao(),
                order.getListaProdutos(),
                order.getValorTotal()
        ));
    }

    @Override
    public List<Order> getAll() {
        return OrderMapper.toOrder(orderRepository.findAll().list());
    }
}
