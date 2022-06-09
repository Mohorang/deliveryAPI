package com.sparta.week5hw.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
public class RestaurantDto {
    private String name;

    @Range(min = 1000, max = 100000)
    private int minOrderPrice;

    @Range(min = 0, max = 10000)
    private int deliveryFee;
}
