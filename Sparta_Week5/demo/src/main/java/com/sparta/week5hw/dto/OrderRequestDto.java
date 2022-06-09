package com.sparta.week5hw.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;
}
