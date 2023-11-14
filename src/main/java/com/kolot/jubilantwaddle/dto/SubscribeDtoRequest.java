package com.kolot.jubilantwaddle.dto;


import com.kolot.jubilantwaddle.enums.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscribeDtoRequest {
    private String Name;
    private Long userId;
    private Duration TYPE_OF_SUBSCRIBE;
    private Double cost;
    private boolean isActiv;
}
