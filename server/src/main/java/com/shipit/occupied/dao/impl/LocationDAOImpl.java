package com.shipit.occupied.dao.impl;

import com.shipit.occupied.dao.LocationDAO;
import com.shipit.occupied.model.Location;
import com.shipit.occupied.model.LocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Location> getAllLocations() {
        List<Sort.Order> sortOrders = new ArrayList<>();
        sortOrders.add(new Sort.Order(Sort.Direction.DESC, "floor"));
        sortOrders.add(new Sort.Order(Sort.Direction.ASC, "zone"));
        sortOrders.add(new Sort.Order(Sort.Direction.DESC, "gender"));

        Query query = new Query()
                .with(new Sort(sortOrders));

        return mongoTemplate.findAll(Location.class);
    }

    @Override
    public Location getLocation(String id) {
        return mongoTemplate.findOne(Query.query(new Criteria("id").is(id)), Location.class);
    }
}
