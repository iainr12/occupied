package com.shipit.occupied.service;

import com.shipit.occupied.model.Location;

import java.util.List;

public interface LocationService {

    Location getLocation(String locationId);

    List<Location> getAllLocations();

}
