package org.acme.infrastructure.mapper;

import org.acme.domain.model.Order;
import org.acme.infrastructure.config.db.schema.OrderSchema;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static List<Order> toOrder(List<OrderSchema> list) {
       return list.stream().map(schema -> new Order(
                schema.getId(),
                schema.getDataEmissao(),
                schema.getDescricao(),
                schema.getListaProdutos(),
                schema.getValorTotal()
        )).collect(Collectors.toList());

    }
}
