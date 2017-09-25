package com.shipit.occupied.dao;

import com.shipit.occupied.model.Location;

import java.util.List;

public interface LocationDAO {

    List<Location> getAllLocations();

    Location getLocation(String id);
}
