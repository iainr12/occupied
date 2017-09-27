package com.shipit.occupied.config;

import com.shipit.occupied.dao.SensorDAO;
import com.shipit.occupied.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitialisation {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @PostConstruct
    public void createSensors() {
        List<Sensor> sensors = new ArrayList<>();

        for (int floor = 0; floor < 7; floor++) {
            for (int eastWest = 0; eastWest < 2; eastWest++) {
                for (int maleFemale = 0; maleFemale < 2; maleFemale++) {
                    String genderString = "Male";
                    String wingString = "East";
                    Gender gender = Gender.MALE;
                    if (eastWest == 1) {
                        wingString = "West";
                    }
                    if (maleFemale == 1) {
                        genderString = "Female";
                        gender = Gender.FEMALE;
                    }

                    List<String> sensorsForFloor = new ArrayList<>();
                    for (int stall = 1; stall < 4; stall++) {
                        String id = "L0" + floor + "-" + wingString.toUpperCase() + "-" + genderString.toUpperCase() + "-" + stall;
                        Sensor sensor = new Sensor.Builder()
                                .withId(id)
                                .withPosition(Integer.toString(stall))
                                .withDisplayName("L" + floor + " " + wingString + ", " + genderString + " Toilets, Stall " + stall)
                                .withOccupied(OccupiedState.OPEN)
                                .build();
                        sensorsForFloor.add(id);
                        //mongoTemplate.save(sensor);
                        System.out.println(sensor.toString());
                    }

                    Location location = new Location.Builder()
                            .withId("L0" + floor + "-" + wingString.toUpperCase() + "-" + genderString.toUpperCase())
                            .withFloor(Integer.toString(floor))
                            .withZone(wingString.toUpperCase())
                            .withLocationType(LocationType.TOILET)
                            .withGender(gender)
                            .withDisplayName("L" + floor + " " + wingString + ", " + genderString + " Toilets")
                            .withSensors(sensorsForFloor)
                            .build();

                    //mongoTemplate.save(location);
                }
            }

            Sensor sensor = new Sensor.Builder()
                    .withId("L0" + floor + "-CENTRAL-ACCESSIBLE")
                    .withPosition("0")
                    .withDisplayName("L" + floor + " Central, Accessible Toilet")
                    .withOccupied(OccupiedState.OPEN)
                    .build();
            //mongoTemplate.save(sensor);
        }

    }
}
