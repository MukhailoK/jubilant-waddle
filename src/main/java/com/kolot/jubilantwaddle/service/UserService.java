package com.kolot.jubilantwaddle.service;

import com.kolot.jubilantwaddle.dto.SubscribeDtoRequest;
import com.kolot.jubilantwaddle.dto.SubscribeDtoResponse;
import com.kolot.jubilantwaddle.dto.UserDtoRequest;
import com.kolot.jubilantwaddle.dto.UserDtoResponse;
import com.kolot.jubilantwaddle.model.Subscribe;
import com.kolot.jubilantwaddle.model.User;
import com.kolot.jubilantwaddle.repository.SubscribeRepository;
import com.kolot.jubilantwaddle.repository.UserRepository;
import com.kolot.jubilantwaddle.util.SubscribeTransformer;
import com.kolot.jubilantwaddle.util.UserTransformer;
import com.kolot.jubilantwaddle.validation.IsAlreadyExistException;
import com.kolot.jubilantwaddle.validation.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTransformer transformer;
    private final SubscribeTransformer subscribeTransformer;
    private final SubscribeRepository subscribeRepository;

    public UserDtoResponse createUser(UserDtoRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            User newUser = transformer.transformFromRequestToUser(request);
            return transformer.transformFromUserToResponse(userRepository.save(newUser));
        } else
            throw new IsAlreadyExistException("Manager with email: " + request.getEmail() + " is already exist!");

    }

    public UserDtoResponse updateUser(Long id, UserDtoRequest request) {
        if (userRepository.existsById(id)) {
            return transformer.transformFromUserToResponse(userRepository
                    .save(transformer.transformFromRequestToUser(request)));
        }
        throw new NotFoundException("User with id " + id + " not found");
    }

    public UserDtoResponse addSubscribe(Long id, SubscribeDtoRequest request) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getReferenceById(id);
            user.getSubscribe().add(subscribeTransformer.transformFromRequestToSubscribe(request));
            return transformer.transformFromUserToResponse(userRepository.save(user));
        }
        throw new NotFoundException("User with id " + id + " not found");
    }

    public UserDtoResponse stopSubscribe(Long userId, Long subscribeId) {
        if (userRepository.existsById(userId) && subscribeRepository.existsById(subscribeId)) {
            Optional<User> optionalUser = userRepository.findById(userId);
            return optionalUser.map(user -> {
                user.getSubscribe().stream()
                        .filter(subscribe -> subscribe.getId().equals(subscribeId) && subscribe.isActiv())
                        .forEach(subscribe -> subscribe.setActiv(false));


                return transformer.transformFromUserToResponse(userRepository.save(user));
            }).orElse(null);
        }
        throw new NotFoundException("User with id " + userId + " not found");
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<UserDtoResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(transformer::transformFromUserToResponse)
                .toList();
    }

    public List<SubscribeDtoResponse> getAllSubscribeByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get()
                    .getSubscribe()
                    .stream()
                    .map(subscribeTransformer::transformFromSubscribeToResponse)
                    .toList();
        }
        throw new NotFoundException("User with id " + userId + " not found");
    }

    public Double getMonthlyAmountByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getSubscribe().stream().mapToDouble(Subscribe::getCost).sum();
        }
        throw new NotFoundException("User with id " + id + " not found");
    }
}
