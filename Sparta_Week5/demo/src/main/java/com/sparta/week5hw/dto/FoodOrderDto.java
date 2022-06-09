package com.sparta.week5hw.dto;


import com.sparta.week5hw.model.Menu;
import com.sparta.week5hw.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class FoodOrderDto {
    String name;
    int quantity;
    int price;
    public FoodOrderDto(Menu menu , int quantity){
        if(quantity <1){
            throw new IllegalArgumentException("최소 1개이상 주문해주세요");
        }
        if(quantity >100){
            throw new IllegalArgumentException("1회 주문시 100개를 초과하실수 없습니다.");
        }
        this.name = menu.getName();
        this.quantity = quantity;
        this.price = menu.getPrice() * quantity;
    }
}
