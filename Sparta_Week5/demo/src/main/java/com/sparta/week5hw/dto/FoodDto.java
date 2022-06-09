package com.sparta.week5hw.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
public class FoodDto {
    private String name;
    //최소값 100원 최대값 1000000원
    @Range(min = 100, max = 1000000)
    private int price;
}
