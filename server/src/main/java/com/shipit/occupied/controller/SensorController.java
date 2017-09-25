package com.shipit.occupied.controller;

import com.shipit.occupied.model.StateChangeRequest;
import com.shipit.occupied.model.StateChangeResponse;

public interface SensorController {

    void heartbeat(String sensorId);

    StateChangeResponse stateChange(String sensorId, StateChangeRequest stateChangeRequest);
}
