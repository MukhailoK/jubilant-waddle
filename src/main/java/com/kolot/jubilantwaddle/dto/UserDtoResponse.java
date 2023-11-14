package com.kolot.jubilantwaddle.dto;

import com.kolot.jubilantwaddle.model.Subscribe;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateOfRegistration;
    private List<Subscribe> subscribe;
}
