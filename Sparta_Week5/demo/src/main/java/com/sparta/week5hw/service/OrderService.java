package com.sparta.week5hw.service;

import com.sparta.week5hw.dto.FoodOrderDto;
import com.sparta.week5hw.dto.OrderDto;
import com.sparta.week5hw.dto.OrderRequestDto;
import com.sparta.week5hw.model.Menu;
import com.sparta.week5hw.model.Restaurant;
import com.sparta.week5hw.model.Orders;
import com.sparta.week5hw.repository.MenuRepository;
import com.sparta.week5hw.repository.OrderRepository;
import com.sparta.week5hw.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestaurantRepository restaurantRepo;
    private final MenuRepository menuRepo;
    private final OrderRepository orderRepo;

    public OrderDto order(OrderRequestDto dto){
        Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("음식점 id가 존재하지 않습니다.")
        );
        Orders Order = new Orders(restaurant);

        //FoodOrderDto를 어떻게 정리할것인가.
        for (int i = 0; i < dto.getFoods().size() ; i++) {
            Menu menu = menuRepo.findById(dto.getFoods().get(i).getId()).get();
            int quantity = dto.getFoods().get(i).getQuantity();
            FoodOrderDto foodOrderDto = new FoodOrderDto(menu , quantity);
            Order.addOrder(foodOrderDto);
        }
        Order.checkPolicy();
        orderRepo.save(Order);

        return new OrderDto(Order);
    }
    public List<OrderDto> getOrders(){
        List<Orders> result = orderRepo.findAll();
        List<OrderDto> dtoResult = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            dtoResult.add(new OrderDto(result.get(i)));
        }
        return dtoResult;
    }
}
