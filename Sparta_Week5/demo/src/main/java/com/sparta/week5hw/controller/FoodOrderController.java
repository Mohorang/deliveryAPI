package com.sparta.week5hw.controller;


import com.sparta.week5hw.dto.OrderDto;
import com.sparta.week5hw.dto.OrderRequestDto;
import com.sparta.week5hw.service.OrderService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Builder
public class FoodOrderController {

    private final OrderService service;
    @PostMapping("/order/request")
    public OrderDto Order(@RequestBody @Valid OrderRequestDto dto){
        //응답할 ResponseBody에 담길 객체는 OrderDto
        //담길 내용 : 음식점이름 , 주문 음식명 , 주문 수량 , 주문 음식의 가격 , 배달비 , 최종결제 금액
        OrderDto result = service.order(dto);
        return result;
    }
    //그동안 성공한 모든 주문 요청을 조회가능
    @GetMapping("/orders")
    public List<OrderDto> getOrders(){
        return service.getOrders();
    }
}
