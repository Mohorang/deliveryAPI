package com.sparta.week5hw.dto;

import com.sparta.week5hw.model.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDto(Orders Order){
        this.restaurantName = Order.getRestaurant().getName();
        this.foods = Order.getFoodOrderDto();
        this.deliveryFee = Order.getRestaurant().getDeliveryFee();
        this.totalPrice = Order.getTotalPrice();
    }

}
