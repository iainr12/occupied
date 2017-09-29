package com.shipit.occupied.dao.impl;

import com.shipit.occupied.dao.SensorDAO;
import com.shipit.occupied.model.OccupiedState;
import com.shipit.occupied.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorDAOImpl implements SensorDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Sensor getSensor(String sensorId) {
        Query query = Query.query(new Criteria("id").is(sensorId));
        return mongoTemplate.findOne(query, Sensor.class);
    }

    @Override
    public List<Sensor> getAllSensors() {
        return mongoTemplate.findAll(Sensor.class);
    }

    @Override
    public void updateState(String sensorId, OccupiedState newState) {
        Query query = Query.query(new Criteria("id").is(sensorId));
        Update update = Update.update("occupiedState", newState);
        mongoTemplate.updateFirst(query, update, Sensor.class);
    }
}
