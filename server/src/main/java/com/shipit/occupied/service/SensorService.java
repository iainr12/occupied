package com.shipit.occupied.service;

import com.shipit.occupied.model.OccupiedState;
import com.shipit.occupied.model.Source;

public interface SensorService {

    OccupiedState processState(String sensorId, OccupiedState state, Source source);

    OccupiedState getCurrentState(String sensorId);

}
