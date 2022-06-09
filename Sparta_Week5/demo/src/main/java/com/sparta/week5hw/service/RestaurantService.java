package com.sparta.week5hw.service;

import com.sparta.week5hw.dto.FoodDto;
import com.sparta.week5hw.dto.RestaurantDto;
import com.sparta.week5hw.model.Menu;
import com.sparta.week5hw.model.Restaurant;
import com.sparta.week5hw.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class RestaurantService {
    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo){
        this.repo = repo;
    }

    @Transactional
    public Restaurant register(RestaurantDto requestDto){
        Restaurant restaurant = new Restaurant(requestDto);
        repo.save(restaurant);
        return restaurant;
    }

    public void registerFood(Long restaurantId , List<FoodDto> dto){
        Restaurant restaurant = repo.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("음식점이 존재하지 않습니다.")
        );
        for (int i = 0; i <dto.size() ; i++){
            Menu menu = new Menu(restaurantId,dto.get(i));
            restaurant.addMenu(menu);
        }
        //메뉴이름 중복 확인하기 어떻게할까..?
        //다른 가게에서는 썻던 메뉴이름을 사용 가능
        restaurant.checkMenuName();
        //restaurant.getMenu().stream().filter()
        repo.save(restaurant);
    }
}
