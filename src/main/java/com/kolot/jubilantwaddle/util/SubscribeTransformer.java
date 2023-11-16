package com.kolot.jubilantwaddle.util;

import com.kolot.jubilantwaddle.dto.SubscribeDtoRequest;
import com.kolot.jubilantwaddle.dto.SubscribeDtoResponse;
import com.kolot.jubilantwaddle.model.Subscribe;
import com.kolot.jubilantwaddle.model.User;
import com.kolot.jubilantwaddle.repository.UserRepository;
import com.kolot.jubilantwaddle.validation.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscribeTransformer {
    private final UserRepository userRepository;

    public Subscribe transformFromRequestToSubscribe(SubscribeDtoRequest request) {
        Optional<User> owner = userRepository.findById(request.getUserId());
        if (owner.isEmpty()) {
            throw new NotFoundException("Request user id not found");
        }
        Subscribe subscribe = new Subscribe();
        subscribe.setName(request.getName());
        subscribe.setId(request.getUserId());
        subscribe.setActiv(request.isActiv());
        subscribe.setStartSubscribe(LocalDateTime.now());
        subscribe.setTYPE_OF_SUBSCRIBE(request.getTYPE_OF_SUBSCRIBE());
        subscribe.setNextPaymentPeriod(LocalDateTime.now().plusMonths(1L));
        subscribe.setUser(owner.get());
        return subscribe;
    }

    public SubscribeDtoResponse transformFromSubscribeToResponse(Subscribe entity) {
        SubscribeDtoResponse response = new SubscribeDtoResponse();
        response.setId(entity.getId());
        response.setCost(entity.getCost());
        response.setLinkToSubscribe(entity.getLinkToSubscribe());
        response.setName(entity.getName());
        response.setStartSubscribe(entity.getStartSubscribe());
        response.setTYPE_OF_SUBSCRIBE(entity.getTYPE_OF_SUBSCRIBE());
        response.setActiv(entity.isActiv());
        response.setNextPaymentPeriod(entity.getNextPaymentPeriod());
        return response;
    }
}
