package org.acme.usecase.product;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.ProductGateway;
import org.acme.infrastructure.config.db.schema.ProductSchema;
import org.acme.infrastructure.mapper.ProductMapper;
import org.acme.usecase.OutputPage;

import java.util.stream.Collectors;

@ApplicationScoped
public class GetProductUseCase {

    private final ProductGateway productGateway;

    public GetProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public OutputPage execute(int page, int size) {
         return toPageable(productGateway.findAll(page, size));
    }

    public ProductOutput execute(Long id) {
        return ProductMapper.fromProductToProductOutput(productGateway.findById(id));
    }

    private OutputPage toPageable(PanacheQuery<ProductSchema> schema) {
        return new OutputPage(
            String.valueOf(schema.page().index),
            String.valueOf(schema.pageCount()),
            String.valueOf(schema.count()),
                schema.stream().map(product -> new ProductOutput(
                        product.getId(),
                        product.getDescricao(),
                        product.getUnidade(),
                        product.getValor()
                )).collect(Collectors.toList()));
    }
}
