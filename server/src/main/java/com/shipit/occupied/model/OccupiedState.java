package com.shipit.occupied.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OccupiedState {
    @JsonProperty("open")
    OPEN,

    @JsonProperty("closed")
    CLOSED;
}
