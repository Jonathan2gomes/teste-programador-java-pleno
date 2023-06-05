package org.acme.infrastructure.mapper;

import org.acme.domain.model.Product;
import org.acme.infrastructure.config.db.schema.ProductSchema;
import org.acme.usecase.product.ProductOutput;

import java.util.List;

public class ProductMapper {

    public static ProductSchema updateFromCustomerToSchema(Product product, ProductSchema schema) {

        schema.setDescricao(product.getDescricao());
        schema.setUnidade(product.getUnidade());
        schema.setValor(product.getValor());

        return schema;
    }

    public static ProductOutput fromProductToProductOutput(Product product) {
        return new ProductOutput(
                product.getId(),
                product.getDescricao(),
                product.getUnidade(),
                product.getValor()
        );
    }

    public static List<ProductSchema> fromProductListToSchemaList(List<Product> list) {
        return list.stream().map(ProductMapper::fromProductToSchema).toList();
    }

    private static ProductSchema fromProductToSchema(Product product) {
        return  new ProductSchema(
                product.getId(),
                product.getDescricao(),
                product.getUnidade(),
                product.getValor()
        );
    }
}
