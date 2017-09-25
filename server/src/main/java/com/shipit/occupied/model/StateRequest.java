package com.shipit.occupied.model;

public class StateRequest {

    private final OccupiedState occupiedState;

    private StateRequest(OccupiedState occupiedState) {
        this.occupiedState = occupiedState;
    }

    public OccupiedState getOccupiedState() {
        return occupiedState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateRequest that = (StateRequest) o;

        return occupiedState == that.occupiedState;
    }

    @Override
    public int hashCode() {
        return occupiedState != null ? occupiedState.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StateRequest{" +
                "occupiedState=" + occupiedState +
                '}';
    }
}
