package org.acme.infrastructure.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.usecase.customer.CreateCustomerUseCase;
import org.acme.usecase.customer.GetCustomerUseCase;
import org.springframework.http.HttpStatus;

@Path("/customers")
public class CustomerController {

    private final GetCustomerUseCase getCustomerUseCase;

    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(GetCustomerUseCase getCustomerUseCase, CreateCustomerUseCase createCustomerUseCase) {
        this.getCustomerUseCase = getCustomerUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @GET
    @Path("/pageable")
    @Produces("application/json")
    public Response getCustomers(@QueryParam(value = "page") int page,
                                 @QueryParam(value = "size") int size) {
        return Response.ok(getCustomerUseCase.execute(page, size)).build();
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(getCustomerUseCase.execute()).build();
    }

    @POST
    @Path("/create")
    @Produces("application/json")
    public Response create() {

        return Response.status(HttpStatus.CREATED.value()).build();
    }
}
