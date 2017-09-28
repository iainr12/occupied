#!/usr/bin/env python

import explorerhat
import requests

sensor_id = "L05-WEST-MALE-1"

def state_change(pin):
    door_state = ''
    if (pin.read()):
	door_state = "closed"
    else:
	door_state = "open"

    r = requests.post("http://172.20.10.3:8080/sensor/" + sensor_id + "/statechange", json=door_state)
    print("Got response (" + str(r.status_code) + ")" + r.text)


explorerhat.input.changed(state_change)
explorerhat.pause()