package com.kolot.jubilantwaddle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime dateOfRegistration;
    @OneToMany
    private List<Subscribe> subscribe;
}
