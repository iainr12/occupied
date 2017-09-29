#!/usr/bin/env python

import explorerhat
import requests
import thread
import time
import random
import sys

# Random wait interval before each door sensor starts sending values
wait_init_lower = 0
wait_init_upper = 60

# Random wait interval for a door sensor to remain "open"
wait_open_lower = 5
wait_open_upper = 750

# Random wait interval for a door sensor to remain "closed"
wait_closed_lower = 45
wait_closed_upper = 550

switched_sensor_id = "L05-WEST-MALE-1"
sensors = [
    "L00-EAST-MALE-1",   "L00-EAST-MALE-2",   "L00-EAST-MALE-3",
    "L01-EAST-MALE-1",   "L01-EAST-MALE-2",   "L01-EAST-MALE-3",
    "L02-EAST-MALE-1",   "L02-EAST-MALE-2",   "L02-EAST-MALE-3",
    "L03-EAST-MALE-1",   "L03-EAST-MALE-2",   "L03-EAST-MALE-3",
    "L04-EAST-MALE-1",   "L04-EAST-MALE-2",   "L04-EAST-MALE-3",
    "L05-EAST-MALE-1",   "L05-EAST-MALE-2",   "L05-EAST-MALE-3",
    "L06-EAST-MALE-1",   "L06-EAST-MALE-2",   "L06-EAST-MALE-3",
    "L00-EAST-FEMALE-1", "L00-EAST-FEMALE-2", "L00-EAST-FEMALE-3",
    "L01-EAST-FEMALE-1", "L01-EAST-FEMALE-2", "L01-EAST-FEMALE-3",
    "L02-EAST-FEMALE-1", "L02-EAST-FEMALE-2", "L02-EAST-FEMALE-3",
    "L03-EAST-FEMALE-1", "L03-EAST-FEMALE-2", "L03-EAST-FEMALE-3",
    "L04-EAST-FEMALE-1", "L04-EAST-FEMALE-2", "L04-EAST-FEMALE-3",
    "L05-EAST-FEMALE-1", "L05-EAST-FEMALE-2", "L05-EAST-FEMALE-3",
    "L06-EAST-FEMALE-1", "L06-EAST-FEMALE-2", "L06-EAST-FEMALE-3",
    "L00-WEST-MALE-1",   "L00-WEST-MALE-2",   "L00-WEST-MALE-3",
    "L01-WEST-MALE-1",   "L01-WEST-MALE-2",   "L01-WEST-MALE-3",
    "L02-WEST-MALE-1",   "L02-WEST-MALE-2",   "L02-WEST-MALE-3",
    "L03-WEST-MALE-1",   "L03-WEST-MALE-2",   "L03-WEST-MALE-3",
    "L04-WEST-MALE-1",   "L04-WEST-MALE-2",   "L04-WEST-MALE-3",
                         "L05-WEST-MALE-2",   "L05-WEST-MALE-3",
    "L06-WEST-MALE-1",   "L06-WEST-MALE-2",   "L06-WEST-MALE-3",
    "L00-WEST-FEMALE-1", "L00-WEST-FEMALE-2", "L00-WEST-FEMALE-3",
    "L01-WEST-FEMALE-1", "L01-WEST-FEMALE-2", "L01-WEST-FEMALE-3",
    "L02-WEST-FEMALE-1", "L02-WEST-FEMALE-2", "L02-WEST-FEMALE-3",
    "L03-WEST-FEMALE-1", "L03-WEST-FEMALE-2", "L03-WEST-FEMALE-3",
    "L04-WEST-FEMALE-1", "L04-WEST-FEMALE-2", "L04-WEST-FEMALE-3",
    "L05-WEST-FEMALE-1", "L05-WEST-FEMALE-2", "L05-WEST-FEMALE-3",
    "L06-WEST-FEMALE-1", "L06-WEST-FEMALE-2", "L06-WEST-FEMALE-3"
    ]

def get_statechange_url(sensor_id):
    return "http://172.20.10.3:8080/sensor/" + sensor_id + "/statechange"

def get_heartbeat_url(sensor_id):
    return "http://172.20.10.3:8080/sensor/" + sensor_id + "/heartbeat"

def handle_state_change(pin):
    door_state = ''
    if (pin.read()):
	door_state = "closed"
    else:
	door_state = "open"

    print("Switch is " + door_state)
    send_state_change_request(switched_sensor_id, door_state)

def send_state_change_request(sensor_id, door_state):
    print("Door " + sensor_id + " is now " + door_state)
    try:
        r = requests.post(get_statechange_url(sensor_id), json=door_state)
    except:
        print "Couldn't publish " + sensor_id + "=" + door_state


def stochastic_state_change(sensor_id):
    door_state = "open"

    # Introduce random initial delays for each toilet
    random_delay = random.randint(wait_init_lower, wait_init_upper)
    time.sleep(random_delay)

    while True:
        if (door_state == "open"):
            random_delay = random.randint(wait_open_lower, wait_open_upper)
            door_state = "closed"
        else:
            random_delay = random.randint(wait_closed_lower, wait_closed_upper)
            door_state = "open"

        time.sleep(random_delay)
        send_state_change_request(sensor_id, door_state)

# Determine wait values
if len(sys.argv) > 1:
    print "DEMO MODE: Using lower wait intervals..."
    wait_init_lower = 0
    wait_init_upper = 10
    wait_open_lower = 3
    wait_open_upper = 15
    wait_closed_lower = 2
    wait_closed_upper = 10

# Set up threads
for sensor in sensors:
    try :
        thread.start_new_thread(stochastic_state_change, (sensor,))
    except:
        print "Couldn't start thread for sensor" + sensor

# Listen for state changes on the reed switch
explorerhat.input.changed(handle_state_change)
explorerhat.pause()