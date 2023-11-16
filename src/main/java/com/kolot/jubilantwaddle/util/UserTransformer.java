package com.kolot.jubilantwaddle.util;

import com.kolot.jubilantwaddle.dto.UserDtoRequest;
import com.kolot.jubilantwaddle.dto.UserDtoResponse;
import com.kolot.jubilantwaddle.model.User;
import com.kolot.jubilantwaddle.repository.UserRepository;
import com.kolot.jubilantwaddle.validation.IsAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserTransformer {

    private final UserRepository userRepository;

    public User transformFromRequestToUser(UserDtoRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new IsAlreadyExistException("Request user id already exist.");
        }
        User entity = new User();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setDateOfRegistration(LocalDateTime.now());
        return entity;
    }

    public UserDtoResponse transformFromUserToResponse(User entity) {
        UserDtoResponse response = new UserDtoResponse();
        response.setId(entity.getId());
        response.setEmail(entity.getEmail());
        response.setLastName(entity.getLastName());
        response.setFirstName(entity.getFirstName());
        response.setDateOfRegistration(entity.getDateOfRegistration());
        response.setSubscribe(entity.getSubscribe());
        return response;
    }
}
