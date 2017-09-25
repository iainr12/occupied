package com.shipit.occupied.controller.impl;

import com.shipit.occupied.controller.SensorController;
import com.shipit.occupied.model.StateChangeRequest;
import com.shipit.occupied.model.StateChangeResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sensor/")
public class SensorControllerImpl implements SensorController {

    @Override
    @RequestMapping(value = "/{id}/heartbeat", method = RequestMethod.POST)
    public void heartbeat(@PathVariable("id") String sensorId) {

    }

    @Override
    @RequestMapping(value = "/{id}/statechange", method = RequestMethod.POST)
    public StateChangeResponse stateChange(@PathVariable("id") String sensorId, @RequestBody StateChangeRequest request) {
        return new StateChangeResponse();
    }
}
