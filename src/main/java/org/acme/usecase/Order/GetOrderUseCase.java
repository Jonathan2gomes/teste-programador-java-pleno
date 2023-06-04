package org.acme.usecase.Order;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.OrderGateway;
import org.acme.domain.gateway.ProductGateway;
import org.acme.domain.model.Order;
import org.acme.domain.model.Product;
import org.acme.usecase.product.ProductOutput;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
        return toOrderOutputList(orderGateway.getAll());
    }

    public OrderOutput execute(Long id){
        return toOrderOutput(orderGateway.findById(id));
    }

    private OrderOutput toOrderOutput(Order list){
        return  new OrderOutput(
                list.getId(),
                list.getDataEmissao(),
                list.getDescricao(),
                list.getValorTotal(),
                list.getListaProdutos().stream().map(b -> new ProductOutput(
                        productGateway.findById(b).getId(),
                        productGateway.findById(b).getDescricao(),
                        productGateway.findById(b).getUnidade(),
                        productGateway.findById(b).getValor()
                )).collect(Collectors.toList()
        )
        );
    }

//    private List<OrderOutput> toOrderOutputList(List<Order> list){
//        return list.stream().map(orderOutput -> new OrderOutput(
//                orderOutput.getId(),
//                orderOutput.getDataEmissao(),
//                orderOutput.getDescricao(),
//                orderOutput.getValorTotal(),
//                orderOutput.getListaProdutos().stream().map(b -> new ProductOutput(
//                        productGateway.findById(b).getId(),
//                        productGateway.findById(b).getDescricao(),
//                        productGateway.findById(b).getUnidade(),
//                        productGateway.findById(b).getValor()
//                )).collect(Collectors.toList()
//                )
//        )).collect(Collectors.toList());
//    }
    private List<OrderOutput> toOrderOutputList(List<Order> list) {
        List<Long> productIds = list.stream()
                .flatMap(order -> order.getListaProdutos().stream())
                .collect(Collectors.toList());

        Map<Long, Product> productMap = productGateway.findByIds(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        return list.stream().map(order -> {
            List<ProductOutput> productOutputs = order.getListaProdutos().stream()
                    .map(productId -> {
                        Product product = productMap.get(productId);
                        return new ProductOutput(
                                product.getId(),
                                product.getDescricao(),
                                product.getUnidade(),
                                product.getValor()
                        );
                    })
                    .collect(Collectors.toList());

            return new OrderOutput(
                    order.getId(),
                    order.getDataEmissao(),
                    order.getDescricao(),
                    order.getValorTotal(),
                    productOutputs
            );
        }).collect(Collectors.toList());
    }
}
