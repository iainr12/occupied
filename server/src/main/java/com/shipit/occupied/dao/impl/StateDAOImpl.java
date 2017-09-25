package com.shipit.occupied.dao.impl;

import com.shipit.occupied.dao.StateDAO;
import com.shipit.occupied.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class StateDAOImpl implements StateDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void persistState(State state) {
        mongoTemplate.save(state);
    }

    @Override
    public State getCurrentState(String sensorId) {

        Query query = new Query(new Criteria("sensorId").is(sensorId))
                .with(new Sort(Sort.Direction.DESC, "recordedAt"));

        return mongoTemplate.findOne(query, State.class);
    }
}
