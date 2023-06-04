package org.acme.infrastructure.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.usecase.product.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


@Path("/product")
public class ProductController {

    private final GetProductUseCase getProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(GetProductUseCase getProductUseCase, CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase) {
        this.getProductUseCase = getProductUseCase;
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @GET
    @Path("/pageable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(@QueryParam("page") int page,
                                   @QueryParam("size") int size) {

        return Response.ok(getProductUseCase.execute(page, size)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {

        return Response.ok(getProductUseCase.execute(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@RequestBody @Valid ProductInput productInput) {
        createProductUseCase.execute(productInput);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/update/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("codigo") Long codigo,
                                  @RequestBody @Valid ProductInput productInput) {
        updateProductUseCase.execute(codigo, productInput);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/delete/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("codigo") Long codigo) {

        deleteProductUseCase.execute(codigo);

        return Response.status(Response.Status.OK).build();
    }
}
