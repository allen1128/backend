package com.ofo.repository;

import com.ofo.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
   Restaurant findByName(@Param("name") String name);
}
