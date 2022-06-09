package com.sparta.week5hw.model;


import com.sparta.week5hw.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
        name = "MENU_SEQ_GENERATOR",
        sequenceName = "MENU_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 50)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MENU_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Menu(Long restaurantId , FoodDto dto){
        this.restaurantId = restaurantId;
        this.name = dto.getName();
        this.price = dto.getPrice();
    }
}
