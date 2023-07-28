package dao;

import java.util.List;
import java.util.Optional;

/**
 * Contract for a generic DAO.
 *
 * @param <T> - Entity type parameter.
 */
public interface BaseDAO <T>{

    List<T> findAll();

    Optional<T> findByID();

    T create(T t);

    T Update(T t);
}
