package com.shipit.occupied.service;

import com.shipit.occupied.model.OccupiedState;
import com.shipit.occupied.model.Sensor;
import com.shipit.occupied.model.Source;

import java.util.List;

public interface SensorService {

    OccupiedState processState(String sensorId, OccupiedState state, Source source);

    OccupiedState getCurrentState(String sensorId);

    List<Sensor> getAllSensors();

    Sensor getSensor(String sensorId);

    void resetAll();
}
