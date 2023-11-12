package com.kolot.jubilantwaddle.model;

import com.kolot.jubilantwaddle.enums.Duration;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe {
    @Id
    private Long id;
    private String Name;
    private Duration TYPE_OF_SUBSCRIBE;
    private Double cost;
    private LocalDateTime startSubscribe;
    private LocalDateTime nextPaymentPeriod;
    private boolean isActiv;
}
