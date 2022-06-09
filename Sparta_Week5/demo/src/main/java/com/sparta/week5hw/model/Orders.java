package com.sparta.week5hw.model;

import com.sparta.week5hw.dto.FoodOrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Restaurant.class , fetch = FetchType.LAZY)
    @JoinColumn
    private Restaurant restaurant;

    @Column(nullable = false)
    private int totalPrice;

    @ElementCollection
    @Column(nullable = false)
    private List<FoodOrderDto> foodOrderDto = new ArrayList<>();

    public void addOrder(FoodOrderDto dto){
        this.foodOrderDto.add(dto);
        this.totalPrice += dto.getPrice();
    }

    public void checkPolicy(){
        if(this.totalPrice < restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("최소주문가격을 만족하지 않습니다.");
        }
        this.totalPrice += restaurant.getDeliveryFee();
    }

    public Orders(Restaurant restaurant){
        this.restaurant = restaurant;
        this.totalPrice = 0;
    }
}
