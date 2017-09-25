package com.shipit.occupied.controller.impl;

import com.shipit.occupied.controller.LocationController;
import com.shipit.occupied.dao.LocationDAO;
import com.shipit.occupied.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationControllerImpl implements LocationController {

    @Autowired
    private LocationDAO locationDAO;

    @RequestMapping(method = RequestMethod.GET)
    public List<Location> getAll() {
        return locationDAO.getAllLocations();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Location getLocation(@PathVariable("id") String id) {
        return locationDAO.getLocation(id);
    }

}
