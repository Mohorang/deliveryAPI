package com.sparta.week5hw.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
public class FoodOrderRequestDto {
    Long id;

    @Range(min = 1 , max = 100)
    int quantity;
}
