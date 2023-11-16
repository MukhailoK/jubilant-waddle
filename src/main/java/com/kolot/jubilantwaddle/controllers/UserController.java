package com.kolot.jubilantwaddle.controllers;

import com.kolot.jubilantwaddle.dto.SubscribeDtoRequest;
import com.kolot.jubilantwaddle.dto.SubscribeDtoResponse;
import com.kolot.jubilantwaddle.dto.UserDtoRequest;
import com.kolot.jubilantwaddle.dto.UserDtoResponse;
import com.kolot.jubilantwaddle.service.UserService;
import com.kolot.jubilantwaddle.validation.IsAlreadyExistException;
import com.kolot.jubilantwaddle.validation.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;


    @PostMapping("/")
    public ResponseEntity<UserDtoResponse> addUser(@RequestBody UserDtoRequest request) {
        try {
            UserDtoResponse response = service.createUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IsAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(@PathVariable Long id, @RequestBody UserDtoRequest request) {
        try {
            UserDtoResponse response = service.updateUser(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/add/subscribe")
    private ResponseEntity<UserDtoResponse> addSubscribe(@PathVariable Long id, @RequestBody SubscribeDtoRequest request) {
        try {
            UserDtoResponse response = service.addSubscribe(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (service.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
        List<UserDtoResponse> users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}/subscribes")
    public ResponseEntity<List<SubscribeDtoResponse>> getAllSubscribeByUserId(@PathVariable Long userId) {
        try {
            List<SubscribeDtoResponse> subscribes = service.getAllSubscribeByUserId(userId);
            return new ResponseEntity<>(subscribes, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/monthly-amount")
    public ResponseEntity<Double> getMonthlyAmountByUserId(@PathVariable Long id) {
        try {
            Double monthlyAmount = service.getMonthlyAmountByUserId(id);
            return new ResponseEntity<>(monthlyAmount, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
