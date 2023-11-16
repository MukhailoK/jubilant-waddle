package com.kolot.jubilantwaddle.dto;

import com.kolot.jubilantwaddle.enums.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDtoResponse {
    private Long id;
    private String Name;
    private String linkToSubscribe;
    private String TYPE_OF_SUBSCRIBE;
    private Double cost;
    private LocalDateTime startSubscribe;
    private LocalDateTime nextPaymentPeriod;
    private boolean isActiv;
}