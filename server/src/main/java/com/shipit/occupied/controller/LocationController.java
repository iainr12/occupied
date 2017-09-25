package com.shipit.occupied.controller;

import com.shipit.occupied.model.Location;

import java.util.List;

public interface LocationController {

    List<Location> getAll();

    Location getLocation(String id);
}
