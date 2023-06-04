package org.acme.infrastructure.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.usecase.Order.CreateOrderUseCase;
import org.acme.usecase.Order.GetOrderUseCase;
import org.acme.usecase.Order.OrderInput;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/order")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
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
}
