package controller;

import service.RestaurantService;
import service.model.RestaurantDTO;

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
    RestaurantService restaurantService;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(RestaurantDTO restaurantDTO) {
        RestaurantDTO restaurant = restaurantService.create(restaurantDTO);
        return Response.created(URI.create("restaurants/" + restaurant.getID())).entity(restaurant).status(Response.Status.CREATED).build();
    }
}
