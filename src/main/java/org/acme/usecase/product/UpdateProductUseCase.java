package org.acme.usecase.product;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.ProductGateway;
import org.acme.domain.model.Product;

@ApplicationScoped
public class UpdateProductUseCase {

    private final ProductGateway productGateway;

    public UpdateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public void execute(Long id, ProductInput customerIncoming) {

        productGateway.update(id, new Product(
                null,
            customerIncoming.descricao(),
            customerIncoming.unidade(),
            customerIncoming.valor())
            );
    }
}
