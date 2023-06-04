package org.acme.usecase.Order;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.model.Order;


@ApplicationScoped
public class UpdateOrderUseCase {

    private final OrderGateway orderGateway;

    public UpdateOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void execute(OrderInput input, Long id){
        orderGateway.update(new Order(
                input.descricao(),
                input.listaProdutos()
        ), id);
    }

}
