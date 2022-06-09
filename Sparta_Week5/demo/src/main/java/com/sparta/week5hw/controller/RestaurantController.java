package com.sparta.week5hw.controller;

import com.sparta.week5hw.dto.FoodDto;
import com.sparta.week5hw.dto.RestaurantDto;
import com.sparta.week5hw.model.Menu;
import com.sparta.week5hw.model.Restaurant;
import com.sparta.week5hw.repository.RestaurantRepository;
import com.sparta.week5hw.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository repo;

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody @Valid RestaurantDto requestDto){
        return restaurantService.register(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant(){
        return repo.findAll();
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId , @RequestBody @Valid List<FoodDto> dto){
        restaurantService.registerFood(restaurantId,dto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Menu> getFood(@PathVariable Long restaurantId){
        return repo.findById(restaurantId).get().getMenu();
    }
}
