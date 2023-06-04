package org.acme.infrastructure.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.usecase.Order.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/order")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, UpdateOrderUseCase updateOrderUseCase, DeleteOrderUseCase deleteOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(@RequestBody @Valid OrderInput orderInput ){
        createOrderUseCase.execute(orderInput);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(){
        return Response.ok(getOrderUseCase.execute()).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderByCodigo(@PathParam("codigo") Long codigo){
        return Response.ok(getOrderUseCase.execute(codigo)).build();
    }

    @PUT
    @Path("update/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("codigo") Long codigo, @RequestBody @Valid OrderInput orderInput){
        updateOrderUseCase.execute(orderInput, codigo);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("codigo") Long codigo){
        deleteOrderUseCase.execute(codigo);

        return Response.status(Response.Status.OK).build();
    }
}
