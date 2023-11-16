package com.kolot.jubilantwaddle.service;

import com.kolot.jubilantwaddle.dto.SubscribeDtoRequest;
import com.kolot.jubilantwaddle.dto.SubscribeDtoResponse;
import com.kolot.jubilantwaddle.model.Subscribe;
import com.kolot.jubilantwaddle.repository.SubscribeRepository;
import com.kolot.jubilantwaddle.util.SubscribeTransformer;
import com.kolot.jubilantwaddle.validation.IsAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final SubscribeTransformer transformer;

    public SubscribeDtoResponse create(SubscribeDtoRequest request) {
        if (subscribeRepository.findByName(request.getName()).isEmpty()) {
            Subscribe newSubscribe = transformer.transformFromRequestToSubscribe(request);
            return transformer.transformFromSubscribeToResponse(subscribeRepository.save(newSubscribe));
        } else
            throw new IsAlreadyExistException("Subscribe for user with id " + request.getUserId() + " is already exist!");

    }

    public SubscribeDtoResponse getById(Long id) {
        Optional<Subscribe> subscribe = subscribeRepository.findById(id);
        if (subscribe.isPresent()) {
            return transformer.transformFromSubscribeToResponse(subscribe.get());
        }
        throw new RuntimeException("Subscribe with id " + id + " not found");
    }

    public boolean delete(Long subscribeId) {
        if (subscribeRepository.existsById(subscribeId)) {
            subscribeRepository.deleteById(subscribeId);
            return true;
        }
        return false;
    }

    public SubscribeDtoResponse update(Long id, SubscribeDtoRequest request) {
        if (subscribeRepository.existsById(id)) {
            return transformer.transformFromSubscribeToResponse(subscribeRepository
                    .save(transformer.transformFromRequestToSubscribe(request)));
        }
        throw new RuntimeException("Subscribe with id " + id + "not found");
    }

    public List<SubscribeDtoResponse> getAll() {
        List<Subscribe> subscribes = subscribeRepository.findAll();
        return subscribes
                .stream()
                .map(transformer::transformFromSubscribeToResponse)
                .toList();
    }
}