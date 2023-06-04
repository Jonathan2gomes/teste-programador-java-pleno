package org.acme.usecase.product;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.ProductGateway;
import org.acme.domain.model.Product;

@ApplicationScoped
public class CreateProductUseCase {

    private final ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public void execute(ProductInput productInput) {
        productGateway.create(new Product(
                null,
                productInput.descricao(),
                productInput.unidade(),
                productInput.valor()
        ));
    }
}
