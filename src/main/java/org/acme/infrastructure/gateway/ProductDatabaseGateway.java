package org.acme.infrastructure.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.exception.ProductNotFoundException;
import org.acme.domain.gateway.ProductGateway;
import org.acme.domain.model.Product;
import org.acme.infrastructure.config.db.repository.ProductRepository;
import org.acme.infrastructure.config.db.schema.ProductSchema;
import org.acme.infrastructure.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class ProductDatabaseGateway implements ProductGateway {

    private final ProductRepository productRepository;

    public ProductDatabaseGateway(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public PanacheQuery<ProductSchema> findAll(int page, int size) {
        return productRepository.findAll().page(page, size);
    }



    @Override
    public void create(Product product) {
        productRepository.persist(new ProductSchema(
                product.getId(),
                product.getDescricao(),
                product.getUnidade(),
                product.getValor()
        ));
    }

    @Override
    public void update(Long id, Product product) {
        ProductSchema productSchema = productRepository.findByIdOptional(id).orElseThrow(ProductNotFoundException::new);
        productRepository.persist(ProductMapper.updateFromCustomerToSchema(product, productSchema));
    }

    @Override
    public void delete(Long id) {
        productRepository.findByIdOptional(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        ProductSchema productSchema = productRepository.findByIdOptional(id).orElseThrow(ProductNotFoundException::new);
        return new Product(
                productSchema.getId(),
                productSchema.getDescricao(),
                productSchema.getUnidade(),
                productSchema.getValor());
    }

    @Override
    public List<Product> findByIds(List<Long> productIds) {
        List<ProductSchema> productSchema = productRepository.list("id in ?1", productIds);
        return productSchema.stream().map(product -> new Product(
                product.getId(),
                product.getDescricao(),
                product.getUnidade(),
                product.getValor())).collect(Collectors.toList());
    }

//    @Override
//    public List<Long> findIdsByIds(List<Long> idList) {
//        return null;
//    }

    public List<Long> findAllIds(List<ProductSchema> productSchema) {

        return productSchema.stream().map(ProductSchema::getId).toList();
    }
}
