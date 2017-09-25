package com.shipit.occupied.dao.impl;

import com.shipit.occupied.dao.LocationDAO;
import com.shipit.occupied.model.Location;
import com.shipit.occupied.model.LocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Location> getAllLocations() {
        Location loc05wm = new Location.Builder()
                .withLocationType(LocationType.TOILET)
                .withFloor("05")
                .withZone("West")
                .withId("05WM")
                .withDisplayName("5th Floor West, Male")
                .build();
        Location loc05wf = new Location.Builder()
                .withLocationType(LocationType.TOILET)
                .withFloor("05")
                .withZone("West")
                .withId("05WF")
                .withDisplayName("5th Floor West, Female")
                .build();
        Location loc05ca = new Location.Builder()
                .withLocationType(LocationType.TOILET)
                .withFloor("05")
                .withZone("Central")
                .withId("05CA")
                .withDisplayName("5th Floor Central, Accessible")
                .build();
        Location loc05em = new Location.Builder()
                .withLocationType(LocationType.TOILET)
                .withFloor("05")
                .withZone("East")
                .withId("05EM")
                .withDisplayName("5th Floor East, Male")
                .build();
        Location loc05ef = new Location.Builder()
                .withLocationType(LocationType.TOILET)
                .withFloor("05")
                .withZone("East")
                .withId("05EF")
                .withDisplayName("5th Floor East, Female")
                .build();

        return mongoTemplate.findAll(Location.class);
    }

    @Override
    public Location getLocation(String id) {
        return mongoTemplate.findOne(Query.query(new Criteria("id").is(id)), Location.class);
    }
}
