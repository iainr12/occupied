package com.shipit.occupied.model;

public class StateChangeRequest {

    private final boolean occupied;

    private StateChangeRequest(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateChangeRequest that = (StateChangeRequest) o;

        return occupied == that.occupied;
    }

    @Override
    public int hashCode() {
        return (occupied ? 1 : 0);
    }

    @Override
    public String toString() {
        return "StateChangeRequest{" +
                "occupied=" + occupied +
                '}';
    }
}
