package com.shipit.occupied.service.impl;

import com.shipit.occupied.dao.SensorDAO;
import com.shipit.occupied.dao.StateDAO;
import com.shipit.occupied.model.OccupiedState;
import com.shipit.occupied.model.Sensor;
import com.shipit.occupied.model.Source;
import com.shipit.occupied.model.State;
import com.shipit.occupied.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SensorServiceImpl implements SensorService {

    private static final Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

    @Autowired
    private SensorDAO sensorDAO;

    @Autowired
    private StateDAO stateDAO;

    private ConcurrentHashMap<String, Sensor> sensorCache;
    private ConcurrentHashMap<String, OccupiedState> sensorStates;

    @PostConstruct
    private void initialise() {
        initialiseSensorStates();
    }

    private void initialiseSensorStates() {
        sensorStates = new ConcurrentHashMap<>();
        sensorCache = new ConcurrentHashMap<>();

        List<Sensor> sensors = sensorDAO.getAllSensors();
        for (Sensor sensor : sensors) {
            sensorCache.put(sensor.getId(), sensor);
            sensorStates.put(sensor.getId(), sensor.getOccupiedState());
        }
    }

    @Override
    public OccupiedState processState(String sensorId, OccupiedState state, Source source) {
        saveState(sensorId, state, source);
        return state;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return new ArrayList<>(sensorCache.values());
    }

    @Override
    public Sensor getSensor(String sensorId) {
        return sensorCache.get(sensorId);
    }

    @Override
    public OccupiedState getCurrentState(String sensorId) {
        logger.info(sensorStates.toString());
        return sensorStates.get(sensorId);
    }

    private void saveState(String sensorId, OccupiedState newState, Source source) {

        OccupiedState currentState = sensorStates.get(sensorId);
        if (currentState != newState) {
            sensorStates.put(sensorId, newState);

            Sensor sensor = sensorCache.get(sensorId);
            Sensor updatedSensor = new Sensor.Builder()
                    .withSensor(sensor)
                    .withOccupied(newState)
                    .build();
            sensorCache.put(sensorId, updatedSensor);

            sensorDAO.updateState(sensorId, newState);
        }

        State state = new State.Builder()
                .withSensorId(sensorId)
                .withOccupiedState(newState)
                .withRecordedAt(new Date())
                .withSource(source)
                .build();
        logger.info("About to persist state {}", state);
        stateDAO.persistState(state);
    }
}
