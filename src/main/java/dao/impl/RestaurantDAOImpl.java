package dao.impl;

import dao.RestaurantDAO;
import entity.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class RestaurantDAOImpl implements RestaurantDAO {
    @PersistenceContext(name = "yummybites")
    private EntityManager em;

    @Override
    public List<Restaurant> findAll() {
        return null;
    }

    @Override
    public Optional<Restaurant> findByID() {
        return Optional.empty();
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        em.persist(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant Update(Restaurant restaurant) {
        return null;
    }
}
