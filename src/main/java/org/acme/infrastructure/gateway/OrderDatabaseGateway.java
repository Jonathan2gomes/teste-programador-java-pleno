package org.acme.infrastructure.gateway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.exception.OrderNotFoundException;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.model.Order;
import org.acme.infrastructure.config.db.repository.OrderRepository;
import org.acme.infrastructure.config.db.schema.OrderSchema;
import org.acme.infrastructure.mapper.CustomerMapper;
import org.acme.infrastructure.mapper.OrderMapper;
import org.acme.infrastructure.mapper.ProductMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class OrderDatabaseGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final ProductDatabaseGateway productGateway;
    private final CustomerDatabaseGateway customerGateway;

    public OrderDatabaseGateway(OrderRepository orderRepository, ProductDatabaseGateway productGateway, CustomerDatabaseGateway customerGateway){
        this.orderRepository = orderRepository;
        this.productGateway = productGateway;
        this.customerGateway = customerGateway;
    };
    @Override
    public void create(Order order) {
        OrderSchema schema = new OrderSchema(
                null,
                LocalDate.now(),
                order.getDescricao(),
                ProductMapper.fromProductListToSchemaList(order.getListaProdutos().stream().map(productGateway::findById).toList()),
                calculateTotalValue(order),
                CustomerMapper.fromCustomerToCustomerSchema(customerGateway.findById(order.getCustomerId())));

        System.out.println(schema);

        orderRepository.persist(schema);
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
                productGateway.findAllIds(orderSchema.getListaProdutos()),
                orderSchema.getValorTotal(),
                orderSchema.getCustomerSchema().getId()

        );
    }

    @Override
    public void update(Order order, Long id) {
        OrderSchema orderSchema = orderRepository.findByIdOptional(id).orElseThrow(() -> new OrderNotFoundException("Pedido não foi encontrado"));
        orderSchema.setDescricao(order.getDescricao());
        orderSchema.setListaProdutos(ProductMapper.fromProductListToSchemaList(order.getListaProdutos().stream().map(productGateway::findById).toList()));
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
