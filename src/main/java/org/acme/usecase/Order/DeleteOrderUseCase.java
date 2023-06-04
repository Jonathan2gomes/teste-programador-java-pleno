package org.acme.usecase.Order;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.OrderGateway;

@ApplicationScoped
public class DeleteOrderUseCase {

    OrderGateway orderGateway;

    public DeleteOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void execute(Long id){
        orderGateway.delete(id);
    }
}
