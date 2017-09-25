package com.shipit.occupied.dao;

import com.shipit.occupied.model.OccupiedState;
import com.shipit.occupied.model.Sensor;

import java.util.List;

public interface SensorDAO {

    List<Sensor> getAllSensors();

    void updateState(String sensorId, OccupiedState newState);
}
