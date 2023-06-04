package org.acme.usecase.Order;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.gateway.ProductGateway;
import org.acme.domain.model.Order;
import org.acme.usecase.product.ProductOutput;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public GetOrderUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public List<OrderOutput> execute(){
        return toOrderOutput(orderGateway.getAll());
    }

    private List<OrderOutput> toOrderOutput(List<Order> list){
        return list.stream().map(a -> new OrderOutput(
                a.getId(),
                a.getDataEmissao(),
                a.getDescricao(),
                a.getValorTotal(),
                a.getListaProdutos().stream().map(b -> new ProductOutput(
                        productGateway.findById(b).getId(),
                        productGateway.findById(b).getDescricao(),
                        productGateway.findById(b).getUnidade(),
                        productGateway.findById(b).getValor()
                )).collect(Collectors.toList()
        )
        )).collect(Collectors.toList());
    }
}
