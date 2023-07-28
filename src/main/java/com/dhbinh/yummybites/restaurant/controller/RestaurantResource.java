package com.dhbinh.yummybites.restaurant.controller;

import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.restaurant.service.model.RestaurantDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/restaurants")
public class RestaurantResource {

    @Inject
    private RestaurantService restaurantService;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(RestaurantDTO restaurantDTO){
        RestaurantDTO createdRestaurant = restaurantService.create(restaurantDTO);
        return Response.created(URI.create("restaurants/" + createdRestaurant.getName()))
                .entity(createdRestaurant).status(Response.Status.CREATED).build();
    }
}