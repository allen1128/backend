package com.ofo.repository;

import com.ofo.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
   List<Restaurant> findByName(@Param("name") String name);
}
