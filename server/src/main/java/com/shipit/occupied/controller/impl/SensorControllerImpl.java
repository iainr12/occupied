package com.shipit.occupied.controller.impl;

import com.shipit.occupied.controller.SensorController;
import com.shipit.occupied.model.OccupiedState;
import com.shipit.occupied.model.Source;
import com.shipit.occupied.model.StateRequest;
import com.shipit.occupied.model.StateResponse;
import com.shipit.occupied.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.util.Date;

@RestController
@RequestMapping(value = "/sensor/")
public class SensorControllerImpl implements SensorController {

    @Autowired
    private SensorService sensorService;

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StateResponse getCurrentState(@PathVariable("id") String sensorId) {
        OccupiedState response = sensorService.getCurrentState(sensorId);

        return new StateResponse.Builder()
                .withOccupiedState(response)
                .build();
    }

    @Override
    @RequestMapping(value = "/{id}/heartbeat", method = RequestMethod.POST)
    public StateResponse heartbeat(@PathVariable("id") String sensorId, @RequestBody StateRequest request) {
        OccupiedState currentState = request.getOccupiedState();
        OccupiedState response = sensorService.processState(sensorId, currentState, Source.HEARTBEAT);

        return new StateResponse.Builder()
                .withOccupiedState(response)
                .build();
    }

    @Override
    @RequestMapping(value = "/{id}/statechange", method = RequestMethod.POST)
    public StateResponse stateChange(@PathVariable("id") String sensorId, @RequestBody StateRequest request) {
        OccupiedState newState = request.getOccupiedState();
        OccupiedState response = sensorService.processState(sensorId, newState, Source.STATE_CHANGE);

        return new StateResponse.Builder()
                .withOccupiedState(response)
                .build();
    }
}
