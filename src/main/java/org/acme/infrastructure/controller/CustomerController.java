package org.acme.infrastructure.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.usecase.customer.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/customer")
public class CustomerController {

    private final GetCustomerUseCase getCustomerUseCase;

    private final CreateCustomerUseCase createCustomerUseCase;

    private final UpdateCustomerUseCase updateCustomerUseCase;

    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerController(GetCustomerUseCase getCustomerUseCase, CreateCustomerUseCase createCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase) {
        this.getCustomerUseCase = getCustomerUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("codigo") Long codigo) {
        return Response.ok(getCustomerUseCase.execute(codigo)).build();
    }

    @GET
    @Path("/pageable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(@QueryParam(value = "page") int page,
                                 @QueryParam(value = "size") int size) {
        return Response.ok(getCustomerUseCase.execute(page, size)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody @Valid CustomerInput input) {
        createCustomerUseCase.execute(input);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/update/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") Long id, @RequestBody @Valid CustomerInput input) {
        updateCustomerUseCase.execute(id, input);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/delete/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("codigo") Long id) {
        deleteCustomerUseCase.execute(id);

        return Response.status(Response.Status.OK).entity("Cliente com codigo: " +id+ " foi deletado com sucesso!").build();
    }

}
