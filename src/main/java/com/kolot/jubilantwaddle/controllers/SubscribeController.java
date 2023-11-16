package com.kolot.jubilantwaddle.controllers;

import com.kolot.jubilantwaddle.dto.SubscribeDtoRequest;
import com.kolot.jubilantwaddle.dto.SubscribeDtoResponse;
import com.kolot.jubilantwaddle.service.SubscribeService;
import com.kolot.jubilantwaddle.validation.IsAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subscribe")
public class SubscribeController {
    private final SubscribeService subscribeService;

    @PostMapping("/create")
    public ResponseEntity<SubscribeDtoResponse> createSubscribe(@RequestBody SubscribeDtoRequest request) {
        try {
            SubscribeDtoResponse response = subscribeService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IsAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscribeDtoResponse> getSubscribeById(@PathVariable Long id) {
        try {
            SubscribeDtoResponse response = subscribeService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscribe(@PathVariable Long id) {
        if (subscribeService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubscribeDtoResponse> updateSubscribe(@PathVariable Long id, @RequestBody SubscribeDtoRequest request) {
        try {
            SubscribeDtoResponse response = subscribeService.update(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubscribeDtoResponse>> getAllSubscribes() {
        List<SubscribeDtoResponse> subscribes = subscribeService.getAll();
        return new ResponseEntity<>(subscribes, HttpStatus.OK);
    }
}
