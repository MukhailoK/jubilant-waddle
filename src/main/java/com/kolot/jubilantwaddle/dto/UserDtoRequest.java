package com.kolot.jubilantwaddle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
