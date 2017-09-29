package com.shipit.occupied.service.impl;

import com.shipit.occupied.dao.LocationDAO;
import com.shipit.occupied.model.Location;
import com.shipit.occupied.model.Sensor;
import com.shipit.occupied.service.LocationService;
import com.shipit.occupied.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private SensorService sensorService;

    @Override
    public Location getLocation(String locationId) {
        return locationDAO.getLocation(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = locationDAO.getAllLocations();

        List<Sensor> sensors = sensorService.getAllSensors();
        for (Location location : locations) {
            List<Sensor> sensorsForLocation = new ArrayList<>();
            for (Sensor sensor : sensors) {
                if (location.getSensorIds().contains(sensor.getId())) {
                    sensorsForLocation.add(sensor);
                }
            }
            location.setSensorData(sensorsForLocation);
        }

        return locations;
    }
}
