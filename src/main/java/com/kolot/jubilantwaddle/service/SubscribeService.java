package com.kolot.jubilantwaddle.service;

import com.kolot.jubilantwaddle.model.Subscribe;
import com.kolot.jubilantwaddle.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeRepository subscribeRepository;

    public Subscribe create(Subscribe subscribe) {
        return subscribeRepository.save(subscribe);
    }

    public Subscribe getById(Long id) {
        Optional<Subscribe> subscribe = subscribeRepository.findById(id);
        if (subscribe.isPresent()) {
            return subscribe.get();
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

    public Subscribe update(Long id, Subscribe subscribe) {
        if (subscribeRepository.existsById(id)) {
            return subscribeRepository.save(subscribe);
        }
        throw new RuntimeException("Subscribe with id " + id + "not found");
    }

    public List<Subscribe> getAll() {
        return subscribeRepository.findAll();
    }
}
