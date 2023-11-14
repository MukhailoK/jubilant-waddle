package com.kolot.jubilantwaddle.model;

import com.kolot.jubilantwaddle.enums.Duration;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscribes")
public class Subscribe {
    @Id
    private Long id;
    private String Name;
    private String linkToSubscribe;
    private Duration TYPE_OF_SUBSCRIBE;
    private Double cost;
    private LocalDateTime startSubscribe;
    private LocalDateTime nextPaymentPeriod;
    @ManyToOne
    private User user;
    private boolean isActiv;
}