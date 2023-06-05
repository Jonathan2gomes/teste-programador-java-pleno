package org.acme.usecase.Order;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.exception.OrderInputException;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.model.Order;

@ApplicationScoped
public class CreateOrderUseCase {

    private final OrderGateway orderGateway;

    public CreateOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void execute(OrderInput orderInput){
        validateOrderInput(orderInput);
        Order order = new Order(
                orderInput.descricao(),
                orderInput.listaProdutos(),
                orderInput.customerId()
                );

        orderGateway.create(order);
    }


    private void validateOrderInput(OrderInput orderInput){
        if(orderInput.listaProdutos().isEmpty()){
            throw new OrderInputException("É necessário ao menos um produto para criar um pedido");
        }
    }
}
