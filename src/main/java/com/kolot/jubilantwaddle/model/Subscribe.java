package com.kolot.jubilantwaddle.model;

import com.kolot.jubilantwaddle.enums.Duration;
import jakarta.persistence.*;
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
@Table(name = "subscribe")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String linkToSubscribe;
    private String TYPE_OF_SUBSCRIBE;
    private Double cost;
    private LocalDateTime startSubscribe;
    private LocalDateTime nextPaymentPeriod;
    @ManyToOne
    private User user;
    private boolean isActiv;
}