package org.acme.usecase.Order;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.exception.OrderInputException;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.gateway.ProductGateway;
import org.acme.domain.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public CreateOrderUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public void execute(OrderInput orderInput){
        validateOrderInput(orderInput);
        Order order = new Order(
                null,
                LocalDate.now(),
                orderInput.descricao(),
                orderInput.listaProdutos(),
                calculateTotalValue(orderInput));

        orderGateway.create(order);
    }

    private Double calculateTotalValue(OrderInput orderInput){

        List<Double> totalValues = new ArrayList<>();

        orderInput.listaProdutos().forEach(id ->
            totalValues.add(productGateway.findById(id).getValor()));


        return  totalValues.stream().mapToDouble(f -> f).sum();
    }

    private void validateOrderInput(OrderInput orderInput){
        if(orderInput.listaProdutos().isEmpty()){
            throw new OrderInputException("É necessário ao menos um produto para criar um pedido");
        }
    }
}
