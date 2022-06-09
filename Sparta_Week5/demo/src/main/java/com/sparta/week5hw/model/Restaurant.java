package com.sparta.week5hw.model;

import com.sparta.week5hw.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
        name = "RESTAURANT_SEQ_GENERATOR",
        sequenceName = "RESTAURANT_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 10)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "RESTAURANT_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    //레스토랑 --> 메뉴이름 1:N 관계생성
    @OneToMany(targetEntity = Menu.class , fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Menu> menu = new ArrayList<>();

    public void addMenu(Menu menu){
        if(menu.getPrice() < 100){
            throw new IllegalArgumentException("100원 미만에러");
        }
        if(menu.getPrice() >1000000){
            throw new IllegalArgumentException("100000원 초과에러");
        }
        if(menu.getPrice()%100 != 0){
            throw new IllegalArgumentException("100원 단위로 입력 안 됨 에러");
        }
        this.menu.add(menu);
    }

    public void checkMenuName(){
        List<Menu> test = menu.stream().filter(distinctByKey(g -> g.getName())).collect(Collectors.toList());
        if(!test.equals(menu)){
            throw new IllegalArgumentException("같은 이름의 음식을 등록할 수 없습니다.");
        }
    }
    public Restaurant(RestaurantDto dto){
        //최소주문가격의 에러 발생요소
        if(dto.getMinOrderPrice()%100 != 0){
            throw new IllegalArgumentException("최소주문가격의 단위는 100원입니다.");
        }
        //기본배달비 허용값
        if(dto.getDeliveryFee()%500 != 0){
            throw new IllegalArgumentException("기본 배달비의 단위는 500원입니다.");
        }

        this.name = dto.getName();
        this.minOrderPrice = dto.getMinOrderPrice();
        this.deliveryFee = dto.getDeliveryFee();
    }

    public static <T> Predicate<T> distinctByKey( Function<? super T, Object> keyExtractor) {
        HashMap<Object, Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
