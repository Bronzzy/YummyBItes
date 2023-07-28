package service;

import dao.RestaurantDAO;
import entity.Restaurant;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import service.mapper.RestaurantMapper;
import service.model.RestaurantDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;

@Stateless
public class RestaurantService {

    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

    @Inject
    private RestaurantDAO restaurantDAO;

    @Inject
    private RestaurantMapper restaurantMapper;

    public RestaurantDTO create(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .phone(restaurantDTO.getPhone())
                .openHour(restaurantDTO.getOpenHour())
                .closeHour(restaurantDTO.getCloseHour())
                .build();
        return restaurantMapper.toDto(restaurantDAO.create(restaurant));
    }
}
