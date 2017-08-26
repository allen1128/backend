package com.ofo.service;

import com.ofo.domain.Restaurant;
import com.ofo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public void saveRestaurantInfo(List<Restaurant> restaurants) {
        restaurantRepository.save(restaurants);
    }

    @Override
    public void deleteByRestaurantId(Long restaurantId) {
        restaurantRepository.delete(restaurantId);
    }
}
