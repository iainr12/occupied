package com.shipit.occupied.controller;

import com.shipit.occupied.model.StateRequest;
import com.shipit.occupied.model.StateResponse;

public interface SensorController {

    StateResponse heartbeat(String sensorId, StateRequest stateRequest);

    StateResponse stateChange(String sensorId, StateRequest stateRequest);
}
