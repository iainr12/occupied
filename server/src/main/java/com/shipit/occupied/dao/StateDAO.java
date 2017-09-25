package com.shipit.occupied.dao;

import com.shipit.occupied.model.State;

public interface StateDAO {

    void persistState(State state);

    State getCurrentState(String sensorId);

}
